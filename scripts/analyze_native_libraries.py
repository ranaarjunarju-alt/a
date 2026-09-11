"""Inspect all packaged ELF libraries without loading or executing them."""
import hashlib, io, json, re, sys, zipfile
from pathlib import Path
from elftools.elf.elffile import ELFFile
src, out = Path(sys.argv[1]), Path(sys.argv[2]); out.mkdir(parents=True,exist_ok=True)
EXPECTED='46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f'
with src.open('rb') as f:
    if hashlib.file_digest(f,'sha256').hexdigest()!=EXPECTED: raise SystemExit('APK mismatch')
pattern=re.compile(r'text.?to.?speech|elevenlabs|11labs|sami|speechengine|tts[_:/.-]|flutter|dart_vm|Dart_Initialize',re.I)
rows=[]; evidence=[]
with zipfile.ZipFile(src) as apk:
    for entry in apk.infolist():
        name=entry.filename
        if not(name.startswith('lib/') and name.endswith('.so')): continue
        data=apk.read(name)
        row={'path':name,'bytes':len(data),'compressed_bytes':entry.compress_size,'sha256':hashlib.sha256(data).hexdigest()}
        try:
            elf=ELFFile(io.BytesIO(data)); deps=[]; soname=None; exports=[]; imports=[]; jni=[]; flags=[]
            dyn=elf.get_section_by_name('.dynamic')
            if dyn:
                for t in dyn.iter_tags():
                    if t.entry.d_tag=='DT_NEEDED':deps.append(t.needed)
                    if t.entry.d_tag=='DT_SONAME':soname=t.soname
                    if t.entry.d_tag in ('DT_FLAGS','DT_FLAGS_1'):flags.append(str(t.entry))
            symbols=elf.get_section_by_name('.dynsym')
            ex_count=im_count=jni_count=0
            if symbols:
                for sym in symbols.iter_symbols():
                    s=sym.name
                    if not s:continue
                    if sym.entry.st_shndx=='SHN_UNDEF':
                        im_count+=1
                        if pattern.search(s):imports.append(s)
                    else:
                        ex_count+=1
                        if pattern.search(s):exports.append(s)
                        if s.startswith('Java_') or s=='JNI_OnLoad':jni_count+=1;jni.append(s)
            stack=[seg for seg in elf.iter_segments() if seg.header.p_type=='PT_GNU_STACK']
            relro=any(seg.header.p_type=='PT_GNU_RELRO' for seg in elf.iter_segments())
            row.update({'elf_class':elf.elfclass,'machine':elf.header.e_machine,'type':elf.header.e_type,'soname':soname,'needed':deps,'has_symtab':elf.get_section_by_name('.symtab') is not None,'dyn_defined_count':ex_count,'dyn_undefined_count':im_count,'jni_symbol_count':jni_count,'gnu_relro_segment':relro,'gnu_stack_executable':bool(stack[0].header.p_flags&1) if stack else None,'dynamic_flags':flags})
            hits=[]; hit_count=0
            for m in re.finditer(rb'[\x20-\x7e]{6,}',data):
                s=m.group().decode('ascii')
                if not pattern.search(s):continue
                hit_count+=1
                if len(hits)<120:
                    s=re.sub(r'[A-Za-z0-9+/=_-]{160,}','<REDACTED_LONG_LITERAL>',s[:1000])
                    s=re.sub(r'(https?://[^\s"?]+)\?[^\s"]+',r'\1?<QUERY_REDACTED>',s)
                    hits.append({'offset':m.start(),'text':s})
            row['tts_marker_count']=hit_count
            evidence.append({'path':name,'matching_defined_symbols':exports[:250],'matching_undefined_symbols':imports[:250],'jni_symbols_sample':jni[:60],'ascii_marker_total':hit_count,'ascii_markers_sample':hits,'sample_limits':{'symbols':250,'jni':60,'strings':120}})
        except Exception as e:row['parse_error']=type(e).__name__+': '+str(e)
        rows.append(row)
(out/'all-libraries.json').write_text(json.dumps(rows,indent=2))
(out/'native-symbol-evidence.json').write_text(json.dumps(evidence,indent=2))
lines=['# All packaged native libraries','', 'ELF metadata and symbol/string heuristics only; not a functional audit or vulnerability proof.','', '| Library | ABI | Bytes | Defined dynamic symbols | JNI symbols | TTS marker hits | Dependencies |','|---|---|---:|---:|---:|---:|---|']
for r in rows:
    parts=r['path'].split('/')
    lines.append(f"| `{parts[-1]}` | {parts[1]} | {r['bytes']} | {r.get('dyn_defined_count','?')} | {r.get('jni_symbol_count','?')} | {r.get('tts_marker_count','?')} | {', '.join(r.get('needed',[]))} |")
(out/'ALL-LIBRARIES.md').write_text('\n'.join(lines)+'\n')
print('Parsed libraries:',len(rows),'errors:',sum('parse_error' in r for r in rows))

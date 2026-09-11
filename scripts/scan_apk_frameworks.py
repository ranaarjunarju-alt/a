"""Static framework/native marker scan; never executes APK binaries."""
import hashlib, io, json, re, sys, zipfile
from pathlib import Path
src, dest = Path(sys.argv[1]), Path(sys.argv[2]); dest.mkdir(parents=True, exist_ok=True)
expected='46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f'
with src.open('rb') as f:
    if hashlib.file_digest(f,'sha256').hexdigest()!=expected: raise SystemExit('APK hash mismatch')
markers=re.compile(rb'flutter|dart:|DartVM|Dart_Initialize|isolate_snapshot|vm_snapshot|text_to_speech|textToSpeech|ElevenLabs|elevenlabs|11labs|sami[_./-]|tts[_./-]|speech[_./-]',re.I)
records=[]; nested=[]; frameworks=[]; limits=[]
with zipfile.ZipFile(src) as z:
    names=z.namelist()
    for name in names:
        if re.search(r'flutter|libapp\.so|dart|snapshot|lynx|lib.*sami|lib.*speech',name,re.I): frameworks.append(name)
        info=z.getinfo(name)
        if name.startswith('assets/') and name.endswith('.zip') and info.file_size < 20_000_000:
            try:
                with zipfile.ZipFile(io.BytesIO(z.read(name))) as inner:
                    nested.append({'archive':name,'entries':[i.filename for i in inner.infolist()][:2000], 'count':len(inner.infolist())})
            except zipfile.BadZipFile: pass
        is_native=name.startswith('lib/arm64-v8a/') and name.endswith('.so')
        is_asset=name.startswith('assets/') and not name.endswith(('.png','.jpg','.jpeg','.webp','.ttf','.otf','.mp4'))
        if not (is_native or is_asset) or info.file_size > 80_000_000: continue
        data=z.read(name); hits=[]
        # Match printable strings only; bounded and no arbitrary credential dump.
        for m in re.finditer(rb'[\x20-\x7e]{6,}',data):
            raw=m.group()
            if not markers.search(raw): continue
            text=raw[:1200].decode('ascii')
            text=re.sub(r'(?i)((?:api[_-]?key|authorization|access[_-]?token|secret)["\s:=]+)[^\s,;}]+',r'\1<REDACTED>',text)
            text=re.sub(r'(https?://[^\s"?]+)\?[^\s"]+',r'\1?<REDACTED_QUERY>',text)
            hits.append({'offset':m.start(),'text':text})
            if len(hits)>=250: limits.append(name); break
        if hits: records.append({'file':name,'sha256':hashlib.sha256(data).hexdigest(),'hits':hits})
result={'scope':'All assets <=80MB and arm64-v8a native libraries; ASCII keyword heuristic, not native disassembly', 'framework_path_candidates':frameworks,'flutter_standard_markers':{k:[n for n in names if k in n] for k in ['libflutter.so','libapp.so','flutter_assets/','kernel_blob.bin','isolate_snapshot_data','vm_snapshot_data']},'truncated_files':limits,'matches':records}
(dest/'framework-native-evidence.json').write_text(json.dumps(result,indent=2))
(dest/'nested-archive-inventory.json').write_text(json.dumps(nested,indent=2))
print('Scanned markers:',len(records),'files; truncated',len(limits))

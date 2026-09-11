"""Bounded static loader/JNI investigation. Never runs APK or native code."""
import hashlib, io, json, re, sys, zipfile
from pathlib import Path
from elftools.elf.elffile import ELFFile
from capstone import Cs, CS_ARCH_ARM64, CS_MODE_LITTLE_ENDIAN
from loguru import logger
logger.remove()
from androguard.core.dex import DEX

source, output = Path(sys.argv[1]), Path(sys.argv[2]); output.mkdir(parents=True, exist_ok=True)
with source.open('rb') as f:
    assert hashlib.file_digest(f,'sha256').hexdigest() == '46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f'
needles = ['libcapcut.so','Dex2cPro','NPDex2c','Encryptor','AppPropertyImpl','libcapcut','7fKOM']
exact_strings = re.compile(r'"(?:capcut|Dex2cPro|NPDex2c|Encryptor|KOM)"')
loader_methods=[]; natives=[]; native_matches=[]; disassembly=[]; dynamic_records=[]

def clean(text):
    return re.sub(r'[A-Za-z0-9+/=_-]{160,}', '<REDACTED_LONG_LITERAL>', text)

def va_offset(elf, addr):
    for seg in elf.iter_segments():
        if seg.header.p_type == 'PT_LOAD' and seg.header.p_vaddr <= addr < seg.header.p_vaddr+seg.header.p_filesz:
            return int(seg.header.p_offset + addr-seg.header.p_vaddr)
    return None

with zipfile.ZipFile(source) as apk:
    for name in apk.namelist():
        if re.fullmatch(r'classes\d*\.dex',name):
            dex=DEX(apk.read(name))
            for cls in dex.get_classes():
                cn=cls.get_name()
                for method in cls.get_methods():
                    desc=method.get_descriptor()
                    if method.get_access_flags() & 0x100 and (cn.startswith(('Lcom/vega/','Lcom/lemon/'))):
                        natives.append({'class':cn,'method':method.get_name(),'descriptor':desc,'dex':name})
                    if not method.get_code():continue
                    ins=[]; selected=False; refs=[]
                    for inst in method.get_instructions():
                        op=inst.get_name()
                        # Retain instruction reference only when this method is selected below.
                        text=inst.get_output()
                        ins.append({'op':op,'arg':clean(text)})
                        if op.startswith('const-string') and (any(n in text for n in needles) or exact_strings.search(text)):
                            selected=True;refs.append(clean(text))
                    if selected:
                        loader_methods.append({'dex':name,'class':cn,'method':method.get_name(),'descriptor':desc,'matched_literals':refs,'instruction_count':len(ins),'instructions':ins[:2500],'truncated':len(ins)>2500})
            del dex
        elif name.startswith('lib/arm64-v8a/') and name.endswith('.so'):
            data=apk.read(name)
            hits=[]; total=0
            for m in re.finditer(rb'[\x20-\x7e]{5,}',data):
                s=m.group().decode('ascii')
                if any(n in s for n in needles):
                    total+=1
                    if len(hits)<100:hits.append({'file_offset':m.start(),'text':clean(s[:1200])})
            if hits:native_matches.append({'path':name,'count':total,'sample':hits})
            if not name.endswith(('libDex2cPro.so','libNPDex2c.so','libEncryptor.so')):continue
            elf=ELFFile(io.BytesIO(data)); ds=elf.get_section_by_name('.dynsym')
            if not ds:continue
            exports=[];imports=[]; start=[]
            for sym in ds.iter_symbols():
                if not sym.name:continue
                if sym.entry.st_shndx == 'SHN_UNDEF':imports.append(sym.name)
                else:
                    exports.append({'name':sym.name,'address':hex(sym.entry.st_value),'size':sym.entry.st_size})
                    if sym.name=='JNI_OnLoad':start.append((sym.name,int(sym.entry.st_value),int(sym.entry.st_size)))
            relocations=[]
            for section in elf.iter_sections():
                if section.header.sh_type not in ('SHT_RELA','SHT_REL'):continue
                table=elf.get_section(section.header.sh_link)
                if not hasattr(table,'get_symbol'):continue
                for reloc in section.iter_relocations():
                    index=reloc.entry.r_info_sym
                    if index:
                        symbol=table.get_symbol(index)
                        relocations.append({'offset':hex(reloc.entry.r_offset),'symbol':symbol.name,'type':reloc.entry.r_info_type})
            dynamic_records.append({'path':name,'imports':imports,'exports':exports,'symbol_relocations':relocations})
            decoder=Cs(CS_ARCH_ARM64,CS_MODE_LITTLE_ENDIAN)
            queue=list(start);visited=set()
            while queue and len(visited)<7:
                label,addr,size=queue.pop(0)
                if addr in visited:continue
                visited.add(addr);offset=va_offset(elf,addr)
                if offset is None:continue
                limit=min(size,2048) if size else 1024
                lines=[];targets=[]
                for ins in decoder.disasm(data[offset:offset+limit],addr):
                    lines.append({'address':hex(ins.address),'bytes':ins.bytes.hex(),'mnemonic':ins.mnemonic,'operands':ins.op_str})
                    if ins.mnemonic=='bl' and ins.op_str.startswith('#0x'):
                        target=int(ins.op_str[1:],16);targets.append(hex(target))
                        if label=='JNI_OnLoad' and target not in visited:queue.append(('direct_callee',target,0))
                    if len(lines)>=256:break
                disassembly.append({'path':name,'label':label,'address':hex(addr),'symbol_size':size,'max_bytes':limit,'instructions':lines,'direct_call_targets':targets,'scope':'linear bounded window; not a complete control-flow graph; direct JNI_OnLoad callees only'})
(output/'dex-loader-methods.json').write_text(json.dumps(loader_methods,indent=2))
(output/'app-native-methods.json').write_text(json.dumps(natives,indent=2))
(output/'native-loader-strings.json').write_text(json.dumps(native_matches,indent=2))
(output/'loader-dynamic-symbols.json').write_text(json.dumps(dynamic_records,indent=2))
(output/'jni-entry-disassembly.json').write_text(json.dumps(disassembly,indent=2))
# Pass a bounded candidate list to JADX, prioritizing actual library-name references.
classes=[]
for r in loader_methods:
    if r['class'] not in classes:classes.append(r['class'])
(output/'loader-class-names.txt').write_text('\n'.join(c[1:-1].replace('/','.') for c in classes)+'\n')
print('DEX matching methods',len(loader_methods),'native method declarations',len(natives),'native matching files',len(native_matches),'bounded disassembly windows',len(disassembly))

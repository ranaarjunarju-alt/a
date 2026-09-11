"""Read decoder ELF metadata and bounded code/string evidence without executing it."""
import hashlib,io,json,re,sys,zipfile
from pathlib import Path
from elftools.elf.elffile import ELFFile
from capstone import Cs,CS_ARCH_ARM64,CS_MODE_LITTLE_ENDIAN
src,out=Path(sys.argv[1]),Path(sys.argv[2]);out.mkdir(parents=True,exist_ok=True)
with src.open('rb') as f:assert hashlib.file_digest(f,'sha256').hexdigest()=='46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f'
rows=[]
with zipfile.ZipFile(src) as apk:
 for lib in ['libzoin.so','libkomprese-decompressor.so']:
  name='lib/arm64-v8a/'+lib;data=apk.read(name);elf=ELFFile(io.BytesIO(data));ds=elf.get_section_by_name('.dynsym')
  exports=[];imports=[];code=[]
  for sym in ds.iter_symbols():
   if not sym.name:continue
   if sym.entry.st_shndx=='SHN_UNDEF':imports.append(sym.name);continue
   exports.append({'name':sym.name,'value':hex(sym.entry.st_value),'size':sym.entry.st_size})
   if sym.entry.st_info.type!='STT_FUNC':continue
   if not re.search('JNI_OnLoad|decode|decomp|Decode|Decomp|komprese',sym.name):continue
   if len(code)>=15:continue
   addr=int(sym.entry.st_value);size=int(sym.entry.st_size)
   for seg in elf.iter_segments():
    if seg.header.p_type=='PT_LOAD' and seg.header.p_vaddr<=addr<seg.header.p_vaddr+seg.header.p_filesz:
     off=int(seg.header.p_offset+addr-seg.header.p_vaddr);break
   else:continue
   instructions=[]
   for ins in Cs(CS_ARCH_ARM64,CS_MODE_LITTLE_ENDIAN).disasm(data[off:off+min(size or 1024,8192)],addr):
    instructions.append({'address':hex(ins.address),'mnemonic':ins.mnemonic,'operands':ins.op_str})
    if len(instructions)>=1500:break
   code.append({'symbol':sym.name,'address':hex(addr),'declared_size':size,'instructions':instructions})
  strings=[]
  for m in re.finditer(rb'[\x20-\x7e]{4,}',data):
   text=m.group().decode('ascii')
   if re.search('zoin|komprese|lzma|lz4|zstd|brotli|deflate|crc|decode|decomp|decodeAndVerify|open|read|seek',text,re.I):strings.append({'offset':m.start(),'text':text[:1000]})
  rows.append({'path':name,'sha256':hashlib.sha256(data).hexdigest(),'imports':imports,'exports':exports,'strings':strings[:300],'string_match_count':len(strings),'bounded_disassembly':code})
(out/'zoin-decoder-evidence.json').write_text(json.dumps(rows,indent=2))
print('Decoder libraries inspected:',len(rows))

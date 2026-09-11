"""Verify locally saved native/decompiler evidence; does not decode payloads."""
import json,re,struct,zlib
from pathlib import Path
root=Path(__file__).resolve().parents[1]/'analysis-report'
text=(root/'zoin-source/com.bytedance.zoin.model.ModuleManager.java').read_text()
abis=dict(re.findall(r'(\w+)\.abiName = "([^"]+)";',text))
files=[];blocks=[]
for kind,dest,add in [('ZoinBuildFileInfo',files,'libFileInfoList'),('ZoinBlockInfo',blocks,'blockInfoList')]:
    pattern=rf'{kind} (\w+) = new {kind}\(\);(.*?)\b(\w+)\.{add}\.add\(\1\);'
    for match in re.finditer(pattern,text,re.S):
        var,body,abi=match.groups(); row={'abi':abis[abi]}
        for key,value in re.findall(rf'\b{var}\.(\w+) = (.*?);',body):
            row[key]=json.loads(value) if value.startswith('"') else int(value.rstrip('L'))
        dest.append(row)
assert len(files)==4 and len(blocks)==2
headers=[]
for r in json.loads((root/'native-evidence/all-libraries.json').read_text()):
    if not r['path'].endswith('libcapcut.so'):continue
    b=bytes.fromhex(r['prefix_hex']);version=struct.unpack('<I',b[4:8])[0];stored=struct.unpack('<I',b[8:12])[0]
    calculated=zlib.crc32(b[4:8]);assert stored==calculated
    headers.append({'path':r['path'],'magic_hex':b[:4].hex(),'version_word':version,'header_crc32_stored':stored,'header_crc32_calculated':calculated,'header_crc32_matches':True})
for block in blocks:
    subset=sorted((f for f in files if f['abi']==block['abi']),key=lambda f:f['beginOffset'])
    assert subset[0]['beginOffset']==0 and subset[-1]['endOffset']==block['totalDecompressedLength']
    assert subset[0]['endOffset']==subset[1]['beginOffset']
    for f in subset:
        assert f['fileLength']==f['endOffset']-f['beginOffset']
        # VerifyUtils uses CRC32 + file length, not CRC32 alone.
        f['expected_crc32_from_check_number']=f['checkNumber']-f['fileLength']
result={'basis':'Metadata and header validation only; decompressed payloads not recovered', 'headers':headers,'declared_payloads':files,'declared_blocks':blocks}
(root/'native-boundary/zoin-layout-verified.json').write_text(json.dumps(result,indent=2)+'\n')
print('PASS: 2 header CRCs, 2 contiguous block layouts, 4 file lengths. Payload checksums not yet tested.')

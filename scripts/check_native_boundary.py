"""Regression checks of static facts; no app execution or decompression."""
import json
from pathlib import Path
root=Path(__file__).resolve().parents[1]/'analysis-report'
checks=0
def check(condition):
 global checks
 assert condition, f'Failed static boundary check {checks+1}'
 checks+=1
def source(name,needle,folder='zoin-source'):
 check(needle in (root/folder/(name+'.java')).read_text())
source('com.vega.launcher.lancet.SoLoadLancet','ModuleManager.isSoDepsNeedsResolve(str)')
source('com.bytedance.zoin.ZoinNative','nInit(false, false, false)')
source('com.bytedance.zoin.ZoinNative','isLoaded = iNInit == 1')
source('com.bytedance.zoin.decode.DecodeProcessor','ZoinNative.nDecodeLibs(str, str2, str3, j, j2, i, zoinBuildFileInfoArr, true, false)')
source('com.bytedance.zoin.utils.VerifyUtils','crc32.getValue() + file.length()')
source('com.bytedance.zoin.lib.inception.Inception','inceptionV1 = new InceptionV4()')
source('com.lm.components.network.extra.NetworkRuntimeConfig','"x-tt-web-proxy"','loader-source')
source('com.lm.components.network.extra.NetworkRuntimeConfig','b.contains(request.getHost())','loader-source')
source('com.vega.launcher.init.config.AppPropertyImpl','public final native int s()','loader-source')
source('com.vega.launcher.init.config.AppPropertyImpl','public final native String w()','loader-source')
r=json.loads((root/'native-boundary/zoin-decoder-evidence.json').read_text())
check('komprese_decompress' in r[0]['imports'])
check(any(s['name']=='komprese_decompress' and s['value']=='0xe9c' and s['size']==2432 for s in r[1]['exports']))
code={i['address']:i for i in r[1]['bounded_disassembly'][0]['instructions']}
check(code['0xef0']['operands']=='w10, #0x4b7f' and code['0xef4']['operands']=='w10, #0x4d4f, lsl #16')
code={i['address']:i for i in r[0]['bounded_disassembly'][0]['instructions']}
check(code['0x233c']['operands']=='w3, #4' and code['0x2348']['operands']=='x8, [x8, #0x6b8]')
r=json.loads((root/'native-boundary/loader-dynamic-symbols.json').read_text())
d=next(v for v in r if v['path'].endswith('libDex2cPro.so'))
check('vmInterpret' in d['imports'])
check(any(v['symbol']=='cacheInitial' and v['offset']=='0x5c5eb8' for v in d['symbol_relocations']))
print(f'PASS: {checks} native-boundary static assertions. Not runtime or decompression tests.')

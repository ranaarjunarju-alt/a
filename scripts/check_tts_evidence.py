"""Regression checks on saved static evidence, not APK runtime tests."""
import json
from pathlib import Path
root=Path(__file__).resolve().parents[1]/'analysis-report'
src=root/'deep-source'
checks=[]
def contains(name, text):
    checks.append((name, text))
    assert text in (src/(name+'.java')).read_text(), (name,text)
contains('com.vega.launcher.debug.AssistConfig','return "editor-api.capcutapi.com"')
contains('com.vega.launcher.init.config.AssistDevelopSetting','return new APIHost(str, str2,')
contains('com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsApiService','@POST("/lv/v1/text_to_speech/new")')
contains('com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsApiService','@POST("/lv/v1/text_to_speech/query")')
contains('com.vega.materialgenerate.ITtsApiService','/lv/v1/ad_maker/intelligent/cut_sentence')
contains('com.vega.core.context.debug.APIHost','this.f79180d = "feed-api.capcutapi.com"')
contains('com.vega.audio.tone.manager.TextToAudioService','/lv/v1/encrypt/get_public_key')
contains('com.vega.audio.tone.manager.TextToAudioService','/lv/v1/get_sami_token')
contains('com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine','wss://sami-sg1.byteintlapi.com/internal/api/v1/ws')
contains('com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode','RSA/ECB/PKCS1Padding')
contains('com.vega.audio.tone.tts.cache.DefaultTTSCacheManager','coerceAtLeast(10485760L')
contains('com.lemon.editor.proxy.AccountImpl','ws_channel_type_tts_streaming')
contains('com.bytedance.sdk.bridge.js.spec.IFlutterInterceptorListener','isFlutterWebView(WebView')
framework=json.loads((root/'deep-evidence/framework-native-evidence.json').read_text())
assert all(not v for v in framework['flutter_standard_markers'].values())
assert any('liblynx.so' in n for n in framework['framework_path_candidates'])
assert any(r['file'].endswith('libspeechsdk.so') for r in framework['matches'])
print(f'PASS: {len(checks)} source evidence assertions + 3 framework assertions. Not runtime tests.')

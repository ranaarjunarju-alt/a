# Deep TTS + framework analysis

> **Newer follow-up:** [ELEVENLABS-REQUEST-LINEAGE.md](ELEVENLABS-REQUEST-LINEAGE.md) traces effect metadata → selected tone → intent → task → JSON and confirms global interceptor registration. [NATIVE-LIBRARY-FINDINGS.md](NATIVE-LIBRARY-FINDINGS.md) covers all 337 `.so` entries, including two non-ELF `libcapcut.so` payloads. Earlier unknown-registration statements below describe the earlier stage.

## Executive result

**Is APK ke liye evidence native Android + Lynx + native speech/editor SDK architecture ko support karta hai, bundled Flutter TTS architecture ko nahi.** Flutter/Dart-named compatibility classes zaroor hain, lekin standard Flutter engine, Dart application snapshot aur Flutter assets nahi mile. Is distinction ko ignore karke Flutter app ya Flutter TTS plugin declare karna incorrect hoga.

TTS code mein ab confirmed host chain, seven executor types, ElevenLabs/Microsoft task APIs, SSML batch APIs, Remote SAMI endpoint, SAMI native online WebSocket engine, separate streaming channel, crypto helper behavior, and cache sizing mile hain. Yeh report previous reports ke unresolved host/segmentation/config points update karti hai.

## 1. Scope, provenance and limitations

- APK package `com.lemon.lvoverseas`, version `19.6.0` (19600200).
- Verified SHA-256 `46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f`.
- 42 DEX files; 27,270 APK ZIP entries.
- New deep extraction: **113 targeted Java source files**, in addition to earlier ElevenLabs evidence. These include decompiler-generated representations and coroutine instruction dumps, not original build source.
- Nested inspection: **115 embedded ZIP archives**, 2,422 listed entries. One nesting level only; selected archives under 20 MB.
- Native/asset scan: eligible assets and arm64 libraries up to 80 MB; 635 retained keyword-string hits across 14 files. This is not a full native disassembly. `libaudioeffect.so` hit the 250-match output cap.
- No Android execution, traffic replay, login, API bypass, voice synthesis, TLS-pinning bypass, or backend access was performed.
- Long credential-like literals are redacted in current deep-source output. No such values are needed to explain this architecture.
- Successful latest extraction: https://github.com/ranaarjunarju-alt/a/actions/runs/34613758035

Evidence levels used below: **confirmed static** (specific code/data), **inference** (interpretation), **unverified runtime** (requires an actual device/config/traffic capture).

## 2. Flutter claim: precisely what was found

### Standard bundled Flutter evidence — absent in APK path inventory

| Marker | Result |
|---|---|
| `libflutter.so` | Not found |
| `libapp.so` | Not found |
| `flutter_assets/` | Not found |
| `kernel_blob.bin` | Not found |
| `isolate_snapshot_data` | Not found |
| `vm_snapshot_data` | Not found |

No Flutter/Dart/snapshot names were found in the inspected nested archive entry lists either. No `io.flutter.*` class was identified by the class-name scan. ASCII native/asset evidence did not surface a Dart VM initialization marker.

### Flutter/Dart-named classes — present

- `com.bytedance.crash.dart.DartCrash`
- `com.bytedance.crash.dart.DartSummary`
- `com.bytedance.sdk.bridge.js.spec.IFlutterInterceptorListener`

These were explicitly decompiled, not dismissed by name:

- `DartCrash` constructs/uploads Dart-category crash reports through ByteDance crash infrastructure. This is crash-reporting support, not a Dart VM or a TTS implementation.
- `IFlutterInterceptorListener` is an interface exposing `call`, `callSync`, `isFlutterWebView(WebView)` and `isInterceptor`. It provides bridge compatibility hooks. An interface declaration does not prove an instantiated Flutter application or registered TTS method channel.

**Conclusion:** “Flutter ka naam milta hai” is true; “TTS Flutter/Dart mein implement hai” is not established. Current evidence shows compatibility support, not a bundled Flutter engine. Separate split APKs, downloaded modules, renamed/packed code remain outside this scan's proof of absence.

### Positive framework evidence

Bundled assets/libraries include:

- `assets/lynx_core.js`
- Multiple `assets/offline/image_lynx_*/.../template.js` bundles and locale JSON files.
- `liblynx.so`, `liblynx_v8_bridge.so`, `liblynx_krypton.so`, `liblynxbase.so`, and related libraries for two ABIs.
- `libmiddle-bridge.so`, `libcccreator.so`, `libcapcut.so` and native speech libraries.

This supports a mixed Android/Lynx/native app. It does **not** prove every TTS screen is Lynx-rendered; the currently traced TTS orchestration lives in Java/Kotlin-derived DEX classes.

## 3. Full architecture map

```text
Editor / tone selection / text intent
          ↓
TextToSpeechTaskManager
  - platform selection
  - scene/clone/config gates
  - read/save and streaming mode selection
          ↓
Task scheduler + executor factory/pool
          ├─ ElevenLabs → REST new/query → resource downloads
          ├─ Microsoft → REST new/query
          ├─ SSML → REST batch-transform new/query
          ├─ Remote SAMI → app-backend text_to_audio
          ├─ SAMI → native SAMICore online WebSocket engine
          ├─ Moyin → generic non-streaming executor in factory
          └─ Qwen → generic non-streaming executor in factory
                 + separately gated streaming paths
          ↓
Cache + playback/download + editor TextToAudioInfo
```

There are also distinct account-mediated streaming message services. Do not collapse the account channel, direct SAMI WebSocket engine, and ElevenLabs REST polling into a single protocol.

## 4. Provider selection is now traced, not guessed

`TextToSpeechTaskManager.j(...)` switches over the platform value in the text intent. `TextToSpeechExecutorType` has seven named values. The factory maps them as follows:

| Platform / executor type | Factory behavior |
|---|---|
| `sami` / SAMI | `SAMITextToSpeechExecutor`, or Remote SAMI under audition optimization |
| MICROSOFT / `microsoft` | `MicrosoftTextToSpeechExecutor` |
| REMOTE_SAMI | `RemoteSAMITextToSpeechExecutor` |
| ELEVEN_LABS / `11labs` | `ElevenLabsTextToSpeechExecutor` |
| SSML / `ssml` | `SSMLTextToSpeechExecutor` |
| MOYIN / `moyin` | `NonStreamingTextToSpeechExecutor` |
| QWEN / `qwen` | `NonStreamingTextToSpeechExecutor` |

Clone-authentication, business scene, audition and remote configuration can alter selection before/around these mappings. Therefore a voice label is not sufficient to prove the active transport. Qwen is more than a config class name: a platform branch and enum are present. Still, exact Qwen neural model/version/weights and live use are not established.

## 5. REST host chain resolved

The earlier ElevenLabs report left the host unresolved. It is now traceable:

```text
ElevenLabsToneManager
  → ContextExtKt.hostEnv().developSettings().host().f79178a
  → HostEnv.developSettings()
  → AssistDevelopSetting.host()
  → AssistConfig host lazy value
  → "editor-api.capcutapi.com"
```

`APIHost` assigns the first constructor string to `f79178a`. This is the **statically configured base host for this build**. Runtime DNS, proxy/TTNet routing, redirects, or hot patches are not captured by this conclusion.

### Confirmed endpoint map

| Subsystem | Method / statically constructed endpoint |
|---|---|
| ElevenLabs create | `POST https://editor-api.capcutapi.com/lv/v1/text_to_speech/new` |
| ElevenLabs query | `POST https://editor-api.capcutapi.com/lv/v1/text_to_speech/query` |
| Microsoft create/query | Same annotated paths; Microsoft create additionally declares `babi_param` query parameter. Its service base is not independently traced here. |
| SSML create | `POST /lv/v1/text_to_speech/batch_transform_new` with `babi_param` query parameter |
| SSML query | `POST /lv/v1/text_to_speech/batch_transform_query` |
| Remote SAMI | Constructed URL `https://editor-api.capcutapi.com/lv/v1/text_to_audio`; transport method not claimed from the URL alone |
| Sentence segmentation | `POST https://feed-api.capcutapi.com/lv/v1/ad_maker/intelligent/cut_sentence` |
| Encryption public-key retrieval | `POST /lv/v1/encrypt/get_public_key` |
| SAMI token retrieval | `POST /lv/v1/get_sami_token` |

Sentence service uses `TtsApiServiceFactory.b`, which reads `APIHost.f79180d`; that field is explicitly `feed-api.capcutapi.com`. Public-key/token interface paths are confirmed, but their factory's base URL is not independently resolved in this pass.

No endpoint was called. These are client declarations/constructions, not verified accessible public APIs.

## 6. ElevenLabs flow — refinements

The full payload and response schemas are in `ELEVENLABS-API-FLOW.md`. Confirmed essentials:

- Task creation sends `platform: "11labs"`, voice/resource identifiers, text list, `sample_rate: 24000`, `word_boundary_enabled: false`, scene and mock-tone metadata.
- Backend returns `task_id` and `text_lan`.
- Polling sends `platform` plus `task_id`.
- Success supplies `speaker_id` and per-segment audio URLs/text/language/status information.
- Downloads run as asynchronous jobs; results are aggregated into `TextToAudioInfo`.
- Local filenames are MD5 hashes of the **download URL**, not the audio bytes.
- Preview plays a local file through `ThirdPartyPlayer`/`MediaPlayer`; this particular branch is not live ElevenLabs audio streaming.

### Important polling-default distinction

Two different defaults now have evidence:

1. If the ElevenLabs config item is absent: manager fallback is **2,000 ms**, count 20, rate 1.0, increasing disabled.
2. A default-constructed `TTSEngineItemConfig` uses **1,000 ms**, count 20, rate 1.0, increasing disabled.

The serialized keys are `interval`, `count`, `increasing_rate`, `enable_increasing`; `TTSEngineConfig` maps its misspelled getter `getEleventLabs()` to JSON key **`elevenlabs`**. Runtime configuration determines which values apply. Inclusive loop bounds can produce count + 1 attempts.

## 7. Native SAMI is explicitly an online streaming engine here

`SamiTextToSpeechEngine` has the base URL:

```text
wss://sami-sg1.byteintlapi.com/internal/api/v1/ws
```

It appends additional reporter-generated URL information and headers. `SAMICoreTtsContextCreateParameter` assignments include:

- `sampleRate = 24000`
- `format = "pcm"`
- `bitRate = 64000`
- `connectTimeout = 10000`
- `tokenType = TOKEN_TO_B_MIXED`
- save directory and callback configuration

The created identify is explicitly:

```text
SAMICoreIdentify_Streaming_Playing_TTS_Online
```

The helper retrieves/initializes token state, uses `KvStorage` named `sami_audio`, and has a `/lv/v1/get_sami_token` service path. Token values are not reproduced. Native parameter values show requested configuration, not independently measured media characteristics.

**This is much stronger than finding a generic speech SDK string:** a concrete app class builds an online SAMICore handle with a concrete WebSocket base URL.

## 8. Other streaming path: channel, payload and audio formats

`StreamingToneMessageService` accesses `IAccount` through `EditorProxyModule`; inspected `AccountImpl.o()` returns:

```text
ws_channel_type_tts_streaming
```

The service queues outgoing messages and incoming responses, maps task IDs to tone managers, and implements keep-alive/disconnect handling. It constructs cancellation requests with status `cancel`. The default create request status is `create`.

Outer request fields:

```text
req_key, payload, req_payload, status,
task_id, matrix_task_id, babi_param
```

Payload schema:

```text
texts, speaker_info, audio_format,
need_subtitle_timestamp, sign, platform,
credit_disable, scene
```

Speaker schema includes:

```text
speaker_id, sample_rate, speech_rate,
emotion, emotion_scale, moyin_emotion,
resource_id, is_clone_tone, style, role, mock_tone_info
```

`StreamingReadingToneManager` constructs PCM-format requests; `StreamingSavingToneManager` constructs MP3-format requests. `StreamingToneReqPayload` also has MP3 as a constructor default. This explains why audition and saved output can use different formats. These facts must not be copied onto the separate ElevenLabs polling API's unspecified downloaded codec.

`credit_disable` being a field is not evidence that changing it bypasses billing. No server enforcement was tested.

## 9. RSA helper: encryption of request metadata, not a private-key signature

The name `SignTextWithRSANode` is misleading if read literally. The inspected helper:

1. Parses a PEM public key.
2. Constructs an X.509 public-key specification.
3. Requests `Cipher.getInstance("RSA/ECB/PKCS1Padding")`.
4. Initializes the cipher for encryption using the public key.
5. Encrypts supplied bytes and Base64-encodes the result.

The streaming caller constructs metadata including app/device/provider/speaker context and an MD5-derived text value before passing it to the helper. The text itself also appears in the synthesis payload.

**Consequences:**

- This is not a `Signature.sign()` operation and does not demonstrate client possession of a secret/private signing key.
- It does not establish end-to-end confidentiality of the complete request; actual synthesis text is separately represented.
- The RSA key can come from TTS config, with a public-key service fallback. The fallback coroutine decompilation has reconstruction warnings; helper crypto calls and service interface are clearer evidence than malformed high-level coroutine output.
- A public key inside an app is not inherently a leaked secret.
- MD5 here is not proof of an exploitable vulnerability by itself. Exploitability/server trust assumptions were not tested.

## 10. Auth, headers and routing: what is and is not known

`ElevenLabsApiService` only explicitly annotates JSON Content-Type. Separately, the app's `RetrofitHeaderInterceptor` constructs header names including:

```text
lan, loc, pf, vr, appvr, vc, device-time,
tdid, sign-ver, sign, app-sdk-version,
appid, header-content, host-abi
```

It also preserves other existing request headers and has a `NetworkRuntimeConfig` bypass branch. `InterceptorCreator` can instantiate this interceptor.

This confirms app-wide client-context/signing-header infrastructure. It **does not yet fully prove** every one of these headers is attached to every ElevenLabs call: complete global registration, cookie handling, request bypass conditions, and TTNet runtime routing remain untraced. A standalone two-endpoint HTTP example must not be described as a complete authenticated client.

`TimeoutInterceptor` interprets internal `CONNECT_TIMEOUT`, `READ_TIMEOUT`, and `WRITE_TIMEOUT` headers and transfers configured values into request handling. Its existence alone does not establish a fixed TTS timeout.

Sensitive context: device identifiers, scene metadata and text-associated information may travel in different parts of the network stack. Actual transmission/retention requires traffic and policy evidence.

## 11. Native speech libraries: offline capability versus actual offline use

`libspeechengine.so` and `libspeechepg.so` expose JNI-style names for engine creation, option setters, audio feed/process, result fetching, directives and destruction.

`libspeechsdk.so` contains option names such as:

- `OPTIONS_KEY_TTS_OFF_RESOURCE_PATH_STRING`
- `OPTIONS_KEY_TTS_VOICE_OFFLINE_STRING`
- `OPTIONS_KEY_TTS_VOICE_TYPE_OFFLINE_STRING`
- `OPTIONS_KEY_TTS_WORK_MODE_INT`
- `OPTIONS_KEY_TTS_LANGUAGE_ONLINE_STRING`
- `OPTIONS_KEY_TTS_USE_VOICECLONE_BOOL`
- `OPTIONS_KEY_TTS_TEXT_TYPE_STRING`
- pitch, speed, volume, sample-rate, cache and backend address options

This proves SDK vocabulary for offline/online modes and cloning exists. It does not prove this app bundles the resources, initializes offline mode, supports Hindi offline, or uses this SDK for the ElevenLabs branch.

`libmiddle-bridge.so` has markers including `11labs_text_to_speech`, `microsoft_text_to_speech`, `sami_text_to_speech`, `moyin_text_to_speech`, and generic TTS operations. This supports native editor integration but does not by itself locate neural model computation inside those libraries.

## 12. Cache sizing, persistence and performance

`DefaultTTSCacheManager` constructs a `DiskLruCacheWrapper` under `DirectoryUtil.D("default_tts_cache")` with size:

```text
max(10,485,760 bytes, configured value × 1024 × 1024)
```

So the configured cache capacity has a **10 MiB floor**, not necessarily a 10 MiB fixed cap. This is the default TTS cache, not the total of all audio storage.

Additional stores include ElevenLabs download files, stream-specific caches, Remote SAMI cache helpers, and SAMI token storage. There is no demonstrated user-text deletion policy or encryption-at-rest guarantee from these constructors alone.

Performance implications (inference, not benchmarks):

- ElevenLabs latency includes task creation, server processing, polling delay and file download.
- PCM streaming can enable earlier playback, but actual first-audio latency is unmeasured.
- Parallel downloads/queues may reduce serial waiting, while cancellation and error aggregation need device testing.
- 42 DEX files and large native editor libraries explain APK size better than assuming 328 MB of TTS weights.
- MD5 URL filenames may change when a server issues a new signed URL even for similar audio; actual cross-request reuse depends on other caches and URL behavior.

## 13. Feature gates and language/voice support

- `TtsQwenSavingStreamingConfig`: JSON key `stream_text_to_speech_listen`, constructor default **false**.
- `TtsV3ModelToneConfig`: JSON key `cc_tone_v3_streaming`, constructor default **false**.
- `ElevenLabsTonePlatformConfig`: default group `v0`; enabled comparison uses the external `V1` constant.
- Live AB/config responses may override defaults.

`ToneTypeHelper` integrates `ResourceRepository` and invokes a `fetchEffectToneType` coroutine. That is evidence of a resource-driven voice/type path, but a complete live voice catalog and response schema were not obtained.

**Not established:** exact Hindi/Hinglish voices, downloadable offline Hindi resources, voice names/IDs available to this account, free/paid status, clone consent gates, current quality, or provider model revisions. App-localization JSON is not a TTS language-support list.

## 14. Concrete updates to earlier findings

1. **Host no longer unknown:** the static ElevenLabs base is `https://editor-api.capcutapi.com`.
2. **Sentence API now known:** feed API host with `/lv/v1/ad_maker/intelligent/cut_sentence`.
3. **Qwen now has provider-branch evidence**, not just a configuration name. Model internals remain unknown.
4. **Offline SDK options now found**, but active app offline synthesis still not proven.
5. **“SignText” now inspected:** public-key encryption helper, not a demonstrated digital-signature operation.
6. **Default-cache capacity floor now known:** 10 MiB.
7. **Flutter markers refined:** compatibility interfaces/crash classes are present; engine/snapshot/assets absent from inspected package.
8. **Polling defaults refined:** absent-config fallback 2 seconds versus item-constructor default 1 second.

## 15. What a genuinely deeper next stage requires

Static inspection is not equivalent to server access or a device test. The highest-value remaining work is:

1. Install this exact APK on an authorized device/emulator; verify certificate, split modules and downloaded assets.
2. Capture actual host resolution, request headers and provider selection for one selected voice.
3. Obtain the live voice catalog/config and map voice/resource ID → platform → executor.
4. Test uncached Hindi input offline separately from cached playback.
5. Measure generation and first-audio latency, long-text segmentation and cancellation.
6. Trace full TTNet/interceptor registration and account WebSocket connection setup.
7. Disassemble selected native call targets and inspect actual model/resource initialization, rather than extrapolating from SDK strings.
8. For suspected Flutter, inspect any split/downloaded module carrying the engine and Dart snapshot; none has been supplied here.

## Evidence map

- `deep-evidence/framework-native-evidence.json`: Flutter marker checks, framework paths, native/asset strings with offsets and file hashes.
- `deep-evidence/nested-archive-inventory.json`: embedded archive paths.
- `deep-source/candidate-classes.txt`: broad class-name scan, including false positives such as “WordArt”.
- `deep-source/routing-candidates.txt`: routing-related class candidates, not proof of use.
- `deep-source/*.java`: targeted decompiled evidence; warnings are retained.
- `elevenlabs-source/*.java`: earlier executor/factory/protocol extraction.
- `ELEVENLABS-API-FLOW.md`: request/response detail, updated with new host resolution.

**Final assessment:** The APK has a substantial native Android/Lynx TTS orchestration system with cloud REST tasks, native online SAMI streaming, separate account-channel streaming, and native editor integration. It is not supported by current evidence to label this a Flutter TTS application or an offline ElevenLabs model package.

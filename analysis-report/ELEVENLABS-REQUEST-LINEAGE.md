# ElevenLabs request: field-by-field origin, transformations and native boundary

## Result and scope

This report traces **where the request data comes from**, rather than just listing API keys. APK: `com.lemon.lvoverseas` 19.6.0; verified SHA-256 `46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f`.

Evidence is static JADX source/instruction dumps plus native ELF parsing. No actual user session, request replay or synthesis was performed. Obfuscated field names below are those produced by this extraction; semantic names are recovered from constructors, getters and `toString()`.

**Main distinctions:**

1. Request body fields are not the same thing as HTTP headers, query parameters, telemetry, or the whole task object.
2. `voice` comes from the selected tone identifier; `resource_id` from the resource identifier, not the effect identifier.
3. Preview can send an audition sentence instead of the user's editor text.
4. The app requests CapCut's backend with `platform: "11labs"`; the backend's private request to ElevenLabs is not visible in this APK.
5. All 337 `.so` entries were inspected as files; 335 parse as ELF and two `libcapcut.so` entries are non-ELF opaque payloads. See `NATIVE-LIBRARY-FINDINGS.md`.

## 1. End-to-end lineage

```text
Effect/resource catalog or existing editor/voiceover state
    ↓
Effect.extra metadata + Effect.resourceId
    ↓ ToneUtil conversion
ToneType
    ├─ voiceType
    ├─ platform
    ├─ resourceId
    ├─ auditionText
    └─ mockToneInfo
    ↓ ToneSelectViewModel / other entry-point callers
TextToSpeechIntent
    ↓ TextToSpeechTaskManager.q (wrapToTask)
    ├─ provider/scene selection
    ├─ user text versus audition text selection
    └─ UTF-8 conversion
TextToSpeechTask
    ↓ ElevenLabsToneManager.e (getToneData)
    ├─ reading: one text item
    └─ saving: sentence API or newline split
ElevenLabsToneManager.c (getAudioResourceInfos)
    ↓ 8-field map → Gson JSON → UTF-8 TypedJson body
ElevenLabsApiService.audioTaskRequest
    ↓ registered network interceptors
POST https://editor-api.capcutapi.com/lv/v1/text_to_speech/new
    ↓ task_id and text_lan
POST /lv/v1/text_to_speech/query
    ↓ resource_infos + speaker_id
Download URLs → local audio paths → playback/editor result
```

This is one established family of callers. The complete live catalog-fetch response, account state and all entry points are not reconstructed.

## 2. Upstream voice metadata: where identifiers originate

`ToneTypeHelper$fetchEffectToneType$2` obtains effect candidates through `ToneFetchAllHelper`, matches identifiers/names, and calls `ToneUtil.c(...)` to convert an `Effect` into a `ToneType`.

In the converter, `Effect.getExtra()` is parsed as JSON. Its `tonetype` member is read with `optString` and parsed again as JSON. Thus the nested structure is a **JSON-encoded string** in the inspected conversion path, not necessarily a directly nested JSON object.

Illustrative metadata only — not an actual server response:

```json
{
  "tonetype": "{\"voice_type\":\"VOICE_ID\",\"platform\":\"11labs\",\"rate\":\"24000\",\"tts_voice\":\"OTHER_VOICE_METADATA\"}",
  "default_audition_text": "Preview sentence",
  "mock_tone_info": "OPAQUE_METADATA_STRING"
}
```

Confirmed conversions:

| Catalog/effect source | ToneType property |
|---|---|
| `extra.tonetype.voice_type` | `voiceType` |
| `extra.tonetype.platform` | `platform` |
| `extra.tonetype.rate` | `rate`, converter default `"24000"` |
| `extra.tonetype.tts_voice` | `ttsVoice` — **not automatically the ElevenLabs `voice` value** |
| `Effect.getResourceId()` | `resourceId` |
| `Effect.getEffectId()` | `effectId` — separate identifier |
| `extra.default_audition_text` | `defaultAuditionText` |
| `extra.mock_tone_info` | `mockToneInfo`, via metadata helper and setter |

`ToneSelectViewModel` then uses getters such as `getPlatform()`, `getResourceId()`, `getMockToneInfo()` and `getAuditionText()` when constructing the intent. A selected voice-type argument or a cached name→voice override can take precedence over `getVoiceType()` in particular paths.

**Therefore:** do not substitute the UI voice name, effect ID, resource ID, `tts_voice`, and provider voice identifier for one another. They are distinct fields in this client.

A separate voiceover entry point reads saved settings such as `tone_type_id`, `tone_platform`, `tone_effect_id`, `tone_resource_id`, `tone_name`, and `tone_category`, falling back to repository state. Its constructor arguments differ: merely reading a setting does not prove it is forwarded into this exact create-body field. The selected-tone view-model path above provides the clearest resource-ID forwarding evidence.

## 3. Exact create request

```http
POST https://editor-api.capcutapi.com/lv/v1/text_to_speech/new
Content-Type: application/json; charset=utf-8
```

Body template (placeholder identifiers are not usable credentials/voice IDs):

```json
{
  "platform": "11labs",
  "voice": "VOICE_ID",
  "resource_id": "RESOURCE_ID",
  "word_boundary_enabled": false,
  "input": ["Selected text segment"],
  "sample_rate": 24000,
  "scene": "SCENE_VALUE_LOWERCASED",
  "mock_tone_info": ""
}
```

### Field-by-field source map

| Wire key | Type | Exact lineage / construction |
|---|---|---|
| `platform` | string | Literal `"11labs"` inside `ElevenLabsToneManager.c`. Intent platform selects this executor earlier; the body is not simply an arbitrary copy of that platform string. |
| `voice` | string | Selected tone voice type → `intent.f88866c` (`toneTypeId`) → `task.f74298c` → first string argument of manager `c` → map key `voice`. |
| `resource_id` | string | `ToneType.getResourceId()` → `intent.s` (`resourceId`) → `task.n` → second string argument of manager `c`. `wrapToTask` converts null resource ID to `""`. |
| `word_boundary_enabled` | boolean | Hardcoded `false` in this ElevenLabs create map. It is not copied from the generic task/streaming subtitle flag. |
| `input` | array of strings | TextInfo/editor or audition text → task text → read/save-dependent segmentation → list passed into manager `c`. Details below. |
| `sample_rate` | integer | `ElevenLabsToneManager.e` passes literal **24000** to manager `c`. The generic intent also has `rate`, but this call site supplies its own constant. |
| `scene` | string | `TextToSpeechReportInfo.toJson()` → `intent.o` (`reportInfo`) → `task.k` → parse JSON → `optString("from")` → lowercase with `Locale.ROOT`. Empty metadata gives `""`. |
| `mock_tone_info` | string | `ToneType.getMockToneInfo()` → `intent.w` → `task.w` → manager argument; empty/null becomes `""`. Its internal string format is not parsed here. |

**Important scene distinction:** this body field comes from report JSON `from`. It is not a direct serialization of `intent.e` (`enterFrom`) or the separate business-scene enum. Full reportInfo fields such as length/status/timing are not automatically included in this 8-field body.

The resource and mock metadata are forwarded values, not locally generated hashes. A live voice/resource catalog is still needed to know real values currently valid for the user's account.

## 4. Exactly how `input` is built

### A. TextInfo representation

Two inspected implementations:

- `TextInfo.AutoSegText(text)`: returns original text, reports count 1 and auto-segmentation flag true.
- `TextInfo.NoSegTextList(list)`: preprocesses entries, filters blank items, joins retained entries with newline, reports their count and auto-segmentation flag false. Its embedded-newline replacement uses an external constant whose concrete value was not separately traced; do not assume a specific replacement character.

### B. Preview text can differ from user text

`RemoteSAMIToneUtil.a(isSaving, executorType, intent)` is called even for ElevenLabs. It selects text based on scene, saving/reading, audition AB settings, and `forceUseDefaultAuditionText` / `supportReadUserText`.

For inspected common scenes, save returns `TextInfo.getText()`. Preview may use `intent.defaultAuditionText` instead. ElevenLabs is explicitly among the types eligible for default-audition selection in a shown branch. Special scenes have additional behavior; this is not a blanket rule for every screen.

`ToneType.getAuditionText()`:

1. Non-clone voice with nonempty catalog default: use it.
2. Non-clone voice without one: consult `IEffectSettings.A()`.
3. If that setting is empty, fallback sentence is:
   > You’re using the text to speech feature. Choose a voice you like.
4. AI-clone voice: uses clone-language-specific configuration instead.

The tone-selection UI may truncate audition user text to a configured `getAuditionUsersTextMaxSize()` value. There is **no verified universal numeric text limit** from this trace. The limiter is conditional and should not be reported as a server limit.

### C. Task wrapping

The selected string is converted to UTF-8 bytes and back into a Java string. This is encoding handling, not encryption. The task receives selected text and the original TextInfo segmentation flag.

### D. ElevenLabs read versus save

- Reading task: `input = [task.text]`.
- Saving with automatic segmentation: POST `{"text": task.text}` to `https://feed-api.capcutapi.com/lv/v1/ad_maker/intelligent/cut_sentence`, flatten the returned sentence groups, use them as `input`.
- Saving without automatic segmentation: split task text on newline and use resulting list.

Thus one action can send text to **two client-facing API calls**: sentence segmentation first, synthesis creation second. The inspected segmentation coroutine catches failures and returns null; the manager's response-flattening path can yield an empty list. A successful server fallback is not established.

### E. Serialization

`TypedJson.Companion.b` runs `new Gson().toJson(map)`, constructs UTF-8 bytes and a JSON TypedByteArray. Serialization failure is caught and substitutes `{}`. Field ordering is not an API guarantee; ordinary Gson escaping can change the wire representation without changing the decoded string.

## 5. What is NOT in the inspected create-body map

The inspected map does not include these as top-level keys:

```text
xi-api-key, Authorization, model_id, stability, similarity_boost,
speaker_boost, speed, emotion, effect_id, local task UUID,
complete reportInfo, user_id, device_id
```

This is a statement about this specific body builder, not about all network traffic. For example, device context can be added in headers; speed/emotion can affect other providers or playback. Opaque metadata and backend configuration may influence server behavior. No public ElevenLabs API compatibility is implied.

Local task IDs are default-generated from UUID with hyphens removed when omitted. The task ID returned by `/new` is a different, server-generated ID.

## 6. Query body and response lineage

Query request:

```http
POST https://editor-api.capcutapi.com/lv/v1/text_to_speech/query
```

```json
{"platform":"11labs","task_id":"SERVER_TASK_ID"}
```

`task_id` is read from `Response.data.task_id` of the successful create response, not derived from voice/text or the local UUID. Query body does not resend input text in the inspected code. Network interceptors can still attach context to each query request.

Create data also includes `text_lan`; it is returned by the backend, not a client `language` field in the create body. Query data includes `status`, `speaker_id`, and `resource_infos`. Each resource has `text`, `url`, `code`, `msg`, `text_lan`.

- Wrapper `ret == "0"`: response success predicate.
- Task `status == 1`: processing, wait and query again.
- Task `status == 0`: extract resources.
- Other status: failure branch.

The returned resource URL is passed to Downloader; its MD5 becomes a local filename. No network content hash validation follows merely from hashing the URL. Transport/file validation elsewhere is not assessed by that observation.

## 7. Common headers are now tied to registration code

Earlier reports only established the header interceptor's existence. This pass found **both `NetworkInitHook` and `NetworkInitTask` constructing it and calling `RetrofitUtils.addInterceptor`**.

Registered list includes:

```text
RetrofitHeaderInterceptor
ArtistCollectInterceptor
AssistInterceptor
SignVerifyInterceptor
LynxSignVerifyInterceptor
TimeOutSettingInterceptor
CrackingInterceptor
IapRegionInterceptor
TimeoutInterceptor
```

This supports the normal initialized Retrofit pipeline. Runtime bypass branches, native TTNet additions and the actual captured header set remain unverified.

### Header source table

| Header | Construction in inspected common interceptor |
|---|---|
| `Content-Type` | API annotation / TypedJson media type: JSON UTF-8 |
| `lan` | `FlavorLocale.h()`, or alternate language function for a configured path set |
| `loc` | `FlavorLocale.b()` |
| `pf` | `String.valueOf(0)` |
| `vr` | `String.valueOf(ContextExtKt.app().s())`; concrete implementation is native in inspected `AppPropertyImpl` |
| `appvr` | `AppContext.getVersion()`; manifest version for this APK is 19.6.0, runtime accessor not executed |
| `vc` | `AppContext.getVersionCode()` converted to string |
| `device-time` | `System.currentTimeMillis() / 1000`, converted to string |
| `tdid` | `AppLogManagerWrapper.getServerDeviceId()`; if empty, `AppContext.getDeviceId()` |
| `sign-ver` | `ProfileManager.VERSION` constant reference |
| `sign` | MD5-based common header helper described below |
| `app-sdk-version` | Literal `184.0.0` |
| `appid` | `AppContext.getAid()` converted to string |
| `header-content` | Delimited path-tail, platform, version, timestamp, device identifier |
| `host-abi` | `Process.is64Bit()` → `"64"` or `"32"` |
| `cc-newuser-channel` | Conditional for installation age under 604,800,000 ms (7 days): launch-source mapping to `tiktok`, `instagram`, or `common` |
| `cc-user-mode` | Conditional when `ContextExtKt.app().h()` is false; value is `ProfileManager.VERSION` |
| `commerce-sign-version` | `SignVerifyInterceptor` appends the `V1` constant reference on its non-bypass path |

Existing request headers whose names are not replaced by the common generated set are preserved. Therefore custom/session headers may coexist; this table is not a guarantee of all wire headers.

### Common `sign` is different from streaming RSA

The common helper takes the request URL's path (not the query), keeps its last seven characters when longer than seven, combines it with fixed literal framing, `pf=0`, app version, epoch seconds and device ID, and computes MD5.

Conceptually:

```text
path_tail = last 7 characters of URI.path, or whole shorter path
header-content = path_tail | pf | app_version | epoch_seconds | device_id
sign = MD5(fixed_prefix | path_tail | 0 | app_version |
           epoch_seconds | device_id | fixed_suffix)
```

The literal framing is retained in the source evidence, but no generated real-device signature/session values are provided. This helper does not receive the JSON body; it is not proof of payload authentication. It is **not** the separate streaming `SignTextWithRSANode`, which public-key encrypts different metadata. Neither observation establishes that unauthenticated requests will work.

### Other conditionally added context

`AssistInterceptor` may apply environment/user header maps and a diagnostic `x-looki-diff` header under its switch. `LynxSignVerifyInterceptor` has its own header-triggered branch.

`IapRegionInterceptor` adds `edata` only for three listed commerce paths, none of which is `/lv/v1/text_to_speech/new` or `/query`. Therefore `edata` should **not** be presented as a required ElevenLabs TTS header based on this interceptor.

Cookies, full account-session routing, transport User-Agent, TLS properties and any native TTNet-added fields remain uncaptured. There is no `xi-api-key` declaration in these two API methods.

## 8. Query parameters added outside the API interface

`RetrofitHeaderInterceptor` can append:

| Parameter | Source |
|---|---|
| `effect_sdk_version` | Cached `VESDKHelper` version; fallback `AppProperty.G()` when available |
| `subdivision_id` | `AppProperty.w()`; implementation is native in extracted `AppPropertyImpl`, actual value unresolved |
| `user_type` | Nonempty `LocalQuestionnaireHelperV4.Y` and/or `.Z` values; code can append the same key twice |

The ElevenLabs interface itself has no `babi_param` query annotation. Microsoft's analogous interface does. Do not mix their declarations.

This clarifies why the eight-field JSON is not the entire network request: context may also be appended to the URL or headers during initialization/transport.

## 9. Additional local logging and metadata, separate from body

Inspected code constructs/logs values including selected text, final audition text, scene, resource/voice identifier and cache settings. `RemoteSAMIToneUtil` updates feedback metadata keys such as `last_audition_speakid`, `last_download_resource_id`, and scene/from information.

This establishes local logging/metadata construction, not proof that every such value is uploaded. Logging enablement, log collectors, remote retention and access control are separate questions. User text in a log call is a privacy-review point, not an automatic confirmed leak.

## 10. Native-library relevance to this request

The request-body builder, Retrofit annotations and the named common header helper are visible in DEX-derived code. You do not need to infer their JSON from native strings.

Native boundaries remain important:

- `AppPropertyImpl.s()` and `.w()` are native methods, so some common context values are opaque in this Java extraction.
- `libmiddle-bridge.so` provides large numbers of editor/JNI operations, including TTS metadata/parameters.
- `libaudioeffect.so` exports SAMICore/native bridge functions; that is evidence for the distinct SAMI audio path, not proof ElevenLabs synthesis runs locally.
- `libsscronet.so`, `libttboringssl.so`, and `libttcrypto.so` participate in packaged native network/crypto dependencies; dependency edges do not expose the entire live auth chain.
- Both `libcapcut.so` entries are non-ELF high-entropy `7fKOM` payloads. Their actual loader/format and decoded content are unresolved.

The complete 337-entry library inventory and major findings are in the accompanying native report. None of those files was executed.

## 11. Remaining unknowns and next useful evidence

- Live catalog endpoint/response actually used by the selected screen and currently valid voice/resource IDs.
- Inner meaning of `mock_tone_info` for a specific resource; client forwards it as a string here.
- Exact native-derived `vr`/`subdivision_id` values and full session/cookie/transport headers.
- Backend-to-ElevenLabs model, voice mapping, credentials and upstream API payload.
- Actual packet capture for create, query, segmentation and download.
- Decoding/loading of the two opaque capcut payloads and instruction-level audit of selected native routines.

**There is no honest static-only way to fill those runtime/server values with certainty.** The report gives confirmed generation rules and explicit boundaries rather than a fabricated ready-to-use authenticated request.

## Evidence files

All Java files below are under `analysis-report/deep-source/` unless specified:

- `com.vega.edit.base.utils.ToneUtil.java`: effect metadata → ToneType.
- `com.lemon.lv.data.ToneType.java`, `ToneTypeKt.java`: getter behavior/default audition text.
- `com.vega.audio.tone.viewmodel.ToneSelectViewModel.java`: selected tone → intent; limits and text source.
- `com.vega.edit.base.tone.TextToSpeechIntent.java`: semantic field names and constructor defaults.
- `com.vega.edit.base.tone.TextInfo.java`: text normalization/segmentation flags.
- `com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil.java`: actual text selection.
- `com.vega.audio.tone.tts.TextToSpeechTaskManager.java`: intent → task mapping.
- `elevenlabs-source/com.vega.audio.tone.tts.core.TextToSpeechTask.java`: task constructor field assignments (relative to analysis-report).
- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.java`: exact map, segmentation, polling and download.
- `com.vega.core.net.TypedJson.java`: Gson/UTF-8 serialization.
- `com.vega.launcher.init.core.hook.NetworkInitHook.java`, `NetworkInitTask.java` in its own full package filename: global interceptor registration.
- `com.vega.launcher.network.interceptors.RetrofitHeaderInterceptor.java`: headers, query additions, common MD5 helper.
- `intent-field-usage.txt`, `intent-constructor-usage.txt`: JADX use references, not a dynamic execution trace.

Latest successful evidence run: https://github.com/ranaarjunarju-alt/a/actions/runs/34618142830

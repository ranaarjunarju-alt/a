# APK TTS analysis — verified static findings

## 1. APK identity aur scan scope

- File: `app-release.apk`
- SHA-256: `46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f` — actual downloaded APK se verified.
- Size: 328,455,327 bytes (~313.24 MiB).
- Manifest package: `com.lemon.lvoverseas`; version `19.6.0`, code `19600200`.
- Package aur `libcapcut.so`/CapCut domains CapCut identity ke consistent evidence hain. Official/unmodified build hona verify nahi kiya; signing certificate comparison nahi hua.
- Minimum SDK 23; target SDK 36.
- Scan: 42 DEX files, 27,270 ZIP entries, 4,075 broad TTS-keyword invocation matches. Har match TTS nahi hai: `speak` aur `synthesis` jaise words unrelated code bhi match karte hain.
- Successful workflow: https://github.com/ranaarjunarju-alt/a/actions/runs/34609351084

Yeh static architecture analysis hai, full source decompilation/native reverse engineering ya runtime traffic audit nahi. APK execute nahi hua.

## 2. Main finding: multi-provider TTS architecture

Primary subsystem: `com.vega.audio.tone.tts`.

`TextToSpeechExecutorFactory.a(TextToSpeechExecutorType)` ke bytecode mein in constructors ke actual invocation references mile:

| Executor | Confirmed meaning | Kya confirm nahi hota |
|---|---|---|
| `ElevenLabsTextToSpeechExecutor` | ElevenLabs-named implementation factory mein wired hai | Har voice ElevenLabs use karti hai, direct ElevenLabs connection, account availability |
| `MicrosoftTextToSpeechExecutor` | Microsoft-named implementation factory mein wired hai | Har user ke liye Azure/Microsoft voice enabled hai |
| `RemoteSAMITextToSpeechExecutor` | Remote SAMI implementation factory mein wired hai | Exact server model/version aur live endpoint |

Additional implemented class families: `SAMITextToSpeechExecutor`, `SSMLTextToSpeechExecutor`, `StreamingTextToSpeechExecutor`, `NonStreamingTextToSpeechExecutor`.

Supporting service classes mein `ElevenLabsApiService`, `MicrosoftApiService`, `SSMLApiService`, `RemoteSAMIToneManager`, aur `SamiTextToSpeechEngine` hain. Yeh provider-specific integration ka evidence hai, sirf UI labels nahi. Lekin backend proxying aur runtime provider selection abhi unresolved hain.

**Conclusion:** Is app ko ek single Android/Google TTS wrapper kehna evidence ke against hoga. Scanned invocation references mein `Landroid/speech/tts/` calls zero mile. Reflection/native/dynamic code ke kaaran isse absolute absence prove nahi hoti.

## 3. Architecture map

Neeche class roles aur selected call references se reconstructed conceptual map hai; har edge ka full control-flow trace nahi hua:

```text
Editor / voiceover text intent
    ↓
TextToSpeechTaskManager
    ↓
TextToSpeechTask + TextToSpeechTaskScheduler
    ↓
Executor factory / executor pool
    ├─ ElevenLabs / Microsoft / SAMI / Remote SAMI
    ├─ SSML
    └─ Streaming / non-streaming
    ↓
Audio playback and/or saving
    ↓
Cache / editor audio information
```

Scheduling infrastructure mein serial, parallel aur migration queues present hain. `TextToSpeechExecutorPool` mein streaming aur non-streaming executors instantiate karne ke calls confirmed hain.

## 4. Streaming, saving aur retry

Confirmed class/call evidence:

- `StreamingReadingToneManager`, `StreamingSavingToneManager`: preview/read aur saved output ke separate implementation families.
- `PCMStreamingPlayer`: streaming executor se invocation references mile; PCM streaming playback component present hai.
- `Operation.StartReadingStreaming`, `StartReadingNonStreaming`, `StartSavingStreaming`, `StartSavingNonStreaming`, `StartSavingParallel`: multiple operation modes represented hain.
- `TextToSpeechTaskManager$wrapToTask$1` se `retryTTSWithNonStreaming` coroutine construct hoti hai: non-streaming retry path wired hai. Exact triggering error/condition inspect nahi hua.
- `TtsStreamingKeepAliveConfig`, `TtsStreamingOptimizedConfig`, `TtsStreamingSceneConfig`: configurable streaming behavior ka evidence.

Practical implication: preview aur final save different paths use kar sakte hain. Streaming path playback ko full result ready hone se pehle start karne ke liye designed lagta hai; actual latency measure nahi ki.

## 5. Offline versus online

Remote SAMI, provider API services, request/response types, aur `DownloadAudioFilesNode` remote-generation/download architecture ka strong evidence hain.

Filename inventory mein recognizable TTS weights/voice packs ke `onnx`, `tflite`, `piper`, `kokoro`, `espeak`, `vocoder` names nahi mile. Sirf do `.model` candidates broad scan mein mile:

- `assets/js_clip128_v1.0.model` — 13,068 bytes.
- `assets/js_translation_v1.0.model` — 12,612 bytes.

Inko TTS models kehne ka evidence nahi hai. APK ka 328 MB hona offline voice weights hone ka proof nahi: large files mein `libmiddle-bridge.so`, `libcccreator.so`, `libmiddleware.so`, resources aur dozens of DEX files hain.

**Assessment:** Remote/cloud-oriented TTS ka strong evidence hai; complete offline synthesis establish nahi hui. Models native libraries, opaque assets ya later downloads mein ho sakte hain. Cached audio offline play hona naya text offline synthesize hone se alag hai.

## 6. Caching aur performance clues

Present components:

- `DefaultTTSCacheManager` with `putAudioFileCache` and `putTextToAudioInfoCache` coroutine classes.
- `RemoteSAMICacheManager`, `RemoteSAMICacheKeyHelper`.
- `NonStreamingToneCache`, `StreamToneCache`, `SSMLCacheUtil`.
- `TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1` and SSML request-cache MD5 helper names.

Interpretation: generated audio/metadata caching aur deterministic cache-key support present hain. MD5 helper ka cache context encryption ka proof nahi; cache-key hashing apne aap vulnerability bhi nahi. Cache size, eviction policy, location aur encryption unknown hain. Serial/parallel queues performance-management design dikhati hain, benchmark nahi.

## 7. Text preparation, SSML aur subtitle alignment

Relevant pipeline classes:

- `GenerateSentenceNode`
- `WrapSSMLNode`
- `SignTextWithRSANode`
- `GetResultNode`
- `ParseTTSResultNode`
- `DownloadAudioFilesNode`

`StreamingReadingToneManager` se `SignTextWithRSANode` constructor aur processing method invocations confirmed hain. Naam se RSA-related text processing intended lagti hai; actual cryptographic operation, padding aur key handling inspect nahi hue. Isliye ise proven encryption/signature security guarantee nahi keh sakte.

`SSMLBreakHelper`, `SSMLMicrosoftPostProcessor`, `SSMLAudioSubtitleInfo`, `SSMLAudioSubtitleWord` pause/markup processing aur subtitle-associated results ka evidence hain. Word data constructor mein string aur do Float values hain, lekin unki units/meaning field inspection ke bina assert nahi ki gayi.

## 8. Qwen aur V3: important limitation

`TtsQwenSavingStreamingConfig` aur `TtsV3ModelToneConfig` classes present hain, task-manager config accessor classes bhi hain.

Yeh Qwen/V3-named configuration support ka evidence hai. **Isse Qwen weights bundled hona, Qwen ka active default provider hona, ya kisi exact Qwen model version ka use prove nahi hota.** Configuration values aur runtime selection follow-up mein inspect karni hongi.

## 9. Hindi, voices aur cloning

Is scan se reliable Hindi voice list, language codes, voice IDs, free/paid status ya regional availability establish nahi hui. Android resources mein app localization aur TTS voice language support alag cheezein hain.

`TtsUpdateServiceImpl` ke nested names mein `checkCommercialCloneToneValid`, `showToneCloneLimitDialog`, `startSavingMultiToneAudio` aur `updateTtsByTextChange` mile. Yeh clone-tone validation/limits aur multi-tone/editor-update integration ka evidence hain; working voice cloning, permission, eligibility ya exact limits ka proof nahi.

## 10. Network aur privacy

APK-wide hostname candidates include:

- `sami-sg1.byteintlapi.com`
- `editor-api.capcutapi.com`
- `editor-api-va.capcutapi.com`

**Yeh global DEX string candidates hain, verified TTS endpoint mapping nahi.** Exact API paths, HTTP methods, payloads, authentication aur direct-versus-proxied provider routing is pass mein collect nahi hue. Koi API request replay nahi ki gayi.

Manifest mein `INTERNET`, `RECORD_AUDIO`, media permissions aur `FOREGROUND_SERVICE_DATA_SYNC` present hain. Permissions app-wide hain: microphone permission ka hona text-only TTS mein microphone use ka proof nahi.

Privacy implication: Remote generation paths ke liye user text server-side processing tak ja sakta hai. Kaunsa text kab transmit hota hai, storage retention aur third-party sharing runtime/policy evidence ke bina confirm nahi hain. Sensitive text ke liye ise offline-only assume na karein.

## 11. Remaining verification plan

1. Targeted decompilation: executor factory selection, provider managers and Retrofit/API annotations; exact endpoints and request fields.
2. Config defaults/remote overrides: provider, Qwen/V3 selection, region/account gates.
3. Voice catalog responses: Hindi/Hinglish, speaker IDs, provider mapping and entitlement.
4. Device test: fresh uncached Hindi text in airplane mode; cached sample separately.
5. Authorized traffic capture: destination, text payload, streamed format and saved output format; no credential bypass.
6. Performance test: time-to-first-audio, complete generation time, long-text failures, retry behavior.
7. Native/opaque asset follow-up and APK signing certificate provenance.

## Evidence files

- `manifest-summary.json`: manifest metadata and component/permission inventory.
- `inventory.json`: full ZIP paths and sizes.
- `tts-class-candidates.json`: broad keyword class candidates.
- `tts-call-references.json`: caller, DEX number and matching invocation target.
- `hostname-candidates.json`: hostname-only global string candidates, not API attribution.
- `report.md`: automatically generated bounded scan report.

**Bottom line:** Is APK mein substantial multi-provider TTS orchestration hai: ElevenLabs/Microsoft/Remote SAMI factory wiring, streaming/non-streaming modes, retry, caching aur editor integration confirmed static evidence hain. Active provider per voice, Hindi catalog, exact neural model aur complete offline operation abhi unverified hain.

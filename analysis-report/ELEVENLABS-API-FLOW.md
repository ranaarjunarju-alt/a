# ElevenLabsTextToSpeechExecutor — exact static API and flow

APK: `com.lemon.lvoverseas` 19.6.0, SHA-256 `46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f`.

Evidence: targeted JADX 1.5.3 output, including instruction dumps for coroutine methods that did not decompile cleanly. These are static findings, not captured successful requests. Generated `UnsupportedOperationException("Method not decompiled...")` placeholders are decompiler artifacts, not proven app behavior.

## 1. Actual client API

`ElevenLabsApiService` declares:

```java
@Headers({"Content-Type:application/json; charset=utf-8"})
@POST("/lv/v1/text_to_speech/new")
Call<Response<ElevenLabsAudioTaskRequestData>> audioTaskRequest(@Body TypedJson body);

@Headers({"Content-Type:application/json; charset=utf-8"})
@POST("/lv/v1/text_to_speech/query")
Call<Response<ElevenLabsAudioTaskResultData>> audioTaskResult(@Body TypedJson body);
```

Base URL construction in `ElevenLabsToneManager`:

```java
"https://" + ContextExtKt.hostEnv().developSettings().host().f79178a
```

`f79178a` is the field name shown by JADX in this extraction. It is the app environment's configured host, not a hardcoded ElevenLabs hostname in this service. The service is built with ByteDance Retrofit/TTNet, Gson converter and `TimeoutInterceptor`.

**Exact URL template:** `https://<configured-app-host>/lv/v1/text_to_speech/new` and corresponding `/query`. A specific live hostname has not been resolved. The earlier APK-wide `editor-api.capcutapi.com` candidate must not be presented as confirmed routing for this request.

**Important:** This is an app-backend task API with `platform: "11labs"`, not a direct client call to the public ElevenLabs `/v1/text-to-speech/{voice_id}` API. The backend's own upstream requests cannot be recovered from this client code alone.

## 2. Create-task payload

The following JSON shows the exact keys, constants and value types reconstructed from `ElevenLabsToneManager.c(...)`. Placeholder values are illustrative, not working credentials or actual voice IDs.

```json
{
  "platform": "11labs",
  "voice": "<selected task voice>",
  "resource_id": "<task resource identifier>",
  "word_boundary_enabled": false,
  "input": ["<text segment 1>", "<text segment 2>"],
  "sample_rate": 24000,
  "scene": "<lowercase from value, or empty string>",
  "mock_tone_info": "<task string, or empty string>"
}
```

Source mapping:

| JSON key | Source/behavior |
|---|---|
| `platform` | Literal `11labs` |
| `voice` | First string argument to manager `c`, from task `f74298c` |
| `resource_id` | Second string argument, from task `n` |
| `word_boundary_enabled` | Literal `false` |
| `input` | List of text segments |
| `sample_rate` | Manager `e(task)` passes literal `24000` into `c` |
| `scene` | If task `k` is nonempty, parse JSON, take `optString("from")`, lowercase using `Locale.ROOT`; otherwise `""` |
| `mock_tone_info` | Task `w` if nonempty, otherwise `""`; purpose not inferred from the name alone |

`24000` is a requested sample rate. It does not establish the actual downloaded audio's codec, container or sample rate.

## 3. Response envelope and polling

The shared `Response` maps `ret`, `errmsg`, `data`, `log_id`, `svr_time`, and `svr_timing` (some fields have aliases). `success()` checks `ret == "0"`.

Create response **data schema**:

```json
{
  "task_id": "<server task ID>",
  "text_lan": "<server language value>"
}
```

Despite its name, `ElevenLabsAudioTaskRequestData` is the response-data model for the create request, not the JSON request body.

Query request is constructed exactly as:

```json
{
  "platform": "11labs",
  "task_id": "<task_id returned by new>"
}
```

Query response **data schema**, illustrated with successful status:

```json
{
  "status": 0,
  "speaker_id": "<server speaker ID>",
  "resource_infos": [
    {
      "text": "<segment text>",
      "url": "<download URL>",
      "code": "<resource-level code string>",
      "msg": "<resource-level message>",
      "text_lan": "<language value>"
    }
  ]
}
```

Task status: `0` = succeeded, `1` = processing, `-1` = failed. The enum spells processing `PROCESSIONG`. Manager handles `1` by waiting/retrying, `0` by extracting resources, and other statuses as failure.

Polling parameters come from `TTSEngineConfig.getEleventLabs()` (spelling as extracted). When that config item is absent, the manager has fallbacks:

- `count = 20`
- `interval = 2000` milliseconds
- `increasingRate = 1.0`
- `enableIncreasing = false`

Instruction dump shows an inclusive counter `0..count`: fallback can therefore mean 21 query attempts, not exactly 20. Queries occur before the processing delay. Increasing mode multiplies the next interval by the configured rate. These are fallback values, not verified current remote configuration or a guaranteed wall-clock timeout; network request time is additional.

## 4. Executor flow

```text
TextToSpeechExecutorFactory
  → ElevenLabsTextToSpeechExecutor
  → executeTask coroutine
      → reject empty text (1100)
      → reading/preview: BaseTextToSpeechExecutor.k(...)
          → cached/base playback or executor readAudioFromNet path
      → saving: ElevenLabsTextToSpeechExecutor.m(...)
          ↓ on network-generation path
      ElevenLabsToneManager.e(task) [getToneData]
          → reset cancellation flag
          → prepare text segments
          → c(...) [getAudioResourceInfos]
              → POST /new
              → read task_id and text_lan
              → repeated POST /query
              → collect speaker_id and resource_infos
          → d(...) [getTextToAudioInfo]
              → b(...) [downloadAudioResources]
              → parallel resource downloads
              → TextToAudioInfo
          → Pair<TtsResult, TextToAudioInfo>
      → preview playback OR saved-result callback
      → clear current task / update state
```

Text preparation in manager `e(task)`:

- Reading branch: one-element list containing the input text.
- Other branch with task segmentation flag `i` enabled: `TtsApiServiceManager.generateSentence` receives `{"text": originalText}`; returned nested sentence lists are flattened. The segmentation HTTP annotation/path has not been extracted in this pass.
- Otherwise: split on newline.

There is no basis to invent a text-length limit or guarantee all languages; `text_lan` is returned by the backend.

## 5. Downloads, playback and local files

`downloadAudioResources` starts async jobs for resources and uses `awaitAll` to aggregate results. Each resource:

1. Checks for a nonempty URL.
2. Uses the lowercase hexadecimal **MD5 of the resource URL** as local filename.
3. Uses directory constructed as:

```text
DirectoryUtil.D("downloadAudio") + "speechAudio" + "/elevenlabs/"
```

4. Invokes the app `Downloader` with resource URL, directory, filename and tag `11labs`.
5. Returns text, file path, filename, status/error and `text_lan` in `ElevenLabsSummaryData`.

`TextToAudioInfo` collects downloaded paths, corresponding texts/languages and returned speaker ID. The absolute device path and exact media format are not established here.

The preview network path waits for generation/download, selects the first local audio path, and invokes `ThirdPartyPlayer`/Android `MediaPlayer`. A `withTimeout(60000)` wraps the playback coroutine; it is not proof of a 60-second generation limit.

**This specific ElevenLabs flow is task creation → polling → file download → playback.** The presence of other streaming executors in the app does not make this path ElevenLabs real-time streaming.

## 6. Errors and cancellation

Observed client error assignments include:

| Code | Observed condition |
|---|---|
| `1100` | Empty input text |
| `3101` | New-task request failure/exception |
| `3102` | Query failure / non-success task status |
| `3105` | Resource result absent/empty after polling; `requestTimeOut` label |
| `3201` | Audio download failure |
| `3500` | Empty local preview audio path |

Atomic cancellation flag is checked during task/result handling. The code includes stop/player cleanup paths. Runtime race behavior and server-side task cancellation were not tested.

## 7. Authentication and feature selection limits

The interface explicitly declares the JSON Content-Type header. No `xi-api-key` header is declared in these two methods. This does **not** prove the request is unauthenticated: the shared network stack may inject session cookies, headers, signatures or environment parameters. That complete transport/auth chain has not been traced, and no private API requests were executed.

`ElevenLabsTonePlatformConfig` has a default group string `v0`; its enable check compares group to `BusinessPhotoTemplateOptEntity.V1`. This is evidence of config gating, not proof of current account entitlement. The referenced constant's definition was not separately extracted.

Unknown: exact active host, valid voice/resource IDs, provider model ID, backend-to-ElevenLabs API version, account/region availability, authentication requirements, response audio codec, and runtime limits. Public ElevenLabs settings such as `stability`, `similarity_boost`, or `model_id` are not present in this reconstructed create payload; backend defaults remain unknown.

## 8. Source evidence

All source filenames below live in `analysis-report/elevenlabs-source/`:

- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsApiService.java`: both POST annotations and body types.
- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.java`: configured base URL, request construction, polling, segmentation and download coordination.
- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData.java`: create response fields.
- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData.java`: query response fields.
- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource.java`: downloadable resource schema.
- `com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2$1$task$1.java`: URL hash, file path and download call.
- `com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor.java` and executeTask coroutine class: execution modes and playback/save callbacks.
- `com.vega.core.net.Response.java`: envelope and success predicate.

Successful extraction: https://github.com/ranaarjunarju-alt/a/actions/runs/34611996511

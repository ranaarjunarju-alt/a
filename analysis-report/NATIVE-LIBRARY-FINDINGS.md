# Native libraries: complete inventory and focused TTS findings

> **Follow-up correction:** the formerly unidentified `libcapcut.so` container is now mapped to Zoin/Komprese and its intended speech-library outputs. See [Zoin/native deep analysis](ZOIN-NATIVE-DEEP-ANALYSIS.md). The older unknown-loader discussion below is superseded; payload decompression is not yet completed.

## Coverage

Every `.so` file under APK `lib/` was read and SHA-256 hashed, without executing it.

- **337 packaged entries**, **169 distinct basenames**.
- **169 arm64-v8a**, **168 armeabi-v7a**.
- The arm64-only basename is `libnpth_fp_unw.so`.
- Combined uncompressed bytes: **402,797,804** (~384.1 MiB). APK size can be smaller because entries are compressed; this is not a model-size total.
- **335 ELF files parsed** with pyelftools 0.32.
- **2 files are not standard ELF**: both `libcapcut.so` entries.

Full per-library table: [native-evidence/ALL-LIBRARIES.md](native-evidence/ALL-LIBRARIES.md).

Full machine-readable evidence:
- `native-evidence/all-libraries.json`: size/hash, ABI, ELF type, needed libraries, dynamic symbol counts, JNI counts, GNU stack/RELRO metadata, parse errors.
- `native-evidence/native-symbol-evidence.json`: selected symbols and bounded ASCII marker samples with offsets. Per-file sample limits: 250 matching defined/undefined symbols, 60 JNI names, 120 matching ASCII strings. Total matching string counts are recorded even when samples are capped.

This is **all-file structural inspection plus selected TTS symbols**, not complete disassembly/decompilation of every native function. No `ldd`, `dlopen`, library execution, app launch or traffic replay was used.

## 1. The two unusual libcapcut.so files

| ABI | Bytes | Prefix | Entropy, bits/byte |
|---|---:|---|---:|
| arm64-v8a | 2,342,912 | `7f 4b 4f 4d 01 00 00 00` | 7.996064 |
| armeabi-v7a | 2,457,600 | `7f 4b 4f 4d 01 00 00 00` | 7.997288 |

Normal ELF magic is `7f 45 4c 46`. These start with `7fKOM`, do not parse as ELF, do not identify as ZIP containers, and a byte search found no embedded literal ELF magic.

SHA-256:
- arm64: `74f79987612952d55256a07f95e36fbd4ddebe3b19f308065f655b68cf1e198b`
- armv7: `68946022f97d3daa2a95c412da9dc991be21d688eaed9ae2698125852e5c001d`

**Interpretation:** opaque custom-format payloads with very high entropy. Compression, encryption or packing are possibilities, not established diagnoses. The extension alone does not make them normal loadable shared libraries. The packer/vendor/decoder and any content hidden in them remain unknown. This is not evidence by itself that the APK is malicious or modified.

A meaningful next native step is to find the loader that reads these payloads, rather than repeatedly running an ELF parser on them.

## 2. Major libraries and why they matter

The roles below combine actual dependency/symbol evidence with cautious interpretation. Sizes/counts shown are arm64 unless noted.

| File | Evidence | Interpretation / limit |
|---|---|---|
| `libmiddle-bridge.so` | 59,631,736 bytes; 47,797 JNI-named defined dynamic symbols; TTS parameter/metadata bridge names | Large generated editor/native bridge, not proof of an embedded voice model |
| `libcccreator.so` | 39,876,624 bytes; 1,693 JNI-named symbols; dependencies include `libaudioeffect.so`, `libbytenn.so`, video/audio libraries | Editor/media engine with native audio/ML integration |
| `libmiddleware.so` | 24,908,280 bytes; bridge/editor dependencies in callers; many dynamic symbols | Native middleware, no ElevenLabs network-body conclusion from filename alone |
| `libaudioeffect.so` | 5,083,304 bytes; SAMICore and SAMIService symbols; 1,873 broad TTS/SAMI marker occurrences | Strong evidence of native SAMI audio implementation; not specifically ElevenLabs model weights |
| `libspeechengine.so` | 18,256 bytes; 18 JNI-named symbols; depends on `libspeechsdk.so` | Thin Java/native speech-engine bridge |
| `libspeechepg.so` | 18,256 bytes; depends on `libspeechspg.so` and `libsscronet.so` | Alternate/plugin speech bridge |
| `libspeechsdk.so` | 954,392 bytes; 1,834 defined dynamic symbols; TTS offline/online option strings | Speech SDK functionality; active mode/resources must be traced separately |
| `libbytenn.so` | 3,147,800 bytes; GLES/EGL/logger dependencies | Neural-compute-related library by naming/dependency context; exact model workloads not identified |
| `libsscronet.so` | 5,973,416 bytes; needs `libttboringssl.so`/`libttcrypto.so` | Packaged native networking/TLS component, not a recovered session/auth implementation |
| `liblynx.so` | 3,570,952 bytes; needs Lynx base, NAPI, Quick runtime components | Positive Lynx runtime evidence |
| `libDex2cPro.so` | 6,039,224 bytes; only one defined dynamic symbol, `JNI_OnLoad`; needs `libNPDex2c.so` | Opaque/native registration boundary worth further investigation; role not proven solely from name |
| `libNPDex2c.so` | 301 defined dynamic symbols, no JNI-named symbol count | Companion dependency; cannot assign decoded payload behavior without instruction trace |
| `libEncryptor.so` | 83,896 bytes; eight defined dynamic symbols; no retained broad TTS marker matches | Crypto-named component, but not proven to generate ElevenLabs request signatures |
| `libttmplayer.so` | Needs media codecs, audioeffect, `libwhisper.so` and rendering libraries | Native media playback chain; a `libwhisper.so` dependency is not proof of OpenAI Whisper model use |

Important negative distinction: absence of a keyword in a stripped/opaque binary does not prove absence of functionality.

## 3. Confirmed dependency chains

Selected `DT_NEEDED` edges:

```text
libmiddle-bridge.so
  ├─ libmiddleware.so
  ├─ libclipflow.so
  └─ libcccreator.so
       ├─ libaudioeffect.so
       ├─ libbytenn.so
       ├─ libttffmpeg.so
       └─ rendering / codec / image / database libraries

libaudioeffect.so
  ├─ libbytenn.so
  ├─ libsscronet.so
  ├─ libttboringssl.so
  ├─ libttcrypto.so
  └─ libttffmpeg.so

libspeechengine.so → libspeechsdk.so
libspeechepg.so → libspeechspg.so + libsscronet.so
libDex2cPro.so → libNPDex2c.so
```

A dependency edge proves linkage metadata, not which function is executed for a selected voice. Dynamic loading and runtime registration can add edges absent from `DT_NEEDED`.

## 4. Actual native TTS symbol evidence

Representative `libaudioeffect.so` defined symbols:

```text
SAMIExecutorCreate
SAMICoreGetPropertyById
SAMICoreInitContext
Java_com_mammon_audiosdk_bridge_SAMICoreHostBridge_Native_1SAMICoreCreateHandleByIdentify
Java_com_mammon_audiosdk_bridge_SAMICoreHostBridge_Native_1SAMICoreDestroyHandle
```

C++ mangled names also identify SAMIService connection, response and cancellation operations. Together with the earlier Java `SamiTextToSpeechEngine` online handle construction, this is a stronger link than a filename-only guess.

Representative `libmiddle-bridge.so` names expose:

- TTS payload getters/setters for digital-human/part metadata.
- TTS generation-scene fields.
- SAMI emotion fields.
- Batch/update voice parameter fields.

Its ASCII strings include `11labs_text_to_speech`, `microsoft_text_to_speech`, `sami_text_to_speech`, and `moyin_text_to_speech`. These are native editor integration markers; they do not move the already traced ElevenLabs REST-body construction out of the Java layer.

## 5. Offline voice resources: still not established

`libspeechsdk.so` has options for offline resource paths, offline voices, work mode, online language, pitch/speed/volume, caching and voice cloning. Generic SDK capabilities do not prove:

- required models are bundled,
- Hindi works offline,
- ElevenLabs is synthesized locally,
- an offline path is initialized for the active feature.

The concrete inspected SAMICore creation path is explicitly online. The two opaque `libcapcut.so` payloads mean the package cannot be exhaustively characterized by plaintext keyword inspection.

## 6. Basic ELF metadata, not a security certificate

Among 335 successfully parsed ELF files:

- All have no `.symtab` section in this scan.
- All advertise a non-executable `PT_GNU_STACK`.
- All include a `PT_GNU_RELRO` segment.

These are structural observations. They do not establish full RELRO without evaluating binding flags, absence of memory corruption, safe JNI usage, cryptographic correctness, current CVE status, or authenticity of the APK. The two non-ELF payloads are excluded from these statistics.

## 7. What “all libraries analyzed” means here

Done for every packaged entry: inventory, size/hash, format attempt, dependency/symbol/selected-string inspection where parsable, and anomaly reporting.

Not done: complete ARM/Thumb/AArch64 control-flow reconstruction; identification of every dynamically registered JNI method; opaque payload unpacking; runtime tracing; validation of library versions/CVEs; or a full security audit.

Highest-value follow-up targets are the loader for `7fKOM` payloads, native AppProperty methods used by common headers, and specific JNI paths between the editor bridge and SAMICore. No false claim of “100× complete” substitutes for those missing stages.

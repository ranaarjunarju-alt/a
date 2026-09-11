# Deep follow-up: packed speech libraries, decoder and JNI boundaries

## Executive conclusion

The earlier unidentified `libcapcut.so` payload is now **mapped to ByteDance Zoin's `capcut` module and the Komprese native decoder**. This is a substantially stronger result than filename/entropy guessing:

1. Java module metadata explicitly names the packaged container and its intended output libraries.
2. The loader locates that container in the installed native directory or base/split APKs.
3. Java calls `ZoinNative.nDecodeLibs` through `libzoin.so`.
4. `libzoin.so` imports `komprese_decompress` and declares `libkomprese-decompressor.so` as a dependency.
5. That decoder checks the exact **`7f 4b 4f 4d`** magic found in both packaged containers.
6. Independent Python CRC32 checks pass for both container headers.

**Not yet achieved:** decompression of the payloads, verification of their output bytes, a complete Komprese specification, or recovery of native request-property return values. “10000x” is not a measurable coverage claim.

This report supersedes the *unidentified loader/vendor* discussion in the earlier [native inventory](NATIVE-LIBRARY-FINDINGS.md) and [request-lineage report](ELEVENLABS-REQUEST-LINEAGE.md). Their physical inventory and Java protocol findings remain applicable.

## 1. Scope and reproducibility

APK: `app-release.apk`, package `com.lemon.lvoverseas`, version `19.6.0`, code `19600200`.

- Actual APK: **328,455,327 bytes**, SHA-256 `46bb98a05a6c3be31a4557e08668a249fa759b9d140eedadbed38c3b4738452f`.
- Local checkout contains an LFS pointer; APK-byte analysis ran on GitHub Actions after LFS checkout, with a mandatory SHA-256 assertion.
- Broad boundary run: **34622958457**, succeeded; targeted Zoin run: **34623675253**, succeeded.
- Androguard 4.1.3, JADX 1.5.3, pyelftools 0.32, Capstone 5.0.6; Python/Java only as analysis tools.
- No APK launch, library loading/execution, live authentication, traffic replay or third-party analysis upload.

### Additional evidence collected

| Evidence | Scope and limitation |
|---|---|
| `native-boundary/dex-loader-methods.json` | 52 literal-selected methods across 42 DEX; bounded instruction lists, not complete application call graph |
| `native-boundary/app-native-methods.json` | 54,110 native declarations under `com.vega`/`com.lemon`; declarations are not 54,110 unique native implementations or TTS features |
| `loader-source/` | 42 selected Java classes including request-context boundaries |
| `zoin-source/` | 49 selected top-level classes: Zoin package plus `SoLoadLancet`; extraction scope file records sizes |
| `native-boundary/loader-dynamic-symbols.json` | arm64 Dex2cPro, NPDex2c and Encryptor imports/exports and symbol relocations |
| `native-boundary/jni-entry-disassembly.json` | 9 bounded JNI-entry/direct-callee windows; not full control-flow recovery |
| `native-boundary/zoin-decoder-evidence.json` | arm64 Zoin/Komprese imports, exports, bounded strings, exported entry disassembly |
| `native-boundary/zoin-layout-verified.json` | independently checked header CRCs and metadata layout; **not decompressed output** |

Linear disassembly windows can include padding, adjacent routines, or data after a thunk. Such output is not evidence of executed instructions, malicious obfuscation, or a complete function body.

## 2. Reconstructed loader chain

```text
SoLoadLancet.loadLibrary(requested library)
  └─ ModuleManager.isSoDepsNeedsResolve(name)
       └─ ZoinDecompressHelper.a() when dependency resolution is needed
            └─ helper initializes Zoin/module manager/work directory
                 └─ registers LibModule(0), selects module "capcut"
                      └─ AbstractModule.doLoad(false)
                           ├─ LibModule.decode(...)
                           │    ├─ cross-process file lock
                           │    ├─ existing-file verification / reuse
                           │    └─ DecodeProcessor.decodeAndVerify(...)
                           │         ├─ nativeLibraryDir / classloader.findLibrary
                           │         ├─ base APK / split APK search
                           │         └─ ZoinNative.nDecodeLibs(...)
                           │              └─ libzoin.so
                           │                   └─ komprese_decompress
                           └─ LibModule.install()
                                └─ Inception: install native search directory
  └─ Librarian.loadLibraryForModule(...) or original loader
```

The trigger is conditional, not proof that every launch or every TTS request decompresses the module. Existing valid output files can be reused. `LibModule.decode` obtains a file-channel lock named `<module>.zoin.lib.lk`, avoiding competing decoder work across processes.

`DecodeProcessor` groups output files by `compressedName`, sorts blocks by decreasing compressed span and submits decode work through futures. For a block, it passes source path, APK path/entry name, begin/end offsets, total output length, output descriptors and flags into `nDecodeLibs`.

`blockCompressedName = "libcapcut_1.so"` is the **metadata block identifier used to associate outputs**, while `blockName = "libcapcut.so"` is used to locate the actual packaged file. This does not imply another missing top-level APK entry named `libcapcut_1.so`.

`Inception` selects implementations by effective Android SDK: V4 for 26+, V3 for 24–25, V2 for 23, V1 for 14–22; below 14 returns false. The effective SDK is incremented for preview builds. This APK has minSdk 23. The source supports native-library directory/classloader installation, not a demonstrated hidden DEX download.

**Sources:** `zoin-source/com.vega.launcher.lancet.SoLoadLancet.java`, `loader-source/com.vega.launcher.start.schedule.tasks.ZoinDecompressHelper.java`, and Zoin `LibModule`, `DecodeProcessor`, `Inception` classes in `zoin-source/`.

## 3. Intended payload layout — now explicitly identified

Metadata is in `zoin-source/com.bytedance.zoin.model.ModuleManager.java`.

### arm64-v8a

- Packaged container length: **2,342,912 bytes**.
- Declared compressed block: begin **0**, end **2,331,059**.
- Declared decompressed stream length: **15,233,416 bytes**.

| Intended output | Begin | End | File length | Metadata checkNumber | Expected CRC32 derived from metadata |
|---|---:|---:|---:|---:|---:|
| `libspeechspg.so` | 0 | 8,726,560 | 8,726,560 | 3,678,770,750 | 3,670,044,190 |
| `libonetex.so` | 8,726,560 | 15,233,416 | 6,506,856 | 2,234,619,001 | 2,228,112,145 |

### armeabi-v7a

- Packaged container length: **2,457,600 bytes**.
- Declared compressed block: begin **0**, end **2,447,947**.
- Declared decompressed stream length: **11,223,788 bytes**.

| Intended output | Begin | End | File length | Metadata checkNumber | Expected CRC32 derived from metadata |
|---|---:|---:|---:|---:|---:|
| `libspeechspg.so` | 0 | 6,564,808 | 6,564,808 | 2,094,404,258 | 2,087,839,450 |
| `libonetex.so` | 6,564,808 | 11,223,788 | 4,658,980 | 1,396,576,587 | 1,391,917,607 |

The ranges are contiguous and exactly sum to each declared output length. The physical entry is longer than the declared block span by **11,853** bytes on arm64 and **9,653** on armv7. Those bytes have not been classified; do not automatically call them padding, encryption material, or another payload.

`libspeechspg.so` and `libonetex.so` do not appear as direct library entries in the original physical inventory. Their module metadata explains the intended compressed packaging. **Expected output is not the same as recovered/verified output.**

### Speech dependency consequence

Zoin metadata declares:

```text
libspeechepg.so
  ├─ libspeechspg.so  ← output inside the capcut compressed module
  ├─ libc++_shared.so
  ├─ libttcrypto.so
  ├─ libttboringssl.so
  ├─ libsscronet.so
  ├─ libopus.so
  └─ libaudio_fingerprint.so
```

`speechspg` has the same listed supporting dependencies excluding itself. `onetex` is listed without dependencies in this module metadata; that is **not yet an ELF DT_NEEDED audit of onetex**.

This reveals an additional native speech-library boundary that a plain APK filename scan misses. It does not establish that the compressed library implements ElevenLabs inference, contains voice weights, or supports offline ElevenLabs generation. `speechspg`, `speechepg`, `speechsdk` and `speechengine` are distinct names and should not be conflated.

## 4. Komprese identification: code evidence, not entropy inference

### ELF linkage

The original all-library inventory records `libzoin.so` depending on `libkomprese-decompressor.so`. New dynamic symbol evidence records:

- `libzoin.so`: imports **`komprese_decompress`**.
- `libkomprese-decompressor.so`: exports that function at arm64 virtual address **`0xe9c`**, declared size **2,432 bytes**.
- `libzoin.so`: additionally imports ZIP-related `inflateInit2_`, `inflate`, `inflateEnd` and ordinary file I/O. Its strings refer to APK decoding and ZIP deflate. The outer APK compression path must not be confused with the inner Komprese payload.

`libbdzstd.so` and Zoin zstd wrapper classes are also packaged, but their presence alone does **not** make this particular native block a zstd stream. The concrete linkage above is the stronger evidence for this module.

### Header check recovered from exported function

Relevant arm64 instructions:

| Address | Observation |
|---|---|
| `0xec8` | Compare input length with `0xd` before the normal header-reading path |
| `0xee8–0xeec` | Read first eight bytes and the following four bytes |
| `0xef0–0xef8` | Construct `0x4d4f4b7f`, compare against first 32-bit word |
| `0xf08` | Set consumed header size to `0xc` = 12 bytes |
| `0xf0c–0xf14` | Require header byte at offset 4 to equal 1 |
| `0xf18–0xf6c` | Table-driven checksum calculation and comparison with word at offset 8 |
| `0xf70–0xf7c` | Advance payload pointer and subtract header length |
| `0xf90` onward | Variable-length integer parsing; not a plain ELF loader |

The normal-path magic is little-endian bytes **`7f 4b 4f 4d`**, exactly matching both inventory prefixes. Both saved headers begin:

```text
7f 4b 4f 4d  01 00 00 00  79 b8 f8 99
magic        version word  little-endian header checksum
```

Independent check using Python's standard library:

```python
zlib.crc32(bytes.fromhex("01 00 00 00")) == 0x99f8b879
```

Both ABI headers pass. The checked header fields, values and result are saved in `zoin-layout-verified.json`.

**Interpretation:** there is positive evidence for a versioned Komprese container, not merely “random-looking encrypted data.” Header CRC success authenticates neither the publisher nor the payload. The inner codec/filter stream and all decoder internal callees have not been reconstructed. No speculative decompression or execution of the APK's decoder was used.

## 5. Output verification is CRC32 plus length

`VerifyUtils.a(File)` reads in 32,768-byte chunks and returns:

```java
crc32.getValue() + file.length()
```

`VerifyUtils.b(directory, descriptors)` compares that value to each file's `checkNumber`. Thus the expected output CRC32 is:

```text
expected_crc32 = checkNumber - declared_file_length
```

Those derived values appear in the tables above. This is **not** a cryptographic digest, signature verification or proof of untamperability. The broader APK signing/security chain is outside this check.

`DecodeProcessor` also has temporary-file verification and rename helpers; native strings include temp-file operations. Output checksum verification must be performed on actual decoded bytes before reporting successful recovery. The current pass verifies only the expected arithmetic and layout.

## 6. JNI initialization and native request-property boundary

### Zoin registration

`ZoinNative.init()` loads `zoin`, calls `nInit(false, false, false)`, and considers initialization successful only if the result equals **1**. It catches `UnsatisfiedLinkError`; library presence does not prove successful initialization on a device.

Four Java native declarations are visible:

- `nInit(boolean, boolean, boolean) : int`
- `nDecodeLibs(String, String, String, long, long, int, ZoinBuildFileInfo[], boolean, boolean) : int`
- `nCalculateFileCRC(String) : long`
- `nDumpDebugLogs() : String`

In arm64 `libzoin.so`, `JNI_OnLoad` at **`0x22a0`**:

1. Obtains a JNI environment using VM vtable offset `0x30` with version `0x10006`.
2. Looks up the class string at virtual address `0x15cb`, matching `com/bytedance/zoin/ZoinNative` in string evidence.
3. Copies **0x60 = 96 bytes** of registration descriptors from address `0x83e8` to its stack.
4. Uses JNI environment vtable offset **`0x6b8`** (`RegisterNatives` on 64-bit JNI), with **4** entries.
5. Returns the JNI version on successful registration, otherwise failure.

Four entries × three 8-byte pointers explains the 96-byte table. This corroborates dynamic registration rather than requiring four `Java_com_...` exports. Individual descriptor function-pointer mappings have not been independently decoded in this pass.

### Dex2cPro / NPDex2c

`libDex2cPro.so` exports only `JNI_OnLoad` in the collected symbol evidence and imports `gVm`, `cacheInitial`, `getCacheClass`, and **`vmInterpret`**. NPDex2c's exports include interpreter/runtime support. This is evidence of a shared native interpretation/runtime boundary, not a recovered source implementation of each protected method.

Dex2cPro's arm64 `JNI_OnLoad` begins at **`0x5831dc`**, size **144 bytes**. Its first normal-path direct call, **`0x583360`**, is a PLT thunk loading relocation address **`0x5c5eb8`**, which resolves to **`cacheInitial`**. It also calls initialization routines at `0x57ac9c`, `0x335490`, `0x33605c`; bounded windows show zeroing of runtime structures and JNI calls.

Additional symbol relocation evidence:

| Relocation address | Symbol |
|---|---|
| `0x5c5e30` | `gVm` |
| `0x5c5e70` | `vmInterpret` |
| `0x5c5e88` | `getCacheClass` |
| `0x5c5eb8` | `cacheInitial` |

Strings include the exact `com/vega/launcher/init/config/AppPropertyImpl` class name and related companion classes. This associates the class with the native binary's referenced metadata; it **does not map a specific Java method to a particular native body**.

The previously unresolved request-property accessors remain:

```java
public final native int s();
public final native String w();
```

Their registration entries, complete execution paths and actual return values have not been recovered. Consequently this report supplies no invented device/account identifiers, native auth secrets or fully reproducible authenticated request.

### Encryptor is a separate boundary

`EncryptorUtil` explicitly loads `Encryptor` and exposes `ttEncrypt(byte[], int)`. Its ordinary `encrypt` wrapper checks non-null input, positive length, and equality between array length and supplied length; errors return null. `encryptFixedLength` has different length validation. Bounded `JNI_OnLoad` evidence exists, but not a recovered encryption protocol or a proven TTS request-body call path. Do not merge it with the previously established common-header MD5 or SAMI RSA flow solely because of its name.

## 7. Network bypass predicate — narrowed, not a replay recipe

`loader-source/com.lm.components.network.extra.NetworkRuntimeConfig.java` resolves the shared predicate:

```text
true if any header's name equals "x-tt-web-proxy"
otherwise true if static HashSet b contains request.getHost()
otherwise false
```

The source compares the header name, not a required header value, using Kotlin equality as decompiled. The host set is initialized empty and mutable; runtime membership remains unknown. A synchronized method removes names from it.

This explains conditional interceptor behavior in the earlier request-lineage report. It does not prove that an ordinary TTS request uses the bypass, that adding a header grants authorization, or that backend verification can be bypassed. The complete runtime cookies/session/native transport headers remain unobserved.

## 8. What changed for the original TTS question?

| Question | Updated answer |
|---|---|
| Why is `libcapcut.so` not ELF? | It is identified as the Zoin/Komprese compressed module container, with matching native magic validation. |
| Is there a hidden native speech dependency? | Metadata explicitly declares `libspeechspg.so` inside that module; `speechepg` depends on it. |
| Which files are intended to emerge? | `libspeechspg.so` and `libonetex.so`, with per-ABI ranges and verification numbers now documented. |
| Is it encrypted or malicious? | Neither follows from entropy or custom packaging. A decompression chain is positively identified. |
| Is the compressed speech library an ElevenLabs offline model? | Not established; outputs are not recovered and no such model/inference chain is proven. |
| Did the ElevenLabs endpoint/body change? | No. Earlier client lineage remains: CapCut backend `/lv/v1/text_to_speech/new` and `/query`, with platform `11labs`. |
| Can we derive the backend ElevenLabs key/model ID from this? | No. Client packaging is not backend configuration evidence. |
| Are native request-property values now known? | No; the runtime dependency and class-reference boundary are clearer, but values remain unresolved. |

## 9. Checks and next useful work

Run locally against saved evidence:

```bash
python scripts/check_tts_evidence.py
python scripts/summarize_native_boundary.py
python scripts/check_native_boundary.py
```

The first retains the previous 30 static assertions. The second regenerates verified layout evidence and checks **two header CRCs, two contiguous output layouts, and four declared file lengths**. The third checks the newly documented source/native boundary facts. These are static-evidence regression checks, not runtime or decompression tests.

Next steps with real evidential value, in order:

1. Reconstruct the remaining Komprese stream/filter descriptors and internal decoder routines; implement or identify a bounded, independently trusted decoder without executing APK libraries.
2. Recover output bytes for each ABI. Require declared lengths, ELF validity and **CRC32 + length** to match every output descriptor.
3. Inventory the recovered libraries' exports, dependencies and data sections, then trace actual speech call sites. Only then assess whether they contain inference machinery, model assets or network clients.
4. Recover native registration descriptors for the exact `AppPropertyImpl.s/w` methods and their full implementation/VM paths. Class-name strings are insufficient.
5. If later authorized and necessary, controlled runtime observation could answer session-specific header/voice-availability questions that static evidence cannot. No runtime work was performed here.

**Bottom line:** the opaque-library mystery is substantially resolved at the container/loader level. The actual decoded speech implementation and native request values are still open work—not silently treated as solved.

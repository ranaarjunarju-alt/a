# All packaged native libraries

ELF metadata and symbol/string heuristics only; not a functional audit or vulnerability proof.

| Library | ABI | Bytes | Defined dynamic symbols | JNI symbols | TTS marker hits | Dependencies |
|---|---|---:|---:|---:|---:|---|
| `libAGFX.so` | arm64-v8a | 755744 | 548 | 0 | 0 | libgaia_lib.so, liblog.so, libGLESv2.so, libEGL.so, libdl.so, libm.so, libc++_shared.so, libc.so |
| `libByteVC1_dec.so` | arm64-v8a | 478536 | 107 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libCepEngine.so` | arm64-v8a | 362464 | 266 | 13 | 0 | libc++_shared.so, liblog.so, libm.so, libdl.so, libc.so |
| `libDex2cPro.so` | arm64-v8a | 6039224 | 1 | 1 | 4 | libNPDex2c.so, liblog.so, libm.so, libdl.so, libc.so |
| `libEncryptor.so` | arm64-v8a | 83896 | 8 | 1 | 0 | liblog.so, libc.so, libm.so, libstdc++.so, libdl.so |
| `libNPDex2c.so` | arm64-v8a | 268688 | 301 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libTTMStrategyCenter.so` | arm64-v8a | 395664 | 757 | 1 | 0 | libTTMachineCore.so, liblog.so, libCepEngine.so, libbytedt.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libTTMachineCore.so` | arm64-v8a | 429872 | 734 | 1 | 0 | libkeva.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libalog.so` | arm64-v8a | 68056 | 14 | 1 | 0 | liblog.so, libz.so, libm.so, libdl.so, libc.so |
| `libanimax.so` | arm64-v8a | 657720 | 294 | 1 | 0 | libdl.so, libEGL.so, libGLESv3.so, libandroid.so, libc.so, libjnigraphics.so, liblog.so, liblynxbase.so, libm.so, libskity.so, libc++_shared.so |
| `libanimax_bytevc1.so` | arm64-v8a | 51184 | 4 | 1 | 0 | libdl.so, libGLESv3.so, libanimax.so, libc.so, libc++_shared.so |
| `libanimax_napi.so` | arm64-v8a | 84064 | 3 | 0 | 0 | libdl.so, libanimax.so, libc.so, libnapi.so, libc++_shared.so |
| `libanimax_textra.so` | arm64-v8a | 83952 | 4 | 1 | 0 | libdl.so, libanimax.so, libc.so, libskity.so, libtttext_lite.so, libc++_shared.so |
| `libapplovin-native-crash-reporter.so` | arm64-v8a | 860200 | 1994 | 3 | 0 | libandroid.so, liblog.so, libm.so, libdl.so, libc.so |
| `libart_sym.so` | arm64-v8a | 124720 | 347 | 0 | 0 | libnpth_dl.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libaudio_fingerprint_sdk.so` | arm64-v8a | 116768 | 63 | 0 | 0 | libm.so, libc++_shared.so, libdl.so, libc.so |
| `libaudioeffect.so` | arm64-v8a | 5083304 | 3767 | 13 | 1873 | libOpenSLES.so, libbytenn.so, libGLESv2.so, libEGL.so, libdl.so, libsscronet.so, libttboringssl.so, libttcrypto.so, libandroid.so, libiesapplogger.so, libm.so, libttffmpeg.so, libz.so, liblog.so, libc++_shared.so, libc.so |
| `libavmdlbase.so` | arm64-v8a | 477128 | 865 | 0 | 0 | liblog.so, libz.so, libttboringssl.so, libttcrypto.so, libvcbasekit.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libavmdlv2.so` | arm64-v8a | 1411344 | 313 | 1 | 0 | liblog.so, libz.so, libsscronet.so, libttboringssl.so, libttcrypto.so, libvcn.so, libavmdlbase.so, libvcbasekit.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbach-sdk-jni.so` | arm64-v8a | 117112 | 20 | 17 | 0 | libcccreator.so, libjazz.so, libiesapplogger.so, libgaia_lib.so, liblog.so, libjnigraphics.so, libandroid.so, libEGL.so, libGLESv2.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbdhm_native.so` | arm64-v8a | 599968 | 27 | 26 | 0 | liblog.so, libdl.so, libm.so, libc.so |
| `libbdvideouploader.so` | arm64-v8a | 837848 | 13 | 1 | 0 | libandroid.so, liblog.so, libz.so, libsscronet.so, libttboringssl.so, libttcrypto.so, libvcn.so, libvcbasekit.so, libdl.so, libm.so, libc++_shared.so, libc.so |
| `libbdzstd.so` | arm64-v8a | 231248 | 68 | 52 | 0 | libm.so, libdl.so, libc.so |
| `libbuffer.so` | arm64-v8a | 18184 | 19 | 11 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libbuffer_pgl.so` | arm64-v8a | 9000 | 12 | 11 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libbvcparser.so` | arm64-v8a | 51416 | 10 | 0 | 0 | libttffmpeg.so, libm.so, libdl.so, libc.so |
| `libbyteVC2dec.so` | arm64-v8a | 511360 | 36 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libbytebench.so` | arm64-v8a | 641376 | 616 | 73 | 0 | libkeva.so, libbytemonitor.so, libandroid.so, liblog.so, libGLESv2.so, libjnigraphics.so, libz.so, libEGL.so, libOpenSLES.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytedt.so` | arm64-v8a | 313800 | 52 | 0 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytehook.so` | arm64-v8a | 59064 | 20 | 1 | 0 | liblog.so, libshadowhook.so, libm.so, libdl.so, libc.so |
| `libbytemonitor.so` | arm64-v8a | 67408 | 32 | 2 | 0 | libandroid.so, liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytenn.so` | arm64-v8a | 3147800 | 206 | 0 | 0 | libiesapplogger.so, liblog.so, libGLESv2.so, libEGL.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytevc0.so` | arm64-v8a | 548760 | 25 | 0 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytevc1enc.so` | arm64-v8a | 1279016 | 31 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libc++_shared.so` | arm64-v8a | 919888 | 2195 | 0 | 0 | libc.so, libdl.so |
| `libcapcut.so` | arm64-v8a | 2342912 | ? | ? | ? |  |
| `libcccreator.so` | arm64-v8a | 39876624 | 7352 | 1693 | 276 | libandroid.so, liblog.so, libGLESv2.so, libjnigraphics.so, libz.so, libEGL.so, libOpenSLES.so, libm.so, libdl.so, libttffmpeg.so, libaudioeffect.so, liblens.so, libfastcv.so, libwcdb.so, libbytevc0.so, libyuv.so, libttheif_dec.so, libbytebench.so, libbvcparser.so, libiesapplogger.so, libByteVC1_dec.so, libbytevc1enc.so, libfdk-aac.so, libbytenn.so, libquick.so, libgaia_lib.so, libjazz.so, libnapi.so, libAGFX.so, libc++_shared.so, libc.so |
| `libchoosle.so` | arm64-v8a | 2048376 | 1 | 1 | 0 | liblundd.so, liblog.so, libm.so, libdl.so, libc.so |
| `libclipflow.so` | arm64-v8a | 4917760 | 9590 | 2495 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libconfigcenter.so` | arm64-v8a | 51144 | 21 | 1 | 0 | libvcbasekit.so, libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libcore.so` | arm64-v8a | 9504 | 8 | 0 | 0 |  |
| `libcybertron_base.so` | arm64-v8a | 509816 | 1443 | 25 | 0 | libandroid.so, liblog.so, libnpth_dl.so, libvcbasekit.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libdelta.so` | arm64-v8a | 18256 | 26 | 19 | 0 | libttboringssl.so, libttcrypto.so, liblog.so, libm.so, libdl.so, libc.so |
| `libenvguard.so` | arm64-v8a | 224680 | 1 | 1 | 0 | liblog.so, libc.so, libm.so, libstdc++.so, libdl.so |
| `libfastcv.so` | arm64-v8a | 1001232 | 205 | 0 | 0 | libEGL.so, libandroid.so, liblog.so, libGLESv2.so, libjnigraphics.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libfdk-aac.so` | arm64-v8a | 625312 | 1002 | 0 | 0 | libc.so, libm.so, libdl.so |
| `libfile_lock.so` | arm64-v8a | 18184 | 13 | 6 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libfile_lock_pgl.so` | arm64-v8a | 6312 | 6 | 6 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libfile_recorder.so` | arm64-v8a | 51472 | 8 | 1 | 0 | libbytehook.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libflipped.so` | arm64-v8a | 7320 | 8 | 1 | 0 | liblog.so, libm.so, libstdc++.so, libdl.so, libc.so |
| `libfly-main-color-lib.so` | arm64-v8a | 51024 | 77 | 5 | 0 | libandroid.so, liblog.so, libjnigraphics.so, libm.so, libdl.so, libc.so |
| `libgaia_lib.so` | arm64-v8a | 329704 | 392 | 0 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libgeckox_bspatch.so` | arm64-v8a | 119768 | 69 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libgifimage.so` | arm64-v8a | 51696 | 8 | 1 | 0 | libc++_shared.so, libjnigraphics.so, liblog.so, libdl.so, libandroid.so, libc.so, libm.so |
| `libgodzilla-acurate-asan.so` | arm64-v8a | 160168 | 20 | 9 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libnpth_unwind.so, libnpth_unw.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libgodzilla-memsponge.so` | arm64-v8a | 1043056 | 2109 | 26 | 0 | liblog.so, libshadowhook.so, libbytehook.so, libnpth_unw.so, libnpth_dl.so, libm.so, libdl.so, libc.so |
| `libgodzilla-sysopt.so` | arm64-v8a | 164440 | 20 | 10 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libgodzilla-touphic.so` | arm64-v8a | 40016 | 22 | 20 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libhakjnjjjnjjjjjjnnrrdcgmhutok.so` | arm64-v8a | 268688 | 301 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libhelios_bm.so` | arm64-v8a | 18424 | 9 | 1 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libiesapplogger.so` | arm64-v8a | 67616 | 75 | 2 | 0 | libm.so, libc++_shared.so, libdl.so, libc.so |
| `libimagepipeline.so` | arm64-v8a | 264328 | 8 | 1 | 0 | libc++_shared.so, liblog.so, libjnigraphics.so, libc.so, libm.so, libdl.so |
| `libjato.so` | arm64-v8a | 362800 | 126 | 126 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libart_sym.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libjazz.so` | arm64-v8a | 231840 | 203 | 0 | 0 | libquick.so, libnapi.so, libgaia_lib.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libjnbnnnsdk.so` | arm64-v8a | 268576 | 301 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libjnidispatch.so` | arm64-v8a | 165992 | 151 | 70 | 0 | libc.so, libdl.so, libm.so |
| `libkeva.so` | arm64-v8a | 215680 | 500 | 1 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libkomprese-decompressor.so` | arm64-v8a | 15800 | 1 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libkrypton.so` | arm64-v8a | 1674432 | 345 | 1 | 0 | libEGL.so, libGLESv3.so, libandroid.so, libc.so, libdl.so, libjnigraphics.so, liblog.so, liblynxbase.so, libm.so, libnapi.so, libskity.so, libz.so, libc++_shared.so |
| `liblens.so` | arm64-v8a | 3591872 | 49 | 1 | 0 | libbytenn.so, libfastcv.so, libEGL.so, libandroid.so, libGLESv2.so, liblog.so, libjnigraphics.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `liblog.so` | arm64-v8a | 9504 | 8 | 0 | 0 |  |
| `liblundd.so` | arm64-v8a | 268672 | 301 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `liblundljnhjjjjjennmes.so` | arm64-v8a | 625320 | 1 | 1 | 0 | libhakjnjjjnjjjjjjnnrrdcgmhutok.so, liblog.so, libm.so, libdl.so, libc.so |
| `liblundnkbaal.so` | arm64-v8a | 115240 | 1 | 1 | 0 | libjnbnnnsdk.so, liblog.so, libm.so, libdl.so, libc.so |
| `libluster.so` | arm64-v8a | 250720 | 8 | 1 | 0 | libterrain.so, liblog.so, libm.so, libdl.so, libc.so |
| `liblynx-imageloader.so` | arm64-v8a | 35168 | 1 | 1 | 0 | libc.so, libjnigraphics.so |
| `liblynx.so` | arm64-v8a | 3570952 | 586 | 1 | 0 | libdl.so, libc.so, libjnigraphics.so, liblynxbase.so, libm.so, libnapi.so, libquick.so, libc++_shared.so |
| `liblynx_krypton.so` | arm64-v8a | 51112 | 4 | 1 | 0 | libandroid.so, libc.so, libdl.so, libkrypton.so, liblynx.so, liblynxbase.so, libc++_shared.so |
| `liblynx_markdown.so` | arm64-v8a | 247888 | 41 | 38 | 0 | libdl.so, libc.so, liblynxbase.so, libtttext_lite.so, libc++_shared.so |
| `liblynx_v8_bridge.so` | arm64-v8a | 117008 | 9 | 1 | 0 | libdl.so, libc.so, liblynx.so, libnapi_v8.so, libv8_libfull.cr.so, liblynxbase.so, libc++_shared.so |
| `liblynxbase.so` | arm64-v8a | 232080 | 335 | 1 | 0 | libdl.so, libandroid.so, libc.so, liblog.so, libc++_shared.so |
| `liblynxsecurity.so` | arm64-v8a | 51112 | 5 | 2 | 0 | libc.so, libttcrypto.so, libc++_shared.so |
| `liblynxtrace.so` | arm64-v8a | 51200 | 34 | 1 | 0 | libdl.so, libc.so, liblynxbase.so, libc++_shared.so |
| `libmdlttkmedianetloader.so` | arm64-v8a | 83560 | 1 | 1 | 0 | libttkmedianet.so, libavmdlbase.so, libavmdlv2.so, libvcbasekit.so, libvcn.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmetasec_ov.so` | arm64-v8a | 1932896 | 12 | 1 | 0 | liblog.so, libnpth_dl.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmiddle-bridge.so` | arm64-v8a | 59631736 | 48299 | 47797 | 213 | libandroid.so, libEGL.so, libmiddleware.so, liblog.so, libjnigraphics.so, libclipflow.so, libcccreator.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmiddleware.so` | arm64-v8a | 24908280 | 48741 | 81 | 0 | liblens.so, libandroid.so, libjnigraphics.so, libEGL.so, libGLESv3.so, libGLESv2.so, libfastcv.so, liblog.so, libz.so, libnapi.so, libcccreator.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmonitorcollector-lib.so` | arm64-v8a | 313744 | 350 | 9 | 0 | libbytehook.so, libnpth_bt.so, libnpth_dl.so, libshadowhook.so, liblog.so, libGLESv2.so, libm.so, libdl.so, libc.so |
| `libnapi.so` | arm64-v8a | 247904 | 306 | 1 | 0 | libdl.so, libquick.so, liblog.so, libm.so, libc++_shared.so, libc.so |
| `libnative-filters.so` | arm64-v8a | 34824 | 8 | 1 | 0 | libc++_shared.so, liblog.so, libjnigraphics.so, libc.so, libm.so, libdl.so |
| `libnativeaudio.so` | arm64-v8a | 51528 | 12 | 1 | 0 | libbytehook.so, liblog.so, libOpenSLES.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnewep.so` | arm64-v8a | 51232 | 29 | 3 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnms.so` | arm64-v8a | 293272 | 1 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libnpth.so` | arm64-v8a | 181952 | 1 | 1 | 0 | libnpth_dl.so, libbytehook.so, libnpth_unw.so, libshadowhook.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_bt.so` | arm64-v8a | 7344 | 5 | 0 | 0 | liblog.so, libnpth_dl.so, libm.so, libdl.so, libc.so |
| `libnpth_dl.so` | arm64-v8a | 27352 | 22 | 0 | 0 | liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_dumper.so` | arm64-v8a | 90384 | 9 | 0 | 0 | libnpth_unwind.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnpth_fd_tracker.so` | arm64-v8a | 39472 | 22 | 0 | 0 | libnpth_dl.so, libshadowhook.so, libnpth_fp_unw.so, libprofiler.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_fp_unw.so` | arm64-v8a | 6112 | 3 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libnpth_heap_tracker.so` | arm64-v8a | 43176 | 1 | 0 | 0 | libnpth_dl.so, libnpth_bt.so, libnpth_unw.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_logcat.so` | arm64-v8a | 16856 | 0 | 0 | 0 | liblog.so, libdl.so, libc.so, libm.so |
| `libnpth_ref_monitor.so` | arm64-v8a | 28040 | 34 | 0 | 0 | libnpth_dl.so, libshadowhook.so, libprofiler.so, libnpth_fp_unw.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_repair.so` | arm64-v8a | 27896 | 32 | 1 | 0 | libnpth_dl.so, libshadowhook.so, libbytehook.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_tls_monitor.so` | arm64-v8a | 15904 | 1 | 0 | 0 | libnpth_dl.so, libbytehook.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_unw.so` | arm64-v8a | 29336 | 23 | 0 | 0 | liblog.so, libnpth_unwind.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnpth_unwind.so` | arm64-v8a | 222368 | 79 | 0 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnpth_vm_monitor.so` | arm64-v8a | 55968 | 47 | 0 | 0 | libnpth_dl.so, libbytehook.so, libnpth_unw.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_xasan.so` | arm64-v8a | 40480 | 2 | 0 | 0 | libnpth_dl.so, libnpth_unw.so, liblog.so, libc.so, libm.so, libc++_shared.so, libdl.so |
| `libnuro.so` | arm64-v8a | 953040 | 174 | 63 | 0 | libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libopus.so` | arm64-v8a | 411168 | 75 | 0 | 0 | libm.so, libdl.so, libc.so |
| `liborbuculum.so` | arm64-v8a | 412176 | 625 | 12 | 0 | liburiparser.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `liborbuculumadapter.so` | arm64-v8a | 149936 | 27 | 1 | 0 | libshadowhook.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libosfix.so` | arm64-v8a | 149616 | 48 | 12 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libpglarmor.so` | arm64-v8a | 68776 | 1 | 1 | 0 | liblog.so, libz.so, libm.so, libdl.so, libc.so |
| `libpine.so` | arm64-v8a | 53624 | 39 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libpl_droidsonroids_gif.so` | arm64-v8a | 42464 | 45 | 44 | 0 | libjnigraphics.so, libandroid.so, libGLESv2.so, liblog.so, libm.so, libdl.so, libc.so |
| `libpns_ttmachine_adapter.so` | arm64-v8a | 330032 | 27 | 4 | 0 | liblog.so, libTTMachineCore.so, libCepEngine.so, libkeva.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libppsec.so` | arm64-v8a | 166400 | 8 | 1 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libpreload.so` | arm64-v8a | 3657488 | 52 | 1 | 0 | libandroid.so, liblog.so, libavmdlbase.so, libvcbasekit.so, libTTMStrategyCenter.so, libttmplayer.so, libconfigcenter.so, libcybertron_base.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libprofiler.so` | arm64-v8a | 471424 | 7 | 1 | 0 | liblog.so, libnpth_dl.so, libshadowhook.so, libm.so, libdl.so, libc.so |
| `libpumbaa-network.so` | arm64-v8a | 51232 | 37 | 1 | 0 | libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libpush_ka.so` | arm64-v8a | 18184 | 8 | 1 | 0 | liblog.so, libnpth_dl.so, libm.so, libdl.so, libc.so |
| `libquick.so` | arm64-v8a | 1837504 | 1547 | 1 | 0 | libdl.so, liblog.so, libm.so, libc++_shared.so, libc.so |
| `libraphael.so` | arm64-v8a | 51336 | 10 | 3 | 0 | liblog.so, libz.so, libbytehook.so, libnpth_unw.so, libnpth_dl.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libres_pool.so` | arm64-v8a | 9925624 | 2 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libretouch_sdk.so` | arm64-v8a | 2066672 | 1864 | 795 | 0 | libmiddleware.so, liblens.so, libcccreator.so, libandroid.so, libjnigraphics.so, liblog.so, libz.so, libEGL.so, libGLESv2.so, libGLESv3.so, libnapi.so, libworker.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `librex.so` | arm64-v8a | 690264 | 13 | 6 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libropaencrypt.so` | arm64-v8a | 34904 | 8 | 1 | 0 | libttcrypto.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libserval_svg.so` | arm64-v8a | 282912 | 40 | 1 | 0 | libandroid.so, liblog.so, libm.so, libdl.so, libc.so |
| `libshadowhook.so` | arm64-v8a | 95000 | 47 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libshadowhook_nothing.so` | arm64-v8a | 1112 | 0 | 0 | 0 |  |
| `libskity.so` | arm64-v8a | 1411352 | 548 | 0 | 0 | libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libsliver.so` | arm64-v8a | 83984 | 15 | 1 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libspeechengine.so` | arm64-v8a | 18256 | 18 | 18 | 19 | libz.so, libandroid.so, libOpenSLES.so, libspeechsdk.so, libaudio_fingerprint_sdk.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libspeechepg.so` | arm64-v8a | 18256 | 18 | 18 | 18 | libz.so, libandroid.so, libOpenSLES.so, libspeechspg.so, libsscronet.so, libaudio_fingerprint_sdk.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libspeechsdk.so` | arm64-v8a | 954392 | 1834 | 0 | 68 | libdl.so, libz.so, liblog.so, libm.so, libOpenSLES.so, libc++_shared.so, libaudio_fingerprint_sdk.so, libc.so |
| `libsscronet.so` | arm64-v8a | 5973416 | 1225 | 1 | 2 | libz.so, libttboringssl.so, libttcrypto.so, libdl.so, libm.so, libandroid.so, liblog.so, libc++_shared.so, libc.so |
| `libstatic-webp.so` | arm64-v8a | 396088 | 16 | 1 | 0 | libc++_shared.so, liblog.so, libjnigraphics.so, libz.so, libdl.so, libc.so, libm.so |
| `libsys-platform.so` | arm64-v8a | 18184 | 14 | 3 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libsysoptimizer.so` | arm64-v8a | 412248 | 228 | 154 | 0 | libEGL.so, libGLESv2.so, libandroid.so, liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libtailor.so` | arm64-v8a | 84136 | 92 | 1 | 0 | libnpth_unw.so, liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libterrain.so` | arm64-v8a | 336672 | 308 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libtexturerender_native.so` | arm64-v8a | 67480 | 37 | 12 | 0 | liblog.so, libandroid.so, libEGL.so, libGLESv2.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libtobEmbedPagEncrypt.so` | arm64-v8a | 8304 | 8 | 2 | 0 | libc.so, libm.so, libstdc++.so, libdl.so |
| `libtt_c2pa_sdk.so` | arm64-v8a | 2937160 | 10 | 0 | 0 | libttcrypto.so, libdl.so, libc.so |
| `libtt_ugen_layout.so` | arm64-v8a | 360968 | 692 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libttboringssl.so` | arm64-v8a | 395304 | 565 | 0 | 0 | libttcrypto.so, libm.so, libdl.so, libc.so |
| `libttcrypto.so` | arm64-v8a | 1231952 | 2431 | 0 | 0 | libm.so, libdl.so, libc.so |
| `libttdemux.so` | arm64-v8a | 51096 | 129 | 0 | 0 | libvcbasekit.so, libttffmpeg.so, libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libttffmpeg.so` | arm64-v8a | 5771392 | 1275 | 0 | 9 | libm.so, libz.so, libttcrypto.so, libttboringssl.so, libc.so, libdl.so |
| `libttheif_dec.so` | arm64-v8a | 214864 | 67 | 0 | 0 | liblog.so, libbyteVC2dec.so, libByteVC1_dec.so, libm.so, libdl.so, libc.so |
| `libttkmedianet.so` | arm64-v8a | 632064 | 115 | 0 | 0 | libvcbasekit.so, libttboringssl.so, libttcrypto.so, liblog.so, libandroid.so, libm.so, libz.so, libc++_shared.so, libdl.so, libc.so |
| `libttmplayer.so` | arm64-v8a | 3594936 | 173 | 4 | 29 | libttr.so, libttdemux.so, libttffmpeg.so, liblog.so, libandroid.so, libEGL.so, libm.so, libz.so, libGLESv2.so, libOpenSLES.so, libjnigraphics.so, libaudioeffect.so, libwhisper.so, libByteVC1_dec.so, libbyteVC2dec.so, libconfigcenter.so, libvcbasekit.so, libc++_shared.so, libdl.so, libc.so |
| `libttmverify.so` | arm64-v8a | 18112 | 10 | 1 | 0 | libttffmpeg.so, libttboringssl.so, libttcrypto.so, libsscronet.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libttmverifylite.so` | arm64-v8a | 34672 | 23 | 1 | 0 | libttffmpeg.so, libttboringssl.so, libttcrypto.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libttr.so` | arm64-v8a | 625192 | 490 | 1 | 4 | libttffmpeg.so, libsscronet.so, libvcbasekit.so, libandroid.so, liblog.so, libEGL.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libtttext_lite.so` | arm64-v8a | 297312 | 434 | 2 | 0 | libdl.so, liblog.so, libm.so, libc++_shared.so, libc.so |
| `libundead_native_ability_q.so` | arm64-v8a | 264448 | 589 | 1 | 0 | liblog.so, libbinder_ndk.so, libm.so, libdl.so, libc.so |
| `liburiparser.so` | arm64-v8a | 100224 | 138 | 0 | 0 | libm.so, libdl.so, libc.so |
| `libvcbasekit.so` | arm64-v8a | 231568 | 460 | 1 | 0 | libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libvcn.so` | arm64-v8a | 214960 | 120 | 0 | 0 | libttcrypto.so, libttboringssl.so, liblog.so, libandroid.so, libm.so, libz.so, libc++_shared.so, libdl.so, libc.so |
| `libvcnverify.so` | arm64-v8a | 18184 | 9 | 1 | 0 | libvcn.so, libsscronet.so, libttcrypto.so, libttboringssl.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libvcnverifylite.so` | arm64-v8a | 34640 | 9 | 1 | 0 | libvcn.so, libttcrypto.so, libttboringssl.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libwasm.so` | arm64-v8a | 247792 | 5 | 1 | 0 | libdl.so, liblog.so, libquick.so, libm.so, libc++_shared.so, libc.so |
| `libwcdb.so` | arm64-v8a | 1763776 | 341 | 1 | 0 | liblog.so, libz.so, libdl.so, libstdc++.so, libm.so, libc.so |
| `libwhisper.so` | arm64-v8a | 38584 | 11 | 0 | 0 | libc++_shared.so, libm.so, libdl.so, libc.so |
| `libworker.so` | arm64-v8a | 313400 | 54 | 1 | 0 | libdl.so, libnapi.so, liblog.so, libquick.so, libm.so, libc++_shared.so, libc.so |
| `libyuv.so` | arm64-v8a | 215112 | 562 | 0 | 0 | libc.so, libm.so, libdl.so |
| `libzoin.so` | arm64-v8a | 20304 | 1 | 1 | 0 | liblog.so, libandroid.so, libkomprese-decompressor.so, libz.so, libm.so, libdl.so, libc.so |
| `libAGFX.so` | armeabi-v7a | 517816 | 545 | 0 | 0 | libgaia_lib.so, liblog.so, libGLESv2.so, libEGL.so, libdl.so, libm.so, libc++_shared.so, libc.so |
| `libByteVC1_dec.so` | armeabi-v7a | 469952 | 319 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libCepEngine.so` | armeabi-v7a | 231052 | 262 | 13 | 0 | libc++_shared.so, liblog.so, libm.so, libdl.so, libc.so |
| `libDex2cPro.so` | armeabi-v7a | 4760788 | 1 | 1 | 4 | libNPDex2c.so, liblog.so, libm.so, libdl.so, libc.so |
| `libEncryptor.so` | armeabi-v7a | 75348 | 54 | 1 | 0 | liblog.so, libc.so, libm.so, libstdc++.so, libdl.so |
| `libNPDex2c.so` | armeabi-v7a | 143276 | 303 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libTTMStrategyCenter.so` | armeabi-v7a | 341868 | 1038 | 1 | 0 | libTTMachineCore.so, liblog.so, libCepEngine.so, libbytedt.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libTTMachineCore.so` | armeabi-v7a | 289548 | 822 | 1 | 0 | libkeva.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libalog.so` | armeabi-v7a | 42892 | 14 | 1 | 0 | liblog.so, libz.so, libm.so, libdl.so, libc.so |
| `libanimax.so` | armeabi-v7a | 419552 | 373 | 1 | 0 | libdl.so, libEGL.so, libGLESv3.so, libandroid.so, libc.so, libjnigraphics.so, liblog.so, liblynxbase.so, libm.so, libskity.so, libc++_shared.so |
| `libanimax_bytevc1.so` | armeabi-v7a | 22028 | 4 | 1 | 0 | libdl.so, libGLESv3.so, libanimax.so, libc.so, libc++_shared.so |
| `libanimax_napi.so` | armeabi-v7a | 34432 | 3 | 0 | 0 | libdl.so, libanimax.so, libc.so, libm.so, libnapi.so, libc++_shared.so |
| `libanimax_textra.so` | armeabi-v7a | 34316 | 4 | 1 | 0 | libdl.so, libanimax.so, libc.so, libm.so, libskity.so, libtttext_lite.so, libc++_shared.so |
| `libapplovin-native-crash-reporter.so` | armeabi-v7a | 500664 | 2040 | 3 | 0 | libandroid.so, liblog.so, libm.so, libdl.so, libc.so |
| `libart_sym.so` | armeabi-v7a | 86508 | 347 | 0 | 0 | libnpth_dl.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libaudio_fingerprint_sdk.so` | armeabi-v7a | 91736 | 164 | 0 | 0 | libm.so, libc++_shared.so, libdl.so, libc.so |
| `libaudioeffect.so` | armeabi-v7a | 3394900 | 3730 | 13 | 1873 | libOpenSLES.so, libbytenn.so, libGLESv2.so, libEGL.so, libdl.so, libsscronet.so, libttboringssl.so, libttcrypto.so, libandroid.so, libiesapplogger.so, libm.so, libttffmpeg.so, libz.so, liblog.so, libc++_shared.so, libc.so |
| `libavmdlbase.so` | armeabi-v7a | 321156 | 865 | 0 | 0 | liblog.so, libz.so, libttboringssl.so, libttcrypto.so, libvcbasekit.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libavmdlv2.so` | armeabi-v7a | 1402736 | 313 | 1 | 0 | liblog.so, libz.so, libsscronet.so, libttboringssl.so, libttcrypto.so, libvcn.so, libavmdlbase.so, libvcbasekit.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbach-sdk-jni.so` | armeabi-v7a | 67204 | 20 | 17 | 0 | libcccreator.so, libjazz.so, libiesapplogger.so, libgaia_lib.so, liblog.so, libjnigraphics.so, libandroid.so, libEGL.so, libGLESv2.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbdhm_native.so` | armeabi-v7a | 317016 | 27 | 26 | 0 | liblog.so, libdl.so, libm.so, libc.so |
| `libbdvideouploader.so` | armeabi-v7a | 636700 | 5 | 1 | 0 | libandroid.so, liblog.so, libz.so, libsscronet.so, libttboringssl.so, libttcrypto.so, libvcn.so, libvcbasekit.so, libdl.so, libm.so, libc++_shared.so, libc.so |
| `libbdzstd.so` | armeabi-v7a | 181836 | 68 | 52 | 0 | libm.so, libdl.so, libc.so |
| `libbuffer.so` | armeabi-v7a | 18000 | 15 | 11 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libbuffer_pgl.so` | armeabi-v7a | 6580 | 12 | 11 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libbvcparser.so` | armeabi-v7a | 34588 | 6 | 0 | 0 | libttffmpeg.so, libm.so, libdl.so, libc.so |
| `libbyteVC2dec.so` | armeabi-v7a | 416772 | 151 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libbytebench.so` | armeabi-v7a | 521964 | 616 | 73 | 0 | libkeva.so, libbytemonitor.so, libandroid.so, liblog.so, libGLESv2.so, libjnigraphics.so, libz.so, libEGL.so, libOpenSLES.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytedt.so` | armeabi-v7a | 259972 | 44 | 0 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytehook.so` | armeabi-v7a | 41892 | 20 | 1 | 0 | liblog.so, libshadowhook.so, libm.so, libdl.so, libc.so |
| `libbytemonitor.so` | armeabi-v7a | 50868 | 27 | 2 | 0 | libandroid.so, liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytenn.so` | armeabi-v7a | 2266812 | 217 | 0 | 0 | libiesapplogger.so, liblog.so, libGLESv2.so, libEGL.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytevc0.so` | armeabi-v7a | 429472 | 21 | 0 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libbytevc1enc.so` | armeabi-v7a | 962700 | 31 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libc++_shared.so` | armeabi-v7a | 558904 | 2280 | 0 | 0 | libc.so, libdl.so |
| `libcapcut.so` | armeabi-v7a | 2457600 | ? | ? | ? |  |
| `libcccreator.so` | armeabi-v7a | 29127040 | 7352 | 1693 | 276 | libandroid.so, liblog.so, libGLESv2.so, libjnigraphics.so, libz.so, libEGL.so, libOpenSLES.so, libm.so, libdl.so, libttffmpeg.so, libaudioeffect.so, liblens.so, libfastcv.so, libwcdb.so, libbytevc0.so, libyuv.so, libttheif_dec.so, libbytebench.so, libbvcparser.so, libiesapplogger.so, libByteVC1_dec.so, libbytevc1enc.so, libfdk-aac.so, libbytenn.so, libquick.so, libgaia_lib.so, libjazz.so, libnapi.so, libAGFX.so, libc++_shared.so, libc.so |
| `libchoosle.so` | armeabi-v7a | 1642388 | 1 | 1 | 0 | liblundd.so, liblog.so, libm.so, libdl.so, libc.so |
| `libclipflow.so` | armeabi-v7a | 3663572 | 9588 | 2495 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libconfigcenter.so` | armeabi-v7a | 34416 | 17 | 1 | 0 | libvcbasekit.so, libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libcore.so` | armeabi-v7a | 1804 | 8 | 0 | 0 |  |
| `libcybertron_base.so` | armeabi-v7a | 374348 | 1432 | 25 | 0 | libandroid.so, liblog.so, libnpth_dl.so, libvcbasekit.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libdelta.so` | armeabi-v7a | 13860 | 22 | 19 | 0 | libttboringssl.so, libttcrypto.so, liblog.so, libm.so, libdl.so, libc.so |
| `libenvguard.so` | armeabi-v7a | 184272 | 1 | 1 | 0 | liblog.so, libc.so, libm.so, libstdc++.so, libdl.so |
| `libfastcv.so` | armeabi-v7a | 845452 | 205 | 0 | 0 | libEGL.so, libandroid.so, liblog.so, libGLESv2.so, libjnigraphics.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libfdk-aac.so` | armeabi-v7a | 464724 | 1004 | 0 | 0 | libdl.so, libc.so, libm.so |
| `libfile_lock.so` | armeabi-v7a | 13904 | 9 | 6 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libfile_lock_pgl.so` | armeabi-v7a | 4704 | 6 | 6 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libfile_recorder.so` | armeabi-v7a | 34548 | 4 | 1 | 0 | libbytehook.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libflipped.so` | armeabi-v7a | 5124 | 8 | 1 | 0 | liblog.so, libm.so, libstdc++.so, libdl.so, libc.so |
| `libfly-main-color-lib.so` | armeabi-v7a | 38488 | 75 | 5 | 0 | libandroid.so, liblog.so, libjnigraphics.so, libm.so, libdl.so, libc.so |
| `libgaia_lib.so` | armeabi-v7a | 239284 | 392 | 0 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libgeckox_bspatch.so` | armeabi-v7a | 86648 | 65 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libgifimage.so` | armeabi-v7a | 26496 | 4 | 1 | 0 | libdl.so, libc++_shared.so, libjnigraphics.so, liblog.so, libandroid.so, libc.so, libm.so |
| `libgodzilla-acurate-asan.so` | armeabi-v7a | 4976 | 9 | 9 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libnpth_unwind.so, libnpth_unw.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libgodzilla-memsponge.so` | armeabi-v7a | 662364 | 2109 | 26 | 0 | liblog.so, libshadowhook.so, libbytehook.so, libnpth_unw.so, libnpth_dl.so, libm.so, libdl.so, libc.so |
| `libgodzilla-sysopt.so` | armeabi-v7a | 113424 | 20 | 10 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libgodzilla-touphic.so` | armeabi-v7a | 24520 | 22 | 20 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libhakjnjjjnjjjjjjnnrrdcgmhutok.so` | armeabi-v7a | 143340 | 303 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libhelios_bm.so` | armeabi-v7a | 13972 | 5 | 1 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libiesapplogger.so` | armeabi-v7a | 42584 | 91 | 2 | 0 | libm.so, libc++_shared.so, libdl.so, libc.so |
| `libimagepipeline.so` | armeabi-v7a | 165584 | 9 | 1 | 0 | libdl.so, libc++_shared.so, liblog.so, libjnigraphics.so, libc.so, libm.so |
| `libjato.so` | armeabi-v7a | 206672 | 126 | 126 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libart_sym.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libjazz.so` | armeabi-v7a | 145088 | 203 | 0 | 0 | libquick.so, libnapi.so, libgaia_lib.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libjnbnnnsdk.so` | armeabi-v7a | 143740 | 303 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libjnidispatch.so` | armeabi-v7a | 116344 | 191 | 70 | 0 | libc.so, libdl.so, libm.so |
| `libkeva.so` | armeabi-v7a | 153604 | 534 | 1 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libkomprese-decompressor.so` | armeabi-v7a | 16904 | 1 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libkrypton.so` | armeabi-v7a | 1161452 | 395 | 1 | 0 | libEGL.so, libGLESv3.so, libandroid.so, libc.so, libdl.so, libjnigraphics.so, liblog.so, liblynxbase.so, libm.so, libnapi.so, libskity.so, libz.so, libc++_shared.so |
| `liblens.so` | armeabi-v7a | 2546232 | 40 | 1 | 0 | libbytenn.so, libfastcv.so, libEGL.so, libandroid.so, libGLESv2.so, liblog.so, libjnigraphics.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `liblog.so` | armeabi-v7a | 1804 | 8 | 0 | 0 |  |
| `liblundd.so` | armeabi-v7a | 143804 | 303 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `liblundljnhjjjjjennmes.so` | armeabi-v7a | 452268 | 1 | 1 | 0 | libhakjnjjjnjjjjjjnnrrdcgmhutok.so, liblog.so, libm.so, libdl.so, libc.so |
| `liblundnkbaal.so` | armeabi-v7a | 87448 | 1 | 1 | 0 | libjnbnnnsdk.so, liblog.so, libm.so, libdl.so, libc.so |
| `libluster.so` | armeabi-v7a | 212116 | 4 | 1 | 0 | libterrain.so, liblog.so, libm.so, libdl.so, libc.so |
| `liblynx-imageloader.so` | armeabi-v7a | 26172 | 39 | 1 | 0 | libc.so, libdl.so, libjnigraphics.so |
| `liblynx.so` | armeabi-v7a | 2333976 | 592 | 1 | 0 | libdl.so, libc.so, libjnigraphics.so, liblynxbase.so, libm.so, libnapi.so, libquick.so, libc++_shared.so |
| `liblynx_krypton.so` | armeabi-v7a | 30268 | 34 | 1 | 0 | libandroid.so, libc.so, libdl.so, libkrypton.so, liblynx.so, liblynxbase.so, libc++_shared.so |
| `liblynx_markdown.so` | armeabi-v7a | 161384 | 56 | 38 | 0 | libdl.so, libc.so, liblynxbase.so, libm.so, libtttext_lite.so, libc++_shared.so |
| `liblynx_v8_bridge.so` | armeabi-v7a | 63108 | 9 | 1 | 0 | libdl.so, libc.so, liblynx.so, libm.so, libnapi.so, libnapi_v8.so, libv8_libfull.cr.so, liblynxbase.so, libquick.so, libc++_shared.so |
| `liblynxbase.so` | armeabi-v7a | 149416 | 362 | 1 | 0 | libdl.so, libandroid.so, libc.so, liblog.so, libm.so, libc++_shared.so |
| `liblynxsecurity.so` | armeabi-v7a | 38460 | 58 | 2 | 0 | libc.so, libdl.so, libm.so, libttcrypto.so, libc++_shared.so |
| `liblynxtrace.so` | armeabi-v7a | 17984 | 34 | 1 | 0 | libdl.so, libc.so, liblynxbase.so, libc++_shared.so |
| `libmdlttkmedianetloader.so` | armeabi-v7a | 71096 | 1 | 1 | 0 | libttkmedianet.so, libavmdlbase.so, libavmdlv2.so, libvcbasekit.so, libvcn.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmetasec_ov.so` | armeabi-v7a | 1213368 | 104 | 1 | 0 | liblog.so, libnpth_dl.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmiddle-bridge.so` | armeabi-v7a | 47201152 | 48355 | 47797 | 213 | libandroid.so, libEGL.so, libmiddleware.so, liblog.so, libjnigraphics.so, libclipflow.so, libcccreator.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmiddleware.so` | armeabi-v7a | 19192828 | 59126 | 81 | 0 | liblens.so, libandroid.so, libjnigraphics.so, libEGL.so, libGLESv3.so, libGLESv2.so, libfastcv.so, liblog.so, libz.so, libnapi.so, libcccreator.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libmonitorcollector-lib.so` | armeabi-v7a | 182120 | 344 | 9 | 0 | libbytehook.so, libnpth_bt.so, libnpth_dl.so, libshadowhook.so, liblog.so, libGLESv2.so, libm.so, libdl.so, libc.so |
| `libnapi.so` | armeabi-v7a | 144980 | 336 | 1 | 0 | libdl.so, libquick.so, liblog.so, libm.so, libc++_shared.so, libc.so |
| `libnative-filters.so` | armeabi-v7a | 13944 | 4 | 1 | 0 | libdl.so, libc++_shared.so, liblog.so, libjnigraphics.so, libc.so, libm.so |
| `libnativeaudio.so` | armeabi-v7a | 30444 | 8 | 1 | 0 | libbytehook.so, liblog.so, libOpenSLES.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnewep.so` | armeabi-v7a | 26200 | 42 | 3 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnms.so` | armeabi-v7a | 194200 | 1 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libnpth.so` | armeabi-v7a | 109456 | 1 | 1 | 0 | libnpth_dl.so, libbytehook.so, libnpth_unw.so, libshadowhook.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_bt.so` | armeabi-v7a | 5312 | 5 | 0 | 0 | liblog.so, libnpth_dl.so, libm.so, libdl.so, libc.so |
| `libnpth_dl.so` | armeabi-v7a | 24312 | 22 | 0 | 0 | liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_dumper.so` | armeabi-v7a | 61592 | 9 | 0 | 0 | libnpth_unwind.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnpth_fd_tracker.so` | armeabi-v7a | 25900 | 22 | 0 | 0 | libnpth_dl.so, libshadowhook.so, libprofiler.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_heap_tracker.so` | armeabi-v7a | 27580 | 1 | 0 | 0 | libnpth_dl.so, libnpth_bt.so, libnpth_unw.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_logcat.so` | armeabi-v7a | 15788 | 0 | 0 | 0 | liblog.so, libdl.so, libc.so, libm.so |
| `libnpth_ref_monitor.so` | armeabi-v7a | 19960 | 34 | 0 | 0 | libnpth_dl.so, libshadowhook.so, libprofiler.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_repair.so` | armeabi-v7a | 21004 | 32 | 1 | 0 | libnpth_dl.so, libshadowhook.so, libbytehook.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_tls_monitor.so` | armeabi-v7a | 11840 | 1 | 0 | 0 | libnpth_dl.so, libbytehook.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_unw.so` | armeabi-v7a | 23596 | 35 | 0 | 0 | liblog.so, libnpth_unwind.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnpth_unwind.so` | armeabi-v7a | 150960 | 79 | 0 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libnpth_vm_monitor.so` | armeabi-v7a | 35424 | 47 | 0 | 0 | libnpth_dl.so, libbytehook.so, libnpth_unw.so, liblog.so, libc.so, libm.so, libdl.so |
| `libnpth_xasan.so` | armeabi-v7a | 2852 | 2 | 0 | 0 | libnpth_dl.so, libnpth_unw.so, liblog.so, libc.so, libm.so, libc++_shared.so, libdl.so |
| `libnuro.so` | armeabi-v7a | 624404 | 169 | 63 | 0 | libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libopus.so` | armeabi-v7a | 366144 | 73 | 0 | 0 | libm.so, libdl.so, libc.so |
| `liborbuculum.so` | armeabi-v7a | 276336 | 622 | 12 | 0 | liburiparser.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `liborbuculumadapter.so` | armeabi-v7a | 91988 | 23 | 1 | 0 | libshadowhook.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libosfix.so` | armeabi-v7a | 132752 | 44 | 12 | 0 | liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libpglarmor.so` | armeabi-v7a | 47100 | 1 | 1 | 0 | liblog.so, libz.so, libm.so, libdl.so, libc.so |
| `libpine.so` | armeabi-v7a | 42824 | 39 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libpl_droidsonroids_gif.so` | armeabi-v7a | 30264 | 45 | 44 | 0 | libjnigraphics.so, libandroid.so, libGLESv2.so, liblog.so, libm.so, libdl.so, libc.so |
| `libpns_ttmachine_adapter.so` | armeabi-v7a | 247640 | 22 | 4 | 0 | liblog.so, libTTMachineCore.so, libCepEngine.so, libkeva.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libppsec.so` | armeabi-v7a | 137248 | 4 | 1 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libpreload.so` | armeabi-v7a | 2619924 | 48 | 1 | 0 | libandroid.so, liblog.so, libavmdlbase.so, libvcbasekit.so, libTTMStrategyCenter.so, libttmplayer.so, libconfigcenter.so, libcybertron_base.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libprofiler.so` | armeabi-v7a | 275364 | 7 | 1 | 0 | liblog.so, libnpth_dl.so, libshadowhook.so, libm.so, libdl.so, libc.so |
| `libpumbaa-network.so` | armeabi-v7a | 26200 | 46 | 1 | 0 | libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libpush_ka.so` | armeabi-v7a | 13904 | 4 | 1 | 0 | liblog.so, libnpth_dl.so, libm.so, libdl.so, libc.so |
| `libquick.so` | armeabi-v7a | 444748 | 643 | 1 | 0 | libdl.so, liblog.so, libm.so, libc++_shared.so, libc.so |
| `libraphael.so` | armeabi-v7a | 30400 | 6 | 3 | 0 | liblog.so, libz.so, libbytehook.so, libnpth_unw.so, libnpth_dl.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libres_pool.so` | armeabi-v7a | 4099716 | 2 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libretouch_sdk.so` | armeabi-v7a | 1587000 | 2543 | 795 | 0 | libmiddleware.so, liblens.so, libcccreator.so, libandroid.so, libjnigraphics.so, liblog.so, libz.so, libEGL.so, libGLESv2.so, libGLESv3.so, libnapi.so, libworker.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `librex.so` | armeabi-v7a | 587500 | 9 | 6 | 0 | liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libropaencrypt.so` | armeabi-v7a | 26296 | 4 | 1 | 0 | libttcrypto.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libserval_svg.so` | armeabi-v7a | 142028 | 36 | 1 | 0 | libandroid.so, liblog.so, libm.so, libdl.so, libc.so |
| `libshadowhook.so` | armeabi-v7a | 74812 | 49 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libshadowhook_nothing.so` | armeabi-v7a | 704 | 0 | 0 | 0 |  |
| `libskity.so` | armeabi-v7a | 943780 | 588 | 0 | 0 | libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libsliver.so` | armeabi-v7a | 87720 | 10 | 1 | 0 | liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libspeechengine.so` | armeabi-v7a | 22104 | 18 | 18 | 19 | libz.so, libandroid.so, libOpenSLES.so, libspeechsdk.so, libaudio_fingerprint_sdk.so, liblog.so, libdl.so, libm.so, libc++_shared.so, libc.so |
| `libspeechepg.so` | armeabi-v7a | 22104 | 18 | 18 | 18 | libz.so, libandroid.so, libOpenSLES.so, libspeechspg.so, libsscronet.so, libaudio_fingerprint_sdk.so, liblog.so, libdl.so, libm.so, libc++_shared.so, libc.so |
| `libspeechsdk.so` | armeabi-v7a | 617104 | 1820 | 0 | 68 | libdl.so, libz.so, liblog.so, libm.so, libOpenSLES.so, libc++_shared.so, libaudio_fingerprint_sdk.so, libc.so |
| `libsscronet.so` | armeabi-v7a | 3648108 | 1225 | 1 | 2 | libz.so, libttboringssl.so, libttcrypto.so, libdl.so, libm.so, libandroid.so, liblog.so, libc++_shared.so, libc.so |
| `libstatic-webp.so` | armeabi-v7a | 329740 | 17 | 1 | 0 | libdl.so, libc++_shared.so, liblog.so, libjnigraphics.so, libz.so, libc.so, libm.so |
| `libsys-platform.so` | armeabi-v7a | 18048 | 10 | 3 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libsysoptimizer.so` | armeabi-v7a | 362412 | 209 | 154 | 0 | libEGL.so, libGLESv2.so, libandroid.so, liblog.so, libnpth_dl.so, libbytehook.so, libshadowhook.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libtailor.so` | armeabi-v7a | 40428 | 92 | 1 | 0 | libnpth_unw.so, liblog.so, libz.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libterrain.so` | armeabi-v7a | 185500 | 306 | 0 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libtexturerender_native.so` | armeabi-v7a | 38488 | 33 | 12 | 0 | liblog.so, libandroid.so, libEGL.so, libGLESv2.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libtobEmbedPagEncrypt.so` | armeabi-v7a | 5860 | 8 | 2 | 0 | libdl.so, libc.so, libm.so, libstdc++.so |
| `libtt_c2pa_sdk.so` | armeabi-v7a | 2082008 | 10 | 0 | 0 | libttcrypto.so, libdl.so, libm.so, libc.so |
| `libtt_ugen_layout.so` | armeabi-v7a | 222336 | 846 | 1 | 0 | liblog.so, libm.so, libdl.so, libc.so |
| `libttboringssl.so` | armeabi-v7a | 247476 | 561 | 0 | 0 | libttcrypto.so, libm.so, libdl.so, libc.so |
| `libttcrypto.so` | armeabi-v7a | 817448 | 2429 | 0 | 0 | libm.so, libdl.so, libc.so |
| `libttdemux.so` | armeabi-v7a | 42640 | 125 | 0 | 0 | libvcbasekit.so, libttffmpeg.so, libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libttffmpeg.so` | armeabi-v7a | 4721284 | 1275 | 0 | 9 | libm.so, libz.so, libttcrypto.so, libttboringssl.so, libc.so, libdl.so |
| `libttheif_dec.so` | armeabi-v7a | 153164 | 63 | 0 | 0 | liblog.so, libbyteVC2dec.so, libByteVC1_dec.so, libm.so, libdl.so, libc.so |
| `libttkmedianet.so` | armeabi-v7a | 457680 | 69 | 0 | 0 | libvcbasekit.so, libttboringssl.so, libttcrypto.so, liblog.so, libandroid.so, libm.so, libz.so, libc++_shared.so, libdl.so, libc.so |
| `libttmplayer.so` | armeabi-v7a | 2388164 | 159 | 4 | 30 | libttr.so, libttdemux.so, libttffmpeg.so, liblog.so, libandroid.so, libEGL.so, libm.so, libz.so, libGLESv2.so, libOpenSLES.so, libjnigraphics.so, libaudioeffect.so, libwhisper.so, libByteVC1_dec.so, libbyteVC2dec.so, libconfigcenter.so, libvcbasekit.so, libc++_shared.so, libdl.so, libc.so |
| `libttmverify.so` | armeabi-v7a | 13748 | 6 | 1 | 0 | libttffmpeg.so, libttboringssl.so, libttcrypto.so, libsscronet.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libttmverifylite.so` | armeabi-v7a | 17956 | 19 | 1 | 0 | libttffmpeg.so, libttboringssl.so, libttcrypto.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libttr.so` | armeabi-v7a | 415604 | 490 | 1 | 4 | libttffmpeg.so, libsscronet.so, libvcbasekit.so, libandroid.so, liblog.so, libEGL.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libtttext_lite.so` | armeabi-v7a | 210668 | 468 | 2 | 0 | libdl.so, liblog.so, libm.so, libc++_shared.so, libc.so |
| `libundead_native_ability_q.so` | armeabi-v7a | 149248 | 595 | 1 | 0 | liblog.so, libbinder_ndk.so, libm.so, libdl.so, libc.so |
| `liburiparser.so` | armeabi-v7a | 75376 | 134 | 0 | 0 | libm.so, libdl.so, libc.so |
| `libvcbasekit.so` | armeabi-v7a | 161532 | 456 | 1 | 0 | libandroid.so, liblog.so, libm.so, libc++_shared.so, libdl.so, libc.so |
| `libvcn.so` | armeabi-v7a | 124556 | 115 | 0 | 0 | libttcrypto.so, libttboringssl.so, liblog.so, libandroid.so, libm.so, libz.so, libc++_shared.so, libdl.so, libc.so |
| `libvcnverify.so` | armeabi-v7a | 9720 | 5 | 1 | 0 | libvcn.so, libsscronet.so, libttcrypto.so, libttboringssl.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libvcnverifylite.so` | armeabi-v7a | 13860 | 5 | 1 | 0 | libvcn.so, libttcrypto.so, libttboringssl.so, liblog.so, libandroid.so, libm.so, libz.so, libdl.so, libc.so |
| `libwasm.so` | armeabi-v7a | 190052 | 67 | 1 | 0 | libdl.so, liblog.so, libquick.so, libm.so, libc++_shared.so, libc.so |
| `libwcdb.so` | armeabi-v7a | 923892 | 344 | 1 | 0 | liblog.so, libz.so, libdl.so, libstdc++.so, libm.so, libc.so |
| `libwhisper.so` | armeabi-v7a | 46620 | 37 | 0 | 0 | libc++_shared.so, libm.so, libdl.so, libc.so |
| `libworker.so` | armeabi-v7a | 157248 | 54 | 1 | 0 | libdl.so, libnapi.so, liblog.so, libquick.so, libm.so, libc++_shared.so, libc.so |
| `libyuv.so` | armeabi-v7a | 157472 | 558 | 0 | 0 | libdl.so, libc.so, libm.so |
| `libzoin.so` | armeabi-v7a | 16060 | 1 | 1 | 0 | liblog.so, libandroid.so, libkomprese-decompressor.so, libz.so, libm.so, libdl.so, libc.so |

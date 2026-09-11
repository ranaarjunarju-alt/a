package com.vega.audio.tone.tts.engine.server;

import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.core.context.ContextExtKt;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes26.dex */
public final class RemoteSAMIToneManager {

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f74432a = new AtomicBoolean(false);
    public final RemoteSAMICacheManager b = new RemoteSAMICacheManager();

    /* renamed from: c, reason: collision with root package name */
    public final String f74433c = "https://" + ContextExtKt.hostEnv().developSettings().host().f79178a + "/lv/v1/text_to_audio";

    /* renamed from: d, reason: collision with root package name */
    public volatile long f74434d;

    /* loaded from: classes11.dex */
    public static final class Companion {
    }

    /* loaded from: classes28.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f74435a;

        static {
            int[] iArr = new int[TextToSpeechReportScene.values().length];
            try {
                iArr[TextToSpeechReportScene.TTS_UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextToSpeechReportScene.TTS_UPDATE_SUBTITLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextToSpeechReportScene.AUDIO_PANEL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TextToSpeechReportScene.AUDIO_CLONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TextToSpeechReportScene.LONG_TEXT_EDITOR.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TextToSpeechReportScene.AI_SCRIPT.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TextToSpeechReportScene.LONG_TEXT_EDITOR_DEFAULT_SPLIT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TextToSpeechReportScene.DIGITAL_HUMAN.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TextToSpeechReportScene.LIP_SYNC.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TextToSpeechReportScene.TEXT_TO_VIDEO.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                iArr[TextToSpeechReportScene.CUT_SAME_AUDIO_PANEL.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                iArr[TextToSpeechReportScene.CC4B_DIGITAL_HUMAN.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                iArr[TextToSpeechReportScene.CC4B_SCRIPT_TO_VIDEO.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                iArr[TextToSpeechReportScene.TEXT_TO_VIDEO_AUDIO_CLONE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                iArr[TextToSpeechReportScene.AUDIO_CLONE_MUSIC.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                iArr[TextToSpeechReportScene.DIGITAL_HUMAN_TEXT.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                iArr[TextToSpeechReportScene.DIGITAL_HUMAN_SUBTITLE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
            f74435a = iArr;
        }
    }

    static {
        new Companion();
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x035d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:110:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0247 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0244  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02a8 A[Catch: all -> 0x02f0, TryCatch #0 {all -> 0x02f0, blocks: (B:84:0x0249, B:86:0x0264, B:88:0x02a8, B:95:0x02c0, B:96:0x02d5, B:94:0x02bd, B:83:0x0247), top: B:133:0x0247 }] */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x02ba  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(com.vega.audio.tone.tts.core.TextToSpeechTask r53, boolean r54, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo>> r55) throws java.security.NoSuchAlgorithmException {
        /*
            r52 = this;
            r5 = r55
            boolean r0 = r5 instanceof com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager$process$1
            r4 = r52
            if (r0 == 0) goto L35e
            r2 = r5
            com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager$process$1 r2 = (com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager$process$1) r2
            int r3 = r2.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r3 & r1
            if (r0 == 0) goto L35e
            int r3 = r3 - r1
            r2.t = r3
        L16:
            java.lang.Object r5 = r2.r
            java.lang.Object r10 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r2.t
            r0 = 1
            java.lang.String r3 = ""
            if (r1 == 0) goto L45
            if (r1 != r0) goto L3ff
            java.lang.Object r1 = r2.q
            java.lang.String r1 = (java.lang.String) r1
            kotlin.ResultKt.throwOnFailure(r5)
        L2c:
            r0 = r5
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            com.vega.aigcapi.materialgenerate.TtsResult r0 = (com.vega.aigcapi.materialgenerate.TtsResult) r0
            com.vega.aigcapi.materialgenerate.StatusResult r2 = r0.f69163a
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            if (r2 != r0) goto L3fe
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager r7 = r4.b
            r7.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            goto L365
        L45:
            kotlin.ResultKt.throwOnFailure(r5)
            com.vega.audio.tone.util.TextToSpeechReportInfo$Companion r5 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion
            r0 = r53
            java.lang.String r1 = r0.k
            r5.getClass()
            com.vega.audio.tone.util.TextToSpeechReportInfo r1 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion.a(r1)
            if (r1 == 0) goto L5d
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r7 = r1.getFrom()
            if (r7 != 0) goto L5f
        L5d:
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r7 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.NONE
        L5f:
            if (r1 == 0) goto L67
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r9 = r1.getCloneFrom()
            if (r9 != 0) goto L69
        L67:
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r9 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.NONE
        L69:
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager r1 = r4.b
            r1.getClass()
            java.lang.String r6 = r0.a()
            java.lang.String r8 = "MD5"
            if (r54 == 0) goto Lc3
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r8)
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            byte[] r1 = r6.getBytes(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            byte[] r5 = r5.digest(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1 r1 = new kotlin.jvm.functions.Function1<java.lang.Byte, java.lang.CharSequence>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1
                static {
                    /*
                        com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1 r0 = new com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1) com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1.e com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(java.lang.Byte r5) {
                    /*
                        r4 = this;
                        java.lang.Number r5 = (java.lang.Number) r5
                        byte r0 = r5.byteValue()
                        r3 = 1
                        java.lang.Object[] r2 = new java.lang.Object[r3]
                        r1 = 0
                        java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
                        r2[r1] = r0
                        java.lang.Object[] r1 = java.util.Arrays.copyOf(r2, r3)
                        java.lang.String r0 = "%02x"
                        java.lang.String r1 = java.lang.String.format(r0, r1)
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            java.lang.String r1 = kotlin.collections.ArraysKt.D(r5, r3, r1)
        L90:
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager r5 = r4.b
            com.vega.audio.tone.tts.engine.server.RemoteSAMIResponse r43 = r5.b(r1)
            if (r43 == 0) goto La4
            java.lang.String r6 = r43.getRet()
            java.lang.String r5 = "0"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r5)
            if (r5 != 0) goto Lc0
        La4:
            r16 = 1
        La6:
            java.lang.String r6 = "RemoteSAMIToneManager"
            if (r16 != 0) goto Lae
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r5 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AUDIO_CLONE
            if (r7 != r5) goto L2de
        Lae:
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r5 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AUDIO_CLONE
            if (r7 != r5) goto Lbd
            r21 = 0
        Lb4:
            java.lang.String r15 = "voice_clone"
            java.lang.String r14 = "requestSync -> textAudioResponse="
            java.lang.String r13 = "requestSync -> textAudioRequest="
            r43 = 0
            goto Lef
        Lbd:
            r21 = r54
            goto Lb4
        Lc0:
            r16 = 0
            goto La6
        Lc3:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            r5.append(r6)
            float r1 = r0.f74300g
            r5.append(r1)
            java.lang.String r6 = r5.toString()
            java.security.MessageDigest r5 = java.security.MessageDigest.getInstance(r8)
            java.nio.charset.Charset r1 = kotlin.text.Charsets.UTF_8
            byte[] r1 = r6.getBytes(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            byte[] r5 = r5.digest(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2 r1 = new kotlin.jvm.functions.Function1<java.lang.Byte, java.lang.CharSequence>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2
                static {
                    /*
                        com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2 r0 = new com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2) com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2.e com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(java.lang.Byte r5) {
                    /*
                        r4 = this;
                        java.lang.Number r5 = (java.lang.Number) r5
                        byte r0 = r5.byteValue()
                        r3 = 1
                        java.lang.Object[] r2 = new java.lang.Object[r3]
                        r1 = 0
                        java.lang.Byte r0 = java.lang.Byte.valueOf(r0)
                        r2[r1] = r0
                        java.lang.Object[] r1 = java.util.Arrays.copyOf(r2, r3)
                        java.lang.String r0 = "%02x"
                        java.lang.String r1 = java.lang.String.format(r0, r1)
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager$getKey$$inlined$md5$2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            java.lang.String r1 = kotlin.collections.ArraysKt.D(r5, r3, r1)
            goto L90
        Lef:
            java.lang.String r5 = r0.b     // Catch: java.lang.Throwable -> L2dc
            r51 = r5
            int r5 = r0.f     // Catch: java.lang.Throwable -> L2dc
            r19 = r5
            java.lang.String r5 = r0.f74298c     // Catch: java.lang.Throwable -> L2dc
            r20 = r5
            boolean r5 = r0.i     // Catch: java.lang.Throwable -> L2da
            r18 = r5
            java.lang.String r25 = r7.getInfo()     // Catch: java.lang.Throwable -> L2da
            int[] r8 = com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager.WhenMappings.f74435a     // Catch: java.lang.Throwable -> L2da
            int r5 = r9.ordinal()     // Catch: java.lang.Throwable -> L2da
            r5 = r8[r5]     // Catch: java.lang.Throwable -> L2da
            switch(r5) {
                case 1: goto L1f9;
                case 2: goto L1eb;
                case 3: goto L1dd;
                case 4: goto L1cf;
                case 5: goto L1c1;
                case 6: goto L1b3;
                case 7: goto L1a5;
                case 8: goto L197;
                case 9: goto L189;
                case 10: goto L17b;
                case 11: goto L16c;
                case 12: goto L15d;
                case 13: goto L14e;
                case 14: goto L13f;
                case 15: goto L130;
                case 16: goto L121;
                case 17: goto L112;
                default: goto L10e;
            }     // Catch: java.lang.Throwable -> L2da
        L10e:
            r31 = r43
            goto L206
        L112:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.u()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L121:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.w()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L130:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.n()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L13f:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.k0()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L14e:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.h0()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L15d:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.g0()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L16c:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.d0()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L17b:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.j0()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L189:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.p()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L197:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.l()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1a5:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.s()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1b3:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.r()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1c1:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.N()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1cf:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.y()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1dd:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.x()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1eb:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.t()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
            goto L206
        L1f9:
            com.vega.costreport.aigc.BabiUtil r5 = com.vega.costreport.aigc.BabiUtil.f79727a     // Catch: java.lang.Throwable -> L2da
            r5.getClass()     // Catch: java.lang.Throwable -> L2da
            com.vega.costreport.aigc.BabiParams r5 = com.vega.costreport.aigc.BabiUtil.v()     // Catch: java.lang.Throwable -> L2da
            java.lang.String r31 = com.vega.core.ext.ExtentionKt.toJson(r5)     // Catch: java.lang.Throwable -> L2da
        L206:
            com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal r26 = new com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal     // Catch: java.lang.Throwable -> L2da
            r45 = 0
            r36 = 0
            r49 = 14
            r22 = 0
            r44 = r26
            r46 = r45
            r47 = r45
            r48 = r45
            r50 = r22
            r44.<init>(r45, r46, r47, r48, r49, r50)     // Catch: java.lang.Throwable -> L2da
            com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig r27 = new com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig     // Catch: java.lang.Throwable -> L2da
            if (r54 == 0) goto L22a
            java.lang.String r33 = "mp3"
        L223:
            int r5 = r0.h     // Catch: java.lang.Throwable -> L2da
            r17 = r5
            if (r54 == 0) goto L230
            goto L22d
        L22a:
            java.lang.String r33 = "wav"
            goto L223
        L22d:
            r12 = 1065353216(0x3f800000, float:1.0)
            goto L232
        L230:
            float r12 = r0.f74300g     // Catch: java.lang.Throwable -> L2da
        L232:
            r37 = 1
            com.vega.edit.base.tone.EmotionOption r5 = r0.q     // Catch: java.lang.Throwable -> L2da
            if (r5 == 0) goto L240
            java.lang.String r11 = r5.e     // Catch: java.lang.Throwable -> L23d
            if (r11 != 0) goto L241
            goto L240
        L23d:
            r5 = move-exception
            goto L2f1
        L240:
            r11 = r3
        L241:
            if (r5 == 0) goto L244
            goto L247
        L244:
            r8 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L249
        L247:
            double r8 = r5.f     // Catch: java.lang.Throwable -> L2f0
        L249:
            r41 = 8
            r32 = r27
            r34 = r17
            r35 = r12
            r38 = r11
            r39 = r8
            r42 = r22
            r32.<init>(r33, r34, r35, r36, r37, r38, r39, r41, r42)     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r9 = r0.r     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r8 = r0.s     // Catch: java.lang.Throwable -> L2f0
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r15)     // Catch: java.lang.Throwable -> L2f0
            if (r5 == 0) goto L2b5
        L264:
            com.vega.audio.tone.tts.engine.server.RemoteSAMIRequest r5 = new com.vega.audio.tone.tts.engine.server.RemoteSAMIRequest     // Catch: java.lang.Throwable -> L2f0
            r32 = 16
            r23 = r1
            r24 = r18
            r28 = r9
            r29 = r8
            r30 = r15
            r33 = r22
            r17 = r5
            r18 = r51
            r19 = r19
            r20 = r20
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33)     // Catch: java.lang.Throwable -> L2f0
            com.google.gson.Gson r8 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r11 = r8.toJson(r5)     // Catch: java.lang.Throwable -> L2f0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f0
            r5.<init>(r13)     // Catch: java.lang.Throwable -> L2f0
            r5.append(r11)     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L2f0
            com.vega.log.BLog.i(r6, r5)     // Catch: java.lang.Throwable -> L2f0
            com.vega.core.net.NetworkManagerWrapper r9 = com.vega.core.net.NetworkManagerWrapper.f79356a     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r8 = r4.f74433c     // Catch: java.lang.Throwable -> L2f0
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L2f0
            r5.<init>(r11)     // Catch: java.lang.Throwable -> L2f0
            r9.getClass()     // Catch: java.lang.Throwable -> L2f0
            com.bytedance.retrofit2.SsResponse r5 = com.vega.core.net.NetworkManagerWrapper.q(r8, r5)     // Catch: java.lang.Throwable -> L2f0
            if (r5 == 0) goto L2ba
            com.google.gson.Gson r9 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Throwable -> L2f0
            java.lang.Object r8 = r5.body()     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r8 = (java.lang.String) r8     // Catch: java.lang.Throwable -> L2f0
            if (r8 != 0) goto L2bd
            goto L2b8
        L2b5:
            r15 = r43
            goto L264
        L2b8:
            r8 = r3
            goto L2c0
        L2ba:
            r8 = r43
            goto L2d5
        L2bd:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)     // Catch: java.lang.Throwable -> L2f0
        L2c0:
            java.lang.Class<com.vega.audio.tone.tts.engine.server.RemoteSAMIResponse> r5 = com.vega.audio.tone.tts.engine.server.RemoteSAMIResponse.class
            java.lang.Object r8 = r9.fromJson(r8, r5)     // Catch: java.lang.Throwable -> L2f0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L2f0
            r5.<init>(r14)     // Catch: java.lang.Throwable -> L2f0
            r5.append(r8)     // Catch: java.lang.Throwable -> L2f0
            java.lang.String r5 = r5.toString()     // Catch: java.lang.Throwable -> L2f0
            com.vega.log.BLog.i(r6, r5)     // Catch: java.lang.Throwable -> L2f0
        L2d5:
            com.vega.audio.tone.tts.engine.server.RemoteSAMIResponse r8 = (com.vega.audio.tone.tts.engine.server.RemoteSAMIResponse) r8     // Catch: java.lang.Throwable -> L2f0
            r43 = r8
            goto L313
        L2da:
            r5 = move-exception
            goto L2f1
        L2dc:
            r5 = move-exception
            goto L2f1
        L2de:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r5 = "process -> cache hit for cacheKey="
            r8.<init>(r5)
            r8.append(r1)
            java.lang.String r5 = r8.toString()
            com.vega.log.BLog.w(r6, r5)
            goto L324
        L2f0:
            r5 = move-exception
        L2f1:
            java.lang.Object r5 = kotlin.ResultKt.createFailure(r5)
            kotlin.Result.m17090constructorimpl(r5)
            java.lang.Throwable r9 = kotlin.Result.m17093exceptionOrNullimpl(r5)
            if (r9 == 0) goto L313
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r5 = "requestSync -> failed, "
            r8.<init>(r5)
            java.lang.String r5 = X.C93862yt.b(r9)
            r8.append(r5)
            java.lang.String r5 = r8.toString()
            com.vega.log.BLog.i(r6, r5)
        L313:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r5 = "process -> cache miss for cacheKey="
            r8.<init>(r5)
            r8.append(r1)
            java.lang.String r5 = r8.toString()
            com.vega.log.BLog.w(r6, r5)
        L324:
            com.vega.audio.tone.util.TextToSpeechReporter r11 = com.vega.audio.tone.util.TextToSpeechReporter.f74499a
            java.lang.String r9 = r7.getInfo()
            r8 = r16 ^ 1
            java.lang.String r5 = r0.b
            int r6 = r5.length()
            r11.getClass()
            java.lang.String r5 = "local_res"
            com.vega.audio.tone.util.TextToSpeechReporter.f(r6, r9, r5, r8)
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager r11 = r4.b
            java.lang.String r9 = r0.b
            boolean r8 = r0.i
            long r5 = r4.f74434d
            java.lang.String r46 = r7.getInfo()
            r2.q = r1
            r0 = 1
            r2.t = r0
            r39 = r11
            r40 = r1
            r41 = r8
            r42 = r9
            r44 = r5
            r47 = r2
            java.lang.Object r5 = r39.a(r40, r41, r42, r43, r44, r46, r47)
            if (r5 != r10) goto L2c
            return r10
        L35e:
            com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager$process$1 r2 = new com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager$process$1
            r2.<init>(r4, r5)
            goto L16
        L365:
            com.vega.audio.tone.tts.engine.server.RemoteSAMIResponse r0 = r7.b(r1)     // Catch: java.lang.Throwable -> L3d5
            if (r0 == 0) goto L3b0
            com.vega.audio.tone.tts.engine.server.RemoteSAMIResponseInfo r0 = r0.getData()     // Catch: java.lang.Throwable -> L3d5
            if (r0 == 0) goto L3b0
            java.util.List r0 = r0.getSamiInfo()     // Catch: java.lang.Throwable -> L3d5
            if (r0 == 0) goto L3b0
            java.util.Iterator r8 = r0.iterator()     // Catch: java.lang.Throwable -> L3d5
            r2 = 0
        L37c:
            boolean r0 = r8.hasNext()     // Catch: java.lang.Throwable -> L3d5
            if (r0 == 0) goto L3b0
            java.lang.Object r0 = r8.next()     // Catch: java.lang.Throwable -> L3d5
            int r6 = r2 + 1
            if (r2 >= 0) goto L38d
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()     // Catch: java.lang.Throwable -> L3d5
        L38d:
            com.vega.audio.tone.tts.engine.server.RemoteSAMIResponseSAMIResult r0 = (com.vega.audio.tone.tts.engine.server.RemoteSAMIResponseSAMIResult) r0     // Catch: java.lang.Throwable -> L3d5
            java.lang.String r4 = com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager.d(r2, r1)     // Catch: java.lang.Throwable -> L3d5
            com.vega.diskcache.DiskLruCacheWrapper r2 = r7.b     // Catch: java.lang.Throwable -> L3d5
            com.vega.diskcache.StringKey r0 = new com.vega.diskcache.StringKey     // Catch: java.lang.Throwable -> L3d5
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L3d5
            r2.a(r0)     // Catch: java.lang.Throwable -> L3d5
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheKeyHelper r0 = r7.f74428a     // Catch: java.lang.Throwable -> L3d5
            android.content.SharedPreferences r0 = r0.f74427a     // Catch: java.lang.Throwable -> L3d5
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)     // Catch: java.lang.Throwable -> L3d5
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch: java.lang.Throwable -> L3d5
            r0.remove(r4)     // Catch: java.lang.Throwable -> L3d5
            r0.apply()     // Catch: java.lang.Throwable -> L3d5
            r2 = r6
            goto L37c
        L3b0:
            java.lang.String r4 = com.vega.audio.tone.tts.engine.server.RemoteSAMICacheManager.e(r1)     // Catch: java.lang.Throwable -> L3d5
            com.vega.diskcache.DiskLruCacheWrapper r2 = r7.b     // Catch: java.lang.Throwable -> L3d5
            com.vega.diskcache.StringKey r0 = new com.vega.diskcache.StringKey     // Catch: java.lang.Throwable -> L3d5
            r0.<init>(r4)     // Catch: java.lang.Throwable -> L3d5
            r2.a(r0)     // Catch: java.lang.Throwable -> L3d5
            com.vega.audio.tone.tts.engine.server.RemoteSAMICacheKeyHelper r0 = r7.f74428a     // Catch: java.lang.Throwable -> L3d5
            android.content.SharedPreferences r0 = r0.f74427a     // Catch: java.lang.Throwable -> L3d5
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)     // Catch: java.lang.Throwable -> L3d5
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch: java.lang.Throwable -> L3d5
            r0.remove(r4)     // Catch: java.lang.Throwable -> L3d5
            r0.apply()     // Catch: java.lang.Throwable -> L3d5
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L3d5
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> L3d5
            goto L3dd
        L3d5:
            r0 = move-exception
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r0)
        L3dd:
            java.lang.Throwable r3 = kotlin.Result.m17093exceptionOrNullimpl(r0)
            if (r3 == 0) goto L3fe
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r0 = "invalidateAllCache -> "
            r2.<init>(r0)
            r2.append(r1)
            java.lang.String r0 = " failed, "
            r2.append(r0)
            r2.append(r3)
            java.lang.String r1 = r2.toString()
            java.lang.String r0 = "RemoteSAMICacheManager"
            com.vega.log.BLog.w(r0, r1)
        L3fe:
            return r5
        L3ff:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.server.RemoteSAMIToneManager.a(com.vega.audio.tone.tts.core.TextToSpeechTask, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
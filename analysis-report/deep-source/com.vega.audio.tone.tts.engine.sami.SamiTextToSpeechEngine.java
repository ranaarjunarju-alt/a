package com.vega.audio.tone.tts.engine.sami;

import com.mammon.audiosdk.enums.SAMICoreContextType;
import com.vega.core.utils.DirectoryUtil;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.kv.KvStorage;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes4.dex */
public final class SamiTextToSpeechEngine {

    /* renamed from: a, reason: collision with root package name */
    public static final SamiTextToSpeechEngine f74423a = new SamiTextToSpeechEngine();
    public static final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$samiUrl$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            SamiTextToSpeechEngine.f74423a.getClass();
            return "wss://sami-sg1.byteintlapi.com/internal/api/v1/ws";
        }
    });

    /* renamed from: c, reason: collision with root package name */
    public static volatile String f74424c = "<REDACTED_LONG_LITERAL>";

    /* renamed from: d, reason: collision with root package name */
    public static volatile long f74425d;
    public static final String e;
    public static final KvStorage f;

    /* renamed from: g, reason: collision with root package name */
    public static final AtomicBoolean f74426g;
    public static final SAMICoreContextType h;

    static {
        StringBuilder sb = new StringBuilder();
        DirectoryUtil.f79563a.getClass();
        sb.append(DirectoryUtil.D("downloadAudio"));
        sb.append("speechAudio");
        e = sb.toString();
        f = new KvStorage(ModuleCommon.INSTANCE.getApplication(), "sami_audio");
        f74426g = new AtomicBoolean(false);
        h = SAMICoreContextType.TokenVerifyMixedContext;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00eb  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(com.mammon.audiosdk.SAMICoreCallBackListener r7, java.lang.String r8, kotlin.coroutines.Continuation<? super com.mammon.audiosdk.SAMICore> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$createSamiCore$1
            if (r0 == 0) goto Leb
            r4 = r9
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$createSamiCore$1 r4 = (com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$createSamiCore$1) r4
            int r2 = r4.u
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto Leb
            int r2 = r2 - r1
            r4.u = r2
        L12:
            java.lang.Object r3 = r4.s
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.u
            r2 = 1
            if (r0 == 0) goto Ld3
            if (r0 != r2) goto Lf2
            java.lang.Object r8 = r4.r
            java.lang.String r8 = (java.lang.String) r8
            com.mammon.audiosdk.SAMICoreCallBackListener r7 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L28:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "createSamiCore -> info="
            r4.<init>(r0)
            com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
            com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
            java.util.Map r1 = r0.userHeaders()
            boolean r0 = r1.isEmpty()
            java.lang.String r3 = ""
            if (r0 == 0) goto L44
            r1 = r3
        L44:
            java.lang.String r0 = com.vega.core.ext.ExtentionKt.toJsonOrEmpty(r1)
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            java.lang.String r4 = "TextToSpeech_SamiTextToSpeechEngine"
            com.vega.log.BLog.i(r4, r0)
            com.mammon.audiosdk.structures.SAMICoreTtsContextCreateParameter r1 = new com.mammon.audiosdk.structures.SAMICoreTtsContextCreateParameter
            r1.<init>()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74423a
            r0.getClass()
            kotlin.Lazy r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.b
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r5.append(r0)
            com.vega.audio.tone.util.TextToSpeechReporter r0 = com.vega.audio.tone.util.TextToSpeechReporter.f74499a
            r0.getClass()
            java.lang.String r0 = com.vega.audio.tone.util.TextToSpeechReporter.b()
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r1.url = r0
            java.lang.String r0 = "ddjeqjLGMn"
            r1.appKey = r0
            com.mammon.audiosdk.enums.SAMICoreTokenType r0 = com.mammon.audiosdk.enums.SAMICoreTokenType.TOKEN_TO_B_MIXED
            r1.tokenType = r0
            r0 = 24000(0x5dc0, float:3.3631E-41)
            r1.sampleRate = r0
            java.lang.String r0 = "pcm"
            r1.format = r0
            r1.enableNetTransportCompress = r2
            r0 = 64000(0xfa00, float:8.9683E-41)
            r1.bitRate = r0
            java.lang.String r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.e
            r1.saveFileDir = r0
            r1.enableTimestamp = r2
            r0 = 10000(0x2710, float:1.4013E-41)
            r1.connectTimeout = r0
            java.lang.String r0 = com.vega.audio.tone.util.TextToSpeechReporter.a()
            r1.header = r0
            r1.emotion = r3
            r0 = 1065353216(0x3f800000, float:1.0)
            r1.emotionScale = r0
            if (r8 == 0) goto Lb1
            r1.extra = r8
        Lb1:
            com.mammon.audiosdk.SAMICore r3 = new com.mammon.audiosdk.SAMICore
            r3.<init>()
            r3.setListener(r7)
            com.mammon.audiosdk.enums.SAMICoreIdentify r0 = com.mammon.audiosdk.enums.SAMICoreIdentify.SAMICoreIdentify_Streaming_Playing_TTS_Online
            int r2 = r3.SAMICoreCreateHandleByIdentify(r0, r1)
            if (r2 == 0) goto Ld2
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "createSamiHandler err ret = "
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r4, r0)
        Ld2:
            return r3
        Ld3:
            kotlin.ResultKt.throwOnFailure(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74426g
            boolean r0 = r0.get()
            if (r0 != 0) goto L28
            r4.q = r7
            r4.r = r8
            r4.u = r2
            java.lang.Object r0 = r6.c(r4)
            if (r0 != r1) goto L28
            return r1
        Leb:
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$createSamiCore$1 r4 = new com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$createSamiCore$1
            r4.<init>(r6, r9)
            goto L12
        Lf2:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.a(com.mammon.audiosdk.SAMICoreCallBackListener, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0085 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0088  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0083 -> B:11:0x0029). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(kotlin.coroutines.Continuation<? super java.lang.String> r11) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$getTokenFromServer$1
            if (r0 == 0) goto L88
            r9 = r11
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$getTokenFromServer$1 r9 = (com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$getTokenFromServer$1) r9
            int r2 = r9.v
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L88
            int r2 = r2 - r1
            r9.v = r2
        L12:
            java.lang.Object r8 = r9.t
            java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r9.v
            r7 = 0
            r5 = 1
            if (r0 == 0) goto L63
            if (r0 != r5) goto L8e
            int r4 = r9.r
            long r2 = r9.s
            int r1 = r9.q
            kotlin.ResultKt.throwOnFailure(r8)
        L29:
            kotlin.Pair r8 = (kotlin.Pair) r8
            if (r8 == 0) goto L6d
            java.lang.Object r6 = r8.getFirst()
            com.vega.audio.tone.manager.SamiTokenBean r6 = (com.vega.audio.tone.manager.SamiTokenBean) r6
            java.lang.Object r0 = r8.getSecond()
            java.lang.Number r0 = (java.lang.Number) r0
            long r4 = r0.longValue()
            long r2 = java.lang.System.currentTimeMillis()
            r0 = 1000(0x3e8, double:4.94E-321)
            long r2 = r2 / r0
            long r4 = r4 - r2
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74425d = r4
        L47:
            android.os.SystemClock.elapsedRealtime()
            java.lang.String r2 = ""
            if (r6 != 0) goto L5a
            com.vega.materialgenerate.TtsSinkWrapper r0 = com.vega.materialgenerate.TtsSinkWrapper.f115002a
            r0.getClass()
            java.lang.String r1 = "get_token_fail"
            r0 = -6
            com.vega.materialgenerate.TtsSinkWrapper.a(r1, r0, r2, r2, r7)
        L59:
            return r2
        L5a:
            java.lang.String r0 = r6.getToken()
            if (r0 != 0) goto L61
            goto L59
        L61:
            r2 = r0
            goto L59
        L63:
            kotlin.ResultKt.throwOnFailure(r8)
            long r2 = android.os.SystemClock.elapsedRealtime()
            r4 = 0
            r1 = 0
            goto L72
        L6d:
            int r4 = r4 + 1
            r0 = 3
            if (r4 >= r0) goto L86
        L72:
            com.vega.audio.tone.manager.TextToAudioRequest r0 = com.vega.audio.tone.manager.TextToAudioRequest.f74161a
            r9.q = r1
            r9.s = r2
            r9.r = r4
            r9.v = r5
            r0.getClass()
            java.lang.Object r8 = com.vega.audio.tone.manager.TextToAudioRequest.b(r9)
            if (r8 != r6) goto L29
            return r6
        L86:
            r6 = 0
            goto L47
        L88:
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$getTokenFromServer$1 r9 = new com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$getTokenFromServer$1
            r9.<init>(r10, r11)
            goto L12
        L8e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x006b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$init$1
            if (r0 == 0) goto L6b
            r3 = r6
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$init$1 r3 = (com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$init$1) r3
            int r2 = r3.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L6b
            int r2 = r2 - r1
            r3.s = r2
        L12:
            java.lang.Object r1 = r3.q
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r3.s
            r4 = 1
            if (r0 == 0) goto L3d
            if (r0 != r4) goto L71
            kotlin.ResultKt.throwOnFailure(r1)
        L22:
            com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtHelper.f79162a
            com.vega.core.app.AppContext r0 = r0.appContext()
            android.content.Context r3 = r0.getContext()
            com.mammon.audiosdk.enums.SAMICoreContextType r2 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.h
            java.lang.String r1 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74424c
            java.lang.String r0 = "ddjeqjLGMn"
            com.mammon.audiosdk.SAMICore.InitContext(r3, r0, r2, r1)
            java.util.concurrent.atomic.AtomicBoolean r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74426g
            r0.set(r4)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L3d:
            kotlin.ResultKt.throwOnFailure(r1)
            java.util.concurrent.atomic.AtomicBoolean r1 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74426g
            r0 = 0
            boolean r0 = r1.compareAndSet(r0, r4)
            if (r0 != 0) goto L4c
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L4c:
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r1 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            java.lang.String r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.e
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 == 0) goto L5f
            boolean r0 = r1.isDirectory()
            if (r0 != 0) goto L62
        L5f:
            r1.mkdirs()
        L62:
            r3.s = r4
            java.lang.Object r0 = r5.d(r3)
            if (r0 != r2) goto L22
            return r2
        L6b:
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$init$1 r3 = new com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$init$1
            r3.<init>(r5, r6)
            goto L12
        L71:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.c(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$initToken$1
            if (r0 == 0) goto L4e
            r4 = r6
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$initToken$1 r4 = (com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$initToken$1) r4
            int r2 = r4.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L4e
            int r2 = r2 - r1
            r4.s = r2
        L12:
            java.lang.Object r3 = r4.q
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.s
            r0 = 1
            if (r1 == 0) goto L42
            if (r1 != r0) goto L54
            kotlin.ResultKt.throwOnFailure(r3)
        L22:
            java.lang.String r3 = (java.lang.String) r3
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            java.lang.String r2 = "token"
            if (r0 != 0) goto L37
            com.vega.kv.KvStorage r1 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f
            r0 = 0
            com.vega.kv.KvStorageKt.g(r1, r2, r3, r0)
        L32:
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74424c = r3
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L37:
            com.vega.kv.KvStorage r1 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f
            java.lang.String r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74424c
            java.lang.Object r3 = com.vega.kv.KvStorageKt.c(r1, r2, r0)
            java.lang.String r3 = (java.lang.String) r3
            goto L32
        L42:
            kotlin.ResultKt.throwOnFailure(r3)
            r4.s = r0
            java.lang.Object r3 = r5.b(r4)
            if (r3 != r2) goto L22
            return r2
        L4e:
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$initToken$1 r4 = new com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$initToken$1
            r4.<init>(r5, r6)
            goto L12
        L54:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.d(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$updateToken$1
            if (r0 == 0) goto L60
            r3 = r7
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$updateToken$1 r3 = (com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$updateToken$1) r3
            int r2 = r3.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L60
            int r2 = r2 - r1
            r3.s = r2
        L12:
            java.lang.Object r2 = r3.q
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r3.s
            r5 = 0
            r4 = 1
            if (r0 == 0) goto L49
            if (r0 != r4) goto L66
            kotlin.ResultKt.throwOnFailure(r2)
        L23:
            com.vega.core.context.IHostEnv r1 = com.vega.core.context.ContextExtHelper.f79162a
            com.vega.core.app.AppContext r0 = r1.appContext()
            android.content.Context r0 = r0.getContext()
            com.mammon.audiosdk.enums.SAMICoreContextType r3 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.h
            com.mammon.audiosdk.SAMICore.ReleaseContext(r0, r3)
            com.vega.core.app.AppContext r0 = r1.appContext()
            android.content.Context r2 = r0.getContext()
            java.lang.String r1 = "ddjeqjLGMn"
            java.lang.String r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74424c
            com.mammon.audiosdk.SAMICore.InitContext(r2, r1, r3, r0)
            java.util.concurrent.atomic.AtomicBoolean r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74426g
            r0.compareAndSet(r5, r4)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L49:
            kotlin.ResultKt.throwOnFailure(r2)
            java.util.concurrent.atomic.AtomicBoolean r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74426g
            boolean r0 = r0.compareAndSet(r4, r5)
            if (r0 != 0) goto L57
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L57:
            r3.s = r4
            java.lang.Object r0 = r6.d(r3)
            if (r0 != r1) goto L23
            return r1
        L60:
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$updateToken$1 r3 = new com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine$updateToken$1
            r3.<init>(r6, r7)
            goto L12
        L66:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.e(kotlin.coroutines.Continuation):java.lang.Object");
    }
}
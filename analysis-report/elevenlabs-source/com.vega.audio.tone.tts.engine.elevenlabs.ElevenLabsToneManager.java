package com.vega.audio.tone.tts.engine.elevenlabs;

import com.bytedance.frameworks.baselib.network.http.retrofit.converter.gson.GsonConverterFactory;
import com.bytedance.retrofit2.CallAdapter;
import com.bytedance.retrofit2.client.Client;
import com.bytedance.ttnet.utils.RetrofitUtils;
import com.vega.audio.tone.tts.engine.ThirdpartyApiServiceFactory;
import com.vega.core.context.ContextExtKt;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.net.Response;
import com.vega.core.net.TimeoutInterceptor;
import com.vega.core.net.TypedJson;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes29.dex */
public final class ElevenLabsToneManager {

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f74378a = new AtomicBoolean(false);
    public final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<ElevenLabsApiService>() { // from class: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$apiService$2
        /* JADX WARN: Type inference failed for: r1v3, types: [com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsApiService, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final ElevenLabsApiService invoke() {
            ThirdpartyApiServiceFactory.f74370a.getClass();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new TimeoutInterceptor());
            ?? CreateService = RetrofitUtils.createService(RetrofitUtils.createRetrofit("https://" + ContextExtKt.hostEnv().developSettings().host().f79178a, arrayList, GsonConverterFactory.create(), (CallAdapter.Factory) null, (Client.Provider) null), ElevenLabsApiService.class);
            Intrinsics.checkNotNullExpressionValue(CreateService, "");
            return CreateService;
        }
    });

    /* renamed from: c, reason: collision with root package name */
    public final Lazy f74379c = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$scope$2
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            return CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        }
    });

    /* loaded from: classes9.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public final Response a(String str) {
        ElevenLabsApiService elevenLabsApiService = (ElevenLabsApiService) this.b.getValue();
        TypedJson.Companion companion = TypedJson.b;
        Map mapMapOf = MapsKt__MapsKt.mapOf(TuplesKt.to("platform", "11labs"), TuplesKt.to("task_id", str));
        companion.getClass();
        Response<ElevenLabsAudioTaskResultData> responseBody = elevenLabsApiService.audioTaskResult(TypedJson.Companion.b(mapMapOf)).execute().body();
        responseBody.getLogId();
        Intrinsics.checkNotNull(responseBody);
        ExtentionKt.toJson(responseBody);
        return responseBody;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00b1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(java.lang.String r20, java.util.List r21, kotlin.coroutines.Continuation r22) {
        /*
            r19 = this;
            r3 = r22
            boolean r0 = r3 instanceof com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$1
            r15 = r19
            if (r0 == 0) goto Lb1
            r5 = r3
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$1 r5 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$1) r5
            int r2 = r5.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto Lb1
            int r2 = r2 - r1
            r5.t = r2
        L16:
            java.lang.Object r1 = r5.r
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r5.t
            r6 = 41
            java.lang.String r3 = "ElevenLabsToneManager"
            r2 = 1
            if (r0 == 0) goto L55
            if (r0 != r2) goto Lb8
            com.lemon.lv.data.TextToAudioInfo r0 = r5.q
            kotlin.ResultKt.throwOnFailure(r1)
        L2c:
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r4 = r1.booleanValue()
            boolean r1 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r1 == 0) goto L4c
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "downloadAudioResources end(result: "
            r2.<init>(r1)
            r2.append(r4)
            r1 = 41
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.vega.log.BLog.i(r3, r1)
        L4c:
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r4)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)
            return r0
        L55:
            kotlin.ResultKt.throwOnFailure(r1)
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            r13 = r21
            if (r0 == 0) goto L76
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "downloadAudioResources start(size: "
            r1.<init>(r0)
            int r0 = r13.size()
            r1.append(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r3, r0)
        L76:
            com.lemon.lv.data.TextToAudioInfo r6 = new com.lemon.lv.data.TextToAudioInfo
            r7 = 0
            r18 = 0
            r12 = 127(0x7f, float:1.78E-43)
            r8 = r7
            r9 = r7
            r10 = r7
            r11 = r7
            r6.<init>(r7, r8, r9, r10, r11, r12)
            kotlinx.coroutines.CompletableDeferred r14 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r7, r2, r7)
            kotlin.Lazy r0 = r15.f74379c
            java.lang.Object r1 = r0.getValue()
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2 r12 = new com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2
            r0 = r6
            r17 = r20
            r16 = r6
            r12.<init>(r13, r14, r15, r16, r17, r18)
            r10 = 3
            r6 = r1
            r7 = r18
            r8 = r18
            r9 = r12
            r11 = r18
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r6, r7, r8, r9, r10, r11)
            r5.q = r0
            r5.t = r2
            java.lang.Object r1 = r14.await(r5)
            if (r1 != r4) goto L2c
            return r4
        Lb1:
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$1 r5 = new com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$1
            r5.<init>(r15, r3)
            goto L16
        Lb8:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.b(java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:14:0x004f  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x024b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x024d  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x0293  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x033d  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0381  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x038e  */
    /* JADX WARN: Type inference failed for: r0v204, types: [int] */
    /* JADX WARN: Type inference failed for: r0v208, types: [int] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.lang.StringBuilder] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:72:0x0337 -> B:12:0x004b). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(java.lang.String r27, java.lang.String r28, java.util.List r29, java.lang.String r30, int r31, com.vega.aigcapi.materialgenerate.TtsResult r32, java.lang.String r33, kotlin.coroutines.Continuation r34) {
        /*
            Method dump skipped, instructions count: 1378
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.c(java.lang.String, java.lang.String, java.util.List, java.lang.String, int, com.vega.aigcapi.materialgenerate.TtsResult, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0047  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(java.util.List<com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource> r6, com.vega.aigcapi.materialgenerate.TtsResult r7, kotlin.coroutines.Continuation<? super com.lemon.lv.data.TextToAudioInfo> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getTextToAudioInfo$1
            if (r0 == 0) goto L47
            r4 = r8
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getTextToAudioInfo$1 r4 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getTextToAudioInfo$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L47
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L37
            if (r0 != r1) goto L79
            com.vega.aigcapi.materialgenerate.TtsResult r7 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            kotlin.Pair r3 = (kotlin.Pair) r3
            java.lang.Object r0 = r3.getFirst()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L4d
            java.lang.Object r0 = r3.getSecond()
            return r0
        L37:
            kotlin.ResultKt.throwOnFailure(r3)
            java.lang.String r0 = r7.m
            r4.q = r7
            r4.t = r1
            java.lang.Object r3 = r5.b(r0, r6, r4)
            if (r3 != r2) goto L24
            return r2
        L47:
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getTextToAudioInfo$1 r4 = new com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getTextToAudioInfo$1
            r4.<init>(r5, r8)
            goto L12
        L4d:
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.f74378a
            boolean r0 = r0.get()
            java.lang.String r1 = ""
            if (r0 == 0) goto L63
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.e
            r7.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r7.f69163a = r0
        L61:
            r0 = 0
            return r0
        L63:
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r7.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r7.f69163a = r0
            r0 = 3201(0xc81, float:4.486E-42)
            r7.b = r0
            java.lang.String r1 = "ElevenLabsToneManager"
            java.lang.String r0 = "download audios failed"
            com.vega.log.BLog.e(r1, r0)
            goto L61
        L79:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.d(java.util.List, com.vega.aigcapi.materialgenerate.TtsResult, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0090 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0101 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0116  */
    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r0v11, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r27v0, types: [com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(com.vega.audio.tone.tts.core.TextToSpeechTask r28, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo>> r29) {
        /*
            Method dump skipped, instructions count: 293
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.e(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
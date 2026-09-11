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
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
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
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
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

    /* JADX DEBUG: Class process forced to load method for inline: kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(kotlinx.coroutines.Job, int, java.lang.Object):kotlinx.coroutines.CompletableDeferred */
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

    /* JADX DEBUG: Type inference failed for r7v4. Raw type applied. Possible types: java.util.Iterator<T>, java.util.Iterator */
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
            r26 = this;
            r3 = r34
            r4 = r33
            r13 = r32
            boolean r0 = r3 instanceof com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getAudioResourceInfos$1
            r25 = r26
            if (r0 == 0) goto L33d
            r12 = r3
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getAudioResourceInfos$1 r12 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getAudioResourceInfos$1) r12
            int r2 = r12.z
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L33d
            int r2 = r2 - r1
            r12.z = r2
        L1a:
            java.lang.Object r2 = r12.x
            java.lang.Object r22 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r12.z
            java.lang.String r16 = ""
            r5 = 2
            r3 = 1
            java.lang.String r21 = ", message:"
            java.lang.String r20 = "code:"
            java.lang.String r15 = "message_id"
            java.lang.String r14 = "task_id"
            java.lang.String r19 = "log_id"
            if (r0 == 0) goto L6d
            if (r0 == r3) goto L54
            if (r0 != r5) goto L348
            long r8 = r12.u
            int r0 = r12.w
            r18 = r0
            double r4 = r12.v
            long r10 = r12.t
            long r6 = r12.s
            com.vega.core.net.Response r0 = r12.r
            r17 = r0
            com.vega.aigcapi.materialgenerate.TtsResult r13 = r12.q
            kotlin.ResultKt.throwOnFailure(r2)
        L4b:
            int r0 = (r8 > r6 ? 1 : (r8 == r6 ? 0 : -1))
            if (r0 == 0) goto L346
            r0 = 1
            long r8 = r8 + r0
            goto L222
        L54:
            long r8 = r12.u
            int r0 = r12.w
            r18 = r0
            double r4 = r12.v
            long r0 = r12.t
            r23 = r0
            long r6 = r12.s
            com.vega.core.net.Response r0 = r12.r
            r17 = r0
            com.vega.aigcapi.materialgenerate.TtsResult r13 = r12.q
            kotlin.ResultKt.throwOnFailure(r2)     // Catch: java.lang.Exception -> L4fa
            goto L24f
        L6d:
            kotlin.ResultKt.throwOnFailure(r2)
            com.vega.core.net.TypedJson$Companion r6 = com.vega.core.net.TypedJson.b
            r0 = 8
            kotlin.Pair[] r2 = new kotlin.Pair[r0]
            java.lang.String r1 = "platform"
            java.lang.String r0 = "11labs"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)
            r1 = 0
            r2[r1] = r0
            java.lang.String r0 = "voice"
            r7 = r27
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r7)
            r2[r3] = r0
            java.lang.String r0 = "resource_id"
            r7 = r28
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r7)
            r2[r5] = r0
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
            java.lang.String r0 = "word_boundary_enabled"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 3
            r2[r0] = r1
            java.lang.String r0 = "input"
            r1 = r29
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 4
            r2[r0] = r1
            java.lang.String r1 = "sample_rate"
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r31)
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r0)
            r0 = 5
            r2[r0] = r1
            r5 = r30
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r5)
            if (r0 == 0) goto L10c
            org.json.JSONObject r1 = new org.json.JSONObject
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r1.<init>(r5)
            java.lang.String r0 = "from"
            java.lang.String r1 = r1.optString(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r1 = r1.toLowerCase(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
        Le0:
            java.lang.String r0 = "scene"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 6
            r2[r0] = r1
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r4)
            if (r0 == 0) goto L109
        Lef:
            java.lang.String r0 = "mock_tone_info"
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r4)
            r0 = 7
            r2[r0] = r1
            java.util.Map r0 = kotlin.collections.MapsKt__MapsKt.mapOf(r2)
            r6.getClass()
            com.vega.core.net.TypedJson r1 = com.vega.core.net.TypedJson.Companion.b(r0)
            java.util.Objects.toString(r1)
            r4 = 3101(0xc1d, float:4.345E-42)
            goto L10f
        L109:
            r4 = r16
            goto Lef
        L10c:
            r1 = r16
            goto Le0
        L10f:
            r0 = r25
            kotlin.Lazy r0 = r0.b     // Catch: java.lang.Exception -> L252
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Exception -> L252
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsApiService r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsApiService) r0     // Catch: java.lang.Exception -> L252
            com.bytedance.retrofit2.Call r0 = r0.audioTaskRequest(r1)     // Catch: java.lang.Exception -> L252
            com.bytedance.retrofit2.SsResponse r0 = r0.execute()     // Catch: java.lang.Exception -> L252
            java.lang.Object r17 = r0.body()     // Catch: java.lang.Exception -> L252
            r0 = r17
            com.vega.core.net.Response r0 = (com.vega.core.net.Response) r0     // Catch: java.lang.Exception -> L252
            r17 = r0
            r1 = r17
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)     // Catch: java.lang.Exception -> L252
            r17.getLogId()
            com.vega.core.ext.ExtentionKt.toJson(r17)
            java.lang.Object r0 = r17.getData()
            r0.getClass()
            java.lang.Object r0 = r17.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData) r0
            java.lang.String r1 = r0.b()
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.l = r1
            r0 = r25
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.f74378a
            boolean r0 = r0.get()
            if (r0 == 0) goto L168
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.e
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
        L166:
            r0 = 0
            return r0
        L168:
            boolean r0 = r17.success()
            if (r0 != 0) goto L1e5
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
            r13.b = r4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r0 = r20
            r1.<init>(r0)
            java.lang.String r0 = r17.getRet()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r13.c(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "requestNewFailed - "
            r1.<init>(r0)
            java.lang.String r0 = r17.getErrmsg()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r13.b(r0)
            com.google.gson.Gson r2 = com.vega.core.ext.ExtentionKt.getGson()
            r0 = 3
            kotlin.Pair[] r4 = new kotlin.Pair[r0]
            java.lang.String r1 = r17.getLogId()
            r0 = r19
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 0
            r4[r0] = r1
            java.lang.Object r0 = r17.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData) r0
            java.lang.String r0 = r0.a()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r14, r0)
            r4[r3] = r0
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r15, r0)
            r0 = 2
            r4[r0] = r1
            java.util.HashMap r0 = kotlin.collections.MapsKt__MapsKt.hashMapOf(r4)
            java.lang.String r1 = r2.toJson(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            r13.a(r1)
            goto L166
        L1e5:
            java.lang.Class<com.vega.audio.tone.tts.util.TTSEngineConfigSettings> r0 = com.vega.audio.tone.tts.util.TTSEngineConfigSettings.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            com.vega.config.IConfig r0 = com.vega.config.ConfigSettingsKt.a(r0)
            com.vega.audio.tone.tts.util.TTSEngineConfig r0 = (com.vega.audio.tone.tts.util.TTSEngineConfig) r0
            com.vega.audio.tone.tts.util.TTSEngineItemConfig r0 = r0.getEleventLabs()
            if (r0 == 0) goto L21f
            long r6 = r0.getCount()
        L1fb:
            if (r0 == 0) goto L21c
            long r10 = r0.getInterval()
        L201:
            if (r0 == 0) goto L219
            double r4 = r0.getIncreasingRate()
        L207:
            if (r0 == 0) goto L216
            boolean r18 = r0.getEnableIncreasing()
        L20d:
            r1 = 0
            int r0 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r0 > 0) goto L35d
            r8 = 0
            goto L222
        L216:
            r18 = 0
            goto L20d
        L219:
            r4 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            goto L207
        L21c:
            r10 = 2000(0x7d0, double:9.88E-321)
            goto L201
        L21f:
            r6 = 20
            goto L1fb
        L222:
            java.lang.Object r0 = r17.getData()     // Catch: java.lang.Exception -> L35a
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData) r0     // Catch: java.lang.Exception -> L35a
            java.lang.String r1 = r0.a()     // Catch: java.lang.Exception -> L357
            r12.q = r13     // Catch: java.lang.Exception -> L357
            r0 = r17
            r12.r = r0     // Catch: java.lang.Exception -> L357
            r12.s = r6     // Catch: java.lang.Exception -> L357
            r12.t = r10     // Catch: java.lang.Exception -> L357
            r12.v = r4     // Catch: java.lang.Exception -> L357
            r0 = r18
            r12.w = r0     // Catch: java.lang.Exception -> L357
            r12.u = r8     // Catch: java.lang.Exception -> L357
            r0 = 1
            r12.z = r0     // Catch: java.lang.Exception -> L354
            r0 = r25
            com.vega.core.net.Response r2 = r0.a(r1)     // Catch: java.lang.Exception -> L354
            r0 = r22
            if (r2 != r0) goto L24d
            goto L350
        L24d:
            r23 = r10
        L24f:
            com.vega.core.net.Response r2 = (com.vega.core.net.Response) r2     // Catch: java.lang.Exception -> L351
            goto L27b
        L252:
            r2 = move-exception
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
            r13.b = r4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "requestNewException - "
            r1.<init>(r0)
            java.lang.String r0 = r2.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r13.c(r0)
            java.lang.String r0 = "ttsSSMLTaskFailed"
            r13.f69165d = r0
            goto L291
        L27b:
            r0 = r25
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.f74378a
            boolean r0 = r0.get()
            if (r0 == 0) goto L293
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.e
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
        L291:
            r0 = 0
            return r0
        L293:
            boolean r0 = r2.success()
            java.lang.String r1 = "requestQueryFailed"
            if (r0 != 0) goto L306
            com.vega.aigcapi.materialgenerate.StatusResult r3 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r13.f69163a = r3
            r0 = 3102(0xc1e, float:4.347E-42)
            r13.b = r0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r0 = r20
            r3.<init>(r0)
            java.lang.String r0 = r2.getRet()
            r3.append(r0)
            r0 = r21
            r3.append(r0)
            java.lang.String r0 = r2.getErrmsg()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r13.c(r0)
            r13.f69165d = r1
            com.google.gson.Gson r3 = com.vega.core.ext.ExtentionKt.getGson()
            r0 = 3
            kotlin.Pair[] r4 = new kotlin.Pair[r0]
            java.lang.String r1 = r2.getLogId()
            r0 = r19
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 0
            r4[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r0)
            r0 = 1
            r4[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r15, r0)
            r0 = 2
            r4[r0] = r1
            java.util.HashMap r0 = kotlin.collections.MapsKt__MapsKt.hashMapOf(r4)
            java.lang.String r1 = r3.toJson(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            r13.a(r1)
            goto L560
        L306:
            java.lang.Object r0 = r2.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData) r0
            int r3 = r0.c()
            r0 = 1
            if (r3 != r0) goto L35f
            if (r18 == 0) goto L33a
            r0 = r23
            double r2 = (double) r0
            double r2 = r2 * r4
            long r10 = (long) r2
        L31a:
            r12.q = r13
            r0 = r17
            r12.r = r0
            r12.s = r6
            r12.t = r10
            r12.v = r4
            r0 = r18
            r12.w = r0
            r12.u = r8
            r0 = 2
            r12.z = r0
            r0 = r23
            java.lang.Object r1 = kotlinx.coroutines.DelayKt.delay(r0, r12)
            r0 = r22
            if (r1 != r0) goto L4b
            return r22
        L33a:
            r10 = r23
            goto L31a
        L33d:
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getAudioResourceInfos$1 r12 = new com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getAudioResourceInfos$1
            r0 = r25
            r12.<init>(r0, r3)
            goto L1a
        L346:
            r4 = 0
            goto L377
        L348:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L350:
            return r22
        L351:
            r2 = move-exception
            goto L4fb
        L354:
            r2 = move-exception
            goto L4fb
        L357:
            r2 = move-exception
            goto L4fb
        L35a:
            r2 = move-exception
            goto L4fb
        L35d:
            r4 = 0
            goto L377
        L35f:
            if (r3 != 0) goto L40b
            java.lang.Object r0 = r2.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData) r0
            java.lang.String r0 = r0.b()
            r13.m = r0
            java.lang.Object r0 = r2.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData) r0
            java.util.List r4 = r0.a()
        L377:
            r0 = r25
            java.util.concurrent.atomic.AtomicBoolean r0 = r0.f74378a
            boolean r0 = r0.get()
            if (r0 == 0) goto L38e
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.e
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
            return r4
        L38e:
            if (r4 == 0) goto L396
            boolean r0 = r4.isEmpty()
            if (r0 == 0) goto L405
        L396:
            r0 = 1
        L397:
            if (r0 == 0) goto L407
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
            r0 = 3105(0xc21, float:4.351E-42)
            r13.b = r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r0 = r20
            r1.<init>(r0)
            java.lang.String r0 = r17.getRet()
            r1.append(r0)
            r0 = r21
            r1.append(r0)
            java.lang.String r0 = r17.getErrmsg()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r13.c(r0)
            java.lang.String r0 = "requestTimeOut"
            r13.f69165d = r0
            com.google.gson.Gson r3 = com.vega.core.ext.ExtentionKt.getGson()
            r0 = 3
            kotlin.Pair[] r2 = new kotlin.Pair[r0]
            java.lang.String r1 = r17.getLogId()
            r0 = r19
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 0
            r2[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r0)
            r0 = 1
            r2[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r15, r0)
            r0 = 2
            r2[r0] = r1
            java.util.HashMap r0 = kotlin.collections.MapsKt__MapsKt.hashMapOf(r2)
            java.lang.String r1 = r3.toJson(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            r13.a(r1)
            return r4
        L405:
            r0 = 0
            goto L397
        L407:
            r4.size()
            return r4
        L40b:
            com.vega.aigcapi.materialgenerate.StatusResult r3 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            r13.f69163a = r3
            r0 = 3102(0xc1e, float:4.347E-42)
            r13.b = r0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r0 = r20
            r5.<init>(r0)
            java.lang.String r0 = r2.getRet()
            r5.append(r0)
            r0 = r21
            r5.append(r0)
            java.lang.String r0 = r2.getErrmsg()
            r5.append(r0)
            java.lang.String r0 = ", taskMessage: "
            r5.append(r0)
            java.lang.Object r0 = r2.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData) r0
            java.util.List r0 = r0.a()
            if (r0 == 0) goto L4b6
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r7 = r0.iterator()
        L44f:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L479
            java.lang.Object r6 = r7.next()
            r3 = r6
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource r3 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource) r3
            java.lang.String r0 = r3.a()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 != 0) goto L470
            java.lang.String r0 = r3.b()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L477
        L470:
            r0 = 1
        L471:
            if (r0 == 0) goto L44f
            r4.add(r6)
            goto L44f
        L477:
            r0 = 0
            goto L471
        L479:
            java.util.ArrayList r7 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r4, r0)
            r7.<init>(r0)
            java.util.Iterator r6 = r4.iterator()
        L488:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L4b7
            java.lang.Object r4 = r6.next()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource r4 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource) r4
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "task_code:"
            r3.<init>(r0)
            java.lang.String r0 = r4.a()
            r3.append(r0)
            java.lang.String r0 = ", task_message:"
            r3.append(r0)
            java.lang.String r0 = r4.b()
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r7.add(r0)
            goto L488
        L4b6:
            r7 = 0
        L4b7:
            r5.append(r7)
            java.lang.String r0 = r5.toString()
            r13.c(r0)
            r13.f69165d = r1
            com.google.gson.Gson r3 = com.vega.core.ext.ExtentionKt.getGson()
            r0 = 3
            kotlin.Pair[] r4 = new kotlin.Pair[r0]
            java.lang.String r1 = r2.getLogId()
            r0 = r19
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 0
            r4[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r0)
            r0 = 1
            r4[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r15, r0)
            r0 = 2
            r4[r0] = r1
            java.util.HashMap r0 = kotlin.collections.MapsKt__MapsKt.hashMapOf(r4)
            java.lang.String r1 = r3.toJson(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            r13.a(r1)
            goto L560
        L4fa:
            r2 = move-exception
        L4fb:
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r13.getClass()
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69163a = r1
            r0 = 3102(0xc1e, float:4.347E-42)
            r13.b = r0
            java.lang.String r0 = r2.getMessage()
            java.lang.String r1 = java.lang.String.valueOf(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r0)
            r13.f69164c = r1
            java.lang.String r0 = "requestQueryException"
            r13.f69165d = r0
            com.google.gson.Gson r2 = com.vega.core.ext.ExtentionKt.getGson()
            r0 = 3
            kotlin.Pair[] r3 = new kotlin.Pair[r0]
            java.lang.String r1 = r17.getLogId()
            r0 = r19
            kotlin.Pair r1 = kotlin.TuplesKt.to(r0, r1)
            r0 = 0
            r3[r0] = r1
            java.lang.Object r0 = r17.getData()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskRequestData) r0
            java.lang.String r0 = r0.a()
            java.lang.String r0 = java.lang.String.valueOf(r0)
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r0)
            r0 = 1
            r3[r0] = r1
            r0 = r16
            kotlin.Pair r1 = kotlin.TuplesKt.to(r15, r0)
            r0 = 2
            r3[r0] = r1
            java.util.HashMap r0 = kotlin.collections.MapsKt__MapsKt.hashMapOf(r3)
            java.lang.String r1 = r2.toJson(r0)
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            r13.a(r1)
        L560:
            r0 = 0
            return r0
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

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:52:0x004e */
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
            r27 = this;
            r3 = r29
            r6 = r28
            boolean r0 = r3 instanceof com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$1
            r5 = r27
            if (r0 == 0) goto L116
            r4 = r3
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$1 r4 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$1) r4
            int r2 = r4.u
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L116
            int r2 = r2 - r1
            r4.u = r2
        L18:
            java.lang.Object r9 = r4.s
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.u
            r13 = 0
            r2 = 3
            r8 = 2
            r14 = 0
            r10 = 1
            if (r0 == 0) goto L38
            if (r0 == r10) goto Lab
            if (r0 == r8) goto Lee
            if (r0 != r2) goto L11d
            java.lang.Object r11 = r4.q
            kotlin.ResultKt.throwOnFailure(r9)
        L32:
            r14 = r9
        L33:
            kotlin.Pair r0 = kotlin.TuplesKt.to(r11, r14)
            return r0
        L38:
            kotlin.ResultKt.throwOnFailure(r9)
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.f74378a
            r0.set(r13)
            java.lang.String r7 = r6.f74298c
            java.lang.String r9 = r6.b
            com.vega.audio.tone.tts.core.TextToSpeechTaskType r1 = r6.f74299d
            com.vega.audio.tone.tts.core.TextToSpeechTaskType r0 = com.vega.audio.tone.tts.core.TextToSpeechTaskType.f74307a
            if (r1 != r0) goto L91
            java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r9)
        L4e:
            java.util.Objects.toString(r0)
            com.vega.aigcapi.materialgenerate.TtsResult r11 = new com.vega.aigcapi.materialgenerate.TtsResult
            com.vega.aigcapi.materialgenerate.StatusResult r12 = com.vega.aigcapi.materialgenerate.StatusResult.b
            r16 = 0
            r26 = 8190(0x1ffe, float:1.1477E-41)
            r15 = r14
            r18 = r13
            r19 = r14
            r20 = r14
            r21 = r14
            r22 = r13
            r23 = r13
            r24 = r14
            r25 = r14
            r11.<init>(r12, r13, r14, r15, r16, r18, r19, r20, r21, r22, r23, r24, r25, r26)
            java.lang.String r10 = r6.n
            java.lang.String r9 = r6.k
            java.lang.String r1 = r6.w
            r4.q = r11
            r4.r = r14
            r4.u = r8
            r20 = 24000(0x5dc0, float:3.3631E-41)
            r18 = r0
            r19 = r9
            r21 = r11
            r22 = r1
            r23 = r4
            r15 = r5
            r16 = r7
            r17 = r10
            java.lang.Object r9 = r15.c(r16, r17, r18, r19, r20, r21, r22, r23)
            if (r9 != r3) goto Lf5
            return r3
        L91:
            boolean r0 = r6.i
            if (r0 == 0) goto Le1
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$texts$segResp$1 r0 = new com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$texts$segResp$1
            r0.<init>(r9, r14)
            r4.q = r6
            r4.r = r7
            r4.u = r10
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt__Builders_commonKt.withContext(r1, r0, r4)
            if (r9 != r3) goto Lb6
            return r3
        Lab:
            java.lang.Object r7 = r4.r
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r6 = r4.q
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = (com.vega.audio.tone.tts.core.TextToSpeechTask) r6
            kotlin.ResultKt.throwOnFailure(r9)
        Lb6:
            com.vega.core.net.Response r9 = (com.vega.core.net.Response) r9
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            if (r9 == 0) goto L4e
            java.lang.Object r1 = r9.getData()
            com.lemon.lv.data.SentenceData r1 = (com.lemon.lv.data.SentenceData) r1
            if (r1 == 0) goto L4e
            java.util.ArrayList r1 = r1.a()
            if (r1 == 0) goto L4e
            java.util.Iterator r9 = r1.iterator()
        Ld1:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L4e
            java.lang.Object r1 = r9.next()
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            r0.addAll(r1)
            goto Ld1
        Le1:
            java.lang.String r0 = "\n"
            java.lang.String[] r1 = new java.lang.String[]{r0}
            r0 = 6
            java.util.List r0 = X.C93472yG.M(r9, r1, r13, r0)
            goto L4e
        Lee:
            java.lang.Object r11 = r4.q
            com.vega.aigcapi.materialgenerate.TtsResult r11 = (com.vega.aigcapi.materialgenerate.TtsResult) r11
            kotlin.ResultKt.throwOnFailure(r9)
        Lf5:
            java.util.List r9 = (java.util.List) r9
            com.vega.aigcapi.materialgenerate.StatusResult r1 = r11.f69163a
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.b
            boolean r0 = r1.equals(r0)
            if (r0 == 0) goto L33
            if (r9 == 0) goto L33
            boolean r0 = r9.isEmpty()
            if (r0 == 0) goto L10b
            goto L33
        L10b:
            r4.q = r11
            r4.u = r2
            java.lang.Object r9 = r5.d(r9, r11, r4)
            if (r9 != r3) goto L32
            return r3
        L116:
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$1 r4 = new com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$1
            r4.<init>(r5, r3)
            goto L18
        L11d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager.e(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
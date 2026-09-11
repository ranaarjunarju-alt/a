package com.vega.audio.tone.tts.engine.streaming;

import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.IStreamingTextToSpeechService;
import com.vega.audio.tone.tts.IStreamingToneManager;
import com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig;
import com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfigSetting;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ContinuationExtKt;
import com.vega.core.ext.ExtentionKt;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* loaded from: classes25.dex */
public final class StreamingReadingToneManager implements IStreamingToneManager {

    /* renamed from: d, reason: collision with root package name */
    public static Job f74467d;

    /* renamed from: a, reason: collision with root package name */
    public static final StreamingReadingToneManager f74465a = new StreamingReadingToneManager();
    public static String b = "";

    /* renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap<String, StreamingStruct> f74466c = new ConcurrentHashMap<>();
    public static final Lazy e = LazyKt__LazyJVMKt.lazy(new Function0<IStreamingTextToSpeechService>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$streamingToneService$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.IStreamingTextToSpeechService, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final IStreamingTextToSpeechService invoke() {
            return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IStreamingTextToSpeechService.class), null);
        }
    });
    public static final Lazy f = LazyKt__LazyJVMKt.lazy(new Function0<TtsStreamingOptimizedConfig>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$ttsStreamingOptimizedConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsStreamingOptimizedConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsStreamingOptimizedConfigSetting.class));
        }
    });

    /* renamed from: g, reason: collision with root package name */
    public static final Map<String, String> f74468g = new LinkedHashMap();

    /* loaded from: classes.dex */
    public static final class StreamingStruct {

        /* renamed from: a, reason: collision with root package name */
        public final CancellableContinuation<Pair<? extends TtsResult, TextToAudioInfo>> f74469a;
        public final Function1<String, Unit> b;

        /* renamed from: c, reason: collision with root package name */
        public final Function3<byte[], Integer, Boolean, Unit> f74470c;

        /* renamed from: d, reason: collision with root package name */
        public final Function2<String, StreamingToneStage, Unit> f74471d;
        public final String e;
        public byte[] f;

        /* renamed from: g, reason: collision with root package name */
        public int f74472g;

        public StreamingStruct(CancellableContinuationImpl cancellableContinuationImpl, Function1 function1, Function3 function3, Function2 function2, String str) {
            Intrinsics.checkNotNullParameter(cancellableContinuationImpl, "");
            Intrinsics.checkNotNullParameter(function1, "");
            Intrinsics.checkNotNullParameter(function3, "");
            Intrinsics.checkNotNullParameter(function2, "");
            Intrinsics.checkNotNullParameter(str, "");
            this.f74469a = cancellableContinuationImpl;
            this.b = function1;
            this.f74470c = function3;
            this.f74471d = function2;
            this.e = str;
            this.f = new byte[0];
        }

        public final void a(String str, StreamingToneStage streamingToneStage) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(streamingToneStage, "");
            this.f74471d.invoke(str, streamingToneStage);
        }

        public final void b(Pair<? extends TtsResult, TextToAudioInfo> pair) {
            Intrinsics.checkNotNullParameter(pair, "");
            ContinuationExtKt.a(this.f74469a, pair);
        }
    }

    public static TtsResult e(StatusResult statusResult, int i, String str, String str2, StreamingResultExtra streamingResultExtra, String str3, String str4) {
        return new TtsResult(statusResult, i, str, str2, 0L, false, TtsResult.RequestScene.f69168d, ExtentionKt.toJson(MapsKt__MapsKt.mapOf(TuplesKt.to("task_id", streamingResultExtra.f74473a), TuplesKt.to("matrix_task_id", streamingResultExtra.b))), null, streamingResultExtra.f74474c, streamingResultExtra.f74475d, str3, str4, 304);
    }

    public static /* synthetic */ TtsResult f(StreamingReadingToneManager streamingReadingToneManager, StatusResult statusResult, int i, String str, String str2, StreamingResultExtra streamingResultExtra, String str3, String str4, int i2) {
        String str5 = str4;
        StreamingResultExtra streamingResultExtra2 = streamingResultExtra;
        String str6 = str2;
        String str7 = str;
        int i3 = i;
        if ((i2 & 2) != 0) {
            i3 = 0;
        }
        if ((i2 & 4) != 0) {
            str7 = "";
        }
        if ((i2 & 8) != 0) {
            str6 = "";
        }
        if ((i2 & 16) != 0) {
            streamingResultExtra2 = new StreamingResultExtra(null, null, false, false, 15);
        }
        String str8 = (i2 & 32) == 0 ? str3 : "";
        if ((i2 & 64) != 0) {
            str5 = null;
        }
        streamingReadingToneManager.getClass();
        return e(statusResult, i3, str7, str6, streamingResultExtra2, str8, str5);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:48:0x018b */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x018b, code lost:
    
        if (r11 != false) goto L49;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r15v0 */
    /* JADX WARN: Type inference failed for: r15v1 */
    /* JADX WARN: Type inference failed for: r15v2, types: [java.lang.Object, java.lang.String, java.util.concurrent.CancellationException, kotlinx.coroutines.Job] */
    /* JADX WARN: Type inference failed for: r15v3 */
    /* JADX WARN: Type inference failed for: r15v4 */
    /* JADX WARN: Type inference failed for: r15v5 */
    @Override // com.vega.audio.tone.tts.IStreamingToneManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.vega.audio.tone.tts.data.StreamingToneResponse r23) {
        /*
            r22 = this;
            java.lang.String r2 = ""
            r4 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)
            java.lang.String r1 = r4.getTaskId()
            if (r1 != 0) goto Le
            r1 = r2
        Le:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74466c
            java.lang.Object r6 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r6 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r6
            if (r6 != 0) goto L3b
            java.util.Map<java.lang.String, java.lang.String> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74468g
            java.lang.Object r5 = r0.remove(r1)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 == 0) goto L38
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74465a
            r0.getClass()
            kotlin.Lazy r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.e
            java.lang.Object r3 = r0.getValue()
            com.vega.audio.tone.tts.IStreamingTextToSpeechService r3 = (com.vega.audio.tone.tts.IStreamingTextToSpeechService) r3
            java.lang.String r0 = r4.getMatrixTaskId()
            if (r0 != 0) goto L39
        L35:
            r3.g(r1, r2, r5)
        L38:
            return
        L39:
            r2 = r0
            goto L35
        L3b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r3 = "dealNextMessage: taskId:"
            r5.<init>(r3)
            java.lang.String r3 = r4.getTaskId()
            r5.append(r3)
            java.lang.String r3 = ", matrixTaskId:"
            r5.append(r3)
            java.lang.String r3 = r4.getMatrixTaskId()
            r5.append(r3)
            java.lang.String r3 = ", code:"
            r5.append(r3)
            java.lang.Integer r3 = r4.getCode()
            r5.append(r3)
            java.lang.String r3 = ", msg:"
            r5.append(r3)
            java.lang.String r3 = r4.getMsg()
            r5.append(r3)
            java.lang.String r3 = ", hitCache:"
            r5.append(r3)
            boolean r3 = r4.getHitCache()
            r5.append(r3)
            java.lang.String r3 = ", hasMore:"
            r5.append(r3)
            boolean r3 = r4.getHasMore()
            r5.append(r3)
            java.lang.String r3 = ", base64 size:"
            r5.append(r3)
            java.lang.String r3 = r4.getAudioBase64()
            r15 = 0
            if (r3 == 0) goto Lb2
            int r3 = r3.length()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
        L99:
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            java.lang.String r7 = "StreamingToneManager"
            com.vega.log.BLog.i(r7, r3)
            java.lang.String r5 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.b
            java.lang.String r3 = r4.getTaskId()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r3)
            if (r3 != 0) goto Lb4
            goto L38
        Lb2:
            r3 = r15
            goto L99
        Lb4:
            java.lang.Integer r3 = r4.getCode()
            if (r3 != 0) goto L146
        Lba:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r3 = "receive error, code: "
            r5.<init>(r3)
            java.lang.Integer r3 = r4.getCode()
            r5.append(r3)
            java.lang.String r3 = ", msg: "
            r5.append(r3)
            java.lang.String r3 = r4.getMsg()
            r5.append(r3)
            java.lang.String r3 = r5.toString()
            com.vega.log.BLog.e(r7, r3)
            java.lang.Object r3 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r3 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r3
            if (r3 == 0) goto L38
            com.vega.aigcapi.materialgenerate.StatusResult r6 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r7 = -402443(0xfffffffffff9dbf5, float:NaN)
            java.lang.Integer r0 = r4.getCode()
            java.lang.String r8 = java.lang.String.valueOf(r0)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "["
            r5.<init>(r0)
            java.lang.String r0 = r4.getMatrixTaskId()
            r5.append(r0)
            java.lang.String r0 = "]: "
            r5.append(r0)
            java.lang.String r0 = r4.getMsg()
            r5.append(r0)
            java.lang.String r9 = r5.toString()
            com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra r10 = new com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra
            java.lang.String r18 = r4.getMatrixTaskId()
            if (r18 != 0) goto L118
            r18 = r2
        L118:
            r19 = 0
            r21 = 12
            r16 = r10
            r17 = r1
            r20 = r19
            r16.<init>(r17, r18, r19, r20, r21)
            java.lang.String r0 = r4.getMsg()
            if (r0 != 0) goto L144
        L12b:
            java.lang.String r11 = com.vega.audio.tone.tts.util.TextToSpeechUtilsKt.b(r2)
            java.lang.String r0 = r4.getMsg()
            java.lang.String r12 = com.vega.audio.tone.tts.util.TextToSpeechUtilsKt.a(r0)
            com.vega.aigcapi.materialgenerate.TtsResult r0 = e(r6, r7, r8, r9, r10, r11, r12)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r15)
            r3.b(r0)
            goto L38
        L144:
            r2 = r0
            goto L12b
        L146:
            int r3 = r3.intValue()
            if (r3 == 0) goto L14e
            goto Lba
        L14e:
            int r7 = r4.getMessageIndex()
            if (r7 != 0) goto L161
            java.lang.Object r5 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r5 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r5
            if (r5 == 0) goto L161
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r3 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.f74490d
            r5.a(r1, r3)
        L161:
            int r5 = r6.f74472g
            r3 = 1
            r8 = 0
            r12 = r22
            if (r7 != r5) goto L2bd
            int r5 = r5 + 1
            r6.f74472g = r5
            boolean r5 = r4.getHasMore()
            r11 = r5 ^ 1
            boolean r5 = r4.getHitCache()
            if (r5 == 0) goto L231
            java.lang.String r5 = r4.getSpeechUrl()
            boolean r5 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r5)
            if (r5 == 0) goto L231
            java.lang.Object r8 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r8 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r8
            if (r8 != 0) goto L1de
        L18b:
            if (r11 == 0) goto L28b
        L18d:
            java.lang.Object r5 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r5 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r5
            if (r5 == 0) goto L1c9
            com.vega.aigcapi.materialgenerate.StatusResult r13 = com.vega.aigcapi.materialgenerate.StatusResult.b
            r14 = 0
            com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra r17 = new com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra
            java.lang.String r8 = r4.getMatrixTaskId()
            if (r8 != 0) goto L1a1
            r8 = r2
        L1a1:
            boolean r9 = r4.getHitCache()
            r11 = 8
            r6 = r17
            r7 = r1
            r10 = r14
            r6.<init>(r7, r8, r9, r10, r11)
            java.lang.String r6 = r4.getTextLan()
            if (r6 != 0) goto L1dc
        L1b4:
            java.lang.String r19 = r4.getSpeakerId()
            r20 = 14
            r16 = r15
            r18 = r2
            com.vega.aigcapi.materialgenerate.TtsResult r2 = f(r12, r13, r14, r15, r16, r17, r18, r19, r20)
            kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r15)
            r5.b(r2)
        L1c9:
            r0.remove(r1)
            kotlinx.coroutines.Job r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74467d
            if (r0 == 0) goto L1d3
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r15, r3, r15)
        L1d3:
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74467d = r15
            java.util.Map<java.lang.String, java.lang.String> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74468g
            r0.remove(r1)
            goto L38
        L1dc:
            r2 = r6
            goto L1b4
        L1de:
            if (r7 != 0) goto L1e5
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r5 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.e
            r8.a(r1, r5)
        L1e5:
            kotlin.Lazy r5 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f
            java.lang.Object r5 = r5.getValue()
            com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig r5 = (com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig) r5
            boolean r5 = r5.a()
            if (r5 != 0) goto L1ff
            java.lang.String r6 = r4.getSpeechUrl()
            if (r6 != 0) goto L1fa
            r6 = r2
        L1fa:
            kotlin.jvm.functions.Function1<java.lang.String, kotlin.Unit> r5 = r8.b
            r5.invoke(r6)
        L1ff:
            java.lang.String r6 = r8.e
            java.lang.String r19 = r4.getSpeechUrl()
            if (r19 != 0) goto L209
            r19 = r2
        L209:
            int r5 = r19.length()
            if (r5 <= 0) goto L18b
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            kotlinx.coroutines.CoroutineScope r13 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r5)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$dealResponseOnHitCache$1 r16 = new com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$dealResponseOnHitCache$1
            r17 = r1
            r18 = r6
            r20 = r8
            r21 = r15
            r16.<init>(r17, r18, r19, r20, r21)
            r17 = 3
            r14 = r15
            r15 = r15
            r16 = r16
            r18 = r15
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r13, r14, r15, r16, r17, r18)
            goto L18b
        L231:
            java.lang.String r5 = r4.getAudioBase64()
            byte[] r10 = android.util.Base64.decode(r5, r8)
            if (r7 != 0) goto L248
            java.lang.Object r8 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r8 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r8
            if (r8 == 0) goto L248
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r5 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.e
            r8.a(r1, r5)
        L248:
            java.lang.Object r8 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r8 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r8
            if (r8 == 0) goto L26b
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r2)
            byte[] r5 = r8.f
            byte[] r5 = kotlin.collections.ArraysKt___ArraysJvmKt.plus(r5, r10)
            r8.f = r5
            kotlin.jvm.functions.Function3<byte[], java.lang.Integer, java.lang.Boolean, kotlin.Unit> r9 = r8.f74470c
            java.lang.Integer r8 = java.lang.Integer.valueOf(r7)
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r11)
            r9.invoke(r10, r8, r5)
        L26b:
            if (r11 == 0) goto L28b
            java.lang.String r7 = r6.e
            byte[] r6 = r6.f
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            kotlinx.coroutines.CoroutineScope r13 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r5)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$dealResponse$3 r5 = new com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$dealResponse$3
            r5.<init>(r1, r7, r6, r15)
            r17 = 3
            r14 = r15
            r15 = r15
            r16 = r5
            r18 = r15
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r13, r14, r15, r16, r17, r18)
            goto L18d
        L28b:
            kotlin.Lazy r0 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f
            java.lang.Object r0 = r0.getValue()
            com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig r0 = (com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig) r0
            boolean r0 = r0.f()
            if (r0 == 0) goto L2b8
            long r5 = r4.getEstimatedWaitingTime()
            double r8 = (double) r5
            r5 = 4608083138725491507(0x3ff3333333333333, double:1.2)
            double r8 = r8 * r5
            long r5 = (long) r8
        L2a5:
            java.lang.String r0 = r4.getMatrixTaskId()
            if (r0 != 0) goto L2b6
        L2ab:
            r14 = 0
            r8 = r12
            r9 = r7
            r10 = r1
            r11 = r2
            r12 = r5
            r8.g(r9, r10, r11, r12, r14)
            goto L38
        L2b6:
            r2 = r0
            goto L2ab
        L2b8:
            long r5 = r4.getEstimatedWaitingTime()
            goto L2a5
        L2bd:
            java.lang.Object r3 = r0.get(r1)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r3 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.StreamingStruct) r3
            if (r3 == 0) goto L38
            com.vega.aigcapi.materialgenerate.StatusResult r7 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r8 = -402444(0xfffffffffff9dbf4, float:NaN)
            java.lang.String r9 = "-6001"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "messageIndex"
            r5.<init>(r0)
            int r0 = r6.f74472g
            r5.append(r0)
            java.lang.String r0 = " loss, matrixTaskId:"
            r5.append(r0)
            java.lang.String r0 = r4.getMatrixTaskId()
            r5.append(r0)
            java.lang.String r10 = r5.toString()
            com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra r11 = new com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra
            java.lang.String r0 = r4.getMatrixTaskId()
            if (r0 != 0) goto L311
        L2f0:
            r19 = 0
            r21 = 12
            r16 = r11
            r17 = r1
            r18 = r2
            r20 = r19
            r16.<init>(r17, r18, r19, r20, r21)
            r14 = 96
            r6 = r12
            r12 = r15
            r13 = r15
            com.vega.aigcapi.materialgenerate.TtsResult r0 = f(r6, r7, r8, r9, r10, r11, r12, r13, r14)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r15)
            r3.b(r0)
            goto L38
        L311:
            r2 = r0
            goto L2f0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.a(com.vega.audio.tone.tts.data.StreamingToneResponse):void");
    }

    @Override // com.vega.audio.tone.tts.IStreamingToneManager
    public final void b(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        if (z) {
            StreamingStruct streamingStruct = f74466c.get(str);
            if (streamingStruct != null) {
                streamingStruct.a(str, StreamingToneStage.f74489c);
                return;
            }
            return;
        }
        StreamingStruct streamingStruct2 = f74466c.get(str);
        if (streamingStruct2 != null) {
            streamingStruct2.b(TuplesKt.to(f(this, StatusResult.f69154c, -402442, null, str2, new StreamingResultExtra(str, null, false, false, 14), null, null, 100), null));
        }
    }

    public final void c(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        ConcurrentHashMap<String, StreamingStruct> concurrentHashMap = f74466c;
        StreamingStruct streamingStruct = concurrentHashMap.get(str);
        if (streamingStruct != null && streamingStruct.f74469a.isActive()) {
            CancellableContinuation.DefaultImpls.cancel$default(streamingStruct.f74469a, null, 1, null);
        }
        concurrentHashMap.remove(str);
        ((IStreamingTextToSpeechService) e.getValue()).c(str);
        Job job = f74467d;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        f74467d = null;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:124:0x0116 */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:25:0x00bf */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:46:0x0132 */
    /* JADX DEBUG: Multi-variable search result rejected for r0v6, resolved type: com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1 */
    /* JADX DEBUG: Multi-variable search result rejected for r4v23, resolved type: T */
    /* JADX DEBUG: Type inference failed for r9v28. Raw type applied. Possible types: ? super byte[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x03bd  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x021a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0238  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0266  */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1, kotlin.coroutines.Continuation] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9, types: [java.lang.Object, kotlin.Pair] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(com.vega.audio.tone.tts.core.TextToSpeechTask r59, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r60, kotlin.jvm.functions.Function3<? super byte[], ? super java.lang.Integer, ? super java.lang.Boolean, kotlin.Unit> r61, kotlin.jvm.functions.Function2<? super java.lang.String, ? super com.vega.audio.tone.tts.engine.streaming.StreamingToneStage, kotlin.Unit> r62, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo>> r63) throws java.lang.Throwable {
        /*
            r58 = this;
            r7 = r63
            r8 = r60
            r5 = r61
            r1 = r59
            r6 = r62
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1
            r25 = r58
            if (r0 == 0) goto L3bd
            r0 = r7
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1 r0 = (com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1) r0
            int r4 = r0.y
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r4 & r3
            if (r2 == 0) goto L3bd
            int r4 = r4 - r3
            r0.y = r4
        L1e:
            java.lang.Object r9 = r0.w
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r12 = r0.y
            r10 = 2
            r7 = 3
            r11 = 1
            java.lang.String r2 = ""
            r4 = 0
            r16 = 0
            if (r12 == 0) goto L4b
            if (r12 == r11) goto L3a
            if (r12 == r10) goto L21b
            if (r12 != r7) goto L3c6
            kotlin.ResultKt.throwOnFailure(r9)
        L39:
            return r9
        L3a:
            java.lang.Object r7 = r0.u
            java.lang.String r7 = (java.lang.String) r7
            kotlin.jvm.functions.Function2 r6 = r0.t
            kotlin.jvm.functions.Function3 r5 = r0.s
            kotlin.jvm.functions.Function1 r8 = r0.r
            com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r0.q
            kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Exception -> L17e
            goto Lf4
        L4b:
            kotlin.ResultKt.throwOnFailure(r9)
            r1.getClass()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            java.lang.String r7 = r1.b
            r9.append(r7)
            java.lang.String r7 = r1.f74298c
            r9.append(r7)
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r7 = r1.e
            r9.append(r7)
            int r7 = r1.f
            r9.append(r7)
            boolean r7 = r1.i
            r9.append(r7)
            com.vega.edit.base.tone.EmotionOption r7 = r1.q
            if (r7 == 0) goto Ld5
            java.lang.String r7 = r7.toString()
        L77:
            r9.append(r7)
            java.lang.String r10 = r9.toString()
            java.lang.String r7 = "MD5"
            java.security.MessageDigest r9 = java.security.MessageDigest.getInstance(r7)
            java.nio.charset.Charset r7 = kotlin.text.Charsets.UTF_8
            byte[] r7 = r10.getBytes(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r2)
            byte[] r9 = r9.digest(r7)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1 r7 = new kotlin.jvm.functions.Function1<java.lang.Byte, java.lang.CharSequence>() { // from class: com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1
                static {
                    /*
                        com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1 r0 = new com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1) com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1.e com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1.<init>():void");
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
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTask$getStreamingCacheKey$$inlined$md5$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            java.lang.String r7 = kotlin.collections.ArraysKt.D(r9, r2, r7)
            com.vega.audio.tone.tts.engine.streaming.StreamToneCache$Companion r9 = com.vega.audio.tone.tts.engine.streaming.StreamToneCache.b
            r9.getClass()
            kotlin.Lazy<com.vega.audio.tone.tts.engine.streaming.StreamToneCache> r13 = com.vega.audio.tone.tts.engine.streaming.StreamToneCache.f74463c
            java.lang.Object r9 = r13.getValue()
            com.vega.audio.tone.tts.engine.streaming.StreamToneCache r9 = (com.vega.audio.tone.tts.engine.streaming.StreamToneCache) r9
            r9.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r9 = r9.f74464a
            java.lang.Object r12 = r9.get(r7)
            java.lang.String r12 = (java.lang.String) r12
            if (r12 != 0) goto Lb8
            r12 = r2
        Lb8:
            int r9 = r12.length()
            if (r9 <= 0) goto Ld3
            r9 = 1
        Lbf:
            if (r9 == 0) goto L1ac
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r9 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            r9.<init>(r12)
            java.lang.String r10 = kotlin.io.FilesKt__UtilsKt.getExtension(r9)
            java.lang.String r9 = "pcm"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r10, r9)
            if (r9 == 0) goto L134
            goto Ld7
        Ld3:
            r9 = 0
            goto Lbf
        Ld5:
            r7 = r4
            goto L77
        Ld7:
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Exception -> L132
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$2 r9 = new com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$2     // Catch: java.lang.Exception -> L132
            r9.<init>(r12, r4)     // Catch: java.lang.Exception -> L132
            r0.q = r1     // Catch: java.lang.Exception -> L132
            r0.r = r8     // Catch: java.lang.Exception -> L132
            r0.s = r5     // Catch: java.lang.Exception -> L130
            r0.t = r6     // Catch: java.lang.Exception -> L12e
            r0.u = r7     // Catch: java.lang.Exception -> L12e
            r0.y = r11     // Catch: java.lang.Exception -> L12e
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt__Builders_commonKt.withContext(r10, r9, r0)     // Catch: java.lang.Exception -> L12e
            if (r9 != r3) goto Lf4
            goto L3cf
        Lf4:
            byte[] r9 = (byte[]) r9     // Catch: java.lang.Exception -> L12c
            java.lang.Integer r12 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r16)     // Catch: java.lang.Exception -> L12c
            java.lang.Boolean r10 = java.lang.Boolean.valueOf(r11)     // Catch: java.lang.Exception -> L12c
            r5.invoke(r9, r12, r10)     // Catch: java.lang.Exception -> L12c
            com.vega.aigcapi.materialgenerate.StatusResult r15 = com.vega.aigcapi.materialgenerate.StatusResult.b     // Catch: java.lang.Exception -> L12c
            com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra r19 = new com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra     // Catch: java.lang.Exception -> L12c
            r24 = 7
            r19 = r19
            r20 = r4
            r21 = r4
            r22 = r16
            r23 = r11
            r19.<init>(r20, r21, r22, r23, r24)     // Catch: java.lang.Exception -> L12c
            r22 = 110(0x6e, float:1.54E-43)
            r17 = r4
            r18 = r4
            r20 = r4
            r21 = r4
            r14 = r25
            com.vega.aigcapi.materialgenerate.TtsResult r9 = f(r14, r15, r16, r17, r18, r19, r20, r21, r22)     // Catch: java.lang.Exception -> L12a
            kotlin.Pair r0 = kotlin.TuplesKt.to(r9, r4)     // Catch: java.lang.Exception -> L12a
            goto L3ce
        L12a:
            r10 = move-exception
            goto L17f
        L12c:
            r10 = move-exception
            goto L17f
        L12e:
            r10 = move-exception
            goto L17f
        L130:
            r10 = move-exception
            goto L17f
        L132:
            r10 = move-exception
            goto L17f
        L134:
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r9 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            r9.<init>(r12)
            boolean r9 = r9.exists()
            if (r9 == 0) goto L161
            r8.invoke(r12)
            com.vega.aigcapi.materialgenerate.StatusResult r1 = com.vega.aigcapi.materialgenerate.StatusResult.b
            r2 = 0
            com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra r5 = new com.vega.audio.tone.tts.engine.streaming.StreamingResultExtra
            r9 = 1
            r10 = 7
            r5 = r5
            r7 = r4
            r8 = r2
            r6 = r4
            r5.<init>(r6, r7, r8, r9, r10)
            r8 = 110(0x6e, float:1.54E-43)
            r3 = r4
            r4 = r4
            r6 = r4
            r7 = r4
            r0 = r25
            com.vega.aigcapi.materialgenerate.TtsResult r0 = f(r0, r1, r2, r3, r4, r5, r6, r7, r8)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r4)
            return r0
        L161:
            java.lang.Object r9 = r13.getValue()
            com.vega.audio.tone.tts.engine.streaming.StreamToneCache r9 = (com.vega.audio.tone.tts.engine.streaming.StreamToneCache) r9
            r9.getClass()
            java.util.HashMap<java.lang.String, java.lang.String> r4 = r9.f74464a
            java.lang.Object r4 = r4.remove(r7)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L17a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.vega.core.utils.FileExKt.b(r4)
        L17a:
            r9.b()
            goto L1ac
        L17e:
            r10 = move-exception
        L17f:
            com.vega.audio.tone.tts.engine.streaming.StreamToneCache$Companion r4 = com.vega.audio.tone.tts.engine.streaming.StreamToneCache.b
            r4.getClass()
            kotlin.Lazy<com.vega.audio.tone.tts.engine.streaming.StreamToneCache> r4 = com.vega.audio.tone.tts.engine.streaming.StreamToneCache.f74463c
            java.lang.Object r9 = r4.getValue()
            com.vega.audio.tone.tts.engine.streaming.StreamToneCache r9 = (com.vega.audio.tone.tts.engine.streaming.StreamToneCache) r9
            r9.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r2)
            java.util.HashMap<java.lang.String, java.lang.String> r4 = r9.f74464a
            java.lang.Object r4 = r4.remove(r7)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 == 0) goto L1a2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)
            com.vega.core.utils.FileExKt.b(r4)
        L1a2:
            r9.b()
            java.lang.String r4 = r10.getMessage()
            com.bytedance.services.apm.api.EnsureManager.ensureNotReachHere(r10, r4)
        L1ac:
            java.lang.String r4 = r1.f74297a
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.b = r4
            com.vega.core.context.AppProperty r4 = com.vega.core.context.ContextExtKt.app()
            r4.I()
            com.vega.corex.context.DeviceInfo r4 = com.vega.core.context.ContextExtKt.device()
            java.lang.String r15 = r4.c()
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r4 = r1.e
            java.lang.String r14 = r4.a()
            java.lang.String r13 = r1.f74298c
            com.vega.core.utils.MD5Utils r9 = com.vega.core.utils.MD5Utils.f79623a
            java.lang.String r4 = r1.b
            r9.getClass()
            java.lang.String r12 = com.vega.core.utils.MD5Utils.b(r4)
            if (r12 != 0) goto L1d5
            r12 = r2
        L1d5:
            com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode r11 = new com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode
            java.lang.String r4 = "streaming"
            r11.<init>(r4)
            com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$Input r10 = new com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$Input
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r4 = "appid:3006&did:"
            r9.<init>(r4)
            r9.append(r15)
            java.lang.String r4 = "&platform:"
            r9.append(r4)
            r9.append(r14)
            java.lang.String r4 = "&speaker_id:"
            r9.append(r4)
            r9.append(r13)
            java.lang.String r4 = "&text:"
            r9.append(r4)
            r9.append(r12)
            java.lang.String r4 = r9.toString()
            r10.<init>(r4)
            r0.q = r1
            r0.r = r8
            r0.s = r5
            r0.t = r6
            r0.u = r7
            r4 = 2
            r0.y = r4
            java.lang.Object r9 = r11.H(r10, r0)
            if (r9 != r3) goto L22a
            return r3
        L21b:
            java.lang.Object r7 = r0.u
            java.lang.String r7 = (java.lang.String) r7
            kotlin.jvm.functions.Function2 r6 = r0.t
            kotlin.jvm.functions.Function3 r5 = r0.s
            kotlin.jvm.functions.Function1 r8 = r0.r
            com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r0.q
            kotlin.ResultKt.throwOnFailure(r9)
        L22a:
            com.vega.clipflow.NodeResult r9 = (com.vega.clipflow.NodeResult) r9
            T r4 = r9.f75661a
            com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$Output r4 = (com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode.Output) r4
            if (r4 == 0) goto L266
            java.lang.String r4 = r4.f74413a
        L234:
            boolean r10 = r9 instanceof com.vega.clipflow.NodeResult.Success
            if (r10 == 0) goto L244
            if (r4 == 0) goto L240
            int r10 = r4.length()
            if (r10 != 0) goto L242
        L240:
            r16 = 1
        L242:
            if (r16 == 0) goto L268
        L244:
            com.vega.aigcapi.materialgenerate.StatusResult r3 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r4 = -402441(0xfffffffffff9dbf7, float:NaN)
            java.lang.Integer r0 = r9.f75663d
            java.lang.String r5 = java.lang.String.valueOf(r0)
            java.lang.String r0 = r9.e
            java.lang.String r6 = java.lang.String.valueOf(r0)
            r1 = 0
            r10 = 112(0x70, float:1.57E-43)
            r7 = r1
            r8 = r1
            r9 = r1
            r2 = r25
            com.vega.aigcapi.materialgenerate.TtsResult r0 = f(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r1)
            return r0
        L266:
            r4 = 0
            goto L234
        L268:
            java.lang.String r10 = r1.f74297a
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r9 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.f74488a
            r6.invoke(r10, r9)
            r0.q = r1
            r0.r = r8
            r0.s = r5
            r0.t = r6
            r0.u = r7
            r0.v = r4
            r9 = 3
            r0.y = r9
            kotlinx.coroutines.CancellableContinuationImpl r9 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r11 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r10 = 1
            r9.<init>(r11, r10)
            r9.initCancellability()
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager r51 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74465a
            r51.getClass()
            kotlin.Lazy r15 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.e
            java.lang.Object r12 = r15.getValue()
            com.vega.audio.tone.tts.IStreamingTextToSpeechService r12 = (com.vega.audio.tone.tts.IStreamingTextToSpeechService) r12
            java.lang.String r11 = r1.f74297a
            r10 = r25
            r12.h(r11, r10)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct> r12 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74466c
            java.lang.String r11 = r1.f74297a
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct r10 = new com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$StreamingStruct
            r16 = r10
            r17 = r9
            r18 = r8
            r19 = r5
            r20 = r6
            r21 = r7
            r16.<init>(r17, r18, r19, r20, r21)
            r12.put(r11, r10)
            java.util.Map<java.lang.String, java.lang.String> r7 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.f74468g
            java.lang.String r6 = r1.f74297a
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r5 = r1.e
            java.lang.String r5 = r5.b()
            r7.put(r6, r5)
            com.vega.audio.tone.tts.data.StreamingToneRequest r41 = new com.vega.audio.tone.tts.data.StreamingToneRequest
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r5 = r1.e
            java.lang.String r42 = r5.b()
            com.vega.audio.tone.tts.data.StreamingTonePayload r30 = new com.vega.audio.tone.tts.data.StreamingTonePayload
            java.lang.String r14 = r1.b
            java.lang.String r13 = r1.f74298c
            com.vega.edit.base.tone.EmotionOption r5 = r1.q
            if (r5 == 0) goto L3ba
            java.lang.String r8 = r5.e
        L2d8:
            if (r5 == 0) goto L3b6
            double r5 = r5.f
            java.lang.Double r20 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r5)
        L2e0:
            java.lang.String r12 = r1.n
            com.vega.edit.base.tone.EmotionOption r5 = r1.q
            if (r5 == 0) goto L3b3
            java.lang.String r7 = r5.f88854g
        L2e8:
            boolean r11 = r1.o
            if (r5 == 0) goto L3b0
            java.lang.String r6 = r5.f88852c
        L2ee:
            if (r5 == 0) goto L3ad
            java.lang.String r5 = r5.b
        L2f2:
            java.lang.String r10 = r1.w
            com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo r16 = new com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo
            r18 = 0
            r34 = 0
            r28 = 18
            r29 = 0
            r21 = r18
            r22 = r7
            r23 = r12
            r24 = r11
            r25 = r6
            r26 = r5
            r27 = r10
            r19 = r8
            r17 = r13
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29)
            java.lang.String r33 = "pcm"
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r5 = r1.e
            java.lang.String r36 = r5.a()
            java.lang.String r5 = r1.k
            boolean r5 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r5)
            if (r5 == 0) goto L3ab
            org.json.JSONObject r6 = new org.json.JSONObject
            java.lang.String r5 = r1.k
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            r6.<init>(r5)
            java.lang.String r5 = "from"
            java.lang.String r6 = r6.optString(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r2)
            java.util.Locale r5 = java.util.Locale.ROOT
            java.lang.String r5 = r6.toLowerCase(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
        L33f:
            r39 = 64
            r31 = r14
            r32 = r16
            r35 = r4
            r37 = r34
            r38 = r5
            r40 = r29
            r30.<init>(r31, r32, r33, r34, r35, r36, r37, r38, r39, r40)
            java.lang.String r45 = "create"
            java.lang.String r46 = com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.b
            com.vega.audio.tone.tts.util.TextToSpeechBabiUtils r4 = com.vega.audio.tone.tts.util.TextToSpeechBabiUtils.f74491a
            r4.getClass()
            com.vega.costreport.aigc.BabiParams r4 = com.vega.audio.tone.tts.util.TextToSpeechBabiUtils.a(r1)
            java.lang.String r4 = com.vega.core.ext.ExtentionKt.toJson(r4)
            if (r4 != 0) goto L3a9
        L363:
            r49 = 36
            r43 = r30
            r44 = r29
            r47 = r29
            r48 = r2
            r50 = r29
            r41.<init>(r42, r43, r44, r45, r46, r47, r48, r49, r50)
            java.lang.Object r5 = r15.getValue()
            com.vega.audio.tone.tts.IStreamingTextToSpeechService r5 = (com.vega.audio.tone.tts.IStreamingTextToSpeechService) r5
            java.lang.String r4 = r1.f74297a
            java.lang.String r2 = com.vega.core.ext.ExtentionKt.toJson(r41)
            r5.d(r4, r2)
            r55 = 60000(0xea60, double:2.9644E-319)
            java.lang.String r2 = r1.f74297a
            r52 = 0
            java.lang.String r54 = ""
            r57 = 1
            r53 = r2
            r51.g(r52, r53, r54, r55, r57)
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$4$1 r2 = new com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$4$1
            r2.<init>()
            r9.invokeOnCancellation(r2)
            java.lang.Object r9 = r9.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r9 != r1) goto L3a6
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        L3a6:
            if (r9 != r3) goto L39
            return r3
        L3a9:
            r2 = r4
            goto L363
        L3ab:
            r5 = r2
            goto L33f
        L3ad:
            r5 = 0
            goto L2f2
        L3b0:
            r6 = 0
            goto L2ee
        L3b3:
            r7 = 0
            goto L2e8
        L3b6:
            r20 = 0
            goto L2e0
        L3ba:
            r8 = 0
            goto L2d8
        L3bd:
            com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1 r0 = new com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager$getToneData$1
            r2 = r25
            r0.<init>(r2, r7)
            goto L1e
        L3c6:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L3ce:
            return r0
        L3cf:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.streaming.StreamingReadingToneManager.d(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void g(int i, String str, String str2, long j, boolean z) {
        Job job = f74467d;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        f74467d = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new StreamingReadingToneManager$waitingNextMessage$1(j, str, z, i, str2, null), 3, null);
    }

    @Override // com.vega.audio.tone.tts.IStreamingToneManager
    public final void onConnected(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        StreamingStruct streamingStruct = f74466c.get(str);
        if (streamingStruct != null) {
            streamingStruct.a(str, StreamingToneStage.b);
        }
    }
}
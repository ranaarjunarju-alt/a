package com.vega.audio.tone.tts.engine.streaming;

import com.bytedance.security.android.aopcheck.PolarisFileOutputStreamWrapper;
import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.services.apm.api.EnsureManager;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.Word;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.IStreamingTextToSpeechService;
import com.vega.audio.tone.tts.IStreamingToneManager;
import com.vega.audio.tone.tts.data.UtteranceData;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ContinuationExtKt;
import com.vega.core.utils.DirectoryUtil;
import com.vega.log.BLog;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* loaded from: classes36.dex */
public final class StreamingSavingToneManager implements IStreamingToneManager {
    public static Job e;

    /* renamed from: a, reason: collision with root package name */
    public static final StreamingSavingToneManager f74476a = new StreamingSavingToneManager();
    public static String b = "";

    /* renamed from: c, reason: collision with root package name */
    public static final ConcurrentHashMap<String, StreamingStruct> f74477c = new ConcurrentHashMap<>();

    /* renamed from: d, reason: collision with root package name */
    public static final ConcurrentHashMap<String, String> f74478d = new ConcurrentHashMap<>();
    public static final Lazy f = LazyKt__LazyJVMKt.lazy(new Function0<IStreamingTextToSpeechService>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$streamingToneService$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.IStreamingTextToSpeechService, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final IStreamingTextToSpeechService invoke() {
            return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IStreamingTextToSpeechService.class), null);
        }
    });

    /* renamed from: g, reason: collision with root package name */
    public static final Map<String, String> f74479g = new LinkedHashMap();

    /* loaded from: classes27.dex */
    public static final class StreamingStruct {

        /* renamed from: a, reason: collision with root package name */
        public final CancellableContinuation<Pair<? extends TtsResult, TextToAudioInfo>> f74480a;
        public final Function2<String, StreamingToneStage, Unit> b;

        /* renamed from: c, reason: collision with root package name */
        public final String f74481c;

        /* renamed from: d, reason: collision with root package name */
        public final List<String> f74482d;
        public final HashMap<Integer, String> e;
        public final HashMap<Integer, List<Word>> f;

        /* renamed from: g, reason: collision with root package name */
        public int f74483g;
        public int h;
        public byte[] i;

        /* loaded from: classes20.dex */
        public static final class Companion {
        }

        static {
            new Companion();
        }

        public StreamingStruct(CancellableContinuationImpl cancellableContinuationImpl, Function2 function2, String str, List list) {
            Intrinsics.checkNotNullParameter(cancellableContinuationImpl, "");
            Intrinsics.checkNotNullParameter(function2, "");
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(list, "");
            this.f74480a = cancellableContinuationImpl;
            this.b = function2;
            this.f74481c = str;
            this.f74482d = list;
            this.e = new HashMap<>();
            this.f = new HashMap<>();
            this.f74483g = -1;
            this.h = -1;
            this.i = new byte[0];
        }

        /* JADX DEBUG: Another duplicated slice has different insns count: {[]}, finally: {[THROW, INVOKE, MOVE_EXCEPTION, THROW, MOVE_EXCEPTION] complete} */
        /* JADX DEBUG: Finally have unexpected throw blocks count: 2, expect 1 */
        public final void a(int i, int i2, byte[] bArr) {
            Intrinsics.checkNotNullParameter(bArr, "");
            if (this.f74483g != i) {
                this.e.put(Integer.valueOf(i), "invalid_path");
                this.h = -1;
                this.f74483g = i;
                this.i = new byte[0];
            }
            byte[] bArrPlus = ArraysKt___ArraysJvmKt.plus(this.i, bArr);
            this.i = bArrPlus;
            if (i2 != -1) {
                if (i2 == this.h + 1) {
                    this.h = i2;
                    return;
                }
                return;
            }
            int i3 = this.f74483g;
            byte[] bArrCopyOf = Arrays.copyOf(bArrPlus, bArrPlus.length);
            Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "");
            if (bArrCopyOf.length != 0) {
                DirectoryUtil.f79563a.getClass();
                String strZ0 = DirectoryUtil.z0();
                StringBuilder sb = new StringBuilder();
                sb.append(strZ0);
                String str = File.separator;
                sb.append(str);
                sb.append("tts_save");
                sb.append(str);
                sb.append(this.f74481c);
                sb.append(str);
                sb.append(i3);
                sb.append(".mp3");
                String string = sb.toString();
                try {
                    File parentFile = new PolarisFileWrapper(string).getParentFile();
                    if (parentFile != null && !parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    PolarisFileOutputStreamWrapper polarisFileOutputStreamWrapper = new PolarisFileOutputStreamWrapper(string);
                    try {
                        polarisFileOutputStreamWrapper.write(bArrCopyOf);
                        CloseableKt.closeFinally(polarisFileOutputStreamWrapper, null);
                        this.e.put(Integer.valueOf(i3), string);
                    } finally {
                    }
                } catch (Exception e) {
                    this.e.put(Integer.valueOf(i3), "invalid_path");
                    BLog.e("StreamingSavingToneManager", "save audio cache failed " + e.getMessage());
                    EnsureManager.ensureNotReachHere(e, e.getMessage());
                }
            }
            this.i = new byte[0];
        }

        public final void b(int i, List<UtteranceData> list) {
            Intrinsics.checkNotNullParameter(list, "");
            HashMap<Integer, List<Word>> map = this.f;
            Integer numValueOf = Integer.valueOf(i);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
            for (UtteranceData utteranceData : list) {
                String text = utteranceData.getText();
                if (text == null) {
                    text = "";
                }
                long jLongValue = 0;
                float fLongValue = (utteranceData.getStartTime() != null ? r0.longValue() : 0L) / 1000.0f;
                Long endTime = utteranceData.getEndTime();
                if (endTime != null) {
                    jLongValue = endTime.longValue();
                }
                arrayList.add(new Word(text, fLongValue, jLongValue / 1000.0f));
            }
            map.put(numValueOf, arrayList);
        }

        public final void c(String str, StreamingToneStage streamingToneStage) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(streamingToneStage, "");
            this.b.invoke(str, streamingToneStage);
        }

        public final void d(Pair<? extends TtsResult, TextToAudioInfo> pair) {
            Intrinsics.checkNotNullParameter(pair, "");
            ContinuationExtKt.a(this.f74480a, pair);
        }
    }

    public static TtsResult e(StatusResult statusResult, int i, String str, String str2) {
        return new TtsResult(statusResult, i, str, str2, 0L, false, TtsResult.RequestScene.f69168d, null, null, false, false, null, null, 8112);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x0183  */
    @Override // com.vega.audio.tone.tts.IStreamingToneManager
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(com.vega.audio.tone.tts.data.StreamingToneResponse r20) {
        /*
            r19 = this;
            java.lang.String r4 = ""
            r7 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r4)
            java.lang.String r3 = r7.getTaskId()
            if (r3 != 0) goto Le
            r3 = r4
        Le:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.lang.String> r1 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74478d
            java.lang.String r0 = r7.getMatrixTaskId()
            if (r0 != 0) goto L17
            r0 = r4
        L17:
            r1.put(r3, r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "dealNextMessage: taskId:"
            r1.<init>(r0)
            java.lang.String r0 = r7.getTaskId()
            r1.append(r0)
            java.lang.String r0 = ", matrixTaskId:"
            r1.append(r0)
            java.lang.String r0 = r7.getMatrixTaskId()
            r1.append(r0)
            java.lang.String r0 = ", code:"
            r1.append(r0)
            java.lang.Integer r0 = r7.getCode()
            r1.append(r0)
            java.lang.String r0 = ", msg:"
            r1.append(r0)
            java.lang.String r0 = r7.getMsg()
            r1.append(r0)
            java.lang.String r0 = ", message_index:"
            r1.append(r0)
            int r0 = r7.getMessageIndex()
            r1.append(r0)
            java.lang.String r0 = ", task_index:"
            r1.append(r0)
            int r0 = r7.getTaskIndex()
            r1.append(r0)
            java.lang.String r0 = ", sub_index:"
            r1.append(r0)
            int r0 = r7.getIndex()
            r1.append(r0)
            java.lang.String r0 = ", utterances:"
            r1.append(r0)
            java.lang.String r0 = r7.getUtterances()
            r13 = 0
            if (r0 == 0) goto Led
            int r0 = r0.length()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        L84:
            r1.append(r0)
            java.lang.String r0 = " hitCache:"
            r1.append(r0)
            boolean r0 = r7.getHitCache()
            r1.append(r0)
            java.lang.String r0 = ", hasMore:"
            r1.append(r0)
            boolean r0 = r7.getHasMore()
            r1.append(r0)
            java.lang.String r0 = ", base64 size:"
            r1.append(r0)
            java.lang.String r0 = r7.getAudioBase64()
            if (r0 == 0) goto Leb
            int r0 = r0.length()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
        Lb2:
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            java.lang.String r10 = "StreamingSavingToneManager"
            com.vega.log.BLog.i(r10, r0)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$StreamingStruct> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74477c
            java.lang.Object r6 = r0.get(r3)
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$StreamingStruct r6 = (com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.StreamingStruct) r6
            if (r6 != 0) goto Lef
            java.util.Map<java.lang.String, java.lang.String> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74479g
            java.lang.Object r2 = r0.remove(r3)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto Le8
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74476a
            r0.getClass()
            kotlin.Lazy r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f
            java.lang.Object r1 = r0.getValue()
            com.vega.audio.tone.tts.IStreamingTextToSpeechService r1 = (com.vega.audio.tone.tts.IStreamingTextToSpeechService) r1
            java.lang.String r0 = r7.getMatrixTaskId()
            if (r0 != 0) goto Le9
        Le5:
            r1.g(r3, r4, r2)
        Le8:
            return
        Le9:
            r4 = r0
            goto Le5
        Leb:
            r0 = r13
            goto Lb2
        Led:
            r0 = r13
            goto L84
        Lef:
            java.lang.String r1 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.b
            java.lang.String r0 = r7.getTaskId()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 != 0) goto Lfc
            return
        Lfc:
            java.lang.Integer r0 = r7.getCode()
            if (r0 != 0) goto L139
        L102:
            com.vega.aigcapi.materialgenerate.StatusResult r3 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            java.lang.Integer r0 = r7.getCode()
            java.lang.String r2 = java.lang.String.valueOf(r0)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "["
            r1.<init>(r0)
            java.lang.String r0 = r7.getMatrixTaskId()
            r1.append(r0)
            java.lang.String r0 = "]: "
            r1.append(r0)
            java.lang.String r0 = r7.getMsg()
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            r0 = -402443(0xfffffffffff9dbf5, float:NaN)
            com.vega.aigcapi.materialgenerate.TtsResult r0 = e(r3, r0, r2, r1)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r13)
            r6.d(r0)
            return
        L139:
            int r0 = r0.intValue()
            if (r0 == 0) goto L140
            goto L102
        L140:
            int r12 = r7.getMessageIndex()
            int r9 = r7.getTaskIndex()
            int r8 = r7.getIndex()
            if (r12 != 0) goto L153
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r0 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.f74490d
            r6.c(r3, r0)
        L153:
            java.lang.String r11 = r7.getUtterances()
            r2 = 0
            r5 = 1
            if (r11 == 0) goto L183
            com.google.gson.Gson r1 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Exception -> L17d
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$onReceiveStreamingResponse$2$utteranceList$1 r0 = new com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$onReceiveStreamingResponse$2$utteranceList$1     // Catch: java.lang.Exception -> L17d
            r0.<init>()     // Catch: java.lang.Exception -> L17d
            java.lang.reflect.Type r0 = r0.getType()     // Catch: java.lang.Exception -> L17d
            java.lang.Object r1 = r1.fromJson(r11, r0)     // Catch: java.lang.Exception -> L17d
            java.util.List r1 = (java.util.List) r1     // Catch: java.lang.Exception -> L17d
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)     // Catch: java.lang.Exception -> L17d
            boolean r0 = r1.isEmpty()     // Catch: java.lang.Exception -> L17d
            r0 = r0 ^ 1
            if (r0 == 0) goto L183
            r6.b(r9, r1)     // Catch: java.lang.Exception -> L17d
            goto L199
        L17d:
            r1 = move-exception
            java.lang.String r0 = "parse utterance error"
            com.vega.log.BLog.e(r10, r0, r1)
        L183:
            java.lang.String r1 = r7.getAudioBase64()
            if (r1 == 0) goto L199
            int r0 = r1.length()
            if (r0 <= 0) goto L276
            byte[] r0 = android.util.Base64.decode(r1, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r6.a(r9, r8, r0)
        L199:
            if (r12 != 0) goto L1a0
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r0 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.e
            r6.c(r3, r0)
        L1a0:
            boolean r0 = r7.getHasMore()
            if (r0 != 0) goto L260
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.b
            com.vega.aigcapi.materialgenerate.TtsResult r8 = e(r0, r2, r4, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r4)
            java.util.HashMap<java.lang.Integer, java.lang.String> r0 = r6.e
            java.util.Collection r1 = r0.values()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            java.lang.String r7 = "invalid_path"
            if (r0 == 0) goto L1ee
        L1c3:
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r8.f69163a = r0
            r0 = -402444(0xfffffffffff9dbf4, float:NaN)
            r8.b = r0
            java.lang.String r0 = "all audio invalid"
            r8.f69165d = r0
            kotlin.Pair r0 = kotlin.TuplesKt.to(r8, r13)
            r6.d(r0)
        L1da:
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$StreamingStruct> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74477c
            r0.remove(r3)
            java.util.Map<java.lang.String, java.lang.String> r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74479g
            r0.remove(r3)
            kotlinx.coroutines.Job r0 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.e
            if (r0 == 0) goto L1eb
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r13, r5, r13)
        L1eb:
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.e = r13
        L1ed:
            return
        L1ee:
            java.util.Iterator r1 = r1.iterator()
        L1f2:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto L1c3
            java.lang.Object r0 = r1.next()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r7)
            if (r0 != 0) goto L1f2
            com.lemon.lv.data.TextToAudioInfo r12 = new com.lemon.lv.data.TextToAudioInfo
            r18 = 127(0x7f, float:1.78E-43)
            r14 = r13
            r15 = r13
            r16 = r13
            r17 = r13
            r12.<init>(r13, r14, r15, r16, r17, r18)
            java.util.HashMap<java.lang.Integer, java.lang.String> r0 = r6.e
            int r9 = r0.size()
        L215:
            if (r2 >= r9) goto L257
            java.util.List<java.lang.String> r0 = r6.f74482d
            int r0 = r0.size()
            if (r0 <= r2) goto L254
            java.util.HashMap<java.lang.Integer, java.lang.String> r1 = r6.e
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            java.lang.Object r11 = r1.get(r0)
            if (r11 != 0) goto L22c
            r11 = r7
        L22c:
            java.util.HashMap<java.lang.Integer, java.util.List<com.lemon.lv.data.Word>> r1 = r6.f
            java.lang.Integer r0 = java.lang.Integer.valueOf(r2)
            java.lang.Object r10 = r1.get(r0)
            java.util.List<java.lang.String> r0 = r12.f59067a
            r0.add(r11)
            java.util.List<java.lang.String> r1 = r12.b
            java.util.List<java.lang.String> r0 = r6.f74482d
            java.lang.Object r0 = r0.get(r2)
            r1.add(r0)
            java.util.List<java.lang.String> r1 = r12.f59068c
            if (r10 == 0) goto L250
            java.lang.String r0 = com.vega.core.ext.ExtentionKt.toJson(r10)
            if (r0 != 0) goto L251
        L250:
            r0 = r4
        L251:
            r1.add(r0)
        L254:
            int r2 = r2 + 1
            goto L215
        L257:
            kotlin.Pair r0 = kotlin.TuplesKt.to(r8, r12)
            r6.d(r0)
            goto L1da
        L260:
            java.lang.String r0 = r7.getMatrixTaskId()
            if (r0 != 0) goto L274
        L266:
            r15 = 60000(0xea60, double:2.9644E-319)
            r11 = r19
            r17 = r2
            r13 = r3
            r14 = r4
            r11.f(r12, r13, r14, r15, r17)
            goto L1ed
        L274:
            r4 = r0
            goto L266
        L276:
            r0 = -1
            if (r8 != r0) goto L199
            byte[] r0 = new byte[r2]
            r6.a(r9, r8, r0)
            goto L199
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.a(com.vega.audio.tone.tts.data.StreamingToneResponse):void");
    }

    @Override // com.vega.audio.tone.tts.IStreamingToneManager
    public final void b(String str, String str2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        BLog.i("StreamingSavingToneManager", "onSendMessageCallback " + z);
        if (z) {
            StreamingStruct streamingStruct = f74477c.get(str);
            if (streamingStruct != null) {
                streamingStruct.c(str, StreamingToneStage.f74489c);
                return;
            }
            return;
        }
        StreamingStruct streamingStruct2 = f74477c.get(str);
        if (streamingStruct2 != null) {
            streamingStruct2.d(TuplesKt.to(e(StatusResult.f69154c, -402442, "", str2), null));
        }
    }

    public final void c(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        ConcurrentHashMap<String, StreamingStruct> concurrentHashMap = f74477c;
        StreamingStruct streamingStruct = concurrentHashMap.get(str);
        if (streamingStruct != null && streamingStruct.f74480a.isActive()) {
            CancellableContinuation.DefaultImpls.cancel$default(streamingStruct.f74480a, null, 1, null);
        }
        concurrentHashMap.remove(str);
        ((IStreamingTextToSpeechService) f.getValue()).c(str);
        Job job = e;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        e = null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v6, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0292  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(com.vega.audio.tone.tts.core.TextToSpeechTask r43, kotlin.jvm.functions.Function2<? super java.lang.String, ? super com.vega.audio.tone.tts.engine.streaming.StreamingToneStage, kotlin.Unit> r44, kotlin.coroutines.Continuation<? super kotlin.Pair<? extends com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo>> r45) {
        /*
            r42 = this;
            r5 = r45
            r0 = r43
            r8 = r44
            boolean r1 = r5 instanceof com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$1
            r9 = r42
            if (r1 == 0) goto L292
            r1 = r5
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$1 r1 = (com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$1) r1
            int r4 = r1.x
            r3 = -2147483648(0xffffffff80000000, float:-0.0)
            r2 = r4 & r3
            if (r2 == 0) goto L292
            int r4 = r4 - r3
            r1.x = r4
        L1a:
            java.lang.Object r10 = r1.v
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r1.x
            r5 = 2
            r3 = 0
            r6 = 1
            java.lang.String r16 = ""
            if (r2 == 0) goto L31
            if (r2 == r6) goto L112
            if (r2 != r5) goto L299
            kotlin.ResultKt.throwOnFailure(r10)
        L30:
            return r10
        L31:
            kotlin.ResultKt.throwOnFailure(r10)
            com.vega.core.context.AppProperty r2 = com.vega.core.context.ContextExtKt.app()
            r2.I()
            com.vega.corex.context.DeviceInfo r2 = com.vega.core.context.ContextExtKt.device()
            java.lang.String r12 = r2.c()
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r2 = r0.e
            java.lang.String r11 = r2.a()
            java.lang.String r4 = r0.f74298c
            java.lang.String r13 = r0.b
            java.lang.String r2 = "\n"
            java.lang.String[] r10 = new java.lang.String[]{r2}
            r19 = 0
            r2 = 6
            java.util.List r2 = X.C93472yG.M(r13, r10, r3, r2)
            com.vega.core.utils.MD5Utils r13 = com.vega.core.utils.MD5Utils.f79623a
            java.lang.String r18 = ""
            r23 = 62
            r17 = r2
            r20 = r19
            r21 = r3
            r22 = r19
            java.lang.String r10 = kotlin.collections.CollectionsKt.j(r17, r18, r19, r20, r21, r22, r23)
            r13.getClass()
            java.lang.String r13 = com.vega.core.utils.MD5Utils.b(r10)
            if (r13 != 0) goto L77
            r13 = r16
        L77:
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r14 = r0.e
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r10 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74292c
            if (r14 == r10) goto L81
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r10 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74291a
            if (r14 != r10) goto L110
        L81:
            boolean r10 = r0.o
            if (r10 == 0) goto L110
            com.vega.audio.tone.util.TextToSpeechReportInfo$Companion r14 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion
            java.lang.String r10 = r0.k
            r14.getClass()
            com.vega.audio.tone.util.TextToSpeechReportInfo r10 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion.a(r10)
            if (r10 == 0) goto L98
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r10 = r10.getFrom()
            if (r10 != 0) goto L9a
        L98:
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r10 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.NONE
        L9a:
            r14 = 5
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene[] r14 = new com.vega.aigcapi.materialgenerate.TextToSpeechReportScene[r14]
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r15 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.LIP_SYNC
            r14[r3] = r15
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r3 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.DIGITAL_HUMAN
            r14[r6] = r3
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r3 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.DIGITAL_HUMAN_TEXT
            r14[r5] = r3
            r5 = 3
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r3 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.DIGITAL_HUMAN_SUBTITLE
            r14[r5] = r3
            r5 = 4
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r3 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AUDIO_CLONE_AUDITION_RESULT
            r14[r5] = r3
            java.util.Set r3 = kotlin.collections.SetsKt__SetsKt.setOf(r14)
            boolean r3 = r3.contains(r10)
        Lbb:
            com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode r10 = new com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode
            java.lang.String r5 = "streaming"
            r10.<init>(r5)
            com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$Input r5 = new com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$Input
            java.lang.StringBuilder r14 = new java.lang.StringBuilder
            java.lang.String r15 = "appid:3006&did:"
            r14.<init>(r15)
            r14.append(r12)
            java.lang.String r12 = "&platform:"
            r14.append(r12)
            r14.append(r11)
            java.lang.String r11 = "&speaker_id:"
            r14.append(r11)
            r14.append(r4)
            java.lang.String r4 = "&texts:"
            r14.append(r4)
            r14.append(r13)
            java.lang.String r4 = "&is_clone_tone:"
            r14.append(r4)
            boolean r4 = r0.o
            r14.append(r4)
            java.lang.String r4 = "&credit_disable:"
            r14.append(r4)
            r14.append(r3)
            java.lang.String r4 = r14.toString()
            r5.<init>(r4)
            r1.q = r0
            r1.r = r8
            r1.s = r2
            r1.u = r3
            r1.x = r6
            java.lang.Object r10 = r10.H(r5, r1)
            if (r10 != r7) goto L11f
            return r7
        L110:
            r3 = 0
            goto Lbb
        L112:
            boolean r3 = r1.u
            java.lang.Object r2 = r1.s
            java.util.List r2 = (java.util.List) r2
            kotlin.jvm.functions.Function2 r8 = r1.r
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r1.q
            kotlin.ResultKt.throwOnFailure(r10)
        L11f:
            com.vega.clipflow.NodeResult r10 = (com.vega.clipflow.NodeResult) r10
            T r4 = r10.f75661a
            com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$Output r4 = (com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode.Output) r4
            if (r4 == 0) goto L150
            java.lang.String r4 = r4.f74413a
        L129:
            boolean r5 = r10 instanceof com.vega.clipflow.NodeResult.Success
            if (r5 == 0) goto L135
            if (r4 == 0) goto L135
            int r5 = r4.length()
            if (r5 != 0) goto L152
        L135:
            com.vega.aigcapi.materialgenerate.StatusResult r3 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            java.lang.Integer r0 = r10.f75663d
            java.lang.String r2 = java.lang.String.valueOf(r0)
            java.lang.String r0 = r10.e
            java.lang.String r1 = java.lang.String.valueOf(r0)
            r0 = -402441(0xfffffffffff9dbf7, float:NaN)
            com.vega.aigcapi.materialgenerate.TtsResult r1 = e(r3, r0, r2, r1)
            r0 = 0
            kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)
            return r0
        L150:
            r4 = 0
            goto L129
        L152:
            r1.q = r0
            r1.r = r8
            r1.s = r2
            r1.t = r4
            r1.u = r3
            r5 = 2
            r1.x = r5
            kotlinx.coroutines.CancellableContinuationImpl r10 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r1)
            r10.<init>(r5, r6)
            r10.initCancellability()
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager r15 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74476a
            r15.getClass()
            kotlin.Lazy r14 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f
            java.lang.Object r6 = r14.getValue()
            com.vega.audio.tone.tts.IStreamingTextToSpeechService r6 = (com.vega.audio.tone.tts.IStreamingTextToSpeechService) r6
            java.lang.String r5 = r0.f74297a
            r6.h(r5, r9)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$StreamingStruct> r11 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74477c
            java.lang.String r9 = r0.f74297a
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$StreamingStruct r6 = new com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$StreamingStruct
            java.lang.String r5 = r0.f74297a
            r6.<init>(r10, r8, r5, r2)
            r11.put(r9, r6)
            java.lang.String r9 = r0.f74297a
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.b = r9
            java.util.Map<java.lang.String, java.lang.String> r6 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.f74479g
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r5 = r0.e
            java.lang.String r5 = r5.c()
            r6.put(r9, r5)
            java.lang.String r6 = r0.f74297a
            com.vega.audio.tone.tts.engine.streaming.StreamingToneStage r5 = com.vega.audio.tone.tts.engine.streaming.StreamingToneStage.f74488a
            r8.invoke(r6, r5)
            com.vega.audio.tone.tts.data.StreamingToneRequest r31 = new com.vega.audio.tone.tts.data.StreamingToneRequest
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r5 = r0.e
            java.lang.String r32 = r5.c()
            com.vega.audio.tone.tts.data.StreamingToneReqPayload r33 = new com.vega.audio.tone.tts.data.StreamingToneReqPayload
            java.lang.String r13 = r0.f74298c
            com.vega.edit.base.tone.EmotionOption r5 = r0.q
            if (r5 == 0) goto L28d
            java.lang.String r9 = r5.e
            double r5 = r5.f
            java.lang.Double r21 = kotlin.coroutines.jvm.internal.Boxing.boxDouble(r5)
        L1b9:
            java.lang.String r12 = r0.n
            com.vega.edit.base.tone.EmotionOption r5 = r0.q
            if (r5 == 0) goto L28a
            java.lang.String r8 = r5.f88854g
        L1c1:
            boolean r11 = r0.o
            if (r5 == 0) goto L286
            java.lang.String r6 = r5.f88852c
            java.lang.String r5 = r5.b
        L1c9:
            com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo r17 = new com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo
            r19 = 0
            r28 = 0
            r29 = 1042(0x412, float:1.46E-42)
            r22 = r19
            r23 = r8
            r24 = r12
            r25 = r11
            r26 = r6
            r27 = r5
            r30 = r28
            r20 = r9
            r18 = r13
            r17.<init>(r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30)
            java.lang.String r36 = "mp3"
            r37 = 1
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r5 = r0.e
            java.lang.String r39 = r5.a()
            java.lang.String r5 = r0.k
            boolean r5 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r5)
            if (r5 == 0) goto L283
            org.json.JSONObject r5 = new org.json.JSONObject
            java.lang.String r6 = r0.k
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r5.<init>(r6)
            java.lang.String r6 = "from"
            java.lang.String r6 = r5.optString(r6)
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
            java.util.Locale r5 = java.util.Locale.ROOT
            java.lang.String r6 = r6.toLowerCase(r5)
            r5 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r5)
        L218:
            r34 = r2
            r35 = r17
            r38 = r4
            r40 = r3
            r41 = r6
            r33.<init>(r34, r35, r36, r37, r38, r39, r40, r41)
            java.lang.String r34 = com.vega.core.ext.ExtentionKt.toJson(r33)
            java.lang.String r35 = "create"
            java.lang.String r36 = com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.b
            com.vega.audio.tone.tts.util.TextToSpeechBabiUtils r2 = com.vega.audio.tone.tts.util.TextToSpeechBabiUtils.f74491a
            r2.getClass()
            com.vega.costreport.aigc.BabiParams r2 = com.vega.audio.tone.tts.util.TextToSpeechBabiUtils.a(r0)
            java.lang.String r2 = com.vega.core.ext.ExtentionKt.toJson(r2)
            if (r2 != 0) goto L280
        L23c:
            r39 = 34
            r37 = r28
            r38 = r16
            r40 = r28
            r33 = r28
            r31.<init>(r32, r33, r34, r35, r36, r37, r38, r39, r40)
            java.lang.Object r4 = r14.getValue()
            com.vega.audio.tone.tts.IStreamingTextToSpeechService r4 = (com.vega.audio.tone.tts.IStreamingTextToSpeechService) r4
            java.lang.String r3 = r0.f74297a
            java.lang.String r2 = com.vega.core.ext.ExtentionKt.toJson(r31)
            r4.d(r3, r2)
            r19 = 60000(0xea60, double:2.9644E-319)
            java.lang.String r2 = r0.f74297a
            r16 = -1
            java.lang.String r18 = ""
            r21 = 1
            r17 = r2
            r15.f(r16, r17, r18, r19, r21)
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$2$1 r2 = new com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$2$1
            r2.<init>()
            r10.invokeOnCancellation(r2)
            java.lang.Object r10 = r10.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r10 != r0) goto L27d
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r1)
        L27d:
            if (r10 != r7) goto L30
            return r7
        L280:
            r16 = r2
            goto L23c
        L283:
            r6 = r16
            goto L218
        L286:
            r6 = 0
            r5 = 0
            goto L1c9
        L28a:
            r8 = 0
            goto L1c1
        L28d:
            r9 = 0
            r21 = 0
            goto L1b9
        L292:
            com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$1 r1 = new com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager$getTTSSavingData$1
            r1.<init>(r9, r5)
            goto L1a
        L299:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.streaming.StreamingSavingToneManager.d(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void f(int i, String str, String str2, long j, boolean z) {
        Job job = e;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        e = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new StreamingSavingToneManager$waitingNextMessage$1(60000L, str, z, i, str2, null), 3, null);
    }

    @Override // com.vega.audio.tone.tts.IStreamingToneManager
    public final void onConnected(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        StreamingStruct streamingStruct = f74477c.get(str);
        if (streamingStruct != null) {
            streamingStruct.c(str, StreamingToneStage.b);
        }
    }
}
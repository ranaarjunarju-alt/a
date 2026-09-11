package com.vega.audio.tone.manager;

import com.bytedance.apm.util.NetUtils;
import com.google.gson.Gson;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.manager.CloneToneTextAudioManager;
import com.vega.audio.tone.manager.CloneToneTextAudioTask;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.edit.base.audio.tone.TextToAudioInfoPack;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.editorapi.bean.TTSWord;
import com.vega.editorapi.bean.TTSWordList;
import com.vega.editorapi.bean.TextAudioData;
import com.vega.editorapi.bean.TextStartSource;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.log.BLog;
import com.vega.operation.bean.Sentence;
import com.vega.subscriptionapi.biz.function.ICloneToneBusiness;
import com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction;
import com.vega.subscriptionapi.core.Result;
import com.vega.ve.data.AudioMetaDataInfo;
import com.vega.ve.utils.MediaUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CancellationException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* loaded from: classes8.dex */
public final class CloneToneTextAudioManager {

    /* renamed from: a, reason: collision with root package name */
    public long f74139a;
    public CloneToneTextAudioTask b;

    /* renamed from: c, reason: collision with root package name */
    public Function1<? super List<TextAudioData>, Unit> f74140c;

    /* renamed from: d, reason: collision with root package name */
    public Function1<? super CloneToneTextAudioTask.State, Unit> f74141d;
    public Function2<? super Integer, ? super String, Unit> e;
    public final ICloneToneBusiness f = (ICloneToneBusiness) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ICloneToneBusiness.class), null);

    /* renamed from: g, reason: collision with root package name */
    public Job f74142g;
    public CoroutineScope h;

    /* loaded from: classes14.dex */
    public static final class Companion {
    }

    /* loaded from: classes19.dex */
    public /* synthetic */ class WhenMappings {
        static {
            CloneToneTextAudioTask.State.values();
        }
    }

    /* loaded from: classes34.dex */
    public final class WrapTextToAudioResult {

        /* renamed from: a, reason: collision with root package name */
        public final TextToAudioInfoPack f74143a;
        public final TtsResult b;

        public WrapTextToAudioResult(TextToAudioInfoPack textToAudioInfoPack, TtsResult ttsResult) {
            Intrinsics.checkNotNullParameter(textToAudioInfoPack, "");
            Intrinsics.checkNotNullParameter(ttsResult, "");
            this.f74143a = textToAudioInfoPack;
            this.b = ttsResult;
        }
    }

    static {
        new Companion();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r25v10, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v11, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v12, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v13, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v14, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v15, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v16, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v17, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v18, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v19, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v20, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v21, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v9, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static List c(TextToAudioInfoPack textToAudioInfoPack, List list, long j) {
        long j2 = j / 1000;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        int i = 0;
        for (Object obj : textToAudioInfoPack.f87040a) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt__CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            MediaUtil.f135467a.getClass();
            AudioMetaDataInfo audioMetaDataInfoC = MediaUtil.c(str);
            long j3 = 1000;
            arrayList.add(Long.valueOf(audioMetaDataInfoC.f135305a * 1000));
            TTSWordList tTSWordList = (TTSWordList) new Gson().fromJson("{\n    \"words\": " + textToAudioInfoPack.f87042d.get(i) + '}', TTSWordList.class);
            ArrayList arrayList3 = new ArrayList();
            for (TTSWord tTSWord : tTSWordList.getWords()) {
                float f = j3;
                float f2 = j2;
                arrayList3.add(new Sentence(tTSWord.getText(), (long) ((tTSWord.getStartTime() * f) + f2), (long) ((tTSWord.getEndTime() * f) + f2), null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 8176, 0 == true ? 1 : 0));
                j3 = 1000;
            }
            Sentence sentence = new Sentence((String) list.get(i), j2, audioMetaDataInfoC.f135305a + j2, null, arrayList3, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 8160, 0 == true ? 1 : 0);
            j2 += audioMetaDataInfoC.f135305a;
            String str2 = (String) CollectionsKt___CollectionsKt.getOrNull(textToAudioInfoPack.f, i);
            if (str2 == null && (str2 = (String) CollectionsKt___CollectionsKt.firstOrNull((List) textToAudioInfoPack.f)) == null) {
                str2 = "";
            }
            arrayList2.add(new TextAudioData(TextStartSource.f97656c, str, sentence, false, false, str2, 24));
            i = i2;
        }
        return arrayList2;
    }

    public final void a() {
        StringBuilder sb = new StringBuilder("cancel task: use commercial:");
        CloneToneTextAudioTask cloneToneTextAudioTask = this.b;
        sb.append(cloneToneTextAudioTask != null ? Boolean.valueOf(cloneToneTextAudioTask.i) : null);
        BLog.i("CloneToneTextAudioManager", sb.toString());
        CloneToneTextAudioTask cloneToneTextAudioTask2 = this.b;
        if (cloneToneTextAudioTask2 != null && cloneToneTextAudioTask2.i) {
            this.f.l().h0();
        }
        CloneToneTextAudioTask cloneToneTextAudioTask3 = this.b;
        if (cloneToneTextAudioTask3 != null) {
            cloneToneTextAudioTask3.e = false;
            cloneToneTextAudioTask3.f = -1002;
            cloneToneTextAudioTask3.f74150g = "task cancel";
            Function1<? super CloneToneTextAudioTask.State, Unit> function1 = this.f74141d;
            if (function1 != null) {
                function1.invoke(cloneToneTextAudioTask3.f74149d);
            }
        }
        Job job = this.f74142g;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        CoroutineScope coroutineScope = this.h;
        if (coroutineScope != null) {
            CoroutineScopeKt.cancel$default(coroutineScope, null, 1, null);
        }
    }

    public final Object b(final ToneType toneType, final List<String> list, final Function2<? super Integer, ? super Result, Unit> function2, Continuation<Object> continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        if (list.isEmpty()) {
            kotlin.Result.m17090constructorimpl(null);
            cancellableContinuationImpl.resumeWith(null);
        } else if (NetUtils.b(ModuleCommon.INSTANCE.getApplication())) {
            final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.vega.audio.tone.manager.CloneToneTextAudioManager$generateCloneToneAudio$2$realSaveAudio$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    TextToSpeechReportScene textToSpeechReportScene = TextToSpeechReportScene.AUDIO_CLONE;
                    Iterator<T> it = list.iterator();
                    int length = 0;
                    while (it.hasNext()) {
                        length += ((String) it.next()).length();
                    }
                    TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(textToSpeechReportScene, null, length, false, false, 0L, null, null, null, null, 994, null);
                    TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
                    TextInfo.NoSegTextList noSegTextList = new TextInfo.NoSegTextList(list);
                    String voiceType = toneType.getVoiceType();
                    String platform = toneType.getPlatform();
                    TTSBusinessType tTSBusinessType = TTSBusinessType.f88857c;
                    HashMap mapHashMapOf = MapsKt__MapsKt.hashMapOf(TuplesKt.to("tone_id", toneType.getId()), TuplesKt.to("tone_category_id", toneType.getCategoryID()), TuplesKt.to("tone_category", toneType.getCategoryKey()), TuplesKt.to("is_vip", ExtentionKt.getReportStr(Boolean.valueOf(toneType.isVip()))), TuplesKt.to("is_cloned", Integer.valueOf(toneType.isAICloneTone() ? 1 : 0)), TuplesKt.to("tone_second_category", toneType.getSecondCategoryKey()), TuplesKt.to("resource_id", toneType.getResourceId()));
                    String json = textToSpeechReportInfo.toJson();
                    String mockToneInfo = toneType.getMockToneInfo();
                    textToSpeechTaskManager.c(new TextToSpeechIntent(null, noSegTextList, voiceType, platform, "VoiceoverTtsGenerator", null, tTSBusinessType, "edit", "", 0.0f, 0, null, mapHashMapOf, false, json, toneType.getAuditionText(), false, toneType.getToneModelType(), toneType.getResourceId(), new Function2<TtsResult, TextToAudioInfo, Unit>(cancellableContinuationImpl, this) { // from class: com.vega.audio.tone.manager.CloneToneTextAudioManager$generateCloneToneAudio$2$realSaveAudio$1.1
                        public final /* synthetic */ CancellableContinuation<Object> e;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                            TtsResult ttsResult2 = ttsResult;
                            TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                            Intrinsics.checkNotNullParameter(ttsResult2, "");
                            Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                            Objects.toString(ttsResult2);
                            textToAudioInfo2.f59067a.size();
                            List list2 = null;
                            TextToAudioInfoPack textToAudioInfoPack = new TextToAudioInfoPack(list2, list2, list2, list2, 63);
                            textToAudioInfoPack.b.addAll(textToAudioInfo2.b);
                            textToAudioInfoPack.f87042d.addAll(textToAudioInfo2.f59068c);
                            textToAudioInfoPack.f87040a.addAll(textToAudioInfo2.f59067a);
                            textToAudioInfoPack.f87041c.addAll(textToAudioInfo2.f59069d);
                            textToAudioInfoPack.f.addAll(textToAudioInfo2.f59070g);
                            CancellableContinuation<Object> cancellableContinuation = this.e;
                            CloneToneTextAudioManager.WrapTextToAudioResult wrapTextToAudioResult = new CloneToneTextAudioManager.WrapTextToAudioResult(textToAudioInfoPack, ttsResult2);
                            kotlin.Result.m17090constructorimpl(wrapTextToAudioResult);
                            cancellableContinuation.resumeWith(wrapTextToAudioResult);
                            return Unit.INSTANCE;
                        }
                    }, null, null, mockToneInfo, null, false, null, Boolean.valueOf(toneType.isAICloneTone()), toneType.isV3ModelTone(), false, false, false, null, null, null, null, false, -407818719, 31));
                    return Unit.INSTANCE;
                }
            };
            if (toneType.isAICloneTone()) {
                ICloneToneBusiness.DefaultImpls.a(this.f, IVoiceCloneApplyFunction.Scene.f131712a, list, toneType.getName(), false, false, null, false, new Function2<Integer, Result, Unit>() { // from class: com.vega.audio.tone.manager.CloneToneTextAudioManager$generateCloneToneAudio$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(Integer num, Result result) {
                        final int iIntValue = num.intValue();
                        final Result result2 = result;
                        Intrinsics.checkNotNullParameter(result2, "");
                        if (result2.f131803a == -8) {
                            CancellableContinuation<Object> cancellableContinuation = cancellableContinuationImpl;
                            kotlin.Result.m17090constructorimpl(3002);
                            cancellableContinuation.resumeWith(3002);
                        } else {
                            final Function2<Integer, Result, Unit> function22 = function2;
                            final Function0<Unit> function02 = function0;
                            result2.e(new Function1<Result, Unit>() { // from class: com.vega.audio.tone.manager.CloneToneTextAudioManager$generateCloneToneAudio$2$1.1
                                /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super com.vega.subscriptionapi.core.Result, kotlin.Unit> */
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(1);
                                }

                                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                @Override // kotlin.jvm.functions.Function1
                                public final Unit invoke(Result result3) {
                                    Intrinsics.checkNotNullParameter(result3, "");
                                    function22.invoke(Integer.valueOf(iIntValue), result2);
                                    function02.invoke();
                                    return Unit.INSTANCE;
                                }
                            });
                            final CancellableContinuation<Object> cancellableContinuation2 = cancellableContinuationImpl;
                            result2.d(new Function1<Result, Unit>() { // from class: com.vega.audio.tone.manager.CloneToneTextAudioManager$generateCloneToneAudio$2$1.2
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(1);
                                }

                                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                @Override // kotlin.jvm.functions.Function1
                                public final Unit invoke(Result result3) {
                                    Intrinsics.checkNotNullParameter(result3, "");
                                    CancellableContinuation<Object> cancellableContinuation3 = cancellableContinuation2;
                                    kotlin.Result.m17090constructorimpl(-1001);
                                    cancellableContinuation3.resumeWith(-1001);
                                    return Unit.INSTANCE;
                                }
                            });
                        }
                        return Unit.INSTANCE;
                    }
                }, 224);
            } else {
                function0.invoke();
            }
            cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.vega.audio.tone.manager.CloneToneTextAudioManager$generateCloneToneAudio$2$2
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Throwable th) {
                    TextToSpeechTaskManager.f74281a.a("");
                    return Unit.INSTANCE;
                }
            });
        } else {
            Integer numBoxInt = Boxing.boxInt(-1001);
            kotlin.Result.m17090constructorimpl(numBoxInt);
            cancellableContinuationImpl.resumeWith(numBoxInt);
        }
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX WARN: Removed duplicated region for block: B:119:0x027c  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x01ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(com.vega.audio.tone.manager.CloneToneTextAudioTask r24, kotlin.coroutines.Continuation<? super kotlin.Unit> r25) {
        /*
            r23 = this;
            r3 = r25
            r6 = r24
            boolean r0 = r3 instanceof com.vega.audio.tone.manager.CloneToneTextAudioManager$process$1
            r7 = r23
            if (r0 == 0) goto L27c
            r9 = r3
            com.vega.audio.tone.manager.CloneToneTextAudioManager$process$1 r9 = (com.vega.audio.tone.manager.CloneToneTextAudioManager$process$1) r9
            int r2 = r9.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L27c
            int r2 = r2 - r1
            r9.t = r2
        L18:
            java.lang.Object r4 = r9.r
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r9.t
            r1 = 2
            java.lang.String r2 = "CloneToneTextAudioManager"
            r5 = 0
            r10 = 3
            r15 = 0
            java.lang.String r8 = ""
            r11 = 1
            if (r0 == 0) goto L37
            if (r0 == r11) goto L177
            if (r0 == r1) goto L177
            if (r0 != r10) goto L283
            kotlin.ResultKt.throwOnFailure(r4)
        L34:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L37:
            kotlin.ResultKt.throwOnFailure(r4)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r0 = "processing: "
            r4.<init>(r0)
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = r6.f74149d
            r4.append(r0)
            java.lang.String r0 = r4.toString()
            com.vega.log.BLog.i(r2, r0)
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = r6.f74149d
            int r0 = r0.ordinal()
            if (r0 == 0) goto L14d
            if (r0 == r11) goto Ldf
            if (r0 == r1) goto L71
            if (r0 == r10) goto L5e
        L5b:
            r4 = 1
            goto L17d
        L5e:
            java.util.List<com.vega.editorapi.bean.TextAudioData> r0 = r6.l
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L5b
            r6.e = r15
            r0 = 4000(0xfa0, float:5.605E-42)
            r6.f = r0
            java.lang.String r0 = "textAudioData is empty"
            r6.f74150g = r0
            goto L5b
        L71:
            r9.q = r6
            r9.t = r1
            kotlinx.coroutines.CancellableContinuationImpl r1 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r9)
            r1.<init>(r0, r11)
            r1.initCancellability()
            com.service.data.SplitTextInfo r0 = r6.h
            if (r0 == 0) goto Lcb
            java.util.List<java.lang.String> r11 = r0.f64890c
            if (r11 == 0) goto Lcb
            boolean r0 = r11.isEmpty()
            if (r0 != 0) goto Lcb
            kotlinx.coroutines.CoroutineScope r0 = r7.h
            if (r0 == 0) goto Ldd
            com.vega.audio.tone.manager.CloneToneTextAudioManager$doTts$2$1 r16 = new com.vega.audio.tone.manager.CloneToneTextAudioManager$doTts$2$1
            r4 = 1
            r20 = r1
            r21 = r5
            r18 = r6
            r19 = r11
            r17 = r7
            r16.<init>(r17, r18, r19, r20, r21)
            r17 = r0
            r18 = r5
            r19 = r5
            r20 = r16
            r21 = r10
            r22 = r5
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r17, r18, r19, r20, r21, r22)
        Lb2:
            java.lang.Object r1 = r1.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto Lbf
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r9)
        Lbf:
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto Lc8
        Lc5:
            if (r1 != r3) goto L17d
            return r3
        Lc8:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto Lc5
        Lcb:
            r6.e = r15
            r0 = 3000(0xbb8, float:4.204E-42)
            r6.f = r0
            java.lang.String r0 = "split text list is empty"
            r6.f74150g = r0
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.Result.m17090constructorimpl(r0)
            r1.resumeWith(r0)
        Ldd:
            r4 = 1
            goto Lb2
        Ldf:
            r4 = 1
            r9.q = r6
            r9.t = r11
            kotlinx.coroutines.CancellableContinuationImpl r1 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r9)
            r1.<init>(r0, r11)
            r1.initCancellability()
            java.lang.String r14 = r6.f74147a
            kotlinx.coroutines.CoroutineScope r13 = r7.h
            if (r13 == 0) goto L120
            com.vega.costreport.aigc.BabiUtil r0 = com.vega.costreport.aigc.BabiUtil.f79727a
            r0.getClass()
            java.lang.String r0 = "clone_tone"
            com.vega.costreport.aigc.BabiParams r0 = com.vega.costreport.aigc.BabiUtil.W(r0)
            if (r0 == 0) goto L14b
            java.lang.String r12 = com.vega.core.ext.ExtentionKt.toJson(r0)
        L107:
            com.vega.core.context.SPIService r11 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.service.ISplitTextService> r0 = com.service.ISplitTextService.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.Object r11 = r11.getImpl(r0, r5)
            com.service.ISplitTextService r11 = (com.service.ISplitTextService) r11
            com.vega.audio.tone.manager.CloneToneTextAudioManager$doSplitText$2$1$1 r0 = new com.vega.audio.tone.manager.CloneToneTextAudioManager$doSplitText$2$1$1
            r0.<init>()
            kotlinx.coroutines.Job r0 = r11.b(r14, r12, r0, r13)
            if (r0 != 0) goto L132
        L120:
            r6.e = r15
            r0 = -1000(0xfffffffffffffc18, float:NaN)
            r6.f = r0
            java.lang.String r0 = "executeScope error"
            r6.f74150g = r0
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            kotlin.Result.m17090constructorimpl(r0)
            r1.resumeWith(r0)
        L132:
            java.lang.Object r1 = r1.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto L13f
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r9)
        L13f:
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto L148
        L145:
            if (r1 != r3) goto L17d
            return r3
        L148:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L145
        L14b:
            r12 = r5
            goto L107
        L14d:
            r4 = 1
            java.lang.String r0 = r6.f74147a
            int r0 = r0.length()
            if (r0 != 0) goto L160
            r6.e = r15
            r0 = 1000(0x3e8, float:1.401E-42)
            r6.f = r0
            java.lang.String r0 = "input text is empty"
            r6.f74150g = r0
        L160:
            com.lemon.lv.data.ToneType r0 = r6.b
            java.lang.String r0 = r0.getVoiceType()
            int r0 = r0.length()
            if (r0 != 0) goto L17d
            r6.e = r15
            r0 = 1001(0x3e9, float:1.403E-42)
            r6.f = r0
            java.lang.String r0 = "ToneType VoiceType invalid"
            r6.f74150g = r0
            goto L17d
        L177:
            com.vega.audio.tone.manager.CloneToneTextAudioTask r6 = r9.q
            kotlin.ResultKt.throwOnFailure(r4)
            r4 = 1
        L17d:
            r9.q = r5
            r9.t = r10
            boolean r0 = r6.e
            if (r0 != 0) goto L209
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "task fail: ["
            r1.<init>(r0)
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = r6.f74149d
            r1.append(r0)
            java.lang.String r0 = "] "
            r1.append(r0)
            java.lang.String r0 = r6.f74150g
            r1.append(r0)
            java.lang.String r0 = ", useCommercial: "
            r1.append(r0)
            boolean r0 = r6.i
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r2, r0)
            kotlin.jvm.functions.Function2<? super java.lang.Integer, ? super java.lang.String, kotlin.Unit> r2 = r7.e
            if (r2 == 0) goto L1bb
            int r0 = r6.f
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = r6.f74150g
            r2.invoke(r1, r0)
        L1bb:
            boolean r0 = r6.i
            if (r0 == 0) goto L1ce
            com.vega.subscriptionapi.biz.function.ICloneToneBusiness r2 = r7.f
            int r1 = r6.j
            com.vega.subscriptionapi.core.Result r0 = r6.k
            if (r0 == 0) goto L207
            com.vega.subscriptionapi.biz.data.CheckData r0 = com.vega.subscriptionapi.ext.ResultExKt.d(r0)
        L1cb:
            r2.k(r15, r1, r0)
        L1ce:
            com.vega.aigcapi.materialgenerate.TtsResult r0 = r6.m
            if (r0 == 0) goto L205
            java.lang.String r1 = r0.f69164c
        L1d4:
            java.lang.String r0 = "1062"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 != 0) goto L1ea
            com.vega.aigcapi.materialgenerate.TtsResult r0 = r6.m
            if (r0 == 0) goto L1e2
            java.lang.String r5 = r0.f69164c
        L1e2:
            java.lang.String r0 = "231050"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r0 == 0) goto L200
        L1ea:
            r14 = 2131896878(0x7f122a2e, float:1.942863E38)
            r19 = 254(0xfe, float:3.56E-43)
            r16 = r15
            r17 = r15
            r18 = r15
            com.vega.util.ToastUtilKt.d(r14, r15, r16, r17, r18, r19)
            com.vega.audio.tone.clonetone.statistics.CloneToneStatistics r0 = com.vega.audio.tone.clonetone.statistics.CloneToneStatistics.f74107a
            r0.getClass()
            com.vega.audio.tone.clonetone.statistics.CloneToneStatistics.q(r15)
        L200:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
        L202:
            if (r1 != r3) goto L34
            return r3
        L205:
            r1 = r5
            goto L1d4
        L207:
            r0 = r5
            goto L1cb
        L209:
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = r6.f74149d
            int r1 = r0.ordinal()
            if (r1 == 0) goto L233
            if (r1 == r4) goto L22b
            r0 = 2
            if (r1 == r0) goto L223
            if (r1 == r10) goto L23e
        L218:
            java.lang.Object r1 = r7.d(r6, r9)
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto L23b
            goto L202
        L223:
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = com.vega.audio.tone.manager.CloneToneTextAudioTask.State.f74153d
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r8)
            r6.f74149d = r0
            goto L218
        L22b:
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = com.vega.audio.tone.manager.CloneToneTextAudioTask.State.f74152c
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r8)
            r6.f74149d = r0
            goto L218
        L233:
            com.vega.audio.tone.manager.CloneToneTextAudioTask$State r0 = com.vega.audio.tone.manager.CloneToneTextAudioTask.State.b
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r8)
            r6.f74149d = r0
            goto L218
        L23b:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L202
        L23e:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "onTaskFinish commercial:"
            r1.<init>(r0)
            boolean r0 = r6.i
            r1.append(r0)
            r0 = 32
            r1.append(r0)
            int r0 = r6.j
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r2, r0)
            boolean r0 = r6.i
            if (r0 == 0) goto L270
            int r2 = r6.j
            if (r2 <= 0) goto L270
            com.vega.subscriptionapi.biz.function.ICloneToneBusiness r1 = r7.f
            com.vega.subscriptionapi.core.Result r0 = r6.k
            if (r0 == 0) goto L26d
            com.vega.subscriptionapi.biz.data.CheckData r5 = com.vega.subscriptionapi.ext.ResultExKt.d(r0)
        L26d:
            r1.k(r4, r2, r5)
        L270:
            kotlin.jvm.functions.Function1<? super java.util.List<com.vega.editorapi.bean.TextAudioData>, kotlin.Unit> r1 = r7.f74140c
            if (r1 == 0) goto L279
            java.util.List<com.vega.editorapi.bean.TextAudioData> r0 = r6.l
            r1.invoke(r0)
        L279:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L202
        L27c:
            com.vega.audio.tone.manager.CloneToneTextAudioManager$process$1 r9 = new com.vega.audio.tone.manager.CloneToneTextAudioManager$process$1
            r9.<init>(r7, r3)
            goto L18
        L283:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.manager.CloneToneTextAudioManager.d(com.vega.audio.tone.manager.CloneToneTextAudioTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void e(CloneToneTextAudioTask cloneToneTextAudioTask) {
        Intrinsics.checkNotNullParameter(cloneToneTextAudioTask, "");
        CloneToneTextAudioTask cloneToneTextAudioTask2 = this.b;
        if (Intrinsics.areEqual(cloneToneTextAudioTask2 != null ? cloneToneTextAudioTask2.f74148c : null, cloneToneTextAudioTask.f74148c)) {
            BLog.e("CloneToneTextAudioManager", "duplicate submit task");
            return;
        }
        Job job = this.f74142g;
        if (job != null && job.isActive()) {
            a();
        }
        this.b = cloneToneTextAudioTask;
        this.f74142g = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new CloneToneTextAudioManager$submitTask$1(this, null), 3, null);
    }
}
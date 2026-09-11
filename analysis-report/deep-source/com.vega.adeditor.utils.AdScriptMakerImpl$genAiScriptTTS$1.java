package com.vega.adeditor.utils;

import com.lemon.lv.data.ScriptList;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.core.context.SPIService;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.infrastructure.vm.ViewModelActivity;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.ui.dialog.LvProgressDialog;
import java.util.LinkedHashMap;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1", f = "AdScriptMakerImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes22.dex */
public final class AdScriptMakerImpl$genAiScriptTTS$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String q;
    public final /* synthetic */ ToneType r;
    public final /* synthetic */ String s;
    public final /* synthetic */ AdScriptMakerImpl t;
    public final /* synthetic */ ViewModelActivity u;
    public final /* synthetic */ ScriptList v;
    public final /* synthetic */ Function0<Unit> w;
    public final /* synthetic */ LvProgressDialog x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdScriptMakerImpl$genAiScriptTTS$1(String str, ToneType toneType, String str2, AdScriptMakerImpl adScriptMakerImpl, ViewModelActivity viewModelActivity, ScriptList scriptList, Function0<Unit> function0, LvProgressDialog lvProgressDialog, Continuation<? super AdScriptMakerImpl$genAiScriptTTS$1> continuation) {
        super(2, continuation);
        this.q = str;
        this.r = toneType;
        this.s = str2;
        this.t = adScriptMakerImpl;
        this.u = viewModelActivity;
        this.v = scriptList;
        this.w = function0;
        this.x = lvProgressDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AdScriptMakerImpl$genAiScriptTTS$1(this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("adType", this.q);
        linkedHashMap.put("effectId", this.r.getEffectId());
        TextInfo noSegTextList = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() ? new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(this.s)) : new TextInfo.AutoSegText(this.s);
        TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(TextToSpeechReportScene.AI_SCRIPT, null, noSegTextList.getText().length(), false, false, 0L, null, null, null, null, 994, null);
        TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
        String voiceType = this.r.getVoiceType();
        String platform = this.r.getPlatform();
        TTSBusinessType tTSBusinessType = TTSBusinessType.b;
        String json = textToSpeechReportInfo.toJson();
        String auditionText = this.r.getAuditionText();
        String toneModelType = this.r.getToneModelType();
        final AdScriptMakerImpl adScriptMakerImpl = this.t;
        final ViewModelActivity viewModelActivity = this.u;
        final ToneType toneType = this.r;
        final ScriptList scriptList = this.v;
        final Function0<Unit> function0 = this.w;
        final LvProgressDialog lvProgressDialog = this.x;
        textToSpeechTaskManager.c(new TextToSpeechIntent(null, noSegTextList, voiceType, platform, "AdScriptMakerImpl", null, tTSBusinessType, "ads_edit", "smart_ad", 0.0f, 0, null, linkedHashMap, false, json, auditionText, false, toneModelType, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v10, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v11, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v12, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v3, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v4, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v5, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v6, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v7, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v8, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r25v9, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:196:0x059a  */
            /* JADX WARN: Removed duplicated region for block: B:75:0x0322  */
            /* JADX WARN: Removed duplicated region for block: B:79:0x032f  */
            @Override // kotlin.jvm.functions.Function2
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final kotlin.Unit invoke(com.vega.aigcapi.materialgenerate.TtsResult r52, com.lemon.lv.data.TextToAudioInfo r53) {
                /*
                    r51 = this;
                    r1 = r53
                    r0 = r52
                    com.vega.aigcapi.materialgenerate.TtsResult r0 = (com.vega.aigcapi.materialgenerate.TtsResult) r0
                    com.lemon.lv.data.TextToAudioInfo r1 = (com.lemon.lv.data.TextToAudioInfo) r1
                    java.lang.String r2 = ""
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
                    com.vega.aigcapi.materialgenerate.StatusResult r4 = r0.f69163a
                    r7 = r51
                    com.vega.adeditor.utils.AdScriptMakerImpl r0 = r2
                    boolean r0 = r0.e
                    if (r0 == 0) goto L1d
                L1a:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                L1d:
                    java.util.List<java.lang.String> r10 = r1.b
                    java.util.List<java.lang.String> r0 = r1.f59067a
                    r50 = r0
                    com.vega.aigcapi.materialgenerate.StatusResult r3 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    r0 = 0
                    if (r4 != r3) goto L5b4
                    boolean r3 = r10.isEmpty()
                    r3 = r3 ^ 1
                    if (r3 == 0) goto L5b4
                    int r4 = r50.size()
                    int r3 = r10.size()
                    if (r4 != r3) goto L5b4
                    com.vega.infrastructure.vm.ViewModelActivity r5 = r3
                    kotlin.LazyThreadSafetyMode r4 = kotlin.LazyThreadSafetyMode.NONE
                    com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1$1$invoke$$inlined$factoryViewModel$1 r3 = new com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1$1$invoke$$inlined$factoryViewModel$1
                    r3.<init>()
                    kotlin.Lazy r4 = kotlin.LazyKt__LazyJVMKt.lazy(r4, r3)
                    com.vega.adeditor.utils.AdScriptMakerImpl r3 = r2
                    r49 = r3
                    java.lang.Object r6 = r4.getValue()
                    com.vega.libsticker.viewmodel.SubtitleViewModel r6 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r6
                    com.lemon.lv.data.ToneType r3 = r4
                    r48 = r3
                    com.vega.core.context.SPIService r5 = com.vega.core.context.SPIService.INSTANCE
                    java.lang.Class<com.lemon.lv.config.ClientSetting> r3 = com.lemon.lv.config.ClientSetting.class
                    kotlin.reflect.KClass r4 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
                    r3 = 0
                    java.lang.Object r3 = r5.getImpl(r4, r3)
                    com.lemon.lv.config.BaseClientSetting r3 = (com.lemon.lv.config.BaseClientSetting) r3
                    com.lemon.lv.config.AutoCaptionsConfig r3 = r3.getAutoCaptionsConfig()
                    java.util.List r4 = r3.a()
                    r3 = 0
                    java.lang.Object r5 = r4.get(r3)
                    com.lemon.lv.config.LanguageItem r5 = (com.lemon.lv.config.LanguageItem) r5
                    java.util.List r39 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
                    com.vega.middlebridge.swig.LVVESubtitleType r37 = com.vega.middlebridge.swig.LVVESubtitleType.SubTitleAiScript
                    com.lemon.lv.data.ScriptList r14 = r5
                    com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1$1$1 r13 = new com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1$1$1
                    kotlin.jvm.functions.Function0<kotlin.Unit> r4 = r6
                    com.vega.ui.dialog.LvProgressDialog r3 = r7
                    r13.<init>()
                    r49.getClass()
                    com.vega.container.session.core.ISession r3 = r6.h
                    r47 = r3
                    boolean r3 = r47.a0()
                    if (r3 == 0) goto L98
                    java.lang.Boolean r0 = java.lang.Boolean.FALSE
                    r13.invoke(r0)
                    goto L1a
                L98:
                    r3 = 1000(0x3e8, float:1.401E-42)
                    long r3 = (long) r3
                    long r0 = r0 / r3
                    java.util.ArrayList r11 = new java.util.ArrayList
                    r11.<init>()
                    java.util.ArrayList r12 = new java.util.ArrayList
                    r12.<init>()
                    java.util.Iterator r18 = r50.iterator()
                    r8 = 0
                Lab:
                    boolean r3 = r18.hasNext()
                    if (r3 == 0) goto L10c
                    java.lang.Object r4 = r18.next()
                    int r17 = r8 + 1
                    if (r8 >= 0) goto Lbc
                    kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
                Lbc:
                    java.lang.String r4 = (java.lang.String) r4
                    com.vega.ve.utils.MediaUtil r3 = com.vega.ve.utils.MediaUtil.f135467a
                    r3.getClass()
                    com.vega.ve.data.AudioMetaDataInfo r7 = com.vega.ve.utils.MediaUtil.c(r4)
                    int r3 = r7.f135305a
                    long r3 = (long) r3
                    r15 = 1000(0x3e8, double:4.94E-321)
                    long r3 = r3 * r15
                    java.lang.Long r3 = java.lang.Long.valueOf(r3)
                    r11.add(r3)
                    com.vega.operation.bean.Sentence r9 = new com.vega.operation.bean.Sentence
                    java.lang.Object r8 = r10.get(r8)
                    java.lang.String r8 = (java.lang.String) r8
                    int r3 = r7.f135305a
                    long r3 = (long) r3
                    long r23 = r0 + r3
                    r25 = 0
                    r35 = 8176(0x1ff0, float:1.1457E-41)
                    r21 = r0
                    r26 = r25
                    r27 = r25
                    r28 = r25
                    r29 = r25
                    r30 = r25
                    r31 = r25
                    r32 = r25
                    r33 = r25
                    r34 = r25
                    r36 = r25
                    r19 = r9
                    r20 = r8
                    r19.<init>(r20, r21, r23, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36)
                    r12.add(r9)
                    int r3 = r7.f135305a
                    long r3 = (long) r3
                    long r0 = r0 + r3
                    r8 = r17
                    goto Lab
                L10c:
                    com.vega.middlebridge.swig.LVVEMetaType r38 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeSubtitle
                    if (r5 == 0) goto L138
                    java.lang.String r40 = r5.b()
                L114:
                    r41 = 0
                    r28 = 0
                    r42 = 1
                    r15 = 0
                    r46 = 122880(0x1e000, float:1.72192E-40)
                    r35 = r6
                    r36 = r12
                    r43 = r15
                    r44 = r41
                    r45 = r41
                    com.vega.middlebridge.swig.EditResult r0 = com.vega.libsticker.viewmodel.SubtitleViewModel.n6(r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46)
                    if (r0 != 0) goto L13b
                    r47.w0()
                    java.lang.Boolean r0 = java.lang.Boolean.TRUE
                    r13.invoke(r0)
                    goto L1a
                L138:
                    r40 = 0
                    goto L114
                L13b:
                    com.vega.middlebridge.swig.VectorNodes r3 = r0.d()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
                    java.util.ArrayList r1 = new java.util.ArrayList
                    r0 = 10
                    int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, r0)
                    r1.<init>(r0)
                    java.util.Iterator r4 = r3.iterator()
                L151:
                    boolean r0 = r4.hasNext()
                    if (r0 == 0) goto L16e
                    java.lang.Object r0 = r4.next()
                    com.vega.middlebridge.swig.ChangedNode r0 = (com.vega.middlebridge.swig.ChangedNode) r0
                    java.lang.String r3 = r0.a()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
                    r0 = r47
                    com.vega.middlebridge.swig.Segment r0 = r0.i(r3)
                    r1.add(r0)
                    goto L151
                L16e:
                    com.service.AiScriptInfo r12 = new com.service.AiScriptInfo
                    java.lang.String r4 = r14.i()
                    java.lang.String r5 = r14.f()
                    java.lang.Long r0 = r14.e()
                    if (r0 == 0) goto L184
                    java.lang.String r6 = r0.toString()
                    if (r6 != 0) goto L185
                L184:
                    r6 = r2
                L185:
                    java.util.Map r3 = r49.f()
                    int r0 = r14.c()
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    java.util.LinkedHashMap r3 = (java.util.LinkedHashMap) r3
                    java.lang.Object r0 = r3.get(r0)
                    com.service.AiScriptLanguageItem r0 = (com.service.AiScriptLanguageItem) r0
                    if (r0 == 0) goto L19f
                    java.lang.String r0 = r0.f64830c
                    if (r0 != 0) goto L1a0
                L19f:
                    r0 = r2
                L1a0:
                    r8 = 16
                    r3 = r12
                    r7 = r0
                    r3.<init>(r4, r5, r6, r7, r8)
                    java.util.ArrayList r27 = new java.util.ArrayList
                    r27.<init>()
                    java.util.ArrayList r9 = new java.util.ArrayList
                    r9.<init>()
                    java.util.ArrayList r26 = new java.util.ArrayList
                    r26.<init>()
                    int r7 = r50.size()
                    java.util.Iterator r17 = r1.iterator()
                    r8 = 0
                L1bf:
                    boolean r0 = r17.hasNext()
                    if (r0 == 0) goto L30f
                    int r16 = r8 + 1
                    java.lang.Object r3 = r17.next()
                    com.vega.middlebridge.swig.Segment r3 = (com.vega.middlebridge.swig.Segment) r3
                    r0 = r50
                    java.lang.Object r6 = r0.get(r8)
                    java.lang.String r6 = (java.lang.String) r6
                    boolean r0 = r3 instanceof com.vega.middlebridge.swig.SegmentText
                    if (r0 == 0) goto L30b
                    r10 = r3
                    com.vega.middlebridge.swig.SegmentText r10 = (com.vega.middlebridge.swig.SegmentText) r10
                L1dc:
                    int r0 = r6.length()
                    if (r0 != 0) goto L1e5
                L1e2:
                    r8 = r16
                    goto L1bf
                L1e5:
                    if (r8 >= r7) goto L1e2
                    if (r10 != 0) goto L1ea
                    goto L1e2
                L1ea:
                    java.util.List<com.service.AiScriptSegmentInfo> r5 = r12.e
                    com.service.AiScriptSegmentInfo r4 = new com.service.AiScriptSegmentInfo
                    java.lang.String r1 = r10.b()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
                    com.vega.middlebridge.swig.MaterialText r0 = r10.u()
                    if (r0 == 0) goto L2b0
                    java.lang.String r0 = r0.a0()
                L1ff:
                    if (r0 != 0) goto L202
                    r0 = r2
                L202:
                    r4.<init>(r1, r0)
                    r5.add(r4)
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r14 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange
                    com.vega.middlebridge.swig.TimeRange r0 = r10.k()
                    long r4 = r0.e()
                    java.lang.Object r0 = r11.get(r8)
                    java.lang.Number r0 = (java.lang.Number) r0
                    long r0 = r0.longValue()
                    r14.<init>(r4, r0)
                    r0 = r26
                    r0.add(r14)
                    com.vega.middlebridge.swig.AddTextAudioParam r5 = new com.vega.middlebridge.swig.AddTextAudioParam
                    r5.<init>()
                    com.vega.middlebridge.swig.AddAudioParam r4 = r5.d()
                    r4.m(r6)
                    java.lang.String r0 = r48.getToneName()
                    r5.u(r0)
                    java.lang.String r0 = r48.getPlatform()
                    r5.q(r0)
                    java.lang.String r0 = r48.getToneName()
                    r5.p(r0)
                    java.lang.String r0 = r48.getResourceId()
                    r4.v(r0)
                    java.lang.String r0 = r48.getEffectId()
                    r5.o(r0)
                    java.lang.String r0 = r48.getCategoryID()
                    r5.m(r0)
                    java.lang.String r0 = r48.getCategoryName()
                    r5.n(r0)
                    com.vega.middlebridge.swig.MaterialText r0 = r10.u()
                    if (r0 == 0) goto L26d
                    java.lang.String r0 = r0.a0()
                    if (r0 != 0) goto L26e
                L26d:
                    r0 = r2
                L26e:
                    r4.n(r0)
                    com.vega.middlebridge.swig.TimeRange r0 = r10.k()
                    long r0 = r0.e()
                    r4.B(r0)
                    r0 = 0
                    r4.A(r0)
                    java.lang.Object r0 = r11.get(r8)
                    java.lang.Number r0 = (java.lang.Number) r0
                    long r0 = r0.longValue()
                    r4.q(r0)
                    com.vega.ve.utils.AudioWaveUtils r8 = com.vega.ve.utils.AudioWaveUtils.f135449a
                    long r0 = r4.e()
                    r8.getClass()
                    float[] r14 = com.vega.ve.utils.AudioWaveUtils.a(r0, r6)
                    int r10 = r14.length
                    r8 = 0
                L29d:
                    if (r8 >= r10) goto L2b4
                    r0 = r14[r8]
                    com.vega.middlebridge.swig.VectorOfDouble r6 = r4.h()
                    double r0 = (double) r0
                    java.lang.Double r0 = java.lang.Double.valueOf(r0)
                    r6.a(r0)
                    int r8 = r8 + 1
                    goto L29d
                L2b0:
                    r0 = r28
                    goto L1ff
                L2b4:
                    com.vega.middlebridge.swig.LVVEMetaType r0 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
                    r4.D(r0)
                    java.lang.String r0 = r3.b()
                    r5.l(r0)
                    r5.j(r15)
                    java.lang.String r0 = r48.getToneName()
                    r5.u(r0)
                    java.lang.String r0 = r48.getPlatform()
                    r5.q(r0)
                    java.lang.String r0 = r48.getToneName()
                    r5.p(r0)
                    java.lang.String r0 = r48.getResourceId()
                    r5.i(r0)
                    java.lang.String r0 = r48.getEffectId()
                    r5.o(r0)
                    java.lang.String r0 = r48.getCategoryID()
                    r5.m(r0)
                    java.lang.String r0 = r48.getCategoryName()
                    r5.n(r0)
                    com.vega.middlebridge.swig.MapOfStringString r4 = r5.b()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r2)
                    java.lang.String r1 = "TEXT_SEGMENT_ID"
                    java.lang.String r0 = r3.b()
                    r4.put(r1, r0)
                    r0 = r27
                    r0.add(r5)
                    goto L1e2
                L30b:
                    r10 = r28
                    goto L1dc
                L30f:
                    com.vega.middlebridge.swig.Draft r1 = r47.l()
                    if (r1 == 0) goto L322
                    java.util.Set r4 = kotlin.collections.SetsKt__SetsKt.emptySet()
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)
                    boolean r0 = r26.isEmpty()
                    if (r0 == 0) goto L382
                L322:
                    r6 = r28
                L324:
                    java.util.Iterator r5 = r27.iterator()
                    r0 = 0
                L329:
                    boolean r1 = r5.hasNext()
                    if (r1 == 0) goto L582
                    java.lang.Object r1 = r5.next()
                    int r4 = r0 + 1
                    if (r0 >= 0) goto L33a
                    kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
                L33a:
                    com.vega.middlebridge.swig.AddTextAudioParam r1 = (com.vega.middlebridge.swig.AddTextAudioParam) r1
                    com.vega.middlebridge.swig.IQueryUtils r21 = r47.m()
                    if (r21 == 0) goto L380
                    com.vega.middlebridge.swig.LVVETrackType r2 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeAudio
                    java.util.List r22 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r2)
                    com.vega.middlebridge.swig.AddAudioParam r2 = r1.d()
                    long r23 = r2.g()
                    com.vega.middlebridge.swig.AddAudioParam r2 = r1.d()
                    long r25 = r2.e()
                    r29 = 24
                    r27 = r15
                    int r3 = com.vega.ve.utils.IQueryUtilsExKt.b(r21, r22, r23, r25, r27, r28, r29)
                L360:
                    com.vega.middlebridge.swig.AddAudioParam r2 = r1.d()
                    if (r6 == 0) goto L370
                    java.lang.Object r0 = r6.get(r0)
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.InsertInfo) r0
                    if (r0 == 0) goto L370
                    int r3 = r0.f74648a
                L370:
                    r2.C(r3)
                    com.vega.middlebridge.swig.AddTextAudioReqStruct r0 = new com.vega.middlebridge.swig.AddTextAudioReqStruct
                    r0.<init>()
                    r0.setParams(r1)
                    r9.add(r0)
                    r0 = r4
                    goto L329
                L380:
                    r3 = -1
                    goto L360
                L382:
                    com.vega.middlebridge.swig.VectorOfTrack r0 = r1.v()
                    java.util.ArrayList r5 = new java.util.ArrayList
                    r5.<init>()
                    java.util.Iterator r3 = r0.iterator()
                L38f:
                    boolean r0 = r3.hasNext()
                    if (r0 == 0) goto L3a8
                    java.lang.Object r2 = r3.next()
                    r0 = r2
                    com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
                    com.vega.middlebridge.swig.LVVETrackType r1 = r0.f()
                    com.vega.middlebridge.swig.LVVETrackType r0 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeAudio
                    if (r1 != r0) goto L38f
                    r5.add(r2)
                    goto L38f
                L3a8:
                    java.util.LinkedHashSet r10 = new java.util.LinkedHashSet
                    r10.<init>()
                    java.util.Iterator r14 = r5.iterator()
                    r5 = 0
                L3b2:
                    boolean r0 = r14.hasNext()
                    if (r0 == 0) goto L451
                    java.lang.Object r1 = r14.next()
                    int r11 = r5 + 1
                    if (r5 >= 0) goto L3c3
                    kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
                L3c3:
                    com.vega.middlebridge.swig.Track r1 = (com.vega.middlebridge.swig.Track) r1
                    com.vega.middlebridge.swig.VectorOfSegment r0 = r1.e()
                    boolean r0 = r0.isEmpty()
                    if (r0 == 0) goto L3de
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r1 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo
                    java.util.ArrayList r0 = new java.util.ArrayList
                    r0.<init>()
                    r1.<init>(r5, r0)
                    r10.add(r1)
                L3dc:
                    r5 = r11
                    goto L3b2
                L3de:
                    com.vega.middlebridge.swig.VectorOfSegment r0 = r1.e()
                    java.util.ArrayList r3 = new java.util.ArrayList
                    r3.<init>()
                    java.util.Iterator r2 = r0.iterator()
                L3eb:
                    boolean r0 = r2.hasNext()
                    if (r0 == 0) goto L408
                    java.lang.Object r1 = r2.next()
                    r0 = r1
                    com.vega.middlebridge.swig.Node r0 = (com.vega.middlebridge.swig.Node) r0
                    java.lang.String r0 = r0.b()
                    boolean r0 = r4.contains(r0)
                    r0 = r0 ^ 1
                    if (r0 == 0) goto L3eb
                    r3.add(r1)
                    goto L3eb
                L408:
                    java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r3)
                    if (r1 == 0) goto L3dc
                    java.util.ArrayList r7 = new java.util.ArrayList
                    r0 = 10
                    int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, r0)
                    r7.<init>(r0)
                    java.util.Iterator r8 = r1.iterator()
                L41d:
                    boolean r0 = r8.hasNext()
                    if (r0 == 0) goto L442
                    java.lang.Object r1 = r8.next()
                    com.vega.middlebridge.swig.Segment r1 = (com.vega.middlebridge.swig.Segment) r1
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r6 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange
                    com.vega.middlebridge.swig.TimeRange r0 = r1.k()
                    long r2 = r0.e()
                    com.vega.middlebridge.swig.TimeRange r0 = r1.k()
                    long r0 = r0.d()
                    r6.<init>(r2, r0)
                    r7.add(r6)
                    goto L41d
                L442:
                    java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(r7)
                    if (r1 == 0) goto L3dc
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo
                    r0.<init>(r5, r1)
                    r10.add(r0)
                    goto L3dc
                L451:
                    java.util.ArrayList r6 = new java.util.ArrayList
                    r6.<init>()
                    java.util.Iterator r25 = r26.iterator()
                L45a:
                    boolean r0 = r25.hasNext()
                    if (r0 == 0) goto L575
                    java.lang.Object r14 = r25.next()
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r14 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.CMTimeRange) r14
                    boolean r0 = r10.isEmpty()
                    if (r0 == 0) goto L487
                    r2 = 0
                L46d:
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo
                    r0.<init>(r2)
                    r6.add(r0)
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r1 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo
                    r0 = 1
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange[] r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel.CMTimeRange[r0]
                    r0[r15] = r14
                    java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsKt.mutableListOf(r0)
                    r1.<init>(r2, r0)
                    r10.add(r1)
                    goto L45a
                L487:
                    java.util.Iterator r24 = r10.iterator()
                L48b:
                    boolean r0 = r24.hasNext()
                    if (r0 == 0) goto L524
                    java.lang.Object r0 = r24.next()
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.TrackInfo) r0
                    int r11 = r0.f74649a
                    java.util.List<com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange> r0 = r0.b
                    if (r0 == 0) goto L4cd
                    boolean r2 = r0.isEmpty()
                    r1 = 1
                    if (r2 != r1) goto L4cd
                L4a4:
                    java.lang.Integer r4 = java.lang.Integer.valueOf(r11)
                L4a8:
                    java.util.ArrayList r2 = new java.util.ArrayList
                    r0 = 10
                    int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r10, r0)
                    r2.<init>(r0)
                    java.util.Iterator r1 = r10.iterator()
                L4b7:
                    boolean r0 = r1.hasNext()
                    if (r0 == 0) goto L527
                    java.lang.Object r0 = r1.next()
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.TrackInfo) r0
                    int r0 = r0.f74649a
                    java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
                    r2.add(r0)
                    goto L4b7
                L4cd:
                    long r7 = r14.f74646a
                    long r4 = r14.b
                    long r4 = r4 + r7
                    if (r0 == 0) goto L4e3
                    int r2 = r0.size()
                    r1 = 1
                    if (r2 <= r1) goto L4e3
                    com.vega.adeditor.utils.AdScriptMakerImpl$canInsertThisTrack$$inlined$sortBy$1 r1 = new com.vega.adeditor.utils.AdScriptMakerImpl$canInsertThisTrack$$inlined$sortBy$1
                    r1.<init>()
                    kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r0, r1)
                L4e3:
                    r22 = -1
                    if (r0 == 0) goto L51f
                    java.util.Iterator r21 = r0.iterator()
                L4eb:
                    boolean r0 = r21.hasNext()
                    if (r0 == 0) goto L51f
                    java.lang.Object r0 = r21.next()
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.CMTimeRange) r0
                    long r2 = r0.f74646a
                    long r0 = r0.b
                    long r0 = r0 + r2
                    int r16 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
                    if (r16 > 0) goto L505
                    int r16 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    if (r16 >= 0) goto L505
                    goto L48b
                L505:
                    r19 = 1
                    long r17 = r7 + r19
                    int r16 = (r17 > r0 ? 1 : (r17 == r0 ? 0 : -1))
                    if (r16 > 0) goto L513
                    int r16 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
                    if (r16 > 0) goto L513
                    goto L48b
                L513:
                    int r16 = (r22 > r7 ? 1 : (r22 == r7 ? 0 : -1))
                    if (r16 > 0) goto L51c
                    int r16 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    if (r16 < 0) goto L51c
                    goto L4a4
                L51c:
                    r22 = r0
                    goto L4eb
                L51f:
                    int r0 = (r22 > r7 ? 1 : (r22 == r7 ? 0 : -1))
                    if (r0 > 0) goto L48b
                    goto L4a4
                L524:
                    r4 = r28
                    goto L4a8
                L527:
                    java.lang.Comparable r0 = kotlin.collections.CollectionsKt___CollectionsKt.maxOrNull(r2)
                    java.lang.Integer r0 = (java.lang.Integer) r0
                    if (r0 == 0) goto L56a
                    int r0 = r0.intValue()
                L533:
                    int r2 = r0 + 1
                    if (r4 == 0) goto L56e
                    r4.intValue()
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r1 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo
                    int r0 = r4.intValue()
                    r1.<init>(r0)
                    r6.add(r1)
                    java.util.Iterator r3 = r10.iterator()
                L54a:
                    boolean r0 = r3.hasNext()
                    if (r0 == 0) goto L5c0
                    java.lang.Object r2 = r3.next()
                    com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r2 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.TrackInfo) r2
                    int r1 = r2.f74649a
                    r0 = -1
                    if (r1 == r0) goto L54a
                    int r0 = r4.intValue()
                    if (r1 != r0) goto L54a
                    java.util.List<com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange> r0 = r2.b
                    if (r0 == 0) goto L45a
                    r0.add(r14)
                    goto L45a
                L56a:
                    r0 = 2147483647(0x7fffffff, float:NaN)
                    goto L533
                L56e:
                    r0 = 2147483647(0x7fffffff, float:NaN)
                    if (r2 > r0) goto L45a
                    goto L46d
                L575:
                    int r1 = r26.size()
                    int r0 = r6.size()
                    int r1 = r1 - r0
                    if (r1 != 0) goto L322
                    goto L324
                L582:
                    int r3 = r47.getId()
                    com.vega.adeditor.utils.AdScriptMakerImpl$onScriptSpeechSuccess$5 r2 = new com.vega.adeditor.utils.AdScriptMakerImpl$onScriptSpeechSuccess$5
                    r1 = r49
                    r0 = r47
                    r2.<init>()
                    r0 = r49
                    r0.k(r3, r2)
                    boolean r0 = r9.isEmpty()
                    if (r0 != 0) goto L5ad
                    com.vega.middlebridge.swig.DraftComboParams r1 = new com.vega.middlebridge.swig.DraftComboParams
                    r1.<init>()
                    java.lang.String r0 = "ADD_TEXT_AUDIO_ACTION"
                    r1.d(r0)
                    com.vega.middlebridge.lyrasession.LyraSession r0 = r47.b()
                    if (r0 == 0) goto L5ad
                    com.vega.middlebridge.client.DraftClient.k(r0, r1, r9)
                L5ad:
                    java.lang.Boolean r0 = java.lang.Boolean.TRUE
                    r13.invoke(r0)
                    goto L1a
                L5b4:
                    com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1$1$2 r3 = new com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1$1$2
                    com.vega.ui.dialog.LvProgressDialog r2 = r7
                    r3.<init>()
                    com.vega.infrastructure.extensions.ThreadUtilKt.b(r0, r3)
                    goto L1a
                L5c0:
                    java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
                    java.lang.String r0 = "Collection contains no element matching the predicate."
                    r1.<init>(r0)
                    throw r1
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vega.adeditor.utils.AdScriptMakerImpl$genAiScriptTTS$1.AnonymousClass1.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -709087, 31));
        return Unit.INSTANCE;
    }
}
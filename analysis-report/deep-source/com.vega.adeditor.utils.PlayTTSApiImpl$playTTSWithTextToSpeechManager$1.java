package com.vega.adeditor.utils;

import androidx.fragment.app.FragmentActivity;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToSpeechManager$1", f = "PlayTTSApiImpl.kt", i = {}, l = {139}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes29.dex */
public final class PlayTTSApiImpl$playTTSWithTextToSpeechManager$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int q;
    public final /* synthetic */ String r;
    public final /* synthetic */ PlayTTSApiImpl s;
    public final /* synthetic */ String t;
    public final /* synthetic */ String u;
    public final /* synthetic */ float v;
    public final /* synthetic */ int w;
    public final /* synthetic */ FragmentActivity x;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayTTSApiImpl$playTTSWithTextToSpeechManager$1(String str, PlayTTSApiImpl playTTSApiImpl, String str2, String str3, float f, int i, FragmentActivity fragmentActivity, Continuation<? super PlayTTSApiImpl$playTTSWithTextToSpeechManager$1> continuation) {
        super(2, continuation);
        this.r = str;
        this.s = playTTSApiImpl;
        this.t = str2;
        this.u = str3;
        this.v = f;
        this.w = i;
        this.x = fragmentActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayTTSApiImpl$playTTSWithTextToSpeechManager$1(this.r, this.s, this.t, this.u, this.v, this.w, this.x, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v5, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x012d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r47) {
        /*
            r46 = this;
            r4 = r47
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r0 = r46
            int r1 = r0.q
            r7 = 0
            r5 = 0
            r3 = 1
            if (r1 == 0) goto L45
            if (r1 != r3) goto L62
            kotlin.ResultKt.throwOnFailure(r4)
        L14:
            java.lang.Iterable r4 = (java.lang.Iterable) r4
            java.lang.String r6 = r0.t
            java.util.Iterator r4 = r4.iterator()
        L1c:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L43
            java.lang.Object r2 = r4.next()
            r1 = r2
            com.lemon.lv.data.ToneType r1 = (com.lemon.lv.data.ToneType) r1
            java.lang.String r1 = r1.getVoiceType()
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r6)
            if (r1 == 0) goto L1c
        L33:
            com.lemon.lv.data.ToneType r2 = (com.lemon.lv.data.ToneType) r2
            if (r2 != 0) goto L5d
            com.vega.adeditor.utils.PlayTTSApiImpl r2 = r0.s
            java.lang.String r1 = "loadFail"
            java.lang.String r0 = r0.t
            r2.e(r1, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L43:
            r2 = r5
            goto L33
        L45:
            kotlin.ResultKt.throwOnFailure(r4)
            java.lang.String r1 = r0.r
            if (r1 == 0) goto L52
            int r1 = r1.length()
            if (r1 != 0) goto L6a
        L52:
            com.vega.adeditor.utils.AdMakerUtils r1 = com.vega.adeditor.utils.AdMakerUtils.f68179a
            r0.q = r3
            java.lang.Object r4 = com.vega.adeditor.utils.AdMakerUtils.P(r1, r0)
            if (r4 != r2) goto L14
            return r2
        L5d:
            java.lang.String r11 = r2.getPlatform()
            goto L6c
        L62:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L6a:
            java.lang.String r11 = r0.r
        L6c:
            com.vega.adeditor.utils.PlayTTSApiImpl r1 = r0.s
            kotlin.Lazy r1 = r1.f
            java.lang.Object r1 = r1.getValue()
            com.vega.audio.tone.tts.TextToSpeechTaskManager r1 = (com.vega.audio.tone.tts.TextToSpeechTaskManager) r1
            r1.init(r5)
            com.vega.adeditor.utils.PlayTTSApiImpl r1 = r0.s
            kotlin.Lazy r1 = r1.f
            java.lang.Object r1 = r1.getValue()
            com.vega.audio.tone.tts.TextToSpeechTaskManager r1 = (com.vega.audio.tone.tts.TextToSpeechTaskManager) r1
            com.vega.core.context.SPIService r4 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.vega.libeffectapi.settings.IEffectSettings> r2 = com.vega.libeffectapi.settings.IEffectSettings.class
            kotlin.reflect.KClass r2 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r2)
            java.lang.Object r2 = r4.getImpl(r2, r5)
            com.vega.libeffectapi.settings.IEffectSettings r2 = (com.vega.libeffectapi.settings.IEffectSettings) r2
            boolean r2 = r2.v()
            if (r2 == 0) goto L12d
            com.vega.edit.base.tone.TextInfo$NoSegTextList r9 = new com.vega.edit.base.tone.TextInfo$NoSegTextList
            java.lang.String r2 = r0.u
            java.util.List r2 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r2)
            r9.<init>(r2)
        La2:
            com.vega.audio.tone.util.TextToSpeechReportInfo r12 = new com.vega.audio.tone.util.TextToSpeechReportInfo
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r13 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.LYNX
            r8 = 0
            java.lang.String r2 = r0.u
            int r15 = r2.length()
            float r4 = r0.v
            r2 = 1065353216(0x3f800000, float:1.0)
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto Lb6
            r7 = 1
        Lb6:
            r17 = r7 ^ 1
            r18 = 0
            r24 = 994(0x3e2, float:1.393E-42)
            r14 = r8
            r16 = r3
            r20 = r8
            r21 = r8
            r22 = r8
            r23 = r8
            r25 = r8
            r12.<init>(r13, r14, r15, r16, r17, r18, r20, r21, r22, r23, r24, r25)
            java.lang.String r22 = r12.toJson()
            com.vega.edit.base.tone.TTSBusinessType r14 = com.vega.edit.base.tone.TTSBusinessType.b
            com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToSpeechManager$1$1 r2 = new com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToSpeechManager$1$1
            com.vega.adeditor.utils.PlayTTSApiImpl r5 = r0.s
            java.lang.String r10 = r0.t
            androidx.fragment.app.FragmentActivity r4 = r0.x
            java.lang.String r3 = r0.u
            r2.<init>()
            com.vega.edit.base.tone.TextToSpeechIntent r7 = new com.vega.edit.base.tone.TextToSpeechIntent
            java.lang.String r12 = "PlayTTSApiImpl"
            java.lang.String r15 = "tiktok_ads_edit"
            java.lang.String r16 = "tiktok_ads"
            float r3 = r0.v
            int r0 = r0.w
            r21 = 0
            r44 = -20447(0xffffffffffffb021, float:NaN)
            r45 = 31
            r13 = r8
            r18 = r0
            r19 = r2
            r20 = r8
            r23 = r8
            r24 = r21
            r25 = r8
            r26 = r8
            r27 = r8
            r28 = r8
            r29 = r8
            r30 = r8
            r31 = r8
            r32 = r21
            r33 = r8
            r34 = r8
            r35 = r21
            r36 = r21
            r37 = r21
            r38 = r21
            r39 = r8
            r40 = r8
            r41 = r8
            r42 = r8
            r43 = r21
            r17 = r3
            r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45)
            r1.f(r7)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L12d:
            com.vega.edit.base.tone.TextInfo$AutoSegText r9 = new com.vega.edit.base.tone.TextInfo$AutoSegText
            java.lang.String r2 = r0.u
            r9.<init>(r2)
            goto La2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToSpeechManager$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
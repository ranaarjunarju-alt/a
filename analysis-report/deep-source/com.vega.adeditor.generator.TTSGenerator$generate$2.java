package com.vega.adeditor.generator;

import com.lemon.lv.data.ToneType;
import com.vega.adeditor.scriptvideo.generate.TextToSpeechData;
import java.util.List;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.adeditor.generator.TTSGenerator$generate$2", f = "TTSGenerator.kt", i = {}, l = {198}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes29.dex */
public final class TTSGenerator$generate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super TextToSpeechData>, Object> {
    public Object q;
    public int r;
    public final /* synthetic */ boolean s;
    public final /* synthetic */ List<String> t;
    public final /* synthetic */ ToneType u;
    public final /* synthetic */ TTSGenerator v;
    public final /* synthetic */ boolean w;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TTSGenerator$generate$2(boolean z, List<String> list, ToneType toneType, TTSGenerator tTSGenerator, boolean z2, Continuation<? super TTSGenerator$generate$2> continuation) {
        super(2, continuation);
        this.s = z;
        this.t = list;
        this.u = toneType;
        this.v = tTSGenerator;
        this.w = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new TTSGenerator$generate$2(this.s, this.t, this.u, this.v, this.w, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super TextToSpeechData> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:15:0x007f  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r49) {
        /*
            r48 = this;
            r2 = r49
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            r0 = r48
            int r1 = r0.r
            r9 = 1
            if (r1 == 0) goto L13
            if (r1 != r9) goto L126
            kotlin.ResultKt.throwOnFailure(r2)
        L12:
            return r2
        L13:
            kotlin.ResultKt.throwOnFailure(r2)
            boolean r7 = r0.s
            java.util.List<java.lang.String> r5 = r0.t
            com.lemon.lv.data.ToneType r6 = r0.u
            com.vega.adeditor.generator.TTSGenerator r1 = r0.v
            boolean r8 = r0.w
            r0.q = r5
            r0.r = r9
            kotlinx.coroutines.CancellableContinuationImpl r2 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r0)
            r2.<init>(r4, r9)
            r2.initCancellability()
            if (r7 == 0) goto L11f
            r4 = 0
            java.lang.Object r4 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r5, r4)
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L3d
            java.lang.String r4 = ""
        L3d:
            com.vega.edit.base.tone.TextInfo$AutoSegText r11 = new com.vega.edit.base.tone.TextInfo$AutoSegText
            r11.<init>(r4)
        L42:
            com.vega.audio.tone.util.TextToSpeechReportInfo r15 = new com.vega.audio.tone.util.TextToSpeechReportInfo
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r16 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.CC4B_IMAGE_TO_VIDEO
            r17 = 0
            java.lang.String r4 = r11.getText()
            int r18 = r4.length()
            r19 = 0
            r21 = 0
            r27 = 994(0x3e2, float:1.393E-42)
            r10 = 0
            r20 = r19
            r23 = r17
            r24 = r17
            r25 = r17
            r26 = r17
            r28 = r17
            r15.<init>(r16, r17, r18, r19, r20, r21, r23, r24, r25, r26, r27, r28)
            com.vega.audio.tone.tts.TextToSpeechTaskManager r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
            com.vega.edit.base.tone.TextToSpeechIntent r9 = new com.vega.edit.base.tone.TextToSpeechIntent
            java.lang.String r12 = r6.getVoiceType()
            java.lang.String r13 = r6.getPlatform()
            java.lang.String r14 = "TTSGenerator"
            com.vega.edit.base.tone.TTSBusinessType r16 = com.vega.edit.base.tone.TTSBusinessType.b
            java.lang.String r7 = r1.f66645a
            int r4 = r7.hashCode()
            switch(r4) {
                case -1471951893: goto L111;
                case -1366605528: goto L10a;
                case -600761767: goto Lfc;
                case 161891550: goto Lf0;
                case 2085189144: goto Le4;
                default: goto L7f;
            }
        L7f:
            java.lang.String r17 = "ads_template_edit"
        L81:
            java.lang.String r7 = r1.f66645a
            r19 = 0
            r20 = 0
            java.lang.String r24 = r15.toJson()
            java.lang.String r25 = r6.getAuditionText()
            java.lang.String r27 = r6.getToneModelType()
            java.lang.String r28 = r6.getResourceId()
            com.vega.adeditor.generator.TTSGenerator$generate$2$1$1 r4 = new com.vega.adeditor.generator.TTSGenerator$generate$2$1$1
            r4.<init>()
            r46 = -975327(0xfffffffffff11e21, float:NaN)
            r47 = 31
            r15 = r10
            r18 = r7
            r21 = r10
            r22 = r10
            r23 = r8
            r26 = r20
            r29 = r4
            r30 = r10
            r31 = r10
            r32 = r10
            r33 = r10
            r34 = r20
            r35 = r10
            r36 = r10
            r37 = r20
            r38 = r20
            r39 = r20
            r40 = r20
            r41 = r10
            r42 = r10
            r43 = r10
            r44 = r10
            r45 = r20
            r9.<init>(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47)
            r5.c(r9)
            java.lang.Object r2 = r2.getResult()
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r2 != r1) goto Le1
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r0)
        Le1:
            if (r2 != r3) goto L12
            return r3
        Le4:
            java.lang.String r4 = "new_smart_ad"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto Led
            goto L7f
        Led:
            java.lang.String r17 = "smart_ad_edit"
            goto L81
        Lf0:
            java.lang.String r4 = "knowledge_share"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto Lf9
            goto L7f
        Lf9:
            java.lang.String r17 = "knowledge_share_edit"
            goto L81
        Lfc:
            java.lang.String r4 = "smart_ad"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L106
            goto L7f
        L106:
            java.lang.String r17 = "ads_edit"
            goto L81
        L10a:
            java.lang.String r4 = "ad_maker"
            r7.equals(r4)
            goto L7f
        L111:
            java.lang.String r4 = "tiktok_ads"
            boolean r4 = r7.equals(r4)
            if (r4 != 0) goto L11b
            goto L7f
        L11b:
            java.lang.String r17 = "tiktok_ads_edit"
            goto L81
        L11f:
            com.vega.edit.base.tone.TextInfo$NoSegTextList r11 = new com.vega.edit.base.tone.TextInfo$NoSegTextList
            r11.<init>(r5)
            goto L42
        L126:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.adeditor.generator.TTSGenerator$generate$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
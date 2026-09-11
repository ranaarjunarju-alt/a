package com.vega.audio.tone;

import com.lemon.lv.data.ToneType;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.audio.tone.ToneTypeHelper$fetchEffectToneType$2", f = "ToneTypeHelper.kt", i = {}, l = {25}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes30.dex */
public final class ToneTypeHelper$fetchEffectToneType$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ToneType>, Object> {
    public int q;
    public final /* synthetic */ String r;
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ String u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ToneTypeHelper$fetchEffectToneType$2(String str, String str2, String str3, String str4, Continuation<? super ToneTypeHelper$fetchEffectToneType$2> continuation) {
        super(2, continuation);
        this.r = str;
        this.s = str2;
        this.t = str3;
        this.u = str4;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ToneTypeHelper$fetchEffectToneType$2(this.r, this.s, this.t, this.u, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ToneType> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.edit.base.utils.ToneUtil.c(com.vega.edit.base.utils.ToneUtil, com.ss.android.ugc.effectmanager.effect.model.Effect, com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel, int):com.lemon.lv.data.ToneType */
    /* JADX WARN: Removed duplicated region for block: B:20:0x004d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r9.q
            r2 = 0
            r1 = 1
            if (r0 == 0) goto L8e
            if (r0 != r1) goto La0
            kotlin.ResultKt.throwOnFailure(r10)
        Lf:
            java.util.List r10 = (java.util.List) r10
            if (r10 == 0) goto L8c
            boolean r0 = r10.isEmpty()
            r0 = r0 ^ 1
            if (r0 != r1) goto L8c
            r0 = 1
        L1c:
            r4 = 0
            if (r0 == 0) goto L9f
            java.lang.String r6 = r9.r
            java.lang.String r5 = r9.s
            java.lang.String r3 = r9.t
            java.lang.String r1 = r9.u
            java.util.Iterator r8 = r10.iterator()
        L2b:
            boolean r0 = r8.hasNext()
            if (r0 == 0) goto L8a
            java.lang.Object r2 = r8.next()
            r7 = r2
            com.ss.android.ugc.effectmanager.effect.model.Effect r7 = (com.ss.android.ugc.effectmanager.effect.model.Effect) r7
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r6)
            if (r0 == 0) goto L4d
            com.vega.edit.base.utils.ToneUtil r0 = com.vega.edit.base.utils.ToneUtil.f89173a
            r0.getClass()
            java.lang.String r0 = com.vega.edit.base.utils.ToneUtil.h(r7)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r0)
            if (r0 != 0) goto L7d
        L4d:
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r5)
            if (r0 == 0) goto L5d
            java.lang.String r0 = r7.getName()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r0)
            if (r0 != 0) goto L7d
        L5d:
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r3)
            if (r0 == 0) goto L6d
            java.lang.String r0 = r7.getName()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            if (r0 != 0) goto L7d
        L6d:
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r1)
            if (r0 == 0) goto L2b
            java.lang.String r0 = r7.getEffectId()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L2b
        L7d:
            com.ss.android.ugc.effectmanager.effect.model.Effect r2 = (com.ss.android.ugc.effectmanager.effect.model.Effect) r2
            if (r2 == 0) goto L9f
            com.vega.edit.base.utils.ToneUtil r1 = com.vega.edit.base.utils.ToneUtil.f89173a
            r0 = 12
            com.lemon.lv.data.ToneType r0 = com.vega.edit.base.utils.ToneUtil.c(r1, r2, r4, r0)
            return r0
        L8a:
            r2 = r4
            goto L7d
        L8c:
            r0 = 0
            goto L1c
        L8e:
            kotlin.ResultKt.throwOnFailure(r10)
            com.vega.effectplatform.artist.utils.ToneFetchAllHelper r0 = com.vega.effectplatform.artist.utils.ToneFetchAllHelper.f98473a
            r9.q = r1
            r0.getClass()
            java.lang.Object r10 = com.vega.effectplatform.artist.utils.ToneFetchAllHelper.a(r2, r9)
            if (r10 != r3) goto Lf
            return r3
        L9f:
            return r4
        La0:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.ToneTypeHelper$fetchEffectToneType$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
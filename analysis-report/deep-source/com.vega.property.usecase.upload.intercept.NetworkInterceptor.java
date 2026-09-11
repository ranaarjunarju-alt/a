package com.vega.property.usecase.upload.intercept;

import android.content.Context;
import com.vega.property.entity.Platform;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes29.dex */
public final class NetworkInterceptor implements IUploadInterceptor {

    /* renamed from: a, reason: collision with root package name */
    public final String f127412a = "network_storage_stage";

    public static Object e(Context context, CoroutineScope coroutineScope, ContinuationImpl continuationImpl) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuationImpl), 1);
        cancellableContinuationImpl.initCancellability();
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new NetworkInterceptor$networkIntercept$2$1(context, cancellableContinuationImpl, null), 2, null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuationImpl);
        }
        return result;
    }

    @Override // com.vega.property.usecase.upload.intercept.IUploadInterceptor
    public final Platform a() {
        return Platform.f126620c;
    }

    @Override // com.vega.property.usecase.upload.intercept.IUploadInterceptor
    public final String b() {
        return this.f127412a;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004c  */
    @Override // com.vega.property.usecase.upload.intercept.IUploadInterceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(android.content.Context r6, kotlinx.coroutines.CoroutineScope r7, java.util.List<com.vega.gallery.local.MediaData> r8, com.vega.property.usecase.upload.UploadMaterialParams r9, kotlin.coroutines.Continuation<? super com.vega.property.usecase.upload.UploadMaterialHandlerResult> r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof com.vega.property.usecase.upload.intercept.NetworkInterceptor$interceptForMaterial$1
            if (r0 == 0) goto L4c
            r4 = r10
            com.vega.property.usecase.upload.intercept.NetworkInterceptor$interceptForMaterial$1 r4 = (com.vega.property.usecase.upload.intercept.NetworkInterceptor$interceptForMaterial$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L4c
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.t
            r0 = 1
            if (r1 == 0) goto L3e
            if (r1 != r0) goto L52
            java.lang.Object r8 = r4.q
            java.util.List r8 = (java.util.List) r8
            kotlin.ResultKt.throwOnFailure(r3)
        L26:
            kotlin.Pair r3 = (kotlin.Pair) r3
            com.vega.property.usecase.upload.UploadMaterialHandlerResult r2 = new com.vega.property.usecase.upload.UploadMaterialHandlerResult
            java.lang.Object r0 = r3.getFirst()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
            java.lang.Object r0 = r3.getSecond()
            java.lang.String r0 = (java.lang.String) r0
            r2.<init>(r1, r8, r0)
            return r2
        L3e:
            kotlin.ResultKt.throwOnFailure(r3)
            r4.q = r8
            r4.t = r0
            java.lang.Object r3 = e(r6, r7, r4)
            if (r3 != r2) goto L26
            return r2
        L4c:
            com.vega.property.usecase.upload.intercept.NetworkInterceptor$interceptForMaterial$1 r4 = new com.vega.property.usecase.upload.intercept.NetworkInterceptor$interceptForMaterial$1
            r4.<init>(r5, r10)
            goto L12
        L52:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.property.usecase.upload.intercept.NetworkInterceptor.c(android.content.Context, kotlinx.coroutines.CoroutineScope, java.util.List, com.vega.property.usecase.upload.UploadMaterialParams, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    @Override // com.vega.property.usecase.upload.intercept.IUploadInterceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(android.content.Context r6, kotlinx.coroutines.CoroutineScope r7, java.util.List<com.lemon.cloud.data.DraftItem> r8, com.vega.property.usecase.upload.UploadParams r9, kotlin.coroutines.Continuation<? super com.vega.property.usecase.upload.UploadHandlerResult> r10) {
        /*
            r5 = this;
            boolean r0 = r10 instanceof com.vega.property.usecase.upload.intercept.NetworkInterceptor$intercept$1
            if (r0 == 0) goto L4e
            r3 = r10
            com.vega.property.usecase.upload.intercept.NetworkInterceptor$intercept$1 r3 = (com.vega.property.usecase.upload.intercept.NetworkInterceptor$intercept$1) r3
            int r2 = r3.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L4e
            int r2 = r2 - r1
            r3.t = r2
        L12:
            java.lang.Object r4 = r3.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r3.t
            r0 = 1
            if (r1 == 0) goto L40
            if (r1 != r0) goto L54
            java.lang.Object r8 = r3.q
            java.util.List r8 = (java.util.List) r8
            kotlin.ResultKt.throwOnFailure(r4)
        L26:
            kotlin.Pair r4 = (kotlin.Pair) r4
            com.vega.property.usecase.upload.UploadHandlerResult r3 = new com.vega.property.usecase.upload.UploadHandlerResult
            java.lang.Object r0 = r4.getFirst()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r2 = r0.booleanValue()
            java.lang.Object r1 = r4.getSecond()
            java.lang.String r1 = (java.lang.String) r1
            r0 = 24
            r3.<init>(r1, r8, r2, r0)
            return r3
        L40:
            kotlin.ResultKt.throwOnFailure(r4)
            r3.q = r8
            r3.t = r0
            java.lang.Object r4 = e(r6, r7, r3)
            if (r4 != r2) goto L26
            return r2
        L4e:
            com.vega.property.usecase.upload.intercept.NetworkInterceptor$intercept$1 r3 = new com.vega.property.usecase.upload.intercept.NetworkInterceptor$intercept$1
            r3.<init>(r5, r10)
            goto L12
        L54:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.property.usecase.upload.intercept.NetworkInterceptor.d(android.content.Context, kotlinx.coroutines.CoroutineScope, java.util.List, com.vega.property.usecase.upload.UploadParams, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
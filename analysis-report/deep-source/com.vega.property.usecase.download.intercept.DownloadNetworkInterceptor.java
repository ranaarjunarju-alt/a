package com.vega.property.usecase.download.intercept;

import android.content.Context;
import com.vega.property.entity.Platform;
import com.vega.property.entity.PropertyFileItemData;
import com.vega.property.usecase.download.DownloadInterceptResult;
import com.vega.property.usecase.download.DownloadParams;
import java.util.List;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes.dex */
public final class DownloadNetworkInterceptor implements IDownloadInterceptor {
    @Override // com.vega.property.usecase.download.intercept.IDownloadInterceptor
    public final Platform a() {
        return Platform.f126620c;
    }

    @Override // com.vega.property.usecase.download.intercept.IDownloadInterceptor
    public final Object b(Context context, CoroutineScope coroutineScope, List<? extends PropertyFileItemData> list, DownloadParams downloadParams, Continuation<? super DownloadInterceptResult> continuation) {
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, Dispatchers.getMain(), null, new DownloadNetworkInterceptor$intercept$2$1(list, null, cancellableContinuationImpl), 2, null);
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
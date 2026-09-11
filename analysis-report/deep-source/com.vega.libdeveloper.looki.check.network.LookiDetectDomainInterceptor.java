package com.vega.libdeveloper.looki.check.network;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.intercept.Interceptor;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes.dex */
public final class LookiDetectDomainInterceptor implements Interceptor {
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "");
        SsResponse<?> ssResponseProceed = chain.proceed(chain.request());
        Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
        return ssResponseProceed;
    }
}
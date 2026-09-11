package com.vega.launcher.network.interceptors;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.core.privacy.looki.LookiCurrentPseudonymStatus;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes35.dex */
public final class LookiRollBackInterceptor implements Interceptor {
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "");
        Request request = chain.request();
        request.getUrl().getClass();
        LookiCurrentPseudonymStatus.j.getClass();
        LookiCurrentPseudonymStatus.Companion.a().getClass();
        SsResponse<?> ssResponseProceed = chain.proceed(request);
        Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
        return ssResponseProceed;
    }
}
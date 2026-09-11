package com.vega.launcher.network.interceptors;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.lemon.clipmonetize.Commerce;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.vega.log.BLog;
import java.net.URI;
import java.util.ArrayList;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
public final class IapRegionInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final Set<String> f107407a;

    /* loaded from: classes12.dex */
    public static final class Companion {
    }

    static {
        new Companion();
        f107407a = SetsKt__SetsKt.setOf((Object[]) new String[]{"/commerce/v1/subscription/cc_price_list", "/commerce/v3/trade/init_trade", "/commerce/v1/subscription/batch_get"});
    }

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(chain, "");
        Request request = chain.request();
        NetworkRuntimeConfig networkRuntimeConfig = NetworkRuntimeConfig.f60924a;
        Intrinsics.checkNotNull(request);
        networkRuntimeConfig.getClass();
        if (NetworkRuntimeConfig.a(request)) {
            SsResponse<?> ssResponseProceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
            return ssResponseProceed;
        }
        String url = request.getUrl();
        try {
            objCreateFailure = URI.create(url).getPath();
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        if (Result.m17096isFailureimpl(objCreateFailure)) {
            objCreateFailure = null;
        }
        String str = (String) objCreateFailure;
        if (str == null || !f107407a.contains(str)) {
            SsResponse<?> ssResponseProceed2 = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed2, "");
            return ssResponseProceed2;
        }
        try {
            String strK = Commerce.p.b().k();
            if (strK == null || strK.length() == 0) {
                BLog.w("IapRegionInterceptor", "Encrypted IAP region is null or empty for path: ".concat(str));
                SsResponse<?> ssResponseProceed3 = chain.proceed(request);
                Intrinsics.checkNotNullExpressionValue(ssResponseProceed3, "");
                return ssResponseProceed3;
            }
            ArrayList arrayList = new ArrayList(request.getHeaders().size() + 1);
            arrayList.addAll(request.getHeaders());
            arrayList.add(new Header("edata", strK));
            Request.Builder builderNewBuilder = request.newBuilder();
            builderNewBuilder.headers(arrayList);
            builderNewBuilder.url(url);
            builderNewBuilder.method(request.getMethod(), request.getBody());
            SsResponse<?> ssResponseProceed4 = chain.proceed(builderNewBuilder.build());
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed4, "");
            return ssResponseProceed4;
        } catch (Exception e) {
            BLog.e("IapRegionInterceptor", "Failed to add edata header for path: ".concat(str), e);
            SsResponse<?> ssResponseProceed5 = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed5, "");
            return ssResponseProceed5;
        }
    }
}
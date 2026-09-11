package com.vega.launcher.network.interceptors;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.google.gson.Gson;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.vega.core.net.SResponse;
import com.vega.launcher.network.interceptors.SignVerifyInterceptor;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes35.dex */
public final class LynxSignVerifyInterceptor implements Interceptor {
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) throws Exception {
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
        List<Header> headers = request.getHeaders();
        Intrinsics.checkNotNullExpressionValue(headers, "");
        Iterator<T> it = headers.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (Intrinsics.areEqual("lynx-commerce-sign-version", ((Header) it.next()).getName())) {
                z = true;
            }
        }
        if (!z) {
            SsResponse<?> ssResponseProceed2 = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed2, "");
            return ssResponseProceed2;
        }
        Request.Builder builderNewBuilder = request.newBuilder();
        ArrayList arrayList = new ArrayList(request.getHeaders());
        arrayList.add(new Header("commerce-sign-version", BusinessPhotoTemplateOptEntity.V1));
        builderNewBuilder.headers(arrayList);
        builderNewBuilder.method(request.getMethod(), request.getBody());
        Request requestBuild = builderNewBuilder.build();
        SsResponse<?> ssResponseProceed3 = chain.proceed(requestBuild);
        Object objBody = ssResponseProceed3.body();
        Intrinsics.checkNotNull(objBody, "");
        SResponse<?> sResponse = (SResponse) new Gson().fromJson((String) objBody, SResponse.class);
        if (!ssResponseProceed3.isSuccessful() || sResponse == null) {
            Intrinsics.checkNotNull(ssResponseProceed3);
        } else {
            SignVerifyInterceptor.Companion companion = SignVerifyInterceptor.f107412a;
            Intrinsics.checkNotNull(requestBuild);
            boolean zB = companion.b(requestBuild, sResponse);
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("LynxSignVerifyInterceptor", "verify result: " + zB);
            }
            if (!zB) {
                throw new Exception("9527003");
            }
            Intrinsics.checkNotNull(ssResponseProceed3);
        }
        return ssResponseProceed3;
    }
}
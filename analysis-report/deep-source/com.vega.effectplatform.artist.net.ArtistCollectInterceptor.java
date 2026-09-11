package com.vega.effectplatform.artist.net;

import android.net.Uri;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.vega.core.utils.FlavorLocale;
import com.vega.effectplatform.resource.ResourceManagerKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Regex;

/* loaded from: classes27.dex */
public final class ArtistCollectInterceptor implements Interceptor {
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        String url;
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
        String path = Uri.parse(request.getUrl()).getPath();
        if (path == null) {
            path = "";
        }
        if (ResourceManagerKt.f98555a.contains(path)) {
            Regex regex = new Regex("(language=[^&]*)");
            String url2 = request.getUrl();
            Intrinsics.checkNotNullExpressionValue(url2, "");
            StringBuilder sb = new StringBuilder("language=");
            FlavorLocale.f79592a.getClass();
            sb.append(FlavorLocale.i(false));
            url = regex.replace(url2, sb.toString());
        } else {
            url = request.getUrl();
        }
        Request.Builder builderNewBuilder = request.newBuilder();
        builderNewBuilder.url(url);
        SsResponse<?> ssResponseProceed2 = chain.proceed(builderNewBuilder.build());
        Intrinsics.checkNotNullExpressionValue(ssResponseProceed2, "");
        return ssResponseProceed2;
    }
}
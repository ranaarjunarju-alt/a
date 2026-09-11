package com.vega.audio.net;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.core.utils.FlavorLocale;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes20.dex */
public final class SongNetHeaderInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final SongNetHeaderInterceptor f73635a = new SongNetHeaderInterceptor();
    public static final List<String> b = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"/lv/v1/get_collection_songs", "/lv/v1/get_recommend_songs", "/lv/v1/get_collections", "/lv/v1/multi_get_songs", "/lv/v1/third_crawl", "/lv/v1/get_my_tiktok_songs", "/lv/v1/search_songs", "/lv/v1/effect/search", "/lv/v1/get_music_effect_collections"});

    /* renamed from: c, reason: collision with root package name */
    public static boolean f73636c;

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        Request request;
        Intrinsics.checkNotNullParameter(chain, "");
        Request request2 = chain.request();
        if (b.contains(request2.getPath())) {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            List<Header> headers = request2.getHeaders();
            Intrinsics.checkNotNullExpressionValue(headers, "");
            ArrayList arrayList = new ArrayList();
            for (Object obj : headers) {
                if (!Intrinsics.areEqual(((Header) obj).getName(), "lan")) {
                    arrayList.add(obj);
                }
            }
            linkedHashSet.addAll(arrayList);
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("SongNetHeaderInterceptor", "lan=" + FlavorLocale.j(FlavorLocale.f79592a));
            }
            FlavorLocale.f79592a.getClass();
            linkedHashSet.add(new Header("lan", FlavorLocale.i(false)));
            Request.Builder builderNewBuilder = request2.newBuilder();
            builderNewBuilder.headers(CollectionsKt___CollectionsKt.toList(linkedHashSet));
            builderNewBuilder.method(request2.getMethod(), request2.getBody());
            request = builderNewBuilder.build();
        } else {
            request = chain.request();
        }
        SsResponse<?> ssResponseProceed = chain.proceed(request);
        Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
        return ssResponseProceed;
    }
}
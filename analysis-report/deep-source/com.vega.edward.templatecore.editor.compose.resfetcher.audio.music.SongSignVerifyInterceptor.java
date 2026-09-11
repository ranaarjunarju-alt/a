package com.vega.edward.templatecore.editor.compose.resfetcher.audio.music;

import android.net.Uri;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.core.ext.ExtentionKt;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* loaded from: classes5.dex */
public final class SongSignVerifyInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final List<String> f97953a;
    public static final List<String> b;

    /* loaded from: classes4.dex */
    public static final class Companion {
    }

    static {
        new Companion();
        f97953a = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"/lv/v1/get_collection_songs", "/lv/v1/get_recommend_songs", "/lv/v1/multi_get_songs", "/lv/v1/get_my_tiktok_songs", "/lv/v1/search_songs", "/artist/v1/effect/user_favorite_list"});
        b = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"aid", "device_id", "device_platform", "language", "region"});
    }

    public final Uri a(Uri uri, String str, String str2) {
        Uri uriBuild;
        return (!uri.isHierarchical() || ExtentionKt.isNotNullOrEmpty(uri.getQueryParameter(str)) || str2 == null || (uriBuild = uri.buildUpon().appendQueryParameter(str, str2).build()) == null) ? uri : uriBuild;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0090 A[ADDED_TO_REGION, REMOVE] */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bytedance.retrofit2.SsResponse<?> intercept(com.bytedance.retrofit2.intercept.Interceptor.Chain r9) {
        /*
            r8 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.bytedance.retrofit2.client.Request r7 = r9.request()
            java.lang.String r0 = r7.getUrl()
            android.net.Uri r2 = android.net.Uri.parse(r0)
            java.util.List<java.lang.String> r1 = com.vega.edward.templatecore.editor.compose.resfetcher.audio.music.SongSignVerifyInterceptor.f97953a
            java.lang.String r0 = r2.getPath()
            boolean r6 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r1, r0)
            if (r6 == 0) goto L94
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.vega.core.utils.FlavorLocale r0 = com.vega.core.utils.FlavorLocale.f79592a
            r0.getClass()
            java.lang.String r1 = com.vega.core.utils.FlavorLocale.h()
            java.lang.String r0 = "language"
            android.net.Uri r2 = r8.a(r2, r0, r1)
            java.lang.String r1 = "region"
            java.lang.String r0 = com.vega.core.utils.FlavorLocale.b()
            android.net.Uri r0 = r8.a(r2, r1, r0)
            java.lang.String r5 = r0.toString()
        L3d:
            com.bytedance.retrofit2.client.Request$Builder r4 = r7.newBuilder()
            java.util.ArrayList r3 = new java.util.ArrayList
            java.util.List r0 = r7.getHeaders()
            r3.<init>(r0)
            if (r6 == 0) goto L58
            com.bytedance.retrofit2.client.Header r2 = new com.bytedance.retrofit2.client.Header
            java.lang.String r1 = "Business-Sign-Version"
            java.lang.String r0 = "v2"
            r2.<init>(r1, r0)
            r3.add(r2)
        L58:
            r4.headers(r3)
            r4.url(r5)
            com.bytedance.retrofit2.client.Request r0 = r4.build()
            com.bytedance.retrofit2.SsResponse r2 = r9.proceed(r0)
            java.lang.Object r1 = r2.body()
            boolean r0 = r2.isSuccessful()
            if (r0 == 0) goto L90
            if (r6 == 0) goto L90
            boolean r0 = r1 instanceof com.vega.effectplatform.artist.api.MaterialSResponse
            if (r0 == 0) goto L90
            com.vega.effectplatform.artist.api.MaterialSResponse r1 = (com.vega.effectplatform.artist.api.MaterialSResponse) r1
            java.lang.String r0 = r1.getSign()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L90
            java.lang.String r0 = r1.getResponse()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L90
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
        L8f:
            return r2
        L90:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            goto L8f
        L94:
            java.lang.String r5 = r7.getUrl()
            goto L3d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edward.templatecore.editor.compose.resfetcher.audio.music.SongSignVerifyInterceptor.intercept(com.bytedance.retrofit2.intercept.Interceptor$Chain):com.bytedance.retrofit2.SsResponse");
    }
}
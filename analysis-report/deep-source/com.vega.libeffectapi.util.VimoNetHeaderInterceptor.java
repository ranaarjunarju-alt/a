package com.vega.libeffectapi.util;

import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.core.net.NetworkManagerWrapper;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;

/* loaded from: classes5.dex */
public final class VimoNetHeaderInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final VimoNetHeaderInterceptor f110032a = new VimoNetHeaderInterceptor();
    public static final List<String> b;

    /* renamed from: c, reason: collision with root package name */
    public static final List<String> f110033c;

    /* renamed from: d, reason: collision with root package name */
    public static boolean f110034d;

    static {
        new LinkedHashSet();
        b = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"/artist/v1/panel/check_update", "/artist/v1/panel/get_panel_info", "/artist/v1/effect/get_resources_by_category_id", "/artist/v1/effect/get_search_words"});
        f110033c = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"/artist/v1/effect/user_favorite_list", "/artist/v1/effect/mget_item", "/artist/v1/effect/search", "/artist/v1/effect/get_resources_by_category_id"});
    }

    public final void a() {
        if (f110034d) {
            return;
        }
        NetworkManagerWrapper.f79356a.getClass();
        NetworkManagerWrapper.a(this);
        f110034d = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x00ef  */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bytedance.retrofit2.SsResponse<?> intercept(com.bytedance.retrofit2.intercept.Interceptor.Chain r12) {
        /*
            r11 = this;
            java.lang.String r9 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r9)
            com.bytedance.retrofit2.client.Request r8 = r12.request()
            com.lm.components.network.extra.NetworkRuntimeConfig r0 = com.lm.components.network.extra.NetworkRuntimeConfig.f60924a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8)
            r0.getClass()
            boolean r0 = com.lm.components.network.extra.NetworkRuntimeConfig.a(r8)
            if (r0 == 0) goto L1f
            com.bytedance.retrofit2.SsResponse r0 = r12.proceed(r8)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r9)
            return r0
        L1f:
            java.lang.String r0 = r8.getUrl()
            android.net.Uri r7 = android.net.Uri.parse(r0)
            java.util.List<java.lang.String> r1 = com.vega.libeffectapi.util.VimoNetHeaderInterceptor.f110033c
            java.lang.String r0 = r7.getPath()
            boolean r0 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r1, r0)
            r10 = 0
            if (r0 == 0) goto Lef
            java.lang.String r3 = "version_code_num"
            java.lang.String r0 = r7.getQueryParameter(r3)
            r2 = 1
            if (r0 != 0) goto Lef
            android.net.Uri$Builder r1 = r7.buildUpon()
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            r0.l()
            r0 = 19600200(0x12b1348, float:3.1421573E-38)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            android.net.Uri$Builder r0 = r1.appendQueryParameter(r3, r0)
            android.net.Uri r7 = r0.build()
        L57:
            java.util.List<java.lang.String> r1 = com.vega.libeffectapi.util.VimoNetHeaderInterceptor.b
            java.lang.String r0 = r7.getPath()
            boolean r0 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r1, r0)
            if (r0 == 0) goto Le3
            kotlin.jvm.internal.Intrinsics.checkNotNull(r7)
            java.lang.String r6 = "language"
            java.lang.String[] r5 = new java.lang.String[]{r6}
            boolean r0 = r7.isHierarchical()
            if (r0 != 0) goto L9e
        L72:
            android.net.Uri$Builder r1 = r7.buildUpon()
            com.vega.core.utils.FlavorLocale r0 = com.vega.core.utils.FlavorLocale.f79592a
            r0.getClass()
            java.lang.String r0 = com.vega.core.utils.FlavorLocale.i(r10)
            android.net.Uri$Builder r0 = r1.appendQueryParameter(r6, r0)
            android.net.Uri r0 = r0.build()
            java.lang.String r1 = r0.toString()
        L8b:
            com.bytedance.retrofit2.client.Request$Builder r0 = r8.newBuilder()
            r0.url(r1)
            com.bytedance.retrofit2.client.Request r0 = r0.build()
            com.bytedance.retrofit2.SsResponse r0 = r12.proceed(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r9)
            return r0
        L9e:
            android.net.Uri$Builder r0 = r7.buildUpon()
            android.net.Uri$Builder r4 = r0.clearQuery()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r9)
            java.util.Set r0 = r7.getQueryParameterNames()
            java.util.Iterator r3 = r0.iterator()
        Lb1:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto Ldb
            java.lang.Object r2 = r3.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r0 = kotlin.collections.ArraysKt___ArraysKt.contains(r5, r2)
            if (r0 != 0) goto Lb1
            java.util.List r0 = r7.getQueryParameters(r2)
            java.util.Iterator r1 = r0.iterator()
        Lcb:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto Lb1
            java.lang.Object r0 = r1.next()
            java.lang.String r0 = (java.lang.String) r0
            r4.appendQueryParameter(r2, r0)
            goto Lcb
        Ldb:
            android.net.Uri r7 = r4.build()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r9)
            goto L72
        Le3:
            if (r2 == 0) goto Lea
            java.lang.String r1 = r7.toString()
            goto L8b
        Lea:
            java.lang.String r1 = r8.getUrl()
            goto L8b
        Lef:
            r2 = 0
            goto L57
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.libeffectapi.util.VimoNetHeaderInterceptor.intercept(com.bytedance.retrofit2.intercept.Interceptor$Chain):com.bytedance.retrofit2.SsResponse");
    }
}
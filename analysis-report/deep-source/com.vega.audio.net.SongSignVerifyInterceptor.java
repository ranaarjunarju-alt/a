package com.vega.audio.net;

import android.net.Uri;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.core.ext.ExtentionKt;
import com.vega.effectplatform.artist.api.MaterialSResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class SongSignVerifyInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final List<String> f73637a;
    public static final List<String> b;

    /* loaded from: classes26.dex */
    public static final class Companion {
    }

    static {
        new Companion();
        f73637a = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"/lv/v1/get_collection_songs", "/lv/v1/get_recommend_songs", "/lv/v1/multi_get_songs", "/lv/v1/get_my_tiktok_songs", "/lv/v1/search_songs", "/artist/v1/effect/user_favorite_list"});
        b = CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"aid", "device_id", "device_platform", "language", "region"});
    }

    public static String b(Request request, MaterialSResponse materialSResponse) {
        ArrayList arrayList = new ArrayList();
        Uri uri = Uri.parse(request.getUrl());
        for (String str : b) {
            String queryParameter = uri.getQueryParameter(str);
            if (queryParameter != null) {
                arrayList.add(TuplesKt.to(str, queryParameter));
            }
        }
        Header firstHeader = request.getFirstHeader("Business-Sign-Version");
        if (firstHeader != null) {
            arrayList.add(TuplesKt.to("sign_version", firstHeader.getValue()));
        }
        arrayList.add(TuplesKt.to("ret", materialSResponse.getRet()));
        arrayList.add(TuplesKt.to("systime", String.valueOf(materialSResponse.getServerTime())));
        arrayList.add(TuplesKt.to("response", materialSResponse.getResponse()));
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            if (((CharSequence) ((Pair) next).getSecond()).length() > 0) {
                arrayList2.add(next);
            }
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "&", null, null, 0, null, new Function1<Pair<? extends String, ? extends String>, CharSequence>() { // from class: com.vega.audio.net.SongSignVerifyInterceptor$getSignStrV2$4
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(Pair<? extends String, ? extends String> pair) {
                Pair<? extends String, ? extends String> pair2 = pair;
                Intrinsics.checkNotNullParameter(pair2, "");
                return pair2.getFirst() + '=' + pair2.getSecond();
            }
        }, 30, null);
    }

    public final Uri a(Uri uri, String str, String str2) {
        Uri uriBuild;
        return (!uri.isHierarchical() || ExtentionKt.isNotNullOrEmpty(uri.getQueryParameter(str)) || str2 == null || (uriBuild = uri.buildUpon().appendQueryParameter(str, str2).build()) == null) ? uri : uriBuild;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x00c0  */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bytedance.retrofit2.SsResponse<?> intercept(com.bytedance.retrofit2.intercept.Interceptor.Chain r9) throws java.lang.Exception {
        /*
            r8 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            com.bytedance.retrofit2.client.Request r7 = r9.request()
            java.lang.String r0 = r7.getUrl()
            android.net.Uri r2 = android.net.Uri.parse(r0)
            java.util.List<java.lang.String> r1 = com.vega.audio.net.SongSignVerifyInterceptor.f73637a
            java.lang.String r0 = r2.getPath()
            boolean r6 = kotlin.collections.CollectionsKt___CollectionsKt.contains(r1, r0)
            if (r6 == 0) goto Lba
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
            com.bytedance.retrofit2.client.Request r3 = r4.build()
            com.bytedance.retrofit2.SsResponse r5 = r9.proceed(r3)
            java.lang.Object r4 = r5.body()
            boolean r0 = r5.isSuccessful()
            if (r0 == 0) goto Lc0
            if (r6 == 0) goto Lc0
            boolean r0 = r4 instanceof com.vega.effectplatform.artist.api.MaterialSResponse
            if (r0 == 0) goto Lc0
            com.vega.effectplatform.artist.api.MaterialSResponse r4 = (com.vega.effectplatform.artist.api.MaterialSResponse) r4
            java.lang.String r0 = r4.getSign()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto Lc0
            java.lang.String r0 = r4.getResponse()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto Lc0
            com.vega.core.context.SPIService r2 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.vega.subscriptionapi.legacy.service.IBusinessService> r0 = com.vega.subscriptionapi.legacy.service.IBusinessService.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            r0 = 0
            java.lang.Object r2 = r2.getImpl(r1, r0)
            com.vega.subscriptionapi.legacy.service.IBusinessService r2 = (com.vega.subscriptionapi.legacy.service.IBusinessService) r2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            java.lang.String r1 = b(r3, r4)
            java.lang.String r0 = r4.getSign()
            boolean r0 = r2.s(r1, r0)
            if (r0 != 0) goto Lc4
            b(r3, r4)
            r4.getSign()
            java.lang.Exception r1 = new java.lang.Exception
            java.lang.String r0 = "9527003"
            r1.<init>(r0)
            throw r1
        Lba:
            java.lang.String r5 = r7.getUrl()
            goto L3d
        Lc0:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
            goto Lc7
        Lc4:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
        Lc7:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.net.SongSignVerifyInterceptor.intercept(com.bytedance.retrofit2.intercept.Interceptor$Chain):com.bytedance.retrofit2.SsResponse");
    }
}
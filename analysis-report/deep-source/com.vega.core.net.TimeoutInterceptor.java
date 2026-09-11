package com.vega.core.net;

import com.bytedance.retrofit2.intercept.Interceptor;

/* loaded from: classes35.dex */
public final class TimeoutInterceptor implements Interceptor {

    /* loaded from: classes17.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ac  */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bytedance.retrofit2.SsResponse<?> intercept(com.bytedance.retrofit2.intercept.Interceptor.Chain r15) {
        /*
            r14 = this;
            java.lang.String r9 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r9)
            com.bytedance.retrofit2.client.Request r11 = r15.request()
            com.lm.components.network.extra.NetworkRuntimeConfig r0 = com.lm.components.network.extra.NetworkRuntimeConfig.f60924a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11)
            r0.getClass()
            boolean r0 = com.lm.components.network.extra.NetworkRuntimeConfig.a(r11)
            if (r0 == 0) goto L1f
            com.bytedance.retrofit2.SsResponse r0 = r15.proceed(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r9)
            return r0
        L1f:
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.lang.String r0 = "CONNECT_TIMEOUT"
            java.util.List r0 = r11.headers(r0)
            r12 = 0
            if (r0 == 0) goto Lac
            r8.addAll(r0)
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r0)
            com.bytedance.retrofit2.client.Header r0 = (com.bytedance.retrofit2.client.Header) r0
            if (r0 == 0) goto Lac
            java.lang.String r0 = r0.getValue()
            if (r0 == 0) goto Lac
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.Long r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(r0)
            if (r0 == 0) goto Lac
            long r6 = r0.longValue()
        L4c:
            java.lang.String r0 = "READ_TIMEOUT"
            java.util.List r0 = r11.headers(r0)
            if (r0 == 0) goto La9
            r8.addAll(r0)
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r0)
            com.bytedance.retrofit2.client.Header r0 = (com.bytedance.retrofit2.client.Header) r0
            if (r0 == 0) goto La9
            java.lang.String r0 = r0.getValue()
            if (r0 == 0) goto La9
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.Long r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(r0)
            if (r0 == 0) goto La9
            long r4 = r0.longValue()
        L72:
            java.lang.String r0 = "WRITE_TIMEOUT"
            java.util.List r0 = r11.headers(r0)
            if (r0 == 0) goto La6
            r8.addAll(r0)
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r0)
            com.bytedance.retrofit2.client.Header r0 = (com.bytedance.retrofit2.client.Header) r0
            if (r0 == 0) goto La6
            java.lang.String r0 = r0.getValue()
            if (r0 == 0) goto La6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.lang.Long r0 = kotlin.text.StringsKt__StringNumberConversionsKt.toLongOrNull(r0)
            if (r0 == 0) goto La6
            long r2 = r0.longValue()
        L98:
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto Laf
            com.bytedance.retrofit2.SsResponse r0 = r15.proceed(r11)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r9)
            return r0
        La6:
            r2 = 0
            goto L98
        La9:
            r4 = 0
            goto L72
        Lac:
            r6 = 0
            goto L4c
        Laf:
            com.bytedance.retrofit2.client.Request$Builder r10 = r11.newBuilder()
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.List r0 = r11.getHeaders()
            r1.<init>(r0)
            r1.removeAll(r8)
            r10.headers(r1)
            int r8 = (r6 > r12 ? 1 : (r6 == r12 ? 0 : -1))
            if (r8 > 0) goto Lce
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 > 0) goto Lce
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 <= 0) goto Le6
        Lce:
            com.bytedance.ttnet.http.RequestContext r1 = new com.bytedance.ttnet.http.RequestContext
            r1.<init>()
            if (r8 <= 0) goto Ld7
            r1.timeout_connect = r6
        Ld7:
            int r0 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1))
            if (r0 <= 0) goto Ldd
            r1.timeout_read = r4
        Ldd:
            int r0 = (r2 > r12 ? 1 : (r2 == r12 ? 0 : -1))
            if (r0 <= 0) goto Le3
            r1.timeout_write = r2
        Le3:
            r10.setExtraInfo(r1)
        Le6:
            com.bytedance.retrofit2.client.Request r0 = r10.build()
            com.bytedance.retrofit2.SsResponse r0 = r15.proceed(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r9)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.core.net.TimeoutInterceptor.intercept(com.bytedance.retrofit2.intercept.Interceptor$Chain):com.bytedance.retrofit2.SsResponse");
    }
}
package com.vega.launcher.network.interceptors;

import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.debug.APIHost;
import com.vega.core.context.debug.DevelopSetting;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes27.dex */
public final class AssistInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public final Lazy f107400a = LazyKt__LazyJVMKt.lazy(new Function0<DevelopSetting>() { // from class: com.vega.launcher.network.interceptors.AssistInterceptor$developSetting$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final DevelopSetting invoke() {
            return ContextExtKt.hostEnv().developSettings();
        }
    });
    public final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<APIHost>() { // from class: com.vega.launcher.network.interceptors.AssistInterceptor$host$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final APIHost invoke() {
            return this.e.a().host();
        }
    });

    /* renamed from: c, reason: collision with root package name */
    public final Lazy f107401c = LazyKt__LazyJVMKt.lazy(new Function0<Map<String, ? extends String>>() { // from class: com.vega.launcher.network.interceptors.AssistInterceptor$boeHostMap$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Map<String, ? extends String> invoke() {
            return MapsKt__MapsKt.mapOf(TuplesKt.to(this.e.b().f79178a, this.e.b().f79178a + this.e.a().boeSuffix()), TuplesKt.to(this.e.b().f79180d, this.e.b().f79180d + this.e.a().boeSuffix()), TuplesKt.to(this.e.b().f79181g, this.e.b().f79181g + this.e.a().boeSuffix()), TuplesKt.to(this.e.b().f, this.e.b().f + this.e.a().boeSuffix()), TuplesKt.to(this.e.b().h, this.e.b().h + this.e.a().boeSuffix()), TuplesKt.to(this.e.b().i, this.e.b().j));
        }
    });

    public final DevelopSetting a() {
        return (DevelopSetting) this.f107400a.getValue();
    }

    public final APIHost b() {
        return (APIHost) this.b.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00b0  */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.bytedance.retrofit2.SsResponse<?> intercept(com.bytedance.retrofit2.intercept.Interceptor.Chain r10) {
        /*
            r9 = this;
            java.lang.String r4 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r4)
            com.bytedance.retrofit2.client.Request r6 = r10.request()
            com.lm.components.network.extra.NetworkRuntimeConfig r0 = com.lm.components.network.extra.NetworkRuntimeConfig.f60924a
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            r0.getClass()
            boolean r0 = com.lm.components.network.extra.NetworkRuntimeConfig.a(r6)
            if (r0 == 0) goto L1f
            com.bytedance.retrofit2.SsResponse r0 = r10.proceed(r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            return r0
        L1f:
            com.bytedance.retrofit2.client.Request$Builder r5 = r6.newBuilder()
            com.vega.core.context.debug.DevelopSetting r0 = r9.a()
            boolean r0 = r0.openBOE()
            if (r0 == 0) goto L8a
            kotlin.Lazy r0 = r9.f107401c
            java.lang.Object r0 = r0.getValue()
            java.util.Map r0 = (java.util.Map) r0
            java.util.Set r1 = r0.keySet()
            java.lang.String r0 = r6.getHost()
            boolean r0 = r1.contains(r0)
            if (r0 == 0) goto L8a
            kotlin.Lazy r0 = r9.f107401c
            java.lang.Object r1 = r0.getValue()
            java.util.Map r1 = (java.util.Map) r1
            java.lang.String r0 = r6.getHost()
            java.lang.Object r2 = r1.get(r0)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto Lb0
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r2)
            r0 = r0 ^ 1
            if (r0 == 0) goto Lb0
            java.lang.String r1 = r6.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.lang.String r0 = r6.getHost()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            java.lang.String r2 = X.C93472yG.H(r1, r0, r2)
            if (r2 == 0) goto Lb0
        L73:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            boolean r0 = X.C93472yG.o(r2, r4)
            if (r0 == 0) goto L87
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            java.lang.String r1 = "https"
            java.lang.String r0 = "http"
            java.lang.String r2 = X.C93472yG.H(r2, r1, r0)
        L87:
            r5.url(r2)
        L8a:
            java.lang.String r1 = r6.getUrl()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            java.lang.String r0 = "open.tiktokapis"
            boolean r0 = X.C93472yG.o(r1, r0)
            if (r0 == 0) goto Lb5
            java.lang.String r1 = r6.getMethod()
            com.bytedance.retrofit2.mime.TypedOutput r0 = r6.getBody()
            r5.method(r1, r0)
            com.bytedance.retrofit2.client.Request r0 = r5.build()
            com.bytedance.retrofit2.SsResponse r0 = r10.proceed(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            return r0
        Lb0:
            java.lang.String r2 = r6.getUrl()
            goto L73
        Lb5:
            java.lang.String r1 = r6.getHost()
            java.lang.String r0 = com.vega.libgecko.GeckoConstantKt.f110060c
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L128
            com.vega.core.context.debug.DevelopSetting r0 = r9.a()
            java.util.Map r3 = r0.geckoHeaders()
        Lc9:
            com.vega.core.privacy.looki.LookiSpManager r0 = com.vega.core.privacy.looki.LookiSpManager.f79474a
            r0.getClass()
            android.content.SharedPreferences r2 = com.vega.core.privacy.looki.LookiSpManager.d()
            java.lang.String r1 = "looki_flow_diff_switch"
            r0 = 0
            boolean r2 = r2.getBoolean(r1, r0)
            if (r2 == 0) goto L123
            java.lang.String r1 = "x-looki-diff"
            java.lang.String r0 = "1"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r1, r0)
            java.util.Map r8 = kotlin.collections.MapsKt__MapsJVMKt.mapOf(r0)
        Le7:
            boolean r0 = r3.isEmpty()
            r0 = r0 ^ 1
            if (r0 != 0) goto Lf1
            if (r2 == 0) goto L15d
        Lf1:
            java.util.ArrayList r7 = new java.util.ArrayList
            java.util.List r0 = r6.getHeaders()
            r7.<init>(r0)
            java.util.Set r0 = r3.entrySet()
            java.util.Iterator r3 = r0.iterator()
        L102:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L131
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            com.bytedance.retrofit2.client.Header r2 = new com.bytedance.retrofit2.client.Header
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r2.<init>(r1, r0)
            r7.add(r2)
            goto L102
        L123:
            java.util.Map r8 = kotlin.collections.MapsKt__MapsKt.emptyMap()
            goto Le7
        L128:
            com.vega.core.context.debug.DevelopSetting r0 = r9.a()
            java.util.Map r3 = r0.userHeaders()
            goto Lc9
        L131:
            java.util.Set r0 = r8.entrySet()
            java.util.Iterator r3 = r0.iterator()
        L139:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L15a
            java.lang.Object r0 = r3.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            com.bytedance.retrofit2.client.Header r2 = new com.bytedance.retrofit2.client.Header
            java.lang.Object r1 = r0.getKey()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.Object r0 = r0.getValue()
            java.lang.String r0 = (java.lang.String) r0
            r2.<init>(r1, r0)
            r7.add(r2)
            goto L139
        L15a:
            r5.headers(r7)
        L15d:
            java.lang.String r1 = r6.getMethod()
            com.bytedance.retrofit2.mime.TypedOutput r0 = r6.getBody()
            r5.method(r1, r0)
            com.bytedance.retrofit2.client.Request r0 = r5.build()
            com.bytedance.retrofit2.SsResponse r0 = r10.proceed(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.launcher.network.interceptors.AssistInterceptor.intercept(com.bytedance.retrofit2.intercept.Interceptor$Chain):com.bytedance.retrofit2.SsResponse");
    }
}
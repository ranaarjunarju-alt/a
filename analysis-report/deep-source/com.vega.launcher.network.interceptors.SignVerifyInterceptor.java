package com.vega.launcher.network.interceptors;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.vega.core.net.SResponse;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.util.CheckerScene;
import com.vega.util.SignChecker;
import com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes35.dex */
public final class SignVerifyInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final Companion f107412a = new Companion();

    /* loaded from: classes12.dex */
    public static final class Companion {
        public static String a(Request request, SResponse sResponse) {
            ArrayList arrayList = new ArrayList();
            Header firstHeader = request.getFirstHeader("appid");
            if (firstHeader != null) {
                arrayList.add(TuplesKt.to(firstHeader.getName(), firstHeader.getValue()));
            }
            Header firstHeader2 = request.getFirstHeader("appvr");
            if (firstHeader2 != null) {
                arrayList.add(TuplesKt.to(firstHeader2.getName(), firstHeader2.getValue()));
            }
            Header firstHeader3 = request.getFirstHeader("commerce-sign-version");
            if (firstHeader3 != null) {
                arrayList.add(TuplesKt.to(firstHeader3.getName(), firstHeader3.getValue()));
            }
            Header firstHeader4 = request.getFirstHeader("device-time");
            if (firstHeader4 != null) {
                arrayList.add(TuplesKt.to(firstHeader4.getName(), firstHeader4.getValue()));
            }
            Header firstHeader5 = request.getFirstHeader("lan");
            if (firstHeader5 != null) {
                arrayList.add(TuplesKt.to(firstHeader5.getName(), firstHeader5.getValue()));
            }
            Header firstHeader6 = request.getFirstHeader("loc");
            if (firstHeader6 != null) {
                arrayList.add(TuplesKt.to(firstHeader6.getName(), firstHeader6.getValue()));
            }
            Header firstHeader7 = request.getFirstHeader("pf");
            if (firstHeader7 != null) {
                arrayList.add(TuplesKt.to(firstHeader7.getName(), firstHeader7.getValue()));
            }
            arrayList.add(TuplesKt.to("response", sResponse.getResponse()));
            arrayList.add(TuplesKt.to("ret", sResponse.getRet()));
            arrayList.add(TuplesKt.to("systime", String.valueOf(sResponse.getServerTime())));
            Header firstHeader8 = request.getFirstHeader("tdid");
            if (firstHeader8 != null) {
                arrayList.add(TuplesKt.to(firstHeader8.getName(), firstHeader8.getValue()));
            }
            ArrayList arrayList2 = new ArrayList();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (((CharSequence) ((Pair) next).getSecond()).length() > 0) {
                    arrayList2.add(next);
                }
            }
            return CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "&", null, null, 0, null, new Function1<Pair<? extends String, ? extends String>, CharSequence>() { // from class: com.vega.launcher.network.interceptors.SignVerifyInterceptor$Companion$getSignStrV1$10
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

        public final synchronized boolean b(Request request, SResponse<?> sResponse) {
            CheckerScene checkerScene;
            Intrinsics.checkNotNullParameter(request, "");
            Intrinsics.checkNotNullParameter(sResponse, "");
            SignChecker signChecker = SignChecker.f135196a;
            checkerScene = CheckerScene.b;
            signChecker.getClass();
            return SignChecker.a(checkerScene).a(a(request, sResponse), sResponse.getSign());
        }
    }

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
        Request request2 = chain.request();
        Request.Builder builderNewBuilder = request2.newBuilder();
        ArrayList arrayList = new ArrayList(request2.getHeaders());
        arrayList.add(new Header("commerce-sign-version", BusinessPhotoTemplateOptEntity.V1));
        builderNewBuilder.headers(arrayList);
        builderNewBuilder.method(request2.getMethod(), request2.getBody());
        Request requestBuild = builderNewBuilder.build();
        SsResponse<?> ssResponseProceed2 = chain.proceed(requestBuild);
        Object objBody = ssResponseProceed2.body();
        if (ssResponseProceed2.isSuccessful() && (objBody instanceof SResponse)) {
            Companion companion = f107412a;
            Intrinsics.checkNotNull(requestBuild);
            boolean zB = companion.b(requestBuild, (SResponse) objBody);
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("SignVerifyInterceptor", "verify result: " + zB);
            }
            if (!zB) {
                throw new Exception("9527003");
            }
            Intrinsics.checkNotNull(ssResponseProceed2);
        } else {
            Intrinsics.checkNotNull(ssResponseProceed2);
        }
        return ssResponseProceed2;
    }
}
package com.vega.subscription.sdk;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.performance.setting.PerformanceProvider;
import com.vega.util.Checker;
import com.vega.util.CheckerScene;
import com.vega.util.SignChecker;
import com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class CommerceSignVerifyInterceptor implements Interceptor {

    /* loaded from: classes31.dex */
    public static final class VerifyInfo {

        /* renamed from: a, reason: collision with root package name */
        public final String f131458a;
        public final String b;

        /* renamed from: c, reason: collision with root package name */
        public final String f131459c;

        /* renamed from: d, reason: collision with root package name */
        public final String f131460d;

        public VerifyInfo(String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            Intrinsics.checkNotNullParameter(str3, "");
            Intrinsics.checkNotNullParameter(str4, "");
            this.f131458a = str;
            this.b = str2;
            this.f131459c = str3;
            this.f131460d = str4;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof VerifyInfo)) {
                return false;
            }
            VerifyInfo verifyInfo = (VerifyInfo) obj;
            return Intrinsics.areEqual(this.f131458a, verifyInfo.f131458a) && Intrinsics.areEqual(this.b, verifyInfo.b) && Intrinsics.areEqual(this.f131459c, verifyInfo.f131459c) && Intrinsics.areEqual(this.f131460d, verifyInfo.f131460d);
        }

        public final int hashCode() {
            return (((((this.f131458a.hashCode() * 31) + this.b.hashCode()) * 31) + this.f131459c.hashCode()) * 31) + this.f131460d.hashCode();
        }

        public final String toString() {
            return "VerifyInfo(sign=" + this.f131458a + ", responseStr=" + this.b + ", retStr=" + this.f131459c + ", sysTimeStr=" + this.f131460d + ')';
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    public static VerifyInfo a(String str) {
        if (StringsKt__StringsKt.isBlank(str)) {
            return null;
        }
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            jsonReader.beginObject();
            String strNextString = "";
            String strNextString2 = "";
            String strNextString3 = "";
            String strNextString4 = "";
            while (jsonReader.hasNext()) {
                String strNextName = jsonReader.nextName();
                if (jsonReader.peek() == JsonToken.NULL) {
                    jsonReader.nextNull();
                } else {
                    if (strNextName != null) {
                        switch (strNextName.hashCode()) {
                            case -1737366758:
                                if (!strNextName.equals("systime")) {
                                    break;
                                } else {
                                    strNextString4 = jsonReader.nextString();
                                    Intrinsics.checkNotNullExpressionValue(strNextString4, "");
                                    break;
                                }
                            case -340323263:
                                if (!strNextName.equals("response")) {
                                    break;
                                } else {
                                    strNextString2 = jsonReader.nextString();
                                    Intrinsics.checkNotNullExpressionValue(strNextString2, "");
                                    break;
                                }
                            case 112801:
                                if (!strNextName.equals("ret")) {
                                    break;
                                } else {
                                    strNextString3 = jsonReader.nextString();
                                    Intrinsics.checkNotNullExpressionValue(strNextString3, "");
                                    break;
                                }
                            case 3530173:
                                if (!strNextName.equals("sign")) {
                                    break;
                                } else {
                                    strNextString = jsonReader.nextString();
                                    Intrinsics.checkNotNullExpressionValue(strNextString, "");
                                    break;
                                }
                        }
                    }
                    jsonReader.skipValue();
                }
            }
            return new VerifyInfo(strNextString, strNextString2, strNextString3, strNextString4);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String c(Request request, VerifyInfo verifyInfo) {
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
        arrayList.add(TuplesKt.to("response", verifyInfo.b));
        arrayList.add(TuplesKt.to("ret", verifyInfo.f131459c));
        arrayList.add(TuplesKt.to("systime", verifyInfo.f131460d));
        Header firstHeader8 = request.getFirstHeader("tdid");
        if (firstHeader8 != null) {
            arrayList.add(TuplesKt.to(firstHeader8.getName(), firstHeader8.getValue()));
        }
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        boolean z = true;
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            if (((CharSequence) pair.getSecond()).length() > 0) {
                if (!z) {
                    sb.append("&");
                }
                sb.append((String) pair.getFirst());
                sb.append("=");
                sb.append((String) pair.getSecond());
                z = false;
            }
        }
        String string = sb.toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return string;
    }

    public final String b(Request request, JSONObject jSONObject) {
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
        arrayList.add(TuplesKt.to("response", jSONObject.optString("response")));
        arrayList.add(TuplesKt.to("ret", jSONObject.optString("ret")));
        arrayList.add(TuplesKt.to("systime", jSONObject.optString("systime")));
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
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "&", null, null, 0, null, new Function1<Pair<? extends String, ? extends String>, CharSequence>() { // from class: com.vega.subscription.sdk.CommerceSignVerifyInterceptor$getSignStrV1$10
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

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) throws Exception {
        Object objCreateFailure;
        boolean zA;
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
        Request.Builder builderNewBuilder = request.newBuilder();
        ArrayList arrayList = new ArrayList(request.getHeaders());
        arrayList.add(new Header("commerce-sign-version", BusinessPhotoTemplateOptEntity.V1));
        builderNewBuilder.headers(arrayList);
        builderNewBuilder.method(request.getMethod(), request.getBody());
        Request requestBuild = builderNewBuilder.build();
        SsResponse<?> ssResponseProceed2 = chain.proceed(requestBuild);
        Object objBody = ssResponseProceed2.body();
        if (ssResponseProceed2.isSuccessful() && (objBody instanceof String)) {
            Intrinsics.checkNotNull(requestBuild);
            String str = (String) objBody;
            synchronized (this) {
                PerformanceProvider.f126381a.getClass();
                if (PerformanceProvider.StartupMemoryOptMask.f126389d.a(PerformanceProvider.F())) {
                    VerifyInfo verifyInfoA = a(str);
                    if (verifyInfoA == null) {
                        zA = true;
                    } else {
                        SignChecker signChecker = SignChecker.f135196a;
                        CheckerScene checkerScene = CheckerScene.b;
                        signChecker.getClass();
                        zA = SignChecker.a(checkerScene).a(c(requestBuild, verifyInfoA), verifyInfoA.f131458a);
                    }
                } else {
                    try {
                        objCreateFailure = new JSONObject(str);
                        Result.m17090constructorimpl(objCreateFailure);
                    } catch (Throwable th) {
                        objCreateFailure = ResultKt.createFailure(th);
                        Result.m17090constructorimpl(objCreateFailure);
                    }
                    if (Result.m17096isFailureimpl(objCreateFailure)) {
                        objCreateFailure = null;
                    }
                    JSONObject jSONObject = (JSONObject) objCreateFailure;
                    if (jSONObject == null) {
                        zA = true;
                    } else {
                        SignChecker signChecker2 = SignChecker.f135196a;
                        CheckerScene checkerScene2 = CheckerScene.b;
                        signChecker2.getClass();
                        Checker checkerA = SignChecker.a(checkerScene2);
                        String strB = b(requestBuild, jSONObject);
                        String strOptString = jSONObject.optString("sign");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "");
                        zA = checkerA.a(strB, strOptString);
                    }
                }
            }
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("CommerceSignVerifyInterceptor", "verify result: " + zA);
            }
            if (!zA) {
                throw new Exception("9527003");
            }
            Intrinsics.checkNotNull(ssResponseProceed2);
        } else {
            Intrinsics.checkNotNull(ssResponseProceed2);
        }
        return ssResponseProceed2;
    }
}
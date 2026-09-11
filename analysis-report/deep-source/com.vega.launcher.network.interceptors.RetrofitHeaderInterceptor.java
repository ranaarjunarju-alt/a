package com.vega.launcher.network.interceptors;

import android.net.Uri;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.lm.components.logservice.alog.BLog;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.ss.android.ugc.effectmanager.common.utils.MD5Utils;
import com.vega.core.app.AppContext;
import com.vega.core.context.ContextExtHelper;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.SPIService;
import com.vega.core.data.NewUserType;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.net.ILogIdProvider;
import com.vega.core.net.IServerTimeProvider;
import com.vega.core.utils.ApkUtil;
import com.vega.core.utils.FlavorLocale;
import com.vega.core.utils.TimeUtil;
import com.vega.main.MainSettings;
import com.vega.main.questionnaire.ab.LocalQuestionnaireHelperV4;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.report.AppLogManagerWrapper;
import com.vega.report.ReportManagerWrapper;
import com.vega.util.FeedbackRecordUtils;
import com.vega.ve.api.VESDKHelper;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class RetrofitHeaderInterceptor implements Interceptor {

    /* renamed from: d, reason: collision with root package name */
    public static final Companion f107409d = new Companion();

    /* renamed from: a, reason: collision with root package name */
    public final AppContext f107410a;
    public String b;

    /* renamed from: c, reason: collision with root package name */
    public final Set<String> f107411c;

    /* loaded from: classes28.dex */
    public static final class Companion {
        public static String a(String str, String str2, String str3, String str4, String str5) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            Intrinsics.checkNotNullParameter(str3, "");
            Intrinsics.checkNotNullParameter(str4, "");
            Intrinsics.checkNotNullParameter(str5, "");
            String path = URI.create(str2).getPath();
            if (path.length() > 7) {
                Intrinsics.checkNotNull(path);
                path = path.substring(path.length() - 7);
                Intrinsics.checkNotNullExpressionValue(path, "");
            }
            return path + '|' + str4 + '|' + str5 + '|' + str + '|' + str3;
        }

        public static String b(String str, String str2, String str3, String str4) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            Intrinsics.checkNotNullParameter(str3, "");
            Intrinsics.checkNotNullParameter(str4, "");
            String path = URI.create(str2).getPath();
            if (path.length() > 7) {
                Intrinsics.checkNotNull(path);
                path = path.substring(path.length() - 7);
                Intrinsics.checkNotNullExpressionValue(path, "");
            }
            String mD5String = MD5Utils.getMD5String("9e2c|" + path + "|0|" + str4 + '|' + str + '|' + str3 + "|11ac");
            Intrinsics.checkNotNullExpressionValue(mD5String, "");
            return mD5String;
        }
    }

    /* loaded from: classes37.dex */
    public /* synthetic */ class WhenMappings {
        static {
            NewUserType.values();
        }
    }

    public RetrofitHeaderInterceptor(AppContext appContext) {
        Intrinsics.checkNotNullParameter(appContext, "");
        this.f107410a = appContext;
        this.f107411c = SetsKt__SetsKt.mutableSetOf("/lv/v1/custom_service/get_entrance", "/lv/v1/custom_service/get_entrance_allow_guest", "/commerce/v1/multi_resource_position");
    }

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        String strH;
        String str;
        Object objCreateFailure;
        ILogIdProvider iLogIdProvider;
        IServerTimeProvider iServerTimeProvider;
        Object next;
        String value;
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
        String url = chain.request().getUrl();
        Intrinsics.checkNotNull(url);
        String str2 = this.b;
        if (str2 == null || str2.length() == 0) {
            VESDKHelper.f135280a.getClass();
            if (VESDKHelper.f135281c) {
                try {
                    this.b = VESDKHelper.a();
                    Result.m17090constructorimpl(Unit.INSTANCE);
                } catch (Throwable th) {
                    Result.m17090constructorimpl(ResultKt.createFailure(th));
                }
            }
        }
        String str3 = this.b;
        LocalQuestionnaireHelperV4.f114649a.getClass();
        String str4 = LocalQuestionnaireHelperV4.Y;
        String str5 = LocalQuestionnaireHelperV4.Z;
        Uri.Builder builderBuildUpon = Uri.parse(url).buildUpon();
        if (str3 == null || str3.length() == 0) {
            try {
                String strG = ContextExtKt.hostEnv().appProperty().G();
                if (ExtentionKt.isNotNullOrEmpty(strG)) {
                    builderBuildUpon.appendQueryParameter("effect_sdk_version", strG);
                }
                BLog.i("appendQuery", "effectSdkVersion=" + strG);
                Result.m17090constructorimpl(Unit.INSTANCE);
            } catch (Throwable th2) {
                Result.m17090constructorimpl(ResultKt.createFailure(th2));
            }
        } else {
            builderBuildUpon.appendQueryParameter("effect_sdk_version", str3);
        }
        builderBuildUpon.appendQueryParameter("subdivision_id", ContextExtKt.hostEnv().appProperty().w());
        if (str4.length() > 0) {
            builderBuildUpon.appendQueryParameter("user_type", str4);
        }
        if (str5.length() > 0) {
            builderBuildUpon.appendQueryParameter("user_type", str5);
        }
        String string = builderBuildUpon.build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        Intrinsics.checkNotNull(string);
        if (StringsKt__StringsJVMKt.startsWith$default(string, "http://", false, 2, null)) {
            List<Header> headers = request.getHeaders();
            Intrinsics.checkNotNullExpressionValue(headers, "");
            Iterator<T> it = headers.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
                if (Intrinsics.areEqual(((Header) next).getName(), "Referer")) {
                    break;
                }
            }
            Header header = (Header) next;
            if (header == null || (value = header.getValue()) == null) {
                value = "";
            }
            Intrinsics.checkNotNull(string);
            ReportManagerWrapper.INSTANCE.onEvent("http_req_collect", MapsKt__MapsKt.mapOf(TuplesKt.to("url", string), TuplesKt.to("referer", value)));
        }
        Intrinsics.checkNotNull(string);
        ArrayList arrayList = new ArrayList();
        ContextExtKt.app().a();
        String strValueOf = String.valueOf(0);
        String strValueOf2 = String.valueOf(ContextExtKt.app().s());
        String strValueOf3 = String.valueOf(System.currentTimeMillis() / 1000);
        String serverDeviceId = AppLogManagerWrapper.INSTANCE.getServerDeviceId();
        if (TextUtils.isEmpty(serverDeviceId)) {
            serverDeviceId = this.f107410a.getDeviceId();
        }
        if (CollectionsKt___CollectionsKt.contains(this.f107411c, Uri.parse(string).getPath())) {
            FlavorLocale.f79592a.getClass();
            strH = FlavorLocale.i(false);
        } else {
            FlavorLocale.f79592a.getClass();
            strH = FlavorLocale.h();
        }
        arrayList.add(new Header("lan", strH));
        FlavorLocale.f79592a.getClass();
        arrayList.add(new Header("loc", FlavorLocale.b()));
        arrayList.add(new Header("pf", strValueOf));
        arrayList.add(new Header("vr", strValueOf2));
        arrayList.add(new Header("appvr", this.f107410a.getVersion()));
        arrayList.add(new Header("vc", String.valueOf(this.f107410a.getVersionCode())));
        arrayList.add(new Header("device-time", strValueOf3));
        arrayList.add(new Header("tdid", serverDeviceId));
        arrayList.add(new Header("sign-ver", ProfileManager.VERSION));
        Companion companion = f107409d;
        String version = this.f107410a.getVersion();
        companion.getClass();
        arrayList.add(new Header("sign", Companion.b(strValueOf3, string, serverDeviceId, version)));
        ContextExtHelper.b.A();
        arrayList.add(new Header("app-sdk-version", "184.0.0"));
        arrayList.add(new Header("appid", String.valueOf(this.f107410a.getAid())));
        arrayList.add(new Header("header-content", Companion.a(strValueOf3, string, serverDeviceId, strValueOf, this.f107410a.getVersion())));
        ApkUtil.f79541a.getClass();
        arrayList.add(new Header("host-abi", Process.is64Bit() ? "64" : "32"));
        Intrinsics.checkNotNullParameter(this.f107410a.getContext(), "");
        if (System.currentTimeMillis() - this.f107410a.getContext().getPackageManager().getPackageInfo(this.f107410a.getContext().getPackageName(), 0).firstInstallTime < 604800000) {
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("getRequestHeader", "new user add header: " + ContextExtKt.hostEnv().launchInfo().e());
            }
            int iOrdinal = ContextExtKt.hostEnv().launchInfo().e().ordinal();
            if (iOrdinal == 1) {
                arrayList.add(new Header("cc-newuser-channel", "tiktok"));
            } else if (iOrdinal != 2) {
                arrayList.add(new Header("cc-newuser-channel", "common"));
            } else {
                arrayList.add(new Header("cc-newuser-channel", "instagram"));
            }
        }
        if (!ContextExtKt.app().h()) {
            arrayList.add(new Header("cc-user-mode", ProfileManager.VERSION));
        }
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((Header) it2.next()).getName());
        }
        List<Header> headers2 = chain.request().getHeaders();
        Intrinsics.checkNotNullExpressionValue(headers2, "");
        ArrayList arrayList3 = new ArrayList();
        for (Object obj : headers2) {
            if (!arrayList2.contains(((Header) obj).getName())) {
                arrayList3.add(obj);
            }
        }
        Iterator it3 = arrayList3.iterator();
        while (it3.hasNext()) {
            arrayList.add(it3.next());
        }
        Request.Builder builderNewBuilder = request.newBuilder();
        builderNewBuilder.headers(arrayList);
        builderNewBuilder.url(string);
        builderNewBuilder.method(request.getMethod(), request.getBody());
        SsResponse<?> ssResponseProceed2 = chain.proceed(builderNewBuilder.build());
        if (ssResponseProceed2.isSuccessful()) {
            Object objBody = ssResponseProceed2.body();
            if ((objBody instanceof IServerTimeProvider) && (iServerTimeProvider = (IServerTimeProvider) objBody) != null) {
                TimeUtil timeUtil = TimeUtil.f79683a;
                iServerTimeProvider.getServerTime();
                timeUtil.getClass();
            }
            URL url2 = new URL(string);
            if (((MainSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(MainSettings.class), null)).getFeedbackWithLogIdConfig().a().contains(url2.getPath())) {
                Object objBody2 = ssResponseProceed2.body();
                if ((objBody2 instanceof ILogIdProvider) && (iLogIdProvider = (ILogIdProvider) objBody2) != null && ExtentionKt.isNotNullOrEmpty(iLogIdProvider.getLogId())) {
                    FeedbackRecordUtils feedbackRecordUtils = FeedbackRecordUtils.f135100a;
                    String path = url2.getPath();
                    Intrinsics.checkNotNullExpressionValue(path, "");
                    String logId = iLogIdProvider.getLogId();
                    feedbackRecordUtils.getClass();
                    Intrinsics.checkNotNullParameter(logId, "");
                    FeedbackRecordUtils.b.p(path, logId, false);
                }
                Object objBody3 = ssResponseProceed2.body();
                if ((objBody3 instanceof String) && (str = (String) objBody3) != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        String strOptString = jSONObject.optString("log_id");
                        if (strOptString == null) {
                            strOptString = jSONObject.optString("logid");
                        }
                        if (ExtentionKt.isNotNullOrEmpty(strOptString)) {
                            FeedbackRecordUtils feedbackRecordUtils2 = FeedbackRecordUtils.f135100a;
                            String path2 = url2.getPath();
                            Intrinsics.checkNotNullExpressionValue(path2, "");
                            Intrinsics.checkNotNull(strOptString);
                            feedbackRecordUtils2.getClass();
                            Intrinsics.checkNotNullParameter(strOptString, "");
                            FeedbackRecordUtils.b.p(path2, strOptString, false);
                        }
                        objCreateFailure = Unit.INSTANCE;
                        Result.m17090constructorimpl(objCreateFailure);
                    } catch (Throwable th3) {
                        objCreateFailure = ResultKt.createFailure(th3);
                        Result.m17090constructorimpl(objCreateFailure);
                    }
                    Result.m17089boximpl(objCreateFailure);
                }
            }
        }
        Intrinsics.checkNotNull(ssResponseProceed2);
        return ssResponseProceed2;
    }
}
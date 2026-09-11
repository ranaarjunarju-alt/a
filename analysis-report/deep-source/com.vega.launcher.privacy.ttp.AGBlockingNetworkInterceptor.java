package com.vega.launcher.privacy.ttp;

import android.net.Uri;
import com.bytedance.frameworks.baselib.network.http.BaseRequestContext;
import com.bytedance.privacy.dispatch.DispatchManager;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.ttnet.http.RequestContext;
import com.vega.report.ReportManagerWrapper;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class AGBlockingNetworkInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final AGBlockingNetworkInterceptor f107475a = new AGBlockingNetworkInterceptor();

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        String host;
        String path;
        Intrinsics.checkNotNullParameter(chain, "");
        Request request = chain.request();
        if (request.getExtraInfo() == null) {
            request.setExtraInfo(new RequestContext());
        }
        Object extraInfo = request.getExtraInfo();
        BaseRequestContext baseRequestContext = extraInfo instanceof RequestContext ? (BaseRequestContext) extraInfo : null;
        if (baseRequestContext != null) {
            baseRequestContext.read_error_response = true;
        }
        try {
            SsResponse<?> ssResponseProceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
            return ssResponseProceed;
        } catch (Throwable th) {
            AGBlockingMonitor.f.getClass();
            if (DispatchManager.INSTANCE.isUSTTP()) {
                TTPSdkConfig.f107490a.getClass();
                if (TTPSdkConfig.b.a()) {
                    AGBlockingMonitor aGBlockingMonitor = new AGBlockingMonitor(th, request.getMethod());
                    if (aGBlockingMonitor.b) {
                        try {
                            Uri uri = Uri.parse(aGBlockingMonitor.f107474d);
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put("ag_code", aGBlockingMonitor.f107473c);
                            if (uri == null || (host = uri.getHost()) == null) {
                                host = "";
                            }
                            jSONObject.put("req_host", host);
                            if (uri == null || (path = uri.getPath()) == null) {
                                path = "";
                            }
                            jSONObject.put("req_path", path);
                            String str = aGBlockingMonitor.f107472a;
                            if (str == null) {
                                str = "";
                            }
                            jSONObject.put("req_method", str);
                            String str2 = aGBlockingMonitor.e;
                            jSONObject.put("error_msg", str2 != null ? str2 : "");
                            ReportManagerWrapper.INSTANCE.onEvent("ag_blocking_monitor", jSONObject);
                            Result.m17090constructorimpl(Unit.INSTANCE);
                        } catch (Throwable th2) {
                            Result.m17090constructorimpl(ResultKt.createFailure(th2));
                        }
                    }
                }
            }
            throw th;
        }
    }
}
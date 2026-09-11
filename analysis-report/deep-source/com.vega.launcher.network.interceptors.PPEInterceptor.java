package com.vega.launcher.network.interceptors;

import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.lemon.host.config.CCPPEDomains;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.privacy.looki.LookiSpManager;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes20.dex */
public final class PPEInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final Companion f107408a = new Companion();

    /* loaded from: classes11.dex */
    public static final class Companion {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    static {
        new CCPPEDomains(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r0v12, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public PPEInterceptor() {
        LookiSpManager.f79474a.getClass();
        String string = LookiSpManager.d().getString("looki_ppe_settings", "");
        String str = string != null ? string : "";
        if (str.length() > 0) {
            try {
                CCPPEDomains cCPPEDomains = (CCPPEDomains) ExtentionKt.getGson().fromJson(str, CCPPEDomains.class);
                if (cCPPEDomains == null) {
                    new CCPPEDomains(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
                } else {
                    Intrinsics.checkNotNull(cCPPEDomains);
                }
                Result.m17090constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.m17090constructorimpl(ResultKt.createFailure(th));
            }
        }
    }

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "");
        SsResponse<?> ssResponseProceed = chain.proceed(chain.request());
        Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
        return ssResponseProceed;
    }
}
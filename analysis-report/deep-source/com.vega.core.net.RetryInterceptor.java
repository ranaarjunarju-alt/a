package com.vega.core.net;

import com.bytedance.frameworks.baselib.network.http.exception.NetworkNotAvailabeException;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.vega.log.BLog;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes27.dex */
public final class RetryInterceptor implements Interceptor {

    /* renamed from: a, reason: collision with root package name */
    public final int f79364a;
    public final long b;

    /* loaded from: classes4.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public RetryInterceptor(int i, long j) {
        this.f79364a = i;
        this.b = j;
    }

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) throws Exception {
        Intrinsics.checkNotNullParameter(chain, "");
        Request request = chain.request();
        Exception e = null;
        int i = 0;
        while (i < this.f79364a) {
            try {
                SsResponse<?> ssResponseProceed = chain.proceed(request);
                Intrinsics.checkNotNull(ssResponseProceed);
                return ssResponseProceed;
            } catch (Exception e2) {
                e = e2;
                long j = this.b;
                if (j > 0) {
                    try {
                        Thread.sleep(j);
                    } catch (InterruptedException e3) {
                        BLog.w("RetryInterceptor", "sleep exception:" + e3.getMessage());
                    }
                }
                i++;
                BLog.w("RetryInterceptor", "exception:" + e.getMessage());
            }
        }
        if (e != null) {
            throw e;
        }
        throw new NetworkNotAvailabeException("Max retries exceeded");
    }
}
package com.vega.launcher.network.service;

import com.vega.core.api.InterceptorCreatorApi;
import com.vega.core.context.ContextExtKt;
import com.vega.launcher.network.interceptors.RetrofitHeaderInterceptor;

/* loaded from: classes19.dex */
public final class InterceptorCreator implements InterceptorCreatorApi {
    @Override // com.vega.core.api.InterceptorCreatorApi
    public final RetrofitHeaderInterceptor a() {
        return new RetrofitHeaderInterceptor(ContextExtKt.hostEnv().appContext());
    }
}
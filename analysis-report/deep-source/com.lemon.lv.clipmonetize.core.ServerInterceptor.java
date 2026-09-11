package com.lemon.lv.clipmonetize.core;

import com.lemon.lv.clipmonetize.platform.ILoading;
import com.lemon.lv.clipmonetize.platform.KMPLoading;
import com.lemon.lv.clipmonetize.strategy.StrategyViewModel;
import com.lemon.lv.clipmonetize.user.UserViewModel;
import com.lemon.lv.clipmonetize.wrapper.ResourcePayConfig;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineExceptionHandler;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* loaded from: classes6.dex */
public final class ServerInterceptor implements Intercept, CoroutineScope {

    /* renamed from: a, reason: collision with root package name */
    public final StrategyViewModel f58820a;
    public final UserViewModel b;

    public ServerInterceptor(StrategyViewModel strategyViewModel, UserViewModel userViewModel) {
        Intrinsics.checkNotNullParameter(strategyViewModel, "");
        Intrinsics.checkNotNullParameter(userViewModel, "");
        this.f58820a = strategyViewModel;
        this.b = userViewModel;
    }

    public final void a(ResourcePayConfig resourcePayConfig) {
        Intrinsics.checkNotNullParameter(resourcePayConfig, "");
        ILoading iLoading = resourcePayConfig.e;
        if (iLoading == null) {
            iLoading = KMPLoading.b;
        }
        BuildersKt__Builders_commonKt.launch$default(this, new ServerInterceptor$exceptionHandler$$inlined$CoroutineExceptionHandler$1(CoroutineExceptionHandler.Key, iLoading, resourcePayConfig), null, new ServerInterceptor$onIntercept$1(resourcePayConfig, this, iLoading, null), 2, null);
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public final CoroutineContext getCoroutineContext() {
        return Dispatchers.getIO().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null));
    }
}
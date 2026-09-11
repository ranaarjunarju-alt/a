package com.vega.launcher.start.schedule.tasks;

import com.bytedance.lego.init.model.IInitTask;
import com.bytedance.router.RoutesConfig;
import com.bytedance.router.SmartRouter;
import com.lemon.LoginActivityInterceptor;
import com.vega.core.AppConfigConstant;
import com.vega.launcher.ScaffoldApplication;
import com.vega.launcher.privacy.looki.LookiActivityInterceptor;
import com.vega.launcher.router.GlobalRouterHandlerInterceptor;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes4.dex */
public final class InitSmartRouterTask extends IInitTask {

    /* renamed from: a, reason: collision with root package name */
    public static final Companion f107604a = new Companion();

    public static final class Companion {
    }

    @Override // java.lang.Runnable
    public final void run() {
        Companion companion = f107604a;
        ScaffoldApplication scaffoldApplicationA = InitTaskToolsKt.a();
        Intrinsics.checkNotNull(scaffoldApplicationA, "");
        companion.getClass();
        Intrinsics.checkNotNullParameter(scaffoldApplicationA, "");
        SmartRouter.init(scaffoldApplicationA);
        RoutesConfig routesConfigConfigRouter = SmartRouter.configRouter("snssdk3006");
        String lowerCase = "vicut".toLowerCase();
        Intrinsics.checkNotNullExpressionValue(lowerCase, "");
        AppConfigConstant.f79135a.getClass();
        routesConfigConfigRouter.b(new String[]{lowerCase, "CapCut", "capcut", "sslocal", "http", "https", AppConfigConstant.f79136c, AppConfigConstant.f79137d});
        SmartRouter.addInterceptor(new LookiActivityInterceptor());
        SmartRouter.addInterceptor(new LoginActivityInterceptor());
        SmartRouter.addInterceptor(new GlobalRouterHandlerInterceptor());
    }
}
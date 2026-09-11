package com.vega.launcher.init.core.hook;

import android.content.Context;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.TTNetInitMetrics;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.ttnet.utils.RetrofitUtils;
import com.lm.components.core.init.IInitTaskHook;
import com.lm.components.network.NetworkManager;
import com.lm.components.network.config.NetWorkConfigure;
import com.lm.components.network.init.CronetDependAdapter;
import com.ss.android.common.util.ToolUtils;
import com.vega.ad.report.AdLaunchMonitor;
import com.vega.core.app.AppContext;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.debug.DevelopSetting;
import com.vega.core.net.NetworkManagerWrapper;
import com.vega.core.net.TimeoutInterceptor;
import com.vega.core.privacy.looki.LookiCurrentPseudonymStatus;
import com.vega.core.privacy.ttp.CommonParamManager;
import com.vega.effectplatform.artist.net.ArtistCollectInterceptor;
import com.vega.feedx.util.anchor.DeepLinkRequestOptimize;
import com.vega.launcher.flavor.FlavorUtilKt;
import com.vega.launcher.init.core.hook.NetworkInitHook$runBeforeTaskInner$7$1;
import com.vega.launcher.network.interceptors.AssistInterceptor;
import com.vega.launcher.network.interceptors.CrackingInterceptor;
import com.vega.launcher.network.interceptors.IapRegionInterceptor;
import com.vega.launcher.network.interceptors.LynxSignVerifyInterceptor;
import com.vega.launcher.network.interceptors.RetrofitHeaderInterceptor;
import com.vega.launcher.network.interceptors.SignVerifyInterceptor;
import com.vega.launcher.network.interceptors.TimeOutSettingInterceptor;
import com.vega.launcher.sec.SecModuleInit;
import com.vega.libeffectapi.util.VimoNetHeaderInterceptor;
import com.vega.performance.setting.PerformanceProvider;
import com.vega.start.statistic.AppLaunchTracker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* loaded from: classes25.dex */
public final class NetworkInitHook implements IInitTaskHook {

    /* renamed from: c, reason: collision with root package name */
    public static final Companion f107316c = new Companion();

    /* renamed from: d, reason: collision with root package name */
    public static volatile boolean f107317d;
    public static volatile TimeOutSettingInterceptor e;

    /* renamed from: a, reason: collision with root package name */
    public final Context f107318a;
    public final AppContext b;

    /* loaded from: classes4.dex */
    public static final class Companion {
    }

    public NetworkInitHook(Context context, AppContext appContext) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(appContext, "");
        this.f107318a = context;
        this.b = appContext;
    }

    @Override // com.lm.components.core.init.IInitTaskHook
    public final void a() {
        NetworkManager.m.getClass();
        NetWorkConfigure netWorkConfigure = NetworkManager.Companion.a().f60908g;
        Interceptor[] interceptorArr = new Interceptor[9];
        interceptorArr[0] = new RetrofitHeaderInterceptor(this.b);
        interceptorArr[1] = new ArtistCollectInterceptor();
        interceptorArr[2] = new AssistInterceptor();
        interceptorArr[3] = new SignVerifyInterceptor();
        interceptorArr[4] = new LynxSignVerifyInterceptor();
        TimeOutSettingInterceptor timeOutSettingInterceptor = e;
        if (timeOutSettingInterceptor == null) {
            timeOutSettingInterceptor = new TimeOutSettingInterceptor();
        }
        interceptorArr[5] = timeOutSettingInterceptor;
        interceptorArr[6] = new CrackingInterceptor();
        interceptorArr[7] = new IapRegionInterceptor();
        interceptorArr[8] = new TimeoutInterceptor();
        ArrayList arrayListArrayListOf = CollectionsKt__CollectionsKt.arrayListOf(interceptorArr);
        VimoNetHeaderInterceptor.f110032a.a();
        Iterator it = arrayListArrayListOf.iterator();
        while (it.hasNext()) {
            Interceptor interceptor = (Interceptor) it.next();
            netWorkConfigure.getClass();
            Intrinsics.checkNotNullParameter(interceptor, "");
            RetrofitUtils.addInterceptor(interceptor);
        }
        f107317d = true;
        NetworkManagerWrapper.f79356a.getClass();
        NetworkManagerWrapper.o();
        PerformanceProvider.f126381a.getClass();
        if (!PerformanceProvider.I()) {
            DeepLinkRequestOptimize deepLinkRequestOptimize = DeepLinkRequestOptimize.f102425a;
            SettingsInitHook.e.getClass();
            boolean z = SettingsInitHook.f107328g;
            deepLinkRequestOptimize.getClass();
            DeepLinkRequestOptimize.a(z);
        }
        AdLaunchMonitor.f65929a.getClass();
        AdLaunchMonitor.f65930c = AdLaunchMonitor.c();
    }

    @Override // com.lm.components.core.init.IInitTaskHook
    public final void b(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "");
        DevelopSetting developSettingDevelopSettings = ContextExtKt.hostEnv().developSettings();
        Logger.setLogLevel(6);
        AdLaunchMonitor.f65929a.getClass();
        AdLaunchMonitor.b = AdLaunchMonitor.c();
        CronetDependAdapter.Companion companion = CronetDependAdapter.b;
        AppLaunchTracker.f130896a.getClass();
        long j = AppLaunchTracker.f130897c;
        companion.getClass();
        CronetDependAdapter.e = j;
        NetworkManager.m.getClass();
        NetWorkConfigure netWorkConfigure = NetworkManager.Companion.a().f60908g;
        netWorkConfigure.f60909a = false;
        netWorkConfigure.d(this.f107318a, developSettingDevelopSettings.openBOE());
        LookiCurrentPseudonymStatus.j.getClass();
        LookiCurrentPseudonymStatus.Companion.a().getClass();
        FlavorUtilKt.a(netWorkConfigure);
        if (ToolUtils.isMainProcess(this.f107318a)) {
            SecModuleInit.b.a(this.f107318a, this.b);
        }
        if (ToolUtils.isMainProcess(this.f107318a)) {
            HashMap map2 = new HashMap();
            CommonParamManager.f79504a.getClass();
            HashMap mapA = CommonParamManager.a();
            if (!mapA.isEmpty()) {
                map2.putAll(mapA);
            }
            if (!map2.isEmpty()) {
                CronetDependAdapter.f60925c = map2;
            }
            Map<String, String> mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("cc_subdivision_id", ContextExtKt.app().w()));
            Intrinsics.checkNotNullParameter(mapMutableMapOf, "");
            CronetDependAdapter.f60926d = mapMutableMapOf;
        }
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new NetworkInitHook$runBeforeTaskInner$6(this, null), 2, null);
        TTNetInitMetrics.setTTNetInitSuccessCallback(new TTNetInitMetrics.TTNetInitCallback() { // from class: X.0tp
            @Override // com.bytedance.frameworks.baselib.network.TTNetInitMetrics.TTNetInitCallback
            public final void ttnetInitSuccessCallback() {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new NetworkInitHook$runBeforeTaskInner$7$1(null), 2, null);
            }
        });
    }
}
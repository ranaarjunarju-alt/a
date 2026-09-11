package com.vega.launcher.start.schedule.tasks;

import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bytedance.bpea.tt.entry.api.device.info.CarrierEntry;
import com.bytedance.bpea.tt.entry.api.device.info.LocaleEntry;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.common.utility.Logger;
import com.bytedance.frameworks.baselib.network.TTNetInitMetrics;
import com.bytedance.lego.init.LaunchBoostExecutor;
import com.bytedance.lego.init.model.IInitTask;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.ttnet.utils.RetrofitUtils;
import com.lm.components.core.manager.CoreParameterHelper;
import com.lm.components.core.manager.TimeMonitor;
import com.lm.components.core.network.CoreNetWorkConfig;
import com.lm.components.core.network.NetworkDevice;
import com.lm.components.core.network.NetworkLog;
import com.lm.components.core.network.NetworkMonitor;
import com.lm.components.core.network.NetworkReport;
import com.lm.components.network.CommonParams;
import com.lm.components.network.IDependencyComponent;
import com.lm.components.network.INetWorkCallback;
import com.lm.components.network.NetworkManager;
import com.lm.components.network.config.NetWorkConfigure;
import com.lm.components.network.config.NetWorkUrlConfig;
import com.lm.components.network.config.NetworkStrategyConfig;
import com.lm.components.network.init.CronetDependAdapter;
import com.lm.components.privacy.looki.ITncDispatch;
import com.lm.components.privacy.looki.PhoneInfoManager;
import com.ss.android.token.TTTokenManager;
import com.ttnet.org.chromium.net.urlconnection.MessageLoop;
import com.vega.ad.report.AdLaunchMonitor;
import com.vega.core.context.ContextExtKt;
import com.vega.core.net.NetworkManagerWrapper;
import com.vega.core.net.TimeoutInterceptor;
import com.vega.core.privacy.looki.LookiCurrentPseudonymStatus;
import com.vega.core.privacy.looki.TncDispatch;
import com.vega.core.privacy.ttp.CommonParamManager;
import com.vega.core.utils.FlavorLocale;
import com.vega.core.utils.PadUtil;
import com.vega.effectplatform.artist.net.ArtistCollectInterceptor;
import com.vega.feedx.util.anchor.DeepLinkRequestOptimize;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.launcher.ScaffoldApplication;
import com.vega.launcher.init.core.hook.NetworkInitHook;
import com.vega.launcher.init.core.hook.SettingsInitHook;
import com.vega.launcher.network.interceptors.AssistInterceptor;
import com.vega.launcher.network.interceptors.CrackingInterceptor;
import com.vega.launcher.network.interceptors.IapRegionInterceptor;
import com.vega.launcher.network.interceptors.LynxSignVerifyInterceptor;
import com.vega.launcher.network.interceptors.RetrofitHeaderInterceptor;
import com.vega.launcher.network.interceptors.SignVerifyInterceptor;
import com.vega.launcher.network.interceptors.TimeOutSettingInterceptor;
import com.vega.launcher.report.GPUInfoHelper;
import com.vega.launcher.start.schedule.tasks.InitTaskToolsKt;
import com.vega.launcher.start.schedule.tasks.NetworkInitTaskImpl;
import com.vega.launcher.start.schedule.tasks.NetworkInitTaskImpl$beforeHook$7$1;
import com.vega.libeffectapi.util.VimoNetHeaderInterceptor;
import com.vega.log.BLog;
import com.vega.performance.setting.PerformanceProvider;
import com.vega.report.AppLogManagerWrapper;
import com.vega.start.statistic.AppLaunchTracker;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* loaded from: classes25.dex */
public final class NetworkInitTask extends IInitTask {
    @Override // java.lang.Runnable
    public final void run() throws PackageManager.NameNotFoundException {
        NetworkInitTaskImpl networkInitTaskImpl = NetworkInitTaskImpl.f107610a;
        networkInitTaskImpl.getClass();
        MessageLoop.disableVaildThreadAssert(true);
        TimeMonitor timeMonitor = TimeMonitor.f60335a;
        long jCurrentTimeMillis = System.currentTimeMillis();
        timeMonitor.getClass();
        TimeMonitor.j = jCurrentTimeMillis;
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
        netWorkConfigure.d(InitTaskToolsKt.a(), false);
        LookiCurrentPseudonymStatus.j.getClass();
        LookiCurrentPseudonymStatus.Companion.a().getClass();
        if (((Boolean) InitTaskToolsKt.b.getValue()).booleanValue()) {
            HashMap map = new HashMap();
            CommonParamManager.f79504a.getClass();
            HashMap mapA = CommonParamManager.a();
            if (!mapA.isEmpty()) {
                map.putAll(mapA);
            }
            if (!map.isEmpty()) {
                CronetDependAdapter.f60925c = map;
            }
            map.toString();
            Map<String, String> mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("cc_subdivision_id", ContextExtKt.app().w()));
            Intrinsics.checkNotNullParameter(mapMutableMapOf, "");
            CronetDependAdapter.f60926d = mapMutableMapOf;
        }
        AppLogManagerWrapper appLogManagerWrapper = AppLogManagerWrapper.INSTANCE;
        PadUtil.f79639a.getClass();
        boolean zO = PadUtil.o();
        String str = ProfileManager.VERSION;
        appLogManagerWrapper.addCustomCommonParams("is_android_pad", zO ? ProfileManager.VERSION : "0");
        if (!PadUtil.p()) {
            str = "0";
        }
        appLogManagerWrapper.addCustomCommonParams("is_pad_pro_installed", str);
        LaunchBoostExecutor launchBoostExecutor = LaunchBoostExecutor.e;
        Runnable runnable = new Runnable() { // from class: X.0nj
            @Override // java.lang.Runnable
            public final void run() {
                String upperCase;
                AppLogManagerWrapper appLogManagerWrapper2 = AppLogManagerWrapper.INSTANCE;
                PhoneInfoManager phoneInfoManager = PhoneInfoManager.f60995a;
                ScaffoldApplication scaffoldApplicationA = InitTaskToolsKt.a();
                phoneInfoManager.getClass();
                appLogManagerWrapper2.addCustomCommonParams("carrier_region", PhoneInfoManager.c(scaffoldApplicationA));
                NetworkInitTaskImpl.f107610a.getClass();
                try {
                    Object systemService = ModuleCommon.INSTANCE.getApplication().getSystemService("phone");
                    Intrinsics.checkNotNull(systemService, "");
                    CarrierEntry.f.getClass();
                    upperCase = CarrierEntry.Companion.g((TelephonyManager) systemService);
                } catch (Exception e) {
                    BLog.e("NetworkInitTask", "TelephonyManager networkOperator exception:\n" + e.getMessage());
                    upperCase = "";
                }
                if (!TextUtils.isEmpty(upperCase)) {
                    Locale locale = Locale.US;
                    Intrinsics.checkNotNullExpressionValue(locale, "");
                    upperCase = upperCase.toUpperCase(locale);
                    Intrinsics.checkNotNullExpressionValue(upperCase, "");
                }
                appLogManagerWrapper2.addCustomCommonParams("mcc_mnc", upperCase);
                AppLogManagerWrapper appLogManagerWrapper3 = AppLogManagerWrapper.INSTANCE;
                FlavorLocale.f79592a.getClass();
                appLogManagerWrapper3.addCustomCommonParams("region", FlavorLocale.b());
            }
        };
        launchBoostExecutor.getClass();
        LaunchBoostExecutor.a(runnable);
        TTNetInitMetrics.setTTNetInitSuccessCallback(new TTNetInitMetrics.TTNetInitCallback() { // from class: X.0to
            @Override // com.bytedance.frameworks.baselib.network.TTNetInitMetrics.TTNetInitCallback
            public final void ttnetInitSuccessCallback() {
                NetworkInitHook.f107316c.getClass();
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getIO(), null, new NetworkInitTaskImpl$beforeHook$7$1(null), 2, null);
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        CoreNetWorkConfig coreNetWorkConfig = new CoreNetWorkConfig(new INetWorkCallback() { // from class: com.vega.launcher.start.schedule.tasks.NetworkInitTaskImpl$runInner$coreNetWorkConfig$1
            @Override // com.lm.components.network.INetWorkCallback
            public final void a() {
            }

            @Override // com.lm.components.network.INetWorkCallback
            public final void b(List<String> list) {
                Intrinsics.checkNotNullParameter(list, "");
                TTTokenManager.addConfigHost(list);
                Objects.toString(list);
            }
        }, new NetWorkUrlConfig("xxx", new String[]{"tnc-v2-boot.capcutapi.com", "tnc-sg.capcutapi.com"}), new TncDispatch());
        int aid = networkInitTaskImpl.a().getAid();
        String appName = networkInitTaskImpl.a().getAppName();
        if (appName == null) {
            appName = "";
        }
        String channel = networkInitTaskImpl.a().getChannel();
        ContextExtKt.app().l();
        String strValueOf = String.valueOf(19600200);
        ContextExtKt.app().K();
        String strValueOf2 = String.valueOf(networkInitTaskImpl.a().getUpdateVersionCode());
        String strValueOf3 = String.valueOf(networkInitTaskImpl.a().getManifestVersionCode());
        FlavorLocale.f79592a.getClass();
        String strH = FlavorLocale.h();
        CoreParameterHelper.f60333a.getClass();
        String strValueOf4 = String.valueOf(Build.VERSION.SDK_INT);
        String strA = CoreParameterHelper.a();
        String strB = CoreParameterHelper.b();
        LocaleEntry.Companion companion2 = LocaleEntry.b;
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "");
        companion2.getClass();
        String strB2 = LocaleEntry.Companion.b(locale);
        GPUInfoHelper gPUInfoHelper = GPUInfoHelper.f107521a;
        ScaffoldApplication scaffoldApplicationA = InitTaskToolsKt.a();
        gPUInfoHelper.getClass();
        String str2 = GPUInfoHelper.a(scaffoldApplicationA).f107523c;
        NetWorkUrlConfig netWorkUrlConfig = coreNetWorkConfig.b;
        INetWorkCallback iNetWorkCallback = coreNetWorkConfig.f60339a;
        String str3 = coreNetWorkConfig.f60340c;
        ITncDispatch iTncDispatch = coreNetWorkConfig.f60341d;
        PerformanceProvider.f126381a.getClass();
        NetworkManager.Companion.a().p(InitTaskToolsKt.a(), new CommonParams(aid, appName, channel, strValueOf, "19.6.0", strValueOf2, strValueOf3, strH, "0", strValueOf4, strA, strB, "", strB2, str2, "{}", linkedHashMap, netWorkUrlConfig, iNetWorkCallback, str3, iTncDispatch, new NetworkStrategyConfig(PerformanceProvider.w().cronetThreadOpt, PerformanceProvider.w().cronetCorePrioBefore, PerformanceProvider.w().cronetCorePrioAfter, PerformanceProvider.w().cronetIoPrioBefore, PerformanceProvider.w().cronetIoPrioAfter, PerformanceProvider.w().cronetInitPrioBefore, PerformanceProvider.w().cronetInitPrioAfter, PerformanceProvider.w().resetPrioTime, PerformanceProvider.w().cronetInitOpt, PerformanceProvider.w().cronetBindCoreOpt, PerformanceProvider.w().cronetStackSizeOpt)), new IDependencyComponent() { // from class: com.vega.launcher.start.schedule.tasks.NetworkInitTaskImpl$runInner$1
            @Override // com.lm.components.network.IDependencyComponent
            public final NetworkLog a() {
                return new NetworkLog();
            }

            @Override // com.lm.components.network.IDependencyComponent
            public final NetworkDevice b() {
                return NetworkDevice.f60342a;
            }

            @Override // com.lm.components.network.IDependencyComponent
            public final NetworkReport c() {
                return new NetworkReport();
            }

            @Override // com.lm.components.network.IDependencyComponent
            public final NetworkMonitor getMonitor() {
                return new NetworkMonitor();
            }
        });
        NetWorkConfigure netWorkConfigure2 = NetworkManager.Companion.a().f60908g;
        Interceptor[] interceptorArr = new Interceptor[9];
        interceptorArr[0] = new RetrofitHeaderInterceptor(networkInitTaskImpl.a());
        interceptorArr[1] = new ArtistCollectInterceptor();
        interceptorArr[2] = new AssistInterceptor();
        interceptorArr[3] = new SignVerifyInterceptor();
        interceptorArr[4] = new LynxSignVerifyInterceptor();
        TimeOutSettingInterceptor timeOutSettingInterceptor = NetworkInitTaskImpl.f107611c;
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
            netWorkConfigure2.getClass();
            Intrinsics.checkNotNullParameter(interceptor, "");
            RetrofitUtils.addInterceptor(interceptor);
        }
        NetworkInitHook.f107316c.getClass();
        NetworkInitHook.f107317d = true;
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
        TimeMonitor timeMonitor2 = TimeMonitor.f60335a;
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        timeMonitor2.getClass();
        TimeMonitor.k = jCurrentTimeMillis2;
    }
}
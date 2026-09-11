package com.vega.business.ad.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.os.SystemClock;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.lemon.lv.editor.EditorProxyFlavorModule;
import com.lemon.lv.editor.proxy.IAdProxy;
import com.ss.android.common.util.ToolUtils;
import com.vega.ad.loader.splash.HubAdEcpmInfo;
import com.vega.ad.loader.splash.HubSplashAdDataProxy;
import com.vega.ad.loader.splash.HubSplashAdLoader;
import com.vega.ad.npth.AdNpthManager;
import com.vega.ad.report.AdConfigReportUtils;
import com.vega.ad.report.AdLaunchMonitor;
import com.vega.ad.util.ThreadUtilKtKt;
import com.vega.adapi.AdDataExKt;
import com.vega.adapi.api.IAdLaunchService;
import com.vega.adapi.api.IAdSdkApi;
import com.vega.adapi.config.CapCutAdSettings;
import com.vega.adapi.config.SplashAdConfigData;
import com.vega.adapi.config.splash.PreloadScenes;
import com.vega.adapi.config.splash.SplashPreloadConfig;
import com.vega.adapi.config.splash.SplashRequestFrom;
import com.vega.adapi.constant.AdFormat;
import com.vega.adapi.constant.AdSDK;
import com.vega.adapi.constant.AdSceneTag;
import com.vega.adapi.constant.AdStage;
import com.vega.adapi.constant.SplashDismissReason;
import com.vega.adapi.constant.SplashScene;
import com.vega.adapi.constant.SplashSceneKt;
import com.vega.adapi.data.AdFreeUserLabel;
import com.vega.adapi.data.AdFreeUserReason;
import com.vega.adapi.data.AdRequestTarget;
import com.vega.adapi.data.AdRequestType;
import com.vega.adapi.data.SplashAdActionReportData;
import com.vega.adapi.data.SplashAdRequestReportData;
import com.vega.adapi.data.SplashAdStatusReason;
import com.vega.adapi.report.AdAction;
import com.vega.adapi.splash.ISplashAdLoader;
import com.vega.adapi.splash.ISplashStageListener;
import com.vega.adapi.splash.SplashRequest;
import com.vega.business.ad.api.IAdService;
import com.vega.business.ad.base.CCSplashAdManagerWrapper;
import com.vega.business.ad.base.CCSplashAdManagerWrapper.SplashAdCallBackImpl;
import com.vega.business.ad.base.DeeplinkInvokeDetector;
import com.vega.business.ad.config.GoogleAdSettings;
import com.vega.business.ad.impl.splash.SplashAdShowFrequencyControl;
import com.vega.business.ad.impl.splash.SplashSpManager;
import com.vega.business.ad.model.SplashAdModel;
import com.vega.business.ad.pixel.PixelAdJsbHandler;
import com.vega.business.ad.report.SplashAdReportUtils;
import com.vega.business.ad.view.ColdStartSplashAdComponent;
import com.vega.business.ad.view.HotStartSplashAdActivity;
import com.vega.business.ad.view.ISplashAdView;
import com.vega.core.context.SPIService;
import com.vega.core.deeplink.DeeplinkIntentLancetImpl;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.ext.JSONObjectExKt;
import com.vega.core.popmanager.popup.PopCenter;
import com.vega.core.settings.obtain.SettingsObtainer;
import com.vega.core.utils.AppActivityRecorder;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.util.LifecycleManager;
import com.vega.libfiles.files.hook.StartMainActivityHook;
import com.vega.log.BLog;
import com.vega.performance.BadParcelableExceptionOpt;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.report.ReportManagerWrapper;
import com.vega.start.listener.PreDrawFrom;
import com.vega.start.listener.StartStatusHolder;
import com.vega.start.splash.SplashAdStatusCallback;
import com.vega.start.statistic.AppLaunchTracker;
import com.vega.start.statistic.LaunchTracer;
import com.vega.start.statistic.SplashAdParamCollector;
import com.vega.ui.accomponent.AcComponentActivity;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringNumberConversionsJVMKt;
import kotlin.text.StringsKt___StringsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public final class CCSplashAdManagerWrapper {
    public static final Companion E = new Companion();
    public static final String F = "Ad_Splash";
    public static final CCSplashAdManagerWrapper G;
    public static final Map<String, String> H;
    public static final Lazy<CapCutAdSettings> I;

    /* renamed from: J, reason: collision with root package name */
    public static final Lazy<GoogleAdSettings> f75216J;
    public int C;

    /* renamed from: c, reason: collision with root package name */
    public volatile long f75218c;

    /* renamed from: d, reason: collision with root package name */
    public volatile long f75219d;
    public volatile long f;

    /* renamed from: g, reason: collision with root package name */
    public volatile long f75220g;
    public long h;
    public volatile AdStage k;
    public Context l;
    public ISplashAdLoader m;
    public volatile ISplashAdView q;
    public volatile AdLoadListener r;
    public volatile AdShowListener s;
    public boolean u;
    public boolean w;
    public long x;
    public boolean y;
    public long z;

    /* renamed from: a, reason: collision with root package name */
    public final MutableLiveData<SplashAdModel> f75217a = new MutableLiveData<>();
    public final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<SplashAdCallBackImpl>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$splashAdCallBack$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final CCSplashAdManagerWrapper.SplashAdCallBackImpl invoke() {
            return this.e.new SplashAdCallBackImpl();
        }
    });
    public boolean e = true;
    public final AtomicBoolean i = new AtomicBoolean(false);
    public final AtomicBoolean j = new AtomicBoolean(false);
    public SplashScene n = SplashScene.b;
    public SplashPreloadConfig o = new SplashPreloadConfig(PreloadScenes.f66039a, SplashRequestFrom.f66042c);
    public final Lazy p = LazyKt__LazyJVMKt.lazy(new Function0<PixelAdJsbHandler>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$pixelAdJsbHandler$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final PixelAdJsbHandler invoke() {
            return new PixelAdJsbHandler();
        }
    });
    public boolean t = true;
    public final Lazy v = LazyKt__LazyJVMKt.lazy(new Function0<IAdProxy>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$adProxy$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final IAdProxy invoke() {
            return ((EditorProxyFlavorModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyFlavorModule.class), null)).j();
        }
    });
    public String A = "";
    public String B = "";
    public final CCSplashAdManagerWrapper$splashAdListener$1 D = new ISplashStageListener() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$splashAdListener$1
        /* JADX WARN: Removed duplicated region for block: B:44:0x01a9  */
        @Override // com.vega.adapi.splash.ISplashStageListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void a(final int r40, final java.lang.String r41) throws org.json.JSONException {
            /*
                r39 = this;
                java.lang.String r5 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r0 = "SplashAdListener onAdLoadedFail errorCode: "
                r1.<init>(r0)
                r11 = r40
                r1.append(r11)
                java.lang.String r3 = ", errorMessage: "
                r1.append(r3)
                r12 = r41
                r1.append(r12)
                java.lang.String r0 = ", thread: "
                r1.append(r0)
                java.lang.Thread r0 = java.lang.Thread.currentThread()
                java.lang.String r0 = r0.getName()
                r1.append(r0)
                java.lang.String r0 = r1.toString()
                com.vega.log.BLog.e(r5, r0)
                r0 = r39
                com.vega.business.ad.base.CCSplashAdManagerWrapper r0 = r0.f75227a
                r0.getClass()
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r1 = "onAdLoadedFailHandler errorCode: "
                r2.<init>(r1)
                r2.append(r11)
                r2.append(r3)
                r2.append(r12)
                java.lang.String r1 = r2.toString()
                com.vega.log.BLog.i(r5, r1)
                long r15 = android.os.SystemClock.uptimeMillis()
                long r8 = android.os.SystemClock.uptimeMillis()
                com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedFailHandler$1 r7 = new com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedFailHandler$1
                r10 = r0
                r11 = r11
                r12 = r12
                r7.<init>()
                com.vega.ad.util.ThreadUtilKtKt.a(r7)
                com.vega.adapi.constant.SplashScene r1 = r0.n
                com.vega.adapi.constant.SplashScene r6 = com.vega.adapi.constant.SplashScene.b
                r4 = 0
                r3 = 1
                if (r1 != r6) goto L252
                com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r1 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
                r1.getClass()
                boolean r1 = com.vega.business.ad.view.ColdStartSplashAdComponent.x
                if (r1 == 0) goto L24e
                java.lang.String r14 = "fail_overtime"
            L73:
                com.vega.ad.report.AdLaunchMonitor r1 = com.vega.ad.report.AdLaunchMonitor.f65929a
                java.lang.Integer r13 = java.lang.Integer.valueOf(r11)
                r1.getClass()
                long r9 = com.vega.ad.report.AdLaunchMonitor.U
                r7 = -1
                int r1 = (r9 > r7 ? 1 : (r9 == r7 ? 0 : -1))
                if (r1 != 0) goto L8e
                long r1 = com.vega.ad.report.AdLaunchMonitor.d(r15)
                com.vega.ad.report.AdLaunchMonitor.U = r1
                com.vega.ad.report.AdLaunchMonitor.f65928X = r14
                com.vega.ad.report.AdLaunchMonitor.Y = r13
            L8e:
                com.vega.business.ad.impl.splash.SplashSpManager r1 = com.vega.business.ad.impl.splash.SplashSpManager.f75304a
                r1.getClass()
                java.lang.String r2 = "SplashSpManager"
                java.lang.String r1 = "recordColdStartRequestFail"
                com.vega.log.BLog.i(r2, r1)
                com.vega.kv.KvStorage r7 = com.vega.business.ad.impl.splash.SplashSpManager.a()
                com.vega.kv.KvStorage r1 = com.vega.business.ad.impl.splash.SplashSpManager.a()
                java.lang.String r2 = "cold_request_fail_streak"
                int r1 = r1.f(r2, r4)
                int r1 = r1 + 1
                r7.n(r1, r2, r4)
                boolean r1 = r0.w
                if (r1 == 0) goto L26d
                long r7 = r0.x
                long r1 = r15 - r7
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r7 = "onAdLoadedFailHandler, COLD_START, cost time after SpeedBidSplashAd="
                r8.<init>(r7)
                r8.append(r1)
                java.lang.String r7 = r8.toString()
                com.vega.log.BLog.i(r5, r7)
            Lc6:
                r7 = 1
            Lc7:
                com.vega.adapi.constant.SplashScene r5 = r0.n
                int r5 = r5.ordinal()
                if (r5 == 0) goto L229
                if (r5 == r3) goto L209
            Ld1:
                com.vega.adapi.config.splash.SplashPreloadConfig r4 = r0.o
                boolean r8 = r4.a()
                com.vega.adapi.constant.SplashScene r4 = r0.n
                if (r4 != r6) goto L1ff
                long r4 = r0.f75218c
                long r20 = r15 - r4
            Ldf:
                long r4 = r0.f75219d
                long r22 = r15 - r4
                org.json.JSONObject r4 = new org.json.JSONObject
                r4.<init>()
                java.lang.String r5 = "speed_bid"
                r4.put(r5, r7)
                java.lang.String r5 = "speed_bid_cost_time"
                r4.put(r5, r1)
                com.vega.adapi.constant.SplashScene r1 = r0.n
                if (r1 != r6) goto L109
                java.lang.String r2 = "is_splashadn_overtime"
                int r1 = r0.C
                r4.put(r2, r1)
                com.vega.ad.report.AdLaunchMonitor r1 = com.vega.ad.report.AdLaunchMonitor.f65929a
                r1.getClass()
                org.json.JSONObject r1 = com.vega.ad.report.AdLaunchMonitor.e(r15)
                com.vega.core.ext.JSONObjectExKt.g(r4, r1, r3)
            L109:
                com.vega.adapi.constant.SplashScene r1 = r0.n
                int r1 = r1.ordinal()
                if (r1 == 0) goto L1a5
                if (r1 == r3) goto L199
            L113:
                com.vega.adapi.constant.SplashScene r7 = r0.n
                if (r7 != r6) goto L15f
                com.vega.ad.report.ColdStartRequestFailMonitor r5 = com.vega.ad.report.ColdStartRequestFailMonitor.f65937a
                com.vega.ad.report.RequestFailEvent r3 = new com.vega.ad.report.RequestFailEvent
                com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r1 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
                r1.getClass()
                boolean r26 = com.vega.business.ad.view.ColdStartSplashAdComponent.x
                com.vega.adapi.config.splash.SplashPreloadConfig r1 = r0.o
                com.vega.adapi.config.splash.SplashRequestFrom r1 = r1.b
                java.lang.String r10 = r1.f66044a
                java.lang.String r2 = r0.A
                java.lang.String r1 = r0.B
                com.vega.adapi.constant.AdSDK r34 = r0.c()
                com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r8 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
                r8.getClass()
                com.vega.adapi.config.CapCutAdSettings r8 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
                com.vega.adapi.config.SplashAdConfigData r9 = r8.E()
                com.vega.adapi.constant.SplashScene r8 = r0.n
                java.lang.String r35 = r9.o(r8)
                java.lang.String r36 = java.lang.String.valueOf(r11)
                r24 = r3
                r25 = r7
                r27 = r10
                r28 = r2
                r29 = r1
                r30 = r20
                r32 = r22
                r37 = r12
                r38 = r4
                r24.<init>(r25, r26, r27, r28, r29, r30, r32, r34, r35, r36, r37, r38)
                r5.b(r3)
            L15f:
                com.vega.business.ad.view.ISplashAdView r1 = r0.q
                if (r1 == 0) goto L179
                com.vega.adapi.constant.SplashScene r1 = r0.n
                if (r1 != r6) goto L196
                com.vega.adapi.constant.AdSceneTag r2 = com.vega.adapi.constant.AdSceneTag.f66066c
            L169:
                com.vega.business.ad.report.SplashAdReportUtils r1 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
                com.vega.adapi.data.SplashAdStatusReason r3 = com.vega.adapi.data.SplashAdStatusReason.f
                r4 = 0
                r11 = 508(0x1fc, float:7.12E-43)
                r5 = r4
                r6 = r4
                r7 = r4
                r8 = r4
                r9 = r4
                r10 = r4
                com.vega.business.ad.report.SplashAdReportUtils.i(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            L179:
                com.vega.adapi.constant.AdStage r1 = com.vega.adapi.constant.AdStage.e
                r0.k = r1
                java.util.concurrent.atomic.AtomicBoolean r2 = r0.j
                r1 = 0
                r2.set(r1)
                com.vega.ad.npth.AdNpthManager r2 = com.vega.ad.npth.AdNpthManager.f65907a
                com.vega.adapi.constant.SplashScene r1 = r0.n
                com.vega.adapi.constant.AdSceneTag r1 = com.vega.adapi.constant.SplashSceneKt.a(r1)
                java.lang.String r1 = r1.f66069a
                java.lang.String r0 = r0.A
                r2.getClass()
                com.vega.ad.npth.AdNpthManager.d(r1, r0)
                return
            L196:
                com.vega.adapi.constant.AdSceneTag r2 = com.vega.adapi.constant.AdSceneTag.f66067d
                goto L169
            L199:
                if (r8 != 0) goto L1a9
                com.vega.business.ad.view.HotStartSplashAdActivity$Companion r1 = com.vega.business.ad.view.HotStartSplashAdActivity.b0
                r1.getClass()
                boolean r1 = com.vega.business.ad.view.HotStartSplashAdActivity.f0
                if (r1 != 0) goto L113
                goto L1a9
            L1a5:
                int r1 = r0.C
                if (r1 != r3) goto L1f5
            L1a9:
                com.vega.adapi.constant.SplashScene r14 = r0.n
                com.vega.adapi.constant.AdStage r15 = com.vega.adapi.constant.AdStage.e
                if (r8 == 0) goto L1f2
                com.vega.adapi.data.AdRequestTarget r16 = com.vega.adapi.data.AdRequestTarget.f66119c
            L1b1:
                com.vega.adapi.config.splash.SplashPreloadConfig r1 = r0.o
                com.vega.adapi.config.splash.SplashRequestFrom r1 = r1.b
                java.lang.String r3 = r1.f66044a
                java.lang.String r2 = r0.A
                java.lang.String r1 = r0.B
                com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r5 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
                r5.getClass()
                com.vega.adapi.config.CapCutAdSettings r5 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
                com.vega.adapi.config.SplashAdConfigData r7 = r5.E()
                com.vega.adapi.constant.SplashScene r5 = r0.n
                java.lang.String r25 = r7.o(r5)
                com.vega.adapi.constant.AdSDK r24 = r0.c()
                java.lang.String r28 = java.lang.String.valueOf(r11)
                com.vega.adapi.constant.AdSDK r5 = r0.c()
                java.lang.String r26 = com.vega.adapi.AdDataExKt.c(r5)
                r27 = 0
                r31 = 4096(0x1000, float:5.74E-42)
                r13 = r0
                r29 = r12
                r30 = r4
                r17 = r3
                r18 = r2
                r19 = r1
                com.vega.business.ad.base.CCSplashAdManagerWrapper.m(r13, r14, r15, r16, r17, r18, r19, r20, r22, r24, r25, r26, r27, r28, r29, r30, r31)
                goto L113
            L1f2:
                com.vega.adapi.data.AdRequestTarget r16 = com.vega.adapi.data.AdRequestTarget.b
                goto L1b1
            L1f5:
                com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r1 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
                r1.getClass()
                boolean r1 = com.vega.business.ad.view.ColdStartSplashAdComponent.x
                if (r1 != 0) goto L113
                goto L1a9
            L1ff:
                long r20 = java.lang.System.currentTimeMillis()
                long r4 = r0.h
                long r20 = r20 - r4
                goto Ldf
            L209:
                com.vega.business.ad.view.HotStartSplashAdActivity$Companion r5 = com.vega.business.ad.view.HotStartSplashAdActivity.b0
                r5.getClass()
                boolean r5 = com.vega.business.ad.view.HotStartSplashAdActivity.e0
                if (r5 != 0) goto Ld1
                com.vega.business.ad.report.SplashAdReportUtils r17 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
                com.vega.adapi.constant.SplashScene r19 = com.vega.adapi.constant.SplashScene.f66086c
                java.lang.String r20 = com.vega.business.ad.view.HotStartSplashAdActivity.d0
                long r21 = android.os.SystemClock.uptimeMillis()
                long r8 = com.vega.business.ad.view.HotStartSplashAdActivity.g0
                long r21 = r21 - r8
                r23 = r4
                r18 = r4
                com.vega.business.ad.report.SplashAdReportUtils.k(r17, r18, r19, r20, r21, r23)
                goto Ld1
            L229:
                com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r5 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
                r5.getClass()
                boolean r5 = com.vega.business.ad.view.ColdStartSplashAdComponent.w
                if (r5 != 0) goto Ld1
                com.vega.start.statistic.SplashAdParamCollector r5 = com.vega.start.statistic.SplashAdParamCollector.f130917a
                r5.f(r4)
                com.vega.business.ad.report.SplashAdReportUtils r17 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
                java.lang.String r20 = com.vega.business.ad.view.ColdStartSplashAdComponent.y
                long r21 = android.os.SystemClock.uptimeMillis()
                long r8 = com.vega.business.ad.view.ColdStartSplashAdComponent.z
                long r21 = r21 - r8
                r18 = r4
                r19 = r6
                r23 = r4
                com.vega.business.ad.report.SplashAdReportUtils.k(r17, r18, r19, r20, r21, r23)
                goto Ld1
            L24e:
                java.lang.String r14 = "fail"
                goto L73
            L252:
                boolean r1 = r0.y
                if (r1 == 0) goto L26d
                long r7 = r0.z
                long r1 = r15 - r7
                java.lang.StringBuilder r8 = new java.lang.StringBuilder
                java.lang.String r7 = "onAdLoadedFailHandler, HOT_START, cost time after SpeedBidSplashAd="
                r8.<init>(r7)
                r8.append(r1)
                java.lang.String r7 = r8.toString()
                com.vega.log.BLog.i(r5, r7)
                goto Lc6
            L26d:
                r1 = 0
                r7 = 0
                goto Lc7
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vega.business.ad.base.CCSplashAdManagerWrapper$splashAdListener$1.a(int, java.lang.String):void");
        }

        @Override // com.vega.adapi.splash.ISplashStageListener
        public final void b(int i, String str) throws JSONException {
            long j;
            BLog.w(CCSplashAdManagerWrapper.F, "SplashAdListener onAdShowFail: errorCode " + i + " errorMessage " + str);
            CCSplashAdManagerWrapper.AdShowListener adShowListener = this.f75227a.s;
            if (adShowListener != null) {
                adShowListener.a();
            }
            SplashAdModel value = this.f75227a.f75217a.getValue();
            if (value != null) {
                CCSplashAdManagerWrapper cCSplashAdManagerWrapper = this.f75227a;
                SplashAdReportUtils splashAdReportUtils = SplashAdReportUtils.f75379a;
                AdSceneTag adSceneTagA = SplashSceneKt.a(value.k);
                AdFreeUserLabel adFreeUserLabel = AdFreeUserLabel.f;
                AdFreeUserReason adFreeUserReason = AdFreeUserReason.n;
                splashAdReportUtils.getClass();
                SplashAdReportUtils.f(adSceneTagA, adFreeUserLabel, adFreeUserReason, str);
                SplashAdCallBack splashAdCallBackD = cCSplashAdManagerWrapper.d();
                SplashScene splashScene = value.k;
                SplashRequestFrom splashRequestFrom = value.l;
                if (splashScene == SplashScene.b) {
                    ColdStartSplashAdComponent.u.getClass();
                    j = ColdStartSplashAdComponent.B;
                } else {
                    HotStartSplashAdActivity.b0.getClass();
                    j = HotStartSplashAdActivity.j0;
                }
                splashAdCallBackD.getClass();
                Intrinsics.checkNotNullParameter(splashScene, "");
                Intrinsics.checkNotNullParameter(splashRequestFrom, "");
                splashAdCallBackD.d();
                splashAdCallBackD.h(splashScene, splashRequestFrom, j, i, str);
                if (Unit.INSTANCE != null) {
                    return;
                }
            }
            this.f75227a.d().d();
        }

        @Override // com.vega.adapi.splash.ISplashStageListener
        public final void c(String str, boolean z) throws JSONException {
            long j;
            String str2;
            Double doubleOrNull;
            BLog.w(CCSplashAdManagerWrapper.F, "SplashAdListener onAdShow: adnName " + str + " isBrand " + z);
            CCSplashAdManagerWrapper.AdShowListener adShowListener = this.f75227a.s;
            if (adShowListener != null) {
                adShowListener.b(str);
            }
            SplashAdModel value = this.f75227a.f75217a.getValue();
            if (value != null) {
                CCSplashAdManagerWrapper cCSplashAdManagerWrapper = this.f75227a;
                HubAdEcpmInfo hubAdEcpmInfoG = value.f75373c.g();
                value.m = hubAdEcpmInfoG != null ? hubAdEcpmInfoG.a() : null;
                value.f = str;
                value.f75375g = z;
                HubAdEcpmInfo hubAdEcpmInfoG2 = value.f75373c.g();
                value.i = (hubAdEcpmInfoG2 == null || (str2 = hubAdEcpmInfoG2.f65879a.e) == null || (doubleOrNull = StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(str2)) == null) ? null : Double.valueOf(doubleOrNull.doubleValue() / 1000);
                SplashAdCallBack splashAdCallBackD = cCSplashAdManagerWrapper.d();
                SplashScene splashScene = value.k;
                SplashRequestFrom splashRequestFrom = value.l;
                if (splashScene == SplashScene.b) {
                    ColdStartSplashAdComponent.u.getClass();
                    j = ColdStartSplashAdComponent.B;
                } else {
                    HotStartSplashAdActivity.b0.getClass();
                    j = HotStartSplashAdActivity.j0;
                }
                String str3 = value.m;
                splashAdCallBackD.getClass();
                Intrinsics.checkNotNullParameter(splashScene, "");
                Intrinsics.checkNotNullParameter(splashRequestFrom, "");
                splashAdCallBackD.c(splashScene);
                splashAdCallBackD.g(splashScene, splashRequestFrom, str, z, j, str3);
                AdNpthManager adNpthManager = AdNpthManager.f65907a;
                String str4 = SplashSceneKt.a(value.k).f66069a;
                String str5 = value.r;
                AdSDK adSDK = value.q;
                String str6 = adSDK != null ? adSDK.f66065a : null;
                String str7 = value.s;
                String str8 = value.m;
                String str9 = value.f;
                String str10 = z ? "contract" : "bidding";
                adNpthManager.getClass();
                AdNpthManager.i(str4, str5, str6, str7, str8, str9, str10);
                if (Unit.INSTANCE != null) {
                    return;
                }
            }
            CCSplashAdManagerWrapper cCSplashAdManagerWrapper2 = this.f75227a;
            cCSplashAdManagerWrapper2.d().c(cCSplashAdManagerWrapper2.n);
        }

        @Override // com.vega.adapi.splash.ISplashStageListener
        public final void d(int i) {
            BLog.w(CCSplashAdManagerWrapper.F, "SplashAdListener onAdDismiss: endReason " + i);
            SplashAdModel value = this.f75227a.f75217a.getValue();
            if (value != null) {
                SplashAdCallBack splashAdCallBackD = this.f75227a.d();
                SplashScene splashScene = value.k;
                SplashRequestFrom splashRequestFrom = value.l;
                long j = value.j;
                splashAdCallBackD.getClass();
                Intrinsics.checkNotNullParameter(splashScene, "");
                Intrinsics.checkNotNullParameter(splashRequestFrom, "");
                splashAdCallBackD.b();
                splashAdCallBackD.f(splashScene, splashRequestFrom, j, i);
                if (Unit.INSTANCE != null) {
                    return;
                }
            }
            this.f75227a.d().b();
        }

        @Override // com.vega.adapi.splash.ISplashStageListener
        public final void e(HubSplashAdDataProxy hubSplashAdDataProxy) throws JSONException {
            Intrinsics.checkNotNullParameter(hubSplashAdDataProxy, "");
            BLog.i(CCSplashAdManagerWrapper.F, "SplashAdListener onAdLoaded splashAdDataProxy: " + hubSplashAdDataProxy + ", thread: " + Thread.currentThread().getName());
            this.f75227a.i(hubSplashAdDataProxy, false);
        }

        @Override // com.vega.adapi.splash.ISplashStageListener
        public final void f(HubSplashAdLoader.HubAdClickInfo hubAdClickInfo) {
            String string;
            int i;
            ISplashAdView iSplashAdView;
            BLog.w(CCSplashAdManagerWrapper.F, "SplashAdListener onAdClicked: splashAdInfo " + hubAdClickInfo);
            try {
                if (this.f75227a.e(hubAdClickInfo) && (iSplashAdView = this.f75227a.q) != null) {
                    iSplashAdView.onAdClick();
                }
                i = 0;
                string = "success";
            } catch (Throwable th) {
                BLog.e(CCSplashAdManagerWrapper.F, "SplashAdListener onAdClicked: catch error and dismiss ad view, ERROR=" + th);
                this.f75227a.a(SplashDismissReason.f66082c);
                string = th.toString();
                i = -1;
            }
            SplashAdModel value = this.f75227a.f75217a.getValue();
            if (value != null) {
                SplashAdCallBack splashAdCallBackD = this.f75227a.d();
                SplashScene splashScene = value.k;
                SplashRequestFrom splashRequestFrom = value.l;
                long j = value.j;
                Integer numValueOf = Integer.valueOf(i);
                splashAdCallBackD.getClass();
                Intrinsics.checkNotNullParameter(splashScene, "");
                Intrinsics.checkNotNullParameter(splashRequestFrom, "");
                splashAdCallBackD.a(splashScene);
                splashAdCallBackD.e(splashScene, splashRequestFrom, j, numValueOf, string);
                if (Unit.INSTANCE != null) {
                    return;
                }
            }
            CCSplashAdManagerWrapper cCSplashAdManagerWrapper = this.f75227a;
            SplashAdCallBack splashAdCallBackD2 = cCSplashAdManagerWrapper.d();
            ISplashAdView iSplashAdView2 = cCSplashAdManagerWrapper.q;
            SplashScene splashScene2 = iSplashAdView2 instanceof HotStartSplashAdActivity ? SplashScene.f66086c : iSplashAdView2 instanceof ColdStartSplashAdComponent ? SplashScene.b : cCSplashAdManagerWrapper.n;
            BLog.w(CCSplashAdManagerWrapper.F, "resolveClickedSplashScene: scene=" + splashScene2 + ", splashAdView=" + cCSplashAdManagerWrapper.q + ", loadAdScene=" + cCSplashAdManagerWrapper.n);
            splashAdCallBackD2.a(splashScene2);
        }
    };

    public interface AdLoadListener {
        boolean a(String str, String str2);
    }

    /* loaded from: classes.dex */
    public interface AdShowListener {
        void a();

        void b(String str);
    }

    /* loaded from: classes6.dex */
    public static final class Companion {
        public static CapCutAdSettings a() {
            return CCSplashAdManagerWrapper.I.getValue();
        }
    }

    /* loaded from: classes24.dex */
    public static final class SingletonHolder {

        /* renamed from: a, reason: collision with root package name */
        public static final SingletonHolder f75221a = new SingletonHolder();
        public static final CCSplashAdManagerWrapper b = new CCSplashAdManagerWrapper();
    }

    /* loaded from: classes35.dex */
    public final class SplashAdCallBackImpl extends SplashAdCallBack {
        /* JADX DEBUG: Incorrect args count in method signature: ()V */
        public SplashAdCallBackImpl() {
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void a(SplashScene splashScene) {
            Intrinsics.checkNotNullParameter(splashScene, "");
            BLog.i(CCSplashAdManagerWrapper.F, "adClickedCoreFunction");
            PopCenter popCenter = PopCenter.f79402a;
            String str = splashScene == SplashScene.b ? "app_open_ad" : "app_open_ad_hot";
            popCenter.getClass();
            PopCenter.b(str);
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void b() {
            BLog.i(CCSplashAdManagerWrapper.F, "adDismissCoreFunction");
            SplashAdParamCollector splashAdParamCollector = SplashAdParamCollector.f130917a;
            splashAdParamCollector.getClass();
            AppLaunchTracker.f130896a.getClass();
            if (!AppLaunchTracker.k) {
                if (PerformanceManagerHelper.blogEnable) {
                    com.lm.components.logservice.alog.BLog.i("StartOpt.SplashAdStatusCollector", "splashAdDismiss");
                }
                SplashAdParamCollector.l = System.currentTimeMillis();
                CopyOnWriteArrayList<SplashAdStatusCallback> copyOnWriteArrayList = SplashAdParamCollector.b;
                if (copyOnWriteArrayList != null) {
                    Iterator<SplashAdStatusCallback> it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        it.next().c(true, false);
                    }
                }
                SplashAdParamCollector.e(splashAdParamCollector, "splash dismiss", null, 6);
                LaunchTracer launchTracer = LaunchTracer.f130906a;
                LaunchTracer.Module module = LaunchTracer.Module.b;
                launchTracer.getClass();
                LaunchTracer.a(module, "show");
            }
            CCSplashAdManagerWrapper.this.a(SplashDismissReason.b);
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void c(SplashScene splashScene) {
            Intrinsics.checkNotNullParameter(splashScene, "");
            SplashAdParamCollector.f130917a.getClass();
            AppLaunchTracker.f130896a.getClass();
            if (!AppLaunchTracker.k) {
                com.lm.components.logservice.alog.BLog.i("StartOpt.SplashAdStatusCollector", "splashAdShown, isWebAdShow: false");
                SplashAdParamCollector.f = true;
                SplashAdParamCollector.f130920g = false;
                SplashAdParamCollector.j = System.currentTimeMillis();
                StartStatusHolder startStatusHolder = StartStatusHolder.f130864a;
                PreDrawFrom preDrawFrom = PreDrawFrom.e;
                startStatusHolder.getClass();
                StartStatusHolder.e(preDrawFrom);
                SplashAdParamCollector.f130918c = true;
                CopyOnWriteArrayList<SplashAdStatusCallback> copyOnWriteArrayList = SplashAdParamCollector.b;
                if (copyOnWriteArrayList != null) {
                    Iterator<SplashAdStatusCallback> it = copyOnWriteArrayList.iterator();
                    while (it.hasNext()) {
                        it.next().b(false);
                    }
                }
                LaunchTracer.c(LaunchTracer.f130906a, LaunchTracer.Module.b, "show");
            }
            PopCenter.m(PopCenter.f79402a, splashScene == SplashScene.b ? "app_open_ad" : "app_open_ad_hot");
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void d() {
            CCSplashAdManagerWrapper.this.a(SplashDismissReason.f66083d);
            SplashAdParamCollector.f130917a.f(false);
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void e(SplashScene splashScene, SplashRequestFrom splashRequestFrom, long j, Integer num, String str) throws JSONException {
            long jCurrentTimeMillis;
            long j2;
            Intrinsics.checkNotNullParameter(splashScene, "");
            Intrinsics.checkNotNullParameter(splashRequestFrom, "");
            if (splashScene == SplashScene.b) {
                jCurrentTimeMillis = SystemClock.uptimeMillis();
                j2 = CCSplashAdManagerWrapper.this.f75218c;
            } else {
                jCurrentTimeMillis = System.currentTimeMillis();
                j2 = CCSplashAdManagerWrapper.this.f75220g;
            }
            long j3 = jCurrentTimeMillis - j2;
            long jUptimeMillis = j > 0 ? SystemClock.uptimeMillis() - j : 0L;
            CCSplashAdManagerWrapper.k(CCSplashAdManagerWrapper.this, splashScene, AdAction.f66212g, j3, jUptimeMillis, num, str, splashRequestFrom, Long.valueOf(jUptimeMillis), null, 256);
        }

        /* JADX WARN: Can't wrap try/catch for region: R(33:0|2|(1:4)(1:61)|5|(1:7)|8|(2:10|(1:60)(1:13))(0)|14|(1:18)|(23:(0)(1:58)|24|(1:28)|29|(1:31)(1:57)|32|(1:34)(1:56)|35|(1:55)(1:39)|40|(1:42)|43|(1:45)|46|(1:48)|49|(1:51)|52|(1:54)|67|62|65|66)(1:59)|23|24|(2:26|28)(0)|29|(0)(0)|32|(0)(0)|35|(2:37|55)(0)|40|(0)|43|(0)|46|(0)|49|(0)|52|(0)|67|62|65|66) */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x01d2, code lost:
        
            r0 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x01d3, code lost:
        
            kotlin.Result.m17090constructorimpl(kotlin.ResultKt.createFailure(r0));
         */
        /* JADX WARN: Removed duplicated region for block: B:28:0x009d  */
        /* JADX WARN: Removed duplicated region for block: B:31:0x00a5  */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00b5  */
        /* JADX WARN: Removed duplicated region for block: B:42:0x012e  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x016d  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0176  */
        /* JADX WARN: Removed duplicated region for block: B:51:0x017f  */
        /* JADX WARN: Removed duplicated region for block: B:54:0x0191  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x0197  */
        /* JADX WARN: Removed duplicated region for block: B:56:0x019b  */
        /* JADX WARN: Removed duplicated region for block: B:57:0x019e  */
        /* JADX WARN: Removed duplicated region for block: B:60:0x01a7  */
        @Override // com.vega.business.ad.base.SplashAdCallBack
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void f(com.vega.adapi.constant.SplashScene r28, com.vega.adapi.config.splash.SplashRequestFrom r29, long r30, int r32) throws org.json.JSONException {
            /*
                r27 = this;
                java.lang.String r10 = ""
                r0 = r28
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r10)
                r3 = r29
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r10)
                com.vega.adapi.constant.SplashScene r1 = com.vega.adapi.constant.SplashScene.b
                r4 = r27
                if (r0 != r1) goto L1aa
                long r17 = android.os.SystemClock.uptimeMillis()
                com.vega.business.ad.base.CCSplashAdManagerWrapper r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.this
                long r1 = r1.f75218c
            L1a:
                long r17 = r17 - r1
                r12 = 0
                int r1 = (r30 > r12 ? 1 : (r30 == r12 ? 0 : -1))
                if (r1 <= 0) goto L28
                long r12 = android.os.SystemClock.uptimeMillis()
                long r12 = r12 - r30
            L28:
                com.vega.adapi.report.AdAction r16 = com.vega.adapi.report.AdAction.h
                com.vega.business.ad.base.CCSplashAdManagerWrapper r5 = com.vega.business.ad.base.CCSplashAdManagerWrapper.this
                r2 = 0
                r1 = 0
                java.lang.Integer r25 = java.lang.Integer.valueOf(r32)
                r26 = 176(0xb0, float:2.47E-43)
                r14 = r5
                r15 = r0
                r19 = r12
                r21 = r2
                r22 = r2
                r23 = r3
                r24 = r2
                com.vega.business.ad.base.CCSplashAdManagerWrapper.k(r14, r15, r16, r17, r19, r21, r22, r23, r24, r25, r26)
                com.vega.business.ad.base.CCSplashAdManagerWrapper r5 = com.vega.business.ad.base.CCSplashAdManagerWrapper.this
                java.lang.String r15 = r3.f66044a
                androidx.lifecycle.MutableLiveData<com.vega.business.ad.model.SplashAdModel> r3 = r5.f75217a
                java.lang.Object r4 = r3.getValue()
                com.vega.business.ad.model.SplashAdModel r4 = (com.vega.business.ad.model.SplashAdModel) r4
                com.vega.adapi.constant.SplashScene r3 = com.vega.adapi.constant.SplashScene.f66086c
                if (r0 != r3) goto L1a7
                com.vega.business.ad.view.HotStartSplashAdActivity$Companion r3 = com.vega.business.ad.view.HotStartSplashAdActivity.b0
                r3.getClass()
                boolean r3 = com.vega.business.ad.view.HotStartSplashAdActivity.i0
                if (r3 == 0) goto L1a7
                if (r4 == 0) goto L1a7
                java.lang.String r7 = r4.o
            L60:
                com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
                r3.getClass()
                com.vega.adapi.config.CapCutAdSettings r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
                com.vega.adapi.config.SplashAdConfigData r3 = r3.E()
                java.lang.String r21 = r3.o(r0)
                if (r4 == 0) goto L77
                java.lang.String r9 = r4.s
                if (r9 != 0) goto L85
            L77:
                com.vega.adapi.config.CapCutAdSettings r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
                com.vega.adapi.config.SplashAdConfigData r6 = r3.E()
                com.vega.adapi.constant.SplashScene r3 = r5.n
                java.lang.String r9 = r6.o(r3)
            L85:
                if (r4 == 0) goto L8b
                java.lang.String r3 = r4.f
                if (r3 != 0) goto L1a4
            L8b:
                if (r4 == 0) goto L1a1
            L8d:
                java.lang.Double r11 = r4.i
            L8f:
                com.vega.adapi.constant.AdSceneTag r14 = com.vega.adapi.constant.SplashSceneKt.a(r0)
                com.vega.adapi.constant.AdSDK r20 = r5.c()
                if (r4 == 0) goto L9d
                com.vega.adapi.constant.AdSDK r3 = r4.q
                if (r3 != 0) goto La1
            L9d:
                com.vega.adapi.constant.AdSDK r3 = r5.c()
            La1:
                java.lang.String r6 = r3.f66065a
                if (r4 == 0) goto L19e
                java.lang.String r5 = r4.r
            La7:
                com.vega.adapi.config.CapCutAdSettings r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
                com.vega.adapi.config.SplashAdConfigData r3 = r3.E()
                java.lang.String r23 = r3.n(r0)
                if (r4 == 0) goto L19b
                java.lang.String r3 = r4.m
            Lb7:
                r0 = 1
                if (r4 == 0) goto L197
                boolean r4 = r4.f75375g
                if (r4 != r0) goto L197
                com.vega.adapi.report.AdType r17 = com.vega.adapi.report.AdType.f66218c
            Lc0:
                com.vega.adapi.data.SplashAdDurationData r8 = new com.vega.adapi.data.SplashAdDurationData
                r18 = r7
                r19 = r3
                r22 = r5
                r16 = r6
                r8.<init>(r9, r10, r11, r12, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
                com.vega.business.ad.report.SplashAdReportUtils r3 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
                r3.getClass()
                org.json.JSONObject r5 = new org.json.JSONObject
                r5.<init>()
                java.lang.String r4 = r8.f66151a
                java.lang.String r3 = "unit_id"
                r5.put(r3, r4)
                long r3 = r8.f66153d
                java.lang.String r6 = "adshow_duration"
                r5.put(r6, r3)
                com.vega.core.context.SPIService r4 = com.vega.core.context.SPIService.INSTANCE
                java.lang.Class<com.vega.adapi.api.IAdSdkApi> r3 = com.vega.adapi.api.IAdSdkApi.class
                kotlin.reflect.KClass r3 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r3)
                java.lang.Object r2 = r4.getImpl(r3, r2)
                com.vega.adapi.api.IAdSdkApi r2 = (com.vega.adapi.api.IAdSdkApi) r2
                boolean r2 = r2.k()
                r0 = r0 ^ r2
                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                java.lang.String r2 = com.vega.core.ext.ExtentionKt.getReportStr(r0)
                java.lang.String r0 = "is_personal"
                r5.put(r0, r2)
                com.vega.adapi.constant.AdSceneTag r0 = r8.e
                java.lang.String r2 = r0.f66069a
                java.lang.String r0 = "tag"
                r5.put(r0, r2)
                java.lang.String r0 = r8.f66154g
                java.lang.String r3 = "mediation_source"
                r5.put(r3, r0)
                java.lang.String r2 = r8.l
                java.lang.String r0 = "main_unit_id"
                r5.put(r0, r2)
                java.lang.String r0 = r8.f66154g
                r5.put(r3, r0)
                com.vega.adapi.constant.AdSDK r0 = r8.k
                java.lang.String r2 = r0.f66065a
                java.lang.String r0 = "main_mediation_source"
                r5.put(r0, r2)
                java.lang.String r2 = r8.m
                if (r2 == 0) goto L133
                java.lang.String r0 = "sdk_type"
                r5.put(r0, r2)
            L133:
                java.lang.String r2 = "request_uuid"
                java.lang.String r0 = com.vega.business.ad.report.SplashAdReportUtils.l
                r5.put(r2, r0)
                java.lang.String r2 = "launch_id"
                java.lang.String r0 = com.vega.business.ad.report.SplashAdReportUtils.l
                r5.put(r2, r0)
                java.lang.String r2 = com.vega.business.ad.report.SplashAdReportUtils.b()
                java.lang.String r0 = "process_id"
                r5.put(r0, r2)
                java.lang.String r2 = r8.f
                java.lang.String r0 = "request_from"
                r5.put(r0, r2)
                com.vega.adapi.constant.AdSceneTag r0 = r8.e
                org.json.JSONObject r0 = com.vega.business.ad.report.SplashAdReportUtils.c(r0)
                java.lang.String r2 = r0.toString()
                java.lang.String r0 = "trigger_from"
                r5.put(r0, r2)
                com.vega.adapi.report.AdType r0 = r8.h
                java.lang.String r2 = r0.f66220a
                java.lang.String r0 = "ad_type"
                r5.put(r0, r2)
                java.lang.String r2 = r8.i
                if (r2 == 0) goto L172
                java.lang.String r0 = "cache_info"
                r5.put(r0, r2)
            L172:
                java.lang.String r2 = r8.b
                if (r2 == 0) goto L17b
                java.lang.String r0 = "ad_platform"
                r5.put(r0, r2)
            L17b:
                java.lang.Double r0 = r8.f66152c
                if (r0 == 0) goto L18d
                double r2 = r0.doubleValue()
                r0 = 1000000(0xf4240, float:1.401298E-39)
                double r6 = (double) r0
                double r2 = r2 * r6
                java.lang.String r0 = "ad_price_million"
                r5.put(r0, r2)
            L18d:
                java.lang.String r2 = r8.j
                if (r2 == 0) goto L1b4
                java.lang.String r0 = "rit_id"
                r5.put(r0, r2)
                goto L1b4
            L197:
                com.vega.adapi.report.AdType r17 = com.vega.adapi.report.AdType.b
                goto Lc0
            L19b:
                r3 = r2
                goto Lb7
            L19e:
                r5 = r2
                goto La7
            L1a1:
                r11 = r2
                goto L8f
            L1a4:
                r10 = r3
                goto L8d
            L1a7:
                r7 = r2
                goto L60
            L1aa:
                long r17 = java.lang.System.currentTimeMillis()
                com.vega.business.ad.base.CCSplashAdManagerWrapper r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.this
                long r1 = r1.f75220g
                goto L1a
            L1b4:
                java.lang.String r2 = "config_settings"
                java.lang.Class<com.vega.adapi.config.CapCutAdSettings> r0 = com.vega.adapi.config.CapCutAdSettings.class
                kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch: java.lang.Throwable -> L1d2
                java.lang.Object r0 = r4.getImpl(r0, r1)     // Catch: java.lang.Throwable -> L1d2
                com.vega.adapi.config.CapCutAdSettings r0 = (com.vega.adapi.config.CapCutAdSettings) r0     // Catch: java.lang.Throwable -> L1d2
                com.vega.adapi.config.SplashAdConfigData r0 = r0.E()     // Catch: java.lang.Throwable -> L1d2
                java.lang.String r0 = com.vega.core.ext.ExtentionKt.toJson(r0)     // Catch: java.lang.Throwable -> L1d2
                org.json.JSONObject r0 = r5.put(r2, r0)     // Catch: java.lang.Throwable -> L1d2
                kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> L1d2
                goto L1da
            L1d2:
                r0 = move-exception
                java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
                kotlin.Result.m17090constructorimpl(r0)
            L1da:
                java.lang.String r1 = r8.n
                java.lang.String r0 = "abtest_id"
                r5.put(r0, r1)
                java.lang.String r1 = "exit_page"
                java.lang.String r0 = com.vega.business.ad.report.SplashAdReportUtils.f75382g
                r5.put(r1, r0)
                com.vega.report.ReportManagerWrapper r1 = com.vega.report.ReportManagerWrapper.INSTANCE
                java.lang.String r0 = "ad_duration"
                r1.onEvent(r0, r5)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vega.business.ad.base.CCSplashAdManagerWrapper.SplashAdCallBackImpl.f(com.vega.adapi.constant.SplashScene, com.vega.adapi.config.splash.SplashRequestFrom, long, int):void");
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void g(SplashScene splashScene, SplashRequestFrom splashRequestFrom, String str, boolean z, long j, String str2) throws JSONException {
            long jCurrentTimeMillis;
            long j2;
            Intrinsics.checkNotNullParameter(splashScene, "");
            Intrinsics.checkNotNullParameter(splashRequestFrom, "");
            if (splashScene == SplashScene.b) {
                jCurrentTimeMillis = SystemClock.uptimeMillis();
                j2 = CCSplashAdManagerWrapper.this.f75218c;
            } else {
                jCurrentTimeMillis = System.currentTimeMillis();
                j2 = CCSplashAdManagerWrapper.this.f75220g;
            }
            long jUptimeMillis = SystemClock.uptimeMillis() - j;
            CCSplashAdManagerWrapper.k(CCSplashAdManagerWrapper.this, splashScene, AdAction.e, jCurrentTimeMillis - j2, jUptimeMillis, null, null, splashRequestFrom, null, null, 432);
            SplashAdReportUtils.f75379a.getClass();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tag", SplashSceneKt.a(splashScene).f66069a);
            jSONObject.put("is_success", true);
            int i = SplashAdReportUtils.WhenMappings.f75383a[splashScene.ordinal()];
            if (i == 1) {
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                ColdStartSplashAdComponent.u.getClass();
                jSONObject.put("cost_time", jUptimeMillis2 - ColdStartSplashAdComponent.A);
            } else if (i != 2) {
                jSONObject.put("cost_time", (Object) null);
            } else {
                long jUptimeMillis3 = SystemClock.uptimeMillis();
                HotStartSplashAdActivity.b0.getClass();
                jSONObject.put("cost_time", jUptimeMillis3 - HotStartSplashAdActivity.h0);
            }
            jSONObject.put("is_brand", z);
            jSONObject.put("adn_name", str);
            if (str2 != null) {
                jSONObject.put("rit_id", str2);
            }
            ReportManagerWrapper.INSTANCE.onEvent("splash_ad_waiting_ad_show", jSONObject);
            jSONObject.toString();
        }

        @Override // com.vega.business.ad.base.SplashAdCallBack
        public final void h(SplashScene splashScene, SplashRequestFrom splashRequestFrom, long j, int i, String str) throws JSONException {
            long jCurrentTimeMillis;
            long j2;
            Intrinsics.checkNotNullParameter(splashScene, "");
            Intrinsics.checkNotNullParameter(splashRequestFrom, "");
            if (splashScene == SplashScene.b) {
                jCurrentTimeMillis = SystemClock.uptimeMillis();
                j2 = CCSplashAdManagerWrapper.this.f75218c;
            } else {
                jCurrentTimeMillis = System.currentTimeMillis();
                j2 = CCSplashAdManagerWrapper.this.f75220g;
            }
            long jUptimeMillis = SystemClock.uptimeMillis() - j;
            CCSplashAdManagerWrapper.k(CCSplashAdManagerWrapper.this, splashScene, AdAction.l, jCurrentTimeMillis - j2, jUptimeMillis, Integer.valueOf(i), str, splashRequestFrom, null, null, 384);
            SplashAdReportUtils splashAdReportUtils = SplashAdReportUtils.f75379a;
            Integer numValueOf = Integer.valueOf(i);
            splashAdReportUtils.getClass();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("tag", SplashSceneKt.a(splashScene).f66069a);
            jSONObject.put("is_success", false);
            int i2 = SplashAdReportUtils.WhenMappings.f75383a[splashScene.ordinal()];
            if (i2 == 1) {
                long jUptimeMillis2 = SystemClock.uptimeMillis();
                ColdStartSplashAdComponent.u.getClass();
                jSONObject.put("cost_time", jUptimeMillis2 - ColdStartSplashAdComponent.A);
            } else if (i2 != 2) {
                jSONObject.put("cost_time", (Object) null);
            } else {
                long jUptimeMillis3 = SystemClock.uptimeMillis();
                HotStartSplashAdActivity.b0.getClass();
                jSONObject.put("cost_time", jUptimeMillis3 - HotStartSplashAdActivity.h0);
            }
            jSONObject.put("error_code", numValueOf);
            jSONObject.put("error_msg", str);
            ReportManagerWrapper.INSTANCE.onEvent("splash_ad_waiting_ad_show", jSONObject);
            jSONObject.toString();
        }
    }

    /* loaded from: classes17.dex */
    public /* synthetic */ class WhenMappings {
        static {
            SplashScene.values();
        }
    }

    static {
        SingletonHolder.f75221a.getClass();
        G = SingletonHolder.b;
        H = MapsKt__MapsKt.mapOf(TuplesKt.to("snssdk1180", "com.ss.android.ugc.trill"), TuplesKt.to("snssdk1233", "com.zhiliaoapp.musically"), TuplesKt.to("snssdk1340", "com.zhiliaoapp.musically.go"));
        I = LazyKt__LazyJVMKt.lazy(new Function0<CapCutAdSettings>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion$adSettings$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.adapi.config.CapCutAdSettings, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final CapCutAdSettings invoke() {
                return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null);
            }
        });
        f75216J = LazyKt__LazyJVMKt.lazy(new Function0<GoogleAdSettings>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion$settings$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v1, types: [com.vega.business.ad.config.GoogleAdSettings, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final GoogleAdSettings invoke() {
                SettingsObtainer.f79519a.getClass();
                return SettingsObtainer.d(GoogleAdSettings.class);
            }
        });
    }

    public static void INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(AcComponentActivity acComponentActivity, Intent intent) {
        DeeplinkIntentLancetImpl.a(intent);
        INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_libfiles_files_hook_StartMainActivityHook_hookAcComponentActivityStartActivity(acComponentActivity, intent);
    }

    public static Intent INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_launcher_lancet_BadParcelableLancet_getInttent(Activity activity) {
        Context context;
        Intent intent = activity.getIntent();
        if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
        }
        return intent;
    }

    public static void INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_libfiles_files_hook_StartMainActivityHook_hookAcComponentActivityStartActivity(AcComponentActivity acComponentActivity, Intent intent) {
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("StartMainActivityHook", "hookAcComponentActivityStartActivity: ");
        }
        StartMainActivityHook.fixLauncherIntent(intent);
        HeliosApiHook heliosApiHook = new HeliosApiHook();
        Object[] objArr = {intent};
        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/content/Intent;)V", "dzBzEhQ/WMuSV0IyTByBdW0kf64EKJVJWlf/mHVuUghZxDRk7e7dFPlqTm7azPn/iAI=");
        if (heliosApiHook.preInvoke(11098, "com/vega/ui/accomponent/AcComponentActivity", "startActivity", acComponentActivity, objArr, "void", extraInfo).isIntercept()) {
            heliosApiHook.postInvoke(null, 11098, "com/vega/ui/accomponent/AcComponentActivity", "startActivity", acComponentActivity, objArr, extraInfo, false);
        } else {
            acComponentActivity.startActivity(intent);
            heliosApiHook.postInvoke(null, 11098, "com/vega/ui/accomponent/AcComponentActivity", "startActivity", acComponentActivity, objArr, extraInfo, true);
        }
    }

    public static SplashAdStatusReason b() {
        E.getClass();
        if (!Companion.a().E().i()) {
            return SplashAdStatusReason.j;
        }
        SplashAdShowFrequencyControl.Companion companion = SplashAdShowFrequencyControl.f75300d;
        SplashScene splashScene = SplashScene.b;
        companion.getClass();
        SplashAdShowFrequencyControl splashAdShowFrequencyControlA = SplashAdShowFrequencyControl.Companion.a(splashScene);
        if (splashAdShowFrequencyControlA.c()) {
            return SplashAdStatusReason.e;
        }
        SplashAdShowFrequencyControl.Result resultD = splashAdShowFrequencyControlA.d();
        if (!resultD.f75303a) {
            return null;
        }
        SplashAdStatusReason splashAdStatusReason = resultD.b;
        return splashAdStatusReason == null ? SplashAdStatusReason.f66160d : splashAdStatusReason;
    }

    /* JADX WARN: Removed duplicated region for block: B:137:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01aa A[Catch: all -> 0x01e7, PHI: r5 r8
      0x01aa: PHI (r5v19 android.net.Uri) = (r5v16 android.net.Uri), (r5v20 android.net.Uri) binds: [B:71:0x01a5, B:73:0x01a8] A[DONT_GENERATE, DONT_INLINE]
      0x01aa: PHI (r8v9 java.lang.String) = (r8v6 java.lang.String), (r8v10 java.lang.String) binds: [B:71:0x01a5, B:73:0x01a8] A[DONT_GENERATE, DONT_INLINE], TryCatch #0 {all -> 0x01e7, blocks: (B:57:0x0173, B:62:0x0186, B:63:0x018a, B:66:0x0195, B:68:0x019b, B:74:0x01aa, B:77:0x01b3, B:79:0x01b7, B:82:0x01bd, B:85:0x01c5, B:87:0x01cb, B:90:0x01d3, B:70:0x019f, B:93:0x01e1), top: B:157:0x0173 }] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01b2 A[PHI: r5 r8
      0x01b2: PHI (r5v17 android.net.Uri) = (r5v19 android.net.Uri), (r5v20 android.net.Uri) binds: [B:75:0x01b0, B:73:0x01a8] A[DONT_GENERATE, DONT_INLINE]
      0x01b2: PHI (r8v7 java.lang.String) = (r8v9 java.lang.String), (r8v10 java.lang.String) binds: [B:75:0x01b0, B:73:0x01a8] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean f(com.vega.business.ad.base.CCSplashAdManagerWrapper r22, com.vega.adapi.constant.SplashScene r23, boolean r24, com.vega.adapi.config.splash.SplashPreloadConfig r25, boolean r26, boolean r27, int r28) throws org.json.JSONException {
        /*
            r6 = r25
            r0 = r28 & 4
            r1 = 0
            if (r0 == 0) goto L8
            r6 = r1
        L8:
            r0 = r28 & 8
            r14 = 0
            if (r0 == 0) goto Lf
            r26 = 0
        Lf:
            r0 = r28 & 16
            if (r0 == 0) goto L15
            r27 = 0
        L15:
            r3 = r22
            r3.getClass()
            java.lang.String r7 = ""
            r0 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r7)
            com.vega.core.popmanager.PopMock r2 = com.vega.core.popmanager.PopMock.f79371a
            r2.getClass()
            com.vega.setting.CustomBuildConfig r2 = com.vega.setting.CustomBuildConfig.f130058a
            java.lang.Class r4 = java.lang.Boolean.TYPE
            r2.getClass()
            java.lang.String r2 = "show_ads"
            java.lang.Object r4 = com.vega.setting.CustomBuildConfig.a(r4, r2)
            java.lang.Boolean r2 = java.lang.Boolean.FALSE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)
            if (r2 == 0) goto L3c
        L3b:
            return r14
        L3c:
            java.lang.Boolean r2 = java.lang.Boolean.TRUE
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r2)
            if (r2 == 0) goto L46
        L44:
            r14 = 1
            goto L3b
        L46:
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r2 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r2.getClass()
            com.vega.adapi.config.CapCutAdSettings r2 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r5 = r2.E()
            java.lang.String r4 = r5.o(r0)
            boolean r2 = r5.i()
            if (r2 == 0) goto L3cf
            int r2 = r4.length()
            if (r2 <= 0) goto L3cf
            long r8 = android.os.SystemClock.uptimeMillis()
            com.vega.ad.impl.adfree.AdFreeManager r4 = com.vega.ad.impl.adfree.AdFreeManager.f65709a
            com.vega.adapi.constant.AdSceneTag r2 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            r4.getClass()
            boolean r4 = com.vega.ad.impl.adfree.AdFreeManager.a(r2)
            com.vega.ad.report.AdLaunchMonitor r2 = com.vega.ad.report.AdLaunchMonitor.f65929a
            long r12 = android.os.SystemClock.uptimeMillis()
            long r12 = r12 - r8
            r2.getClass()
            long r10 = com.vega.ad.report.AdLaunchMonitor.t0
            r8 = -1
            int r2 = (r10 > r8 ? 1 : (r10 == r8 ? 0 : -1))
            if (r2 != 0) goto L88
            com.vega.ad.report.AdLaunchMonitor.t0 = r12
        L88:
            if (r4 == 0) goto Lb5
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.SplashAdStatusReason r4 = com.vega.adapi.data.SplashAdStatusReason.k
            if (r24 == 0) goto La0
            com.vega.business.ad.report.SplashAdReportUtils r2 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r12 = 508(0x1fc, float:7.12E-43)
            r6 = r1
            r7 = r1
            r8 = r1
            r9 = r1
            r10 = r1
            r11 = r1
            r5 = r1
            com.vega.business.ad.report.SplashAdReportUtils.i(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
        La0:
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.f66115c
            r4.getClass()
            java.lang.String r0 = "reward_adfree"
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        Lb5:
            com.vega.adapi.constant.SplashScene r2 = com.vega.adapi.constant.SplashScene.f66086c
            if (r0 != r2) goto L1ef
            com.vega.subscriptionapi.utils.SplashBusinessPopupUtil r4 = com.vega.subscriptionapi.utils.SplashBusinessPopupUtil.f131993a
            com.vega.subscriptionapi.utils.SplashBusinessPopupUtil$SplashBusinessPopupType r2 = com.vega.subscriptionapi.utils.SplashBusinessPopupUtil.SplashBusinessPopupType.f131996c
            boolean r2 = r4.d(r2)
            if (r2 == 0) goto Ld9
            if (r26 == 0) goto L3b
            com.vega.adapi.data.AdFreeUserReason r4 = com.vega.adapi.data.AdFreeUserReason.o
            com.vega.business.ad.report.SplashAdReportUtils r3 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r2 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r1 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            r3.getClass()
            java.lang.String r0 = "ft_popup_showing"
            com.vega.business.ad.report.SplashAdReportUtils.f(r2, r1, r4, r0)
            goto L3b
        Ld9:
            cn.capcut.adtools.api.AdTestTools r2 = cn.capcut.adtools.api.AdTestTools.f32806a
            r2.getClass()
            cn.capcut.adtools.api.service.IAdTestToolService r2 = cn.capcut.adtools.api.AdTestTools.a()
            r2.g()
            com.vega.infrastructure.util.LifecycleManager r2 = com.vega.infrastructure.util.LifecycleManager.INSTANCE
            java.lang.ref.WeakReference r2 = r2.getTopmostActivity()
            java.lang.Object r4 = r2.get()
            boolean r2 = r3.u
            if (r2 != 0) goto Lf9
            if (r4 == 0) goto L133
            boolean r2 = r4 instanceof com.vega.business.ad.view.HotStartSplashAdActivity
            if (r2 == 0) goto L10d
        Lf9:
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r0 = com.vega.adapi.data.AdFreeUserReason.f66116d
            r4.getClass()
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r0, r1)
            goto L3b
        L10d:
            java.lang.Class r2 = r4.getClass()
            java.lang.String r4 = r2.getSimpleName()
            java.lang.String r2 = "LoginActivity"
            boolean r2 = android.text.TextUtils.equals(r2, r4)
            if (r2 == 0) goto L133
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.e
            r4.getClass()
            java.lang.String r0 = "tt_login_page"
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        L133:
            boolean r2 = r5.isHotStartShow
            if (r2 != 0) goto L158
            cn.capcut.adtools.api.service.IAdTestToolService r1 = cn.capcut.adtools.api.AdTestTools.a()
            r1.a()
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.f66115c
            int r0 = r5.closeReason
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r4.getClass()
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        L158:
            com.vega.ad.exclusion.DeepLinkDisableAdManager r4 = com.vega.ad.exclusion.DeepLinkDisableAdManager.f65676a
            com.vega.adapi.constant.AdSceneTag r8 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.core.utils.AppActivityRecorder r2 = com.vega.core.utils.AppActivityRecorder.f79544a
            android.app.Activity r2 = r2.f()
            if (r2 == 0) goto L171
            android.content.Intent r5 = INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r2)
        L16a:
            r4.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r7)
            goto L173
        L171:
            r5 = r1
            goto L16a
        L173:
            com.vega.adapi.config.AbTestConfig r2 = com.vega.ad.exclusion.DeepLinkDisableAdManager.c()     // Catch: java.lang.Throwable -> L1e7
            java.util.List<java.lang.String> r4 = r2.disableDeeplinkAdScene     // Catch: java.lang.Throwable -> L1e7
            java.lang.String r2 = r8.f66069a     // Catch: java.lang.Throwable -> L1e7
            boolean r2 = r4.contains(r2)     // Catch: java.lang.Throwable -> L1e7
            if (r2 == 0) goto L1e1
            if (r5 == 0) goto L184
            goto L186
        L184:
            r5 = r1
            goto L18a
        L186:
            android.net.Uri r5 = r5.getData()     // Catch: java.lang.Throwable -> L1e7
        L18a:
            com.vega.business.ad.report.SplashAdReportUtils r2 = com.vega.business.ad.report.SplashAdReportUtils.f75379a     // Catch: java.lang.Throwable -> L1e7
            r2.getClass()     // Catch: java.lang.Throwable -> L1e7
            java.lang.String r2 = com.vega.business.ad.report.SplashAdReportUtils.h     // Catch: java.lang.Throwable -> L1e7
            java.lang.String r9 = "push"
            if (r5 != 0) goto L19f
            boolean r2 = X.C93472yG.S(r2, r9)     // Catch: java.lang.Throwable -> L1e7
            if (r2 == 0) goto L1a7
            android.net.Uri r5 = com.vega.business.ad.report.SplashAdReportUtils.i     // Catch: java.lang.Throwable -> L1e7
            if (r5 == 0) goto L1a7
        L19f:
            java.lang.String r2 = "enter_from"
            java.lang.String r8 = com.vega.edit.base.operation.util.ExtKt.a(r5, r2)     // Catch: java.lang.Throwable -> L1e7
            if (r8 != 0) goto L1aa
        L1a7:
            r8 = r7
            if (r5 == 0) goto L1b2
        L1aa:
            java.lang.String r2 = "anchor_key"
            java.lang.String r4 = com.vega.edit.base.operation.util.ExtKt.a(r5, r2)     // Catch: java.lang.Throwable -> L1e7
            if (r4 != 0) goto L1b3
        L1b2:
            r4 = r7
        L1b3:
            boolean r2 = com.vega.ad.exclusion.DeepLinkDisableAdManager.b     // Catch: java.lang.Throwable -> L1e7
            if (r2 != 0) goto L3b9
            boolean r2 = com.vega.ad.exclusion.DeepLinkDisableAdManager.f65677c     // Catch: java.lang.Throwable -> L1e7
            if (r2 == 0) goto L1bd
            goto L3b9
        L1bd:
            boolean r2 = com.vega.ad.exclusion.DeepLinkDisableAdManager.g(r8)     // Catch: java.lang.Throwable -> L1e7
            if (r2 == 0) goto L1c5
            goto L3b9
        L1c5:
            boolean r2 = X.C93472yG.S(r8, r9)     // Catch: java.lang.Throwable -> L1e7
            if (r2 == 0) goto L1e1
            int r2 = r4.length()     // Catch: java.lang.Throwable -> L1e7
            if (r2 <= 0) goto L1d3
            goto L3b9
        L1d3:
            java.lang.String r4 = java.lang.String.valueOf(r5)     // Catch: java.lang.Throwable -> L1e7
            java.lang.String r2 = "capcut://template/detail"
            boolean r2 = X.C93472yG.S(r4, r2)     // Catch: java.lang.Throwable -> L1e7
            if (r2 == 0) goto L1e1
            goto L3b9
        L1e1:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L1e7
            kotlin.Result.m17090constructorimpl(r2)     // Catch: java.lang.Throwable -> L1e7
            goto L1ef
        L1e7:
            r2 = move-exception
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
            kotlin.Result.m17090constructorimpl(r2)
        L1ef:
            com.vega.adapi.constant.AdSceneTag r2 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            if (r24 == 0) goto L21a
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r4.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r7)
            java.util.HashMap r7 = new java.util.HashMap
            r7.<init>()
            java.lang.String r5 = "action"
            java.lang.String r4 = "splash_open"
            r7.put(r5, r4)
            java.lang.String r5 = r2.f66069a
            java.lang.String r4 = "tag"
            r7.put(r4, r5)
            com.vega.report.ReportManagerWrapper r5 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r4 = "splash_ad_status"
            r5.onEvent(r4, r7)
            r7.toString()
        L21a:
            cn.capcut.adtools.api.AdTestTools r4 = cn.capcut.adtools.api.AdTestTools.f32806a
            r4.getClass()
            cn.capcut.adtools.api.service.IAdTestToolService r4 = cn.capcut.adtools.api.AdTestTools.a()
            r4.a()
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r8 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r8.getClass()
            com.vega.adapi.config.CapCutAdSettings r4 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r7 = r4.E()
            com.vega.business.ad.impl.splash.SplashAdShowFrequencyControl$Companion r4 = com.vega.business.ad.impl.splash.SplashAdShowFrequencyControl.f75300d
            r4.getClass()
            com.vega.business.ad.impl.splash.SplashAdShowFrequencyControl r5 = com.vega.business.ad.impl.splash.SplashAdShowFrequencyControl.Companion.a(r0)
            boolean r4 = r5.c()
            if (r4 == 0) goto L27e
            if (r24 == 0) goto L255
            com.vega.business.ad.report.SplashAdReportUtils r3 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.data.SplashAdStatusReason r5 = com.vega.adapi.data.SplashAdStatusReason.e
            r13 = 508(0x1fc, float:7.12E-43)
            r7 = r1
            r8 = r1
            r9 = r1
            r10 = r1
            r11 = r1
            r12 = r1
            r6 = r1
            r4 = r2
            com.vega.business.ad.report.SplashAdReportUtils.i(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
        L255:
            if (r26 == 0) goto L269
            com.vega.business.ad.report.SplashAdReportUtils r1 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r4 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r3 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r2 = com.vega.adapi.data.AdFreeUserReason.f66117g
            r1.getClass()
            java.lang.String r1 = "show_count_limit"
            com.vega.business.ad.report.SplashAdReportUtils.f(r4, r3, r2, r1)
        L269:
            java.lang.String r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "frequencyControl: true, isShowLimitedByDailyTimes , scene="
            r2.<init>(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.vega.log.BLog.i(r3, r0)
            goto L3b
        L27e:
            com.vega.business.ad.impl.splash.SplashAdShowFrequencyControl$Result r5 = r5.d()
            boolean r4 = r5.f75303a
            if (r4 == 0) goto L2d0
            if (r24 == 0) goto L2a7
            com.vega.business.ad.report.SplashAdReportUtils r15 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.data.SplashAdStatusReason r3 = r5.b
            if (r3 != 0) goto L290
            com.vega.adapi.data.SplashAdStatusReason r3 = com.vega.adapi.data.SplashAdStatusReason.f66160d
        L290:
            r25 = 508(0x1fc, float:7.12E-43)
            r19 = r1
            r20 = r1
            r21 = r1
            r22 = r1
            r23 = r1
            r24 = r1
            r18 = r1
            r16 = r2
            r17 = r3
            com.vega.business.ad.report.SplashAdReportUtils.i(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
        L2a7:
            if (r26 == 0) goto L2bb
            com.vega.business.ad.report.SplashAdReportUtils r1 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r4 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r3 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r2 = com.vega.adapi.data.AdFreeUserReason.f66117g
            r1.getClass()
            java.lang.String r1 = "colddown_peroid"
            com.vega.business.ad.report.SplashAdReportUtils.f(r4, r3, r2, r1)
        L2bb:
            java.lang.String r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "frequencyControl: true , isShowLimitedByTimeInterval , scene="
            r2.<init>(r1)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.vega.log.BLog.i(r3, r0)
            goto L3b
        L2d0:
            com.vega.adapi.constant.SplashScene r4 = com.vega.adapi.constant.SplashScene.f66086c
            if (r0 != r4) goto L303
            int r5 = r7.adaptiveHubSdkInitAbTest
            r4 = 2
            if (r5 != r4) goto L35a
            if (r6 == 0) goto L2dd
            com.vega.adapi.config.splash.PreloadScenes r1 = r6.f66041a
        L2dd:
            com.vega.adapi.config.splash.PreloadScenes r4 = com.vega.adapi.config.splash.PreloadScenes.b
            if (r1 != r4) goto L35a
            java.lang.String r3 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r1 = "frequencyControl : PreloadScenes.BACK_GROUND , adaptiveHubSdkInitAbTest="
            r2.<init>(r1)
            com.vega.adapi.config.CapCutAdSettings r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r1 = r1.E()
            int r1 = r1.adaptiveHubSdkInitAbTest
            r2.append(r1)
            java.lang.String r1 = " , forcePreloadHotSplashAdWhenEnterBackground"
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            com.vega.log.BLog.i(r3, r1)
        L303:
            com.vega.adapi.constant.SplashScene r1 = com.vega.adapi.constant.SplashScene.f66086c
            if (r0 != r1) goto L44
            r8.getClass()
            com.vega.adapi.config.CapCutAdSettings r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.AbTestConfig r1 = r1.a()
            boolean r4 = r1.disableHotSplashShowInBlacklist
            boolean r3 = r1.disableHotSplashReqInBlacklist
            java.util.List<java.lang.String> r2 = r1.androidHotSplashBlackList
            com.vega.business.ad.report.SplashAdReportUtils r15 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r15.getClass()
            java.lang.String r1 = com.vega.business.ad.report.SplashAdReportUtils.f75382g
            java.util.Objects.toString(r2)
            if (r1 == 0) goto L44
            boolean r1 = r2.contains(r1)
            if (r1 == 0) goto L44
            if (r4 == 0) goto L3b3
            if (r27 == 0) goto L3b3
            if (r24 == 0) goto L349
            com.vega.adapi.constant.AdSceneTag r16 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.SplashAdStatusReason r17 = com.vega.adapi.data.SplashAdStatusReason.m
            r18 = 0
            r25 = 508(0x1fc, float:7.12E-43)
            r19 = r18
            r20 = r18
            r21 = r18
            r22 = r18
            r23 = r18
            r24 = r18
            com.vega.business.ad.report.SplashAdReportUtils.i(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
        L349:
            if (r26 == 0) goto L3b
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.j
            java.lang.String r0 = "black_list"
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        L35a:
            java.lang.String r5 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "frequencyControl : adaptiveHubSdkInitAbTest="
            r4.<init>(r1)
            com.vega.adapi.config.CapCutAdSettings r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r1 = r1.E()
            int r1 = r1.adaptiveHubSdkInitAbTest
            r4.append(r1)
            java.lang.String r1 = " , not forcePreloadHotSplashAdWhenEnterBackground or not isPreload, cal interval..."
            r4.append(r1)
            java.lang.String r1 = r4.toString()
            com.vega.log.BLog.i(r5, r1)
            long r5 = java.lang.System.currentTimeMillis()
            long r3 = r3.f
            long r5 = r5 - r3
            int r1 = r7.hotStartInterval
            long r3 = (long) r1
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 >= 0) goto L303
            if (r24 == 0) goto L39d
            com.vega.business.ad.report.SplashAdReportUtils r1 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.data.SplashAdStatusReason r3 = com.vega.adapi.data.SplashAdStatusReason.b
            r4 = 0
            r11 = 508(0x1fc, float:7.12E-43)
            r5 = r4
            r6 = r4
            r7 = r4
            r8 = r4
            r9 = r4
            r10 = r4
            r2 = r2
            com.vega.business.ad.report.SplashAdReportUtils.i(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
        L39d:
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.f66117g
            r4.getClass()
            java.lang.String r0 = "warm_interval"
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        L3b3:
            if (r3 == 0) goto L44
            if (r27 != 0) goto L44
            goto L3b
        L3b9:
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.e
            r4.getClass()
            java.lang.String r0 = "push_or_anchor"
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        L3cf:
            com.vega.adapi.constant.AdSceneTag r16 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.SplashAdStatusReason r17 = com.vega.adapi.data.SplashAdStatusReason.j
            if (r24 == 0) goto L3ec
            com.vega.business.ad.report.SplashAdReportUtils r15 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r25 = 508(0x1fc, float:7.12E-43)
            r19 = r1
            r20 = r1
            r21 = r1
            r22 = r1
            r23 = r1
            r24 = r1
            r18 = r1
            com.vega.business.ad.report.SplashAdReportUtils.i(r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25)
        L3ec:
            if (r26 == 0) goto L3b
            com.vega.business.ad.report.SplashAdReportUtils r4 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            com.vega.adapi.constant.AdSceneTag r3 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            com.vega.adapi.data.AdFreeUserLabel r2 = com.vega.adapi.data.AdFreeUserLabel.f66111c
            com.vega.adapi.data.AdFreeUserReason r1 = com.vega.adapi.data.AdFreeUserReason.f66115c
            int r0 = r5.closeReason
            if (r0 != 0) goto L406
            java.lang.String r0 = "no_config"
        L3fe:
            r4.getClass()
            com.vega.business.ad.report.SplashAdReportUtils.f(r3, r2, r1, r0)
            goto L3b
        L406:
            java.lang.String r0 = java.lang.String.valueOf(r0)
            goto L3fe
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.business.ad.base.CCSplashAdManagerWrapper.f(com.vega.business.ad.base.CCSplashAdManagerWrapper, com.vega.adapi.constant.SplashScene, boolean, com.vega.adapi.config.splash.SplashPreloadConfig, boolean, boolean, int):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00df  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00e4 A[PHI: r2 r10
      0x00e4: PHI (r2v2 java.lang.String) = (r2v4 java.lang.String), (r2v5 java.lang.String) binds: [B:44:0x009c, B:42:0x0097] A[DONT_GENERATE, DONT_INLINE]
      0x00e4: PHI (r10v1 java.lang.String) = (r10v3 java.lang.String), (r10v0 java.lang.String) binds: [B:44:0x009c, B:42:0x0097] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00ec  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void k(com.vega.business.ad.base.CCSplashAdManagerWrapper r31, com.vega.adapi.constant.SplashScene r32, com.vega.adapi.report.AdAction r33, long r34, long r36, java.lang.Integer r38, java.lang.String r39, com.vega.adapi.config.splash.SplashRequestFrom r40, java.lang.Long r41, java.lang.Integer r42, int r43) throws org.json.JSONException {
        /*
            r1 = r43
            r23 = r42
            r20 = r41
            r19 = r40
            r18 = r39
            r17 = r38
            r0 = r1 & 16
            r25 = 0
            if (r0 == 0) goto L14
            r17 = r25
        L14:
            r0 = r1 & 32
            if (r0 == 0) goto L1a
            r18 = r25
        L1a:
            r0 = r1 & 64
            if (r0 == 0) goto L20
            r19 = r25
        L20:
            r0 = r1 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L26
            r20 = r25
        L26:
            r0 = r1 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L2c
            r23 = r25
        L2c:
            r1 = r31
            r1.getClass()
            r2 = r32
            com.vega.adapi.constant.AdSceneTag r6 = com.vega.adapi.constant.SplashSceneKt.a(r2)
            androidx.lifecycle.MutableLiveData<com.vega.business.ad.model.SplashAdModel> r0 = r1.f75217a
            java.lang.Object r5 = r0.getValue()
            com.vega.business.ad.model.SplashAdModel r5 = (com.vega.business.ad.model.SplashAdModel) r5
            if (r5 == 0) goto L45
            java.lang.String r8 = r5.s
            if (r8 != 0) goto L56
        L45:
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r0.getClass()
            com.vega.adapi.config.CapCutAdSettings r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r0 = r0.E()
            java.lang.String r8 = r0.o(r2)
        L56:
            com.vega.adapi.constant.AdSceneTag r0 = com.vega.adapi.constant.AdSceneTag.f66067d
            if (r6 != r0) goto Lec
            com.vega.business.ad.view.HotStartSplashAdActivity$Companion r0 = com.vega.business.ad.view.HotStartSplashAdActivity.b0
            r0.getClass()
            boolean r0 = com.vega.business.ad.view.HotStartSplashAdActivity.i0
            if (r0 == 0) goto Lec
            if (r5 == 0) goto Lec
            java.lang.String r3 = r5.o
        L67:
            java.lang.String r10 = ""
            if (r5 == 0) goto L6f
            java.lang.String r0 = r5.t
            if (r0 != 0) goto L70
        L6f:
            r0 = r10
        L70:
            com.vega.adapi.constant.AdSDK r29 = r1.c()
            if (r5 == 0) goto L7a
            com.vega.adapi.constant.AdSDK r9 = r5.q
            if (r9 != 0) goto L7e
        L7a:
            com.vega.adapi.constant.AdSDK r9 = r1.c()
        L7e:
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r1.getClass()
            com.vega.adapi.config.CapCutAdSettings r1 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r1 = r1.E()
            java.lang.String r28 = r1.n(r2)
            if (r5 == 0) goto Le9
            java.lang.String r2 = r5.r
            java.lang.String r1 = r5.f
            if (r1 != 0) goto Le7
        L97:
            if (r5 == 0) goto Le4
        L99:
            boolean r4 = r5.f75375g
            r1 = 1
            if (r4 != r1) goto Le4
            com.vega.adapi.report.AdType r13 = com.vega.adapi.report.AdType.f66218c
        La0:
            if (r5 == 0) goto Ldf
            java.lang.Double r11 = r5.i
            java.lang.String r1 = r5.m
            org.json.JSONObject r25 = r5.a()
        Laa:
            if (r5 == 0) goto Lb6
            com.vega.adapi.data.splash.SplashAdDataProxy r4 = r5.f75373c
            if (r4 == 0) goto Lb6
            com.vega.adapi.constant.AdFormat r26 = r4.a()
            if (r26 != 0) goto Lb8
        Lb6:
            com.vega.adapi.constant.AdFormat r26 = com.vega.adapi.constant.AdFormat.f
        Lb8:
            com.vega.adapi.data.SplashAdActionReportData r5 = new com.vega.adapi.data.SplashAdActionReportData
            java.lang.String r12 = ""
            java.lang.Long r16 = java.lang.Long.valueOf(r36)
            r22 = 0
            r32 = 655362(0xa0002, float:9.18358E-40)
            r7 = r33
            r14 = r34
            r21 = r3
            r24 = r22
            r27 = r1
            r30 = r0
            r31 = r2
            r5.<init>(r6, r7, r8, r9, r10, r11, r12, r13, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32)
            com.vega.business.ad.report.SplashAdReportUtils r0 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r0.getClass()
            com.vega.business.ad.report.SplashAdReportUtils.e(r5)
            return
        Ldf:
            r11 = r25
            r1 = r25
            goto Laa
        Le4:
            com.vega.adapi.report.AdType r13 = com.vega.adapi.report.AdType.b
            goto La0
        Le7:
            r10 = r1
            goto L99
        Le9:
            r2 = r25
            goto L97
        Lec:
            r3 = r25
            goto L67
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.business.ad.base.CCSplashAdManagerWrapper.k(com.vega.business.ad.base.CCSplashAdManagerWrapper, com.vega.adapi.constant.SplashScene, com.vega.adapi.report.AdAction, long, long, java.lang.Integer, java.lang.String, com.vega.adapi.config.splash.SplashRequestFrom, java.lang.Long, java.lang.Integer, int):void");
    }

    public static /* synthetic */ void m(CCSplashAdManagerWrapper cCSplashAdManagerWrapper, SplashScene splashScene, AdStage adStage, AdRequestTarget adRequestTarget, String str, String str2, String str3, long j, long j2, AdSDK adSDK, String str4, String str5, AdFormat adFormat, String str6, String str7, JSONObject jSONObject, int i) {
        String str8 = str5;
        AdFormat adFormat2 = adFormat;
        String str9 = str6;
        String str10 = str7;
        AdRequestType adRequestType = AdRequestType.b;
        if ((i & SpeechEngineDefines.ASR_WORK_MODE_OFFLINE) != 0) {
            str8 = null;
        }
        if ((i & SpeechEngineDefines.TTS_WORK_MODE_BOTH) != 0) {
            adFormat2 = null;
        }
        if ((i & 8192) != 0) {
            str9 = null;
        }
        if ((i & 16384) != 0) {
            str10 = null;
        }
        cCSplashAdManagerWrapper.l(splashScene, adStage, adRequestType, adRequestTarget, str, str2, str3, j, j2, adSDK, str4, str8, adFormat2, str9, str10, (i & 32768) == 0 ? jSONObject : null);
    }

    public static void n(String str, Long l, Map map, Throwable th, Map map2, int i) throws JSONException {
        String strTake = null;
        if ((i & 8) != 0) {
            th = null;
        }
        if ((i & 16) != 0) {
            map2 = MapsKt__MapsKt.emptyMap();
        }
        SplashAdReportUtils splashAdReportUtils = SplashAdReportUtils.f75379a;
        Pair[] pairArr = new Pair[3];
        pairArr[0] = TuplesKt.to("exception_type", th != null ? th.getClass().getName() : null);
        pairArr[1] = TuplesKt.to("exception_message", th != null ? th.getMessage() : null);
        if (th != null) {
            String stackTraceString = Log.getStackTraceString(th);
            Intrinsics.checkNotNullExpressionValue(stackTraceString, "");
            strTake = StringsKt___StringsKt.take(stackTraceString, 500);
        }
        pairArr[2] = TuplesKt.to("stack_trace", strTake);
        Map mapPlus = MapsKt__MapsKt.plus(MapsKt__MapsKt.plus(map, MapsKt__MapsKt.mapOf(pairArr)), map2);
        splashAdReportUtils.getClass();
        SplashAdReportUtils.g("dpl_failed", str, l, mapPlus);
    }

    public static final void o(AcComponentActivity acComponentActivity, final String str, final String str2, final Long l, final DeeplinkInvokeDetector.ResolveSnapshot resolveSnapshot, final Map<String, ? extends Object> map) {
        DeeplinkInvokeDetector.Companion companion = DeeplinkInvokeDetector.t;
        Function2<DeeplinkInvokeDetector.DetectResult, String, Unit> function2 = new Function2<DeeplinkInvokeDetector.DetectResult, String, Unit>(str, resolveSnapshot, str2, l, map) { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$tryOpenBySchema$2$startDetect$1
            public final /* synthetic */ DeeplinkInvokeDetector.ResolveSnapshot e;
            public final /* synthetic */ String f;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Long f75228g;
            public final /* synthetic */ Map<String, Object> h;

            /* loaded from: classes4.dex */
            public /* synthetic */ class WhenMappings {
                static {
                    DeeplinkInvokeDetector.DetectResult.values();
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
                this.e = resolveSnapshot;
                this.f = str2;
                this.f75228g = l;
                this.h = map;
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(DeeplinkInvokeDetector.DetectResult detectResult, String str3) throws JSONException {
                DeeplinkInvokeDetector.DetectResult detectResult2 = detectResult;
                String str4 = str3;
                Intrinsics.checkNotNullParameter(detectResult2, "");
                Intrinsics.checkNotNullParameter(str4, "");
                int iOrdinal = detectResult2.ordinal();
                if (iOrdinal == 0) {
                    Objects.toString(this.e);
                    SplashAdReportUtils splashAdReportUtils = SplashAdReportUtils.f75379a;
                    String str5 = this.f;
                    Long l2 = this.f75228g;
                    Map<String, Object> map2 = this.h;
                    splashAdReportUtils.getClass();
                    SplashAdReportUtils.g("dpl_success", str5, l2, map2);
                } else if (iOrdinal == 1) {
                    Objects.toString(this.e);
                    CCSplashAdManagerWrapper.n(this.f, this.f75228g, this.h, null, MapsKt__MapsJVMKt.mapOf(TuplesKt.to("reason", str4)), 8);
                } else if (iOrdinal == 2) {
                    Objects.toString(this.e);
                    String str6 = this.f;
                    Long l3 = this.f75228g;
                    Map<String, Object> map3 = this.h;
                    Pair[] pairArr = new Pair[2];
                    pairArr[0] = TuplesKt.to("detect_result", "unknown");
                    if (str4.length() == 0) {
                        str4 = "unknown";
                    }
                    pairArr[1] = TuplesKt.to("reason", str4);
                    CCSplashAdManagerWrapper.n(str6, l3, map3, null, MapsKt__MapsKt.mapOf(pairArr), 8);
                }
                return Unit.INSTANCE;
            }
        };
        companion.getClass();
        Intrinsics.checkNotNullParameter(acComponentActivity, "");
        Intrinsics.checkNotNullParameter(resolveSnapshot, "");
        final DeeplinkInvokeDetector deeplinkInvokeDetector = new DeeplinkInvokeDetector(acComponentActivity, resolveSnapshot, function2);
        if (!Intrinsics.areEqual(Looper.myLooper(), Looper.getMainLooper())) {
            deeplinkInvokeDetector.f75231d.post(new Runnable() { // from class: X.4gI
                @Override // java.lang.Runnable
                public final void run() {
                    DeeplinkInvokeDetector deeplinkInvokeDetector2 = deeplinkInvokeDetector;
                    deeplinkInvokeDetector2.a();
                    deeplinkInvokeDetector2.b("startPost");
                }
            });
        } else {
            deeplinkInvokeDetector.a();
            deeplinkInvokeDetector.b("start");
        }
    }

    public final void a(final SplashDismissReason splashDismissReason) {
        E.getClass();
        boolean z = Companion.a().getAbTestConfig().splashDismissOpt;
        BLog.i(F, "dismissSplashAdView ccSplashAdView: " + this.q + ", splashDismissOpt: " + z + ", isUiThread: " + ThreadUtilKt.a());
        final long jUptimeMillis = SystemClock.uptimeMillis();
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$dismissSplashAdView$block$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                AdLaunchMonitor adLaunchMonitor = AdLaunchMonitor.f65929a;
                long jUptimeMillis2 = SystemClock.uptimeMillis() - jUptimeMillis;
                adLaunchMonitor.getClass();
                AdLaunchMonitor.m0 = jUptimeMillis2;
                BLog.i(CCSplashAdManagerWrapper.F, "dismissSplashAdView ccSplashAdView?.dismiss");
                ISplashAdView iSplashAdView = this.q;
                if (iSplashAdView == null) {
                    return null;
                }
                iSplashAdView.Q0(splashDismissReason);
                return Unit.INSTANCE;
            }
        };
        if (z) {
            ThreadUtilKtKt.a(new Function0<Unit>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$dismissSplashAdView$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    function0.invoke();
                    return Unit.INSTANCE;
                }
            });
        } else {
            ThreadUtilKt.b(0L, new Function0<Unit>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$dismissSplashAdView$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    function0.invoke();
                    return Unit.INSTANCE;
                }
            });
        }
    }

    public final AdSDK c() {
        ISplashAdLoader iSplashAdLoader = this.m;
        if (iSplashAdLoader == null) {
            return ((CapCutAdSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null)).E().a();
        }
        iSplashAdLoader.j();
        return AdSDK.e;
    }

    public final SplashAdCallBack d() {
        return (SplashAdCallBack) this.b.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0034 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b5  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0111  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x01e3 A[ADDED_TO_REGION, ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0219  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e(com.vega.ad.loader.splash.HubSplashAdLoader.HubAdClickInfo r23) throws org.json.JSONException {
        /*
            r22 = this;
            r13 = 0
            if (r23 != 0) goto L4
            return r13
        L4:
            java.lang.Boolean r1 = r23.f()
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            r12 = 1
            if (r0 == 0) goto L12
            return r12
        L12:
            java.lang.String r8 = r23.d()
            java.lang.String r2 = r23.c()
            java.lang.Long r1 = r23.a()
            if (r8 == 0) goto L26
            int r0 = r8.length()
            if (r0 != 0) goto L174
        L26:
            r0 = 1
        L27:
            r6 = 268435456(0x10000000, float:2.5243549E-29)
            java.lang.String r4 = ""
            java.lang.String r15 = "android.intent.action.VIEW"
            r5 = r22
            if (r0 == 0) goto L36
        L31:
            r0 = 0
            if (r0 == 0) goto L1c1
        L34:
            r0 = 1
            return r0
        L36:
            android.net.Uri r9 = android.net.Uri.parse(r8)
            java.lang.String r7 = r9.getScheme()
            java.lang.String r0 = "sslocal"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r7)
            java.lang.String r3 = "capcut"
            if (r0 == 0) goto L50
            java.lang.String r0 = X.C93472yG.H(r8, r7, r3)
            android.net.Uri r9 = android.net.Uri.parse(r0)
        L50:
            android.content.Intent r7 = new android.content.Intent
            r7.<init>(r15)
            r7.setData(r9)
            java.lang.String r0 = r9.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            boolean r0 = X.C93472yG.S(r0, r3)
            if (r0 == 0) goto L82
            com.vega.business.ad.view.ISplashAdView r0 = r5.q
            if (r0 == 0) goto L31
            com.vega.ui.accomponent.AcComponentActivity r1 = r0.getActivity()
            r1.setIntent(r7)
            kotlin.Lazy r0 = r5.v
            java.lang.Object r0 = r0.getValue()
            com.lemon.lv.editor.proxy.IAdProxy r0 = (com.lemon.lv.editor.proxy.IAdProxy) r0
            com.vega.ui.accomponent.AcComponent r0 = r0.b(r1, r7)
            r0.b = r7
            r1.H1(r0)
            goto L34
        L82:
            com.vega.business.ad.view.ISplashAdView r0 = r5.q
            if (r0 == 0) goto L1c1
            com.vega.ui.accomponent.AcComponentActivity r3 = r0.getActivity()
            r7.addFlags(r6)
            java.lang.String r6 = r9.getScheme()
            r11 = 0
            if (r6 == 0) goto L116
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.util.Locale r0 = java.util.Locale.ROOT
            java.lang.String r6 = r6.toLowerCase(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            java.util.Map<java.lang.String, java.lang.String> r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.H
            java.lang.Object r6 = r0.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto Lb0
            int r0 = r6.length()
            if (r0 != 0) goto L114
        Lb0:
            r0 = 1
        Lb1:
            r14 = r0 ^ 1
            if (r14 == 0) goto Lb8
            r7.setPackage(r6)
        Lb8:
            r0 = 4
            kotlin.Pair[] r10 = new kotlin.Pair[r0]
            java.lang.String r6 = "deep_link_url"
            java.lang.String r0 = r9.toString()
            kotlin.Pair r0 = kotlin.TuplesKt.to(r6, r0)
            r10[r13] = r0
            java.lang.String r6 = "set_package"
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r14)
            kotlin.Pair r0 = kotlin.TuplesKt.to(r6, r0)
            r10[r12] = r0
            if (r14 == 0) goto L111
            java.lang.String r6 = "scheme_map"
        Ld7:
            java.lang.String r0 = "package_source"
            kotlin.Pair r6 = kotlin.TuplesKt.to(r0, r6)
            r0 = 2
            r10[r0] = r6
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            java.lang.String r9 = "fallback_retry"
            kotlin.Pair r6 = kotlin.TuplesKt.to(r9, r0)
            r0 = 3
            r10[r0] = r6
            java.util.Map r6 = kotlin.collections.MapsKt__MapsKt.mutableMapOf(r10)
            com.vega.business.ad.base.DeeplinkInvokeDetector$Companion r0 = com.vega.business.ad.base.DeeplinkInvokeDetector.t
            r0.getClass()
            com.vega.business.ad.base.DeeplinkInvokeDetector$ResolveSnapshot r10 = com.vega.business.ad.base.DeeplinkInvokeDetector.Companion.a(r3, r7)
            java.util.Set<java.lang.String> r0 = r10.f75236a
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 != 0) goto L118
            r10.toString()
            r12 = 24
            r8 = r1
            r9 = r6
            r10 = r11
            r11 = r11
            r7 = r2
            n(r7, r8, r9, r10, r11, r12)
            goto L31
        L111:
            java.lang.String r6 = "none"
            goto Ld7
        L114:
            r0 = 0
            goto Lb1
        L116:
            r6 = r11
            goto Lb0
        L118:
            com.vega.business.ad.report.SplashAdReportUtils r0 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r0.getClass()
            java.lang.String r0 = "open_url_app"
            com.vega.business.ad.report.SplashAdReportUtils.g(r0, r2, r1, r6)
            INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(r3, r7)     // Catch: android.content.ActivityNotFoundException -> L138 java.lang.Throwable -> L1a3
            r16 = r3
            r18 = r2
            r17 = r8
            r19 = r1
            r20 = r10
            r21 = r6
            o(r16, r17, r18, r19, r20, r21)     // Catch: android.content.ActivityNotFoundException -> L136 java.lang.Throwable -> L177
            goto L34
        L136:
            r10 = move-exception
            goto L139
        L138:
            r10 = move-exception
        L139:
            java.lang.String r12 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            java.lang.String r0 = "tryOpenBySchema startActivity ActivityNotFoundException: "
            r13.<init>(r0)
            r13.append(r10)
            java.lang.String r0 = r13.toString()
            com.vega.log.BLog.e(r12, r0)
            if (r14 == 0) goto L198
            r7.setPackage(r11)
            com.vega.business.ad.base.DeeplinkInvokeDetector$Companion r0 = com.vega.business.ad.base.DeeplinkInvokeDetector.t
            r0.getClass()
            com.vega.business.ad.base.DeeplinkInvokeDetector$ResolveSnapshot r10 = com.vega.business.ad.base.DeeplinkInvokeDetector.Companion.a(r3, r7)
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            kotlin.Pair r0 = kotlin.TuplesKt.to(r9, r0)
            java.util.Map r0 = kotlin.collections.MapsKt__MapsJVMKt.mapOf(r0)
            java.util.Map r11 = kotlin.collections.MapsKt__MapsKt.plus(r6, r0)
            INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(r3, r7)     // Catch: java.lang.Throwable -> L179
            r6 = r3
            r7 = r8
            r8 = r2
            r9 = r1
            o(r6, r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L179
            goto L34
        L174:
            r0 = 0
            goto L27
        L177:
            r8 = move-exception
            goto L1a4
        L179:
            r7 = move-exception
            java.lang.String r6 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "tryOpenBySchema fallback retry error: "
            r3.<init>(r0)
            r3.append(r7)
            java.lang.String r0 = r3.toString()
            com.vega.log.BLog.e(r6, r0)
            r12 = 0
            r13 = 16
            r9 = r1
            r10 = r11
            r11 = r7
            r8 = r2
            n(r8, r9, r10, r11, r12, r13)
            goto L1c1
        L198:
            r11 = 0
            r12 = 16
            r8 = r1
            r9 = r6
            r10 = r10
            r7 = r2
            n(r7, r8, r9, r10, r11, r12)
            goto L1c1
        L1a3:
            r8 = move-exception
        L1a4:
            java.lang.String r7 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "tryOpenBySchema startActivity error: "
            r3.<init>(r0)
            r3.append(r8)
            java.lang.String r0 = r3.toString()
            com.vega.log.BLog.e(r7, r0)
            r12 = 0
            r13 = 16
            r9 = r1
            r10 = r6
            r11 = r8
            r8 = r2
            n(r8, r9, r10, r11, r12, r13)
        L1c1:
            java.lang.String r14 = r23.e()
            java.lang.String r13 = r23.c()
            java.lang.Long r12 = r23.a()
            java.lang.String r2 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            if (r14 == 0) goto L1d7
            int r0 = r14.length()
            if (r0 != 0) goto L200
        L1d7:
            java.lang.String r1 = r23.b()
            if (r1 == 0) goto L1e3
            int r0 = r1.length()
            if (r0 != 0) goto L1e5
        L1e3:
            r0 = 0
            return r0
        L1e5:
            android.net.Uri r0 = android.net.Uri.parse(r1)
            android.content.Intent r1 = new android.content.Intent
            r1.<init>(r15, r0)
            r0 = 268435456(0x10000000, float:2.5243549E-29)
            r1.addFlags(r0)
            com.vega.business.ad.view.ISplashAdView r0 = r5.q
            if (r0 == 0) goto L1e3
            com.vega.ui.accomponent.AcComponentActivity r0 = r0.getActivity()
            INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(r0, r1)
            r0 = 1
            return r0
        L200:
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r0.getClass()
            kotlin.Lazy<com.vega.business.ad.config.GoogleAdSettings> r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.f75216J
            java.lang.Object r0 = r0.getValue()
            com.vega.business.ad.config.GoogleAdSettings r0 = (com.vega.business.ad.config.GoogleAdSettings) r0
            com.vega.adapi.config.CCAdSecLinkConfig r0 = r0.getCcAdSecLinkConfig()
            boolean r11 = r0.a()
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L22a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "tryOpenByWebActivity , enableSec="
            r1.<init>(r0)
            r1.append(r11)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r2, r0)
        L22a:
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r10 = "render_type"
            java.lang.String r9 = "h5"
            r1.put(r10, r9)
            java.lang.String r8 = "render_type_2"
            r0 = 0
            r1.put(r8, r0)
            java.lang.String r0 = "first_page"
            java.lang.String r7 = "1"
            r1.put(r0, r7)
            java.lang.String r0 = "next_url"
            r1.put(r0, r14)
            java.lang.String r6 = r1.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r6, r4)
            com.vega.business.ad.view.ISplashAdView r3 = r5.q
            if (r3 == 0) goto L1d7
            com.bytedance.privacy.sandbox.channel.webrouter.WebRouterValidator r2 = com.bytedance.privacy.sandbox.channel.webrouter.WebRouterValidator.INSTANCE
            com.bytedance.privacy.sandbox.channel.webrouter.WebRouterValidator$WebviewRuntime r1 = com.bytedance.privacy.sandbox.channel.webrouter.WebRouterValidator.WebviewRuntime.PURE_RUNTIME
            java.lang.String r0 = "402657536"
            com.bytedance.privacy.sandbox.channel.webrouter.WebRouterValidator$ValidateResult r0 = r2.validate(r14, r1, r0)
            boolean r0 = r0.isPassed()
            if (r0 == 0) goto L1d7
            com.vega.ui.accomponent.AcComponentActivity r1 = r3.getActivity()
            java.lang.String r0 = "//main/web"
            com.bytedance.router.SmartRoute r1 = com.bytedance.router.SmartRouter.buildRoute(r1, r0)
            java.lang.String r0 = "web_url"
            r1.withParam(r0, r14)
            r3 = 1
            r2 = r11 ^ 1
            java.lang.String r0 = "disable_sec_link"
            r1.withParam(r0, r2)
            java.lang.String r0 = "is_ad"
            r1.withParam(r0, r3)
            java.lang.String r0 = "ad_cid"
            r1.withParam(r0, r12)
            java.lang.String r5 = "ad_extra_data"
            r1.withParam(r5, r6)
            java.lang.String r0 = "ad_log_extra"
            r1.withParam(r0, r13)
            r1.open()
            com.vega.business.ad.report.SplashAdReportUtils r0 = com.vega.business.ad.report.SplashAdReportUtils.f75379a
            r0.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r4)
            com.vega.report.ReportManagerWrapper r3 = com.vega.report.ReportManagerWrapper.INSTANCE
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            r1.putOpt(r10, r9)
            r0 = 0
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.putOpt(r8, r0)
            r2.put(r5, r1)
            java.lang.String r0 = "log_extra"
            r2.put(r0, r13)
            java.lang.String r0 = "is_ad_event"
            r2.put(r0, r7)
            java.lang.String r1 = "category"
            java.lang.String r0 = "umeng"
            r2.put(r1, r0)
            java.lang.String r0 = "value"
            r2.put(r0, r12)
            java.lang.String r1 = "tag"
            java.lang.String r0 = "landingpage"
            r2.put(r1, r0)
            java.lang.String r0 = "open_url_h5"
            r3.onEvent(r0, r2)
            r0 = 1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.business.ad.base.CCSplashAdManagerWrapper.e(com.vega.ad.loader.splash.HubSplashAdLoader$HubAdClickInfo):boolean");
    }

    public final void g(AdSDK adSDK, SplashScene splashScene, SplashPreloadConfig splashPreloadConfig) {
        String string;
        long j;
        Intrinsics.checkNotNullParameter(adSDK, "");
        Intrinsics.checkNotNullParameter(splashScene, "");
        Intrinsics.checkNotNullParameter(splashPreloadConfig, "");
        if (this.j.get()) {
            return;
        }
        if (!this.i.get()) {
            BLog.e(F, "loadSplashAd: init not complete");
            return;
        }
        AdStage adStage = AdStage.f66070c;
        this.k = adStage;
        this.j.set(true);
        boolean zA = splashPreloadConfig.a();
        if (zA) {
            string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
        } else {
            SplashAdReportUtils.f75379a.getClass();
            string = SplashAdReportUtils.l;
        }
        this.A = string;
        AdNpthManager adNpthManager = AdNpthManager.f65907a;
        String str = SplashSceneKt.a(splashScene).f66069a;
        String str2 = splashPreloadConfig.b.f66044a;
        String str3 = this.A;
        String strC = AdDataExKt.c(c());
        String str4 = adSDK.f66065a;
        E.getClass();
        String strO = Companion.a().E().o(this.n);
        String strO2 = Companion.a().E().o(this.n);
        String strN = Companion.a().E().n(this.n);
        adNpthManager.getClass();
        AdNpthManager.e(str, str2, str3, strC, str4, str4, strO, strO2, strN);
        String str5 = F;
        splashScene.toString();
        this.n = splashScene;
        this.o = splashPreloadConfig;
        SplashAdConfigData splashAdConfigDataE = Companion.a().E();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("speed_bid", Companion.a().getAbTestConfig().splashImmediatelyGetAd);
        jSONObject.put("speed_bid_cost_time", 0L);
        SplashScene splashScene2 = this.n;
        SplashScene splashScene3 = SplashScene.b;
        if (splashScene2 == splashScene3) {
            jSONObject.put("is_splashadn_overtime", this.C);
            SplashSpManager.f75304a.getClass();
            jSONObject.put("cold_request_fail_streak", SplashSpManager.a().f("cold_request_fail_streak", 0));
        }
        int i = 10000;
        if (splashScene != splashScene3) {
            int i2 = splashAdConfigDataE.hotSplashRequestTimeOut;
            if (i2 > 0) {
                BLog.i(str5, "set request time out for hot splash , hotSplashRequestTimeOut=" + i2);
                i = i2;
            } else {
                BLog.i(str5, "set request time out for hot splash , DEFAULT_LOAD_AD_TIME_OUT_IN_MS=10000");
            }
        } else {
            int i3 = splashAdConfigDataE.aggregationBiddingTimeout;
            if (i3 > 0) {
                jSONObject.put("bidding_timeout", i3);
                jSONObject.put("bidding_timeout_type", "config");
                i = i3;
            } else {
                jSONObject.put("bidding_timeout", 10000);
                jSONObject.put("bidding_timeout_type", "default");
            }
        }
        this.f75219d = SystemClock.uptimeMillis();
        ISplashAdLoader iSplashAdLoader = this.m;
        if (iSplashAdLoader != null) {
            Context context = this.l;
            if (context == null) {
                Intrinsics.throwUninitializedPropertyAccessException("context");
                context = null;
            }
            iSplashAdLoader.i(context, new SplashRequest(this.A, splashScene, splashAdConfigDataE.o(splashScene), i));
        }
        BLog.w(str5, "load ad , scene=" + splashScene + " , splash ad bidding time out config=" + jSONObject + ", position id=" + splashAdConfigDataE.o(splashScene) + " , timeout=" + i + " , splashPreloadConfig=" + splashPreloadConfig);
        if (this.n == splashScene3) {
            AdLaunchMonitor adLaunchMonitor = AdLaunchMonitor.f65929a;
            long j2 = this.f75219d;
            long jG = ((IAdSdkApi) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAdSdkApi.class), null)).g();
            int iCoerceAtLeast = RangesKt___RangesKt.coerceAtLeast(i - ((int) (jG > 0 ? SystemClock.uptimeMillis() - jG : 0L)), 0);
            adLaunchMonitor.getClass();
            if (AdLaunchMonitor.T == -1) {
                AdLaunchMonitor.S = SystemClock.uptimeMillis() - j2;
                AdLaunchMonitor.T = AdLaunchMonitor.d(j2);
                AdLaunchMonitor.V = i;
                AdLaunchMonitor.W = iCoerceAtLeast;
            }
            ColdStartSplashAdComponent.Companion companion = ColdStartSplashAdComponent.u;
            SystemClock.uptimeMillis();
            companion.getClass();
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.n == splashScene3) {
            j = this.f75218c;
        } else {
            jUptimeMillis = System.currentTimeMillis();
            j = this.f75220g;
        }
        long j3 = jUptimeMillis - j;
        this.h = this.f75220g;
        SplashAdReportUtils.f75379a.getClass();
        String str6 = SplashAdReportUtils.l;
        this.B = str6;
        m(this, splashScene, adStage, zA ? AdRequestTarget.f66119c : AdRequestTarget.b, splashPreloadConfig.b.f66044a, this.A, str6, j3, j3, adSDK, Companion.a().E().o(this.n), AdDataExKt.c(c()), null, null, null, jSONObject, 28672);
    }

    public final void h() {
        BLog.i(F, "observeAppLifeCycle");
        Observable<Boolean> observableObserveOn = LifecycleManager.INSTANCE.getAppStateSubject().observeOn(AndroidSchedulers.mainThread());
        final Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$observeAppLifeCycle$1
            {
                super(1);
            }

            public static Intent INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent(Activity activity) {
                Context context;
                Intent intent = activity.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) throws JSONException {
                SplashAdModel value;
                Intent intentINVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent;
                Intent intentINVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent2;
                Boolean bool2 = bool;
                BLog.i(CCSplashAdManagerWrapper.F, "App LifecycleCallback: isForeground " + bool2);
                Intrinsics.checkNotNull(bool2);
                if (bool2.booleanValue()) {
                    this.e.f75220g = System.currentTimeMillis();
                    if (this.e.t) {
                        SplashAdReportUtils splashAdReportUtils = SplashAdReportUtils.f75379a;
                        Activity activityF = AppActivityRecorder.f79544a.f();
                        if (activityF != null && (intentINVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent(activityF)) != null) {
                            simpleName = intentINVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("deeplink");
                        }
                        splashAdReportUtils.getClass();
                        SplashAdReportUtils.h("0", simpleName);
                    } else {
                        AppActivityRecorder appActivityRecorder = AppActivityRecorder.f79544a;
                        Activity activityF2 = appActivityRecorder.f();
                        final String stringExtra = (activityF2 == null || (intentINVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 = INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent(activityF2)) == null) ? null : intentINVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent2.getStringExtra("deeplink");
                        SplashAdReportUtils.f75379a.getClass();
                        String string = UUID.randomUUID().toString();
                        Intrinsics.checkNotNullExpressionValue(string, "");
                        SplashAdReportUtils.l = string;
                        Activity activityF3 = appActivityRecorder.f();
                        SplashAdReportUtils.h = SplashAdReportUtils.a(activityF3 != null ? INVOKEVIRTUAL_com_vega_business_ad_base_CCSplashAdManagerWrapper$observeAppLifeCycle$1_com_vega_launcher_lancet_BadParcelableLancet_getInttent(activityF3) : null);
                        SplashAdReportUtils.h(ProfileManager.VERSION, stringExtra);
                        CCSplashAdManagerWrapper cCSplashAdManagerWrapper = this.e;
                        SplashScene splashScene = SplashScene.f66086c;
                        if (CCSplashAdManagerWrapper.f(cCSplashAdManagerWrapper, splashScene, true, null, true, true, 4)) {
                            final Activity activity = LifecycleManager.INSTANCE.getTopmostActivity().get();
                            if (activity != null) {
                                PopCenter popCenter = PopCenter.f79402a;
                                popCenter.getClass();
                                PopCenter.n(popCenter, "app_open_ad_hot", null, null, new Function0<Unit>() { // from class: com.vega.business.ad.base.CCSplashAdManagerWrapper$observeAppLifeCycle$1$1$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(activity, "//ad/hot_start_splash");
                                        smartRouteBuildRoute.withParam("deeplink", stringExtra);
                                        SplashAdReportUtils.f75379a.getClass();
                                        smartRouteBuildRoute.withParam("exit_gap", System.currentTimeMillis() - SplashAdReportUtils.f.g("app_exit_time", 0L));
                                        smartRouteBuildRoute.open();
                                        return Unit.INSTANCE;
                                    }
                                }, 6);
                            }
                        } else {
                            SPIService sPIService = SPIService.INSTANCE;
                            String strO = ((CapCutAdSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null)).E().o(splashScene);
                            SplashAdReportUtils.e(new SplashAdActionReportData(AdSceneTag.f66067d, AdAction.f66211d, strO, this.e.c(), null, null, null, null, System.currentTimeMillis() - this.e.f75220g, null, null, ((IAdService) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IAdService.class), null)).e(splashScene, false, null), null, null, null, null, null, null, null, null, null, ((CapCutAdSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null)).E().n(splashScene), this.e.c(), strO, null, 75488226));
                        }
                    }
                    CCSplashAdManagerWrapper cCSplashAdManagerWrapper2 = this.e;
                    cCSplashAdManagerWrapper2.t = false;
                    cCSplashAdManagerWrapper2.u = false;
                    SplashAdReportUtils.f75379a.getClass();
                    SplashAdReportUtils.f.o(System.currentTimeMillis(), "app_launch_time", false);
                } else {
                    ((IAdLaunchService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAdLaunchService.class), null)).g();
                    SplashAdReportUtils splashAdReportUtils2 = SplashAdReportUtils.f75379a;
                    AppActivityRecorder appActivityRecorder2 = AppActivityRecorder.f79544a;
                    Activity activityF4 = appActivityRecorder2.f();
                    simpleName = activityF4 != null ? activityF4.getClass().getSimpleName() : null;
                    splashAdReportUtils2.getClass();
                    SplashAdReportUtils.f.o(System.currentTimeMillis(), "app_exit_time", false);
                    SplashAdReportUtils.f75382g = simpleName;
                    if (!SplashAdReportUtils.j && !appActivityRecorder2.c("com.vega.main.MainActivity")) {
                        SplashAdReportUtils.j = true;
                        SplashAdReportUtils.f(AdSceneTag.f66066c, AdFreeUserLabel.b, AdFreeUserReason.b, SplashAdReportUtils.e);
                    }
                    this.e.f = System.currentTimeMillis();
                    if (this.e.q instanceof ColdStartSplashAdComponent) {
                        this.e.u = true;
                    }
                    SplashPreloadConfig splashPreloadConfig = new SplashPreloadConfig(PreloadScenes.b, SplashRequestFrom.f66043d);
                    CCSplashAdManagerWrapper cCSplashAdManagerWrapper3 = this.e;
                    if (cCSplashAdManagerWrapper3.i.get() && (((value = cCSplashAdManagerWrapper3.f75217a.getValue()) == null || !value.b(true)) && CCSplashAdManagerWrapper.f(cCSplashAdManagerWrapper3, SplashScene.f66086c, false, splashPreloadConfig, false, false, 24))) {
                        CCSplashAdManagerWrapper cCSplashAdManagerWrapper4 = this.e;
                        cCSplashAdManagerWrapper4.g(cCSplashAdManagerWrapper4.c(), SplashScene.f66086c, splashPreloadConfig);
                    }
                }
                return Unit.INSTANCE;
            }
        };
        observableObserveOn.subscribe(new Consumer() { // from class: X.4gL
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                function1.invoke(obj);
            }
        });
    }

    /* JADX DEBUG: Multi-variable search result rejected for r42v0, resolved type: com.vega.adapi.data.splash.SplashAdDataProxy */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:112:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0353  */
    /* JADX WARN: Type inference failed for: r0v48, types: [com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedHandler$1] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(com.vega.adapi.data.splash.SplashAdDataProxy r42, boolean r43) throws org.json.JSONException {
        /*
            r41 = this;
            java.lang.String r6 = ""
            r13 = r42
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r6)
            java.lang.String r4 = com.vega.business.ad.base.CCSplashAdManagerWrapper.F
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "onAdLoadedHandler splashAd: "
            r1.<init>(r0)
            r1.append(r13)
            java.lang.String r0 = ", fromFetchSyncSplashAd: "
            r1.append(r0)
            r14 = r43
            r1.append(r14)
            java.lang.String r0 = ", thread: "
            r1.append(r0)
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            java.lang.String r0 = r0.getName()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.w(r4, r0)
            long r27 = android.os.SystemClock.uptimeMillis()
            r5 = r41
            com.vega.adapi.constant.SplashScene r0 = r5.n
            com.vega.adapi.constant.SplashScene r8 = com.vega.adapi.constant.SplashScene.b
            r10 = 1
            r33 = 0
            if (r0 != r8) goto L3c9
            com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
            r0.getClass()
            boolean r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.x
            if (r0 == 0) goto L3c5
            java.lang.String r7 = "success_overtime"
        L4e:
            com.vega.ad.report.AdLaunchMonitor r1 = com.vega.ad.report.AdLaunchMonitor.f65929a
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L3c1
            java.lang.String r3 = r0.b()
        L5a:
            r1.getClass()
            long r11 = com.vega.ad.report.AdLaunchMonitor.U
            r1 = -1
            int r0 = (r11 > r1 ? 1 : (r11 == r1 ? 0 : -1))
            if (r0 != 0) goto L73
            long r0 = com.vega.ad.report.AdLaunchMonitor.d(r27)
            com.vega.ad.report.AdLaunchMonitor.U = r0
            com.vega.ad.report.AdLaunchMonitor.f65928X = r7
            if (r3 == 0) goto L71
            com.vega.ad.report.AdLaunchMonitor.Z = r3
        L71:
            com.vega.ad.report.AdLaunchMonitor.Y = r33
        L73:
            boolean r0 = r5.w
            if (r0 == 0) goto L3e4
            long r0 = r5.x
            long r2 = r27 - r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "onAdLoadedHandler, COLD_START, cost time after SpeedBidSplashAd="
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r4, r0)
        L8c:
            r9 = 1
        L8d:
            com.vega.adapi.constant.SplashScene r12 = r5.n
            com.vega.adapi.splash.ISplashAdLoader r0 = r5.m
            r7 = 1000(0x3e8, float:1.401E-42)
            java.lang.String r40 = "contract"
            java.lang.String r17 = "bidding"
            if (r0 == 0) goto L3ba
            org.json.JSONObject r4 = r0.h(r13, r12)
            if (r4 == 0) goto L3ba
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L3b6
            boolean r0 = r0.c()
            java.lang.Boolean r1 = java.lang.Boolean.valueOf(r0)
        Lad:
            java.lang.String r0 = "load_is_brand"
            r4.put(r0, r1)
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L3b3
            java.lang.String r1 = r0.b()
        Lbc:
            java.lang.String r0 = "load_adn_name"
            r4.put(r0, r1)
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L3b0
            java.lang.String r1 = r0.b()
        Lcb:
            java.lang.String r0 = "ad_platform"
            r4.put(r0, r1)
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L3ac
            boolean r0 = r0.c()
            if (r0 != r10) goto L3ac
            r1 = r40
        Lde:
            java.lang.String r0 = "ad_type"
            r4.put(r0, r1)
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L105
            com.bd.adhubsdk.api.BDAHAdEcpmInfo r0 = r0.f65879a
            java.lang.String r0 = r0.e
            if (r0 == 0) goto L105
            java.lang.Double r0 = kotlin.text.StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(r0)
            if (r0 == 0) goto L105
            double r15 = r0.doubleValue()
            double r0 = (double) r7
            double r15 = r15 / r0
            r0 = 1000000(0xf4240, float:1.401298E-39)
            double r0 = (double) r0
            double r0 = r0 * r15
            java.lang.String r11 = "ad_price_million"
            r4.put(r11, r0)
        L105:
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L114
            java.lang.String r1 = r0.a()
            java.lang.String r0 = "rit_id"
            r4.put(r0, r1)
        L114:
            int r0 = r12.ordinal()
            java.lang.String r11 = "request_id"
            java.lang.String r1 = "time_out_runnable"
            if (r0 == 0) goto L39b
            if (r0 == r10) goto L38a
        L120:
            java.lang.String r0 = "speed_bid"
            r4.put(r0, r9)
            java.lang.String r0 = "speed_bid_cost_time"
            r4.put(r0, r2)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r14)
            int r1 = com.vega.core.ext.ExtentionKt.getReportInt(r0)
            java.lang.String r0 = "is_fetch_sync_ad"
            r4.put(r0, r1)
            com.vega.adapi.constant.SplashScene r0 = r5.n
            if (r0 != r8) goto L17c
            java.lang.String r1 = "is_splashadn_overtime"
            int r0 = r5.C
            r4.put(r1, r0)
            com.vega.business.ad.impl.splash.SplashSpManager r0 = com.vega.business.ad.impl.splash.SplashSpManager.f75304a
            r0.getClass()
            com.vega.kv.KvStorage r0 = com.vega.business.ad.impl.splash.SplashSpManager.a()
            java.lang.String r11 = "cold_request_fail_streak"
            r9 = 0
            int r0 = r0.f(r11, r9)
            r4.put(r11, r0)
            java.lang.String r1 = "SplashSpManager"
            java.lang.String r0 = "recordColdStartRequestSuccess"
            com.vega.log.BLog.i(r1, r0)
            com.vega.kv.KvStorage r3 = com.vega.business.ad.impl.splash.SplashSpManager.a()
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.String r2 = "last_cold_request_success_time"
            r3.o(r0, r2, r9)
            com.vega.kv.KvStorage r0 = com.vega.business.ad.impl.splash.SplashSpManager.a()
            r0.n(r9, r11, r9)
            com.vega.ad.report.AdLaunchMonitor r0 = com.vega.ad.report.AdLaunchMonitor.f65929a
            r0.getClass()
            org.json.JSONObject r0 = com.vega.ad.report.AdLaunchMonitor.e(r27)
            com.vega.core.ext.JSONObjectExKt.g(r4, r0, r10)
        L17c:
            com.vega.business.ad.model.SplashAdModel r2 = new com.vega.business.ad.model.SplashAdModel
            com.vega.adapi.constant.SplashScene r1 = r5.n
            com.vega.adapi.config.splash.SplashPreloadConfig r0 = r5.o
            r18 = r2
            r19 = r27
            r21 = r1
            r22 = r13
            r23 = r0
            r18.<init>(r19, r21, r22, r23)
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L386
            com.bd.adhubsdk.api.BDAHAdEcpmInfo r0 = r0.f65879a
            java.lang.String r0 = r0.e
            if (r0 == 0) goto L386
            java.lang.Double r0 = kotlin.text.StringsKt__StringNumberConversionsJVMKt.toDoubleOrNull(r0)
            if (r0 == 0) goto L386
            double r11 = r0.doubleValue()
            double r0 = (double) r7
            double r11 = r11 / r0
            java.lang.Double r0 = java.lang.Double.valueOf(r11)
        L1ab:
            r2.i = r0
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L382
            java.lang.String r0 = r0.b()
        L1b7:
            r2.f = r0
            java.lang.String r0 = r5.A
            r2.h = r0
            r2.n = r4
            org.json.JSONObject r3 = new org.json.JSONObject
            r3.<init>()
            java.lang.String r1 = "origin_request_uuid"
            java.lang.String r0 = r5.A
            r3.put(r1, r0)
            java.lang.String r1 = "origin_launch_id"
            java.lang.String r0 = r5.B
            r3.put(r1, r0)
            com.vega.adapi.config.splash.SplashPreloadConfig r0 = r5.o
            com.vega.adapi.config.splash.SplashRequestFrom r0 = r0.b
            java.lang.String r1 = r0.f66044a
            java.lang.String r0 = "origin_request_from"
            r3.put(r0, r1)
            java.lang.String r0 = r3.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r6)
            r2.o = r0
            java.lang.String r0 = r5.B
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r6)
            r2.p = r0
            r13.f()
            com.vega.adapi.constant.AdSDK r3 = com.vega.adapi.constant.AdSDK.e
            cn.capcut.adloader.core.report.AdPlatform r0 = com.vega.adapi.constant.AdSDKKt.a(r3)
            java.lang.String r1 = r0.b()
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L37e
            java.lang.String r0 = r0.d()
        L204:
            java.lang.String r0 = cn.capcut.adloader.util.IAdItemExtKt.d(r1, r0)
            com.vega.adapi.constant.AdSDK r0 = com.vega.adapi.AdDataExKt.g(r0)
            r2.q = r0
            r13.f()
            boolean r1 = com.vega.adapi.AdDataExKt.e(r3)
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L37a
            java.lang.String r0 = r0.d()
        L21f:
            java.lang.String r0 = cn.capcut.adloader.util.IAdItemExtKt.b(r0, r1)
            r2.r = r0
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L376
            java.lang.String r0 = r0.e()
        L22f:
            r2.s = r0
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L372
            java.lang.String r0 = r0.f()
        L23b:
            r2.t = r0
            com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedHandler$1 r0 = new com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedHandler$1
            r0.<init>()
            r13.h(r0)
            long r0 = android.os.SystemClock.uptimeMillis()
            com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedHandler$2 r3 = new com.vega.business.ad.base.CCSplashAdManagerWrapper$onAdLoadedHandler$2
            r3.<init>()
            com.vega.ad.util.ThreadUtilKtKt.a(r3)
            com.vega.adapi.config.splash.SplashPreloadConfig r0 = r5.o
            boolean r3 = r0.a()
            com.vega.adapi.constant.SplashScene r0 = r5.n
            if (r0 != r8) goto L368
            long r0 = r5.f75218c
            long r25 = r27 - r0
        L25f:
            long r0 = r5.f75219d
            long r27 = r27 - r0
            com.vega.adapi.constant.SplashScene r0 = r5.n
            int r0 = r0.ordinal()
            if (r0 == 0) goto L357
            if (r0 == r10) goto L344
            com.vega.adapi.constant.AdStage r20 = com.vega.adapi.constant.AdStage.f66071d
        L26f:
            r20.toString()
            com.vega.adapi.constant.SplashScene r0 = r5.n
            java.util.Objects.toString(r0)
            com.vega.adapi.constant.SplashScene r10 = r5.n
            com.vega.adapi.config.splash.SplashPreloadConfig r0 = r5.o
            boolean r0 = r0.a()
            if (r0 == 0) goto L340
            com.vega.adapi.data.AdRequestTarget r21 = com.vega.adapi.data.AdRequestTarget.f66119c
        L283:
            com.vega.adapi.config.splash.SplashPreloadConfig r0 = r5.o
            com.vega.adapi.config.splash.SplashRequestFrom r0 = r0.b
            java.lang.String r9 = r0.f66044a
            java.lang.String r8 = r5.A
            java.lang.String r7 = r5.B
            java.lang.String r3 = r2.s
            if (r3 != 0) goto L2a4
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r0.getClass()
            com.vega.adapi.config.CapCutAdSettings r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r1 = r0.E()
            com.vega.adapi.constant.SplashScene r0 = r5.n
            java.lang.String r3 = r1.o(r0)
        L2a4:
            com.vega.adapi.constant.AdSDK r1 = r2.q
            if (r1 != 0) goto L2ac
            com.vega.adapi.constant.AdSDK r1 = r5.c()
        L2ac:
            java.lang.String r0 = r2.r
            com.vega.adapi.data.splash.SplashAdDataProxy r6 = r2.f75373c
            com.vega.adapi.constant.AdFormat r32 = r6.a()
            r36 = 24576(0x6000, float:3.4438E-41)
            r22 = r9
            r23 = r8
            r24 = r7
            r29 = r1
            r30 = r3
            r31 = r0
            r34 = r33
            r35 = r4
            r18 = r5
            r19 = r10
            m(r18, r19, r20, r21, r22, r23, r24, r25, r27, r29, r30, r31, r32, r33, r34, r35, r36)
            com.vega.adapi.constant.AdStage r0 = com.vega.adapi.constant.AdStage.f66071d
            r5.k = r0
            java.util.concurrent.atomic.AtomicBoolean r1 = r5.j
            r0 = 0
            r1.set(r0)
            com.vega.ad.npth.AdNpthManager r7 = com.vega.ad.npth.AdNpthManager.f65907a
            com.vega.adapi.constant.SplashScene r0 = r5.n
            com.vega.adapi.constant.AdSceneTag r0 = com.vega.adapi.constant.SplashSceneKt.a(r0)
            java.lang.String r6 = r0.f66069a
            java.lang.String r4 = r2.r
            com.vega.adapi.constant.AdSDK r0 = r2.q
            if (r0 != 0) goto L2eb
            com.vega.adapi.constant.AdSDK r0 = r5.c()
        L2eb:
            java.lang.String r3 = r0.f66065a
            java.lang.String r2 = r2.s
            if (r2 != 0) goto L304
            com.vega.business.ad.base.CCSplashAdManagerWrapper$Companion r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.E
            r0.getClass()
            com.vega.adapi.config.CapCutAdSettings r0 = com.vega.business.ad.base.CCSplashAdManagerWrapper.Companion.a()
            com.vega.adapi.config.SplashAdConfigData r1 = r0.E()
            com.vega.adapi.constant.SplashScene r0 = r5.n
            java.lang.String r2 = r1.o(r0)
        L304:
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L33d
            java.lang.String r38 = r0.a()
        L30e:
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L318
            java.lang.String r33 = r0.b()
        L318:
            com.vega.ad.loader.splash.HubAdEcpmInfo r0 = r13.e()
            if (r0 == 0) goto L33b
            boolean r1 = r0.c()
            r0 = 1
            if (r1 != r0) goto L33b
        L325:
            if (r0 == 0) goto L338
        L327:
            r7.getClass()
            r34 = r6
            r35 = r4
            r36 = r3
            r37 = r2
            r39 = r33
            com.vega.ad.npth.AdNpthManager.f(r34, r35, r36, r37, r38, r39, r40)
            return
        L338:
            r40 = r17
            goto L327
        L33b:
            r0 = 0
            goto L325
        L33d:
            r38 = r33
            goto L30e
        L340:
            com.vega.adapi.data.AdRequestTarget r21 = com.vega.adapi.data.AdRequestTarget.b
            goto L283
        L344:
            if (r3 != 0) goto L353
            com.vega.business.ad.view.HotStartSplashAdActivity$Companion r0 = com.vega.business.ad.view.HotStartSplashAdActivity.b0
            r0.getClass()
            boolean r0 = com.vega.business.ad.view.HotStartSplashAdActivity.f0
            if (r0 == 0) goto L353
            com.vega.adapi.constant.AdStage r20 = com.vega.adapi.constant.AdStage.f
            goto L26f
        L353:
            com.vega.adapi.constant.AdStage r20 = com.vega.adapi.constant.AdStage.f66071d
            goto L26f
        L357:
            com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
            r0.getClass()
            boolean r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.x
            if (r0 == 0) goto L364
            com.vega.adapi.constant.AdStage r20 = com.vega.adapi.constant.AdStage.f
            goto L26f
        L364:
            com.vega.adapi.constant.AdStage r20 = com.vega.adapi.constant.AdStage.f66071d
            goto L26f
        L368:
            long r25 = java.lang.System.currentTimeMillis()
            long r0 = r5.h
            long r25 = r25 - r0
            goto L25f
        L372:
            r0 = r33
            goto L23b
        L376:
            r0 = r33
            goto L22f
        L37a:
            r0 = r33
            goto L21f
        L37e:
            r0 = r33
            goto L204
        L382:
            r0 = r33
            goto L1b7
        L386:
            r0 = r33
            goto L1ab
        L38a:
            com.vega.business.ad.view.HotStartSplashAdActivity$Companion r0 = com.vega.business.ad.view.HotStartSplashAdActivity.b0
            r0.getClass()
            boolean r0 = com.vega.business.ad.view.HotStartSplashAdActivity.e0
            r4.put(r1, r0)
            java.lang.String r0 = com.vega.business.ad.view.HotStartSplashAdActivity.d0
            r4.put(r11, r0)
            goto L120
        L39b:
            com.vega.business.ad.view.ColdStartSplashAdComponent$Companion r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.u
            r0.getClass()
            boolean r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.w
            r4.put(r1, r0)
            java.lang.String r0 = com.vega.business.ad.view.ColdStartSplashAdComponent.y
            r4.put(r11, r0)
            goto L120
        L3ac:
            r1 = r17
            goto Lde
        L3b0:
            r1 = r6
            goto Lcb
        L3b3:
            r1 = r6
            goto Lbc
        L3b6:
            r1 = r33
            goto Lad
        L3ba:
            org.json.JSONObject r4 = new org.json.JSONObject
            r4.<init>()
            goto L120
        L3c1:
            r3 = r33
            goto L5a
        L3c5:
            java.lang.String r7 = "success"
            goto L4e
        L3c9:
            boolean r0 = r5.y
            if (r0 == 0) goto L3e4
            long r0 = r5.z
            long r2 = r27 - r0
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "onAdLoadedHandler, HOT_START, cost time after SpeedBidSplashAd="
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r4, r0)
            goto L8c
        L3e4:
            r2 = 0
            r9 = 0
            goto L8d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.business.ad.base.CCSplashAdManagerWrapper.i(com.vega.adapi.data.splash.SplashAdDataProxy, boolean):void");
    }

    public final boolean j(ISplashAdView iSplashAdView, LifecycleOwner lifecycleOwner, Observer<SplashAdModel> observer, AdLoadListener adLoadListener, AdShowListener adShowListener) {
        Intrinsics.checkNotNullParameter(iSplashAdView, "");
        Intrinsics.checkNotNullParameter(lifecycleOwner, "");
        Intrinsics.checkNotNullParameter(observer, "");
        if (this.q != null) {
            BLog.e(F, "registerSplashAdView: splashAdView already registered");
            return false;
        }
        BLog.w(F, "registerSplashAdView , splashAdView=" + iSplashAdView);
        this.q = iSplashAdView;
        this.r = adLoadListener;
        this.s = adShowListener;
        this.f75217a.observe(lifecycleOwner, observer);
        return true;
    }

    public final void l(SplashScene splashScene, AdStage adStage, AdRequestType adRequestType, AdRequestTarget adRequestTarget, String str, String str2, String str3, long j, long j2, AdSDK adSDK, String str4, String str5, AdFormat adFormat, String str6, String str7, JSONObject jSONObject) {
        Intrinsics.checkNotNullParameter(splashScene, "");
        Intrinsics.checkNotNullParameter(adStage, "");
        Intrinsics.checkNotNullParameter(adRequestType, "");
        Intrinsics.checkNotNullParameter(adRequestTarget, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(adSDK, "");
        Intrinsics.checkNotNullParameter(str4, "");
        E.getClass();
        SplashAdRequestReportData splashAdRequestReportData = new SplashAdRequestReportData(SplashSceneKt.a(splashScene), str, adStage, str4, adSDK, j2, str6, str7, str2, j, jSONObject, adRequestType.f66124a, adRequestTarget.f66121a, str3, adFormat, c(), Companion.a().E().o(splashScene), str5, Companion.a().E().n(splashScene));
        SplashAdReportUtils.f75379a.getClass();
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("tag", splashAdRequestReportData.k().f66069a);
        jSONObject2.put("request_from", splashAdRequestReportData.h());
        jSONObject2.put("stage", splashAdRequestReportData.j().f66073a);
        jSONObject2.put("unit_id", splashAdRequestReportData.m());
        jSONObject2.put("mediation_source", splashAdRequestReportData.g().f66065a);
        jSONObject2.put("main_unit_id", splashAdRequestReportData.h);
        jSONObject2.put("main_mediation_source", splashAdRequestReportData.f66158g.f66065a);
        String str8 = splashAdRequestReportData.i;
        if (str8 != null) {
            jSONObject2.put("sdk_type", str8);
        }
        jSONObject2.put("ad_id", splashAdRequestReportData.a());
        jSONObject2.put("type", splashAdRequestReportData.f66156c);
        jSONObject2.put("request_target", splashAdRequestReportData.f66157d);
        try {
            Result.m17090constructorimpl(jSONObject2.put("process_name", ToolUtils.getCurProcessName(ModuleCommon.INSTANCE.getApplication())));
        } catch (Throwable th) {
            Result.m17090constructorimpl(ResultKt.createFailure(th));
        }
        SPIService sPIService = SPIService.INSTANCE;
        Boolean boolT = ((CapCutAdSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null)).T(splashAdRequestReportData.g(), splashAdRequestReportData.k());
        if (boolT != null) {
            jSONObject2.put("unit_id_same_as_init", boolT.booleanValue());
        }
        jSONObject2.put("is_personal", ((CapCutAdSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null)).S() ? ExtentionKt.getReportStr(Boolean.FALSE) : ExtentionKt.getReportStr(Boolean.valueOf(!((IAdSdkApi) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IAdSdkApi.class), null)).k())));
        jSONObject2.put("is_vip", ExtentionKt.getReportStr(Boolean.valueOf(((EditorProxyFlavorModule) sPIService.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyFlavorModule.class), null)).a().isVip())));
        long j3 = splashAdRequestReportData.f66155a;
        if (j3 > 0) {
            jSONObject2.put("time_to_start", j3);
        }
        if (splashAdRequestReportData.c() > 0) {
            jSONObject2.put("duration", splashAdRequestReportData.c());
        }
        if (ExtentionKt.isNotNullOrEmpty(splashAdRequestReportData.d())) {
            jSONObject2.put("error_code", splashAdRequestReportData.d());
        }
        if (ExtentionKt.isNotNullOrEmpty(splashAdRequestReportData.e())) {
            jSONObject2.put("error_message", splashAdRequestReportData.e());
        }
        String strI = splashAdRequestReportData.i();
        if (strI != null) {
            jSONObject2.put("request_uuid", strI);
        }
        jSONObject2.put("launch_id", splashAdRequestReportData.e);
        SplashAdReportUtils.f75379a.getClass();
        jSONObject2.put("process_id", SplashAdReportUtils.b());
        AdFormat adFormat2 = splashAdRequestReportData.f;
        if (adFormat2 != null) {
            jSONObject2.put("ad_format", adFormat2.f66050a);
        }
        jSONObject2.put("exit_page", SplashAdReportUtils.f75382g);
        try {
            AdConfigReportUtils.f65924a.getClass();
            JSONObjectExKt.f(AdConfigReportUtils.b(), jSONObject2);
        } catch (Throwable unused) {
        }
        if (Intrinsics.areEqual(splashAdRequestReportData.h(), "hot") || Intrinsics.areEqual(splashAdRequestReportData.h(), "cold")) {
            SplashAdReportUtils splashAdReportUtils = SplashAdReportUtils.f75379a;
            AdSceneTag adSceneTagK = splashAdRequestReportData.k();
            splashAdReportUtils.getClass();
            jSONObject2.put("trigger_from", SplashAdReportUtils.c(adSceneTagK).toString());
        }
        try {
            JSONObject jSONObject3 = splashAdRequestReportData.b;
            if (jSONObject3 != null) {
                JSONObjectExKt.g(jSONObject2, jSONObject3, true);
            }
            Result.m17090constructorimpl(jSONObject2.put("config_settings", ExtentionKt.toJson(((CapCutAdSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(CapCutAdSettings.class), null)).E())));
        } catch (Throwable th2) {
            Result.m17090constructorimpl(ResultKt.createFailure(th2));
        }
        jSONObject2.put("abtest_id", splashAdRequestReportData.j);
        if (splashAdRequestReportData.k() == AdSceneTag.f66066c) {
            jSONObject2.put("splash_trigger_count", ((IAdLaunchService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAdLaunchService.class), null)).k());
        }
        ReportManagerWrapper.INSTANCE.onEvent("request_ad", jSONObject2);
        jSONObject2.toString();
    }

    public final void p(ISplashAdView iSplashAdView, Observer<SplashAdModel> observer, boolean z) {
        Intrinsics.checkNotNullParameter(iSplashAdView, "");
        Intrinsics.checkNotNullParameter(observer, "");
        if (this.q != iSplashAdView) {
            BLog.w(F, "unregisterSplashAdView , but splashAdView not equals , ccSplashAdView=" + this.q + " , splashAdView=" + iSplashAdView);
            return;
        }
        BLog.w(F, "unregisterSplashAdView , splashAdView=" + iSplashAdView);
        if (!z) {
            this.q = null;
        }
        this.r = null;
        this.f75217a.removeObserver(observer);
    }
}
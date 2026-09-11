package com.vega.nativesettings;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Process;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.bytedance.bpea.transmit.delegate.BPEAHandler;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.bytedance.news.common.settings.api.exposed.ExposedManager;
import com.bytedance.news.common.settings.internal.GlobalConfig;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.bytedance.services.apm.api.EnsureManager;
import com.lemon.lv.config.ClientSetting;
import com.lemon.lvoverseas.R;
import com.vega.config.AutomaticTestConfig;
import com.vega.config.CC4BTestConfig;
import com.vega.config.RetouchPictureTestConfig;
import com.vega.core.api.LoginService;
import com.vega.core.app.AppContext;
import com.vega.core.context.AppProperty;
import com.vega.core.context.ContextExtHelper;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.LocationProperty;
import com.vega.core.context.LocationPropertyKt;
import com.vega.core.context.SPIService;
import com.vega.core.context.debug.DevelopSetting;
import com.vega.core.deeplink.DeeplinkIntentLancetImpl;
import com.vega.core.utils.ApkUtil;
import com.vega.core.utils.PatchHelper;
import com.vega.draft.monitor.DraftMonitorLancet;
import com.vega.draft.monitor.MonitorExtKt;
import com.vega.feelgoodapi.settings.DeveloperSettingManager;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.infrastructure.base.ModuleCommonKt;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.kv.KvStorage;
import com.vega.kv.keva.KevaSpAopHook;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.libfiles.files.hook.StartMainActivityHook;
import com.vega.log.BLog;
import com.vega.log.ExceptionPrinter;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.upload.UploadConfig;
import com.vega.upload.UploadConfigProvider;
import com.vega.util.ToastUtilKt;
import com.vega.ve.api.VESettings;
import com.xt.retouch.applauncher.module.ContentProviderHook;
import com.xt.retouch.lib.log.XTLog;
import java.io.File;
import java.util.List;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes24.dex */
public class BaseUIWidget extends MenuGroup {
    public final SharedPreferences e;

    /* loaded from: classes21.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseUIWidget(Context context, AttributeSet attributeSet) {
        BaseNewDeveloperActivity baseNewDeveloperActivity;
        AppContext appContext;
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "");
        this.e = INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(ModuleCommon.INSTANCE.getApplication(), "log_level_config", 0);
        e();
        a(ModuleCommonKt.b(R.string.mlb), new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$1
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MenuGroup menuGroup) {
                final MenuGroup menuGroup2 = menuGroup;
                Intrinsics.checkNotNullParameter(menuGroup2, "");
                String strB = ModuleCommonKt.b(R.string.mlc);
                final BaseUIWidget baseUIWidget = this.e;
                menuGroup2.c(strB, new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$1.1
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) {
                        Intrinsics.checkNotNullParameter(view, "");
                        baseUIWidget.f("gettdid");
                        return Unit.INSTANCE;
                    }
                });
                String strB2 = ModuleCommonKt.b(R.string.rwt);
                final BaseUIWidget baseUIWidget2 = this.e;
                menuGroup2.c(strB2, new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$1.2
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) {
                        Intrinsics.checkNotNullParameter(view, "");
                        baseUIWidget2.f("version");
                        return Unit.INSTANCE;
                    }
                });
                String strB3 = ModuleCommonKt.b(R.string.mld);
                final BaseUIWidget baseUIWidget3 = this.e;
                menuGroup2.c(strB3, new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$1.3
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) {
                        Intrinsics.checkNotNullParameter(view, "");
                        baseUIWidget3.f("getfingerprint");
                        return Unit.INSTANCE;
                    }
                });
                menuGroup2.c(ModuleCommonKt.b(R.string.rxg), new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$1.4
                    {
                        super(1);
                    }

                    public static void INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget$baseMenuItems$1$4_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(Context context2, Intent intent) {
                        DeeplinkIntentLancetImpl.a(intent);
                        INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget$baseMenuItems$1$4_com_vega_libfiles_files_hook_StartMainActivityHook_startActivity(context2, intent);
                    }

                    public static void INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget$baseMenuItems$1$4_com_vega_libfiles_files_hook_StartMainActivityHook_startActivity(Context context2, Intent intent) {
                        StartMainActivityHook.fixLauncherIntent(intent);
                        HeliosApiHook heliosApiHook = new HeliosApiHook();
                        Object[] objArr = {intent};
                        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/content/Intent;)V", "dzBzEhQ/WMuSW1Y1TASBdVDwZ8SuBIidxCpqnMXoBMtvsD67");
                        if (heliosApiHook.preInvoke(11090, "android/content/Context", "startActivity", context2, objArr, "void", extraInfo).isIntercept()) {
                            heliosApiHook.postInvoke(null, 11090, "android/content/Context", "startActivity", context2, objArr, extraInfo, false);
                        } else {
                            context2.startActivity(intent);
                            heliosApiHook.postInvoke(null, 11090, "android/content/Context", "startActivity", context2, objArr, extraInfo, true);
                        }
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) throws ClassNotFoundException {
                        Intrinsics.checkNotNullParameter(view, "");
                        try {
                            Class<?> cls = Class.forName("com.bytedance.mira.MiraPluginListActivity");
                            Intrinsics.checkNotNullExpressionValue(cls, "");
                            INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget$baseMenuItems$1$4_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(menuGroup2.getContext(), new Intent(menuGroup2.getContext(), cls));
                        } catch (Exception e) {
                            StringBuilder sb = new StringBuilder("Failed to open plugin list, it can only be opened in the domestic version. msg:");
                            ExceptionPrinter.printStackTrace(e);
                            sb.append(Unit.INSTANCE);
                            BLog.e("BaseUIWidget", sb.toString());
                        }
                        return Unit.INSTANCE;
                    }
                });
                final BaseUIWidget baseUIWidget4 = this.e;
                menuGroup2.c("getAbVid", new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$1.5
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) {
                        Intrinsics.checkNotNullParameter(view, "");
                        baseUIWidget4.f("getAbVid");
                        return Unit.INSTANCE;
                    }
                });
                return Unit.INSTANCE;
            }
        });
        b();
        a(ModuleCommonKt.b(R.string.mks), new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final kotlin.Unit invoke(com.vega.nativesettings.MenuGroup r5) {
                /*
                    r4 = this;
                    com.vega.nativesettings.MenuGroup r5 = (com.vega.nativesettings.MenuGroup) r5
                    java.lang.String r0 = ""
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                    r0 = 2131898298(0x7f122fba, float:1.943151E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$1 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$1
                    com.vega.nativesettings.BaseUIWidget r0 = r4.e
                    r1.<init>()
                    r5.c(r2, r1)
                    com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
                    com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
                    boolean r0 = r0.enableDevEntrance()
                    r2 = 0
                    if (r0 != 0) goto L55
                    com.vega.feelgoodapi.settings.DeveloperSettingManager r0 = com.vega.feelgoodapi.settings.DeveloperSettingManager.f102457a
                    r0.getClass()
                    boolean r0 = com.vega.feelgoodapi.settings.DeveloperSettingManager.b
                    if (r0 != 0) goto L55
                    android.content.Context r1 = r5.getContext()
                    boolean r0 = r1 instanceof com.vega.nativesettings.BaseNewDeveloperActivity
                    if (r0 == 0) goto L4a
                    com.vega.nativesettings.BaseNewDeveloperActivity r1 = (com.vega.nativesettings.BaseNewDeveloperActivity) r1
                    if (r1 == 0) goto L4a
                    kotlin.Lazy r0 = r1.v
                    java.lang.Object r0 = r0.getValue()
                    com.vega.core.app.AppContext r0 = (com.vega.core.app.AppContext) r0
                    if (r0 == 0) goto L4a
                    java.lang.String r2 = r0.getChannel()
                L4a:
                    java.lang.String r0 = "local_test"
                    boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
                    if (r0 != 0) goto L55
                L52:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                L55:
                    r0 = 2131898661(0x7f123125, float:1.9432246E38)
                    java.lang.String r1 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$2 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$2
                    r0.<init>()
                    r5.c(r1, r0)
                    r0 = 2131898665(0x7f123129, float:1.9432254E38)
                    java.lang.String r1 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$3 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$3
                    r0.<init>()
                    r5.c(r1, r0)
                    r0 = 2131889734(0x7f120e46, float:1.941414E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$4 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$4
                    com.vega.nativesettings.BaseUIWidget r0 = r4.e
                    r1.<init>()
                    r5.c(r2, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$5 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$5
                    r1.<init>()
                    java.lang.String r0 = "AB/Settings Mock"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$6 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$6
                    r1.<init>()
                    java.lang.String r0 = "Airplane Mode Debug Entry"
                    r5.c(r0, r1)
                    java.lang.String r1 = "Java Crash"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$7 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.7
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$7 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$7
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$7) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.7.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$7
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass7.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass7.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r3) {
                            /*
                                r2 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                java.lang.RuntimeException r1 = new java.lang.RuntimeException
                                java.lang.String r0 = "test"
                                r1.<init>(r0)
                                throw r1
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass7.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    java.lang.String r1 = "Java OOM"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$8 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.8
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$8 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$8
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$8) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.8.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$8
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass8.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass8.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r3) {
                            /*
                                r2 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                r1 = 10000(0x2710, float:1.4013E-41)
                                r0 = 0
                            L8:
                                int r0 = r0 + 1
                                if (r0 >= r1) goto Ld
                                goto L8
                            Ld:
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass8.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$9 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$9
                    r1.<init>()
                    java.lang.String r0 = "Native Crash"
                    r5.c(r0, r1)
                    java.lang.String r1 = "ANR"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$10 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.10
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$10 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$10
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$10) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.10.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$10
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass10.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass10.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r3) throws java.lang.InterruptedException {
                            /*
                                r2 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                r0 = 3000(0xbb8, double:1.482E-320)
                                java.lang.Thread.sleep(r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass10.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$11 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$11
                    r1.<init>()
                    java.lang.String r0 = "AB/Setting Clone"
                    r5.c(r0, r1)
                    com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
                    com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
                    java.lang.String r3 = r0.webDebugToolURL()
                    int r0 = r3.length()
                    if (r0 <= 0) goto Le4
                    com.vega.nativesettings.BaseUIWidget r2 = r4.e
                    r0 = 2131897587(0x7f122cf3, float:1.9430068E38)
                    java.lang.String r1 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$13$1 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$13$1
                    r0.<init>()
                    r5.c(r1, r0)
                Le4:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$14 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$14
                    r1.<init>()
                    java.lang.String r0 = "Open Pumbaa's Ruler Debug Tool"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$15 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$15
                    r1.<init>()
                    java.lang.String r0 = "Open Pumbaa's Monitor Debug Tool"
                    r5.c(r0, r1)
                    com.vega.core.devkit.DevkitManager r0 = com.vega.core.devkit.DevkitManager.f79226a
                    r0.getClass()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$16 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$16
                    r1.<init>(r5)
                    java.lang.String r0 = "Open WSP Debug Tool"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$17 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$17
                    r1.<init>()
                    java.lang.String r0 = "Open UG Cross-Terminal Debugging Tool"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$18 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$18
                    r1.<init>()
                    java.lang.String r0 = "Clear UG Local Cache"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$19 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$19
                    r1.<init>()
                    java.lang.String r0 = "Open Experience Center Saitama"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$20 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$20
                    r1.<init>()
                    java.lang.String r0 = "Open Home Page"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$21 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$21
                    r1.<init>()
                    java.lang.String r0 = "Get GAID"
                    r5.c(r0, r1)
                    com.vega.config.AdTestConfig r0 = com.vega.config.AdTestConfig.f78886a
                    r0.getClass()
                    boolean r2 = com.vega.config.AdTestConfig.f78889g
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$22 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.22
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$22 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$22
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$22) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.22.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$22
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass22.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass22.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r6, java.lang.Boolean r7) {
                            /*
                                r5 = this;
                                java.lang.Boolean r7 = (java.lang.Boolean) r7
                                boolean r4 = r7.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                com.vega.config.AdTestConfig r3 = com.vega.config.AdTestConfig.f78886a
                                r3.getClass()
                                com.vega.config.AdTestConfig.f78889g = r4
                                kotlin.properties.ReadWriteProperty r2 = com.vega.config.AdTestConfig.f78888d
                                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.vega.config.AdTestConfig.b
                                r0 = 0
                                r1 = r1[r0]
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                                r2.setValue(r3, r1, r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass22.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Enable Ad Insertion Prompt"
                    r5.d(r1, r2, r0)
                    boolean r2 = com.vega.config.AdTestConfig.h
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$23 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.23
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$23 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$23
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$23) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.23.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$23
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass23.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass23.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r6, java.lang.Boolean r7) {
                            /*
                                r5 = this;
                                java.lang.Boolean r7 = (java.lang.Boolean) r7
                                boolean r4 = r7.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                com.vega.config.AdTestConfig r3 = com.vega.config.AdTestConfig.f78886a
                                r3.getClass()
                                com.vega.config.AdTestConfig.h = r4
                                kotlin.properties.ReadWriteProperty r2 = com.vega.config.AdTestConfig.e
                                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.vega.config.AdTestConfig.b
                                r0 = 1
                                r1 = r1[r0]
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                                r2.setValue(r3, r1, r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass23.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Force Upload Native Ad Screenshot"
                    r5.d(r1, r2, r0)
                    java.lang.String r1 = "Set Landing Strategy"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$24 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.24
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$24 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$24
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$24) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.24.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$24
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass24.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass24.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.core.context.SPIService r2 = com.vega.core.context.SPIService.INSTANCE
                                java.lang.Class<com.lemon.vega.ug.api.INewUserAcceptanceService> r0 = com.lemon.vega.ug.api.INewUserAcceptanceService.class
                                kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                                r0 = 0
                                java.lang.Object r1 = r2.getImpl(r1, r0)
                                com.lemon.vega.ug.api.INewUserAcceptanceService r1 = (com.lemon.vega.ug.api.INewUserAcceptanceService) r1
                                r0 = 1
                                r1.f(r0, r0)
                                java.lang.String r0 = "Set successfully"
                                r1 = 0
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass24.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    java.lang.String r1 = "Clear Creator Fission Banner Invisible Cache"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$25 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.25
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$25 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$25
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$25) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.25.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$25
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass25.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass25.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.core.context.SPIService r2 = com.vega.core.context.SPIService.INSTANCE
                                java.lang.Class<com.lemon.vega.ug.api.ICreatorIncentiveService> r0 = com.lemon.vega.ug.api.ICreatorIncentiveService.class
                                kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                                r0 = 0
                                java.lang.Object r0 = r2.getImpl(r1, r0)
                                com.lemon.vega.ug.api.ICreatorIncentiveService r0 = (com.lemon.vega.ug.api.ICreatorIncentiveService) r0
                                r0.a()
                                java.lang.String r0 = "Cleared successfully, please restart"
                                r1 = 0
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass25.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    java.lang.String r1 = "Clear Export Page Activity Cache"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$26 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.26
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$26 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$26
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$26) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.26.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$26
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass26.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass26.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.core.context.SPIService r2 = com.vega.core.context.SPIService.INSTANCE
                                java.lang.Class<com.lemon.vega.ug.api.IExportPageActivityService> r0 = com.lemon.vega.ug.api.IExportPageActivityService.class
                                kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                                r0 = 0
                                java.lang.Object r0 = r2.getImpl(r1, r0)
                                com.lemon.vega.ug.api.IExportPageActivityService r0 = (com.lemon.vega.ug.api.IExportPageActivityService) r0
                                r0.clearCache()
                                java.lang.String r0 = "Cleared successfully, please restart"
                                r1 = 0
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass26.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    java.lang.String r1 = "Clear Home Page Red Dot Cache"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$27 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.27
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$27 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$27
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$27) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.27.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$27
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass27.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass27.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.kv.KvStorage r2 = new com.vega.kv.KvStorage
                                com.vega.infrastructure.base.ModuleCommon r0 = com.vega.infrastructure.base.ModuleCommon.INSTANCE
                                android.app.Application r1 = r0.getApplication()
                                java.lang.String r0 = "home_page_red_dot.sp"
                                r2.<init>(r1, r0)
                                r1 = 0
                                r2.b(r1)
                                java.lang.String r0 = "Cleared successfully, please restart"
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass27.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$28 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$28
                    r1.<init>(r5)
                    java.lang.String r0 = "Clear Fresco Image Cache"
                    r5.c(r0, r1)
                    java.lang.String r1 = "Clear Export Page Share Channel Cache"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$29 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.29
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$29 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$29
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$29) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.29.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$29
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass29.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass29.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.core.context.SPIService r2 = com.vega.core.context.SPIService.INSTANCE
                                java.lang.Class<com.lemon.vega.ug.api.IExportAllowUploadService> r0 = com.lemon.vega.ug.api.IExportAllowUploadService.class
                                kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                                r0 = 0
                                java.lang.Object r0 = r2.getImpl(r1, r0)
                                com.lemon.vega.ug.api.IExportAllowUploadService r0 = (com.lemon.vega.ug.api.IExportAllowUploadService) r0
                                r0.b()
                                java.lang.String r0 = "Cleared successfully, please restart"
                                r1 = 0
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass29.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    java.lang.String r1 = "Clear Swipe to View More Guide Frequency Control"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$30 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.30
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$30 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$30
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$30) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.30.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$30
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass30.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass30.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.edit.arealocked.view.NewScrollTipsGuide$Companion r0 = com.vega.edit.arealocked.view.NewScrollTipsGuide.h
                                r0.getClass()
                                com.vega.edit.arealocked.view.NewScrollTipsGuide$Companion$slideMoreSceneTips$1 r1 = com.vega.edit.arealocked.view.NewScrollTipsGuide.j
                                r0 = 1
                                r1.e(r0)
                                java.lang.String r0 = "Cleared successfully"
                                r1 = 0
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass30.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$31 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$31
                    r1.<init>()
                    java.lang.String r0 = "Import Smart Ad JSON"
                    r5.c(r0, r1)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$32 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$32
                    r1.<init>()
                    java.lang.String r0 = "Tone Debug"
                    r5.c(r0, r1)
                    java.lang.String r1 = "Clear Draft Video Algorithm Cache"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.33


                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.33.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass33.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass33.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r2) {
                            /*
                                r1 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33$1 r0 = new kotlin.jvm.functions.Function0<kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget.baseMenuItems.2.33.1
                                    static {
                                        /*
                                            com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33$1 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33$1
                                            r0.<init>()
                                            
                                            // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33$1) com.vega.nativesettings.BaseUIWidget.baseMenuItems.2.33.1.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$33$1
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass33.AnonymousClass1.<clinit>():void");
                                    }

                                    {
                                        /*
                                            r1 = this;
                                            r0 = 0
                                            r1.<init>(r0)
                                            return
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass33.AnonymousClass1.<init>():void");
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final kotlin.Unit invoke() {
                                        /*
                                            r9 = this;
                                            java.lang.String r0 = "Cleared successfully"
                                            r1 = 0
                                            r6 = 0
                                            r8 = 510(0x1fe, float:7.15E-43)
                                            r2 = r1
                                            r3 = r1
                                            r4 = r1
                                            r5 = r1
                                            r7 = r1
                                            com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                            kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                            return r0
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass33.AnonymousClass1.invoke():java.lang.Object");
                                    }
                                }
                                com.vega.draft.util.DraftUtilKt.a(r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass33.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    java.lang.String r1 = "Clear Feature Recommendation Cache"
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$34 r0 = new kotlin.jvm.functions.Function1<android.view.View, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.34
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$34 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$34
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$34) com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.34.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$2$34
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass34.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 1
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass34.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final kotlin.Unit invoke(android.view.View r10) {
                            /*
                                r9 = this;
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.core.context.SPIService r2 = com.vega.core.context.SPIService.INSTANCE
                                java.lang.Class<com.service.FunctionAssistantApiProxy> r0 = com.service.FunctionAssistantApiProxy.class
                                kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
                                r0 = 0
                                java.lang.Object r0 = r2.getImpl(r1, r0)
                                com.service.FunctionAssistantApiProxy r0 = (com.service.FunctionAssistantApiProxy) r0
                                r0.clearCache()
                                java.lang.String r0 = "Cleared successfully"
                                r1 = 0
                                r6 = 0
                                r8 = 510(0x1fe, float:7.15E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.AnonymousClass34.invoke(java.lang.Object):java.lang.Object");
                        }
                    }
                    r5.c(r1, r0)
                    goto L52
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$2.invoke(java.lang.Object):java.lang.Object");
            }
        });
        if (!ContextExtKt.hostEnv().developSettings().enableDevEntrance()) {
            DeveloperSettingManager.f102457a.getClass();
            if (!DeveloperSettingManager.b) {
                Context context2 = getContext();
                if (!Intrinsics.areEqual((!(context2 instanceof BaseNewDeveloperActivity) || (baseNewDeveloperActivity = (BaseNewDeveloperActivity) context2) == null || (appContext = (AppContext) baseNewDeveloperActivity.v.getValue()) == null) ? null : appContext.getChannel(), "local_test")) {
                    return;
                }
            }
        }
        b();
        final DevelopSetting developSettingDevelopSettings = ContextExtKt.hostEnv().developSettings();
        SPIService sPIService = SPIService.INSTANCE;
        final ClientSetting clientSetting = (ClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null);
        final VESettings vESettings = (VESettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(VESettings.class), null);
        a(ModuleCommonKt.b(R.string.q3__res_0x7f123094), new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Duplicate block (B:40:0x02a7) to fix multi-entry loop: BACK_EDGE: B:40:0x02a7 -> B:41:0x02a8 */
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x02a8, code lost:
            
                r2 = false;
             */
            @Override // kotlin.jvm.functions.Function1
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final kotlin.Unit invoke(com.vega.nativesettings.MenuGroup r10) {
                /*
                    r9 = this;
                    com.vega.nativesettings.MenuGroup r10 = (com.vega.nativesettings.MenuGroup) r10
                    java.lang.String r6 = ""
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r6)
                    com.vega.ve.api.VEDebugConfigHelper r0 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                    r0.getClass()
                    java.lang.Boolean r0 = com.vega.ve.api.VEDebugConfigHelper.j
                    r3 = 0
                    if (r0 == 0) goto L282
                    boolean r2 = r0.booleanValue()
                L15:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$1 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.1
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$1 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$1
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$1) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.1.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$1
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass1.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass1.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r4, java.lang.Boolean r5) {
                            /*
                                r3 = this;
                                java.lang.Boolean r5 = (java.lang.Boolean) r5
                                boolean r2 = r5.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                                com.vega.ve.api.VEDebugConfigHelper r1 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                                r1.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.j = r0
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass1.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "first_frame_opt_from_album"
                    r10.d(r1, r2, r0)
                    java.lang.Boolean r0 = com.vega.ve.api.VEDebugConfigHelper.a()
                    if (r0 == 0) goto L27f
                    boolean r2 = r0.booleanValue()
                L26:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$2 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.2
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$2 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$2
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$2) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.2.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$2
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass2.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass2.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r5, java.lang.Boolean r6) {
                            /*
                                r4 = this;
                                java.lang.Boolean r6 = (java.lang.Boolean) r6
                                boolean r1 = r6.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                                com.vega.ve.api.VEDebugConfigHelper r0 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                java.lang.Boolean r3 = java.lang.Boolean.valueOf(r1)
                                r0.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.h = r3
                                if (r3 == 0) goto L2b
                                android.content.SharedPreferences r0 = com.vega.ve.api.VEDebugConfigHelper.b
                                android.content.SharedPreferences$Editor r2 = r0.edit()
                                java.lang.String r1 = "enable_simple_player"
                                boolean r0 = r3.booleanValue()
                                android.content.SharedPreferences$Editor r0 = r2.putBoolean(r1, r0)
                                r0.apply()
                            L2b:
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass2.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "enable_simple_player"
                    r10.d(r1, r2, r0)
                    java.lang.Boolean r0 = com.vega.ve.api.VEDebugConfigHelper.i
                    if (r0 == 0) goto L27c
                    boolean r2 = r0.booleanValue()
                L35:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$3 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.3
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$3 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$3
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$3) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.3.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$3
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass3.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass3.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r4, java.lang.Boolean r5) {
                            /*
                                r3 = this;
                                java.lang.Boolean r5 = (java.lang.Boolean) r5
                                boolean r2 = r5.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                                com.vega.ve.api.VEDebugConfigHelper r1 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                                r1.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.i = r0
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass3.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "enable_pre_render"
                    r10.d(r1, r2, r0)
                    r0 = 2131898862(0x7f1231ee, float:1.9432654E38)
                    java.lang.String r4 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.lemon.lv.config.ClientSetting r0 = r2
                    com.lemon.lv.editor.PlayerConfig r0 = r0.getPlayerConfig()
                    boolean r2 = r0.f59418a
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$4 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$4
                    com.lemon.lv.config.ClientSetting r0 = r2
                    r1.<init>()
                    r10.d(r1, r2, r4)
                    r0 = 2131890517(0x7f121155, float:1.9415728E38)
                    java.lang.String r4 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.core.context.debug.DevelopSetting r0 = r3
                    boolean r2 = r0.useGetFrame3()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$5 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$5
                    com.vega.core.context.debug.DevelopSetting r0 = r3
                    r1.<init>()
                    r10.d(r1, r2, r4)
                    r0 = 2131897560(0x7f122cd8, float:1.9430013E38)
                    java.lang.String r4 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.core.context.debug.DevelopSetting r0 = r3
                    boolean r2 = r0.useCameraBootOpt()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$6 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$6
                    com.vega.core.context.debug.DevelopSetting r0 = r3
                    r1.<init>()
                    r10.d(r1, r2, r4)
                    r0 = 2131897563(0x7f122cdb, float:1.943002E38)
                    java.lang.String r4 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.lemon.lv.config.ClientSetting r0 = r2
                    com.lemon.lv.editor.FileCacheClean r0 = r0.Z()
                    boolean r2 = r0.f59410a
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$7 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$7
                    com.lemon.lv.config.ClientSetting r0 = r2
                    r1.<init>()
                    r10.d(r1, r2, r4)
                    r0 = 2131890889(0x7f1212c9, float:1.9416483E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    android.content.SharedPreferences r1 = com.vega.ve.api.VEDebugConfigHelper.b
                    java.lang.String r0 = "force_do_not_compress_size"
                    boolean r1 = r1.getBoolean(r0, r3)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$8 r0 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.8
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$8 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$8
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$8) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.8.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$8
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass8.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass8.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r10, java.lang.Boolean r11) {
                            /*
                                r9 = this;
                                java.lang.Boolean r11 = (java.lang.Boolean) r11
                                boolean r3 = r11.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.ve.api.VEDebugConfigHelper r0 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                r0.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.f135277c = r3
                                android.content.SharedPreferences r0 = com.vega.ve.api.VEDebugConfigHelper.b
                                android.content.SharedPreferences$Editor r2 = r0.edit()
                                java.lang.String r1 = "force_do_not_compress_size"
                                boolean r0 = com.vega.ve.api.VEDebugConfigHelper.f135277c
                                android.content.SharedPreferences$Editor r0 = r2.putBoolean(r1, r0)
                                r0.apply()
                                if (r3 == 0) goto L3b
                                r0 = 2131897478(0x7f122c86, float:1.9429847E38)
                            L28:
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r2 = r1
                                r3 = r1
                                r4 = r1
                                r5 = r1
                                r7 = r1
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            L3b:
                                r0 = 2131897477(0x7f122c85, float:1.9429845E38)
                                goto L28
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass8.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    r10.d(r0, r1, r2)
                    r0 = 2131897310(0x7f122bde, float:1.9429506E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    boolean r1 = com.vega.ve.api.VEDebugConfigHelper.f135278d
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$9 r0 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.9
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$9 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$9
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$9) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.9.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$9
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass9.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass9.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.vega.ve.api.VEDebugConfigHelper r0 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                r0.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.f135278d = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass9.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    r10.d(r0, r1, r2)
                    r0 = 2131891152(0x7f1213d0, float:1.9417016E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.ve.api.VESDKHelper r0 = com.vega.ve.api.VESDKHelper.f135280a
                    r0.getClass()
                    com.vega.ve.data.VeInitConfig r0 = com.vega.ve.api.VESDKHelper.h
                    boolean r1 = r0.C
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$10 r0 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.10
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$10 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$10
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$10) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.10.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$10
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass10.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass10.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.vega.ve.api.VESDKHelper r0 = com.vega.ve.api.VESDKHelper.f135280a
                                r0.getClass()
                                com.vega.ve.data.VeInitConfig r0 = com.vega.ve.api.VESDKHelper.h
                                r0.C = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass10.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    r10.d(r0, r1, r2)
                    r0 = 2131897571(0x7f122ce3, float:1.9430035E38)
                    java.lang.String r4 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.BaseUIWidget r0 = r4
                    android.content.SharedPreferences r1 = r0.e
                    java.lang.String r0 = "enable_all_level_log_output"
                    boolean r2 = r1.getBoolean(r0, r3)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$11 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$11
                    com.vega.nativesettings.BaseUIWidget r0 = r4
                    r1.<init>()
                    r10.d(r1, r2, r4)
                    com.vega.nativesettings.BaseUIWidget r0 = r4
                    android.content.SharedPreferences r1 = r0.e
                    java.lang.String r0 = "disable_logcat_output"
                    boolean r2 = r1.getBoolean(r0, r3)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$12 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$12
                    com.vega.nativesettings.BaseUIWidget r0 = r4
                    r1.<init>()
                    java.lang.String r0 = "Disable logcat output"
                    r10.d(r1, r2, r0)
                    r0 = 2131897579(0x7f122ceb, float:1.9430051E38)
                    java.lang.String r4 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.kv.KvStorage r2 = new com.vega.kv.KvStorage
                    android.content.Context r0 = r10.getContext()
                    android.content.Context r1 = r0.getApplicationContext()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                    java.lang.String r0 = "native_debug"
                    r2.<init>(r1, r0)
                    java.lang.String r0 = "false"
                    java.lang.String r5 = "enable"
                    java.lang.String r1 = r2.i(r5, r0)
                    java.lang.String r0 = "true"
                    boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$13 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$13
                    r0.<init>()
                    r10.d(r0, r1, r4)
                    r0 = 2131890896(0x7f1212d0, float:1.9416497E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    com.vega.nativesettings.FpsCollector r0 = com.vega.nativesettings.FpsCollector.f125650a
                    r0.getClass()
                    boolean r1 = com.vega.nativesettings.FpsCollector.b
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$14 r0 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.14
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$14 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$14
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$14) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.14.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$14
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass14.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass14.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r10, java.lang.Boolean r11) {
                            /*
                                r9 = this;
                                java.lang.Boolean r11 = (java.lang.Boolean) r11
                                boolean r1 = r11.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
                                com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
                                boolean r0 = r0.printLog()
                                if (r0 == 0) goto L42
                                if (r1 == 0) goto L39
                                com.vega.nativesettings.FpsCollector r0 = com.vega.nativesettings.FpsCollector.f125650a
                                r0.getClass()
                                com.vega.nativesettings.FpsCollector.a()
                                r0 = 2131897993(0x7f122e89, float:1.9430891E38)
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 1
                                r2 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r3 = r2
                                r4 = r2
                                r5 = r2
                                r7 = r2
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                            L36:
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            L39:
                                com.vega.nativesettings.FpsCollector r0 = com.vega.nativesettings.FpsCollector.f125650a
                                r0.getClass()
                                com.vega.nativesettings.FpsCollector.c()
                                goto L36
                            L42:
                                r0 = 2131891005(0x7f12133d, float:1.9416718E38)
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 1
                                r2 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r3 = r2
                                r4 = r2
                                r5 = r2
                                r7 = r2
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                goto L36
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass14.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    r10.d(r0, r1, r2)
                    r0 = 2131897564(0x7f122cdc, float:1.9430021E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    java.lang.Boolean r0 = com.vega.ve.api.VEDebugConfigHelper.f
                    if (r0 == 0) goto L270
                    boolean r1 = r0.booleanValue()
                L155:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$15 r0 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.15
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$15 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$15
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$15) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.15.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$15
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass15.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass15.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r10, java.lang.Boolean r11) {
                            /*
                                r9 = this;
                                java.lang.Boolean r11 = (java.lang.Boolean) r11
                                boolean r2 = r11.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.ve.api.VEDebugConfigHelper r1 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                                r1.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.f = r0
                                if (r2 == 0) goto L2e
                                r0 = 2131897567(0x7f122cdf, float:1.9430027E38)
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 1
                                r2 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r3 = r2
                                r4 = r2
                                r5 = r2
                                r7 = r2
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                            L2b:
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            L2e:
                                r0 = 2131897582(0x7f122cee, float:1.9430058E38)
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 1
                                r2 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r3 = r2
                                r4 = r2
                                r5 = r2
                                r7 = r2
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                goto L2b
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass15.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    r10.d(r0, r1, r2)
                    java.lang.Boolean r0 = com.vega.ve.api.VEDebugConfigHelper.f135279g
                    if (r0 == 0) goto L264
                    boolean r2 = r0.booleanValue()
                L162:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$16 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.16
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$16 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$16
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$16) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.16.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$16
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass16.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass16.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r4, java.lang.Boolean r5) {
                            /*
                                r3 = this;
                                java.lang.Boolean r5 = (java.lang.Boolean) r5
                                boolean r2 = r5.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                                com.vega.ve.api.VEDebugConfigHelper r1 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                                r1.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.f135279g = r0
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass16.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Support remux"
                    r10.d(r1, r2, r0)
                    r0 = 2131897565(0x7f122cdd, float:1.9430023E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    java.lang.Boolean r0 = com.vega.ve.api.VEDebugConfigHelper.e
                    if (r0 == 0) goto L258
                    boolean r1 = r0.booleanValue()
                L178:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$17 r0 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.17
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$17 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$17
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$17) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.17.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$17
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass17.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass17.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r10, java.lang.Boolean r11) {
                            /*
                                r9 = this;
                                java.lang.Boolean r11 = (java.lang.Boolean) r11
                                boolean r2 = r11.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
                                com.vega.ve.api.VEDebugConfigHelper r1 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r2)
                                r1.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.e = r0
                                if (r2 == 0) goto L2e
                                r0 = 2131897566(0x7f122cde, float:1.9430025E38)
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 1
                                r2 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r3 = r2
                                r4 = r2
                                r5 = r2
                                r7 = r2
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                            L2b:
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            L2e:
                                r0 = 2131897581(0x7f122ced, float:1.9430056E38)
                                java.lang.String r0 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                                r1 = 1
                                r2 = 0
                                r6 = 0
                                r8 = 508(0x1fc, float:7.12E-43)
                                r3 = r2
                                r4 = r2
                                r5 = r2
                                r7 = r2
                                com.vega.util.ToastUtilKt.e(r0, r1, r2, r3, r4, r5, r6, r7, r8)
                                goto L2b
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass17.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    r10.d(r0, r1, r2)
                    com.ss.android.anywheredoor_api.core.AnyDoorManager r0 = com.ss.android.anywheredoor_api.core.AnyDoorManager.INSTANCE
                    com.ss.android.anywheredoor_api.service.IAnyDoorService r8 = r0.getAnyDoorService()
                    kotlin.jvm.internal.Ref$BooleanRef r7 = new kotlin.jvm.internal.Ref$BooleanRef
                    r7.<init>()
                    if (r8 == 0) goto L255
                    android.content.Context r0 = r10.getContext()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r6)
                    boolean r4 = r8.getAnywhereSwitch(r0)
                L195:
                    r7.element = r4
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$18 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$18
                    r1.<init>()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$19 r2 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$19
                    r2.<init>()
                    java.lang.String r0 = "Random door switch"
                    com.vega.nativesettings.MenuItem r0 = r10.d(r1, r4, r0)
                    android.widget.TextView r1 = r0.getTitleText()
                    X.0tr r0 = new X.0tr
                    r0.<init>()
                    r1.setOnClickListener(r0)
                    com.vega.kv.KvStorage r4 = new com.vega.kv.KvStorage
                    android.content.Context r0 = r10.getContext()
                    android.content.Context r1 = r0.getApplicationContext()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                    java.lang.String r0 = "devkit"
                    r4.<init>(r1, r0)
                    r0 = 2131890230(0x7f121036, float:1.9415146E38)
                    java.lang.String r2 = com.vega.infrastructure.base.ModuleCommonKt.b(r0)
                    java.lang.String r0 = "key_calidge_enable"
                    boolean r1 = r4.d(r0, r3)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$20$1 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$20$1
                    r0.<init>(r10, r4)
                    r10.d(r0, r1, r2)
                    com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
                    boolean r0 = r0.v()
                    if (r0 == 0) goto L1fa
                    com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
                    com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
                    boolean r2 = r0.getForbidDisableScreenRecord()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$21 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$21
                    r1.<init>(r10)
                    java.lang.String r0 = "Disable enableScreenRecord"
                    r10.d(r1, r2, r0)
                L1fa:
                    boolean r2 = com.vega.ve.api.VEDebugConfigHelper.k
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$22 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.22
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$22 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$22
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$22) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.22.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$22
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass22.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass22.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.vega.ve.api.VEDebugConfigHelper r0 = com.vega.ve.api.VEDebugConfigHelper.f135276a
                                r0.getClass()
                                com.vega.ve.api.VEDebugConfigHelper.k = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass22.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "showfaceid"
                    r10.d(r1, r2, r0)
                    com.vega.feelgoodapi.FeelGoodService$Companion r0 = com.vega.feelgoodapi.FeelGoodService.f102444a
                    r0.getClass()
                    boolean r2 = com.vega.feelgoodapi.FeelGoodService.Companion.b
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$23 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.23
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$23 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$23
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$23) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.23.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$23
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass23.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass23.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.vega.feelgoodapi.FeelGoodService$Companion r0 = com.vega.feelgoodapi.FeelGoodService.f102444a
                                r0.getClass()
                                com.vega.feelgoodapi.FeelGoodService.Companion.b = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass23.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Ignore positive review pop-up frequency control"
                    r10.d(r1, r2, r0)
                    com.lemon.vega.ug.api.IPublishQuestionnaireService$Companion r0 = com.lemon.vega.ug.api.IPublishQuestionnaireService.f59952a
                    r0.getClass()
                    boolean r2 = com.lemon.vega.ug.api.IPublishQuestionnaireService.Companion.b
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$24 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.24
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$24 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$24
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$24) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.24.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$24
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass24.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass24.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.lemon.vega.ug.api.IPublishQuestionnaireService$Companion r0 = com.lemon.vega.ug.api.IPublishQuestionnaireService.f59952a
                                r0.getClass()
                                com.lemon.vega.ug.api.IPublishQuestionnaireService.Companion.b = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass24.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Ignore the frequency control of the submission preference questionnaire"
                    r10.d(r1, r2, r0)
                    com.vega.config.AdTestConfig r4 = com.vega.config.AdTestConfig.f78886a
                    r4.getClass()
                    android.content.SharedPreferences r1 = com.vega.config.AdTestConfig.f78887c
                    java.lang.String r0 = "force_do_not_filter_new_user"
                    boolean r2 = r1.getBoolean(r0, r3)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$25 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.25
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$25 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$25
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$25) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.25.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$25
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass25.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass25.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r4, java.lang.Boolean r5) {
                            /*
                                r3 = this;
                                java.lang.Boolean r5 = (java.lang.Boolean) r5
                                boolean r1 = r5.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
                                com.vega.config.AdTestConfig r0 = com.vega.config.AdTestConfig.f78886a
                                r0.getClass()
                                com.vega.config.AdTestConfig.j = r1
                                android.content.SharedPreferences r0 = com.vega.config.AdTestConfig.f78887c
                                android.content.SharedPreferences$Editor r2 = r0.edit()
                                java.lang.String r1 = "force_do_not_filter_new_user"
                                boolean r0 = com.vega.config.AdTestConfig.j
                                android.content.SharedPreferences$Editor r0 = r2.putBoolean(r1, r0)
                                r0.apply()
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass25.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Ignore ads new user filter"
                    r10.d(r1, r2, r0)
                    boolean r2 = r4.a()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$26 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.26
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$26 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$26
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$26) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.26.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$26
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass26.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass26.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r6, java.lang.Boolean r7) {
                            /*
                                r5 = this;
                                java.lang.Boolean r7 = (java.lang.Boolean) r7
                                boolean r4 = r7.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                com.vega.config.AdTestConfig r3 = com.vega.config.AdTestConfig.f78886a
                                r3.getClass()
                                kotlin.properties.ReadWriteProperty r2 = com.vega.config.AdTestConfig.k
                                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.vega.config.AdTestConfig.b
                                r0 = 3
                                r1 = r1[r0]
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                                r2.setValue(r3, r1, r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass26.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Launch open personalized popup"
                    r10.d(r1, r2, r0)
                    boolean r2 = r4.a()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$27 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.27
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$27 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$27
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$27) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.27.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$27
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass27.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass27.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.vega.adeditorapi.part.AdCubeTemplateConfig r0 = com.vega.adeditorapi.part.AdCubeTemplateConfig.f68429a
                                r0.getClass()
                                com.vega.adeditorapi.part.AdCubeTemplateConfig.b = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass27.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Enable advertisement template debugging"
                    r10.d(r1, r2, r0)
                    com.lm.components.report.ReportManager r0 = com.lm.components.report.ReportManager.f61031c
                    android.content.Context r1 = r10.getContext()
                    com.lm.components.report.applog.AppLogImpl r0 = r0.b
                    r0.getClass()
                    goto L285
                L255:
                    r4 = 0
                    goto L195
                L258:
                    com.vega.ve.api.VESettings r0 = r5
                    com.vega.ve.api.VENewConfig r0 = r0.getVeNewConfig()
                    boolean r1 = r0.r()
                    goto L178
                L264:
                    com.lemon.lv.config.ClientSetting r0 = r2
                    com.lemon.lv.config.VEReMuxConfig r0 = r0.getVeReMuxConfig()
                    boolean r2 = r0.a()
                    goto L162
                L270:
                    com.vega.ve.api.VESettings r0 = r5
                    com.vega.ve.api.VENewConfig r0 = r0.getVeNewConfig()
                    boolean r1 = r0.s()
                    goto L155
                L27c:
                    r2 = 0
                    goto L35
                L27f:
                    r2 = 0
                    goto L26
                L282:
                    r2 = 0
                    goto L15
                L285:
                    boolean r0 = com.ss.android.common.applog.AppLog.getSwitchToBdtracker()     // Catch: java.lang.Exception -> L2a7
                    if (r0 == 0) goto L290
                    boolean r2 = com.bytedance.applog.AppLog.isNewUserMode(r1)     // Catch: java.lang.Exception -> L2a7
                    goto L2a8
                L290:
                    com.ss.android.common.applog.AppLog r0 = com.ss.android.common.applog.AppLog.getInstance(r1)     // Catch: java.lang.Exception -> L2a7
                    com.ss.android.deviceregister.INewUserModeManager r0 = r0.newUserMode(r1)     // Catch: java.lang.Exception -> L2a7
                    if (r0 == 0) goto L2a7
                    com.ss.android.common.applog.AppLog r0 = com.ss.android.common.applog.AppLog.getInstance(r1)     // Catch: java.lang.Exception -> L2a7
                    com.ss.android.deviceregister.INewUserModeManager r0 = r0.newUserMode(r1)     // Catch: java.lang.Exception -> L2a7
                    boolean r2 = r0.isNewUserMode()     // Catch: java.lang.Exception -> L2a7
                    goto L2a8
                L2a7:
                    r2 = 0
                L2a8:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$28 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$28
                    com.vega.nativesettings.BaseUIWidget r0 = r4
                    r1.<init>()
                    java.lang.String r0 = "Set DID for new user (only for debug package)"
                    r10.d(r1, r2, r0)
                    com.vega.start.logic.StartDebugConfig r4 = com.vega.start.logic.StartDebugConfig.f130868a
                    r4.getClass()
                    kotlin.properties.ReadWriteProperty r1 = com.vega.start.logic.StartDebugConfig.f130869c
                    kotlin.reflect.KProperty<java.lang.Object>[] r7 = com.vega.start.logic.StartDebugConfig.b
                    r0 = r7[r3]
                    java.lang.Object r0 = r1.getValue(r4, r0)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r2 = r0.booleanValue()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$29 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.29
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$29 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$29
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$29) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.29.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$29
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass29.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass29.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r6, java.lang.Boolean r7) {
                            /*
                                r5 = this;
                                java.lang.Boolean r7 = (java.lang.Boolean) r7
                                boolean r4 = r7.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                com.vega.start.logic.StartDebugConfig r3 = com.vega.start.logic.StartDebugConfig.f130868a
                                r3.getClass()
                                kotlin.properties.ReadWriteProperty r2 = com.vega.start.logic.StartDebugConfig.f130869c
                                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.vega.start.logic.StartDebugConfig.b
                                r0 = 0
                                r1 = r1[r0]
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                                r2.setValue(r3, r1, r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass29.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Start task delayed execution"
                    r10.d(r1, r2, r0)
                    boolean r2 = r4.a()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$30 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.30
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$30 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$30
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$30) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.30.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$30
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass30.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass30.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r6, java.lang.Boolean r7) {
                            /*
                                r5 = this;
                                java.lang.Boolean r7 = (java.lang.Boolean) r7
                                boolean r4 = r7.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                com.vega.start.logic.StartDebugConfig r3 = com.vega.start.logic.StartDebugConfig.f130868a
                                r3.getClass()
                                kotlin.properties.ReadWriteProperty r2 = com.vega.start.logic.StartDebugConfig.f130870d
                                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.vega.start.logic.StartDebugConfig.b
                                r0 = 1
                                r1 = r1[r0]
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                                r2.setValue(r3, r1, r0)
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass30.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Main thread prints log"
                    r10.d(r1, r2, r0)
                    kotlin.properties.ReadWriteProperty r1 = com.vega.start.logic.StartDebugConfig.e
                    r0 = 2
                    r0 = r7[r0]
                    java.lang.Object r0 = r1.getValue(r4, r0)
                    java.lang.Boolean r0 = (java.lang.Boolean) r0
                    boolean r2 = r0.booleanValue()
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$31 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.31
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$31 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$31
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$31) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.31.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$31
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass31.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass31.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r6, java.lang.Boolean r7) {
                            /*
                                r5 = this;
                                java.lang.Boolean r7 = (java.lang.Boolean) r7
                                boolean r4 = r7.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                                com.vega.start.logic.StartDebugConfig r3 = com.vega.start.logic.StartDebugConfig.f130868a
                                r3.getClass()
                                kotlin.properties.ReadWriteProperty r2 = com.vega.start.logic.StartDebugConfig.e
                                kotlin.reflect.KProperty<java.lang.Object>[] r1 = com.vega.start.logic.StartDebugConfig.b
                                r0 = 2
                                r1 = r1[r0]
                                java.lang.Boolean r0 = java.lang.Boolean.valueOf(r4)
                                r2.setValue(r3, r1, r0)
                                if (r4 == 0) goto L22
                                com.vega.performance.PerformanceManagerHelper.blogEnable = r4
                            L22:
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass31.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "Forcefully enable BLog.i logging"
                    r10.d(r1, r2, r0)
                    com.vega.kv.KvStorage r4 = new com.vega.kv.KvStorage
                    android.content.Context r0 = r10.getContext()
                    android.content.Context r1 = r0.getApplicationContext()
                    kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r6)
                    java.lang.String r0 = "local_questionnaire"
                    r4.<init>(r1, r0)
                    boolean r2 = r4.d(r5, r3)
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$32$1 r1 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$32$1
                    r1.<init>(r10, r4)
                    java.lang.String r0 = "Each cold start retrieves user features"
                    r10.d(r1, r2, r0)
                    com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
                    com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
                    boolean r0 = r0.openBOE()
                    if (r0 == 0) goto L334
                    com.vega.core.context.IHostEnv r0 = com.vega.core.context.ContextExtKt.hostEnv()
                    com.vega.core.context.debug.DevelopSetting r0 = r0.developSettings()
                    boolean r0 = r0.openBOE()
                    if (r0 != 0) goto L337
                L32d:
                    com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$33 r1 = new kotlin.jvm.functions.Function2<android.widget.CompoundButton, java.lang.Boolean, kotlin.Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.33
                        static {
                            /*
                                com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$33 r0 = new com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$33
                                r0.<init>()
                                
                                // error: 0x0005: SPUT (r0 I:com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$33) com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.33.e com.vega.nativesettings.BaseUIWidget$baseMenuItems$3$33
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass33.<clinit>():void");
                        }

                        {
                            /*
                                r1 = this;
                                r0 = 2
                                r1.<init>(r0)
                                return
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass33.<init>():void");
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function2
                        public final kotlin.Unit invoke(android.widget.CompoundButton r3, java.lang.Boolean r4) {
                            /*
                                r2 = this;
                                java.lang.Boolean r4 = (java.lang.Boolean) r4
                                boolean r1 = r4.booleanValue()
                                java.lang.String r0 = ""
                                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                                com.vega.config.PanelMigrateVimoConfigKt.f78929a = r1
                                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                return r0
                            */
                            throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.AnonymousClass33.invoke(java.lang.Object, java.lang.Object):java.lang.Object");
                        }
                    }
                    java.lang.String r0 = "cc stickers/material library now uses vimo (no need to restart)"
                    r10.d(r1, r3, r0)
                L334:
                    kotlin.Unit r0 = kotlin.Unit.INSTANCE
                    return r0
                L337:
                    boolean r3 = com.vega.config.PanelMigrateVimoConfigKt.f78929a
                    goto L32d
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vega.nativesettings.BaseUIWidget$baseMenuItems$3.invoke(java.lang.Object):java.lang.Object");
            }
        });
        b();
        a("Automated testing", new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$4
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MenuGroup menuGroup) {
                MenuGroup menuGroup2 = menuGroup;
                Intrinsics.checkNotNullParameter(menuGroup2, "");
                AutomaticTestConfig.f78900a.getClass();
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$4.1
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        AutomaticTestConfig.f78900a.getClass();
                        AutomaticTestConfig.f78901c = zBooleanValue;
                        AutomaticTestConfig.b.edit().putBoolean("close_popup_window", AutomaticTestConfig.f78901c).apply();
                        return Unit.INSTANCE;
                    }
                }, AutomaticTestConfig.a(), "Block all pop-ups");
                SharedPreferences sharedPreferences = AutomaticTestConfig.b;
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$4.2
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        AutomaticTestConfig.f78900a.getClass();
                        AutomaticTestConfig.f78902d = zBooleanValue;
                        AutomaticTestConfig.b.edit().putBoolean("output_memory_and_cpu_info", AutomaticTestConfig.f78902d).apply();
                        if (zBooleanValue) {
                            OutputMemoryAndCPU.f125662a.getClass();
                            BPEAHandler bPEAHandler = OutputMemoryAndCPU.b;
                            if (bPEAHandler.hasMessages(1001)) {
                                bPEAHandler.removeMessages(1001);
                            }
                            bPEAHandler.sendEmptyMessage(1001);
                        } else {
                            OutputMemoryAndCPU.f125662a.getClass();
                            OutputMemoryAndCPU.b.removeMessages(1001);
                        }
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("output_memory_and_cpu_info", false), "Output memory and CPU logs");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$4.3
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        AutomaticTestConfig.f78900a.getClass();
                        AutomaticTestConfig.e = zBooleanValue;
                        AutomaticTestConfig.b.edit().putBoolean("enable_album_batch_select", AutomaticTestConfig.e).apply();
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("enable_album_batch_select", false), "Batch select/deselect album materials");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$4.4
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        AutomaticTestConfig.f78900a.getClass();
                        AutomaticTestConfig.f = zBooleanValue;
                        AutomaticTestConfig.b.edit().putBoolean("enable_force_draft_lose_in_db", AutomaticTestConfig.f).apply();
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("enable_force_draft_lose_in_db", false), "Simulated draft loss");
                return Unit.INSTANCE;
            }
        });
        b();
        a("Commercial Creation Experiment Switch", new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$5
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MenuGroup menuGroup) {
                final MenuGroup menuGroup2 = menuGroup;
                Intrinsics.checkNotNullParameter(menuGroup2, "");
                CC4BTestConfig.f78903a.getClass();
                SharedPreferences sharedPreferences = CC4BTestConfig.b;
                boolean z = sharedPreferences.getBoolean("adscript_test_entrance", false);
                final BaseUIWidget baseUIWidget = this.e;
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$5.1
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        CC4BTestConfig.f78903a.getClass();
                        CC4BTestConfig.f78904c = zBooleanValue;
                        CC4BTestConfig.b.edit().putBoolean("adscript_test_entrance", CC4BTestConfig.f78904c).apply();
                        LocationProperty locationProperty = LocationPropertyKt.f79173a;
                        locationProperty.getClass();
                        locationProperty.f79171a = "US";
                        ContextExtKt.app().u(locationProperty);
                        baseUIWidget.getClass();
                        BaseUIWidget.h();
                        return Unit.INSTANCE;
                    }
                }, z, "adscript Entrance");
                boolean z2 = sharedPreferences.getBoolean("bc_test_entrance", false);
                final BaseUIWidget baseUIWidget2 = this.e;
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$5.2
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        CC4BTestConfig.f78903a.getClass();
                        CC4BTestConfig.f78905d = zBooleanValue;
                        CC4BTestConfig.b.edit().putBoolean("bc_test_entrance", CC4BTestConfig.f78905d).apply();
                        LocationProperty locationProperty = LocationPropertyKt.f79173a;
                        locationProperty.getClass();
                        locationProperty.f79171a = "US";
                        ContextExtKt.app().u(locationProperty);
                        baseUIWidget2.getClass();
                        BaseUIWidget.h();
                        return Unit.INSTANCE;
                    }
                }, z2, "BC end integrated entry");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$5.3
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        CC4BTestConfig.f78903a.getClass();
                        CC4BTestConfig.e = zBooleanValue;
                        CC4BTestConfig.b.edit().putBoolean("ad_maker_shoot_album_entrance", CC4BTestConfig.e).apply();
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("ad_maker_shoot_album_entrance", false), "new ad maker Capture display album entry");
                menuGroup2.c("Ad script Editor,Current" + sharedPreferences.getInt("ad_script_use_ad_maker_edit", 0), new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$5.4
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) {
                        View view2 = view;
                        Intrinsics.checkNotNullParameter(view2, "");
                        Context context3 = menuGroup2.getContext();
                        Intrinsics.checkNotNullExpressionValue(context3, "");
                        CommonInputDialog commonInputDialog = new CommonInputDialog(context3, "Confirm", "0-Online, 1-Ad Maker Editor, 2-Component Editor", true, new Function2<CommonInputDialog, String, Unit>(menuGroup2, view2) { // from class: com.vega.nativesettings.BaseUIWidget.baseMenuItems.5.4.1
                            public final /* synthetic */ View e;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(2);
                                this.e = view2;
                            }

                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function2
                            public final Unit invoke(CommonInputDialog commonInputDialog2, String str) {
                                Object objCreateFailure;
                                MenuItem menuItem;
                                TextView titleText;
                                CommonInputDialog commonInputDialog3 = commonInputDialog2;
                                String str2 = str;
                                Intrinsics.checkNotNullParameter(commonInputDialog3, "");
                                Intrinsics.checkNotNullParameter(str2, "");
                                try {
                                    objCreateFailure = Integer.valueOf(Integer.parseInt(StringsKt__StringsKt.trim((CharSequence) str2).toString()));
                                    Result.m17090constructorimpl(objCreateFailure);
                                } catch (Throwable th) {
                                    objCreateFailure = ResultKt.createFailure(th);
                                    Result.m17090constructorimpl(objCreateFailure);
                                }
                                if (Result.m17097isSuccessimpl(objCreateFailure)) {
                                    CC4BTestConfig cC4BTestConfig = CC4BTestConfig.f78903a;
                                    Integer num = (Integer) (Result.m17096isFailureimpl(objCreateFailure) ? null : objCreateFailure);
                                    int iIntValue = num != null ? num.intValue() : 0;
                                    cC4BTestConfig.getClass();
                                    CC4BTestConfig.f = iIntValue;
                                    CC4BTestConfig.b.edit().putInt("ad_script_use_ad_maker_edit", CC4BTestConfig.f).apply();
                                    View view3 = this.e;
                                    if ((view3 instanceof MenuItem) && (menuItem = (MenuItem) view3) != null && (titleText = menuItem.getTitleText()) != null) {
                                        StringBuilder sb = new StringBuilder("Ad script editor, current");
                                        if (Result.m17096isFailureimpl(objCreateFailure)) {
                                            objCreateFailure = null;
                                        }
                                        sb.append(objCreateFailure);
                                        titleText.setText(sb.toString());
                                    }
                                } else {
                                    ToastUtilKt.e("Incorrect input, please enter a number...", 0, 0, 0, 0, false, null, false, 510);
                                }
                                commonInputDialog3.dismiss();
                                return Unit.INSTANCE;
                            }
                        }, 34);
                        if (!new HeliosApiHook().preInvoke(300000, "com/vega/nativesettings/CommonInputDialog", "show", commonInputDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSW1Y1TASBdVDwZ8SuBIidxCpqnMXoBMtvsD67")).isIntercept()) {
                            commonInputDialog.show();
                        }
                        return Unit.INSTANCE;
                    }
                });
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$5.5
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        CC4BTestConfig.f78903a.getClass();
                        CC4BTestConfig.f78906g = zBooleanValue;
                        CC4BTestConfig.b.edit().putBoolean("ad_script_video_force", CC4BTestConfig.f78906g).apply();
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("ad_script_video_force", false), "ScriptVideo uses the new editor");
                return Unit.INSTANCE;
            }
        });
        b();
        a("Hypic image template test switch", new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$6
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MenuGroup menuGroup) {
                final MenuGroup menuGroup2 = menuGroup;
                Intrinsics.checkNotNullParameter(menuGroup2, "");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$6.1
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        Context applicationContext = menuGroup2.getContext().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "");
                        new KvStorage(applicationContext, "retouch_debug_assist").l("open_opt", zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, false, "Hypic Assistant Debugging");
                RetouchPictureTestConfig.f78930a.getClass();
                SharedPreferences sharedPreferences = RetouchPictureTestConfig.b;
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$6.2
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        RetouchPictureTestConfig.f78930a.getClass();
                        RetouchPictureTestConfig.f78931c = zBooleanValue;
                        RetouchPictureTestConfig.b.edit().putBoolean("hypic_template_export_fail", RetouchPictureTestConfig.f78931c).apply();
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("hypic_template_export_fail", false), "Image template simulation export failed");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$6.3
                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        RetouchPictureTestConfig.f78930a.getClass();
                        RetouchPictureTestConfig.f78932d = zBooleanValue;
                        RetouchPictureTestConfig.b.edit().putBoolean("hypic_template_export_slow", RetouchPictureTestConfig.f78932d).apply();
                        return Unit.INSTANCE;
                    }
                }, sharedPreferences.getBoolean("hypic_template_export_slow", false), "The image template simulation export gradually increases the time consumption by 3 seconds.");
                return Unit.INSTANCE;
            }
        });
        b();
        a("Ecological-related debugging switch", new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MenuGroup menuGroup) {
                final MenuGroup menuGroup2 = menuGroup;
                Intrinsics.checkNotNullParameter(menuGroup2, "");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7.1
                    public final /* synthetic */ String f = "cc_others";

                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        Context applicationContext = menuGroup2.getContext().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "");
                        new KvStorage(applicationContext, this.f).l("lite_editor_not_use_rounded", zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, false, "LiteEditor does not use rounded corners");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7.2
                    public final /* synthetic */ String f = "cc_others";

                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        Context applicationContext = menuGroup2.getContext().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext, "");
                        new KvStorage(applicationContext, this.f).l("export_fail_mock", zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, false, "Simulation export failed after 1.5 seconds.");
                Context applicationContext = menuGroup2.getContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7.3
                    public final /* synthetic */ String f = "cc_others";

                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        Context applicationContext2 = menuGroup2.getContext().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext2, "");
                        new KvStorage(applicationContext2, this.f).l("export_feed_native", zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, new KvStorage(applicationContext, "cc_others").d("export_feed_native", false), "Export feed uses native cards");
                Context applicationContext2 = menuGroup2.getContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext2, "");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7.4
                    public final /* synthetic */ String f = "cc_others";

                    /* renamed from: g, reason: collision with root package name */
                    public final /* synthetic */ String f125641g = "edit_tutorial";

                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        Context applicationContext3 = menuGroup2.getContext().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext3, "");
                        new KvStorage(applicationContext3, this.f).l(this.f125641g, zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, new KvStorage(applicationContext2, "cc_others").d("edit_tutorial", false), "Clip tutorial entry guide forced display");
                menuGroup2.c("Open the popup center test page", new Function1<View, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7.5
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(View view) {
                        Intrinsics.checkNotNullParameter(view, "");
                        SmartRouter.buildRoute(menuGroup2.getContext(), "//test/pop_center").open();
                        return Unit.INSTANCE;
                    }
                });
                Context applicationContext3 = menuGroup2.getContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext3, "");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$7.6
                    public final /* synthetic */ String f = "cc_others";

                    /* renamed from: g, reason: collision with root package name */
                    public final /* synthetic */ String f125642g = "smart_template_debug_switch";

                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        Context applicationContext4 = menuGroup2.getContext().getApplicationContext();
                        Intrinsics.checkNotNullExpressionValue(applicationContext4, "");
                        new KvStorage(applicationContext4, this.f).l(this.f125642g, zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, new KvStorage(applicationContext3, "cc_others").d("smart_template_debug_switch", false), "smartTemplate Script-Local Debug Switch");
                return Unit.INSTANCE;
            }
        });
        b();
        a("Material-related debugging switches", new Function1<MenuGroup, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$8
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(MenuGroup menuGroup) {
                MenuGroup menuGroup2 = menuGroup;
                Intrinsics.checkNotNullParameter(menuGroup2, "");
                Context applicationContext = menuGroup2.getContext().getApplicationContext();
                Intrinsics.checkNotNullExpressionValue(applicationContext, "");
                final KvStorage kvStorage = new KvStorage(applicationContext, "material_panel");
                menuGroup2.d(new Function2<CompoundButton, Boolean, Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$baseMenuItems$8.1
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(CompoundButton compoundButton, Boolean bool) {
                        boolean zBooleanValue = bool.booleanValue();
                        Intrinsics.checkNotNullParameter(compoundButton, "");
                        kvStorage.l("debug", zBooleanValue, false);
                        return Unit.INSTANCE;
                    }
                }, kvStorage.d("debug", false), "Material panel debugging tool switch");
                return Unit.INSTANCE;
            }
        });
    }

    public static Uri INVOKESTATIC_com_vega_nativesettings_BaseUIWidget_com_xt_retouch_applauncher_module_ContentProviderHook_getUriForFile(Context context, String str, File file) {
        XTLog.f144340a.getClass();
        XTLog.e("ContentProviderHook", "hook_getUriForFile");
        if (str.equals("com.xt.retouch.provider")) {
            ContentProviderHook.reportFileAccess(file);
        }
        return FileProvider.getUriForFile(context, str, file);
    }

    public static void INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(Context context, Intent intent) {
        DeeplinkIntentLancetImpl.a(intent);
        INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_libfiles_files_hook_StartMainActivityHook_startActivity(context, intent);
    }

    public static boolean INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_draft_monitor_DraftMonitorLancet_delete(File file) {
        try {
            if (MonitorExtKt.e && (file instanceof File)) {
                String absolutePath = file.getAbsolutePath();
                MonitorExtKt.c(file);
                MonitorExtKt.b("DraftMonitorLancet#delete", absolutePath);
                if (!DraftMonitorLancet.a(absolutePath, "delete")) {
                    return false;
                }
            }
        } catch (Throwable unused) {
        }
        return INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_libfiles_files_hook_FileHook_delete(file);
    }

    public static SharedPreferences INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(Application application, String str, int i) throws InterruptedException {
        try {
            return KevaSpAopHook.getSharedPreferences(application, str, i);
        } catch (NullPointerException e) {
            try {
                Thread.sleep(0L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (e.getMessage() != null && e.getMessage().contains("isUserKeyUnlocked")) {
                EnsureManager.ensureNotReachHere(e, "getSharedPreferences isUserKeyUnlocked NullPointerException name=" + str + ",mode" + i);
            }
            return KevaSpAopHook.getSharedPreferences(application, str, i);
        }
    }

    public static boolean INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_libfiles_files_hook_FileHook_delete(File file) {
        FileAssist fileAssist = FileAssist.INSTANCE;
        if (!fileAssist.isEnable()) {
            return file.delete();
        }
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("FileHook", "hook_delete");
        }
        if (!(file instanceof File)) {
            return false;
        }
        fileAssist.awaitInspect(file);
        if (FileHook.resolvePath(file)) {
            return file.delete();
        }
        return false;
    }

    public static void INVOKEVIRTUAL_com_vega_nativesettings_BaseUIWidget_com_vega_libfiles_files_hook_StartMainActivityHook_startActivity(Context context, Intent intent) {
        StartMainActivityHook.fixLauncherIntent(intent);
        HeliosApiHook heliosApiHook = new HeliosApiHook();
        Object[] objArr = {intent};
        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/content/Intent;)V", "dzBzEhQ/WMuSW1Y1TASBdVDwZ8SuBIidxCpqnMXoBMtvsD67");
        if (heliosApiHook.preInvoke(11090, "android/content/Context", "startActivity", context, objArr, "void", extraInfo).isIntercept()) {
            heliosApiHook.postInvoke(null, 11090, "android/content/Context", "startActivity", context, objArr, extraInfo, false);
        } else {
            context.startActivity(intent);
            heliosApiHook.postInvoke(null, 11090, "android/content/Context", "startActivity", context, objArr, extraInfo, true);
        }
    }

    public static void h() {
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(ModuleCommon.INSTANCE.getApplication(), "//main");
        smartRouteBuildRoute.addFlags(268468224);
        smartRouteBuildRoute.open();
        ThreadUtilKt.b(500L, new Function0<Unit>() { // from class: com.vega.nativesettings.BaseUIWidget$rebotCc4b$1
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                SmartRoute smartRouteBuildRoute2 = SmartRouter.buildRoute(ModuleCommon.INSTANCE.getApplication(), "//main");
                smartRouteBuildRoute2.addFlags(268468224);
                smartRouteBuildRoute2.open();
                Process.killProcess(Process.myPid());
                System.exit(0);
                throw new RuntimeException("System.exit returned normally, while it was supposed to halt JVM.");
            }
        });
    }

    public void e() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public final void f(String str) {
        String exposedVids;
        BaseNewDeveloperActivity baseNewDeveloperActivity;
        AppContext appContext;
        BaseNewDeveloperActivity baseNewDeveloperActivity2;
        AppContext appContext2;
        BaseNewDeveloperActivity baseNewDeveloperActivity3;
        AppContext appContext3;
        Intrinsics.checkNotNullParameter(str, "");
        SPIService sPIService = SPIService.INSTANCE;
        String channel = null;
        UploadConfig config = ((UploadConfigProvider) sPIService.getImpl(Reflection.getOrCreateKotlinClass(UploadConfigProvider.class), null)).getConfig();
        boolean z = false;
        if (Intrinsics.areEqual(str, "version")) {
            StringBuilder sb = new StringBuilder("manufacture\t: ");
            sb.append(Build.MANUFACTURER);
            sb.append("\nmodel\t: ");
            sb.append(Build.MODEL);
            sb.append("\nversionCode\t: ");
            Context context = getContext();
            sb.append((!(context instanceof BaseNewDeveloperActivity) || (baseNewDeveloperActivity3 = (BaseNewDeveloperActivity) context) == null || (appContext3 = (AppContext) baseNewDeveloperActivity3.v.getValue()) == null) ? null : Integer.valueOf(appContext3.getVersionCode()));
            sb.append("\nversionName\t: ");
            Context context2 = getContext();
            sb.append((!(context2 instanceof BaseNewDeveloperActivity) || (baseNewDeveloperActivity2 = (BaseNewDeveloperActivity) context2) == null || (appContext2 = (AppContext) baseNewDeveloperActivity2.v.getValue()) == null) ? null : appContext2.getVersion());
            sb.append("\nchannel\t: ");
            Context context3 = getContext();
            if ((context3 instanceof BaseNewDeveloperActivity) && (baseNewDeveloperActivity = (BaseNewDeveloperActivity) context3) != null && (appContext = (AppContext) baseNewDeveloperActivity.v.getValue()) != null) {
                channel = appContext.getChannel();
            }
            sb.append(channel);
            sb.append("\npatch\t:");
            sb.append(PatchHelper.isApplyPatch());
            sb.append('_');
            sb.append(PatchHelper.getPatchVersion());
            sb.append("\ncommitID\t: 57ee77d5cc64\ndraft\t:184.0.0\nveSDK\t: 22.0.0.0_rel_20260807100021-cc\neffectSDK\t:");
            AppProperty appProperty = ContextExtHelper.b;
            appProperty.A();
            appProperty.r();
            sb.append(appProperty.D());
            sb.append("\neffect channel\t:");
            sb.append(ContextExtKt.hostEnv().developSettings().effectDebugChannel() ? "test" : "online");
            sb.append("\nsupport-ABIs: ");
            String[] strArr = Build.SUPPORTED_ABIS;
            Intrinsics.checkNotNullExpressionValue(strArr, "");
            sb.append(ArraysKt___ArraysKt.joinToString$default(strArr, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
            sb.append("\nLynx version: 0.0.2-hq-SNAPSHOT\napk-ABI: ");
            ContextExtKt.app().k();
            ApkUtil.f79541a.getClass();
            sb.append(Process.is64Bit() ? "arm64-v8a" : "armeabi-v7a");
            sb.append("\nuploader-TTNet: ");
            sb.append(config.d());
            sb.append("\nlv_videoeditor: a43a2bd3abe0f4aec0947c8f1efa7e885ecc7633\nlv_videoeditor_ve: 22.0.0.0_rel_20260807100021-cc\neverPhotoSdk: 13.1.0-alpha.25-ttp\nretouchSdk: local-version\n");
            appProperty.b();
            appProperty.C();
            appProperty.P();
            appProperty.c();
            exposedVids = sb.toString();
        } else if (Intrinsics.areEqual(str, "gettdid")) {
            exposedVids = "did\t: " + ContextExtKt.device().c() + "\ninstallId\t: " + ContextExtKt.app().m() + "\nuserId\t: " + ((LoginService) sPIService.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).getUserId();
        } else if (Intrinsics.areEqual(str, "getfingerprint")) {
            exposedVids = "manufacture\t: " + Build.MANUFACTURER + "\nmodel\t: " + Build.MODEL + "\nversionRelease\t: " + Build.VERSION.RELEASE + "\nversionIncremental\t: " + Build.VERSION.INCREMENTAL + "\ndisplay\t: " + Build.DISPLAY;
        } else if (StringsKt__StringsJVMKt.startsWith$default(str, "native_debug", false, 2, null)) {
            try {
                String strSubstring = str.substring(13);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "");
                List listSplit$default = StringsKt__StringsKt.split$default(strSubstring, new String[]{"="}, false, 0, 6, (Object) null);
                if (listSplit$default.size() != 2) {
                    exposedVids = "error command: ".concat(str);
                } else {
                    String str2 = (String) listSplit$default.get(0);
                    Context applicationContext = getContext().getApplicationContext();
                    Intrinsics.checkNotNullExpressionValue(applicationContext, "");
                    new KvStorage(applicationContext, "native_debug").p(str2, (String) listSplit$default.get(1), false);
                    exposedVids = "exec " + str + " ok";
                }
            } catch (Exception e) {
                exposedVids = "exec " + str + " error, " + e.getMessage();
            }
        } else if (StringsKt__StringsJVMKt.startsWith$default(str, "http", false, 2, null)) {
            exposedVids = g(str);
        } else if (StringsKt__StringsJVMKt.startsWith$default(str, "capcut", false, 2, null)) {
            Uri uri = Uri.parse(str);
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(uri);
            intent.addFlags(268435456);
            ContextCompat.Api16Impl.INVOKEVIRTUAL_androidx_core_content_ContextCompat$Api16Impl_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(getContext(), intent, null);
            exposedVids = String.valueOf(uri);
        } else if (Intrinsics.areEqual(str, "getAbVid")) {
            exposedVids = ExposedManager.getInstance(GlobalConfig.a()).getExposedVids();
            if (exposedVids == null) {
                exposedVids = "";
            }
        } else {
            exposedVids = "Wrong command";
        }
        Context context4 = getContext();
        Intrinsics.checkNotNullExpressionValue(context4, "");
        CommonInputDialog commonInputDialog = new CommonInputDialog(context4, null, exposedVids, z, 0 == true ? 1 : 0, 118);
        if (new HeliosApiHook().preInvoke(300000, "com/vega/nativesettings/CommonInputDialog", "show", commonInputDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSW1Y1TASBdVDwZ8SuBIidxCpqnMXoBMtvsD67")).isIntercept()) {
            return;
        }
        commonInputDialog.show();
    }

    public final String g(String str) {
        Uri uriBuild = Uri.parse("capcut://main/web").buildUpon().appendQueryParameter("web_url", str).build();
        Intent intent = new Intent("android.intent.action.VIEW", uriBuild);
        intent.addFlags(268435456);
        ContextCompat.Api16Impl.INVOKEVIRTUAL_androidx_core_content_ContextCompat$Api16Impl_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(getContext(), intent, null);
        return String.valueOf(uriBuild);
    }
}
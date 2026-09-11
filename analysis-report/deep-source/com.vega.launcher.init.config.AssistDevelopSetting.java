package com.vega.launcher.init.config;

import com.bytedance.common.profilesdk.ProfileManager;
import com.vega.config.AutomaticTestConfig;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.debug.APIHost;
import com.vega.core.context.debug.DevelopSetting;
import com.vega.feelgoodapi.settings.DeveloperSettingManager;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.kv.KvStorage;
import com.vega.kv.KvStorageKt;
import com.vega.launcher.debug.AssistConfig;
import com.vega.libdeveloper.nativesettings.DeveloperLogManager;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import top.canyie.pine.BuildConfig;

/* loaded from: classes23.dex */
public final class AssistDevelopSetting implements DevelopSetting {

    /* renamed from: a, reason: collision with root package name */
    public static final AssistDevelopSetting f107289a;
    public static final /* synthetic */ KProperty<Object>[] b;

    /* renamed from: c, reason: collision with root package name */
    public static final ReadWriteProperty f107290c;

    /* renamed from: d, reason: collision with root package name */
    public static final ReadWriteProperty f107291d;
    public static final ReadWriteProperty e;
    public static final ReadWriteProperty f;

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(AssistDevelopSetting.class, "useGetFrame3", "getUseGetFrame3()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(AssistDevelopSetting.class, "useCameraBootOpt", "getUseCameraBootOpt()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl2);
        MutablePropertyReference1Impl mutablePropertyReference1Impl3 = new MutablePropertyReference1Impl(AssistDevelopSetting.class, "forbidDisableScreenRecord", "getForbidDisableScreenRecord()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl3);
        MutablePropertyReference1Impl mutablePropertyReference1Impl4 = new MutablePropertyReference1Impl(AssistDevelopSetting.class, "isPluginEntryOpen", "isPluginEntryOpen()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl4);
        b = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2, mutablePropertyReference1Impl3, mutablePropertyReference1Impl4};
        f107289a = new AssistDevelopSetting();
        KvStorage kvStorage = new KvStorage(ModuleCommon.INSTANCE.getApplication(), "AssistDevelopSetting.conf");
        Boolean bool = Boolean.FALSE;
        f107290c = KvStorageKt.e(kvStorage, "useGetFrame3", bool, false);
        f107291d = KvStorageKt.e(kvStorage, "useCameraBootOpt", bool, false);
        e = KvStorageKt.e(kvStorage, "key_forbid_disable_screen_record", bool, false);
        f = KvStorageKt.e(kvStorage, "key_plugin_entry_open", bool, false);
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String abGroup() {
        AssistConfig.f107239a.getClass();
        return (String) AssistConfig.f.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean anyWhereDoor() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.w.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean autoDownloadDraftTest() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.z.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String boeSuffix() {
        return "";
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final List<String> boeWhiteHosts() {
        AssistConfig.f107239a.getClass();
        return (List) AssistConfig.k.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean effectDebugChannel() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.l.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean effectModuleDebugChannel() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.m.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean enableCameraEffectSDK() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.A.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean enableCvFloatingWindow() {
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean enableDevEntrance() {
        AssistConfig.f107239a.getClass();
        if (!((Boolean) AssistConfig.y.getValue()).booleanValue()) {
            DeveloperSettingManager.f102457a.getClass();
            if (!DeveloperSettingManager.b && !isInnerChannel()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean enableMockNewUserQuestion() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.H.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean etEnable() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.n.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean exportDraft() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.s.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean geckoDebug() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.u.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final Map<String, String> geckoHeaders() {
        AssistConfig.f107239a.getClass();
        return (Map) AssistConfig.p.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean getForbidDisableScreenRecord() {
        return ((Boolean) e.getValue(this, b[2])).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String getHostChannel() {
        AssistConfig.f107239a.getClass();
        String str = (String) ((Map) AssistConfig.o.getValue()).get("X-TT-ENV");
        return str == null ? "" : str;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String getMockNewUserQstLibGroup() {
        AssistConfig.f107239a.getClass();
        return (String) AssistConfig.I.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String getPitayaRemoteUrl() {
        AssistConfig.f107239a.getClass();
        return (String) AssistConfig.D.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean hideGIF() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.q.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final APIHost host() {
        AssistConfig.f107239a.getClass();
        String str = (String) AssistConfig.f107241d.getValue();
        String str2 = (String) AssistConfig.f107242g.getValue();
        Intrinsics.checkNotNullExpressionValue(str2, "");
        return new APIHost(str, str2, (String) AssistConfig.h.getValue());
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean importDraft() {
        AssistConfig.f107239a.getClass();
        ((Boolean) AssistConfig.r.getValue()).booleanValue();
        return true;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean inPPEReviewEnv() {
        AssistConfig.f107239a.getClass();
        Lazy lazy = AssistConfig.o;
        return Intrinsics.areEqual(((Map) lazy.getValue()).get("X-TT-ENV"), "ppe_for_review") && Intrinsics.areEqual(((Map) lazy.getValue()).get("X-USE-PPE"), ProfileManager.VERSION);
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isAlbumToVideoDebug() {
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isAutoTest() {
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isBuildRheaPro() {
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isDisableDraftEncryptAll() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.B.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isDisableDraftExportEncrypt() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.C.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isHyperTest() {
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isInnerChannel() {
        return Intrinsics.areEqual(ContextExtKt.app().channel(), BuildConfig.BUILD_TYPE) || Intrinsics.areEqual(ContextExtKt.app().channel(), "local_test") || Intrinsics.areEqual(ContextExtKt.app().channel(), "debug") || Intrinsics.areEqual(ContextExtKt.app().channel(), "auto_test");
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isIntegratedBuild() {
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isOutBuild() {
        return true;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isPluginEntryOpen() {
        return ((Boolean) f.getValue(this, b[3])).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isSmartClientDebug() {
        return new KvStorage(ModuleCommon.INSTANCE.getApplication(), "smart_client_debug_config").d("smart_client_debug_switch", false);
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean isTestChannel() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.F.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean lynxDebug() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.t.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean notVipExportEnable() {
        AutomaticTestConfig.f78900a.getClass();
        return AutomaticTestConfig.b.getBoolean("enable_not_vip_export", false);
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean openBOE() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.f107240c.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean openPPEEnv() {
        AssistConfig.f107239a.getClass();
        return false;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean printLog() {
        if (!isInnerChannel()) {
            AssistConfig.f107239a.getClass();
            if (!((Boolean) AssistConfig.i.getValue()).booleanValue()) {
                return false;
            }
        }
        return true;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean reportToDebugEnv() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.j.getValue()).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final void setCameraBootOpt(boolean z) {
        f107291d.setValue(this, b[1], Boolean.valueOf(z));
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final void setForbidDisableScreenRecord(boolean z) {
        e.setValue(this, b[2], Boolean.valueOf(z));
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final void setGetFrame3(boolean z) {
        f107290c.setValue(this, b[0], Boolean.valueOf(z));
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final void setPluginEntryOpen(boolean z) {
        f.setValue(this, b[3], Boolean.valueOf(z));
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final void showDeveloperLog(CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(charSequence, "");
        DeveloperLogManager.f109354a.getClass();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean useCameraBootOpt() {
        return ((Boolean) f107291d.getValue(this, b[1])).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean useGetFrame3() {
        return ((Boolean) f107290c.getValue(this, b[0])).booleanValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final Map<String, String> userHeaders() {
        AssistConfig.f107239a.getClass();
        return (Map) AssistConfig.o.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String veAndEffectLogLevel() {
        AssistConfig.f107239a.getClass();
        return (String) AssistConfig.E.getValue();
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final String webDebugToolURL() {
        AssistConfig.f107239a.getClass();
        String str = (String) AssistConfig.x.getValue();
        Intrinsics.checkNotNullExpressionValue(str, "");
        return str;
    }

    @Override // com.vega.core.context.debug.DevelopSetting
    public final boolean webViewHock() {
        AssistConfig.f107239a.getClass();
        return ((Boolean) AssistConfig.v.getValue()).booleanValue();
    }
}
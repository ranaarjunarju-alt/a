package com.lemon.host.config;

import com.lemon.account.AccessConfig;
import com.lemon.cloud.CloudHostConfig;
import com.lemon.cloud.CloudSwitchConfig;
import com.lemon.config.BirthdayFillinAlertConfig;
import com.lemon.config.BirthdayInputConfig;
import com.lemon.config.CapcutAccess;
import com.lemon.config.CheckAgeConfig;
import com.lemon.config.CloudDraftAccess;
import com.lemon.config.DraftMainTabAbtest;
import com.lemon.config.FlavorAccountConfig;
import com.vega.airecommend.TipsProvider;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.settings.obtain.SettingsObtainer;
import com.vega.gallery.api.OverseaGallerySettings;
import com.vega.gallery.api.RecommendPromptAbTest;
import com.vega.gallery.api.RecommendPromptConfig;
import com.vega.gallery.api.RecommendPromptContainerConfig;
import com.vega.gallery.api.RecommendPromptV2AbTest;
import com.vega.main.MainPageToolConfig;
import com.vega.main.config.AIModelConfig;
import com.vega.main.config.AutoCutEntranceReverseConfig;
import com.vega.main.config.AutoCutEntranceStatus;
import com.vega.main.config.AutoCutGuideConfig;
import com.vega.main.config.BusinessUserCriterion;
import com.vega.main.config.CCPAConfig;
import com.vega.main.config.CCProfileHomeVipCardOptConfig;
import com.vega.main.config.CCVideoCaptureConfig;
import com.vega.main.config.CourseTabTipsConfigEntry;
import com.vega.main.config.CustomizeCodeConfig;
import com.vega.main.config.DiscoverEntranceConfig;
import com.vega.main.config.DynamicDiscover;
import com.vega.main.config.EditSearchAbTest;
import com.vega.main.config.FlavorMainConfig;
import com.vega.main.config.GlobalHomeToolAutoCutConfig;
import com.vega.main.config.HelpCenterEntranceAbTest;
import com.vega.main.config.HelpCenterEntranceAbTestV2;
import com.vega.main.config.HomeDraftBoxLayoutAbTest;
import com.vega.main.config.HomeImportantBugfix;
import com.vega.main.config.HomeNewToolConfig;
import com.vega.main.config.HomeToolAutoCutConfig;
import com.vega.main.config.HomepageResConfig;
import com.vega.main.config.HomepageTopBannerConfigEntity;
import com.vega.main.config.HomepageVipBtnStyleConfig;
import com.vega.main.config.OverseaConfigProvider;
import com.vega.main.config.PersonalFeatureKeyConfig;
import com.vega.main.config.PostTemplateHomeToolAb;
import com.vega.main.config.PrivacySdkConfig;
import com.vega.main.config.ProfileCoursesFeedConfig;
import com.vega.main.config.ProfileHomeReform;
import com.vega.main.config.TextToVideoEntranceABConfig;
import com.vega.main.config.UserAgreementUpdateInfo;
import com.vega.recorderapi.config.CameraEditorConfig;
import com.vega.recorderapi.config.CameraSettingsProvider;
import com.vega.recorderapi.config.FixCameraMemoryLeak;
import com.vega.shareapi.config.FlavorShareConfig;
import com.vega.shareapi.config.ShareAwemeConfig;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes28.dex */
public final class OverseaHostSettings implements FlavorAccountConfig, OverseaConfigProvider, FlavorMainConfig, TipsProvider, CameraSettingsProvider, OverseaGallerySettings, CloudConfig, FlavorShareConfig {

    /* renamed from: a, reason: collision with root package name */
    public static final Companion f58770a = new Companion();
    public static final Lazy<OverseaRemoteHostSettings> b = LazyKt__LazyJVMKt.lazy(new Function0<OverseaRemoteHostSettings>() { // from class: com.lemon.host.config.OverseaHostSettings$Companion$settings$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v1, types: [com.lemon.host.config.OverseaRemoteHostSettings, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final OverseaRemoteHostSettings invoke() {
            SettingsObtainer.f79519a.getClass();
            return SettingsObtainer.d(OverseaRemoteHostSettings.class);
        }
    });

    /* loaded from: classes7.dex */
    public static final class Companion {
        public static OverseaRemoteHostSettings a() {
            return OverseaHostSettings.b.getValue();
        }
    }

    @Override // com.lemon.host.config.CloudConfig
    public final CloudHostConfig.CloudBannerConfig a() {
        f58770a.getClass();
        return Companion.a().getCloudBannerConfig();
    }

    @Override // com.lemon.config.FlavorAccountConfig
    public final DraftMainTabAbtest b() {
        f58770a.getClass();
        return Companion.a().getDraftMainTabAbtest();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final ProfileHomeReform c() {
        f58770a.getClass();
        return Companion.a().getProfileHomeReform();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final boolean d() {
        f58770a.getClass();
        return Companion.a().getMainPageToolConfig().isAdMakerToolVisible() || (Companion.a().getDynamicDiscover().c() && Companion.a().getDiscoverEntranceConfig().a());
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final boolean e() {
        f58770a.getClass();
        if (Companion.a().getDynamicDiscover().c()) {
            if (!Companion.a().getDiscoverEntranceConfig().a()) {
                SPIService sPIService = SPIService.INSTANCE;
                if (((AccessConfig) sPIService.getImpl(Reflection.getOrCreateKotlinClass(AccessConfig.class), null)).l() || ((AccessConfig) sPIService.getImpl(Reflection.getOrCreateKotlinClass(AccessConfig.class), null)).r0()) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final boolean f() {
        f58770a.getClass();
        return Companion.a().getDynamicDiscover().a();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final CCProfileHomeVipCardOptConfig g() {
        return (CCProfileHomeVipCardOptConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ProfileHomeVipCardOptSetting.class));
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final AIModelConfig getAiModelConfig() {
        f58770a.getClass();
        return Companion.a().getAiModelConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final AutoCutEntranceReverseConfig getAutoCutEntranceReverseConfig() {
        f58770a.getClass();
        return Companion.a().getAutoCutEntranceReverseConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final AutoCutEntranceStatus getAutoCutEntranceStatus() {
        f58770a.getClass();
        return Companion.a().getAutoCutEntranceStatus();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final AutoCutGuideConfig getAutoCutGuideConfig() {
        f58770a.getClass();
        return Companion.a().getAutoCutGuideConfig();
    }

    @Override // com.lemon.config.FlavorAccountConfig
    public final BirthdayFillinAlertConfig getBirthdayFillinAlertConfig() {
        f58770a.getClass();
        return Companion.a().getBirthdayFillinAlertConfig();
    }

    @Override // com.lemon.config.FlavorAccountConfig
    public final BirthdayInputConfig getBirthdayInputConfig() {
        f58770a.getClass();
        return Companion.a().getBirthdayInputConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final BusinessUserCriterion getBusinessUserCriterion() {
        f58770a.getClass();
        return Companion.a().getBusinessUserCriterion();
    }

    @Override // com.vega.recorderapi.config.CameraSettingsProvider
    public final CameraEditorConfig getCameraEditorConfig() {
        f58770a.getClass();
        return Companion.a().getCameraEditorConfig();
    }

    @Override // com.lemon.config.FlavorAccountConfig
    public final CapcutAccess getCapcutAccess() {
        f58770a.getClass();
        return Companion.a().getCapcutAccess();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final CCVideoCaptureConfig getCcVideoCaptureConfig() {
        f58770a.getClass();
        return Companion.a().getCcVideoCaptureConfig();
    }

    @Override // com.vega.main.config.OverseaConfigProvider
    public final CCPAConfig getCcpaConfig() {
        f58770a.getClass();
        return Companion.a().getCcpaConfig();
    }

    @Override // com.lemon.config.FlavorAccountConfig
    public final CheckAgeConfig getCheckAgeConfig() {
        f58770a.getClass();
        return Companion.a().getCheckAgeConfig();
    }

    @Override // com.lemon.config.FlavorAccountConfig
    public final CloudDraftAccess getCloudDraftAccess() {
        f58770a.getClass();
        return Companion.a().getCloudDraftAccess();
    }

    @Override // com.lemon.host.config.CloudConfig
    public final CloudSwitchConfig.CloudShareReviewEntrance getCloudShareReviewEntrance() {
        f58770a.getClass();
        return Companion.a().getCloudShareReviewEntrance();
    }

    @Override // com.vega.main.config.OverseaConfigProvider
    public final UserAgreementUpdateInfo getComplianceUpdateInfo() {
        f58770a.getClass();
        return Companion.a().getComplianceUpdateInfo();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final CourseTabTipsConfigEntry getCourseTabTipsConfig() {
        f58770a.getClass();
        return Companion.a().getCourseTabTipsConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final CustomizeCodeConfig getCustomizeCodeConfig() {
        f58770a.getClass();
        return Companion.a().getCustomizeCodeConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final DiscoverEntranceConfig getDiscoverEntranceConfig() {
        f58770a.getClass();
        return Companion.a().getDiscoverEntranceConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final DynamicDiscover getDynamicDiscover() {
        f58770a.getClass();
        return Companion.a().getDynamicDiscover();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final EditSearchAbTest getEditSearchAbTest() {
        f58770a.getClass();
        return Companion.a().getEditSearchAbTest();
    }

    @Override // com.vega.recorderapi.config.CameraSettingsProvider
    public final FixCameraMemoryLeak getFixCameraMemoryLeak() {
        f58770a.getClass();
        return Companion.a().getFixCameraMemoryLeak();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final GlobalHomeToolAutoCutConfig getGlobalHomeToolAutoCutConfig() {
        f58770a.getClass();
        return Companion.a().getGlobalHomeToolAutoCutConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HelpCenterEntranceAbTest getHelpCenterEntranceAbTest() {
        return new HelpCenterEntranceAbTest(true, false);
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HelpCenterEntranceAbTestV2 getHelpCenterEntranceAbTestV2() {
        f58770a.getClass();
        return Companion.a().getHelpCenterEntranceAbTestV2();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomeDraftBoxLayoutAbTest getHomeDraftBoxLayoutAbTest() {
        f58770a.getClass();
        return Companion.a().getHomeDraftBoxLayoutAbTest();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomeImportantBugfix getHomeImportantBugfix() {
        f58770a.getClass();
        return Companion.a().getHomeImportantBugfix();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomeNewToolConfig getHomeNewToolConfig() {
        f58770a.getClass();
        return Companion.a().getHomeNewToolConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomeToolAutoCutConfig getHomeToolAutoCutConfig() {
        f58770a.getClass();
        return Companion.a().getHomeToolAutoCutConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomepageResConfig getHomepageResConfig() {
        f58770a.getClass();
        return Companion.a().getHomepageResConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomepageTopBannerConfigEntity getHomepageTopBannerConfigEntity() {
        f58770a.getClass();
        return Companion.a().getHomepageTopBannerConfigEntity();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final HomepageVipBtnStyleConfig getHomepageVipBtnStyleConfig() {
        f58770a.getClass();
        return Companion.a().getHomepageVipBtnStyleConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final MainPageToolConfig getMainPageToolConfig() {
        f58770a.getClass();
        return Companion.a().getMainPageToolConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final PersonalFeatureKeyConfig getPersonalFeatureKeyConfig() {
        f58770a.getClass();
        return Companion.a().getPersonalFeatureKeyConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final PostTemplateHomeToolAb getPostTemplateHomeToolAb() {
        f58770a.getClass();
        return Companion.a().getPostTemplateHomeToolAb();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final PrivacySdkConfig getPrivacySdkConfig() {
        f58770a.getClass();
        return Companion.a().getPrivacySdkConfig();
    }

    @Override // com.vega.main.config.OverseaConfigProvider
    public final ProfileCoursesFeedConfig getProfileCoursesFeedConfig() {
        f58770a.getClass();
        return Companion.a().getProfileCoursesFeedConfig();
    }

    @Override // com.vega.gallery.api.OverseaGallerySettings
    public final RecommendPromptAbTest getRecommendPromptAbTest() {
        f58770a.getClass();
        return Companion.a().getRecommendPromptAbTest();
    }

    @Override // com.vega.gallery.api.OverseaGallerySettings
    public final RecommendPromptConfig getRecommendPromptConfig() {
        f58770a.getClass();
        return Companion.a().getRecommendPromptConfig();
    }

    @Override // com.vega.gallery.api.OverseaGallerySettings
    public final RecommendPromptContainerConfig getRecommendPromptContainerConfig() {
        f58770a.getClass();
        return Companion.a().getRecommendPromptContainerConfig();
    }

    @Override // com.vega.gallery.api.OverseaGallerySettings
    public final RecommendPromptV2AbTest getRecommendPromptV2AbTest() {
        f58770a.getClass();
        return Companion.a().getRecommendPromptV2AbTest();
    }

    @Override // com.vega.recorderapi.config.CameraSettingsProvider
    public final Map<String, List<String>> getScanDomainAllowList() {
        f58770a.getClass();
        return Companion.a().getScanDomainAllowList();
    }

    @Override // com.vega.shareapi.config.FlavorShareConfig
    public final ShareAwemeConfig getShareAwemeConfig() {
        f58770a.getClass();
        return Companion.a().getShareAwemeConfig();
    }

    @Override // com.vega.main.config.FlavorMainConfig
    public final TextToVideoEntranceABConfig getTextToVideoEntranceABConfig() {
        f58770a.getClass();
        return Companion.a().getTextToVideoEntranceABConfig();
    }

    @Override // com.vega.main.config.OverseaConfigProvider
    public final UserAgreementUpdateInfo getUserAgreementUpdateInfo() {
        f58770a.getClass();
        return Companion.a().getUserAgreementUpdateInfo();
    }
}
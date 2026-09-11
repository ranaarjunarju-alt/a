package com.lemon.host.config;

import com.bytedance.news.common.settings.api.annotation.ISettings;
import com.bytedance.news.common.settings.api.annotation.Settings;
import com.lemon.cloud.CloudHostConfig;
import com.lemon.cloud.CloudSwitchConfig;
import com.lemon.config.AgeDoorConfig;
import com.lemon.config.BirthdayFillinAlertConfig;
import com.lemon.config.BirthdayInputConfig;
import com.lemon.config.CapcutAccess;
import com.lemon.config.CheckAgeConfig;
import com.lemon.config.CloudDraftAccess;
import com.lemon.config.DraftMainTabAbtest;
import com.vega.airecommend.AIRecommendAbConfig;
import com.vega.airecommend.AIRecommendGuideTips;
import com.vega.gallery.api.RecommendPromptAbTest;
import com.vega.gallery.api.RecommendPromptConfig;
import com.vega.gallery.api.RecommendPromptContainerConfig;
import com.vega.gallery.api.RecommendPromptV2AbTest;
import com.vega.main.MainPageToolConfig;
import com.vega.main.config.AIModelConfig;
import com.vega.main.config.AllSearchConfig;
import com.vega.main.config.AutoCutEntranceReverseConfig;
import com.vega.main.config.AutoCutEntranceStatus;
import com.vega.main.config.AutoCutGuideConfig;
import com.vega.main.config.BusinessUserCriterion;
import com.vega.main.config.CCHomepageBanner;
import com.vega.main.config.CCPAConfig;
import com.vega.main.config.CCVideoCaptureConfig;
import com.vega.main.config.CourseTabTipsConfigEntry;
import com.vega.main.config.CustomizeCodeConfig;
import com.vega.main.config.DiscoverEntranceConfig;
import com.vega.main.config.DynamicDiscover;
import com.vega.main.config.EditSearchAbTest;
import com.vega.main.config.GlobalHomeToolAutoCutConfig;
import com.vega.main.config.HelpCenterEntranceAbTest;
import com.vega.main.config.HelpCenterEntranceAbTestV2;
import com.vega.main.config.HomeDraftBoxLayoutAbTest;
import com.vega.main.config.HomeDraftListOptimize;
import com.vega.main.config.HomeImportantBugfix;
import com.vega.main.config.HomeNewToolConfig;
import com.vega.main.config.HomeToolAutoCutConfig;
import com.vega.main.config.HomeToolCountConfig;
import com.vega.main.config.HomeTryCardConfig;
import com.vega.main.config.HomepageResConfig;
import com.vega.main.config.HomepageTopBannerConfigEntity;
import com.vega.main.config.HomepageVipBtnStyleConfig;
import com.vega.main.config.LocationEntry;
import com.vega.main.config.PersonalFeatureKeyConfig;
import com.vega.main.config.PostTemplateHomeToolAb;
import com.vega.main.config.PrivacySdkConfig;
import com.vega.main.config.ProfileCoursesFeedConfig;
import com.vega.main.config.ProfileHomeReform;
import com.vega.main.config.TextToVideoEntranceABConfig;
import com.vega.main.config.UserAgreementUpdateInfo;
import com.vega.recorderapi.config.CameraEditorConfig;
import com.vega.recorderapi.config.CapCutCameraEntryABConfig;
import com.vega.recorderapi.config.FixCameraMemoryLeak;
import com.vega.shareapi.config.ShareAwemeConfig;
import com.vega.shareapi.config.ShareWhatsAppOptConfig;
import java.util.List;
import java.util.Map;

@Settings(storageKey = "oversea_settings")
/* loaded from: classes39.dex */
public interface OverseaRemoteHostSettings extends ISettings {
    AgeDoorConfig getAgeDoorConfig();

    AIModelConfig getAiModelConfig();

    AIRecommendAbConfig getAiRecommendAbConfig();

    AIRecommendGuideTips getAiRecommendGuideTips();

    AutoCutEntranceReverseConfig getAutoCutEntranceReverseConfig();

    AutoCutEntranceStatus getAutoCutEntranceStatus();

    AutoCutGuideConfig getAutoCutGuideConfig();

    BirthdayFillinAlertConfig getBirthdayFillinAlertConfig();

    BirthdayInputConfig getBirthdayInputConfig();

    BusinessUserCriterion getBusinessUserCriterion();

    CameraEditorConfig getCameraEditorConfig();

    CapCutCameraEntryABConfig getCapCutCameraEntryABConfig();

    CapcutAccess getCapcutAccess();

    CCHomepageBanner getCcHomepageBanner();

    CCVideoCaptureConfig getCcVideoCaptureConfig();

    CCPAConfig getCcpaConfig();

    CheckAgeConfig getCheckAgeConfig();

    CloudHostConfig.CloudBannerConfig getCloudBannerConfig();

    CloudDraftAccess getCloudDraftAccess();

    CloudSwitchConfig.CloudShareReviewEntrance getCloudShareReviewEntrance();

    UserAgreementUpdateInfo getComplianceUpdateInfo();

    CourseTabTipsConfigEntry getCourseTabTipsConfig();

    CustomizeCodeConfig getCustomizeCodeConfig();

    DiscoverEntranceConfig getDiscoverEntranceConfig();

    DraftMainTabAbtest getDraftMainTabAbtest();

    DynamicDiscover getDynamicDiscover();

    EditSearchAbTest getEditSearchAbTest();

    FixCameraMemoryLeak getFixCameraMemoryLeak();

    GlobalHomeToolAutoCutConfig getGlobalHomeToolAutoCutConfig();

    HelpCenterEntranceAbTest getHelpCenterEntranceAbTest();

    HelpCenterEntranceAbTestV2 getHelpCenterEntranceAbTestV2();

    HomeDraftBoxLayoutAbTest getHomeDraftBoxLayoutAbTest();

    HomeDraftListOptimize getHomeDraftListOptimize();

    HomeImportantBugfix getHomeImportantBugfix();

    HomeNewToolConfig getHomeNewToolConfig();

    HomeToolAutoCutConfig getHomeToolAutoCutConfig();

    HomeToolCountConfig getHomeToolCountConfig();

    HomeTryCardConfig getHomeTryCardConfig();

    HomepageResConfig getHomepageResConfig();

    HomepageTopBannerConfigEntity getHomepageTopBannerConfigEntity();

    HomepageVipBtnStyleConfig getHomepageVipBtnStyleConfig();

    LocationEntry getLocationInfo();

    MainPageToolConfig getMainPageToolConfig();

    PersonalFeatureKeyConfig getPersonalFeatureKeyConfig();

    PostTemplateHomeToolAb getPostTemplateHomeToolAb();

    PrivacySdkConfig getPrivacySdkConfig();

    ProfileCoursesFeedConfig getProfileCoursesFeedConfig();

    ProfileHomeReform getProfileHomeReform();

    RecommendPromptAbTest getRecommendPromptAbTest();

    RecommendPromptConfig getRecommendPromptConfig();

    RecommendPromptContainerConfig getRecommendPromptContainerConfig();

    RecommendPromptV2AbTest getRecommendPromptV2AbTest();

    Map<String, List<String>> getScanDomainAllowList();

    AllSearchConfig getSearchConfig();

    ShareAwemeConfig getShareAwemeConfig();

    ShareWhatsAppOptConfig getShareWhatsAppOptConfig();

    TextToVideoEntranceABConfig getTextToVideoEntranceABConfig();

    UserAgreementUpdateInfo getUserAgreementUpdateInfo();
}
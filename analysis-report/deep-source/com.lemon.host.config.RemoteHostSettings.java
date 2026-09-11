package com.lemon.host.config;

import com.bytedance.news.common.settings.api.annotation.ISettings;
import com.bytedance.news.common.settings.api.annotation.Settings;
import com.lemon.lv.libpush.PushKeepAliveEntity;
import com.vega.feelgoodapi.config.FeedbackConfig;
import com.vega.feelgoodapi.settings.DevelopPageConfig;
import com.vega.gallery.api.AlbumCloudMaterialAbTest;
import com.vega.gallery.api.AlbumPerformanceABTest;
import com.vega.gallery.api.AlbumPreviewConfig;
import com.vega.gallery.api.CopyrightDistribute;
import com.vega.gallery.api.DirectRequestAbTest;
import com.vega.gallery.api.MaterialJumpPersonalPageAbTest;
import com.vega.gallery.api.MaterialSearchV1Config;
import com.vega.gallery.api.MediaFilterConfig;
import com.vega.gallery.api.OptMediaPickerConfig;
import com.vega.gallery.api.PipTrackLimitConfig;
import com.vega.gallery.api.RetouchPictureEditorAbtest;
import com.vega.gallery.api.SmartClientAbTest;
import com.vega.gallery.api.VideoPlayerABTest;
import com.vega.guideapi.GuideConfig;
import com.vega.libfiles.files.SdcardDowngradeConfig;
import com.vega.libgecko.config.FalconConfig;
import com.vega.libmedia.EnablePlayerH265HwDecoder;
import com.vega.libmedia.LvPlayerConfig;
import com.vega.libmedia.VideoPlayerParameterConfig;
import com.vega.main.ActivityPopupConfig;
import com.vega.main.AdMakerHomeFeedConfig;
import com.vega.main.AiPreloadSwitchConfig;
import com.vega.main.BusinessImageEditorRevamp;
import com.vega.main.BusinessImageEditorTemplateSwitch;
import com.vega.main.BusinessPhotoTemplateUnpackZipOpt;
import com.vega.main.CleanerForAndroidSystemConfig;
import com.vega.main.CommonABTestConfig;
import com.vega.main.EnableLazyLoadRetouchSdk;
import com.vega.main.EncryptABConfig;
import com.vega.main.FdCheckerConfig;
import com.vega.main.FontPanelUIABConfig;
import com.vega.main.FunctionTutorialCopywritingConfig;
import com.vega.main.HDImportLevelConfig;
import com.vega.main.HomepageBannerConfigEntity;
import com.vega.main.JavaThreadOomOptSettingABTest;
import com.vega.main.LibraFusedTestConfig;
import com.vega.main.LvCloudSubscribeEntry;
import com.vega.main.LvRecordTips;
import com.vega.main.MainTabLaunchABTest;
import com.vega.main.MainTabOrderABTest;
import com.vega.main.QuestionnaireAbTest;
import com.vega.main.QuestionnaireLimitConfig;
import com.vega.main.RetouchImageEnhanceRemoveBackgroundConfig;
import com.vega.main.ScriptReportConfig;
import com.vega.main.SettingsFusedTestConfig;
import com.vega.main.TabLandingConfig;
import com.vega.main.TabLandingOptimizationConfig;
import com.vega.main.UserResearchEntity;
import com.vega.main.VMSizeOptSetting;
import com.vega.main.VboostConfig;
import com.vega.shareapi.ClipBoardAccessConfig;
import com.vega.theme.config.ThemeSetting;
import com.vega.upload.UploadConfig;
import com.vega.ve.api.ImportImageConfig;
import com.vega.ve.api.VENewConfig;
import com.vega.webapi.AgreeReqCollectConfig;
import com.vega.webapi.HttpReqCollectConfig;
import com.vega.webapi.JSBridgeConfig;
import com.vega.webapi.SeclinkConfig;
import com.vega.webapi.WebViewUserAgentStringSetting;
import java.util.List;

@Settings(storageKey = "common_settings")
/* loaded from: classes8.dex */
public interface RemoteHostSettings extends ISettings {
    AbVersion getAbVersion();

    ActivityPopupConfig getActivityPopupConfig();

    AdMakerHomeFeedConfig getAdMakerHomeFeedConfig();

    AgreeReqCollectConfig getAgreeReqCollect();

    AiPreloadSwitchConfig getAiPreloadSwitchConfig();

    AlbumCloudMaterialAbTest getAlbumCloudMaterialAbTest();

    AlbumPerformanceABTest getAlbumPerformanceABTest();

    AlbumPreviewConfig getAlbumPreviewPreview();

    AppLogRegisterPollInterval getAppLogRegisterPollInterval();

    ApplogAdjustTerminateConfig getApplogAdjustTerminateConfig();

    BusinessImageEditorRevamp getBusinessImageEditorRevamp();

    BusinessImageEditorTemplateSwitch getBusinessImageEditorTemplateSwitch();

    BusinessPhotoTemplateUnpackZipOpt getBusinessPhotoTemplateUnpackZipOpt();

    CCPPEDomains getCcppeDomains();

    ClassPreloadABTest getClassPreloadABTest();

    CleanerForAndroidSystemConfig getCleanerForAndroidSystemConfig();

    ClipBoardAccessConfig getClipBoardAccessConfig();

    ColdLaunchOptConfig getColdLaunchOptConfig();

    CopyrightDistribute getCopyrightDistribute();

    CrossBorderDomainList getCrossBorderDomainList();

    DevelopPageConfig getDevelopPageConfig();

    DirectRequestAbTest getDirectRequestAbTest();

    DomainMonitorRate getDomainMonitorRate();

    EnableLazyLoadRetouchSdk getEnableLazyLoadRetouchSdk();

    EnablePlayerH265HwDecoder getEnablePlayerH265HwDecoder();

    EncryptABConfig getEndryptABConfig();

    EscapeDomainMonitorConfig getEscapeDomainMonitorConfig();

    FalconConfig getFalconConfig();

    FdCheckerConfig getFdCheckerConfig();

    FeatureSwitch getFeatureSwitch();

    FeedbackConfig getFeedbackConfig();

    FontPanelUIABConfig getFontPanelUIABConfig();

    FpsSamplingConfig getFpsSamplingConfig();

    FpsSceneSamplingConfig getFpsSceneSamplingConfig();

    FrameDropLevelConfig getFrameDropLevelConfig();

    FunctionTutorialCopywritingConfig getFunctionTutorialCopywritingConfig();

    GlideFixConfig getGlideFixConfig();

    GuideConfig getGuideSetting();

    HDImportLevelConfig getHdImportLevelConfig();

    HomepageBannerConfigEntity getHomepageBannerConfigEntity();

    HomepageBannerConfigEntity getHomepageBannerTtpConfigEntity();

    HttpReqCollectConfig getHttpReqCollect();

    ImportImageConfig getImportImageConfig();

    JavaThreadOomOptSettingABTest getJavaThreadOomOptSettingABTest();

    JSBridgeConfig getJsBridgeConfig();

    PushKeepAliveEntity getKeepLiveConfig();

    LibraFusedTestConfig getLibraFusedTestConfig();

    LooperProtectConfig getLooperProtectConfig();

    LvCloudSubscribeEntry getLvCloudSubscribeEntry();

    LvPlayerConfig getLvPlayerConfig();

    LvRecordTips getLvRecordTips();

    VMSizeOptSetting getLvvmSizeOptSetting();

    CommonABTestConfig getLynxAsyncLayoutAbTest();

    MainTabLaunchABTest getMainTabLaunchABTest();

    MainTabOrderABTest getMainTabOrderABTest();

    MaterialJumpPersonalPageAbTest getMaterialJumpPersonalPageAbTest();

    MaterialSearchV1Config getMaterialSearchV1Config();

    MaterialTagMapping getMaterialTagMapping();

    MediaFilterConfig getMediaFilterConfig();

    NetworkMonitorGetDomain getNetworkMonitorGetDomain();

    OptMediaPickerConfig getOptMediaPickerConfig();

    PipTrackLimitConfig getPipTrackLimitConfig();

    PseudonymDataCleanConfig getPseudonymDataCleanConfig();

    PseudonymMarkMonitorConfig getPseudonymMarkMonitorConfig();

    QuestionnaireAbTest getQuestionnaireAbTest();

    QuestionnaireLimitConfig getQuestionnaireLimitConfig();

    RegionConfig getRegionConfig();

    RetouchImageEnhanceRemoveBackgroundConfig getRetouchImageEnhanceRemoveBackgroundConfig();

    RetouchPictureEditorAbtest getRetouchPictureEditorAbtest();

    ScriptReportConfig getScriptReportConfig();

    SdcardCannotReadConfig getSdcardCannotReadConfig();

    SdcardDowngradeConfig getSdcardDowngradeConfig();

    SeclinkConfig getSeclinkConfig();

    SettingsFusedTestConfig getSettingsFusedTestConfig();

    SmartClientAbTest getSmartClientAbTest();

    SubServerRoomDomainList getSubServerRoomDomainList();

    TabLandingConfig getTabLandingConfig();

    TabLandingOptimizationConfig getTabLandingOptimizationConfig();

    ThemeSetting getThemeConfig();

    UploadConfig getUploadConfig();

    List<UserResearchEntity> getUserResearchEntity();

    VboostConfig getVboostConfig();

    VENewConfig getVeNewConfig();

    VerifyDataAbnormalProblemConfig getVerifyDataAbnormalProblemConfig();

    VideoPlayerABTest getVideoPlayerAbTest();

    VideoPlayerParameterConfig getVideoPlayerParameterConfig();

    WebViewCdnCutFlowConfig getWebViewCdnCutFlowConfig();

    WebViewCookieConfig getWebViewCookieConfig();

    WebViewHookConfig getWebViewHookConfig();

    WebViewUserAgentStringSetting getWebViewUserAgentStringSetting();

    WspCoreSettingsEntity getWspCoreSettingsEntity();
}
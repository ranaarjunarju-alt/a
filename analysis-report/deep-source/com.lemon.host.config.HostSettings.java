package com.lemon.host.config;

import com.bytedance.news.common.settings.IndividualManager;
import com.lemon.lv.libpush.PushKeepAliveEntity;
import com.vega.core.settings.obtain.SettingsObtainer;
import com.vega.feelgoodapi.config.FeedbackConfig;
import com.vega.feelgoodapi.settings.DevelopPageConfig;
import com.vega.gallery.api.AlbumPerformanceABTest;
import com.vega.gallery.api.AlbumPreviewConfig;
import com.vega.gallery.api.CopyrightDistribute;
import com.vega.gallery.api.DirectRequestAbTest;
import com.vega.gallery.api.GallerySettings;
import com.vega.gallery.api.MaterialJumpPersonalPageAbTest;
import com.vega.gallery.api.MaterialSearchFilterAbTest;
import com.vega.gallery.api.MaterialSearchV1Config;
import com.vega.gallery.api.OptMediaPickerConfig;
import com.vega.gallery.api.PipTrackLimitConfig;
import com.vega.gallery.api.RetouchPictureEditorAbtest;
import com.vega.gallery.api.SmartClientAbTest;
import com.vega.gallery.api.VideoPlayerABTest;
import com.vega.guideapi.GuideConfig;
import com.vega.libfiles.files.SdcardDowngradeConfig;
import com.vega.libgecko.config.FalconConfig;
import com.vega.libgecko.config.GeckoConfig;
import com.vega.libmedia.EnableConfigureVideoPlayer;
import com.vega.libmedia.EnablePlayerH264HwDecoder;
import com.vega.libmedia.LvPlayerConfig;
import com.vega.libmedia.PlayerParams;
import com.vega.libmedia.VideoPlayerBufferingTimeConfig;
import com.vega.libmedia.VideoPlayerParameterConfig;
import com.vega.main.AdMakerHomeFeedConfig;
import com.vega.main.AiPreloadSwitchConfig;
import com.vega.main.BusinessImageEditorTemplateSwitch;
import com.vega.main.BusinessPhotoTemplateUnpackZipOpt;
import com.vega.main.CleanerForAndroidSystemConfig;
import com.vega.main.CommonABTestConfig;
import com.vega.main.EncryptABConfig;
import com.vega.main.FeedbackWithLogIdConfig;
import com.vega.main.FunctionTutorialCopywritingConfig;
import com.vega.main.HDImportLevelConfig;
import com.vega.main.HomepageBannerConfigEntity;
import com.vega.main.JavaThreadOomOptSettingABTest;
import com.vega.main.LibraFusedTestConfig;
import com.vega.main.MainSettings;
import com.vega.main.QuestionnaireLimitConfig;
import com.vega.main.RetouchImageEnhanceRemoveBackgroundConfig;
import com.vega.main.SettingsFusedTestConfig;
import com.vega.main.TabLandingConfig;
import com.vega.main.UserResearchEntity;
import com.vega.upload.UploadConfig;
import com.vega.upload.UploadConfigProvider;
import com.vega.ve.api.ImportImageConfig;
import com.vega.ve.api.VENewConfig;
import com.vega.ve.api.VESettings;
import com.vega.webapi.AgreeReqCollectConfig;
import com.vega.webapi.IWebConfig;
import com.vega.webapi.JSBridgeConfig;
import com.vega.webapi.SeclinkConfig;
import com.vega.webapi.WebViewUserAgentStringSetting;
import com.vungle.ads.internal.protos.Sdk;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes11.dex */
public final class HostSettings implements MainSettings, IWebConfig, ConfigProvider, UploadConfigProvider, GallerySettings, VESettings, GeckoConfig {

    /* renamed from: a, reason: collision with root package name */
    public final Lazy f58769a = LazyKt__LazyJVMKt.lazy(new Function0<IndividualRemoteHostSettings>() { // from class: com.lemon.host.config.HostSettings$individualSettings$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r1v1, types: [com.lemon.host.config.IndividualRemoteHostSettings, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final IndividualRemoteHostSettings invoke() {
            ?? B = IndividualManager.c("capcut_ops_config").b(IndividualRemoteHostSettings.class);
            Intrinsics.checkNotNullExpressionValue(B, "");
            return B;
        }
    });

    public static RemoteHostSettings k() {
        SettingsObtainer.f79519a.getClass();
        return (RemoteHostSettings) SettingsObtainer.d(RemoteHostSettings.class);
    }

    @Override // com.vega.main.MainSettings
    public final EncryptABConfig a() {
        return k().getEndryptABConfig();
    }

    @Override // com.vega.webapi.IWebConfig
    public final JSBridgeConfig b() {
        return k().getJsBridgeConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final GuideConfig c() {
        return k().getGuideSetting();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final AlbumPreviewConfig d() {
        return k().getAlbumPreviewPreview();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vega.gallery.api.GallerySettings
    public final MaterialSearchFilterAbTest e() {
        return new MaterialSearchFilterAbTest(null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 7, 0 == true ? 1 : 0);
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final FrameDropLevelConfig f() {
        return k().getFrameDropLevelConfig();
    }

    @Override // com.vega.webapi.IWebConfig
    public final AgreeReqCollectConfig g() {
        return k().getAgreeReqCollect();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final AbVersion getAbVersion() {
        return k().getAbVersion();
    }

    @Override // com.vega.main.MainSettings
    public final AdMakerHomeFeedConfig getAdMakerHomeFeedConfig() {
        return k().getAdMakerHomeFeedConfig();
    }

    @Override // com.vega.main.MainSettings
    public final AiPreloadSwitchConfig getAiPreloadSwitchConfig() {
        return k().getAiPreloadSwitchConfig();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final AlbumPerformanceABTest getAlbumPerformanceABTest() {
        return k().getAlbumPerformanceABTest();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final AppLogRegisterPollInterval getAppLogRegisterPollInterval() {
        return k().getAppLogRegisterPollInterval();
    }

    @Override // com.vega.main.MainSettings
    public final BusinessImageEditorTemplateSwitch getBusinessImageEditorTemplateSwitch() {
        return k().getBusinessImageEditorTemplateSwitch();
    }

    @Override // com.vega.main.MainSettings
    public final BusinessPhotoTemplateUnpackZipOpt getBusinessPhotoTemplateUnpackZipOpt() {
        return k().getBusinessPhotoTemplateUnpackZipOpt();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final ClassPreloadABTest getClassPreloadABTest() {
        return k().getClassPreloadABTest();
    }

    @Override // com.vega.main.MainSettings
    public final CleanerForAndroidSystemConfig getCleanerForAndroidSystemConfig() {
        return k().getCleanerForAndroidSystemConfig();
    }

    @Override // com.vega.upload.UploadConfigProvider
    public final UploadConfig getConfig() {
        return k().getUploadConfig();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final CopyrightDistribute getCopyrightDistribute() {
        return k().getCopyrightDistribute();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final CrossBorderDomainList getCrossBorderDomainList() {
        return k().getCrossBorderDomainList();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final DirectRequestAbTest getDirectRequestAbTest() {
        return k().getDirectRequestAbTest();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final DomainMonitorRate getDomainMonitorRate() {
        return k().getDomainMonitorRate();
    }

    @Override // com.vega.libgecko.config.GeckoConfig
    public final FalconConfig getFalconConfig() {
        return k().getFalconConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final FeedbackConfig getFeedbackConfig() {
        return k().getFeedbackConfig();
    }

    @Override // com.vega.main.MainSettings
    public final FeedbackWithLogIdConfig getFeedbackWithLogIdConfig() {
        return ((IndividualRemoteHostSettings) this.f58769a.getValue()).getFeedbackWithLogIdConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final FpsSamplingConfig getFpsSamplingConfig() {
        return k().getFpsSamplingConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final FpsSceneSamplingConfig getFpsSceneSamplingConfig() {
        return k().getFpsSceneSamplingConfig();
    }

    @Override // com.vega.main.MainSettings
    public final FunctionTutorialCopywritingConfig getFunctionTutorialCopywritingConfig() {
        return k().getFunctionTutorialCopywritingConfig();
    }

    @Override // com.vega.main.MainSettings
    public final HDImportLevelConfig getHdImportLevelConfig() {
        return k().getHdImportLevelConfig();
    }

    @Override // com.vega.main.MainSettings
    public final HomepageBannerConfigEntity getHomepageBannerConfigEntity() {
        return k().getHomepageBannerConfigEntity();
    }

    @Override // com.vega.main.MainSettings
    public final HomepageBannerConfigEntity getHomepageBannerTtpConfigEntity() {
        return k().getHomepageBannerTtpConfigEntity();
    }

    @Override // com.vega.ve.api.VESettings
    public final ImportImageConfig getImportImageConfig() {
        return k().getImportImageConfig();
    }

    @Override // com.lemon.lv.libpush.PushKeepAliveConfigProvider
    public final PushKeepAliveEntity getKeepLiveConfig() {
        return k().getKeepLiveConfig();
    }

    @Override // com.vega.main.MainSettings
    public final LibraFusedTestConfig getLibraFusedTestConfig() {
        return k().getLibraFusedTestConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final LooperProtectConfig getLooperProtectConfig() {
        return k().getLooperProtectConfig();
    }

    @Override // com.vega.main.MainSettings
    public final CommonABTestConfig getLynxAsyncLayoutAbTest() {
        return k().getLynxAsyncLayoutAbTest();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final MaterialJumpPersonalPageAbTest getMaterialJumpPersonalPageAbTest() {
        return k().getMaterialJumpPersonalPageAbTest();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final MaterialSearchV1Config getMaterialSearchV1Config() {
        return k().getMaterialSearchV1Config();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final MaterialTagMapping getMaterialTagMapping() {
        return k().getMaterialTagMapping();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final OptMediaPickerConfig getOptMediaPickerConfig() {
        return k().getOptMediaPickerConfig();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final PipTrackLimitConfig getPipTrackLimitConfig() {
        return k().getPipTrackLimitConfig();
    }

    @Override // com.vega.main.MainSettings
    public final QuestionnaireLimitConfig getQuestionnaireLimitConfig() {
        return k().getQuestionnaireLimitConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final RegionConfig getRegionConfig() {
        return k().getRegionConfig();
    }

    @Override // com.vega.main.MainSettings
    public final RetouchImageEnhanceRemoveBackgroundConfig getRetouchImageEnhanceRemoveBackgroundConfig() {
        return k().getRetouchImageEnhanceRemoveBackgroundConfig();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final RetouchPictureEditorAbtest getRetouchPictureEditorAbtest() {
        return k().getRetouchPictureEditorAbtest();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final SdcardCannotReadConfig getSdcardCannotReadConfig() {
        return k().getSdcardCannotReadConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final SdcardDowngradeConfig getSdcardDowngradeConfig() {
        return k().getSdcardDowngradeConfig();
    }

    @Override // com.vega.webapi.IWebConfig
    public final SeclinkConfig getSeclinkConfig() {
        return k().getSeclinkConfig();
    }

    @Override // com.vega.main.MainSettings
    public final SettingsFusedTestConfig getSettingsFusedTestConfig() {
        return k().getSettingsFusedTestConfig();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final SmartClientAbTest getSmartClientAbTest() {
        return k().getSmartClientAbTest();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final SubServerRoomDomainList getSubServerRoomDomainList() {
        return k().getSubServerRoomDomainList();
    }

    @Override // com.vega.main.MainSettings
    public final TabLandingConfig getTabLandingConfig() {
        return k().getTabLandingConfig();
    }

    @Override // com.vega.main.MainSettings
    public final List<UserResearchEntity> getUserResearchEntity() {
        return k().getUserResearchEntity();
    }

    @Override // com.vega.ve.api.VESettings
    public final VENewConfig getVeNewConfig() {
        return k().getVeNewConfig();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final VerifyDataAbnormalProblemConfig getVerifyDataAbnormalProblemConfig() {
        return k().getVerifyDataAbnormalProblemConfig();
    }

    @Override // com.vega.gallery.api.GallerySettings
    public final VideoPlayerABTest getVideoPlayerAbTest() {
        return k().getVideoPlayerAbTest();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final WebViewCookieConfig getWebViewCookieConfig() {
        return k().getWebViewCookieConfig();
    }

    @Override // com.vega.webapi.IWebConfig
    public final WebViewUserAgentStringSetting getWebViewUserAgentStringSetting() {
        return k().getWebViewUserAgentStringSetting();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final WspCoreSettingsEntity getWspCoreSettingsEntity() {
        return k().getWspCoreSettingsEntity();
    }

    @Override // com.vega.main.MainSettings
    public final JavaThreadOomOptSettingABTest h() {
        return k().getJavaThreadOomOptSettingABTest();
    }

    @Override // com.lemon.host.config.ConfigProvider
    public final DevelopPageConfig i() {
        return k().getDevelopPageConfig();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r5v8, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lemon.host.config.ConfigProvider
    public final PlayerParams j() {
        RemoteHostSettings remoteHostSettingsK = k();
        LvPlayerConfig lvPlayerConfig = remoteHostSettingsK.getLvPlayerConfig();
        VideoPlayerParameterConfig videoPlayerParameterConfig = remoteHostSettingsK.getVideoPlayerParameterConfig();
        int i = Sdk.SDKError.Reason.ASSET_FAILED_MAX_SPACE_EXCEEDED_VALUE;
        DefaultConstructorMarker defaultConstructorMarker = null;
        return new PlayerParams(lvPlayerConfig, videoPlayerParameterConfig, new VideoPlayerBufferingTimeConfig(false, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0.0f, 0 == true ? 1 : 0, 0 == true ? 1 : 0, i, defaultConstructorMarker), new EnableConfigureVideoPlayer(0 == true ? 1 : 0, 1, defaultConstructorMarker), new EnablePlayerH264HwDecoder(0 == true ? 1 : 0, 0 == true ? 1 : 0, 3, defaultConstructorMarker), remoteHostSettingsK.getEnablePlayerH265HwDecoder());
    }
}
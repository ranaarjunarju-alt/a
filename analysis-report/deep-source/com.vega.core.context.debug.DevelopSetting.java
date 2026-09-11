package com.vega.core.context.debug;

import java.util.List;
import java.util.Map;

/* loaded from: classes16.dex */
public interface DevelopSetting {

    /* loaded from: classes39.dex */
    public static final class DefaultImpls {
    }

    String abGroup();

    boolean anyWhereDoor();

    boolean autoDownloadDraftTest();

    String boeSuffix();

    List<String> boeWhiteHosts();

    boolean effectDebugChannel();

    boolean effectModuleDebugChannel();

    boolean enableCameraEffectSDK();

    boolean enableCvFloatingWindow();

    boolean enableDevEntrance();

    boolean enableMockNewUserQuestion();

    boolean etEnable();

    boolean exportDraft();

    boolean geckoDebug();

    Map<String, String> geckoHeaders();

    boolean getForbidDisableScreenRecord();

    String getHostChannel();

    String getMockNewUserQstLibGroup();

    String getPitayaRemoteUrl();

    boolean hideGIF();

    APIHost host();

    boolean importDraft();

    boolean inPPEReviewEnv();

    boolean isAlbumToVideoDebug();

    boolean isAutoTest();

    boolean isBuildRheaPro();

    boolean isDisableDraftEncryptAll();

    boolean isDisableDraftExportEncrypt();

    boolean isHyperTest();

    boolean isInnerChannel();

    boolean isIntegratedBuild();

    boolean isOutBuild();

    boolean isPluginEntryOpen();

    boolean isSmartClientDebug();

    boolean isTestChannel();

    boolean lynxDebug();

    boolean notVipExportEnable();

    boolean openBOE();

    boolean openPPEEnv();

    boolean printLog();

    boolean reportToDebugEnv();

    void setCameraBootOpt(boolean z);

    void setForbidDisableScreenRecord(boolean z);

    void setGetFrame3(boolean z);

    void setPluginEntryOpen(boolean z);

    void showDeveloperLog(CharSequence charSequence);

    boolean useCameraBootOpt();

    boolean useGetFrame3();

    Map<String, String> userHeaders();

    String veAndEffectLogLevel();

    String webDebugToolURL();

    boolean webViewHock();
}
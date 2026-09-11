package com.vega.core.app;

import android.content.Context;

/* loaded from: classes33.dex */
public interface AppContext {

    /* loaded from: classes23.dex */
    public static final class DefaultImpls {
    }

    String getAbClient();

    String getAbFeature();

    long getAbFlag();

    String getAbGroup();

    String getAbVersion();

    int getAid();

    String getApiHost();

    String getAppName();

    String getChannel();

    Context getContext();

    boolean getDebug();

    String getDeviceId();

    String getFeedbackAppKey();

    String getInstallId();

    String getManifestVersion();

    int getManifestVersionCode();

    String getPreinstallChannel();

    String getStringAppName();

    String getTweakedChannel();

    int getUpdateVersionCode();

    String getVersion();

    int getVersionCode();

    boolean isGooglePlayPackage();
}
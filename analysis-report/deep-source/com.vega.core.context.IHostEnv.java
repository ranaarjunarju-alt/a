package com.vega.core.context;

import android.app.Application;
import com.vega.core.app.AppContext;
import com.vega.core.context.debug.DevelopSetting;
import com.vega.core.data.LaunchInfo;

/* loaded from: classes25.dex */
public interface IHostEnv {
    Application app();

    AppContext appContext();

    AppProperty appProperty();

    DevelopSetting developSettings();

    com.vega.corex.context.DeviceInfo deviceInfo();

    LaunchInfo launchInfo();
}
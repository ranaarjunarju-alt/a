package com.lemon.host.config;

import com.bytedance.news.common.settings.api.annotation.ISettings;
import com.bytedance.news.common.settings.api.annotation.Settings;
import com.vega.main.FeedbackWithLogIdConfig;

@Settings(settingsId = "capcut_ops_config", storageKey = "ops_config")
/* loaded from: classes40.dex */
public interface IndividualRemoteHostSettings extends ISettings {
    FeedbackWithLogIdConfig getFeedbackWithLogIdConfig();
}
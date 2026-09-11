package com.vega.audio.tone.tts.config;

import com.bytedance.news.common.settings.api.annotation.Settings;
import com.vega.config.IConfigSetting;

@Settings(storageKey = "common_settings")
/* loaded from: classes21.dex */
public interface TtsV3ModelToneConfigSetting extends IConfigSetting<TtsV3ModelToneConfig> {
    @Override // com.vega.config.IConfigSetting
    TtsV3ModelToneConfig getConfig();
}
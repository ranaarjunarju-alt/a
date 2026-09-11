package com.vega.audio.tone.tts.config;

import com.bytedance.news.common.settings.api.annotation.Settings;
import com.vega.config.IConfigSetting;

@Settings(storageKey = "common_settings")
/* loaded from: classes6.dex */
public interface TtsQwenSavingStreamingConfigSettings extends IConfigSetting<TtsQwenSavingStreamingConfig> {
    @Override // com.vega.config.IConfigSetting
    TtsQwenSavingStreamingConfig getConfig();
}
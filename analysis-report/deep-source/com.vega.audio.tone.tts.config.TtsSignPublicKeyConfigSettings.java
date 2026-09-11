package com.vega.audio.tone.tts.config;

import com.bytedance.news.common.settings.api.annotation.Settings;
import com.vega.config.IConfigSetting;

@Settings(storageKey = "common_settings")
/* loaded from: classes24.dex */
public interface TtsSignPublicKeyConfigSettings extends IConfigSetting<TtsSignPublicKeyConfig> {
    @Override // com.vega.config.IConfigSetting
    TtsSignPublicKeyConfig getConfig();
}
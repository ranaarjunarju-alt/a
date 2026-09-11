package com.vega.audio.tone.tts.util;

import com.bytedance.news.common.settings.api.annotation.Settings;
import com.vega.config.IConfigSetting;

@Settings(storageKey = "common_settings")
/* loaded from: classes15.dex */
public interface TTSEngineConfigSettings extends IConfigSetting<TTSEngineConfig> {
    @Override // com.vega.config.IConfigSetting
    TTSEngineConfig getConfig();
}
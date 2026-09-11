package com.vega.audio.tone.tts.config;

import com.google.gson.annotations.SerializedName;
import com.vega.config.IConfig;
import com.vega.config.IConfigInterceptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes16.dex */
public final class TtsV3ModelToneConfig implements IConfig<TtsV3ModelToneConfig> {

    @SerializedName("cc_tone_v3_streaming")
    public final boolean enableV3ModelTone;

    public TtsV3ModelToneConfig() {
        this(false, 1, null);
    }

    public TtsV3ModelToneConfig(boolean z) {
        this.enableV3ModelTone = z;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0005: CONSTRUCTOR 
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0000: ARITH (r3v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? false : (r2v0 boolean))
     A[MD:(boolean):void (m)] call: com.vega.audio.tone.tts.config.TtsV3ModelToneConfig.<init>(boolean):void type: THIS */
    public /* synthetic */ TtsV3ModelToneConfig(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean a() {
        return this.enableV3ModelTone;
    }

    @Override // com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider
    public final Object create() {
        return IConfig.DefaultImpls.a(this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TtsV3ModelToneConfig) && this.enableV3ModelTone == ((TtsV3ModelToneConfig) obj).enableV3ModelTone;
    }

    public final int hashCode() {
        return this.enableV3ModelTone ? 1231 : 1237;
    }

    @Override // com.vega.config.IConfig
    public final IConfigInterceptor interceptor() {
        return null;
    }

    public final String toString() {
        return "TtsV3ModelToneConfig(enableV3ModelTone=" + this.enableV3ModelTone + ')';
    }
}
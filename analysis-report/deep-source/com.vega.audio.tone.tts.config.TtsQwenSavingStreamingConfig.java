package com.vega.audio.tone.tts.config;

import com.google.gson.annotations.SerializedName;
import com.vega.config.IConfig;
import com.vega.config.IConfigInterceptor;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes30.dex */
public final class TtsQwenSavingStreamingConfig implements IConfig<TtsQwenSavingStreamingConfig> {

    @SerializedName("stream_text_to_speech_listen")
    public final boolean streamTextToSpeechListen;

    public TtsQwenSavingStreamingConfig() {
        this(false, 1, null);
    }

    public TtsQwenSavingStreamingConfig(boolean z) {
        this.streamTextToSpeechListen = z;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0005: CONSTRUCTOR 
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0000: ARITH (r3v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? false : (r2v0 boolean))
     A[MD:(boolean):void (m)] call: com.vega.audio.tone.tts.config.TtsQwenSavingStreamingConfig.<init>(boolean):void type: THIS */
    public /* synthetic */ TtsQwenSavingStreamingConfig(boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z);
    }

    public final boolean a() {
        return this.streamTextToSpeechListen;
    }

    @Override // com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider
    public final Object create() {
        return IConfig.DefaultImpls.a(this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TtsQwenSavingStreamingConfig) && this.streamTextToSpeechListen == ((TtsQwenSavingStreamingConfig) obj).streamTextToSpeechListen;
    }

    public final int hashCode() {
        return this.streamTextToSpeechListen ? 1231 : 1237;
    }

    @Override // com.vega.config.IConfig
    public final IConfigInterceptor interceptor() {
        return null;
    }

    public final String toString() {
        return "TtsQwenSavingStreamingConfig(streamTextToSpeechListen=" + this.streamTextToSpeechListen + ')';
    }
}
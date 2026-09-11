package com.vega.audio.tone.tts.util;

import com.google.gson.annotations.SerializedName;
import com.vega.config.IConfig;
import com.vega.config.IConfigInterceptor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes30.dex */
public final class TTSEngineConfig implements IConfig<TTSEngineConfig> {

    @SerializedName("elevenlabs")
    public final TTSEngineItemConfig eleventLabs;

    @SerializedName("microsoft")
    public final TTSEngineItemConfig microsoft;

    @SerializedName("ssml")
    public final TTSEngineItemConfig ssml;

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public TTSEngineConfig() {
        this(null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 7, 0 == true ? 1 : 0);
    }

    public TTSEngineConfig(TTSEngineItemConfig tTSEngineItemConfig, TTSEngineItemConfig tTSEngineItemConfig2, TTSEngineItemConfig tTSEngineItemConfig3) {
        this.microsoft = tTSEngineItemConfig;
        this.eleventLabs = tTSEngineItemConfig2;
        this.ssml = tTSEngineItemConfig3;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0010: CONSTRUCTOR 
      (wrap:com.vega.audio.tone.tts.util.TTSEngineItemConfig:?: TERNARY null = ((wrap:int:0x0000: ARITH (r6v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.tts.util.TTSEngineItemConfig) : (r3v0 com.vega.audio.tone.tts.util.TTSEngineItemConfig))
      (wrap:com.vega.audio.tone.tts.util.TTSEngineItemConfig:?: TERNARY null = ((wrap:int:0x0006: ARITH (r6v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.tts.util.TTSEngineItemConfig) : (r4v0 com.vega.audio.tone.tts.util.TTSEngineItemConfig))
      (wrap:com.vega.audio.tone.tts.util.TTSEngineItemConfig:?: TERNARY null = ((wrap:int:0x000b: ARITH (r6v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.tts.util.TTSEngineItemConfig) : (r5v0 com.vega.audio.tone.tts.util.TTSEngineItemConfig))
     A[MD:(com.vega.audio.tone.tts.util.TTSEngineItemConfig, com.vega.audio.tone.tts.util.TTSEngineItemConfig, com.vega.audio.tone.tts.util.TTSEngineItemConfig):void (m)] call: com.vega.audio.tone.tts.util.TTSEngineConfig.<init>(com.vega.audio.tone.tts.util.TTSEngineItemConfig, com.vega.audio.tone.tts.util.TTSEngineItemConfig, com.vega.audio.tone.tts.util.TTSEngineItemConfig):void type: THIS */
    public /* synthetic */ TTSEngineConfig(TTSEngineItemConfig tTSEngineItemConfig, TTSEngineItemConfig tTSEngineItemConfig2, TTSEngineItemConfig tTSEngineItemConfig3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : tTSEngineItemConfig, (i & 2) != 0 ? null : tTSEngineItemConfig2, (i & 4) != 0 ? null : tTSEngineItemConfig3);
    }

    public static /* synthetic */ TTSEngineConfig copy$default(TTSEngineConfig tTSEngineConfig, TTSEngineItemConfig tTSEngineItemConfig, TTSEngineItemConfig tTSEngineItemConfig2, TTSEngineItemConfig tTSEngineItemConfig3, int i, Object obj) {
        if ((i & 1) != 0) {
            tTSEngineItemConfig = tTSEngineConfig.microsoft;
        }
        if ((i & 2) != 0) {
            tTSEngineItemConfig2 = tTSEngineConfig.eleventLabs;
        }
        if ((i & 4) != 0) {
            tTSEngineItemConfig3 = tTSEngineConfig.ssml;
        }
        return tTSEngineConfig.copy(tTSEngineItemConfig, tTSEngineItemConfig2, tTSEngineItemConfig3);
    }

    public final TTSEngineConfig copy(TTSEngineItemConfig tTSEngineItemConfig, TTSEngineItemConfig tTSEngineItemConfig2, TTSEngineItemConfig tTSEngineItemConfig3) {
        return new TTSEngineConfig(tTSEngineItemConfig, tTSEngineItemConfig2, tTSEngineItemConfig3);
    }

    /* JADX DEBUG: Method merged with bridge method: create()Ljava/lang/Object; */
    @Override // com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider
    public TTSEngineConfig create() {
        return (TTSEngineConfig) IConfig.DefaultImpls.a(this);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TTSEngineConfig)) {
            return false;
        }
        TTSEngineConfig tTSEngineConfig = (TTSEngineConfig) obj;
        return Intrinsics.areEqual(this.microsoft, tTSEngineConfig.microsoft) && Intrinsics.areEqual(this.eleventLabs, tTSEngineConfig.eleventLabs) && Intrinsics.areEqual(this.ssml, tTSEngineConfig.ssml);
    }

    public final TTSEngineItemConfig getEleventLabs() {
        return this.eleventLabs;
    }

    public final TTSEngineItemConfig getMicrosoft() {
        return this.microsoft;
    }

    public final TTSEngineItemConfig getSsml() {
        return this.ssml;
    }

    public int hashCode() {
        TTSEngineItemConfig tTSEngineItemConfig = this.microsoft;
        int iHashCode = (tTSEngineItemConfig == null ? 0 : tTSEngineItemConfig.hashCode()) * 31;
        TTSEngineItemConfig tTSEngineItemConfig2 = this.eleventLabs;
        int iHashCode2 = (iHashCode + (tTSEngineItemConfig2 == null ? 0 : tTSEngineItemConfig2.hashCode())) * 31;
        TTSEngineItemConfig tTSEngineItemConfig3 = this.ssml;
        return iHashCode2 + (tTSEngineItemConfig3 != null ? tTSEngineItemConfig3.hashCode() : 0);
    }

    @Override // com.vega.config.IConfig
    public IConfigInterceptor interceptor() {
        return null;
    }

    public String toString() {
        return "TTSEngineConfig(microsoft=" + this.microsoft + ", eleventLabs=" + this.eleventLabs + ", ssml=" + this.ssml + ')';
    }
}
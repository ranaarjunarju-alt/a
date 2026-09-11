package com.vega.audio.tone.tts.engine.server;

import com.google.gson.annotations.SerializedName;
import com.vungle.ads.internal.protos.Sdk;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes3.dex */
public final class RemoteSAMIRequestAudioConfig {

    @SerializedName("emotion")
    public final String emotion;

    @SerializedName("emotion_scale")
    public final double emotionScale;

    @SerializedName("enable_timestamp")
    public final boolean enableTimestamp;

    @SerializedName("format")
    public final String format;

    @SerializedName("pitch_rate")
    public final int pitchRate;

    @SerializedName("sample_rate")
    public final int sampleRate;

    @SerializedName("speech_rate")
    public final float speechRate;

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public RemoteSAMIRequestAudioConfig() {
        int i = Sdk.SDKError.Reason.ASSET_FAILED_MAX_SPACE_EXCEEDED_VALUE;
        Object[] objArr = 0 == true ? 1 : 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        this(null, 0, 0.0f, 0 == true ? 1 : 0, 0 == true ? 1 : 0, objArr, 0.0d, i, objArr2);
    }

    public RemoteSAMIRequestAudioConfig(String str, int i, float f, int i2, boolean z, String str2, double d2) {
        Intrinsics.checkNotNullParameter(str2, "");
        this.format = str;
        this.sampleRate = i;
        this.speechRate = f;
        this.pitchRate = i2;
        this.enableTimestamp = z;
        this.emotion = str2;
        this.emotionScale = d2;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x002f: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0007: ARITH (r18v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r10v0 java.lang.String))
      (wrap:int:?: TERNARY null = ((wrap:int:0x000c: ARITH (r18v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (24000 int) : (r11v0 int))
      (wrap:float:?: TERNARY null = ((wrap:int:0x0012: ARITH (r18v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (1.0f float) : (r12v0 float))
      (wrap:int:?: TERNARY null = ((wrap:int:0x0018: ARITH (r18v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r13v0 int))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x001e: ARITH (r18v0 int) & (16 int) A[WRAPPED]) == (0 int)) ? (r14v0 boolean) : false)
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0022: ARITH (r18v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? ("") : (r15v0 java.lang.String))
      (wrap:double:?: TERNARY null = ((wrap:int:0x0028: ARITH (r18v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? (0.0d double) : (r16v0 double))
     A[MD:(java.lang.String, int, float, int, boolean, java.lang.String, double):void (m)] call: com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig.<init>(java.lang.String, int, float, int, boolean, java.lang.String, double):void type: THIS */
    public /* synthetic */ RemoteSAMIRequestAudioConfig(String str, int i, float f, int i2, boolean z, String str2, double d2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? 24000 : i, (i3 & 4) != 0 ? 1.0f : f, (i3 & 8) != 0 ? 0 : i2, (i3 & 16) == 0 ? z : false, (i3 & 32) != 0 ? "" : str2, (i3 & 64) != 0 ? 0.0d : d2);
    }

    public static /* synthetic */ RemoteSAMIRequestAudioConfig copy$default(RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig, String str, int i, float f, int i2, boolean z, String str2, double d2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = remoteSAMIRequestAudioConfig.format;
        }
        if ((i3 & 2) != 0) {
            i = remoteSAMIRequestAudioConfig.sampleRate;
        }
        if ((i3 & 4) != 0) {
            f = remoteSAMIRequestAudioConfig.speechRate;
        }
        if ((i3 & 8) != 0) {
            i2 = remoteSAMIRequestAudioConfig.pitchRate;
        }
        if ((i3 & 16) != 0) {
            z = remoteSAMIRequestAudioConfig.enableTimestamp;
        }
        if ((i3 & 32) != 0) {
            str2 = remoteSAMIRequestAudioConfig.emotion;
        }
        if ((i3 & 64) != 0) {
            d2 = remoteSAMIRequestAudioConfig.emotionScale;
        }
        return remoteSAMIRequestAudioConfig.copy(str, i, f, i2, z, str2, d2);
    }

    public final RemoteSAMIRequestAudioConfig copy(String str, int i, float f, int i2, boolean z, String str2, double d2) {
        Intrinsics.checkNotNullParameter(str2, "");
        return new RemoteSAMIRequestAudioConfig(str, i, f, i2, z, str2, d2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RemoteSAMIRequestAudioConfig)) {
            return false;
        }
        RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig = (RemoteSAMIRequestAudioConfig) obj;
        return Intrinsics.areEqual(this.format, remoteSAMIRequestAudioConfig.format) && this.sampleRate == remoteSAMIRequestAudioConfig.sampleRate && Float.compare(this.speechRate, remoteSAMIRequestAudioConfig.speechRate) == 0 && this.pitchRate == remoteSAMIRequestAudioConfig.pitchRate && this.enableTimestamp == remoteSAMIRequestAudioConfig.enableTimestamp && Intrinsics.areEqual(this.emotion, remoteSAMIRequestAudioConfig.emotion) && Double.compare(this.emotionScale, remoteSAMIRequestAudioConfig.emotionScale) == 0;
    }

    public final String getEmotion() {
        return this.emotion;
    }

    public final double getEmotionScale() {
        return this.emotionScale;
    }

    public final boolean getEnableTimestamp() {
        return this.enableTimestamp;
    }

    public final String getFormat() {
        return this.format;
    }

    public final int getPitchRate() {
        return this.pitchRate;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public final float getSpeechRate() {
        return this.speechRate;
    }

    public int hashCode() {
        String str = this.format;
        int iHashCode = (((((((((((str == null ? 0 : str.hashCode()) * 31) + this.sampleRate) * 31) + Float.floatToIntBits(this.speechRate)) * 31) + this.pitchRate) * 31) + (this.enableTimestamp ? 1231 : 1237)) * 31) + this.emotion.hashCode()) * 31;
        long jDoubleToLongBits = Double.doubleToLongBits(this.emotionScale);
        return iHashCode + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)));
    }

    public String toString() {
        return "RemoteSAMIRequestAudioConfig(format=" + this.format + ", sampleRate=" + this.sampleRate + ", speechRate=" + this.speechRate + ", pitchRate=" + this.pitchRate + ", enableTimestamp=" + this.enableTimestamp + ", emotion=" + this.emotion + ", emotionScale=" + this.emotionScale + ')';
    }
}
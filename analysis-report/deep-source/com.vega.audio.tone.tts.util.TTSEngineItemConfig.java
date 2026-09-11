package com.vega.audio.tone.tts.util;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes25.dex */
public final class TTSEngineItemConfig {

    @SerializedName("count")
    public final long count;

    @SerializedName("enable_increasing")
    public final boolean enableIncreasing;

    @SerializedName("increasing_rate")
    public final double increasingRate;

    @SerializedName("interval")
    public final long interval;

    /* JADX WARN: Illegal instructions before constructor call */
    public TTSEngineItemConfig() {
        long j = 0;
        this(j, j, 0.0d, false, 15, null);
    }

    public TTSEngineItemConfig(long j, long j2, double d2, boolean z) {
        this.interval = j;
        this.count = j2;
        this.increasingRate = d2;
        this.enableIncreasing = z;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0017: CONSTRUCTOR 
      (wrap:long:?: TERNARY null = ((wrap:int:0x0000: ARITH (r9v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (1000 long) : (r2v0 long))
      (wrap:long:?: TERNARY null = ((wrap:int:0x0006: ARITH (r9v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (20 long) : (r4v0 long))
      (wrap:double:?: TERNARY null = ((wrap:int:0x000c: ARITH (r9v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (1.0d double) : (r6v0 double))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0012: ARITH (r9v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? false : (r8v0 boolean))
     A[MD:(long, long, double, boolean):void (m)] call: com.vega.audio.tone.tts.util.TTSEngineItemConfig.<init>(long, long, double, boolean):void type: THIS */
    public /* synthetic */ TTSEngineItemConfig(long j, long j2, double d2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? 1000L : j, (i & 2) != 0 ? 20L : j2, (i & 4) != 0 ? 1.0d : d2, (i & 8) != 0 ? false : z);
    }

    public static /* synthetic */ TTSEngineItemConfig copy$default(TTSEngineItemConfig tTSEngineItemConfig, long j, long j2, double d2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            j = tTSEngineItemConfig.interval;
        }
        if ((i & 2) != 0) {
            j2 = tTSEngineItemConfig.count;
        }
        if ((i & 4) != 0) {
            d2 = tTSEngineItemConfig.increasingRate;
        }
        if ((i & 8) != 0) {
            z = tTSEngineItemConfig.enableIncreasing;
        }
        return tTSEngineItemConfig.copy(j, j2, d2, z);
    }

    public final TTSEngineItemConfig copy(long j, long j2, double d2, boolean z) {
        return new TTSEngineItemConfig(j, j2, d2, z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TTSEngineItemConfig)) {
            return false;
        }
        TTSEngineItemConfig tTSEngineItemConfig = (TTSEngineItemConfig) obj;
        return this.interval == tTSEngineItemConfig.interval && this.count == tTSEngineItemConfig.count && Double.compare(this.increasingRate, tTSEngineItemConfig.increasingRate) == 0 && this.enableIncreasing == tTSEngineItemConfig.enableIncreasing;
    }

    public final long getCount() {
        return this.count;
    }

    public final boolean getEnableIncreasing() {
        return this.enableIncreasing;
    }

    public final double getIncreasingRate() {
        return this.increasingRate;
    }

    public final long getInterval() {
        return this.interval;
    }

    public int hashCode() {
        long j = this.interval;
        int i = ((int) (j ^ (j >>> 32))) * 31;
        long j2 = this.count;
        long jDoubleToLongBits = Double.doubleToLongBits(this.increasingRate);
        return ((((i + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) ((jDoubleToLongBits >>> 32) ^ jDoubleToLongBits))) * 31) + (this.enableIncreasing ? 1231 : 1237);
    }

    public String toString() {
        return "TTSEngineItemConfig(interval=" + this.interval + ", count=" + this.count + ", increasingRate=" + this.increasingRate + ", enableIncreasing=" + this.enableIncreasing + ')';
    }
}
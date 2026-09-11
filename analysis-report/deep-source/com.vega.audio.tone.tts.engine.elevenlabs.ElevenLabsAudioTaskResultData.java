package com.vega.audio.tone.tts.engine.elevenlabs;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes24.dex */
public final class ElevenLabsAudioTaskResultData {

    @SerializedName("resource_infos")
    public final List<ElevenLabsAudioResource> resourceInfos;

    @SerializedName("speaker_id")
    public final String speakerId;

    @SerializedName("status")
    public final int status;

    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public ElevenLabsAudioTaskResultData() {
        this(0, null, 0 == true ? 1 : 0, 7, 0 == true ? 1 : 0);
    }

    public ElevenLabsAudioTaskResultData(int i, List<ElevenLabsAudioResource> list, String str) {
        this.status = i;
        this.resourceInfos = list;
        this.speakerId = str;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0010: CONSTRUCTOR 
      (wrap:int:?: TERNARY null = ((wrap:int:0x0000: ARITH (r6v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r3v0 int))
      (wrap:java.util.List:?: TERNARY null = ((wrap:int:0x0005: ARITH (r6v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null java.util.List) : (r4v0 java.util.List))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x000b: ARITH (r6v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r5v0 java.lang.String))
     A[MD:(int, java.util.List<com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioResource>, java.lang.String):void (m)] call: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsAudioTaskResultData.<init>(int, java.util.List, java.lang.String):void type: THIS */
    public /* synthetic */ ElevenLabsAudioTaskResultData(int i, List list, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? null : list, (i2 & 4) != 0 ? null : str);
    }

    public final List<ElevenLabsAudioResource> a() {
        return this.resourceInfos;
    }

    public final String b() {
        return this.speakerId;
    }

    public final int c() {
        return this.status;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElevenLabsAudioTaskResultData)) {
            return false;
        }
        ElevenLabsAudioTaskResultData elevenLabsAudioTaskResultData = (ElevenLabsAudioTaskResultData) obj;
        return this.status == elevenLabsAudioTaskResultData.status && Intrinsics.areEqual(this.resourceInfos, elevenLabsAudioTaskResultData.resourceInfos) && Intrinsics.areEqual(this.speakerId, elevenLabsAudioTaskResultData.speakerId);
    }

    public final int hashCode() {
        int i = this.status * 31;
        List<ElevenLabsAudioResource> list = this.resourceInfos;
        int iHashCode = (i + (list == null ? 0 : list.hashCode())) * 31;
        String str = this.speakerId;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public final String toString() {
        return "ElevenLabsAudioTaskResultData(status=" + this.status + ", resourceInfos=" + this.resourceInfos + ", speakerId=" + this.speakerId + ')';
    }
}
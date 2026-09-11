package com.vega.audio.tone.tts.data;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes28.dex */
public final class StreamingToneSpeakerInfo {

    @SerializedName("emotion")
    public final String emotion;

    @SerializedName("emotion_scale")
    public final Double emotionScale;

    @SerializedName("is_clone_tone")
    public final boolean isCloneTone;

    @SerializedName("mock_tone_info")
    public final String mockToneInfo;

    @SerializedName("moyin_emotion")
    public final String moyinEmotion;

    @SerializedName("resource_id")
    public final String resourceId;

    @SerializedName("role")
    public final String role;

    @SerializedName("sample_rate")
    public final int sampleRate;

    @SerializedName("speaker_id")
    public final String speakerId;

    @SerializedName("speech_rate")
    public final int speechRate;

    @SerializedName("style")
    public final String style;

    public StreamingToneSpeakerInfo(String str, int i, String str2, Double d2, int i2, String str3, String str4, boolean z, String str5, String str6, String str7) {
        Intrinsics.checkNotNullParameter(str, "");
        this.speakerId = str;
        this.sampleRate = i;
        this.emotion = str2;
        this.emotionScale = d2;
        this.speechRate = i2;
        this.moyinEmotion = str3;
        this.resourceId = str4;
        this.isCloneTone = z;
        this.style = str5;
        this.role = str6;
        this.mockToneInfo = str7;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0047: CONSTRUCTOR 
      (r13v0 java.lang.String)
      (wrap:int:?: TERNARY null = ((wrap:int:0x0012: ARITH (r24v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (24000 int) : (r14v0 int))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0018: ARITH (r24v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r15v0 java.lang.String))
      (wrap:java.lang.Double:?: TERNARY null = ((wrap:int:0x001e: ARITH (r24v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? (null java.lang.Double) : (r16v0 java.lang.Double))
      (wrap:int:?: TERNARY null = ((wrap:int:0x0023: ARITH (r24v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? (1 int) : (r17v0 int))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0028: ARITH (r24v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r18v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x002d: ARITH (r24v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r19v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0032: ARITH (r24v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? false : (r20v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0037: ARITH (r24v0 int) & (256 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r21v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x003c: ARITH (r24v0 int) & (512 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r22v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0041: ARITH (r24v0 int) & (1024 int) A[WRAPPED]) == (0 int)) ? (r23v0 java.lang.String) : (null java.lang.String))
     A[MD:(java.lang.String, int, java.lang.String, java.lang.Double, int, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String):void (m)] call: com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo.<init>(java.lang.String, int, java.lang.String, java.lang.Double, int, java.lang.String, java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String):void type: THIS */
    public /* synthetic */ StreamingToneSpeakerInfo(String str, int i, String str2, Double d2, int i2, String str3, String str4, boolean z, String str5, String str6, String str7, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i3 & 2) != 0 ? 24000 : i, (i3 & 4) != 0 ? null : str2, (i3 & 8) != 0 ? null : d2, (i3 & 16) != 0 ? 1 : i2, (i3 & 32) != 0 ? null : str3, (i3 & 64) != 0 ? null : str4, (i3 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? false : z, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? null : str6, (i3 & 1024) == 0 ? str7 : null);
    }

    public static /* synthetic */ StreamingToneSpeakerInfo copy$default(StreamingToneSpeakerInfo streamingToneSpeakerInfo, String str, int i, String str2, Double d2, int i2, String str3, String str4, boolean z, String str5, String str6, String str7, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = streamingToneSpeakerInfo.speakerId;
        }
        if ((i3 & 2) != 0) {
            i = streamingToneSpeakerInfo.sampleRate;
        }
        if ((i3 & 4) != 0) {
            str2 = streamingToneSpeakerInfo.emotion;
        }
        if ((i3 & 8) != 0) {
            d2 = streamingToneSpeakerInfo.emotionScale;
        }
        if ((i3 & 16) != 0) {
            i2 = streamingToneSpeakerInfo.speechRate;
        }
        if ((i3 & 32) != 0) {
            str3 = streamingToneSpeakerInfo.moyinEmotion;
        }
        if ((i3 & 64) != 0) {
            str4 = streamingToneSpeakerInfo.resourceId;
        }
        if ((i3 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            z = streamingToneSpeakerInfo.isCloneTone;
        }
        if ((i3 & 256) != 0) {
            str5 = streamingToneSpeakerInfo.style;
        }
        if ((i3 & 512) != 0) {
            str6 = streamingToneSpeakerInfo.role;
        }
        if ((i3 & 1024) != 0) {
            str7 = streamingToneSpeakerInfo.mockToneInfo;
        }
        return streamingToneSpeakerInfo.copy(str, i, str2, d2, i2, str3, str4, z, str5, str6, str7);
    }

    public final StreamingToneSpeakerInfo copy(String str, int i, String str2, Double d2, int i2, String str3, String str4, boolean z, String str5, String str6, String str7) {
        Intrinsics.checkNotNullParameter(str, "");
        return new StreamingToneSpeakerInfo(str, i, str2, d2, i2, str3, str4, z, str5, str6, str7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreamingToneSpeakerInfo)) {
            return false;
        }
        StreamingToneSpeakerInfo streamingToneSpeakerInfo = (StreamingToneSpeakerInfo) obj;
        return Intrinsics.areEqual(this.speakerId, streamingToneSpeakerInfo.speakerId) && this.sampleRate == streamingToneSpeakerInfo.sampleRate && Intrinsics.areEqual(this.emotion, streamingToneSpeakerInfo.emotion) && Intrinsics.areEqual((Object) this.emotionScale, (Object) streamingToneSpeakerInfo.emotionScale) && this.speechRate == streamingToneSpeakerInfo.speechRate && Intrinsics.areEqual(this.moyinEmotion, streamingToneSpeakerInfo.moyinEmotion) && Intrinsics.areEqual(this.resourceId, streamingToneSpeakerInfo.resourceId) && this.isCloneTone == streamingToneSpeakerInfo.isCloneTone && Intrinsics.areEqual(this.style, streamingToneSpeakerInfo.style) && Intrinsics.areEqual(this.role, streamingToneSpeakerInfo.role) && Intrinsics.areEqual(this.mockToneInfo, streamingToneSpeakerInfo.mockToneInfo);
    }

    public final String getEmotion() {
        return this.emotion;
    }

    public final Double getEmotionScale() {
        return this.emotionScale;
    }

    public final String getMockToneInfo() {
        return this.mockToneInfo;
    }

    public final String getMoyinEmotion() {
        return this.moyinEmotion;
    }

    public final String getResourceId() {
        return this.resourceId;
    }

    public final String getRole() {
        return this.role;
    }

    public final int getSampleRate() {
        return this.sampleRate;
    }

    public final String getSpeakerId() {
        return this.speakerId;
    }

    public final int getSpeechRate() {
        return this.speechRate;
    }

    public final String getStyle() {
        return this.style;
    }

    public int hashCode() {
        int iHashCode = ((this.speakerId.hashCode() * 31) + this.sampleRate) * 31;
        String str = this.emotion;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        Double d2 = this.emotionScale;
        int iHashCode3 = (((iHashCode2 + (d2 == null ? 0 : d2.hashCode())) * 31) + this.speechRate) * 31;
        String str2 = this.moyinEmotion;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.resourceId;
        int iHashCode5 = (((iHashCode4 + (str3 == null ? 0 : str3.hashCode())) * 31) + (this.isCloneTone ? 1231 : 1237)) * 31;
        String str4 = this.style;
        int iHashCode6 = (iHashCode5 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.role;
        int iHashCode7 = (iHashCode6 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.mockToneInfo;
        return iHashCode7 + (str6 != null ? str6.hashCode() : 0);
    }

    public final boolean isCloneTone() {
        return this.isCloneTone;
    }

    public String toString() {
        return "StreamingToneSpeakerInfo(speakerId=" + this.speakerId + ", sampleRate=" + this.sampleRate + ", emotion=" + this.emotion + ", emotionScale=" + this.emotionScale + ", speechRate=" + this.speechRate + ", moyinEmotion=" + this.moyinEmotion + ", resourceId=" + this.resourceId + ", isCloneTone=" + this.isCloneTone + ", style=" + this.style + ", role=" + this.role + ", mockToneInfo=" + this.mockToneInfo + ')';
    }
}
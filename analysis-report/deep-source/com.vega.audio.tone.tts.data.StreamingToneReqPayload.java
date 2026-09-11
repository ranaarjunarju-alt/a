package com.vega.audio.tone.tts.data;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes36.dex */
public final class StreamingToneReqPayload {

    @SerializedName("audio_format")
    public final String audioFormat;

    @SerializedName("credit_disable")
    public final boolean creditDisable;

    @SerializedName("need_subtitle_timestamp")
    public final boolean needSubtitleTimeStamp;

    @SerializedName("platform")
    public final String platform;

    @SerializedName("scene")
    public final String scene;

    @SerializedName("sign")
    public final String sign;

    @SerializedName("speaker_info")
    public final StreamingToneSpeakerInfo speakerInfo;

    @SerializedName("texts")
    public final List<String> texts;

    public StreamingToneReqPayload(List<String> list, StreamingToneSpeakerInfo streamingToneSpeakerInfo, String str, boolean z, String str2, String str3, boolean z2, String str4) {
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(streamingToneSpeakerInfo, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        this.texts = list;
        this.speakerInfo = streamingToneSpeakerInfo;
        this.audioFormat = str;
        this.needSubtitleTimeStamp = z;
        this.sign = str2;
        this.platform = str3;
        this.creditDisable = z2;
        this.scene = str4;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0016: CONSTRUCTOR 
      (r2v0 java.util.List)
      (r3v0 com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo)
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0000: ARITH (r10v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? ("mp3") : (r4v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0006: ARITH (r10v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? false : (r5v0 boolean))
      (r6v0 java.lang.String)
      (r7v0 java.lang.String)
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x000b: ARITH (r10v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? false : (r8v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0010: ARITH (r10v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? ("") : (r9v0 java.lang.String))
     A[MD:(java.util.List<java.lang.String>, com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo, java.lang.String, boolean, java.lang.String, java.lang.String, boolean, java.lang.String):void (m)] call: com.vega.audio.tone.tts.data.StreamingToneReqPayload.<init>(java.util.List, com.vega.audio.tone.tts.data.StreamingToneSpeakerInfo, java.lang.String, boolean, java.lang.String, java.lang.String, boolean, java.lang.String):void type: THIS */
    public /* synthetic */ StreamingToneReqPayload(List list, StreamingToneSpeakerInfo streamingToneSpeakerInfo, String str, boolean z, String str2, String str3, boolean z2, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(list, streamingToneSpeakerInfo, (i & 4) != 0 ? "mp3" : str, (i & 8) != 0 ? false : z, str2, str3, (i & 64) != 0 ? false : z2, (i & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? "" : str4);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.vega.audio.tone.tts.data.StreamingToneReqPayload */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ StreamingToneReqPayload copy$default(StreamingToneReqPayload streamingToneReqPayload, List list, StreamingToneSpeakerInfo streamingToneSpeakerInfo, String str, boolean z, String str2, String str3, boolean z2, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            list = streamingToneReqPayload.texts;
        }
        if ((i & 2) != 0) {
            streamingToneSpeakerInfo = streamingToneReqPayload.speakerInfo;
        }
        if ((i & 4) != 0) {
            str = streamingToneReqPayload.audioFormat;
        }
        if ((i & 8) != 0) {
            z = streamingToneReqPayload.needSubtitleTimeStamp;
        }
        if ((i & 16) != 0) {
            str2 = streamingToneReqPayload.sign;
        }
        if ((i & 32) != 0) {
            str3 = streamingToneReqPayload.platform;
        }
        if ((i & 64) != 0) {
            z2 = streamingToneReqPayload.creditDisable;
        }
        if ((i & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            str4 = streamingToneReqPayload.scene;
        }
        return streamingToneReqPayload.copy(list, streamingToneSpeakerInfo, str, z, str2, str3, z2, str4);
    }

    public final StreamingToneReqPayload copy(List<String> list, StreamingToneSpeakerInfo streamingToneSpeakerInfo, String str, boolean z, String str2, String str3, boolean z2, String str4) {
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(streamingToneSpeakerInfo, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        return new StreamingToneReqPayload(list, streamingToneSpeakerInfo, str, z, str2, str3, z2, str4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreamingToneReqPayload)) {
            return false;
        }
        StreamingToneReqPayload streamingToneReqPayload = (StreamingToneReqPayload) obj;
        return Intrinsics.areEqual(this.texts, streamingToneReqPayload.texts) && Intrinsics.areEqual(this.speakerInfo, streamingToneReqPayload.speakerInfo) && Intrinsics.areEqual(this.audioFormat, streamingToneReqPayload.audioFormat) && this.needSubtitleTimeStamp == streamingToneReqPayload.needSubtitleTimeStamp && Intrinsics.areEqual(this.sign, streamingToneReqPayload.sign) && Intrinsics.areEqual(this.platform, streamingToneReqPayload.platform) && this.creditDisable == streamingToneReqPayload.creditDisable && Intrinsics.areEqual(this.scene, streamingToneReqPayload.scene);
    }

    public final String getAudioFormat() {
        return this.audioFormat;
    }

    public final boolean getCreditDisable() {
        return this.creditDisable;
    }

    public final boolean getNeedSubtitleTimeStamp() {
        return this.needSubtitleTimeStamp;
    }

    public final String getPlatform() {
        return this.platform;
    }

    public final String getScene() {
        return this.scene;
    }

    public final String getSign() {
        return this.sign;
    }

    public final StreamingToneSpeakerInfo getSpeakerInfo() {
        return this.speakerInfo;
    }

    public final List<String> getTexts() {
        return this.texts;
    }

    public int hashCode() {
        return (((((((((((((this.texts.hashCode() * 31) + this.speakerInfo.hashCode()) * 31) + this.audioFormat.hashCode()) * 31) + (this.needSubtitleTimeStamp ? 1231 : 1237)) * 31) + this.sign.hashCode()) * 31) + this.platform.hashCode()) * 31) + (this.creditDisable ? 1231 : 1237)) * 31) + this.scene.hashCode();
    }

    public String toString() {
        return "StreamingToneReqPayload(texts=" + this.texts + ", speakerInfo=" + this.speakerInfo + ", audioFormat=" + this.audioFormat + ", needSubtitleTimeStamp=" + this.needSubtitleTimeStamp + ", sign=" + this.sign + ", platform=" + this.platform + ", creditDisable=" + this.creditDisable + ", scene=" + this.scene + ')';
    }
}
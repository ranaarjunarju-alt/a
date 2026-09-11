package com.vega.audio.tone.tts.engine.server;

import androidx.core.app.NotificationCompat;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes35.dex */
public final class RemoteSAMIRequest {

    @SerializedName("algo_type")
    public final String algoType;

    @SerializedName("babi_param")
    public final String babiParam;

    @SerializedName("cache_key")
    public final String cacheKey;

    @SerializedName("audio_config")
    public final RemoteSAMIRequestAudioConfig config;

    @SerializedName("enable_text_seg")
    public final boolean enableSentenceSeg;

    @SerializedName("enter_from")
    public final String enterFrom;

    @SerializedName("from")
    public final String from;

    @SerializedName("internal")
    public final RemoteSAMIRequestAudioInternal internal;

    @SerializedName("item_id")
    public final String itemId;

    @SerializedName("scene")
    public final String scene;

    @SerializedName("text")
    public final String text;

    @SerializedName("text_count")
    public final int textCount;

    @SerializedName("use_cache")
    public final boolean useCache;

    @SerializedName("voice_type")
    public final String voiceType;

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v10, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v11, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v9, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r2v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public RemoteSAMIRequest() {
        Object[] objArr = 0 == true ? 1 : 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        Object[] objArr3 = 0 == true ? 1 : 0;
        Object[] objArr4 = 0 == true ? 1 : 0;
        Object[] objArr5 = 0 == true ? 1 : 0;
        Object[] objArr6 = 0 == true ? 1 : 0;
        Object[] objArr7 = 0 == true ? 1 : 0;
        Object[] objArr8 = 0 == true ? 1 : 0;
        Object[] objArr9 = 0 == true ? 1 : 0;
        Object[] objArr10 = 0 == true ? 1 : 0;
        Object[] objArr11 = 0 == true ? 1 : 0;
        this(null, 0, objArr, 0 == true ? 1 : 0, objArr2, objArr3, 0 == true ? 1 : 0, objArr4, objArr5, objArr6, objArr7, objArr8, objArr9, objArr10, 16383, objArr11);
    }

    public RemoteSAMIRequest(String str, int i, String str2, boolean z, String str3, String str4, boolean z2, String str5, RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal, RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig, String str6, String str7, String str8, String str9) {
        this.text = str;
        this.textCount = i;
        this.voiceType = str2;
        this.useCache = z;
        this.algoType = str3;
        this.cacheKey = str4;
        this.enableSentenceSeg = z2;
        this.from = str5;
        this.internal = remoteSAMIRequestAudioInternal;
        this.config = remoteSAMIRequestAudioConfig;
        this.itemId = str6;
        this.enterFrom = str7;
        this.scene = str8;
        this.babiParam = str9;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0064: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x001a: ARITH (r31v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r17v0 java.lang.String))
      (wrap:int:?: TERNARY null = ((wrap:int:0x0020: ARITH (r31v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (1 int) : (r18v0 int))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0025: ARITH (r31v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r19v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x002a: ARITH (r31v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? false : (r20v0 boolean))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0030: ARITH (r31v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? ("sami") : (r21v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0036: ARITH (r31v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? ("") : (r22v0 java.lang.String))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x003c: ARITH (r31v0 int) & (64 int) A[WRAPPED]) == (0 int)) ? (r23v0 boolean) : false)
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0040: ARITH (r31v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r24v0 java.lang.String))
      (wrap:com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal:?: TERNARY null = ((wrap:int:0x0045: ARITH (r31v0 int) & (256 int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal) : (r25v0 com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal))
      (wrap:com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig:?: TERNARY null = ((wrap:int:0x004a: ARITH (r31v0 int) & (512 int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig) : (r26v0 com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x004f: ARITH (r31v0 int) & (1024 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r27v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0054: ARITH (r31v0 int) & (wrap:??:SGET  A[WRAPPED] com.bytedance.speech.speechengine.SpeechEngineDefines.ASR_WORK_MODE_OFFLINE int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r28v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0059: ARITH (r31v0 int) & (wrap:??:SGET  A[WRAPPED] com.bytedance.speech.speechengine.SpeechEngineDefines.TTS_WORK_MODE_BOTH int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r29v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x005e: ARITH (r31v0 int) & (8192 int) A[WRAPPED]) == (0 int)) ? (r30v0 java.lang.String) : (null java.lang.String))
     A[MD:(java.lang.String, int, java.lang.String, boolean, java.lang.String, java.lang.String, boolean, java.lang.String, com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal, com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void (m)] call: com.vega.audio.tone.tts.engine.server.RemoteSAMIRequest.<init>(java.lang.String, int, java.lang.String, boolean, java.lang.String, java.lang.String, boolean, java.lang.String, com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal, com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioConfig, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: THIS */
    public /* synthetic */ RemoteSAMIRequest(String str, int i, String str2, boolean z, String str3, String str4, boolean z2, String str5, RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal, RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig, String str6, String str7, String str8, String str9, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? 1 : i, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? false : z, (i2 & 16) != 0 ? "sami" : str3, (i2 & 32) != 0 ? "" : str4, (i2 & 64) == 0 ? z2 : false, (i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? null : str5, (i2 & 256) != 0 ? null : remoteSAMIRequestAudioInternal, (i2 & 512) != 0 ? null : remoteSAMIRequestAudioConfig, (i2 & 1024) != 0 ? null : str6, (i2 & SpeechEngineDefines.ASR_WORK_MODE_OFFLINE) != 0 ? null : str7, (i2 & SpeechEngineDefines.TTS_WORK_MODE_BOTH) != 0 ? null : str8, (i2 & 8192) == 0 ? str9 : null);
    }

    public static /* synthetic */ RemoteSAMIRequest copy$default(RemoteSAMIRequest remoteSAMIRequest, String str, int i, String str2, boolean z, String str3, String str4, boolean z2, String str5, RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal, RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig, String str6, String str7, String str8, String str9, int i2, Object obj) {
        String str10 = str;
        int i3 = i;
        String str11 = str2;
        boolean z3 = z;
        String str12 = str3;
        String str13 = str4;
        boolean z4 = z2;
        String str14 = str5;
        RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal2 = remoteSAMIRequestAudioInternal;
        RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig2 = remoteSAMIRequestAudioConfig;
        String str15 = str6;
        String str16 = str7;
        String str17 = str8;
        String str18 = str9;
        if ((i2 & 1) != 0) {
            str10 = remoteSAMIRequest.text;
        }
        if ((i2 & 2) != 0) {
            i3 = remoteSAMIRequest.textCount;
        }
        if ((i2 & 4) != 0) {
            str11 = remoteSAMIRequest.voiceType;
        }
        if ((i2 & 8) != 0) {
            z3 = remoteSAMIRequest.useCache;
        }
        if ((i2 & 16) != 0) {
            str12 = remoteSAMIRequest.algoType;
        }
        if ((i2 & 32) != 0) {
            str13 = remoteSAMIRequest.cacheKey;
        }
        if ((i2 & 64) != 0) {
            z4 = remoteSAMIRequest.enableSentenceSeg;
        }
        if ((i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            str14 = remoteSAMIRequest.from;
        }
        if ((i2 & 256) != 0) {
            remoteSAMIRequestAudioInternal2 = remoteSAMIRequest.internal;
        }
        if ((i2 & 512) != 0) {
            remoteSAMIRequestAudioConfig2 = remoteSAMIRequest.config;
        }
        if ((i2 & 1024) != 0) {
            str15 = remoteSAMIRequest.itemId;
        }
        if ((i2 & SpeechEngineDefines.ASR_WORK_MODE_OFFLINE) != 0) {
            str16 = remoteSAMIRequest.enterFrom;
        }
        if ((i2 & SpeechEngineDefines.TTS_WORK_MODE_BOTH) != 0) {
            str17 = remoteSAMIRequest.scene;
        }
        if ((i2 & 8192) != 0) {
            str18 = remoteSAMIRequest.babiParam;
        }
        return remoteSAMIRequest.copy(str10, i3, str11, z3, str12, str13, z4, str14, remoteSAMIRequestAudioInternal2, remoteSAMIRequestAudioConfig2, str15, str16, str17, str18);
    }

    public final RemoteSAMIRequest copy(String str, int i, String str2, boolean z, String str3, String str4, boolean z2, String str5, RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal, RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig, String str6, String str7, String str8, String str9) {
        return new RemoteSAMIRequest(str, i, str2, z, str3, str4, z2, str5, remoteSAMIRequestAudioInternal, remoteSAMIRequestAudioConfig, str6, str7, str8, str9);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RemoteSAMIRequest)) {
            return false;
        }
        RemoteSAMIRequest remoteSAMIRequest = (RemoteSAMIRequest) obj;
        return Intrinsics.areEqual(this.text, remoteSAMIRequest.text) && this.textCount == remoteSAMIRequest.textCount && Intrinsics.areEqual(this.voiceType, remoteSAMIRequest.voiceType) && this.useCache == remoteSAMIRequest.useCache && Intrinsics.areEqual(this.algoType, remoteSAMIRequest.algoType) && Intrinsics.areEqual(this.cacheKey, remoteSAMIRequest.cacheKey) && this.enableSentenceSeg == remoteSAMIRequest.enableSentenceSeg && Intrinsics.areEqual(this.from, remoteSAMIRequest.from) && Intrinsics.areEqual(this.internal, remoteSAMIRequest.internal) && Intrinsics.areEqual(this.config, remoteSAMIRequest.config) && Intrinsics.areEqual(this.itemId, remoteSAMIRequest.itemId) && Intrinsics.areEqual(this.enterFrom, remoteSAMIRequest.enterFrom) && Intrinsics.areEqual(this.scene, remoteSAMIRequest.scene) && Intrinsics.areEqual(this.babiParam, remoteSAMIRequest.babiParam);
    }

    public final String getAlgoType() {
        return this.algoType;
    }

    public final String getBabiParam() {
        return this.babiParam;
    }

    public final String getCacheKey() {
        return this.cacheKey;
    }

    public final RemoteSAMIRequestAudioConfig getConfig() {
        return this.config;
    }

    public final boolean getEnableSentenceSeg() {
        return this.enableSentenceSeg;
    }

    public final String getEnterFrom() {
        return this.enterFrom;
    }

    public final String getFrom() {
        return this.from;
    }

    public final RemoteSAMIRequestAudioInternal getInternal() {
        return this.internal;
    }

    public final String getItemId() {
        return this.itemId;
    }

    public final String getScene() {
        return this.scene;
    }

    public final String getText() {
        return this.text;
    }

    public final int getTextCount() {
        return this.textCount;
    }

    public final boolean getUseCache() {
        return this.useCache;
    }

    public final String getVoiceType() {
        return this.voiceType;
    }

    public int hashCode() {
        String str = this.text;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.textCount) * 31;
        String str2 = this.voiceType;
        int iHashCode2 = (((iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31) + (this.useCache ? 1231 : 1237)) * 31;
        String str3 = this.algoType;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.cacheKey;
        int iHashCode4 = (((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + (this.enableSentenceSeg ? 1231 : 1237)) * 31;
        String str5 = this.from;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal = this.internal;
        int iHashCode6 = (iHashCode5 + (remoteSAMIRequestAudioInternal == null ? 0 : remoteSAMIRequestAudioInternal.hashCode())) * 31;
        RemoteSAMIRequestAudioConfig remoteSAMIRequestAudioConfig = this.config;
        int iHashCode7 = (iHashCode6 + (remoteSAMIRequestAudioConfig == null ? 0 : remoteSAMIRequestAudioConfig.hashCode())) * 31;
        String str6 = this.itemId;
        int iHashCode8 = (iHashCode7 + (str6 == null ? 0 : str6.hashCode())) * 31;
        String str7 = this.enterFrom;
        int iHashCode9 = (iHashCode8 + (str7 == null ? 0 : str7.hashCode())) * 31;
        String str8 = this.scene;
        int iHashCode10 = (iHashCode9 + (str8 == null ? 0 : str8.hashCode())) * 31;
        String str9 = this.babiParam;
        return iHashCode10 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "RemoteSAMIRequest(text=" + this.text + ", textCount=" + this.textCount + ", voiceType=" + this.voiceType + ", useCache=" + this.useCache + ", algoType=" + this.algoType + ", cacheKey=" + this.cacheKey + ", enableSentenceSeg=" + this.enableSentenceSeg + ", from=" + this.from + ", internal=" + this.internal + ", config=" + this.config + ", itemId=" + this.itemId + ", enterFrom=" + this.enterFrom + ", scene=" + this.scene + ", babiParam=" + this.babiParam + ')';
    }
}
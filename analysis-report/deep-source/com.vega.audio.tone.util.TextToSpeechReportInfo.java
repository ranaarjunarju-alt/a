package com.vega.audio.tone.util;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.core.ext.ExtentionKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes22.dex */
public final class TextToSpeechReportInfo {
    public static final Companion Companion = new Companion();

    @SerializedName("clone_from")
    public final TextToSpeechReportScene cloneFrom;

    @SerializedName("digital_human_entrance")
    public final String digitalHumanEntrance;

    @SerializedName("error")
    public final TextToSpeechReportSceneError error;

    @SerializedName("from")
    public final TextToSpeechReportScene from;

    @SerializedName("is_audition")
    public final boolean isAudition;

    @SerializedName("is_change_speed")
    public final boolean isChangeSpeed;

    @SerializedName("length")
    public final int length;

    @SerializedName("start_time")
    public final long startTime;

    @SerializedName("status")
    public final String status;

    @SerializedName("sub_from")
    public final String subFrom;

    /* loaded from: classes.dex */
    public static final class Companion {
        public static TextToSpeechReportInfo a(String str) {
            if (str == null) {
                return null;
            }
            try {
                return (TextToSpeechReportInfo) ExtentionKt.getGson().fromJson(str, TextToSpeechReportInfo.class);
            } catch (Throwable th) {
                Result.m17090constructorimpl(ResultKt.createFailure(th));
                return null;
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public TextToSpeechReportInfo() {
        Object[] objArr = 0 == true ? 1 : 0;
        Object[] objArr2 = 0 == true ? 1 : 0;
        Object[] objArr3 = 0 == true ? 1 : 0;
        Object[] objArr4 = 0 == true ? 1 : 0;
        Object[] objArr5 = 0 == true ? 1 : 0;
        Object[] objArr6 = 0 == true ? 1 : 0;
        this(null, objArr, 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0L, objArr2, objArr3, objArr4, objArr5, 1023, objArr6);
    }

    public TextToSpeechReportInfo(TextToSpeechReportScene textToSpeechReportScene, TextToSpeechReportScene textToSpeechReportScene2, int i, boolean z, boolean z2, long j, String str, TextToSpeechReportSceneError textToSpeechReportSceneError, String str2, String str3) {
        this.from = textToSpeechReportScene;
        this.cloneFrom = textToSpeechReportScene2;
        this.length = i;
        this.isAudition = z;
        this.isChangeSpeed = z2;
        this.startTime = j;
        this.status = str;
        this.error = textToSpeechReportSceneError;
        this.digitalHumanEntrance = str2;
        this.subFrom = str3;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0047: CONSTRUCTOR 
      (wrap:com.vega.aigcapi.materialgenerate.TextToSpeechReportScene:?: TERNARY null = ((wrap:int:0x0010: ARITH (r25v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (null com.vega.aigcapi.materialgenerate.TextToSpeechReportScene) : (r14v0 com.vega.aigcapi.materialgenerate.TextToSpeechReportScene))
      (wrap:com.vega.aigcapi.materialgenerate.TextToSpeechReportScene:?: TERNARY null = ((wrap:int:0x0016: ARITH (r25v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null com.vega.aigcapi.materialgenerate.TextToSpeechReportScene) : (r15v0 com.vega.aigcapi.materialgenerate.TextToSpeechReportScene))
      (wrap:int:?: TERNARY null = ((wrap:int:0x001b: ARITH (r25v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r16v0 int))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0021: ARITH (r25v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? false : (r17v0 boolean))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0026: ARITH (r25v0 int) & (16 int) A[WRAPPED]) == (0 int)) ? (r18v0 boolean) : false)
      (wrap:long:?: TERNARY null = ((wrap:int:0x002a: ARITH (r25v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? (wrap:long:0x002e: INVOKE  STATIC call: java.lang.System.currentTimeMillis():long A[MD:():long (c), WRAPPED]) : (r19v0 long))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0032: ARITH (r25v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? ("") : (r21v0 java.lang.String))
      (wrap:com.vega.audio.tone.util.TextToSpeechReportSceneError:?: TERNARY null = ((wrap:int:0x0038: ARITH (r25v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.util.TextToSpeechReportSceneError) : (r22v0 com.vega.audio.tone.util.TextToSpeechReportSceneError))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x003d: ARITH (r25v0 int) & (256 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r23v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0042: ARITH (r25v0 int) & (512 int) A[WRAPPED]) == (0 int)) ? (r24v0 java.lang.String) : (null java.lang.String))
     A[MD:(com.vega.aigcapi.materialgenerate.TextToSpeechReportScene, com.vega.aigcapi.materialgenerate.TextToSpeechReportScene, int, boolean, boolean, long, java.lang.String, com.vega.audio.tone.util.TextToSpeechReportSceneError, java.lang.String, java.lang.String):void (m)] call: com.vega.audio.tone.util.TextToSpeechReportInfo.<init>(com.vega.aigcapi.materialgenerate.TextToSpeechReportScene, com.vega.aigcapi.materialgenerate.TextToSpeechReportScene, int, boolean, boolean, long, java.lang.String, com.vega.audio.tone.util.TextToSpeechReportSceneError, java.lang.String, java.lang.String):void type: THIS */
    public /* synthetic */ TextToSpeechReportInfo(TextToSpeechReportScene textToSpeechReportScene, TextToSpeechReportScene textToSpeechReportScene2, int i, boolean z, boolean z2, long j, String str, TextToSpeechReportSceneError textToSpeechReportSceneError, String str2, String str3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : textToSpeechReportScene, (i2 & 2) != 0 ? null : textToSpeechReportScene2, (i2 & 4) != 0 ? 0 : i, (i2 & 8) != 0 ? false : z, (i2 & 16) == 0 ? z2 : false, (i2 & 32) != 0 ? System.currentTimeMillis() : j, (i2 & 64) != 0 ? "" : str, (i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? null : textToSpeechReportSceneError, (i2 & 256) != 0 ? null : str2, (i2 & 512) == 0 ? str3 : null);
    }

    public static /* synthetic */ TextToSpeechReportInfo copy$default(TextToSpeechReportInfo textToSpeechReportInfo, TextToSpeechReportScene textToSpeechReportScene, TextToSpeechReportScene textToSpeechReportScene2, int i, boolean z, boolean z2, long j, String str, TextToSpeechReportSceneError textToSpeechReportSceneError, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            textToSpeechReportScene = textToSpeechReportInfo.from;
        }
        if ((i2 & 2) != 0) {
            textToSpeechReportScene2 = textToSpeechReportInfo.cloneFrom;
        }
        if ((i2 & 4) != 0) {
            i = textToSpeechReportInfo.length;
        }
        if ((i2 & 8) != 0) {
            z = textToSpeechReportInfo.isAudition;
        }
        if ((i2 & 16) != 0) {
            z2 = textToSpeechReportInfo.isChangeSpeed;
        }
        if ((i2 & 32) != 0) {
            j = textToSpeechReportInfo.startTime;
        }
        if ((i2 & 64) != 0) {
            str = textToSpeechReportInfo.status;
        }
        if ((i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            textToSpeechReportSceneError = textToSpeechReportInfo.error;
        }
        if ((i2 & 256) != 0) {
            str2 = textToSpeechReportInfo.digitalHumanEntrance;
        }
        if ((i2 & 512) != 0) {
            str3 = textToSpeechReportInfo.subFrom;
        }
        return textToSpeechReportInfo.copy(textToSpeechReportScene, textToSpeechReportScene2, i, z, z2, j, str, textToSpeechReportSceneError, str2, str3);
    }

    public final TextToSpeechReportInfo copy(TextToSpeechReportScene textToSpeechReportScene, TextToSpeechReportScene textToSpeechReportScene2, int i, boolean z, boolean z2, long j, String str, TextToSpeechReportSceneError textToSpeechReportSceneError, String str2, String str3) {
        return new TextToSpeechReportInfo(textToSpeechReportScene, textToSpeechReportScene2, i, z, z2, j, str, textToSpeechReportSceneError, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextToSpeechReportInfo)) {
            return false;
        }
        TextToSpeechReportInfo textToSpeechReportInfo = (TextToSpeechReportInfo) obj;
        return this.from == textToSpeechReportInfo.from && this.cloneFrom == textToSpeechReportInfo.cloneFrom && this.length == textToSpeechReportInfo.length && this.isAudition == textToSpeechReportInfo.isAudition && this.isChangeSpeed == textToSpeechReportInfo.isChangeSpeed && this.startTime == textToSpeechReportInfo.startTime && Intrinsics.areEqual(this.status, textToSpeechReportInfo.status) && Intrinsics.areEqual(this.error, textToSpeechReportInfo.error) && Intrinsics.areEqual(this.digitalHumanEntrance, textToSpeechReportInfo.digitalHumanEntrance) && Intrinsics.areEqual(this.subFrom, textToSpeechReportInfo.subFrom);
    }

    public final TextToSpeechReportScene getCloneFrom() {
        return this.cloneFrom;
    }

    public final String getDigitalHumanEntrance() {
        return this.digitalHumanEntrance;
    }

    public final TextToSpeechReportSceneError getError() {
        return this.error;
    }

    public final TextToSpeechReportScene getFrom() {
        return this.from;
    }

    public final int getLength() {
        return this.length;
    }

    public final long getStartTime() {
        return this.startTime;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getSubFrom() {
        return this.subFrom;
    }

    public int hashCode() {
        TextToSpeechReportScene textToSpeechReportScene = this.from;
        int iHashCode = (textToSpeechReportScene == null ? 0 : textToSpeechReportScene.hashCode()) * 31;
        TextToSpeechReportScene textToSpeechReportScene2 = this.cloneFrom;
        int iHashCode2 = (((((((iHashCode + (textToSpeechReportScene2 == null ? 0 : textToSpeechReportScene2.hashCode())) * 31) + this.length) * 31) + (this.isAudition ? 1231 : 1237)) * 31) + (this.isChangeSpeed ? 1231 : 1237)) * 31;
        long j = this.startTime;
        int i = (iHashCode2 + ((int) (j ^ (j >>> 32)))) * 31;
        String str = this.status;
        int iHashCode3 = (i + (str == null ? 0 : str.hashCode())) * 31;
        TextToSpeechReportSceneError textToSpeechReportSceneError = this.error;
        int iHashCode4 = (iHashCode3 + (textToSpeechReportSceneError == null ? 0 : textToSpeechReportSceneError.hashCode())) * 31;
        String str2 = this.digitalHumanEntrance;
        int iHashCode5 = (iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.subFrom;
        return iHashCode5 + (str3 != null ? str3.hashCode() : 0);
    }

    public final boolean isAudition() {
        return this.isAudition;
    }

    public final boolean isChangeSpeed() {
        return this.isChangeSpeed;
    }

    public final String toJson() {
        try {
            return ExtentionKt.toJsonOrEmpty(this);
        } catch (Throwable th) {
            Result.m17090constructorimpl(ResultKt.createFailure(th));
            return "";
        }
    }

    public String toString() {
        return "TextToSpeechReportInfo(from=" + this.from + ", cloneFrom=" + this.cloneFrom + ", length=" + this.length + ", isAudition=" + this.isAudition + ", isChangeSpeed=" + this.isChangeSpeed + ", startTime=" + this.startTime + ", status=" + this.status + ", error=" + this.error + ", digitalHumanEntrance=" + this.digitalHumanEntrance + ", subFrom=" + this.subFrom + ')';
    }
}
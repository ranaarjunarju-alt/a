package com.vega.edit.base.functionassistant.api;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes8.dex */
public final class ReportActionRequest {

    @SerializedName("action")
    public final String action;

    @SerializedName("app_id")
    public final int appId;

    @SerializedName("biz")
    public final String biz;

    @SerializedName("biz_scene")
    public final String bizScene;

    @SerializedName("function_key")
    public final String functionKey;

    @SerializedName("request_id")
    public final String requestId;

    @SerializedName("scene")
    public final String scene;

    @SerializedName("source")
    public final String source;

    public ReportActionRequest(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        this.biz = str;
        this.appId = i;
        this.scene = str2;
        this.source = str3;
        this.functionKey = str4;
        this.action = str5;
        this.requestId = str6;
        this.bizScene = str7;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0017: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0000: ARITH (r10v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("capcut") : (r2v0 java.lang.String))
      (wrap:int:?: TERNARY null = ((wrap:int:0x0006: ARITH (r10v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (3006 int) : (r3v0 int))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x000c: ARITH (r10v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? ("function_recommend") : (r4v0 java.lang.String))
      (r5v0 java.lang.String)
      (r6v0 java.lang.String)
      (r7v0 java.lang.String)
      (r8v0 java.lang.String)
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0012: ARITH (r10v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r9v0 java.lang.String))
     A[MD:(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void (m)] call: com.vega.edit.base.functionassistant.api.ReportActionRequest.<init>(java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: THIS */
    public /* synthetic */ ReportActionRequest(String str, int i, String str2, String str3, String str4, String str5, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "capcut" : str, (i2 & 2) != 0 ? 3006 : i, (i2 & 4) != 0 ? "function_recommend" : str2, str3, str4, str5, str6, (i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? null : str7);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReportActionRequest)) {
            return false;
        }
        ReportActionRequest reportActionRequest = (ReportActionRequest) obj;
        return Intrinsics.areEqual(this.biz, reportActionRequest.biz) && this.appId == reportActionRequest.appId && Intrinsics.areEqual(this.scene, reportActionRequest.scene) && Intrinsics.areEqual(this.source, reportActionRequest.source) && Intrinsics.areEqual(this.functionKey, reportActionRequest.functionKey) && Intrinsics.areEqual(this.action, reportActionRequest.action) && Intrinsics.areEqual(this.requestId, reportActionRequest.requestId) && Intrinsics.areEqual(this.bizScene, reportActionRequest.bizScene);
    }

    public final int hashCode() {
        int iHashCode = ((((((((((((this.biz.hashCode() * 31) + this.appId) * 31) + this.scene.hashCode()) * 31) + this.source.hashCode()) * 31) + this.functionKey.hashCode()) * 31) + this.action.hashCode()) * 31) + this.requestId.hashCode()) * 31;
        String str = this.bizScene;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    public final String toString() {
        return "ReportActionRequest(biz=" + this.biz + ", appId=" + this.appId + ", scene=" + this.scene + ", source=" + this.source + ", functionKey=" + this.functionKey + ", action=" + this.action + ", requestId=" + this.requestId + ", bizScene=" + this.bizScene + ')';
    }
}
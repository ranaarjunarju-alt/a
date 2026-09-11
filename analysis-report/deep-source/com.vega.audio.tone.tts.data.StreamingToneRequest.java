package com.vega.audio.tone.tts.data;

import com.google.gson.annotations.SerializedName;
import com.vungle.ads.internal.protos.Sdk;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes18.dex */
public final class StreamingToneRequest {
    public static final Companion Companion = new Companion();

    @SerializedName("babi_param")
    public final String babiParam;

    @SerializedName("matrix_task_id")
    public final String matrixTaskId;

    @SerializedName("payload")
    public final StreamingTonePayload payload;

    @SerializedName("req_key")
    public final String reqKey;

    @SerializedName("req_payload")
    public final String reqPayload;

    @SerializedName("status")
    public final String status;

    @SerializedName("task_id")
    public final String taskId;

    public static final class Companion {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public StreamingToneRequest() {
        this(null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, Sdk.SDKError.Reason.ASSET_FAILED_MAX_SPACE_EXCEEDED_VALUE, 0 == true ? 1 : 0);
    }

    public StreamingToneRequest(String str, StreamingTonePayload streamingTonePayload, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        this.reqKey = str;
        this.payload = streamingTonePayload;
        this.reqPayload = str2;
        this.status = str3;
        this.taskId = str4;
        this.matrixTaskId = str5;
        this.babiParam = str6;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x002b: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0005: ARITH (r16v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r9v0 java.lang.String))
      (wrap:com.vega.audio.tone.tts.data.StreamingTonePayload:?: TERNARY null = ((wrap:int:0x000b: ARITH (r16v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (null com.vega.audio.tone.tts.data.StreamingTonePayload) : (r10v0 com.vega.audio.tone.tts.data.StreamingTonePayload))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0010: ARITH (r16v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r11v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0015: ARITH (r16v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? ("create") : (r12v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x001b: ARITH (r16v0 int) & (16 int) A[WRAPPED]) != (0 int)) ? ("") : (r13v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0022: ARITH (r16v0 int) & (32 int) A[WRAPPED]) == (0 int)) ? (r14v0 java.lang.String) : (""))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0026: ARITH (r16v0 int) & (64 int) A[WRAPPED]) == (0 int)) ? (r15v0 java.lang.String) : (null java.lang.String))
     A[MD:(java.lang.String, com.vega.audio.tone.tts.data.StreamingTonePayload, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void (m)] call: com.vega.audio.tone.tts.data.StreamingToneRequest.<init>(java.lang.String, com.vega.audio.tone.tts.data.StreamingTonePayload, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):void type: THIS */
    public /* synthetic */ StreamingToneRequest(String str, StreamingTonePayload streamingTonePayload, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : streamingTonePayload, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? "create" : str3, (i & 16) != 0 ? "" : str4, (i & 32) == 0 ? str5 : "", (i & 64) == 0 ? str6 : null);
    }

    public static /* synthetic */ StreamingToneRequest copy$default(StreamingToneRequest streamingToneRequest, String str, StreamingTonePayload streamingTonePayload, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = streamingToneRequest.reqKey;
        }
        if ((i & 2) != 0) {
            streamingTonePayload = streamingToneRequest.payload;
        }
        if ((i & 4) != 0) {
            str2 = streamingToneRequest.reqPayload;
        }
        if ((i & 8) != 0) {
            str3 = streamingToneRequest.status;
        }
        if ((i & 16) != 0) {
            str4 = streamingToneRequest.taskId;
        }
        if ((i & 32) != 0) {
            str5 = streamingToneRequest.matrixTaskId;
        }
        if ((i & 64) != 0) {
            str6 = streamingToneRequest.babiParam;
        }
        return streamingToneRequest.copy(str, streamingTonePayload, str2, str3, str4, str5, str6);
    }

    public final StreamingToneRequest copy(String str, StreamingTonePayload streamingTonePayload, String str2, String str3, String str4, String str5, String str6) {
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        return new StreamingToneRequest(str, streamingTonePayload, str2, str3, str4, str5, str6);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreamingToneRequest)) {
            return false;
        }
        StreamingToneRequest streamingToneRequest = (StreamingToneRequest) obj;
        return Intrinsics.areEqual(this.reqKey, streamingToneRequest.reqKey) && Intrinsics.areEqual(this.payload, streamingToneRequest.payload) && Intrinsics.areEqual(this.reqPayload, streamingToneRequest.reqPayload) && Intrinsics.areEqual(this.status, streamingToneRequest.status) && Intrinsics.areEqual(this.taskId, streamingToneRequest.taskId) && Intrinsics.areEqual(this.matrixTaskId, streamingToneRequest.matrixTaskId) && Intrinsics.areEqual(this.babiParam, streamingToneRequest.babiParam);
    }

    public final String getBabiParam() {
        return this.babiParam;
    }

    public final String getMatrixTaskId() {
        return this.matrixTaskId;
    }

    public final StreamingTonePayload getPayload() {
        return this.payload;
    }

    public final String getReqKey() {
        return this.reqKey;
    }

    public final String getReqPayload() {
        return this.reqPayload;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTaskId() {
        return this.taskId;
    }

    public int hashCode() {
        String str = this.reqKey;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        StreamingTonePayload streamingTonePayload = this.payload;
        int iHashCode2 = (iHashCode + (streamingTonePayload == null ? 0 : streamingTonePayload.hashCode())) * 31;
        String str2 = this.reqPayload;
        int iHashCode3 = (((((((iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31) + this.status.hashCode()) * 31) + this.taskId.hashCode()) * 31) + this.matrixTaskId.hashCode()) * 31;
        String str3 = this.babiParam;
        return iHashCode3 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "StreamingToneRequest(reqKey=" + this.reqKey + ", payload=" + this.payload + ", reqPayload=" + this.reqPayload + ", status=" + this.status + ", taskId=" + this.taskId + ", matrixTaskId=" + this.matrixTaskId + ", babiParam=" + this.babiParam + ')';
    }
}
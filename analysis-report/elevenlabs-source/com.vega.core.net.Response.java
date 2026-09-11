package com.vega.core.net;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes8.dex */
public final class Response<T> implements Serializable, IServerTimeProvider, ILogIdProvider {

    @SerializedName(alternate = {"Data"}, value = "data")
    public final T data;

    @SerializedName("errmsg")
    public final String errmsg;

    @SerializedName(alternate = {"logid"}, value = "log_id")
    public final String logId;

    @SerializedName("ret")
    public final String ret;

    @SerializedName(alternate = {"systime"}, value = "svr_time")
    @JsonAdapter(SysTimeTypeAdapter.class)
    public final long serverTime;

    @SerializedName("svr_timing")
    public final Object svrTiming;

    public Response(String str, String str2, T t, long j, String str3, Object obj) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        this.ret = str;
        this.errmsg = str2;
        this.data = t;
        this.serverTime = j;
        this.logId = str3;
        this.svrTiming = obj;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0021: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0004: ARITH (r16v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("") : (r9v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x000b: ARITH (r16v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? ("") : (r10v0 java.lang.String))
      (r11v0 java.lang.Object)
      (wrap:long:?: TERNARY null = ((wrap:int:0x0010: ARITH (r16v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? (0 long) : (r12v0 long))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0016: ARITH (r16v0 int) & (16 int) A[WRAPPED]) == (0 int)) ? (r14v0 java.lang.String) : (""))
      (wrap:java.lang.Object:?: TERNARY null = ((wrap:int:0x001a: ARITH (r16v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? (null java.lang.Object) : (r15v0 java.lang.Object))
     A[MD:(java.lang.String, java.lang.String, T, long, java.lang.String, java.lang.Object):void (m)] call: com.vega.core.net.Response.<init>(java.lang.String, java.lang.String, java.lang.Object, long, java.lang.String, java.lang.Object):void type: THIS */
    public /* synthetic */ Response(String str, String str2, Object obj, long j, String str3, Object obj2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, obj, (i & 8) != 0 ? 0L : j, (i & 16) == 0 ? str3 : "", (i & 32) != 0 ? null : obj2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.vega.core.net.Response */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Response copy$default(Response response, String str, String str2, Object obj, long j, String str3, Object obj2, int i, Object obj3) {
        if ((i & 1) != 0) {
            str = response.ret;
        }
        if ((i & 2) != 0) {
            str2 = response.errmsg;
        }
        if ((i & 4) != 0) {
            obj = response.data;
        }
        if ((i & 8) != 0) {
            j = response.serverTime;
        }
        if ((i & 16) != 0) {
            str3 = response.logId;
        }
        if ((i & 32) != 0) {
            obj2 = response.svrTiming;
        }
        return response.copy(str, str2, obj, j, str3, obj2);
    }

    public final Response<T> copy(String str, String str2, T t, long j, String str3, Object obj) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        return new Response<>(str, str2, t, j, str3, obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Response)) {
            return false;
        }
        Response response = (Response) obj;
        return Intrinsics.areEqual(this.ret, response.ret) && Intrinsics.areEqual(this.errmsg, response.errmsg) && Intrinsics.areEqual(this.data, response.data) && this.serverTime == response.serverTime && Intrinsics.areEqual(this.logId, response.logId) && Intrinsics.areEqual(this.svrTiming, response.svrTiming);
    }

    public final T getData() {
        return this.data;
    }

    public final String getErrmsg() {
        return this.errmsg;
    }

    @Override // com.vega.core.net.ILogIdProvider
    public String getLogId() {
        return this.logId;
    }

    public final String getRet() {
        return this.ret;
    }

    @Override // com.vega.core.net.IServerTimeProvider
    public long getServerTime() {
        return this.serverTime;
    }

    public final Map<String, Object> getSvrTimeParams() {
        Object objCreateFailure;
        HashMap map = new HashMap();
        Object obj = this.svrTiming;
        if (obj != null) {
            try {
                if (obj instanceof Map) {
                    for (Map.Entry entry : ((Map) obj).entrySet()) {
                        Object key = entry.getKey();
                        String str = key instanceof String ? (String) key : null;
                        Object value = entry.getValue();
                        if (str != null && value != null) {
                            map.put(str, value);
                        }
                    }
                }
                objCreateFailure = Unit.INSTANCE;
                Result.m17090constructorimpl(objCreateFailure);
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
                Result.m17090constructorimpl(objCreateFailure);
            }
            Result.m17089boximpl(objCreateFailure);
        }
        return map;
    }

    public final Object getSvrTiming() {
        return this.svrTiming;
    }

    public int hashCode() {
        int iHashCode = ((this.ret.hashCode() * 31) + this.errmsg.hashCode()) * 31;
        T t = this.data;
        int iHashCode2 = t == null ? 0 : t.hashCode();
        long j = this.serverTime;
        int iHashCode3 = (((((iHashCode + iHashCode2) * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.logId.hashCode()) * 31;
        Object obj = this.svrTiming;
        return iHashCode3 + (obj != null ? obj.hashCode() : 0);
    }

    public final boolean noFailed() {
        return success() || processing();
    }

    public final boolean processing() {
        return Intrinsics.areEqual(this.ret, "1000") || Intrinsics.areEqual(this.ret, "3100") || Intrinsics.areEqual(this.ret, "-1") || Intrinsics.areEqual(this.ret, "-5") || Intrinsics.areEqual(this.ret, "1014");
    }

    public final boolean success() {
        return Intrinsics.areEqual(this.ret, "0");
    }

    public String toString() {
        return "Response(ret=" + this.ret + ", errmsg=" + this.errmsg + ", data=" + this.data + ", serverTime=" + this.serverTime + ", logId=" + this.logId + ", svrTiming=" + this.svrTiming + ')';
    }
}
package com.vega.adapi.data.compliance;

import androidx.core.app.NotificationCompat;
import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

@Serializable
/* loaded from: classes15.dex */
public final class ComplianceNetRequest {
    public static final Companion Companion = new Companion();

    @SerializedName("ad_platform")
    public final String adPlatform;

    @SerializedName("aggregation_platform")
    public final String aggregationPlatform;

    @SerializedName("screen_shot_enable")
    public final Boolean isAdScreenShot;

    @SerializedName("material_url")
    public final String materialUrl;

    @SerializedName("mediation_source")
    public final String mediationSource;

    @SerializedName("scene")
    public final String scene;

    @SerializedName("title")
    public final String title;

    @SerializedName("ts")
    public final Long ts;

    @SerializedName("uid")
    public final String uid;

    public static final class Companion {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v9, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public ComplianceNetRequest() {
        this((String) null, (String) (0 == true ? 1 : 0), (String) (0 == true ? 1 : 0), (String) (0 == true ? 1 : 0), (String) (0 == true ? 1 : 0), (Long) (0 == true ? 1 : 0), (String) (0 == true ? 1 : 0), (String) (0 == true ? 1 : 0), (Boolean) (0 == true ? 1 : 0), 511, (DefaultConstructorMarker) (0 == true ? 1 : 0));
    }

    public ComplianceNetRequest(int i, String str, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Boolean bool, SerializationConstructorMarker serializationConstructorMarker) {
        if (0 != 0) {
            ComplianceNetRequest$$serializer.f66164a.getClass();
            PluginExceptionsKt.throwMissingFieldException(i, 0, ComplianceNetRequest$$serializer.b);
        }
        this.mediationSource = (i & 1) == 0 ? "capcut" : str;
        if ((i & 2) == 0) {
            this.aggregationPlatform = "";
        } else {
            this.aggregationPlatform = str2;
        }
        if ((i & 4) == 0) {
            this.adPlatform = "";
        } else {
            this.adPlatform = str3;
        }
        if ((i & 8) == 0) {
            this.title = "";
        } else {
            this.title = str4;
        }
        if ((i & 16) == 0) {
            this.materialUrl = "";
        } else {
            this.materialUrl = str5;
        }
        if ((i & 32) == 0) {
            this.ts = null;
        } else {
            this.ts = l;
        }
        if ((i & 64) == 0) {
            this.uid = null;
        } else {
            this.uid = str6;
        }
        if ((i & NotificationCompat.FLAG_HIGH_PRIORITY) == 0) {
            this.scene = null;
        } else {
            this.scene = str7;
        }
        if ((i & 256) == 0) {
            this.isAdScreenShot = null;
        } else {
            this.isAdScreenShot = bool;
        }
    }

    public ComplianceNetRequest(String str, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        this.mediationSource = str;
        this.aggregationPlatform = str2;
        this.adPlatform = str3;
        this.title = str4;
        this.materialUrl = str5;
        this.ts = l;
        this.uid = str6;
        this.scene = str7;
        this.isAdScreenShot = bool;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x003c: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x000c: ARITH (r21v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("capcut") : (r12v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0012: ARITH (r21v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? ("") : (r13v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0019: ARITH (r21v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? ("") : (r14v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x001e: ARITH (r21v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? ("") : (r15v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0023: ARITH (r21v0 int) & (16 int) A[WRAPPED]) == (0 int)) ? (r16v0 java.lang.String) : (""))
      (wrap:java.lang.Long:?: TERNARY null = ((wrap:int:0x0027: ARITH (r21v0 int) & (32 int) A[WRAPPED]) != (0 int)) ? (null java.lang.Long) : (r17v0 java.lang.Long))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x002d: ARITH (r21v0 int) & (64 int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r18v0 java.lang.String))
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0032: ARITH (r21v0 int) & (wrap:??:SGET  A[WRAPPED] androidx.core.app.NotificationCompat.FLAG_HIGH_PRIORITY int) A[WRAPPED]) != (0 int)) ? (null java.lang.String) : (r19v0 java.lang.String))
      (wrap:java.lang.Boolean:?: TERNARY null = ((wrap:int:0x0037: ARITH (r21v0 int) & (256 int) A[WRAPPED]) == (0 int)) ? (r20v0 java.lang.Boolean) : (null java.lang.Boolean))
     A[MD:(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean):void (m)] call: com.vega.adapi.data.compliance.ComplianceNetRequest.<init>(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.String, java.lang.String, java.lang.Boolean):void type: THIS */
    public /* synthetic */ ComplianceNetRequest(String str, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Boolean bool, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "capcut" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) == 0 ? str5 : "", (i & 32) != 0 ? null : l, (i & 64) != 0 ? null : str6, (i & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? null : str7, (i & 256) == 0 ? bool : null);
    }

    public static /* synthetic */ ComplianceNetRequest copy$default(ComplianceNetRequest complianceNetRequest, String str, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Boolean bool, int i, Object obj) {
        if ((i & 1) != 0) {
            str = complianceNetRequest.mediationSource;
        }
        if ((i & 2) != 0) {
            str2 = complianceNetRequest.aggregationPlatform;
        }
        if ((i & 4) != 0) {
            str3 = complianceNetRequest.adPlatform;
        }
        if ((i & 8) != 0) {
            str4 = complianceNetRequest.title;
        }
        if ((i & 16) != 0) {
            str5 = complianceNetRequest.materialUrl;
        }
        if ((i & 32) != 0) {
            l = complianceNetRequest.ts;
        }
        if ((i & 64) != 0) {
            str6 = complianceNetRequest.uid;
        }
        if ((i & NotificationCompat.FLAG_HIGH_PRIORITY) != 0) {
            str7 = complianceNetRequest.scene;
        }
        if ((i & 256) != 0) {
            bool = complianceNetRequest.isAdScreenShot;
        }
        return complianceNetRequest.copy(str, str2, str3, str4, str5, l, str6, str7, bool);
    }

    public static final /* synthetic */ void write$Self$libadapi_release(ComplianceNetRequest complianceNetRequest, CompositeEncoder compositeEncoder, SerialDescriptor serialDescriptor) {
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 0) || !Intrinsics.areEqual(complianceNetRequest.mediationSource, "capcut")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 0, complianceNetRequest.mediationSource);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 1) || !Intrinsics.areEqual(complianceNetRequest.aggregationPlatform, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 1, complianceNetRequest.aggregationPlatform);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 2) || !Intrinsics.areEqual(complianceNetRequest.adPlatform, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 2, complianceNetRequest.adPlatform);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 3) || !Intrinsics.areEqual(complianceNetRequest.title, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 3, complianceNetRequest.title);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 4) || !Intrinsics.areEqual(complianceNetRequest.materialUrl, "")) {
            compositeEncoder.encodeStringElement(serialDescriptor, 4, complianceNetRequest.materialUrl);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 5) || complianceNetRequest.ts != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 5, LongSerializer.INSTANCE, complianceNetRequest.ts);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 6) || complianceNetRequest.uid != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, complianceNetRequest.uid);
        }
        if (compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 7) || complianceNetRequest.scene != null) {
            compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 7, StringSerializer.INSTANCE, complianceNetRequest.scene);
        }
        if (!compositeEncoder.shouldEncodeElementDefault(serialDescriptor, 8) && complianceNetRequest.isAdScreenShot == null) {
            return;
        }
        compositeEncoder.encodeNullableSerializableElement(serialDescriptor, 8, BooleanSerializer.INSTANCE, complianceNetRequest.isAdScreenShot);
    }

    public final ComplianceNetRequest copy(String str, String str2, String str3, String str4, String str5, Long l, String str6, String str7, Boolean bool) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        return new ComplianceNetRequest(str, str2, str3, str4, str5, l, str6, str7, bool);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComplianceNetRequest)) {
            return false;
        }
        ComplianceNetRequest complianceNetRequest = (ComplianceNetRequest) obj;
        return Intrinsics.areEqual(this.mediationSource, complianceNetRequest.mediationSource) && Intrinsics.areEqual(this.aggregationPlatform, complianceNetRequest.aggregationPlatform) && Intrinsics.areEqual(this.adPlatform, complianceNetRequest.adPlatform) && Intrinsics.areEqual(this.title, complianceNetRequest.title) && Intrinsics.areEqual(this.materialUrl, complianceNetRequest.materialUrl) && Intrinsics.areEqual(this.ts, complianceNetRequest.ts) && Intrinsics.areEqual(this.uid, complianceNetRequest.uid) && Intrinsics.areEqual(this.scene, complianceNetRequest.scene) && Intrinsics.areEqual(this.isAdScreenShot, complianceNetRequest.isAdScreenShot);
    }

    public final String getAdPlatform() {
        return this.adPlatform;
    }

    public final String getAggregationPlatform() {
        return this.aggregationPlatform;
    }

    public final String getMaterialUrl() {
        return this.materialUrl;
    }

    public final String getMediationSource() {
        return this.mediationSource;
    }

    public final String getScene() {
        return this.scene;
    }

    public final String getTitle() {
        return this.title;
    }

    public final Long getTs() {
        return this.ts;
    }

    public final String getUid() {
        return this.uid;
    }

    public int hashCode() {
        int iHashCode = ((((((((this.mediationSource.hashCode() * 31) + this.aggregationPlatform.hashCode()) * 31) + this.adPlatform.hashCode()) * 31) + this.title.hashCode()) * 31) + this.materialUrl.hashCode()) * 31;
        Long l = this.ts;
        int iHashCode2 = (iHashCode + (l == null ? 0 : l.hashCode())) * 31;
        String str = this.uid;
        int iHashCode3 = (iHashCode2 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.scene;
        int iHashCode4 = (iHashCode3 + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.isAdScreenShot;
        return iHashCode4 + (bool != null ? bool.hashCode() : 0);
    }

    public final Boolean isAdScreenShot() {
        return this.isAdScreenShot;
    }

    public String toString() {
        return "ComplianceNetRequest(mediationSource=" + this.mediationSource + ", aggregationPlatform=" + this.aggregationPlatform + ", adPlatform=" + this.adPlatform + ", title=" + this.title + ", materialUrl=" + this.materialUrl + ", ts=" + this.ts + ", uid=" + this.uid + ", scene=" + this.scene + ", isAdScreenShot=" + this.isAdScreenShot + ')';
    }
}
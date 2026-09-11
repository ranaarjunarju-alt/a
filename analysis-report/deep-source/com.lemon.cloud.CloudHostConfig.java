package com.lemon.cloud;

import com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider;
import com.google.gson.annotations.SerializedName;
import com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes34.dex */
public final class CloudHostConfig {

    /* loaded from: classes15.dex */
    public static final class CloudBannerConfig implements IDefaultValueProvider<CloudBannerConfig> {

        @SerializedName("cloud_banner_list")
        public final List<CloudBannerItem> group;

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        public CloudBannerConfig() {
            this(null, 1, 0 == true ? 1 : 0);
        }

        public CloudBannerConfig(List<CloudBannerItem> list) {
            Intrinsics.checkNotNullParameter(list, "");
            this.group = list;
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0008: CONSTRUCTOR 
          (wrap:java.util.List:?: TERNARY null = ((wrap:int:0x0000: ARITH (r3v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (wrap:java.util.List:0x0004: INVOKE  STATIC call: kotlin.collections.CollectionsKt__CollectionsKt.emptyList():java.util.List A[MD:<T>:():java.util.List<T> (m), WRAPPED]) : (r2v0 java.util.List))
         A[MD:(java.util.List<com.lemon.cloud.CloudHostConfig$CloudBannerItem>):void (m)] call: com.lemon.cloud.CloudHostConfig.CloudBannerConfig.<init>(java.util.List):void type: THIS */
        public /* synthetic */ CloudBannerConfig(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
        }

        public final List<CloudBannerItem> a() {
            return this.group;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider
        public final CloudBannerConfig create() {
            return new CloudBannerConfig(null, 1, 0 == true ? 1 : 0);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof CloudBannerConfig) && Intrinsics.areEqual(this.group, ((CloudBannerConfig) obj).group);
        }

        public final int hashCode() {
            return this.group.hashCode();
        }

        public final String toString() {
            return "CloudBannerConfig(group=" + this.group + ')';
        }
    }

    /* loaded from: classes30.dex */
    public static final class CloudBannerItem {

        @SerializedName("cloud_title")
        public final String cloudTitle;

        @SerializedName("cloud_url")
        public final String cloudUrl;

        @SerializedName("is_owner")
        public final boolean isOwner;

        /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
        /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
        /* JADX WARN: Multi-variable type inference failed */
        public CloudBannerItem() {
            this(null, 0 == true ? 1 : 0, false, 7, 0 == true ? 1 : 0);
        }

        public CloudBannerItem(String str, String str2, boolean z) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(str2, "");
            this.cloudTitle = str;
            this.cloudUrl = str2;
            this.isOwner = z;
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0011: CONSTRUCTOR 
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0000: ARITH (r6v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (wrap:java.lang.String:0x0006: SGET  A[WRAPPED] com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity.V1 java.lang.String) : (r3v0 java.lang.String))
          (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0007: ARITH (r6v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (wrap:java.lang.String:0x000b: SGET  A[WRAPPED] com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity.V1 java.lang.String) : (r4v0 java.lang.String))
          (wrap:boolean:?: TERNARY null = ((wrap:int:0x000c: ARITH (r6v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? false : (r5v0 boolean))
         A[MD:(java.lang.String, java.lang.String, boolean):void (m)] call: com.lemon.cloud.CloudHostConfig.CloudBannerItem.<init>(java.lang.String, java.lang.String, boolean):void type: THIS */
        public /* synthetic */ CloudBannerItem(String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? BusinessPhotoTemplateOptEntity.V1 : str, (i & 2) != 0 ? BusinessPhotoTemplateOptEntity.V1 : str2, (i & 4) != 0 ? false : z);
        }

        public final String a() {
            return this.cloudTitle;
        }

        public final String b() {
            return this.cloudUrl;
        }

        public final boolean c() {
            return this.isOwner;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof CloudBannerItem)) {
                return false;
            }
            CloudBannerItem cloudBannerItem = (CloudBannerItem) obj;
            return Intrinsics.areEqual(this.cloudTitle, cloudBannerItem.cloudTitle) && Intrinsics.areEqual(this.cloudUrl, cloudBannerItem.cloudUrl) && this.isOwner == cloudBannerItem.isOwner;
        }

        public final int hashCode() {
            return (((this.cloudTitle.hashCode() * 31) + this.cloudUrl.hashCode()) * 31) + (this.isOwner ? 1231 : 1237);
        }

        public final String toString() {
            return "CloudBannerItem(cloudTitle=" + this.cloudTitle + ", cloudUrl=" + this.cloudUrl + ", isOwner=" + this.isOwner + ')';
        }
    }
}
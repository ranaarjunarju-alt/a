package com.lemon.lv.config;

import com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider;
import com.google.gson.annotations.SerializedName;
import com.xt.retouch.abtest.bean.BusinessPhotoTemplateOptEntity;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes25.dex */
public final class ElevenLabsTonePlatformConfig implements IDefaultValueProvider<ElevenLabsTonePlatformConfig> {

    @SerializedName("group")
    public final String group;

    /* JADX WARN: Multi-variable type inference failed */
    public ElevenLabsTonePlatformConfig() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public ElevenLabsTonePlatformConfig(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.group = str;
    }

    public /* synthetic */ ElevenLabsTonePlatformConfig(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "v0" : str);
    }

    public static /* synthetic */ ElevenLabsTonePlatformConfig copy$default(ElevenLabsTonePlatformConfig elevenLabsTonePlatformConfig, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = elevenLabsTonePlatformConfig.group;
        }
        return elevenLabsTonePlatformConfig.copy(str);
    }

    public final ElevenLabsTonePlatformConfig copy(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        return new ElevenLabsTonePlatformConfig(str);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider
    public ElevenLabsTonePlatformConfig create() {
        return new ElevenLabsTonePlatformConfig(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof ElevenLabsTonePlatformConfig) && Intrinsics.areEqual(this.group, ((ElevenLabsTonePlatformConfig) obj).group);
    }

    public final String getGroup() {
        return this.group;
    }

    public int hashCode() {
        return this.group.hashCode();
    }

    public final boolean isEnabled() {
        return Intrinsics.areEqual(this.group, BusinessPhotoTemplateOptEntity.V1);
    }

    public String toString() {
        return "ElevenLabsTonePlatformConfig(group=" + this.group + ')';
    }
}
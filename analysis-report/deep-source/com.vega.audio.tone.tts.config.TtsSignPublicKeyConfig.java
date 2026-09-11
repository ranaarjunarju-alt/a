package com.vega.audio.tone.tts.config;

import com.google.gson.annotations.SerializedName;
import com.vega.config.IConfig;
import com.vega.config.IConfigInterceptor;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class TtsSignPublicKeyConfig implements IConfig<TtsSignPublicKeyConfig> {

    @SerializedName("key")
    public final String key;

    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public TtsSignPublicKeyConfig() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public TtsSignPublicKeyConfig(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.key = str;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0006: CONSTRUCTOR 
      (wrap:java.lang.String:?: TERNARY null = ((wrap:int:0x0000: ARITH (r3v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? ("-----BEGIN PUBLIC KEY-----
    MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTd34Lw4b7IuldSXh/zY
    CMla+ITdGG5TeWz6ad+OySd4r+IrY45AoqrYUxhQ2dl+7z+i7r/5vEa8rr39BYfB
    8AGMQLmZA8HmgpWBsqrn/V6daUALkKnkLb70Fn32CJigIuGXAYqxUdGuI340aC+0
    v5Es3puJsHyzf01/AelE4Cdc6bZhQrASJLBh8R3BQToYClmDVSDUQk28o8sl/guA
    Z4n303Vj+6Siv1HayPCdV6kpVVnMBAG4+umUbwGmn132N3fgpzLarFF3XyWmS1zh
    D/J07iM/rP8GDO9IskHNHd2phrO0G6KzrcFAnTBHjVv+hCBEfzN/no3FNA9AuC36
    mwIDAQAB
    -----END PUBLIC KEY-----") : (r2v0 java.lang.String))
     A[MD:(java.lang.String):void (m)] call: com.vega.audio.tone.tts.config.TtsSignPublicKeyConfig.<init>(java.lang.String):void type: THIS */
    public /* synthetic */ TtsSignPublicKeyConfig(String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "-----BEGIN PUBLIC KEY-----\nMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmTd34Lw4b7IuldSXh/zY\nCMla+ITdGG5TeWz6ad+OySd4r+IrY45AoqrYUxhQ2dl+7z+i7r/5vEa8rr39BYfB\n8AGMQLmZA8HmgpWBsqrn/V6daUALkKnkLb70Fn32CJigIuGXAYqxUdGuI340aC+0\nv5Es3puJsHyzf01/AelE4Cdc6bZhQrASJLBh8R3BQToYClmDVSDUQk28o8sl/guA\nZ4n303Vj+6Siv1HayPCdV6kpVVnMBAG4+umUbwGmn132N3fgpzLarFF3XyWmS1zh\nD/J07iM/rP8GDO9IskHNHd2phrO0G6KzrcFAnTBHjVv+hCBEfzN/no3FNA9AuC36\nmwIDAQAB\n-----END PUBLIC KEY-----" : str);
    }

    public final String a() {
        return this.key;
    }

    @Override // com.bytedance.news.common.settings.api.annotation.IDefaultValueProvider
    public final Object create() {
        return IConfig.DefaultImpls.a(this);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof TtsSignPublicKeyConfig) && Intrinsics.areEqual(this.key, ((TtsSignPublicKeyConfig) obj).key);
    }

    public final int hashCode() {
        return this.key.hashCode();
    }

    @Override // com.vega.config.IConfig
    public final IConfigInterceptor interceptor() {
        return null;
    }

    public final String toString() {
        return "TtsSignPublicKeyConfig(key=" + this.key + ')';
    }
}
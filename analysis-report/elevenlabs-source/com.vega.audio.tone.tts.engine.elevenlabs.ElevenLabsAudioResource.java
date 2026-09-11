package com.vega.audio.tone.tts.engine.elevenlabs;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes38.dex */
public final class ElevenLabsAudioResource {

    @SerializedName("code")
    public final String code;

    @SerializedName("msg")
    public final String msg;

    @SerializedName("url")
    public final String resourceUrl;

    @SerializedName("text")
    public final String text;

    @SerializedName("text_lan")
    public final String textLan;

    /* JADX WARN: Multi-variable type inference failed */
    public ElevenLabsAudioResource() {
        this(null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 31, 0 == true ? 1 : 0);
    }

    public ElevenLabsAudioResource(String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        this.text = str;
        this.resourceUrl = str2;
        this.code = str3;
        this.msg = str4;
        this.textLan = str5;
    }

    public /* synthetic */ ElevenLabsAudioResource(String str, String str2, String str3, String str4, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) == 0 ? str5 : "");
    }

    public final String a() {
        return this.code;
    }

    public final String b() {
        return this.msg;
    }

    public final String c() {
        return this.resourceUrl;
    }

    public final String d() {
        return this.text;
    }

    public final String e() {
        return this.textLan;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElevenLabsAudioResource)) {
            return false;
        }
        ElevenLabsAudioResource elevenLabsAudioResource = (ElevenLabsAudioResource) obj;
        return Intrinsics.areEqual(this.text, elevenLabsAudioResource.text) && Intrinsics.areEqual(this.resourceUrl, elevenLabsAudioResource.resourceUrl) && Intrinsics.areEqual(this.code, elevenLabsAudioResource.code) && Intrinsics.areEqual(this.msg, elevenLabsAudioResource.msg) && Intrinsics.areEqual(this.textLan, elevenLabsAudioResource.textLan);
    }

    public final int hashCode() {
        return (((((((this.text.hashCode() * 31) + this.resourceUrl.hashCode()) * 31) + this.code.hashCode()) * 31) + this.msg.hashCode()) * 31) + this.textLan.hashCode();
    }

    public final String toString() {
        return "ElevenLabsAudioResource(text=" + this.text + ", resourceUrl=" + this.resourceUrl + ", code=" + this.code + ", msg=" + this.msg + ", textLan=" + this.textLan + ')';
    }
}
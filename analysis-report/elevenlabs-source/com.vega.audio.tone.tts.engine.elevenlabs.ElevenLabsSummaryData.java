package com.vega.audio.tone.tts.engine.elevenlabs;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ElevenLabsSummaryData {

    /* renamed from: a, reason: collision with root package name */
    public final ElevenLabsErrorCode f74375a;
    public final String b;

    /* renamed from: c, reason: collision with root package name */
    public final String f74376c;

    /* renamed from: d, reason: collision with root package name */
    public final String f74377d;
    public final String e;
    public final String f;

    public ElevenLabsSummaryData(ElevenLabsErrorCode elevenLabsErrorCode, String str, String str2, String str3, String str4, String str5) {
        Intrinsics.checkNotNullParameter(elevenLabsErrorCode, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        this.f74375a = elevenLabsErrorCode;
        this.b = str;
        this.f74376c = str2;
        this.f74377d = str3;
        this.e = str4;
        this.f = str5;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElevenLabsSummaryData)) {
            return false;
        }
        ElevenLabsSummaryData elevenLabsSummaryData = (ElevenLabsSummaryData) obj;
        return this.f74375a == elevenLabsSummaryData.f74375a && Intrinsics.areEqual(this.b, elevenLabsSummaryData.b) && Intrinsics.areEqual(this.f74376c, elevenLabsSummaryData.f74376c) && Intrinsics.areEqual(this.f74377d, elevenLabsSummaryData.f74377d) && Intrinsics.areEqual(this.e, elevenLabsSummaryData.e) && Intrinsics.areEqual(this.f, elevenLabsSummaryData.f);
    }

    public final int hashCode() {
        return (((((((((this.f74375a.hashCode() * 31) + this.b.hashCode()) * 31) + this.f74376c.hashCode()) * 31) + this.f74377d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    public final String toString() {
        return "ElevenLabsSummaryData(errorCode=" + this.f74375a + ", text=" + this.b + ", audioFileName=" + this.f74376c + ", audioFilePath=" + this.f74377d + ", errorMessage=" + this.e + ", textLan=" + this.f + ')';
    }
}
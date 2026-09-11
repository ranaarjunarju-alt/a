package com.vega.audio.tone.tts.engine.elevenlabs;

import X.C06W;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes26.dex */
public final class ElevenLabsErrorCode {

    /* renamed from: a, reason: collision with root package name */
    public static final ElevenLabsErrorCode f74372a;
    public static final ElevenLabsErrorCode b;

    /* renamed from: c, reason: collision with root package name */
    public static final ElevenLabsErrorCode f74373c;

    /* renamed from: d, reason: collision with root package name */
    public static final /* synthetic */ ElevenLabsErrorCode[] f74374d;

    static {
        ElevenLabsErrorCode elevenLabsErrorCode = new ElevenLabsErrorCode("SUCCESS", 0);
        f74372a = elevenLabsErrorCode;
        ElevenLabsErrorCode elevenLabsErrorCode2 = new ElevenLabsErrorCode("FAILED", 1);
        b = elevenLabsErrorCode2;
        ElevenLabsErrorCode elevenLabsErrorCode3 = new ElevenLabsErrorCode("CANCELLED", 2);
        f74373c = elevenLabsErrorCode3;
        ElevenLabsErrorCode[] elevenLabsErrorCodeArr = {elevenLabsErrorCode, elevenLabsErrorCode2, elevenLabsErrorCode3};
        f74374d = elevenLabsErrorCodeArr;
        C06W.a(elevenLabsErrorCodeArr);
    }

    public ElevenLabsErrorCode(String str, int i) {
    }

    public static ElevenLabsErrorCode valueOf(String str) {
        return (ElevenLabsErrorCode) Enum.valueOf(ElevenLabsErrorCode.class, str);
    }

    public static ElevenLabsErrorCode[] values() {
        return (ElevenLabsErrorCode[]) f74374d.clone();
    }
}
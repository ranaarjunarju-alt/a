package com.vega.audio.tone.tts.engine.elevenlabs;

import X.C06W;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes40.dex */
public final class ElevenLabsAudioTaskResultDataStatus {

    /* renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ ElevenLabsAudioTaskResultDataStatus[] f74371a;

    static {
        ElevenLabsAudioTaskResultDataStatus[] elevenLabsAudioTaskResultDataStatusArr = {new ElevenLabsAudioTaskResultDataStatus("SUCCEEDED", 0, 0), new ElevenLabsAudioTaskResultDataStatus("FAILED", 1, -1), new ElevenLabsAudioTaskResultDataStatus("PROCESSIONG", 2, 1)};
        f74371a = elevenLabsAudioTaskResultDataStatusArr;
        C06W.a(elevenLabsAudioTaskResultDataStatusArr);
    }

    public ElevenLabsAudioTaskResultDataStatus(String str, int i, int i2) {
    }

    public static ElevenLabsAudioTaskResultDataStatus valueOf(String str) {
        return (ElevenLabsAudioTaskResultDataStatus) Enum.valueOf(ElevenLabsAudioTaskResultDataStatus.class, str);
    }

    public static ElevenLabsAudioTaskResultDataStatus[] values() {
        return (ElevenLabsAudioTaskResultDataStatus[]) f74371a.clone();
    }
}
package com.vega.audio.tone.tts.core;

import X.C06W;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes37.dex */
public final class TextToSpeechTaskType {

    /* renamed from: a, reason: collision with root package name */
    public static final TextToSpeechTaskType f74307a;
    public static final TextToSpeechTaskType b;

    /* renamed from: c, reason: collision with root package name */
    public static final /* synthetic */ TextToSpeechTaskType[] f74308c;

    static {
        TextToSpeechTaskType textToSpeechTaskType = new TextToSpeechTaskType("ReadingSpeech", 0);
        f74307a = textToSpeechTaskType;
        TextToSpeechTaskType textToSpeechTaskType2 = new TextToSpeechTaskType("SavingSpeech", 1);
        b = textToSpeechTaskType2;
        TextToSpeechTaskType[] textToSpeechTaskTypeArr = {textToSpeechTaskType, textToSpeechTaskType2};
        f74308c = textToSpeechTaskTypeArr;
        C06W.a(textToSpeechTaskTypeArr);
    }

    public TextToSpeechTaskType(String str, int i) {
    }

    public static TextToSpeechTaskType valueOf(String str) {
        return (TextToSpeechTaskType) Enum.valueOf(TextToSpeechTaskType.class, str);
    }

    public static TextToSpeechTaskType[] values() {
        return (TextToSpeechTaskType[]) f74308c.clone();
    }
}
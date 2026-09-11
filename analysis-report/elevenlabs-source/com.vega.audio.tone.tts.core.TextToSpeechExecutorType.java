package com.vega.audio.tone.tts.core;

import X.C06W;
import kotlin.NoWhenBranchMatchedException;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes34.dex */
public final class TextToSpeechExecutorType {

    /* renamed from: a, reason: collision with root package name */
    public static final TextToSpeechExecutorType f74291a;
    public static final TextToSpeechExecutorType b;

    /* renamed from: c, reason: collision with root package name */
    public static final TextToSpeechExecutorType f74292c;

    /* renamed from: d, reason: collision with root package name */
    public static final TextToSpeechExecutorType f74293d;
    public static final TextToSpeechExecutorType e;
    public static final TextToSpeechExecutorType f;

    /* renamed from: g, reason: collision with root package name */
    public static final TextToSpeechExecutorType f74294g;
    public static final /* synthetic */ TextToSpeechExecutorType[] h;

    /* loaded from: classes30.dex */
    public /* synthetic */ class WhenMappings {
        static {
            TextToSpeechExecutorType.values();
        }
    }

    static {
        TextToSpeechExecutorType textToSpeechExecutorType = new TextToSpeechExecutorType("SAMI", 0);
        f74291a = textToSpeechExecutorType;
        TextToSpeechExecutorType textToSpeechExecutorType2 = new TextToSpeechExecutorType("MICROSOFT", 1);
        b = textToSpeechExecutorType2;
        TextToSpeechExecutorType textToSpeechExecutorType3 = new TextToSpeechExecutorType("REMOTE_SAMI", 2);
        f74292c = textToSpeechExecutorType3;
        TextToSpeechExecutorType textToSpeechExecutorType4 = new TextToSpeechExecutorType("ELEVEN_LABS", 3);
        f74293d = textToSpeechExecutorType4;
        TextToSpeechExecutorType textToSpeechExecutorType5 = new TextToSpeechExecutorType("SSML", 4);
        e = textToSpeechExecutorType5;
        TextToSpeechExecutorType textToSpeechExecutorType6 = new TextToSpeechExecutorType("MOYIN", 5);
        f = textToSpeechExecutorType6;
        TextToSpeechExecutorType textToSpeechExecutorType7 = new TextToSpeechExecutorType("QWEN", 6);
        f74294g = textToSpeechExecutorType7;
        TextToSpeechExecutorType[] textToSpeechExecutorTypeArr = {textToSpeechExecutorType, textToSpeechExecutorType2, textToSpeechExecutorType3, textToSpeechExecutorType4, textToSpeechExecutorType5, textToSpeechExecutorType6, textToSpeechExecutorType7};
        h = textToSpeechExecutorTypeArr;
        C06W.a(textToSpeechExecutorTypeArr);
    }

    public TextToSpeechExecutorType(String str, int i) {
    }

    public static TextToSpeechExecutorType valueOf(String str) {
        return (TextToSpeechExecutorType) Enum.valueOf(TextToSpeechExecutorType.class, str);
    }

    public static TextToSpeechExecutorType[] values() {
        return (TextToSpeechExecutorType[]) h.clone();
    }

    public final String a() {
        switch (ordinal()) {
            case 0:
            case 2:
                return "sami";
            case 1:
                return "microsoft";
            case 3:
                return "11labs";
            case 4:
                return "ssml";
            case 5:
                return "moyin";
            case 6:
                return "qwen";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String b() {
        switch (ordinal()) {
            case 0:
            case 2:
                return "sami_stream_tts";
            case 1:
                return "microsoft_stream_tts";
            case 3:
                return "11labs_stream_tts";
            case 4:
                return "";
            case 5:
                return "moyin_stream_tts";
            case 6:
                return "qwen_stream_tts";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public final String c() {
        int iOrdinal = ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal == 1) {
                return "microsoft_stream_tts_v2";
            }
            if (iOrdinal != 2) {
                return iOrdinal != 3 ? iOrdinal != 5 ? iOrdinal != 6 ? "" : "qwen_stream_tts_v2" : "moyin_stream_tts_v2" : "11labs_stream_tts_v2";
            }
        }
        return "sami_stream_tts_v2";
    }
}
package com.vega.audio.tone.tts.core;

import com.vega.audio.tone.tts.TextToSpeechTaskManager$wrapToTask$1;
import com.vega.audio.tone.tts.config.TtsMigrationConfig;
import com.vega.audio.tone.tts.engine.nonstreaming.NonStreamingToneUtils;
import com.vega.edit.base.tone.EmotionOption;
import java.util.HashMap;
import java.util.List;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* loaded from: classes11.dex */
public final class TextToSpeechTask {

    /* renamed from: a, reason: collision with root package name */
    public final String f74297a;
    public final String b;

    /* renamed from: c, reason: collision with root package name */
    public final String f74298c;

    /* renamed from: d, reason: collision with root package name */
    public final TextToSpeechTaskType f74299d;
    public final TextToSpeechExecutorType e;
    public final int f;

    /* renamed from: g, reason: collision with root package name */
    public final float f74300g;
    public final int h;
    public final boolean i;
    public TextToSpeechTaskState j;
    public final String k;
    public final TextToSpeechListener l;
    public final String m;
    public final String n;
    public final boolean o;
    public final boolean p;
    public final EmotionOption q;
    public final String r;
    public final String s;
    public final boolean t;
    public final IntRange u;
    public final List<HashMap<String, String>> v;
    public final String w;

    public static final class Companion {
    }

    static {
        new Companion();
    }

    public TextToSpeechTask() {
        throw null;
    }

    public TextToSpeechTask(String str, String str2, String str3, TextToSpeechTaskType textToSpeechTaskType, TextToSpeechExecutorType textToSpeechExecutorType, int i, float f, int i2, boolean z, String str4, TextToSpeechTaskManager$wrapToTask$1 textToSpeechTaskManager$wrapToTask$1, String str5, String str6, boolean z2, boolean z3, EmotionOption emotionOption, String str7, String str8, boolean z4, IntRange intRange, List list, String str9) {
        TextToSpeechTaskState textToSpeechTaskState = TextToSpeechTaskState.f74304a;
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(textToSpeechTaskType, "");
        Intrinsics.checkNotNullParameter(textToSpeechExecutorType, "");
        Intrinsics.checkNotNullParameter(textToSpeechTaskState, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        this.f74297a = str;
        this.b = str2;
        this.f74298c = str3;
        this.f74299d = textToSpeechTaskType;
        this.e = textToSpeechExecutorType;
        this.f = i;
        this.f74300g = f;
        this.h = i2;
        this.i = z;
        this.j = textToSpeechTaskState;
        this.k = str4;
        this.l = textToSpeechTaskManager$wrapToTask$1;
        this.m = str5;
        this.n = str6;
        this.o = z2;
        this.p = z3;
        this.q = emotionOption;
        this.r = str7;
        this.s = str8;
        this.t = z4;
        this.u = intRange;
        this.v = list;
        this.w = str9;
    }

    public final String a() {
        boolean zD;
        NonStreamingToneUtils nonStreamingToneUtils = NonStreamingToneUtils.f74397a;
        TextToSpeechExecutorType textToSpeechExecutorType = this.e;
        nonStreamingToneUtils.getClass();
        Intrinsics.checkNotNullParameter(textToSpeechExecutorType, "");
        switch (textToSpeechExecutorType.ordinal()) {
            case 0:
                zD = ((TtsMigrationConfig) NonStreamingToneUtils.b.getValue()).d();
                break;
            case 1:
                zD = ((TtsMigrationConfig) NonStreamingToneUtils.b.getValue()).b();
                break;
            case 2:
                zD = ((TtsMigrationConfig) NonStreamingToneUtils.b.getValue()).c();
                break;
            case 3:
                zD = ((TtsMigrationConfig) NonStreamingToneUtils.b.getValue()).a();
                break;
            case 4:
                zD = ((TtsMigrationConfig) NonStreamingToneUtils.b.getValue()).e();
                break;
            case 5:
            case 6:
                zD = true;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        if (zD) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.b);
            sb.append(this.f74298c);
            sb.append(this.e);
            sb.append(this.f);
            sb.append(this.h);
            sb.append(this.i);
            EmotionOption emotionOption = this.q;
            sb.append(emotionOption != null ? emotionOption.toString() : null);
            return sb.toString();
        }
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.b);
        sb2.append(this.f74298c);
        sb2.append(this.e);
        sb2.append(this.f);
        sb2.append(this.h);
        sb2.append(this.i);
        sb2.append(this.f74300g);
        EmotionOption emotionOption2 = this.q;
        sb2.append(emotionOption2 != null ? emotionOption2.toString() : null);
        return sb2.toString();
    }
}
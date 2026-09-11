package com.vega.edit.base.tone;

import androidx.core.app.NotificationCompat;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.ReadingListener;
import com.vega.aigcapi.materialgenerate.TtsResult;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes22.dex */
public final class TextToSpeechIntent {
    public final Boolean A;
    public final Boolean B;
    public final boolean C;
    public final boolean D;
    public final boolean E;
    public final boolean F;
    public final IntRange G;
    public final List<HashMap<String, String>> H;
    public final Map<String, String> I;

    /* renamed from: J, reason: collision with root package name */
    public final Function0<Unit> f88864J;
    public final boolean K;

    /* renamed from: a, reason: collision with root package name */
    public final String f88865a;
    public final TextInfo b;

    /* renamed from: c, reason: collision with root package name */
    public final String f88866c;

    /* renamed from: d, reason: collision with root package name */
    public final String f88867d;
    public final String e;
    public final TTSBusinessScene f;

    /* renamed from: g, reason: collision with root package name */
    public final TTSBusinessType f88868g;
    public final String h;
    public final String i;
    public final float j;
    public final int k;
    public final ReadingListener l;
    public final Map<String, Object> m;
    public final boolean n;
    public final String o;
    public final String p;
    public final boolean q;
    public final String r;
    public final String s;
    public final Function2<TtsResult, TextToAudioInfo, Unit> t;
    public final String u;
    public final EmotionOption v;
    public final String w;
    public final Boolean x;
    public final boolean y;
    public final Boolean z;

    /* JADX DEBUG: Multi-variable search result rejected for r22v0, resolved type: kotlin.jvm.functions.Function2<? super com.vega.aigcapi.materialgenerate.TtsResult, ? super com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> */
    /* JADX DEBUG: Multi-variable search result rejected for r36v0, resolved type: java.util.List<? extends java.util.HashMap<java.lang.String, java.lang.String>> */
    /* JADX WARN: Multi-variable type inference failed */
    public TextToSpeechIntent(String str, TextInfo textInfo, String str2, String str3, String str4, TTSBusinessScene tTSBusinessScene, TTSBusinessType tTSBusinessType, String str5, String str6, float f, int i, ReadingListener readingListener, Map<String, ? extends Object> map, boolean z, String str7, String str8, boolean z2, String str9, String str10, Function2<? super TtsResult, ? super TextToAudioInfo, Unit> function2, String str11, EmotionOption emotionOption, String str12, Boolean bool, boolean z3, Boolean bool2, Boolean bool3, Boolean bool4, boolean z4, boolean z5, boolean z6, boolean z7, IntRange intRange, List<? extends HashMap<String, String>> list, Map<String, String> map2, Function0<Unit> function0, boolean z8) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(textInfo, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(tTSBusinessScene, "");
        Intrinsics.checkNotNullParameter(tTSBusinessType, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        Intrinsics.checkNotNullParameter(str11, "");
        this.f88865a = str;
        this.b = textInfo;
        this.f88866c = str2;
        this.f88867d = str3;
        this.e = str4;
        this.f = tTSBusinessScene;
        this.f88868g = tTSBusinessType;
        this.h = str5;
        this.i = str6;
        this.j = f;
        this.k = i;
        this.l = readingListener;
        this.m = map;
        this.n = z;
        this.o = str7;
        this.p = str8;
        this.q = z2;
        this.r = str9;
        this.s = str10;
        this.t = function2;
        this.u = str11;
        this.v = emotionOption;
        this.w = str12;
        this.x = bool;
        this.y = z3;
        this.z = bool2;
        this.A = bool3;
        this.B = bool4;
        this.C = z4;
        this.D = z5;
        this.E = z6;
        this.F = z7;
        this.G = intRange;
        this.H = list;
        this.I = map2;
        this.f88864J = function0;
        this.K = z8;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TextToSpeechIntent(String str, TextInfo textInfo, String str2, String str3, String str4, TTSBusinessScene tTSBusinessScene, TTSBusinessType tTSBusinessType, String str5, String str6, float f, int i, ReadingListener readingListener, Map map, boolean z, String str7, String str8, boolean z2, String str9, String str10, Function2 function2, String str11, EmotionOption emotionOption, String str12, Boolean bool, boolean z3, Boolean bool2, Boolean bool3, boolean z4, boolean z5, boolean z6, boolean z7, IntRange intRange, List list, Map map2, Function0 function0, boolean z8, int i2, int i3) {
        boolean z9 = z8;
        Map map3 = map2;
        List list2 = list;
        String str13 = str7;
        String strReplace$default = str;
        boolean z10 = z;
        Map map4 = map;
        int i4 = i;
        float f2 = f;
        String str14 = str6;
        String str15 = str5;
        Boolean bool4 = bool;
        ReadingListener readingListener2 = readingListener;
        TTSBusinessScene tTSBusinessScene2 = tTSBusinessScene;
        EmotionOption emotionOption2 = emotionOption;
        String str16 = str3;
        String str17 = str8;
        String str18 = str2;
        IntRange intRange2 = intRange;
        boolean z11 = z2;
        String str19 = str9;
        String str20 = str10;
        Function2 function22 = function2;
        String str21 = str12;
        boolean z12 = z3;
        Boolean bool5 = bool2;
        Boolean bool6 = bool3;
        boolean z13 = z4;
        boolean z14 = z5;
        boolean z15 = z6;
        boolean z16 = z7;
        if ((i2 & 1) != 0) {
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            strReplace$default = StringsKt__StringsJVMKt.replace$default(string, "-", "", false, 4, (Object) null);
        }
        this(strReplace$default, textInfo, (i2 & 4) != 0 ? "" : str18, (i2 & 8) != 0 ? "" : str16, str4, (i2 & 32) != 0 ? TTSBusinessScene.f88855a : tTSBusinessScene2, tTSBusinessType, (i2 & NotificationCompat.FLAG_HIGH_PRIORITY) != 0 ? "" : str15, (i2 & 256) != 0 ? "" : str14, (i2 & 512) != 0 ? 1.0f : f2, (i2 & 1024) != 0 ? 24000 : i4, (i2 & SpeechEngineDefines.ASR_WORK_MODE_OFFLINE) != 0 ? null : readingListener2, (i2 & SpeechEngineDefines.TTS_WORK_MODE_BOTH) != 0 ? null : map4, (i2 & 8192) != 0 ? true : z10, (i2 & 16384) != 0 ? null : str13, (32768 & i2) != 0 ? null : str17, (65536 & i2) != 0 ? false : z11, (131072 & i2) != 0 ? null : str19, (262144 & i2) != 0 ? null : str20, (524288 & i2) != 0 ? null : function22, (1048576 & i2) == 0 ? str11 : "", (2097152 & i2) != 0 ? null : emotionOption2, (4194304 & i2) != 0 ? null : str21, (8388608 & i2) != 0 ? Boolean.FALSE : bool4, (16777216 & i2) != 0 ? false : z12, null, (67108864 & i2) != 0 ? null : bool5, (134217728 & i2) != 0 ? null : bool6, (268435456 & i2) != 0 ? false : z13, (536870912 & i2) != 0 ? false : z14, (1073741824 & i2) != 0 ? false : z15, (i2 & Integer.MIN_VALUE) != 0 ? false : z16, (i3 & 1) != 0 ? null : intRange2, (i3 & 2) != 0 ? null : list2, (i3 & 4) != 0 ? null : map3, (i3 & 8) == 0 ? function0 : null, (i3 & 16) != 0 ? false : z9);
    }

    public static TextToSpeechIntent a(TextToSpeechIntent textToSpeechIntent, Boolean bool) {
        String str = textToSpeechIntent.f88865a;
        TextInfo textInfo = textToSpeechIntent.b;
        String str2 = textToSpeechIntent.f88866c;
        String str3 = textToSpeechIntent.f88867d;
        String str4 = textToSpeechIntent.e;
        TTSBusinessScene tTSBusinessScene = textToSpeechIntent.f;
        TTSBusinessType tTSBusinessType = textToSpeechIntent.f88868g;
        String str5 = textToSpeechIntent.h;
        String str6 = textToSpeechIntent.i;
        float f = textToSpeechIntent.j;
        int i = textToSpeechIntent.k;
        ReadingListener readingListener = textToSpeechIntent.l;
        Map<String, Object> map = textToSpeechIntent.m;
        boolean z = textToSpeechIntent.n;
        String str7 = textToSpeechIntent.o;
        String str8 = textToSpeechIntent.p;
        boolean z2 = textToSpeechIntent.q;
        String str9 = textToSpeechIntent.r;
        String str10 = textToSpeechIntent.s;
        Function2<TtsResult, TextToAudioInfo, Unit> function2 = textToSpeechIntent.t;
        String str11 = textToSpeechIntent.u;
        EmotionOption emotionOption = textToSpeechIntent.v;
        String str12 = textToSpeechIntent.w;
        Boolean bool2 = textToSpeechIntent.x;
        boolean z3 = textToSpeechIntent.y;
        Boolean bool3 = textToSpeechIntent.A;
        Boolean bool4 = textToSpeechIntent.B;
        boolean z4 = textToSpeechIntent.C;
        boolean z5 = textToSpeechIntent.D;
        boolean z6 = textToSpeechIntent.E;
        boolean z7 = textToSpeechIntent.F;
        IntRange intRange = textToSpeechIntent.G;
        List<HashMap<String, String>> list = textToSpeechIntent.H;
        Map<String, String> map2 = textToSpeechIntent.I;
        Function0<Unit> function0 = textToSpeechIntent.f88864J;
        boolean z8 = textToSpeechIntent.K;
        textToSpeechIntent.getClass();
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(textInfo, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(tTSBusinessScene, "");
        Intrinsics.checkNotNullParameter(tTSBusinessType, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        Intrinsics.checkNotNullParameter(str11, "");
        return new TextToSpeechIntent(str, textInfo, str2, str3, str4, tTSBusinessScene, tTSBusinessType, str5, str6, f, i, readingListener, map, z, str7, str8, z2, str9, str10, function2, str11, emotionOption, str12, bool2, z3, bool, bool3, bool4, z4, z5, z6, z7, intRange, list, map2, function0, z8);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextToSpeechIntent)) {
            return false;
        }
        TextToSpeechIntent textToSpeechIntent = (TextToSpeechIntent) obj;
        return Intrinsics.areEqual(this.f88865a, textToSpeechIntent.f88865a) && Intrinsics.areEqual(this.b, textToSpeechIntent.b) && Intrinsics.areEqual(this.f88866c, textToSpeechIntent.f88866c) && Intrinsics.areEqual(this.f88867d, textToSpeechIntent.f88867d) && Intrinsics.areEqual(this.e, textToSpeechIntent.e) && this.f == textToSpeechIntent.f && this.f88868g == textToSpeechIntent.f88868g && Intrinsics.areEqual(this.h, textToSpeechIntent.h) && Intrinsics.areEqual(this.i, textToSpeechIntent.i) && Float.compare(this.j, textToSpeechIntent.j) == 0 && this.k == textToSpeechIntent.k && Intrinsics.areEqual(this.l, textToSpeechIntent.l) && Intrinsics.areEqual(this.m, textToSpeechIntent.m) && this.n == textToSpeechIntent.n && Intrinsics.areEqual(this.o, textToSpeechIntent.o) && Intrinsics.areEqual(this.p, textToSpeechIntent.p) && this.q == textToSpeechIntent.q && Intrinsics.areEqual(this.r, textToSpeechIntent.r) && Intrinsics.areEqual(this.s, textToSpeechIntent.s) && Intrinsics.areEqual(this.t, textToSpeechIntent.t) && Intrinsics.areEqual(this.u, textToSpeechIntent.u) && Intrinsics.areEqual(this.v, textToSpeechIntent.v) && Intrinsics.areEqual(this.w, textToSpeechIntent.w) && Intrinsics.areEqual(this.x, textToSpeechIntent.x) && this.y == textToSpeechIntent.y && Intrinsics.areEqual(this.z, textToSpeechIntent.z) && Intrinsics.areEqual(this.A, textToSpeechIntent.A) && Intrinsics.areEqual(this.B, textToSpeechIntent.B) && this.C == textToSpeechIntent.C && this.D == textToSpeechIntent.D && this.E == textToSpeechIntent.E && this.F == textToSpeechIntent.F && Intrinsics.areEqual(this.G, textToSpeechIntent.G) && Intrinsics.areEqual(this.H, textToSpeechIntent.H) && Intrinsics.areEqual(this.I, textToSpeechIntent.I) && Intrinsics.areEqual(this.f88864J, textToSpeechIntent.f88864J) && this.K == textToSpeechIntent.K;
    }

    public final int hashCode() {
        int iHashCode = ((((((((((((((((((((this.f88865a.hashCode() * 31) + this.b.hashCode()) * 31) + this.f88866c.hashCode()) * 31) + this.f88867d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.f88868g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + Float.floatToIntBits(this.j)) * 31) + this.k) * 31;
        ReadingListener readingListener = this.l;
        int iHashCode2 = (iHashCode + (readingListener == null ? 0 : readingListener.hashCode())) * 31;
        Map<String, Object> map = this.m;
        int iHashCode3 = (((iHashCode2 + (map == null ? 0 : map.hashCode())) * 31) + (this.n ? 1231 : 1237)) * 31;
        String str = this.o;
        int iHashCode4 = (iHashCode3 + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.p;
        int iHashCode5 = (((iHashCode4 + (str2 == null ? 0 : str2.hashCode())) * 31) + (this.q ? 1231 : 1237)) * 31;
        String str3 = this.r;
        int iHashCode6 = (iHashCode5 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.s;
        int iHashCode7 = (iHashCode6 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Function2<TtsResult, TextToAudioInfo, Unit> function2 = this.t;
        int iHashCode8 = (((iHashCode7 + (function2 == null ? 0 : function2.hashCode())) * 31) + this.u.hashCode()) * 31;
        EmotionOption emotionOption = this.v;
        int iHashCode9 = (iHashCode8 + (emotionOption == null ? 0 : emotionOption.hashCode())) * 31;
        String str5 = this.w;
        int iHashCode10 = (iHashCode9 + (str5 == null ? 0 : str5.hashCode())) * 31;
        Boolean bool = this.x;
        int iHashCode11 = (((iHashCode10 + (bool == null ? 0 : bool.hashCode())) * 31) + (this.y ? 1231 : 1237)) * 31;
        Boolean bool2 = this.z;
        int iHashCode12 = (iHashCode11 + (bool2 == null ? 0 : bool2.hashCode())) * 31;
        Boolean bool3 = this.A;
        int iHashCode13 = (iHashCode12 + (bool3 == null ? 0 : bool3.hashCode())) * 31;
        Boolean bool4 = this.B;
        int iHashCode14 = (((((((((iHashCode13 + (bool4 == null ? 0 : bool4.hashCode())) * 31) + (this.C ? 1231 : 1237)) * 31) + (this.D ? 1231 : 1237)) * 31) + (this.E ? 1231 : 1237)) * 31) + (this.F ? 1231 : 1237)) * 31;
        IntRange intRange = this.G;
        int iHashCode15 = (iHashCode14 + (intRange == null ? 0 : intRange.hashCode())) * 31;
        List<HashMap<String, String>> list = this.H;
        int iHashCode16 = (iHashCode15 + (list == null ? 0 : list.hashCode())) * 31;
        Map<String, String> map2 = this.I;
        int iHashCode17 = (iHashCode16 + (map2 == null ? 0 : map2.hashCode())) * 31;
        Function0<Unit> function0 = this.f88864J;
        return ((iHashCode17 + (function0 != null ? function0.hashCode() : 0)) * 31) + (this.K ? 1231 : 1237);
    }

    public final String toString() {
        return "TextToSpeechIntent(id=" + this.f88865a + ", textInfo=" + this.b + ", toneTypeId=" + this.f88866c + ", platformType=" + this.f88867d + ", enterFrom=" + this.e + ", scene=" + this.f + ", businessType=" + this.f88868g + ", editType=" + this.h + ", adType=" + this.i + ", speed=" + this.j + ", rate=" + this.k + ", readingListener=" + this.l + ", extraReportParam=" + this.m + ", showToast=" + this.n + ", reportInfo=" + this.o + ", defaultAuditionText=" + this.p + ", forceUseDefaultAuditionText=" + this.q + ", toneModelType=" + this.r + ", resourceId=" + this.s + ", callback=" + this.t + ", effectId=" + this.u + ", emotionOption=" + this.v + ", mockToneInfo=" + this.w + ", ignoreInvalidPath=" + this.x + ", correctionTtsInfo=" + this.y + ", isFromReading=" + this.z + ", isFromSmartCutAudio=" + this.A + ", isAiCloneTone=" + this.B + ", isV3ModelTone=" + this.C + ", isFromDigitalHuman=" + this.D + ", supportReadUserText=" + this.E + ", useCache=" + this.F + ", ssmlReadingIndexRange=" + this.G + ", ssmlProsodyMetaInfo=" + this.H + ", ssmlReportMap=" + this.I + ", cancelCallback=" + this.f88864J + ", isFromPilot=" + this.K + ')';
    }
}
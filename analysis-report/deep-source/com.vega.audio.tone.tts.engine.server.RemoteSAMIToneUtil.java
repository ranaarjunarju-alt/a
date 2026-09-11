package com.vega.audio.tone.tts.engine.server;

import com.lemon.lv.config.AuditionUsersTextEntranceABConfig;
import com.lemon.lv.config.AuditionUsersTextEntranceABTest;
import com.lemon.lv.config.SamiToneEmotionClientConfig;
import com.lemon.lv.config.SamiToneEmotionClientConfigSetting;
import com.lemon.lv.config.ToneCommercialOptABTest;
import com.lemon.lv.config.ToneCommercialOptABTestConfig;
import com.lemon.lv.config.ToneEmotionClientConfig;
import com.lemon.lv.config.ToneEmotionClientConfigSetting;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.audio.tone.tts.AuditionTextType;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.tts.core.TextToSpeechExecutorType;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.edit.base.tone.EmotionOption;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import com.vega.util.FeedbackAudioUtils;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes12.dex */
public final class RemoteSAMIToneUtil {

    /* renamed from: a, reason: collision with root package name */
    public static final RemoteSAMIToneUtil f74436a = new RemoteSAMIToneUtil();
    public static final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil$mircoTonePreviewOption$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            return Integer.valueOf(((ToneEmotionClientConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneEmotionClientConfigSetting.class))).c());
        }
    });

    /* renamed from: c, reason: collision with root package name */
    public static final Lazy f74437c = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil$samiTonePreviewOption$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Integer invoke() {
            return Integer.valueOf(((SamiToneEmotionClientConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(SamiToneEmotionClientConfigSetting.class))).b());
        }
    });

    /* renamed from: d, reason: collision with root package name */
    public static final Lazy f74438d = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil$isInMicroTonePreviewExperiment$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            ToneEmotionClientConfig toneEmotionClientConfig = (ToneEmotionClientConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneEmotionClientConfigSetting.class));
            boolean z = true;
            if (toneEmotionClientConfig.b() && !toneEmotionClientConfig.a() && toneEmotionClientConfig.c() == 0) {
                z = false;
            }
            return Boolean.valueOf(z);
        }
    });
    public static final Lazy e = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil$isInSamiTonePreviewExperiment$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(((SamiToneEmotionClientConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(SamiToneEmotionClientConfigSetting.class))).a());
        }
    });

    /* loaded from: classes6.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f74439a;

        static {
            int[] iArr = new int[TextToSpeechReportScene.values().length];
            try {
                iArr[TextToSpeechReportScene.LONG_TEXT_EDITOR.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextToSpeechReportScene.LONG_TEXT_EDITOR_ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[TextToSpeechReportScene.AUDIO_CLONE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[TextToSpeechReportScene.AUDIO_PANEL.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[TextToSpeechReportScene.AI_SCRIPT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[TextToSpeechReportScene.AI_DUBBING_TOOL.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr[TextToSpeechReportScene.AI_DUBBING_EDITOR.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr[TextToSpeechReportScene.AI_DUBBING_EDITOR_AUDIO.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr[TextToSpeechReportScene.AI_DUBBING_EDITOR_TEXT.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr[TextToSpeechReportScene.SMART_EDIT_AI_VOICEOVER.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            f74439a = iArr;
        }
    }

    public static Pair a(boolean z, TextToSpeechExecutorType textToSpeechExecutorType, final TextToSpeechIntent textToSpeechIntent) {
        final String str;
        Pair<? extends String, ? extends Integer> pairInvoke;
        Intrinsics.checkNotNullParameter(textToSpeechExecutorType, "");
        Intrinsics.checkNotNullParameter(textToSpeechIntent, "");
        final boolean z2 = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).d() || textToSpeechIntent.q || ((ToneCommercialOptABTestConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneCommercialOptABTest.class))).enableAuditionOpt();
        TextToSpeechReportInfo.Companion companion = TextToSpeechReportInfo.Companion;
        String str2 = textToSpeechIntent.o;
        companion.getClass();
        TextToSpeechReportInfo textToSpeechReportInfoA = TextToSpeechReportInfo.Companion.a(str2);
        TextToSpeechReportScene from = textToSpeechReportInfoA != null ? textToSpeechReportInfoA.getFrom() : null;
        Objects.toString(ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneEmotionClientConfigSetting.class)));
        BLog.i("RemoteSAMIToneUtil", "getTTSText -> use from=" + from + ", enableDefaultTTSAudition=" + z2 + ", isSaving=" + z + ", intent.reportInfo=" + textToSpeechIntent.o + ", defaultAuditionText = " + textToSpeechIntent.p + ",supportReadUserText=" + textToSpeechIntent.E + ",forceUseDefaultAuditionText=" + textToSpeechIntent.q);
        FeedbackAudioUtils feedbackAudioUtils = FeedbackAudioUtils.f135099a;
        String str3 = textToSpeechIntent.f88866c;
        String info = from != null ? from.getInfo() : null;
        String strName = textToSpeechExecutorType.name();
        String str4 = textToSpeechIntent.s;
        String str5 = textToSpeechIntent.f88866c;
        String info2 = from != null ? from.getInfo() : null;
        String strName2 = textToSpeechExecutorType.name();
        String str6 = textToSpeechIntent.s;
        feedbackAudioUtils.getClass();
        if (z) {
            if (str5 != null) {
                FeedbackAudioUtils.b.put("last_download_speakid", str5);
            }
            if (info2 != null) {
                FeedbackAudioUtils.b.put("last_download_from", info2);
            }
            if (strName2 != null) {
                FeedbackAudioUtils.b.put("last_download_scene", strName2);
            }
            if (str6 != null) {
                FeedbackAudioUtils.b.put("last_download_resource_id", str6);
            }
        } else {
            if (str3 != null) {
                FeedbackAudioUtils.b.put("last_audition_speakid", str3);
            }
            if (info != null) {
                FeedbackAudioUtils.b.put("last_audition_from", info);
            }
            if (strName != null) {
                FeedbackAudioUtils.b.put("last_audition_scene", strName);
            }
            if (str4 != null) {
                FeedbackAudioUtils.b.put("last_audition_resource_id", str4);
            }
        }
        boolean z3 = textToSpeechExecutorType == TextToSpeechExecutorType.e;
        if (from != null) {
            switch (WhenMappings.f74439a[from.ordinal()]) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                    if (z || z3) {
                        return new Pair(textToSpeechIntent.b.getText(), Integer.valueOf(textToSpeechIntent.b.a()));
                    }
                    if (((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).hitExperiment()) {
                        TextToSpeechTaskManager.f74281a.getClass();
                        if (TextToSpeechTaskManager.h(textToSpeechIntent) != AuditionTextType.f74267a) {
                            return new Pair(textToSpeechIntent.b.getText(), Integer.valueOf(textToSpeechIntent.b.a()));
                        }
                        String text = textToSpeechIntent.p;
                        if (text == null) {
                            text = textToSpeechIntent.b.getText();
                        }
                        return new Pair(text, 1);
                    }
                    TextToSpeechExecutorType textToSpeechExecutorType2 = TextToSpeechExecutorType.b;
                    boolean z4 = textToSpeechExecutorType == textToSpeechExecutorType2;
                    boolean z5 = textToSpeechExecutorType == TextToSpeechExecutorType.f74291a || textToSpeechExecutorType == TextToSpeechExecutorType.f74292c;
                    EmotionOption emotionOption = textToSpeechIntent.v;
                    if (emotionOption == null || (str = emotionOption.f88853d) == null) {
                        str = "";
                    }
                    String str7 = textToSpeechIntent.p;
                    final String str8 = str7 != null ? str7 : "";
                    Function1<Integer, Pair<? extends String, ? extends Integer>> function1 = new Function1<Integer, Pair<? extends String, ? extends Integer>>() { // from class: com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil$getTTSText$pairDataForEmotionOption$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final Pair<? extends String, ? extends Integer> invoke(Integer num) {
                            int iIntValue = num.intValue();
                            if (iIntValue == 0) {
                                return (!z2 || str8.length() <= 0) ? new Pair<>(textToSpeechIntent.b.getText(), Integer.valueOf(textToSpeechIntent.b.a())) : new Pair<>(str8, 1);
                            }
                            if (iIntValue == 1) {
                                if (str.length() > 0) {
                                    return new Pair<>(str, 1);
                                }
                                if (z2 && str8.length() > 0) {
                                    return new Pair<>(str8, 1);
                                }
                            }
                            return null;
                        }
                    };
                    Lazy lazy = f74438d;
                    if (((Boolean) lazy.getValue()).booleanValue() || ((Boolean) e.getValue()).booleanValue()) {
                        ((Boolean) lazy.getValue()).booleanValue();
                        ((Boolean) e.getValue()).booleanValue();
                        if (z4) {
                            pairInvoke = function1.invoke(Integer.valueOf(((Number) b.getValue()).intValue()));
                        } else if (z5) {
                            pairInvoke = function1.invoke(Integer.valueOf(((Number) f74437c.getValue()).intValue()));
                        }
                        if (pairInvoke != null) {
                            return pairInvoke;
                        }
                    }
                    if (!z2 || !ExtentionKt.isNotNullOrEmpty(textToSpeechIntent.p)) {
                        return new Pair(textToSpeechIntent.b.getText(), Integer.valueOf(textToSpeechIntent.b.a()));
                    }
                    if (textToSpeechExecutorType != TextToSpeechExecutorType.f74292c && textToSpeechExecutorType != textToSpeechExecutorType2 && textToSpeechExecutorType != TextToSpeechExecutorType.f74293d) {
                        return new Pair(textToSpeechIntent.b.getText(), Integer.valueOf(textToSpeechIntent.b.a()));
                    }
                    String text2 = textToSpeechIntent.p;
                    if (text2 == null) {
                        text2 = textToSpeechIntent.b.getText();
                    }
                    return new Pair(text2, 1);
                case 10:
                    String text3 = textToSpeechIntent.p;
                    if (text3 == null) {
                        text3 = textToSpeechIntent.b.getText();
                    }
                    return new Pair(text3, 1);
            }
        }
        return new Pair(textToSpeechIntent.b.getText(), Integer.valueOf(textToSpeechIntent.b.a()));
    }
}
package com.vega.audio.tone.tts.core.executor;

import com.lemon.lv.config.ToneCommercialOptABTest;
import com.lemon.lv.config.ToneCommercialOptABTestConfig;
import com.vega.audio.tone.tts.core.TextToSpeechExecutorType;
import com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor;
import com.vega.audio.tone.tts.core.executor.impl.MicrosoftTextToSpeechExecutor;
import com.vega.audio.tone.tts.core.executor.impl.NonStreamingTextToSpeechExecutor;
import com.vega.audio.tone.tts.core.executor.impl.RemoteSAMITextToSpeechExecutor;
import com.vega.audio.tone.tts.core.executor.impl.SAMITextToSpeechExecutor;
import com.vega.audio.tone.tts.core.executor.impl.SSMLTextToSpeechExecutor;
import com.vega.config.ConfigSettingsKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes35.dex */
public final class TextToSpeechExecutorFactory {

    /* renamed from: a, reason: collision with root package name */
    public static final TextToSpeechExecutorFactory f74312a = new TextToSpeechExecutorFactory();

    /* loaded from: classes23.dex */
    public /* synthetic */ class WhenMappings {
        static {
            TextToSpeechExecutorType.values();
        }
    }

    public static BaseTextToSpeechExecutor a(TextToSpeechExecutorType textToSpeechExecutorType) {
        Intrinsics.checkNotNullParameter(textToSpeechExecutorType, "");
        switch (textToSpeechExecutorType.ordinal()) {
            case 0:
                return ((ToneCommercialOptABTestConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneCommercialOptABTest.class))).enableAuditionOpt() ? new RemoteSAMITextToSpeechExecutor() : new SAMITextToSpeechExecutor();
            case 1:
                return new MicrosoftTextToSpeechExecutor();
            case 2:
                return new RemoteSAMITextToSpeechExecutor();
            case 3:
                return new ElevenLabsTextToSpeechExecutor();
            case 4:
                return new SSMLTextToSpeechExecutor();
            case 5:
                return new NonStreamingTextToSpeechExecutor();
            case 6:
                return new NonStreamingTextToSpeechExecutor();
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
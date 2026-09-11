package com.vega.audio.tone.tts.engine.elevenlabs;

import com.lemon.lv.data.TextToAudioInfo;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager", f = "ElevenLabsToneManager.kt", i = {0}, l = {208}, m = "downloadAudioResources", n = {"textToAudioInfo"}, s = {"L$0"})
/* loaded from: classes11.dex */
public final class ElevenLabsToneManager$downloadAudioResources$1 extends ContinuationImpl {
    public TextToAudioInfo q;
    public /* synthetic */ Object r;
    public final /* synthetic */ ElevenLabsToneManager s;
    public int t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsToneManager$downloadAudioResources$1(ElevenLabsToneManager elevenLabsToneManager, Continuation<? super ElevenLabsToneManager$downloadAudioResources$1> continuation) {
        super(continuation);
        this.s = elevenLabsToneManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.r = obj;
        this.t |= Integer.MIN_VALUE;
        return this.s.b(null, null, this);
    }
}
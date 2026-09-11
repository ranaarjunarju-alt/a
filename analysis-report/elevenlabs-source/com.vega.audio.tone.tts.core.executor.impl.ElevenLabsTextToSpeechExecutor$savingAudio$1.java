package com.vega.audio.tone.tts.core.executor.impl;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor", f = "ElevenLabsTextToSpeechExecutor.kt", i = {}, l = {180}, m = "savingAudio", n = {}, s = {})
/* loaded from: classes6.dex */
public final class ElevenLabsTextToSpeechExecutor$savingAudio$1 extends ContinuationImpl {
    public /* synthetic */ Object q;
    public final /* synthetic */ ElevenLabsTextToSpeechExecutor r;
    public int s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsTextToSpeechExecutor$savingAudio$1(ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor, Continuation<? super ElevenLabsTextToSpeechExecutor$savingAudio$1> continuation) {
        super(continuation);
        this.r = elevenLabsTextToSpeechExecutor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.q = obj;
        this.s |= Integer.MIN_VALUE;
        return this.r.m(null, this);
    }
}
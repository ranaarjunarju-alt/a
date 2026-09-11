package com.vega.audio.tone.tts.core.executor.impl;

import androidx.core.app.NotificationCompat;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@DebugMetadata(c = "com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor", f = "ElevenLabsTextToSpeechExecutor.kt", i = {0}, l = {97, NotificationCompat.FLAG_HIGH_PRIORITY}, m = "readAudioFromNet", n = {"onLoadSuccess"}, s = {"L$0"})
/* loaded from: classes23.dex */
public final class ElevenLabsTextToSpeechExecutor$readAudioFromNet$1 extends ContinuationImpl {
    public Function1 q;
    public /* synthetic */ Object r;
    public final /* synthetic */ ElevenLabsTextToSpeechExecutor s;
    public int t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsTextToSpeechExecutor$readAudioFromNet$1(ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor, Continuation<? super ElevenLabsTextToSpeechExecutor$readAudioFromNet$1> continuation) {
        super(continuation);
        this.s = elevenLabsTextToSpeechExecutor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.r = obj;
        this.t |= Integer.MIN_VALUE;
        return this.s.j(null, null, this);
    }
}
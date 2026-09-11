package com.vega.audio.tone.tts.core.executor.impl;

import com.vega.audio.tone.tts.core.TextToSpeechTask;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor", f = "ElevenLabsTextToSpeechExecutor.kt", i = {0, 1}, l = {55, 81}, m = "executeTask", n = {"task", "task"}, s = {"L$0", "L$0"})
/* loaded from: classes12.dex */
public final class ElevenLabsTextToSpeechExecutor$executeTask$1 extends ContinuationImpl {
    public TextToSpeechTask q;
    public ElevenLabsTextToSpeechExecutor r;
    public /* synthetic */ Object s;
    public final /* synthetic */ ElevenLabsTextToSpeechExecutor t;
    public int u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsTextToSpeechExecutor$executeTask$1(ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor, Continuation<? super ElevenLabsTextToSpeechExecutor$executeTask$1> continuation) {
        super(continuation);
        this.t = elevenLabsTextToSpeechExecutor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.s = obj;
        this.u |= Integer.MIN_VALUE;
        return this.t.e(null, this);
    }
}
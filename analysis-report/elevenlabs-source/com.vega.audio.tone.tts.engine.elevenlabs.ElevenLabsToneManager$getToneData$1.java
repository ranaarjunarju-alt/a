package com.vega.audio.tone.tts.engine.elevenlabs;

import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager", f = "ElevenLabsToneManager.kt", i = {0, 0, 1, 2}, l = {68, 89, 100}, m = "getToneData", n = {"task", "toneType", "ttsResult", "ttsResult"}, s = {"L$0", "L$1", "L$0", "L$0"})
/* loaded from: classes23.dex */
public final class ElevenLabsToneManager$getToneData$1 extends ContinuationImpl {
    public Object q;
    public Object r;
    public /* synthetic */ Object s;
    public final /* synthetic */ ElevenLabsToneManager t;
    public int u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsToneManager$getToneData$1(ElevenLabsToneManager elevenLabsToneManager, Continuation<? super ElevenLabsToneManager$getToneData$1> continuation) {
        super(continuation);
        this.t = elevenLabsToneManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.s = obj;
        this.u |= Integer.MIN_VALUE;
        return this.t.e(null, this);
    }
}
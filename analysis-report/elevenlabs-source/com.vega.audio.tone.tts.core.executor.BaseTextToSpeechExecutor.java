package com.vega.audio.tone.tts.core.executor;

import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.core.TextToSpeechTask;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes24.dex */
public abstract class BaseTextToSpeechExecutor implements ITextToSpeechExecutor {

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f74309a = new AtomicBoolean(false);
    public final TtsResult.RequestScene b = TtsResult.RequestScene.b;

    /* loaded from: classes33.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object k(com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor r22, com.vega.audio.tone.tts.core.TextToSpeechTask r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            Method dump skipped, instructions count: 453
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor.k(com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor, com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final boolean b() {
        return this.f74309a.compareAndSet(false, true);
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final boolean c() {
        return this.f74309a.get();
    }

    public TtsResult.RequestScene g() {
        return this.b;
    }

    public void h() {
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(final com.vega.audio.tone.tts.core.TextToSpeechTask r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1
            if (r0 == 0) goto L3e
            r4 = r7
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1 r4 = (com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L3e
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L2b
            if (r0 != r1) goto L47
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            boolean r0 = r6.t
            if (r0 != 0) goto L44
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L2b:
            kotlin.ResultKt.throwOnFailure(r3)
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$2 r0 = new com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$2
            r0.<init>()
            r4.q = r6
            r4.t = r1
            java.lang.Object r0 = r5.j(r6, r0, r4)
            if (r0 != r2) goto L24
            return r2
        L3e:
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1 r4 = new com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1
            r4.<init>(r5, r7)
            goto L12
        L44:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L47:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor.i(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object j(TextToSpeechTask textToSpeechTask, Function1<? super Pair<TextToAudioInfo, ? extends TtsResult>, Unit> function1, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final void release() {
        this.f74309a.set(false);
    }
}
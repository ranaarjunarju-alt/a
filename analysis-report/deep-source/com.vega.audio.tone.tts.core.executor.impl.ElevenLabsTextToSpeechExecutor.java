package com.vega.audio.tone.tts.core.executor.impl;

import com.vega.aigcapi.materialgenerate.EventType;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.Operation;
import com.vega.audio.tone.tts.core.TextToSpeechExecutorType;
import com.vega.audio.tone.tts.core.TextToSpeechListener;
import com.vega.audio.tone.tts.core.TextToSpeechTask;
import com.vega.audio.tone.tts.core.TextToSpeechTaskState;
import com.vega.audio.tone.tts.core.TextToSpeechTaskType;
import com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor;
import com.vega.audio.tone.tts.engine.ThirdPartyPlayer;
import com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* loaded from: classes12.dex */
public final class ElevenLabsTextToSpeechExecutor extends BaseTextToSpeechExecutor {

    /* renamed from: c, reason: collision with root package name */
    public final Lazy f74316c = LazyKt__LazyJVMKt.lazy(new Function0<ElevenLabsToneManager>() { // from class: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$toneManager$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final ElevenLabsToneManager invoke() {
            return new ElevenLabsToneManager();
        }
    });

    /* renamed from: d, reason: collision with root package name */
    public volatile TextToSpeechTask f74317d;
    public Job e;
    public volatile long f;

    /* loaded from: classes6.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final TextToSpeechTask a() {
        return this.f74317d;
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final void close() {
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final Object d(String str, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x006b  */
    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(com.vega.audio.tone.tts.core.TextToSpeechTask r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r7 = this;
            boolean r0 = r9 instanceof com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$1
            if (r0 == 0) goto L6b
            r6 = r9
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$1 r6 = (com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$1) r6
            int r2 = r6.u
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L6b
            int r2 = r2 - r1
            r6.u = r2
        L12:
            java.lang.Object r1 = r6.s
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.u
            r4 = 2
            r3 = 1
            r2 = 0
            if (r0 == 0) goto L31
            if (r0 == r3) goto L29
            if (r0 != r4) goto L71
            kotlin.ResultKt.throwOnFailure(r1)
        L26:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L29:
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor r0 = r6.r
            com.vega.audio.tone.tts.core.TextToSpeechTask r8 = r6.q
            kotlin.ResultKt.throwOnFailure(r1)
            goto L56
        L31:
            kotlin.ResultKt.throwOnFailure(r1)
            long r0 = java.lang.System.currentTimeMillis()
            r7.f = r0
            r7.f74317d = r8
            kotlinx.coroutines.Job r0 = r7.e
            if (r0 == 0) goto L43
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r2, r3, r2)
        L43:
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$2 r0 = new com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$2
            r0.<init>(r8, r7, r2)
            r6.q = r8
            r6.r = r7
            r6.u = r3
            java.lang.Object r1 = kotlinx.coroutines.SupervisorKt.supervisorScope(r0, r6)
            if (r1 != r5) goto L55
            return r5
        L55:
            r0 = r7
        L56:
            kotlinx.coroutines.Job r1 = (kotlinx.coroutines.Job) r1
            r0.e = r1
            kotlinx.coroutines.Job r0 = r7.e
            if (r0 == 0) goto L26
            r6.q = r8
            r6.r = r2
            r6.u = r4
            java.lang.Object r0 = r0.join(r6)
            if (r0 != r5) goto L26
            return r5
        L6b:
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$1 r6 = new com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$1
            r6.<init>(r7, r9)
            goto L12
        L71:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor.e(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final void f(Operation operation) {
        TextToSpeechListener textToSpeechListener;
        TextToSpeechListener textToSpeechListener2;
        TextToSpeechListener textToSpeechListener3;
        Intrinsics.checkNotNullParameter(operation, "");
        TextToSpeechTask textToSpeechTask = this.f74317d;
        if (textToSpeechTask != null) {
            TextToSpeechTaskState textToSpeechTaskState = TextToSpeechTaskState.f74306d;
            Intrinsics.checkNotNullParameter(textToSpeechTaskState, "");
            textToSpeechTask.j = textToSpeechTaskState;
        }
        ((ElevenLabsToneManager) this.f74316c.getValue()).f74378a.set(true);
        TextToSpeechTask textToSpeechTask2 = this.f74317d;
        if (textToSpeechTask2 != null && (textToSpeechListener3 = textToSpeechTask2.l) != null) {
            textToSpeechListener3.c(TtsResult.RequestScene.b);
        }
        TextToSpeechTask textToSpeechTask3 = this.f74317d;
        if ((textToSpeechTask3 != null ? textToSpeechTask3.f74299d : null) == TextToSpeechTaskType.f74307a) {
            ThirdPartyPlayer.f74363a.b();
            TextToSpeechTask textToSpeechTask4 = this.f74317d;
            if (textToSpeechTask4 != null && (textToSpeechListener2 = textToSpeechTask4.l) != null) {
                textToSpeechListener2.b();
            }
        }
        Job job = this.e;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        TextToSpeechTask textToSpeechTask5 = this.f74317d;
        if (textToSpeechTask5 != null && (textToSpeechListener = textToSpeechTask5.l) != null) {
            TextToSpeechListener.DefaultImpls.b(textToSpeechListener, TextToSpeechExecutorType.f74291a, EventType.f, null, 12);
        }
        l();
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x0119  */
    @Override // com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object j(com.vega.audio.tone.tts.core.TextToSpeechTask r15, kotlin.jvm.functions.Function1<? super kotlin.Pair<com.lemon.lv.data.TextToAudioInfo, ? extends com.vega.aigcapi.materialgenerate.TtsResult>, kotlin.Unit> r16, kotlin.coroutines.Continuation<? super kotlin.Unit> r17) throws java.lang.Throwable {
        /*
            r14 = this;
            r3 = r17
            r11 = r16
            boolean r0 = r3 instanceof com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$1
            r8 = r14
            if (r0 == 0) goto L119
            r4 = r3
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$1 r4 = (com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L119
            int r2 = r2 - r1
            r4.t = r2
        L17:
            java.lang.Object r12 = r4.r
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r13 = 0
            r5 = 2
            r7 = 1
            if (r0 == 0) goto L33
            if (r0 == r7) goto L5a
            if (r0 != r5) goto L120
            kotlin.ResultKt.throwOnFailure(r12)
        L2b:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            r12.booleanValue()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L33:
            kotlin.ResultKt.throwOnFailure(r12)
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r8.f74317d
            if (r0 == 0) goto L47
            com.vega.audio.tone.tts.core.TextToSpeechListener r6 = r0.l
            if (r6 == 0) goto L47
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r2 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r1 = com.vega.aigcapi.materialgenerate.EventType.f69147a
            r0 = 12
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r6, r2, r1, r13, r0)
        L47:
            kotlin.Lazy r0 = r8.f74316c
            java.lang.Object r0 = r0.getValue()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager) r0
            r4.q = r11
            r4.t = r7
            java.lang.Object r12 = r0.e(r15, r4)
            if (r12 != r3) goto L5f
            return r3
        L5a:
            kotlin.jvm.functions.Function1 r11 = r4.q
            kotlin.ResultKt.throwOnFailure(r12)
        L5f:
            kotlin.Pair r12 = (kotlin.Pair) r12
            java.lang.Object r9 = r12.getFirst()
            com.vega.aigcapi.materialgenerate.TtsResult r9 = (com.vega.aigcapi.materialgenerate.TtsResult) r9
            com.vega.aigcapi.materialgenerate.StatusResult r1 = r9.f69163a
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            r6 = 8
            java.lang.String r7 = "TextToSpeech_ElevenLabsTextToSpeechExecutor"
            if (r1 == r0) goto L75
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.e
            if (r1 != r0) goto Lb4
        L75:
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r8.f74317d
            if (r0 == 0) goto L84
            com.vega.audio.tone.tts.core.TextToSpeechListener r2 = r0.l
            if (r2 == 0) goto L84
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r1 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r0 = com.vega.aigcapi.materialgenerate.EventType.f69148c
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r2, r1, r0, r9, r6)
        L84:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r0 = "readingAudio: fail error code: "
            r2.<init>(r0)
            com.vega.aigcapi.materialgenerate.StatusResult r0 = r9.f69163a
            r2.append(r0)
            java.lang.String r1 = ", "
            r2.append(r1)
            int r0 = r9.b
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r9.f69164c
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r9.f69165d
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            com.vega.log.BLog.e(r7, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Lb4:
            java.lang.Object r0 = r12.getSecond()
            com.lemon.lv.data.TextToAudioInfo r0 = (com.lemon.lv.data.TextToAudioInfo) r0
            r2 = 0
            java.lang.String r1 = ""
            if (r0 == 0) goto Lcb
            java.util.List<java.lang.String> r0 = r0.f59067a
            if (r0 == 0) goto Lcb
            java.lang.Object r10 = r0.get(r2)
            java.lang.String r10 = (java.lang.String) r10
            if (r10 != 0) goto Lcc
        Lcb:
            r10 = r1
        Lcc:
            int r0 = r10.length()
            if (r0 != 0) goto Led
            r0 = 3500(0xdac, float:4.905E-42)
            r9.b = r0
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r8.f74317d
            if (r0 == 0) goto Le5
            com.vega.audio.tone.tts.core.TextToSpeechListener r2 = r0.l
            if (r2 == 0) goto Le5
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r1 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r0 = com.vega.aigcapi.materialgenerate.EventType.f69148c
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r2, r1, r0, r9, r6)
        Le5:
            java.lang.String r0 = "readingAudio: audioPath empty"
            com.vega.log.BLog.e(r7, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        Led:
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.b
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            r9.f69163a = r0
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r8.f74317d
            if (r0 == 0) goto L103
            com.vega.audio.tone.tts.core.TextToSpeechListener r2 = r0.l
            if (r2 == 0) goto L103
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r1 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r0 = com.vega.aigcapi.materialgenerate.EventType.b
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r2, r1, r0, r9, r6)
        L103:
            r12.toString()
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1 r7 = new com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1
            r7.<init>(r8, r9, r10, r11, r12, r13)
            r4.q = r13
            r4.t = r5
            r0 = 60000(0xea60, double:2.9644E-319)
            java.lang.Object r12 = kotlinx.coroutines.TimeoutKt.withTimeout(r0, r7, r4)
            if (r12 != r3) goto L2b
            return r3
        L119:
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$1 r4 = new com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$1
            r4.<init>(r8, r3)
            goto L17
        L120:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor.j(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void l() {
        TextToSpeechTask textToSpeechTask = this.f74317d;
        if (textToSpeechTask != null) {
            TextToSpeechTaskState textToSpeechTaskState = TextToSpeechTaskState.e;
            Intrinsics.checkNotNullParameter(textToSpeechTaskState, "");
            textToSpeechTask.j = textToSpeechTaskState;
        }
        this.f74317d = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m(com.vega.audio.tone.tts.core.TextToSpeechTask r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Throwable {
        /*
            r9 = this;
            boolean r0 = r11 instanceof com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$savingAudio$1
            if (r0 == 0) goto Lb4
            r8 = r11
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$savingAudio$1 r8 = (com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$savingAudio$1) r8
            int r2 = r8.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto Lb4
            int r2 = r2 - r1
            r8.s = r2
        L12:
            java.lang.Object r6 = r8.q
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r8.s
            r5 = 1
            if (r0 == 0) goto L8e
            if (r0 != r5) goto Lbb
            kotlin.ResultKt.throwOnFailure(r6)
        L22:
            kotlin.Pair r6 = (kotlin.Pair) r6
            java.lang.Object r4 = r6.getFirst()
            com.vega.aigcapi.materialgenerate.TtsResult r4 = (com.vega.aigcapi.materialgenerate.TtsResult) r4
            long r2 = java.lang.System.currentTimeMillis()
            long r0 = r9.f
            long r2 = r2 - r0
            r4.e = r2
            com.vega.aigcapi.materialgenerate.StatusResult r1 = r4.f69163a
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
            if (r1 == r0) goto L3d
            com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.e
            if (r1 != r0) goto L78
        L3d:
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r9.f74317d
            if (r0 == 0) goto L4e
            com.vega.audio.tone.tts.core.TextToSpeechListener r3 = r0.l
            if (r3 == 0) goto L4e
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r2 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r1 = com.vega.aigcapi.materialgenerate.EventType.f69148c
            r0 = 8
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r3, r2, r1, r4, r0)
        L4e:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r0 = "savingAudio: error code: "
            r2.<init>(r0)
            int r0 = r4.b
            r2.append(r0)
            java.lang.String r1 = ", "
            r2.append(r1)
            java.lang.String r0 = r4.f69164c
            r2.append(r0)
            r2.append(r1)
            java.lang.String r0 = r4.f69165d
            r2.append(r0)
            java.lang.String r1 = r2.toString()
            java.lang.String r0 = "TextToSpeech_ElevenLabsTextToSpeechExecutor"
            com.vega.log.BLog.e(r0, r1)
        L75:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L78:
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r9.f74317d
            if (r0 == 0) goto L75
            com.vega.audio.tone.tts.core.TextToSpeechListener r3 = r0.l
            if (r3 == 0) goto L75
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r2 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r1 = com.vega.aigcapi.materialgenerate.EventType.b
            java.lang.Object r0 = r6.getSecond()
            com.lemon.lv.data.TextToAudioInfo r0 = (com.lemon.lv.data.TextToAudioInfo) r0
            r3.d(r2, r1, r4, r0)
            goto L75
        L8e:
            kotlin.ResultKt.throwOnFailure(r6)
            com.vega.audio.tone.tts.core.TextToSpeechTask r0 = r9.f74317d
            if (r0 == 0) goto La3
            com.vega.audio.tone.tts.core.TextToSpeechListener r4 = r0.l
            if (r4 == 0) goto La3
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r3 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.f74293d
            com.vega.aigcapi.materialgenerate.EventType r2 = com.vega.aigcapi.materialgenerate.EventType.f69147a
            r1 = 0
            r0 = 12
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r4, r3, r2, r1, r0)
        La3:
            kotlin.Lazy r0 = r9.f74316c
            java.lang.Object r0 = r0.getValue()
            com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager r0 = (com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager) r0
            r8.s = r5
            java.lang.Object r6 = r0.e(r10, r8)
            if (r6 != r7) goto L22
            return r7
        Lb4:
            com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$savingAudio$1 r8 = new com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$savingAudio$1
            r8.<init>(r9, r11)
            goto L12
        Lbb:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor.m(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
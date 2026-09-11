package com.vega.audio.tone.tts.core;

import com.mammon.audiosdk.SAMICore;
import com.vega.audio.tone.tts.Operation;
import com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor;
import com.vega.audio.tone.tts.core.executor.TextToSpeechExecutorPool;
import com.vega.audio.tone.tts.core.executor.impl.SAMITextToSpeechExecutor;
import com.vega.audio.tone.tts.core.queue.TextToSpeechMigrationQueue;
import com.vega.audio.tone.tts.core.queue.TextToSpeechParallelQueue;
import com.vega.audio.tone.tts.core.queue.TextToSpeechSerialQueue;
import com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine;
import com.vega.core.context.ContextExtHelper;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;

/* loaded from: classes2.dex */
public final class TextToSpeechTaskScheduler {
    public static final /* synthetic */ int f = 0;

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f74301a = new AtomicBoolean(false);
    public final TextToSpeechExecutorPool b;

    /* renamed from: c, reason: collision with root package name */
    public final TextToSpeechSerialQueue f74302c;

    /* renamed from: d, reason: collision with root package name */
    public final TextToSpeechParallelQueue f74303d;
    public final TextToSpeechMigrationQueue e;

    /* loaded from: classes17.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public TextToSpeechTaskScheduler() {
        TextToSpeechExecutorPool textToSpeechExecutorPool = new TextToSpeechExecutorPool(0);
        this.b = textToSpeechExecutorPool;
        this.f74302c = new TextToSpeechSerialQueue(textToSpeechExecutorPool);
        this.f74303d = new TextToSpeechParallelQueue(textToSpeechExecutorPool);
        this.e = new TextToSpeechMigrationQueue(textToSpeechExecutorPool);
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor.DefaultImpls.a(com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor):void */
    public static void a(TextToSpeechTaskScheduler textToSpeechTaskScheduler, boolean z) {
        Job job;
        Job job2;
        textToSpeechTaskScheduler.getClass();
        TextToSpeechSerialQueue textToSpeechSerialQueue = textToSpeechTaskScheduler.f74302c;
        textToSpeechSerialQueue.getClass();
        TextToSpeechExecutorType[] textToSpeechExecutorTypeArrValues = TextToSpeechExecutorType.values();
        int length = textToSpeechExecutorTypeArrValues.length;
        int i = 0;
        boolean z2 = false;
        while (true) {
            if (i >= length) {
                break;
            }
            ITextToSpeechExecutor iTextToSpeechExecutor = textToSpeechSerialQueue.f74359a.b.get(textToSpeechExecutorTypeArrValues[i]);
            if (iTextToSpeechExecutor != null) {
                TextToSpeechTask textToSpeechTaskA = iTextToSpeechExecutor.a();
                if ((textToSpeechTaskA != null ? textToSpeechTaskA.f74299d : null) == TextToSpeechTaskType.f74307a || z) {
                    iTextToSpeechExecutor.f(new Operation.Init(null));
                    iTextToSpeechExecutor.a();
                    z2 = true;
                }
            }
            i++;
        }
        if (z2 && (job2 = textToSpeechSerialQueue.f74360c) != null) {
            Job.DefaultImpls.cancel$default(job2, (CancellationException) null, 1, (Object) null);
        }
        textToSpeechTaskScheduler.f74303d.b("", new Operation.Init(null));
        TextToSpeechMigrationQueue textToSpeechMigrationQueue = textToSpeechTaskScheduler.e;
        textToSpeechMigrationQueue.getClass();
        boolean z3 = false;
        for (TextToSpeechRequestType textToSpeechRequestType : TextToSpeechRequestType.values()) {
            ITextToSpeechExecutor iTextToSpeechExecutor2 = textToSpeechMigrationQueue.f74351a.f74315d.get(textToSpeechRequestType);
            if (iTextToSpeechExecutor2 != null) {
                TextToSpeechTask textToSpeechTaskA2 = iTextToSpeechExecutor2.a();
                if ((textToSpeechTaskA2 != null ? textToSpeechTaskA2.f74299d : null) == TextToSpeechTaskType.f74307a || z) {
                    iTextToSpeechExecutor2.f(new Operation.Init(null));
                    iTextToSpeechExecutor2.a();
                    z3 = true;
                }
            }
        }
        if (!z3 || (job = textToSpeechMigrationQueue.f74352c) == null) {
            return;
        }
        Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
    }

    public final void b() {
        TextToSpeechSerialQueue textToSpeechSerialQueue = this.f74302c;
        textToSpeechSerialQueue.getClass();
        textToSpeechSerialQueue.a(new Operation.Init(null));
        this.f74303d.a();
        TextToSpeechMigrationQueue textToSpeechMigrationQueue = this.e;
        textToSpeechMigrationQueue.getClass();
        textToSpeechMigrationQueue.a(new Operation.Init(null));
        TextToSpeechExecutorPool textToSpeechExecutorPool = this.b;
        Collection<ITextToSpeechExecutor> collectionValues = textToSpeechExecutorPool.b.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues, "");
        Iterator<T> it = collectionValues.iterator();
        while (it.hasNext()) {
            ((ITextToSpeechExecutor) it.next()).close();
        }
        Collection<CopyOnWriteArrayList<ITextToSpeechExecutor>> collectionValues2 = textToSpeechExecutorPool.f74314c.values();
        Intrinsics.checkNotNullExpressionValue(collectionValues2, "");
        Iterator<T> it2 = collectionValues2.iterator();
        while (it2.hasNext()) {
            CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) it2.next();
            Intrinsics.checkNotNull(copyOnWriteArrayList);
            Iterator it3 = copyOnWriteArrayList.iterator();
            while (it3.hasNext()) {
                ((ITextToSpeechExecutor) it3.next()).close();
            }
            copyOnWriteArrayList.clear();
        }
        textToSpeechExecutorPool.b.clear();
        textToSpeechExecutorPool.f74314c.clear();
        SAMITextToSpeechExecutor.i.getClass();
        SamiTextToSpeechEngine.f74423a.getClass();
        SAMICore.ReleaseContext(ContextExtHelper.f79162a.appContext().getContext(), SamiTextToSpeechEngine.h);
        SamiTextToSpeechEngine.f74426g.set(false);
        this.f74301a.set(false);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(com.vega.audio.tone.tts.core.TextToSpeechTask r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeNonStreamingTask$1
            if (r0 == 0) goto L59
            r4 = r7
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeNonStreamingTask$1 r4 = (com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeNonStreamingTask$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L59
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L42
            if (r0 != r1) goto L69
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r1 = r6.j
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = com.vega.audio.tone.tts.core.TextToSpeechTaskState.f74304a
            if (r1 == r0) goto L5f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "executeNonStreamingTask: task state wrong: "
            r1.<init>(r0)
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = r6.j
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "TextToSpeech_TextToSpeechTaskScheduler"
            com.vega.log.BLog.e(r0, r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L42:
            kotlin.ResultKt.throwOnFailure(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.f74301a
            boolean r0 = r0.get()
            if (r0 != 0) goto L24
            r4.q = r6
            r4.t = r1
            r0 = 0
            java.lang.Object r0 = r5.g(r0, r4)
            if (r0 != r2) goto L24
            return r2
        L59:
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeNonStreamingTask$1 r4 = new com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeNonStreamingTask$1
            r4.<init>(r5, r7)
            goto L12
        L5f:
            com.vega.audio.tone.tts.core.queue.TextToSpeechMigrationQueue r1 = r5.e
            com.vega.audio.tone.tts.core.TextToSpeechRequestType r0 = com.vega.audio.tone.tts.core.TextToSpeechRequestType.b
            r1.b(r0, r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L69:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler.c(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object d(com.vega.audio.tone.tts.core.TextToSpeechTask r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeSavingTaskParallel$1
            if (r0 == 0) goto L59
            r4 = r7
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeSavingTaskParallel$1 r4 = (com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeSavingTaskParallel$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L59
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L42
            if (r0 != r1) goto L83
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r1 = r6.j
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = com.vega.audio.tone.tts.core.TextToSpeechTaskState.f74304a
            java.lang.String r2 = "TextToSpeech_TextToSpeechTaskScheduler"
            if (r1 == r0) goto L5f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "executeSavingTaskParallel: task state wrong: "
            r1.<init>(r0)
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = r6.j
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r2, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L42:
            kotlin.ResultKt.throwOnFailure(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.f74301a
            boolean r0 = r0.get()
            if (r0 != 0) goto L24
            r4.q = r6
            r4.t = r1
            r0 = 0
            java.lang.Object r0 = r5.g(r0, r4)
            if (r0 != r2) goto L24
            return r2
        L59:
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeSavingTaskParallel$1 r4 = new com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeSavingTaskParallel$1
            r4.<init>(r5, r7)
            goto L12
        L5f:
            com.vega.audio.tone.tts.core.TextToSpeechTaskType r1 = r6.f74299d
            com.vega.audio.tone.tts.core.TextToSpeechTaskType r0 = com.vega.audio.tone.tts.core.TextToSpeechTaskType.f74307a
            if (r1 != r0) goto L7b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "executeSavingTaskParallel: task type wrong: "
            r1.<init>(r0)
            com.vega.audio.tone.tts.core.TextToSpeechTaskType r0 = r6.f74299d
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.e(r2, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L7b:
            com.vega.audio.tone.tts.core.queue.TextToSpeechParallelQueue r0 = r5.f74303d
            r0.c(r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L83:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler.d(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object e(com.vega.audio.tone.tts.core.TextToSpeechTask r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeStreamingTask$1
            if (r0 == 0) goto L59
            r4 = r7
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeStreamingTask$1 r4 = (com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeStreamingTask$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L59
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L42
            if (r0 != r1) goto L69
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r1 = r6.j
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = com.vega.audio.tone.tts.core.TextToSpeechTaskState.f74304a
            if (r1 == r0) goto L5f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "executeStreamingTask: task state wrong: "
            r1.<init>(r0)
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = r6.j
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "TextToSpeech_TextToSpeechTaskScheduler"
            com.vega.log.BLog.e(r0, r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L42:
            kotlin.ResultKt.throwOnFailure(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.f74301a
            boolean r0 = r0.get()
            if (r0 != 0) goto L24
            r4.q = r6
            r4.t = r1
            r0 = 0
            java.lang.Object r0 = r5.g(r0, r4)
            if (r0 != r2) goto L24
            return r2
        L59:
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeStreamingTask$1 r4 = new com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeStreamingTask$1
            r4.<init>(r5, r7)
            goto L12
        L5f:
            com.vega.audio.tone.tts.core.queue.TextToSpeechMigrationQueue r1 = r5.e
            com.vega.audio.tone.tts.core.TextToSpeechRequestType r0 = com.vega.audio.tone.tts.core.TextToSpeechRequestType.f74295a
            r1.b(r0, r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L69:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler.e(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object f(com.vega.audio.tone.tts.core.TextToSpeechTask r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeTaskSerial$1
            if (r0 == 0) goto L59
            r4 = r7
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeTaskSerial$1 r4 = (com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeTaskSerial$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L59
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L42
            if (r0 != r1) goto L67
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r1 = r6.j
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = com.vega.audio.tone.tts.core.TextToSpeechTaskState.f74304a
            if (r1 == r0) goto L5f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "executeTaskSerial: task state wrong: "
            r1.<init>(r0)
            com.vega.audio.tone.tts.core.TextToSpeechTaskState r0 = r6.j
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "TextToSpeech_TextToSpeechTaskScheduler"
            com.vega.log.BLog.e(r0, r1)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L42:
            kotlin.ResultKt.throwOnFailure(r3)
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.f74301a
            boolean r0 = r0.get()
            if (r0 != 0) goto L24
            r4.q = r6
            r4.t = r1
            r0 = 0
            java.lang.Object r0 = r5.g(r0, r4)
            if (r0 != r2) goto L24
            return r2
        L59:
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeTaskSerial$1 r4 = new com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$executeTaskSerial$1
            r4.<init>(r5, r7)
            goto L12
        L5f:
            com.vega.audio.tone.tts.core.queue.TextToSpeechSerialQueue r0 = r5.f74302c
            r0.b(r6)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L67:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler.f(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(java.lang.String r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$init$1
            if (r0 == 0) goto L23
            r5 = r8
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$init$1 r5 = (com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$init$1) r5
            int r2 = r5.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L23
            int r2 = r2 - r1
            r5.t = r2
        L12:
            java.lang.Object r1 = r5.r
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r5.t
            r3 = 2
            r2 = 1
            if (r0 == 0) goto L3a
            if (r0 == r2) goto L35
            if (r0 != r3) goto L2d
            goto L29
        L23:
            com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$init$1 r5 = new com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler$init$1
            r5.<init>(r6, r8)
            goto L12
        L29:
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L75
            goto L7c
        L2d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L35:
            java.lang.Object r7 = r5.q
            java.lang.String r7 = (java.lang.String) r7
            goto L64
        L3a:
            kotlin.ResultKt.throwOnFailure(r1)
            java.util.concurrent.atomic.AtomicBoolean r1 = r6.f74301a
            r0 = 0
            boolean r0 = r1.compareAndSet(r0, r2)
            if (r0 != 0) goto L49
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L49:
            com.vega.audio.tone.tts.core.executor.impl.SAMITextToSpeechExecutor$Companion r0 = com.vega.audio.tone.tts.core.executor.impl.SAMITextToSpeechExecutor.i     // Catch: java.lang.Throwable -> L75
            r5.q = r7     // Catch: java.lang.Throwable -> L75
            r5.t = r2     // Catch: java.lang.Throwable -> L75
            r0.getClass()     // Catch: java.lang.Throwable -> L75
            com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine r0 = com.vega.audio.tone.tts.engine.sami.SamiTextToSpeechEngine.f74423a     // Catch: java.lang.Throwable -> L75
            java.lang.Object r1 = r0.c(r5)     // Catch: java.lang.Throwable -> L75
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()     // Catch: java.lang.Throwable -> L75
            if (r1 != r0) goto L5f
            goto L61
        L5f:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch: java.lang.Throwable -> L75
        L61:
            if (r1 != r4) goto L67
            return r4
        L64:
            kotlin.ResultKt.throwOnFailure(r1)     // Catch: java.lang.Throwable -> L75
        L67:
            com.vega.audio.tone.tts.core.executor.TextToSpeechExecutorPool r1 = r6.b     // Catch: java.lang.Throwable -> L75
            r0 = 0
            r5.q = r0     // Catch: java.lang.Throwable -> L75
            r5.t = r3     // Catch: java.lang.Throwable -> L75
            java.lang.Object r0 = r1.d(r7, r5)     // Catch: java.lang.Throwable -> L75
            if (r0 != r4) goto L7c
            return r4
        L75:
            java.lang.String r1 = "TextToSpeech_TextToSpeechTaskScheduler"
            java.lang.String r0 = "initInternal: error"
            com.vega.log.BLog.e(r1, r0)
        L7c:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler.g(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
package com.vega.audio.tone.tts.core.executor.impl;

import com.vega.aigcapi.materialgenerate.EventType;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.core.TextToSpeechExecutorType;
import com.vega.audio.tone.tts.core.TextToSpeechListener;
import com.vega.audio.tone.tts.core.TextToSpeechTask;
import com.vega.audio.tone.tts.core.TextToSpeechTaskType;
import com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

@DebugMetadata(c = "com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$2", f = "ElevenLabsTextToSpeechExecutor.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
public final class ElevenLabsTextToSpeechExecutor$executeTask$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Job>, Object> {
    public /* synthetic */ Object q;
    public final /* synthetic */ TextToSpeechTask r;
    public final /* synthetic */ ElevenLabsTextToSpeechExecutor s;

    @DebugMetadata(c = "com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$2$1", f = "ElevenLabsTextToSpeechExecutor.kt", i = {}, l = {70, 71}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$2$1, reason: invalid class name */
    /* loaded from: classes10.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int q;
        public final /* synthetic */ TextToSpeechTask r;
        public final /* synthetic */ ElevenLabsTextToSpeechExecutor s;

        /* renamed from: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$executeTask$2$1$WhenMappings */
        /* loaded from: classes37.dex */
        public /* synthetic */ class WhenMappings {
            static {
                TextToSpeechTaskType.values();
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(TextToSpeechTask textToSpeechTask, ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.r = textToSpeechTask;
            this.s = elevenLabsTextToSpeechExecutor;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.r, this.s, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:29:0x0085 -> B:31:0x00a2). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            TextToSpeechListener textToSpeechListener;
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.q;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    if (this.r.b.length() == 0) {
                        TtsResult ttsResult = new TtsResult(StatusResult.f69154c, 1100, "text is empty", null, 0L, false, null, null, null, false, false, null, null, 8184);
                        TextToSpeechTask textToSpeechTask = this.s.f74317d;
                        if (textToSpeechTask != null && (textToSpeechListener = textToSpeechTask.l) != null) {
                            TextToSpeechListener.DefaultImpls.b(textToSpeechListener, TextToSpeechExecutorType.f74293d, EventType.f69148c, ttsResult, 8);
                        }
                        this.s.l();
                        return Unit.INSTANCE;
                    }
                    int iOrdinal = this.r.f74299d.ordinal();
                    if (iOrdinal == 0) {
                        ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor = this.s;
                        TextToSpeechTask textToSpeechTask2 = this.r;
                        this.q = 1;
                        elevenLabsTextToSpeechExecutor.getClass();
                        if (BaseTextToSpeechExecutor.k(elevenLabsTextToSpeechExecutor, textToSpeechTask2, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else if (iOrdinal == 1) {
                        ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor2 = this.s;
                        TextToSpeechTask textToSpeechTask3 = this.r;
                        this.q = 2;
                        if (elevenLabsTextToSpeechExecutor2.m(textToSpeechTask3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    if (i != 1 && i != 2) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } finally {
                try {
                    this.s.l();
                    return Unit.INSTANCE;
                } catch (Throwable th) {
                }
            }
            this.s.l();
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsTextToSpeechExecutor$executeTask$2(TextToSpeechTask textToSpeechTask, ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor, Continuation<? super ElevenLabsTextToSpeechExecutor$executeTask$2> continuation) {
        super(2, continuation);
        this.r = textToSpeechTask;
        this.s = elevenLabsTextToSpeechExecutor;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ElevenLabsTextToSpeechExecutor$executeTask$2 elevenLabsTextToSpeechExecutor$executeTask$2 = new ElevenLabsTextToSpeechExecutor$executeTask$2(this.r, this.s, continuation);
        elevenLabsTextToSpeechExecutor$executeTask$2.q = obj;
        return elevenLabsTextToSpeechExecutor$executeTask$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Job> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        return BuildersKt__Builders_commonKt.launch$default((CoroutineScope) this.q, null, null, new AnonymousClass1(this.r, this.s, null), 3, null);
    }
}
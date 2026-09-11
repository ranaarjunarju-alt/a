package com.vega.audio.tone.tts.core.executor.impl;

import android.media.MediaPlayer;
import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.EventType;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.core.TextToSpeechExecutorType;
import com.vega.audio.tone.tts.core.TextToSpeechListener;
import com.vega.audio.tone.tts.core.TextToSpeechTask;
import com.vega.audio.tone.tts.engine.ThirdPartyPlayer;
import com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager;
import com.vega.core.ext.ContinuationExtKt;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1", f = "ElevenLabsTextToSpeechExecutor.kt", i = {}, l = {221}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes13.dex */
public final class ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    public Object q;
    public Function1 r;
    public int s;
    public final /* synthetic */ ElevenLabsTextToSpeechExecutor t;
    public final /* synthetic */ TtsResult u;
    public final /* synthetic */ String v;
    public final /* synthetic */ Function1<Pair<TextToAudioInfo, ? extends TtsResult>, Unit> w;
    public final /* synthetic */ Pair<TtsResult, TextToAudioInfo> x;

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: kotlin.jvm.functions.Function1<? super kotlin.Pair<com.lemon.lv.data.TextToAudioInfo, ? extends com.vega.aigcapi.materialgenerate.TtsResult>, kotlin.Unit> */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: kotlin.Pair<? extends com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo> */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1(ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor, TtsResult ttsResult, String str, Function1<? super Pair<TextToAudioInfo, ? extends TtsResult>, Unit> function1, Pair<? extends TtsResult, TextToAudioInfo> pair, Continuation<? super ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1> continuation) {
        super(2, continuation);
        this.t = elevenLabsTextToSpeechExecutor;
        this.u = ttsResult;
        this.v = str;
        this.w = function1;
        this.x = pair;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1(this.t, this.u, this.v, this.w, this.x, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TextToSpeechListener textToSpeechListener;
        TextToSpeechListener textToSpeechListener2;
        Object result = obj;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.s;
        if (i == 0) {
            ResultKt.throwOnFailure(result);
            final ElevenLabsTextToSpeechExecutor elevenLabsTextToSpeechExecutor = this.t;
            final TtsResult ttsResult = this.u;
            String str = this.v;
            final Function1<Pair<TextToAudioInfo, ? extends TtsResult>, Unit> function1 = this.w;
            final Pair<TtsResult, TextToAudioInfo> pair = this.x;
            this.q = str;
            this.r = function1;
            this.s = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            if (((ElevenLabsToneManager) elevenLabsTextToSpeechExecutor.f74316c.getValue()).f74378a.get()) {
                StatusResult statusResult = StatusResult.e;
                ttsResult.getClass();
                Intrinsics.checkNotNullParameter(statusResult, "");
                ttsResult.f69163a = statusResult;
                TextToSpeechTask textToSpeechTask = elevenLabsTextToSpeechExecutor.f74317d;
                if (textToSpeechTask != null && (textToSpeechListener2 = textToSpeechTask.l) != null) {
                    TextToSpeechListener.DefaultImpls.b(textToSpeechListener2, TextToSpeechExecutorType.f74293d, EventType.b, ttsResult, 8);
                }
                ContinuationExtKt.a(cancellableContinuationImpl, true);
            } else {
                TextToSpeechTask textToSpeechTask2 = elevenLabsTextToSpeechExecutor.f74317d;
                if (textToSpeechTask2 != null && (textToSpeechListener = textToSpeechTask2.l) != null) {
                    textToSpeechListener.a();
                }
                TextToSpeechTask textToSpeechTask3 = elevenLabsTextToSpeechExecutor.f74317d;
                float f = textToSpeechTask3 != null ? textToSpeechTask3.f74300g : 1.0f;
                ttsResult.e = System.currentTimeMillis() - elevenLabsTextToSpeechExecutor.f;
                ThirdPartyPlayer.a(ThirdPartyPlayer.f74363a, str, f, new Function0<Unit>() { // from class: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1$1$1
                    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function1<? super kotlin.Pair<com.lemon.lv.data.TextToAudioInfo, ? extends com.vega.aigcapi.materialgenerate.TtsResult>, kotlin.Unit> */
                    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.Pair<? extends com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo> */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        TextToSpeechListener textToSpeechListener3;
                        function1.invoke(TuplesKt.to(pair.getSecond(), pair.getFirst()));
                        TextToSpeechTask textToSpeechTask4 = elevenLabsTextToSpeechExecutor.f74317d;
                        if (textToSpeechTask4 != null && (textToSpeechListener3 = textToSpeechTask4.l) != null) {
                            TextToSpeechListener.DefaultImpls.b(textToSpeechListener3, TextToSpeechExecutorType.f74293d, EventType.f69149d, null, 12);
                        }
                        return Unit.INSTANCE;
                    }
                }, new MediaPlayer.OnCompletionListener() { // from class: com.vega.audio.tone.tts.core.executor.impl.ElevenLabsTextToSpeechExecutor$readAudioFromNet$playStatus$1$1$2
                    @Override // android.media.MediaPlayer.OnCompletionListener
                    public final void onCompletion(MediaPlayer mediaPlayer) {
                        TextToSpeechListener textToSpeechListener3;
                        TextToSpeechListener textToSpeechListener4;
                        if (mediaPlayer != null) {
                            TextToSpeechTask textToSpeechTask4 = elevenLabsTextToSpeechExecutor.f74317d;
                            if (textToSpeechTask4 != null && (textToSpeechListener4 = textToSpeechTask4.l) != null) {
                                TextToSpeechListener.DefaultImpls.b(textToSpeechListener4, TextToSpeechExecutorType.f74293d, EventType.e, null, 12);
                            }
                        } else {
                            TtsResult ttsResult2 = ttsResult;
                            StatusResult statusResult2 = StatusResult.f69154c;
                            ttsResult2.getClass();
                            Intrinsics.checkNotNullParameter(statusResult2, "");
                            ttsResult2.f69163a = statusResult2;
                            ttsResult.b = 3600;
                            TextToSpeechTask textToSpeechTask5 = elevenLabsTextToSpeechExecutor.f74317d;
                            if (textToSpeechTask5 != null && (textToSpeechListener3 = textToSpeechTask5.l) != null) {
                                TextToSpeechListener.DefaultImpls.b(textToSpeechListener3, TextToSpeechExecutorType.f74293d, EventType.f69148c, ttsResult, 8);
                            }
                        }
                        ContinuationExtKt.a(cancellableContinuationImpl, Boolean.TRUE);
                    }
                }, 2);
            }
            result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(this);
            }
            if (result == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(result);
        }
        return result;
    }
}
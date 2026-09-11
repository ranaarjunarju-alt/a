package com.vega.adeditor.utils;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.EventType;
import com.vega.aigcapi.materialgenerate.ReadingListener;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToAudioType;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

@DebugMetadata(c = "com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToAudioManager$3", f = "PlayTTSApiImpl.kt", i = {}, l = {257}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes16.dex */
public final class PlayTTSApiImpl$playTTSWithTextToAudioManager$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int q;
    public final /* synthetic */ String r;
    public final /* synthetic */ String s;
    public final /* synthetic */ float t;
    public final /* synthetic */ int u;
    public final /* synthetic */ PlayTTSApiImpl v;
    public final /* synthetic */ FragmentActivity w;

    @DebugMetadata(c = "com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToAudioManager$3$1", f = "PlayTTSApiImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.adeditor.utils.PlayTTSApiImpl$playTTSWithTextToAudioManager$3$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public final /* synthetic */ String q;
        public final /* synthetic */ String r;
        public final /* synthetic */ float s;
        public final /* synthetic */ int t;
        public final /* synthetic */ PlayTTSApiImpl u;
        public final /* synthetic */ FragmentActivity v;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public AnonymousClass1(String str, String str2, float f, int i, PlayTTSApiImpl playTTSApiImpl, FragmentActivity fragmentActivity, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.q = str;
            this.r = str2;
            this.s = f;
            this.t = i;
            this.u = playTTSApiImpl;
            this.v = fragmentActivity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.q, this.r, this.s, this.t, this.u, this.v, continuation);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ResultKt.throwOnFailure(obj);
            TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
            TextInfo.AutoSegText autoSegText = new TextInfo.AutoSegText(this.q);
            final String str = this.r;
            TTSBusinessType tTSBusinessType = TTSBusinessType.b;
            float f = this.s;
            int i = this.t;
            final PlayTTSApiImpl playTTSApiImpl = this.u;
            final FragmentActivity fragmentActivity = this.v;
            final String str2 = this.q;
            textToSpeechTaskManager.f(new TextToSpeechIntent(null, autoSegText, str, "sami", "PlayTTSApiImpl", null, tTSBusinessType, null, null, f, i, new ReadingListener() { // from class: com.vega.adeditor.utils.PlayTTSApiImpl.playTTSWithTextToAudioManager.3.1.1
                @Override // com.vega.aigcapi.materialgenerate.ReadingListener
                public final void a() {
                    playTTSApiImpl.f68227c.set(true);
                    playTTSApiImpl.e("loadSuccess", str);
                    LifecycleOwnerKt.a(fragmentActivity).d(new PlayTTSApiImpl$playTTSWithTextToAudioManager$3$1$1$onStartReading$1(str2, playTTSApiImpl, str, null));
                }

                @Override // com.vega.aigcapi.materialgenerate.ReadingListener
                public final void b() {
                    playTTSApiImpl.e("finish", str);
                }

                @Override // com.vega.aigcapi.materialgenerate.ReadingListener
                public final void c(TextToAudioType textToAudioType, EventType eventType, TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                    Intrinsics.checkNotNullParameter(textToAudioType, "");
                    Intrinsics.checkNotNullParameter(eventType, "");
                    if (eventType == EventType.f69148c) {
                        if ((ttsResult != null ? ttsResult.f69163a : null) != StatusResult.e) {
                            playTTSApiImpl.f68227c.set(true);
                            playTTSApiImpl.e("loadFail", str);
                        }
                    }
                }
            }, null, false, null, null, false, null, null, null, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -3679, 31));
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayTTSApiImpl$playTTSWithTextToAudioManager$3(String str, String str2, float f, int i, PlayTTSApiImpl playTTSApiImpl, FragmentActivity fragmentActivity, Continuation<? super PlayTTSApiImpl$playTTSWithTextToAudioManager$3> continuation) {
        super(2, continuation);
        this.r = str;
        this.s = str2;
        this.t = f;
        this.u = i;
        this.v = playTTSApiImpl;
        this.w = fragmentActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlayTTSApiImpl$playTTSWithTextToAudioManager$3(this.r, this.s, this.t, this.u, this.v, this.w, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.q;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.r, this.s, this.t, this.u, this.v, this.w, null);
            this.q = 1;
            if (TimeoutKt.withTimeout(5000L, anonymousClass1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
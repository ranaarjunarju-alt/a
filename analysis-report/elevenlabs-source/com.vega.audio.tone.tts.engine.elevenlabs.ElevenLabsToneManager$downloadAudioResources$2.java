package com.vega.audio.tone.tts.engine.elevenlabs;

import com.lemon.lv.data.TextToAudioInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2", f = "ElevenLabsToneManager.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes9.dex */
public final class ElevenLabsToneManager$downloadAudioResources$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public int q;
    public final /* synthetic */ List<ElevenLabsAudioResource> r;
    public final /* synthetic */ CompletableDeferred<Boolean> s;
    public final /* synthetic */ ElevenLabsToneManager t;
    public final /* synthetic */ TextToAudioInfo u;
    public final /* synthetic */ String v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsToneManager$downloadAudioResources$2(List<ElevenLabsAudioResource> list, CompletableDeferred<Boolean> completableDeferred, ElevenLabsToneManager elevenLabsToneManager, TextToAudioInfo textToAudioInfo, String str, Continuation<? super ElevenLabsToneManager$downloadAudioResources$2> continuation) {
        super(2, continuation);
        this.r = list;
        this.s = completableDeferred;
        this.t = elevenLabsToneManager;
        this.u = textToAudioInfo;
        this.v = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ElevenLabsToneManager$downloadAudioResources$2(this.r, this.s, this.t, this.u, this.v, continuation);
    }

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
            ArrayList arrayList = new ArrayList();
            List<ElevenLabsAudioResource> list = this.r;
            ElevenLabsToneManager elevenLabsToneManager = this.t;
            CompletableDeferred<Boolean> completableDeferred = this.s;
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(BuildersKt__Builders_commonKt.async$default((CoroutineScope) elevenLabsToneManager.f74379c.getValue(), null, null, new ElevenLabsToneManager$downloadAudioResources$2$1$task$1((ElevenLabsAudioResource) it.next(), completableDeferred, elevenLabsToneManager, null), 3, null));
            }
            this.q = 1;
            obj = AwaitKt.awaitAll(arrayList, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        TextToAudioInfo textToAudioInfo = this.u;
        String str = this.v;
        for (ElevenLabsSummaryData elevenLabsSummaryData : (Iterable) obj) {
            textToAudioInfo.f59067a.add(elevenLabsSummaryData.f74377d);
            textToAudioInfo.b.add(elevenLabsSummaryData.b);
            textToAudioInfo.f.add(elevenLabsSummaryData.f);
            if (str != null) {
                textToAudioInfo.f59070g.add(str);
            }
        }
        this.s.complete(true);
        return Unit.INSTANCE;
    }
}
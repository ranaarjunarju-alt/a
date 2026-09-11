package com.vega.audio.tone.tts.engine.elevenlabs;

import com.lemon.lv.data.SentenceData;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;
import com.vega.materialgenerate.TtsApiServiceManager;
import java.util.Map;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$getToneData$texts$segResp$1", f = "ElevenLabsToneManager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes23.dex */
public final class ElevenLabsToneManager$getToneData$texts$segResp$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Response<SentenceData>>, Object> {
    public final /* synthetic */ String q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsToneManager$getToneData$texts$segResp$1(String str, Continuation<? super ElevenLabsToneManager$getToneData$texts$segResp$1> continuation) {
        super(2, continuation);
        this.q = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ElevenLabsToneManager$getToneData$texts$segResp$1(this.q, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Response<SentenceData>> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objCreateFailure;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        try {
            Map mapMapOf = MapsKt__MapsJVMKt.mapOf(TuplesKt.to("text", this.q));
            TtsApiServiceManager ttsApiServiceManager = TtsApiServiceManager.f115001a;
            TypedJson.b.getClass();
            objCreateFailure = (Response) ttsApiServiceManager.generateSentence(TypedJson.Companion.b(mapMapOf)).execute().body();
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        if (Result.m17096isFailureimpl(objCreateFailure)) {
            return null;
        }
        return objCreateFailure;
    }
}
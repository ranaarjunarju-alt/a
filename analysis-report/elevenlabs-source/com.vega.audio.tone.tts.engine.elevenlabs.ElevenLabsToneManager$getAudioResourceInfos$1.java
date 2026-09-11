package com.vega.audio.tone.tts.engine.elevenlabs;

import com.bytedance.bdturing.EventReport;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.core.net.Response;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager", f = "ElevenLabsToneManager.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1}, l = {280, 319}, m = "getAudioResourceInfos", n = {EventReport.VERIFY_RESULT, "audioTaskRequestData", "requestTime", "requestInterval", "increasingRate", "enableIncreasingRate", "i", EventReport.VERIFY_RESULT, "audioTaskRequestData", "requestTime", "requestInterval", "increasingRate", "enableIncreasingRate", "i"}, s = {"L$0", "L$1", "J$0", "J$1", "D$0", "I$0", "J$2", "L$0", "L$1", "J$0", "J$1", "D$0", "I$0", "J$2"})
/* loaded from: classes4.dex */
public final class ElevenLabsToneManager$getAudioResourceInfos$1 extends ContinuationImpl {
    public TtsResult q;
    public Response r;
    public long s;
    public long t;
    public long u;
    public double v;
    public int w;
    public /* synthetic */ Object x;
    public final /* synthetic */ ElevenLabsToneManager y;
    public int z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsToneManager$getAudioResourceInfos$1(ElevenLabsToneManager elevenLabsToneManager, Continuation<? super ElevenLabsToneManager$getAudioResourceInfos$1> continuation) {
        super(continuation);
        this.y = elevenLabsToneManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.x = obj;
        this.z |= Integer.MIN_VALUE;
        return this.y.c(null, null, null, null, 0, null, null, this);
    }
}
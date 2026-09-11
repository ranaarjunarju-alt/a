package com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes;

import androidx.core.view.MotionEventCompat;
import com.bytedance.services.apm.api.EnsureManager;
import com.lemon.lv.data.SignPublicKeyData;
import com.vega.audio.tone.manager.TextToAudioRequest;
import com.vega.audio.tone.tts.config.TtsSignPublicKeyConfig;
import com.vega.audio.tone.tts.config.TtsSignPublicKeyConfigSettings;
import com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode;
import com.vega.clipflow.NodeResult;
import com.vega.config.ConfigSettingsKt;
import java.util.HashMap;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes.SignTextWithRSANode$runAsync$2", f = "SignTextWithRSANode.kt", i = {}, l = {MotionEventCompat.AXIS_GENERIC_8}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
public final class SignTextWithRSANode$runAsync$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super NodeResult<SignTextWithRSANode.Output>>, Object> {
    public int q;
    public final /* synthetic */ SignTextWithRSANode.Input r;
    public final /* synthetic */ SignTextWithRSANode s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignTextWithRSANode$runAsync$2(SignTextWithRSANode.Input input, SignTextWithRSANode signTextWithRSANode, Continuation<? super SignTextWithRSANode$runAsync$2> continuation) {
        super(2, continuation);
        this.r = input;
        this.s = signTextWithRSANode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SignTextWithRSANode$runAsync$2(this.r, this.s, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super NodeResult<SignTextWithRSANode.Output>> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.clipflow.ClipflowNodeError.DefaultImpls.b(com.vega.clipflow.ClipflowNodeError):com.vega.clipflow.NodeResult */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object objCreateFailure;
        String strA;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.q;
        try {
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                strA = ((TtsSignPublicKeyConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsSignPublicKeyConfigSettings.class))).a();
                if (strA.length() == 0) {
                    TextToAudioRequest textToAudioRequest = TextToAudioRequest.f74161a;
                    this.q = 1;
                    textToAudioRequest.getClass();
                    obj = TextToAudioRequest.a(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                byte[] bytes = this.r.f74412a.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "");
                this.s.getClass();
                return new NodeResult.Success(new SignTextWithRSANode.Output(SignTextWithRSANode.J(strA, bytes)));
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            this.s.getClass();
            return new NodeResult.Success(new SignTextWithRSANode.Output(SignTextWithRSANode.J(strA, bytes)));
        } catch (Exception e) {
            EnsureManager.ensureNotReachHere(e, e.getMessage());
            return SignTextWithRSANode.Error.f74410d.toResultWithMessage("encrypt fail " + e.getMessage(), new HashMap<>());
        }
        SignPublicKeyData signPublicKeyData = (SignPublicKeyData) obj;
        objCreateFailure = signPublicKeyData != null ? signPublicKeyData.getPublicKey() : null;
        Result.m17090constructorimpl(objCreateFailure);
        String str = (String) (Result.m17096isFailureimpl(objCreateFailure) ? null : objCreateFailure);
        strA = str == null ? "" : str;
        if (strA.length() == 0) {
            return SignTextWithRSANode.Error.f74409c.toResult(new HashMap<>());
        }
        byte[] bytes2 = this.r.f74412a.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes2, "");
    }
}
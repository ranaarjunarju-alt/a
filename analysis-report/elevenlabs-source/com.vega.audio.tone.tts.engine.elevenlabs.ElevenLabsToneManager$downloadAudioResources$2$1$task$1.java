package com.vega.audio.tone.tts.engine.elevenlabs;

import com.vega.audio.tone.tts.engine.ThirdpartyApiServiceFactory;
import com.vega.core.utils.Downloader;
import com.vega.core.utils.Success;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2$1$task$1", f = "ElevenLabsToneManager.kt", i = {0, 0}, l = {159}, m = "invokeSuspend", n = {"fileName", "dir"}, s = {"L$0", "L$1"})
/* loaded from: classes24.dex */
public final class ElevenLabsToneManager$downloadAudioResources$2$1$task$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ElevenLabsSummaryData>, Object> {
    public Object q;
    public Object r;
    public int s;
    public final /* synthetic */ ElevenLabsAudioResource t;
    public final /* synthetic */ CompletableDeferred<Boolean> u;
    public final /* synthetic */ ElevenLabsToneManager v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ElevenLabsToneManager$downloadAudioResources$2$1$task$1(ElevenLabsAudioResource elevenLabsAudioResource, CompletableDeferred<Boolean> completableDeferred, ElevenLabsToneManager elevenLabsToneManager, Continuation<? super ElevenLabsToneManager$downloadAudioResources$2$1$task$1> continuation) {
        super(2, continuation);
        this.t = elevenLabsAudioResource;
        this.u = completableDeferred;
        this.v = elevenLabsToneManager;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ElevenLabsToneManager$downloadAudioResources$2$1$task$1(this.t, this.u, this.v, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ElevenLabsSummaryData> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Class process forced to load method for inline: kotlin.collections.ArraysKt.D(byte[], java.lang.CharSequence, kotlin.jvm.functions.Function1):java.lang.String */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) throws NoSuchAlgorithmException {
        String strJoinToString$default;
        String string;
        Object objI = obj;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.s;
        if (i == 0) {
            ResultKt.throwOnFailure(objI);
            if (this.t.c().length() == 0) {
                String str = "Empty resource url(code:" + this.t.a() + ", msg:" + this.t.b() + ')';
                if (PerformanceManagerHelper.blogEnable) {
                    BLog.e("ElevenLabsToneManager", "downloadAudioResource(text:" + this.t.d() + ", result:" + str + ')');
                }
                this.u.complete(false);
                return new ElevenLabsSummaryData(ElevenLabsErrorCode.b, "", "", "", str, "");
            }
            String strC = this.t.c();
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] bytes = strC.getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "");
            byte[] bArrDigest = messageDigest.digest(bytes);
            Intrinsics.checkNotNull(bArrDigest);
            strJoinToString$default = ArraysKt___ArraysKt.joinToString$default(bArrDigest, (CharSequence) "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2$1$task$1$invokeSuspend$$inlined$md5$1
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final CharSequence invoke(Byte b) {
                    String str2 = String.format("%02x", Arrays.copyOf(new Object[]{Byte.valueOf(b.byteValue())}, 1));
                    Intrinsics.checkNotNullExpressionValue(str2, "");
                    return str2;
                }
            }, 30, (Object) null);
            StringBuilder sb = new StringBuilder();
            ThirdpartyApiServiceFactory.f74370a.getClass();
            sb.append(ThirdpartyApiServiceFactory.b);
            sb.append("/elevenlabs/");
            string = sb.toString();
            Downloader downloader = Downloader.f79569c;
            String strC2 = this.t.c();
            ElevenLabsToneManager$downloadAudioResources$2$1$task$1$res$1 elevenLabsToneManager$downloadAudioResources$2$1$task$1$res$1 = new Function1<Integer, Unit>() { // from class: com.vega.audio.tone.tts.engine.elevenlabs.ElevenLabsToneManager$downloadAudioResources$2$1$task$1$res$1
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Integer num) {
                    num.intValue();
                    return Unit.INSTANCE;
                }
            };
            this.q = strJoinToString$default;
            this.r = string;
            this.s = 1;
            objI = Downloader.i(downloader, strC2, string, strJoinToString$default, "11labs", null, null, null, null, null, 3, false, elevenLabsToneManager$downloadAudioResources$2$1$task$1$res$1, null, true, this, 96240);
            if (objI == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            string = (String) this.r;
            strJoinToString$default = (String) this.q;
            ResultKt.throwOnFailure(objI);
        }
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("ElevenLabsToneManager", "downloadAudioResource(name:" + strJoinToString$default + ", result:" + objI + ')');
        }
        ElevenLabsErrorCode elevenLabsErrorCode = objI instanceof Success ? ElevenLabsErrorCode.f74372a : this.v.f74378a.get() ? ElevenLabsErrorCode.f74373c : ElevenLabsErrorCode.b;
        if (elevenLabsErrorCode != ElevenLabsErrorCode.f74372a) {
            this.u.complete(false);
        }
        return new ElevenLabsSummaryData(elevenLabsErrorCode, this.t.d(), strJoinToString$default, string + strJoinToString$default, "", this.t.e());
    }
}
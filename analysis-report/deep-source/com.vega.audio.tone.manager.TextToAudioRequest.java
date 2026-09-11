package com.vega.audio.tone.manager;

import com.lemon.lv.data.SignPublicKeyData;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.Result;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;

/* loaded from: classes29.dex */
public final class TextToAudioRequest {

    /* renamed from: a, reason: collision with root package name */
    public static final TextToAudioRequest f74161a = new TextToAudioRequest();

    public static Object a(Continuation continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        TextToAudioServiceHelper.f74165a.getClass();
        TextToAudioService textToAudioService = (TextToAudioService) TextToAudioServiceHelper.b.getValue();
        TypedJson.b.getClass();
        Observable<Response<SignPublicKeyData>> observableTimeout = textToAudioService.getPublicKey(TypedJson.Companion.a()).timeout(30L, TimeUnit.SECONDS);
        final TextToAudioServiceHelper$getPublicKeyFromServer$1 textToAudioServiceHelper$getPublicKeyFromServer$1 = new Function1<Response<SignPublicKeyData>, SignPublicKeyData>() { // from class: com.vega.audio.tone.manager.TextToAudioServiceHelper$getPublicKeyFromServer$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final SignPublicKeyData invoke(Response<SignPublicKeyData> response) {
                Response<SignPublicKeyData> response2 = response;
                Intrinsics.checkNotNullParameter(response2, "");
                if (Intrinsics.areEqual(response2.getRet(), "0")) {
                    return response2.getData();
                }
                return null;
            }
        };
        Observable observableSubscribeOn = observableTimeout.map(new Function() { // from class: X.31e
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Function1 function1 = textToAudioServiceHelper$getPublicKeyFromServer$1;
                Intrinsics.checkNotNullParameter(obj, "");
                return function1.invoke(obj);
            }
        }).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "");
        final Disposable disposableSubscribe = observableSubscribeOn.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TextToAudioRequest$sam$io_reactivex_functions_Consumer$0(new Function1<SignPublicKeyData, Unit>() { // from class: com.vega.audio.tone.manager.TextToAudioRequest$getPublicKey$2$disposable$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(SignPublicKeyData signPublicKeyData) {
                CancellableContinuation<SignPublicKeyData> cancellableContinuation = cancellableContinuationImpl;
                Result.m17090constructorimpl(signPublicKeyData);
                cancellableContinuation.resumeWith(signPublicKeyData);
                return Unit.INSTANCE;
            }
        }), new TextToAudioRequest$sam$io_reactivex_functions_Consumer$0(new Function1<Throwable, Unit>() { // from class: com.vega.audio.tone.manager.TextToAudioRequest$getPublicKey$2$disposable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Throwable th) {
                CancellableContinuation<SignPublicKeyData> cancellableContinuation = cancellableContinuationImpl;
                Result.m17090constructorimpl(null);
                cancellableContinuation.resumeWith(null);
                return Unit.INSTANCE;
            }
        }));
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.vega.audio.tone.manager.TextToAudioRequest$getPublicKey$2$1
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Throwable th) {
                if (!disposableSubscribe.isDisposed()) {
                    disposableSubscribe.dispose();
                }
                return Unit.INSTANCE;
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    public static Object b(Continuation continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        TextToAudioServiceHelper.f74165a.getClass();
        TextToAudioService textToAudioService = (TextToAudioService) TextToAudioServiceHelper.b.getValue();
        TypedJson.b.getClass();
        Observable<Response<SamiTokenBean>> samiToken = textToAudioService.getSamiToken(TypedJson.Companion.a());
        final TextToAudioServiceHelper$getSamiTokenFromServer$1 textToAudioServiceHelper$getSamiTokenFromServer$1 = new Function1<Response<SamiTokenBean>, Pair<? extends SamiTokenBean, ? extends Long>>() { // from class: com.vega.audio.tone.manager.TextToAudioServiceHelper$getSamiTokenFromServer$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Pair<? extends SamiTokenBean, ? extends Long> invoke(Response<SamiTokenBean> response) {
                Response<SamiTokenBean> response2 = response;
                Intrinsics.checkNotNullParameter(response2, "");
                if (PerformanceManagerHelper.blogEnable) {
                    BLog.i("TextToAudioServiceHelper", "getSamiTokenFromServer ret = " + response2.getRet() + ", msg = " + response2.getErrmsg());
                }
                if (Intrinsics.areEqual(response2.getRet(), "0")) {
                    return new Pair<>(response2.getData(), Long.valueOf(response2.getServerTime()));
                }
                return null;
            }
        };
        Observable observableSubscribeOn = samiToken.map(new Function() { // from class: X.31f
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                Function1 function1 = textToAudioServiceHelper$getSamiTokenFromServer$1;
                Intrinsics.checkNotNullParameter(obj, "");
                return function1.invoke(obj);
            }
        }).timeout(30L, TimeUnit.SECONDS).subscribeOn(Schedulers.io());
        Intrinsics.checkNotNullExpressionValue(observableSubscribeOn, "");
        final Disposable disposableSubscribe = observableSubscribeOn.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TextToAudioRequest$sam$io_reactivex_functions_Consumer$0(new Function1<Pair<? extends SamiTokenBean, ? extends Long>, Unit>() { // from class: com.vega.audio.tone.manager.TextToAudioRequest$getSamiToken$2$disposable$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Pair<? extends SamiTokenBean, ? extends Long> pair) {
                CancellableContinuation<Pair<SamiTokenBean, Long>> cancellableContinuation = cancellableContinuationImpl;
                Result.m17090constructorimpl(pair);
                cancellableContinuation.resumeWith(pair);
                return Unit.INSTANCE;
            }
        }), new TextToAudioRequest$sam$io_reactivex_functions_Consumer$0(new Function1<Throwable, Unit>() { // from class: com.vega.audio.tone.manager.TextToAudioRequest$getSamiToken$2$disposable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Throwable th) {
                CancellableContinuation<Pair<SamiTokenBean, Long>> cancellableContinuation = cancellableContinuationImpl;
                Result.m17090constructorimpl(null);
                cancellableContinuation.resumeWith(null);
                return Unit.INSTANCE;
            }
        }));
        cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.vega.audio.tone.manager.TextToAudioRequest$getSamiToken$2$1
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Throwable th) {
                if (!disposableSubscribe.isDisposed()) {
                    disposableSubscribe.dispose();
                }
                return Unit.INSTANCE;
            }
        });
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }
}
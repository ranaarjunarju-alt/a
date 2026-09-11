package com.vega.adeditor.scriptvideo.service;

import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.vega.adeditor.scriptvideo.model.TextAudio;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.core.ext.ContinuationExtKt;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.ve.data.AudioMetaDataInfo;
import com.vega.ve.utils.MediaUtil;
import java.util.ArrayList;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.adeditor.scriptvideo.service.ScriptVideoTtsGenerateService$generateTts2$2", f = "ScriptVideoTtsGenerateService.kt", i = {}, l = {114}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes22.dex */
public final class ScriptVideoTtsGenerateService$generateTts2$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends TextAudio>>, Object> {
    public Object q;
    public int r;
    public final /* synthetic */ boolean s;
    public final /* synthetic */ String t;
    public final /* synthetic */ ToneType u;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ScriptVideoTtsGenerateService$generateTts2$2(ToneType toneType, String str, Continuation continuation, boolean z) {
        super(2, continuation);
        this.s = z;
        this.t = str;
        this.u = toneType;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        boolean z = this.s;
        return new ScriptVideoTtsGenerateService$generateTts2$2(this.u, this.t, continuation, z);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends TextAudio>> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object result = obj;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.r;
        if (i == 0) {
            ResultKt.throwOnFailure(result);
            final boolean z = this.s;
            final String str = this.t;
            ToneType toneType = this.u;
            this.q = str;
            this.r = 1;
            final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(this), 1);
            cancellableContinuationImpl.initCancellability();
            cancellableContinuationImpl.invokeOnCancellation(new Function1<Throwable, Unit>() { // from class: com.vega.adeditor.scriptvideo.service.ScriptVideoTtsGenerateService$generateTts2$2$1$1
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Throwable th) {
                    TextToSpeechTaskManager.f74281a.a("");
                    return Unit.INSTANCE;
                }
            });
            TextInfo autoSegText = z ? new TextInfo.AutoSegText(str) : new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(str));
            TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
            String voiceType = toneType.getVoiceType();
            String platform = toneType.getPlatform();
            Integer intOrNull = StringsKt__StringNumberConversionsKt.toIntOrNull(toneType.getRate());
            textToSpeechTaskManager.c(new TextToSpeechIntent(null, autoSegText, voiceType, platform, "ScriptVideoTtsGenerateService", null, TTSBusinessType.b, "script_video_edit", "script_video", 0.0f, intOrNull != null ? intOrNull.intValue() : 24000, null, null, false, new TextToSpeechReportInfo(TextToSpeechReportScene.CC4B_SCRIPT_TO_VIDEO, null, str.length(), false, false, 0L, null, null, null, null, 994, null).toJson(), null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.scriptvideo.service.ScriptVideoTtsGenerateService$generateTts2$2$1$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                /* JADX DEBUG: Multi-variable search result rejected for r12v1, resolved type: java.lang.Object[] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function2
                public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                    String str2;
                    TtsResult ttsResult2 = ttsResult;
                    TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                    Intrinsics.checkNotNullParameter(ttsResult2, "");
                    Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                    ArrayList arrayList = new ArrayList();
                    int size = textToAudioInfo2.f59067a.size();
                    for (int i2 = 0; i2 < size; i2++) {
                        String str3 = textToAudioInfo2.f59067a.get(i2);
                        MediaUtil.f135467a.getClass();
                        AudioMetaDataInfo audioMetaDataInfoC = MediaUtil.c(str3);
                        if (z) {
                            str2 = (String) CollectionsKt___CollectionsKt.getOrNull(textToAudioInfo2.b, i2);
                            if (str2 == null) {
                            }
                        } else {
                            str2 = str;
                        }
                        arrayList.add(new TextAudio(str2, str3, audioMetaDataInfoC.f135305a, null, (String) CollectionsKt___CollectionsKt.getOrNull(textToAudioInfo2.f59068c, i2), 8, 0 == true ? 1 : 0));
                    }
                    StatusResult statusResult = ttsResult2.f69163a;
                    if (statusResult == StatusResult.b || statusResult == StatusResult.f69155d) {
                        ContinuationExtKt.a(cancellableContinuationImpl, arrayList);
                    } else {
                        ContinuationExtKt.a(cancellableContinuationImpl, CollectionsKt__CollectionsKt.emptyList());
                    }
                    return Unit.INSTANCE;
                }
            }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -542175, 31));
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
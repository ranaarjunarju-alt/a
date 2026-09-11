package com.vega.adeditor.voiceover;

import androidx.lifecycle.LifecycleOwnerKt;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.lemon.lvoverseas.R;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.core.context.SPIService;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import com.vega.ui.dialog.BaseDialog;
import com.vega.util.ToastUtilKt;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

@DebugMetadata(c = "com.vega.adeditor.voiceover.AdTTSGenerateFragment$generateTTS$1", f = "AdTTSGenerateFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes22.dex */
public final class AdTTSGenerateFragment$generateTTS$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ String q;
    public final /* synthetic */ ToneType r;
    public final /* synthetic */ AdTTSGenerateFragment s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AdTTSGenerateFragment$generateTTS$1(String str, ToneType toneType, AdTTSGenerateFragment adTTSGenerateFragment, Continuation<? super AdTTSGenerateFragment$generateTTS$1> continuation) {
        super(2, continuation);
        this.q = str;
        this.r = toneType;
        this.s = adTTSGenerateFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AdTTSGenerateFragment$generateTTS$1(this.q, this.r, this.s, continuation);
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
        TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(TextToSpeechReportScene.CC4B_VOICE_OVER, null, this.q.length(), false, false, 0L, null, null, null, null, 994, null);
        TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
        TextInfo noSegTextList = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() ? new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(this.q)) : new TextInfo.AutoSegText(this.q);
        String voiceType = this.r.getVoiceType();
        String platform = this.r.getPlatform();
        TTSBusinessType tTSBusinessType = TTSBusinessType.b;
        String json = textToSpeechReportInfo.toJson();
        String auditionText = this.r.getAuditionText();
        String toneModelType = this.r.getToneModelType();
        String resourceId = this.r.getResourceId();
        final AdTTSGenerateFragment adTTSGenerateFragment = this.s;
        final ToneType toneType = this.r;
        textToSpeechTaskManager.c(new TextToSpeechIntent(null, noSegTextList, voiceType, platform, "AdTTSGenerateFragment", null, tTSBusinessType, "ads_template_edit", "ad_maker", 0.0f, 0, null, null, false, json, auditionText, false, toneModelType, resourceId, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.voiceover.AdTTSGenerateFragment$generateTTS$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                TtsResult ttsResult2 = ttsResult;
                TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                Intrinsics.checkNotNullParameter(ttsResult2, "");
                Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                StatusResult statusResult = ttsResult2.f69163a;
                AdTTSGenerateFragment adTTSGenerateFragment2 = adTTSGenerateFragment;
                if (!adTTSGenerateFragment2.f68273J) {
                    List<String> list = textToAudioInfo2.b;
                    List<String> list2 = textToAudioInfo2.f59067a;
                    ((BaseDialog) adTTSGenerateFragment2.K.getValue()).dismiss();
                    if (statusResult == StatusResult.b) {
                        AdTTSGenerateFragment adTTSGenerateFragment3 = adTTSGenerateFragment;
                        ToneType toneType2 = toneType;
                        adTTSGenerateFragment3.getClass();
                        if (!list2.isEmpty()) {
                            adTTSGenerateFragment3.D = toneType2;
                            if (!adTTSGenerateFragment3.E) {
                                adTTSGenerateFragment3.E = true;
                            }
                            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.a(adTTSGenerateFragment3), Dispatchers.getMain(), null, new AdTTSGenerateFragment$onGenerateSuccessNewArch$1(adTTSGenerateFragment3, list, list2, toneType2, null), 2, null);
                        }
                    } else {
                        ToastUtilKt.d(R.string.rqc, 0, 0, 0, 0, 254);
                        BLog.e("AdTTSGenerateFragment", "startSavingAudio error");
                    }
                }
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -967135, 31));
        return Unit.INSTANCE;
    }
}
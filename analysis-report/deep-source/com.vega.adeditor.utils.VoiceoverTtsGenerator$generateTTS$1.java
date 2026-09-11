package com.vega.adeditor.utils;

import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.vega.adeditor.voiceover.model.VoiceoverRepository;
import com.vega.adeditorapi.util.Status;
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
import com.vega.ve.utils.MediaUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CoroutineScope;

@DebugMetadata(c = "com.vega.adeditor.utils.VoiceoverTtsGenerator$generateTTS$1", f = "VoiceoverTtsGenerator.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes29.dex */
public final class VoiceoverTtsGenerator$generateTTS$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ ToneType q;
    public final /* synthetic */ String r;
    public final /* synthetic */ VoiceoverTtsGenerator s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceoverTtsGenerator$generateTTS$1(ToneType toneType, String str, VoiceoverTtsGenerator voiceoverTtsGenerator, Continuation<? super VoiceoverTtsGenerator$generateTTS$1> continuation) {
        super(2, continuation);
        this.q = toneType;
        this.r = str;
        this.s = voiceoverTtsGenerator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VoiceoverTtsGenerator$generateTTS$1(this.q, this.r, this.s, continuation);
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
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("effectId", this.q.getEffectId());
        TextInfo noSegTextList = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() ? new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(this.r)) : new TextInfo.AutoSegText(this.r);
        TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
        String voiceType = this.q.getVoiceType();
        String platform = this.q.getPlatform();
        TTSBusinessType tTSBusinessType = TTSBusinessType.b;
        String auditionText = this.q.getAuditionText();
        String json = new TextToSpeechReportInfo(TextToSpeechReportScene.CC4B_VOICE_OVER, null, noSegTextList.getText().length(), false, false, 0L, null, null, null, null, 994, null).toJson();
        String toneModelType = this.q.getToneModelType();
        String resourceId = this.q.getResourceId();
        final VoiceoverTtsGenerator voiceoverTtsGenerator = this.s;
        final ToneType toneType = this.q;
        textToSpeechTaskManager.c(new TextToSpeechIntent(null, noSegTextList, voiceType, platform, "VoiceoverTtsGenerator", null, tTSBusinessType, "ads_template_edit", "ad_maker", 0.0f, 0, null, linkedHashMap, false, json, auditionText, false, toneModelType, resourceId, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.utils.VoiceoverTtsGenerator$generateTTS$1.1
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
                voiceoverTtsGenerator.getClass();
                List<String> list = textToAudioInfo2.b;
                List<String> list2 = textToAudioInfo2.f59067a;
                if (statusResult == StatusResult.b) {
                    ArrayList arrayList = new ArrayList();
                    for (String str : list2) {
                        MediaUtil.f135467a.getClass();
                        arrayList.add(Integer.valueOf(MediaUtil.c(str).f135305a));
                    }
                    VoiceoverRepository.Companion companion = VoiceoverRepository.f68343a;
                    List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                    String toneName = toneType.getToneName();
                    String categoryName = toneType.getCategoryName();
                    String categoryID = toneType.getCategoryID();
                    String voiceType2 = toneType.getVoiceType();
                    boolean zIsAICloneTone = toneType.isAICloneTone();
                    companion.getClass();
                    voiceoverTtsGenerator.f68246a.postValue(new com.vega.adeditorapi.util.TtsResult(list, Status.f68540a, VoiceoverRepository.Companion.b(listEmptyList, list, list2, arrayList, toneName, categoryName, categoryID, voiceType2, zIsAICloneTone)));
                } else {
                    voiceoverTtsGenerator.f68246a.postValue(new com.vega.adeditorapi.util.TtsResult(list, Status.b, null));
                    BLog.e("AdTTSGenerateFragment", "startSavingAudio error");
                }
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -971231, 31));
        return Unit.INSTANCE;
    }
}
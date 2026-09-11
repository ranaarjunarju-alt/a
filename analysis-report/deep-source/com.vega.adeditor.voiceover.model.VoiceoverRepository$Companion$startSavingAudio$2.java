package com.vega.adeditor.voiceover.model;

import com.lemon.lv.config.BaseClientSetting;
import com.lemon.lv.config.ClientSetting;
import com.lemon.lv.data.TextToAudioInfo;
import com.vega.adeditor.utils.AdEditUtils;
import com.vega.adeditor.voiceover.model.VoiceoverRepository;
import com.vega.adeditorapi.VoiceoverData;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.gallery.local.MediaData;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import com.vega.ve.utils.MediaUtil;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
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
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import org.json.JSONObject;

@DebugMetadata(c = "com.vega.adeditor.voiceover.model.VoiceoverRepository$Companion$startSavingAudio$2", f = "VoiceoverRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes8.dex */
public final class VoiceoverRepository$Companion$startSavingAudio$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CompletableDeferred<VoiceoverData>>, Object> {
    public final /* synthetic */ String q;
    public final /* synthetic */ List<MediaData> r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VoiceoverRepository$Companion$startSavingAudio$2(String str, List<MediaData> list, Continuation<? super VoiceoverRepository$Companion$startSavingAudio$2> continuation) {
        super(2, continuation);
        this.q = str;
        this.r = list;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VoiceoverRepository$Companion$startSavingAudio$2(this.q, this.r, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super CompletableDeferred<VoiceoverData>> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String value;
        String value2;
        String value3;
        String value4;
        String value5;
        String value6;
        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ResultKt.throwOnFailure(obj);
        final CompletableDeferred completableDeferredCompletableDeferred$default = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (StringsKt__StringsKt.isBlank(this.q)) {
            BLog.e("Voiceover_VoiceoverRepository", "captionsStr is blank");
            completableDeferredCompletableDeferred$default.complete(null);
            return completableDeferredCompletableDeferred$default;
        }
        AdEditUtils.f68169a.getClass();
        JSONObject jSONObjectC = AdEditUtils.c();
        if (ExtentionKt.isNotNullOrEmpty(jSONObjectC.optString("tone_type_id"))) {
            value = jSONObjectC.optString("tone_type_id");
        } else {
            VoiceoverRepository.f68343a.getClass();
            value = VoiceoverRepository.e.getValue();
        }
        if (ExtentionKt.isNotNullOrEmpty(jSONObjectC.optString("tone_platform"))) {
            value2 = jSONObjectC.optString("tone_platform");
        } else {
            VoiceoverRepository.f68343a.getClass();
            value2 = VoiceoverRepository.f.getValue();
        }
        if (ExtentionKt.isNotNullOrEmpty(jSONObjectC.optString("tone_effect_id"))) {
            value3 = jSONObjectC.optString("tone_effect_id");
        } else {
            VoiceoverRepository.f68343a.getClass();
            value3 = VoiceoverRepository.f68346g.getValue();
        }
        if (ExtentionKt.isNotNullOrEmpty(jSONObjectC.optString("tone_name"))) {
            value4 = jSONObjectC.optString("tone_name");
        } else {
            VoiceoverRepository.f68343a.getClass();
            value4 = VoiceoverRepository.f68345d.getValue();
        }
        if (ExtentionKt.isNotNullOrEmpty(jSONObjectC.optString("tone_category"))) {
            value5 = jSONObjectC.optString("tone_category");
        } else {
            VoiceoverRepository.f68343a.getClass();
            value5 = VoiceoverRepository.i.getValue();
        }
        if (ExtentionKt.isNotNullOrEmpty(jSONObjectC.optString("tone_resource_id"))) {
            value6 = jSONObjectC.optString("tone_resource_id");
        } else {
            VoiceoverRepository.f68343a.getClass();
            value6 = VoiceoverRepository.h.getValue();
        }
        final boolean zOptBoolean = jSONObjectC.optBoolean("is_ai_clone_tone");
        SPIService sPIService = SPIService.INSTANCE;
        ((BaseClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getTtsNewFrameworkConfig().a();
        ((IEffectSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Intrinsics.checkNotNull(value3);
        linkedHashMap.put("effectId", value3);
        TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(TextToSpeechReportScene.CC4B_VOICE_OVER, null, this.q.length(), false, false, 0L, null, null, null, null, 994, null);
        TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
        TextInfo noSegTextList = ((IEffectSettings) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() ? new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(this.q)) : new TextInfo.AutoSegText(this.q);
        Intrinsics.checkNotNull(value);
        Intrinsics.checkNotNull(value2);
        TTSBusinessType tTSBusinessType = TTSBusinessType.b;
        String json = textToSpeechReportInfo.toJson();
        final List<MediaData> list = this.r;
        final String str = value4;
        final String str2 = value5;
        final String str3 = value6;
        final String str4 = value;
        textToSpeechTaskManager.c(new TextToSpeechIntent(null, noSegTextList, value, value2, "VoiceoverRepository", null, tTSBusinessType, "ads_template_edit", "ad_maker", 0.0f, 0, null, linkedHashMap, false, json, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.voiceover.model.VoiceoverRepository$Companion$startSavingAudio$2.1
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
                List<String> list2 = textToAudioInfo2.b;
                List<String> list3 = textToAudioInfo2.f59067a;
                Objects.toString(statusResult);
                list3.size();
                if (statusResult == StatusResult.b) {
                    ArrayList arrayList = new ArrayList();
                    for (String str5 : list3) {
                        MediaUtil.f135467a.getClass();
                        arrayList.add(Integer.valueOf(MediaUtil.c(str5).f135305a));
                    }
                    VoiceoverRepository.Companion companion = VoiceoverRepository.f68343a;
                    List<MediaData> list4 = list;
                    String str6 = str;
                    Intrinsics.checkNotNull(str6);
                    String str7 = str2;
                    Intrinsics.checkNotNull(str7);
                    String str8 = str3;
                    Intrinsics.checkNotNull(str8);
                    String str9 = str4;
                    Intrinsics.checkNotNull(str9);
                    boolean z = zOptBoolean;
                    companion.getClass();
                    completableDeferredCompletableDeferred$default.complete(VoiceoverRepository.Companion.b(list4, list2, list3, arrayList, str6, str7, str8, str9, z));
                } else {
                    BLog.e("Voiceover_VoiceoverRepository", "startSavingAudio error");
                    completableDeferredCompletableDeferred$default.complete(null);
                }
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -545247, 31));
        return completableDeferredCompletableDeferred$default;
    }
}
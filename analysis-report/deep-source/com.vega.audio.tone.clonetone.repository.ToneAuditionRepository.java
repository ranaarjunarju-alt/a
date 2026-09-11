package com.vega.audio.tone.clonetone.repository;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.TextToAudioManager;
import com.vega.audio.tone.clonetone.repository.ToneAuditionRepository;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.audio.tone.viewmodel.ListenKey;
import com.vega.core.context.SPIService;
import com.vega.core.utils.DirectoryUtil;
import com.vega.core.utils.FileUtils;
import com.vega.edit.base.audio.tone.DealStatus;
import com.vega.edit.base.audio.tone.TextToAudioInfoPack;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.util.FileUtil;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Result;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.SafeContinuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.random.Random;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes13.dex */
public final class ToneAuditionRepository {

    /* renamed from: a, reason: collision with root package name */
    public final HashMap<ListenKey, String> f74102a = new HashMap<>();
    public final HashMap<String, Long> b = new HashMap<>();

    /* loaded from: classes34.dex */
    public static final class AuditionResult {

        /* renamed from: a, reason: collision with root package name */
        public final boolean f74103a;
        public final String b;

        public AuditionResult(boolean z, String str) {
            Intrinsics.checkNotNullParameter(str, "");
            this.f74103a = z;
            this.b = str;
        }
    }

    /* loaded from: classes36.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public static String c(String str) {
        int iLastIndexOf$default = StringsKt__StringsKt.lastIndexOf$default(str, "/", 0, false, 6, (Object) null);
        String str2 = "";
        if (iLastIndexOf$default > 0) {
            String strSubstring = str.substring(iLastIndexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(strSubstring, "");
            str2 = strSubstring;
        }
        DirectoryUtil.f79563a.getClass();
        String strV = DirectoryUtil.v();
        StringBuilder sb = new StringBuilder();
        sb.append(strV);
        String str3 = File.separator;
        sb.append(str3);
        sb.append("audition");
        sb.append(str3);
        sb.append(str2);
        String string = sb.toString();
        if (!FileUtils.f(str, string)) {
            return str;
        }
        FileUtil fileUtil = FileUtil.f106629a;
        PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(str);
        fileUtil.getClass();
        FileUtil.h(polarisFileWrapper);
        return string;
    }

    public final long a(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Long lValueOf = this.b.get(str);
        if (lValueOf == null) {
            Collection<Long> collectionValues = this.b.values();
            Intrinsics.checkNotNullExpressionValue(collectionValues, "");
            lValueOf = Long.valueOf(Random.Default.nextLong());
            while (CollectionsKt___CollectionsKt.contains(collectionValues, lValueOf)) {
                lValueOf = Long.valueOf(Random.Default.nextLong());
            }
            this.b.put(str, lValueOf);
        }
        return lValueOf.longValue();
    }

    public final Object b(final ListenKey listenKey, String str, String str2, String str3, Continuation<? super AuditionResult> continuation) {
        final SafeContinuation safeContinuation = new SafeContinuation(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation));
        boolean zV = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v();
        String str4 = Intrinsics.areEqual(str3, "11labs") ? "11labs" : "sami";
        if (zV || Intrinsics.areEqual(str3, "11labs")) {
            TextToSpeechTaskManager.f74281a.c(new TextToSpeechIntent(null, new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(str)), str2, str4, "AudioClone", null, TTSBusinessType.f88857c, "", "", 0.0f, 24000, null, null, false, new TextToSpeechReportInfo(TextToSpeechReportScene.AUDIO_CLONE_AUDITION_RESULT, null, str.length(), true, false, 0L, null, null, null, null, 994, null).toJson(), null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.audio.tone.clonetone.repository.ToneAuditionRepository$requestAudition$2$intent$1

                /* loaded from: classes9.dex */
                public /* synthetic */ class WhenMappings {
                    static {
                        StatusResult.values();
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function2
                public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                    final DealStatus dealStatus;
                    TtsResult ttsResult2 = ttsResult;
                    final TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                    Intrinsics.checkNotNullParameter(ttsResult2, "");
                    Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                    int iOrdinal = ttsResult2.f69163a.ordinal();
                    if (iOrdinal == 0) {
                        dealStatus = DealStatus.b;
                    } else if (iOrdinal == 1) {
                        dealStatus = DealStatus.f87036c;
                    } else if (iOrdinal == 2) {
                        dealStatus = DealStatus.f87037d;
                    } else {
                        if (iOrdinal != 3) {
                            throw new NoWhenBranchMatchedException();
                        }
                        dealStatus = DealStatus.f87036c;
                    }
                    final ToneAuditionRepository toneAuditionRepository = this.e;
                    final ListenKey listenKey2 = listenKey;
                    final Continuation<ToneAuditionRepository.AuditionResult> continuation2 = safeContinuation;
                    ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.audio.tone.clonetone.repository.ToneAuditionRepository$requestAudition$2$intent$1.1
                        /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: kotlin.coroutines.Continuation<? super com.vega.audio.tone.clonetone.repository.ToneAuditionRepository$AuditionResult> */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final Unit invoke() {
                            if (dealStatus == DealStatus.b && (!textToAudioInfo2.f59067a.isEmpty())) {
                                ToneAuditionRepository toneAuditionRepository2 = toneAuditionRepository;
                                String str5 = textToAudioInfo2.f59067a.get(0);
                                toneAuditionRepository2.getClass();
                                String strC = ToneAuditionRepository.c(str5);
                                toneAuditionRepository.f74102a.put(listenKey2, strC);
                                Continuation<ToneAuditionRepository.AuditionResult> continuation3 = continuation2;
                                ToneAuditionRepository.AuditionResult auditionResult = new ToneAuditionRepository.AuditionResult(true, strC);
                                Result.m17090constructorimpl(auditionResult);
                                continuation3.resumeWith(auditionResult);
                            } else {
                                Continuation<ToneAuditionRepository.AuditionResult> continuation4 = continuation2;
                                ToneAuditionRepository.AuditionResult auditionResult2 = new ToneAuditionRepository.AuditionResult(false, "");
                                Result.m17090constructorimpl(auditionResult2);
                                continuation4.resumeWith(auditionResult2);
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, null, null, null, null, false, null, true, false, false, false, false, null, null, null, null, false, -134759903, 31));
        } else {
            TextToAudioManager.e(TextToAudioManager.f73896a, CollectionsKt__CollectionsJVMKt.listOf(str), str2, TextToSpeechReportScene.AUDIO_CLONE, new Function2<DealStatus, TextToAudioInfoPack, Unit>() { // from class: com.vega.audio.tone.clonetone.repository.ToneAuditionRepository$requestAudition$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function2
                public final Unit invoke(DealStatus dealStatus, TextToAudioInfoPack textToAudioInfoPack) {
                    TextToAudioInfoPack textToAudioInfoPack2 = textToAudioInfoPack;
                    Intrinsics.checkNotNullParameter(dealStatus, "");
                    Intrinsics.checkNotNullParameter(textToAudioInfoPack2, "");
                    BLog.i("ToneAuditionRepository", "requestAudition status=" + dealStatus);
                    if (dealStatus == DealStatus.b && (!textToAudioInfoPack2.f87040a.isEmpty())) {
                        ToneAuditionRepository toneAuditionRepository = this.e;
                        String str5 = textToAudioInfoPack2.f87040a.get(0);
                        toneAuditionRepository.getClass();
                        String strC = ToneAuditionRepository.c(str5);
                        this.e.f74102a.put(listenKey, strC);
                        Continuation<ToneAuditionRepository.AuditionResult> continuation2 = safeContinuation;
                        ToneAuditionRepository.AuditionResult auditionResult = new ToneAuditionRepository.AuditionResult(true, strC);
                        Result.m17090constructorimpl(auditionResult);
                        continuation2.resumeWith(auditionResult);
                    } else {
                        Continuation<ToneAuditionRepository.AuditionResult> continuation3 = safeContinuation;
                        ToneAuditionRepository.AuditionResult auditionResult2 = new ToneAuditionRepository.AuditionResult(false, "");
                        Result.m17090constructorimpl(auditionResult2);
                        continuation3.resumeWith(auditionResult2);
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        Object orThrow = safeContinuation.getOrThrow();
        if (orThrow == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return orThrow;
    }
}
package com.vega.adeditor.utils;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.lemon.lv.config.AiScriptVoiceoverToneConfig;
import com.lemon.lv.config.BaseClientSetting;
import com.lemon.lv.config.ClientSetting;
import com.lemon.lv.data.AudioInfo;
import com.lemon.lv.data.Emotion;
import com.lemon.lv.data.ScriptItem;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.lemon.lvoverseas.R;
import com.service.AIScriptApi;
import com.service.ISplitTextService;
import com.service.data.SplitStatus;
import com.service.data.SplitTextData;
import com.vega.adeditor.voiceover.model.AdTextToAudioManager;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.audio.tone.viewmodel.AudioToneSelectViewModel;
import com.vega.audio.tone.viewmodel.ToneSelectViewModel;
import com.vega.container.session.core.ISession;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.utils.FunctionsKt;
import com.vega.costreport.aigc.BabiParams;
import com.vega.costreport.aigc.BabiUtil;
import com.vega.edit.base.tone.EmotionOption;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.edit.base.view.BaseTabViewModel;
import com.vega.editorapi.util.EmotionUtilsKt;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.koin.ScopeProxy;
import com.vega.infrastructure.vm.ViewModelActivity;
import com.vega.libsticker.viewmodel.TextViewModel;
import com.vega.log.BLog;
import com.vega.middlebridge.client.DraftClient;
import com.vega.middlebridge.lyrasession.LyraSession;
import com.vega.middlebridge.swig.AddAudioParam;
import com.vega.middlebridge.swig.AddAudioParamModuleJNI;
import com.vega.middlebridge.swig.AddTextAudioParam;
import com.vega.middlebridge.swig.AddTextAudioParamModuleJNI;
import com.vega.middlebridge.swig.AddTextAudioReqStruct;
import com.vega.middlebridge.swig.ChangedNode;
import com.vega.middlebridge.swig.Draft;
import com.vega.middlebridge.swig.DraftBaseStructModuleJNI;
import com.vega.middlebridge.swig.DraftComboParams;
import com.vega.middlebridge.swig.DraftRespStruct;
import com.vega.middlebridge.swig.EditResult;
import com.vega.middlebridge.swig.IQueryUtils;
import com.vega.middlebridge.swig.LVVEMetaType;
import com.vega.middlebridge.swig.LVVETrackType;
import com.vega.middlebridge.swig.MapOfStringString;
import com.vega.middlebridge.swig.MaterialText;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentText;
import com.vega.middlebridge.swig.Track;
import com.vega.middlebridge.swig.VectorNodes;
import com.vega.middlebridge.swig.VectorOfPair;
import com.vega.middlebridge.swig.VectorOfSegment;
import com.vega.middlebridge.swig.VectorOfTrack;
import com.vega.subscriptionapi.biz.function.ICloneToneBusiness;
import com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction;
import com.vega.subscriptionapi.core.Result;
import com.vega.subscriptionapi.ext.ResultExKt;
import com.vega.ui.dialog.AIFeatureProgressDialog;
import com.vega.ui.dialog.BaseDialog;
import com.vega.ui.dialog.LvProgressDialog;
import com.vega.ui.util.ViewExKt;
import com.vega.util.ToastUtilKt;
import com.vega.ve.utils.AudioWaveUtils;
import com.vega.ve.utils.IQueryUtilsExKt;
import com.vega.ve.utils.MediaUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes40.dex */
public final class AIScriptImpl implements AIScriptApi {
    public static final /* synthetic */ int b = 0;

    /* renamed from: a, reason: collision with root package name */
    public boolean f68131a;

    /* loaded from: classes16.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public static void g(final ViewModelActivity viewModelActivity, ToneType toneType, String str, final Function1 function1) {
        if (toneType.isAICloneTone()) {
            ICloneToneBusiness.DefaultImpls.a((ICloneToneBusiness) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ICloneToneBusiness.class), null), IVoiceCloneApplyFunction.Scene.f131712a, CollectionsKt__CollectionsJVMKt.listOf(str), toneType.getToneName(), false, false, null, false, new Function2<Integer, Result, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$checkAICloneTone$1
                /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.jvm.functions.Function1<? super kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit>, kotlin.Unit> */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function2
                public final Unit invoke(Integer num, Result result) {
                    final int iIntValue = num.intValue();
                    final Result result2 = result;
                    Intrinsics.checkNotNullParameter(result2, "");
                    if (result2.f131803a == -8) {
                        AdEditUtils adEditUtils = AdEditUtils.f68169a;
                        ViewModelActivity viewModelActivity2 = viewModelActivity;
                        adEditUtils.getClass();
                        AdEditUtils.p(viewModelActivity2);
                    } else {
                        final Function1<Function1<? super Boolean, Unit>, Unit> function12 = function1;
                        result2.e(new Function1<Result, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$checkAICloneTone$1.1
                            /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function1<? super kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit>, kotlin.Unit> */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function1
                            public final Unit invoke(Result result3) {
                                Intrinsics.checkNotNullParameter(result3, "");
                                Function1<Function1<? super Boolean, Unit>, Unit> function13 = function12;
                                final int i = iIntValue;
                                final Result result4 = result2;
                                function13.invoke(new Function1<Boolean, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl.checkAICloneTone.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Unit invoke(Boolean bool) {
                                        ((ICloneToneBusiness) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ICloneToneBusiness.class), null)).k(bool.booleanValue(), i, ResultExKt.d(result4));
                                        return Unit.INSTANCE;
                                    }
                                });
                                return Unit.INSTANCE;
                            }
                        });
                        result2.c(new Function1<Result, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$checkAICloneTone$1.2
                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function1
                            public final Unit invoke(Result result3) {
                                Intrinsics.checkNotNullParameter(result3, "");
                                ToastUtilKt.d(R.string.qk7, 0, 0, 0, 0, 254);
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    return Unit.INSTANCE;
                }
            }, 248);
        } else {
            function1.invoke(null);
        }
    }

    public static List h(AIScriptImpl aIScriptImpl, Draft draft, List list, Set set) {
        int iIntValue;
        Integer numValueOf;
        aIScriptImpl.getClass();
        Intrinsics.checkNotNullParameter(draft, "");
        Intrinsics.checkNotNullParameter(set, "");
        if (!list.isEmpty()) {
            VectorOfTrack vectorOfTrackV = draft.v();
            ArrayList arrayList = new ArrayList();
            Iterator<Track> it = vectorOfTrackV.iterator();
            while (it.hasNext()) {
                Track next = it.next();
                if (next.f() == LVVETrackType.TrackTypeAudio) {
                    arrayList.add(next);
                }
            }
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it2 = arrayList.iterator();
            int i = 0;
            while (it2.hasNext()) {
                Object next2 = it2.next();
                int i2 = i + 1;
                if (i < 0) {
                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                }
                Track track = (Track) next2;
                if (track.e().isEmpty()) {
                    linkedHashSet.add(new ToneSelectViewModel.TrackInfo(i, new ArrayList()));
                } else {
                    VectorOfSegment vectorOfSegmentE = track.e();
                    ArrayList arrayList2 = new ArrayList();
                    Iterator<Segment> it3 = vectorOfSegmentE.iterator();
                    while (it3.hasNext()) {
                        Segment next3 = it3.next();
                        if (!set.contains(next3.b())) {
                            arrayList2.add(next3);
                        }
                    }
                    List<Segment> list2 = CollectionsKt___CollectionsKt.toList(arrayList2);
                    if (list2 != null) {
                        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                        for (Segment segment : list2) {
                            arrayList3.add(new ToneSelectViewModel.CMTimeRange(segment.k().e(), segment.k().d()));
                        }
                        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList3);
                        if (mutableList != null) {
                            linkedHashSet.add(new ToneSelectViewModel.TrackInfo(i, mutableList));
                        }
                    }
                }
                i = i2;
            }
            ArrayList arrayList4 = new ArrayList();
            Iterator it4 = list.iterator();
            while (it4.hasNext()) {
                ToneSelectViewModel.CMTimeRange cMTimeRange = (ToneSelectViewModel.CMTimeRange) it4.next();
                if (linkedHashSet.isEmpty()) {
                    iIntValue = 0;
                } else {
                    Iterator it5 = linkedHashSet.iterator();
                    while (it5.hasNext()) {
                        ToneSelectViewModel.TrackInfo trackInfo = (ToneSelectViewModel.TrackInfo) it5.next();
                        int i3 = trackInfo.f74649a;
                        List<ToneSelectViewModel.CMTimeRange> list3 = trackInfo.b;
                        if (list3 == null || !list3.isEmpty()) {
                            long j = cMTimeRange.f74646a;
                            long j2 = cMTimeRange.b + j;
                            if (list3 != null && list3.size() > 1) {
                                CollectionsKt__MutableCollectionsJVMKt.sortWith(list3, new Comparator() { // from class: com.vega.adeditor.utils.AIScriptImpl$canInsertThisTrack$$inlined$sortBy$1
                                    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: T */
                                    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: T */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // java.util.Comparator
                                    public final int compare(T t, T t2) {
                                        return ComparisonsKt__ComparisonsKt.compareValues(Long.valueOf(((ToneSelectViewModel.CMTimeRange) t).f74646a), Long.valueOf(((ToneSelectViewModel.CMTimeRange) t2).f74646a));
                                    }
                                });
                            }
                            long j3 = -1;
                            if (list3 != null) {
                                for (ToneSelectViewModel.CMTimeRange cMTimeRange2 : list3) {
                                    long j4 = cMTimeRange2.f74646a;
                                    long j5 = cMTimeRange2.b + j4;
                                    if ((j > j4 || j4 >= j2) && (j + 1 > j5 || j5 > j2)) {
                                        if (j3 <= j && j4 >= j2) {
                                            break;
                                        }
                                        j3 = j5;
                                    }
                                }
                            }
                            if (j3 <= j) {
                            }
                        }
                        numValueOf = Integer.valueOf(i3);
                    }
                    numValueOf = null;
                    ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(linkedHashSet, 10));
                    Iterator it6 = linkedHashSet.iterator();
                    while (it6.hasNext()) {
                        arrayList5.add(Integer.valueOf(((ToneSelectViewModel.TrackInfo) it6.next()).f74649a));
                    }
                    Integer num = (Integer) CollectionsKt___CollectionsKt.maxOrNull((Iterable) arrayList5);
                    iIntValue = (num != null ? num.intValue() : Integer.MAX_VALUE) + 1;
                    if (numValueOf != null) {
                        numValueOf.intValue();
                        arrayList4.add(new ToneSelectViewModel.InsertInfo(numValueOf.intValue()));
                        Iterator it7 = linkedHashSet.iterator();
                        while (it7.hasNext()) {
                            ToneSelectViewModel.TrackInfo trackInfo2 = (ToneSelectViewModel.TrackInfo) it7.next();
                            int i4 = trackInfo2.f74649a;
                            if (i4 != -1 && i4 == numValueOf.intValue()) {
                                List<ToneSelectViewModel.CMTimeRange> list4 = trackInfo2.b;
                                if (list4 != null) {
                                    list4.add(cMTimeRange);
                                }
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                    if (iIntValue <= Integer.MAX_VALUE) {
                    }
                }
                arrayList4.add(new ToneSelectViewModel.InsertInfo(iIntValue));
                linkedHashSet.add(new ToneSelectViewModel.TrackInfo(iIntValue, CollectionsKt__CollectionsKt.mutableListOf(cMTimeRange)));
            }
            if (((ArrayList) list).size() - arrayList4.size() == 0) {
                return arrayList4;
            }
        }
        return null;
    }

    public static Object i(String str, Continuation continuation) {
        final CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt__IntrinsicsJvmKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        BabiUtil.f79727a.getClass();
        BabiParams babiParamsW = BabiUtil.W("ai_text");
        ((ISplitTextService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ISplitTextService.class), null)).b(str, babiParamsW != null ? ExtentionKt.toJson(babiParamsW) : null, new Function2<SplitTextData, String, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$getSplitText$2$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(SplitTextData splitTextData, String str2) {
                SplitTextData splitTextData2 = splitTextData;
                Intrinsics.checkNotNullParameter(splitTextData2, "");
                Intrinsics.checkNotNullParameter(str2, "");
                SplitStatus splitStatus = splitTextData2.f64888a;
                if (splitStatus == SplitStatus.f64886c) {
                    ExtentionKt.safeResume(cancellableContinuationImpl, splitTextData2.b);
                } else if (splitStatus == SplitStatus.b) {
                    ExtentionKt.safeResume(cancellableContinuationImpl, null);
                }
                return Unit.INSTANCE;
            }
        }, CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()));
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        return result;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r27v10, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v11, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v12, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v13, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r27v9, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:126:0x047e  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0487  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x04aa  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0531  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0534  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x055c  */
    @Override // com.service.AIScriptApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a(final com.lemon.lv.data.TextToAudioInfo r56, final com.vega.infrastructure.vm.ViewModelActivity r57, com.lemon.lv.data.ToneType r58, com.lemon.lv.data.ScriptItem r59, boolean r60, com.lemon.lv.data.Emotion r61, boolean r62, boolean r63, final kotlin.jvm.functions.Function1<? super com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r64, final kotlin.jvm.functions.Function0<kotlin.Unit> r65, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r66, java.util.List<java.lang.String> r67) {
        /*
            r55 = this;
            java.lang.String r2 = ""
            r0 = r56
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r4 = r57
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r2)
            r53 = r58
            r1 = r53
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r11 = r59
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r2)
            kotlin.LazyThreadSafetyMode r3 = kotlin.LazyThreadSafetyMode.NONE
            com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$$inlined$factoryViewModel$1 r1 = new com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$$inlined$factoryViewModel$1
            r1.<init>()
            kotlin.Lazy r21 = kotlin.LazyKt__LazyJVMKt.lazy(r3, r1)
            com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$$inlined$factoryViewModel$2 r1 = new com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$$inlined$factoryViewModel$2
            r1.<init>()
            kotlin.Lazy r7 = kotlin.LazyKt__LazyJVMKt.lazy(r3, r1)
            if (r61 != 0) goto L3c
            java.lang.Object r3 = r7.getValue()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel r3 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel) r3
            java.lang.String r1 = r53.getVoiceType()
            com.lemon.lv.data.Emotion r61 = r3.t7(r1)
        L3c:
            r3 = 0
            r6 = 0
            r54 = r55
            r49 = r62
            r14 = r64
            r13 = r65
            r12 = r66
            if (r60 == 0) goto L3ca
            java.lang.Object r5 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r5 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r5
            java.util.List<java.lang.String> r9 = r0.b
            java.util.List<java.lang.String> r1 = r0.f59067a
            r52 = r1
            com.vega.core.context.SPIService r4 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.lemon.lv.config.ClientSetting> r1 = com.lemon.lv.config.ClientSetting.class
            kotlin.reflect.KClass r1 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r1)
            java.lang.Object r1 = r4.getImpl(r1, r6)
            com.lemon.lv.config.BaseClientSetting r1 = (com.lemon.lv.config.BaseClientSetting) r1
            com.lemon.lv.config.AutoCaptionsConfig r1 = r1.getAutoCaptionsConfig()
            java.util.List r1 = r1.a()
            java.lang.Object r3 = r1.get(r3)
            com.lemon.lv.config.LanguageItem r3 = (com.lemon.lv.config.LanguageItem) r3
            java.util.List r41 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            com.vega.middlebridge.swig.LVVESubtitleType r39 = com.vega.middlebridge.swig.LVVESubtitleType.SubTitleAiScript
            java.lang.Long r1 = r11.getStartTime()
            if (r1 == 0) goto L3c6
            long r23 = r1.longValue()
        L82:
            java.lang.Object r1 = r7.getValue()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel r1 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel) r1
            float r1 = r1.R
            r51 = r1
            if (r61 == 0) goto L94
            java.lang.String r20 = r61.getNameKey()
            if (r20 != 0) goto L96
        L94:
            r20 = r2
        L96:
            com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$1 r10 = new com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$1
            r10.<init>()
            r44 = 1
            r19 = 0
            com.vega.container.session.core.ISession r0 = r5.h
            r50 = r0
            boolean r0 = r50.a0()
            if (r0 == 0) goto Laf
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            r10.invoke(r0)
        Lae:
            return
        Laf:
            r0 = 1000(0x3e8, float:1.401E-42)
            long r0 = (long) r0
            long r23 = r23 / r0
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r16 = r52.iterator()
            r6 = 0
        Lc3:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L122
            java.lang.Object r1 = r16.next()
            int r12 = r6 + 1
            if (r6 >= 0) goto Ld4
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        Ld4:
            java.lang.String r1 = (java.lang.String) r1
            com.vega.ve.utils.MediaUtil r0 = com.vega.ve.utils.MediaUtil.f135467a
            r0.getClass()
            com.vega.ve.data.AudioMetaDataInfo r4 = com.vega.ve.utils.MediaUtil.c(r1)
            int r0 = r4.f135305a
            long r0 = (long) r0
            r14 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r14
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
            r13.add(r0)
            com.vega.operation.bean.Sentence r7 = new com.vega.operation.bean.Sentence
            java.lang.Object r6 = r9.get(r6)
            java.lang.String r6 = (java.lang.String) r6
            int r0 = r4.f135305a
            long r0 = (long) r0
            long r25 = r23 + r0
            r27 = 0
            r37 = 8176(0x1ff0, float:1.1457E-41)
            r21 = r7
            r22 = r6
            r28 = r27
            r29 = r27
            r30 = r27
            r31 = r27
            r32 = r27
            r33 = r27
            r34 = r27
            r35 = r27
            r36 = r27
            r38 = r27
            r21.<init>(r22, r23, r25, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)
            r8.add(r7)
            int r0 = r4.f135305a
            long r0 = (long) r0
            long r23 = r23 + r0
            r6 = r12
            goto Lc3
        L122:
            com.vega.middlebridge.swig.LVVEMetaType r40 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeSubtitle
            if (r3 == 0) goto L149
            java.lang.String r42 = r3.b()
        L12a:
            r43 = 0
            r48 = 122880(0x1e000, float:1.72192E-40)
            r37 = r5
            r38 = r8
            r45 = r19
            r46 = r43
            r47 = r43
            com.vega.middlebridge.swig.EditResult r0 = com.vega.libsticker.viewmodel.SubtitleViewModel.n6(r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48)
            if (r0 != 0) goto L14c
            r50.w0()
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r10.invoke(r0)
            goto Lae
        L149:
            r42 = 0
            goto L12a
        L14c:
            com.vega.middlebridge.swig.VectorNodes r3 = r0.d()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            java.util.ArrayList r1 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, r0)
            r1.<init>(r0)
            java.util.Iterator r4 = r3.iterator()
        L162:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L17f
            java.lang.Object r0 = r4.next()
            com.vega.middlebridge.swig.ChangedNode r0 = (com.vega.middlebridge.swig.ChangedNode) r0
            java.lang.String r3 = r0.a()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            r0 = r50
            com.vega.middlebridge.swig.Segment r0 = r0.i(r3)
            r1.add(r0)
            goto L162
        L17f:
            com.service.AiScriptInfo r15 = new com.service.AiScriptInfo
            java.lang.String r5 = r11.getSmartAdId()
            java.lang.String r6 = r11.getScriptRequestId()
            long r3 = r11.getScriptId()
            java.lang.String r7 = java.lang.String.valueOf(r3)
            java.lang.String r8 = r11.getRoutineName()
            r9 = 16
            r4 = r15
            r4.<init>(r5, r6, r7, r8, r9)
            java.util.ArrayList r18 = new java.util.ArrayList
            r18.<init>()
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            int r11 = r52.size()
            java.util.Iterator r17 = r1.iterator()
            r8 = 0
        L1b3:
            boolean r0 = r17.hasNext()
            if (r0 == 0) goto L322
            int r16 = r8 + 1
            java.lang.Object r3 = r17.next()
            com.vega.middlebridge.swig.Segment r3 = (com.vega.middlebridge.swig.Segment) r3
            int r0 = r52.size()
            if (r8 < r0) goto L1ca
        L1c7:
            r8 = r16
            goto L1b3
        L1ca:
            r0 = r52
            java.lang.Object r6 = r0.get(r8)
            java.lang.String r6 = (java.lang.String) r6
            boolean r0 = r3 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L1e0
            r12 = r3
            com.vega.middlebridge.swig.SegmentText r12 = (com.vega.middlebridge.swig.SegmentText) r12
        L1d9:
            int r0 = r6.length()
            if (r0 != 0) goto L1e2
            goto L1c7
        L1e0:
            r12 = 0
            goto L1d9
        L1e2:
            if (r8 >= r11) goto L1c7
            if (r12 != 0) goto L1e7
            goto L1c7
        L1e7:
            java.util.List<com.service.AiScriptSegmentInfo> r5 = r15.e
            com.service.AiScriptSegmentInfo r4 = new com.service.AiScriptSegmentInfo
            java.lang.String r1 = r12.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r2)
            com.vega.middlebridge.swig.MaterialText r0 = r12.u()
            if (r0 == 0) goto L2ab
            java.lang.String r0 = r0.a0()
        L1fc:
            if (r0 != 0) goto L1ff
            r0 = r2
        L1ff:
            r4.<init>(r1, r0)
            r5.add(r4)
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r14 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange
            com.vega.middlebridge.swig.TimeRange r0 = r12.k()
            long r4 = r0.e()
            java.lang.Object r0 = r13.get(r8)
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
            r14.<init>(r4, r0)
            r7.add(r14)
            com.vega.middlebridge.swig.AddTextAudioParam r4 = new com.vega.middlebridge.swig.AddTextAudioParam
            r4.<init>()
            com.vega.middlebridge.swig.AddAudioParam r5 = r4.d()
            r5.m(r6)
            java.lang.String r0 = r53.getToneName()
            r4.u(r0)
            java.lang.String r0 = r53.getPlatform()
            r4.q(r0)
            java.lang.String r0 = r53.getToneName()
            r4.p(r0)
            java.lang.String r0 = r53.getResourceId()
            r5.v(r0)
            java.lang.String r0 = r53.getEffectId()
            r4.o(r0)
            java.lang.String r0 = r53.getCategoryID()
            r4.m(r0)
            java.lang.String r0 = r53.getCategoryName()
            r4.n(r0)
            com.vega.middlebridge.swig.MaterialText r0 = r12.u()
            if (r0 == 0) goto L268
            java.lang.String r0 = r0.a0()
            if (r0 != 0) goto L269
        L268:
            r0 = r2
        L269:
            r5.n(r0)
            com.vega.middlebridge.swig.TimeRange r0 = r12.k()
            long r0 = r0.e()
            r5.B(r0)
            r0 = 0
            r5.A(r0)
            java.lang.Object r0 = r13.get(r8)
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
            r5.q(r0)
            com.vega.ve.utils.AudioWaveUtils r8 = com.vega.ve.utils.AudioWaveUtils.f135449a
            long r0 = r5.e()
            r8.getClass()
            float[] r14 = com.vega.ve.utils.AudioWaveUtils.a(r0, r6)
            int r12 = r14.length
            r8 = 0
        L298:
            if (r8 >= r12) goto L2ae
            r0 = r14[r8]
            com.vega.middlebridge.swig.VectorOfDouble r6 = r5.h()
            double r0 = (double) r0
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            r6.a(r0)
            int r8 = r8 + 1
            goto L298
        L2ab:
            r0 = 0
            goto L1fc
        L2ae:
            com.vega.middlebridge.swig.LVVEMetaType r0 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            r5.D(r0)
            r0 = r51
            double r0 = (double) r0
            r4.k(r0)
            boolean r0 = r53.isAICloneTone()
            r5.s(r0)
            java.lang.String r0 = r53.getVoiceType()
            r4.t(r0)
            java.lang.String r0 = r3.b()
            r4.l(r0)
            r0 = r19
            r4.j(r0)
            java.lang.String r0 = r53.getToneName()
            r4.u(r0)
            java.lang.String r0 = r53.getPlatform()
            r4.q(r0)
            java.lang.String r0 = r53.getToneName()
            r4.p(r0)
            java.lang.String r0 = r53.getResourceId()
            r4.i(r0)
            java.lang.String r0 = r53.getEffectId()
            r4.o(r0)
            java.lang.String r0 = r53.getCategoryID()
            r4.m(r0)
            java.lang.String r0 = r53.getCategoryName()
            r4.n(r0)
            long r0 = r4.f115688d
            r5 = r20
            com.vega.middlebridge.swig.AddTextAudioParamModuleJNI.AddTextAudioParam_tone_emotion_name_key_set(r0, r4, r5)
            com.vega.middlebridge.swig.MapOfStringString r5 = r4.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r2)
            java.lang.String r1 = "TEXT_SEGMENT_ID"
            java.lang.String r0 = r3.b()
            r5.put(r1, r0)
            r0 = r18
            r0.add(r4)
            goto L1c7
        L322:
            com.vega.middlebridge.swig.Draft r3 = r50.l()
            if (r3 == 0) goto L392
            java.util.Set r1 = kotlin.collections.SetsKt__SetsKt.emptySet()
            r0 = r54
            java.util.List r4 = h(r0, r3, r7, r1)
        L332:
            java.util.Iterator r7 = r18.iterator()
            r0 = 0
        L337:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L394
            java.lang.Object r3 = r7.next()
            int r6 = r0 + 1
            if (r0 >= 0) goto L348
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L348:
            com.vega.middlebridge.swig.AddTextAudioParam r3 = (com.vega.middlebridge.swig.AddTextAudioParam) r3
            com.vega.middlebridge.swig.IQueryUtils r13 = r50.m()
            if (r13 == 0) goto L390
            com.vega.middlebridge.swig.LVVETrackType r1 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeAudio
            java.util.List r14 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r1)
            com.vega.middlebridge.swig.AddAudioParam r1 = r3.d()
            long r15 = r1.g()
            com.vega.middlebridge.swig.AddAudioParam r1 = r3.d()
            long r17 = r1.e()
            r21 = 24
            r19 = r19
            r20 = r43
            int r5 = com.vega.ve.utils.IQueryUtilsExKt.b(r13, r14, r15, r17, r19, r20, r21)
        L370:
            com.vega.middlebridge.swig.AddAudioParam r1 = r3.d()
            if (r4 == 0) goto L380
            java.lang.Object r0 = r4.get(r0)
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.InsertInfo) r0
            if (r0 == 0) goto L380
            int r5 = r0.f74648a
        L380:
            r1.C(r5)
            com.vega.middlebridge.swig.AddTextAudioReqStruct r0 = new com.vega.middlebridge.swig.AddTextAudioReqStruct
            r0.<init>()
            r0.setParams(r3)
            r9.add(r0)
            r0 = r6
            goto L337
        L390:
            r5 = -1
            goto L370
        L392:
            r4 = 0
            goto L332
        L394:
            boolean r0 = r9.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L3bf
            com.vega.middlebridge.swig.DraftComboParams r4 = new com.vega.middlebridge.swig.DraftComboParams
            r4.<init>()
            java.lang.String r0 = "ADD_TEXT_AUDIO_ACTION"
            r4.d(r0)
            com.vega.middlebridge.swig.MapOfStringString r3 = r4.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            java.lang.String r1 = "need_delete_preview_result"
            java.lang.String r0 = java.lang.String.valueOf(r49)
            r3.put(r1, r0)
            com.vega.middlebridge.lyrasession.LyraSession r0 = r50.b()
            if (r0 == 0) goto L3bf
            com.vega.middlebridge.client.DraftClient.k(r0, r4, r9)
        L3bf:
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r10.invoke(r0)
            goto Lae
        L3c6:
            r23 = 0
            goto L82
        L3ca:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            r50 = r63
            if (r50 == 0) goto L56d
            java.lang.Object r1 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r1 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r1
            com.vega.container.session.core.ISession r1 = r1.h
            com.vega.middlebridge.swig.Draft r1 = r1.l()
            if (r1 == 0) goto L40c
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.q(r1)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            java.util.Iterator r4 = r1.iterator()
        L3f0:
            boolean r1 = r4.hasNext()
            if (r1 == 0) goto L40c
            java.lang.Object r3 = r4.next()
            com.vega.middlebridge.swig.Segment r3 = (com.vega.middlebridge.swig.Segment) r3
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r2)
            com.vega.middlebridge.swig.SegmentAudio r3 = (com.vega.middlebridge.swig.SegmentAudio) r3
            com.vega.middlebridge.swig.MaterialAudio r1 = r3.u()
            r1.C()
            r3.b()
            goto L3f0
        L40c:
            java.util.List<java.lang.String> r1 = r0.f59067a
            java.util.Iterator r20 = r1.iterator()
            r5 = 0
        L413:
            boolean r1 = r20.hasNext()
            if (r1 == 0) goto L5bc
            java.lang.Object r6 = r20.next()
            int r19 = r5 + 1
            if (r5 >= 0) goto L424
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L424:
            java.lang.String r6 = (java.lang.String) r6
            com.vega.ve.utils.MediaUtil r1 = com.vega.ve.utils.MediaUtil.f135467a
            r1.getClass()
            com.vega.ve.data.AudioMetaDataInfo r1 = com.vega.ve.utils.MediaUtil.c(r6)
            int r1 = r1.f135305a
            long r7 = (long) r1
            r1 = r67
            if (r1 == 0) goto L56a
            java.lang.Object r4 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r1, r5)
        L43a:
            java.lang.Object r1 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r1 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r1
            com.vega.container.session.core.ISession r1 = r1.h
            com.vega.middlebridge.swig.Draft r1 = r1.l()
            if (r1 == 0) goto L563
            java.util.List r1 = com.vega.middlebridge.expand.DraftExpandKt.B(r1)
            java.util.ArrayList r1 = (java.util.ArrayList) r1
            java.util.Iterator r11 = r1.iterator()
        L452:
            boolean r1 = r11.hasNext()
            if (r1 == 0) goto L560
            java.lang.Object r1 = r11.next()
            r3 = r1
            com.vega.middlebridge.swig.Segment r3 = (com.vega.middlebridge.swig.Segment) r3
            java.lang.String r3 = com.vega.ve.expand.UnifyTextExpandKt.y(r3)
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r4)
            if (r3 == 0) goto L452
        L469:
            com.vega.middlebridge.swig.Segment r1 = (com.vega.middlebridge.swig.Segment) r1
            if (r1 == 0) goto L564
            com.vega.middlebridge.swig.TimeRange r3 = r1.k()
            if (r3 == 0) goto L564
            long r25 = r3.e()
            r3 = 1000(0x3e8, float:1.401E-42)
        L479:
            long r3 = (long) r3
            long r25 = r25 / r3
            if (r1 == 0) goto L55c
            com.vega.middlebridge.swig.Material r18 = com.vega.middlebridge.expand.DraftExpandKt.Q(r1)
        L482:
            com.vega.ve.expand.UnifyTextExpandKt.y(r1)
            if (r1 == 0) goto L559
            com.vega.middlebridge.swig.Material r3 = com.vega.middlebridge.expand.DraftExpandKt.Q(r1)
            if (r3 == 0) goto L490
            r3.b()
        L490:
            com.vega.edit.base.utils.ToneUtil r3 = com.vega.edit.base.utils.ToneUtil.f89173a
            r3.getClass()
            java.util.List r3 = com.vega.edit.base.utils.ToneUtil.f(r1)
        L499:
            java.util.Objects.toString(r3)
            java.lang.Object r3 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r3 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r3
            com.vega.container.session.core.ISession r3 = r3.h
            com.vega.middlebridge.swig.Draft r3 = r3.l()
            if (r3 == 0) goto L4f7
            java.util.List r3 = com.vega.middlebridge.expand.DraftExpandKt.q(r3)
            java.util.ArrayList r3 = (java.util.ArrayList) r3
            java.util.Iterator r17 = r3.iterator()
        L4b4:
            boolean r3 = r17.hasNext()
            if (r3 == 0) goto L557
            java.lang.Object r11 = r17.next()
            r3 = r11
            com.vega.middlebridge.swig.Segment r3 = (com.vega.middlebridge.swig.Segment) r3
            com.vega.middlebridge.swig.TimeRange r4 = r3.k()
            long r15 = r4.e()
            int r4 = (r15 > r25 ? 1 : (r15 == r25 ? 0 : -1))
            if (r4 == 0) goto L4e6
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r2)
            com.vega.middlebridge.swig.SegmentAudio r3 = (com.vega.middlebridge.swig.SegmentAudio) r3
            com.vega.middlebridge.swig.MaterialAudio r3 = r3.u()
            java.lang.String r4 = r3.C()
            if (r18 == 0) goto L555
            java.lang.String r3 = r18.b()
        L4e0:
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r3 == 0) goto L4b4
        L4e6:
            com.vega.middlebridge.swig.Node r11 = (com.vega.middlebridge.swig.Node) r11
            if (r11 == 0) goto L4f7
            r11.toString()
            java.lang.String r3 = r11.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r2)
            r9.add(r3)
        L4f7:
            if (r1 == 0) goto L527
            com.vega.ve.expand.UnifyTextExpandKt.y(r1)
            com.vega.middlebridge.swig.TimeRange r3 = r1.k()
            r3.e()
            r3 = 1000(0x3e8, double:4.94E-321)
            long r31 = r7 * r3
            com.vega.edit.base.action.ActionDispatcher r11 = com.vega.edit.base.action.ActionDispatcher.f86111a
            java.lang.Object r3 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r3 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r3
            com.vega.container.session.core.ISession r3 = r3.h
            com.vega.middlebridge.swig.TimeRange r4 = r1.k()
            long r29 = r4.e()
            r33 = 1
            java.lang.Boolean r34 = java.lang.Boolean.FALSE
            r11.getClass()
            r27 = r3
            r28 = r1
            com.vega.edit.base.action.ActionDispatcher.m(r27, r28, r29, r31, r33, r34)
        L527:
            java.util.List<java.lang.String> r3 = r0.b
            java.lang.Object r3 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r3, r5)
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L532
            r3 = r2
        L532:
            if (r1 == 0) goto L552
            java.lang.String r32 = r1.b()
        L538:
            com.lemon.lv.data.AudioInfo r1 = new com.lemon.lv.data.AudioInfo
            java.lang.String r23 = ""
            r30 = 0
            r33 = 32
            r27 = r7
            r29 = r3
            r22 = r1
            r24 = r6
            r22.<init>(r23, r24, r25, r27, r29, r30, r32, r33)
            r10.add(r1)
            r5 = r19
            goto L413
        L552:
            r32 = 0
            goto L538
        L555:
            r3 = 0
            goto L4e0
        L557:
            r11 = 0
            goto L4e6
        L559:
            r3 = 0
            goto L499
        L55c:
            r18 = 0
            goto L482
        L560:
            r1 = 0
            goto L469
        L563:
            r1 = 0
        L564:
            r3 = 1000(0x3e8, float:1.401E-42)
            r25 = 0
            goto L479
        L56a:
            r4 = 0
            goto L43a
        L56d:
            java.util.List<java.lang.String> r1 = r0.f59067a
            java.util.Iterator r8 = r1.iterator()
            r5 = 0
            r25 = 0
        L576:
            boolean r1 = r8.hasNext()
            if (r1 == 0) goto L5bc
            java.lang.Object r6 = r8.next()
            int r7 = r5 + 1
            if (r5 >= 0) goto L587
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L587:
            java.lang.String r6 = (java.lang.String) r6
            com.vega.ve.utils.MediaUtil r1 = com.vega.ve.utils.MediaUtil.f135467a
            r1.getClass()
            com.vega.ve.data.AudioMetaDataInfo r1 = com.vega.ve.utils.MediaUtil.c(r6)
            int r1 = r1.f135305a
            long r3 = (long) r1
            java.util.List<java.lang.String> r1 = r0.b
            java.lang.Object r5 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r1, r5)
            java.lang.String r5 = (java.lang.String) r5
            if (r5 != 0) goto L5a0
            r5 = r2
        L5a0:
            com.lemon.lv.data.AudioInfo r1 = new com.lemon.lv.data.AudioInfo
            java.lang.String r23 = ""
            r30 = 0
            r32 = 0
            r33 = 96
            r27 = r3
            r29 = r5
            r22 = r1
            r24 = r6
            r22.<init>(r23, r24, r25, r27, r29, r30, r32, r33)
            r10.add(r1)
            long r25 = r25 + r3
            r5 = r7
            goto L576
        L5bc:
            boolean r1 = r9.isEmpty()
            r1 = r1 ^ 1
            if (r1 == 0) goto L5f3
            r9.toString()
            com.vega.middlebridge.swig.SegmentIdsParam r4 = new com.vega.middlebridge.swig.SegmentIdsParam
            r4.<init>()
            com.vega.middlebridge.swig.VectorOfString r1 = r4.e()
            r1.addAll(r9)
            java.lang.Object r1 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r1 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r1
            com.vega.container.session.core.ISession r1 = r1.h
            com.vega.middlebridge.lyrasession.LyraSession r3 = r1.b()
            if (r3 == 0) goto L5f0
            com.vega.middlebridge.swig.RemoveSegmentReqStruct r2 = new com.vega.middlebridge.swig.RemoveSegmentReqStruct
            r2.<init>()
            r2.setParams(r4)
            r1 = 0
            r2.setCommit_immediately(r1)
            com.vega.middlebridge.client.CommonClient.r(r3, r2)
        L5f0:
            r4.a()
        L5f3:
            r10.size()
            java.lang.Object r1 = r21.getValue()
            com.vega.libsticker.viewmodel.SubtitleViewModel r1 = (com.vega.libsticker.viewmodel.SubtitleViewModel) r1
            com.vega.container.session.core.ISession r2 = r1.h
            if (r61 == 0) goto L618
            java.lang.String r48 = r61.getNameKey()
        L604:
            com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$6 r1 = new com.vega.adeditor.utils.AIScriptImpl$genTTSByTextToAudioInfo$6
            r1.<init>()
            r44 = r54
            r45 = r2
            r46 = r10
            r47 = r53
            r51 = r1
            r44.e(r45, r46, r47, r48, r49, r50, r51)
            goto Lae
        L618:
            r48 = 0
            goto L604
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.adeditor.utils.AIScriptImpl.a(com.lemon.lv.data.TextToAudioInfo, com.vega.infrastructure.vm.ViewModelActivity, com.lemon.lv.data.ToneType, com.lemon.lv.data.ScriptItem, boolean, com.lemon.lv.data.Emotion, boolean, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, java.util.List):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v1, resolved type: int */
    /* JADX DEBUG: Multi-variable search result rejected for r25v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r25v9, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v10, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v11, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v12, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v13, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v14, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v15, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v16, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v17, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v18, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v19, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v20, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v21, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v22, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v23, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v24, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v25, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v26, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v27, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v28, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v9, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0 */
    /* JADX WARN: Type inference failed for: r10v4 */
    @Override // com.service.AIScriptApi
    public final void b(ViewModelActivity viewModelActivity, ScriptItem scriptItem, String str, boolean z, Function0 function0, final Function0 function02, Function0 function03, final Function0 function04) {
        LvProgressDialog lvProgressDialog;
        Intrinsics.checkNotNullParameter(viewModelActivity, "");
        Intrinsics.checkNotNullParameter(scriptItem, "");
        Intrinsics.checkNotNullParameter(str, "");
        if (StringsKt__StringsKt.isBlank(str)) {
            if (function03 != null) {
                function03.invoke();
            }
            if (function04 != null) {
                function04.invoke();
                return;
            }
            return;
        }
        AdTextToAudioManager.f68333a.getClass();
        AdTextToAudioManager.a();
        int i = 0;
        if (z) {
            i = 0;
            lvProgressDialog = new LvProgressDialog(viewModelActivity, false, false, false, false, 30);
            lvProgressDialog.setCanceledOnTouchOutside(false);
            lvProgressDialog.v = true;
            lvProgressDialog.C(ViewExKt.h(R.string._2r_res_0x7f12048f));
            lvProgressDialog.s = new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptSubTitle$dialog$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    this.e.f68131a = true;
                    TextToSpeechTaskManager.f74281a.a("");
                    Function0<Unit> function05 = function02;
                    if (function05 != null) {
                        function05.invoke();
                    }
                    return Unit.INSTANCE;
                }
            };
        } else {
            lvProgressDialog = null;
        }
        this.f68131a = i;
        if (lvProgressDialog != null) {
            if (!new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/LvProgressDialog", "show", lvProgressDialog, new Object[i], "void", new ExtraInfo((boolean) i, "()V", "dzBzEhQ/WMuSVFMkQRuQacWJWbIRYuQffTFp3HVhjf2pvoVA")).isIntercept()) {
                lvProgressDialog.show();
            }
        }
        AiScriptVoiceoverToneConfig aiScriptVoiceoverToneConfig = ((BaseClientSetting) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getAiScriptVoiceoverToneConfig();
        String strE = aiScriptVoiceoverToneConfig.e();
        String strG = aiScriptVoiceoverToneConfig.g();
        String strD = aiScriptVoiceoverToneConfig.d();
        String strF = aiScriptVoiceoverToneConfig.f();
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AIScriptImpl$genAiScriptSubTitle$1("type", str, this, new ToneType(null, 0 == true ? 1 : 0, strG, 0 == true ? 1 : 0, 0 == true ? 1 : 0, strE, aiScriptVoiceoverToneConfig.c(), 0 == true ? 1 : 0, aiScriptVoiceoverToneConfig.b(), 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, strF, strD, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, false, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0.0d, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, -12645, 1023, 0 == true ? 1 : 0), viewModelActivity, scriptItem, function0, function03, lvProgressDialog, null), 3, null).invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptSubTitle$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Throwable th) {
                Function0<Unit> function05 = function04;
                if (function05 != null) {
                    function05.invoke();
                }
                return Unit.INSTANCE;
            }
        });
    }

    @Override // com.service.AIScriptApi
    public final void c() {
        TextToSpeechTaskManager.f74281a.init(null);
    }

    @Override // com.service.AIScriptApi
    public final void d(final ViewModelActivity viewModelActivity, final ScriptItem scriptItem, final String str, final List list, final ToneType toneType, final Emotion emotion, final boolean z, final boolean z2, final boolean z3, final boolean z4, final Function1 function1, final Function0 function0, final Function0 function02, final Function0 function03) {
        Intrinsics.checkNotNullParameter(viewModelActivity, "");
        Intrinsics.checkNotNullParameter(scriptItem, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(toneType, "");
        if (!StringsKt__StringsKt.isBlank(str) || (list != null && !list.isEmpty())) {
            final String strJoinToString$default = (list == null || list.isEmpty()) ? str : CollectionsKt___CollectionsKt.joinToString$default(list, "", null, null, 0, null, null, 62, null);
            g(viewModelActivity, toneType, strJoinToString$default, new Function1<Function1<? super Boolean, ? extends Unit>, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1
                public final /* synthetic */ String l = "type";

                @DebugMetadata(c = "com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$1", f = "AIScriptImpl.kt", i = {0}, l = {764}, m = "invokeSuspend", n = {"reportInfo"}, s = {"L$0"})
                /* renamed from: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$1, reason: invalid class name */
                /* loaded from: classes13.dex */
                public final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ ViewModelActivity A;
                    public final /* synthetic */ ScriptItem B;
                    public final /* synthetic */ boolean C;
                    public final /* synthetic */ Emotion D;
                    public final /* synthetic */ Function1<TextToAudioInfo, Unit> E;
                    public final /* synthetic */ Function0<Unit> F;
                    public final /* synthetic */ Function1<Boolean, Unit> G;
                    public final /* synthetic */ BaseDialog H;
                    public TextToSpeechReportInfo q;
                    public int r;
                    public final /* synthetic */ String s;
                    public final /* synthetic */ boolean t;
                    public final /* synthetic */ String u;
                    public final /* synthetic */ List<String> v;
                    public final /* synthetic */ AIScriptImpl w;
                    public final /* synthetic */ String x;
                    public final /* synthetic */ ToneType y;
                    public final /* synthetic */ EmotionOption z;

                    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: kotlin.jvm.functions.Function1<? super com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> */
                    /* JADX DEBUG: Multi-variable search result rejected for r17v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public AnonymousClass1(String str, boolean z, String str2, List<String> list, AIScriptImpl aIScriptImpl, String str3, ToneType toneType, EmotionOption emotionOption, ViewModelActivity viewModelActivity, ScriptItem scriptItem, boolean z2, Emotion emotion, Function1<? super TextToAudioInfo, Unit> function1, Function0<Unit> function0, Function1<? super Boolean, Unit> function12, BaseDialog baseDialog, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.s = str;
                        this.t = z;
                        this.u = str2;
                        this.v = list;
                        this.w = aIScriptImpl;
                        this.x = str3;
                        this.y = toneType;
                        this.z = emotionOption;
                        this.A = viewModelActivity;
                        this.B = scriptItem;
                        this.C = z2;
                        this.D = emotion;
                        this.E = function1;
                        this.F = function0;
                        this.G = function12;
                        this.H = baseDialog;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, this.C, this.D, this.E, this.F, this.G, this.H, continuation);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARN: Removed duplicated region for block: B:10:0x001e  */
                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final java.lang.Object invokeSuspend(java.lang.Object r59) {
                        /*
                            r58 = this;
                            r2 = r59
                            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            r0 = r58
                            int r1 = r0.r
                            r3 = 1
                            if (r1 == 0) goto Ldd
                            if (r1 != r3) goto L130
                            com.vega.audio.tone.util.TextToSpeechReportInfo r5 = r0.q
                            kotlin.ResultKt.throwOnFailure(r2)
                        L14:
                            java.util.List r2 = (java.util.List) r2
                        L16:
                            if (r2 == 0) goto L1e
                            boolean r1 = r2.isEmpty()
                            if (r1 == 0) goto Ld6
                        L1e:
                            com.vega.edit.base.tone.TextInfo$AutoSegText r1 = new com.vega.edit.base.tone.TextInfo$AutoSegText
                            java.lang.String r2 = r0.x
                            r1.<init>(r2)
                        L25:
                            com.vega.audio.tone.tts.TextToSpeechTaskManager r3 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
                            com.lemon.lv.data.ToneType r2 = r0.y
                            java.lang.String r19 = r2.getVoiceType()
                            com.lemon.lv.data.ToneType r2 = r0.y
                            java.lang.String r20 = r2.getPlatform()
                            com.vega.edit.base.tone.TTSBusinessType r23 = com.vega.edit.base.tone.TTSBusinessType.f88857c
                            java.lang.String r31 = r5.toJson()
                            com.lemon.lv.data.ToneType r2 = r0.y
                            java.lang.String r39 = r2.getMockToneInfo()
                            com.lemon.lv.data.ToneType r2 = r0.y
                            boolean r16 = r2.isAICloneTone()
                            com.lemon.lv.data.ToneType r2 = r0.y
                            boolean r44 = r2.isV3ModelTone()
                            com.vega.edit.base.tone.TextToSpeechIntent r2 = new com.vega.edit.base.tone.TextToSpeechIntent
                            java.lang.String r21 = "AIScriptImpl"
                            r17 = 0
                            r27 = 0
                            com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$1$1 r36 = new com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$1$1
                            com.vega.adeditor.utils.AIScriptImpl r15 = r0.w
                            boolean r14 = r0.t
                            java.util.List<java.lang.String> r13 = r0.v
                            com.vega.infrastructure.vm.ViewModelActivity r12 = r0.A
                            com.lemon.lv.data.ToneType r11 = r0.y
                            com.lemon.lv.data.ScriptItem r10 = r0.B
                            boolean r9 = r0.C
                            com.lemon.lv.data.Emotion r8 = r0.D
                            kotlin.jvm.functions.Function1<com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r7 = r0.E
                            kotlin.jvm.functions.Function0<kotlin.Unit> r6 = r0.F
                            kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r5 = r0.G
                            com.vega.ui.dialog.BaseDialog r4 = r0.H
                            r45 = r36
                            r46 = r15
                            r47 = r14
                            r48 = r13
                            r49 = r12
                            r50 = r11
                            r51 = r10
                            r52 = r9
                            r53 = r8
                            r54 = r7
                            r55 = r6
                            r56 = r5
                            r57 = r4
                            r45.<init>()
                            com.vega.edit.base.tone.EmotionOption r4 = r0.z
                            boolean r0 = r0.t
                            java.lang.Boolean r42 = java.lang.Boolean.valueOf(r0)
                            java.lang.Boolean r43 = java.lang.Boolean.valueOf(r16)
                            r53 = -476594271(0xffffffffe397bfa1, float:-5.5985333E21)
                            r54 = 31
                            r26 = 0
                            r22 = r17
                            r24 = r17
                            r25 = r17
                            r28 = r17
                            r29 = r17
                            r30 = r27
                            r32 = r17
                            r33 = r27
                            r34 = r17
                            r35 = r17
                            r37 = r17
                            r38 = r4
                            r40 = r17
                            r41 = r27
                            r45 = r27
                            r46 = r27
                            r47 = r27
                            r48 = r17
                            r49 = r17
                            r50 = r17
                            r51 = r17
                            r52 = r27
                            r18 = r1
                            r16 = r2
                            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54)
                            r3.c(r2)
                            kotlin.Unit r0 = kotlin.Unit.INSTANCE
                            return r0
                        Ld6:
                            com.vega.edit.base.tone.TextInfo$NoSegTextList r1 = new com.vega.edit.base.tone.TextInfo$NoSegTextList
                            r1.<init>(r2)
                            goto L25
                        Ldd:
                            kotlin.ResultKt.throwOnFailure(r2)
                            com.vega.adeditor.voiceover.model.AdTextToAudioManager r1 = com.vega.adeditor.voiceover.model.AdTextToAudioManager.f68333a
                            java.lang.String r2 = r0.s
                            r1.getClass()
                            java.lang.String r1 = ""
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
                            com.vega.audio.tone.util.TextToSpeechReportInfo r5 = new com.vega.audio.tone.util.TextToSpeechReportInfo
                            boolean r1 = r0.t
                            if (r1 == 0) goto L11b
                            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r6 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.SMART_INTELLIGENT_EDIT
                        Lf4:
                            r7 = 0
                            java.lang.String r1 = r0.u
                            int r8 = r1.length()
                            r9 = 0
                            r11 = 0
                            r17 = 994(0x3e2, float:1.393E-42)
                            r10 = r9
                            r13 = r7
                            r14 = r7
                            r15 = r7
                            r16 = r7
                            r18 = r7
                            r5.<init>(r6, r7, r8, r9, r10, r11, r13, r14, r15, r16, r17, r18)
                            java.util.List<java.lang.String> r1 = r0.v
                            if (r1 == 0) goto L11e
                            boolean r1 = r1.isEmpty()
                            r1 = r1 ^ 1
                            if (r1 != r3) goto L11e
                            java.util.List<java.lang.String> r2 = r0.v
                            goto L16
                        L11b:
                            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r6 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AI_SCRIPT
                            goto Lf4
                        L11e:
                            com.vega.adeditor.utils.AIScriptImpl r2 = r0.w
                            java.lang.String r1 = r0.x
                            r0.q = r5
                            r0.r = r3
                            r2.getClass()
                            java.lang.Object r2 = com.vega.adeditor.utils.AIScriptImpl.i(r1, r0)
                            if (r2 != r4) goto L14
                            return r4
                        L130:
                            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r1.<init>(r0)
                            throw r1
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Multi-variable search result rejected for r6v2, resolved type: int */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r6v0 */
                /* JADX WARN: Type inference failed for: r6v1 */
                /* JADX WARN: Type inference failed for: r6v4 */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Function1<? super Boolean, ? extends Unit> function12) {
                    BaseDialog aIFeatureProgressDialog;
                    Function1<? super Boolean, ? extends Unit> function13 = function12;
                    AdTextToAudioManager.f68333a.getClass();
                    AdTextToAudioManager.a();
                    int i = 0;
                    i = 0;
                    if (z4) {
                        aIFeatureProgressDialog = null;
                    } else if (z2) {
                        ViewModelActivity viewModelActivity2 = viewModelActivity;
                        final AIScriptImpl aIScriptImpl = this;
                        final Function0<Unit> function04 = function02;
                        aIFeatureProgressDialog = new AIFeatureProgressDialog(viewModelActivity2, false, null, new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$dialog$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function0
                            public final Unit invoke() {
                                aIScriptImpl.f68131a = true;
                                TextToSpeechTaskManager.f74281a.a("");
                                Function0<Unit> function05 = function04;
                                if (function05 != null) {
                                    function05.invoke();
                                }
                                return Unit.INSTANCE;
                            }
                        }, 42);
                    } else {
                        i = 0;
                        LvProgressDialog lvProgressDialog = new LvProgressDialog(viewModelActivity, false, false, false, false, 30);
                        final AIScriptImpl aIScriptImpl2 = this;
                        final Function0<Unit> function05 = function02;
                        lvProgressDialog.setCanceledOnTouchOutside(false);
                        lvProgressDialog.v = true;
                        lvProgressDialog.s = new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$dialog$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function0
                            public final Unit invoke() {
                                aIScriptImpl2.f68131a = true;
                                TextToSpeechTaskManager.f74281a.a("");
                                Function0<Unit> function06 = function05;
                                if (function06 != null) {
                                    function06.invoke();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        lvProgressDialog.C(FunctionsKt.b(R.string._1l_res_0x7f120461));
                        aIFeatureProgressDialog = lvProgressDialog;
                    }
                    this.f68131a = i;
                    final ViewModelActivity viewModelActivity3 = viewModelActivity;
                    Lazy lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<AudioToneSelectViewModel>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1$invoke$$inlined$factoryViewModel$1
                        public final /* synthetic */ Qualifier f = null;

                        /* renamed from: g, reason: collision with root package name */
                        public final /* synthetic */ Function0 f68133g = null;
                        public final /* synthetic */ Function0 h = null;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.audio.tone.viewmodel.AudioToneSelectViewModel] */
                        @Override // kotlin.jvm.functions.Function0
                        public final AudioToneSelectViewModel invoke() {
                            CreationExtras defaultViewModelCreationExtras;
                            ComponentActivity componentActivity = viewModelActivity3;
                            Qualifier qualifier = this.f;
                            Function0 function06 = this.f68133g;
                            Function0 function07 = this.h;
                            ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                            if (function06 == null || (defaultViewModelCreationExtras = (CreationExtras) function06.invoke()) == null) {
                                defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                            }
                            ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(AudioToneSelectViewModel.class);
                            Intrinsics.checkNotNull(viewModelStore);
                            return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function07);
                        }
                    });
                    if (aIFeatureProgressDialog != null) {
                        if (!new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/BaseDialog", "show", aIFeatureProgressDialog, new Object[i], "void", new ExtraInfo((boolean) i, "()V", "dzBzEhQ/WMuSVFMkQRuQacWJWbIRYuQffTFp3HVhjf2pvoVA")).isIntercept()) {
                            aIFeatureProgressDialog.show();
                        }
                    }
                    Emotion emotionT7 = emotion;
                    if (emotionT7 == null) {
                        emotionT7 = ((ToneSelectViewModel) lazy.getValue()).t7(toneType.getVoiceType());
                    }
                    EmotionOption.h.getClass();
                    Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(this.l, z3, strJoinToString$default, list, this, str, toneType, EmotionOption.Companion.a(emotionT7), viewModelActivity, scriptItem, z, emotionT7, function1, function0, function13, aIFeatureProgressDialog, null), 3, null);
                    final Function0<Unit> function06 = function03;
                    jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTS$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final Unit invoke(Throwable th) {
                            Function0<Unit> function07 = function06;
                            if (function07 != null) {
                                function07.invoke();
                            }
                            return Unit.INSTANCE;
                        }
                    });
                    return Unit.INSTANCE;
                }
            });
            return;
        }
        if (function0 != null) {
            function0.invoke();
        }
        if (function03 != null) {
            function03.invoke();
        }
    }

    @Override // com.service.AIScriptApi
    public final void e(ISession iSession, List<AudioInfo> list, ToneType toneType, String str, boolean z, boolean z2, Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(iSession, "");
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(toneType, "");
        if (iSession.a0()) {
            if (function1 != null) {
                function1.invoke(Boolean.FALSE);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (AudioInfo audioInfo : list) {
            String str2 = audioInfo.b;
            if (str2.length() > 0) {
                AddTextAudioParam addTextAudioParam = new AddTextAudioParam();
                AddAudioParam addAudioParamD = addTextAudioParam.d();
                String str3 = audioInfo.f59041g;
                if (str3 != null) {
                    addTextAudioParam.l(str3);
                }
                AddTextAudioParamModuleJNI.AddTextAudioParam_need_bind_text_set(addTextAudioParam.f115688d, addTextAudioParam, false);
                addAudioParamD.m(str2);
                addTextAudioParam.u(toneType.getToneName());
                addTextAudioParam.q(toneType.getPlatform());
                addTextAudioParam.p(toneType.getToneName());
                addAudioParamD.v(toneType.getResourceId());
                addTextAudioParam.o(toneType.getEffectId());
                addTextAudioParam.m(toneType.getCategoryID());
                addTextAudioParam.n(toneType.getCategoryName());
                String str4 = audioInfo.e;
                if (str4 == null) {
                    str4 = "";
                }
                addAudioParamD.n(str4);
                addAudioParamD.B(audioInfo.f59039c * 1000);
                addAudioParamD.q(audioInfo.f59040d * 1000);
                AudioWaveUtils audioWaveUtils = AudioWaveUtils.f135449a;
                long jE = addAudioParamD.e() * 1000;
                audioWaveUtils.getClass();
                int length = AudioWaveUtils.a(jE, str2).length;
                for (int i = 0; i < length; i++) {
                    addAudioParamD.h().a(Double.valueOf(r14[i]));
                }
                addTextAudioParam.k(audioInfo.f);
                addAudioParamD.D(LVVEMetaType.MetaTypeTextToAudio);
                addAudioParamD.s(toneType.isAICloneTone());
                addTextAudioParam.t(toneType.getVoiceType());
                IQueryUtils iQueryUtilsM = iSession.m();
                addAudioParamD.C(z2 ? -1 : iQueryUtilsM != null ? IQueryUtilsExKt.b(iQueryUtilsM, CollectionsKt__CollectionsJVMKt.listOf(LVVETrackType.TrackTypeAudio), addAudioParamD.g(), addAudioParamD.e(), 0, null, 24) : -1);
                AddAudioParamModuleJNI.AddAudioParam_target_track_index_get(addAudioParamD.f115498d, addAudioParamD);
                addTextAudioParam.j(false);
                addTextAudioParam.u(toneType.getToneName());
                addTextAudioParam.q(toneType.getPlatform());
                addTextAudioParam.p(toneType.getToneName());
                addTextAudioParam.i(toneType.getResourceId());
                addTextAudioParam.o(toneType.getEffectId());
                addTextAudioParam.m(toneType.getCategoryID());
                addTextAudioParam.n(toneType.getCategoryName());
                if (str != null) {
                    AddTextAudioParamModuleJNI.AddTextAudioParam_tone_emotion_name_key_set(addTextAudioParam.f115688d, addTextAudioParam, str);
                }
                AddTextAudioReqStruct addTextAudioReqStruct = new AddTextAudioReqStruct();
                addTextAudioReqStruct.setParams(addTextAudioParam);
                arrayList.add(addTextAudioReqStruct);
            }
        }
        if (!arrayList.isEmpty()) {
            DraftComboParams draftComboParams = new DraftComboParams();
            draftComboParams.d("ADD_TEXT_AUDIO_ACTION");
            MapOfStringString mapOfStringStringB = draftComboParams.b();
            Intrinsics.checkNotNullExpressionValue(mapOfStringStringB, "");
            mapOfStringStringB.put("need_delete_preview_result", String.valueOf(z));
            LyraSession lyraSessionB = iSession.b();
            if (lyraSessionB != null) {
                DraftRespStruct draftRespStructK = DraftClient.k(lyraSessionB, draftComboParams, arrayList);
                StringBuilder sb = new StringBuilder("addAudioWithTitle result: ");
                sb.append(list.size());
                sb.append(' ');
                long jDraftRespStruct_changed_nodes_get = DraftBaseStructModuleJNI.DraftRespStruct_changed_nodes_get(draftRespStructK.f117450d, draftRespStructK);
                sb.append((jDraftRespStruct_changed_nodes_get == 0 ? null : new VectorOfPair(jDraftRespStruct_changed_nodes_get, false)).size());
                BLog.i("AIScriptImpl", sb.toString());
            }
        }
        if (function1 != null) {
            function1.invoke(Boolean.TRUE);
        }
    }

    @Override // com.service.AIScriptApi
    public final void f(final ViewModelActivity viewModelActivity, final ScriptItem scriptItem, final String str, final ToneType toneType, final Function0<Unit> function0, final Function0<Unit> function02, final Function0<Unit> function03, final Function0<Unit> function04) {
        Intrinsics.checkNotNullParameter(viewModelActivity, "");
        Intrinsics.checkNotNullParameter(scriptItem, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(toneType, "");
        if (StringsKt__StringsKt.isBlank(str)) {
            if (function03 != null) {
                function03.invoke();
            }
            if (function04 != null) {
                function04.invoke();
                return;
            }
            return;
        }
        final Lazy lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<AudioToneSelectViewModel>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$$inlined$factoryViewModel$1
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f68134g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.audio.tone.viewmodel.AudioToneSelectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AudioToneSelectViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = viewModelActivity;
                Qualifier qualifier = this.f;
                Function0 function05 = this.f68134g;
                Function0 function06 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function05 == null || (defaultViewModelCreationExtras = (CreationExtras) function05.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(AudioToneSelectViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function06);
            }
        });
        final ISession iSession = ((BaseTabViewModel) lazy.getValue()).o;
        if (!iSession.a0()) {
            g(viewModelActivity, toneType, str, new Function1<Function1<? super Boolean, ? extends Unit>, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$1

                @DebugMetadata(c = "com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$1$1", f = "AIScriptImpl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$1$1, reason: invalid class name */
                /* loaded from: classes5.dex */
                public final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    public final /* synthetic */ Function0<Unit> A;
                    public final /* synthetic */ Function0<Unit> B;
                    public final /* synthetic */ String q;
                    public final /* synthetic */ ToneType r;
                    public final /* synthetic */ EmotionOption s;
                    public final /* synthetic */ List<Segment> t;
                    public final /* synthetic */ ISession u;
                    public final /* synthetic */ AIScriptImpl v;
                    public final /* synthetic */ Emotion w;
                    public final /* synthetic */ Lazy<AudioToneSelectViewModel> x;
                    public final /* synthetic */ LvProgressDialog y;
                    public final /* synthetic */ Function1<Boolean, Unit> z;

                    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
                    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: java.util.List<? extends com.vega.middlebridge.swig.Segment> */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    public AnonymousClass1(String str, ToneType toneType, EmotionOption emotionOption, List<? extends Segment> list, ISession iSession, AIScriptImpl aIScriptImpl, Emotion emotion, Lazy<AudioToneSelectViewModel> lazy, LvProgressDialog lvProgressDialog, Function1<? super Boolean, Unit> function1, Function0<Unit> function0, Function0<Unit> function02, Continuation<? super AnonymousClass1> continuation) {
                        super(2, continuation);
                        this.q = str;
                        this.r = toneType;
                        this.s = emotionOption;
                        this.t = list;
                        this.u = iSession;
                        this.v = aIScriptImpl;
                        this.w = emotion;
                        this.x = lazy;
                        this.y = lvProgressDialog;
                        this.z = function1;
                        this.A = function0;
                        this.B = function02;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(this.q, this.r, this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B, continuation);
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
                        AdTextToAudioManager.f68333a.getClass();
                        TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(TextToSpeechReportScene.AI_SCRIPT, null, this.q.length(), false, false, 0L, null, null, null, null, 994, null);
                        TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
                        TextInfo.NoSegTextList noSegTextList = new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(this.q));
                        String voiceType = this.r.getVoiceType();
                        String platform = this.r.getPlatform();
                        TTSBusinessType tTSBusinessType = TTSBusinessType.f88857c;
                        String json = textToSpeechReportInfo.toJson();
                        String mockToneInfo = this.r.getMockToneInfo();
                        boolean zIsAICloneTone = this.r.isAICloneTone();
                        boolean zIsV3ModelTone = this.r.isV3ModelTone();
                        final List<Segment> list = this.t;
                        final ISession iSession = this.u;
                        final AIScriptImpl aIScriptImpl = this.v;
                        final ToneType toneType = this.r;
                        final Emotion emotion = this.w;
                        final Lazy<AudioToneSelectViewModel> lazy = this.x;
                        final LvProgressDialog lvProgressDialog = this.y;
                        final Function1<Boolean, Unit> function1 = this.z;
                        final Function0<Unit> function0 = this.A;
                        final Function0<Unit> function02 = this.B;
                        textToSpeechTaskManager.c(new TextToSpeechIntent(null, noSegTextList, voiceType, platform, "AIScriptImpl", null, tTSBusinessType, null, null, 0.0f, 0, null, null, false, json, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl.genAiScriptTTSNotSpilt.1.1.1
                            /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.util.List<? extends com.vega.middlebridge.swig.Segment> */
                            /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function2
                            public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                                final boolean z;
                                LyraSession lyraSessionB;
                                ToneSelectViewModel.InsertInfo insertInfo;
                                String strA0;
                                TtsResult ttsResult2 = ttsResult;
                                TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                                Intrinsics.checkNotNullParameter(ttsResult2, "");
                                Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                                AdTextToAudioManager.f68333a.getClass();
                                List<String> list2 = textToAudioInfo2.f59067a;
                                if (ttsResult2.f69163a == StatusResult.b) {
                                    ArrayList arrayList = new ArrayList();
                                    ArrayList arrayList2 = new ArrayList();
                                    int i = 0;
                                    for (Object obj2 : list2) {
                                        int i2 = i + 1;
                                        if (i < 0) {
                                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                                        }
                                        MediaUtil.f135467a.getClass();
                                        arrayList.add(Long.valueOf(MediaUtil.c((String) obj2).f135305a * 1000));
                                        i = i2;
                                    }
                                    ArrayList arrayList3 = new ArrayList();
                                    ArrayList arrayList4 = new ArrayList();
                                    int i3 = 0;
                                    for (Segment segment : list) {
                                        int i4 = i3 + 1;
                                        String str = list2.get(i3);
                                        SegmentText segmentText = segment instanceof SegmentText ? (SegmentText) segment : null;
                                        if (str.length() != 0 && segmentText != null) {
                                            arrayList2.add(new ToneSelectViewModel.CMTimeRange(segmentText.k().e(), ((Number) arrayList.get(i3)).longValue()));
                                            AddTextAudioParam addTextAudioParam = new AddTextAudioParam();
                                            ToneType toneType2 = toneType;
                                            Emotion emotion2 = emotion;
                                            Lazy<AudioToneSelectViewModel> lazy2 = lazy;
                                            AddAudioParam addAudioParamD = addTextAudioParam.d();
                                            addAudioParamD.m(str);
                                            addTextAudioParam.u(toneType2.getToneName());
                                            addTextAudioParam.q(toneType2.getPlatform());
                                            addTextAudioParam.p(toneType2.getToneName());
                                            addAudioParamD.v(toneType2.getResourceId());
                                            addTextAudioParam.o(toneType2.getEffectId());
                                            addTextAudioParam.m(toneType2.getCategoryID());
                                            addTextAudioParam.n(toneType2.getCategoryName());
                                            MaterialText materialTextU = segmentText.u();
                                            if (materialTextU == null || (strA0 = materialTextU.a0()) == null) {
                                                strA0 = "";
                                            }
                                            addAudioParamD.n(strA0);
                                            addAudioParamD.B(segmentText.k().e());
                                            addAudioParamD.A(0L);
                                            addAudioParamD.q(((Number) arrayList.get(i3)).longValue());
                                            AudioWaveUtils audioWaveUtils = AudioWaveUtils.f135449a;
                                            long jE = addAudioParamD.e();
                                            audioWaveUtils.getClass();
                                            int length = AudioWaveUtils.a(jE, str).length;
                                            for (int i5 = 0; i5 < length; i5++) {
                                                addAudioParamD.h().a(Double.valueOf(r15[i5]));
                                            }
                                            addAudioParamD.D(LVVEMetaType.MetaTypeTextToAudio);
                                            addTextAudioParam.k(lazy2.getValue().R);
                                            addAudioParamD.s(toneType2.isAICloneTone());
                                            addTextAudioParam.l(segment.b());
                                            addTextAudioParam.j(false);
                                            addTextAudioParam.u(toneType2.getToneName());
                                            addTextAudioParam.q(toneType2.getPlatform());
                                            addTextAudioParam.p(toneType2.getToneName());
                                            addTextAudioParam.i(toneType2.getResourceId());
                                            addTextAudioParam.o(toneType2.getEffectId());
                                            addTextAudioParam.m(toneType2.getCategoryID());
                                            addTextAudioParam.n(toneType2.getCategoryName());
                                            if (emotion2 != null) {
                                                EmotionUtilsKt.a(addTextAudioParam, emotion2);
                                            }
                                            MapOfStringString mapOfStringStringB = addTextAudioParam.b();
                                            Intrinsics.checkNotNullExpressionValue(mapOfStringStringB, "");
                                            mapOfStringStringB.put("TEXT_SEGMENT_ID", segment.b());
                                            arrayList3.add(addTextAudioParam);
                                        }
                                        i3 = i4;
                                    }
                                    int i6 = 0;
                                    Draft draftL = iSession.l();
                                    List listH = draftL != null ? AIScriptImpl.h(aIScriptImpl, draftL, arrayList2, SetsKt__SetsKt.emptySet()) : null;
                                    ISession iSession2 = iSession;
                                    Iterator it = arrayList3.iterator();
                                    while (it.hasNext()) {
                                        Object next = it.next();
                                        int i7 = i6 + 1;
                                        if (i6 < 0) {
                                            CollectionsKt__CollectionsKt.throwIndexOverflow();
                                        }
                                        AddTextAudioParam addTextAudioParam2 = (AddTextAudioParam) next;
                                        IQueryUtils iQueryUtilsM = iSession2.m();
                                        Integer numValueOf = iQueryUtilsM != null ? Integer.valueOf(IQueryUtilsExKt.b(iQueryUtilsM, CollectionsKt__CollectionsJVMKt.listOf(LVVETrackType.TrackTypeAudio), addTextAudioParam2.d().g(), addTextAudioParam2.d().e(), 0, null, 24)) : null;
                                        AddAudioParam addAudioParamD2 = addTextAudioParam2.d();
                                        if (listH != null && (insertInfo = (ToneSelectViewModel.InsertInfo) listH.get(i6)) != null) {
                                            numValueOf = Integer.valueOf(insertInfo.f74648a);
                                        }
                                        Intrinsics.checkNotNull(numValueOf);
                                        addAudioParamD2.C(numValueOf.intValue());
                                        AddTextAudioReqStruct addTextAudioReqStruct = new AddTextAudioReqStruct();
                                        addTextAudioReqStruct.setParams(addTextAudioParam2);
                                        arrayList4.add(addTextAudioReqStruct);
                                        i6 = i7;
                                    }
                                    z = true;
                                    if (!arrayList4.isEmpty()) {
                                        DraftComboParams draftComboParams = new DraftComboParams();
                                        draftComboParams.d("ADD_TEXT_AUDIO_ACTION");
                                        ISession iSession3 = iSession;
                                        if (iSession3 != null && (lyraSessionB = iSession3.b()) != null) {
                                            DraftClient.k(lyraSessionB, draftComboParams, arrayList4);
                                        }
                                    }
                                } else {
                                    z = false;
                                }
                                final LvProgressDialog lvProgressDialog2 = lvProgressDialog;
                                final Function1<Boolean, Unit> function12 = function1;
                                final Function0<Unit> function03 = function0;
                                final Function0<Unit> function04 = function02;
                                ThreadUtilKt.b(0L, new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl.genAiScriptTTSNotSpilt.1.1.1.6
                                    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        lvProgressDialog2.dismiss();
                                        Function1<Boolean, Unit> function13 = function12;
                                        if (function13 != null) {
                                            function13.invoke(Boolean.valueOf(z));
                                        }
                                        if (z) {
                                            Function0<Unit> function05 = function03;
                                            if (function05 != null) {
                                                function05.invoke();
                                            }
                                        } else {
                                            Function0<Unit> function06 = function04;
                                            if (function06 != null) {
                                                function06.invoke();
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                });
                                return Unit.INSTANCE;
                            }
                        }, null, this.s, mockToneInfo, null, false, null, Boolean.valueOf(zIsAICloneTone), zIsV3ModelTone, false, false, false, null, null, null, null, false, -409485407, 31));
                        return Unit.INSTANCE;
                    }
                }

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Function1<? super Boolean, ? extends Unit> function1) {
                    VectorNodes vectorNodesD;
                    Function1<? super Boolean, ? extends Unit> function12 = function1;
                    final ViewModelActivity viewModelActivity2 = viewModelActivity;
                    TextViewModel textViewModel = (TextViewModel) LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<TextViewModel>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$1$invoke$$inlined$factoryViewModel$1
                        public final /* synthetic */ Qualifier f = null;

                        /* renamed from: g, reason: collision with root package name */
                        public final /* synthetic */ Function0 f68135g = null;
                        public final /* synthetic */ Function0 h = null;

                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.viewmodel.TextViewModel] */
                        @Override // kotlin.jvm.functions.Function0
                        public final TextViewModel invoke() {
                            CreationExtras defaultViewModelCreationExtras;
                            ComponentActivity componentActivity = viewModelActivity2;
                            Qualifier qualifier = this.f;
                            Function0 function05 = this.f68135g;
                            Function0 function06 = this.h;
                            ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                            if (function05 == null || (defaultViewModelCreationExtras = (CreationExtras) function05.invoke()) == null) {
                                defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                                Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                            }
                            ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                            KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(TextViewModel.class);
                            Intrinsics.checkNotNull(viewModelStore);
                            return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function06);
                        }
                    }).getValue();
                    Boolean bool = Boolean.FALSE;
                    EditResult editResultH6 = TextViewModel.H6(textViewModel, bool, bool, str, null, scriptItem.getStartTime(), 0L, null, false, 465);
                    if (editResultH6 != null && (vectorNodesD = editResultH6.d()) != null) {
                        ISession iSession2 = iSession;
                        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(vectorNodesD, 10));
                        Iterator<ChangedNode> it = vectorNodesD.iterator();
                        while (it.hasNext()) {
                            String strA = it.next().a();
                            Intrinsics.checkNotNullExpressionValue(strA, "");
                            arrayList.add(iSession2.i(strA));
                        }
                        AdTextToAudioManager.f68333a.getClass();
                        AdTextToAudioManager.a();
                        LvProgressDialog lvProgressDialog = new LvProgressDialog(viewModelActivity, false, false, false, false, 30);
                        final AIScriptImpl aIScriptImpl = this;
                        final Function0<Unit> function05 = function02;
                        lvProgressDialog.setCanceledOnTouchOutside(false);
                        lvProgressDialog.v = true;
                        lvProgressDialog.s = new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$1$dialog$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function0
                            public final Unit invoke() {
                                aIScriptImpl.f68131a = true;
                                TextToSpeechTaskManager.f74281a.a("");
                                AdTextToAudioManager.f68333a.getClass();
                                Function0<Unit> function06 = function05;
                                if (function06 != null) {
                                    function06.invoke();
                                }
                                return Unit.INSTANCE;
                            }
                        };
                        lvProgressDialog.C(FunctionsKt.b(R.string._1l_res_0x7f120461));
                        if (!new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/LvProgressDialog", "show", lvProgressDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSVFMkQRuQacWJWbIRYuQffTFp3HVhjf2pvoVA")).isIntercept()) {
                            lvProgressDialog.show();
                        }
                        Emotion emotionT7 = lazy.getValue().t7(toneType.getVoiceType());
                        EmotionOption.h.getClass();
                        Job jobLaunch$default = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(str, toneType, EmotionOption.Companion.a(emotionT7), arrayList, iSession, this, emotionT7, lazy, lvProgressDialog, function12, function0, function03, null), 3, null);
                        final Function0<Unit> function06 = function04;
                        jobLaunch$default.invokeOnCompletion(new Function1<Throwable, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptTTSNotSpilt$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function1
                            public final Unit invoke(Throwable th) {
                                Function0<Unit> function07 = function06;
                                if (function07 != null) {
                                    function07.invoke();
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                    return Unit.INSTANCE;
                }
            });
            return;
        }
        if (function03 != null) {
            function03.invoke();
        }
        if (function04 != null) {
            function04.invoke();
        }
    }
}
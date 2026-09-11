package com.vega.adeditor.utils;

import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.lemon.lv.config.BaseClientSetting;
import com.lemon.lv.config.ClientSetting;
import com.lemon.lv.config.LanguageItem;
import com.lemon.lv.data.ScriptItem;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.lemon.lvoverseas.R;
import com.vega.adeditor.voiceover.model.AdTextToAudioManager;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.container.session.core.ISession;
import com.vega.core.context.SPIService;
import com.vega.edit.base.report.AIScriptInfo;
import com.vega.edit.base.report.AIScriptSegmentInfo;
import com.vega.edit.base.report.IAIScriptReporter;
import com.vega.edit.base.report.ScriptType;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.koin.ScopeProxy;
import com.vega.infrastructure.vm.ViewModelActivity;
import com.vega.libsticker.viewmodel.SubtitleViewModel;
import com.vega.log.BLog;
import com.vega.middlebridge.swig.ChangedNode;
import com.vega.middlebridge.swig.EditResult;
import com.vega.middlebridge.swig.LVVEMetaType;
import com.vega.middlebridge.swig.LVVESubtitleType;
import com.vega.middlebridge.swig.MaterialText;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentText;
import com.vega.middlebridge.swig.VectorNodes;
import com.vega.operation.bean.Sentence;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.ui.dialog.LvProgressDialog;
import com.vega.ui.util.ViewExKt;
import com.vega.util.ToastUtilKt;
import com.vega.ve.data.AudioMetaDataInfo;
import com.vega.ve.utils.MediaUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.CoroutineScope;
import org.koin.core.qualifier.Qualifier;

@DebugMetadata(c = "com.vega.adeditor.utils.AIScriptImpl$genAiScriptSubTitle$1", f = "AIScriptImpl.kt", i = {0}, l = {1076}, m = "invokeSuspend", n = {"reportInfo"}, s = {"L$0"})
/* loaded from: classes29.dex */
public final class AIScriptImpl$genAiScriptSubTitle$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    public final /* synthetic */ LvProgressDialog A;
    public TextToSpeechReportInfo q;
    public int r;
    public final /* synthetic */ String s;
    public final /* synthetic */ String t;
    public final /* synthetic */ AIScriptImpl u;
    public final /* synthetic */ ToneType v;
    public final /* synthetic */ ViewModelActivity w;
    public final /* synthetic */ ScriptItem x;
    public final /* synthetic */ Function0<Unit> y;
    public final /* synthetic */ Function0<Unit> z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AIScriptImpl$genAiScriptSubTitle$1(String str, String str2, AIScriptImpl aIScriptImpl, ToneType toneType, ViewModelActivity viewModelActivity, ScriptItem scriptItem, Function0<Unit> function0, Function0<Unit> function02, LvProgressDialog lvProgressDialog, Continuation<? super AIScriptImpl$genAiScriptSubTitle$1> continuation) {
        super(2, continuation);
        this.s = str;
        this.t = str2;
        this.u = aIScriptImpl;
        this.v = toneType;
        this.w = viewModelActivity;
        this.x = scriptItem;
        this.y = function0;
        this.z = function02;
        this.A = lvProgressDialog;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AIScriptImpl$genAiScriptSubTitle$1(this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, continuation);
    }

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        TextToSpeechReportInfo textToSpeechReportInfo;
        Object objI = obj;
        Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.r;
        if (i == 0) {
            ResultKt.throwOnFailure(objI);
            AdTextToAudioManager adTextToAudioManager = AdTextToAudioManager.f68333a;
            String str = this.s;
            adTextToAudioManager.getClass();
            Intrinsics.checkNotNullParameter(str, "");
            textToSpeechReportInfo = new TextToSpeechReportInfo(TextToSpeechReportScene.AI_SCRIPT, null, this.t.length(), false, false, 0L, null, null, null, null, 994, null);
            AIScriptImpl aIScriptImpl = this.u;
            String str2 = this.t;
            this.q = textToSpeechReportInfo;
            this.r = 1;
            aIScriptImpl.getClass();
            objI = AIScriptImpl.i(str2, this);
            if (objI == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            textToSpeechReportInfo = this.q;
            ResultKt.throwOnFailure(objI);
        }
        List list = (List) objI;
        TextInfo autoSegText = (list == null || list.isEmpty()) ? new TextInfo.AutoSegText(this.t) : new TextInfo.NoSegTextList(list);
        TextToSpeechTaskManager textToSpeechTaskManager = TextToSpeechTaskManager.f74281a;
        String voiceType = this.v.getVoiceType();
        String platform = this.v.getPlatform();
        TTSBusinessType tTSBusinessType = TTSBusinessType.f88857c;
        String json = textToSpeechReportInfo.toJson();
        boolean zIsAICloneTone = this.v.isAICloneTone();
        boolean zIsV3ModelTone = this.v.isV3ModelTone();
        final AIScriptImpl aIScriptImpl2 = this.u;
        final ViewModelActivity viewModelActivity = this.w;
        final ScriptItem scriptItem = this.x;
        final Function0<Unit> function0 = this.y;
        final Function0<Unit> function02 = this.z;
        final LvProgressDialog lvProgressDialog = this.A;
        textToSpeechTaskManager.c(new TextToSpeechIntent(null, autoSegText, voiceType, platform, "AIScriptImpl", null, tTSBusinessType, null, null, 0.0f, 0, null, null, false, json, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptSubTitle$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v10, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v11, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v2, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v3, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v4, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v5, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v6, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v7, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v8, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Multi-variable search result rejected for r26v9, resolved type: java.lang.Object[] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                String strA0;
                TtsResult ttsResult2 = ttsResult;
                TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                Intrinsics.checkNotNullParameter(ttsResult2, "");
                Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                List<String> list2 = textToAudioInfo2.b;
                List<String> list3 = textToAudioInfo2.f59067a;
                if (!aIScriptImpl2.f68131a) {
                    if (ttsResult2.f69163a == StatusResult.b && (!list2.isEmpty()) && list3.size() == list2.size()) {
                        final ViewModelActivity viewModelActivity2 = viewModelActivity;
                        Lazy lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<SubtitleViewModel>() { // from class: com.vega.adeditor.utils.AIScriptImpl$genAiScriptSubTitle$1$1$invoke$$inlined$factoryViewModel$1
                            public final /* synthetic */ Qualifier f = null;

                            /* renamed from: g, reason: collision with root package name */
                            public final /* synthetic */ Function0 f68132g = null;
                            public final /* synthetic */ Function0 h = null;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.viewmodel.SubtitleViewModel] */
                            @Override // kotlin.jvm.functions.Function0
                            public final SubtitleViewModel invoke() {
                                CreationExtras defaultViewModelCreationExtras;
                                ComponentActivity componentActivity = viewModelActivity2;
                                Qualifier qualifier = this.f;
                                Function0 function03 = this.f68132g;
                                Function0 function04 = this.h;
                                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                                if (function03 == null || (defaultViewModelCreationExtras = (CreationExtras) function03.invoke()) == null) {
                                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                                }
                                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SubtitleViewModel.class);
                                Intrinsics.checkNotNull(viewModelStore);
                                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function04);
                            }
                        });
                        AIScriptImpl aIScriptImpl3 = aIScriptImpl2;
                        SubtitleViewModel subtitleViewModel = (SubtitleViewModel) lazy.getValue();
                        LanguageItem languageItem = ((BaseClientSetting) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getAutoCaptionsConfig().a().get(0);
                        List listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                        LVVESubtitleType lVVESubtitleType = LVVESubtitleType.SubTitleAiScript;
                        Long startTime = scriptItem.getStartTime();
                        long jLongValue = startTime != null ? startTime.longValue() : 0L;
                        ScriptItem scriptItem2 = scriptItem;
                        final Function0<Unit> function03 = function0;
                        final Function0<Unit> function04 = function02;
                        final LvProgressDialog lvProgressDialog2 = lvProgressDialog;
                        Function1<Boolean, Unit> function1 = new Function1<Boolean, Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl.genAiScriptSubTitle.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function1
                            public final Unit invoke(Boolean bool) {
                                final boolean zBooleanValue = bool.booleanValue();
                                final Function0<Unit> function05 = function03;
                                final Function0<Unit> function06 = function04;
                                final LvProgressDialog lvProgressDialog3 = lvProgressDialog2;
                                ThreadUtilKt.b(0L, new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl.genAiScriptSubTitle.1.1.1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        if (zBooleanValue) {
                                            Function0<Unit> function07 = function05;
                                            if (function07 != null) {
                                                function07.invoke();
                                            }
                                        } else {
                                            Function0<Unit> function08 = function06;
                                            if (function08 != null) {
                                                function08.invoke();
                                            }
                                        }
                                        LvProgressDialog lvProgressDialog4 = lvProgressDialog3;
                                        if (lvProgressDialog4 != null) {
                                            lvProgressDialog4.dismiss();
                                        }
                                        return Unit.INSTANCE;
                                    }
                                });
                                return Unit.INSTANCE;
                            }
                        };
                        aIScriptImpl3.getClass();
                        if (subtitleViewModel.h.a0()) {
                            function1.invoke(Boolean.FALSE);
                        } else {
                            long j = jLongValue / 1000;
                            ArrayList arrayList = new ArrayList();
                            ArrayList arrayList2 = new ArrayList();
                            int i2 = 0;
                            for (Object obj2 : list3) {
                                int i3 = i2 + 1;
                                if (i2 < 0) {
                                    CollectionsKt__CollectionsKt.throwIndexOverflow();
                                }
                                MediaUtil.f135467a.getClass();
                                AudioMetaDataInfo audioMetaDataInfoC = MediaUtil.c((String) obj2);
                                arrayList.add(Long.valueOf(audioMetaDataInfoC.f135305a * 1000));
                                arrayList2.add(new Sentence(list2.get(i2), j, j + audioMetaDataInfoC.f135305a, null, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 8176, 0 == true ? 1 : 0));
                                j += audioMetaDataInfoC.f135305a;
                                i2 = i3;
                            }
                            ScriptType scriptType = null;
                            Object[] objArr = 0;
                            Object[] objArr2 = 0;
                            Object[] objArr3 = 0;
                            Object[] objArr4 = 0;
                            Object[] objArr5 = 0;
                            EditResult editResultN6 = SubtitleViewModel.n6(subtitleViewModel, arrayList2, lVVESubtitleType, LVVEMetaType.MetaTypeSubtitle, listEmptyList, languageItem != null ? languageItem.b() : null, null, true, false, null, null, 122880);
                            if (editResultN6 != null) {
                                subtitleViewModel.h.w0();
                                function1.invoke(Boolean.TRUE);
                                IAIScriptReporter iAIScriptReporter = (IAIScriptReporter) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAIScriptReporter.class), null);
                                AIScriptInfo aIScriptInfo = new AIScriptInfo(scriptItem2.getSmartAdId(), scriptItem2.getScriptRequestId(), String.valueOf(scriptItem2.getScriptId()), scriptItem2.getRoutineName(), scriptItem2.getModel(), scriptType, objArr5 == true ? 1 : 0, scriptItem2.getTheme(), scriptItem2.getProductDetails(), scriptItem2.getPromptTextDetail(), scriptItem2.getSellingPointsTextDetail(), objArr4 == true ? 1 : 0, scriptItem2.getPromptDetail(), scriptItem2.getLongerCnt(), scriptItem2.getShorterCnt(), scriptItem2.getPolishCnt(), scriptItem2.getTranslateCnt(), scriptItem2.getTranslateLang(), scriptItem2.getPolishType(), scriptItem2.getEnterFrom(), objArr3 == true ? 1 : 0, objArr2 == true ? 1 : 0, 3147872, objArr == true ? 1 : 0);
                                VectorNodes vectorNodesD = editResultN6.d();
                                Intrinsics.checkNotNullExpressionValue(vectorNodesD, "");
                                ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(vectorNodesD, 10));
                                Iterator<ChangedNode> it = vectorNodesD.iterator();
                                while (it.hasNext()) {
                                    ChangedNode next = it.next();
                                    ISession iSession = subtitleViewModel.h;
                                    String strA = next.a();
                                    Intrinsics.checkNotNullExpressionValue(strA, "");
                                    arrayList3.add(iSession.i(strA));
                                }
                                int size = list3.size();
                                Iterator it2 = arrayList3.iterator();
                                int i4 = 0;
                                while (it2.hasNext()) {
                                    int i5 = i4 + 1;
                                    Segment segment = (Segment) it2.next();
                                    String str3 = list3.get(i4);
                                    SegmentText segmentText = segment instanceof SegmentText ? (SegmentText) segment : null;
                                    if (str3.length() != 0 && i4 < size && segmentText != null) {
                                        String strB = segmentText.b();
                                        Intrinsics.checkNotNullExpressionValue(strB, "");
                                        MaterialText materialTextU = segmentText.u();
                                        if (materialTextU == null || (strA0 = materialTextU.a0()) == null) {
                                            strA0 = "";
                                        }
                                        aIScriptInfo.getSegments().add(new AIScriptSegmentInfo(strB, strA0));
                                    }
                                    i4 = i5;
                                }
                                iAIScriptReporter.c(subtitleViewModel.h, aIScriptInfo);
                                ToastUtilKt.e(ViewExKt.h(R.string.q4h), 0, 0, 0, 0, false, null, false, 510);
                                if (PerformanceManagerHelper.blogEnable) {
                                    BLog.i("AIScriptImpl", "split sentence complete");
                                }
                            }
                        }
                    } else {
                        final Function0<Unit> function05 = function02;
                        final LvProgressDialog lvProgressDialog3 = lvProgressDialog;
                        ThreadUtilKt.b(0L, new Function0<Unit>() { // from class: com.vega.adeditor.utils.AIScriptImpl.genAiScriptSubTitle.1.1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function0
                            public final Unit invoke() {
                                Function0<Unit> function06 = function05;
                                if (function06 != null) {
                                    function06.invoke();
                                }
                                LvProgressDialog lvProgressDialog4 = lvProgressDialog3;
                                if (lvProgressDialog4 != null) {
                                    lvProgressDialog4.dismiss();
                                }
                                return Unit.INSTANCE;
                            }
                        });
                    }
                }
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, Boolean.valueOf(zIsAICloneTone), zIsV3ModelTone, false, false, false, null, null, null, null, false, -403193951, 31));
        return Unit.INSTANCE;
    }
}
package com.vega.edit.aiclipper.panel.aiedit.audio;

import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.airbnb.lottie.LottieAnimationView;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lemon.lv.config.AiVoiceOverDynamicConfig;
import com.lemon.lv.config.AiVoiceOverDynamicConfigSettings;
import com.lemon.lv.config.ItemData;
import com.lemon.lv.config.SmartCutFrameworkSettings;
import com.lemon.lv.config.SmartCutFrameworkSettingsConfig;
import com.lemon.lv.config.StyleConfig;
import com.lemon.lv.config.TextCreateToneListDefaultTab;
import com.lemon.lv.config.TextCreateToneListDefaultTabSettings;
import com.lemon.lv.config.ToneEmotionConfig;
import com.lemon.lv.config.ToneEmotionConfigSetting;
import com.lemon.lv.data.Emotion;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.data.ToneType;
import com.lemon.lvoverseas.R;
import com.ss.android.ugc.effectmanager.effect.model.Effect;
import com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel;
import com.vega.aigcapi.materialgenerate.EventType;
import com.vega.aigcapi.materialgenerate.ReadingListener;
import com.vega.aigcapi.materialgenerate.TextToAudioType;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.audio.tone.view.EditToneSelectBottomPanelV2;
import com.vega.audio.tone.viewmodel.AudioToneSelectViewModel;
import com.vega.audio.tone.viewmodel.ToneSelectViewModel;
import com.vega.config.ConfigSettingsKt;
import com.vega.container.session.core.ISession;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.ext.LiveDataExtKt;
import com.vega.core.image.IImageLoader;
import com.vega.core.image.ImageLoaderKt;
import com.vega.core.tempvip.BenefitType;
import com.vega.core.utils.FunctionsKt;
import com.vega.core.utils.MultiListState;
import com.vega.edit.VideoActionDispatcher;
import com.vega.edit.aiclipper.panel.aiedit.AiEditContainer;
import com.vega.edit.aiclipper.panel.aiedit.AiEditType;
import com.vega.edit.aiclipper.panel.aiedit.audio.AIEditToneEmotionPanel;
import com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2;
import com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$addToneObserver$1$3$1$1;
import com.vega.edit.aiclipper.panel.aiedit.audio.TonePlayState;
import com.vega.edit.aiclipper.panel.aiedit.audio.adapter.SmartCutAudioStyle;
import com.vega.edit.aiclipper.panel.aiedit.audio.adapter.ThemeAdapter;
import com.vega.edit.aiclipper.panel.aiedit.audio.data.SmartAiVoiceConfig;
import com.vega.edit.aiclipper.panel.aiedit.audio.task.SmartCutAudioClipFlowTask;
import com.vega.edit.aiclipper.panel.aiedit.base.AiEditSettingsPage;
import com.vega.edit.aiclipper.panel.aiedit.service.SmartEditService;
import com.vega.edit.aiclipper.panel.aiedit.ui.SmartEditSelectItemDecoration;
import com.vega.edit.aiclipper.ui.AiEditTitleBar;
import com.vega.edit.aiclipper.utils.AIVoiceOverReporter;
import com.vega.edit.base.dock.IPanelProvider;
import com.vega.edit.base.dock.IPanelProviderKt;
import com.vega.edit.base.dock.PanelType;
import com.vega.edit.base.sticker.model.StickerCategoryItemKt;
import com.vega.edit.base.tone.EmotionOption;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.edit.base.utils.ToneUtil;
import com.vega.edit.base.view.BaseTabViewModel;
import com.vega.edit.base.vipmaterial.VipMaterialUtils;
import com.vega.edit.viewmodel.DraftChangeObserverViewModel;
import com.vega.effectplatform.loki.EffectPanel;
import com.vega.effectplatform.repository.EffectListState;
import com.vega.effectplatform.repository.RepoResult;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.infrastructure.extensions.ViewExtKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.util.KeyboardUtils;
import com.vega.infrastructure.vm.ViewModelActivity;
import com.vega.kv.KvStorage;
import com.vega.libeffect.repository.CategoryListState;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import com.vega.subscriptionapi.swidget.BusinessMarkView;
import com.vega.theme.VegaEditText;
import com.vega.theme.text.VegaTextView;
import com.vega.ui.AlphaButton;
import com.vega.ui.util.DisplayUtils;
import com.vega.ui.util.KeyboardStatusObserver;
import com.vega.util.ToastUtilKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes26.dex */
public final class AiEditAudioSettingsPageV2 extends AiEditSettingsPage {
    public static final /* synthetic */ int m0 = 0;
    public final AiEditContainer L;
    public FrameLayout M;
    public RecyclerView N;
    public final Lazy O;
    public final Lazy P;
    public CheckBox Q;
    public CheckBox R;
    public VegaEditText S;
    public SimpleDraweeView T;
    public BusinessMarkView U;
    public VegaTextView V;
    public VegaTextView W;

    /* renamed from: X, reason: collision with root package name */
    public VegaTextView f84108X;
    public ImageView Y;
    public String Z;
    public final int[] a0;
    public final String b0;
    public final Animation c0;
    public ConstraintLayout d0;
    public ConstraintLayout e0;
    public AlphaButton f0;
    public AlphaButton g0;
    public LottieAnimationView h0;
    public TonePlayState i0;
    public EffectCategoryModel j0;
    public final Lazy k0;
    public boolean l0;

    /* loaded from: classes34.dex */
    public /* synthetic */ class WhenMappings {
        static {
            TonePlayState.values();
        }
    }

    @DebugMetadata(c = "com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$onPause$1", f = "AiEditAudioSettingsPageV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$onPause$1, reason: invalid class name */
    /* loaded from: classes17.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return AiEditAudioSettingsPageV2.this.new AnonymousClass1(continuation);
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
            VegaEditText vegaEditText = AiEditAudioSettingsPageV2.this.S;
            VegaEditText vegaEditText2 = null;
            if (vegaEditText == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
                vegaEditText = null;
            }
            if (vegaEditText.getText().length() > 0) {
                SmartAiVoiceConfig smartAiVoiceConfig = SmartAiVoiceConfig.f84159a;
                VegaEditText vegaEditText3 = AiEditAudioSettingsPageV2.this.S;
                if (vegaEditText3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
                } else {
                    vegaEditText2 = vegaEditText3;
                }
                String string = vegaEditText2.getText().toString();
                smartAiVoiceConfig.getClass();
                Intrinsics.checkNotNullParameter(string, "");
                KvStorage kvStorage = SmartAiVoiceConfig.b;
                StringBuilder sb = new StringBuilder();
                DraftChangeObserverViewModel.i.getClass();
                sb.append(DraftChangeObserverViewModel.j);
                sb.append("_smart_ai_voice_prompt_cache");
                kvStorage.p(sb.toString(), string, false);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r9v2, resolved type: java.lang.Object[] */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$special$$inlined$activityFactoryViewModel$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$special$$inlined$activityFactoryViewModel$3] */
    public AiEditAudioSettingsPageV2(AiEditContainer aiEditContainer) {
        super(aiEditContainer, AiEditType.o, 0, 4, null);
        Intrinsics.checkNotNullParameter(aiEditContainer, "");
        this.L = aiEditContainer;
        final ?? r1 = new Function0<FragmentActivity>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$special$$inlined$activityFactoryViewModel$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentActivity invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return fragmentActivityRequireActivity;
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.O = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SmartCutAudioViewModel>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$special$$inlined$activityFactoryViewModel$2
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.aiclipper.panel.aiedit.audio.SmartCutAudioViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SmartCutAudioViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r1;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(SmartCutAudioViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r12 = new Function0<FragmentActivity>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$special$$inlined$activityFactoryViewModel$3
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentActivity invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return fragmentActivityRequireActivity;
            }
        };
        this.P = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<AudioToneSelectViewModel>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$special$$inlined$activityFactoryViewModel$4
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.audio.tone.viewmodel.AudioToneSelectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AudioToneSelectViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r12;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(AudioToneSelectViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        this.Z = "";
        this.a0 = new int[]{0, 0};
        this.b0 = "AiEditAudioSettingsPage";
        this.c0 = AnimationUtils.loadAnimation(ModuleCommon.INSTANCE.getApplication(), R.anim.f);
        this.i0 = TonePlayState.b;
        EffectCategoryModel effectCategoryModel = new EffectCategoryModel(0 == true ? 1 : 0, 1, 0 == true ? 1 : 0);
        effectCategoryModel.setId("0");
        effectCategoryModel.setKey("trending");
        effectCategoryModel.setName("long_text_edit");
        this.j0 = effectCategoryModel;
        this.k0 = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$defaultTabIndex$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(((TextCreateToneListDefaultTab) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TextCreateToneListDefaultTabSettings.class))).getIndex());
            }
        });
    }

    @Override // com.vega.edit.aiclipper.panel.aiedit.base.AiEditSettingsPage
    public final void H4(final ViewGroup viewGroup) {
        int i;
        Object next;
        Intrinsics.checkNotNullParameter(viewGroup, "");
        viewGroup.addView(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.uc, viewGroup, false));
        MutableLiveData<Boolean> mutableLiveData = U4().w;
        Boolean bool = Boolean.FALSE;
        mutableLiveData.setValue(bool);
        U4().t.setValue(bool);
        U4().u.setValue(Boolean.TRUE);
        View viewFindViewById = viewGroup.findViewById(R.id.smart_cut_rv_ai_voiceover_prompt_cl);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "");
        this.M = (FrameLayout) viewFindViewById;
        View viewFindViewById2 = viewGroup.findViewById(R.id.smart_cut_rv_ai_voiceover);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "");
        this.N = (RecyclerView) viewFindViewById2;
        Intrinsics.checkNotNullExpressionValue(viewGroup.findViewById(R.id.smart_cut_text_sync), "");
        View viewFindViewById3 = viewGroup.findViewById(R.id.smart_cut_radio_sync);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "");
        this.Q = (CheckBox) viewFindViewById3;
        View viewFindViewById4 = viewGroup.findViewById(R.id.smart_ai_voice_using_custom_tone);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "");
        this.R = (CheckBox) viewFindViewById4;
        View viewFindViewById5 = viewGroup.findViewById(R.id.fragment_ai_music_home_promote_input_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "");
        this.S = (VegaEditText) viewFindViewById5;
        Intrinsics.checkNotNullExpressionValue(viewGroup.findViewById(R.id.tong_info_container), "");
        View viewFindViewById6 = viewGroup.findViewById(R.id.tong_loading_cl);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "");
        this.d0 = (ConstraintLayout) viewFindViewById6;
        View viewFindViewById7 = viewGroup.findViewById(R.id.tone_detail_card);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "");
        this.e0 = (ConstraintLayout) viewFindViewById7;
        View viewFindViewById8 = viewGroup.findViewById(R.id.tong_loading_anim);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "");
        this.f0 = (AlphaButton) viewFindViewById8;
        View viewFindViewById9 = viewGroup.findViewById(R.id.tone_cover);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "");
        this.T = (SimpleDraweeView) viewFindViewById9;
        View viewFindViewById10 = viewGroup.findViewById(R.id.tone_title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "");
        this.V = (VegaTextView) viewFindViewById10;
        View viewFindViewById11 = viewGroup.findViewById(R.id.tone_detail);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "");
        this.f84108X = (VegaTextView) viewFindViewById11;
        View viewFindViewById12 = viewGroup.findViewById(R.id.tone_replace);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "");
        this.Y = (ImageView) viewFindViewById12;
        View viewFindViewById13 = viewGroup.findViewById(R.id.tong_last_used);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById13, "");
        this.W = (VegaTextView) viewFindViewById13;
        View viewFindViewById14 = viewGroup.findViewById(R.id.ai_voice_tone_state);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById14, "");
        this.g0 = (AlphaButton) viewFindViewById14;
        View viewFindViewById15 = viewGroup.findViewById(R.id.ai_music_home_sug_state_anim);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById15, "");
        this.h0 = (LottieAnimationView) viewFindViewById15;
        View viewFindViewById16 = viewGroup.findViewById(R.id.iv_limit_free);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById16, "");
        this.U = (BusinessMarkView) viewFindViewById16;
        LottieAnimationView lottieAnimationView = this.h0;
        VegaEditText vegaEditText = null;
        if (lottieAnimationView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
            lottieAnimationView = null;
        }
        lottieAnimationView.enableMergePathsForKitKatAndAbove(true);
        viewGroup.findViewById(R.id.smart_cut_audio_beat_match_click_area).setOnClickListener(new View.OnClickListener() { // from class: X.39n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14352a;
                CheckBox checkBox = aiEditAudioSettingsPageV2.Q;
                CheckBox checkBox2 = null;
                if (checkBox == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onlyMatchOption");
                    checkBox = null;
                }
                if (!checkBox.isChecked()) {
                    CheckBox checkBox3 = aiEditAudioSettingsPageV2.Q;
                    if (checkBox3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onlyMatchOption");
                    } else {
                        checkBox2 = checkBox3;
                    }
                    checkBox2.toggle();
                }
                AIVoiceOverReporter.f84628a.getClass();
                AIVoiceOverReporter.c("voice_automatch");
            }
        });
        viewGroup.findViewById(R.id.use_custom_match_click_area).setOnClickListener(new View.OnClickListener() { // from class: X.39l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14350a;
                AIVoiceOverReporter.f84628a.getClass();
                AIVoiceOverReporter.c("voice_customize");
                ConstraintLayout constraintLayout = aiEditAudioSettingsPageV2.d0;
                CheckBox checkBox = null;
                if (constraintLayout == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
                    constraintLayout = null;
                }
                if (constraintLayout.isShown()) {
                    ToastUtilKt.d(R.string.ocv, 0, 0, 0, 0, 254);
                    return;
                }
                CheckBox checkBox2 = aiEditAudioSettingsPageV2.R;
                if (checkBox2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
                    checkBox2 = null;
                }
                if (checkBox2.isChecked()) {
                    return;
                }
                CheckBox checkBox3 = aiEditAudioSettingsPageV2.R;
                if (checkBox3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
                    checkBox3 = null;
                }
                checkBox3.toggle();
                CheckBox checkBox4 = aiEditAudioSettingsPageV2.Q;
                if (checkBox4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onlyMatchOption");
                } else {
                    checkBox = checkBox4;
                }
                checkBox.setChecked(false);
            }
        });
        ConstraintLayout constraintLayout = this.e0;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tongDisplayCR");
            constraintLayout = null;
        }
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: X.39m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14351a;
                ConstraintLayout constraintLayout2 = aiEditAudioSettingsPageV2.d0;
                CheckBox checkBox = null;
                if (constraintLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
                    constraintLayout2 = null;
                }
                if (constraintLayout2.isShown()) {
                    ToastUtilKt.d(R.string.ocv, 0, 0, 0, 0, 254);
                    return;
                }
                CheckBox checkBox2 = aiEditAudioSettingsPageV2.R;
                if (checkBox2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
                    checkBox2 = null;
                }
                if (checkBox2.isChecked()) {
                    return;
                }
                CheckBox checkBox3 = aiEditAudioSettingsPageV2.R;
                if (checkBox3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
                    checkBox3 = null;
                }
                checkBox3.toggle();
                CheckBox checkBox4 = aiEditAudioSettingsPageV2.Q;
                if (checkBox4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("onlyMatchOption");
                } else {
                    checkBox = checkBox4;
                }
                checkBox.setChecked(false);
            }
        });
        G4().setBackType(1);
        X4(TonePlayState.b);
        List<ItemData> listA = ((SmartCutFrameworkSettingsConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(SmartCutFrameworkSettings.class))).a().a();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listA, 10));
        Iterator<T> it = listA.iterator();
        while (true) {
            i = 56;
            if (!it.hasNext()) {
                break;
            }
            ItemData itemData = (ItemData) it.next();
            arrayList.add(new SmartCutAudioStyle(itemData.a(), itemData.b(), i));
        }
        List<StyleConfig> topicStyle = ((AiVoiceOverDynamicConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AiVoiceOverDynamicConfigSettings.class))).getTopicStyle();
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(topicStyle, 10));
        for (StyleConfig styleConfig : topicStyle) {
            arrayList2.add(new SmartCutAudioStyle(styleConfig.a(), styleConfig.b(), i));
        }
        List mutableList = CollectionsKt___CollectionsKt.toMutableList((Collection) arrayList2);
        if (mutableList.isEmpty()) {
            mutableList.add(new SmartCutAudioStyle(FunctionsKt.b(R.string.pow), FunctionsKt.b(R.string.pow), i));
            mutableList.add(new SmartCutAudioStyle(FunctionsKt.b(R.string.poa), FunctionsKt.b(R.string.poa), i));
            mutableList.add(new SmartCutAudioStyle(FunctionsKt.b(R.string.poz), FunctionsKt.b(R.string.poz), i));
            mutableList.add(new SmartCutAudioStyle(FunctionsKt.b(R.string.pob), FunctionsKt.b(R.string.pob), i));
        }
        Iterator it2 = mutableList.iterator();
        while (true) {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
            SmartCutAudioStyle smartCutAudioStyle = (SmartCutAudioStyle) next;
            SmartAiVoiceConfig.f84159a.getClass();
            if (SmartAiVoiceConfig.b().length() > 0 && Intrinsics.areEqual(SmartAiVoiceConfig.b(), smartCutAudioStyle.b)) {
                break;
            }
        }
        SmartCutAudioStyle smartCutAudioStyle2 = (SmartCutAudioStyle) next;
        if (smartCutAudioStyle2 != null || (smartCutAudioStyle2 = (SmartCutAudioStyle) CollectionsKt___CollectionsKt.firstOrNull(mutableList)) != null) {
            smartCutAudioStyle2.f84153c = true;
            U4().x.setValue(smartCutAudioStyle2);
            AIVoiceOverReporter aIVoiceOverReporter = AIVoiceOverReporter.f84628a;
            String str = smartCutAudioStyle2.f84152a;
            aIVoiceOverReporter.getClass();
            Intrinsics.checkNotNullParameter(str, "");
            AIVoiceOverReporter.b = str;
        }
        ThemeAdapter themeAdapter = new ThemeAdapter(mutableList, new Function1<SmartCutAudioStyle, Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$onContentCreated$adapter$1

            @DebugMetadata(c = "com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$onContentCreated$adapter$1$1", f = "AiEditAudioSettingsPageV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$onContentCreated$adapter$1$1, reason: invalid class name */
            /* loaded from: classes15.dex */
            public final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                public final /* synthetic */ SmartCutAudioStyle q;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(SmartCutAudioStyle smartCutAudioStyle, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.q = smartCutAudioStyle;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.q, continuation);
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
                    SmartAiVoiceConfig smartAiVoiceConfig = SmartAiVoiceConfig.f84159a;
                    String str = this.q.b;
                    smartAiVoiceConfig.getClass();
                    Intrinsics.checkNotNullParameter(str, "");
                    KvStorage kvStorage = SmartAiVoiceConfig.b;
                    StringBuilder sb = new StringBuilder();
                    DraftChangeObserverViewModel.i.getClass();
                    sb.append(DraftChangeObserverViewModel.j);
                    sb.append("_smart_ai_voice_style_cache");
                    kvStorage.p(sb.toString(), str, false);
                    return Unit.INSTANCE;
                }
            }

            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(SmartCutAudioStyle smartCutAudioStyle3) {
                SmartCutAudioStyle smartCutAudioStyle4 = smartCutAudioStyle3;
                Intrinsics.checkNotNullParameter(smartCutAudioStyle4, "");
                this.e.U4().x.setValue(smartCutAudioStyle4);
                BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(smartCutAudioStyle4, null), 3, null);
                AIVoiceOverReporter aIVoiceOverReporter2 = AIVoiceOverReporter.f84628a;
                String str2 = smartCutAudioStyle4.f84152a;
                aIVoiceOverReporter2.getClass();
                Intrinsics.checkNotNullParameter(str2, "");
                AIVoiceOverReporter.b = str2;
                AIVoiceOverReporter.c("topic_option");
                return Unit.INSTANCE;
            }
        });
        RecyclerView recyclerView = this.N;
        if (recyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceStyleRV");
            recyclerView = null;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), 0, false));
        RecyclerView recyclerView2 = this.N;
        if (recyclerView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceStyleRV");
            recyclerView2 = null;
        }
        recyclerView2.setAdapter(themeAdapter);
        RecyclerView recyclerView3 = this.N;
        if (recyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("aiVoiceStyleRV");
            recyclerView3 = null;
        }
        recyclerView3.addItemDecoration(new SmartEditSelectItemDecoration());
        U4().L6();
        AiEditTitleBar aiEditTitleBarG4 = G4();
        aiEditTitleBarG4.p = true;
        ImageView imageView = aiEditTitleBarG4.f;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivHelp");
            imageView = null;
        }
        imageView.setVisibility(0);
        KeyboardStatusObserver.d(KeyboardStatusObserver.f134350a, viewGroup, new KeyboardStatusObserver.KeyboardListener() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$addKeyboardObserve$1
            @Override // com.vega.ui.util.KeyboardStatusObserver.KeyboardListener
            public final void a(boolean z) {
                this.f84111a.l0 = z;
                if (!z) {
                    viewGroup.setTranslationY(0.0f);
                    return;
                }
                View view = viewGroup;
                DisplayUtils.f134332a.getClass();
                view.setTranslationY(-DisplayUtils.b(30));
            }

            @Override // com.vega.ui.util.KeyboardStatusObserver.KeyboardListener
            public final void b(boolean z) {
            }

            @Override // com.vega.ui.util.KeyboardStatusObserver.KeyboardListener
            public final void c(int i2) {
            }

            @Override // com.vega.ui.util.KeyboardStatusObserver.KeyboardListener
            public final void d() {
            }

            @Override // com.vega.ui.util.KeyboardStatusObserver.KeyboardListener
            public final void e(int i2, boolean z) {
            }

            @Override // com.vega.ui.util.KeyboardStatusObserver.KeyboardListener
            public final void f() {
            }
        });
        BaseTabViewModel.E6(T4(), EffectPanel.B, false, false, 6);
        AlphaButton alphaButton = this.g0;
        if (alphaButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playState");
            alphaButton = null;
        }
        alphaButton.setOnClickListener(new View.OnClickListener() { // from class: X.3Ba
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14463a;
                AIVoiceOverReporter.f84628a.getClass();
                AIVoiceOverReporter.c("voice_try");
                ToneType value = aiEditAudioSettingsPageV2.U4().B.getValue();
                if (value != null) {
                    Objects.toString(aiEditAudioSettingsPageV2.i0);
                    TonePlayState tonePlayState = aiEditAudioSettingsPageV2.i0;
                    TonePlayState tonePlayState2 = TonePlayState.f84149a;
                    if (tonePlayState == tonePlayState2) {
                        TextToSpeechTaskManager.f74281a.b(false);
                        aiEditAudioSettingsPageV2.X4(TonePlayState.b);
                        return;
                    }
                    if (tonePlayState != TonePlayState.f84150c) {
                        aiEditAudioSettingsPageV2.X4(tonePlayState2);
                        TextToSpeechReportScene textToSpeechReportScene = TextToSpeechReportScene.SMART_EDIT_AI_VOICEOVER;
                        TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(textToSpeechReportScene, null, 0, false, false, 0L, null, null, null, null, 1022, null);
                        Emotion emotionT7 = aiEditAudioSettingsPageV2.T4().t7(value.getVoiceType());
                        TextInfo.NoSegTextList noSegTextList = new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf("hello world "));
                        String voiceType = value.getVoiceType();
                        String platform = value.getPlatform();
                        String info = textToSpeechReportScene.getInfo();
                        TTSBusinessType tTSBusinessType = TTSBusinessType.f88857c;
                        String auditionText = value.getAuditionText();
                        boolean zIsAICloneTone = value.isAICloneTone();
                        boolean zIsV3ModelTone = value.isV3ModelTone();
                        String toneModelType = value.getToneModelType();
                        String resourceId = value.getResourceId();
                        String json = textToSpeechReportInfo.toJson();
                        EmotionOption.h.getClass();
                        TextToSpeechTaskManager.f74281a.f(new TextToSpeechIntent(null, noSegTextList, voiceType, platform, info, null, tTSBusinessType, null, null, 1.0f, 0, new ReadingListener() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$startReadingTone$intent$1
                            @Override // com.vega.aigcapi.materialgenerate.ReadingListener
                            public final void a() {
                                final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV22 = aiEditAudioSettingsPageV2;
                                ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$startReadingTone$intent$1$onStartReading$1
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        aiEditAudioSettingsPageV22.X4(TonePlayState.f84149a);
                                        return Unit.INSTANCE;
                                    }
                                });
                            }

                            @Override // com.vega.aigcapi.materialgenerate.ReadingListener
                            public final void b() {
                                final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV22 = aiEditAudioSettingsPageV2;
                                ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$startReadingTone$intent$1$onStopReading$1
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        aiEditAudioSettingsPageV22.X4(TonePlayState.b);
                                        return Unit.INSTANCE;
                                    }
                                });
                            }

                            @Override // com.vega.aigcapi.materialgenerate.ReadingListener
                            public final void c(TextToAudioType textToAudioType, EventType eventType, TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                                Intrinsics.checkNotNullParameter(textToAudioType, "");
                                Intrinsics.checkNotNullParameter(eventType, "");
                                if (eventType == EventType.e) {
                                    final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV22 = aiEditAudioSettingsPageV2;
                                    ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$startReadingTone$intent$1$onMessageReceived$1
                                        {
                                            super(0);
                                        }

                                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                        @Override // kotlin.jvm.functions.Function0
                                        public final Unit invoke() {
                                            aiEditAudioSettingsPageV22.X4(TonePlayState.b);
                                            return Unit.INSTANCE;
                                        }
                                    });
                                }
                            }
                        }, null, false, json, auditionText, false, toneModelType, resourceId, null, null, EmotionOption.Companion.a(emotionT7), null, null, false, null, Boolean.valueOf(zIsAICloneTone), zIsV3ModelTone, false, false, true, null, null, null, null, false, 1742288289, 31));
                    }
                }
            }
        });
        VegaTextView vegaTextView = this.f84108X;
        if (vegaTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
            vegaTextView = null;
        }
        vegaTextView.setOnClickListener(new View.OnClickListener() { // from class: X.39M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                List<Emotion> listEmptyList;
                final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14319a;
                AIVoiceOverReporter.f84628a.getClass();
                AIVoiceOverReporter.c("voice_emotion");
                ViewModelActivity activity = aiEditAudioSettingsPageV2.L.getActivity();
                ToneType value = aiEditAudioSettingsPageV2.U4().B.getValue();
                if (value == null || (listEmptyList = value.getEmotionList()) == null) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                String value2 = aiEditAudioSettingsPageV2.U4().C.getValue();
                if (value2 == null) {
                    value2 = "";
                }
                AIEditToneEmotionPanel aIEditToneEmotionPanel = new AIEditToneEmotionPanel(activity, value2, aiEditAudioSettingsPageV2.U4().B.getValue(), aiEditAudioSettingsPageV2.T4(), listEmptyList, new Function1<Emotion, Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$showEmotionSelectPanel$1
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Emotion emotion) {
                        Emotion emotion2 = emotion;
                        BLog.i("AiEditAudioResultPage", "onEmotionSelected: " + emotion2);
                        if (emotion2 != null) {
                            AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV22 = aiEditAudioSettingsPageV2;
                            aiEditAudioSettingsPageV22.U4().C.postValue(emotion2.getNameKey());
                            VegaTextView vegaTextView2 = aiEditAudioSettingsPageV22.f84108X;
                            if (vegaTextView2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
                                vegaTextView2 = null;
                            }
                            String strB = ((ToneEmotionConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneEmotionConfigSetting.class))).b(emotion2.getNameKey());
                            if (strB == null) {
                                strB = "";
                            }
                            vegaTextView2.setText(strB);
                        }
                        aiEditAudioSettingsPageV2.S4(false);
                        return Unit.INSTANCE;
                    }
                });
                aIEditToneEmotionPanel.setCanceledOnTouchOutside(true);
                if (!new HeliosApiHook().preInvoke(300000, "com/vega/edit/aiclipper/panel/aiedit/audio/AIEditToneEmotionPanel", "show", aIEditToneEmotionPanel, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSUFMoUVyFb8dajwiJSMFuNh/nirR+gQHgeVquGpLFw8fauOd5PnI71mLUQwOefojIDoYHWHQmoL+msEw=")).isIntercept()) {
                    aIEditToneEmotionPanel.show();
                }
                ThreadUtilKt.e(50L, new Function0<Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$showEmotionSelectPanel$3
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        aiEditAudioSettingsPageV2.S4(true);
                        return Unit.INSTANCE;
                    }
                });
            }
        });
        ImageView imageView2 = this.Y;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneReplace");
            imageView2 = null;
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: X.39L
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14318a;
                AIVoiceOverReporter.f84628a.getClass();
                AIVoiceOverReporter.c("voice_change");
                ToneType value = aiEditAudioSettingsPageV2.U4().B.getValue();
                if (value != null) {
                    aiEditAudioSettingsPageV2.T4().G = value;
                    aiEditAudioSettingsPageV2.T4().s0 = value.isAICloneTone();
                    aiEditAudioSettingsPageV2.T4().H = value.getToneName();
                    LiveDataExtKt.o(aiEditAudioSettingsPageV2.T4().F, value);
                    LiveDataExtKt.o(aiEditAudioSettingsPageV2.T4().z, value.getVoiceType());
                    AudioToneSelectViewModel audioToneSelectViewModelT4 = aiEditAudioSettingsPageV2.T4();
                    String toneName = value.getToneName();
                    audioToneSelectViewModelT4.getClass();
                    Intrinsics.checkNotNullParameter(toneName, "");
                    audioToneSelectViewModelT4.A0 = toneName;
                    AudioToneSelectViewModel audioToneSelectViewModelT42 = aiEditAudioSettingsPageV2.T4();
                    String categoryID = value.getCategoryID();
                    audioToneSelectViewModelT42.getClass();
                    Intrinsics.checkNotNullParameter(categoryID, "");
                    audioToneSelectViewModelT42.getClass();
                }
                IPanelProvider iPanelProviderA = IPanelProviderKt.a();
                ViewModelActivity activity = aiEditAudioSettingsPageV2.L.getActivity();
                ViewModelActivity activity2 = aiEditAudioSettingsPageV2.L.getActivity();
                String strA = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).A();
                if (strA.length() == 0) {
                    strA = "You’re using the text to speech feature. Choose a voice you like.";
                }
                EditToneSelectBottomPanelV2 editToneSelectBottomPanelV2A = iPanelProviderA.A(activity, activity2, strA, MapsKt__MapsKt.mutableMapOf(TuplesKt.to("scene", 18)), new Function0<Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$setupListeners$3$3
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        aiEditAudioSettingsPageV2.S4(false);
                        return Unit.INSTANCE;
                    }
                }, new Function1<ToneType, Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$setupListeners$3$2
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    /* JADX WARN: Removed duplicated region for block: B:12:0x0061  */
                    @Override // kotlin.jvm.functions.Function1
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final kotlin.Unit invoke(com.lemon.lv.data.ToneType r5) {
                        /*
                            r4 = this;
                            com.lemon.lv.data.ToneType r5 = (com.lemon.lv.data.ToneType) r5
                            java.lang.String r0 = ""
                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            java.lang.String r2 = r0.b0
                            java.lang.StringBuilder r1 = new java.lang.StringBuilder
                            java.lang.String r0 = "onToneSelect: "
                            r1.<init>(r0)
                            r1.append(r5)
                            java.lang.String r0 = r1.toString()
                            com.vega.log.BLog.i(r2, r0)
                            java.lang.String r1 = r5.getResourceId()
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            com.vega.edit.aiclipper.panel.aiedit.audio.SmartCutAudioViewModel r0 = r0.U4()
                            androidx.lifecycle.MutableLiveData<com.lemon.lv.data.ToneType> r0 = r0.B
                            java.lang.Object r0 = r0.getValue()
                            com.lemon.lv.data.ToneType r0 = (com.lemon.lv.data.ToneType) r0
                            r2 = 0
                            if (r0 == 0) goto Lc1
                            java.lang.String r0 = r0.getResourceId()
                        L35:
                            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
                            if (r0 == 0) goto L61
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            com.vega.edit.aiclipper.panel.aiedit.audio.SmartCutAudioViewModel r0 = r0.U4()
                            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.C
                            java.lang.Object r3 = r0.getValue()
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r1 = r0.T4()
                            java.lang.String r0 = r5.getVoiceType()
                            com.lemon.lv.data.Emotion r0 = r1.t7(r0)
                            if (r0 == 0) goto Lbf
                            java.lang.String r0 = r0.getNameKey()
                        L5b:
                            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
                            if (r0 != 0) goto Laa
                        L61:
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            com.vega.audio.tone.viewmodel.AudioToneSelectViewModel r1 = r0.T4()
                            java.lang.String r0 = r5.getVoiceType()
                            com.lemon.lv.data.Emotion r1 = r1.t7(r0)
                            if (r1 != 0) goto L7d
                            java.util.List r0 = r5.getEmotionList()
                            if (r0 == 0) goto Lbd
                            java.lang.Object r1 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r0)
                            com.lemon.lv.data.Emotion r1 = (com.lemon.lv.data.Emotion) r1
                        L7d:
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            com.vega.edit.aiclipper.panel.aiedit.audio.SmartCutAudioViewModel r0 = r0.U4()
                            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r0.C
                            if (r1 == 0) goto L8b
                            java.lang.String r2 = r1.getNameKey()
                        L8b:
                            r0.setValue(r2)
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            com.vega.edit.aiclipper.panel.aiedit.audio.SmartCutAudioViewModel r0 = r0.U4()
                            androidx.lifecycle.MutableLiveData<com.lemon.lv.data.ToneType> r0 = r0.B
                            r0.setValue(r5)
                            java.lang.String r1 = r5.getVoiceType()
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            java.lang.String r0 = r0.Z
                            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r0 = r2
                            r0.Z4(r5, r1)
                        Laa:
                            com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2 r1 = r2
                            r0 = 0
                            r1.S4(r0)
                            com.vega.edit.aiclipper.utils.AIVoiceOverReporter r0 = com.vega.edit.aiclipper.utils.AIVoiceOverReporter.f84628a
                            r0.getClass()
                            java.lang.String r0 = "voice_change_confirm"
                            com.vega.edit.aiclipper.utils.AIVoiceOverReporter.c(r0)
                            kotlin.Unit r0 = kotlin.Unit.INSTANCE
                            return r0
                        Lbd:
                            r1 = r2
                            goto L7d
                        Lbf:
                            r0 = r2
                            goto L5b
                        Lc1:
                            r0 = r2
                            goto L35
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$setupListeners$3$2.invoke(java.lang.Object):java.lang.Object");
                    }
                }, false, false, true, false, true, true);
                editToneSelectBottomPanelV2A.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: X.39N
                    @Override // android.content.DialogInterface.OnDismissListener
                    public final void onDismiss(DialogInterface dialogInterface) {
                        BLog.i(aiEditAudioSettingsPageV2.b0, "onDismiss");
                    }
                });
                aiEditAudioSettingsPageV2.S4(true);
                if (new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/BaseDialog", "show", editToneSelectBottomPanelV2A, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSUFMoUVyFb8dajwiJSMFuNh/nirR+gQHgeVquGpLFw8fauOd5PnI71mLUQwOefojIDoYHWHQmoL+msEw=")).isIntercept()) {
                    return;
                }
                editToneSelectBottomPanelV2A.show();
            }
        });
        U4().I.setValue(Boolean.TRUE);
        Y4(false);
        CheckBox checkBox = this.Q;
        if (checkBox == null) {
            Intrinsics.throwUninitializedPropertyAccessException("onlyMatchOption");
            checkBox = null;
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: X.39k
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14349a;
                Intrinsics.checkNotNullParameter(compoundButton, "");
                CheckBox checkBox2 = null;
                if (z) {
                    ConstraintLayout constraintLayout2 = aiEditAudioSettingsPageV2.d0;
                    if (constraintLayout2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
                        constraintLayout2 = null;
                    }
                    if (constraintLayout2.isShown()) {
                        ToastUtilKt.d(R.string.ocv, 0, 0, 0, 0, 254);
                        return;
                    }
                    CheckBox checkBox3 = aiEditAudioSettingsPageV2.R;
                    if (checkBox3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
                    } else {
                        checkBox2 = checkBox3;
                    }
                    checkBox2.setChecked(false);
                    aiEditAudioSettingsPageV2.Y4(false);
                }
                aiEditAudioSettingsPageV2.U4().I.setValue(Boolean.valueOf(z));
            }
        });
        CheckBox checkBox2 = this.R;
        if (checkBox2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
            checkBox2 = null;
        }
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: X.39j
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.f14348a;
                Intrinsics.checkNotNullParameter(compoundButton, "");
                ConstraintLayout constraintLayout2 = aiEditAudioSettingsPageV2.d0;
                CheckBox checkBox3 = null;
                if (constraintLayout2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
                    constraintLayout2 = null;
                }
                if (constraintLayout2.isShown()) {
                    ToastUtilKt.d(R.string.ocv, 0, 0, 0, 0, 254);
                    return;
                }
                if (z) {
                    CheckBox checkBox4 = aiEditAudioSettingsPageV2.Q;
                    if (checkBox4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("onlyMatchOption");
                    } else {
                        checkBox3 = checkBox4;
                    }
                    checkBox3.setChecked(false);
                    aiEditAudioSettingsPageV2.Y4(true);
                }
                SmartAiVoiceConfig.f84159a.getClass();
                KvStorage kvStorage = SmartAiVoiceConfig.b;
                StringBuilder sb = new StringBuilder();
                DraftChangeObserverViewModel.i.getClass();
                sb.append(DraftChangeObserverViewModel.j);
                sb.append("_smart_ai_voice_tone_recommend_config");
                kvStorage.l(sb.toString(), z, false);
                aiEditAudioSettingsPageV2.U4().G.setValue(Boolean.valueOf(z));
            }
        });
        VegaEditText vegaEditText2 = this.S;
        if (vegaEditText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
            vegaEditText2 = null;
        }
        vegaEditText2.setOnTouchListener(new View.OnTouchListener() { // from class: X.39o
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() != 0) {
                    return false;
                }
                AIVoiceOverReporter.f84628a.getClass();
                AIVoiceOverReporter.c("topic_input");
                return false;
            }
        });
        T4().c7().observe(getViewLifecycleOwner(), new AiEditAudioSettingsPageV2$sam$androidx_lifecycle_Observer$0(new Function1<CategoryListState, Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2$addToneObserver$1

            /* loaded from: classes40.dex */
            public /* synthetic */ class WhenMappings {
                static {
                    RepoResult.values();
                }
            }

            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(CategoryListState categoryListState) {
                Object next2;
                Object next3;
                CategoryListState categoryListState2 = categoryListState;
                int iOrdinal = categoryListState2.f109539a.ordinal();
                AlphaButton alphaButton2 = null;
                if (iOrdinal == 0) {
                    Objects.toString(categoryListState2.b);
                    List<EffectCategoryModel> list = categoryListState2.b;
                    AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV2 = this.e;
                    Iterator<T> it3 = list.iterator();
                    while (true) {
                        if (!it3.hasNext()) {
                            next2 = null;
                            break;
                        }
                        next2 = it3.next();
                        if (Intrinsics.areEqual(((com.ss.ugc.effectplatform.model.EffectCategoryModel) next2).getKey(), aiEditAudioSettingsPageV2.j0.getKey())) {
                            break;
                        }
                    }
                    if (next2 == null) {
                        AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV22 = this.e;
                        Iterator<T> it4 = categoryListState2.b.iterator();
                        while (true) {
                            if (!it4.hasNext()) {
                                next3 = null;
                                break;
                            }
                            next3 = it4.next();
                            EffectCategoryModel effectCategoryModel = (EffectCategoryModel) next3;
                            if (!StickerCategoryItemKt.l(effectCategoryModel) && !StickerCategoryItemKt.r(effectCategoryModel)) {
                                break;
                            }
                        }
                        EffectCategoryModel effectCategoryModel2 = (EffectCategoryModel) next3;
                        if (effectCategoryModel2 == null) {
                            effectCategoryModel2 = this.e.j0;
                        }
                        aiEditAudioSettingsPageV22.getClass();
                        Intrinsics.checkNotNullParameter(effectCategoryModel2, "");
                        aiEditAudioSettingsPageV22.j0 = effectCategoryModel2;
                        int size = categoryListState2.b.size();
                        int iIntValue = ((Number) this.e.k0.getValue()).intValue();
                        if (iIntValue >= 0 && iIntValue < size) {
                            AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV23 = this.e;
                            EffectCategoryModel effectCategoryModel3 = categoryListState2.b.get(((Number) aiEditAudioSettingsPageV23.k0.getValue()).intValue());
                            Intrinsics.checkNotNullParameter(effectCategoryModel3, "");
                            aiEditAudioSettingsPageV23.j0 = effectCategoryModel3;
                        }
                    }
                    MultiListState<String, EffectListState> multiListState = this.e.T4().r;
                    LifecycleOwner viewLifecycleOwner = this.e.getViewLifecycleOwner();
                    Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "");
                    String key = this.e.j0.getKey();
                    final AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV24 = this.e;
                    multiListState.g(viewLifecycleOwner, key, new Observer() { // from class: X.39p
                        @Override // androidx.lifecycle.Observer
                        public final void onChanged(Object obj) {
                            AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV25 = aiEditAudioSettingsPageV24;
                            EffectListState effectListState = (EffectListState) obj;
                            Intrinsics.checkNotNullParameter(effectListState, "");
                            if (effectListState.f98525g == RepoResult.f98531a) {
                                System.currentTimeMillis();
                                Effect effect = (Effect) CollectionsKt___CollectionsKt.firstOrNull((List) effectListState.h);
                                if (effect != null) {
                                    BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AiEditAudioSettingsPageV2$addToneObserver$1$3$1$1(ToneUtil.c(ToneUtil.f89173a, effect, null, 14), aiEditAudioSettingsPageV25, null), 3, null);
                                }
                                aiEditAudioSettingsPageV25.W4();
                            }
                        }
                    });
                    AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV25 = this.e;
                    aiEditAudioSettingsPageV25.getClass();
                    BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AiEditAudioSettingsPageV2$loadRecommendTone$1(aiEditAudioSettingsPageV25, null), 3, null);
                    AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV26 = this.e;
                    System.currentTimeMillis();
                    aiEditAudioSettingsPageV26.getClass();
                } else if (iOrdinal == 2) {
                    AiEditAudioSettingsPageV2 aiEditAudioSettingsPageV27 = this.e;
                    System.currentTimeMillis();
                    aiEditAudioSettingsPageV27.getClass();
                    ConstraintLayout constraintLayout2 = this.e.d0;
                    if (constraintLayout2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
                        constraintLayout2 = null;
                    }
                    ViewExtKt.e(constraintLayout2);
                    ConstraintLayout constraintLayout3 = this.e.e0;
                    if (constraintLayout3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tongDisplayCR");
                        constraintLayout3 = null;
                    }
                    ViewExtKt.c(constraintLayout3);
                    AlphaButton alphaButton3 = this.e.f0;
                    if (alphaButton3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tongLoadingAnim");
                        alphaButton3 = null;
                    }
                    alphaButton3.setImageResource(R.drawable._0u_res_0x7f080442);
                    AlphaButton alphaButton4 = this.e.f0;
                    if (alphaButton4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("tongLoadingAnim");
                    } else {
                        alphaButton2 = alphaButton4;
                    }
                    alphaButton2.startAnimation(this.e.c0);
                }
                return Unit.INSTANCE;
            }
        }));
        SmartAiVoiceConfig.f84159a.getClass();
        if (ExtentionKt.isNotNullOrEmpty(SmartAiVoiceConfig.a())) {
            SmartAiVoiceConfig.a();
            VegaEditText vegaEditText3 = this.S;
            if (vegaEditText3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
            } else {
                vegaEditText = vegaEditText3;
            }
            vegaEditText.setText(SmartAiVoiceConfig.a());
            U4().y.postValue(SmartAiVoiceConfig.a());
        }
        SmartEditService smartEditService = SmartEditService.f84458a;
        ISession iSession = U4().f84129c;
        smartEditService.getClass();
        SmartEditService.c(iSession, false, true);
        VideoActionDispatcher videoActionDispatcher = VideoActionDispatcher.f83874a;
        ISession iSession2 = U4().f84129c;
        videoActionDispatcher.getClass();
        VideoActionDispatcher.z(iSession2, true, false);
        AIVoiceOverReporter.f84628a.getClass();
        AIVoiceOverReporter.c("show");
        AIVoiceOverReporter.d("show");
    }

    @Override // com.vega.edit.aiclipper.panel.aiedit.base.AiEditSettingsPage
    public final HashMap<String, Object> M4() {
        Pair[] pairArr = new Pair[4];
        AIVoiceOverReporter aIVoiceOverReporter = AIVoiceOverReporter.f84628a;
        aIVoiceOverReporter.getClass();
        pairArr[0] = TuplesKt.to("topic", AIVoiceOverReporter.b);
        aIVoiceOverReporter.getClass();
        pairArr[1] = TuplesKt.to("voice_source", AIVoiceOverReporter.e);
        aIVoiceOverReporter.getClass();
        pairArr[2] = TuplesKt.to("tone_id", AIVoiceOverReporter.f84629c);
        aIVoiceOverReporter.getClass();
        pairArr[3] = TuplesKt.to("is_cloned", AIVoiceOverReporter.f84630d ? ProfileManager.VERSION : "0");
        Map mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(pairArr);
        Intrinsics.checkNotNull(mapMutableMapOf, "");
        return (HashMap) mapMutableMapOf;
    }

    @Override // com.vega.edit.aiclipper.panel.aiedit.base.AiEditSettingsPage
    public final void Q4() {
        Emotion emotion;
        String emotion2;
        String role;
        Object next;
        U4().l = 0.0f;
        MutableLiveData<String> mutableLiveData = U4().y;
        VegaEditText vegaEditText = this.S;
        SmartCutAudioClipFlowTask.SpeakerInfo speakerInfo = null;
        Double emotionScale = null;
        speakerInfo = null;
        if (vegaEditText == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
            vegaEditText = null;
        }
        mutableLiveData.setValue(vegaEditText.getText().toString());
        if (Intrinsics.areEqual(U4().G.getValue(), Boolean.TRUE) && U4().B.getValue() != null) {
            ToneType value = U4().B.getValue();
            Intrinsics.checkNotNull(value);
            ToneType toneType = value;
            List<Emotion> emotionList = toneType.getEmotionList();
            if (emotionList != null) {
                Iterator<T> it = emotionList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    } else {
                        next = it.next();
                        if (Intrinsics.areEqual(((Emotion) next).getNameKey(), U4().C.getValue())) {
                            break;
                        }
                    }
                }
                emotion = (Emotion) next;
            } else {
                emotion = null;
            }
            String voiceType = toneType.getVoiceType();
            String platform = toneType.getPlatform();
            String resourceId = toneType.getResourceId();
            if (emotion == null) {
                emotion2 = null;
                role = null;
                speakerInfo = new SmartCutAudioClipFlowTask.SpeakerInfo(voiceType, resourceId, platform, emotion2, role, emotionScale);
            } else {
                emotion2 = emotion.getEmotion();
                if (emotion2 == null && (emotion2 = emotion.getStyle()) == null) {
                    if (emotion != null) {
                        emotion2 = emotion.getMoyinEmotion();
                        if (emotion2 == null) {
                            if (emotion != null) {
                                emotion2 = emotion.getRole();
                            }
                        }
                    }
                    emotion2 = null;
                    role = null;
                    speakerInfo = new SmartCutAudioClipFlowTask.SpeakerInfo(voiceType, resourceId, platform, emotion2, role, emotionScale);
                }
                if (emotion != null) {
                    role = emotion.getRole();
                    emotionScale = emotion.getEmotionScale();
                } else {
                    role = null;
                }
                speakerInfo = new SmartCutAudioClipFlowTask.SpeakerInfo(voiceType, resourceId, platform, emotion2, role, emotionScale);
            }
        }
        SmartCutAudioViewModel smartCutAudioViewModelU4 = U4();
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNull(fragmentActivityRequireActivity, "");
        SmartCutAudioViewModel.P6(smartCutAudioViewModelU4, (ViewModelActivity) fragmentActivityRequireActivity, false, speakerInfo, null, 174);
        AIVoiceOverReporter.f84628a.getClass();
        AIVoiceOverReporter.d("use");
    }

    public final void S4(boolean z) {
        View view = null;
        if (z) {
            View view2 = this.H;
            if (view2 != null) {
                view = view2;
            } else {
                Intrinsics.throwUninitializedPropertyAccessException("panelMaskView");
            }
            ViewExtKt.e(view);
            return;
        }
        View view3 = this.H;
        if (view3 != null) {
            view = view3;
        } else {
            Intrinsics.throwUninitializedPropertyAccessException("panelMaskView");
        }
        ViewExtKt.b(view);
    }

    public final AudioToneSelectViewModel T4() {
        return (AudioToneSelectViewModel) this.P.getValue();
    }

    public final SmartCutAudioViewModel U4() {
        return (SmartCutAudioViewModel) this.O.getValue();
    }

    public final void W4() {
        ConstraintLayout constraintLayout = this.d0;
        AlphaButton alphaButton = null;
        if (constraintLayout == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
            constraintLayout = null;
        }
        ViewExtKt.c(constraintLayout);
        ConstraintLayout constraintLayout2 = this.e0;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tongDisplayCR");
            constraintLayout2 = null;
        }
        ViewExtKt.e(constraintLayout2);
        AlphaButton alphaButton2 = this.f0;
        if (alphaButton2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tongLoadingAnim");
        } else {
            alphaButton = alphaButton2;
        }
        alphaButton.clearAnimation();
    }

    public final void X4(TonePlayState tonePlayState) {
        int i;
        int iOrdinal = tonePlayState.ordinal();
        if (iOrdinal == 0) {
            i = R.drawable.lcc;
        } else if (iOrdinal == 1) {
            i = R.drawable._0t_res_0x7f080441;
        } else if (iOrdinal == 2) {
            i = R.drawable._0u_res_0x7f080442;
        } else {
            if (iOrdinal != 3) {
                throw new NoWhenBranchMatchedException();
            }
            i = R.drawable._0s_res_0x7f080440;
        }
        AlphaButton alphaButton = this.g0;
        AlphaButton alphaButton2 = null;
        if (alphaButton == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playState");
            alphaButton = null;
        }
        alphaButton.setImageResource(i);
        if (tonePlayState == TonePlayState.f84149a) {
            LottieAnimationView lottieAnimationView = this.h0;
            if (lottieAnimationView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
                lottieAnimationView = null;
            }
            lottieAnimationView.setVisibility(0);
            LottieAnimationView lottieAnimationView2 = this.h0;
            if (lottieAnimationView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
                lottieAnimationView2 = null;
            }
            lottieAnimationView2.setRepeatCount(-1);
            LottieAnimationView lottieAnimationView3 = this.h0;
            if (lottieAnimationView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
                lottieAnimationView3 = null;
            }
            lottieAnimationView3.setSpeed(1.0f);
            LottieAnimationView lottieAnimationView4 = this.h0;
            if (lottieAnimationView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
                lottieAnimationView4 = null;
            }
            lottieAnimationView4.playAnimation();
        } else {
            LottieAnimationView lottieAnimationView5 = this.h0;
            if (lottieAnimationView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
                lottieAnimationView5 = null;
            }
            lottieAnimationView5.setVisibility(8);
            LottieAnimationView lottieAnimationView6 = this.h0;
            if (lottieAnimationView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playStateAnimation");
                lottieAnimationView6 = null;
            }
            lottieAnimationView6.cancelAnimation();
        }
        if (tonePlayState == TonePlayState.f84150c) {
            AlphaButton alphaButton3 = this.g0;
            if (alphaButton3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playState");
            } else {
                alphaButton2 = alphaButton3;
            }
            alphaButton2.startAnimation(this.c0);
        } else {
            AlphaButton alphaButton4 = this.g0;
            if (alphaButton4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playState");
            } else {
                alphaButton2 = alphaButton4;
            }
            alphaButton2.clearAnimation();
        }
        this.i0 = tonePlayState;
    }

    public final void Y4(boolean z) {
        VegaTextView vegaTextView = null;
        if (z) {
            AIVoiceOverReporter.f84628a.getClass();
            AIVoiceOverReporter.e = "customize";
            ConstraintLayout constraintLayout = this.d0;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
                constraintLayout = null;
            }
            constraintLayout.setAlpha(1.0f);
            ImageView imageView = this.Y;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneReplace");
                imageView = null;
            }
            imageView.setAlpha(1.0f);
            AlphaButton alphaButton = this.g0;
            if (alphaButton == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playState");
                alphaButton = null;
            }
            alphaButton.setAlpha(1.0f);
            VegaTextView vegaTextView2 = this.f84108X;
            if (vegaTextView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
                vegaTextView2 = null;
            }
            vegaTextView2.setAlpha(1.0f);
            VegaTextView vegaTextView3 = this.V;
            if (vegaTextView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
                vegaTextView3 = null;
            }
            vegaTextView3.setAlpha(1.0f);
            ImageView imageView2 = this.Y;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneReplace");
                imageView2 = null;
            }
            imageView2.setEnabled(true);
            AlphaButton alphaButton2 = this.g0;
            if (alphaButton2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("playState");
                alphaButton2 = null;
            }
            alphaButton2.setEnabled(true);
            VegaTextView vegaTextView4 = this.f84108X;
            if (vegaTextView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
                vegaTextView4 = null;
            }
            vegaTextView4.setEnabled(true);
            CheckBox checkBox = this.R;
            if (checkBox == null) {
                Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
                checkBox = null;
            }
            checkBox.setEnabled(true);
            SimpleDraweeView simpleDraweeView = this.T;
            if (simpleDraweeView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneCover");
                simpleDraweeView = null;
            }
            simpleDraweeView.setAlpha(1.0f);
            VegaTextView vegaTextView5 = this.W;
            if (vegaTextView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tongLastUsed");
            } else {
                vegaTextView = vegaTextView5;
            }
            vegaTextView.setAlpha(1.0f);
            return;
        }
        AIVoiceOverReporter.f84628a.getClass();
        AIVoiceOverReporter.e = "auto_match";
        ConstraintLayout constraintLayout2 = this.d0;
        if (constraintLayout2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("loadingCR");
            constraintLayout2 = null;
        }
        constraintLayout2.setAlpha(0.4f);
        ImageView imageView3 = this.Y;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneReplace");
            imageView3 = null;
        }
        imageView3.setAlpha(0.4f);
        AlphaButton alphaButton3 = this.g0;
        if (alphaButton3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playState");
            alphaButton3 = null;
        }
        alphaButton3.setAlpha(0.4f);
        VegaTextView vegaTextView6 = this.f84108X;
        if (vegaTextView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
            vegaTextView6 = null;
        }
        vegaTextView6.setAlpha(0.4f);
        ImageView imageView4 = this.Y;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneReplace");
            imageView4 = null;
        }
        imageView4.setEnabled(false);
        AlphaButton alphaButton4 = this.g0;
        if (alphaButton4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("playState");
            alphaButton4 = null;
        }
        alphaButton4.setEnabled(false);
        VegaTextView vegaTextView7 = this.f84108X;
        if (vegaTextView7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
            vegaTextView7 = null;
        }
        vegaTextView7.setEnabled(false);
        CheckBox checkBox2 = this.R;
        if (checkBox2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
            checkBox2 = null;
        }
        checkBox2.setEnabled(false);
        SimpleDraweeView simpleDraweeView2 = this.T;
        if (simpleDraweeView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneCover");
            simpleDraweeView2 = null;
        }
        simpleDraweeView2.setAlpha(0.4f);
        VegaTextView vegaTextView8 = this.V;
        if (vegaTextView8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
            vegaTextView8 = null;
        }
        vegaTextView8.setAlpha(0.4f);
        VegaTextView vegaTextView9 = this.W;
        if (vegaTextView9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tongLastUsed");
        } else {
            vegaTextView = vegaTextView9;
        }
        vegaTextView.setAlpha(0.4f);
    }

    public final void Z4(ToneType toneType, boolean z) {
        String strK7;
        String value;
        List<Emotion> emotionList;
        Emotion emotion;
        AIVoiceOverReporter aIVoiceOverReporter = AIVoiceOverReporter.f84628a;
        String voiceType = toneType.getVoiceType();
        aIVoiceOverReporter.getClass();
        Intrinsics.checkNotNullParameter(voiceType, "");
        AIVoiceOverReporter.f84629c = voiceType;
        AIVoiceOverReporter.f84630d = toneType.isSingCloneTone() || toneType.isAICloneTone();
        U4().B.setValue(toneType);
        VegaTextView vegaTextView = this.W;
        CheckBox checkBox = null;
        if (vegaTextView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tongLastUsed");
            vegaTextView = null;
        }
        vegaTextView.setVisibility(z ? 0 : 4);
        if (Intrinsics.areEqual(toneType.getVoiceType(), "none")) {
            SimpleDraweeView simpleDraweeView = this.T;
            if (simpleDraweeView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneCover");
                simpleDraweeView = null;
            }
            simpleDraweeView.setImageResource(R.drawable.opy);
        } else if (toneType.isSingCloneTone() || toneType.isAICloneTone()) {
            SimpleDraweeView simpleDraweeView2 = this.T;
            if (simpleDraweeView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneCover");
                simpleDraweeView2 = null;
            }
            DisplayUtils.f134332a.getClass();
            simpleDraweeView2.setPadding(DisplayUtils.b(10), DisplayUtils.b(10), DisplayUtils.b(10), DisplayUtils.b(10));
            SimpleDraweeView simpleDraweeView3 = this.T;
            if (simpleDraweeView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneCover");
                simpleDraweeView3 = null;
            }
            simpleDraweeView3.setImageResource(R.drawable.q1d);
        } else {
            SimpleDraweeView simpleDraweeView4 = this.T;
            if (simpleDraweeView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneCover");
                simpleDraweeView4 = null;
            }
            simpleDraweeView4.setPadding(0, 0, 0, 0);
            IImageLoader iImageLoaderA = ImageLoaderKt.a();
            String avatarUrl = toneType.getAvatarUrl();
            SimpleDraweeView simpleDraweeView5 = this.T;
            if (simpleDraweeView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneCover");
                simpleDraweeView5 = null;
            }
            DisplayUtils.f134332a.getClass();
            IImageLoader.DefaultImpls.b(iImageLoaderA, avatarUrl, simpleDraweeView5, R.drawable.m5m, true, false, null, DisplayUtils.b(12), null, false, 0.0f, 0, 0.0f, 0, 0, false, false, null, null, false, null, null, null, null, null, null, null, null, null, 268435376);
        }
        BenefitType benefitType = Intrinsics.areEqual(toneType.getVipStatus(), "limited_time_free") ? BenefitType.f79530d : (Intrinsics.areEqual(toneType.getVipStatus(), "subscribe") && toneType.isVip()) ? BenefitType.e : (!Intrinsics.areEqual(toneType.getVipStatus(), "subscribe") || toneType.isVip()) ? BenefitType.f79529c : BenefitType.f;
        VipMaterialUtils vipMaterialUtils = VipMaterialUtils.f89614a;
        BusinessMarkView businessMarkView = this.U;
        if (businessMarkView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("ivVipVoiceTone");
            businessMarkView = null;
        }
        VipMaterialUtils.g(vipMaterialUtils, businessMarkView, benefitType, PanelType.f87459g, false, false, null, null, 120);
        VegaTextView vegaTextView2 = this.V;
        if (vegaTextView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
            vegaTextView2 = null;
        }
        vegaTextView2.setText(toneType.getName());
        if (!ToneType.isMultiEmotionTone$default(toneType, 0, 1, null) || ((value = U4().C.getValue()) == null && ((emotionList = toneType.getEmotionList()) == null || (emotion = (Emotion) CollectionsKt___CollectionsKt.firstOrNull((List) emotionList)) == null || (value = emotion.getNameKey()) == null))) {
            strK7 = "";
        } else {
            T4().getClass();
            strK7 = ToneSelectViewModel.k7(value);
            U4().C.postValue(value);
        }
        BLog.i(this.b0, "applyTone, name: " + toneType.getName() + ", emotion: " + strK7);
        if (strK7.length() == 0) {
            VegaTextView vegaTextView3 = this.f84108X;
            if (vegaTextView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
                vegaTextView3 = null;
            }
            ViewExtKt.b(vegaTextView3);
            VegaTextView vegaTextView4 = this.V;
            if (vegaTextView4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
                vegaTextView4 = null;
            }
            ViewGroup.LayoutParams layoutParams = vegaTextView4.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams, "");
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.bottomToBottom = R.id.tone_cover;
            VegaTextView vegaTextView5 = this.V;
            if (vegaTextView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
                vegaTextView5 = null;
            }
            vegaTextView5.setLayoutParams(layoutParams2);
            ConstraintLayout constraintLayout = this.e0;
            if (constraintLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tongDisplayCR");
                constraintLayout = null;
            }
            constraintLayout.requestLayout();
        } else {
            VegaTextView vegaTextView6 = this.f84108X;
            if (vegaTextView6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
                vegaTextView6 = null;
            }
            vegaTextView6.setText(strK7);
            VegaTextView vegaTextView7 = this.f84108X;
            if (vegaTextView7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneDetail");
                vegaTextView7 = null;
            }
            ViewExtKt.e(vegaTextView7);
            VegaTextView vegaTextView8 = this.V;
            if (vegaTextView8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
                vegaTextView8 = null;
            }
            ViewGroup.LayoutParams layoutParams3 = vegaTextView8.getLayoutParams();
            Intrinsics.checkNotNull(layoutParams3, "");
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
            layoutParams4.bottomToTop = R.id.tone_detail;
            VegaTextView vegaTextView9 = this.V;
            if (vegaTextView9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("toneTitle");
                vegaTextView9 = null;
            }
            vegaTextView9.setLayoutParams(layoutParams4);
            ConstraintLayout constraintLayout2 = this.e0;
            if (constraintLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tongDisplayCR");
                constraintLayout2 = null;
            }
            constraintLayout2.requestLayout();
        }
        SmartAiVoiceConfig.f84159a.getClass();
        KvStorage kvStorage = SmartAiVoiceConfig.b;
        StringBuilder sb = new StringBuilder();
        DraftChangeObserverViewModel.i.getClass();
        sb.append(DraftChangeObserverViewModel.j);
        sb.append("_smart_ai_voice_tone_recommend_config");
        if (kvStorage.d(sb.toString(), false)) {
            CheckBox checkBox2 = this.R;
            if (checkBox2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("usingCustomOption");
            } else {
                checkBox = checkBox2;
            }
            checkBox.setChecked(true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onPause() {
        super.onPause();
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        SmartAiVoiceConfig.f84159a.getClass();
        if (!SmartAiVoiceConfig.b.d("smart_ai_voice_guide_has_show", false) || (ContextExtKt.hostEnv().appContext().getDebug() && ExtentionKt.isNotNullOrEmpty(((AiVoiceOverDynamicConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AiVoiceOverDynamicConfigSettings.class))).getGuideVideoUrl()))) {
            ThreadUtilKt.e(400L, new Function0<Unit>() { // from class: com.vega.edit.aiclipper.panel.aiedit.audio.AiEditAudioSettingsPageV2.onResume.1
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    AiEditAudioSettingsPageV2.this.G4().Q(false);
                    SmartAiVoiceConfig.f84159a.getClass();
                    SmartAiVoiceConfig.b.l("smart_ai_voice_guide_has_show", true, false);
                    return Unit.INSTANCE;
                }
            });
        }
    }

    @Override // com.vega.edit.aiclipper.panel.aiedit.base.AiEditSettingsPage, com.vega.edit.aiclipper.panel.aiedit.base.AiEditPage
    public final void r4(boolean z) {
        super.r4(z);
    }

    @Override // com.vega.edit.aiclipper.panel.aiedit.base.AiEditPage
    public final void u4(MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(motionEvent, "");
        VegaEditText vegaEditText = null;
        if (motionEvent.getAction() == 0) {
            FrameLayout frameLayout = this.M;
            if (frameLayout == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiThemeSelection");
                frameLayout = null;
            }
            frameLayout.getLocationOnScreen(this.a0);
        }
        int[] iArr = this.a0;
        float f = iArr[0];
        float f2 = iArr[1];
        if (motionEvent.getRawX() >= f) {
            float rawX = motionEvent.getRawX();
            FrameLayout frameLayout2 = this.M;
            if (frameLayout2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("aiThemeSelection");
                frameLayout2 = null;
            }
            if (rawX <= f + frameLayout2.getWidth() && motionEvent.getRawY() >= f2) {
                float rawY = motionEvent.getRawY();
                FrameLayout frameLayout3 = this.M;
                if (frameLayout3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("aiThemeSelection");
                    frameLayout3 = null;
                }
                if (rawY <= f2 + frameLayout3.getHeight()) {
                    return;
                }
            }
        }
        VegaEditText vegaEditText2 = this.S;
        if (vegaEditText2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
            vegaEditText2 = null;
        }
        vegaEditText2.clearFocus();
        KeyboardUtils keyboardUtils = KeyboardUtils.f106639a;
        VegaEditText vegaEditText3 = this.S;
        if (vegaEditText3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("promptEditText");
        } else {
            vegaEditText = vegaEditText3;
        }
        keyboardUtils.getClass();
        KeyboardUtils.b(vegaEditText);
    }
}
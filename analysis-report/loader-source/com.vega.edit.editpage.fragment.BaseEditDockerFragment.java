package com.vega.edit.editpage.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.kanyun.kace.AndroidExtensionsBase;
import com.lemon.lv.config.OverseaPanelColumnCountConfig;
import com.lemon.lv.config.PanelColumnCountConfig;
import com.lemon.lv.editor.EditorProxyModule;
import com.lemon.lv.editor.proxy.ICutsameProxy;
import com.lemon.lvoverseas.R;
import com.vega.audio.viewmodel.AudioActionObserveViewModel;
import com.vega.audio.viewmodel.AudioViewModel;
import com.vega.commonedit.digitalhuman.DigitalHumanPanelDataViewModel;
import com.vega.commonedit.formula.viewmodel.FormulaViewModelV2;
import com.vega.commonedit.fragment.AbsEditFragment;
import com.vega.commonedit.template.utils.InEditTemplateReportHelper;
import com.vega.commonedit.template.viewmodel.TemplateViewModel;
import com.vega.commonedit.videoanim.ui_vertical.MainVideoAnimVerticalPanel;
import com.vega.config.ConfigSettingsKt;
import com.vega.config.EditE2EImportOptConfig;
import com.vega.config.EditE2EImportOptConfigSetting;
import com.vega.container.EditContainer;
import com.vega.container.session.core.ISession;
import com.vega.container.session.state.LiteStateMachine;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.utils.performance.PerformanceLog;
import com.vega.core.utils.performance.StageDefine;
import com.vega.core.viewmodel.EmptyEvent;
import com.vega.core.viewmodel.NoneTypeLiveEvent;
import com.vega.edit.adjust.viewmodel.GlobalAdjustViewModel;
import com.vega.edit.base.component.builder.ITranscriptEditPanel;
import com.vega.edit.base.component.model.Component;
import com.vega.edit.base.container.track.IEditTrackComponent;
import com.vega.edit.base.di.EditContainerProviderKt;
import com.vega.edit.base.dock.CommonDockManager;
import com.vega.edit.base.dock.DockGroupView;
import com.vega.edit.base.dock.DockItem;
import com.vega.edit.base.dock.DockItemGroup;
import com.vega.edit.base.dock.DockRedDotManager;
import com.vega.edit.base.dock.IDockManager;
import com.vega.edit.base.dock.IPanel;
import com.vega.edit.base.dock.IPanelListener;
import com.vega.edit.base.model.repository.SegmentState;
import com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting;
import com.vega.edit.base.template.abtest.TemplateEditABManager;
import com.vega.edit.base.template.reportHelper.TemplateCombinationReportHelper;
import com.vega.edit.base.utils.DraftExKt;
import com.vega.edit.base.viewmodel.EditComponentViewModel;
import com.vega.edit.base.viewmodel.IEditUIViewModel;
import com.vega.edit.base.viewmodel.IFunctionAssistantViewModel;
import com.vega.edit.base.viewmodel.PlayPositionState;
import com.vega.edit.cover.view.panel.CoverPanel;
import com.vega.edit.digitalhuman.digital.viewmodel.DigitalHumanViewModel;
import com.vega.edit.digitalhuman.digital.viewmodel.MainVideoDigitalHumanViewModel;
import com.vega.edit.digitalhuman.digital.viewmodel.StickerDigitalHumanViewModel;
import com.vega.edit.digitalhuman.digital.viewmodel.SubVideoDigitalHumanViewModel;
import com.vega.edit.dock.DockManager;
import com.vega.edit.dock.DockManagerEx;
import com.vega.edit.editpage.controller.BaseEditVipController;
import com.vega.edit.editpage.controller.VideoAgentDeeplinkController;
import com.vega.edit.editpage.fragment.BaseEditDockerFragment;
import com.vega.edit.editpage.helper.EditCommonParams;
import com.vega.edit.editpage.viewmodel.EditDockerViewModel;
import com.vega.edit.editpage.viewmodel.PresentComponentInfo;
import com.vega.edit.editpage.viewmodel.PresentEventParam;
import com.vega.edit.figure.model.FigureResourceViewModel;
import com.vega.edit.figure.model.dock.FigureCategoryViewModel;
import com.vega.edit.filter.viewmodel.GlobalFilterViewModel;
import com.vega.edit.image.mode.DockActionEvent;
import com.vega.edit.image.mode.ImageTabViewModel;
import com.vega.edit.keyframe.KeyframeUIHelper;
import com.vega.edit.muxer.viewmodel.SubVideoViewModel;
import com.vega.edit.video.viewmodel.MainVideoViewModel;
import com.vega.edit.videoeffect.viewmodel.VideoEffectViewModel;
import com.vega.edit.volume.MainVideoVolumeViewModel;
import com.vega.guideapi.IGuide;
import com.vega.homepage.singlefunc.AbsSingleFunctionEditSharedVM;
import com.vega.homepage.singlefunc.SingleFunctionService;
import com.vega.infrastructure.extensions.ViewExtKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.vm.ViewModelActivity;
import com.vega.libsticker.viewmodel.MutableSubtitleViewModel;
import com.vega.libsticker.viewmodel.StickerViewModel;
import com.vega.middlebridge.swig.Draft;
import com.vega.middlebridge.swig.LVVESingleFunctionType;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentVideo;
import com.vega.performance.BadParcelableExceptionOpt;
import com.vega.report.ReportManagerWrapper;
import com.vega.ui.activity.ActivitySystemBarExtensionsKt;
import com.vega.ve.utils.DraftExpandKt;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONException;
import org.json.JSONObject;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes21.dex */
public class BaseEditDockerFragment extends AbsEditFragment {
    public final Lazy A;
    public final Lazy B;
    public final Lazy C;
    public final Lazy D;
    public final Lazy E;
    public final Lazy F;
    public final Lazy G;
    public final Lazy H;
    public String I;

    /* renamed from: J, reason: collision with root package name */
    public String f91647J;
    public boolean K;
    public Function4<? super IPanel, ? super DockGroupView, ? super String, ? super DockGroupView.State, Unit> L;
    public Function1<? super DockManager, Unit> M;
    public KeyframeUIHelper N;
    public EditCommonParams O;
    public BaseEditVipController P;
    public TrackSelectCallback Q;
    public boolean R;
    public final Lazy S;
    public final Lazy T;
    public VideoAgentDeeplinkController U;
    public DockManager j;
    public final Lazy l;
    public final Lazy m;
    public final Lazy n;
    public final Lazy o;
    public final Lazy p;
    public final Lazy q;
    public final Lazy r;
    public final Lazy s;
    public final Lazy t;
    public final Lazy u;
    public final Lazy v;
    public final Lazy w;
    public final Lazy x;
    public final Lazy y;
    public final Lazy z;
    public final String i = "EditDockerNitaView";
    public final Lazy k = EditContainerProviderKt.a(this);

    /* loaded from: classes16.dex */
    public static final class Companion {
    }

    /* loaded from: classes19.dex */
    public interface TrackSelectCallback {
        boolean a(String str);
    }

    /* loaded from: classes14.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f91670a;

        static {
            int[] iArr = new int[LVVESingleFunctionType.values().length];
            try {
                iArr[0] = 1;
            } catch (NoSuchFieldError unused) {
            }
            f91670a = iArr;
        }
    }

    static {
        new Companion();
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$3] */
    /* JADX WARN: Type inference failed for: r1v10, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$21] */
    /* JADX WARN: Type inference failed for: r1v11, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$23] */
    /* JADX WARN: Type inference failed for: r1v12, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$25] */
    /* JADX WARN: Type inference failed for: r1v13, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$27] */
    /* JADX WARN: Type inference failed for: r1v14, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$29] */
    /* JADX WARN: Type inference failed for: r1v15, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$31] */
    /* JADX WARN: Type inference failed for: r1v16, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$33] */
    /* JADX WARN: Type inference failed for: r1v17, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$35] */
    /* JADX WARN: Type inference failed for: r1v18, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$37] */
    /* JADX WARN: Type inference failed for: r1v19, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$39] */
    /* JADX WARN: Type inference failed for: r1v2, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$5] */
    /* JADX WARN: Type inference failed for: r1v20, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$41] */
    /* JADX WARN: Type inference failed for: r1v21, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$43] */
    /* JADX WARN: Type inference failed for: r1v3, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$7] */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$9] */
    /* JADX WARN: Type inference failed for: r1v5, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$11] */
    /* JADX WARN: Type inference failed for: r1v6, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$13] */
    /* JADX WARN: Type inference failed for: r1v7, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$15] */
    /* JADX WARN: Type inference failed for: r1v8, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$17] */
    /* JADX WARN: Type inference failed for: r1v9, types: [com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$19] */
    public BaseEditDockerFragment() {
        final ?? r1 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$1
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
        this.l = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<IEditUIViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$2
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.IEditUIViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final IEditUIViewModel invoke() {
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
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(IEditUIViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r12 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$3
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
        this.m = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$4
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.video.viewmodel.MainVideoViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoViewModel invoke() {
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
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(MainVideoViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r13 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$5
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
        this.n = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoDigitalHumanViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$6
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.digitalhuman.digital.viewmodel.MainVideoDigitalHumanViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoDigitalHumanViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r13;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(MainVideoDigitalHumanViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r14 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$7
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
        this.o = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<DigitalHumanPanelDataViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$8
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.commonedit.digitalhuman.DigitalHumanPanelDataViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DigitalHumanPanelDataViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r14;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(DigitalHumanPanelDataViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r15 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$9
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
        this.p = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubVideoDigitalHumanViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$10
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.digitalhuman.digital.viewmodel.SubVideoDigitalHumanViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubVideoDigitalHumanViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r15;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(SubVideoDigitalHumanViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r16 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$11
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
        this.q = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<StickerDigitalHumanViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$12
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.digitalhuman.digital.viewmodel.StickerDigitalHumanViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final StickerDigitalHumanViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r16;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(StickerDigitalHumanViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r17 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$13
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
        this.r = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditDockerViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$14
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.editpage.viewmodel.EditDockerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EditDockerViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r17;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(EditDockerViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r18 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$15
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
        this.s = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<StickerViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$16
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.viewmodel.StickerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final StickerViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r18;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(StickerViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r19 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$17
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
        this.t = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<AudioActionObserveViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$18
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.audio.viewmodel.AudioActionObserveViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AudioActionObserveViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r19;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(AudioActionObserveViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r110 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$19
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
        this.u = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditComponentViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$20
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.EditComponentViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EditComponentViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r110;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(EditComponentViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r111 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$21
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
        this.v = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MutableSubtitleViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$22
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.viewmodel.MutableSubtitleViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MutableSubtitleViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r111;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(MutableSubtitleViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r112 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$23
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
        this.w = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubVideoViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$24
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.muxer.viewmodel.SubVideoViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubVideoViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r112;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(SubVideoViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r113 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$25
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
        this.x = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<AudioViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$26
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.audio.viewmodel.AudioViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AudioViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r113;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(AudioViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r114 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$27
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
        this.y = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<GlobalFilterViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$28
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.filter.viewmodel.GlobalFilterViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final GlobalFilterViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r114;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(GlobalFilterViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r115 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$29
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
        this.z = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<GlobalAdjustViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$30
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.adjust.viewmodel.GlobalAdjustViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final GlobalAdjustViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r115;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(GlobalAdjustViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r116 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$31
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
        this.A = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<VideoEffectViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$32
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.videoeffect.viewmodel.VideoEffectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final VideoEffectViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r116;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(VideoEffectViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r117 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$33
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
        this.B = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<TemplateViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$34
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.commonedit.template.viewmodel.TemplateViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final TemplateViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r117;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(TemplateViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r118 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$35
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
        this.C = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoVolumeViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$36
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.volume.MainVideoVolumeViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoVolumeViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r118;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(MainVideoVolumeViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r119 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$37
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
        this.D = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<FormulaViewModelV2>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$38
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.commonedit.formula.viewmodel.FormulaViewModelV2] */
            @Override // kotlin.jvm.functions.Function0
            public final FormulaViewModelV2 invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r119;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(FormulaViewModelV2.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r120 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$39
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
        this.E = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<FigureCategoryViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$40
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.figure.model.dock.FigureCategoryViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final FigureCategoryViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r120;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(FigureCategoryViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r121 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$41
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
        this.F = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<IFunctionAssistantViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$42
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.IFunctionAssistantViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final IFunctionAssistantViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r121;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(IFunctionAssistantViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        this.G = LazyKt__LazyJVMKt.lazy(new Function0<AbsSingleFunctionEditSharedVM>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$singleFunctionViewModel$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final AbsSingleFunctionEditSharedVM invoke() {
                FragmentActivity activity = this.e.getActivity();
                if (activity != null) {
                    return SingleFunctionService.b.b(activity);
                }
                return null;
            }
        });
        final ?? r122 = new Function0<FragmentActivity>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$43
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
        this.H = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ImageTabViewModel>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$special$$inlined$activityFactoryViewModel$44
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.edit.image.mode.ImageTabViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ImageTabViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r122;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(ImageTabViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        this.M = new Function1<DockManager, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$onDockerAttachListener$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(DockManager dockManager) {
                Intrinsics.checkNotNullParameter(dockManager, "");
                return Unit.INSTANCE;
            }
        };
        this.S = LazyKt__LazyJVMKt.lazy(new Function0<IGuide>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$guide$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.guideapi.IGuide, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final IGuide invoke() {
                return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IGuide.class), null);
            }
        });
        this.T = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$isFromEditAnchor$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(this.e.i4().l.getBoolean("from_anchor", false));
            }
        });
    }

    public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_fragment_BaseEditDockerFragment_com_vega_launcher_lancet_BadParcelableLancet_getInttent(FragmentActivity fragmentActivity) {
        Context context;
        Intent intent = fragmentActivity.getIntent();
        if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
        }
        return intent;
    }

    @Override // com.vega.commonedit.fragment.AbsEditFragment
    public final int Y3() {
        return R.layout._52_res_0x7f0c051c;
    }

    @Override // com.vega.commonedit.fragment.AbsEditFragment
    public final String a4() {
        return this.i;
    }

    /* JADX WARN: Type inference failed for: r3v1, types: [X.0S8, androidx.lifecycle.Observer] */
    @Override // com.vega.commonedit.fragment.AbsEditFragment
    public void c4() {
        NoneTypeLiveEvent noneTypeLiveEvent = n4().f92012c;
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "");
        noneTypeLiveEvent.b(viewLifecycleOwner, new Function0<Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                DockManager dockManager = this.e.j;
                if (dockManager != null) {
                    dockManager.onBackPressed();
                }
                return Unit.INSTANCE;
            }
        });
        n4().l.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<PresentEventParam, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$2
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(PresentEventParam presentEventParam) {
                Object obj;
                String str;
                PresentEventParam presentEventParam2 = presentEventParam;
                Map<String, Object> map = presentEventParam2.e;
                if (map != null && (obj = map.get("select_segment_id")) != null && (obj instanceof String) && (str = (String) obj) != null) {
                    BaseEditDockerFragment baseEditDockerFragment = this.e;
                    baseEditDockerFragment.k4().u(str);
                    BaseEditDockerFragment.TrackSelectCallback trackSelectCallback = baseEditDockerFragment.Q;
                    if (trackSelectCallback != null) {
                        trackSelectCallback.a(str);
                    }
                }
                DockManager dockManager = this.e.j;
                if (dockManager != null) {
                    dockManager.y2(presentEventParam2.f92023a, presentEventParam2.b, presentEventParam2.f92024c, presentEventParam2.f92025d, presentEventParam2.e);
                }
                return Unit.INSTANCE;
            }
        }));
        IEditTrackComponent iEditTrackComponentK4 = k4();
        LifecycleOwner viewLifecycleOwner2 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner2, "");
        iEditTrackComponentK4.h(viewLifecycleOwner2, new Function1<HashMap<String, Object>, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$3
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(HashMap<String, Object> map) {
                HashMap<String, Object> map2 = map;
                Intrinsics.checkNotNullParameter(map2, "");
                map2.put("enter_from", Intrinsics.areEqual(this.e.I, "root") ? "base" : "cut");
                DockManager dockManager = this.e.j;
                if (dockManager != null) {
                    dockManager.W5("audio_root");
                }
                return Unit.INSTANCE;
            }
        });
        EditTrackOptimizeConfigSetting.Companion.getClass();
        if (EditTrackOptimizeConfigSetting.Companion.a()) {
            IEditTrackComponent iEditTrackComponentK42 = k4();
            LifecycleOwner viewLifecycleOwner3 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner3, "");
            iEditTrackComponentK42.K(viewLifecycleOwner3, new Function1<HashMap<String, Object>, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$4
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(HashMap<String, Object> map) {
                    HashMap<String, Object> map2 = map;
                    Intrinsics.checkNotNullParameter(map2, "");
                    map2.put("enter_from", Intrinsics.areEqual(this.e.I, "root") ? "base" : "cut");
                    map2.put("entrySource", "TextTrack");
                    map2.put("visiblePage", "TextPanelExpanded");
                    DockManager dockManager = this.e.j;
                    if (dockManager != null) {
                        dockManager.W5("text_root");
                    }
                    return Unit.INSTANCE;
                }
            });
            IEditTrackComponent iEditTrackComponentK43 = k4();
            LifecycleOwner viewLifecycleOwner4 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner4, "");
            iEditTrackComponentK43.z(viewLifecycleOwner4, new Function1<HashMap<String, Object>, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$5
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(HashMap<String, Object> map) {
                    HashMap<String, Object> map2 = map;
                    Intrinsics.checkNotNullParameter(map2, "");
                    map2.put("enter_from", Intrinsics.areEqual(this.e.I, "root") ? "base" : "cut");
                    DockManager dockManager = this.e.j;
                    if (dockManager != null) {
                        dockManager.W5("subVideo_root");
                    }
                    return Unit.INSTANCE;
                }
            });
            IEditTrackComponent iEditTrackComponentK44 = k4();
            LifecycleOwner viewLifecycleOwner5 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner5, "");
            iEditTrackComponentK44.C(viewLifecycleOwner5, new Function1<HashMap<String, Object>, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$6
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(HashMap<String, Object> map) {
                    HashMap<String, Object> map2 = map;
                    Intrinsics.checkNotNullParameter(map2, "");
                    map2.put("enter_from", Intrinsics.areEqual(this.e.I, "root") ? "base" : "cut");
                    DockManager dockManager = this.e.j;
                    if (dockManager != null) {
                        dockManager.W5("videoEffect_root");
                    }
                    return Unit.INSTANCE;
                }
            });
            IEditTrackComponent iEditTrackComponentK45 = k4();
            LifecycleOwner viewLifecycleOwner6 = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner6, "");
            iEditTrackComponentK45.o(viewLifecycleOwner6, new Function1<HashMap<String, Object>, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$7
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(HashMap<String, Object> map) {
                    HashMap<String, Object> map2 = map;
                    Intrinsics.checkNotNullParameter(map2, "");
                    map2.put("enter_from", Intrinsics.areEqual(this.e.I, "root") ? "base" : "cut");
                    DockManager dockManager = this.e.j;
                    if (dockManager != null) {
                        dockManager.W5("filter_root");
                    }
                    return Unit.INSTANCE;
                }
            });
        }
        IEditTrackComponent iEditTrackComponentK46 = k4();
        LifecycleOwner viewLifecycleOwner7 = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner7, "");
        iEditTrackComponentK46.m(viewLifecycleOwner7, new Function1<String, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$8
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(String str) {
                Intrinsics.checkNotNullParameter(str, "");
                DockManager dockManager = this.e.j;
                if (dockManager != null) {
                    dockManager.W5("subVideo_add");
                }
                this.e.K = true;
                return Unit.INSTANCE;
            }
        });
        ((MutableSubtitleViewModel) this.v.getValue()).s.observe(this, new Observer() { // from class: X.0QY
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DockItemGroup currDock;
                List<DockItem> list;
                DockManager dockManager;
                BaseEditDockerFragment baseEditDockerFragment = this.f2384a;
                DockManager dockManager2 = baseEditDockerFragment.j;
                if ((dockManager2 != null ? dockManager2.W() : null) instanceof ITranscriptEditPanel) {
                    return;
                }
                DockManagerEx dockManagerEx = DockManagerEx.f91126a;
                DockManager dockManager3 = baseEditDockerFragment.j;
                DockGroupView dockGroupView = dockManager3 != null ? dockManager3.b : null;
                dockManagerEx.getClass();
                if (dockGroupView != null) {
                    TemplateEditABManager.f88836a.getClass();
                    if (TemplateEditABManager.c() && (currDock = dockGroupView.getCurrDock()) != null && (list = currDock.f87435c) != null) {
                        Iterator<T> it = list.iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Object next = it.next();
                            if (Intrinsics.areEqual(((DockItem) next).f87430a, "template_edit_text")) {
                                if (next != null) {
                                    return;
                                }
                            }
                        }
                    }
                }
                SegmentState value = baseEditDockerFragment.m4().v.getValue();
                if (value == null || value.f87871c == null) {
                    DockManager dockManager4 = baseEditDockerFragment.j;
                    if (((dockManager4 != null ? dockManager4.W() : null) instanceof CoverPanel) || (dockManager = baseEditDockerFragment.j) == null) {
                        return;
                    }
                    dockManager.onBackPressed();
                }
            }
        });
        ((AudioActionObserveViewModel) this.t.getValue()).o.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$10
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(String str) {
                String str2 = str;
                if (str2 != null) {
                    StickerViewModel.S7(this.e.m4(), str2, null, false, false, false, 30);
                }
                return Unit.INSTANCE;
            }
        }));
        n4().m.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<String, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$11
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(String str) {
                String str2 = str;
                BaseEditDockerFragment baseEditDockerFragment = this.e;
                Intrinsics.checkNotNull(str2);
                baseEditDockerFragment.q4(str2);
                return Unit.INSTANCE;
            }
        }));
        ((MainVideoVolumeViewModel) this.C.getValue()).k.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Pair<? extends Segment, ? extends Float>, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$12
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Pair<? extends Segment, ? extends Float> pair) throws JSONException {
                Segment segment;
                Pair<? extends Segment, ? extends Float> pair2 = pair;
                if (DraftExKt.O(pair2.getFirst()) || DraftExpandKt.N(pair2.getFirst())) {
                    TemplateViewModel templateViewModel = (TemplateViewModel) this.e.B.getValue();
                    String.valueOf(pair2.getSecond().floatValue() * 100);
                    templateViewModel.getClass();
                    SegmentState value = templateViewModel.n.f87853d.getValue();
                    if (value != null && (segment = value.f87871c) != null) {
                        InEditTemplateReportHelper inEditTemplateReportHelper = InEditTemplateReportHelper.f78429a;
                        Draft draftL = templateViewModel.o.l();
                        if (draftL != null) {
                            draftL.b();
                        }
                        String strB = segment.b();
                        inEditTemplateReportHelper.getClass();
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("action_type", "volume");
                        if (strB != null) {
                            TemplateCombinationReportHelper.f88842a.getClass();
                            Boolean bool = TemplateCombinationReportHelper.f88845g.get(strB);
                            if (bool != null) {
                                jSONObject.put("is_edit_template_use_from_hook", bool.booleanValue() ? 1 : 0);
                            }
                        }
                        ReportManagerWrapper.INSTANCE.onEvent("edit_template_adjust", jSONObject);
                    }
                }
                return Unit.INSTANCE;
            }
        }));
        n4().n.b(this, new Function0<Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$13
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                DockManager dockManager = this.e.j;
                if (dockManager != null) {
                    dockManager.e();
                }
                return Unit.INSTANCE;
            }
        });
        n4().o.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<PresentComponentInfo, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$14
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(PresentComponentInfo presentComponentInfo) {
                String str;
                PresentComponentInfo presentComponentInfo2 = presentComponentInfo;
                Object obj = presentComponentInfo2.b.get("select_segment_id");
                if (obj != null && (obj instanceof String) && (str = (String) obj) != null) {
                    BaseEditDockerFragment baseEditDockerFragment = this.e;
                    baseEditDockerFragment.k4().u(str);
                    BaseEditDockerFragment.TrackSelectCallback trackSelectCallback = baseEditDockerFragment.Q;
                    if (trackSelectCallback != null) {
                        trackSelectCallback.a(str);
                    }
                }
                DockManager dockManager = this.e.j;
                if (dockManager != null) {
                    IDockManager.DefaultImpls.a(dockManager, presentComponentInfo2.f92022a, true, null, false, presentComponentInfo2.b, 12);
                }
                return Unit.INSTANCE;
            }
        }));
        ((MainVideoViewModel) this.m.getValue()).j.observe(this, new Observer() { // from class: X.07n
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                DockManager dockManager;
                BaseEditDockerFragment baseEditDockerFragment = this.f1196a;
                String str = baseEditDockerFragment.I;
                if (str == null) {
                    return;
                }
                if (!(Intrinsics.areEqual(str, "video_root") || Intrinsics.areEqual(str, "subVideo_add")) || (dockManager = baseEditDockerFragment.j) == null) {
                    return;
                }
                dockManager.I1();
            }
        });
        ((DigitalHumanViewModel) this.p.getValue()).f90849d.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<EmptyEvent, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$2
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(EmptyEvent emptyEvent) {
                DockManager dockManager;
                String str = this.e.I;
                if (str != null && ((Intrinsics.areEqual(str, "video_root") || Intrinsics.areEqual(str, "subVideo_add")) && (dockManager = this.e.j) != null)) {
                    dockManager.I1();
                }
                return Unit.INSTANCE;
            }
        }));
        ((DigitalHumanViewModel) this.n.getValue()).f90849d.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<EmptyEvent, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$3
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(EmptyEvent emptyEvent) {
                DockManager dockManager;
                String str = this.e.I;
                if (str != null && ((Intrinsics.areEqual(str, "video_root") || Intrinsics.areEqual(str, "subVideo_add")) && (dockManager = this.e.j) != null)) {
                    dockManager.I1();
                }
                return Unit.INSTANCE;
            }
        }));
        ((DigitalHumanViewModel) this.q.getValue()).f90849d.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<EmptyEvent, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$4
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(EmptyEvent emptyEvent) {
                DockManager dockManager;
                String str = this.e.I;
                if (str != null && ((Intrinsics.areEqual(str, "video_root") || Intrinsics.areEqual(str, "subVideo_add")) && (dockManager = this.e.j) != null)) {
                    dockManager.I1();
                }
                return Unit.INSTANCE;
            }
        }));
        ((DigitalHumanPanelDataViewModel) this.o.getValue()).r.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<EmptyEvent, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$5
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(EmptyEvent emptyEvent) {
                DockManager dockManager;
                String str = this.e.I;
                if (str != null && ((Intrinsics.areEqual(str, "video_root") || Intrinsics.areEqual(str, "subVideo_add")) && (dockManager = this.e.j) != null)) {
                    dockManager.I1();
                }
                return Unit.INSTANCE;
            }
        }));
        ((SubVideoViewModel) this.w.getValue()).n.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Unit, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$6
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Unit unit) {
                DockManager dockManager;
                Intrinsics.checkNotNullParameter(unit, "");
                String str = this.e.I;
                if (str != null && ((Intrinsics.areEqual(str, "video_root") || Intrinsics.areEqual(str, "subVideo_add")) && (dockManager = this.e.j) != null)) {
                    dockManager.I1();
                }
                return Unit.INSTANCE;
            }
        }));
        ((MainVideoViewModel) this.m.getValue()).e.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<SegmentState, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$7
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(SegmentState segmentState) {
                SegmentVideo segmentVideo;
                DockManager dockManager;
                Component componentI;
                Segment segment = segmentState.f87871c;
                if (segment != null) {
                    BaseEditDockerFragment baseEditDockerFragment = this.e;
                    DockManager dockManager2 = baseEditDockerFragment.j;
                    String strD = (dockManager2 == null || (componentI = dockManager2.Z5().i()) == null) ? null : componentI.d();
                    MutableLiveData<Pair<String, IPanel>> mutableLiveData = baseEditDockerFragment.n4().e;
                    DockManager dockManager3 = baseEditDockerFragment.j;
                    mutableLiveData.setValue(TuplesKt.to(strD, dockManager3 != null ? dockManager3.W() : null));
                    if ((segment instanceof SegmentVideo) && (segmentVideo = (SegmentVideo) segment) != null && (dockManager = baseEditDockerFragment.j) != null) {
                        dockManager.j(segmentVideo, false);
                    }
                }
                return Unit.INSTANCE;
            }
        }));
        ((SubVideoViewModel) this.w.getValue()).e.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<SegmentState, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeBackgroundWorkState$8
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(SegmentState segmentState) {
                SegmentVideo segmentVideo;
                DockManager dockManager;
                Segment segment = segmentState.f87871c;
                if (segment != null) {
                    BaseEditDockerFragment baseEditDockerFragment = this.e;
                    if ((segment instanceof SegmentVideo) && (segmentVideo = (SegmentVideo) segment) != null && (dockManager = baseEditDockerFragment.j) != null) {
                        dockManager.j(segmentVideo, true);
                    }
                }
                return Unit.INSTANCE;
            }
        }));
        final ?? r3 = new Observer() { // from class: X.0S8
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Code restructure failed: missing block: B:83:0x0153, code lost:
            
                if (r0 != null) goto L86;
             */
            /* JADX WARN: Removed duplicated region for block: B:38:0x0080  */
            /* JADX WARN: Removed duplicated region for block: B:53:0x00c8  */
            /* JADX WARN: Removed duplicated region for block: B:74:0x0120  */
            /* JADX WARN: Removed duplicated region for block: B:80:0x013f  */
            @Override // androidx.lifecycle.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void onChanged(java.lang.Object r10) {
                /*
                    r9 = this;
                    com.vega.edit.editpage.fragment.BaseEditDockerFragment r4 = r9.f2506a
                    com.vega.edit.base.model.repository.SegmentState r10 = (com.vega.edit.base.model.repository.SegmentState) r10
                    java.lang.String r1 = r4.I
                    if (r1 != 0) goto L9
                L8:
                    return
                L9:
                    kotlin.Lazy r0 = r4.l
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.viewmodel.IEditUIViewModel r0 = (com.vega.edit.base.viewmodel.IEditUIViewModel) r0
                    androidx.lifecycle.MutableLiveData r0 = r0.g7()
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.viewmodel.PlayPositionState r0 = (com.vega.edit.base.viewmodel.PlayPositionState) r0
                    if (r0 == 0) goto L157
                    long r2 = r0.f89437a
                L1f:
                    if (r10 == 0) goto L25
                    com.vega.middlebridge.swig.Segment r0 = r10.f87871c
                    if (r0 != 0) goto L15b
                L25:
                    int r0 = r1.hashCode()
                    switch(r0) {
                        case -1477447462: goto L38;
                        case -1404546804: goto L41;
                        case -1159072410: goto L4a;
                        case -944117480: goto L53;
                        case -927172971: goto L5c;
                        case -923728823: goto L65;
                        case -883751188: goto L6e;
                        case -866488195: goto L77;
                        case -543538738: goto L96;
                        case -156455031: goto L9f;
                        case -155861538: goto Lbe;
                        case -8633486: goto Ld9;
                        case 603814076: goto Lf8;
                        case 819303517: goto L102;
                        case 1353992269: goto L10c;
                        case 1359356727: goto L116;
                        case 1600526079: goto L135;
                        default: goto L2c;
                    }
                L2c:
                    com.vega.edit.editpage.viewmodel.EditDockerViewModel r0 = r4.n4()
                    androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r0.f
                    java.lang.Boolean r0 = java.lang.Boolean.FALSE
                    r1.setValue(r0)
                    goto L8
                L38:
                    java.lang.String r0 = "audio_record"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L120
                    goto L2c
                L41:
                    java.lang.String r0 = "subVideo_edit_figure_body"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L80
                    goto L2c
                L4a:
                    java.lang.String r0 = "subVideo_edit_figure_beauty"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L80
                    goto L2c
                L53:
                    java.lang.String r0 = "audio_extract"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L120
                    goto L2c
                L5c:
                    java.lang.String r0 = "subVideo_edit_figure"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L80
                    goto L2c
                L65:
                    java.lang.String r0 = "infoSticker_addSubtitle"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto Lc8
                    goto L2c
                L6e:
                    java.lang.String r0 = "infoSticker_addSticker"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto Lc8
                    goto L2c
                L77:
                    java.lang.String r0 = "subVideo_add"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L80
                    goto L2c
                L80:
                    kotlin.Lazy r0 = r4.w
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.muxer.viewmodel.SubVideoViewModel r0 = (com.vega.edit.muxer.viewmodel.SubVideoViewModel) r0
                    com.vega.edit.base.model.repository.KeyframeCacheRepository$segmentState$1 r0 = r0.e
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
                    if (r0 == 0) goto L2c
                    com.vega.middlebridge.swig.Segment r0 = r0.f87871c
                    goto L153
                L96:
                    java.lang.String r0 = "infoSticker_addLyric"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto Lc8
                    goto L2c
                L9f:
                    java.lang.String r0 = "filter_addAdjust"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto La8
                    goto L2c
                La8:
                    kotlin.Lazy r0 = r4.z
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.adjust.viewmodel.GlobalAdjustViewModel r0 = (com.vega.edit.adjust.viewmodel.GlobalAdjustViewModel) r0
                    androidx.lifecycle.MutableLiveData<com.vega.edit.base.model.repository.SegmentState> r0 = r0.w
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
                    if (r0 == 0) goto L2c
                    com.vega.middlebridge.swig.Segment r0 = r0.f87871c
                    goto L153
                Lbe:
                    java.lang.String r0 = "infoSticker_addText"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto Lc8
                    goto L2c
                Lc8:
                    com.vega.libsticker.viewmodel.StickerViewModel r0 = r4.m4()
                    com.vega.edit.base.model.repository.KeyframeCacheRepository$segmentState$1 r0 = r0.v
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
                    if (r0 == 0) goto L2c
                    com.vega.middlebridge.swig.Segment r0 = r0.f87871c
                    goto L153
                Ld9:
                    java.lang.String r0 = "filter_addFilter"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto Le3
                    goto L2c
                Le3:
                    kotlin.Lazy r0 = r4.y
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.filter.viewmodel.GlobalFilterViewModel r0 = (com.vega.edit.filter.viewmodel.GlobalFilterViewModel) r0
                    androidx.lifecycle.MutableLiveData<com.vega.edit.base.model.repository.SegmentState> r0 = r0.v0
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
                    if (r0 == 0) goto L2c
                    com.vega.middlebridge.swig.Segment r0 = r0.f87871c
                    goto L153
                Lf8:
                    java.lang.String r0 = "videoEffect_addFaceEffect"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L13f
                    goto L2c
                L102:
                    java.lang.String r0 = "text_to_audio_root"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L120
                    goto L2c
                L10c:
                    java.lang.String r0 = "audio_addMusic"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L120
                    goto L2c
                L116:
                    java.lang.String r0 = "audio_addSound"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L120
                    goto L2c
                L120:
                    kotlin.Lazy r0 = r4.x
                    java.lang.Object r0 = r0.getValue()
                    com.vega.audio.viewmodel.AudioViewModel r0 = (com.vega.audio.viewmodel.AudioViewModel) r0
                    com.vega.edit.base.model.repository.KeyframeCacheRepository$segmentState$1 r0 = r0.f
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
                    if (r0 == 0) goto L2c
                    com.vega.middlebridge.swig.Segment r0 = r0.f87871c
                    goto L153
                L135:
                    java.lang.String r0 = "videoEffect_addEffect"
                    boolean r0 = r1.equals(r0)
                    if (r0 != 0) goto L13f
                    goto L2c
                L13f:
                    kotlin.Lazy r0 = r4.A
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.videoeffect.viewmodel.VideoEffectViewModel r0 = (com.vega.edit.videoeffect.viewmodel.VideoEffectViewModel) r0
                    com.vega.edit.base.model.repository.KeyframeCacheRepository$segmentState$1 r0 = r0.V
                    java.lang.Object r0 = r0.getValue()
                    com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
                    if (r0 == 0) goto L2c
                    com.vega.middlebridge.swig.Segment r0 = r0.f87871c
                L153:
                    if (r0 != 0) goto L15b
                    goto L2c
                L157:
                    r2 = 0
                    goto L1f
                L15b:
                    com.vega.middlebridge.swig.TimeRange r1 = r0.k()
                    com.vega.edit.editpage.viewmodel.EditDockerViewModel r0 = r4.n4()
                    androidx.lifecycle.MutableLiveData<java.lang.Boolean> r8 = r0.f
                    long r6 = r1.e()
                    long r4 = r1.e()
                    long r0 = r1.d()
                    long r4 = r4 + r0
                    int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
                    if (r0 > 0) goto L186
                    int r0 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
                    if (r0 > 0) goto L186
                    r0 = 1
                L17b:
                    r0 = r0 ^ 1
                    java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                    r8.setValue(r0)
                    goto L8
                L186:
                    r0 = 0
                    goto L17b
                */
                throw new UnsupportedOperationException("Method not decompiled: X.C0S8.onChanged(java.lang.Object):void");
            }
        };
        ((IEditUIViewModel) this.l.getValue()).g7().observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<PlayPositionState, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$observeKeyFrame$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(PlayPositionState playPositionState) {
                r3.onChanged(null);
                return Unit.INSTANCE;
            }
        }));
        m4().v.observe(this, r3);
        ((GlobalAdjustViewModel) this.z.getValue()).w.observe(this, r3);
        ((GlobalFilterViewModel) this.y.getValue()).v0.observe(this, r3);
        ((AudioViewModel) this.x.getValue()).f.observe(this, r3);
        ((ImageTabViewModel) this.H.getValue()).w.observe(this, new BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0(new Function1<DockActionEvent, Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$initObservers$15
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(DockActionEvent dockActionEvent) {
                DockManager dockManager;
                DockManager dockManager2;
                DockActionEvent dockActionEvent2 = dockActionEvent;
                if (!dockActionEvent2.a()) {
                    if (dockActionEvent2.f92904c) {
                        if ((dockActionEvent2.b.length() == 0 || Intrinsics.areEqual(this.e.I, dockActionEvent2.b)) && (dockManager2 = this.e.j) != null) {
                            dockManager2.t4();
                        }
                    } else if (dockActionEvent2.b.length() > 0 && (dockManager = this.e.j) != null) {
                        dockManager.W5(dockActionEvent2.b);
                    }
                }
                return Unit.INSTANCE;
            }
        }));
    }

    public final void h4(final DockManager dockManager) {
        Intrinsics.checkNotNullParameter(dockManager, "");
        Intrinsics.checkNotNull(this, "");
        ViewGroup viewGroup = (ViewGroup) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class);
        if (viewGroup != null) {
            final EditDockerFragment editDockerFragment = (EditDockerFragment) this;
            viewGroup.setOnHierarchyChangeListener(new ViewGroup.OnHierarchyChangeListener() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$attchDockManager$1
                @Override // android.view.ViewGroup.OnHierarchyChangeListener
                public final void onChildViewAdded(View view, View view2) {
                    if (view2 == null) {
                        return;
                    }
                    ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
                    DockGroupView.h.getClass();
                    int i = DockGroupView.j;
                    FragmentActivity activity = editDockerFragment.getActivity();
                    layoutParams.height = i + (activity != null ? ActivitySystemBarExtensionsKt.c(activity) : 0);
                    FragmentActivity activity2 = editDockerFragment.getActivity();
                    view2.setPadding(0, 0, 0, activity2 != null ? ActivitySystemBarExtensionsKt.c(activity2) : 0);
                }

                @Override // android.view.ViewGroup.OnHierarchyChangeListener
                public final void onChildViewRemoved(View view, View view2) {
                }
            });
        }
        AbsSingleFunctionEditSharedVM absSingleFunctionEditSharedVML4 = l4();
        if (absSingleFunctionEditSharedVML4 != null && absSingleFunctionEditSharedVML4.A6()) {
            Intrinsics.checkNotNull(this, "");
            View viewFindViewByIdCached = findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class);
            if (viewFindViewByIdCached != null) {
                ViewExtKt.b(viewFindViewByIdCached);
            }
        }
        this.j = dockManager;
        final EditDockerFragment editDockerFragment2 = (EditDockerFragment) this;
        dockManager.f91091d.b(new DockGroupView.OnStateChangeListener() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$attchDockManager$2$1
            @Override // com.vega.edit.base.dock.DockGroupView.OnStateChangeListener
            public final void a(int i, DockItemGroup dockItemGroup, List<String> list, List<String> list2, DockGroupView.State state) {
                Intrinsics.checkNotNullParameter(dockItemGroup, "");
                Intrinsics.checkNotNullParameter(list, "");
                Intrinsics.checkNotNullParameter(list2, "");
                Intrinsics.checkNotNullParameter(state, "");
                editDockerFragment2.p4(i, dockItemGroup, list, list2, dockManager.W(), state);
            }
        });
        dockManager.D(new IPanelListener() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$attchDockManager$2$2
            @Override // com.vega.edit.base.dock.IPanelListener
            public final void a(IPanel iPanel) {
                Intrinsics.checkNotNullParameter(iPanel, "");
                AndroidExtensionsBase androidExtensionsBase = editDockerFragment2;
                Intrinsics.checkNotNull(androidExtensionsBase, "");
                if (androidExtensionsBase.findViewByIdCached(androidExtensionsBase, R.id.msdBottomDocker, DockGroupView.class) == null) {
                    return;
                }
                BaseEditDockerFragment baseEditDockerFragment = editDockerFragment2;
                Intrinsics.checkNotNull(baseEditDockerFragment, "");
                int level = ((DockGroupView) baseEditDockerFragment.findViewByIdCached(baseEditDockerFragment, R.id.msdBottomDocker, DockGroupView.class)).getLevel();
                AndroidExtensionsBase androidExtensionsBase2 = editDockerFragment2;
                Intrinsics.checkNotNull(androidExtensionsBase2, "");
                baseEditDockerFragment.p4(level, ((DockGroupView) androidExtensionsBase2.findViewByIdCached(androidExtensionsBase2, R.id.msdBottomDocker, DockGroupView.class)).getCurrDock(), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), iPanel, null);
            }

            @Override // com.vega.edit.base.dock.IPanelListener
            public final void b(IPanel iPanel) {
                Intrinsics.checkNotNullParameter(iPanel, "");
                AndroidExtensionsBase androidExtensionsBase = editDockerFragment2;
                Intrinsics.checkNotNull(androidExtensionsBase, "");
                if (androidExtensionsBase.findViewByIdCached(androidExtensionsBase, R.id.msdBottomDocker, DockGroupView.class) == null) {
                    return;
                }
                BaseEditDockerFragment baseEditDockerFragment = editDockerFragment2;
                Intrinsics.checkNotNull(baseEditDockerFragment, "");
                int level = ((DockGroupView) baseEditDockerFragment.findViewByIdCached(baseEditDockerFragment, R.id.msdBottomDocker, DockGroupView.class)).getLevel();
                AndroidExtensionsBase androidExtensionsBase2 = editDockerFragment2;
                Intrinsics.checkNotNull(androidExtensionsBase2, "");
                baseEditDockerFragment.p4(level, ((DockGroupView) androidExtensionsBase2.findViewByIdCached(androidExtensionsBase2, R.id.msdBottomDocker, DockGroupView.class)).getCurrDock(), CollectionsKt__CollectionsKt.emptyList(), CollectionsKt__CollectionsKt.emptyList(), null, null);
            }
        });
        if (((EditE2EImportOptConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(EditE2EImportOptConfigSetting.class))).enableDelayInitViewOpt) {
            final EditDockerFragment editDockerFragment3 = (EditDockerFragment) this;
            ((IEditUIViewModel) this.l.getValue()).b().f0(new Function0<Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$attchDockManager$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    editDockerFragment3.M.invoke(dockManager);
                    return Unit.INSTANCE;
                }
            });
        } else {
            this.M.invoke(dockManager);
        }
        if (this.U == null) {
            FragmentActivity activity = getActivity();
            Intrinsics.checkNotNull(activity, "");
            this.U = new VideoAgentDeeplinkController((EditDockerFragment) this, (ViewModelActivity) activity, dockManager);
        }
    }

    public final EditComponentViewModel i4() {
        return (EditComponentViewModel) this.u.getValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0080  */
    @Override // com.vega.commonedit.fragment.AbsEditFragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void initView(android.view.View r15) {
        /*
            r14 = this;
            java.lang.String r3 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r3)
            com.vega.homepage.singlefunc.AbsSingleFunctionEditSharedVM r0 = r14.l4()
            r7 = 0
            r2 = 1
            if (r0 == 0) goto L1e
            boolean r0 = r0.G6()
            if (r0 != r2) goto L1e
            kotlin.Lazy r0 = r14.m
            java.lang.Object r0 = r0.getValue()
            com.vega.edit.video.viewmodel.MainVideoViewModel r0 = (com.vega.edit.video.viewmodel.MainVideoViewModel) r0
            r0.m6()
        L1e:
            androidx.fragment.app.FragmentActivity r0 = r14.getActivity()
            if (r0 == 0) goto L116
            android.content.Intent r4 = INVOKEVIRTUAL_com_vega_edit_editpage_fragment_BaseEditDockerFragment_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r4 == 0) goto L116
            com.vega.homepage.singlefunc.AbsSingleFunctionEditSharedVM r0 = r14.l4()
            if (r0 == 0) goto L1c3
            com.vega.middlebridge.swig.LVVESingleFunctionType r8 = r0.q6()
        L34:
            int[] r1 = com.vega.edit.editpage.fragment.BaseEditDockerFragment.WhenMappings.f91670a
            int r0 = r8.ordinal()
            r0 = r1[r0]
            r9 = 0
            java.lang.String r1 = "deeplink"
            if (r0 != r2) goto L16f
            com.vega.edit.base.viewmodel.EditComponentViewModel r5 = r14.i4()
            r5.getClass()
            com.vega.edit.base.component.model.EnterEditParam$Companion r6 = com.vega.edit.base.component.model.EnterEditParam.f87288c
            java.lang.String r0 = "key_edit_param"
            java.lang.String r0 = r4.getStringExtra(r0)
            if (r0 != 0) goto L53
            r0 = r3
        L53:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r6.getClass()
            com.vega.edit.base.component.model.EnterEditParam r7 = com.vega.edit.base.component.model.EnterEditParam.Companion.a(r0)
            if (r7 == 0) goto L80
            com.vega.edit.base.component.model.PresentParam r0 = r7.f87290a
            java.util.Objects.toString(r0)
            android.os.Bundle r0 = r7.b
            java.util.Objects.toString(r0)
            androidx.lifecycle.MutableLiveData<com.vega.edit.base.component.model.Component> r6 = r5.f89405d
            kotlin.jvm.functions.Function0<com.vega.edit.base.component.model.Component> r0 = com.vega.edit.base.component.model.ComponentsKt.k
            java.lang.Object r0 = r0.invoke()
            r6.setValue(r0)
            com.vega.edit.base.component.model.PresentParam r0 = r7.f87290a
            r5.j = r0
            android.os.Bundle r0 = r7.b
            r5.l = r0
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            if (r0 != 0) goto L83
        L80:
            r5.v6(r4)
        L83:
            java.lang.String r0 = r4.getStringExtra(r1)
            if (r0 == 0) goto Lec
            java.lang.String r0 = com.vega.core.ext.ExtentionKt.takeIfNotEmpty(r0)
            if (r0 == 0) goto Lec
            android.net.Uri r6 = android.net.Uri.parse(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            java.lang.String r0 = "template_id"
            java.lang.String r9 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r9 != 0) goto L9f
            r9 = r3
        L9f:
            java.lang.String r0 = "function"
            java.lang.String r8 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r8 != 0) goto La8
            r8 = r3
        La8:
            java.lang.String r0 = "enter_from"
            java.lang.String r11 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r11 != 0) goto Lb1
            r11 = r3
        Lb1:
            java.lang.String r0 = "resource_id"
            java.lang.String r10 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r10 != 0) goto Lca
            java.lang.String r0 = "effect_id"
            java.lang.String r10 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r10 != 0) goto Lca
            java.lang.String r0 = "music_id"
            java.lang.String r10 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r10 != 0) goto Lca
            r10 = r3
        Lca:
            java.lang.String r0 = "template_feed_edit_card_id"
            java.lang.String r12 = com.vega.edit.base.operation.util.ExtKt.a(r6, r0)
            if (r12 != 0) goto Ld3
            r12 = r3
        Ld3:
            com.vega.report.ReporterInterceptor r6 = com.vega.report.ReporterInterceptor.f129908a
            java.lang.String r0 = "video_inspire"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r0)
            if (r0 == 0) goto L168
            com.vega.edit.base.report.VideoInspireAnchorReportInterceptor r7 = new com.vega.edit.base.report.VideoInspireAnchorReportInterceptor
            r7.<init>(r8, r9, r10, r11)
        Le2:
            r6.getClass()
            io.reactivex.disposables.Disposable r0 = com.vega.report.ReporterInterceptor.a(r7)
            r5.k6(r0)
        Lec:
            java.lang.String r0 = r4.getStringExtra(r1)
            if (r0 == 0) goto Lf8
            int r0 = r0.length()
            if (r0 != 0) goto L114
        Lf8:
            com.vega.core.utils.AppActivityRecorder r0 = com.vega.core.utils.AppActivityRecorder.f79544a
            android.app.Activity r6 = r0.f()
            java.lang.String r9 = "feature_key"
            java.lang.String r8 = r4.getStringExtra(r9)
            java.lang.String r7 = "edit_position"
            java.lang.String r4 = r4.getStringExtra(r7)
            if (r6 == 0) goto L114
            if (r8 == 0) goto L114
            int r0 = r8.length()
            if (r0 != 0) goto L132
        L114:
            r5.m = r2
        L116:
            androidx.fragment.app.FragmentActivity r0 = r14.getActivity()
            if (r0 == 0) goto L131
            androidx.lifecycle.LiveData r3 = com.vega.ui.activity.ActivitySystemBarExtensionsKt.b(r0)
            androidx.lifecycle.LifecycleOwner r2 = r14.getViewLifecycleOwner()
            com.vega.edit.editpage.fragment.BaseEditDockerFragment$initView$2 r1 = new com.vega.edit.editpage.fragment.BaseEditDockerFragment$initView$2
            r1.<init>()
            com.vega.edit.editpage.fragment.BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0 r0 = new com.vega.edit.editpage.fragment.BaseEditDockerFragment$sam$androidx_lifecycle_Observer$0
            r0.<init>(r1)
            r3.observe(r2, r0)
        L131:
            return
        L132:
            if (r4 == 0) goto L114
            int r0 = r4.length()
            if (r0 != 0) goto L13b
            goto L114
        L13b:
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            java.lang.String r0 = "capcut"
            android.net.Uri$Builder r1 = r1.scheme(r0)
            java.lang.String r0 = "//editor/create"
            android.net.Uri$Builder r0 = r1.path(r0)
            android.net.Uri$Builder r0 = r0.appendQueryParameter(r9, r8)
            android.net.Uri$Builder r0 = r0.appendQueryParameter(r7, r4)
            android.net.Uri r0 = r0.build()
            java.lang.String r1 = r0.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            com.vega.report.HybridReporterManager r0 = com.vega.report.HybridReporterManager.f129892a
            r0.getClass()
            com.vega.report.HybridReporterManager.a(r6, r1)
            goto L114
        L168:
            com.vega.edit.base.report.EditTemplateAnchorReportInterceptor r7 = new com.vega.edit.base.report.EditTemplateAnchorReportInterceptor
            r7.<init>(r8, r9, r10, r11, r12)
            goto Le2
        L16f:
            com.vega.edit.base.viewmodel.EditComponentViewModel r3 = r14.i4()
            r3.getClass()
            androidx.lifecycle.MutableLiveData<com.vega.edit.base.component.model.Component> r6 = r3.f89405d
            com.vega.core.context.SPIService r5 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.vega.edit.base.api.IDockHelper> r0 = com.vega.edit.base.api.IDockHelper.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.Object r0 = r5.getImpl(r0, r9)
            com.vega.edit.base.api.IDockHelper r0 = (com.vega.edit.base.api.IDockHelper) r0
            com.vega.edit.base.component.model.ComponentGroup r0 = r0.c(r8)
            r6.setValue(r0)
            java.lang.String r0 = r4.getStringExtra(r1)
            if (r0 == 0) goto L1b0
            android.net.Uri r1 = android.net.Uri.parse(r0)     // Catch: java.lang.Throwable -> L1a5
            r0 = 14
            boolean r0 = com.vega.edit.base.viewmodel.EditComponentViewModel.C6(r3, r1, r7, r9, r0)     // Catch: java.lang.Throwable -> L1a5
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch: java.lang.Throwable -> L1a5
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> L1a5
            goto L1ad
        L1a5:
            r0 = move-exception
            java.lang.Object r0 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r0)
        L1ad:
            kotlin.Result.m17089boximpl(r0)
        L1b0:
            r3.m = r2
            androidx.lifecycle.LifecycleCoroutineScopeImpl r8 = androidx.lifecycle.LifecycleOwnerKt.a(r14)
            com.vega.edit.editpage.fragment.BaseEditDockerFragment$initView$1$1 r11 = new com.vega.edit.editpage.fragment.BaseEditDockerFragment$initView$1$1
            r11.<init>(r14, r9)
            r12 = 3
            r10 = r9
            r13 = r9
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r8, r9, r10, r11, r12, r13)
            goto L116
        L1c3:
            com.vega.middlebridge.swig.LVVESingleFunctionType r8 = com.vega.middlebridge.swig.LVVESingleFunctionType.LVVESingleNone
            goto L34
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.editpage.fragment.BaseEditDockerFragment.initView(android.view.View):void");
    }

    public final DockGroupView j4() {
        Intrinsics.checkNotNull(this, "");
        DockGroupView dockGroupView = (DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class);
        Intrinsics.checkNotNullExpressionValue(dockGroupView, "");
        return dockGroupView;
    }

    public final IEditTrackComponent k4() {
        Object objE = ((EditContainer) this.k.getValue()).e(Reflection.getOrCreateKotlinClass(IEditTrackComponent.class));
        Intrinsics.checkNotNull(objE);
        return (IEditTrackComponent) objE;
    }

    public final AbsSingleFunctionEditSharedVM l4() {
        return (AbsSingleFunctionEditSharedVM) this.G.getValue();
    }

    public final StickerViewModel m4() {
        return (StickerViewModel) this.s.getValue();
    }

    public final EditDockerViewModel n4() {
        return (EditDockerViewModel) this.r.getValue();
    }

    public final boolean o4(IPanel iPanel) {
        if (iPanel instanceof MainVideoAnimVerticalPanel) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("audio_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("sticker_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("text_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("videoEffect_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("subVideo_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("filter_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("adjust_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("palette_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("video_anim_root")) {
            return true;
        }
        Intrinsics.checkNotNull(this, "");
        if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("captions_root")) {
            return true;
        }
        AbsSingleFunctionEditSharedVM absSingleFunctionEditSharedVML4 = l4();
        if (absSingleFunctionEditSharedVML4 != null && absSingleFunctionEditSharedVML4.C6()) {
            Intrinsics.checkNotNull(this, "");
            if (((DockGroupView) findViewByIdCached(this, R.id.msdBottomDocker, DockGroupView.class)).c("single_func_group_root")) {
                return true;
            }
        }
        return false;
    }

    @Override // androidx.fragment.app.Fragment
    public final void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        super.onAttach(context);
        PerformanceLog performanceLog = PerformanceLog.f79700a;
        StageDefine stageDefine = StageDefine.F;
        performanceLog.getClass();
        PerformanceLog.d(stageDefine);
    }

    @Override // com.vega.commonedit.fragment.AbsEditFragment
    public final boolean onBackPressed() {
        DockManager dockManager = this.j;
        return dockManager != null && dockManager.onBackPressed();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:281:0x05ee, code lost:
    
        if (r1.equals("sticker_root") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:288:0x0606, code lost:
    
        if (r1.equals("audio_record") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:294:0x0618, code lost:
    
        if (r1.equals("filter_root") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:297:0x0622, code lost:
    
        if (r1.equals("infoSticker_addSticker") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:303:0x0634, code lost:
    
        if (r1.equals("filter_addAdjust") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:306:0x063e, code lost:
    
        if (r1.equals("infoSticker_addText") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:308:0x0642, code lost:
    
        r14 = "text_sticker";
     */
    /* JADX WARN: Code restructure failed: missing block: B:310:0x064b, code lost:
    
        if (r1.equals("filter_addFilter") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:312:0x064f, code lost:
    
        r14 = "filter";
     */
    /* JADX WARN: Code restructure failed: missing block: B:318:0x0665, code lost:
    
        if (r1.equals("text_to_audio_root") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:321:0x066f, code lost:
    
        if (r1.equals("adjust_root") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:323:0x0673, code lost:
    
        r14 = "adjust";
     */
    /* JADX WARN: Code restructure failed: missing block: B:329:0x0689, code lost:
    
        if (r1.equals("audio_addMusic") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:332:0x0693, code lost:
    
        if (r1.equals("audio_addSound") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:335:0x069d, code lost:
    
        if (r1.equals("audio_root") == false) goto L58;
     */
    /* JADX WARN: Code restructure failed: missing block: B:337:0x06a1, code lost:
    
        r14 = "audio";
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:137:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x02f5  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x032c  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x03a0  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x03e9  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x03f3  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x04c2  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:244:0x04ee  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x053c  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p4(int r31, com.vega.edit.base.dock.DockItemGroup r32, java.util.List<java.lang.String> r33, java.util.List<java.lang.String> r34, com.vega.edit.base.dock.IPanel r35, com.vega.edit.base.dock.DockGroupView.State r36) {
        /*
            r30 = this;
            java.lang.String r9 = ""
            r12 = r33
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r9)
            r11 = r34
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r9)
            r4 = r30
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            r6 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r0 = r4.findViewByIdCached(r4, r6, r0)
            if (r0 != 0) goto L1d
            return
        L1d:
            androidx.fragment.app.FragmentActivity r18 = r4.getActivity()
            r0 = r18
            boolean r0 = r0 instanceof com.vega.infrastructure.vm.ViewModelActivity
            if (r0 == 0) goto L29
            if (r18 != 0) goto L2a
        L29:
            return
        L2a:
            r0 = r32
            if (r0 == 0) goto L768
            com.vega.edit.base.component.model.Component r0 = r0.b
            if (r0 == 0) goto L768
            java.lang.String r2 = r0.d()
        L36:
            kotlin.jvm.functions.Function4<? super com.vega.edit.base.dock.IPanel, ? super com.vega.edit.base.dock.DockGroupView, ? super java.lang.String, ? super com.vega.edit.base.dock.DockGroupView$State, kotlin.Unit> r5 = r4.L
            r3 = r35
            r10 = r36
            if (r5 == 0) goto L50
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            android.view.View r1 = r4.findViewByIdCached(r4, r6, r0)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r9)
            if (r2 != 0) goto L765
            r0 = r9
        L4d:
            r5.invoke(r3, r1, r0, r10)
        L50:
            kotlin.Lazy r0 = r4.F
            java.lang.Object r1 = r0.getValue()
            com.vega.edit.base.viewmodel.IFunctionAssistantViewModel r1 = (com.vega.edit.base.viewmodel.IFunctionAssistantViewModel) r1
            r19 = r31
            r0 = r19
            r1.u6(r0, r3)
            boolean r0 = r3 instanceof com.vega.ui.guide.IGuideEnable
            if (r0 == 0) goto L762
            r0 = r3
        L64:
            r7 = 1
            r5 = 0
            if (r0 == 0) goto L749
            boolean r0 = r0.y0()
            if (r0 != r7) goto L749
        L6e:
            java.lang.String r8 = "video_root"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r8)
            java.lang.String r17 = "root"
            if (r0 == 0) goto L7c
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.b
            if (r10 == r0) goto L88
        L7c:
            r0 = r17
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r0 == 0) goto L94
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.f87425a
            if (r10 != r0) goto L94
        L88:
            kotlin.Lazy r0 = r4.S
            java.lang.Object r1 = r0.getValue()
            com.vega.guideapi.IGuide r1 = (com.vega.guideapi.IGuide) r1
            r0 = 5
            com.vega.guideapi.IGuide.DefaultImpls.a(r1, r5, r5, r0)
        L94:
            kotlin.Lazy r0 = r4.D
            java.lang.Object r0 = r0.getValue()
            com.vega.commonedit.formula.viewmodel.FormulaViewModelV2 r0 = (com.vega.commonedit.formula.viewmodel.FormulaViewModelV2) r0
            androidx.lifecycle.MutableLiveData r0 = r0.f78250c
            java.lang.Object r13 = r0.getValue()
            com.vega.homepage.singlefunc.AbsSingleFunctionEditSharedVM r0 = r4.l4()
            if (r0 == 0) goto L746
            boolean r0 = r0.C6()
            if (r0 != r7) goto L746
            r0 = 1
        Laf:
            java.lang.String r29 = "template_root"
            if (r0 == 0) goto L717
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            android.view.View r1 = r4.findViewByIdCached(r4, r6, r0)
            com.vega.edit.base.dock.DockGroupView r1 = (com.vega.edit.base.dock.DockGroupView) r1
            java.util.Set<java.lang.String> r0 = com.vega.edit.base.component.model.ComponentsKt.f87285g
            boolean r0 = r1.d(r0)
            if (r0 == 0) goto Lca
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.f87425a
            if (r10 != r0) goto Ld2
        Lca:
            com.vega.edit.base.container.track.IEditTrackComponent r1 = r4.k4()
            r0 = 0
            r1.A(r0, r5)
        Ld2:
            com.vega.homepage.singlefunc.AbsSingleFunctionEditSharedVM r0 = r4.l4()
            if (r0 == 0) goto L6e2
            boolean r1 = r0.G6()
            r0 = 1
            if (r1 != r0) goto L6e2
        Ldf:
            com.vega.edit.editpage.viewmodel.EditDockerViewModel r0 = r4.n4()
            androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.vega.edit.base.dock.IPanel>> r1 = r0.e
            kotlin.Pair r0 = new kotlin.Pair
            r0.<init>(r2, r3)
            r1.setValue(r0)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r8)
            java.lang.String r28 = "infoSticker_addSticker"
            java.lang.String r7 = "subVideo_add"
            java.lang.String r27 = "videoEffect_root"
            java.lang.String r26 = "adjust_root"
            java.lang.String r25 = "filter_addFilter"
            java.lang.String r24 = "filter_addAdjust"
            java.lang.String r23 = "filter_root"
            java.lang.String r6 = "sticker_root"
            java.lang.String r5 = "subVideo_root"
            java.lang.String r22 = "audio_root"
            java.lang.String r14 = "cut"
            java.lang.String r15 = "base"
            java.lang.String r13 = "pip"
            if (r0 == 0) goto L6b3
            java.lang.String r1 = r4.I
            if (r1 == 0) goto L118
            int r0 = r1.hashCode()
            switch(r0) {
                case -2031063388: goto L5ea;
                case -1872667799: goto L5f2;
                case -1477447462: goto L600;
                case -1090812794: goto L60a;
                case -889988791: goto L612;
                case -883751188: goto L61c;
                case -866488195: goto L626;
                case -156455031: goto L62e;
                case -155861538: goto L638;
                case -8633486: goto L645;
                case 3506402: goto L652;
                case 819303517: goto L65f;
                case 1147560850: goto L669;
                case 1157847893: goto L676;
                case 1353992269: goto L683;
                case 1359356727: goto L68d;
                case 1549308843: goto L697;
                case 2113091734: goto L6a4;
                default: goto L118;
            }
        L118:
            r13 = r9
        L119:
            int r0 = r13.length()
            if (r0 <= 0) goto L129
            com.vega.edit.base.utils.EditReportManager r0 = com.vega.edit.base.utils.EditReportManager.f88945a
            r0.getClass()
            java.lang.String r0 = "main"
            com.vega.edit.base.utils.EditReportManager.J0(r13, r0)
        L129:
            boolean r0 = r3 instanceof com.vega.audio.view.panel.AudioBeatPanel
            if (r0 == 0) goto L13b
            kotlin.Lazy r0 = r4.S
            java.lang.Object r14 = r0.getValue()
            com.vega.guideapi.IGuide r14 = (com.vega.guideapi.IGuide) r14
            r13 = 4
            r1 = 1
            r0 = 0
            com.vega.guideapi.IGuide.DefaultImpls.a(r14, r1, r0, r13)
        L13b:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r1 = com.vega.edit.base.dock.DockGroupView.class
            r0 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r0 = r4.findViewByIdCached(r4, r0, r1)
            com.vega.edit.base.dock.DockGroupView r0 = (com.vega.edit.base.dock.DockGroupView) r0
            java.lang.String r13 = "text_root"
            boolean r0 = r0.c(r13)
            java.lang.String r1 = "captions_root"
            if (r0 != 0) goto L17c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            r14 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r0 = r4.findViewByIdCached(r4, r14, r0)
            com.vega.edit.base.dock.DockGroupView r0 = (com.vega.edit.base.dock.DockGroupView) r0
            boolean r0 = r0.c(r6)
            if (r0 != 0) goto L17c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            android.view.View r0 = r4.findViewByIdCached(r4, r14, r0)
            com.vega.edit.base.dock.DockGroupView r0 = (com.vega.edit.base.dock.DockGroupView) r0
            boolean r0 = r0.c(r1)
            if (r0 != 0) goto L17c
            boolean r0 = r3 instanceof com.vega.audio.view.panel.AudioRecordPanel
            if (r0 == 0) goto L5e0
        L17c:
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 4
            r14.e(r0)
        L184:
            r0 = r17
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r0 != 0) goto L192
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r8)
            if (r0 == 0) goto L197
        L192:
            r14 = 2
            r0 = r19
            if (r0 < r14) goto L1ad
        L197:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r14 = com.vega.edit.base.dock.DockGroupView.class
            r0 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r14 = r4.findViewByIdCached(r4, r0, r14)
            com.vega.edit.base.dock.DockGroupView r14 = (com.vega.edit.base.dock.DockGroupView) r14
            r0 = r22
            boolean r0 = r14.c(r0)
            if (r0 == 0) goto L5cc
        L1ad:
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 8
            r14.p(r0)
        L1b6:
            r0 = r17
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r0 != 0) goto L1c4
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r8)
            if (r0 == 0) goto L562
        L1c4:
            r14 = 2
            r0 = r19
            if (r0 >= r14) goto L562
            com.vega.edit.base.viewmodel.EditComponentViewModel r0 = r4.i4()
            boolean r0 = r0.y6()
            if (r0 == 0) goto L55f
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r14 = 0
            r0.W(r14)
        L1db:
            com.vega.edit.base.viewmodel.EditComponentViewModel r0 = r4.i4()
            boolean r0 = r0.x6(r7)
            if (r0 == 0) goto L1f4
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.g(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 1
            r14.t(r0)
        L1f4:
            com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting$Companion r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion
            r0.getClass()
            boolean r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion.a()
            if (r0 == 0) goto L21c
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r14 = 0
            r0.D(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.n(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.J(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.j(r14)
        L21c:
            boolean r0 = r3 instanceof com.vega.edit.gameplay.view.panel.MainVideoRemoteEffectPanel
            if (r0 != 0) goto L230
            boolean r0 = r3 instanceof com.vega.edit.gameplay.view.panel.SubVideoRemoteEffectPanel
            if (r0 != 0) goto L230
            boolean r0 = r3 instanceof com.vega.edit.gameplay.view.panel.GlobalVideoGameplayAdjustPanel
            if (r0 != 0) goto L230
            boolean r0 = r3 instanceof com.vega.edit.gameplay.view.panel.MainVideoGameplayAdjustPanel
            if (r0 != 0) goto L230
            boolean r0 = r3 instanceof com.vega.edit.gameplay.view.panel.SubVideoGameplayAdjustPanel
            if (r0 == 0) goto L268
        L230:
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 0
            r14.p(r0)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r14 = 8
            r0.W(r14)
            com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting$Companion r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion
            r0.getClass()
            boolean r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion.a()
            if (r0 == 0) goto L268
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.D(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.n(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.J(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.j(r14)
        L268:
            boolean r0 = r3 instanceof com.vega.edit.base.dock.TopTrackVisibilityPolicy
            if (r0 == 0) goto L55c
            r15 = r3
            com.vega.edit.base.dock.TopTrackVisibilityPolicy r15 = (com.vega.edit.base.dock.TopTrackVisibilityPolicy) r15
            if (r15 == 0) goto L27e
            r15.B()
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r14 = 8
            r0 = r0
            r0.W(r14)
        L27e:
            com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting$Companion r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion
            r0.getClass()
            boolean r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion.a()
            if (r0 == 0) goto L297
            if (r15 == 0) goto L297
            r15.a()
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 8
            r14.D(r0)
        L297:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r9)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r9)
            r0 = r22
            boolean r0 = r11.contains(r0)
            java.lang.String r21 = "formula_root"
            java.lang.String r20 = "palette_root"
            if (r0 == 0) goto L4a5
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.f87323a
        L2ab:
            if (r0 == 0) goto L2b5
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r11 = 0
            r14.b(r11, r0)
        L2b5:
            if (r10 == 0) goto L2bb
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.f87425a
            if (r10 != r0) goto L3d0
        L2bb:
            java.lang.String r1 = r4.I
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r0 != 0) goto L2da
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r8)
            if (r0 != 0) goto L2cf
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r7)
            if (r0 == 0) goto L2da
        L2cf:
            com.vega.edit.editpage.viewmodel.EditDockerViewModel r0 = r4.n4()
            com.vega.core.viewmodel.NoneTypeLiveEvent r1 = r0.h
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r1.g(r0)
        L2da:
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L2f1
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "dockManager onChanged: "
            r1.<init>(r0)
            r1.append(r3)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "EditDockerFragment"
            com.vega.log.BLog.i(r0, r1)
        L2f1:
            com.vega.edit.dock.DockManager r0 = r4.j
            if (r0 == 0) goto L3cd
            com.vega.edit.base.dock.IPanel r0 = r0.W()
        L2f9:
            boolean r0 = r0 instanceof com.vega.libsticker.view.panel.StickerPanel
            if (r0 == 0) goto L315
            if (r3 != 0) goto L315
            com.vega.libsticker.viewmodel.StickerViewModel r0 = r4.m4()
            kotlinx.coroutines.Job r0 = r0.R0
            if (r0 == 0) goto L315
            boolean r0 = r0.isActive()
            r1 = 1
            if (r0 != r1) goto L315
            com.vega.libsticker.viewmodel.StickerViewModel r0 = r4.m4()
            r0.K6(r1)
        L315:
            boolean r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion.a()
            if (r0 == 0) goto L322
            if (r19 == 0) goto L3c8
            r1 = 1
            r0 = r19
            if (r0 == r1) goto L3c4
        L322:
            r4.I = r2
            boolean r0 = r3 instanceof com.vega.libsticker.view.panel.TextPanel
            if (r0 != 0) goto L32c
            boolean r0 = r3 instanceof com.vega.libsticker.view.newtextpanelv2.NewTextPanelV2
            if (r0 == 0) goto L337
        L32c:
            com.vega.edit.editpage.viewmodel.EditDockerViewModel r0 = r4.n4()
            com.vega.core.viewmodel.NoneTypeLiveEvent r1 = r0.j
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            r1.g(r0)
        L337:
            boolean r0 = r3 instanceof com.vega.edit.transition.view.NewVideoTransitionPanel
            if (r0 == 0) goto L3a0
            com.vega.libsticker.viewmodel.StickerViewModel r0 = r4.m4()
            com.vega.container.session.core.ISession r6 = r0.t
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r9)
            com.vega.core.context.SPIService r1 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.lemon.lv.editor.EditorProxyModule> r0 = com.lemon.lv.editor.EditorProxyModule.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            r5 = 0
            java.lang.Object r0 = r1.getImpl(r0, r5)
            com.lemon.lv.editor.EditorProxyModule r0 = (com.lemon.lv.editor.EditorProxyModule) r0
            com.lemon.lv.editor.proxy.IFeelGood r1 = r0.l()
            r0 = r18
            r1.onEventShowMaterialSuccess(r0)
            com.vega.report.ReportManagerWrapper r1 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r0 = "click_edit_transitions"
            r1.onEvent(r0)
            com.vega.edit.base.utils.FeelGoodReportHelper r1 = com.vega.edit.base.utils.FeelGoodReportHelper.f88997a
            com.vega.middlebridge.swig.Draft r0 = r6.l()
            if (r0 == 0) goto L36f
            java.lang.String r5 = r0.b()
        L36f:
            r1.getClass()
            java.lang.String r1 = "transition_state"
            java.lang.String r0 = "click"
            com.vega.edit.base.utils.FeelGoodReportHelper.c(r5, r1, r0)
        L379:
            r0 = r17
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r0 == 0) goto L38b
            android.content.Context r1 = r4.getContext()
            if (r1 == 0) goto L38b
            r0 = -1
            com.vega.libsticker.utils.StickerUtilsKt.i(r0, r1)
        L38b:
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.c()
            com.vega.edit.editpage.viewmodel.EditDockerViewModel r0 = r4.n4()
            androidx.lifecycle.MutableLiveData<kotlin.Pair<java.lang.String, com.vega.edit.base.dock.IPanel>> r1 = r0.k
            kotlin.Pair r0 = kotlin.TuplesKt.to(r2, r3)
            r1.setValue(r0)
            return
        L3a0:
            boolean r0 = r3 instanceof com.vega.edit.transition.ai.view.AITransitionStandalonePanel
            if (r0 == 0) goto L379
            java.util.LinkedHashMap r10 = new java.util.LinkedHashMap
            r10.<init>()
            java.lang.String r1 = "enter_source"
            java.lang.String r0 = "standalone_panel"
            r10.put(r1, r0)
            com.vega.subscriptionapi.report.BusinessReporter r5 = com.vega.subscriptionapi.report.BusinessReporter.f131850a
            java.lang.String r6 = "entrance_click"
            java.lang.String r7 = "ai_transition"
            r8 = 0
            com.vega.edit.base.utils.EditReportManager r0 = com.vega.edit.base.utils.EditReportManager.f88945a
            r0.getClass()
            java.lang.String r9 = com.vega.edit.base.utils.EditReportManager.f88947d
            r11 = 8
            com.vega.subscriptionapi.report.BusinessReporter.q(r5, r6, r7, r8, r9, r10, r11)
            goto L379
        L3c4:
            r4.f91647J = r2
            goto L322
        L3c8:
            r0 = 0
            r4.f91647J = r0
            goto L322
        L3cd:
            r0 = 0
            goto L2f9
        L3d0:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r12, r9)
            r0 = r22
            boolean r0 = r12.contains(r0)
            if (r0 == 0) goto L3f3
            com.vega.edit.base.viewmodel.EditComponentViewModel r0 = r4.i4()
            boolean r0 = r0.y6()
            if (r0 == 0) goto L2bb
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.f87323a
        L3e7:
            if (r5 == 0) goto L2bb
            com.vega.edit.base.container.track.IEditTrackComponent r1 = r4.k4()
            r0 = 1
            r1.b(r0, r5)
            goto L2bb
        L3f3:
            boolean r0 = r12.contains(r6)
            if (r0 != 0) goto L410
            boolean r0 = r12.contains(r13)
            if (r0 != 0) goto L410
            boolean r0 = r12.contains(r1)
            if (r0 == 0) goto L41f
            com.vega.edit.base.caption.CaptionsDockAbConfig r0 = com.vega.edit.base.caption.CaptionsDockAbConfig.f87133a
            r0.getClass()
            boolean r0 = com.vega.edit.base.caption.CaptionsDockAbConfig.b()
            if (r0 == 0) goto L41f
        L410:
            com.vega.edit.base.viewmodel.EditComponentViewModel r1 = r4.i4()
            r0 = r28
            boolean r0 = r1.x6(r0)
            if (r0 == 0) goto L2bb
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.b
            goto L3e7
        L41f:
            r0 = r27
            boolean r0 = r12.contains(r0)
            if (r0 == 0) goto L43e
            com.vega.edit.base.viewmodel.EditComponentViewModel r1 = r4.i4()
            r0 = r27
            boolean r0 = r1.x6(r0)
            if (r0 != 0) goto L43b
            java.lang.String r0 = "videoEffect_addEffect"
            boolean r0 = r1.x6(r0)
            if (r0 == 0) goto L2bb
        L43b:
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.f87324c
            goto L3e7
        L43e:
            boolean r0 = r12.contains(r5)
            if (r0 == 0) goto L451
            com.vega.edit.base.viewmodel.EditComponentViewModel r0 = r4.i4()
            boolean r0 = r0.x6(r7)
            if (r0 == 0) goto L2bb
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.f87325d
            goto L3e7
        L451:
            r0 = r23
            boolean r0 = r12.contains(r0)
            if (r0 != 0) goto L469
            r0 = r26
            boolean r0 = r12.contains(r0)
            if (r0 != 0) goto L469
            r0 = r20
            boolean r0 = r12.contains(r0)
            if (r0 == 0) goto L481
        L469:
            com.vega.edit.base.viewmodel.EditComponentViewModel r1 = r4.i4()
            r0 = r25
            boolean r0 = r1.x6(r0)
            if (r0 != 0) goto L47d
            r0 = r24
            boolean r0 = r1.x6(r0)
            if (r0 == 0) goto L2bb
        L47d:
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.e
            goto L3e7
        L481:
            r0 = r29
            boolean r0 = r12.contains(r0)
            if (r0 != 0) goto L491
            r0 = r21
            boolean r0 = r12.contains(r0)
            if (r0 == 0) goto L495
        L491:
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.f
            goto L3e7
        L495:
            boolean r0 = r3 instanceof com.vega.libsticker.dock.StickerRenderIndexPanel
            if (r0 == 0) goto L49d
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.f87325d
            goto L3e7
        L49d:
            boolean r0 = r3 instanceof com.vega.edit.muxer.view.panel.SubVideoRenderIndexPanel
            if (r0 == 0) goto L2bb
            com.vega.edit.base.container.track.TrackUIType r5 = com.vega.edit.base.container.track.TrackUIType.b
            goto L3e7
        L4a5:
            boolean r0 = r11.contains(r6)
            if (r0 != 0) goto L4c2
            boolean r0 = r11.contains(r13)
            if (r0 != 0) goto L4c2
            boolean r0 = r11.contains(r1)
            if (r0 == 0) goto L4e2
            com.vega.edit.base.caption.CaptionsDockAbConfig r0 = com.vega.edit.base.caption.CaptionsDockAbConfig.f87133a
            r0.getClass()
            boolean r0 = com.vega.edit.base.caption.CaptionsDockAbConfig.b()
            if (r0 == 0) goto L4e2
        L4c2:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r14 = com.vega.edit.base.dock.DockGroupView.class
            r0 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r14 = r4.findViewByIdCached(r4, r0, r14)
            com.vega.edit.base.dock.DockGroupView r14 = (com.vega.edit.base.dock.DockGroupView) r14
            java.lang.String[] r0 = new java.lang.String[]{r6, r13, r1}
            java.util.Set r0 = kotlin.collections.SetsKt__SetsKt.setOf(r0)
            boolean r0 = r14.d(r0)
            if (r0 != 0) goto L4e2
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.b
            goto L2ab
        L4e2:
            r0 = r27
            boolean r0 = r11.contains(r0)
            if (r0 == 0) goto L4ee
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.f87324c
            goto L2ab
        L4ee:
            boolean r0 = r11.contains(r5)
            if (r0 == 0) goto L4f8
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.f87325d
            goto L2ab
        L4f8:
            r0 = r26
            boolean r0 = r11.contains(r0)
            if (r0 != 0) goto L510
            r0 = r23
            boolean r0 = r11.contains(r0)
            if (r0 != 0) goto L510
            r0 = r20
            boolean r0 = r11.contains(r0)
            if (r0 == 0) goto L53c
        L510:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r14 = com.vega.edit.base.dock.DockGroupView.class
            r0 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r16 = r4.findViewByIdCached(r4, r0, r14)
            r0 = r16
            com.vega.edit.base.dock.DockGroupView r0 = (com.vega.edit.base.dock.DockGroupView) r0
            r16 = r0
            r15 = r23
            r14 = r26
            r0 = r20
            java.lang.String[] r0 = new java.lang.String[]{r14, r15, r0}
            java.util.Set r14 = kotlin.collections.SetsKt__SetsKt.setOf(r0)
            r0 = r16
            boolean r0 = r0.d(r14)
            if (r0 != 0) goto L53c
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.e
            goto L2ab
        L53c:
            r0 = r29
            boolean r0 = r11.contains(r0)
            if (r0 != 0) goto L54c
            r0 = r21
            boolean r0 = r11.contains(r0)
            if (r0 == 0) goto L550
        L54c:
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.f
            goto L2ab
        L550:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r7)
            if (r0 == 0) goto L2b5
            if (r3 != 0) goto L2b5
            com.vega.edit.base.container.track.TrackUIType r0 = com.vega.edit.base.container.track.TrackUIType.b
            goto L2ab
        L55c:
            r15 = 0
            goto L27e
        L55f:
            r14 = 0
            goto L1db
        L562:
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r14 = 8
            r0.W(r14)
            com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting$Companion r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion
            r0.getClass()
            boolean r0 = com.vega.edit.base.multitrack.EditTrackOptimizeConfigSetting.Companion.a()
            if (r0 == 0) goto L592
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.D(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.n(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.J(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.j(r14)
        L592:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r14 = com.vega.edit.base.dock.DockGroupView.class
            r0 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r0 = r4.findViewByIdCached(r4, r0, r14)
            com.vega.edit.base.dock.DockGroupView r0 = (com.vega.edit.base.dock.DockGroupView) r0
            boolean r0 = r0.c(r5)
            if (r0 == 0) goto L5b1
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 8
            r14.g(r0)
            goto L21c
        L5b1:
            com.vega.edit.base.viewmodel.EditComponentViewModel r0 = r4.i4()
            boolean r0 = r0.x6(r7)
            if (r0 == 0) goto L21c
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r14 = 0
            r0.g(r14)
            com.vega.edit.base.container.track.IEditTrackComponent r0 = r4.k4()
            r0.t(r14)
            goto L21c
        L5cc:
            com.vega.edit.base.viewmodel.EditComponentViewModel r0 = r4.i4()
            boolean r0 = r0.y6()
            if (r0 == 0) goto L1b6
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 0
            r14.p(r0)
            goto L1b6
        L5e0:
            com.vega.edit.base.container.track.IEditTrackComponent r14 = r4.k4()
            r0 = 0
            r14.e(r0)
            goto L184
        L5ea:
            boolean r0 = r1.equals(r6)
            if (r0 != 0) goto L642
            goto L118
        L5f2:
            java.lang.String r0 = "canvas_root"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L5fc
            goto L118
        L5fc:
            java.lang.String r14 = "canvas_background"
            goto L6b0
        L600:
            java.lang.String r0 = "audio_record"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L6a1
            goto L118
        L60a:
            boolean r0 = r1.equals(r5)
            if (r0 != 0) goto L119
            goto L118
        L612:
            r0 = r23
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L64f
            goto L118
        L61c:
            r0 = r28
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L642
            goto L118
        L626:
            boolean r0 = r1.equals(r7)
            if (r0 != 0) goto L6b0
            goto L118
        L62e:
            r0 = r24
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L673
            goto L118
        L638:
            java.lang.String r0 = "infoSticker_addText"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L642
            goto L118
        L642:
            java.lang.String r14 = "text_sticker"
            goto L6b0
        L645:
            r0 = r25
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L64f
            goto L118
        L64f:
            java.lang.String r14 = "filter"
            goto L6b0
        L652:
            r0 = r17
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L65c
            goto L118
        L65c:
            r13 = r15
            goto L119
        L65f:
            java.lang.String r0 = "text_to_audio_root"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L6a1
            goto L118
        L669:
            r0 = r26
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L673
            goto L118
        L673:
            java.lang.String r14 = "adjust"
            goto L6b0
        L676:
            r0 = r27
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L680
            goto L118
        L680:
            java.lang.String r14 = "special_effect"
            goto L6b0
        L683:
            java.lang.String r0 = "audio_addMusic"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L6a1
            goto L118
        L68d:
            java.lang.String r0 = "audio_addSound"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L6a1
            goto L118
        L697:
            r0 = r22
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L6a1
            goto L118
        L6a1:
            java.lang.String r14 = "audio"
            goto L6b0
        L6a4:
            java.lang.String r0 = "ratio_root"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L6ae
            goto L118
        L6ae:
            java.lang.String r14 = "canvas_scale"
        L6b0:
            r13 = r14
            goto L119
        L6b3:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r7)
            if (r0 == 0) goto L129
            if (r10 == 0) goto L129
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.f87425a
            if (r10 == r0) goto L129
            java.lang.String r1 = r4.I
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r8)
            if (r0 == 0) goto L6d1
        L6c7:
            com.vega.edit.base.utils.EditReportManager r0 = com.vega.edit.base.utils.EditReportManager.f88945a
            r0.getClass()
            com.vega.edit.base.utils.EditReportManager.J0(r14, r13)
            goto L129
        L6d1:
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r7)
            if (r0 == 0) goto L6de
            boolean r0 = r4.K
            if (r0 == 0) goto L6e0
            r0 = 0
            r4.K = r0
        L6de:
            r14 = r15
            goto L6c7
        L6e0:
            r14 = r13
            goto L6c7
        L6e2:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            r1 = 2131306371(0x7f092783, float:1.823094E38)
            android.view.View r0 = r4.findViewByIdCached(r4, r1, r0)
            com.vega.edit.base.dock.DockGroupView r0 = (com.vega.edit.base.dock.DockGroupView) r0
            boolean r0 = r0.c(r8)
            if (r0 != 0) goto Ldf
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            android.view.View r1 = r4.findViewByIdCached(r4, r1, r0)
            com.vega.edit.base.dock.DockGroupView r1 = (com.vega.edit.base.dock.DockGroupView) r1
            r0 = r29
            boolean r0 = r1.c(r0)
            if (r0 != 0) goto Ldf
            kotlin.Lazy r0 = r4.m
            java.lang.Object r1 = r0.getValue()
            com.vega.edit.video.viewmodel.MainVideoViewModel r1 = (com.vega.edit.video.viewmodel.MainVideoViewModel) r1
            r0 = 0
            r1.K6(r0, r5)
            goto Ldf
        L717:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r9)
            java.lang.Class<com.vega.edit.base.dock.DockGroupView> r0 = com.vega.edit.base.dock.DockGroupView.class
            android.view.View r1 = r4.findViewByIdCached(r4, r6, r0)
            com.vega.edit.base.dock.DockGroupView r1 = (com.vega.edit.base.dock.DockGroupView) r1
            java.util.Set<java.lang.String> r0 = com.vega.edit.base.component.model.ComponentsKt.f
            boolean r0 = r1.d(r0)
            if (r0 == 0) goto L73c
            if (r13 == 0) goto L730
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.f87425a
            if (r10 == r0) goto L73c
        L730:
            com.vega.edit.base.dock.DockGroupView$State r0 = com.vega.edit.base.dock.DockGroupView.State.f87426c
            if (r10 != r0) goto Ld2
            r0 = r29
            boolean r0 = r12.contains(r0)
            if (r0 == 0) goto Ld2
        L73c:
            com.vega.edit.base.container.track.IEditTrackComponent r1 = r4.k4()
            r0 = 0
            r1.A(r0, r5)
            goto Ld2
        L746:
            r0 = 0
            goto Laf
        L749:
            r0 = 0
            r1 = 4
            if (r0 != 0) goto L6e
            if (r3 == 0) goto L75e
            r4.R = r7
            kotlin.Lazy r0 = r4.S
            java.lang.Object r0 = r0.getValue()
            com.vega.guideapi.IGuide r0 = (com.vega.guideapi.IGuide) r0
            com.vega.guideapi.IGuide.DefaultImpls.a(r0, r7, r5, r1)
            goto L6e
        L75e:
            r4.R = r5
            goto L6e
        L762:
            r0 = 0
            goto L64
        L765:
            r0 = r2
            goto L4d
        L768:
            r2 = 0
            goto L36
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.editpage.fragment.BaseEditDockerFragment.p4(int, com.vega.edit.base.dock.DockItemGroup, java.util.List, java.util.List, com.vega.edit.base.dock.IPanel, com.vega.edit.base.dock.DockGroupView$State):void");
    }

    public void q4(String str) {
        String strTakeIfNotEmpty;
        DockManager dockManager;
        final CommonDockManager commonDockManager;
        Intrinsics.checkNotNullParameter(str, "");
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$onProjectPrepared$draftLoadTask$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                BaseEditDockerFragment baseEditDockerFragment = this.e;
                DockManager dockManager2 = baseEditDockerFragment.j;
                if (dockManager2 == null) {
                    return null;
                }
                baseEditDockerFragment.i4().A6(dockManager2);
                return Unit.INSTANCE;
            }
        };
        EditCommonParams editCommonParams = null;
        if (((EditE2EImportOptConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(EditE2EImportOptConfigSetting.class))).enableDelayInitViewOpt) {
            DockManager dockManager2 = this.j;
            if (dockManager2 != null && (commonDockManager = dockManager2.f91091d) != null) {
                ((LiteStateMachine) commonDockManager.w.getValue()).a(new Function0<Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$onProjectPrepared$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        ISession iSession = commonDockManager.v;
                        if (iSession != null) {
                            final Function0<Unit> function02 = function0;
                            iSession.f0(new Function0<Unit>() { // from class: com.vega.edit.editpage.fragment.BaseEditDockerFragment$onProjectPrepared$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                @Override // kotlin.jvm.functions.Function0
                                public final Unit invoke() {
                                    function02.invoke();
                                    return Unit.INSTANCE;
                                }
                            });
                        }
                        return Unit.INSTANCE;
                    }
                });
            }
        } else {
            function0.invoke();
        }
        ICutsameProxy iCutsameProxyM = ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).m();
        EditCommonParams editCommonParams2 = this.O;
        if (editCommonParams2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("editCommonParams");
        } else {
            editCommonParams = editCommonParams2;
        }
        String strE = iCutsameProxyM.e(editCommonParams.c());
        if (strE != null && (strTakeIfNotEmpty = ExtentionKt.takeIfNotEmpty(strE)) != null && ExtentionKt.isNotNullOrEmpty(str) && (dockManager = this.j) != null) {
            CommonDockManager commonDockManager2 = dockManager.f91091d;
            commonDockManager2.getClass();
            DockRedDotManager dockRedDotManager = commonDockManager2.o;
            if (dockRedDotManager != null) {
                dockRedDotManager.a(strTakeIfNotEmpty);
                commonDockManager2.I1();
            }
        }
        ((FigureResourceViewModel) this.E.getValue()).p6();
        ((PanelColumnCountConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(OverseaPanelColumnCountConfig.class))).a();
        PerformanceLog performanceLog = PerformanceLog.f79700a;
        StageDefine stageDefine = StageDefine.F;
        performanceLog.getClass();
        PerformanceLog.c(stageDefine);
    }

    public final void r4(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        DockManager dockManager = this.j;
        if (dockManager != null) {
            dockManager.W5(str);
        }
    }

    public final void s4(IPanel iPanel) {
        Intrinsics.checkNotNullParameter(iPanel, "");
        DockManager dockManager = this.j;
        if (dockManager != null) {
            dockManager.W7(iPanel, true);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final String toString() {
        return "EditDockerFragment";
    }
}
package com.vega.edit.editpage.controller;

import X.C0NI;
import X.C111833mm;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.bytedance.bdturing.EventReport;
import com.bytedance.common.utility.io.IOUtils;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.bytedance.sysoptimizer.BadParcelableCrashOptimizer;
import com.kanyun.kace.AndroidExtensionsBase;
import com.lemon.lv.ScanLogsUtil;
import com.lemon.lv.clipmonetize.wrapper.BenefitResource;
import com.lemon.lv.config.AdMakerAIGCWaterMarkConfig;
import com.lemon.lv.config.BaseClientSetting;
import com.lemon.lv.config.ClientSetting;
import com.lemon.lv.config.ExportOptimizeConfig;
import com.lemon.lv.database.entity.ProjectSnapshot;
import com.lemon.lv.editor.EditorProxyModule;
import com.lemon.lv.editor.proxy.IFeedProxy;
import com.lemon.lvoverseas.R;
import com.lm.components.permission.PermissionRequest;
import com.lm.components.permission.PermissionResult;
import com.lm.components.permission.PermissionUtil;
import com.vega.commonedit.activity.AbsEditActivity;
import com.vega.commonedit.viewmodel.ResolutionViewModel;
import com.vega.config.ConfigSettingsKt;
import com.vega.config.EditMaterialLostABSettings;
import com.vega.config.EditMaterialLostConfig;
import com.vega.container.EditContainer;
import com.vega.container.session.core.ISession;
import com.vega.container.topbar.IEditTopBarComponent;
import com.vega.core.context.SPIService;
import com.vega.core.ext.BundleExKt;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.utils.FileUtilWithAndroid11Kt;
import com.vega.core.utils.FunctionsKt;
import com.vega.core.utils.ImageUtilKt;
import com.vega.core.utils.StorageUtil;
import com.vega.deeplinkapi.DeepLinkDataProvider;
import com.vega.deeplinkapi.data.MainTabbarDeepLinkData;
import com.vega.edit.aigenerator.viewmodel.EditAIPaintingViewModelV2;
import com.vega.edit.base.container.track.IEditTrackComponent;
import com.vega.edit.base.container.track.ITrackComponent;
import com.vega.edit.base.dock.IDockManager;
import com.vega.edit.base.dock.IPanel;
import com.vega.edit.base.model.ReportPromptExportData;
import com.vega.edit.base.operation.util.DraftLogUtils;
import com.vega.edit.base.utils.DigitalHumanUtils;
import com.vega.edit.base.utils.EditReportManager;
import com.vega.edit.base.utils.ExportDigitalHumanNum;
import com.vega.edit.base.viewmodel.EditBusinessViewModel;
import com.vega.edit.base.viewmodel.ExportDraftZipState;
import com.vega.edit.base.viewmodel.IEditPerformanceViewModel;
import com.vega.edit.base.viewmodel.IEditUIViewModel;
import com.vega.edit.base.viewmodel.PlayPositionState;
import com.vega.edit.base.viewmodel.ReportViewModel;
import com.vega.edit.base.viewmodel.VarHeightViewModel;
import com.vega.edit.base.watermark.model.WatermarkExperimentGroup;
import com.vega.edit.base.watermark.viewmodel.WaterMarkViewModel;
import com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController;
import com.vega.edit.editpage.controller.export_refactor.intercept.ExportInnterceptPrority;
import com.vega.edit.editpage.controller.export_refactor.intercept.base.IExportInterceptor;
import com.vega.edit.editpage.viewmodel.EditPreviewViewModel;
import com.vega.edit.editpage.viewmodel.EditViewModel;
import com.vega.edit.figure.LVFigureGlobalInjectModule;
import com.vega.edit.figure.model.panel.BaseManualFigureViewModel;
import com.vega.edit.figure.model.panel.MainVideoManualFigureViewModel;
import com.vega.edit.figure.model.panel.SubVideoManualFigureViewModel;
import com.vega.edit.figure.view.panel.auto.MainVideoBeautyPanel;
import com.vega.edit.figure.view.panel.auto.SubVideoBeautyPanel;
import com.vega.edit.inpainting.viewmodel.MainVideoInPaintingViewModel;
import com.vega.edit.inpainting.viewmodel.SubVideoInPaintingViewModel;
import com.vega.edit.locate.LocatorDispatcher;
import com.vega.edit.matting.MainVideoCustomizedMattingPanel;
import com.vega.edit.matting.SubVideoCustomizedMattingPanel;
import com.vega.edit.matting.view.VideoMattingPanel;
import com.vega.edit.matting.viewmodel.MainVideoMattingViewModel;
import com.vega.edit.matting.viewmodel.SubVideoMattingViewModel;
import com.vega.edit.matting.viewmodel.VideoMattingViewModel;
import com.vega.edit.smartcrop.view.MainVideoSmartCropPanel;
import com.vega.edit.smartcrop.view.SubVideoSmartCropPanel;
import com.vega.edit.stable.viewmodel.MainVideoStableViewModel;
import com.vega.edit.stable.viewmodel.SubVideoStableViewModel;
import com.vega.edit.utils.EditMaterialLostUtil;
import com.vega.edit.video.viewmodel.MainVideoViewModel;
import com.vega.edit.video.viewmodel.MainVideoViewModelExKt;
import com.vega.edit.videoeffect.viewmodel.VideoEffectViewModel;
import com.vega.edit.viewmodel.EditUIViewModel;
import com.vega.feedx.main.bean.UgCampaignParams;
import com.vega.guideapi.IGuide;
import com.vega.homepage.singlefunc.AbsSingleFunctionEditSharedVM;
import com.vega.homepage.singlefunc.SingleFunctionService;
import com.vega.image.IImageEditService;
import com.vega.infrastructure.base.ModuleCommonKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.koin.ScopeProxy;
import com.vega.libfiles.files.BaseFileAbility;
import com.vega.libfiles.files.FileScavenger;
import com.vega.libsticker.handwrite.HandwritePanel;
import com.vega.libsticker.handwrite.HandwriteViewModel;
import com.vega.libsticker.view.newtextpanelv2.NewTextPanelV2;
import com.vega.libsticker.view.panel.StickerPanel;
import com.vega.libsticker.view.panel.TextPanel;
import com.vega.libsticker.viewmodel.ReportAudioToTextResult;
import com.vega.libsticker.viewmodel.StickerViewModel;
import com.vega.libsticker.viewmodel.SubtitleViewModel;
import com.vega.log.BLog;
import com.vega.middlebridge.swig.Draft;
import com.vega.middlebridge.swig.LVVESingleFunctionType;
import com.vega.middlebridge.swig.SegmentVideo;
import com.vega.operation.data.AdDraftExtraInfo;
import com.vega.performance.BadParcelableExceptionOpt;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.report.ReportManagerWrapper;
import com.vega.subscription.p008const.BusinessResourcesKt;
import com.vega.subscriptionapi.biz.function.IVipExportBusinessFunction;
import com.vega.subscriptionapi.legacy.attachment.IBusiness;
import com.vega.subscriptionapi.widget.VipExportLoading;
import com.vega.textaihuman.render.DigitalHumanRenderService;
import com.vega.textaihuman.render.DigitalHumanRenderServiceManager;
import com.vega.ui.AlphaTextButton;
import com.vega.ui.dialog.ConfirmCloseDialog;
import com.vega.ui.dialog.LvProgressDialog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import org.json.JSONException;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes32.dex */
public abstract class BaseEditExportController extends BaseRefactorEditExportController {
    public static final /* synthetic */ int W0 = 0;
    public final Lazy A;
    public Integer A0;
    public final Lazy B;
    public Integer B0;
    public final Lazy C;
    public Integer C0;
    public final Lazy D;
    public final ClientSetting D0;
    public final Lazy E;
    public final AtomicBoolean E0;
    public final Lazy F;
    public long F0;
    public final Lazy G;
    public boolean G0;
    public final Lazy H;
    public boolean H0;
    public final Lazy I;
    public boolean I0;

    /* renamed from: J, reason: collision with root package name */
    public final Lazy f91358J;
    public boolean J0;
    public final Lazy K;
    public boolean K0;
    public final Lazy L;
    public boolean L0;
    public final Lazy M;
    public boolean M0;
    public final Lazy N;
    public Function0<Boolean> N0;
    public LvProgressDialog O;
    public boolean O0;
    public final Lazy P;
    public final Lazy P0;
    public final Lazy Q;
    public Function0<? extends HashMap<String, Object>> Q0;
    public final Lazy R;
    public AdDraftExtraInfo R0;
    public final Lazy S;
    public String S0;
    public final Lazy T;
    public String T0;
    public final Lazy U;
    public boolean U0;
    public final Lazy V;
    public final Resources V0;
    public final Lazy W;

    /* renamed from: X, reason: collision with root package name */
    public final Lazy f91359X;
    public final Lazy Y;
    public final Lazy Z;
    public final Lazy a0;
    public final AbsEditActivity b;
    public final Lazy b0;

    /* renamed from: c, reason: collision with root package name */
    public final String f91360c;
    public final Lazy c0;

    /* renamed from: d, reason: collision with root package name */
    public final String f91361d;
    public final Lazy d0;
    public final FileScavenger e;
    public final Lazy e0;
    public final ProjectSnapshot f;
    public final Lazy f0;

    /* renamed from: g, reason: collision with root package name */
    public final EditContainer f91362g;
    public final Lazy g0;
    public final Function0<String> h;
    public final Lazy h0;
    public final IVipExportBusinessFunction i;
    public final Lazy i0;
    public final Function0<IPanel> j;
    public final Lazy j0;
    public final Function0<Unit> k;
    public final Lazy k0;
    public final Function0<Boolean> l;
    public final List<BenefitResource> l0;
    public final Function0<Boolean> m;
    public boolean m0;
    public final Function0<Pair<Boolean, Boolean>> n;
    public final Lazy n0;
    public final Function0<LocatorDispatcher> o;
    public final Lazy o0;
    public final Function0<EditMaterialLostUtil> p;
    public final Lazy p0;
    public final Lazy q;
    public final Lazy q0;
    public final Lazy r;
    public final Lazy r0;
    public final Lazy s;
    public final Lazy s0;
    public final Lazy t;
    public final Lazy t0;
    public final Lazy u;
    public final Lazy u0;
    public final Lazy v;
    public final Lazy v0;
    public final Lazy w;
    public final Lazy w0;
    public final Lazy x;
    public final Lazy x0;
    public final Lazy y;
    public String y0;
    public final Lazy z;
    public String z0;

    /* loaded from: classes22.dex */
    public static final class Companion {
    }

    /* loaded from: classes37.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f91389a;

        static {
            int[] iArr = new int[LVVESingleFunctionType.values().length];
            try {
                iArr[1] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[2] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f91389a = iArr;
        }
    }

    static {
        new Companion();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r15v0, resolved type: kotlin.jvm.functions.Function0<? extends com.vega.edit.base.dock.IPanel> */
    /* JADX WARN: Multi-variable type inference failed */
    public BaseEditExportController(final AbsEditActivity absEditActivity, String str, String str2, FileScavenger fileScavenger, ProjectSnapshot projectSnapshot, EditContainer editContainer, Function0<String> function0, IVipExportBusinessFunction iVipExportBusinessFunction, Function0<? extends IPanel> function02, Function0<Unit> function03, Function0<Boolean> function04, Function0<Boolean> function05, Function0<Pair<Boolean, Boolean>> function06, Function0<LocatorDispatcher> function07, Function0<EditMaterialLostUtil> function08) {
        Intrinsics.checkNotNullParameter(absEditActivity, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(fileScavenger, "");
        Intrinsics.checkNotNullParameter(editContainer, "");
        Intrinsics.checkNotNullParameter(function0, "");
        Intrinsics.checkNotNullParameter(iVipExportBusinessFunction, "");
        Intrinsics.checkNotNullParameter(function02, "");
        Intrinsics.checkNotNullParameter(function03, "");
        Intrinsics.checkNotNullParameter(function07, "");
        Intrinsics.checkNotNullParameter(function08, "");
        this.b = absEditActivity;
        this.f91360c = str;
        this.f91361d = str2;
        this.e = fileScavenger;
        this.f = projectSnapshot;
        this.f91362g = editContainer;
        this.h = function0;
        this.i = iVipExportBusinessFunction;
        this.j = function02;
        this.k = function03;
        this.l = function04;
        this.m = function05;
        this.n = function06;
        this.o = function07;
        this.p = function08;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.q = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ReportViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$1
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91364g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.ReportViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ReportViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91364g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ReportViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.r = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ResolutionViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$2
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91375g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.commonedit.viewmodel.ResolutionViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ResolutionViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91375g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(ResolutionViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.s = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditUIViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$3
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91380g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.viewmodel.EditUIViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EditUIViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91380g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(EditUIViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.t = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditBusinessViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$4
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91381g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.EditBusinessViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EditBusinessViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91381g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(EditBusinessViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.u = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$5
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91382g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.editpage.viewmodel.EditViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EditViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91382g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(EditViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.v = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoStableViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$6
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91383g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.stable.viewmodel.MainVideoStableViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoStableViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91383g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MainVideoStableViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.w = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubVideoStableViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$7
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91384g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.stable.viewmodel.SubVideoStableViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubVideoStableViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91384g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SubVideoStableViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.x = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<HandwriteViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$8
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91385g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.handwrite.HandwriteViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HandwriteViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91385g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(HandwriteViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.y = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<VideoEffectViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$9
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91386g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.videoeffect.viewmodel.VideoEffectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final VideoEffectViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91386g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(VideoEffectViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.z = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<VarHeightViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$10
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91365g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.VarHeightViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final VarHeightViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91365g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(VarHeightViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.A = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubtitleViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$11
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91366g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.viewmodel.SubtitleViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubtitleViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91366g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SubtitleViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.B = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<IEditPerformanceViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$12
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91367g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.viewmodel.IEditPerformanceViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final IEditPerformanceViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91367g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(IEditPerformanceViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.C = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<StickerViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$13
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91368g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.libsticker.viewmodel.StickerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final StickerViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91368g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(StickerViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.D = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoManualFigureViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$14
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91369g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.figure.model.panel.MainVideoManualFigureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoManualFigureViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91369g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MainVideoManualFigureViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.E = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubVideoManualFigureViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$15
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91370g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.figure.model.panel.SubVideoManualFigureViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubVideoManualFigureViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91370g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SubVideoManualFigureViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.F = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoMattingViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$16
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91371g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.matting.viewmodel.MainVideoMattingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoMattingViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91371g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MainVideoMattingViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.G = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubVideoMattingViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$17
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91372g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.matting.viewmodel.SubVideoMattingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubVideoMattingViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91372g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SubVideoMattingViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.H = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditPreviewViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$18
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91373g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.editpage.viewmodel.EditPreviewViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EditPreviewViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91373g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(EditPreviewViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.I = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<EditAIPaintingViewModelV2>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$19
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91374g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.aigenerator.viewmodel.EditAIPaintingViewModelV2] */
            @Override // kotlin.jvm.functions.Function0
            public final EditAIPaintingViewModelV2 invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91374g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(EditAIPaintingViewModelV2.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.f91358J = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$20
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91376g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.video.viewmodel.MainVideoViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91376g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MainVideoViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.K = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<MainVideoInPaintingViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$21
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91377g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.inpainting.viewmodel.MainVideoInPaintingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MainVideoInPaintingViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91377g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(MainVideoInPaintingViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.L = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<SubVideoInPaintingViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$22
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91378g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.inpainting.viewmodel.SubVideoInPaintingViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SubVideoInPaintingViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91378g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(SubVideoInPaintingViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        this.M = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<WaterMarkViewModel>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$special$$inlined$factoryViewModel$23
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f91379g = null;
            public final /* synthetic */ Function0 h = null;

            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.edit.base.watermark.viewmodel.WaterMarkViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WaterMarkViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                ComponentActivity componentActivity = absEditActivity;
                Qualifier qualifier = this.f;
                Function0 function09 = this.f91379g;
                Function0 function010 = this.h;
                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                if (function09 == null || (defaultViewModelCreationExtras = (CreationExtras) function09.invoke()) == null) {
                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(WaterMarkViewModel.class);
                Intrinsics.checkNotNull(viewModelStore);
                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function010);
            }
        });
        final EditExportController editExportController = (EditExportController) this;
        this.N = LazyKt__LazyJVMKt.lazy(new Function0<AbsSingleFunctionEditSharedVM>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$singleFunctionViewModel$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final AbsSingleFunctionEditSharedVM invoke() {
                return SingleFunctionService.b.b(editExportController.b);
            }
        });
        this.P = LazyKt__LazyJVMKt.lazy(new Function0<ISession>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$session$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final ISession invoke() {
                return editExportController.q0().s;
            }
        });
        this.Q = LazyKt__LazyJVMKt.lazy(new Function0<IGuide>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$guide$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.guideapi.IGuide, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final IGuide invoke() {
                return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IGuide.class), null);
            }
        });
        this.R = LazyKt__LazyJVMKt.lazy(new Function0<AdMakerAIGCWaterMarkConfig>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$aigcDigitalWaterMaskConfig$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final AdMakerAIGCWaterMarkConfig invoke() {
                BaseClientSetting baseClientSetting = (BaseClientSetting) SPIService.INSTANCE.getOrNullImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class));
                if (baseClientSetting != null) {
                    return baseClientSetting.c();
                }
                return null;
            }
        });
        this.S = LazyKt__LazyJVMKt.lazy(new Function0<Bundle>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$publishExtra$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$publishExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Bundle invoke() {
                IFeedProxy iFeedProxyI = ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).i();
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$publishExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$publishExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                Intrinsics.checkNotNullExpressionValue(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$publishExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent, "");
                return iFeedProxyI.e(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$publishExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent);
            }
        });
        this.T = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$hasBindDraft$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$hasBindDraft$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$hasBindDraft$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$hasBindDraft$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Boolean.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$hasBindDraft$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$hasBindDraft$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getBooleanExtra("key_tutorial_include_draft", false) : false);
            }
        });
        this.U = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$isFromMultiCutSame$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromMultiCutSame$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromMultiCutSame$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromMultiCutSame$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Boolean.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromMultiCutSame$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromMultiCutSame$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getBooleanExtra("is_from_multi_cut_same", false) : false);
            }
        });
        this.V = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$postTopicEnterFrom$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$postTopicEnterFrom$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra;
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$postTopicEnterFrom$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$postTopicEnterFrom$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$postTopicEnterFrom$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$postTopicEnterFrom$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("task_center_enter_from")) == null) ? "" : stringExtra;
            }
        });
        this.W = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$homeDraftListMode$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$homeDraftListMode$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra;
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$homeDraftListMode$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$homeDraftListMode$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$homeDraftListMode$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$homeDraftListMode$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("home_draft_list_mode")) == null) ? "" : stringExtra;
            }
        });
        this.f91359X = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$specificExportResolution$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportResolution$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportResolution$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportResolution$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Integer.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportResolution$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportResolution$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getIntExtra("specific_export_resolution", 0) : 0);
            }
        });
        this.Y = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$specificExportFps$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportFps$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportFps$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportFps$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Integer.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportFps$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$specificExportFps$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getIntExtra("specific_export_fps", 0) : 0);
            }
        });
        this.Z = LazyKt__LazyJVMKt.lazy(new Function0<Long>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$vid$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$vid$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Long invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$vid$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$vid$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Long.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$vid$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$vid$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getLongExtra("vid", -1L) : -1L);
            }
        });
        this.a0 = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$isFromIntelligentRecommend$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromIntelligentRecommend$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromIntelligentRecommend$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromIntelligentRecommend$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Boolean.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromIntelligentRecommend$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromIntelligentRecommend$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getBooleanExtra("is_from_intelligent_recommend", false) : false);
            }
        });
        this.b0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$passThroughLogExtra$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$passThroughLogExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$passThroughLogExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$passThroughLogExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                if (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$passThroughLogExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null) {
                    return intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$passThroughLogExtra$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("pass_through_log_extra");
                }
                return null;
            }
        });
        this.c0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$loadEnterFrom$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String enterFrom;
                ProjectSnapshot projectSnapshot2 = editExportController.f;
                return (projectSnapshot2 == null || (enterFrom = projectSnapshot2.getEnterFrom()) == null) ? "" : enterFrom;
            }
        });
        this.d0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$loadRuleId$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String ruleId;
                ProjectSnapshot projectSnapshot2 = editExportController.f;
                return (projectSnapshot2 == null || (ruleId = projectSnapshot2.getRuleId()) == null) ? "" : ruleId;
            }
        });
        this.e0 = LazyKt__LazyJVMKt.lazy(new Function0<ReportPromptExportData>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$exportPromptParams$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$exportPromptParams$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v4, types: [android.os.Parcelable, com.vega.edit.base.model.ReportPromptExportData] */
            @Override // kotlin.jvm.functions.Function0
            public final ReportPromptExportData invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$exportPromptParams$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$exportPromptParams$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                if (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$exportPromptParams$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null) {
                    return intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$exportPromptParams$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getParcelableExtra("export_prompt_params");
                }
                return null;
            }
        });
        this.f0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$enterPosition$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$enterPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra;
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$enterPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$enterPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$enterPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$enterPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("key_template_enter_position")) == null) ? "" : stringExtra;
            }
        });
        this.g0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$tobRequestId$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobRequestId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra;
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobRequestId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobRequestId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobRequestId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobRequestId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("tob_request_id")) == null) ? "" : stringExtra;
            }
        });
        this.h0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$tobCategoryId$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobCategoryId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra;
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobCategoryId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobCategoryId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobCategoryId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobCategoryId$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("tob_category_id")) == null) ? "" : stringExtra;
            }
        });
        this.i0 = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$tobPosition$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                String stringExtra;
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$tobPosition$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("tob_position")) == null) ? "" : stringExtra;
            }
        });
        this.j0 = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$searchRank$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$searchRank$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$searchRank$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$searchRank$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Integer.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$searchRank$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$searchRank$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getIntExtra("rank", 0) : 0);
            }
        });
        this.k0 = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$isFromDrafts$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromDrafts$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity2) {
                Context context;
                Intent intent = absEditActivity2.getIntent();
                if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
                    intent.setExtrasClassLoader(context.getClassLoader());
                }
                return intent;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromDrafts$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromDrafts$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent(editExportController.b);
                return Integer.valueOf(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromDrafts$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController$isFromDrafts$2_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getIntExtra("tem_enter_draft", 0) : 0);
            }
        });
        this.l0 = new ArrayList();
        this.n0 = t0("", "anchor_edit_type");
        this.o0 = t0("", "anchor_effect_id");
        this.p0 = t0("", "anchor_effect");
        this.q0 = t0("", "is_pass_anchor_popup");
        this.r0 = t0("", "anchor_key");
        this.s0 = t0("", "select_draft_dialog_extra_report");
        this.t0 = t0("", "KEY_BUSINESS_TEMPLATE_PAY_TYPE");
        this.u0 = t0("", "KEY_BUSINESS_TEMPLATE_PAY_STATUS");
        this.v0 = t0("", "KEY_BUSINESS_TEMPLATE_CATEGORY");
        this.w0 = t0(-1L, "KEY_BUSINESS_TEMPLATE_ORIGIN_PRICE");
        this.x0 = t0(-1L, "KEY_BUSINESS_TEMPLATE_PRICE");
        this.D0 = (ClientSetting) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null);
        this.E0 = new AtomicBoolean(false);
        this.N0 = new Function0<Boolean>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$isVipUser$1
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.FALSE;
            }
        };
        this.P0 = LazyKt__LazyJVMKt.lazy(new Function0<VipExportLoading>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$vipExportLoading$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final VipExportLoading invoke() {
                final BaseEditExportController baseEditExportController = editExportController;
                AbsEditActivity absEditActivity2 = baseEditExportController.b;
                Function3<View, Integer, Integer, Unit> function3 = new Function3<View, Integer, Integer, Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$vipExportLoading$2.1
                    {
                        super(3);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function3
                    public final Unit invoke(View view, Integer num, Integer num2) {
                        View view2 = view;
                        int iIntValue = num.intValue();
                        int iIntValue2 = num2.intValue();
                        Intrinsics.checkNotNullParameter(view2, "");
                        AndroidExtensionsBase androidExtensionsBase = baseEditExportController.b;
                        Intrinsics.checkNotNull(androidExtensionsBase, "");
                        ViewGroup viewGroup = (ViewGroup) androidExtensionsBase.findViewByIdCached(androidExtensionsBase, R.id.editParentRoot, FrameLayout.class);
                        if (viewGroup != null) {
                            viewGroup.addView(view2, iIntValue, iIntValue2);
                        }
                        return Unit.INSTANCE;
                    }
                };
                final BaseEditExportController baseEditExportController2 = editExportController;
                return new VipExportLoading(absEditActivity2, function3, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$vipExportLoading$2.2
                    {
                        super(0);
                    }

                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final Unit invoke() {
                        AndroidExtensionsBase androidExtensionsBase = baseEditExportController2.b;
                        Intrinsics.checkNotNull(androidExtensionsBase, "");
                        View viewFindViewByIdCached = androidExtensionsBase.findViewByIdCached(androidExtensionsBase, R.id.tvExport, AlphaTextButton.class);
                        if (viewFindViewByIdCached != null) {
                            viewFindViewByIdCached.performClick();
                        }
                        return Unit.INSTANCE;
                    }
                });
            }
        });
        this.Q0 = new Function0() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$rewardAdReportParam$1
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Object invoke() {
                return null;
            }
        };
        this.S0 = "";
        this.T0 = "";
        q0().t0.observe(absEditActivity, new BaseEditExportController$sam$androidx_lifecycle_Observer$0(new Function1<ExportDraftZipState, Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController.1

            /* renamed from: com.vega.edit.editpage.controller.BaseEditExportController$1$WhenMappings */
            /* loaded from: classes7.dex */
            public /* synthetic */ class WhenMappings {

                /* renamed from: a, reason: collision with root package name */
                public static final /* synthetic */ int[] f91387a;

                static {
                    int[] iArr = new int[ExportDraftZipState.values().length];
                    try {
                        iArr[0] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[1] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[2] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    f91387a = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(ExportDraftZipState exportDraftZipState) {
                LvProgressDialog lvProgressDialog;
                ExportDraftZipState exportDraftZipState2 = exportDraftZipState;
                if (exportDraftZipState2 != null) {
                    int i = WhenMappings.f91387a[exportDraftZipState2.ordinal()];
                    if (i == 1) {
                        editExportController.y0();
                    } else if (i == 2) {
                        LvProgressDialog lvProgressDialog2 = editExportController.O;
                        if (lvProgressDialog2 != null) {
                            lvProgressDialog2.t();
                        }
                        editExportController.O = null;
                    } else if (i == 3 && (lvProgressDialog = editExportController.O) != null) {
                        lvProgressDialog.s();
                    }
                }
                return Unit.INSTANCE;
            }
        }));
        q0().u0.observe(absEditActivity, new BaseEditExportController$sam$androidx_lifecycle_Observer$0(new Function1<ExportDraftZipState, Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController.2

            /* renamed from: com.vega.edit.editpage.controller.BaseEditExportController$2$WhenMappings */
            /* loaded from: classes13.dex */
            public /* synthetic */ class WhenMappings {

                /* renamed from: a, reason: collision with root package name */
                public static final /* synthetic */ int[] f91388a;

                static {
                    int[] iArr = new int[ExportDraftZipState.values().length];
                    try {
                        iArr[0] = 1;
                    } catch (NoSuchFieldError unused) {
                    }
                    try {
                        iArr[1] = 2;
                    } catch (NoSuchFieldError unused2) {
                    }
                    try {
                        iArr[2] = 3;
                    } catch (NoSuchFieldError unused3) {
                    }
                    f91388a = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(ExportDraftZipState exportDraftZipState) {
                LvProgressDialog lvProgressDialog;
                ExportDraftZipState exportDraftZipState2 = exportDraftZipState;
                if (exportDraftZipState2 != null) {
                    int i = WhenMappings.f91388a[exportDraftZipState2.ordinal()];
                    if (i == 1) {
                        editExportController.y0();
                    } else if (i == 2) {
                        LvProgressDialog lvProgressDialog2 = editExportController.O;
                        if (lvProgressDialog2 != null) {
                            lvProgressDialog2.t();
                        }
                        editExportController.O = null;
                    } else if (i == 3 && (lvProgressDialog = editExportController.O) != null) {
                        lvProgressDialog.s();
                    }
                }
                return Unit.INSTANCE;
            }
        }));
        this.V0 = absEditActivity.getResources();
    }

    public static Bundle INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_bytedance_sysoptimizer_BadParcelableLancet_getBundleExtra(Intent intent, String str) {
        Bundle bundleExtra = intent.getBundleExtra(str);
        Context context = BadParcelableCrashOptimizer.getContext();
        if (bundleExtra != null && context != null) {
            bundleExtra.setClassLoader(context.getClassLoader());
        }
        return bundleExtra;
    }

    public static Intent INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(AbsEditActivity absEditActivity) {
        Context context;
        Intent intent = absEditActivity.getIntent();
        if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
        }
        return intent;
    }

    public static void a0(ConfirmCloseDialog confirmCloseDialog) {
        if (new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/ConfirmCloseDialog", "show", confirmCloseDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSUFMoUVyBYvVqFY2fxTeR88l91bAbtb3pBXciFQodDth4xFEBa/UTSDWUVWbN5SQyHw==")).isIntercept()) {
            return;
        }
        confirmCloseDialog.show();
    }

    public static /* synthetic */ void e0(BaseEditExportController baseEditExportController, boolean z, int i) throws Resources.NotFoundException {
        if ((i & 8) != 0) {
            z = false;
        }
        baseEditExportController.d0(false, false, false, z);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
     */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0736  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x07ab  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x07f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void f0(com.vega.edit.editpage.controller.BaseEditExportController r17, boolean r18, boolean r19, boolean r20, boolean r21) throws org.json.JSONException {
        /*
            java.lang.String r9 = "enter_from"
            r5 = r17
            if (r18 == 0) goto L58
            com.vega.commonedit.activity.AbsEditActivity r2 = r5.b
            kotlin.LazyThreadSafetyMode r1 = kotlin.LazyThreadSafetyMode.NONE
            com.vega.edit.editpage.controller.BaseEditExportController$publishEventReport$$inlined$factoryViewModel$1 r0 = new com.vega.edit.editpage.controller.BaseEditExportController$publishEventReport$$inlined$factoryViewModel$1
            r0.<init>()
            kotlin.Lazy r0 = kotlin.LazyKt__LazyJVMKt.lazy(r1, r0)
            java.lang.Object r0 = r0.getValue()
            com.vega.edit.export.viewmodel.EditPreExportViewModel r0 = (com.vega.edit.export.viewmodel.EditPreExportViewModel) r0
            boolean r0 = r0.A6()
            if (r0 == 0) goto L7a0
            java.lang.String r1 = "export_panel"
        L21:
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r3 = "type"
            java.lang.String r0 = "template"
            r2.put(r3, r0)
            r2.put(r9, r1)
            java.lang.String r1 = "creator_type"
            java.lang.String r0 = "videocut_creator"
            r2.put(r1, r0)
            java.lang.String r1 = "platform"
            java.lang.String r0 = "videocut"
            r2.put(r1, r0)
            com.vega.report.params.ReportParams$Companion r0 = com.vega.report.params.ReportParams.f129929c
            r0.getClass()
            com.vega.report.params.Tab r0 = com.vega.report.params.ReportParams.Companion.a()
            java.lang.String r1 = r0.f129935a
            java.lang.String r0 = "tab_name"
            r2.put(r0, r1)
            r2.toString()
            com.vega.report.ReportManagerWrapper r1 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r0 = "click_publish_template_type_next"
            r1.onEvent(r0, r2)
        L58:
            r1 = 0
            r5.U0 = r1
            com.lemon.lv.config.ClientSetting r0 = r5.D0
            r0.getExportActivityDialogAbTest()
            java.util.concurrent.atomic.AtomicBoolean r0 = r5.E0
            r4 = 1
            boolean r0 = r0.compareAndSet(r1, r4)
            java.lang.String r2 = "BaseEditExportController"
            if (r0 == 0) goto La6d
            kotlin.Lazy r0 = r5.H
            java.lang.Object r0 = r0.getValue()
            com.vega.commonedit.viewmodel.CommonPreviewViewModel r0 = (com.vega.commonedit.viewmodel.CommonPreviewViewModel) r0
            androidx.lifecycle.MutableLiveData<java.lang.Long> r0 = r0.q
            java.lang.Object r0 = r0.getValue()
            java.lang.Long r0 = (java.lang.Long) r0
            if (r0 != 0) goto L83
            r0 = 0
            java.lang.Long r0 = java.lang.Long.valueOf(r0)
        L83:
            r0.longValue()
            com.ss.android.ttve.nativePort.TEVideoUtils.nativeReleaseGetFramesReader()
            com.vega.middlebridge.utils.FrameReader r0 = com.vega.middlebridge.utils.FrameReader.INSTANCE
            r0.releaseFrameLoader()
            boolean r0 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r0 == 0) goto L98
            java.lang.String r0 = "releaseGetFramesReader"
            com.vega.log.BLog.i(r2, r0)
        L98:
            com.vega.textaihuman.render.DigitalHumanRenderServiceManager r1 = com.vega.textaihuman.render.DigitalHumanRenderServiceManager.f132469a
            com.vega.container.session.core.ISession r0 = r5.p0()
            r1.getClass()
            com.vega.textaihuman.render.DigitalHumanRenderService r0 = com.vega.textaihuman.render.DigitalHumanRenderServiceManager.d(r0)
            if (r0 == 0) goto Laa
            r0.b()
        Laa:
            com.vega.edit.base.utils.EditReportManager r2 = com.vega.edit.base.utils.EditReportManager.f88945a
            long r6 = java.lang.System.currentTimeMillis()
            long r0 = r5.F0
            long r6 = r6 - r0
            r2.getClass()
            com.vega.report.ReportManagerWrapper r3 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r2 = java.lang.String.valueOf(r6)
            java.lang.String r1 = "edit_time"
            java.lang.String r0 = "time"
            r3.onEvent(r1, r0, r2)
            com.vega.edit.base.watermark.viewmodel.WaterMarkViewModel r0 = r5.s0()
            java.util.Map r0 = r0.w6()
            com.vega.edit.base.utils.EditReportManager.k1 = r0
            com.vega.core.utils.PerformanceDebug r1 = com.vega.core.utils.PerformanceDebug.f79649a
            java.lang.String r0 = "trace_compile"
            com.vega.core.utils.PerformanceDebug.a(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            r0.v1()
            r17 = r19
            r0 = r17
            r5.u0(r0)
            r5.H0 = r4
            boolean r15 = r5.O0
            kotlin.jvm.functions.Function0<java.lang.Boolean> r0 = r5.N0
            java.lang.Object r0 = r0.invoke()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r13 = r0.booleanValue()
            com.vega.container.session.core.ISession r0 = r5.p0()
            com.vega.middlebridge.swig.Draft r0 = r0.l()
            if (r0 == 0) goto L79d
            java.lang.String r2 = r0.b()
        Lfe:
            com.vega.kv.keva.KevaUtil r1 = com.vega.kv.keva.KevaUtil.f107170a
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r14 = "export_ai_moment_task_id"
            r0.<init>(r14)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            r1.getClass()
            java.lang.String r1 = "export_ai_moment_sp"
            java.lang.String r12 = com.vega.kv.keva.KevaUtil.d(r1, r0)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r11 = "export_ai_moment_template_id"
            r0.<init>(r11)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.String r10 = com.vega.kv.keva.KevaUtil.d(r1, r0)
            boolean r0 = r5.Y()
            if (r0 == 0) goto L799
            java.lang.String r8 = r5.k0()
        L133:
            java.lang.String r7 = r5.f91361d
            kotlin.jvm.functions.Function0<kotlin.Pair<java.lang.Boolean, java.lang.Boolean>> r0 = r5.n
            if (r0 == 0) goto L796
            java.lang.Object r2 = r0.invoke()
            kotlin.Pair r2 = (kotlin.Pair) r2
        L13f:
            com.vega.container.session.core.ISession r16 = r5.p0()
            if (r18 == 0) goto L792
            java.lang.String r1 = "//template/publish"
        L147:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            com.bytedance.router.SmartRoute r4 = com.bytedance.router.SmartRouter.buildRoute(r0, r1)
            java.lang.String r3 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r3)
            int r0 = r16.getId()
            java.lang.String r6 = "session_id"
            r4.withParam(r6, r0)
            com.vega.container.session.lifecycle.ISessionLifecycleOwnerContract r0 = r16.N0()
            boolean r1 = r0.a()
            java.lang.String r0 = "session_bind_lifecycle"
            r4.withParam(r0, r1)
            java.lang.String r0 = "key_export_enter_from"
            r4.withParam(r0, r8)
            java.lang.String r0 = "key_export_page_from"
            r4.withParam(r0, r7)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r0 = r0.f89443c
            java.lang.String r1 = "edit_type"
            r4.withParam(r1, r0)
            kotlin.Lazy r0 = r5.S
            java.lang.Object r0 = r0.getValue()
            android.os.Bundle r0 = (android.os.Bundle) r0
            r4.withParam(r0)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r0 = r0.f89443c
            r4.withParam(r1, r0)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r1 = r0.m
            java.lang.String r0 = "edit_source"
            r4.withParam(r0, r1)
            kotlin.jvm.functions.Function0<java.lang.String> r0 = r5.h
            java.lang.Object r1 = r0.invoke()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "key_template_id"
            r4.withParam(r0, r1)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "extra_report_str"
            if (r0 == 0) goto L78f
            java.lang.String r0 = r0.getStringExtra(r1)
        L1b7:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r7 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "is_draft"
            if (r7 == 0) goto L78c
            r0 = 0
            int r0 = r7.getIntExtra(r1, r0)
        L1c9:
            r4.withParam(r1, r0)
            kotlin.jvm.functions.Function0<java.lang.Boolean> r0 = r5.l
            if (r0 == 0) goto L789
            java.lang.Object r0 = r0.invoke()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
        L1da:
            java.lang.String r0 = "key_template_unlock_free"
            r4.withParam(r0, r1)
            kotlin.jvm.functions.Function0<java.lang.Boolean> r0 = r5.m
            if (r0 == 0) goto L786
            java.lang.Object r0 = r0.invoke()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
        L1ed:
            java.lang.String r0 = "key_template_from_cc4b_lite"
            r4.withParam(r0, r1)
            if (r2 == 0) goto L783
            java.lang.Object r0 = r2.getFirst()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
        L1fe:
            java.lang.String r0 = "key_template_is_from_copy"
            r4.withParam(r0, r1)
            if (r2 == 0) goto L780
            java.lang.Object r0 = r2.getSecond()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
        L20f:
            java.lang.String r0 = "key_template_is_from_copy_cache_credible"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.T
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
            java.lang.String r0 = "key_tutorial_include_draft"
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r1 = r0.f89445g
            java.lang.String r0 = "extra_data"
            r4.withParam(r0, r1)
            r4.withParam(r14, r12)
            r4.withParam(r11, r10)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r2 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "from_template_tutorial_bond"
            r0 = 0
            int r0 = r2.getIntExtra(r1, r0)
            r4.withParam(r1, r0)
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r1 = "xigua_outer_source"
            java.lang.String r0 = "vicut_export_share"
            r2.put(r1, r0)
            java.lang.String r1 = r2.toString()
            java.lang.String r0 = "xigua_extra"
            r4.withParam(r0, r1)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r2 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "key_is_sample_article"
            if (r2 == 0) goto L77d
            r0 = 0
            boolean r0 = r2.getBooleanExtra(r1, r0)
        L26a:
            r4.withParam(r1, r0)
            java.lang.String r0 = "key_ttv_material_info"
            r2 = 0
            r4.withParam(r0, r2)
            java.lang.String r1 = r5.f91360c
            java.lang.String r0 = "push_"
            boolean r0 = X.C93472yG.S(r1, r0)
            if (r0 == 0) goto L286
            java.lang.String r1 = "rule_id"
            java.lang.String r0 = r5.o0()
            r4.withParam(r1, r0)
        L286:
            kotlin.Lazy r0 = r5.V
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "task_center_enter_from"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.i0
            java.lang.String r0 = "task_center_task_source"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.j0
            java.lang.String r0 = "task_center_position"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.k0
            java.lang.String r0 = "task_center_mission_type"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.l0
            java.lang.String r0 = "task_center_task_name"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.m0
            java.lang.String r0 = "task_center_task_id"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.n0
            java.lang.String r0 = "task_center_task_url"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.p0
            java.lang.String r0 = "trending_tab_name"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.q0
            java.lang.String r0 = "trending_sub_tab_name"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.r0
            java.lang.String r0 = "trend_source_type"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.s0
            java.lang.String r0 = "task_center_status"
            r4.withParam(r0, r1)
            boolean r1 = com.vega.edit.base.utils.EditReportManager.o0
            java.lang.String r0 = "is_paid"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.t0
            java.lang.String r0 = "task_center_reward_type"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.u0
            java.lang.String r0 = "task_center_start_stop_time"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.v0
            java.lang.String r0 = "task_business_id"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.w0
            java.lang.String r0 = "task_region"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.x0
            java.lang.String r0 = "trend_name"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.y0
            java.lang.String r0 = "trend_id"
            r4.withParam(r0, r1)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.z0
            java.lang.String r0 = "task_report_extra"
            r4.withParam(r0, r1)
            int r1 = com.vega.edit.base.utils.EditReportManager.A0
            java.lang.String r0 = "task_item_type"
            r4.withParam(r0, r1)
            java.util.ArrayList<java.lang.String> r1 = com.vega.edit.base.utils.EditReportManager.B0
            java.lang.String r0 = "supply_regions"
            r4.withParam(r0, r1)
            boolean r1 = com.vega.edit.base.utils.EditReportManager.q1
            java.lang.String r0 = "is_ai_prompt"
            r4.withParam(r0, r1)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "scene"
            if (r0 == 0) goto L336
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L337
        L336:
            r0 = r3
        L337:
            r4.withParam(r1, r0)
            java.lang.String r1 = "select_draft_dialog_extra_report"
            java.lang.String r0 = r5.o()
            r4.withParam(r1, r0)
            kotlin.Lazy r0 = r5.U
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r1 = r0.booleanValue()
            java.lang.String r0 = "is_from_multi_cut_same"
            r4.withParam(r0, r1)
            android.os.Bundle r0 = r5.h0()
            r4.withParam(r0)
            com.vega.subscriptionapi.biz.function.IVipExportBusinessFunction r1 = r5.i
            r0 = r16
            android.os.Bundle r0 = r1.K0(r0)
            r4.withParam(r0)
            java.lang.String r0 = "isVipExport"
            r4.withParam(r0, r15)
            java.lang.String r1 = "isTextToVideoVipTemplate"
            r0 = -1
            r4.withParam(r1, r0)
            java.lang.String r0 = "isVipUser"
            r4.withParam(r0, r13)
            java.lang.String r1 = "key_business_reward_ad_export_param"
            java.util.HashMap r0 = r5.n0()
            r4.withParam(r1, r0)
            kotlin.Lazy r0 = r5.t0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "KEY_BUSINESS_TEMPLATE_PAY_TYPE"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.u0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "KEY_BUSINESS_TEMPLATE_PAY_STATUS"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.v0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "KEY_BUSINESS_TEMPLATE_CATEGORY"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.w0
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
            java.lang.String r7 = "KEY_BUSINESS_TEMPLATE_ORIGIN_PRICE"
            r4.withParam(r7, r0)
            kotlin.Lazy r0 = r5.x0
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            long r0 = r0.longValue()
            java.lang.String r7 = "KEY_BUSINESS_TEMPLATE_PRICE"
            r4.withParam(r7, r0)
            kotlin.Lazy r0 = r5.W
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "home_draft_list_mode"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.b0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "pass_through_log_extra"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.f0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "key_template_enter_position"
            r4.withParam(r0, r1)
            java.lang.String r1 = "export_from"
            java.lang.String r0 = "edit"
            r4.withParam(r1, r0)
            java.lang.String r1 = "is_rewarded"
            r0 = r17
            r4.withParam(r1, r0)
            java.lang.String r0 = "is_benefit_rewarded"
            r1 = r20
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r1 = r0.t
            java.lang.String r0 = "generate_method"
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r1 = r0.u
            java.lang.String r0 = "smart_ad_video_id"
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            com.vega.adeditorapi.bean.AdReportParams r1 = r0.A
            java.lang.String r0 = "ad_report_params"
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r1 = r0.v
            java.lang.String r0 = "request_id"
            r4.withParam(r0, r1)
            java.lang.String r0 = "key_edit_auto_share_tiktok"
            r1 = r21
            r4.withParam(r0, r1)
            java.lang.String r1 = "key_edit_publish_replicate"
            boolean r0 = r5.I0
            r4.withParam(r1, r0)
            java.lang.String r1 = "key_edit_enable_publish_replicate"
            boolean r0 = r5.X()
            r4.withParam(r1, r0)
            java.lang.String r1 = "is_promote_checked"
            boolean r0 = r5.J0
            r4.withParam(r1, r0)
            java.lang.String r1 = "is_inspiration_checked"
            boolean r0 = r5.K0
            r4.withParam(r1, r0)
            java.lang.String r1 = "can_show_promote"
            boolean r0 = r5.L0
            r4.withParam(r1, r0)
            java.lang.String r1 = "should_save_promote_status"
            boolean r0 = r5.M0
            r4.withParam(r1, r0)
            kotlin.Lazy r0 = r5.g0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "tob_request_id"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.h0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "tob_category_id"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.i0
            java.lang.Object r1 = r0.getValue()
            java.lang.String r1 = (java.lang.String) r1
            java.lang.String r0 = "tob_position"
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.lang.String r1 = r0.w
            java.lang.String r0 = "video_type"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.j0
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            int r1 = r0.intValue()
            java.lang.String r0 = "rank"
            r4.withParam(r0, r1)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "sub_tab"
            if (r0 == 0) goto L4b6
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L4b7
        L4b6:
            r0 = r3
        L4b7:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "sub_category"
            if (r0 == 0) goto L4ca
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L4cb
        L4ca:
            r0 = r3
        L4cb:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "video_type_id"
            if (r0 == 0) goto L4de
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L4df
        L4de:
            r0 = r3
        L4df:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "script_create_method"
            if (r0 == 0) goto L4f2
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L4f3
        L4f2:
            r0 = r3
        L4f3:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "business_edit_stage"
            if (r0 == 0) goto L506
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L507
        L506:
            r0 = r3
        L507:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r7 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "is_modify_business_edit_prompt"
            if (r7 == 0) goto L77a
            r0 = 0
            int r0 = r7.getIntExtra(r1, r0)
        L519:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "business_edit_original_text"
            if (r0 == 0) goto L52c
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L52d
        L52c:
            r0 = r3
        L52d:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "business_edit_final_text"
            if (r0 == 0) goto L540
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L541
        L540:
            r0 = r3
        L541:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "business_title"
            if (r0 == 0) goto L554
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L555
        L554:
            r0 = r3
        L555:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "ai_story_task_id"
            if (r0 == 0) goto L777
            java.lang.String r0 = r0.getStringExtra(r1)
        L566:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "ai_story_lynx_report_param"
            if (r0 == 0) goto L774
            android.os.Bundle r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_bytedance_sysoptimizer_BadParcelableLancet_getBundleExtra(r0, r1)
        L577:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "ai_story_report_info"
            if (r0 == 0) goto L771
            java.lang.String r0 = r0.getStringExtra(r1)
        L588:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "business_edit_page_type"
            if (r0 == 0) goto L59b
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L59c
        L59b:
            r0 = r3
        L59c:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "ai_lab_agent_common_params"
            if (r0 == 0) goto L5af
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 != 0) goto L5b0
        L5af:
            r0 = r3
        L5b0:
            r4.withParam(r1, r0)
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.M0
            java.lang.String r0 = "music"
            r4.withParam(r0, r1)
            java.lang.Boolean r1 = com.vega.edit.base.utils.EditReportManager.H0
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L76d
            java.lang.String r1 = "1"
        L5c6:
            java.lang.String r0 = "music_all_mute"
            r4.withParam(r0, r1)
            java.lang.Boolean r1 = com.vega.edit.base.utils.EditReportManager.I0
            java.lang.String r0 = "music_from_library"
            r4.withParam(r0, r1)
            java.util.ArrayList<java.lang.Long> r1 = com.vega.edit.base.utils.EditReportManager.J0
            java.lang.String r0 = "library_music_ids"
            r4.withParam(r0, r1)
            com.vega.feedx.main.bean.UgCampaignParams$Companion r1 = com.vega.feedx.main.bean.UgCampaignParams.i
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            r1.getClass()
            com.vega.feedx.main.bean.UgCampaignParams r0 = com.vega.feedx.main.bean.UgCampaignParams.Companion.b(r0)
            if (r0 == 0) goto L76a
            java.lang.String r1 = r0.f100531a
        L5ec:
            java.lang.String r0 = "ug_campaign_params"
            r4.withParam(r0, r1)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "is_from_property_local_draft"
            r7 = 0
            if (r0 == 0) goto L767
            boolean r0 = r0.getBooleanExtra(r1, r7)
        L600:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "is_from_property_space"
            if (r0 == 0) goto L764
            boolean r0 = r0.getBooleanExtra(r1, r7)
        L611:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "is_from_property_material"
            if (r0 == 0) goto L761
            boolean r0 = r0.getBooleanExtra(r1, r7)
        L622:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "airecommend_report_info"
            if (r0 == 0) goto L75e
            java.lang.String r0 = r0.getStringExtra(r1)
        L633:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "common_export_params"
            if (r0 == 0) goto L75b
            boolean r0 = r0.getBooleanExtra(r1, r7)
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
        L648:
            r4.withParam(r1, r0)
            boolean r0 = com.vega.edit.base.utils.EditReportManager.D0
            if (r0 == 0) goto L656
            java.lang.String r1 = com.vega.edit.base.utils.EditReportManager.E0
            java.lang.String r0 = "project"
            r4.withParam(r0, r1)
        L656:
            com.vega.edit.viewmodel.EditUIViewModel r0 = r5.q0()
            com.vega.container.session.core.ISession r0 = r0.s
            int r0 = r0.getId()
            r4.withParam(r6, r0)
            r5.W(r4)
            kotlin.Lazy r0 = r5.f91359X
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            int r1 = r0.intValue()
            java.lang.String r0 = "specific_export_resolution"
            r4.withParam(r0, r1)
            kotlin.Lazy r0 = r5.Y
            java.lang.Object r0 = r0.getValue()
            java.lang.Number r0 = (java.lang.Number) r0
            int r1 = r0.intValue()
            java.lang.String r0 = "specific_export_fps"
            r4.withParam(r0, r1)
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.x
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r0 = r0.get(r9)
            java.lang.String r6 = "knowledge_share"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r6)
            if (r0 != 0) goto L6b0
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.x
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r1 = r0.get(r9)
            java.lang.String r0 = "tiktok_ads"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L6b9
        L6b0:
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.x
            com.vega.core.ext.SmartRouteExKt.b(r4, r0)
        L6b9:
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.y
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r0 = r0.get(r9)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r6)
            if (r0 == 0) goto L6d4
            com.vega.edit.base.viewmodel.ReportViewModel r0 = r5.l0()
            java.util.Map<java.lang.String, java.lang.Object> r0 = r0.y
            com.vega.core.ext.SmartRouteExKt.b(r4, r0)
        L6d4:
            com.vega.container.session.core.ISession r0 = r5.p0()
            com.vega.middlebridge.lyrasession.LyraSession r1 = r0.b()
            if (r1 == 0) goto L759
            com.vega.middlebridge.swig.GetAICreateAttachmentDataReqStruct r0 = new com.vega.middlebridge.swig.GetAICreateAttachmentDataReqStruct
            r0.<init>()
            com.vega.middlebridge.swig.GetAICreateAttachmentDataRespStruct r0 = com.vega.middlebridge.client.AiCreateClient.b(r1, r0)
            if (r0 == 0) goto L759
            com.vega.middlebridge.swig.AttachmentAiCreate r6 = r0.c()
            if (r6 == 0) goto L759
            long r0 = r6.f116014d
            java.lang.String r1 = com.vega.middlebridge.swig.AttachmentAiCreateModuleJNI.AttachmentAiCreate_getTrackInfo(r0, r6)
        L6f5:
            java.lang.String r0 = "key_ai_creator_export_event_info"
            r4.withParam(r0, r1)
            com.vega.container.session.core.ISession r0 = r5.p0()
            com.vega.middlebridge.lyrasession.LyraSession r1 = r0.b()
            if (r1 == 0) goto L7f5
            com.vega.middlebridge.swig.GetAICreateAttachmentDataReqStruct r0 = new com.vega.middlebridge.swig.GetAICreateAttachmentDataReqStruct
            r0.<init>()
            com.vega.middlebridge.swig.GetAICreateAttachmentDataRespStruct r0 = com.vega.middlebridge.client.AiCreateClient.b(r1, r0)
            if (r0 == 0) goto L7f5
            com.vega.middlebridge.swig.AttachmentAiCreate r0 = r0.c()
            if (r0 == 0) goto L7f5
            com.vega.middlebridge.swig.VectorOfInt r1 = r0.d()
            java.util.ArrayList r8 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, r0)
            r8.<init>(r0)
            java.util.Iterator r7 = r1.iterator()
        L728:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto L7d6
            java.lang.Object r6 = r7.next()
            java.lang.Integer r6 = (java.lang.Integer) r6
            if (r6 != 0) goto L73b
        L736:
            r0 = r3
        L737:
            r8.add(r0)
            goto L728
        L73b:
            int r1 = r6.intValue()
            r0 = 1
            if (r1 != r0) goto L745
            java.lang.String r0 = "graphic_editor"
            goto L737
        L745:
            int r1 = r6.intValue()
            r0 = 2
            if (r1 != r0) goto L74f
            java.lang.String r0 = "lite_editor"
            goto L737
        L74f:
            int r1 = r6.intValue()
            r0 = 3
            if (r1 != r0) goto L736
            java.lang.String r0 = "editor"
            goto L737
        L759:
            r1 = r2
            goto L6f5
        L75b:
            r0 = r2
            goto L648
        L75e:
            r0 = r2
            goto L633
        L761:
            r0 = 0
            goto L622
        L764:
            r0 = 0
            goto L611
        L767:
            r0 = 0
            goto L600
        L76a:
            r1 = r2
            goto L5ec
        L76d:
            java.lang.String r1 = "0"
            goto L5c6
        L771:
            r0 = r2
            goto L588
        L774:
            r0 = r2
            goto L577
        L777:
            r0 = r2
            goto L566
        L77a:
            r0 = 0
            goto L519
        L77d:
            r0 = 0
            goto L26a
        L780:
            r1 = 0
            goto L20f
        L783:
            r1 = 0
            goto L1fe
        L786:
            r1 = 0
            goto L1ed
        L789:
            r1 = 0
            goto L1da
        L78c:
            r0 = 0
            goto L1c9
        L78f:
            r0 = 0
            goto L1b7
        L792:
            java.lang.String r1 = "//export"
            goto L147
        L796:
            r2 = 0
            goto L13f
        L799:
            java.lang.String r8 = r5.f91360c
            goto L133
        L79d:
            r2 = 0
            goto Lfe
        L7a0:
            java.lang.String r2 = r5.f91360c
            int r0 = r2.hashCode()
            java.lang.String r1 = "profile"
            switch(r0) {
                case -1866011987: goto L7c9;
                case -309425751: goto L7c2;
                case 394422480: goto L7b9;
                case 1131499164: goto L7af;
                default: goto L7ab;
            }
        L7ab:
            java.lang.String r1 = r5.f91360c
            goto L21
        L7af:
            java.lang.String r0 = "creator_trending_center"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L7ab
            goto L21
        L7b9:
            java.lang.String r0 = "personal_task_center"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L21
            goto L7ab
        L7c2:
            boolean r0 = r2.equals(r1)
            if (r0 != 0) goto L21
            goto L7ab
        L7c9:
            java.lang.String r0 = "edit_tool"
            boolean r0 = r2.equals(r0)
            if (r0 != 0) goto L7d2
            goto L7ab
        L7d2:
            java.lang.String r1 = "post_template_tool"
            goto L21
        L7d6:
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r6 = r8.iterator()
        L7df:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L7f7
            java.lang.Object r1 = r6.next()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r1)
            r0 = r0 ^ 1
            if (r0 == 0) goto L7df
            r7.add(r1)
            goto L7df
        L7f5:
            r1 = r2
            goto L809
        L7f7:
            java.util.Set r6 = kotlin.collections.CollectionsKt___CollectionsKt.toSet(r7)
            if (r6 == 0) goto L7f5
            java.lang.String r7 = ","
            r10 = 0
            r12 = 62
            r8 = r2
            r9 = r2
            r11 = r2
            java.lang.String r1 = kotlin.collections.CollectionsKt.j(r6, r7, r8, r9, r10, r11, r12)
        L809:
            java.lang.String r0 = "key_is_edited"
            r4.withParam(r0, r1)
            r5.U(r4)
            kotlin.Lazy r0 = r5.e0
            java.lang.Object r0 = r0.getValue()
            if (r0 == 0) goto L826
            kotlin.Lazy r0 = r5.e0
            java.lang.Object r1 = r0.getValue()
            com.vega.edit.base.model.ReportPromptExportData r1 = (com.vega.edit.base.model.ReportPromptExportData) r1
            java.lang.String r0 = "export_prompt_params"
            r4.withParam(r0, r1)
        L826:
            java.lang.String r0 = r5.i()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L839
            java.lang.String r1 = "ad_type"
            java.lang.String r0 = r5.i()
            r4.withParam(r1, r0)
        L839:
            java.lang.String r0 = r5.k()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L84c
            java.lang.String r1 = "ads_template_id"
            java.lang.String r0 = r5.k()
            r4.withParam(r1, r0)
        L84c:
            java.lang.String r0 = r5.j()
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L85f
            java.lang.String r1 = "ads_draft_id"
            java.lang.String r0 = r5.j()
            r4.withParam(r1, r0)
        L85f:
            java.lang.String r0 = r5.S0
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L86e
            java.lang.String r1 = r5.S0
            java.lang.String r0 = "smart_script_type_for_anchor"
            r4.withParam(r0, r1)
        L86e:
            java.lang.String r0 = r5.T0
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L87d
            java.lang.String r1 = r5.T0
            java.lang.String r0 = "text_to_video_type"
            r4.withParam(r0, r1)
        L87d:
            com.vega.commonedit.viewmodel.ResolutionViewModel r1 = r5.m0()
            com.vega.middlebridge.swig.Draft r0 = r16.l()
            if (r0 == 0) goto L88d
            java.lang.String r0 = r0.b()
            if (r0 != 0) goto La6a
        L88d:
            r1.F6(r3)
            com.vega.commonedit.viewmodel.ResolutionViewModel r0 = r5.m0()
            boolean r1 = r0.B6()
            java.lang.String r0 = "specific_export_hdr"
            r4.withParam(r0, r1)
            com.vega.edit.utils.SmartCompleteFrameUtil r1 = com.vega.edit.utils.SmartCompleteFrameUtil.f96266a
            com.vega.middlebridge.swig.Draft r0 = r16.l()
            if (r0 == 0) goto La67
            java.lang.String r0 = r0.b()
        L8a9:
            r1.getClass()
            boolean r1 = com.vega.edit.utils.SmartCompleteFrameUtil.f(r0)
            java.lang.String r0 = "export_smart_frame"
            r4.withParam(r0, r1)
            com.vega.middlebridge.swig.Draft r0 = r16.l()
            if (r0 == 0) goto La64
            java.lang.String r0 = r0.b()
        L8bf:
            boolean r1 = com.vega.edit.utils.SmartCompleteFrameUtil.g(r0)
            java.lang.String r0 = "export_high_definition"
            r4.withParam(r0, r1)
            com.vega.middlebridge.swig.Draft r0 = r16.l()
            if (r0 == 0) goto La61
            java.lang.String r0 = r0.b()
        L8d2:
            boolean r1 = com.vega.edit.utils.SmartCompleteFrameUtil.d(r0)
            java.lang.String r0 = "export_add_adjust_for_high_definition"
            r4.withParam(r0, r1)
            if (r18 == 0) goto La4d
            r5.V(r4)
        L8e0:
            com.lemon.lv.ScanLogsUtil r0 = com.lemon.lv.ScanLogsUtil.f58790a
            r0.getClass()
            java.lang.String r0 = "open_export_activity"
            com.lemon.lv.ScanLogsUtil.a(r0)
            com.vega.commonedit.activity.AbsEditActivity r3 = r5.b
            r1 = 2130772100(0x7f010084, float:1.7147309E38)
            r0 = 0
            r3.overridePendingTransition(r1, r0)
            if (r18 == 0) goto La49
            r3 = 1024(0x400, float:1.435E-42)
        L8f7:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "refer_from"
            java.lang.String r7 = "deeplink"
            if (r0 == 0) goto L915
            java.lang.String r0 = r0.getStringExtra(r7)
            if (r0 == 0) goto L915
            android.net.Uri r0 = android.net.Uri.parse(r0)
            if (r0 == 0) goto L915
            java.lang.String r0 = r0.getQueryParameter(r1)
            if (r0 != 0) goto L917
        L915:
            java.lang.String r0 = "multi_track"
        L917:
            r4.withParam(r1, r0)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r0 == 0) goto L92d
            java.lang.String r1 = "popup_from"
            java.lang.String r0 = r0.getStringExtra(r1)
            if (r0 == 0) goto L92d
            r4.withParam(r1, r0)
        L92d:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r1 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r1 == 0) goto L942
            java.lang.String r0 = "new_project_position"
            java.lang.String r1 = r1.getStringExtra(r0)
            if (r1 == 0) goto L942
            java.lang.String r0 = "new_project_position"
            r4.withParam(r0, r1)
        L942:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r1 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r1 == 0) goto L957
            java.lang.String r0 = "edit_homepage_hot_template_floor_title"
            java.lang.String r1 = r1.getStringExtra(r0)
            if (r1 == 0) goto L957
            java.lang.String r0 = "edit_homepage_hot_template_floor_title"
            r4.withParam(r0, r1)
        L957:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r1 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r1 == 0) goto L96c
            java.lang.String r0 = "edit_homepage_hot_template_floor_id"
            java.lang.String r1 = r1.getStringExtra(r0)
            if (r1 == 0) goto L96c
            java.lang.String r0 = "edit_homepage_hot_template_floor_id"
            r4.withParam(r0, r1)
        L96c:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r1 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r1 == 0) goto L981
            java.lang.String r0 = "edit_homepage_hot_template_plan_title"
            java.lang.String r1 = r1.getStringExtra(r0)
            if (r1 == 0) goto L981
            java.lang.String r0 = "edit_homepage_hot_template_plan_title"
            r4.withParam(r0, r1)
        L981:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r1 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r1 == 0) goto L996
            java.lang.String r0 = "edit_homepage_hot_template_plan_id"
            java.lang.String r1 = r1.getStringExtra(r0)
            if (r1 == 0) goto L996
            java.lang.String r0 = "edit_homepage_hot_template_plan_id"
            r4.withParam(r0, r1)
        L996:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r6 = "key_edit_token_undertake_params"
            if (r0 == 0) goto La2d
            java.lang.String r0 = r0.getStringExtra(r6)
            if (r0 == 0) goto La2d
            int r0 = r0.length()
            if (r0 <= 0) goto La2d
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r0 == 0) goto L9b8
            java.lang.String r2 = r0.getStringExtra(r6)
        L9b8:
            r4.withParam(r6, r2)
        L9bb:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r2 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "is_from_home"
            r0 = 0
            boolean r1 = r2.getBooleanExtra(r1, r0)
            java.lang.String r0 = "is_from_home"
            r4.withParam(r0, r1)
            java.lang.String r1 = r5.y0
            if (r1 == 0) goto L9d6
            java.lang.String r0 = "edit_tool_enter_from"
            r4.withParam(r0, r1)
        L9d6:
            java.lang.String r1 = r5.z0
            if (r1 == 0) goto L9df
            java.lang.String r0 = "edit_more_from"
            r4.withParam(r0, r1)
        L9df:
            java.lang.Integer r0 = r5.A0
            if (r0 == 0) goto L9ec
            int r1 = r0.intValue()
            java.lang.String r0 = "is_collage_used"
            r4.withParam(r0, r1)
        L9ec:
            java.lang.Integer r0 = r5.B0
            if (r0 == 0) goto L9f9
            int r1 = r0.intValue()
            java.lang.String r0 = "is_retouch_used"
            r4.withParam(r0, r1)
        L9f9:
            java.lang.Integer r0 = r5.C0
            if (r0 == 0) goto La06
            int r1 = r0.intValue()
            java.lang.String r0 = "export_as_image"
            r4.withParam(r0, r1)
        La06:
            com.vega.config.ToolExportLimitFreeConfigSetting$Companion r0 = com.vega.config.ToolExportLimitFreeConfigSetting.Companion
            r0.getClass()
            boolean r0 = com.vega.config.ToolExportLimitFreeConfigSetting.Companion.c()
            if (r0 == 0) goto La1d
            java.util.ArrayList r1 = new java.util.ArrayList
            java.util.List<com.lemon.lv.clipmonetize.wrapper.BenefitResource> r0 = r5.l0
            r1.<init>(r0)
            java.lang.String r0 = "key_export_benefit_resources"
            r4.withParamParcelableList(r0, r1)
        La1d:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            com.vega.homepage.report.HomeToolReportInfoExtKt.g(r4, r0)
            r4.open(r3)
            r0 = 1
            r5.m0 = r0
            goto La72
        La2d:
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            if (r0 == 0) goto L9bb
            java.lang.String r1 = r0.getStringExtra(r7)
            if (r1 == 0) goto L9bb
            com.vega.util.ParseTokenParamsUtils r0 = com.vega.util.ParseTokenParamsUtils.f135183a
            r0.getClass()
            java.lang.String r0 = com.vega.util.ParseTokenParamsUtils.a(r1)
            r4.withParam(r6, r0)
            goto L9bb
        La49:
            r3 = 1002(0x3ea, float:1.404E-42)
            goto L8f7
        La4d:
            r5.T(r4)
            com.vega.commonedit.activity.AbsEditActivity r0 = r5.b
            android.content.Intent r0 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            java.lang.String r1 = "key_ug_activity_data"
            java.lang.String r0 = r0.getStringExtra(r1)
            r4.withParam(r1, r0)
            goto L8e0
        La61:
            r0 = r2
            goto L8d2
        La64:
            r0 = r2
            goto L8bf
        La67:
            r0 = r2
            goto L8a9
        La6a:
            r3 = r0
            goto L88d
        La6d:
            java.lang.String r0 = "export has been clicked~~"
            com.vega.log.BLog.e(r2, r0)
        La72:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.editpage.controller.BaseEditExportController.f0(com.vega.edit.editpage.controller.BaseEditExportController, boolean, boolean, boolean, boolean):void");
    }

    public static final void g0(final BaseEditExportController baseEditExportController, final boolean z, final boolean z2, final boolean z3, final boolean z4) throws JSONException {
        if (Intrinsics.areEqual(((HandwriteViewModel) baseEditExportController.x.getValue()).E.getValue(), Boolean.TRUE)) {
            ((HandwriteViewModel) baseEditExportController.x.getValue()).p6(new Function0<Unit>(baseEditExportController) { // from class: com.vega.edit.editpage.controller.BaseEditExportController$export$startExport$1
                public final /* synthetic */ BaseEditExportController f;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                    this.f = baseEditExportController;
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() throws JSONException {
                    BaseEditExportController.f0(this.f, z, z2, z3, z4);
                    return Unit.INSTANCE;
                }
            });
        } else {
            f0(baseEditExportController, z, z2, z3, z4);
        }
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Function0<Boolean> A() {
        return this.l;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v3, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v4, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v5, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v6, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v7, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v8, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r8v9, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public void B(Intent intent) throws Resources.NotFoundException {
        String str = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        Object[] objArr4 = 0;
        Object[] objArr5 = 0;
        Object[] objArr6 = 0;
        Object[] objArr7 = 0;
        Object[] objArr8 = 0;
        Object[] objArr9 = 0;
        String stringExtra = intent != null ? intent.getStringExtra("reload_project_id") : null;
        if (intent != null && intent.getBooleanExtra("hd_export", false)) {
            i0().r6();
            return;
        }
        WatermarkExperimentGroup.f89714a.getClass();
        if (WatermarkExperimentGroup.g()) {
            MainVideoViewModelExKt.d(p0().b(), (AdMakerAIGCWaterMarkConfig) this.R.getValue(), p0().l(), new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$onActivityResult$1
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    WaterMarkViewModel waterMarkViewModelS0 = this.e.s0();
                    if (waterMarkViewModelS0 != null) {
                        waterMarkViewModelS0.L6(this.e.p0().b(), (AdMakerAIGCWaterMarkConfig) this.e.R.getValue(), this.e.p0().l());
                    }
                    return Unit.INSTANCE;
                }
            });
            boolean booleanExtra = intent != null ? intent.getBooleanExtra("export_success", false) : false;
            WaterMarkViewModel waterMarkViewModelS0 = s0();
            if (waterMarkViewModelS0 != null) {
                waterMarkViewModelS0.H6(booleanExtra && !TextUtils.isEmpty(stringExtra));
            }
        } else {
            MainVideoViewModelExKt.d(p0().b(), (AdMakerAIGCWaterMarkConfig) this.R.getValue(), p0().l(), null);
            if (!TextUtils.isEmpty(stringExtra)) {
                s0().N6();
            }
        }
        if (!TextUtils.isEmpty(stringExtra)) {
            q0().i8();
            EditUIViewModel editUIViewModelQ0 = q0();
            PlayPositionState value = q0().w.getValue();
            IEditUIViewModel.m8(editUIViewModelQ0, Long.valueOf(value != null ? value.f89437a : 0L), 897, false, 0.0f, 0.0f, 60);
            if (p0().l() != null) {
                DigitalHumanRenderServiceManager digitalHumanRenderServiceManager = DigitalHumanRenderServiceManager.f132469a;
                ISession iSessionP0 = p0();
                digitalHumanRenderServiceManager.getClass();
                DigitalHumanRenderService digitalHumanRenderServiceD = DigitalHumanRenderServiceManager.d(iSessionP0);
                if (digitalHumanRenderServiceD != null) {
                    digitalHumanRenderServiceD.i();
                    return;
                }
                return;
            }
            return;
        }
        q0().s6(l0().f89443c, null, "", "", false);
        this.b.setResult(-1, intent);
        if (INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b).getBooleanExtra("intent_extra_back_main_after_export", false)) {
            SmartRouter.buildRoute(this.b, "//main").open();
            return;
        }
        if (intent == null || !intent.getBooleanExtra("back_to_main", false)) {
            this.b.finish();
            return;
        }
        if (!Intrinsics.areEqual(this.f91360c, "task_center")) {
            SmartRouter.buildRoute(this.b, "//main").open();
            return;
        }
        AbsEditActivity absEditActivity = this.b;
        Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//main/tabbar");
        Intrinsics.checkNotNull(builderPath);
        MainTabbarDeepLinkData mainTabbarDeepLinkData = new MainTabbarDeepLinkData(MainTabbarDeepLinkData.EnumIndex.f82500c, str, objArr9 == true ? 1 : 0, objArr8 == true ? 1 : 0, objArr7 == true ? 1 : 0, objArr6 == true ? 1 : 0, objArr5 == true ? 1 : 0, objArr4 == true ? 1 : 0, objArr3 == true ? 1 : 0, objArr2 == true ? 1 : 0, 1022, objArr == true ? 1 : 0);
        DeepLinkDataProvider deepLinkDataProvider = (DeepLinkDataProvider) C0NI.f2191a.a().getScopeRegistry().getRootScope().getOrNull(Reflection.getOrCreateKotlinClass(DeepLinkDataProvider.class), new C111833mm(Reflection.getOrCreateKotlinClass(MainTabbarDeepLinkData.class)), null);
        if (deepLinkDataProvider != null) {
            deepLinkDataProvider.a(mainTabbarDeepLinkData, builderPath);
        }
        String string = builderPath.toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        SmartRouter.buildRoute(absEditActivity, string).open();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public void C() {
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public void D() {
        this.F0 = System.currentTimeMillis();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void E(BenefitResource benefitResource) {
        Intrinsics.checkNotNullParameter(benefitResource, "");
        ((ArrayList) this.l0).remove(benefitResource);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void F() {
        this.E0.set(false);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void G(AdDraftExtraInfo adDraftExtraInfo) {
        this.R0 = adDraftExtraInfo;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void H(Integer num) {
        this.A0 = num;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void I(String str) {
        this.z0 = str;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void J(String str) {
        this.y0 = str;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void K(Integer num) {
        this.C0 = num;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void L(boolean z) {
        this.K0 = z;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void M(boolean z) {
        this.I0 = z;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void N(boolean z, boolean z2, boolean z3) {
        this.J0 = z;
        this.L0 = z2;
        this.M0 = z3;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void O(Integer num) {
        this.B0 = num;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void P(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.S0 = str;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void Q(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.T0 = str;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void R(Function0<Boolean> function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        this.N0 = function0;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void S() {
        i0().r6();
    }

    public void T(SmartRoute smartRoute) {
        Intrinsics.checkNotNullParameter(smartRoute, "");
    }

    public void U(SmartRoute smartRoute) {
        Intrinsics.checkNotNullParameter(smartRoute, "");
    }

    public abstract void V(SmartRoute smartRoute);

    public void W(SmartRoute smartRoute) {
        Intrinsics.checkNotNullParameter(smartRoute, "");
    }

    public boolean X() {
        return false;
    }

    public final boolean Y() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"template_play_page", "template_preview_page", "lock_template", "edit_more"}).contains(this.f0.getValue());
    }

    public void Z(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        ((BaseEditExportController$goExport$1) function0).invoke();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void a(BenefitResource benefitResource) {
        Intrinsics.checkNotNullParameter(benefitResource, "");
        ((ArrayList) this.l0).add(benefitResource);
    }

    public final void b0(final Function0<Unit> function0) {
        EditMaterialLostUtil editMaterialLostUtilInvoke = this.p.invoke();
        List<SegmentVideo> listB = editMaterialLostUtilInvoke.b();
        StringBuilder sb = new StringBuilder("materialLostUtil = ");
        sb.append(editMaterialLostUtilInvoke);
        sb.append(", lostMaterials size = ");
        ArrayList arrayList = (ArrayList) listB;
        sb.append(arrayList.size());
        BLog.i("BaseEditExportController", sb.toString());
        if (!(!arrayList.isEmpty())) {
            function0.invoke();
            return;
        }
        if (((EditMaterialLostConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(EditMaterialLostABSettings.class))).b()) {
            EditMaterialLostUtil.h(editMaterialLostUtilInvoke, listB, editMaterialLostUtilInvoke.e, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckLostMaterial$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    function0.invoke();
                    return Unit.INSTANCE;
                }
            }, 20);
        } else {
            editMaterialLostUtilInvoke.k(listB, null, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckLostMaterial$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    function0.invoke();
                    return Unit.INSTANCE;
                }
            }, null);
        }
        String strT = ((SegmentVideo) arrayList.get(0)).O().t();
        Intrinsics.checkNotNull(strT);
        String lowerCase = StringsKt__StringsKt.substringAfterLast$default(strT, '.', (String) null, 2, (Object) null).toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "");
        w0("lost_material", lowerCase);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void c(boolean z) {
        Boolean value = ((VideoMattingViewModel) this.F.getValue()).s.getValue();
        Boolean bool = Boolean.FALSE;
        boolean z2 = Intrinsics.areEqual(value, bool) || Intrinsics.areEqual(((VideoMattingViewModel) this.G.getValue()).s.getValue(), bool);
        UgCampaignParams.Companion companion = UgCampaignParams.i;
        Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
        companion.getClass();
        UgCampaignParams ugCampaignParamsB = UgCampaignParams.Companion.b(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent);
        String str = ugCampaignParamsB != null ? ugCampaignParamsB.f100531a : null;
        if (z && !this.H0 && !z2) {
            EditViewModel editViewModel = (EditViewModel) this.u.getValue();
            String str2 = l0().f89443c;
            EditReportManager.f88945a.getClass();
            EditViewModel.m6(editViewModel, false, str2, null, EditReportManager.f0, str, 1008);
        }
        this.H0 = false;
    }

    public final void c0(final Function0<Unit> function0) throws Resources.NotFoundException {
        boolean z;
        ITrackComponent iTrackComponent = (ITrackComponent) this.f91362g.e(Reflection.getOrCreateKotlinClass(IEditTrackComponent.class));
        if (iTrackComponent != null) {
            iTrackComponent.x();
        }
        SPIService sPIService = SPIService.INSTANCE;
        Function0 function02 = null;
        if (!((BaseClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getExportOptimizeConfig().e()) {
            final Function0<Unit> function03 = new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doExportCheck$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    this.e.b0(function0);
                    return Unit.INSTANCE;
                }
            };
            if (!((Boolean) new Function0<Boolean>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$shouldCleanCache$1
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                /* JADX WARN: Removed duplicated region for block: B:13:0x0046  */
                @Override // kotlin.jvm.functions.Function0
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Boolean invoke() {
                    /*
                        r7 = this;
                        java.io.File r0 = android.os.Environment.getExternalStorageDirectory()
                        java.lang.String r0 = r0.getAbsolutePath()
                        long r5 = com.bytedance.common.utility.io.IOUtils.getAvailableBytes(r0)
                        com.vega.edit.editpage.controller.BaseEditExportController r0 = r7.e
                        long r3 = r0.j0()
                        r1 = 0
                        int r0 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
                        if (r0 <= 0) goto L46
                        com.vega.edit.editpage.controller.BaseEditExportController r0 = r7.e
                        long r1 = r0.j0()
                        int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                        if (r0 >= 0) goto L46
                        com.vega.libfiles.files.BaseFileAbility r0 = com.vega.libfiles.files.BaseFileAbility.f110035a
                        r0.getClass()
                        long r0 = com.vega.libfiles.files.BaseFileAbility.b
                        long r5 = r5 + r0
                        com.vega.edit.editpage.controller.BaseEditExportController r0 = r7.e
                        long r1 = r0.j0()
                        int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                        if (r0 <= 0) goto L46
                        com.vega.edit.editpage.controller.BaseEditExportController r0 = r7.e
                        com.lemon.lv.config.ClientSetting r0 = r0.D0
                        com.lemon.lv.editor.FileCacheClean r0 = r0.Z()
                        boolean r0 = r0.f59410a
                        if (r0 == 0) goto L46
                        r0 = 1
                    L41:
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    L46:
                        r0 = 0
                        goto L41
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$shouldCleanCache$1.invoke():java.lang.Object");
                }
            }.invoke()).booleanValue()) {
                if (!x0()) {
                    function03.invoke();
                    return;
                }
                ConfirmCloseDialog confirmCloseDialog = new ConfirmCloseDialog(this.b, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$4
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final /* bridge */ /* synthetic */ Unit invoke() {
                        return Unit.INSTANCE;
                    }
                }, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$5
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function0
                    public final /* bridge */ /* synthetic */ Unit invoke() {
                        return Unit.INSTANCE;
                    }
                }, function02, 24);
                confirmCloseDialog.m(ModuleCommonKt.b(R.string.mvx));
                confirmCloseDialog.k(ModuleCommonKt.b(R.string.moo));
                confirmCloseDialog.p = false;
                confirmCloseDialog.setCancelable(false);
                a0(confirmCloseDialog);
                w0("not_enough_disk_space", "need:" + m0().m6());
                this.G0 = true;
                return;
            }
            ConfirmCloseDialog confirmCloseDialog2 = new ConfirmCloseDialog(this.b, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    function03.invoke();
                    ReportManagerWrapper.INSTANCE.onEvent("shoot_insufficient_popup", MapsKt__MapsJVMKt.mapOf(TuplesKt.to("action_type", "cancel")));
                    return Unit.INSTANCE;
                }
            }, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$2
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    ReportManagerWrapper.INSTANCE.onEvent("shoot_insufficient_popup", MapsKt__MapsJVMKt.mapOf(TuplesKt.to("action_type", "confirm")));
                    BaseEditExportController baseEditExportController = this.e;
                    baseEditExportController.e.g(baseEditExportController.b, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorage$2.1
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final /* bridge */ /* synthetic */ Unit invoke() {
                            return Unit.INSTANCE;
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, function02, 24);
            String string = this.V0.getString(R.string.mrk);
            Intrinsics.checkNotNullExpressionValue(string, "");
            confirmCloseDialog2.q(string);
            String string2 = this.V0.getString(R.string.q0l);
            Intrinsics.checkNotNullExpressionValue(string2, "");
            confirmCloseDialog2.m(string2);
            String string3 = this.V0.getString(R.string.lst);
            Intrinsics.checkNotNullExpressionValue(string3, "");
            confirmCloseDialog2.k(string3);
            String string4 = this.V0.getString(R.string.lhs);
            Intrinsics.checkNotNullExpressionValue(string4, "");
            confirmCloseDialog2.l(string4);
            confirmCloseDialog2.s = Integer.valueOf(this.V0.getColor(R.color.km_));
            confirmCloseDialog2.t = Integer.valueOf(this.V0.getColor(R.color._u));
            confirmCloseDialog2.v = Integer.valueOf(Color.parseColor("#343434"));
            confirmCloseDialog2.w = Integer.valueOf(this.V0.getColor(R.color._e));
            confirmCloseDialog2.z = Integer.valueOf(this.V0.getColor(R.color.kmg));
            confirmCloseDialog2.y = Integer.valueOf(this.V0.getColor(R.color.sc));
            confirmCloseDialog2.p = true;
            confirmCloseDialog2.setCancelable(false);
            a0(confirmCloseDialog2);
            ReportManagerWrapper.INSTANCE.onEvent("shoot_insufficient_popup", MapsKt__MapsJVMKt.mapOf(TuplesKt.to("action_type", "show")));
            return;
        }
        final Function0<Unit> function04 = new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doExportCheck$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                this.e.b0(function0);
                return Unit.INSTANCE;
            }
        };
        long availableBytes = IOUtils.getAvailableBytes(Environment.getExternalStorageDirectory().getAbsolutePath());
        m0().t6();
        ExportOptimizeConfig exportOptimizeConfig = ((BaseClientSetting) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getExportOptimizeConfig();
        float fJ0 = (long) ((j0() * exportOptimizeConfig.g()) + exportOptimizeConfig.f());
        long jC = (long) (((!ImageUtilKt.a() || FileUtilWithAndroid11Kt.h()) ? exportOptimizeConfig.c() : exportOptimizeConfig.b()) * fJ0);
        float f = 1024;
        EditReportManager.f88945a.getClass();
        EditReportManager.V0 = (availableBytes / 1024.0f) / f;
        EditReportManager.W0 = (fJ0 / 1024.0f) / f;
        StringBuilder sb = new StringBuilder("doCheckStorageNew,availableSize=");
        sb.append(EditReportManager.V0);
        sb.append(" estimatedSize=");
        sb.append(EditReportManager.W0);
        sb.append(" interceptMinSpace=");
        sb.append(exportOptimizeConfig.d());
        sb.append(" needSpace=");
        sb.append((jC / 1024.0f) / f);
        sb.append(" cacheSize=");
        BaseFileAbility.f110035a.getClass();
        sb.append((BaseFileAbility.b / 1024.0f) / f);
        BLog.i("BaseEditExportController", sb.toString());
        StorageUtil.f79678a.getClass();
        final boolean z2 = Build.VERSION.SDK_INT >= 26;
        if (availableBytes < ((long) (exportOptimizeConfig.d() * f * f)) && availableBytes != 0) {
            ConfirmCloseDialog confirmCloseDialog3 = new ConfirmCloseDialog(this.b, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$1
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    this.e.w0("no_space_intercept", EventReport.DIALOG_CLOSE);
                    return Unit.INSTANCE;
                }
            }, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    if (z2) {
                        StorageUtil storageUtil = StorageUtil.f79678a;
                        AbsEditActivity absEditActivity = this.b;
                        storageUtil.getClass();
                        StorageUtil.d(absEditActivity);
                        this.w0("no_space_intercept", "clear_space");
                    }
                    return Unit.INSTANCE;
                }
            }, (Function0) null, 24);
            confirmCloseDialog3.m(FunctionsKt.b(R.string.ofk));
            if (z2) {
                confirmCloseDialog3.k(FunctionsKt.b(R.string.oe__res_0x7f1222ac));
                confirmCloseDialog3.l(FunctionsKt.b(R.string.ofl));
                z = true;
                confirmCloseDialog3.p = true;
            } else {
                z = true;
                confirmCloseDialog3.k(FunctionsKt.b(R.string.ofl));
                confirmCloseDialog3.p = false;
            }
            confirmCloseDialog3.o();
            confirmCloseDialog3.r = z;
            confirmCloseDialog3.setCancelable(z);
            a0(confirmCloseDialog3);
            w0("no_space_intercept", "show");
            return;
        }
        if (availableBytes >= jC) {
            function04.invoke();
            return;
        }
        if (availableBytes + BaseFileAbility.b >= jC) {
            ConfirmCloseDialog confirmCloseDialog4 = new ConfirmCloseDialog(this.b, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$4
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    this.e.w0("no_space_hint_clear_cache", EventReport.DIALOG_CLOSE);
                    return Unit.INSTANCE;
                }
            }, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$5
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    BaseEditExportController baseEditExportController = this.e;
                    FileScavenger fileScavenger = baseEditExportController.e;
                    AbsEditActivity absEditActivity = baseEditExportController.b;
                    final Function0<Unit> function05 = function04;
                    fileScavenger.g(absEditActivity, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$5.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final Unit invoke() {
                            function05.invoke();
                            return Unit.INSTANCE;
                        }
                    });
                    this.e.w0("no_space_hint_clear_cache", "clear_cache");
                    return Unit.INSTANCE;
                }
            }, (Function0) null, 24);
            confirmCloseDialog4.m(FunctionsKt.b(R.string.oe_));
            confirmCloseDialog4.k(FunctionsKt.b(R.string.oe__res_0x7f1222ad));
            confirmCloseDialog4.o();
            confirmCloseDialog4.p = false;
            confirmCloseDialog4.r = true;
            confirmCloseDialog4.setCancelable(false);
            a0(confirmCloseDialog4);
            w0("no_space_hint_clear_cache", "show");
            return;
        }
        ConfirmCloseDialog confirmCloseDialog5 = new ConfirmCloseDialog(this.b, new Function0<Unit>(this) { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$7
            public final /* synthetic */ BaseEditExportController f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.f = this;
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                function04.invoke();
                this.f.w0("no_space_waring", "force_export");
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$8
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                if (z2) {
                    StorageUtil storageUtil = StorageUtil.f79678a;
                    AbsEditActivity absEditActivity = this.b;
                    storageUtil.getClass();
                    StorageUtil.d(absEditActivity);
                    this.w0("no_space_waring", "clear_space");
                } else {
                    this.w0("no_space_waring", EventReport.DIALOG_CLOSE);
                }
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$doCheckStorageNew$9
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                this.e.w0("no_space_waring", EventReport.DIALOG_CLOSE);
                return Unit.INSTANCE;
            }
        }, 16);
        confirmCloseDialog5.m(FunctionsKt.b(R.string.ofk));
        if (z2) {
            confirmCloseDialog5.k(FunctionsKt.b(R.string.oe__res_0x7f1222ac));
        } else {
            confirmCloseDialog5.k(FunctionsKt.b(R.string.ofl));
        }
        confirmCloseDialog5.p = true;
        confirmCloseDialog5.l(FunctionsKt.b(R.string.oe__res_0x7f1222ae));
        confirmCloseDialog5.o();
        confirmCloseDialog5.r = true;
        confirmCloseDialog5.setCancelable(true);
        a0(confirmCloseDialog5);
        w0("no_space_waring", "show");
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:76:0x00bc */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v26, types: [T, com.vega.edit.base.utils.ExportDigitalHumanNum] */
    /* JADX WARN: Type inference failed for: r0v5, types: [T, com.vega.edit.base.utils.ExportDigitalHumanNum] */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.Boolean] */
    /* JADX WARN: Type inference failed for: r12v0, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r14v0 */
    /* JADX WARN: Type inference failed for: r14v1, types: [com.lemon.lv.clipmonetize.wrapper.BenefitResource] */
    /* JADX WARN: Type inference failed for: r14v2, types: [com.lemon.lv.clipmonetize.wrapper.BenefitResource] */
    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void d() {
        String strB;
        this.k.invoke();
        String str = null;
        BuildersKt__Builders_commonKt.launch$default(this.b, null, null, new BaseEditExportController$clickExport$1(this, null), 3, null);
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("BaseEditExportController", " click to export ");
        }
        ScanLogsUtil.f58790a.getClass();
        ScanLogsUtil.a("click_export_btn");
        IPanel iPanelInvoke = this.j.invoke();
        if (iPanelInvoke != null) {
            if ((iPanelInvoke instanceof TextPanel) || (iPanelInvoke instanceof NewTextPanelV2)) {
                iPanelInvoke.n0().G();
            } else {
                if (iPanelInvoke instanceof HandwritePanel) {
                    iPanelInvoke.n0().G();
                    return;
                }
                if ((iPanelInvoke instanceof MainVideoCustomizedMattingPanel) || (iPanelInvoke instanceof SubVideoCustomizedMattingPanel) || (iPanelInvoke instanceof VideoMattingPanel)) {
                    (q0().o7() != null ? (VideoMattingViewModel) this.F.getValue() : (VideoMattingViewModel) this.G.getValue()).t6();
                } else if (iPanelInvoke instanceof MainVideoBeautyPanel) {
                    DraftLogUtils.f88073a.getClass();
                    DraftLogUtils.c("BaseEditExportController", "is MainVideoBeautyPanel");
                    str = null;
                    ISession.DefaultImpls.C(p0(), true, null, false, null, false, null, null, 126);
                    if (LVFigureGlobalInjectModule.b.b().d() && ((BaseManualFigureViewModel) this.D.getValue()).x7()) {
                        ((BaseManualFigureViewModel) this.D.getValue()).u7(2, null);
                        return;
                    }
                } else if (iPanelInvoke instanceof SubVideoBeautyPanel) {
                    DraftLogUtils.f88073a.getClass();
                    DraftLogUtils.c("BaseEditExportController", "is SubVideoBeautyPanel");
                    str = null;
                    ISession.DefaultImpls.C(p0(), true, null, false, null, false, null, null, 126);
                    if (LVFigureGlobalInjectModule.b.b().d() && ((BaseManualFigureViewModel) this.E.getValue()).x7()) {
                        ((BaseManualFigureViewModel) this.E.getValue()).u7(2, null);
                        return;
                    }
                } else if (iPanelInvoke instanceof StickerPanel) {
                    Job job = ((StickerViewModel) this.C.getValue()).R0;
                    if (job != null && job.isActive()) {
                        ((StickerViewModel) this.C.getValue()).K6(false);
                    }
                } else if ((iPanelInvoke instanceof MainVideoSmartCropPanel) || (iPanelInvoke instanceof SubVideoSmartCropPanel)) {
                    iPanelInvoke.n0().G();
                    return;
                }
            }
        }
        Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
        ref$ObjectRef.element = ExportDigitalHumanNum.f88968c;
        if (((BaseClientSetting) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), str)).getEditorDigitalHumanAbtestConfig().a()) {
            DigitalHumanUtils digitalHumanUtils = DigitalHumanUtils.f88933a;
            Draft draftL = p0().l();
            digitalHumanUtils.getClass();
            ref$ObjectRef.element = DigitalHumanUtils.q(draftL);
        }
        ResolutionViewModel resolutionViewModelM0 = m0();
        Draft draftL2 = p0().l();
        if (draftL2 == null || (strB = draftL2.b()) == null) {
            strB = "";
        }
        resolutionViewModelM0.F6(strB);
        v0(new BaseEditExportController$clickExport$2(this, ref$ObjectRef));
        Iterator it = ((ArrayList) m0().A6()).iterator();
        while (it.hasNext()) {
            IBusiness.BizVipFeature bizVipFeature = (IBusiness.BizVipFeature) it.next();
            ?? r14 = Intrinsics.areEqual(bizVipFeature.f131821a, "ai_ultra_hd_export") ? BusinessResourcesKt.A1 : str;
            EditReportManager editReportManager = EditReportManager.f88945a;
            String str2 = bizVipFeature.f131821a;
            EditReportManager.t1(editReportManager, str2, "use", "export_quality", str2, str, str, str, str, str, r14, str, 1572848);
        }
    }

    public void d0(final boolean z, final boolean z2, final boolean z3, final boolean z4) throws Resources.NotFoundException {
        IGuide.DefaultImpls.a((IGuide) this.Q.getValue(), false, false, 5);
        PermissionUtil permissionUtil = PermissionUtil.f60974a;
        permissionUtil.getClass();
        final List listF = PermissionUtil.f();
        if (PermissionUtil.j(this.b, listF)) {
            c0(new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$export$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() throws JSONException {
                    BaseEditExportController.g0(this.e, z3, z, z4, z2);
                    return Unit.INSTANCE;
                }
            });
            return;
        }
        PermissionRequest.Companion companion = PermissionRequest.h;
        AbsEditActivity absEditActivity = this.b;
        companion.getClass();
        PermissionUtil.p(permissionUtil, PermissionRequest.Companion.a(absEditActivity, "Export", listF), new Function1<PermissionResult, Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$export$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(PermissionResult permissionResult) throws Resources.NotFoundException {
                PermissionResult permissionResult2 = permissionResult;
                Intrinsics.checkNotNullParameter(permissionResult2, "");
                if (permissionResult2.f60971a.containsAll(listF)) {
                    final BaseEditExportController baseEditExportController = this;
                    final boolean z5 = z3;
                    final boolean z6 = z;
                    final boolean z7 = z4;
                    final boolean z8 = z2;
                    baseEditExportController.c0(new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$export$2.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final Unit invoke() throws JSONException {
                            BaseEditExportController.g0(baseEditExportController, z5, z6, z7, z8);
                            return Unit.INSTANCE;
                        }
                    });
                } else {
                    ScanLogsUtil.f58790a.getClass();
                    ScanLogsUtil.a("no_permission");
                }
                return Unit.INSTANCE;
            }
        }, 2);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void e() {
        i0().h8();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void f(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        q0().z8(str);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void g(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        q0().A8(str);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void h(LinkedHashMap<ExportInnterceptPrority, IExportInterceptor> linkedHashMap) {
        Intrinsics.checkNotNullParameter(linkedHashMap, "");
        BLog.e("BaseEditExportController", "error exportWithInterceptors!");
    }

    public final Bundle h0() {
        Bundle bundle = new Bundle();
        if (((String) this.n0.getValue()).length() > 0) {
            bundle.putString("anchor_edit_type", (String) this.n0.getValue());
        }
        if (((String) this.o0.getValue()).length() > 0) {
            bundle.putString("anchor_effect_id", (String) this.o0.getValue());
        }
        if (((String) this.p0.getValue()).length() > 0) {
            bundle.putString("anchor_effect", (String) this.p0.getValue());
        }
        String strTakeIfNotEmpty = ExtentionKt.takeIfNotEmpty((String) this.q0.getValue());
        if (strTakeIfNotEmpty != null) {
            bundle.putString("is_pass_anchor_popup", strTakeIfNotEmpty);
        }
        String str = (String) this.r0.getValue();
        if (ExtentionKt.isNotNullOrEmpty(str) && str != null) {
            bundle.putString("anchor_key", str);
        }
        return bundle;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final String i() {
        String adType;
        Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
        if (ExtentionKt.isNotNullOrEmpty(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("ad_type") : null)) {
            Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
            adType = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2.getStringExtra("ad_type") : null;
            Intrinsics.checkNotNull(adType);
            return adType;
        }
        AdDraftExtraInfo adDraftExtraInfo = this.R0;
        if (!ExtentionKt.isNotNullOrEmpty(adDraftExtraInfo != null ? adDraftExtraInfo.getAdType() : null)) {
            return "";
        }
        AdDraftExtraInfo adDraftExtraInfo2 = this.R0;
        adType = adDraftExtraInfo2 != null ? adDraftExtraInfo2.getAdType() : null;
        Intrinsics.checkNotNull(adType);
        return adType;
    }

    public final IEditTopBarComponent i0() {
        Object objE = this.f91362g.e(Reflection.getOrCreateKotlinClass(IEditTopBarComponent.class));
        Intrinsics.checkNotNull(objE);
        return (IEditTopBarComponent) objE;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final String j() {
        String adsDraftId;
        Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
        if (ExtentionKt.isNotNullOrEmpty(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("ads_draft_id") : null)) {
            Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
            adsDraftId = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2.getStringExtra("ads_draft_id") : null;
            Intrinsics.checkNotNull(adsDraftId);
            return adsDraftId;
        }
        AdDraftExtraInfo adDraftExtraInfo = this.R0;
        if (!ExtentionKt.isNotNullOrEmpty(adDraftExtraInfo != null ? adDraftExtraInfo.getAdsDraftId() : null)) {
            return "";
        }
        AdDraftExtraInfo adDraftExtraInfo2 = this.R0;
        adsDraftId = adDraftExtraInfo2 != null ? adDraftExtraInfo2.getAdsDraftId() : null;
        Intrinsics.checkNotNull(adsDraftId);
        return adsDraftId;
    }

    public final long j0() {
        double d2 = 1024.0f;
        return (long) (m0().m6() * d2 * d2);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final String k() {
        String adsTemplateId;
        Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
        if (ExtentionKt.isNotNullOrEmpty(intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("ads_template_id") : null)) {
            Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
            adsTemplateId = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent2.getStringExtra("ads_template_id") : null;
            Intrinsics.checkNotNull(adsTemplateId);
            return adsTemplateId;
        }
        AdDraftExtraInfo adDraftExtraInfo = this.R0;
        if (!ExtentionKt.isNotNullOrEmpty(adDraftExtraInfo != null ? adDraftExtraInfo.getAdsTemplateId() : null)) {
            return "";
        }
        AdDraftExtraInfo adDraftExtraInfo2 = this.R0;
        adsTemplateId = adDraftExtraInfo2 != null ? adDraftExtraInfo2.getAdsTemplateId() : null;
        Intrinsics.checkNotNull(adsTemplateId);
        return adsTemplateId;
    }

    public final String k0() {
        if (!Y()) {
            return (((Boolean) this.a0.getValue()).booleanValue() || ((Boolean) this.U.getValue()).booleanValue()) ? "intelligent_drafts" : ((Number) this.k0.getValue()).intValue() == 1 ? "template_drafts" : this.f91360c;
        }
        String stringExtra = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b).getStringExtra("enter_from");
        return stringExtra == null ? "template_edit_pay" : stringExtra;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Map<String, String> l() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (((String) this.n0.getValue()).length() > 0) {
            linkedHashMap.put("anchor_instruction_effect_type", this.n0.getValue());
        }
        if (((String) this.o0.getValue()).length() > 0) {
            linkedHashMap.put("anchor_effect_id", this.o0.getValue());
        }
        if (((String) this.p0.getValue()).length() > 0) {
            linkedHashMap.put("anchor_effect", this.p0.getValue());
        }
        String strTakeIfNotEmpty = ExtentionKt.takeIfNotEmpty((String) this.q0.getValue());
        if (strTakeIfNotEmpty != null) {
            linkedHashMap.put("is_pass_anchor_popup", strTakeIfNotEmpty);
        }
        String str = (String) this.r0.getValue();
        if (ExtentionKt.isNotNullOrEmpty(str) && str != null) {
            linkedHashMap.put("anchor_key", str);
        }
        return linkedHashMap;
    }

    public final ReportViewModel l0() {
        return (ReportViewModel) this.q.getValue();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Map<String, Object> m() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        if (((String) this.u0.getValue()).length() > 0) {
            linkedHashMap.put("business_template_pay_status", this.u0.getValue());
        }
        if (((String) this.t0.getValue()).length() > 0) {
            linkedHashMap.put("business_template_pay_type", this.t0.getValue());
        }
        if (((String) this.v0.getValue()).length() > 0) {
            linkedHashMap.put("business_template_cate", this.v0.getValue());
        }
        if (((Number) this.w0.getValue()).longValue() >= 0) {
            linkedHashMap.put("business_template_origin_price", Long.valueOf(((Number) this.w0.getValue()).longValue()));
        }
        if (((Number) this.x0.getValue()).longValue() >= 0) {
            linkedHashMap.put("business_template_price", Long.valueOf(((Number) this.x0.getValue()).longValue()));
        }
        return linkedHashMap;
    }

    public final ResolutionViewModel m0() {
        return (ResolutionViewModel) this.r.getValue();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final String n() {
        return this.z0;
    }

    public HashMap<String, Object> n0() {
        return this.Q0.invoke();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final String o() {
        return (String) this.s0.getValue();
    }

    public final String o0() {
        String stringExtra;
        if (StringsKt__StringsJVMKt.startsWith$default((String) this.c0.getValue(), "push_", false, 2, null) && ((String) this.d0.getValue()).length() > 0) {
            return (String) this.d0.getValue();
        }
        Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
        return (intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent == null || (stringExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("rule_id")) == null) ? "" : stringExtra;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final String p() {
        return this.y0;
    }

    public final ISession p0() {
        return (ISession) this.P.getValue();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Integer q() {
        return this.C0;
    }

    public final EditUIViewModel q0() {
        return (EditUIViewModel) this.s.getValue();
    }

    public VipExportLoading r0() {
        return (VipExportLoading) this.P0.getValue();
    }

    /* JADX DEBUG: Type inference failed for r0v0. Raw type applied. Possible types: kotlin.jvm.functions.Function0<? extends java.util.HashMap<java.lang.String, java.lang.Object>>, kotlin.jvm.functions.Function0<java.util.HashMap<java.lang.String, java.lang.Object>> */
    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Function0<HashMap<String, Object>> s() {
        return this.Q0;
    }

    public final WaterMarkViewModel s0() {
        return (WaterMarkViewModel) this.M.getValue();
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Long t() {
        return (Long) this.Z.getValue();
    }

    public final Lazy t0(Object obj, String str) {
        return BundleExKt.c(this.b, str, obj);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final boolean u() {
        return this.m0;
    }

    public void u0(boolean z) {
        LVVESingleFunctionType lVVESingleFunctionTypeQ6;
        VideoEffectViewModel videoEffectViewModel = (VideoEffectViewModel) this.y.getValue();
        String value = ((VarHeightViewModel) this.z.getValue()).h.getValue();
        if (value == null) {
            value = "";
        }
        String str = (((VarHeightViewModel) this.z.getValue()).f89460c.getValue() == null || !Intrinsics.areEqual(((VarHeightViewModel) this.z.getValue()).f89460c.getValue(), ((VarHeightViewModel) this.z.getValue()).e.getValue())) ? "original" : "panel_up";
        videoEffectViewModel.getClass();
        if (videoEffectViewModel.h0) {
            videoEffectViewModel.O6(value, str);
            videoEffectViewModel.Q7();
        }
        String str2 = null;
        if (!((IImageEditService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IImageEditService.class), null)).f(this.b)) {
            Object objE = this.f91362g.e(Reflection.getOrCreateKotlinClass(IDockManager.class));
            Intrinsics.checkNotNull(objE);
            ((IDockManager) objE).e();
        }
        SubtitleViewModel subtitleViewModel = (SubtitleViewModel) this.A.getValue();
        subtitleViewModel.f112674c.b(new ReportAudioToTextResult(subtitleViewModel.h));
        EditReportManager editReportManager = EditReportManager.f88945a;
        AbsSingleFunctionEditSharedVM absSingleFunctionEditSharedVM = (AbsSingleFunctionEditSharedVM) this.N.getValue();
        int iOrdinal = (absSingleFunctionEditSharedVM != null ? absSingleFunctionEditSharedVM.q6() : LVVESingleFunctionType.LVVESingleNone).ordinal();
        editReportManager.getClass();
        EditReportManager.f88946c = iOrdinal;
        AbsSingleFunctionEditSharedVM absSingleFunctionEditSharedVM2 = (AbsSingleFunctionEditSharedVM) this.N.getValue();
        if (absSingleFunctionEditSharedVM2 != null && (lVVESingleFunctionTypeQ6 = absSingleFunctionEditSharedVM2.q6()) != null) {
            int i = WhenMappings.f91389a[lVVESingleFunctionTypeQ6.ordinal()];
            if (i == 1) {
                str2 = "click_text_sticker_option";
            } else if (i == 2) {
                str2 = "text_cut";
            }
        }
        IEditPerformanceViewModel iEditPerformanceViewModel = (IEditPerformanceViewModel) this.B.getValue();
        Integer numValueOf = Integer.valueOf(m0().y6());
        Integer numX6 = m0().x6();
        Integer numValueOf2 = Integer.valueOf(m0().v6());
        String str3 = this.f91360c;
        String str4 = l0().i;
        String str5 = l0().k;
        String str6 = l0().m;
        Map<String, String> map = l0().n;
        Map<String, String> mapL = l();
        Map<String, Object> mapM = m();
        String strO = o();
        Bundle bundleY1 = this.i.Y1(p0());
        String strW6 = m0().w6();
        long jLongValue = t().longValue();
        String str7 = l0().f89445g;
        Intent intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this.b);
        int intExtra = intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null ? intentINVOKEVIRTUAL_com_vega_edit_editpage_controller_BaseEditExportController_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getIntExtra("from_template_tutorial_bond", 0) : 0;
        HashMap<String, Object> mapN0 = n0();
        String str8 = l0().t;
        Map<String, Object> map2 = l0().x;
        String strI = i();
        String str9 = l0().w;
        Map<String, String> map3 = l0().z;
        WaterMarkViewModel waterMarkViewModelS0 = s0();
        IEditPerformanceViewModel.z6(iEditPerformanceViewModel, numValueOf, numX6, numValueOf2, str3, str4, str5, str6, map, mapL, mapM, strO, bundleY1, strW6, jLongValue, str7, intExtra, z, mapN0, str8, map2, strI, str9, map3, waterMarkViewModelS0 != null ? waterMarkViewModelS0.w6() : null, str2, 33554432);
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void v(String str) {
        Intrinsics.checkNotNullParameter(str, "");
    }

    public void v0(Function1<? super Boolean, Unit> function1) {
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final void w() {
    }

    public final void w0(String str, String str2) {
        String strB;
        ReportManagerWrapper reportManagerWrapper = ReportManagerWrapper.INSTANCE;
        Pair[] pairArr = new Pair[4];
        pairArr[0] = TuplesKt.to("edit_time", String.valueOf((System.currentTimeMillis() - this.F0) / 1000.0f));
        pairArr[1] = TuplesKt.to("reason", str);
        pairArr[2] = TuplesKt.to("msg", str2);
        Draft draftL = p0().l();
        if (draftL == null || (strB = draftL.b()) == null) {
            strB = "";
        }
        pairArr[3] = TuplesKt.to("project_id", strB);
        reportManagerWrapper.onEvent("export_intercept", MapsKt__MapsKt.mapOf(pairArr));
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Integer x() {
        return this.A0;
    }

    public boolean x0() {
        return (((double) IOUtils.getAvailableBytes(Environment.getExternalStorageDirectory().getAbsolutePath())) / 1024.0d) / 1024.0d < m0().m6() && !this.G0;
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Function0<Boolean> y() {
        return this.m;
    }

    public final void y0() {
        LvProgressDialog lvProgressDialog = this.O;
        if (lvProgressDialog != null) {
            Intrinsics.checkNotNull(lvProgressDialog);
            if (lvProgressDialog.isShowing()) {
                LvProgressDialog lvProgressDialog2 = this.O;
                Intrinsics.checkNotNull(lvProgressDialog2);
                if (lvProgressDialog2.v) {
                    return;
                }
            }
        }
        final LvProgressDialog lvProgressDialog3 = new LvProgressDialog(this.b, false, false, false, false, 30);
        this.O = lvProgressDialog3;
        String string = this.b.getString(R.string.l0k);
        Intrinsics.checkNotNullExpressionValue(string, "");
        lvProgressDialog3.C(string);
        String string2 = this.b.getString(R.string.lj_);
        Intrinsics.checkNotNullExpressionValue(string2, "");
        lvProgressDialog3.B(string2);
        String string3 = this.b.getString(R.string.lj__res_0x7f1210b6);
        Intrinsics.checkNotNullExpressionValue(string3, "");
        lvProgressDialog3.A(string3);
        lvProgressDialog3.v = true;
        lvProgressDialog3.setCancelable(false);
        lvProgressDialog3.s = new Function0<Unit>() { // from class: com.vega.edit.editpage.controller.BaseEditExportController$showExportZipLoadingDialog$1$1
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final /* bridge */ /* synthetic */ Unit invoke() {
                return Unit.INSTANCE;
            }
        };
        this.b.runOnUiThread(new Runnable() { // from class: X.07j
            @Override // java.lang.Runnable
            public final void run() {
                LvProgressDialog lvProgressDialog4 = lvProgressDialog3;
                if (new HeliosApiHook().preInvoke(300000, "com/vega/ui/dialog/LvProgressDialog", "show", lvProgressDialog4, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSUFMoUVyBYvVqFY2fxTeR88l91bAbtb3pBXciFQodDth4xFEBa/UTSDWUVWbN5SQyHw==")).isIntercept()) {
                    return;
                }
                lvProgressDialog4.show();
            }
        });
    }

    @Override // com.vega.edit.editpage.controller.export_refactor.BaseRefactorEditExportController
    public final Integer z() {
        return this.B0;
    }
}
package com.xt.edit;

import X.C06W;
import X.C1W6;
import X.InterfaceC39790ts;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Size;
import android.util.SizeF;
import android.view.KeyEvent;
import androidx.core.view.ViewCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.example.template.api.IPersonalTemplateManager;
import com.example.template.api.IPublishTemplate;
import com.example.template.data.ExtraTemplateMessage;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.lemon.lvoverseas.R;
import com.lm.components.lynx.msgcenter.LynxMsgCenter;
import com.retouch.layermanager.api.layer.BackgroundLayer;
import com.retouch.layermanager.api.layer.ILayerManager;
import com.retouch.layermanager.api.layer.Layer;
import com.retouch.layermanager.api.layer.LayerType;
import com.retouch.layermanager.api.layer.PictureLayer;
import com.retouch.layermanager.api.layer.data.LatestBackground;
import com.retouch.layermanager.api.transform.DefaultImageChangeListener;
import com.retouch.layermanager.api.transform.ITransformManager;
import com.retouch.layermanager.api.transform.ImageStatus;
import com.xt.edit.EditActivityViewModel;
import com.xt.edit.api.IBatchEditExporter;
import com.xt.edit.api.IEditReport;
import com.xt.edit.api.Picture;
import com.xt.edit.api.RenderSizeConfig;
import com.xt.edit.api.SavePhotoParams;
import com.xt.edit.api.background.IImportBackgroundData;
import com.xt.edit.api.modle.EditConfig;
import com.xt.edit.api.modle.EnvConfig;
import com.xt.edit.deeplink.DeeplinkManager;
import com.xt.edit.impl.EditHostImpl;
import com.xt.edit.transition.PictureSnapshot;
import com.xt.retouch.abtest.AbTestFacade;
import com.xt.retouch.account.api.IAccount;
import com.xt.retouch.api.template.cover.data.CoverTemplateInnerEditMode;
import com.xt.retouch.api.template.cover.inner.ICoverTemplateLogic;
import com.xt.retouch.apiservice.ApiService;
import com.xt.retouch.applauncher.api.AppContext;
import com.xt.retouch.basearchitect.viewmodel.Event;
import com.xt.retouch.baseflavorconfig.FlavorConfig;
import com.xt.retouch.baseui.animation.transition.NavTabTransition;
import com.xt.retouch.business.api.report.IBusinessPicReporter;
import com.xt.retouch.config.api.IConfigManager;
import com.xt.retouch.config.api.IPerformanceManager;
import com.xt.retouch.config.api.model.HighResABTest;
import com.xt.retouch.config.api.model.HighResABTestStrategies;
import com.xt.retouch.config.api.model.JsonConfig;
import com.xt.retouch.config.api.model.LayerNumConfig;
import com.xt.retouch.debug.api.IEffectAutoTest;
import com.xt.retouch.debug.api.bean.EffectAutoTestData;
import com.xt.retouch.draft.api.DraftSaveResult;
import com.xt.retouch.draft.api.IBoxDraftExporter;
import com.xt.retouch.draftbox.api.ImageDraftBoxManager;
import com.xt.retouch.edit.base.api.EditContext;
import com.xt.retouch.edit.base.api.IExitObserver;
import com.xt.retouch.edit.base.api.RequestRecommendResTaskType;
import com.xt.retouch.edit.base.api.host.SaveImageEvent;
import com.xt.retouch.edit.base.context.EditMode;
import com.xt.retouch.edit.base.deeplink.Deeplink;
import com.xt.retouch.edit.base.model.AppliedTemplateInfo;
import com.xt.retouch.edit.base.model.BatchSavingResult;
import com.xt.retouch.edit.base.model.PictureExporter;
import com.xt.retouch.edit.base.model.ThirdPartEditMode;
import com.xt.retouch.edit.base.portrait.FaceInfo;
import com.xt.retouch.edit.base.report.TemplateReport;
import com.xt.retouch.edit.base.util.ResolutionStrategy;
import com.xt.retouch.effect.api.IEffectProducer;
import com.xt.retouch.effect.api.IEffectProvider;
import com.xt.retouch.lib.log.XTLog;
import com.xt.retouch.painter.algorithm.v2.RecogClassificationResult;
import com.xt.retouch.painter.algorithm.v2.RecognitionC1Result;
import com.xt.retouch.painter.algorithm.v2.TagInfo;
import com.xt.retouch.painter.api.ITemplateSdk;
import com.xt.retouch.painter.api.SnapshotHandler;
import com.xt.retouch.painter.function.api.IPainterCommon;
import com.xt.retouch.painter.function.api.IPainterLayer;
import com.xt.retouch.painter.model.LayerParams;
import com.xt.retouch.painter.trace.EditFlow;
import com.xt.retouch.painter.trace.EffectFlow;
import com.xt.retouch.popup.api.IEditScenePopupController;
import com.xt.retouch.report.api.EventValue;
import com.xt.retouch.report.api.IAppEventReport;
import com.xt.retouch.report.api.IEventReport;
import com.xt.retouch.report.api.IPhotoImportReport;
import com.xt.retouch.scenes.api.IBaseScenesModel;
import com.xt.retouch.scenes.api.IEditActivityScenesModel;
import com.xt.retouch.scenes.api.MiddlePageRecorder;
import com.xt.retouch.scenes.api.draft.IDraftScenesModel;
import com.xt.retouch.scenes.api.jigsaw.IJigsawScenesModel;
import com.xt.retouch.subscribe.api.SubscribeApi;
import com.xt.retouch.subscribe.api.data.VipBenefitInfo;
import com.xt.retouch.template.TemplateProviderImpl;
import com.xt.retouch.util.CPUUtils;
import com.xt.retouch.util.CoroutineUtilsKt;
import com.xt.retouch.util.DimenExtensionsKt;
import com.xt.retouch.util.IdUtils;
import com.xt.retouch.util.KvSettingProvider;
import com.xt.retouch.util.MediaUtil;
import com.xt.retouch.util.PermissionUtil;
import com.xt.retouch.util.StatusBarUtils;
import com.xt.retouch.util.StorageUtils;
import com.xt.retouch.util.StringUtils;
import com.xt.retouch.util.ThreadUtils;
import com.xt.retouch.util.screenshot.ScreenShotListener;
import java.lang.ref.WeakReference;
import java.math.RoundingMode;
import java.text.Format;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$BooleanRef;
import kotlin.jvm.internal.Ref$IntRef;
import kotlin.random.Random;
import kotlin.ranges.RangesKt___RangesKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.flow.FlowKt__ShareKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;
import model.SaveResult;
import org.json.JSONObject;
import template.ITemplate;
import template.ITemplateGroup;

/* loaded from: classes33.dex */
public final class EditActivityViewModel extends ViewModel {
    public static final /* synthetic */ int w2 = 0;
    public final Lazy A;
    public String A0;
    public final MutableLiveData<Event<Object>> A1;
    public RecogClassificationResult B;
    public String B0;
    public Function2<? super Integer, ? super Boolean, Unit> B1;
    public RecogClassificationResult C;
    public boolean C0;
    public Function4<? super Integer, ? super Boolean, ? super Boolean, ? super IExitObserver, Unit> C1;
    public RecognitionC1Result D;
    public boolean D0;
    public final MutableLiveData<List<FaceInfo>> D1;
    public boolean E;
    public boolean E0;
    public final MutableLiveData<List<IPainterCommon.ImageSkeleton>> E1;
    public String F;
    public EditConfig F0;
    public final MutableLiveData<List<List<Integer>>> F1;
    public String G;
    public IEditActivityScenesModel G0;
    public final MutableLiveData<Integer> G1;
    public String H;
    public IJigsawScenesModel H0;
    public final MutableLiveData<Integer> H1;
    public String I;
    public IDraftScenesModel I0;
    public final MutableLiveData I1;

    /* renamed from: J, reason: collision with root package name */
    public DeeplinkManager f136341J;
    public ILayerManager J0;
    public final MutableLiveData<Boolean> J1;
    public boolean K;
    public ITransformManager K0;
    public final MutableLiveData<Boolean> K1;
    public String L;
    public IEffectProducer L0;
    public final MutableLiveData<Boolean> L1;
    public SnapshotHandler.Snapshot M;
    public IEffectProvider M0;
    public final MutableLiveData<Integer> M1;
    public final MutableLiveData<Float> N;
    public final MutableLiveData<Boolean> N0;
    public final MutableLiveData<Integer> N1;
    public boolean O;
    public AppContext O0;
    public Bitmap O1;
    public IImportBackgroundData P;
    public IConfigManager P0;
    public final EditActivityViewModel$currentFragmentName$1 P1;
    public boolean Q;
    public IAppEventReport Q0;
    public final MutableLiveData<Integer> Q1;
    public String R;
    public IEventReport R0;
    public final MutableLiveData<String> R1;
    public String S;
    public IEditReport S0;
    public String S1;
    public String T;
    public IPerformanceManager T0;
    public boolean T1;
    public final MutableLiveData<Integer> U;
    public ImageDraftBoxManager U0;
    public Function1<? super KeyEvent, Boolean> U1;
    public final MutableLiveData<Integer> V;
    public IPublishTemplate V0;
    public Boolean V1;
    public final LiveData<Float> W;
    public ITemplateSdk W0;
    public final MutableLiveData<Boolean> W1;

    /* renamed from: X, reason: collision with root package name */
    public int f136342X;
    public SubscribeApi X0;
    public final Lazy X1;
    public boolean Y;
    public VipBenefitInfo Y0;
    public String Y1;
    public int Z;
    public InterfaceC39790ts<ICoverTemplateLogic> Z0;
    public boolean Z1;

    /* renamed from: a, reason: collision with root package name */
    public final IBusinessPicReporter f136343a;
    public String a0;
    public boolean a1;
    public final MutableLiveData<Integer> a2;
    public IEffectAutoTest b;
    public boolean b0;
    public boolean b1;
    public LayerNumConfig b2;

    /* renamed from: c, reason: collision with root package name */
    public IAccount f136344c;
    public boolean c0;
    public String c1;
    public final MutableLiveData<Boolean> c2;

    /* renamed from: d, reason: collision with root package name */
    public PictureExporter f136345d;
    public String d0;
    public String d1;
    public final Lazy d2;
    public TemplateReport e;
    public String e0;
    public String e1;
    public final long e2;
    public IPersonalTemplateManager f;
    public ArrayList<String> f0;
    public String f1;
    public final MutableLiveData<Pair<ImageStatus, LayerParams>> f2;

    /* renamed from: g, reason: collision with root package name */
    public IBoxDraftExporter f136346g;
    public String g0;
    public final MutableLiveData<ThirdPartEditMode> g1;
    public final List<Layer> g2;
    public ImageDraftBoxManager h;
    public String h0;
    public final MutableLiveData<String> h1;
    public boolean h2;
    public IEditScenePopupController i;
    public final MutableLiveData<DraftSaveResult> i0;
    public boolean i1;
    public Function1<? super NavTabTransition, Unit> i2;
    public EditContext j;
    public final MutableLiveData<Boolean> j0;
    public String j1;
    public Function1<? super NavTabTransition, Unit> j2;
    public final MutableSharedFlow<Integer> k;
    public final MutableLiveData<Boolean> k0;
    public String k1;
    public IPhotoImportReport k2;
    public final SharedFlow<Integer> l;
    public final MutableLiveData<Boolean> l0;
    public String l1;
    public boolean l2;
    public WeakReference<EditActivity> m;
    public final ResolutionStrategy m0;
    public String m1;
    public String m2;
    public MutableLiveData<Boolean> n;
    public final MutableLiveData<Boolean> n0;
    public final MutableLiveData<AppliedTemplateInfo> n1;
    public boolean n2;
    public IBatchEditExporter o;
    public final MutableLiveData<BatchSavingResult> o0;
    public final MutableLiveData<Boolean> o1;
    public boolean o2;
    public final List<Picture> p;
    public final MutableLiveData<BatchSavingResult> p0;
    public Function0<Unit> p1;
    public Function0<Unit> p2;
    public String q;
    public Job q0;
    public String q1;
    public final Bundle q2;
    public boolean r;
    public boolean r0;
    public Integer r1;
    public String r2;
    public String s;
    public boolean s0;
    public final MutableLiveData<SaveImageEvent> s1;
    public final EditActivityViewModel$imageChangeListener$1 s2;
    public final MutableLiveData<Boolean> t;
    public PictureSnapshot t0;
    public final Lazy t1;
    public long t2;
    public final MutableLiveData<Boolean> u;
    public String u0;
    public C1W6 u1;
    public final Map<String, String> u2;
    public IQueryFragmentIdMsgCallback v;
    public boolean v0;
    public List<C1W6> v1;
    public final Map<LayerType, String> v2;
    public String w;
    public final MutableLiveData<String> w0;
    public boolean w1;
    public int x;
    public String x0;
    public final MutableLiveData<Boolean> x1;
    public int y;
    public LynxEvent y0;
    public final List<Long> y1;
    public String z;
    public String z0;
    public final MutableLiveData<Event<SaveResult>> z1;

    /* loaded from: classes27.dex */
    public static final class BeautyMeUserComment {
        public final Map<String, String> data;

        @SerializedName("source_type")
        public final String sourceType;

        public BeautyMeUserComment(Map<String, String> map, String str) {
            Intrinsics.checkNotNullParameter(map, "");
            Intrinsics.checkNotNullParameter(str, "");
            this.data = map;
            this.sourceType = str;
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.xt.edit.EditActivityViewModel$BeautyMeUserComment */
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ BeautyMeUserComment copy$default(BeautyMeUserComment beautyMeUserComment, Map map, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                map = beautyMeUserComment.data;
            }
            if ((i & 2) != 0) {
                str = beautyMeUserComment.sourceType;
            }
            return beautyMeUserComment.copy(map, str);
        }

        public final BeautyMeUserComment copy(Map<String, String> map, String str) {
            Intrinsics.checkNotNullParameter(map, "");
            Intrinsics.checkNotNullParameter(str, "");
            return new BeautyMeUserComment(map, str);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof BeautyMeUserComment)) {
                return false;
            }
            BeautyMeUserComment beautyMeUserComment = (BeautyMeUserComment) obj;
            return Intrinsics.areEqual(this.data, beautyMeUserComment.data) && Intrinsics.areEqual(this.sourceType, beautyMeUserComment.sourceType);
        }

        public final Map<String, String> getData() {
            return this.data;
        }

        public final String getSourceType() {
            return this.sourceType;
        }

        public int hashCode() {
            return (this.data.hashCode() * 31) + this.sourceType.hashCode();
        }

        public String toString() {
            return "BeautyMeUserComment(data=" + this.data + ", sourceType=" + this.sourceType + ')';
        }
    }

    /* loaded from: classes23.dex */
    public static final class Companion {
    }

    /* loaded from: classes40.dex */
    public interface IQueryFragmentIdMsgCallback {
        String a(int i);

        void b();

        boolean c(int i);

        boolean d(int i);

        Integer e(Uri uri);

        boolean f(int i);

        boolean g(int i);

        Integer h(Uri uri);

        void i();
    }

    /* loaded from: classes16.dex */
    public static final class LynxEvent {

        /* renamed from: a, reason: collision with root package name */
        public final LynxEventType f136347a;
        public final String b;

        /* renamed from: c, reason: collision with root package name */
        public final HashMap<String, String> f136348c;

        public LynxEvent(String str, HashMap map) {
            LynxEventType lynxEventType = LynxEventType.f136349a;
            Intrinsics.checkNotNullParameter(lynxEventType, "");
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(map, "");
            this.f136347a = lynxEventType;
            this.b = str;
            this.f136348c = map;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof LynxEvent)) {
                return false;
            }
            LynxEvent lynxEvent = (LynxEvent) obj;
            return this.f136347a == lynxEvent.f136347a && Intrinsics.areEqual(this.b, lynxEvent.b) && Intrinsics.areEqual(this.f136348c, lynxEvent.f136348c);
        }

        public final int hashCode() {
            return (((this.f136347a.hashCode() * 31) + this.b.hashCode()) * 31) + this.f136348c.hashCode();
        }

        public final String toString() {
            return "LynxEvent(eventType=" + this.f136347a + ", mainEventParams=" + this.b + ", extraParams=" + this.f136348c + ')';
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes34.dex */
    public static final class LynxEventType {

        /* renamed from: a, reason: collision with root package name */
        public static final LynxEventType f136349a;
        public static final /* synthetic */ LynxEventType[] b;

        static {
            LynxEventType lynxEventType = new LynxEventType();
            f136349a = lynxEventType;
            LynxEventType[] lynxEventTypeArr = {lynxEventType};
            b = lynxEventTypeArr;
            C06W.a(lynxEventTypeArr);
        }

        public static LynxEventType valueOf(String str) {
            return (LynxEventType) Enum.valueOf(LynxEventType.class, str);
        }

        public static LynxEventType[] values() {
            return (LynxEventType[]) b.clone();
        }
    }

    /* loaded from: classes30.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f136350a;

        static {
            int[] iArr = new int[EditConfig.Mode.values().length];
            try {
                iArr[4] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[6] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f136350a = iArr;
            CoverTemplateInnerEditMode.values();
        }
    }

    static {
        new Companion();
    }

    /* JADX WARN: Type inference failed for: r0v51, types: [com.xt.edit.EditActivityViewModel$currentFragmentName$1] */
    /* JADX WARN: Type inference failed for: r0v67, types: [com.xt.edit.EditActivityViewModel$imageChangeListener$1] */
    public EditActivityViewModel() {
        ApiService.f139261a.getClass();
        this.f136343a = (IBusinessPicReporter) ApiService.a(IBusinessPicReporter.class);
        MutableSharedFlow<Integer> mutableSharedFlowMutableSharedFlow$default = SharedFlowKt.MutableSharedFlow$default(0, 0, null, 7, null);
        this.k = mutableSharedFlowMutableSharedFlow$default;
        this.l = FlowKt__ShareKt.asSharedFlow(mutableSharedFlowMutableSharedFlow$default);
        this.p = new ArrayList();
        Boolean bool = Boolean.TRUE;
        this.t = new MutableLiveData<>(bool);
        this.u = new MutableLiveData<>(bool);
        this.w = "";
        this.z = "";
        this.A = LazyKt__LazyJVMKt.lazy(new Function0<NumberFormat>() { // from class: com.xt.edit.EditActivityViewModel$numberFormat$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final NumberFormat invoke() {
                NumberFormat numberInstance = NumberFormat.getNumberInstance();
                numberInstance.setMaximumFractionDigits(2);
                numberInstance.setMinimumFractionDigits(2);
                numberInstance.setRoundingMode(RoundingMode.HALF_UP);
                numberInstance.setGroupingUsed(false);
                return numberInstance;
            }
        });
        this.F = "";
        this.G = "";
        this.H = "";
        this.I = "";
        this.L = "";
        StatusBarUtils.f150155a.getClass();
        this.N = StatusBarUtils.b;
        this.R = "";
        this.T = "";
        this.U = new MutableLiveData<>(0);
        this.V = new MutableLiveData<>(null);
        this.W = Transformations.map(StatusBarUtils.f150156c, new Function1<Float, Float>() { // from class: com.xt.edit.EditActivityViewModel$titleBarPaddingTop$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Float invoke(Float f) {
                return f;
            }
        });
        this.a0 = "";
        this.b0 = true;
        this.d0 = "";
        this.e0 = "";
        new LinkedHashSet();
        this.g0 = "";
        this.h0 = "";
        this.i0 = new MutableLiveData<>();
        new MutableLiveData("");
        Boolean bool2 = Boolean.FALSE;
        this.j0 = new MutableLiveData<>(bool2);
        this.k0 = new MutableLiveData<>(bool2);
        this.l0 = new MutableLiveData<>(bool2);
        this.m0 = new ResolutionStrategy();
        this.n0 = new MutableLiveData<>(bool2);
        this.o0 = new MutableLiveData<>();
        this.p0 = new MutableLiveData<>();
        this.r0 = true;
        this.s0 = true;
        this.u0 = "";
        this.w0 = new MutableLiveData<>();
        this.z0 = "";
        this.A0 = "";
        this.B0 = "";
        this.N0 = new MutableLiveData<>(bool2);
        this.d1 = "";
        this.e1 = "";
        this.f1 = "";
        this.g1 = new MutableLiveData<>(ThirdPartEditMode.f142054c);
        this.h1 = new MutableLiveData<>("");
        this.i1 = true;
        this.j1 = "";
        this.k1 = "";
        this.l1 = "";
        this.m1 = "";
        this.n1 = new MutableLiveData<>();
        this.o1 = new MutableLiveData<>(bool2);
        this.s1 = new MutableLiveData<>(new SaveImageEvent(SaveImageEvent.Status.f141834a, null, null, null, false, 0, 62));
        this.t1 = LazyKt__LazyJVMKt.lazy(new Function0<EditHostImpl>() { // from class: com.xt.edit.EditActivityViewModel$editHost$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final EditHostImpl invoke() {
                return new EditHostImpl(this.e);
            }
        });
        this.x1 = new MutableLiveData<>(bool2);
        this.y1 = new ArrayList();
        this.z1 = new MutableLiveData<>();
        this.A1 = new MutableLiveData<>();
        this.D1 = new MutableLiveData<>();
        this.E1 = new MutableLiveData<>();
        this.F1 = new MutableLiveData<>(null);
        this.G1 = new MutableLiveData<>(0);
        MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>(0);
        this.H1 = mutableLiveData;
        this.I1 = mutableLiveData;
        this.J1 = new MutableLiveData<>(bool2);
        this.K1 = new MutableLiveData<>(bool2);
        new MutableLiveData(bool2);
        this.L1 = new MutableLiveData<>(bool2);
        this.M1 = new MutableLiveData<>();
        this.N1 = new MutableLiveData<>();
        this.P1 = new MediatorLiveData<String>() { // from class: com.xt.edit.EditActivityViewModel$currentFragmentName$1
            {
                addSource(this.f136351a.N1, new EditActivityViewModel$sam$androidx_lifecycle_Observer$0(new Function1<Integer, Unit>() { // from class: com.xt.edit.EditActivityViewModel$currentFragmentName$1.1
                    {
                        super(1);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function1
                    public final Unit invoke(Integer num) {
                        Integer num2 = num;
                        EditActivityViewModel$currentFragmentName$1 editActivityViewModel$currentFragmentName$1 = EditActivityViewModel$currentFragmentName$1.this;
                        Intrinsics.checkNotNull(num2);
                        int iIntValue = num2.intValue();
                        EditActivityViewModel.IQueryFragmentIdMsgCallback iQueryFragmentIdMsgCallback = editActivityViewModel$currentFragmentName$1.f136351a.v;
                        editActivityViewModel$currentFragmentName$1.setValue(iQueryFragmentIdMsgCallback != null ? iQueryFragmentIdMsgCallback.a(iIntValue) : null);
                        return Unit.INSTANCE;
                    }
                }));
            }
        };
        this.Q1 = new MutableLiveData<>();
        this.R1 = new MutableLiveData<>("outdoor");
        this.S1 = "";
        this.W1 = new MutableLiveData<>(bool2);
        this.X1 = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.xt.edit.EditActivityViewModel$maxRenderLen$2
            {
                super(0);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r0v16, resolved type: T */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                int i;
                IEffectAutoTest iEffectAutoTest = this.e.b;
                IEffectAutoTest iEffectAutoTest2 = null;
                if (iEffectAutoTest == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("autoTest");
                    iEffectAutoTest = null;
                }
                if (iEffectAutoTest.h()) {
                    IEffectAutoTest iEffectAutoTest3 = this.e.b;
                    if (iEffectAutoTest3 != null) {
                        iEffectAutoTest2 = iEffectAutoTest3;
                    } else {
                        Intrinsics.throwUninitializedPropertyAccessException("autoTest");
                    }
                    EffectAutoTestData effectAutoTestDataB = iEffectAutoTest2.b();
                    i = effectAutoTestDataB != null ? effectAutoTestDataB.f141577a.getResolution() : SpeechEngineDefines.TTS_WORK_MODE_BOTH;
                } else {
                    if (this.e.K6().f141806a == EditMode.f141866a) {
                        HighResABTest.Companion companion = HighResABTest.Companion;
                        JsonConfig jsonConfig = (JsonConfig) this.e.A6().getHighResABTest().getValue();
                        companion.getClass();
                        HighResABTest highResABTestA = HighResABTest.Companion.a(jsonConfig);
                        if (highResABTestA.isHighResEnable() && highResABTestA.getStrategies() != null) {
                            HighResABTestStrategies strategies = highResABTestA.getStrategies();
                            Intrinsics.checkNotNull(strategies);
                            int maxResolutionWidth = strategies.getMaxResolutionWidth();
                            HighResABTestStrategies strategies2 = highResABTestA.getStrategies();
                            Intrinsics.checkNotNull(strategies2);
                            return Integer.valueOf(RangesKt___RangesKt.coerceAtMost(maxResolutionWidth, strategies2.getMaxResolutionHeight()));
                        }
                    }
                    RenderSizeConfig.Companion companion2 = RenderSizeConfig.f136513a;
                    IConfigManager iConfigManagerA6 = this.e.A6();
                    companion2.getClass();
                    i = RenderSizeConfig.Companion.a(iConfigManagerA6).i(KvSettingProvider.f150073a.J3());
                }
                return Integer.valueOf(i);
            }
        });
        this.Y1 = "";
        this.a2 = new MutableLiveData<>(0);
        LayerNumConfig.f141428a.getClass();
        this.b2 = LayerNumConfig.e;
        this.c2 = new MutableLiveData<>(bool2);
        this.d2 = LazyKt__LazyJVMKt.lazy(new Function0<MutableLiveData<String>>() { // from class: com.xt.edit.EditActivityViewModel$_reportDraftId$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final MutableLiveData<String> invoke() {
                return new MutableLiveData<>(this.e.J6().U1());
            }
        });
        this.e2 = SystemClock.elapsedRealtime();
        this.f2 = new MutableLiveData<>();
        this.g2 = new ArrayList();
        this.m2 = "";
        this.q2 = new Bundle();
        this.r2 = "";
        this.s2 = new DefaultImageChangeListener() { // from class: com.xt.edit.EditActivityViewModel$imageChangeListener$1
            @Override // com.retouch.layermanager.api.transform.DefaultImageChangeListener, com.retouch.layermanager.api.transform.OnImageChangeListener
            public final void d(float f, float f2) {
                this.f136356a.G7();
            }

            @Override // com.retouch.layermanager.api.transform.DefaultImageChangeListener, com.retouch.layermanager.api.transform.OnImageChangeListener
            public final void e(float f, float f2, float f3, float f4) {
                this.f136356a.G7();
            }
        };
        this.u2 = MapsKt__MapsKt.mapOf(new Pair("Beauty", "/facial_beauty"), new Pair("BeautyAuto", "/facial_beauty_auto"), new Pair("BeautyAll", "/beauty_all"), new Pair("BeautyFace", "/face_beauty"), new Pair("Liquid", "/liquify"), new Pair("BeautyMakeup", "/makeup"), new Pair("BeautyAIMakeup", "/ai_makeup"), new Pair("Composition", "/composition"), new Pair("hair", "/hair"), new Pair("expression", "/facial_expression"), new Pair("MakeupPen", "/pen"), new Pair("erasurePen", "/eliminate_pen"), new Pair("BeautyBody_Auto", "/body_beauty"), new Pair("ManualBody", "/manual_body_beauty"), new Pair("Stereoscopic", "/stereo"), new Pair("org_cutout", "/org_cutout"), new Pair("hsl", "/hsl"), new Pair("LocalAdjustment", "/local_adjustment"), new Pair("mosaic", "/mosaic"), new Pair("play_function", "/play_function"), new Pair("cutout_play_function", "/play_function"), new Pair("hdr", "/hdr"), new Pair("ambient_light", "/ambient"), new Pair("camera_roll", "/camera_roll"), new Pair("backlight_correct", "/backlight_correct"), new Pair("stretch", "/stretch"));
        this.v2 = MapsKt__MapsKt.mapOf(new Pair(LayerType.b, "/filter"), new Pair(LayerType.f64595d, "/edit"), new Pair(LayerType.h, "/sticker"), new Pair(LayerType.f64594c, "/image_effect"), new Pair(LayerType.i, "/cutout_image"), new Pair(LayerType.j, "/text"), new Pair(LayerType.q, "/svg"), new Pair(LayerType.k, "/graffiti_pen"), new Pair(LayerType.l, "/background"), new Pair(LayerType.m, "/text_template"), new Pair(LayerType.w, "/ambient"), new Pair(LayerType.y, "/camera_roll"), new Pair(LayerType.x, "/stretch"));
    }

    public static void C7(EditActivityViewModel editActivityViewModel, IAppEventReport.PhotoImportParams photoImportParams, String str, boolean z, Integer num, int i) {
        Integer num2 = num;
        String str2 = str;
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        if ((i & 8) != 0) {
            num2 = null;
        }
        if (!editActivityViewModel.b7()) {
            if (!editActivityViewModel.v7()) {
                if (!Intrinsics.areEqual(editActivityViewModel.R, "photo_export_page")) {
                    editActivityViewModel.x6().Nb(photoImportParams);
                    return;
                }
                photoImportParams.getClass();
                photoImportParams.f145009c = "photo_export_page";
                editActivityViewModel.x6().Nb(photoImportParams);
                return;
            }
            if (num2 != null) {
                photoImportParams.l = num2;
            }
            if (z) {
                photoImportParams.getClass();
                photoImportParams.f145009c = "photo_album_page";
                photoImportParams.k = "use_template";
                photoImportParams.o = null;
                editActivityViewModel.M6().S("use_template");
                editActivityViewModel.S1 = "use_template";
            } else if (!editActivityViewModel.C0 && editActivityViewModel.h0.length() == 0) {
                photoImportParams.p = "from_template_to_photo";
                photoImportParams.k = "use_template";
                editActivityViewModel.M6().S("use_template");
                editActivityViewModel.S1 = "use_template";
                if (str2 != null) {
                    photoImportParams.q = str2;
                }
            }
            editActivityViewModel.x6().Nb(photoImportParams);
            return;
        }
        if (editActivityViewModel.v7()) {
            ArrayList<String> arrayList = editActivityViewModel.f0;
            if (arrayList == null || arrayList.size() <= 1) {
                return;
            }
            CoroutineUtilsKt.a(CoroutineUtilsKt.f150006a, new EditActivityViewModel$onReportPhotoImportInner$2$1(photoImportParams, arrayList, editActivityViewModel, str2, null));
            return;
        }
        if (editActivityViewModel.K6().b()) {
            CoroutineUtilsKt.a(CoroutineUtilsKt.f150006a, new EditActivityViewModel$onReportPhotoImportInner$3(editActivityViewModel, photoImportParams, null));
            return;
        }
        if (!editActivityViewModel.K6().b) {
            editActivityViewModel.x6().Nb(photoImportParams);
            return;
        }
        ArrayList<String> arrayList2 = editActivityViewModel.f0;
        if (arrayList2 != null) {
            photoImportParams.f = arrayList2.size();
            IAppEventReport iAppEventReportX6 = editActivityViewModel.x6();
            String str3 = photoImportParams.f145008a;
            String str4 = photoImportParams.b;
            String str5 = photoImportParams.f145009c;
            boolean z2 = photoImportParams.f145010d;
            boolean z3 = photoImportParams.e;
            int i2 = photoImportParams.f;
            Integer num3 = photoImportParams.f145011g;
            Integer num4 = photoImportParams.h;
            boolean z4 = photoImportParams.i;
            String str6 = photoImportParams.j;
            String str7 = photoImportParams.k;
            Integer num5 = photoImportParams.l;
            Integer num6 = photoImportParams.m;
            String str8 = photoImportParams.n;
            String str9 = photoImportParams.o;
            String str10 = photoImportParams.p;
            String str11 = photoImportParams.q;
            String str12 = photoImportParams.r;
            String str13 = photoImportParams.s;
            String str14 = photoImportParams.t;
            String str15 = photoImportParams.u;
            String str16 = photoImportParams.v;
            String str17 = photoImportParams.w;
            String str18 = photoImportParams.x;
            String str19 = photoImportParams.y;
            ArrayList<Integer> arrayList3 = photoImportParams.z;
            ArrayList<String> arrayList4 = photoImportParams.A;
            IAppEventReport.PreviousNum previousNum = photoImportParams.B;
            IAppEventReport.DiffType diffType = photoImportParams.C;
            Intrinsics.checkNotNullParameter(str3, "");
            Intrinsics.checkNotNullParameter(str4, "");
            Intrinsics.checkNotNullParameter(str5, "");
            Intrinsics.checkNotNullParameter(str7, "");
            iAppEventReportX6.Nb(new IAppEventReport.PhotoImportParams(str3, str4, str5, z2, z3, i2, num3, num4, z4, str6, str7, num5, num6, str8, str9, str10, str11, str12, str13, str14, str15, str16, str17, str18, str19, arrayList3, arrayList4, previousNum, diffType, "new"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:58:0x012a A[PHI: r15
      0x012a: PHI (r15v5 java.lang.String) = (r15v0 java.lang.String), (r15v6 java.lang.String) binds: [B:57:0x0128, B:55:0x0124] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x012e A[PHI: r15
      0x012e: PHI (r15v4 java.lang.String) = (r15v0 java.lang.String), (r15v5 java.lang.String) binds: [B:57:0x0128, B:59:0x012c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0132 A[PHI: r14 r15
      0x0132: PHI (r14v0 java.lang.String) = (r14v3 java.lang.String), (r14v4 java.lang.String) binds: [B:61:0x0130, B:59:0x012c] A[DONT_GENERATE, DONT_INLINE]
      0x0132: PHI (r15v1 java.lang.String) = (r15v4 java.lang.String), (r15v5 java.lang.String) binds: [B:61:0x0130, B:59:0x012c] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void K7(com.xt.edit.EditActivityViewModel r61, X.C1W6 r62, boolean r63, java.lang.String r64, boolean r65, java.lang.String r66, model.SaveResult.ExportPicStatus r67, int r68) {
        /*
            r1 = r68
            r2 = r67
            r41 = r65
            r10 = r66
            r36 = r64
            r35 = r63
            r0 = r1 & 2
            if (r0 == 0) goto L12
            r35 = 0
        L12:
            r0 = r1 & 4
            java.lang.String r20 = ""
            if (r0 == 0) goto L1a
            r36 = r20
        L1a:
            r0 = r1 & 8
            if (r0 == 0) goto L20
            r41 = 0
        L20:
            r0 = r1 & 16
            if (r0 == 0) goto L26
            r10 = r20
        L26:
            r0 = r1 & 32
            if (r0 == 0) goto L2d
            kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L2d:
            r0 = r1 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L32
            r2 = 0
        L32:
            r0 = r61
            com.xt.retouch.business.api.report.IBusinessPicReporter r1 = r0.f136343a
            com.xt.retouch.business.api.report.BusinessReportContext r1 = r1.b()
            boolean r1 = r1.F
            if (r1 == 0) goto L1da
            java.lang.String r3 = "ai_background"
        L40:
            com.xt.retouch.edit.base.api.EditContext r1 = r0.K6()
            boolean r1 = r1.b()
            r4 = 1
            if (r1 == 0) goto L1d6
            boolean r1 = r0.w1
            if (r1 != 0) goto L5f
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r0.n
            if (r1 == 0) goto L1d3
            java.lang.Object r5 = r1.getValue()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r1)
            if (r1 == 0) goto L1d3
        L5f:
            r7 = 1
        L60:
            com.xt.retouch.lib.log.XTLog r5 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.String r1 = "[handleTextRecognition] page:"
            java.lang.String r1 = r1.concat(r3)
            r5.getClass()
            java.lang.String r5 = "EditActivityViewModel"
            com.xt.retouch.lib.log.XTLog.e(r5, r1)
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r1 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            r1.getClass()
            com.xt.retouch.baseflavorconfig.FlavorConfig r1 = com.xt.retouch.baseflavorconfig.FlavorConfig.f139375a
            r1.getClass()
            com.xt.retouch.baseflavorconfig.FlavorConfig.g()
            java.lang.String r1 = "[handleTextRecognition] feature disabled"
            com.xt.retouch.lib.log.XTLog.e(r5, r1)
            java.util.List r61 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            java.util.Map r6 = r0.L6()
            java.util.Map r1 = r0.N6()
            java.util.Map r5 = r0.P6()
            java.util.Map r1 = kotlin.collections.MapsKt__MapsKt.plus(r6, r1)
            java.util.Map r5 = kotlin.collections.MapsKt__MapsKt.plus(r1, r5)
            java.util.Map r1 = r0.t6()
            java.util.Map r48 = kotlin.collections.MapsKt__MapsKt.plus(r5, r1)
            java.lang.String r1 = r0.z
            boolean r1 = kotlin.text.StringsKt__StringsKt.isBlank(r1)
            r1 = r1 ^ 1
            if (r1 == 0) goto Lbe
            com.xt.retouch.report.api.EventValue r1 = com.xt.retouch.report.api.EventValue.f144995a
            r1.getClass()
            java.lang.String r1 = com.xt.retouch.report.api.EventValue.e
            boolean r1 = kotlin.text.StringsKt__StringsKt.isBlank(r1)
            if (r1 == 0) goto Lbe
            java.lang.String r1 = r0.z
            com.xt.retouch.report.api.EventValue.c(r1)
        Lbe:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r0.J6()
            com.xt.retouch.painter.function.api.IPainter r1 = r1.j()
            com.xt.retouch.painter.model.template.TemplateItem r19 = r1.fa()
            com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r0.J6()
            com.xt.retouch.painter.trace.EffectFlow r18 = com.xt.retouch.painter.function.api.IPainterCommon.DefaultImpls.d(r1)
            java.util.List r1 = r18.getEditItemList()
            java.util.Iterator r9 = r1.iterator()
        Lda:
            boolean r1 = r9.hasNext()
            if (r1 == 0) goto L1cf
            java.lang.Object r8 = r9.next()
            r6 = r8
            com.xt.retouch.painter.trace.EffectFlow$CommonItem r6 = (com.xt.retouch.painter.trace.EffectFlow.CommonItem) r6
            java.lang.String r5 = r6.f144814a
            java.lang.String r1 = "smart_color_button"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r1)
            if (r1 == 0) goto Lda
            int r1 = r6.b
            if (r1 == 0) goto Lda
            if (r8 == 0) goto L1cf
            r56 = 1
        Lf9:
            com.example.template.api.TemplateContainer r5 = com.example.template.api.TemplateContainer.f48698a
            java.lang.String r1 = r19.getTemplateId()
            r5.getClass()
            template.ITemplate r1 = com.example.template.api.TemplateContainer.b(r1)
            if (r1 == 0) goto L1cb
            int r1 = r1.b()
            java.lang.Integer r17 = java.lang.Integer.valueOf(r1)
        L110:
            com.retouch.layermanager.api.layer.ILayerManager r1 = r0.T6()
            com.retouch.layermanager.api.layer.CameraRollLayer r1 = r1.B2()
            if (r1 == 0) goto L1c7
            boolean r39 = r1.u()
        L11e:
            r1 = r62
            if (r1 == 0) goto L126
            java.lang.String r15 = r1.f6946a
            if (r15 != 0) goto L12a
        L126:
            r15 = r20
            if (r1 == 0) goto L12e
        L12a:
            java.lang.String r14 = r1.f
            if (r14 != 0) goto L132
        L12e:
            r14 = r20
            if (r1 == 0) goto L1c4
        L132:
            int r13 = r1.h
        L134:
            int r26 = r0.E6()
            int r27 = r0.D6()
            com.xt.retouch.account.api.IAccount r5 = r0.u6()
            r5.isLogin()
            com.xt.retouch.account.api.IAccount r5 = r0.u6()
            boolean r30 = r5.h()
            if (r7 != 0) goto L1c0
            if (r2 == 0) goto L1be
            int r9 = r2.f150875a
            java.lang.String r8 = r2.b
            if (r8 != 0) goto L157
        L155:
            java.lang.String r8 = "exportPicture failed,save to file fail"
        L157:
            if (r1 == 0) goto L1bb
            boolean r2 = r1.i
            if (r2 != r4) goto L1bb
            java.lang.String r33 = "png"
        L15f:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r0.J6()
            boolean r34 = r2.i6()
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.AppliedTemplateInfo> r2 = r0.n1
            java.lang.Object r2 = r2.getValue()
            if (r2 != 0) goto L1b0
        L16f:
            r6 = r20
        L171:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r0.J6()
            boolean r38 = r2.V2()
            com.retouch.layermanager.api.layer.ILayerManager r2 = r0.T6()
            com.retouch.layermanager.api.layer.EditLayer r2 = r2.g()
            if (r2 == 0) goto L21b
            r4 = 0
            java.util.List r2 = r2.G0(r4)
            if (r2 == 0) goto L21b
            java.util.Iterator r11 = r2.iterator()
        L18e:
            boolean r2 = r11.hasNext()
            if (r2 == 0) goto L21b
            java.lang.Object r2 = r11.next()
            com.retouch.layermanager.api.layer.data.LatestEdit r2 = (com.retouch.layermanager.api.layer.data.LatestEdit) r2
            java.lang.String r5 = r2.getKey()
            java.lang.String r4 = "backlight_correct"
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r4)
            if (r4 == 0) goto L18e
            float r4 = r2.getValue()
            r2 = 0
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 != 0) goto L21e
            goto L18e
        L1b0:
            androidx.lifecycle.MutableLiveData<java.lang.String> r2 = r0.w0
            java.lang.Object r6 = r2.getValue()
            java.lang.String r6 = (java.lang.String) r6
            if (r6 != 0) goto L171
            goto L16f
        L1bb:
            java.lang.String r33 = "jpg"
            goto L15f
        L1be:
            r9 = 1
            goto L155
        L1c0:
            r9 = 0
            r8 = r20
            goto L157
        L1c4:
            r13 = 0
            goto L134
        L1c7:
            r39 = 0
            goto L11e
        L1cb:
            r17 = 0
            goto L110
        L1cf:
            r56 = 0
            goto Lf9
        L1d3:
            r7 = 0
            goto L60
        L1d6:
            boolean r7 = r0.w1
            goto L60
        L1da:
            com.xt.retouch.business.api.report.IBusinessPicReporter r1 = r0.f136343a
            com.xt.retouch.business.api.report.BusinessReportContext r1 = r1.b()
            boolean r1 = r1.G
            if (r1 == 0) goto L1e8
            java.lang.String r3 = "watermark_edit_page"
            goto L40
        L1e8:
            boolean r1 = r0.s7()
            if (r1 == 0) goto L1f2
            java.lang.String r3 = "business_edit_page"
            goto L40
        L1f2:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r0.j0
            java.lang.Object r3 = r1.getValue()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r1)
            if (r1 == 0) goto L204
            java.lang.String r3 = "photo_edit_middle_page"
            goto L40
        L204:
            com.xt.retouch.edit.base.api.EditContext r1 = r0.K6()
            boolean r1 = r1.b()
            if (r1 != 0) goto L212
        L20e:
            java.lang.String r3 = "photo_edit_page"
            goto L40
        L212:
            boolean r1 = r0.E0
            if (r1 != 0) goto L217
            goto L20e
        L217:
            java.lang.String r3 = "photo_export_page"
            goto L40
        L21b:
            r40 = 0
            goto L220
        L21e:
            r40 = 1
        L220:
            com.xt.retouch.edit.base.api.EditContext r2 = r0.K6()
            boolean r2 = r2.b()
            if (r2 == 0) goto L3d9
        L22a:
            java.lang.String r42 = h7(r10)
            com.xt.retouch.edit.base.api.EditContext r2 = r0.K6()
            com.xt.retouch.edit.base.api.EditContext$BatchEditState r2 = r2.a()
            if (r2 == 0) goto L3d6
            boolean r12 = r2.f141810a
        L23a:
            com.xt.retouch.edit.base.api.EditContext r2 = r0.K6()
            com.xt.retouch.edit.base.api.EditContext$BatchEditState r2 = r2.a()
            if (r2 == 0) goto L3d3
            boolean r11 = r2.b
        L246:
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.ThirdPartEditMode> r2 = r0.g1
            java.lang.Object r4 = r2.getValue()
            com.xt.retouch.edit.base.model.ThirdPartEditMode r2 = com.xt.retouch.edit.base.model.ThirdPartEditMode.f142053a
            if (r4 != r2) goto L3cf
            r45 = 1
        L252:
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.ThirdPartEditMode> r2 = r0.g1
            java.lang.Object r4 = r2.getValue()
            com.xt.retouch.edit.base.model.ThirdPartEditMode r2 = com.xt.retouch.edit.base.model.ThirdPartEditMode.f142054c
            if (r4 == r2) goto L3cb
            r46 = 1
        L25e:
            java.lang.String r10 = r0.m1
            int r2 = r10.length()
            if (r2 <= 0) goto L3c7
        L266:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r0.J6()
            com.xt.retouch.painter.function.api.IPainter r2 = r2.j()
            int r57 = r2.m()
            java.util.List r2 = r0.v6()
            java.util.ArrayList r2 = (java.util.ArrayList) r2
            boolean r4 = r2.isEmpty()
            if (r4 == 0) goto L385
        L27e:
            r51 = 0
        L280:
            java.util.List r62 = r0.v6()
            r63 = 0
            r29 = 0
            com.xt.edit.EditActivityViewModel$getUsedEffectIds$1 r67 = new kotlin.jvm.functions.Function1<java.lang.String, java.lang.CharSequence>() { // from class: com.xt.edit.EditActivityViewModel$getUsedEffectIds$1
                static {
                    /*
                        com.xt.edit.EditActivityViewModel$getUsedEffectIds$1 r0 = new com.xt.edit.EditActivityViewModel$getUsedEffectIds$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.xt.edit.EditActivityViewModel$getUsedEffectIds$1) com.xt.edit.EditActivityViewModel$getUsedEffectIds$1.e com.xt.edit.EditActivityViewModel$getUsedEffectIds$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel$getUsedEffectIds$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel$getUsedEffectIds$1.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(java.lang.String r2) {
                    /*
                        r1 = this;
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
                        java.lang.String r0 = ","
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel$getUsedEffectIds$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r68 = 31
            r64 = r63
            r65 = r63
            r66 = r29
            java.lang.String r53 = kotlin.collections.CollectionsKt.j(r62, r63, r64, r65, r66, r67, r68)
            boolean r2 = r61.isEmpty()
            r55 = r2 ^ 1
            java.lang.String r58 = r19.getTemplateId()
            if (r17 == 0) goto L381
            int r60 = r17.intValue()
        L2a6:
            java.lang.String r2 = r19.getTemplateId()
            int r2 = r2.length()
            if (r2 != 0) goto L373
            com.xt.retouch.report.api.EventValue r2 = com.xt.retouch.report.api.EventValue.f144995a
            r2.getClass()
            java.lang.String r59 = com.xt.retouch.report.api.EventValue.e
        L2b7:
            com.xt.retouch.scenes.api.jigsaw.IJigsawScenesModel r2 = r0.H0
            if (r2 == 0) goto L36b
        L2bb:
            com.xt.retouch.painter.model.jigsaw.JigsawConfig r62 = r2.C()
            com.xt.retouch.painter.trace.EffectFlow$UVLiquefyFlow r63 = r18.getUvLiquefyFlow()
            com.xt.edit.api.SavePhotoParams r2 = new com.xt.edit.api.SavePhotoParams
            java.lang.String r52 = ""
            r28 = r3
            r31 = r9
            r32 = r8
            r37 = r6
            r43 = r12
            r44 = r11
            r47 = r10
            r49 = r29
            r50 = r29
            r54 = r29
            r21 = r2
            r22 = r7
            r23 = r15
            r24 = r14
            r25 = r13
            r21.<init>(r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63)
            com.xt.edit.api.IEditReport r4 = r0.M6()
            java.lang.String r3 = r0.H
            r4.U2(r2, r3)
            if (r1 == 0) goto L2f7
            java.lang.String r5 = r1.f6946a
            if (r5 != 0) goto L2f9
        L2f7:
            r5 = r20
        L2f9:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r0.J6()
            com.xt.retouch.painter.trace.EffectFlow r7 = com.xt.retouch.painter.function.api.IPainterCommon.DefaultImpls.d(r1)
            com.retouch.layermanager.api.layer.ILayerManager r1 = r0.T6()
            java.util.List r1 = r1.I1()
            java.util.Iterator r6 = r1.iterator()
        L30d:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L3dd
            java.lang.Object r1 = r6.next()
            com.retouch.layermanager.api.layer.Layer r1 = (com.retouch.layermanager.api.layer.Layer) r1
            int r3 = r1.b()
            java.lang.Integer r1 = r1.Y1()
            if (r1 == 0) goto L369
            int r4 = r1.intValue()
        L327:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r0.J6()
            com.xt.retouch.painter.function.api.IPainter r1 = r1.j()
            boolean r1 = r1.id(r3)
            if (r1 == 0) goto L30d
            java.util.Map r2 = r7.getWatermarkItem()
            java.lang.String r1 = java.lang.String.valueOf(r3)
            java.lang.Object r2 = r2.get(r1)
            com.xt.retouch.painter.trace.EffectFlow$WatermarkItem r2 = (com.xt.retouch.painter.trace.EffectFlow.WatermarkItem) r2
            if (r2 == 0) goto L30d
            com.xt.edit.api.SaveWatermarkParams r8 = new com.xt.edit.api.SaveWatermarkParams
            java.lang.String r15 = r2.f144899a
            int r9 = r2.b
            int r10 = r2.f144900c
            int r11 = r2.f144901d
            int r12 = r2.e
            java.lang.String r3 = r2.f
            java.lang.String r1 = r2.f144902g
            int r13 = r2.h
            int r14 = r2.i
            r2 = r8
            r17 = r1
            r16 = r3
            r8.<init>(r9, r10, r11, r12, r13, r14, r15, r16, r17)
            com.xt.edit.api.IEditReport r1 = r0.M6()
            r1.o9(r5, r4, r2)
            goto L30d
        L369:
            r4 = 0
            goto L327
        L36b:
            java.lang.String r2 = "iJigsawScenesModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = 0
            goto L2bb
        L373:
            boolean r2 = r19.isBusinessTemplate()
            if (r2 == 0) goto L37d
            java.lang.String r59 = "business_template"
            goto L2b7
        L37d:
            java.lang.String r59 = "use_template"
            goto L2b7
        L381:
            r60 = 1
            goto L2a6
        L385:
            java.util.Iterator r16 = r2.iterator()
        L389:
            boolean r2 = r16.hasNext()
            if (r2 == 0) goto L27e
            java.lang.Object r4 = r16.next()
            java.lang.String r4 = (java.lang.String) r4
            com.xt.retouch.subscribe.api.data.VipBenefitInfo r2 = r0.Y0
            if (r2 == 0) goto L389
            java.util.List r2 = r2.getLimitFreeList()
            if (r2 == 0) goto L389
            boolean r5 = r2 instanceof java.util.Collection
            if (r5 == 0) goto L3ad
            r5 = r2
            java.util.Collection r5 = (java.util.Collection) r5
            boolean r5 = r5.isEmpty()
            if (r5 == 0) goto L3ad
            goto L389
        L3ad:
            java.util.Iterator r5 = r2.iterator()
        L3b1:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L389
            java.lang.Object r2 = r5.next()
            java.lang.String r2 = (java.lang.String) r2
            boolean r2 = X.C93472yG.o(r2, r4)
            if (r2 == 0) goto L3b1
            r51 = 1
            goto L280
        L3c7:
            java.lang.String r10 = "capcut"
            goto L266
        L3cb:
            r46 = 0
            goto L25e
        L3cf:
            r45 = 0
            goto L252
        L3d3:
            r11 = 0
            goto L246
        L3d6:
            r12 = 0
            goto L23a
        L3d9:
            java.lang.String r10 = r0.w
            goto L22a
        L3dd:
            com.xt.retouch.report.api.EventValue r0 = com.xt.retouch.report.api.EventValue.f144995a
            r0.getClass()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.K7(com.xt.edit.EditActivityViewModel, X.1W6, boolean, java.lang.String, boolean, java.lang.String, model.SaveResult$ExportPicStatus, int):void");
    }

    public static void T7(EditActivityViewModel editActivityViewModel, boolean z, boolean z2, int i) {
        long jC0;
        boolean z3 = z;
        if ((i & 1) != 0) {
            z3 = false;
        }
        int i2 = 2;
        boolean z4 = (i & 2) == 0 ? z2 : false;
        editActivityViewModel.getClass();
        System.currentTimeMillis();
        editActivityViewModel.t2 = SystemClock.elapsedRealtime();
        XTLog xTLog = XTLog.f144340a;
        String str = "saveImage() current time = " + System.currentTimeMillis();
        xTLog.getClass();
        XTLog.e("EditActivityViewModel", str);
        SaveImageEvent value = editActivityViewModel.s1.getValue();
        if ((value != null ? value.b() : null) != SaveImageEvent.Status.f141834a) {
            SaveImageEvent value2 = editActivityViewModel.s1.getValue();
            if ((value2 != null ? value2.b() : null) != SaveImageEvent.Status.f141836d) {
                return;
            }
        }
        Event<SaveResult> value3 = editActivityViewModel.z1.getValue();
        if (value3 != null) {
            value3.f139373c = true;
        }
        editActivityViewModel.M6().ta();
        KvSettingProvider kvSettingProvider = KvSettingProvider.f150073a;
        if (kvSettingProvider.c0() > 0) {
            kvSettingProvider.c0();
            jC0 = kvSettingProvider.c0();
        } else {
            jC0 = editActivityViewModel.f136342X * editActivityViewModel.Z * 4;
        }
        long jC = StorageUtils.c(StorageUtils.f150158a);
        XTLog.e("EditActivityViewModel", "maxByte = " + jC0 + ", width = " + editActivityViewModel.f136342X + ", height = " + editActivityViewModel.Z + ", availableByte = " + jC);
        if (jC >= jC0) {
            editActivityViewModel.s1.setValue(new SaveImageEvent(SaveImageEvent.Status.b, null, null, null, false, 0, 62));
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.a(editActivityViewModel), null, null, new EditActivityViewModel$saveImage$1(editActivityViewModel, z4, z3, null), 3, null);
        } else {
            editActivityViewModel.z1.setValue(new Event<>(new SaveResult(i2)));
            editActivityViewModel.L7(2, "not enough space");
            XTLog.e("PictureExporter", "saveImage export error: not enough space");
        }
    }

    public static String h7(String str) {
        String strJ;
        MediaUtil.f150102a.getClass();
        Intrinsics.checkNotNullParameter(str, "");
        if (str.length() == 0) {
            strJ = null;
        } else {
            try {
                strJ = new ExifInterface(str).j("UserComment");
            } catch (Exception e) {
                XTLog.f144340a.getClass();
                XTLog.c("MediaUtil", "getImageExifInfo() failed! ", e);
            }
        }
        try {
            BeautyMeUserComment beautyMeUserComment = (BeautyMeUserComment) new Gson().fromJson(strJ, BeautyMeUserComment.class);
            if (!beautyMeUserComment.getData().containsKey("product")) {
                return "";
            }
            String str2 = beautyMeUserComment.getData().get("product");
            return str2 == null ? "" : str2;
        } catch (Exception unused) {
            return "";
        }
    }

    public static boolean p6() {
        if (KvSettingProvider.f150073a.f2()) {
            return Random.Default.nextBoolean();
        }
        return false;
    }

    public static void q6(final EditActivityViewModel editActivityViewModel, final IPainterCommon.BitmapInfo bitmapInfo, final Context context, final MiddlePageRecorder middlePageRecorder, String str, boolean z, boolean z2, Integer num, int i) {
        final boolean z3 = z2;
        final boolean z4 = z;
        final Integer num2 = num;
        final String str2 = str;
        if ((i & 8) != 0) {
            str2 = "";
        }
        if ((i & 16) != 0) {
            z4 = false;
        }
        if ((i & 32) != 0) {
            z3 = false;
        }
        if ((i & 64) != 0) {
            num2 = null;
        }
        editActivityViewModel.getClass();
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(str2, "");
        EditActivity editActivityI6 = editActivityViewModel.I6();
        if (editActivityI6 != null) {
            editActivityViewModel.p2 = new Function0<Unit>() { // from class: com.xt.edit.EditActivityViewModel$doExport$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    if (z4) {
                        EditActivityViewModel editActivityViewModel2 = editActivityViewModel;
                        IPainterCommon.BitmapInfo bitmapInfo2 = bitmapInfo;
                        Context context2 = context;
                        MiddlePageRecorder middlePageRecorder2 = middlePageRecorder;
                        String str3 = str2;
                        editActivityViewModel2.getClass();
                        Intrinsics.checkNotNullParameter(context2, "");
                        Intrinsics.checkNotNullParameter(str3, "");
                        editActivityViewModel2.p2 = null;
                        editActivityViewModel2.J7(false);
                        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.a(editActivityViewModel2), Dispatchers.getIO(), null, new EditActivityViewModel$simpleExportImage$1(editActivityViewModel2, bitmapInfo2, context2, middlePageRecorder2, str3, null), 2, null);
                    } else {
                        final EditActivityViewModel editActivityViewModel3 = editActivityViewModel;
                        IPainterCommon.BitmapInfo bitmapInfo3 = bitmapInfo;
                        final Context context3 = context;
                        MiddlePageRecorder middlePageRecorder3 = middlePageRecorder;
                        String str4 = str2;
                        boolean z5 = z3;
                        Integer num3 = num2;
                        editActivityViewModel3.getClass();
                        Intrinsics.checkNotNullParameter(context3, "");
                        Intrinsics.checkNotNullParameter(str4, "");
                        editActivityViewModel3.p2 = null;
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        editActivityViewModel3.J7(false);
                        Observer observer = new Observer() { // from class: X.1U3
                            @Override // androidx.lifecycle.Observer
                            public final void onChanged(Object obj) {
                                EditActivityViewModel editActivityViewModel4 = editActivityViewModel3;
                                Context context4 = context3;
                                int iIntValue = ((Integer) obj).intValue();
                                if (editActivityViewModel4.w7()) {
                                    return;
                                }
                                MutableLiveData<BatchSavingResult> mutableLiveData = editActivityViewModel4.o0;
                                String string = context4.getString(R.string.s___res_0x7f123e2e);
                                Intrinsics.checkNotNullExpressionValue(string, "");
                                String str5 = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(Math.min(iIntValue, ((ArrayList) editActivityViewModel4.p).size())), Integer.valueOf(((ArrayList) editActivityViewModel4.p).size())}, 2));
                                Intrinsics.checkNotNullExpressionValue(str5, "");
                                mutableLiveData.postValue(new BatchSavingResult(1, str5));
                            }
                        };
                        Observer observer2 = new Observer() { // from class: X.1U4
                            @Override // androidx.lifecycle.Observer
                            public final void onChanged(Object obj) {
                                EditActivityViewModel editActivityViewModel4 = editActivityViewModel3;
                                Context context4 = context3;
                                int iIntValue = ((Integer) obj).intValue();
                                if (KvSettingProvider.f150073a.o()) {
                                    int i2 = editActivityViewModel4.w7() ? R.string.s___res_0x7f123e2f : R.string.s___res_0x7f123e2e;
                                    MutableLiveData<BatchSavingResult> mutableLiveData = editActivityViewModel4.o0;
                                    String string = context4.getString(i2);
                                    Intrinsics.checkNotNullExpressionValue(string, "");
                                    String str5 = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(Math.min(iIntValue, ((ArrayList) editActivityViewModel4.p).size())), Integer.valueOf(((ArrayList) editActivityViewModel4.p).size())}, 2));
                                    Intrinsics.checkNotNullExpressionValue(str5, "");
                                    mutableLiveData.postValue(new BatchSavingResult(1, str5, 4));
                                }
                            }
                        };
                        Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
                        ref$BooleanRef.element = true;
                        CoroutineUtilsKt.a(CoroutineUtilsKt.f150006a, new EditActivityViewModel$realDoExport$1(editActivityViewModel3, bitmapInfo3, observer, observer2, middlePageRecorder3, str4, context3, ref$BooleanRef, jCurrentTimeMillis, num3, z5, null));
                    }
                    return Unit.INSTANCE;
                }
            };
            int i2 = Build.VERSION.SDK_INT;
            if (i2 >= 24 && i2 <= 29) {
                PermissionUtil.f150127a.getClass();
                if (!PermissionUtil.e(editActivityI6)) {
                    PermissionUtil.g(editActivityI6, 7);
                    return;
                }
            }
            Function0<Unit> function0 = editActivityViewModel.p2;
            if (function0 != null) {
                ((EditActivityViewModel$doExport$1$1) function0).invoke();
            }
        }
    }

    public final IConfigManager A6() {
        IConfigManager iConfigManager = this.P0;
        if (iConfigManager != null) {
            return iConfigManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("configManager");
        return null;
    }

    public final void A7(Integer num, boolean z) {
        if (!b7()) {
            CoroutineUtilsKt.a(CoroutineUtilsKt.f150006a, new EditActivityViewModel$onReportPhotoImport$2(this, z, num, null));
            return;
        }
        IAppEventReport.PhotoImportParams photoImportParamsY6 = y6();
        photoImportParamsY6.f145010d = b7();
        CoroutineUtilsKt.a(CoroutineUtilsKt.f150006a, new EditActivityViewModel$onReportPhotoImport$3(this, photoImportParamsY6, z, num, null));
    }

    public final InterfaceC39790ts<ICoverTemplateLogic> B6() {
        InterfaceC39790ts<ICoverTemplateLogic> interfaceC39790ts = this.Z0;
        if (interfaceC39790ts != null) {
            return interfaceC39790ts;
        }
        Intrinsics.throwUninitializedPropertyAccessException("coverTemplateLogic");
        return null;
    }

    public final void B7(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        CoroutineUtilsKt.a(CoroutineUtilsKt.f150006a, new EditActivityViewModel$onReportPhotoImport$1(this, str, null));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    public final Integer C6() {
        Integer num;
        if (!p7() || (num = (Integer) this.I1.getValue()) == null) {
            return null;
        }
        int iIntValue = num.intValue();
        List<FaceInfo> value = this.D1.getValue();
        return Integer.valueOf((value == null || iIntValue >= value.size()) ? 0 : value.get(iIntValue).b);
    }

    public final int D6() {
        return (int) (((int) J6().E4().a().b) / J6().E4().a().f);
    }

    public final void D7(ITemplate iTemplate, ExtraTemplateMessage extraTemplateMessage) {
        this.n1.postValue(new AppliedTemplateInfo(iTemplate, extraTemplateMessage, Intrinsics.areEqual(this.j0.getValue(), Boolean.TRUE) ? AppliedTemplateInfo.From.f141988a : AppliedTemplateInfo.From.b));
    }

    public final int E6() {
        return (int) (((int) J6().E4().a().f64720a) / J6().E4().a().e);
    }

    public final void E7(int i, Context context) {
        MutableLiveData<BatchSavingResult> mutableLiveData = this.p0;
        StringBuilder sb = new StringBuilder();
        String string = context.getString(R.string.r_2);
        Intrinsics.checkNotNullExpressionValue(string, "");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "");
        sb.append(str);
        sb.append("% ...");
        mutableLiveData.postValue(new BatchSavingResult(1, sb.toString(), 4));
    }

    public final Deeplink F6() {
        DeeplinkManager deeplinkManager = this.f136341J;
        if (deeplinkManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deeplinkManager");
            deeplinkManager = null;
        }
        deeplinkManager.getClass();
        if (KvSettingProvider.f150073a.H()) {
            ThreadUtils.f150169a.getClass();
            if (!ThreadUtils.a()) {
                throw new IllegalStateException("Check failed.");
            }
        }
        return deeplinkManager.f136842a;
    }

    public final void F7(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        Bitmap bitmapCopy = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCopy, "");
        Canvas canvas = new Canvas(bitmapCopy);
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, (Paint) null);
        float fCoerceAtLeast = RangesKt___RangesKt.coerceAtLeast(DimenExtensionsKt.c(148) / bitmapCopy.getWidth(), DimenExtensionsKt.c(148) / bitmapCopy.getHeight());
        Matrix matrix = new Matrix();
        matrix.setScale(fCoerceAtLeast, fCoerceAtLeast);
        Bitmap bitmap2 = this.O1;
        if (bitmap2 != null) {
            bitmap2.recycle();
        }
        this.O1 = Bitmap.createBitmap(bitmapCopy, 0, 0, bitmapCopy.getWidth(), bitmapCopy.getHeight(), matrix, true);
    }

    public final String G6() {
        if (this.h0.length() <= 0) {
            return J6().U1();
        }
        StringUtils stringUtils = StringUtils.f150163a;
        String str = this.h0;
        stringUtils.getClass();
        return StringUtils.d(str);
    }

    public final void G7() {
        CoroutineUtilsKt.c(ViewModelKt.a(this), new EditActivityViewModel$refreshFaceFrameLocate$1(this, null));
    }

    public final boolean H6() {
        return !K6().b() && this.b0;
    }

    public final void H7() {
        IEditActivityScenesModel iEditActivityScenesModelJ6;
        LayerParams layerParamsDg;
        if (T6().o2() == null || (layerParamsDg = (iEditActivityScenesModelJ6 = J6()).Dg(J6().U7())) == null) {
            return;
        }
        if (Float.isNaN(layerParamsDg.f144784g) || Float.isNaN(layerParamsDg.h)) {
            XTLog.f144340a.getClass();
            XTLog.b("EditActivityViewModel", "refreshLayerConfig failed, invalid size");
            return;
        }
        float f = layerParamsDg.f144784g;
        PointF pointF = layerParamsDg.f;
        Size size = new Size((int) (f / pointF.x), (int) (layerParamsDg.h / pointF.y));
        Size size2 = new Size(iEditActivityScenesModelJ6.E4().c(), iEditActivityScenesModelJ6.E4().d());
        ITransformManager iTransformManager = this.K0;
        if (iTransformManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transformManager");
            iTransformManager = null;
        }
        iTransformManager.j2(size, new SizeF(layerParamsDg.f144784g, layerParamsDg.h), layerParamsDg.e, size2, false, false);
    }

    public final EditActivity I6() {
        WeakReference<EditActivity> weakReference = this.m;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public final void I7(Pair<Integer, String> pair, boolean z, IPainterCommon.BitmapInfo bitmapInfo, IPainterCommon.QueryBitmapInfo queryBitmapInfo) {
        if (pair.getFirst().intValue() >= 0) {
            String str = "business_tool";
            String str2 = K6().f141806a == EditMode.f ? (z || Intrinsics.areEqual(this.z, "ai_poster") || K6().b) ? "business_tool" : "business_template" : "image_edit";
            if (K6().f141806a == EditMode.b || K6().f141806a == EditMode.e) {
                str2 = "image_template";
            }
            if (K6().f141806a != EditMode.f141869g) {
                str = str2;
            } else if (!z && !Intrinsics.areEqual(this.z, "ai_poster")) {
                str = "image_edit";
            }
            if (bitmapInfo != null) {
                IdUtils.f150059a.getClass();
                x6().r1(new IAppEventReport.BlingWaterMartReportInfo(bitmapInfo.getEncodeWaterMarkTime(), IdUtils.a(), queryBitmapInfo.f144745g, bitmapInfo.isEncodeWaterMark() ? 1 : 0, str));
            }
        }
    }

    public final IEditActivityScenesModel J6() {
        IEditActivityScenesModel iEditActivityScenesModel = this.G0;
        if (iEditActivityScenesModel != null) {
            return iEditActivityScenesModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("editActivityScenesModel");
        return null;
    }

    public final void J7(boolean z) {
        IAppEventReport iAppEventReportX6 = x6();
        String strU1 = J6().U1();
        int i = this.f136342X;
        int i2 = this.Z;
        boolean zJ3 = KvSettingProvider.f150073a.J3();
        CPUUtils.f149986a.getClass();
        iAppEventReportX6.i1(i, i2, strU1, z, CPUUtils.a(), zJ3);
    }

    public final EditContext K6() {
        EditContext editContext = this.j;
        if (editContext != null) {
            return editContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("editContext");
        return null;
    }

    public final Map<String, Object> L6() {
        LatestBackground latestBackgroundQ1;
        EditFlow editFlowZ2 = J6().j().Z2();
        BackgroundLayer backgroundLayerX = T6().X();
        if (backgroundLayerX != null && (latestBackgroundQ1 = backgroundLayerX.q1()) != null) {
            editFlowZ2.setBackgroundWidth((int) latestBackgroundQ1.g());
            editFlowZ2.setBackgroundHeight((int) latestBackgroundQ1.c());
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("is_change_text", Integer.valueOf(editFlowZ2.isChangeText() ? 1 : 0));
        linkedHashMap.put("is_change_picture", Integer.valueOf(editFlowZ2.isChangePicture() ? 1 : 0));
        linkedHashMap.put("is_change_sticker", Integer.valueOf(editFlowZ2.isChangeSticker() ? 1 : 0));
        linkedHashMap.put("is_auto_layout", Integer.valueOf(editFlowZ2.isUsedAutoLayout() ? 1 : 0));
        linkedHashMap.put("is_local_auto_layout", Integer.valueOf(editFlowZ2.isLocalAutoLayout() ? 1 : 0));
        linkedHashMap.put("height", Integer.valueOf(editFlowZ2.getBackgroundHeight()));
        linkedHashMap.put("width", Integer.valueOf(editFlowZ2.getBackgroundWidth()));
        linkedHashMap.put("is_change_background", Integer.valueOf(editFlowZ2.isChangedBackground() ? 1 : 0));
        linkedHashMap.put("template_picture_cnt", Integer.valueOf(editFlowZ2.getTemplatePictureCount()));
        linkedHashMap.put("picture_cnt", Integer.valueOf(editFlowZ2.getPictureCount()));
        linkedHashMap.put("template_text_cnt", Integer.valueOf(editFlowZ2.getTemplateTextCount()));
        linkedHashMap.put("text_cnt", Integer.valueOf(editFlowZ2.getTextCount()));
        linkedHashMap.put("template_sticker_cnt", Integer.valueOf(editFlowZ2.getTemplateStickerCount()));
        linkedHashMap.put("sticker_cnt", Integer.valueOf(editFlowZ2.getStickerCount()));
        linkedHashMap.put("template_svg_cnt", Integer.valueOf(editFlowZ2.getTemplateSVGCount()));
        linkedHashMap.put("svg_cnt", Integer.valueOf(editFlowZ2.getSvgCount()));
        linkedHashMap.put("is_change_color", Integer.valueOf(editFlowZ2.isChangedColor() ? 1 : 0));
        linkedHashMap.put("is_use_styles", Integer.valueOf(editFlowZ2.isUsedStyle() ? 1 : 0));
        linkedHashMap.put("is_ai_background", Integer.valueOf(J6().Je() ? 1 : 0));
        linkedHashMap.put("ai_background_id", editFlowZ2.getAiBackgroundId());
        linkedHashMap.put("is_background_remove", Integer.valueOf((J6().i6() || q7()) ? 1 : 0));
        linkedHashMap.put("is_image_clear", Integer.valueOf(J6().Q9() ? 1 : 0));
        linkedHashMap.put("template_style_frame_cnt", Integer.valueOf(editFlowZ2.getOriginalSingleSlotImageContainerCount()));
        linkedHashMap.put("style_frame_cnt", Integer.valueOf(editFlowZ2.getCurrentSingleSlotImageContainerCount()));
        linkedHashMap.put("is_magic_eliminate", Integer.valueOf(editFlowZ2.isMagicEliminate() ? 1 : 0));
        linkedHashMap.put("is_fog_remove", Integer.valueOf(editFlowZ2.isUseFogRemove() ? 1 : 0));
        linkedHashMap.put("cutout_cnt", Integer.valueOf(editFlowZ2.getUseCutoutCnt()));
        linkedHashMap.put("is_erase", Boolean.valueOf(editFlowZ2.getUseLayerEraseCnt() > 0 || editFlowZ2.getUseLayerEraseCntInMiddle() > 0));
        StringBuffer stringBuffer = new StringBuffer();
        if (editFlowZ2.getUseLayerEraseCnt() > 0) {
            stringBuffer.append("photo_edit_page");
        }
        if (editFlowZ2.getUseLayerEraseCntInMiddle() > 0) {
            String string = stringBuffer.toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            if (string.length() == 0) {
                stringBuffer.append("photo_edit_middle_page");
            } else {
                stringBuffer.append(",photo_edit_middle_page");
            }
        }
        String string2 = stringBuffer.toString();
        Intrinsics.checkNotNullExpressionValue(string2, "");
        linkedHashMap.put("erase_position", string2);
        linkedHashMap.put("is_ai_cutout", Integer.valueOf(editFlowZ2.isUseIntelligentCutout() ? 1 : 0));
        linkedHashMap.put("is_ai_cutout_tricks", Integer.valueOf(editFlowZ2.isUseIntelligentCutoutAndPlay() ? 1 : 0));
        linkedHashMap.put("is_template_ai_cut_out", Integer.valueOf(this.a1 ? 1 : 0));
        linkedHashMap.put("is_template_ai_cutout_tricks", Integer.valueOf(this.b1 ? 1 : 0));
        EditActivity editActivityI6 = I6();
        if (editActivityI6 != null) {
            linkedHashMap.put("is_template_type", (editActivityI6.i2().v0 || K6().f141806a == EditMode.b) ? "template" : "");
        }
        linkedHashMap.put("is_use_styles", Integer.valueOf(editFlowZ2.isUsedStyle() ? 1 : 0));
        linkedHashMap.put("is_use_local_styles", Integer.valueOf(editFlowZ2.isUsedLocalStyle() ? 1 : 0));
        linkedHashMap.put("use_styles_scene", editFlowZ2.getUseStyleScene());
        FlavorConfig.f139375a.getClass();
        FlavorConfig.j();
        return linkedHashMap;
    }

    public final void L7(int i, String str) {
        String value;
        Map mapPlus = MapsKt__MapsKt.plus(MapsKt__MapsKt.plus(MapsKt__MapsKt.plus(L6(), N6()), P6()), t6());
        String str2 = this.L;
        int i2 = this.x;
        int iE6 = E6();
        int iD6 = D6();
        String str3 = Intrinsics.areEqual(this.j0.getValue(), Boolean.TRUE) ? "photo_edit_middle_page" : "photo_edit_page";
        u6().isLogin();
        int i3 = 0;
        boolean zH = u6().h();
        boolean zI6 = J6().i6();
        boolean zV2 = J6().V2();
        if (this.n1.getValue() == null || (value = this.w0.getValue()) == null) {
            value = "";
        }
        EventValue.f144995a.getClass();
        M6().U2(new SavePhotoParams(false, "", str2, i2, iE6, iD6, str3, false, zH, i, str, "", zI6, false, "", value, zV2, false, 0, false, "", false, false, false, false, "", mapPlus, 0, 0, 0, "", "", 0, false, false, 0, "", EventValue.e, 1, CollectionsKt__CollectionsKt.emptyList(), null, new EffectFlow.UVLiquefyFlow(i3)), this.H);
    }

    public final IEditReport M6() {
        IEditReport iEditReport = this.S0;
        if (iEditReport != null) {
            return iEditReport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("editReport");
        return null;
    }

    public final void M7() {
        float fB;
        List<ITemplate> listI;
        TemplateProviderImpl templateProviderImplJ = O6().j();
        String strG = templateProviderImplJ.G();
        if (strG == null) {
            return;
        }
        int i = this.Z;
        if (i != 0) {
            fB = this.f136342X / i;
        } else {
            IImportBackgroundData iImportBackgroundData = this.P;
            fB = iImportBackgroundData != null ? iImportBackgroundData.b() : 1.0f;
        }
        new RatioTemplateRequestParams(strG, fB);
        ITemplateGroup iTemplateGroup = (ITemplateGroup) templateProviderImplJ.w(fB, strG).getValue();
        if (iTemplateGroup == null || (listI = iTemplateGroup.I()) == null || !(!listI.isEmpty())) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.a(this), null, null, new EditActivityViewModel$requestRatioTemplate$1(templateProviderImplJ, strG, fB, null), 3, null);
        }
    }

    public final Map<String, Object> N6() {
        Object next;
        boolean z;
        boolean z2;
        EffectFlow effectFlowIf = J6().j().If(0, 0L);
        Iterator<T> it = effectFlowIf.getIntelligenceItemList().iterator();
        do {
            next = null;
            if (!it.hasNext()) {
                break;
            }
            next = it.next();
        } while (!((EffectFlow.CommonItem) next).i);
        int i = 1;
        boolean z3 = next != null;
        Iterator<T> it2 = effectFlowIf.getHairList().iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            Object next2 = it2.next();
            if (((EffectFlow.HairItem) next2).f) {
                z = next2 != null;
            }
        }
        Iterator<T> it3 = effectFlowIf.getExpressionList().iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next3 = it3.next();
            if (((EffectFlow.ExpressionItem) next3).e) {
                z2 = next3 != null;
            }
        }
        if (!z3 && !z && !z2) {
            i = 0;
        }
        return MapsKt__MapsJVMKt.mapOf(TuplesKt.to("is_ai_portrait", Integer.valueOf(i)));
    }

    public final void N7() {
        String queryParameter;
        Deeplink deeplinkF6 = F6();
        if (deeplinkF6 == null || deeplinkF6.b != Deeplink.Type.f141898a || (queryParameter = deeplinkF6.f141896a.getQueryParameter("operate_source")) == null || queryParameter.length() == 0) {
            return;
        }
        Intrinsics.checkNotNull(queryParameter);
        this.e1 = queryParameter;
    }

    public final IEffectProvider O6() {
        IEffectProvider iEffectProvider = this.M0;
        if (iEffectProvider != null) {
            return iEffectProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("effectProvider");
        return null;
    }

    public final void O7(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new EditActivityViewModel$resumeEditPage$1(this, str, str2, null), 3, null);
    }

    public final Map<String, Object> P6() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("is_adjust_layer_order", Integer.valueOf(K6().e ? 1 : 0));
        linkedHashMap.put("is_ai_eliminate", Integer.valueOf(K6().f ? 1 : 0));
        try {
            if (this.m2.length() > 0) {
                JSONObject jSONObject = new JSONObject(this.m2);
                Iterator<String> itKeys = jSONObject.keys();
                Intrinsics.checkNotNullExpressionValue(itKeys, "");
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    Intrinsics.checkNotNull(next);
                    String strOptString = jSONObject.optString(next);
                    Intrinsics.checkNotNullExpressionValue(strOptString, "");
                    linkedHashMap.put(next, strOptString);
                }
            }
            Result.m17090constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.m17090constructorimpl(ResultKt.createFailure(th));
        }
        return linkedHashMap;
    }

    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    /* JADX WARN: Removed duplicated region for block: B:107:0x02bd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0039 A[PHI: r12
      0x0039: PHI (r12v14 java.lang.Object) = (r12v13 java.lang.Object), (r12v0 java.lang.Object) binds: [B:106:0x02bb, B:13:0x0036] A[DONT_GENERATE, DONT_INLINE], RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0226 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x025a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x025d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:88:0x0224 -> B:91:0x0238). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object P7(android.content.Context r28, com.xt.retouch.painter.function.api.IPainterCommon.BitmapInfo r29, X.C1W6 r30, com.xt.retouch.painter.trace.EffectFlow r31, kotlin.coroutines.Continuation r32) {
        /*
            r27 = this;
            r9 = r32
            r0 = r28
            r6 = r29
            r3 = r30
            r7 = r31
            boolean r1 = r9 instanceof com.xt.edit.EditActivityViewModel$saveAtlas$1
            r4 = r27
            if (r1 == 0) goto L2be
            r2 = r9
            com.xt.edit.EditActivityViewModel$saveAtlas$1 r2 = (com.xt.edit.EditActivityViewModel$saveAtlas$1) r2
            int r8 = r2.y
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r8 & r5
            if (r1 == 0) goto L2be
            int r8 = r8 - r5
            r2.y = r8
        L1e:
            java.lang.Object r12 = r2.w
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r11 = r2.y
            r5 = 2
            r23 = 0
            r9 = 1
            r10 = 4
            r8 = 3
            if (r11 == 0) goto L3a
            if (r11 == r9) goto L1c9
            if (r11 == r5) goto L227
            if (r11 == r8) goto L2b0
            if (r11 != r10) goto L2c5
            kotlin.ResultKt.throwOnFailure(r12)
        L39:
            return r12
        L3a:
            kotlin.ResultKt.throwOnFailure(r12)
            com.xt.retouch.baseflavorconfig.FlavorConfig r5 = com.xt.retouch.baseflavorconfig.FlavorConfig.f139375a
            r5.getClass()
            boolean r5 = com.xt.retouch.baseflavorconfig.FlavorConfig.g()
            if (r5 != 0) goto L2a8
            r11 = 0
        L49:
            boolean r5 = p6()
            if (r5 == 0) goto L5e
            com.xt.retouch.draft.api.DraftSaveResult r12 = new com.xt.retouch.draft.api.DraftSaveResult
            r14 = 0
            r17 = 14
            r18 = 0
            r13 = r9
            r16 = r23
            r12.<init>(r13, r14, r16, r17, r18)
        L5d:
            return r12
        L5e:
            java.lang.String r5 = "EditActivityViewModel"
            if (r3 == 0) goto L260
            com.xt.retouch.debug.api.IEffectAutoTest r9 = r4.b
            if (r9 == 0) goto Le5
        L66:
            boolean r9 = r9.h()
            if (r9 != 0) goto L260
            if (r11 == 0) goto L260
            java.util.List r5 = r7.getTemplateItemList()
            java.lang.Object r13 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r5)
            com.xt.retouch.painter.model.template.TemplateItem r13 = (com.xt.retouch.painter.model.template.TemplateItem) r13
            java.util.ArrayList r17 = new java.util.ArrayList
            r17.<init>()
            if (r13 == 0) goto L9c
            com.xt.retouch.effect.api.IEffectProducer r8 = r4.L0
            if (r8 == 0) goto Ldd
        L83:
            java.lang.String r5 = r13.getTemplateId()
            template.ITemplate r5 = r8.c(r5)
            if (r5 == 0) goto L9c
            java.util.List r8 = r5.Y()
            if (r8 == 0) goto L9c
            r5 = r17
            boolean r5 = r5.addAll(r8)
            java.lang.Boolean.valueOf(r5)
        L9c:
            java.lang.String r5 = r3.f6946a
            r20 = r5
            java.lang.String r5 = r3.b
            r19 = r5
            if (r6 == 0) goto Lda
            android.graphics.Bitmap r18 = r6.getBitmap()
        Laa:
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            com.retouch.layermanager.api.layer.ILayerManager r5 = r4.T6()
            java.util.List r5 = r5.I1()
            r11.addAll(r5)
            java.util.Iterator r9 = r11.iterator()
        Lc3:
            boolean r5 = r9.hasNext()
            java.lang.String r10 = ""
            if (r5 == 0) goto Lee
            java.lang.Object r8 = r9.next()
            com.retouch.layermanager.api.layer.Layer r8 = (com.retouch.layermanager.api.layer.Layer) r8
            com.xt.retouch.lib.log.XTLog r5 = com.xt.retouch.lib.log.XTLog.f144340a
            r8.z1()
            r5.getClass()
            goto Lc3
        Lda:
            r18 = r23
            goto Laa
        Ldd:
            java.lang.String r5 = "effectProducer"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            r8 = r23
            goto L83
        Le5:
            java.lang.String r9 = "autoTest"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = r23
            goto L66
        Lee:
            java.util.List<com.retouch.layermanager.api.layer.Layer> r5 = r4.g2
            java.util.ArrayList r5 = (java.util.ArrayList) r5
            java.util.Iterator r16 = r5.iterator()
        Lf6:
            boolean r5 = r16.hasNext()
            if (r5 == 0) goto L138
            java.lang.Object r9 = r16.next()
            com.retouch.layermanager.api.layer.Layer r9 = (com.retouch.layermanager.api.layer.Layer) r9
            int r8 = kotlin.collections.CollectionsKt__CollectionsKt.getLastIndex(r11)
        L106:
            r5 = -1
            if (r5 >= r8) goto Lf6
            java.lang.Object r5 = r11.get(r8)
            com.retouch.layermanager.api.layer.Layer r5 = (com.retouch.layermanager.api.layer.Layer) r5
            java.lang.String r15 = r5.z1()
            java.lang.String r14 = r9.z1()
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r14)
            if (r14 != 0) goto L129
            java.lang.String r15 = r5.z1()
            java.lang.String r14 = "picture"
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r14)
            if (r14 == 0) goto L135
        L129:
            r11.remove(r8)
            com.xt.retouch.lib.log.XTLog r8 = com.xt.retouch.lib.log.XTLog.f144340a
            r5.z1()
            r8.getClass()
            goto Lf6
        L135:
            int r8 = r8 + (-1)
            goto L106
        L138:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r5 = r4.J6()
            java.util.List r5 = r5.U4()
            java.util.Iterator r9 = r5.iterator()
        L144:
            boolean r5 = r9.hasNext()
            if (r5 == 0) goto L164
            java.lang.Object r8 = r9.next()
            java.util.Map<java.lang.String, java.lang.String> r5 = r4.u2
            java.lang.Object r8 = r5.get(r8)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L144
            r5 = 1
            java.lang.String r5 = r8.substring(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r10)
            r12.add(r5)
            goto L144
        L164:
            java.util.Iterator r9 = r11.iterator()
        L168:
            boolean r5 = r9.hasNext()
            if (r5 == 0) goto L18e
            java.lang.Object r5 = r9.next()
            com.retouch.layermanager.api.layer.Layer r5 = (com.retouch.layermanager.api.layer.Layer) r5
            java.util.Map<com.retouch.layermanager.api.layer.LayerType, java.lang.String> r8 = r4.v2
            com.retouch.layermanager.api.layer.LayerType r5 = r5.T1()
            java.lang.Object r8 = r8.get(r5)
            java.lang.String r8 = (java.lang.String) r8
            if (r8 == 0) goto L168
            r5 = 1
            java.lang.String r5 = r8.substring(r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r10)
            r12.add(r5)
            goto L168
        L18e:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r5 = r4.J6()
            com.xt.retouch.painter.trace.EffectFlow r5 = com.xt.retouch.painter.function.api.IPainterCommon.DefaultImpls.d(r5)
            java.util.List r5 = r5.getTemplateItemList()
            boolean r5 = r5.isEmpty()
            r5 = r5 ^ 1
            if (r5 == 0) goto L1a7
            java.lang.String r5 = "template"
            r12.add(r5)
        L1a7:
            java.lang.String r8 = r4.F
            r2.q = r0
            r2.r = r6
            r2.s = r3
            r2.t = r7
            r5 = 1
            r2.y = r5
            r21 = r17
            r22 = r2
            r15 = r13
            r16 = r20
            r17 = r19
            r19 = r12
            r20 = r8
            r14 = r4
            java.lang.Object r12 = r14.Q7(r15, r16, r17, r18, r19, r20, r21, r22)
            if (r12 != r1) goto L1d6
            return r1
        L1c9:
            com.xt.retouch.painter.trace.EffectFlow r7 = r2.t
            X.1W6 r3 = r2.s
            com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo r6 = r2.r
            java.lang.Object r0 = r2.q
            android.content.Context r0 = (android.content.Context) r0
            kotlin.ResultKt.throwOnFailure(r12)
        L1d6:
            com.xt.retouch.draft.api.DraftSaveResult r12 = (com.xt.retouch.draft.api.DraftSaveResult) r12
            com.xt.retouch.util.AssistConfig r5 = com.xt.retouch.util.AssistConfig.f149970a
            r5.getClass()
            boolean r5 = com.xt.retouch.util.AssistConfig.e()
            if (r5 == 0) goto L5d
            com.xt.retouch.util.KvSettingProvider r5 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r5 = r5.J()
            if (r5 == 0) goto L5d
            r8 = r12
            r5 = 0
        L1ed:
            java.util.List r9 = r7.getTemplateItemList()
            java.lang.Object r10 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r9)
            com.xt.retouch.painter.model.template.TemplateItem r10 = (com.xt.retouch.painter.model.template.TemplateItem) r10
            java.lang.String r11 = r3.f6946a
            java.lang.String r9 = r3.b
            if (r6 == 0) goto L25a
            android.graphics.Bitmap r13 = r6.getBitmap()
        L201:
            r2.q = r0
            r2.r = r6
            r2.s = r3
            r2.t = r7
            r2.u = r8
            r2.v = r5
            r12 = 2
            r2.y = r12
            java.util.List r14 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            java.lang.String r15 = ""
            java.util.List r16 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            r17 = r2
            r10 = r10
            r11 = r11
            r12 = r9
            r9 = r4
            java.lang.Object r9 = r9.Q7(r10, r11, r12, r13, r14, r15, r16, r17)
            if (r9 != r1) goto L238
            return r1
        L227:
            int r5 = r2.v
            com.xt.retouch.draft.api.DraftSaveResult r8 = r2.u
            com.xt.retouch.painter.trace.EffectFlow r7 = r2.t
            X.1W6 r3 = r2.s
            com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo r6 = r2.r
            java.lang.Object r0 = r2.q
            android.content.Context r0 = (android.content.Context) r0
            kotlin.ResultKt.throwOnFailure(r12)
        L238:
            com.xt.retouch.baseui.ToastUtils r20 = com.xt.retouch.baseui.ToastUtils.f139481a
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r9 = "count = "
            r10.<init>(r9)
            int r5 = r5 + 1
            r10.append(r5)
            java.lang.String r22 = r10.toString()
            r24 = 0
            r26 = 28
            r21 = r0
            r25 = r24
            com.xt.retouch.baseui.ToastUtils.f(r20, r21, r22, r23, r24, r25, r26)
            r9 = 100
            if (r5 >= r9) goto L25d
            goto L1ed
        L25a:
            r13 = r23
            goto L201
        L25d:
            r12 = r8
            goto L5d
        L260:
            com.xt.retouch.config.api.IConfigManager r0 = r4.A6()
            boolean r0 = com.xt.retouch.config.api.ConfigExtKt.a(r0)
            if (r0 == 0) goto L298
            if (r3 != 0) goto L298
            com.xt.retouch.lib.log.XTLog r6 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "draft-flow:export "
            r3.<init>(r0)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r4.J6()
            java.lang.String r0 = r0.U1()
            r3.append(r0)
            java.lang.String r0 = " failed, image null, keep draft"
            r3.append(r0)
            java.lang.String r0 = r3.toString()
            r6.getClass()
            com.xt.retouch.lib.log.XTLog.f(r5, r0)
            r2.y = r8
            java.lang.Object r12 = r4.d7(r2)
            if (r12 != r1) goto L2b3
            return r1
        L298:
            com.xt.retouch.draft.api.DraftSaveResult r12 = new com.xt.retouch.draft.api.DraftSaveResult
            r13 = 3
            r14 = 0
            r17 = 14
            r18 = 0
            r16 = r23
            r12.<init>(r13, r14, r16, r17, r18)
            goto L5d
        L2a8:
            com.xt.retouch.util.KvSettingProvider r5 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r11 = r5.o()
            goto L49
        L2b0:
            kotlin.ResultKt.throwOnFailure(r12)
        L2b3:
            kotlinx.coroutines.Deferred r12 = (kotlinx.coroutines.Deferred) r12
            r2.y = r10
            java.lang.Object r12 = r12.await(r2)
            if (r12 != r1) goto L39
            return r1
        L2be:
            com.xt.edit.EditActivityViewModel$saveAtlas$1 r2 = new com.xt.edit.EditActivityViewModel$saveAtlas$1
            r2.<init>(r4, r9)
            goto L1e
        L2c5:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.P7(android.content.Context, com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo, X.1W6, com.xt.retouch.painter.trace.EffectFlow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final MutableLiveData Q6() {
        return this.I1;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x003c  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x015c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object Q7(final com.xt.retouch.painter.model.template.TemplateItem r24, final java.lang.String r25, final java.lang.String r26, final android.graphics.Bitmap r27, final java.util.List<java.lang.String> r28, final java.lang.String r29, final java.util.List<java.lang.String> r30, kotlin.coroutines.Continuation<? super com.xt.retouch.draft.api.DraftSaveResult> r31) {
        /*
            r23 = this;
            r3 = r31
            boolean r0 = r3 instanceof com.xt.edit.EditActivityViewModel$saveAtlasBlocked$1
            r9 = r23
            if (r0 == 0) goto L15c
            r4 = r3
            com.xt.edit.EditActivityViewModel$saveAtlasBlocked$1 r4 = (com.xt.edit.EditActivityViewModel$saveAtlasBlocked$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L15c
            int r2 = r2 - r1
            r4.t = r2
        L16:
            java.lang.Object r6 = r4.r
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r4.t
            r0 = 2
            r2 = 1
            java.lang.String r1 = ""
            if (r5 == 0) goto L3e
            if (r5 == r2) goto L146
            if (r5 != r0) goto L163
            java.lang.Object r15 = r4.q
            java.lang.String r15 = (java.lang.String) r15
            kotlin.ResultKt.throwOnFailure(r6)
            r5 = 1
        L30:
            com.xt.retouch.draft.api.DraftSaveResult r6 = (com.xt.retouch.draft.api.DraftSaveResult) r6
        L32:
            int r0 = r6.f141642a
            if (r0 != 0) goto L3c
        L36:
            r9.g0 = r15
            r9.l7(r5, r6)
            return r6
        L3c:
            r15 = r1
            goto L36
        L3e:
            kotlin.ResultKt.throwOnFailure(r6)
            com.xt.retouch.painter.trace.EffectFlow$FilterIdDesc r13 = new com.xt.retouch.painter.trace.EffectFlow$FilterIdDesc
            com.xt.retouch.scenes.api.IEditActivityScenesModel r5 = r9.J6()
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r9.J6()
            java.util.List r0 = r0.H()
            java.lang.String r8 = r5.ug(r0)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r5 = r9.J6()
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r9.J6()
            java.util.List r0 = r0.q()
            java.lang.String r7 = r5.ug(r0)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r9.J6()
            com.xt.retouch.painter.trace.EffectFlow r0 = com.xt.retouch.painter.function.api.IPainterCommon.DefaultImpls.d(r0)
            java.util.List r0 = r0.getPlayFunctionItemList()
            java.lang.StringBuffer r6 = new java.lang.StringBuffer
            r6.<init>()
            java.util.Iterator r5 = r0.iterator()
        L78:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L8f
            java.lang.Object r0 = r5.next()
            com.xt.retouch.painter.trace.EffectFlow$PlayFunctionItem r0 = (com.xt.retouch.painter.trace.EffectFlow.PlayFunctionItem) r0
            java.lang.String r0 = r0.b
            r6.append(r0)
            java.lang.String r0 = ","
            r6.append(r0)
            goto L78
        L8f:
            java.lang.String r0 = r6.toString()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)
            r13.<init>(r8, r7, r0)
            java.lang.String r0 = r9.T
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L136
            com.xt.retouch.util.IdUtils r0 = com.xt.retouch.util.IdUtils.f150059a
            r0.getClass()
            java.lang.String r15 = com.xt.retouch.util.IdUtils.a()
        Laa:
            com.xt.retouch.lib.log.XTLog r6 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "draft-flow:saveAtlasBlocked draft:"
            r5.<init>(r0)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r9.J6()
            java.lang.String r0 = r0.U1()
            r5.append(r0)
            java.lang.String r0 = " to atlasId:"
            r5.append(r0)
            r5.append(r15)
            java.lang.String r5 = r5.toString()
            r6.getClass()
            java.lang.String r0 = "EditActivityViewModel"
            com.xt.retouch.lib.log.XTLog.e(r0, r5)
            kotlin.jvm.internal.Ref$IntRef r11 = new kotlin.jvm.internal.Ref$IntRef
            r11.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r10 = new kotlin.jvm.internal.Ref$BooleanRef
            r10.<init>()
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r9.J6()
            java.util.Set r0 = r0.y8()
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto Lfe
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r9.J6()
            java.lang.Integer r0 = r0.b2()
            if (r0 == 0) goto L134
            int r0 = r0.intValue()
        Lfa:
            r11.element = r0
            r10.element = r2
        Lfe:
            com.xt.retouch.draftbox.api.ImageDraftBoxManager r0 = r9.S6()
            java.lang.String r17 = r0.e(r15)
            com.xt.edit.EditActivityViewModel$saveAtlasBlocked$handler$1 r8 = new com.xt.edit.EditActivityViewModel$saveAtlasBlocked$handler$1
            r2 = r8
            r12 = r24
            r14 = r25
            r16 = r26
            r21 = r27
            r19 = r28
            r18 = r29
            r20 = r30
            r8.<init>()
            boolean r0 = p6()
            if (r0 == 0) goto L13a
            com.xt.retouch.draft.api.DraftSaveResult r6 = new com.xt.retouch.draft.api.DraftSaveResult
            r20 = 0
            r17 = 1
            r18 = 0
            r21 = 14
            r22 = 0
            r16 = r6
            r16.<init>(r17, r18, r20, r21, r22)
            r5 = 1
            goto L32
        L134:
            r0 = 0
            goto Lfa
        L136:
            java.lang.String r15 = r9.T
            goto Laa
        L13a:
            r4.q = r15
            r5 = 1
            r4.t = r5
            java.lang.Object r6 = r9.m8(r4, r2)
            if (r6 != r3) goto L14e
            return r3
        L146:
            java.lang.Object r15 = r4.q
            java.lang.String r15 = (java.lang.String) r15
            kotlin.ResultKt.throwOnFailure(r6)
            r5 = 1
        L14e:
            kotlinx.coroutines.Deferred r6 = (kotlinx.coroutines.Deferred) r6
            r4.q = r15
            r0 = 2
            r4.t = r0
            java.lang.Object r6 = r6.await(r4)
            if (r6 != r3) goto L30
            return r3
        L15c:
            com.xt.edit.EditActivityViewModel$saveAtlasBlocked$1 r4 = new com.xt.edit.EditActivityViewModel$saveAtlasBlocked$1
            r4.<init>(r9, r3)
            goto L16
        L163:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.Q7(com.xt.retouch.painter.model.template.TemplateItem, java.lang.String, java.lang.String, android.graphics.Bitmap, java.util.List, java.lang.String, java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final MutableLiveData<List<FaceInfo>> R6() {
        return this.D1;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object R7(kotlin.coroutines.Continuation r11, kotlin.jvm.functions.Function1 r12) {
        /*
            r10 = this;
            boolean r0 = r11 instanceof com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$1
            if (r0 == 0) goto L48
            r4 = r11
            com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$1 r4 = (com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L48
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.t
            r0 = 1
            if (r1 == 0) goto L3a
            if (r1 != r0) goto L4e
            kotlin.jvm.functions.Function1 r12 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            kotlinx.coroutines.Deferred r3 = (kotlinx.coroutines.Deferred) r3
            kotlinx.coroutines.GlobalScope r4 = kotlinx.coroutines.GlobalScope.INSTANCE
            kotlinx.coroutines.CoroutineDispatcher r5 = kotlinx.coroutines.Dispatchers.getIO()
            r6 = 0
            com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$2 r7 = new com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$2
            r7.<init>(r3, r10, r12, r6)
            r8 = 2
            r9 = r6
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r4, r5, r6, r7, r8, r9)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L3a:
            kotlin.ResultKt.throwOnFailure(r3)
            r4.q = r12
            r4.t = r0
            java.lang.Object r3 = r10.d7(r4)
            if (r3 != r2) goto L24
            return r2
        L48:
            com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$1 r4 = new com.xt.edit.EditActivityViewModel$saveBoxDraftAsync$1
            r4.<init>(r10, r11)
            goto L12
        L4e:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.R7(kotlin.coroutines.Continuation, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    public final ImageDraftBoxManager S6() {
        ImageDraftBoxManager imageDraftBoxManager = this.U0;
        if (imageDraftBoxManager != null) {
            return imageDraftBoxManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageDraftBoxManager");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object S7(kotlin.coroutines.Continuation<? super com.xt.retouch.draft.api.DraftSaveResult> r7) {
        /*
            r6 = this;
            boolean r0 = r7 instanceof com.xt.edit.EditActivityViewModel$saveBoxDraftBlocked$1
            if (r0 == 0) goto L46
            r5 = r7
            com.xt.edit.EditActivityViewModel$saveBoxDraftBlocked$1 r5 = (com.xt.edit.EditActivityViewModel$saveBoxDraftBlocked$1) r5
            int r2 = r5.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L46
            int r2 = r2 - r1
            r5.s = r2
        L12:
            java.lang.Object r4 = r5.q
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r5.s
            r1 = 2
            r0 = 1
            if (r2 == 0) goto L2c
            if (r2 == r0) goto L38
            if (r2 != r1) goto L4c
            kotlin.ResultKt.throwOnFailure(r4)
        L25:
            com.xt.retouch.draft.api.DraftSaveResult r4 = (com.xt.retouch.draft.api.DraftSaveResult) r4
            r0 = 0
            r6.l7(r0, r4)
            return r4
        L2c:
            kotlin.ResultKt.throwOnFailure(r4)
            r5.s = r0
            java.lang.Object r4 = r6.d7(r5)
            if (r4 != r3) goto L3b
            return r3
        L38:
            kotlin.ResultKt.throwOnFailure(r4)
        L3b:
            kotlinx.coroutines.Deferred r4 = (kotlinx.coroutines.Deferred) r4
            r5.s = r1
            java.lang.Object r4 = r4.await(r5)
            if (r4 != r3) goto L25
            return r3
        L46:
            com.xt.edit.EditActivityViewModel$saveBoxDraftBlocked$1 r5 = new com.xt.edit.EditActivityViewModel$saveBoxDraftBlocked$1
            r5.<init>(r6, r7)
            goto L12
        L4c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.S7(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final ILayerManager T6() {
        ILayerManager iLayerManager = this.J0;
        if (iLayerManager != null) {
            return iLayerManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layerManager");
        return null;
    }

    public final int U6() {
        return ((Number) this.X1.getValue()).intValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x016e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object U7(android.content.Context r43, kotlin.coroutines.Continuation<? super X.C1W6> r44) {
        /*
            r42 = this;
            r4 = r44
            boolean r1 = r4 instanceof com.xt.edit.EditActivityViewModel$saveImageSuspend$1
            r0 = r42
            if (r1 == 0) goto L16e
            r5 = r4
            com.xt.edit.EditActivityViewModel$saveImageSuspend$1 r5 = (com.xt.edit.EditActivityViewModel$saveImageSuspend$1) r5
            int r3 = r5.s
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r3 & r2
            if (r1 == 0) goto L16e
            int r3 = r3 - r2
            r5.s = r3
        L16:
            java.lang.Object r2 = r5.q
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.s
            r11 = 0
            r3 = 1
            if (r1 == 0) goto L125
            if (r1 != r3) goto L176
            kotlin.ResultKt.throwOnFailure(r2)
        L27:
            java.lang.Long[] r5 = new java.lang.Long[r3]
            r3 = 0
            java.lang.Long r1 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r3)
            r5[r11] = r1
            java.util.List r3 = kotlin.collections.CollectionsKt__CollectionsKt.mutableListOf(r5)
            java.lang.String r18 = ""
            java.lang.String[] r1 = new java.lang.String[]{r18}
            java.util.List r5 = kotlin.collections.CollectionsKt__CollectionsKt.mutableListOf(r1)
            java.util.Iterator r17 = r3.iterator()
            r6 = 0
        L44:
            boolean r1 = r17.hasNext()
            if (r1 == 0) goto L175
            java.lang.Object r1 = r17.next()
            int r16 = r6 + 1
            if (r6 >= 0) goto L55
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L55:
            java.lang.Number r1 = (java.lang.Number) r1
            long r36 = r1.longValue()
            r0.i8()
            com.xt.retouch.scenes.api.IEditActivityScenesModel r19 = r0.J6()
            java.lang.String r15 = r0.c1
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.AppliedTemplateInfo> r1 = r0.n1
            java.lang.Object r1 = r1.getValue()
            com.xt.retouch.edit.base.model.AppliedTemplateInfo r1 = (com.xt.retouch.edit.base.model.AppliedTemplateInfo) r1
            if (r1 == 0) goto L72
            template.ITemplate r4 = r1.f141985a
            if (r4 != 0) goto L77
        L72:
            com.xt.edit.EditActivityViewModel$saveImageSuspend$2$1 r4 = new com.xt.edit.EditActivityViewModel$saveImageSuspend$2$1
            r4.<init>()
        L77:
            java.lang.String r14 = r0.f1
            java.lang.String r13 = r0.d1
            java.lang.String r12 = r0.z0
            java.lang.String r11 = r0.A0
            java.lang.String r27 = r0.g7()
            androidx.lifecycle.MutableLiveData<java.lang.String> r1 = r0.w0
            java.lang.Object r3 = r1.getValue()
            java.lang.String r3 = (java.lang.String) r3
            if (r3 != 0) goto L8f
            r3 = r18
        L8f:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            boolean r29 = r0.v7()
            java.lang.String r7 = r0.e1
            com.xt.retouch.edit.base.report.TemplateReport r1 = r0.e
            r8 = 0
            if (r1 == 0) goto L11d
        L9d:
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.AppliedTemplateInfo> r8 = r0.n1
            java.lang.Object r8 = r8.getValue()
            com.xt.retouch.edit.base.model.AppliedTemplateInfo r8 = (com.xt.retouch.edit.base.model.AppliedTemplateInfo) r8
            if (r8 == 0) goto L119
            com.example.template.data.ExtraTemplateMessage r10 = r8.b
            if (r10 == 0) goto L119
            kotlin.jvm.functions.Function2<? super template.ITemplate, ? super template.ITemplateGroup, java.lang.Integer> r9 = r1.f142165d
            if (r9 == 0) goto L115
            template.ITemplate r8 = r10.b
            template.ITemplateGroup r1 = r10.f48714a
            java.lang.Object r1 = r9.invoke(r8, r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            if (r1 == 0) goto L115
            r1.intValue()
        Lbe:
            androidx.lifecycle.MutableLiveData<java.util.List<com.xt.retouch.edit.base.portrait.FaceInfo>> r1 = r0.D1
            java.lang.Object r1 = r1.getValue()
            java.util.List r1 = (java.util.List) r1
            if (r1 == 0) goto L112
            int r32 = r1.size()
        Lcc:
            java.lang.String r33 = r0.Z6()
            java.lang.String r34 = r0.Y6()
            java.lang.String r35 = r0.a7()
            if (r6 < 0) goto L10f
            int r1 = r5.size()
            if (r6 >= r1) goto L10f
            java.lang.Object r1 = r5.get(r6)
        Le4:
            java.lang.String r1 = (java.lang.String) r1
            com.xt.retouch.report.api.IAppEventReport r6 = r0.x6()
            java.lang.String r39 = r6.e()
            r26 = 0
            r40 = 0
            r25 = r11
            r28 = r3
            r30 = r7
            r31 = r26
            r38 = r1
            r41 = r26
            r21 = r4
            r22 = r14
            r23 = r13
            r24 = r12
            r20 = r15
            r19.Y6(r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r38, r39, r40, r41)
            r6 = r16
            goto L44
        L10f:
            r1 = r18
            goto Le4
        L112:
            r32 = 0
            goto Lcc
        L115:
            r10.a()
            goto Lbe
        L119:
            r1.getClass()
            goto Lbe
        L11d:
            java.lang.String r1 = "templateReport"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
            r1 = r8
            goto L9d
        L125:
            kotlin.ResultKt.throwOnFailure(r2)
            android.graphics.Rect r10 = new android.graphics.Rect
            r10.<init>()
            com.xt.retouch.painter.function.api.IPainterCommon$QueryBitmapInfo r8 = new com.xt.retouch.painter.function.api.IPainterCommon$QueryBitmapInfo
            com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r0.J6()
            int r9 = r1.U7()
            r13 = 0
            r14 = 124(0x7c, float:1.74E-43)
            r12 = r11
            r8.<init>(r9, r10, r11, r12, r13, r14)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r0.J6()
            com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo r7 = r1.Q8(r8)
            com.xt.retouch.edit.base.util.BlindWaterEncodeManager r6 = com.xt.retouch.edit.base.util.BlindWaterEncodeManager.f142270a
            com.xt.retouch.edit.base.api.EditContext r1 = r0.K6()
            com.xt.retouch.edit.base.context.EditMode r2 = r1.f141806a
            com.xt.retouch.config.api.IConfigManager r1 = r0.A6()
            r6.getClass()
            kotlin.Pair r1 = com.xt.retouch.edit.base.util.BlindWaterEncodeManager.b(r11, r2, r1)
            r0.I7(r1, r11, r7, r8)
            java.lang.String r10 = ""
            r5.s = r3
            r8 = r43
            r6 = r0
            r7 = r7
            r9 = r13
            r11 = r11
            r12 = r5
            java.lang.Object r2 = r6.m7(r7, r8, r9, r10, r11, r12)
            if (r2 != r4) goto L27
            return r4
        L16e:
            com.xt.edit.EditActivityViewModel$saveImageSuspend$1 r5 = new com.xt.edit.EditActivityViewModel$saveImageSuspend$1
            r5.<init>(r0, r4)
            goto L16
        L175:
            return r2
        L176:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.U7(android.content.Context, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final IPerformanceManager V6() {
        IPerformanceManager iPerformanceManager = this.T0;
        if (iPerformanceManager != null) {
            return iPerformanceManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("performanceManager");
        return null;
    }

    public final void V7(Function1<? super Bitmap, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.a(this), null, null, new EditActivityViewModel$saveImageToBitmap$1(function1, this, null), 3, null);
    }

    public final IPersonalTemplateManager W6() {
        IPersonalTemplateManager iPersonalTemplateManager = this.f;
        if (iPersonalTemplateManager != null) {
            return iPersonalTemplateManager;
        }
        Intrinsics.throwUninitializedPropertyAccessException("personalTemplateManager");
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x004d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object W7(com.xt.edit.api.modle.SaveDraftHelper r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r6 = this;
            boolean r0 = r8 instanceof com.xt.edit.EditActivityViewModel$saveVegaDraft$1
            if (r0 == 0) goto L4d
            r5 = r8
            com.xt.edit.EditActivityViewModel$saveVegaDraft$1 r5 = (com.xt.edit.EditActivityViewModel$saveVegaDraft$1) r5
            int r2 = r5.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L4d
            int r2 = r2 - r1
            r5.t = r2
        L12:
            java.lang.Object r4 = r5.r
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r5.t
            r1 = 2
            r0 = 1
            if (r2 == 0) goto L28
            if (r2 == r0) goto L36
            if (r2 != r1) goto L53
            kotlin.ResultKt.throwOnFailure(r4)
        L25:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L28:
            kotlin.ResultKt.throwOnFailure(r4)
            r5.q = r7
            r5.t = r0
            java.lang.Object r4 = r6.S7(r5)
            if (r4 != r3) goto L3b
            return r3
        L36:
            com.xt.edit.api.modle.SaveDraftHelper r7 = r5.q
            kotlin.ResultKt.throwOnFailure(r4)
        L3b:
            com.xt.retouch.draft.api.DraftSaveResult r4 = (com.xt.retouch.draft.api.DraftSaveResult) r4
            int r0 = r4.f141642a
            if (r0 != 0) goto L5b
            r0 = 0
            r5.q = r0
            r5.t = r1
            java.lang.Object r0 = r7.a(r5)
            if (r0 != r3) goto L25
            return r3
        L4d:
            com.xt.edit.EditActivityViewModel$saveVegaDraft$1 r5 = new com.xt.edit.EditActivityViewModel$saveVegaDraft$1
            r5.<init>(r6, r8)
            goto L12
        L53:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L5b:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.W7(com.xt.edit.api.modle.SaveDraftHelper, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final IPhotoImportReport X6() {
        IPhotoImportReport iPhotoImportReport = this.k2;
        if (iPhotoImportReport != null) {
            return iPhotoImportReport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("photoImportReport");
        return null;
    }

    public final void X7(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.T = str;
    }

    public final String Y6() {
        RecogClassificationResult recogClassificationResult = this.B;
        String str = "";
        if (recogClassificationResult != null) {
            for (TagInfo tagInfo : recogClassificationResult.getTopTag(10, 10)) {
                str = (str + tagInfo.getId() + ':' + ((Format) this.A.getValue()).format(Float.valueOf(tagInfo.getProb()))) + ',';
            }
        }
        return str;
    }

    public final void Y7(Deeplink deeplink) {
        Intrinsics.checkNotNullParameter(deeplink, "");
        String path = deeplink.f141896a.getPath();
        if (!(!(path == null || path.length() == 0))) {
            throw new IllegalStateException("Check failed.");
        }
        DeeplinkManager deeplinkManager = this.f136341J;
        if (deeplinkManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deeplinkManager");
            deeplinkManager = null;
        }
        deeplinkManager.getClass();
        if (KvSettingProvider.f150073a.H()) {
            ThreadUtils.f150169a.getClass();
            if (!ThreadUtils.a()) {
                throw new IllegalStateException("Check failed.");
            }
        }
        deeplinkManager.f136842a = deeplink;
        if (deeplink.b == Deeplink.Type.f141898a) {
            String queryParameter = deeplink.f141896a.getQueryParameter("entry");
            if (queryParameter == null) {
                queryParameter = "";
            }
            this.G = queryParameter;
            String queryParameter2 = deeplink.f141896a.getQueryParameter("project_id");
            if (queryParameter2 == null) {
                queryParameter2 = "";
            }
            this.F = queryParameter2;
            String queryParameter3 = deeplink.f141896a.getQueryParameter("videocut_source");
            if (queryParameter3 == null) {
                queryParameter3 = "";
            }
            this.H = queryParameter3;
            String queryParameter4 = deeplink.f141896a.getQueryParameter("from_videocut_draft_id");
            this.I = queryParameter4 != null ? queryParameter4 : "";
        }
    }

    public final String Z6() {
        RecognitionC1Result recognitionC1Result = this.D;
        String str = "";
        if (recognitionC1Result != null) {
            List<TagInfo> allTag = recognitionC1Result.getAllTag(4);
            ArrayList arrayList = new ArrayList();
            for (Object obj : allTag) {
                if (((TagInfo) obj).isSatisfy()) {
                    arrayList.add(obj);
                }
            }
            for (TagInfo tagInfo : CollectionsKt___CollectionsKt.sortedWith(arrayList, new Comparator() { // from class: com.xt.edit.EditActivityViewModel$_get_pictureSceneCodeC1_$lambda$3$$inlined$sortedByDescending$1
                /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: T */
                /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: T */
                /* JADX WARN: Multi-variable type inference failed */
                @Override // java.util.Comparator
                public final int compare(T t, T t2) {
                    return ComparisonsKt__ComparisonsKt.compareValues(Float.valueOf(((TagInfo) t2).getProb()), Float.valueOf(((TagInfo) t).getProb()));
                }
            })) {
                str = (str + tagInfo.getId() + ':' + ((Format) this.A.getValue()).format(Float.valueOf(tagInfo.getProb()))) + ',';
            }
        }
        return str;
    }

    public final void Z7(IPainterCommon.FaceDetectInfo[] faceDetectInfoArr) {
        ArrayList arrayList = new ArrayList();
        if (faceDetectInfoArr != null) {
            for (IPainterCommon.FaceDetectInfo faceDetectInfo : faceDetectInfoArr) {
                Rect rect = faceDetectInfo.f144725a;
                if (rect != null) {
                    arrayList.add(new FaceInfo(rect, faceDetectInfo.i, faceDetectInfo.f144726c, faceDetectInfo.b, faceDetectInfo.m, faceDetectInfo.n));
                }
            }
        }
        XTLog.f144340a.getClass();
        XTLog.e("EditActivityViewModel", " faceInfoList : " + arrayList);
        if (faceDetectInfoArr != null && faceDetectInfoArr.length == 1) {
            j8(0);
        }
        ThreadUtils.f150169a.getClass();
        BuildersKt__Builders_commonKt.launch(CoroutineUtilsKt.f150006a, Dispatchers.getMain().getImmediate(), ThreadUtils.a() ? CoroutineStart.UNDISPATCHED : CoroutineStart.DEFAULT, new EditActivityViewModel$setFaceInfo$$inlined$runOnMainImmediately$1(null, this, arrayList));
    }

    public final String a7() {
        RecogClassificationResult recogClassificationResult = this.C;
        String str = "";
        if (recogClassificationResult != null) {
            for (TagInfo tagInfo : recogClassificationResult.getTopTag(10, 10)) {
                str = (str + tagInfo.getId() + ':' + ((Format) this.A.getValue()).format(Float.valueOf(tagInfo.getProb()))) + ',';
            }
        }
        return str;
    }

    public final void a8(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.R = str;
    }

    public final boolean b7() {
        ArrayList<String> arrayList;
        if (this.h2) {
            return true;
        }
        return (v7() || K6().b) && (arrayList = this.f0) != null && arrayList.size() > 1;
    }

    public final void b8(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.z0 = str;
    }

    public final Size c7() {
        Integer numB2 = J6().b2();
        if (numB2 != null) {
            Size sizeSi = J6().Si(numB2.intValue());
            if (sizeSi != null) {
                return sizeSi;
            }
        }
        return new Size(0, 0);
    }

    public final void c8(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.A0 = str;
    }

    public final Object d7(ContinuationImpl continuationImpl) {
        final String strY;
        EnvConfig envConfig;
        EnvConfig envConfig2;
        String strG6 = G6();
        if (this.h0.length() > 0) {
            strY = S6().d(strG6);
        } else {
            EditConfig editConfig = this.F0;
            if (editConfig == null || (envConfig = editConfig.f) == null || (strY = envConfig.b) == null || strY.length() <= 0) {
                strY = S6().y(strG6);
            } else {
                EditConfig editConfig2 = this.F0;
                if (editConfig2 != null && (envConfig2 = editConfig2.f) != null && envConfig2.e) {
                    strY = strY + '/' + strG6;
                }
            }
        }
        final String str = this.h0;
        XTLog xTLog = XTLog.f144340a;
        String str2 = "draft-flow:getSaveBoxDraftDeferred draftId:" + J6().U1() + " dstDir:" + strY + " replaceDir:" + str;
        xTLog.getClass();
        XTLog.e("EditActivityViewModel", str2);
        final Ref$IntRef ref$IntRef = new Ref$IntRef();
        final Ref$BooleanRef ref$BooleanRef = new Ref$BooleanRef();
        if (!J6().y8().isEmpty()) {
            PictureLayer pictureLayerK = T6().K();
            ref$IntRef.element = pictureLayerK != null ? pictureLayerK.b() : 0;
            ref$BooleanRef.element = true;
        } else {
            ref$IntRef.element = 0;
            ref$BooleanRef.element = false;
        }
        return m8(continuationImpl, new Function1<Function0<? extends Unit>, Deferred<? extends DraftSaveResult>>() { // from class: com.xt.edit.EditActivityViewModel$getSaveBoxDraftDeferred$handler$1

            @DebugMetadata(c = "com.xt.edit.EditActivityViewModel$getSaveBoxDraftDeferred$handler$1$1", f = "EditActivityViewModel.kt", i = {}, l = {1897}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.xt.edit.EditActivityViewModel$getSaveBoxDraftDeferred$handler$1$1, reason: invalid class name */
            /* loaded from: classes22.dex */
            public final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super DraftSaveResult>, Object> {
                public int q;
                public final /* synthetic */ EditActivityViewModel r;
                public final /* synthetic */ Ref$IntRef s;
                public final /* synthetic */ Ref$BooleanRef t;
                public final /* synthetic */ String u;
                public final /* synthetic */ String v;
                public final /* synthetic */ Function0<Unit> w;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public AnonymousClass1(EditActivityViewModel editActivityViewModel, Ref$IntRef ref$IntRef, Ref$BooleanRef ref$BooleanRef, String str, String str2, Function0<Unit> function0, Continuation<? super AnonymousClass1> continuation) {
                    super(2, continuation);
                    this.r = editActivityViewModel;
                    this.s = ref$IntRef;
                    this.t = ref$BooleanRef;
                    this.u = str;
                    this.v = str2;
                    this.w = function0;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new AnonymousClass1(this.r, this.s, this.t, this.u, this.v, this.w, continuation);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super DraftSaveResult> continuation) {
                    return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:30:0x00a8  */
                /* JADX WARN: Removed duplicated region for block: B:43:0x00db A[RETURN] */
                /* JADX WARN: Removed duplicated region for block: B:44:0x00dc  */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r28) {
                    /*
                        r27 = this;
                        r3 = r28
                        java.lang.Object r6 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        r0 = r27
                        int r1 = r0.q
                        r2 = 1
                        if (r1 == 0) goto L13
                        if (r1 != r2) goto L121
                        kotlin.ResultKt.throwOnFailure(r3)
                    L12:
                        return r3
                    L13:
                        kotlin.ResultKt.throwOnFailure(r3)
                        com.xt.retouch.util.KvSettingProvider r1 = com.xt.retouch.util.KvSettingProvider.f150073a
                        int r1 = r1.q3()
                        r3 = 2
                        if (r1 == r3) goto L27
                        com.xt.edit.EditActivityViewModel r1 = r0.r
                        boolean r1 = r1.s7()
                        if (r1 == 0) goto L11d
                    L27:
                        com.xt.retouch.draftbox.api.ProducerType r18 = com.xt.retouch.draftbox.api.ProducerType.BUSINESS_MODE
                    L29:
                        com.xt.edit.EditActivityViewModel r1 = r0.r
                        com.xt.retouch.scenes.api.IEditActivityScenesModel r1 = r1.J6()
                        java.lang.String r8 = r1.U1()
                        com.xt.retouch.painter.api.SnapshotHandler$Snapshot$Source r9 = com.xt.retouch.painter.api.SnapshotHandler.Snapshot.Source.f144685c
                        kotlin.jvm.internal.Ref$IntRef r1 = r0.s
                        int r14 = r1.element
                        kotlin.jvm.internal.Ref$BooleanRef r1 = r0.t
                        boolean r15 = r1.element
                        com.xt.edit.EditActivityViewModel r1 = r0.r
                        androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r1.j0
                        java.lang.Object r1 = r1.getValue()
                        java.lang.Boolean r1 = (java.lang.Boolean) r1
                        if (r1 != 0) goto L4e
                        r1 = 0
                        java.lang.Boolean r1 = java.lang.Boolean.valueOf(r1)
                    L4e:
                        com.xt.edit.EditActivityViewModel r4 = r0.r
                        androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.ThirdPartEditMode> r4 = r4.g1
                        java.lang.Object r5 = r4.getValue()
                        com.xt.retouch.edit.base.model.ThirdPartEditMode r4 = com.xt.retouch.edit.base.model.ThirdPartEditMode.f142054c
                        if (r5 == r4) goto L119
                        r16 = 1
                    L5c:
                        com.xt.retouch.draftbox.api.DraftExtraParams r17 = new com.xt.retouch.draftbox.api.DraftExtraParams
                        r19 = 0
                        r21 = 0
                        com.xt.edit.EditActivityViewModel r4 = r0.r
                        com.xt.retouch.edit.base.api.EditContext r4 = r4.K6()
                        boolean r4 = r4.c()
                        if (r4 == 0) goto L115
                        com.xt.edit.EditActivityViewModel r4 = r0.r
                        com.xt.retouch.edit.base.api.EditContext r4 = r4.K6()
                        boolean r4 = r4.b
                        if (r4 == 0) goto L115
                        r22 = 1
                    L7a:
                        com.xt.edit.EditActivityViewModel r4 = r0.r
                        kotlin.Lazy r4 = r4.t1
                        java.lang.Object r4 = r4.getValue()
                        com.xt.retouch.edit.base.api.host.IEditHost r4 = (com.xt.retouch.edit.base.api.host.IEditHost) r4
                        android.os.Bundle r5 = r4.P0()
                        java.lang.String r4 = "magicEliminateTaskId"
                        java.lang.String r23 = r5.getString(r4)
                        if (r23 != 0) goto L92
                        java.lang.String r23 = ""
                    L92:
                        r12 = 0
                        r26 = 0
                        r25 = 38
                        r24 = r21
                        r17.<init>(r18, r19, r21, r22, r23, r24, r25, r26)
                        com.xt.edit.EditActivityViewModel r4 = r0.r
                        com.xt.edit.api.modle.EditConfig r5 = r4.F0
                        if (r5 == 0) goto La8
                        com.xt.edit.api.modle.EditConfig$Mode r5 = r5.c()
                        if (r5 != 0) goto Lee
                    La8:
                        com.xt.edit.api.modle.EditConfig r3 = r4.F0
                        if (r3 == 0) goto Lba
                        com.xt.edit.api.modle.EnvConfig r3 = r3.f
                        if (r3 == 0) goto Lba
                        java.lang.String r3 = r3.b
                        if (r3 == 0) goto Lba
                        int r3 = r3.length()
                        if (r3 != 0) goto Le4
                    Lba:
                        r3 = r26
                    Lbc:
                        com.xt.retouch.draft.api.BoxDraftExportConfig r7 = new com.xt.retouch.draft.api.BoxDraftExportConfig
                        java.lang.String r10 = r0.u
                        java.lang.String r11 = r0.v
                        boolean r13 = r1.booleanValue()
                        r18 = r3
                        r7.<init>(r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
                        com.xt.edit.EditActivityViewModel r1 = r0.r
                        com.xt.retouch.draft.api.IBoxDraftExporter r3 = r1.f136346g
                        if (r3 == 0) goto Ldc
                    Ld1:
                        kotlin.jvm.functions.Function0<kotlin.Unit> r1 = r0.w
                        r0.q = r2
                        java.lang.Object r3 = r3.a(r7, r1, r0)
                        if (r3 != r6) goto L12
                        return r6
                    Ldc:
                        java.lang.String r1 = "boxDraftExporter"
                        kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                        r3 = r26
                        goto Ld1
                    Le4:
                        com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canAutoSavedDraft$3 r5 = com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canAutoSavedDraft$3.e
                        com.xt.retouch.draftbox.api.ImageDraftBoxManager$ExternalDraftExportConfig r3 = new com.xt.retouch.draftbox.api.ImageDraftBoxManager$ExternalDraftExportConfig
                        com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$1 r4 = com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$1.e
                        r3.<init>(r5, r4)
                        goto Lbc
                    Lee:
                        int[] r7 = com.xt.edit.EditActivityViewModel.WhenMappings.f136350a
                        int r5 = r5.ordinal()
                        r5 = r7[r5]
                        if (r5 == r2) goto L108
                        if (r5 == r3) goto Lfb
                        goto La8
                    Lfb:
                        com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canDeleteDraft$2 r5 = new com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canDeleteDraft$2
                        r5.<init>(r4)
                        com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canAutoSavedDraft$2 r4 = com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canAutoSavedDraft$2.e
                        com.xt.retouch.draftbox.api.ImageDraftBoxManager$ExternalDraftExportConfig r3 = new com.xt.retouch.draftbox.api.ImageDraftBoxManager$ExternalDraftExportConfig
                        r3.<init>(r4, r5)
                        goto Lbc
                    L108:
                        com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canDeleteDraft$1 r5 = new com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canDeleteDraft$1
                        r5.<init>(r4)
                        com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canAutoSavedDraft$1 r4 = com.xt.edit.EditActivityViewModel$getExternalDraftExportConfig$canAutoSavedDraft$1.e
                        com.xt.retouch.draftbox.api.ImageDraftBoxManager$ExternalDraftExportConfig r3 = new com.xt.retouch.draftbox.api.ImageDraftBoxManager$ExternalDraftExportConfig
                        r3.<init>(r4, r5)
                        goto Lbc
                    L115:
                        r22 = 0
                        goto L7a
                    L119:
                        r16 = 0
                        goto L5c
                    L11d:
                        com.xt.retouch.draftbox.api.ProducerType r18 = com.xt.retouch.draftbox.api.ProducerType.CLIENT_MODE
                        goto L29
                    L121:
                        java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r1.<init>(r0)
                        throw r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel$getSaveBoxDraftDeferred$handler$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Deferred<? extends DraftSaveResult> invoke(Function0<? extends Unit> function0) {
                Function0<? extends Unit> function02 = function0;
                Intrinsics.checkNotNullParameter(function02, "");
                return BuildersKt__Builders_commonKt.async$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(this.e, ref$IntRef, ref$BooleanRef, strY, str, function02, null), 3, null);
            }
        });
    }

    public final void d8(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        Intrinsics.checkNotNullParameter(str7, "");
        Intrinsics.checkNotNullParameter(str8, "");
        Intrinsics.checkNotNullParameter(str9, "");
        Intrinsics.checkNotNullParameter(str10, "");
        Intrinsics.checkNotNullParameter(str11, "");
        Intrinsics.checkNotNullParameter(str12, "");
        Intrinsics.checkNotNullParameter(str13, "");
        Intrinsics.checkNotNullParameter(str14, "");
        Intrinsics.checkNotNullParameter(str15, "");
        Intrinsics.checkNotNullParameter(str16, "");
        Intrinsics.checkNotNullParameter(str17, "");
        Intrinsics.checkNotNullParameter(str18, "");
        Intrinsics.checkNotNullParameter(str19, "");
        Intrinsics.checkNotNullParameter(str20, "");
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str2)) {
            map.put("lynx_from_page", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("lynx_form_channel", str3);
        }
        map.put("lynx_template_position", str4);
        map.put("lynx_template_share_from", str5);
        map.put("lynx_template_search_keyword", str6);
        map.put("lynx_template_search_keyword_source", str7);
        map.put("lynx_template_push_rule_id", str8);
        map.put("lynx_template_push_group_id", str9);
        map.put("lynx_template_search_id", str10);
        map.put("lynx_template_search_request_id", str11);
        map.put("lynx_template_search_result_id", str12);
        map.put("lynx_template_search_server_channel", str13);
        map.put("lynx_template_topic_id", str14);
        map.put("lynx_template_topic_name", str15);
        map.put("lynx_template_topic_tab", str16);
        map.put("is_need_face_detect", str20);
        map.put("project", str18);
        map.put("project_id", str19);
        this.y0 = new LynxEvent(str, map);
        this.e1 = str17;
        this.x0 = str;
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("template_id")) {
                String strOptString = jSONObject.optString("template_id");
                if (!TextUtils.isEmpty(this.u0)) {
                    this.v0 = true;
                }
                Intrinsics.checkNotNull(strOptString);
                this.u0 = strOptString;
                XTLog.f144340a.getClass();
                XTLog.e("EditActivityViewModel", "lynxTemplateId = " + strOptString);
            }
            Result.m17090constructorimpl(Unit.INSTANCE);
        } catch (Throwable th) {
            Result.m17090constructorimpl(ResultKt.createFailure(th));
        }
    }

    public final MutableLiveData<Boolean> e7() {
        return this.K1;
    }

    public final void e8(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.m2 = str;
    }

    public final SubscribeApi f7() {
        SubscribeApi subscribeApi = this.X0;
        if (subscribeApi != null) {
            return subscribeApi;
        }
        Intrinsics.throwUninitializedPropertyAccessException("subscribeApi");
        return null;
    }

    public final void f8(boolean z, boolean z2) {
        XTLog.f144340a.getClass();
        if (!Intrinsics.areEqual(Boolean.valueOf(z), this.K1.getValue())) {
            this.r1 = null;
        }
        if (z2) {
            this.K1.postValue(Boolean.valueOf(z));
        } else {
            ThreadUtils.f150169a.getClass();
            BuildersKt__Builders_commonKt.launch(CoroutineUtilsKt.f150006a, Dispatchers.getMain().getImmediate(), ThreadUtils.a() ? CoroutineStart.UNDISPATCHED : CoroutineStart.DEFAULT, new EditActivityViewModel$showFace$$inlined$runOnMainImmediately$1(null, this, z));
        }
    }

    public final String g7() {
        String str;
        Unit unit;
        ExtraTemplateMessage extraTemplateMessage;
        AppliedTemplateInfo value = this.n1.getValue();
        if (value == null || (extraTemplateMessage = value.b) == null || (str = extraTemplateMessage.G) == null) {
            str = "";
        }
        try {
            String str2 = this.x0;
            if (str2 != null) {
                JSONObject jSONObject = new JSONObject(str2);
                if (jSONObject.has("request_id")) {
                    String strOptString = jSONObject.optString("request_id");
                    Intrinsics.checkNotNullExpressionValue(strOptString, "");
                    str = strOptString;
                }
                unit = Unit.INSTANCE;
            } else {
                unit = null;
            }
            Result.m17090constructorimpl(unit);
        } catch (Throwable th) {
            Result.m17090constructorimpl(ResultKt.createFailure(th));
        }
        return str;
    }

    public final void g8(final Function1<? super String, Unit> function1) {
        if (this.n2) {
            return;
        }
        ScreenShotListener.Companion companion = ScreenShotListener.i;
        Context context = w6().getContext();
        companion.getClass();
        ScreenShotListener screenShotListenerA = ScreenShotListener.Companion.a(context);
        ScreenShotListener.OnScreenShotListener onScreenShotListener = new ScreenShotListener.OnScreenShotListener() { // from class: com.xt.edit.EditActivityViewModel$startScreenShotListen$1
            @Override // com.xt.retouch.util.screenshot.ScreenShotListener.OnScreenShotListener
            public final void a(String str) {
                Intrinsics.checkNotNullParameter(str, "");
                Function1<String, Unit> function12 = function1;
                if (function12 != null) {
                    function12.invoke(str);
                }
            }
        };
        screenShotListenerA.getClass();
        screenShotListenerA.f150217d = onScreenShotListener;
        screenShotListenerA.f();
        this.n2 = true;
    }

    public final void h8(LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImpl) {
        Intrinsics.checkNotNullParameter(lifecycleCoroutineScopeImpl, "");
        FlavorConfig.f139375a.getClass();
        if (FlavorConfig.g()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(lifecycleCoroutineScopeImpl, Dispatchers.getMain().plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)), null, new EditActivityViewModel$subscribeShowFaceFlag$1(this, null), 2, null);
    }

    public final void i7() {
        Intrinsics.areEqual(this.j0.getValue(), Boolean.TRUE);
    }

    public final void i8() {
        List<Layer> listI1 = T6().I1();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listI1, 10));
        Iterator<T> it = listI1.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(((Layer) it.next()).b()));
        }
        IBaseScenesModel.DefaultImpls.c(J6(), arrayList, null, 6);
    }

    public final MutableLiveData<String> j7() {
        return (MutableLiveData) this.d2.getValue();
    }

    public final void j8(int i) {
        ThreadUtils.f150169a.getClass();
        BuildersKt__Builders_commonKt.launch(CoroutineUtilsKt.f150006a, Dispatchers.getMain().getImmediate(), ThreadUtils.a() ? CoroutineStart.UNDISPATCHED : CoroutineStart.DEFAULT, new EditActivityViewModel$updateFaceIndex$$inlined$runOnMainImmediately$1(i, this, null));
    }

    public final void k6() {
        J6().z0();
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x025a A[LOOP:3: B:105:0x0254->B:107:0x025a, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x012a  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0144  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0165  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0177  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object k7(com.xt.retouch.painter.function.api.IPainterCommon.BitmapInfo r30, androidx.lifecycle.Observer<java.lang.Integer> r31, androidx.lifecycle.Observer<java.lang.Integer> r32, com.xt.retouch.scenes.api.MiddlePageRecorder r33, java.lang.String r34, kotlin.coroutines.Continuation<? super com.xt.edit.api.IBatchEditExporter.Result> r35) {
        /*
            r29 = this;
            r5 = r35
            r12 = r31
            r10 = r32
            r9 = r33
            r4 = r34
            boolean r0 = r5 instanceof com.xt.edit.EditActivityViewModel$handleBatchModeExport$1
            r1 = r29
            if (r0 == 0) goto Lf5
            r6 = r5
            com.xt.edit.EditActivityViewModel$handleBatchModeExport$1 r6 = (com.xt.edit.EditActivityViewModel$handleBatchModeExport$1) r6
            int r3 = r6.y
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r3 & r2
            if (r0 == 0) goto Lf5
            int r3 = r3 - r2
            r6.y = r3
        L1e:
            java.lang.Object r0 = r6.w
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r6.y
            r2 = 1
            r11 = 0
            if (r3 == 0) goto L7c
            if (r3 != r2) goto L28c
            long r7 = r6.v
            X.1W6 r3 = r6.u
            java.lang.Object r4 = r6.t
            java.lang.String r4 = (java.lang.String) r4
            com.xt.retouch.scenes.api.MiddlePageRecorder r9 = r6.s
            androidx.lifecycle.Observer r10 = r6.r
            androidx.lifecycle.Observer r12 = r6.q
            kotlin.ResultKt.throwOnFailure(r0)
        L3d:
            com.xt.edit.api.IBatchEditExporter$Result r0 = (com.xt.edit.api.IBatchEditExporter.Result) r0
            if (r0 == 0) goto Lf2
            boolean r6 = r0.f136492a
        L43:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r5 = r1.x1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r6)
            r5.postValue(r2)
            r1.w1 = r6
            if (r0 == 0) goto L54
            java.util.List<com.xt.retouch.draft.api.DraftSaveResult> r5 = r0.f136493c
            if (r5 != 0) goto L58
        L54:
            java.util.List r5 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L58:
            if (r0 == 0) goto L107
            java.util.List<X.1W6> r11 = r0.b
            if (r11 == 0) goto L107
            java.util.ArrayList r13 = new java.util.ArrayList
            r13.<init>()
            java.util.Iterator r14 = r5.iterator()
        L67:
            boolean r2 = r14.hasNext()
            if (r2 == 0) goto Lfc
            java.lang.Object r6 = r14.next()
            r2 = r6
            com.xt.retouch.draft.api.DraftSaveResult r2 = (com.xt.retouch.draft.api.DraftSaveResult) r2
            int r2 = r2.f141642a
            if (r2 != 0) goto L67
            r13.add(r6)
            goto L67
        L7c:
            kotlin.ResultKt.throwOnFailure(r0)
            long r7 = android.os.SystemClock.elapsedRealtime()
            if (r30 == 0) goto Lef
            android.graphics.Bitmap r14 = r30.getBitmap()
        L89:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r1.J6()
            com.xt.retouch.painter.trace.EffectFlow r13 = com.xt.retouch.painter.function.api.IPainterCommon.DefaultImpls.d(r0)
            java.lang.String r0 = r1.z6()
            r3 = 2
            X.1Vs r19 = X.C51841Vt.a(r13, r0, r3)
            if (r14 == 0) goto L9f
            r14.recycle()
        L9f:
            kotlinx.coroutines.GlobalScope r13 = kotlinx.coroutines.GlobalScope.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r14 = kotlinx.coroutines.Dispatchers.getMain()
            com.xt.edit.EditActivityViewModel$handleBatchModeExport$2 r0 = new com.xt.edit.EditActivityViewModel$handleBatchModeExport$2
            r0.<init>(r1, r12, r10, r11)
            r15 = r11
            r16 = r0
            r17 = r3
            r18 = r11
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r13, r14, r15, r16, r17, r18)
            X.1W6 r3 = new X.1W6
            r0 = 2047(0x7ff, float:2.868E-42)
            r3.<init>(r11, r0)
            androidx.lifecycle.MutableLiveData r11 = new androidx.lifecycle.MutableLiveData
            r11.<init>()
            r1.n = r11
            com.xt.edit.api.IBatchEditExporter r0 = r1.o
            if (r0 == 0) goto Lf1
            com.xt.retouch.scenes.api.IEditActivityScenesModel r13 = r1.J6()
            java.lang.String r18 = r13.U1()
            com.xt.retouch.util.KvSettingProvider r13 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r21 = r13.o()
            r6.q = r12
            r6.r = r10
            r6.s = r9
            r6.t = r4
            r6.u = r3
            r6.v = r7
            r6.y = r2
            r17 = r0
            r20 = r11
            r22 = r6
            java.lang.Object r0 = r17.a(r18, r19, r20, r21, r22)
            if (r0 != r5) goto L3d
            return r5
        Lef:
            r14 = r11
            goto L89
        Lf1:
            r0 = 0
        Lf2:
            r6 = 0
            goto L43
        Lf5:
            com.xt.edit.EditActivityViewModel$handleBatchModeExport$1 r6 = new com.xt.edit.EditActivityViewModel$handleBatchModeExport$1
            r6.<init>(r1, r5)
            goto L1e
        Lfc:
            int r6 = r13.size()
            int r2 = r11.size()
            if (r6 != r2) goto L107
            goto L10f
        L107:
            com.xt.retouch.util.KvSettingProvider r2 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r2 = r2.o()
            if (r2 != 0) goto L177
        L10f:
            r27 = 1
        L111:
            com.xt.retouch.draft.api.DraftSaveResult r15 = new com.xt.retouch.draft.api.DraftSaveResult
            boolean r2 = p6()
            if (r2 == 0) goto L167
        L119:
            r16 = 1
        L11b:
            r17 = 0
            r19 = 0
            r20 = 14
            r15 = r15
            r2 = 0
            r21 = r2
            r15.<init>(r16, r17, r19, r20, r21)
            if (r0 == 0) goto L165
            java.util.List<X.1W6> r6 = r0.b
        L12c:
            r1.v1 = r6
            java.util.List<java.lang.Long> r6 = r1.y1
            java.util.ArrayList r6 = (java.util.ArrayList) r6
            r6.clear()
            java.util.List<java.lang.Long> r11 = r1.y1
            if (r0 == 0) goto L160
            java.util.List<java.lang.Long> r6 = r0.f136494d
            if (r6 == 0) goto L160
        L13d:
            java.util.ArrayList r11 = (java.util.ArrayList) r11
            r11.addAll(r6)
            if (r0 == 0) goto L24d
            java.util.List<model.SaveResult$ExportPicStatus> r6 = r0.e
            if (r6 == 0) goto L17a
            java.util.Iterator r13 = r6.iterator()
        L14c:
            boolean r6 = r13.hasNext()
            if (r6 == 0) goto L17a
            java.lang.Object r6 = r13.next()
            model.SaveResult$ExportPicStatus r6 = (model.SaveResult.ExportPicStatus) r6
            int r11 = r6.f150875a
            java.lang.String r6 = r6.b
            r1.L7(r11, r6)
            goto L14c
        L160:
            java.util.List r6 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            goto L13d
        L165:
            r6 = 0
            goto L12c
        L167:
            com.xt.retouch.util.KvSettingProvider r2 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r2 = r2.o()
            if (r2 != 0) goto L172
            r16 = 3
            goto L11b
        L172:
            if (r27 == 0) goto L119
            r16 = 0
            goto L11b
        L177:
            r27 = 0
            goto L111
        L17a:
            java.util.List<X.1W6> r6 = r0.b
            if (r6 == 0) goto L24d
            java.util.Iterator r18 = r6.iterator()
        L182:
            boolean r6 = r18.hasNext()
            if (r6 == 0) goto L24d
            java.lang.Object r6 = r18.next()
            int r17 = r2 + 1
            if (r2 >= 0) goto L193
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L193:
            X.1W6 r6 = (X.C1W6) r6
            java.lang.String r11 = r6.b
            int r11 = r11.length()
            if (r11 <= 0) goto L24a
            r13 = 1
        L19e:
            java.lang.String r11 = ""
            if (r13 == 0) goto L1ac
            java.lang.String r13 = r6.b
            r3.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r11)
            r3.b = r13
        L1ac:
            java.lang.String r14 = r6.b
            com.xt.retouch.util.mediastore.XtMediaStore r13 = com.xt.retouch.util.mediastore.XtMediaStore.f150196a
            com.xt.retouch.util.mediastore.XtMediaStore.b(r13, r14)
            if (r9 == 0) goto L213
            boolean r23 = r9.isMoveSticker()
            java.util.List<com.xt.edit.api.Picture> r13 = r1.p
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r13 = r13.get(r2)
            com.xt.edit.api.Picture r13 = (com.xt.edit.api.Picture) r13
            java.lang.String r14 = r13.f136507a
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r16 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            java.lang.String r13 = java.lang.String.valueOf(r2)
            r16.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r11)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.HashSet<java.lang.String>> r11 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.j
            java.lang.Object r11 = r11.get(r13)
            if (r11 != 0) goto L1dc
            kotlin.collections.SetsKt__SetsKt.emptySet()
        L1dc:
            r25 = 0
            r21 = 1
            r26 = 160(0xa0, float:2.24E-43)
            r24 = r14
            r19 = r1
            r20 = r6
            r22 = r4
            K7(r19, r20, r21, r22, r23, r24, r25, r26)
        L1ed:
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r6 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            java.lang.String r11 = java.lang.String.valueOf(r2)
            r6.getClass()
            if (r11 != 0) goto L1fa
            java.lang.String r11 = "CACHE_KEY_DEFAULT"
        L1fa:
            com.xt.retouch.lib.log.XTLog r13 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.String r2 = "[clearCache] key:"
            java.lang.String r6 = r2.concat(r11)
            r13.getClass()
            java.lang.String r2 = "TextRecognitionHelper"
            com.xt.retouch.lib.log.XTLog.e(r2, r6)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.HashSet<java.lang.String>> r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.j
            r2.remove(r11)
            r2 = r17
            goto L182
        L213:
            r21 = 0
            java.util.List<com.xt.edit.api.Picture> r13 = r1.p
            java.util.ArrayList r13 = (java.util.ArrayList) r13
            java.lang.Object r13 = r13.get(r2)
            com.xt.edit.api.Picture r13 = (com.xt.edit.api.Picture) r13
            java.lang.String r14 = r13.f136507a
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r16 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            java.lang.String r13 = java.lang.String.valueOf(r2)
            r16.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r11)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.HashSet<java.lang.String>> r11 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.j
            java.lang.Object r11 = r11.get(r13)
            if (r11 != 0) goto L238
            kotlin.collections.SetsKt__SetsKt.emptySet()
        L238:
            r25 = 0
            r26 = 138(0x8a, float:1.93E-43)
            r23 = r21
            r24 = r14
            r19 = r1
            r20 = r6
            r22 = r4
            K7(r19, r20, r21, r22, r23, r24, r25, r26)
            goto L1ed
        L24a:
            r13 = 0
            goto L19e
        L24d:
            r2 = 0
            r1.E0 = r2
            java.util.Iterator r5 = r5.iterator()
        L254:
            boolean r2 = r5.hasNext()
            if (r2 == 0) goto L265
            java.lang.Object r4 = r5.next()
            com.xt.retouch.draft.api.DraftSaveResult r4 = (com.xt.retouch.draft.api.DraftSaveResult) r4
            r2 = 1
            r1.l7(r2, r4)
            goto L254
        L265:
            kotlinx.coroutines.GlobalScope r16 = kotlinx.coroutines.GlobalScope.INSTANCE
            kotlinx.coroutines.MainCoroutineDispatcher r17 = kotlinx.coroutines.Dispatchers.getMain()
            r28 = 0
            com.xt.edit.EditActivityViewModel$handleBatchModeExport$6 r23 = new com.xt.edit.EditActivityViewModel$handleBatchModeExport$6
            r24 = r1
            r25 = r12
            r26 = r10
            r23.<init>(r24, r25, r26, r27, r28)
            r20 = 2
            r18 = r28
            r21 = r28
            r19 = r23
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r16, r17, r18, r19, r20, r21)
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r4 - r7
            r1.n7(r4, r3, r15)
            return r0
        L28c:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.k7(com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo, androidx.lifecycle.Observer, androidx.lifecycle.Observer, com.xt.retouch.scenes.api.MiddlePageRecorder, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void k8(Pair<Boolean, Boolean> pair) {
        Intrinsics.checkNotNullParameter(pair, "");
        J6().i(pair);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x002f  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0054  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x003c -> B:11:0x0025). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object l6(kotlin.coroutines.Continuation<? super java.lang.Integer> r8) {
        /*
            r7 = this;
            boolean r0 = r8 instanceof com.xt.edit.EditActivityViewModel$awaitSkeletonSize$1
            if (r0 == 0) goto L44
            r6 = r8
            com.xt.edit.EditActivityViewModel$awaitSkeletonSize$1 r6 = (com.xt.edit.EditActivityViewModel$awaitSkeletonSize$1) r6
            int r2 = r6.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L44
            int r2 = r2 - r1
            r6.t = r2
        L12:
            java.lang.Object r1 = r6.r
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.t
            r4 = 0
            r3 = 1
            if (r0 == 0) goto L3f
            if (r0 != r3) goto L5d
            int r2 = r6.q
            kotlin.ResultKt.throwOnFailure(r1)
        L25:
            int r2 = r2 + 1
        L27:
            androidx.lifecycle.MutableLiveData<java.util.List<java.util.List<java.lang.Integer>>> r0 = r7.F1
            java.lang.Object r0 = r0.getValue()
            if (r0 != 0) goto L4a
            r0 = 5
            if (r2 >= r0) goto L4a
            r6.q = r2
            r6.t = r3
            r0 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r0 = kotlinx.coroutines.DelayKt.delay(r0, r6)
            if (r0 != r5) goto L25
            return r5
        L3f:
            kotlin.ResultKt.throwOnFailure(r1)
            r2 = 0
            goto L27
        L44:
            com.xt.edit.EditActivityViewModel$awaitSkeletonSize$1 r6 = new com.xt.edit.EditActivityViewModel$awaitSkeletonSize$1
            r6.<init>(r7, r8)
            goto L12
        L4a:
            androidx.lifecycle.MutableLiveData<java.util.List<java.util.List<java.lang.Integer>>> r0 = r7.F1
            java.lang.Object r0 = r0.getValue()
            java.util.List r0 = (java.util.List) r0
            if (r0 == 0) goto L58
            int r4 = r0.size()
        L58:
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r4)
            return r0
        L5d:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.l6(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0050  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void l7(boolean r11, com.xt.retouch.draft.api.DraftSaveResult r12) {
        /*
            r10 = this;
            java.lang.String r3 = ""
            if (r11 != 0) goto Lf
            int r0 = r12.f141642a
            if (r0 != 0) goto Lf
            java.lang.String r0 = r12.f141643c
            if (r0 != 0) goto Ld
            r0 = r3
        Ld:
            r10.h0 = r0
        Lf:
            int r0 = r12.f141642a
            if (r0 != 0) goto L33
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r4 = r10.e2
            long r0 = r0 - r4
            com.xt.retouch.report.api.IAppEventReport r5 = r10.x6()
            androidx.lifecycle.MutableLiveData r2 = r10.j7()
            java.lang.Object r4 = r2.getValue()
            java.lang.String r4 = (java.lang.String) r4
            if (r4 != 0) goto L2b
            r4 = r3
        L2b:
            java.lang.String r2 = r12.f141643c
            if (r2 != 0) goto L30
            r2 = r3
        L30:
            r5.G0(r0, r4, r2)
        L33:
            if (r11 == 0) goto La0
            java.lang.String r2 = "atlas"
        L37:
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r10.N1
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            r9 = 0
            if (r0 == 0) goto L50
            int r1 = r0.intValue()
            com.xt.edit.EditActivityViewModel$IQueryFragmentIdMsgCallback r0 = r10.v
            if (r0 == 0) goto L50
            java.lang.String r5 = r0.a(r1)
            if (r5 != 0) goto L51
        L50:
            r5 = r3
        L51:
            int r4 = r12.f141642a
            r0 = 1
            java.lang.String r1 = "failure"
            if (r4 == r0) goto L94
            r0 = 2
            if (r4 == r0) goto L91
            java.lang.String r1 = "success"
        L5d:
            int r4 = r12.f141642a
            r0 = 3
            if (r4 == r0) goto L8e
            com.xt.edit.api.DraftSaveReportParams r4 = new com.xt.edit.api.DraftSaveReportParams
            long r6 = r12.b
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.AppliedTemplateInfo> r0 = r10.n1
            java.lang.Object r0 = r0.getValue()
            com.xt.retouch.edit.base.model.AppliedTemplateInfo r0 = (com.xt.retouch.edit.base.model.AppliedTemplateInfo) r0
            if (r0 == 0) goto L8f
            template.ITemplate r0 = r0.f141985a
            if (r0 == 0) goto L8f
            java.lang.String r8 = r0.c()
        L78:
            androidx.lifecycle.MutableLiveData<com.xt.retouch.edit.base.model.AppliedTemplateInfo> r0 = r10.n1
            java.lang.Object r0 = r0.getValue()
            com.xt.retouch.edit.base.model.AppliedTemplateInfo r0 = (com.xt.retouch.edit.base.model.AppliedTemplateInfo) r0
            if (r0 == 0) goto L88
            com.example.template.data.ExtraTemplateMessage r0 = r0.b
            if (r0 == 0) goto L88
            java.lang.String r9 = r0.p
        L88:
            r4.<init>(r5, r6, r8, r9)
            r10.z7(r2, r1, r3, r4)
        L8e:
            return
        L8f:
            r8 = r9
            goto L78
        L91:
            java.lang.String r3 = "space"
            goto L5d
        L94:
            int r0 = r12.f141644d
            if (r0 == 0) goto L9d
            java.lang.String r3 = java.lang.String.valueOf(r0)
            goto L5d
        L9d:
            java.lang.String r3 = "other"
            goto L5d
        La0:
            java.lang.String r2 = "draft"
            goto L37
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.l7(boolean, com.xt.retouch.draft.api.DraftSaveResult):void");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v21, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r0v8, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    public final void l8() {
        LayerNumConfig layerNumConfigA;
        LayerNumConfig.DeviceLevel deviceLevel = V6().c() ? LayerNumConfig.DeviceLevel.f141431a : V6().d() ? LayerNumConfig.DeviceLevel.b : LayerNumConfig.DeviceLevel.f141432c;
        if (K6().b()) {
            LayerNumConfig.Companion companion = LayerNumConfig.f141428a;
            JsonConfig jsonConfig = (JsonConfig) A6().getBatchLayerNumConfig().getValue();
            companion.getClass();
            layerNumConfigA = LayerNumConfig.Companion.a(jsonConfig, deviceLevel);
        } else {
            LayerNumConfig.Companion companion2 = LayerNumConfig.f141428a;
            JsonConfig jsonConfig2 = (JsonConfig) A6().getLayerNumConfig().getValue();
            companion2.getClass();
            layerNumConfigA = LayerNumConfig.Companion.a(jsonConfig2, deviceLevel);
        }
        this.b2 = layerNumConfigA;
        XTLog xTLog = XTLog.f144340a;
        String str = "layerNumConfig: " + this.b2;
        xTLog.getClass();
        XTLog.e("EditActivityViewModel", str);
        J6().j().ee(K6().f141806a == EditMode.f ? new IPainterLayer.LayerLimitConfig(layerNumConfigA.d(), layerNumConfigA.a(), layerNumConfigA.b(), layerNumConfigA.c(), layerNumConfigA.m()) : K6().f141806a == EditMode.f141869g ? new IPainterLayer.LayerLimitConfig(layerNumConfigA.l(), layerNumConfigA.i(), layerNumConfigA.j(), layerNumConfigA.k(), layerNumConfigA.m()) : new IPainterLayer.LayerLimitConfig(layerNumConfigA.n(), 4, 0, 0, layerNumConfigA.m()));
    }

    public final float m6() {
        Float value = this.N.getValue();
        if (value == null) {
            return 0.0f;
        }
        return value.floatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x018a A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x01da  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x028e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0347 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x034a  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x037c  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0391  */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4, types: [com.xt.retouch.painter.trace.EffectFlow, com.xt.retouch.scenes.api.MiddlePageRecorder, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v5 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m7(com.xt.retouch.painter.function.api.IPainterCommon.BitmapInfo r29, android.content.Context r30, com.xt.retouch.scenes.api.MiddlePageRecorder r31, java.lang.String r32, boolean r33, kotlin.coroutines.Continuation<? super X.C1W6> r34) {
        /*
            r28 = this;
            r3 = r34
            r17 = r30
            r20 = r31
            r18 = r32
            r12 = r29
            boolean r0 = r3 instanceof com.xt.edit.EditActivityViewModel$handleNormalExport$1
            r8 = r28
            if (r0 == 0) goto L391
            r7 = r3
            com.xt.edit.EditActivityViewModel$handleNormalExport$1 r7 = (com.xt.edit.EditActivityViewModel$handleNormalExport$1) r7
            int r2 = r7.z
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L391
            int r2 = r2 - r1
            r7.z = r2
        L1e:
            java.lang.Object r10 = r7.x
            java.lang.Object r19 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r7.z
            r5 = 2
            r4 = 1
            r6 = 0
            java.lang.String r11 = "PictureExporter"
            if (r0 == 0) goto L77
            if (r0 == r4) goto L209
            if (r0 != r5) goto L398
            long r2 = r7.w
            long r0 = r7.v
            java.lang.Object r9 = r7.r
            X.1W6 r9 = (X.C1W6) r9
            com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo r12 = r7.q
            kotlin.ResultKt.throwOnFailure(r10)
        L3e:
            com.xt.retouch.draft.api.DraftSaveResult r10 = (com.xt.retouch.draft.api.DraftSaveResult) r10
            if (r12 == 0) goto L4b
            android.graphics.Bitmap r4 = r12.getBitmap()
            if (r4 == 0) goto L4b
            r4.recycle()
        L4b:
            com.xt.retouch.lib.log.XTLog r7 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r4 = "draft-flow:save atlas time: "
            r6.<init>(r4)
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r4 = r4 - r2
            r6.append(r4)
            java.lang.String r2 = ", status:"
            r6.append(r2)
            r6.append(r10)
            java.lang.String r2 = r6.toString()
            r7.getClass()
            com.xt.retouch.lib.log.XTLog.e(r11, r2)
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            r8.n7(r2, r9, r10)
            return r9
        L77:
            kotlin.ResultKt.throwOnFailure(r10)
            com.xt.retouch.lib.log.XTLog r10 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "draft-flow:handleNormalExport draft:"
            r9.<init>(r0)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r8.J6()
            java.lang.String r0 = r0.U1()
            r9.append(r0)
            java.lang.String r0 = " to atlas, start save time: "
            r9.append(r0)
            long r0 = android.os.SystemClock.elapsedRealtime()
            long r2 = r8.t2
            long r0 = r0 - r2
            r9.append(r0)
            java.lang.String r0 = r9.toString()
            r10.getClass()
            com.xt.retouch.lib.log.XTLog.e(r11, r0)
            r8.K = r6
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r8.J6()
            com.xt.retouch.painter.trace.EffectFlow r13 = com.xt.retouch.painter.function.api.IPainterCommon.DefaultImpls.d(r0)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r8.J6()
            r0.K7(r13)
            java.lang.String r0 = r8.z6()
            X.1Vs r2 = X.C51841Vt.a(r13, r0, r5)
            com.xt.retouch.edit.base.api.EditContext r0 = r8.K6()
            java.lang.String r1 = r0.i
            com.xt.retouch.edit.base.api.EditContext r0 = r8.K6()
            java.lang.String r0 = r0.h
            X.1Vs r6 = X.C51831Vs.a(r2, r1, r0)
            long r0 = java.lang.System.currentTimeMillis()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r2 = "alpha cost: "
            r5.<init>(r2)
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            com.xt.retouch.lib.log.XTLog.e(r11, r2)
            r7.q = r12
            r2 = r17
            r7.r = r2
            r2 = r20
            r7.s = r2
            r2 = r18
            r7.t = r2
            r7.u = r13
            r7.getClass()
            r7.v = r0
            r7.z = r4
            com.xt.retouch.config.api.IConfigManager r2 = r8.A6()
            boolean r2 = com.xt.retouch.config.api.ConfigExtKt.a(r2)
            java.lang.String r9 = "EditActivityViewModel"
            java.lang.String r10 = "draft-flow:handleNormalExport "
            if (r2 == 0) goto L18d
            com.retouch.layermanager.api.layer.ILayerManager r2 = r8.T6()
            com.retouch.layermanager.api.layer.GroupLayer r2 = r2.o2()
            if (r2 == 0) goto L18b
            int r3 = r2.b()
        L11d:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r8.J6()
            android.util.Size r3 = r2.Si(r3)
            if (r3 != 0) goto L12e
            android.util.Size r3 = new android.util.Size
            r2 = 100
            r3.<init>(r2, r2)
        L12e:
            int r15 = r3.getWidth()
            int r14 = r3.getHeight()
            int r2 = r15 * r14
            long r4 = (long) r2
            r2 = 4
            long r4 = r4 * r2
            com.xt.retouch.util.StorageUtils r2 = com.xt.retouch.util.StorageUtils.f150158a
            long r2 = com.xt.retouch.util.StorageUtils.c(r2)
            int r16 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r16 >= 0) goto L18d
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r10)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r10 = r8.J6()
            java.lang.String r10 = r10.U1()
            r6.append(r10)
            java.lang.String r10 = " no enough space, maxByte = "
            r6.append(r10)
            r6.append(r4)
            java.lang.String r4 = ", width = "
            r6.append(r4)
            r6.append(r15)
            java.lang.String r4 = ", height = "
            r6.append(r4)
            r6.append(r14)
            java.lang.String r4 = ", availableByte = "
            r6.append(r4)
            r6.append(r2)
            java.lang.String r2 = r6.toString()
            com.xt.retouch.lib.log.XTLog.b(r9, r2)
            X.1W6 r10 = new X.1W6
            model.SaveResult$ExportPicStatus r3 = model.SaveResult.ExportPicStatus.f150873d
            r2 = 1023(0x3ff, float:1.434E-42)
            r10.<init>(r3, r2)
        L186:
            r2 = r19
            if (r10 != r2) goto L22a
            return r19
        L18b:
            r3 = 0
            goto L11d
        L18d:
            com.xt.retouch.config.api.IConfigManager r2 = r8.A6()
            boolean r2 = com.xt.retouch.config.api.ConfigExtKt.a(r2)
            if (r2 == 0) goto L1da
            int r3 = android.os.Build.VERSION.SDK_INT
            r2 = 24
            if (r2 > r3) goto L1da
            r2 = 30
            if (r3 >= r2) goto L1da
            com.xt.retouch.util.PermissionUtil r3 = com.xt.retouch.util.PermissionUtil.f150127a
            com.xt.retouch.applauncher.api.AppContext r2 = r8.w6()
            android.content.Context r2 = r2.getContext()
            r3.getClass()
            boolean r2 = com.xt.retouch.util.PermissionUtil.e(r2)
            if (r2 != 0) goto L1da
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r10)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r8.J6()
            java.lang.String r2 = r2.U1()
            r3.append(r2)
            java.lang.String r2 = " no permission"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            com.xt.retouch.lib.log.XTLog.b(r9, r2)
            X.1W6 r10 = new X.1W6
            model.SaveResult$ExportPicStatus r3 = model.SaveResult.ExportPicStatus.k
            r2 = 1023(0x3ff, float:1.434E-42)
            r10.<init>(r3, r2)
            goto L186
        L1da:
            com.xt.retouch.util.KvSettingProvider r2 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r2 = r2.e2()
            if (r2 == 0) goto L1ec
            kotlin.random.Random$Default r2 = kotlin.random.Random.Default
            boolean r2 = r2.nextBoolean()
            if (r2 == 0) goto L1ec
            r10 = 0
            goto L186
        L1ec:
            com.xt.retouch.edit.base.model.PictureExporter r4 = r8.f136345d
            if (r4 == 0) goto L202
        L1f0:
            com.xt.edit.EditActivityViewModel$exportImage$2 r3 = new com.xt.edit.EditActivityViewModel$exportImage$2
            r3.<init>()
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r8.J6()
            java.lang.String r2 = r2.U1()
            java.lang.Object r10 = r4.a(r6, r3, r2, r7)
            goto L186
        L202:
            java.lang.String r2 = "pictureExporter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r4 = 0
            goto L1f0
        L209:
            long r0 = r7.v
            com.xt.retouch.painter.trace.EffectFlow r13 = r7.u
            java.lang.Object r2 = r7.t
            r18 = r2
            r2 = r18
            java.lang.String r2 = (java.lang.String) r2
            r18 = r2
            com.xt.retouch.scenes.api.MiddlePageRecorder r2 = r7.s
            r20 = r2
            java.lang.Object r2 = r7.r
            r17 = r2
            r2 = r17
            android.content.Context r2 = (android.content.Context) r2
            r17 = r2
            com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo r12 = r7.q
            kotlin.ResultKt.throwOnFailure(r10)
        L22a:
            X.1W6 r10 = (X.C1W6) r10
            com.xt.retouch.util.KvSettingProvider r2 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r2 = r2.C1()
            if (r2 == 0) goto L382
        L234:
            r4 = 0
        L235:
            com.xt.retouch.lib.log.XTLog r5 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r2 = "draft-flow:exportPicture "
            r3.<init>(r2)
            com.xt.retouch.scenes.api.IEditActivityScenesModel r2 = r8.J6()
            java.lang.String r2 = r2.U1()
            r3.append(r2)
            java.lang.String r2 = " failed, saveStatus:"
            r3.append(r2)
            if (r10 == 0) goto L37f
            model.SaveResult$ExportPicStatus r2 = r10.k
            if (r2 == 0) goto L37f
            java.lang.String r2 = r2.b
        L256:
            r3.append(r2)
            java.lang.String r2 = " editmode:"
            r3.append(r2)
            com.xt.retouch.edit.base.api.EditContext r2 = r8.K6()
            com.xt.retouch.edit.base.context.EditMode r2 = r2.f141806a
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r5.getClass()
            com.xt.retouch.lib.log.XTLog.b(r11, r2)
        L271:
            com.xt.retouch.lib.log.XTLog r14 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r2 = "draft-flow:picture export cost: "
            r5.<init>(r2)
            long r2 = java.lang.System.currentTimeMillis()
            long r2 = r2 - r0
            r5.append(r2)
            java.lang.String r2 = r5.toString()
            r14.getClass()
            com.xt.retouch.lib.log.XTLog.e(r11, r2)
            if (r4 == 0) goto L37c
            r5 = 1
        L28f:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r3 = r8.x1
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r5)
            r3.postValue(r2)
            r8.w1 = r5
            r8.u1 = r4
            if (r4 == 0) goto L2b8
            java.lang.String r3 = r4.b
            com.xt.retouch.util.mediastore.XtMediaStore r2 = com.xt.retouch.util.mediastore.XtMediaStore.f150196a
            com.xt.retouch.util.mediastore.XtMediaStore.b(r2, r3)
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r3 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            com.retouch.layermanager.api.layer.ILayerManager r2 = r8.T6()
            r3.getClass()
            java.util.Set r5 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.c(r2)
            java.lang.String r3 = "EditActivityViewModel-handleNormalExport"
            r2 = 0
            com.xt.retouch.edit.base.helper.TextRecognitionHelper.a(r2, r3, r5)
        L2b8:
            java.lang.String r3 = ""
            if (r20 == 0) goto L34a
            r22 = 1
            boolean r24 = r20.isMoveSticker()
            r6 = 0
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            r2.getClass()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.HashSet<java.lang.String>> r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.j
            java.util.Collection r2 = r2.values()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.util.List r2 = kotlin.collections.CollectionsKt__IterablesKt.flatten(r2)
            kotlin.collections.CollectionsKt___CollectionsKt.toSet(r2)
            if (r10 == 0) goto L348
            model.SaveResult$ExportPicStatus r2 = r10.k
        L2dc:
            r9 = r4
            r27 = 48
            r23 = r18
            r25 = r6
            r26 = r2
            r20 = r8
            r21 = r4
            K7(r20, r21, r22, r23, r24, r25, r26, r27)
        L2ec:
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            r2.getClass()
            java.lang.String r2 = "[clearCache] key:"
            java.lang.String r4 = "CACHE_KEY_DEFAULT"
            java.lang.String r3 = r2.concat(r4)
            r14.getClass()
            java.lang.String r2 = "TextRecognitionHelper"
            com.xt.retouch.lib.log.XTLog.e(r2, r3)
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.HashSet<java.lang.String>> r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.j
            r2.remove(r4)
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r2 = "draft-flow:start save atlas time: "
            r10.<init>(r2)
            long r4 = android.os.SystemClock.elapsedRealtime()
            long r2 = r8.t2
            long r4 = r4 - r2
            r10.append(r4)
            java.lang.String r2 = r10.toString()
            com.xt.retouch.lib.log.XTLog.e(r11, r2)
            long r2 = android.os.SystemClock.elapsedRealtime()
            r7.q = r12
            r7.r = r9
            r7.s = r6
            r7.t = r6
            r7.u = r6
            r7.v = r0
            r7.w = r2
            r4 = 2
            r7.z = r4
            r20 = r8
            r21 = r17
            r22 = r12
            r23 = r9
            r24 = r13
            r25 = r7
            java.lang.Object r10 = r20.P7(r21, r22, r23, r24, r25)
            r4 = r19
            if (r10 != r4) goto L3e
            return r19
        L348:
            r2 = 0
            goto L2dc
        L34a:
            r9 = r4
            r22 = 0
            r6 = 0
            com.xt.retouch.edit.base.helper.TextRecognitionHelper r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.f141946a
            r2.getClass()
            java.util.concurrent.ConcurrentHashMap<java.lang.String, java.util.HashSet<java.lang.String>> r2 = com.xt.retouch.edit.base.helper.TextRecognitionHelper.j
            java.util.Collection r2 = r2.values()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
            java.util.List r2 = kotlin.collections.CollectionsKt__IterablesKt.flatten(r2)
            kotlin.collections.CollectionsKt___CollectionsKt.toSet(r2)
            if (r10 == 0) goto L37a
            model.SaveResult$ExportPicStatus r2 = r10.k
        L367:
            r27 = 58
            r20 = r8
            r21 = r9
            r23 = r18
            r24 = r22
            r25 = r6
            r26 = r2
            K7(r20, r21, r22, r23, r24, r25, r26, r27)
            goto L2ec
        L37a:
            r2 = 0
            goto L367
        L37c:
            r5 = 0
            goto L28f
        L37f:
            r2 = 0
            goto L256
        L382:
            if (r10 == 0) goto L234
            model.SaveResult$ExportPicStatus r2 = r10.k
            if (r2 == 0) goto L234
            int r2 = r2.f150875a
            if (r2 != 0) goto L234
            r4 = r10
            if (r10 != 0) goto L271
            goto L235
        L391:
            com.xt.edit.EditActivityViewModel$handleNormalExport$1 r7 = new com.xt.edit.EditActivityViewModel$handleNormalExport$1
            r7.<init>(r8, r3)
            goto L1e
        L398:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.m7(com.xt.retouch.painter.function.api.IPainterCommon$BitmapInfo, android.content.Context, com.xt.retouch.scenes.api.MiddlePageRecorder, java.lang.String, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x009f  */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, kotlin.coroutines.SafeContinuation] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object m8(kotlin.coroutines.Continuation r14, kotlin.jvm.functions.Function1 r15) {
        /*
            r13 = this;
            java.lang.String r12 = "suspendCoroutine, invoked "
            boolean r0 = r14 instanceof com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$1
            if (r0 == 0) goto L9f
            r6 = r14
            com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$1 r6 = (com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$1) r6
            int r2 = r6.u
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L9f
            int r2 = r2 - r1
            r6.u = r2
        L14:
            java.lang.Object r1 = r6.s
            java.lang.Object r7 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.u
            r10 = 1
            if (r0 == 0) goto L33
            if (r0 != r10) goto La6
            kotlinx.coroutines.Deferred r5 = r6.r
            kotlin.ResultKt.throwOnFailure(r1)
        L26:
            com.xt.retouch.lib.log.XTLog r2 = com.xt.retouch.lib.log.XTLog.f144340a
            java.lang.String r1 = "EditActivityViewModel"
            java.lang.String r0 = "get save box draft deferred"
            r2.getClass()
            com.xt.retouch.lib.log.XTLog.e(r1, r0)
            return r5
        L33:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.Object r8 = new java.lang.Object
            r8.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r11 = new kotlin.jvm.internal.Ref$ObjectRef
            r11.<init>()
            kotlin.jvm.internal.Ref$BooleanRef r9 = new kotlin.jvm.internal.Ref$BooleanRef
            r9.<init>()
            com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$deferred$1 r0 = new com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$deferred$1
            r0.<init>()
            java.lang.Object r5 = r15.invoke(r0)
            kotlinx.coroutines.Deferred r5 = (kotlinx.coroutines.Deferred) r5
            r6.q = r8
            r6.getClass()
            r6.getClass()
            r6.r = r5
            r6.u = r10
            kotlin.coroutines.SafeContinuation r4 = new kotlin.coroutines.SafeContinuation
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r6)
            r4.<init>(r0)
            monitor-enter(r8)
            com.xt.retouch.lib.log.XTLog r3 = com.xt.retouch.lib.log.XTLog.f144340a     // Catch: java.lang.Throwable -> Lae
            java.lang.String r2 = "EditActivityViewModel"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lae
            r1.<init>(r12)     // Catch: java.lang.Throwable -> Lae
            boolean r0 = r9.element     // Catch: java.lang.Throwable -> Lae
            r1.append(r0)     // Catch: java.lang.Throwable -> Lae
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> Lae
            r3.getClass()     // Catch: java.lang.Throwable -> Lae
            com.xt.retouch.lib.log.XTLog.e(r2, r0)     // Catch: java.lang.Throwable -> Lae
            r11.element = r4     // Catch: java.lang.Throwable -> Lae
            boolean r0 = r9.element     // Catch: java.lang.Throwable -> Lae
            if (r0 == 0) goto L8e
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r10)     // Catch: java.lang.Throwable -> Lae
            kotlin.Result.m17090constructorimpl(r0)     // Catch: java.lang.Throwable -> Lae
            r4.resumeWith(r0)     // Catch: java.lang.Throwable -> Lae
        L8e:
            monitor-exit(r8)
            java.lang.Object r1 = r4.getOrThrow()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto L9c
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r6)
        L9c:
            if (r1 != r7) goto L26
            return r7
        L9f:
            com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$1 r6 = new com.xt.edit.EditActivityViewModel$wrapExportFunctionCall$1
            r6.<init>(r13, r14)
            goto L14
        La6:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        Lae:
            r0 = move-exception
            monitor-exit(r8)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.m8(kotlin.coroutines.Continuation, kotlin.jvm.functions.Function1):java.lang.Object");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01e8  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00e2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void n6(boolean r11) {
        /*
            r10 = this;
            com.xt.edit.deeplink.DeeplinkManager r3 = r10.f136341J
            java.lang.String r6 = "deeplinkManager"
            r2 = 0
            if (r3 == 0) goto L24
        L7:
            r3.getClass()
            com.xt.retouch.util.KvSettingProvider r5 = com.xt.retouch.util.KvSettingProvider.f150073a
            boolean r0 = r5.H()
            java.lang.String r1 = "Check failed."
            if (r0 == 0) goto L1f
            com.xt.retouch.util.ThreadUtils r0 = com.xt.retouch.util.ThreadUtils.f150169a
            r0.getClass()
            boolean r0 = com.xt.retouch.util.ThreadUtils.a()
            if (r0 == 0) goto L346
        L1f:
            com.xt.retouch.edit.base.deeplink.Deeplink r4 = r3.f136842a
            if (r4 != 0) goto L29
            return
        L24:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r3 = r2
            goto L7
        L29:
            com.xt.edit.deeplink.DeeplinkManager r3 = r10.f136341J
            if (r3 == 0) goto Lba
        L2d:
            r3.getClass()
            boolean r0 = r5.H()
            if (r0 == 0) goto L41
            com.xt.retouch.util.ThreadUtils r0 = com.xt.retouch.util.ThreadUtils.f150169a
            r0.getClass()
            boolean r0 = com.xt.retouch.util.ThreadUtils.a()
            if (r0 == 0) goto L340
        L41:
            com.xt.retouch.edit.base.deeplink.Deeplink r0 = r3.f136842a
            if (r0 == 0) goto Lb8
            android.net.Uri r0 = r0.f141896a
            if (r0 == 0) goto Lb8
            java.lang.String r1 = r0.getPath()
        L4d:
            r3.f136842a = r2
            r5 = 0
            r3 = 1
            java.lang.String r9 = ""
            if (r1 == 0) goto L62
            com.xt.retouch.report.api.EditScenePopupRecorder r0 = com.xt.retouch.report.api.EditScenePopupRecorder.f144989a
            r0.getClass()
            com.xt.retouch.report.api.EditScenePopupRecorder$RecordData r0 = com.xt.retouch.report.api.EditScenePopupRecorder.f144990c
            if (r0 == 0) goto L62
            android.net.Uri r0 = r0.f144992a
            if (r0 != 0) goto L84
        L62:
            com.xt.retouch.edit.base.deeplink.Deeplink$Type r1 = r4.b
            com.xt.retouch.edit.base.deeplink.Deeplink$Type r0 = com.xt.retouch.edit.base.deeplink.Deeplink.Type.f141898a
            if (r1 != r0) goto L164
            android.net.Uri r1 = r4.f141896a
            java.lang.String r0 = "search_result_type"
            java.lang.String r1 = r1.getQueryParameter(r0)
            java.lang.String r0 = "function"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L164
            android.net.Uri r1 = r4.f141896a
            java.lang.String r0 = "channel"
            java.lang.String r6 = r1.getQueryParameter(r0)
            if (r6 != 0) goto Lc0
            r6 = r9
            goto Lc0
        L84:
            if (r11 == 0) goto Lb6
            com.xt.retouch.report.api.EditScenePopupRecorder$RecordData r0 = com.xt.retouch.report.api.EditScenePopupRecorder.f144990c
            if (r0 == 0) goto Lb6
            android.net.Uri r0 = r0.f144992a
            if (r0 == 0) goto Lb6
            java.lang.String r0 = r0.getPath()
            if (r0 == 0) goto Lb6
            boolean r0 = X.C93472yG.S(r0, r1)
            if (r0 != r3) goto Lb6
            r0 = 1
        L9b:
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r1 = com.xt.retouch.report.api.EditScenePopupRecorder.e
            if (r0 == 0) goto Lac
            com.xt.retouch.lib.log.XTLog r0 = com.xt.retouch.lib.log.XTLog.f144340a
            r0.getClass()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r5)
        La8:
            r1.setValue(r0)
            goto L62
        Lac:
            com.xt.retouch.lib.log.XTLog r0 = com.xt.retouch.lib.log.XTLog.f144340a
            r0.getClass()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            goto La8
        Lb6:
            r0 = 0
            goto L9b
        Lb8:
            r1 = r2
            goto L4d
        Lba:
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)
            r3 = r2
            goto L2d
        Lc0:
            android.net.Uri r1 = r4.f141896a     // Catch: java.lang.Throwable -> Le7
            java.lang.String r0 = "keyword"
            java.lang.String r3 = r1.getQueryParameter(r0)     // Catch: java.lang.Throwable -> Le7
            if (r3 == 0) goto Le2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)     // Catch: java.lang.Throwable -> Le7
            java.lang.String r0 = "\n"
            java.lang.String[] r1 = new java.lang.String[]{r0}     // Catch: java.lang.Throwable -> Le7
            r0 = 6
            java.util.List r0 = X.C93472yG.M(r3, r1, r5, r0)     // Catch: java.lang.Throwable -> Le7
            if (r0 == 0) goto Le2
            java.lang.Object r7 = r0.get(r5)     // Catch: java.lang.Throwable -> Le7
            java.lang.String r7 = (java.lang.String) r7     // Catch: java.lang.Throwable -> Le7
            if (r7 != 0) goto Le3
        Le2:
            r7 = r9
        Le3:
            kotlin.Result.m17090constructorimpl(r7)     // Catch: java.lang.Throwable -> Le7
            goto Lef
        Le7:
            r0 = move-exception
            java.lang.Object r7 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r7)
        Lef:
            boolean r0 = kotlin.Result.m17096isFailureimpl(r7)
            if (r0 == 0) goto Lf6
            r7 = r2
        Lf6:
            java.lang.String r7 = (java.lang.String) r7
            if (r7 != 0) goto Lfb
            r7 = r9
        Lfb:
            android.net.Uri r1 = r4.f141896a
            java.lang.String r0 = "keyword_source"
            java.lang.String r8 = r1.getQueryParameter(r0)
            if (r8 != 0) goto L106
            r8 = r9
        L106:
            android.net.Uri r1 = r4.f141896a     // Catch: java.lang.Throwable -> L121
            java.lang.String r0 = "position"
            java.lang.String r0 = r1.getQueryParameter(r0)     // Catch: java.lang.Throwable -> L121
            if (r0 == 0) goto L11f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)     // Catch: java.lang.Throwable -> L121
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.Throwable -> L121
        L117:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> L121
            kotlin.Result.m17090constructorimpl(r1)     // Catch: java.lang.Throwable -> L121
            goto L129
        L11f:
            r0 = 0
            goto L117
        L121:
            r0 = move-exception
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r1)
        L129:
            boolean r0 = kotlin.Result.m17096isFailureimpl(r1)
            if (r0 == 0) goto L33d
        L12f:
            java.lang.Integer r2 = (java.lang.Integer) r2
            if (r2 == 0) goto L33a
            int r5 = r2.intValue()
        L137:
            android.net.Uri r0 = r4.f141896a
            java.lang.String r1 = r0.getPath()
            if (r1 != 0) goto L140
            r1 = r9
        L140:
            android.net.Uri r2 = r4.f141896a
            java.lang.String r0 = "pattern"
            java.lang.String r3 = r2.getQueryParameter(r0)
            if (r3 != 0) goto L14b
            r3 = r9
        L14b:
            android.net.Uri r2 = r4.f141896a
            java.lang.String r0 = "item"
            java.lang.String r2 = r2.getQueryParameter(r0)
            if (r2 != 0) goto L156
            r2 = r9
        L156:
            com.xt.retouch.report.api.IAppEventReport r4 = r10.x6()
            int r0 = r1.hashCode()
            switch(r0) {
                case -2145266176: goto L165;
                case -2129364761: goto L16e;
                case -1963002340: goto L17a;
                case -1662065424: goto L186;
                case -1627619186: goto L192;
                case -1582545362: goto L19b;
                case -1483508182: goto L1a7;
                case -1351815987: goto L1b3;
                case -1249622319: goto L1bf;
                case -569044316: goto L1cb;
                case -553910458: goto L1d7;
                case -372560471: goto L1f0;
                case -314171121: goto L1fe;
                case 1503794: goto L20c;
                case 1511050: goto L21a;
                case 1514453: goto L228;
                case 46513849: goto L236;
                case 46600337: goto L268;
                case 46937644: goto L276;
                case 46962140: goto L284;
                case 62818737: goto L292;
                case 110438045: goto L2a0;
                case 487865324: goto L2ae;
                case 791373229: goto L2bc;
                case 932996635: goto L2ca;
                case 1225750708: goto L2d8;
                case 1340725551: goto L2e6;
                case 1678253258: goto L2f4;
                case 1693336822: goto L302;
                case 1783475303: goto L310;
                case 1976447480: goto L31e;
                case 1989610873: goto L32c;
                default: goto L161;
            }
        L161:
            r4.K0(r5, r6, r7, r8, r9)
        L164:
            return
        L165:
            java.lang.String r0 = "/facial_beauty"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1e0
            goto L161
        L16e:
            java.lang.String r0 = "/stereo"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L177
            goto L161
        L177:
            java.lang.String r9 = "stereo"
            goto L161
        L17a:
            java.lang.String r0 = "/facial_expression"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L183
            goto L161
        L183:
            java.lang.String r9 = "facial_expression"
            goto L161
        L186:
            java.lang.String r0 = "/local_adjustment"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L18f
            goto L161
        L18f:
            java.lang.String r9 = "local_adjustment"
            goto L161
        L192:
            java.lang.String r0 = "/facial_beauty_auto"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1ec
            goto L161
        L19b:
            java.lang.String r0 = "/sticker"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1a4
            goto L161
        L1a4:
            java.lang.String r9 = "sticker"
            goto L161
        L1a7:
            java.lang.String r0 = "/body_beauty"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1b0
            goto L161
        L1b0:
            java.lang.String r9 = "body_beauty"
            goto L161
        L1b3:
            java.lang.String r0 = "/beauty_all"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1bc
            goto L161
        L1bc:
            java.lang.String r9 = "one_key_beauty"
            goto L161
        L1bf:
            java.lang.String r0 = "/graffiti_pen"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1c8
            goto L161
        L1c8:
            java.lang.String r9 = "graffiti_pen"
            goto L161
        L1cb:
            java.lang.String r0 = "/image_effect"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1d4
            goto L161
        L1d4:
            java.lang.String r9 = "image_effect"
            goto L161
        L1d7:
            java.lang.String r0 = "/manual_beauty"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1e8
            goto L161
        L1e0:
            java.lang.String r0 = "manual"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            if (r0 == 0) goto L1ec
        L1e8:
            java.lang.String r9 = "manual_facial_beauty"
            goto L161
        L1ec:
            java.lang.String r9 = "facial_beauty"
            goto L161
        L1f0:
            java.lang.String r0 = "/template"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L1fa
            goto L161
        L1fa:
            java.lang.String r9 = "template"
            goto L161
        L1fe:
            java.lang.String r0 = "/face_beauty"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L208
            goto L161
        L208:
            java.lang.String r9 = "face_beauty"
            goto L161
        L20c:
            java.lang.String r0 = "/hsl"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L216
            goto L161
        L216:
            java.lang.String r9 = "hsl"
            goto L161
        L21a:
            java.lang.String r0 = "/pen"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L224
            goto L161
        L224:
            java.lang.String r9 = "pen"
            goto L161
        L228:
            java.lang.String r0 = "/svg"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L232
            goto L161
        L232:
            java.lang.String r9 = "shape"
            goto L161
        L236:
            java.lang.String r0 = "/edit"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L240
            goto L161
        L240:
            int r0 = r2.length()
            if (r0 != 0) goto L24a
            java.lang.String r9 = "edit"
            goto L161
        L24a:
            com.xt.retouch.effect.api.IEffectProvider r0 = r10.O6()
            androidx.lifecycle.MutableLiveData r0 = r0.c0()
            java.lang.Object r0 = r0.getValue()
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L161
            java.lang.Object r0 = r0.get(r2)
            com.xt.retouch.effect.api.IEffect r0 = (com.xt.retouch.effect.api.IEffect) r0
            if (r0 == 0) goto L161
            java.lang.String r9 = r0.b()
            goto L161
        L268:
            java.lang.String r0 = "/hair"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L272
            goto L161
        L272:
            java.lang.String r9 = "hair"
            goto L161
        L276:
            java.lang.String r0 = "/skin"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L280
            goto L161
        L280:
            java.lang.String r9 = "skin"
            goto L161
        L284:
            java.lang.String r0 = "/text"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L28e
            goto L161
        L28e:
            java.lang.String r9 = "text"
            goto L161
        L292:
            java.lang.String r0 = "/ai_makeup"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L29c
            goto L161
        L29c:
            java.lang.String r9 = "ai_makeup"
            goto L161
        L2a0:
            java.lang.String r0 = "/background"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2aa
            goto L161
        L2aa:
            java.lang.String r9 = "frame"
            goto L161
        L2ae:
            java.lang.String r0 = "/liquify"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2b8
            goto L161
        L2b8:
            java.lang.String r9 = "liquify"
            goto L161
        L2bc:
            java.lang.String r0 = "/eliminate_pen"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2c6
            goto L161
        L2c6:
            java.lang.String r9 = "eliminate_pen"
            goto L161
        L2ca:
            java.lang.String r0 = "/composition"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2d4
            goto L161
        L2d4:
            java.lang.String r9 = "composition"
            goto L161
        L2d8:
            java.lang.String r0 = "/play_function"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2e2
            goto L161
        L2e2:
            java.lang.String r9 = "tricks"
            goto L161
        L2e6:
            java.lang.String r0 = "/manual_body_beauty"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2f0
            goto L161
        L2f0:
            java.lang.String r9 = "body_modeling"
            goto L161
        L2f4:
            java.lang.String r0 = "/portrait"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L2fe
            goto L161
        L2fe:
            java.lang.String r9 = "portrait"
            goto L161
        L302:
            java.lang.String r0 = "/org_cutout"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L30c
            goto L161
        L30c:
            java.lang.String r9 = "cutout"
            goto L161
        L310:
            java.lang.String r0 = "/filter"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L31a
            goto L161
        L31a:
            java.lang.String r9 = "filter"
            goto L161
        L31e:
            java.lang.String r0 = "/makeup"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L328
            goto L161
        L328:
            java.lang.String r9 = "makeup"
            goto L161
        L32c:
            java.lang.String r0 = "/mosaic"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L336
            goto L161
        L336:
            java.lang.String r9 = "mosaic"
            goto L161
        L33a:
            r5 = 0
            goto L137
        L33d:
            r2 = r1
            goto L12f
        L340:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        L346:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.n6(boolean):void");
    }

    public final void n7(final long j, final C1W6 c1w6, final DraftSaveResult draftSaveResult) {
        XTLog xTLog = XTLog.f144340a;
        String str = "handleOnImageSaved: imageSaveResult=" + this.w1 + " image=" + c1w6;
        xTLog.getClass();
        XTLog.e("EditActivityViewModel", str);
        final SaveResult saveResult = new SaveResult(!this.w1 ? 1 : 0, j, c1w6);
        Function0<Unit> function0 = new Function0<Unit>() { // from class: com.xt.edit.EditActivityViewModel$handleOnImageSaved$callback$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                if (KvSettingProvider.f150073a.C1()) {
                    SaveResult saveResult2 = new SaveResult(11, j, c1w6);
                    this.s1.postValue(new SaveImageEvent(SaveImageEvent.Status.f141836d, null, null, saveResult2, false, 0, 54));
                    this.z1.postValue(new Event<>(saveResult2));
                    this.i0.postValue(draftSaveResult);
                } else {
                    this.s1.postValue(new SaveImageEvent(SaveImageEvent.Status.f141836d, null, null, saveResult, false, 0, 54));
                    this.z1.postValue(new Event<>(saveResult));
                    this.i0.postValue(draftSaveResult);
                }
                return Unit.INSTANCE;
            }
        };
        if (!t7()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new EditActivityViewModel$handleOnImageSaved$2(this, function0, null), 2, null);
            return;
        }
        String templateId = J6().j().fa().getTemplateId();
        EditActivity editActivityI6 = I6();
        if (editActivityI6 != null) {
            B6().get().c(editActivityI6, templateId, saveResult);
        }
    }

    public final void n8(IAppEventReport.PhotoImportParams photoImportParams) {
        IEventReport iEventReport = this.R0;
        if (iEventReport == null) {
            Intrinsics.throwUninitializedPropertyAccessException("eventReport");
            iEventReport = null;
        }
        Map<String, Object> mapD = iEventReport.d();
        if (!mapD.isEmpty()) {
            try {
                photoImportParams.r = (String) mapD.get("transfer_channel");
                photoImportParams.s = (String) mapD.get("transfer_effect_id");
                photoImportParams.t = (String) mapD.get("transfer_filter_id");
                photoImportParams.u = (String) mapD.get("transfer_gid");
                photoImportParams.v = (String) mapD.get("transfer_template_id");
                photoImportParams.w = (String) mapD.get("transfer_tricks_id");
                photoImportParams.x = (String) mapD.get("transfer_type");
                photoImportParams.y = (String) mapD.get("enter_from_platform");
            } catch (Exception unused) {
            }
        }
    }

    public final MutableLiveData o6() {
        return this.f2;
    }

    public final void o7(List<? extends RequestRecommendResTaskType> list) {
        Intrinsics.checkNotNullParameter(list, "");
        K6().b();
    }

    public final boolean p7() {
        boolean zA = V6().a();
        if (J6().Jg() && zA) {
            J6().y5();
        }
        List<FaceInfo> value = this.D1.getValue();
        return !(value == null || value.isEmpty());
    }

    public final boolean q7() {
        List<Layer> listI1 = T6().I1();
        if ((listI1 instanceof Collection) && listI1.isEmpty()) {
            return false;
        }
        Iterator<T> it = listI1.iterator();
        while (it.hasNext()) {
            if (J6().j().G5(((Layer) it.next()).b())) {
                return true;
            }
        }
        return false;
    }

    public final void r6() {
        long jC0;
        XTLog.f144340a.getClass();
        XTLog.e("EditActivityViewModel", "download save image");
        KvSettingProvider kvSettingProvider = KvSettingProvider.f150073a;
        if (kvSettingProvider.c0() > 0) {
            kvSettingProvider.c0();
            jC0 = kvSettingProvider.c0();
        } else {
            jC0 = this.f136342X * this.Z * 4;
        }
        long jC = StorageUtils.c(StorageUtils.f150158a);
        XTLog.e("EditActivityViewModel", "maxByte = " + jC0 + ", width = " + this.f136342X + ", height = " + this.Z + ", availableByte = " + jC);
        if (jC >= jC0) {
            BuildersKt__Builders_commonKt.launch$default(ViewModelKt.a(this), null, null, new EditActivityViewModel$downloadSaveImage$1(this, null), 3, null);
            return;
        }
        this.z1.setValue(new Event<>(new SaveResult(2)));
        L7(2, "not enough space");
        XTLog.e("PictureExporter", "downloadSaveImage export error: not enough space");
    }

    public final void r7() {
        CoroutineUtilsKt.d(new EditActivityViewModel$initPersonalTemplateSaveState$1(this, null));
    }

    public final void s6(IPainterCommon.BitmapInfo bitmapInfo, Context context) {
        if (bitmapInfo == null || !bitmapInfo.getHasAlpha() || bitmapInfo.getBitmap().getWidth() * bitmapInfo.getBitmap().getHeight() <= 16777216) {
            return;
        }
        Ref$IntRef ref$IntRef = new Ref$IntRef();
        E7(ref$IntRef.element, context);
        this.q0 = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new EditActivityViewModel$exportBigPng$1$1(ref$IntRef, this, context, null), 2, null);
    }

    public final boolean s7() {
        return K6().c();
    }

    public final Map<String, Object> t6() {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        EffectFlow effectFlowIf = J6().j().If(0, 0L);
        int i = 1;
        if (!J6().j().Z2().isMagicEliminate() && !(!effectFlowIf.getOutPaintItems().isEmpty()) && !(!effectFlowIf.getAiRedrawItems().isEmpty())) {
            i = 0;
        }
        linkedHashMap.put("is_ai_payment", Integer.valueOf(i));
        return linkedHashMap;
    }

    public final boolean t7() {
        return TextUtils.equals(this.R, "cover_edit_page_slide");
    }

    public final IAccount u6() {
        IAccount iAccount = this.f136344c;
        if (iAccount != null) {
            return iAccount;
        }
        Intrinsics.throwUninitializedPropertyAccessException("account");
        return null;
    }

    public final boolean u7() {
        return K6().f141806a == EditMode.b && Intrinsics.areEqual(this.j0.getValue(), Boolean.FALSE);
    }

    public final List<String> v6() {
        EffectFlow effectFlowIf = J6().If(0, 0L);
        ArrayList arrayList = new ArrayList();
        List<EffectFlow.CommonItem> autoItemList = effectFlowIf.getAutoItemList();
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(autoItemList, 10));
        for (EffectFlow.CommonItem commonItem : autoItemList) {
            arrayList2.add(Intrinsics.areEqual(commonItem.f144814a, "auto_oil_remove") ? "oil_remove" : commonItem.f144814a);
        }
        arrayList.addAll(arrayList2);
        List<EffectFlow.ManualItem> manualItemList = effectFlowIf.getManualItemList();
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(manualItemList, 10));
        Iterator<T> it = manualItemList.iterator();
        while (it.hasNext()) {
            arrayList3.add(((EffectFlow.ManualItem) it.next()).f144850a);
        }
        arrayList.addAll(arrayList3);
        List<EffectFlow.CommonItem> editItemList = effectFlowIf.getEditItemList();
        ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(editItemList, 10));
        Iterator<T> it2 = editItemList.iterator();
        while (it2.hasNext()) {
            arrayList4.add(((EffectFlow.CommonItem) it2.next()).f144814a);
        }
        arrayList.addAll(arrayList4);
        List<EffectFlow.MakeupItem> makeupItemList = effectFlowIf.getMakeupItemList();
        ArrayList arrayList5 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(makeupItemList, 10));
        Iterator<T> it3 = makeupItemList.iterator();
        while (it3.hasNext()) {
            arrayList5.add(((EffectFlow.MakeupItem) it3.next()).b);
        }
        arrayList.addAll(CollectionsKt__IterablesKt.flatten(arrayList5));
        List<EffectFlow.SkinItem> skinItemList = effectFlowIf.getSkinItemList();
        ArrayList arrayList6 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(skinItemList, 10));
        Iterator<T> it4 = skinItemList.iterator();
        while (it4.hasNext()) {
            arrayList6.add(((EffectFlow.SkinItem) it4.next()).f144877a);
        }
        arrayList.addAll(arrayList6);
        List<EffectFlow.HairItem> hairList = effectFlowIf.getHairList();
        ArrayList arrayList7 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(hairList, 10));
        Iterator<T> it5 = hairList.iterator();
        while (it5.hasNext()) {
            arrayList7.add(((EffectFlow.HairItem) it5.next()).f144831a);
        }
        arrayList.addAll(arrayList7);
        return arrayList;
    }

    public final boolean v7() {
        return K6().f141806a == EditMode.b;
    }

    public final AppContext w6() {
        AppContext appContext = this.O0;
        if (appContext != null) {
            return appContext;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appContext");
        return null;
    }

    public final boolean w7() {
        FlavorConfig.f139375a.getClass();
        if (FlavorConfig.h()) {
            return true;
        }
        AbTestFacade.f138429a.getClass();
        return ArraysKt___ArraysKt.contains(new String[]{"2", "3"}, AbTestFacade.l().a());
    }

    public final IAppEventReport x6() {
        IAppEventReport iAppEventReport = this.Q0;
        if (iAppEventReport != null) {
            return iAppEventReport;
        }
        Intrinsics.throwUninitializedPropertyAccessException("appEventReport");
        return null;
    }

    public final boolean x7() {
        SnapshotHandler.Snapshot snapshot = this.M;
        if (snapshot != null) {
            List<String> list = snapshot.m;
            if (!list.isEmpty()) {
                ITemplateSdk iTemplateSdk = this.W0;
                if (iTemplateSdk == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("templateSdk");
                    iTemplateSdk = null;
                }
                List<String> listB = iTemplateSdk.b();
                for (String str : list) {
                    if (!listB.contains(str)) {
                        XTLog.f144340a.getClass();
                        XTLog.e("EditActivityViewModel", "checkSupportFeatureList, feature(" + str + ") isn't support");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public final IAppEventReport.PhotoImportParams y6() {
        String strU1 = J6().U1();
        String str = this.S;
        if (str == null) {
            str = this.B0;
        }
        String str2 = this.R;
        u6().isLogin();
        boolean zB7 = b7();
        int size = ((ArrayList) this.p).size() > 1 ? ((ArrayList) this.p).size() : 1;
        String str3 = this.S1;
        List<FaceInfo> value = this.D1.getValue();
        Integer numValueOf = value != null ? Integer.valueOf(value.size()) : null;
        List<List<Integer>> value2 = this.F1.getValue();
        return new IAppEventReport.PhotoImportParams(strU1, str, str2, zB7, false, size, null, null, false, null, str3, numValueOf, value2 != null ? Integer.valueOf(value2.size()) : null, null, (this.D0 || this.C0 || Intrinsics.areEqual(this.S1, "other_draft_recover") || Intrinsics.areEqual(this.S1, "batch_draft_recover")) ? this.z0 : null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, "");
    }

    public final void y7() {
        KvSettingProvider.f150073a.T4(false);
        f7().h(new Function1<VipBenefitInfo, Unit>() { // from class: com.xt.edit.EditActivityViewModel$onCreate$1
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(VipBenefitInfo vipBenefitInfo) {
                this.e.Y0 = vipBenefitInfo;
                return Unit.INSTANCE;
            }
        });
        f7().b(null);
        JSONObject jSONObjectPut = new JSONObject().put("data", new JSONObject());
        LynxMsgCenter lynxMsgCenter = LynxMsgCenter.f60731a;
        EditActivityViewModel$onCreate$2 editActivityViewModel$onCreate$2 = new Function1<Object, Unit>() { // from class: com.xt.edit.EditActivityViewModel$onCreate$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        lynxMsgCenter.getClass();
        LynxMsgCenter.g("updateSubscribeInfo", "", jSONObjectPut, 1, editActivityViewModel$onCreate$2);
        if (t7()) {
            B6().get().b(this.F0);
        }
    }

    public final String z6() {
        FlavorConfig.f139375a.getClass();
        if (FlavorConfig.b()) {
            if (J6().i6() || q7()) {
                return "retouch_remove_background";
            }
            if (J6().Q9()) {
                return "retouch_image_clear";
            }
            if (J6().Je()) {
                return "retouch_ai_background";
            }
            if (K6().f141806a == EditMode.f141869g) {
                return "retouch_edit_tool";
            }
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x003b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void z7(java.lang.String r20, java.lang.String r21, java.lang.String r22, com.xt.edit.api.DraftSaveReportParams r23) {
        /*
            r19 = this;
            java.lang.String r6 = ""
            r5 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r6)
            r8 = r21
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r6)
            r11 = r22
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r6)
            r3 = 0
            r4 = r23
            if (r4 == 0) goto L1a
            java.lang.String r14 = r4.f136482a
            if (r14 != 0) goto L1b
        L1a:
            r14 = r6
        L1b:
            int r0 = r14.length()
            r1 = r19
            if (r0 != 0) goto L3c
            androidx.lifecycle.MutableLiveData<java.lang.Integer> r0 = r1.N1
            java.lang.Object r0 = r0.getValue()
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L3b
            int r2 = r0.intValue()
            com.xt.edit.EditActivityViewModel$IQueryFragmentIdMsgCallback r0 = r1.v
            if (r0 == 0) goto L3b
            java.lang.String r14 = r0.a(r2)
            if (r14 != 0) goto L3c
        L3b:
            r14 = r6
        L3c:
            if (r4 == 0) goto L8d
            long r15 = r4.b
            java.lang.String r2 = r4.f136483c
            java.lang.String r0 = r4.f136484d
            com.xt.edit.api.DraftSaveReportParams r13 = new com.xt.edit.api.DraftSaveReportParams
            r17 = r2
            r18 = r0
            r13.<init>(r14, r15, r17, r18)
        L4d:
            com.xt.retouch.report.api.IAppEventReport r4 = r1.x6()
            if (r13 == 0) goto L57
            java.lang.String r0 = r13.f136482a
            if (r0 != 0) goto L8b
        L57:
            com.xt.retouch.scenes.api.IEditActivityScenesModel r0 = r1.J6()
            java.lang.String r7 = r0.U1()
            if (r13 == 0) goto L86
            long r9 = r13.b
            java.lang.String r12 = r13.f136483c
            java.lang.String r13 = r13.f136484d
        L67:
            com.xt.retouch.scenes.api.draft.IDraftScenesModel r0 = r1.I0
            if (r0 == 0) goto L80
            r3 = r0
        L6c:
            com.xt.retouch.painter.function.api.IPainter r2 = r3.j()
            com.xt.retouch.painter.function.api.ProfilerType r1 = com.xt.retouch.painter.function.api.ProfilerType.e
            java.lang.String r0 = "LAST_ID"
            com.xt.retouch.painter.function.api.ProfilingData r0 = r2.qa(r1, r0)
            java.util.Map r14 = r0.toParams()
            r4.Q1(r5, r6, r7, r8, r9, r11, r12, r13, r14)
            return
        L80:
            java.lang.String r0 = "draftScenesModel"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L6c
        L86:
            r9 = 0
            r12 = r3
            r13 = r3
            goto L67
        L8b:
            r6 = r0
            goto L57
        L8d:
            r13 = r3
            goto L4d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xt.edit.EditActivityViewModel.z7(java.lang.String, java.lang.String, java.lang.String, com.xt.edit.api.DraftSaveReportParams):void");
    }
}
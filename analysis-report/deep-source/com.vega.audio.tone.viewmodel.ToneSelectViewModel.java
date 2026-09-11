package com.vega.audio.tone.viewmodel;

import X.C28530bi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.activity.result.ActivityResult;
import androidx.core.view.MotionEventCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleCoroutineScopeImpl;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import com.bytedance.apm.util.NetUtils;
import com.bytedance.bdturing.EventReport;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.bytedance.speech.speechengine.SpeechEngineDefines;
import com.lemon.librespool.model.gen.EffectByIdParams;
import com.lemon.librespool.model.gen.PackOptional;
import com.lemon.lv.config.AuditionUsersTextCacheABConfig;
import com.lemon.lv.config.AuditionUsersTextCacheABTest;
import com.lemon.lv.config.AuditionUsersTextConfig;
import com.lemon.lv.config.AuditionUsersTextEntranceABConfig;
import com.lemon.lv.config.AuditionUsersTextEntranceABTest;
import com.lemon.lv.config.AuditionUsersTextSettings;
import com.lemon.lv.config.BaseClientSetting;
import com.lemon.lv.config.ClientSetting;
import com.lemon.lv.config.ModelConfig;
import com.lemon.lv.config.ToneCommercialOptABTest;
import com.lemon.lv.config.ToneCommercialOptABTestConfig;
import com.lemon.lv.config.ToneEmotionConfig;
import com.lemon.lv.config.ToneEmotionConfigSetting;
import com.lemon.lv.data.Emotion;
import com.lemon.lv.data.ToneType;
import com.lemon.lv.editor.EditorProxyModule;
import com.lemon.lv.editor.proxy.IAccount;
import com.lemon.lvoverseas.R;
import com.service.CustomizedStatusListener;
import com.service.DigitalHumanCustomizeApi;
import com.ss.android.ugc.effectmanager.effect.model.Effect;
import com.ss.android.ugc.effectmanager.effect.model.EffectCategoryModel;
import com.ss.android.ugc.effectmanager.effect.model.template.EffectTemplate;
import com.ss.android.vesdk.VEUtils;
import com.vega.aigcapi.materialgenerate.ReadingListener;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportDigitalHumanEntrance;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.audio.config.FilterGroup;
import com.vega.audio.config.FilterItem;
import com.vega.audio.config.ToneSelectPanelTagConfig;
import com.vega.audio.config.ToneSelectPanelTagConfigSettings;
import com.vega.audio.musicimport.extract.ExtractGalleryMusicActivity;
import com.vega.audio.songclone.ClipflowSongCloneManagePreviewTask;
import com.vega.audio.tone.ReportHelper;
import com.vega.audio.tone.TextToAudioManager;
import com.vega.audio.tone.clonetone.CloneToneLoginHelper;
import com.vega.audio.tone.clonetone.ability.CloneToneUtils;
import com.vega.audio.tone.clonetone.dialog.ToneCloneIntroDialog;
import com.vega.audio.tone.clonetone.dialog.ToneCloneLegalDialog;
import com.vega.audio.tone.clonetone.statistics.CloneToneStatistics;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig;
import com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfigSetting;
import com.vega.audio.tone.tts.engine.ThirdPartyPlayer;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.audio.tone.util.ToneTagInfoUtils;
import com.vega.audio.tone.view.AudioChoicePanel;
import com.vega.clipflow.Clipflow;
import com.vega.clipflow.ClipflowManager;
import com.vega.clipflow.swig.ClipFlowTaskEvent;
import com.vega.config.ConfigSettingsKt;
import com.vega.config.EntranceKey;
import com.vega.config.TbcEditEntranceConfig2;
import com.vega.config.TbcEditEntranceConfigSettings;
import com.vega.container.session.core.ISession;
import com.vega.container.session.ext.DraftCallbackResult;
import com.vega.container.session.observable.AbsSessionObservable;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.ext.LiveDataExtKt;
import com.vega.core.privacy.fbv.geoblock.GeoBlockManager;
import com.vega.core.utils.CombinedLiveData;
import com.vega.core.utils.DirectoryUtil;
import com.vega.core.utils.FileUtils;
import com.vega.core.utils.LiveDataExKt;
import com.vega.edit.base.audio.tone.ICloneToneRepository;
import com.vega.edit.base.audio.tone.IProgressDialogController;
import com.vega.edit.base.audio.tone.IToneSelectViewModel;
import com.vega.edit.base.audio.tone.TextToAudioInfoPack;
import com.vega.edit.base.clonetone.apis.data.GetPlayInfoAuth;
import com.vega.edit.base.dock.locate.EffectListStateWrapper;
import com.vega.edit.base.dock.locate.EffectLocatorOwner;
import com.vega.edit.base.model.repository.SegmentState;
import com.vega.edit.base.session.ISessionBusinessBridgeKt;
import com.vega.edit.base.sticker.model.FixCategoryItem;
import com.vega.edit.base.tone.EmotionOption;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.edit.base.utils.ToneFlavorImpl;
import com.vega.edit.base.utils.ToneUtil;
import com.vega.edit.base.view.BaseTabViewModel;
import com.vega.edit.base.viewmodel.effect.IEffectItemViewModel;
import com.vega.edit.base.vipmaterial.VipMaterialUtils;
import com.vega.editorapi.session.SessionScene;
import com.vega.edward.templatecore.editor.draft.CutSameData;
import com.vega.effectplatform.artist.Constants;
import com.vega.effectplatform.artist.data.EffectExtendKt;
import com.vega.effectplatform.loki.EffectExKt;
import com.vega.effectplatform.loki.EffectPanel;
import com.vega.effectplatform.repository.EffectListState;
import com.vega.effectplatform.repository.PagedEffectListState;
import com.vega.effectplatform.repository.RepoResult;
import com.vega.effectplatform.utils.ReportEditSessionManager;
import com.vega.gallery.Utils;
import com.vega.gallery.local.MediaData;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.infrastructure.base.ModuleCommonKt;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.kv.KvStorage;
import com.vega.kv.KvStorageKt;
import com.vega.libeffect.model.PreLoadFirstParams;
import com.vega.libeffect.repository.AllEffectsRepository;
import com.vega.libeffect.repository.CategoriesRepository;
import com.vega.libeffect.repository.CategoryListState;
import com.vega.libeffect.settings.MaterialMergeApiOptimizeV2;
import com.vega.libeffect.settings.MaterialMergeApiOptimizeV2Settings;
import com.vega.libeffect.settings.MergeApiModel;
import com.vega.libeffect.settings.OptimizeApiPanels;
import com.vega.libeffect.settings.PanelsApiOptimizationKt;
import com.vega.libeffect.settings.PanelsApiOptimizationOptimization;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.libmedia.videoview.VideoCommonUtils;
import com.vega.log.BLog;
import com.vega.middlebridge.client.DraftClient;
import com.vega.middlebridge.lyrasession.LyraSession;
import com.vega.middlebridge.swig.DigitalHumanVoiceInfo;
import com.vega.middlebridge.swig.Draft;
import com.vega.middlebridge.swig.IQueryUtils;
import com.vega.middlebridge.swig.LVVEMetaType;
import com.vega.middlebridge.swig.LVVETrackType;
import com.vega.middlebridge.swig.MaterialAudio;
import com.vega.middlebridge.swig.MaterialDigitalHuman;
import com.vega.middlebridge.swig.MaterialTextTemplate;
import com.vega.middlebridge.swig.Node;
import com.vega.middlebridge.swig.QueryUtilsModuleJNI;
import com.vega.middlebridge.swig.Segment;
import com.vega.middlebridge.swig.SegmentAudio;
import com.vega.middlebridge.swig.SegmentText;
import com.vega.middlebridge.swig.SegmentTextTemplate;
import com.vega.middlebridge.swig.SegmentVideo;
import com.vega.middlebridge.swig.Track;
import com.vega.middlebridge.swig.VectorOfLVVETrackType;
import com.vega.middlebridge.swig.VectorOfSegment;
import com.vega.middlebridge.swig.VectorOfString;
import com.vega.middlebridge.swig.VectorOfTrack;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.report.ReportManagerWrapper;
import com.vega.subscriptionapi.biz.function.ICloneToneBusiness;
import com.vega.theme.textpanel.ThemeType;
import com.vega.ui.LoadingDialog;
import com.vega.ui.activity.IActivityForResult;
import com.vega.ui.util.ContextExKt;
import com.vega.util.ToastUtilKt;
import com.vega.ve.utils.DraftExpandKt;
import com.vega.ve.utils.VEUtils;
import io.reactivex.subjects.BehaviorSubject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt__StringNumberConversionsKt;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes32.dex */
public abstract class ToneSelectViewModel extends BaseTabViewModel implements IToneSelectViewModel {
    public static final /* synthetic */ KProperty<Object>[] m1;
    public final MutableLiveData<String> A;
    public String A0;
    public final MutableLiveData<String> B;
    public LiveData<Integer> B0;
    public String C;
    public final MutableLiveData C0;
    public final MutableLiveData<Boolean> D;
    public final boolean D0;
    public final MutableLiveData<Boolean> E;
    public final boolean E0;
    public final MutableLiveData<ToneType> F;
    public final MutableLiveData F0;
    public ToneType G;
    public final Lazy G0;
    public String H;
    public Effect H0;
    public LiveData<Boolean> I;
    public final MutableLiveData<EffectListState> I0;

    /* renamed from: J, reason: collision with root package name */
    public LiveData<Boolean> f74644J;
    public final MutableLiveData<PagedEffectListState<Effect>> J0;
    public final MutableLiveData K;
    public final MutableLiveData<PagedEffectListState<Effect>> K0;
    public LiveData<Float> L;
    public final ArrayList<CutSameData> L0;
    public LiveData<String> M;
    public final MutableLiveData<Boolean> M0;
    public final MutableLiveData N;
    public final MutableLiveData<EventModel> N0;
    public final MutableLiveData O;
    public final MutableLiveData<Boolean> O0;
    public final MutableLiveData P;
    public final KvStorage P0;
    public SegmentVideo Q;
    public final ReadWriteProperty Q0;
    public float R;
    public final ReadWriteProperty R0;
    public boolean S;
    public final ReadWriteProperty S0;
    public boolean T;
    public boolean T0;
    public final MutableLiveData U;
    public final ICloneToneBusiness U0;
    public final MutableLiveData V;
    public int V0;
    public final CombinedLiveData W;
    public int W0;

    /* renamed from: X, reason: collision with root package name */
    public final MutableLiveData f74645X;
    public final HashMap<String, Emotion> X0;
    public final Map<String, Float> Y;
    public final HashMap<String, Boolean> Y0;
    public final Map<String, String> Z;
    public final Lazy Z0;
    public final MutableLiveData<Float> a0;
    public final Lazy a1;
    public boolean b0;
    public final String b1;
    public boolean c0;
    public String c1;
    public boolean d0;
    public final ToneSelectViewModel$customizedCharacterStatusListener$1 d1;
    public int e0;
    public final Lazy e1;
    public String f0;
    public final Lazy f1;
    public boolean g0;
    public final SparseArray<ToneType> g1;
    public int h0;
    public List<String> h1;
    public final MutableLiveData<Boolean> i0;
    public int i1;
    public final MutableLiveData j0;
    public String j1;
    public final MutableLiveData k0;
    public final Lazy k1;
    public boolean l0;
    public boolean l1;
    public String m0;
    public boolean n0;
    public Boolean o0;
    public boolean p0;
    public ClipflowSongCloneManagePreviewTask q0;
    public boolean r0;
    public boolean s0;
    public String t0;
    public final Lazy u0;
    public final Lazy v0;
    public final AllEffectsRepository w;
    public Lazy<? extends LiveData<CategoryListState>> w0;
    public final ICloneToneRepository x;
    public final MutableLiveData x0;
    public String y;
    public final List<EffectCategoryModel> y0;
    public final MutableLiveData<String> z;
    public final String z0;

    /* loaded from: classes.dex */
    public static final class CMTimeRange {

        /* renamed from: a, reason: collision with root package name */
        public final long f74646a;
        public final long b;

        public CMTimeRange(long j, long j2) {
            this.f74646a = j;
            this.b = j2;
        }
    }

    /* loaded from: classes15.dex */
    public static final class Companion {
    }

    /* loaded from: classes23.dex */
    public static final class EventModel {

        /* renamed from: a, reason: collision with root package name */
        public final String f74647a;
        public final Object b;

        public EventModel(String str, Object obj) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(obj, "");
            this.f74647a = str;
            this.b = obj;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof EventModel)) {
                return false;
            }
            EventModel eventModel = (EventModel) obj;
            return Intrinsics.areEqual(this.f74647a, eventModel.f74647a) && Intrinsics.areEqual(this.b, eventModel.b);
        }

        public final int hashCode() {
            return (this.f74647a.hashCode() * 31) + this.b.hashCode();
        }

        public final String toString() {
            return "EventModel(name=" + this.f74647a + ", data=" + this.b + ')';
        }
    }

    /* loaded from: classes.dex */
    public static final class InsertInfo {

        /* renamed from: a, reason: collision with root package name */
        public final int f74648a;

        public InsertInfo(int i) {
            this.f74648a = i;
        }
    }

    /* loaded from: classes5.dex */
    public static final class TrackInfo {

        /* renamed from: a, reason: collision with root package name */
        public final int f74649a;
        public final List<CMTimeRange> b;

        public TrackInfo(int i, List<CMTimeRange> list) {
            this.f74649a = i;
            this.b = list;
        }
    }

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f74650a;
        public static final /* synthetic */ int[] b;

        static {
            int[] iArr = new int[TextToSpeechReportDigitalHumanEntrance.values().length];
            try {
                iArr[TextToSpeechReportDigitalHumanEntrance.TEXT_ADD_SUBTITLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[TextToSpeechReportDigitalHumanEntrance.TEXT_ADD_TEXT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            f74650a = iArr;
            int[] iArr2 = new int[ThemeType.values().length];
            try {
                iArr2[1] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr2[2] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr2[3] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[4] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                iArr2[6] = 5;
            } catch (NoSuchFieldError unused7) {
            }
            b = iArr2;
        }
    }

    static {
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(ToneSelectViewModel.class, "canShowCloneToneGuide", "getCanShowCloneToneGuide()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(ToneSelectViewModel.class, "agreeToneCloneLegal", "getAgreeToneCloneLegal()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl2);
        MutablePropertyReference1Impl mutablePropertyReference1Impl3 = new MutablePropertyReference1Impl(ToneSelectViewModel.class, "agreeSingToneCloneLegal", "getAgreeSingToneCloneLegal()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl3);
        m1 = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2, mutablePropertyReference1Impl3};
        new Companion();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v10, resolved type: com.service.DigitalHumanCustomizeApi */
    /* JADX DEBUG: Multi-variable search result rejected for r3v85, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v86, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v87, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r3v88, resolved type: java.lang.Object[] */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v39, types: [com.vega.audio.tone.viewmodel.ToneSelectViewModel$customizedCharacterStatusListener$1] */
    public ToneSelectViewModel(AllEffectsRepository allEffectsRepository, CategoriesRepository categoriesRepository, Provider<IEffectItemViewModel> provider, ISession iSession, ICloneToneRepository iCloneToneRepository) {
        List listEmptyList;
        super(categoriesRepository, provider, iSession);
        Intrinsics.checkNotNullParameter(allEffectsRepository, "");
        Intrinsics.checkNotNullParameter(categoriesRepository, "");
        Intrinsics.checkNotNullParameter(provider, "");
        Intrinsics.checkNotNullParameter(iSession, "");
        Intrinsics.checkNotNullParameter(iCloneToneRepository, "");
        this.w = allEffectsRepository;
        this.x = iCloneToneRepository;
        this.z = new MutableLiveData<>();
        this.A = new MutableLiveData<>();
        this.B = new MutableLiveData<>();
        this.C = "";
        Boolean bool = Boolean.FALSE;
        this.D = new MutableLiveData<>(bool);
        this.E = new MutableLiveData<>(bool);
        this.F = new MutableLiveData<>();
        this.I = new MutableLiveData();
        this.f74644J = new MutableLiveData();
        this.K = new MutableLiveData();
        this.L = new MutableLiveData();
        this.M = new MutableLiveData();
        this.N = new MutableLiveData();
        this.O = new MutableLiveData();
        this.P = new MutableLiveData();
        this.R = 1.0f;
        MutableLiveData mutableLiveData = new MutableLiveData();
        this.U = mutableLiveData;
        MutableLiveData mutableLiveData2 = new MutableLiveData();
        this.V = mutableLiveData2;
        this.W = LiveDataExKt.f(mutableLiveData, mutableLiveData2, new Function2<ToneType, String, Pair<? extends ToneType, ? extends String>>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$changeToneCloneModel$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Pair<? extends ToneType, ? extends String> invoke(ToneType toneType, String str) {
                return new Pair<>(toneType, str);
            }
        });
        this.f74645X = new MutableLiveData();
        this.Y = new LinkedHashMap();
        this.Z = new LinkedHashMap();
        this.a0 = new MutableLiveData<>();
        this.d0 = true;
        this.h0 = -1;
        MutableLiveData<Boolean> mutableLiveData3 = new MutableLiveData<>();
        this.i0 = mutableLiveData3;
        this.j0 = mutableLiveData3;
        this.k0 = new MutableLiveData(CollectionsKt__CollectionsKt.emptyList());
        this.m0 = "";
        this.t0 = "none";
        Lazy lazy = LazyKt__LazyJVMKt.lazy(new Function0<ToneFlavorImpl>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$toneFlavor$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final ToneFlavorImpl invoke() {
                return new ToneFlavorImpl();
            }
        });
        this.u0 = lazy;
        this.v0 = LazyKt__LazyJVMKt.lazy(new Function0<IAccount>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$account$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final IAccount invoke() {
                return ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccount();
            }
        });
        this.w0 = LazyKt__LazyJVMKt.lazy(new Function0<LiveData<CategoryListState>>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$_categoryListState$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final LiveData<CategoryListState> invoke() {
                return this.e.x7();
            }
        });
        if (((ToneFlavorImpl) lazy.getValue()).f89172a) {
            FixCategoryItem.f88715a.getClass();
            listEmptyList = CollectionsKt__CollectionsJVMKt.listOf(FixCategoryItem.n);
        } else {
            listEmptyList = CollectionsKt__CollectionsKt.emptyList();
        }
        RepoResult repoResult = RepoResult.f98531a;
        FixCategoryItem.f88715a.getClass();
        EffectCategoryModel effectCategoryModel = FixCategoryItem.o;
        String str = null;
        this.x0 = new MutableLiveData(new CategoryListState(repoResult, CollectionsKt___CollectionsKt.plus((Collection) listEmptyList, (Iterable) CollectionsKt__CollectionsJVMKt.listOf(effectCategoryModel)), 0, str, str, str, 60));
        this.y0 = new ArrayList();
        this.z0 = "";
        this.A0 = "";
        this.B0 = new MutableLiveData();
        int i = 0;
        this.C0 = new MutableLiveData(0);
        GeoBlockManager.f79427a.getClass();
        this.D0 = GeoBlockManager.a("bpea-fbv_voice_editor_text_addtext_text_to_speech_text_to_speech").b || GeoBlockManager.a("bpea-fbv_voice_editor_text_texttemplate_text_to_speech_text_to_speech").b || GeoBlockManager.a("bpea-fbv_voice_editor_text_autocaptions_text_to_speech_text_to_speech").b || GeoBlockManager.a("bpea-fbv_voice_editor_text_autolyrics_text_to_speech_text_to_speech").b;
        this.E0 = GeoBlockManager.a("bpea-fbv_voice_editor_text_addtext_text_to_speech_custom_voices").b || GeoBlockManager.a("bpea-fbv_voice_editor_text_texttemplate_text_to_speech_custom_voices").b;
        effectCategoryModel.setName(ModuleCommonKt.b(R.string.na0));
        EffectCategoryModel effectCategoryModel2 = FixCategoryItem.x;
        effectCategoryModel2.setName(ModuleCommonKt.b(R.string.peu));
        List listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new EffectCategoryModel[]{effectCategoryModel, effectCategoryModel2});
        ArrayList arrayList = new ArrayList();
        for (Object obj : listListOf) {
            com.ss.ugc.effectplatform.model.EffectCategoryModel effectCategoryModel3 = (com.ss.ugc.effectplatform.model.EffectCategoryModel) obj;
            if (!this.D0 || !Intrinsics.areEqual(effectCategoryModel3.getName(), ModuleCommonKt.b(R.string.peu))) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            com.ss.ugc.effectplatform.model.EffectCategoryModel effectCategoryModel4 = (com.ss.ugc.effectplatform.model.EffectCategoryModel) next;
            if ((!this.E0 && ((TbcEditEntranceConfig2) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TbcEditEntranceConfigSettings.class))).a(EntranceKey.m, "")) || !Intrinsics.areEqual(effectCategoryModel4.getName(), ModuleCommonKt.b(R.string.na0))) {
                arrayList2.add(next);
            }
        }
        this.F0 = new MutableLiveData(new CategoryListState(repoResult, arrayList2, i, null, null == true ? 1 : 0, null == true ? 1 : 0, 60));
        this.G0 = LazyKt__LazyJVMKt.lazy(new Function0<EffectListStateWrapper>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$toneMultiLiveData$2
            {
                super(0);
            }

            /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.vega.audio.tone.viewmodel.ToneSelectViewModel */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function0
            public final EffectListStateWrapper invoke() {
                ToneSelectViewModel toneSelectViewModel = this.e;
                return new EffectListStateWrapper(toneSelectViewModel instanceof EffectLocatorOwner ? (EffectLocatorOwner) toneSelectViewModel : null, Constants.EffectType.r, toneSelectViewModel.r);
            }
        });
        this.H0 = new Effect(null == true ? 1 : 0, 1, null == true ? 1 : 0);
        MutableLiveData<EffectListState> mutableLiveData4 = this.w.f109509d;
        Intrinsics.checkNotNull(mutableLiveData4, "");
        this.I0 = mutableLiveData4;
        this.J0 = this.x.I();
        this.K0 = this.x.M();
        this.L0 = new ArrayList<>();
        Boolean bool2 = Boolean.FALSE;
        this.M0 = new MutableLiveData<>(bool2);
        this.N0 = new MutableLiveData<>();
        this.O0 = new MutableLiveData<>(bool2);
        KvStorage kvStorage = new KvStorage(ModuleCommon.INSTANCE.getApplication(), "clone_tone.config");
        this.P0 = kvStorage;
        Boolean bool3 = Boolean.TRUE;
        this.Q0 = KvStorageKt.e(kvStorage, "key_clone_tone_guide", bool3, false);
        this.R0 = KvStorageKt.e(kvStorage, "key_agree_clone_tone_legal", bool2, false);
        this.S0 = KvStorageKt.e(kvStorage, "key_agree_sing_clone_tone_legal", bool2, false);
        SPIService sPIService = SPIService.INSTANCE;
        this.U0 = (ICloneToneBusiness) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ICloneToneBusiness.class), null);
        this.V0 = -300000;
        this.W0 = -300000;
        this.X0 = new HashMap<>();
        this.Y0 = new HashMap<>();
        this.Z0 = LazyKt__LazyJVMKt.lazy(new Function0<MergeApiModel>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$toneMergeApi$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final MergeApiModel invoke() {
                return ((MaterialMergeApiOptimizeV2) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(MaterialMergeApiOptimizeV2Settings.class))).b();
            }
        });
        this.a1 = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$toneForceNetwork$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(PanelsApiOptimizationKt.d((OptimizeApiPanels) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(PanelsApiOptimizationOptimization.class)), "tone"));
            }
        });
        this.b1 = "";
        this.c1 = "";
        ?? r4 = new CustomizedStatusListener() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$customizedCharacterStatusListener$1
            @Override // com.service.CustomizedStatusListener
            public final void a() {
                IToneSelectViewModel.DefaultImpls.a(this.f74651a, false, false, 4);
            }
        };
        this.d1 = r4;
        this.e1 = LazyKt__LazyJVMKt.lazy(new Function0<Integer>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$auditionUsersTextMaxSize$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                return Integer.valueOf(((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).hitExperiment() ? ((AuditionUsersTextConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextSettings.class))).getAuditionUsersTextMaxSize() : ((AuditionUsersTextCacheABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextCacheABTest.class))).getAuditionUsersTextMaxSize());
            }
        });
        this.f1 = LazyKt__LazyJVMKt.lazy(new Function0<List<TextToSpeechReportScene>>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$ttSAuditionCacheScenes$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final List<TextToSpeechReportScene> invoke() {
                ArrayList arrayList3 = new ArrayList();
                if (((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).cacheOnlyForTextEdit()) {
                    arrayList3.add(TextToSpeechReportScene.AUDIO_PANEL);
                } else if (((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).hitExperiment() || ((AuditionUsersTextCacheABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextCacheABTest.class))).getUseCache()) {
                    arrayList3.add(TextToSpeechReportScene.AUDIO_PANEL);
                    arrayList3.add(TextToSpeechReportScene.AUDIO_CLONE);
                    arrayList3.add(TextToSpeechReportScene.DIGITAL_HUMAN);
                    arrayList3.add(TextToSpeechReportScene.AI_SCRIPT);
                    arrayList3.add(TextToSpeechReportScene.LONG_TEXT_EDITOR);
                    TextToSpeechReportScene textToSpeechReportScene = TextToSpeechReportScene.TEXT_TO_VIDEO;
                    arrayList3.add(textToSpeechReportScene);
                    arrayList3.add(textToSpeechReportScene);
                }
                return arrayList3;
            }
        });
        this.g1 = new SparseArray<>();
        if (iSession.C0() == SessionScene.f97670d) {
            this.D.setValue(bool3);
        }
        ((DigitalHumanCustomizeApi) sPIService.getImpl(Reflection.getOrCreateKotlinClass(DigitalHumanCustomizeApi.class), null)).c(r4);
        this.k1 = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$enableRemoteSami$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v());
            }
        });
    }

    public static /* synthetic */ void I7(ToneSelectViewModel toneSelectViewModel, String str, int i, LVVEMetaType lVVEMetaType, int i2) {
        if ((i2 & 4) != 0) {
            lVVEMetaType = null;
        }
        toneSelectViewModel.H7(str, i, lVVEMetaType, false);
    }

    public static void K7() {
        ReportManagerWrapper.INSTANCE.onEvent("login_show", MapsKt__MapsKt.mapOf(TuplesKt.to("material_type", "timbre"), TuplesKt.to("enter_from", "custom_voice"), TuplesKt.to("business_type", String.valueOf(((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccess().a()))));
    }

    public static MediatorLiveData M6(LiveData liveData, final List list) {
        Intrinsics.checkNotNullParameter(liveData, "");
        Intrinsics.checkNotNullParameter(list, "");
        final MediatorLiveData mediatorLiveData = new MediatorLiveData();
        mediatorLiveData.addSource(liveData, new ToneSelectViewModel$sam$androidx_lifecycle_Observer$0(new Function1<CategoryListState, Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$addExtraCategoriesToState$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(CategoryListState categoryListState) {
                CategoryListState categoryListState2 = categoryListState;
                List listPlus = CollectionsKt___CollectionsKt.plus((Collection) list, (Iterable) categoryListState2.b);
                Intrinsics.checkNotNull(categoryListState2);
                mediatorLiveData.setValue(CategoryListState.a(categoryListState2, null, listPlus, 61));
                return Unit.INSTANCE;
            }
        }));
        return mediatorLiveData;
    }

    public static /* synthetic */ void M7(ToneSelectViewModel toneSelectViewModel, boolean z, int i) {
        if ((i & 1) != 0) {
            z = true;
        }
        toneSelectViewModel.L7(z, false);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v3, resolved type: com.vega.report.ReportManagerWrapper */
    /* JADX DEBUG: Multi-variable search result rejected for r6v28, resolved type: java.lang.Boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r6v8, resolved type: java.lang.Boolean */
    /* JADX DEBUG: Multi-variable search result rejected for r6v9, resolved type: java.lang.Boolean */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:132:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x037e  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0397  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x03b9  */
    /* JADX WARN: Removed duplicated region for block: B:243:0x03d3  */
    /* JADX WARN: Removed duplicated region for block: B:259:0x040c  */
    /* JADX WARN: Removed duplicated region for block: B:262:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:263:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x0466  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void N7(com.vega.audio.tone.viewmodel.ToneSelectViewModel r19, com.lemon.lv.data.ToneType r20, java.lang.String r21, java.util.HashMap r22, java.lang.Float r23, com.vega.middlebridge.swig.LVVEMetaType r24, java.lang.String r25, java.lang.String r26, java.lang.String r27, java.lang.String r28, int r29, java.lang.String r30, boolean r31, boolean r32, java.lang.String r33, int r34) {
        /*
            r1 = r22
            r5 = r33
            r2 = r34
            r8 = r30
            r18 = r28
            r14 = r27
            r11 = r26
            r17 = r29
            r9 = r25
            r12 = r24
            r0 = r2 & 4
            if (r0 == 0) goto L19
            r1 = 0
        L19:
            r0 = r2 & 8
            if (r0 == 0) goto L1f
            r23 = 0
        L1f:
            r0 = r2 & 16
            if (r0 == 0) goto L24
            r12 = 0
        L24:
            r0 = r2 & 32
            java.lang.String r6 = ""
            if (r0 == 0) goto L2b
            r9 = r6
        L2b:
            r0 = r2 & 64
            if (r0 == 0) goto L30
            r11 = r6
        L30:
            r0 = r2 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L35
            r14 = r6
        L35:
            r0 = r2 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L3b
            r18 = r6
        L3b:
            r0 = r2 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L41
            r17 = -1
        L41:
            r0 = r2 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L18d
            r10 = r6
        L46:
            r0 = r2 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L18a
            r7 = r6
        L4b:
            r0 = r2 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L50
            r8 = r6
        L50:
            r0 = r2 & 8192(0x2000, float:1.14794E-41)
            if (r0 == 0) goto L56
            r31 = 0
        L56:
            r0 = r2 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L5c
            r32 = 0
        L5c:
            r0 = 32768(0x8000, float:4.5918E-41)
            r2 = r2 & r0
            if (r2 == 0) goto L63
            r5 = r6
        L63:
            r3 = r19
            r3.getClass()
            r0 = r21
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r6)
            r0 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r6)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r6)
            r2 = r20
            if (r2 == 0) goto L91
            java.lang.String r4 = r2.getToneName()
            if (r4 != 0) goto L93
        L91:
            java.lang.String r4 = "none"
        L93:
            if (r1 != 0) goto L9a
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
        L9a:
            java.lang.String r0 = "tone"
            r1.put(r0, r4)
            if (r2 == 0) goto La7
            java.lang.String r4 = r2.getResourceId()
            if (r4 != 0) goto La8
        La7:
            r4 = r6
        La8:
            java.lang.String r0 = "tone_id"
            r1.put(r0, r4)
            if (r2 == 0) goto Lb5
            java.lang.String r4 = r2.getPlatform()
            if (r4 != 0) goto Lb6
        Lb5:
            r4 = r6
        Lb6:
            java.lang.String r0 = "platform"
            r1.put(r0, r4)
            if (r2 == 0) goto Lc3
            java.lang.String r4 = r2.getVipStatus()
            if (r4 != 0) goto Lc5
        Lc3:
            java.lang.String r4 = "free"
        Lc5:
            java.lang.String r0 = "right_status"
            r1.put(r0, r4)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r3.D
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r4 = java.lang.Boolean.TRUE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            java.lang.String r13 = "1"
            java.lang.String r16 = "0"
            if (r0 == 0) goto L186
            r15 = r13
        Ldd:
            java.lang.String r0 = "is_apply_all"
            r1.put(r0, r15)
            if (r31 == 0) goto L182
            r15 = r13
        Le5:
            java.lang.String r0 = "is_filter"
            r1.put(r0, r15)
            if (r32 == 0) goto L17e
            r15 = r13
        Led:
            java.lang.String r0 = "is_filter_commercial"
            r1.put(r0, r15)
            java.lang.String r0 = "type"
            r1.put(r0, r5)
            if (r2 == 0) goto Lff
            java.lang.String r0 = r2.getVoiceType()
            if (r0 != 0) goto L100
        Lff:
            r0 = r6
        L100:
            com.lemon.lv.config.ModelConfig r0 = r3.r7(r2, r0)
            if (r0 == 0) goto L10c
            java.lang.String r5 = r0.getVoiceType()
            if (r5 != 0) goto L10d
        L10c:
            r5 = r6
        L10d:
            java.lang.String r0 = "cloned_model_type"
            r1.put(r0, r5)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r3.D
            java.lang.Object r0 = r0.getValue()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            if (r0 == 0) goto L1ce
            com.vega.container.session.core.ISession r0 = r3.o
            com.vega.container.session.core.ISessionScene r5 = r0.C0()
            com.vega.editorapi.session.SessionScene r0 = com.vega.editorapi.session.SessionScene.f97670d
            if (r5 != r0) goto L1b9
            java.util.ArrayList<com.vega.edward.templatecore.editor.draft.CutSameData> r5 = r3.L0
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r6)
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r3.D
            java.lang.Object r0 = r0.getValue()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r4)
            if (r0 == 0) goto L1db
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            r4.<init>()
            java.util.Iterator r15 = r5.iterator()
        L142:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L190
            java.lang.Object r12 = r15.next()
            com.vega.edward.templatecore.editor.draft.CutSameData r12 = (com.vega.edward.templatecore.editor.draft.CutSameData) r12
            int r5 = r12.getMediaType()
            r0 = 2
            if (r5 != r0) goto L142
            boolean r0 = r12.getLock()
            if (r0 != 0) goto L142
            boolean r0 = r12.isTextBeenToAudio()
            if (r0 != 0) goto L162
            goto L142
        L162:
            com.vega.container.session.core.ISession r0 = r3.o
            com.vega.middlebridge.lyrasession.LyraSession r0 = r0.b()
            if (r0 == 0) goto L142
            com.vega.middlebridge.swig.IQueryUtils r5 = com.vega.middlebridge.client.DraftClient.p(r0)
            if (r5 == 0) goto L142
            java.lang.String r0 = r12.getSegmentId()
            com.vega.middlebridge.swig.Segment r0 = r5.m(r0)
            if (r0 == 0) goto L142
            r4.add(r0)
            goto L142
        L17e:
            r15 = r16
            goto Led
        L182:
            r15 = r16
            goto Le5
        L186:
            r15 = r16
            goto Ldd
        L18a:
            r7 = 0
            goto L4b
        L18d:
            r10 = 0
            goto L46
        L190:
            java.util.HashSet r12 = new java.util.HashSet
            r12.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r15 = r4.iterator()
        L19e:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L1dc
            java.lang.Object r4 = r15.next()
            r0 = r4
            com.vega.middlebridge.swig.Node r0 = (com.vega.middlebridge.swig.Node) r0
            java.lang.String r0 = r0.b()
            boolean r0 = r12.add(r0)
            if (r0 == 0) goto L19e
            r5.add(r4)
            goto L19e
        L1b9:
            java.util.List r0 = r3.f7(r12)
            if (r0 == 0) goto L1f0
            int r0 = r0.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r4 = r0.toString()
            if (r4 != 0) goto L1f2
            goto L1f0
        L1ce:
            r0 = 0
            java.lang.String r0 = r3.p7(r6, r0, r0)
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r0)
            if (r0 == 0) goto L1f0
            r4 = r13
            goto L1f2
        L1db:
            r5 = 0
        L1dc:
            java.util.List r0 = r3.T6(r5)
            if (r0 == 0) goto L1f0
            int r0 = r0.size()
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.String r4 = r0.toString()
            if (r4 != 0) goto L1f2
        L1f0:
            r4 = r16
        L1f2:
            java.lang.String r0 = "text_to_audio_cnt"
            r1.put(r0, r4)
            if (r2 == 0) goto L4d5
            java.util.List r4 = r2.getTags()
            if (r4 == 0) goto L4d5
            java.lang.String r0 = "cc4b"
            boolean r0 = r4.contains(r0)
        L205:
            r5 = 1
            if (r0 != 0) goto L210
            if (r2 == 0) goto L4d1
            boolean r0 = r2.isCommercial()
            if (r0 != r5) goto L4d1
        L210:
            r4 = r13
        L211:
            java.lang.String r0 = "is_commercial"
            r1.put(r0, r4)
            if (r2 == 0) goto L4cd
            boolean r0 = r2.isVip()
            if (r0 != r5) goto L4cd
            r4 = r13
        L21f:
            java.lang.String r0 = "is_vip"
            r1.put(r0, r4)
            if (r23 == 0) goto L22c
            java.lang.String r4 = r23.toString()
            if (r4 != 0) goto L22d
        L22c:
            r4 = r13
        L22d:
            java.lang.String r0 = "change_speed"
            r1.put(r0, r4)
            boolean r0 = r3.f8()
            if (r0 == 0) goto L4c9
            java.lang.String r4 = "custom_voice"
        L23a:
            java.lang.String r0 = "tone_subcategory"
            r1.put(r0, r4)
            if (r2 == 0) goto L247
            boolean r0 = r2.isAICloneTone()
            if (r0 != r5) goto L4c1
        L247:
            r4 = r6
        L248:
            java.lang.String r0 = "tone_thirdcategory"
            r1.put(r0, r4)
            if (r2 == 0) goto L255
            boolean r0 = r2.isAICloneTone()
            if (r0 != r5) goto L4b9
        L255:
            r4 = r6
        L256:
            java.lang.String r0 = "tone_thirdcategory_id"
            r1.put(r0, r4)
            java.lang.String r0 = "page_from"
            r1.put(r0, r11)
            int r0 = r10.length()
            if (r0 <= 0) goto L4ae
        L266:
            java.lang.String r4 = "tone_category"
            r1.put(r4, r10)
            if (r2 == 0) goto L273
            java.lang.String r10 = r2.getCategoryID()
            if (r10 != 0) goto L274
        L273:
            r10 = r6
        L274:
            java.lang.String r0 = "tone_category_id"
            r1.put(r0, r10)
            int r0 = r7.length()
            if (r0 <= 0) goto L4a6
        L27f:
            java.lang.String r0 = "is_business_filter"
            r1.put(r0, r7)
            java.lang.String r0 = "enter_from"
            r1.put(r0, r9)
            if (r2 == 0) goto L291
            java.lang.String r7 = r2.getRequestId()
            if (r7 != 0) goto L292
        L291:
            r7 = r6
        L292:
            java.lang.String r0 = "material_request_id"
            r1.put(r0, r7)
            if (r2 == 0) goto L4a3
            boolean r0 = r2.isAICloneTone()
            if (r0 != r5) goto L4a3
            r0 = 1
        L2a0:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = "is_cloned"
            r1.put(r0, r7)
            if (r2 == 0) goto L2b1
            java.lang.String r7 = r2.getSecondCategoryKey()
            if (r7 != 0) goto L2b2
        L2b1:
            r7 = r6
        L2b2:
            java.lang.String r0 = "tone_second_category"
            r1.put(r0, r7)
            if (r2 == 0) goto L2bf
            java.lang.String r7 = r2.getResourceId()
            if (r7 != 0) goto L2c0
        L2bf:
            r7 = r6
        L2c0:
            java.lang.String r0 = "resource_id"
            r1.put(r0, r7)
            java.lang.String r0 = "edit_type"
            r1.put(r0, r14)
            if (r2 == 0) goto L4a0
            boolean r0 = r2.isVop()
            if (r0 != r5) goto L4a0
            r0 = 1
        L2d3:
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)
            java.lang.String r0 = "is_ugc"
            r1.put(r0, r7)
            r7 = 45
            java.lang.String r0 = "rank"
            if (r2 == 0) goto L2ee
            r11 = -1
            r10 = r17
            if (r10 == r11) goto L472
            java.lang.Integer r10 = java.lang.Integer.valueOf(r17)
            r1.put(r0, r10)
        L2ee:
            int r10 = r18.length()
            if (r10 <= 0) goto L2fb
            java.lang.String r11 = "action"
            r10 = r18
            r1.put(r11, r10)
        L2fb:
            com.vega.edit.base.utils.RecommendCapabilityViewInfo$Companion r11 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.o
            com.vega.edit.base.utils.EditReportManager r10 = com.vega.edit.base.utils.EditReportManager.f88945a
            r10.getClass()
            com.vega.edit.base.utils.RecommendCapabilityViewInfo r10 = com.vega.edit.base.utils.EditReportManager.i1
            r11.getClass()
            java.util.Map r10 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.Companion.d(r10)
            r1.putAll(r10)
            java.lang.String r10 = "long_text_editor_homepage"
            boolean r10 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
            if (r10 != 0) goto L31e
            java.lang.String r10 = "long_text_editor_tone_detail_page"
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r10)
            if (r9 == 0) goto L32a
        L31e:
            com.vega.audio.textstart.TextStartReporter r9 = com.vega.audio.textstart.TextStartReporter.f73799a
            r9.getClass()
            java.lang.String r10 = com.vega.audio.textstart.TextStartReporter.b
            java.lang.String r9 = "long_text_editor_page_from"
            r1.put(r9, r10)
        L32a:
            int r9 = r8.length()
            if (r9 != 0) goto L332
            java.lang.String r8 = "default"
        L332:
            java.lang.String r9 = "style_id"
            r1.put(r9, r8)
            if (r2 == 0) goto L46d
            r8 = 0
            r10 = 0
            boolean r8 = com.lemon.lv.data.ToneType.isMultiEmotionTone$default(r2, r8, r5, r10)
            if (r8 == 0) goto L46e
        L341:
            java.lang.String r8 = "is_style"
            r1.put(r8, r13)
            if (r2 == 0) goto L35c
            java.util.List r8 = r2.getToneStyleTagList()
            if (r8 == 0) goto L35c
            java.lang.String r9 = ","
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2 r13 = new kotlin.jvm.functions.Function1<com.lemon.lv.data.ToneTypeTagInfo, java.lang.CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2) com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.lemon.lv.data.ToneTypeTagInfo r3) {
                    /*
                        r2 = this;
                        com.lemon.lv.data.ToneTypeTagInfo r3 = (com.lemon.lv.data.ToneTypeTagInfo) r3
                        java.lang.String r1 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r1)
                        java.lang.String r0 = r3.getName()
                        if (r0 == 0) goto Le
                        r1 = r0
                    Le:
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$reportTick$2.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r12 = 0
            r14 = 30
            r11 = r10
            java.lang.String r8 = kotlin.collections.CollectionsKt.j(r8, r9, r10, r11, r12, r13, r14)
            if (r8 != 0) goto L46a
        L35c:
            java.lang.String r8 = "tone_style_id"
            r1.put(r8, r6)
            if (r2 == 0) goto L466
            boolean r6 = r2.isAICloneTone()
            if (r6 != r5) goto L466
            com.lemon.lv.clipmonetize.wrapper.ResourceType r9 = com.lemon.lv.clipmonetize.wrapper.ResourceType.e
        L36b:
            com.vega.subscriptionapi.report.BusinessReportEvents r8 = com.vega.subscriptionapi.report.BusinessReportEvents.f131848a
            r14 = 30
            r10 = r10
            r11 = r10
            r12 = r10
            r13 = r10
            java.util.Map r6 = com.vega.subscriptionapi.report.BusinessReportEvents.o(r8, r9, r10, r11, r12, r13, r14)
            r1.putAll(r6)
            java.util.HashMap<java.lang.String, java.lang.Boolean> r8 = r3.Y0
            if (r2 == 0) goto L463
            java.lang.String r6 = r2.getVoiceType()
        L382:
            java.lang.Object r8 = r8.get(r6)
            java.lang.Boolean r6 = java.lang.Boolean.TRUE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r6)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r6)
            java.lang.String r6 = "is_audition"
            r1.put(r6, r8)
            if (r2 == 0) goto L460
            boolean r6 = r2.isRecentUsed()
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r6)
        L39f:
            java.lang.String r8 = com.vega.core.ext.ExtentionKt.getReportStr(r6)
            java.lang.String r6 = "is_recent_use"
            r1.put(r6, r8)
            com.vega.core.utils.RankReporter r9 = com.vega.core.utils.RankReporter.f79662a
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            com.vega.core.utils.RankReportType r6 = com.vega.core.utils.RankReportType.o
            r8.append(r6)
            r8.append(r7)
            if (r2 == 0) goto L3bd
            java.lang.String r10 = r2.getResourceId()
        L3bd:
            r8.append(r10)
            java.lang.String r6 = r8.toString()
            r9.getClass()
            java.util.Map r7 = com.vega.core.utils.RankReporter.c(r6)
            java.lang.String r9 = "keyword_source"
            java.lang.String r10 = "search_id"
            java.lang.String r11 = "search_keyword"
            if (r7 == 0) goto L40a
            java.lang.Object r6 = r7.get(r0)
            java.lang.String r6 = (java.lang.String) r6
            if (r6 == 0) goto L3e6
            int r6 = java.lang.Integer.parseInt(r6)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r1.put(r0, r6)
        L3e6:
            java.lang.Object r6 = r7.get(r10)
            if (r6 == 0) goto L3ef
            r1.put(r10, r6)
        L3ef:
            java.lang.Object r6 = r7.get(r11)
            if (r6 == 0) goto L3f8
            r1.put(r11, r6)
        L3f8:
            java.lang.Object r6 = r7.get(r9)
            if (r6 == 0) goto L401
            r1.put(r9, r6)
        L401:
            java.lang.Object r6 = r7.get(r4)
            if (r6 == 0) goto L40a
            r1.put(r4, r6)
        L40a:
            if (r2 == 0) goto L453
            com.vega.edit.base.draft.DraftExtraUtils r7 = com.vega.edit.base.draft.DraftExtraUtils.f87534a
            com.vega.container.session.core.ISession r6 = r3.o
            com.vega.edit.base.draft.DraftExtraDataType r4 = com.vega.edit.base.draft.DraftExtraDataType.u
            com.vega.edit.base.draft.DraftExtraUpdateItem[] r8 = new com.vega.edit.base.draft.DraftExtraUpdateItem[r5]
            com.vega.edit.base.draft.DraftExtraUpdateItem r5 = new com.vega.edit.base.draft.DraftExtraUpdateItem
            java.lang.String r2 = r2.getEffectId()
            r5.<init>(r2)
            java.lang.Object r2 = r1.get(r11)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r5.setQuery(r2)
            java.lang.Object r2 = r1.get(r10)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r5.setSearchId(r2)
            java.lang.Object r2 = r1.get(r9)
            java.lang.String r2 = java.lang.String.valueOf(r2)
            r5.setKeywordSource(r2)
            java.lang.Object r0 = r1.get(r0)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r5.setRank(r0)
            r0 = 0
            r8[r0] = r5
            java.util.ArrayList r0 = kotlin.collections.CollectionsKt__CollectionsKt.arrayListOf(r8)
            com.vega.edit.base.draft.DraftExtraUtils.f(r7, r6, r4, r0)
        L453:
            com.vega.report.ReportManagerWrapper r2 = com.vega.report.ReportManagerWrapper.INSTANCE
            java.lang.String r0 = "click_text_to_audio_change_tone"
            r2.onEvent(r0, r1)
            java.util.HashMap<java.lang.String, java.lang.Boolean> r0 = r3.Y0
            r0.clear()
            return
        L460:
            r6 = r10
            goto L39f
        L463:
            r6 = r10
            goto L382
        L466:
            com.lemon.lv.clipmonetize.wrapper.ResourceType r9 = com.lemon.lv.clipmonetize.wrapper.ResourceType.h
            goto L36b
        L46a:
            r6 = r8
            goto L35c
        L46d:
            r10 = 0
        L46e:
            r13 = r16
            goto L341
        L472:
            com.vega.core.utils.RankReporter r12 = com.vega.core.utils.RankReporter.f79662a
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            com.vega.core.utils.RankReportType r10 = com.vega.core.utils.RankReportType.o
            r11.append(r10)
            r11.append(r7)
            java.lang.String r10 = r2.getCategoryID()
            r11.append(r10)
            java.lang.String r11 = r11.toString()
            java.lang.String r10 = r2.getEffectId()
            r12.getClass()
            int r10 = com.vega.core.utils.RankReporter.e(r11, r10)
            java.lang.String r10 = java.lang.String.valueOf(r10)
            r1.put(r0, r10)
            goto L2ee
        L4a0:
            r0 = 0
            goto L2d3
        L4a3:
            r0 = 0
            goto L2a0
        L4a6:
            com.utils.BusinessFilterUtil r0 = com.utils.BusinessFilterUtil.f65451a
            java.lang.String r7 = r0.e()
            goto L27f
        L4ae:
            if (r2 == 0) goto L4b6
            java.lang.String r10 = r2.getCategoryKey()
            if (r10 != 0) goto L266
        L4b6:
            r10 = r6
            goto L266
        L4b9:
            java.lang.String r4 = r2.getCategoryID()
            if (r4 != 0) goto L256
            goto L255
        L4c1:
            java.lang.String r4 = r2.getCategoryName()
            if (r4 != 0) goto L248
            goto L247
        L4c9:
            java.lang.String r4 = "text_to_speech"
            goto L23a
        L4cd:
            r4 = r16
            goto L21f
        L4d1:
            r4 = r16
            goto L211
        L4d5:
            r0 = 0
            goto L205
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.N7(com.vega.audio.tone.viewmodel.ToneSelectViewModel, com.lemon.lv.data.ToneType, java.lang.String, java.util.HashMap, java.lang.Float, com.vega.middlebridge.swig.LVVEMetaType, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, boolean, boolean, java.lang.String, int):void");
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.infrastructure.extensions.ThreadUtilKt.f(kotlin.jvm.functions.Function0):void */
    /* JADX DEBUG: Class process forced to load method for inline: com.vega.subscriptionapi.biz.function.ICloneToneBusiness.DefaultImpls.a(com.vega.subscriptionapi.biz.function.ICloneToneBusiness, com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene, java.util.List, java.lang.String, boolean, boolean, java.util.Map, boolean, kotlin.jvm.functions.Function2, int):void */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:236:0x027b */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0257  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x0356 A[ADDED_TO_REGION] */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v6, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v7, types: [java.util.List<java.lang.String>] */
    /* JADX WARN: Type inference failed for: r3v8, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v9, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void T7(final com.vega.audio.tone.viewmodel.ToneSelectViewModel r69, boolean r70, final com.vega.edit.base.audio.tone.IProgressDialogController r71, final java.lang.String r72, final java.lang.String r73, java.lang.String r74, final java.lang.String r75, java.util.List r76, float r77, int r78, java.lang.String r79, java.lang.String r80, java.lang.String r81, boolean r82, com.vega.middlebridge.swig.LVVEMetaType r83, java.util.List r84, java.lang.String r85, java.lang.String r86, boolean r87, java.lang.String r88, boolean r89, boolean r90, java.lang.String r91, java.lang.String r92, long r93, com.vega.theme.textpanel.ThemeType r95, java.lang.String r96, java.lang.String r97, boolean r98, java.lang.String r99, boolean r100, com.vega.edit.base.utils.RecommendCapabilityViewInfo r101, java.lang.Boolean r102, boolean r103, com.lemon.lv.data.ToneType r104, java.util.Map r105, boolean r106, kotlin.jvm.functions.Function2 r107, kotlin.jvm.functions.Function0 r108, kotlin.jvm.functions.Function1 r109, kotlin.jvm.functions.Function2 r110, int r111, int r112) {
        /*
            r10 = r110
            r1 = r112
            r8 = r109
            r68 = r107
            r38 = r106
            r61 = r105
            r6 = r86
            r64 = r93
            r24 = r82
            r3 = r84
            r31 = r78
            r32 = r79
            r27 = r81
            r52 = r77
            r13 = r76
            r28 = r80
            r63 = r74
            r35 = r103
            r36 = r90
            r18 = r83
            r0 = r111
            r20 = r92
            r21 = r70
            r62 = r87
            r57 = r88
            r25 = r85
            r19 = r89
            r37 = r108
            r23 = r91
            r22 = r95
            r5 = r99
            r26 = r97
            r58 = r100
            r59 = r101
            r7 = r104
            r34 = r102
            r2 = r0 & 1
            if (r2 == 0) goto L4e
            r21 = 0
        L4e:
            r4 = r0 & 16
            java.lang.String r2 = ""
            if (r4 == 0) goto L56
            r63 = r2
        L56:
            r14 = 0
            r4 = r0 & 512(0x200, float:7.175E-43)
            if (r4 == 0) goto L265
            r51 = 1
        L5d:
            r9 = 0
            r4 = r0 & 4096(0x1000, float:5.74E-42)
            if (r4 == 0) goto L63
            r13 = 0
        L63:
            r4 = r0 & 8192(0x2000, float:1.14794E-41)
            if (r4 == 0) goto L69
            r52 = 1065353216(0x3f800000, float:1.0)
        L69:
            r4 = r0 & 16384(0x4000, float:2.2959E-41)
            if (r4 == 0) goto L6f
            r31 = 24000(0x5dc0, float:3.3631E-41)
        L6f:
            r4 = 32768(0x8000, float:4.5918E-41)
            r4 = r4 & r0
            if (r4 == 0) goto L77
            r32 = r2
        L77:
            r4 = 65536(0x10000, float:9.18355E-41)
            r4 = r4 & r0
            if (r4 == 0) goto L7e
            r28 = r2
        L7e:
            r4 = 131072(0x20000, float:1.83671E-40)
            r4 = r4 & r0
            if (r4 == 0) goto L85
            r27 = r2
        L85:
            r4 = 262144(0x40000, float:3.67342E-40)
            r4 = r4 & r0
            if (r4 == 0) goto L8c
            r24 = 0
        L8c:
            r4 = 524288(0x80000, float:7.34684E-40)
            r4 = r4 & r0
            if (r4 == 0) goto L93
            r18 = 0
        L93:
            r4 = 1048576(0x100000, float:1.469368E-39)
            r4 = r4 & r0
            if (r4 == 0) goto L99
            r3 = 0
        L99:
            r4 = 2097152(0x200000, float:2.938736E-39)
            r4 = r4 & r0
            if (r4 == 0) goto La0
            r25 = r2
        La0:
            r4 = 4194304(0x400000, float:5.877472E-39)
            r4 = r4 & r0
            if (r4 == 0) goto La7
            java.lang.String r6 = "text"
        La7:
            r4 = 8388608(0x800000, float:1.17549435E-38)
            r4 = r4 & r0
            if (r4 == 0) goto Lae
            r62 = 0
        Lae:
            r4 = 16777216(0x1000000, float:2.3509887E-38)
            r4 = r4 & r0
            if (r4 == 0) goto Lb5
            r57 = r2
        Lb5:
            r4 = 33554432(0x2000000, float:9.403955E-38)
            r4 = r4 & r0
            if (r4 == 0) goto Lbc
            r19 = 0
        Lbc:
            r4 = 67108864(0x4000000, float:1.5046328E-36)
            r4 = r4 & r0
            if (r4 == 0) goto Lc3
            r36 = 0
        Lc3:
            r4 = 134217728(0x8000000, float:3.85186E-34)
            r4 = r4 & r0
            if (r4 == 0) goto Lca
            r23 = r2
        Lca:
            r4 = 268435456(0x10000000, float:2.5243549E-29)
            r4 = r4 & r0
            if (r4 == 0) goto Ld1
            r20 = r2
        Ld1:
            r4 = 536870912(0x20000000, float:1.0842022E-19)
            r4 = r4 & r0
            if (r4 == 0) goto L261
            r44 = 1
        Ld8:
            r4 = 1073741824(0x40000000, float:2.0)
            r0 = r0 & r4
            if (r0 == 0) goto Ldf
            r64 = 0
        Ldf:
            r0 = r1 & 1
            if (r0 == 0) goto Le5
            r22 = 0
        Le5:
            r0 = r1 & 2
            if (r0 == 0) goto Leb
            r96 = 0
        Leb:
            r0 = r1 & 4
            if (r0 == 0) goto Lf1
            r26 = 0
        Lf1:
            r0 = r1 & 8
            if (r0 == 0) goto Lf7
            r98 = 0
        Lf7:
            r0 = r1 & 32
            if (r0 == 0) goto Lfc
            r5 = 0
        Lfc:
            r0 = r1 & 64
            if (r0 == 0) goto L102
            r58 = 0
        L102:
            r0 = r1 & 128(0x80, float:1.794E-43)
            if (r0 == 0) goto L108
            r59 = 0
        L108:
            r0 = r1 & 256(0x100, float:3.59E-43)
            if (r0 == 0) goto L10e
            r34 = 0
        L10e:
            r0 = r1 & 512(0x200, float:7.175E-43)
            if (r0 == 0) goto L114
            r35 = 0
        L114:
            r0 = r1 & 1024(0x400, float:1.435E-42)
            if (r0 == 0) goto L119
            r7 = 0
        L119:
            r0 = r1 & 2048(0x800, float:2.87E-42)
            if (r0 == 0) goto L11f
            r61 = 0
        L11f:
            r0 = r1 & 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L125
            r38 = 0
        L125:
            r0 = r1 & 8192(0x2000, float:1.14794E-41)
            if (r0 == 0) goto L12b
            r68 = 0
        L12b:
            r0 = r1 & 16384(0x4000, float:2.2959E-41)
            if (r0 == 0) goto L25c
            r0 = 32768(0x8000, float:4.5918E-41)
            r37 = 0
        L134:
            r0 = r0 & r1
            if (r0 == 0) goto L138
            r8 = 0
        L138:
            r0 = 65536(0x10000, float:9.18355E-41)
            r1 = r1 & r0
            if (r1 == 0) goto L13e
            r10 = 0
        L13e:
            r1 = r69
            r1.getClass()
            r15 = r71
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r15, r2)
            r46 = r72
            r0 = r46
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r30 = r73
            r0 = r30
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r63
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r47 = r75
            r0 = r47
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r32
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r28
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r27
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r25
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
            r0 = r57
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r23
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            r0 = r20
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            if (r5 != 0) goto L192
            androidx.lifecycle.MutableLiveData<java.lang.String> r0 = r1.z
            java.lang.Object r5 = r0.getValue()
            java.lang.String r5 = (java.lang.String) r5
        L192:
            if (r7 != 0) goto L19c
            androidx.lifecycle.MutableLiveData<com.lemon.lv.data.ToneType> r0 = r1.F
            java.lang.Object r7 = r0.getValue()
            com.lemon.lv.data.ToneType r7 = (com.lemon.lv.data.ToneType) r7
        L19c:
            java.util.HashMap<java.lang.String, com.lemon.lv.data.Emotion> r0 = r1.X0
            java.lang.Object r4 = r0.get(r5)
            com.lemon.lv.data.Emotion r4 = (com.lemon.lv.data.Emotion) r4
            if (r4 != 0) goto L1ac
            if (r7 == 0) goto L259
            com.lemon.lv.data.Emotion r4 = r7.getDefaultEmotion()
        L1ac:
            com.vega.edit.base.tone.EmotionOption$Companion r0 = com.vega.edit.base.tone.EmotionOption.h
            r0.getClass()
            com.vega.edit.base.tone.EmotionOption r33 = com.vega.edit.base.tone.EmotionOption.Companion.a(r4)
            if (r13 == 0) goto L269
            boolean r0 = r13.isEmpty()
            r11 = 1
            r0 = r0 ^ 1
            if (r0 != r11) goto L269
            java.util.ArrayList r3 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r13, r0)
            r3.<init>(r0)
            java.util.Iterator r17 = r13.iterator()
        L1cf:
            boolean r0 = r17.hasNext()
            if (r0 == 0) goto L27b
            java.lang.Object r0 = r17.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            boolean r11 = r0 instanceof com.vega.middlebridge.swig.SegmentText
            if (r11 == 0) goto L1e9
            com.vega.middlebridge.swig.SegmentText r0 = (com.vega.middlebridge.swig.SegmentText) r0
            java.lang.String r0 = com.vega.ve.utils.DraftExpandKt.v(r0)
        L1e5:
            r3.add(r0)
            goto L1cf
        L1e9:
            boolean r11 = r0 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            if (r11 == 0) goto L24c
            boolean r11 = com.vega.ve.expand.UnifyTextExpandKt.G(r0)
            if (r11 == 0) goto L1f8
            java.lang.String r0 = com.vega.ve.expand.UnifyTextExpandKt.y(r0)
            goto L1e5
        L1f8:
            com.vega.middlebridge.swig.SegmentTextTemplate r0 = (com.vega.middlebridge.swig.SegmentTextTemplate) r0
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r0.p()
            com.vega.middlebridge.swig.VectorOfTextBindEffectInfo r11 = r0.q()
            boolean r0 = r11.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L257
            java.util.ArrayList r12 = new java.util.ArrayList
            r12.<init>()
            java.util.Iterator r16 = r11.iterator()
        L213:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L237
            java.lang.Object r0 = r16.next()
            r11 = r0
            com.vega.middlebridge.swig.TextBindEffectInfo r11 = (com.vega.middlebridge.swig.TextBindEffectInfo) r11
            com.vega.middlebridge.swig.MaterialText r11 = r11.i()
            java.lang.String r11 = r11.a0()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r2)
            boolean r11 = kotlin.text.StringsKt__StringsKt.isBlank(r11)
            r11 = r11 ^ 1
            if (r11 == 0) goto L213
            r12.add(r0)
            goto L213
        L237:
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3 r74 = new kotlin.jvm.functions.Function1<com.vega.middlebridge.swig.TextBindEffectInfo, java.lang.CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3) com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.vega.middlebridge.swig.TextBindEffectInfo r3) {
                    /*
                        r2 = this;
                        com.vega.middlebridge.swig.TextBindEffectInfo r3 = (com.vega.middlebridge.swig.TextBindEffectInfo) r3
                        com.vega.middlebridge.swig.MaterialText r0 = r3.i()
                        java.lang.String r1 = r0.a0()
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingTextForSegment$3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r75 = 31
            r69 = r12
            r70 = r9
            r71 = r9
            r72 = r9
            r73 = r14
            java.lang.String r0 = kotlin.collections.CollectionsKt.j(r69, r70, r71, r72, r73, r74, r75)
            if (r0 == 0) goto L257
            goto L1e5
        L24c:
            boolean r11 = r0 instanceof com.vega.middlebridge.swig.SegmentVideo
            if (r11 == 0) goto L257
            com.vega.middlebridge.swig.SegmentVideo r0 = (com.vega.middlebridge.swig.SegmentVideo) r0
            java.lang.String r0 = com.vega.edit.base.action.DraftExKt.b(r0)
            goto L1e5
        L257:
            r0 = r2
            goto L1e5
        L259:
            r4 = 0
            goto L1ac
        L25c:
            r0 = 32768(0x8000, float:4.5918E-41)
            goto L134
        L261:
            r44 = 0
            goto Ld8
        L265:
            r51 = 0
            goto L5d
        L269:
            if (r3 == 0) goto L271
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L27b
        L271:
            int r0 = r63.length()
            if (r0 <= 0) goto L3ce
            java.util.List r3 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r63)
        L27b:
            if (r5 == 0) goto L283
            int r0 = r5.length()
            if (r0 != 0) goto L2b4
        L283:
            if (r5 == 0) goto L28b
            int r0 = r5.length()
            if (r0 != 0) goto L2b1
        L28b:
            r1 = -1000(0xfffffffffffffc18, float:NaN)
        L28d:
            if (r8 == 0) goto L297
            com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult r0 = new com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult
            r0.<init>(r1, r9, r9)
            r8.invoke(r0)
        L297:
            if (r10 == 0) goto L2aa
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            com.vega.edit.base.audio.tone.TextToAudioInfoPack r0 = new com.vega.edit.base.audio.tone.TextToAudioInfoPack
            r7 = 63
            r2 = r0
            r3 = r9
            r4 = r9
            r5 = r9
            r6 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            r10.invoke(r1, r0)
        L2aa:
            com.vega.audio.tone.TextToAudioManager r1 = com.vega.audio.tone.TextToAudioManager.f73896a
            r0 = -4
            com.vega.audio.tone.TextToAudioManager.c(r1, r0)
        L2b0:
            return
        L2b1:
            r1 = -1100(0xfffffffffffffbb4, float:NaN)
            goto L28d
        L2b4:
            if (r3 == 0) goto L283
            boolean r0 = r3.isEmpty()
            if (r0 == 0) goto L2bd
            goto L283
        L2bd:
            com.vega.infrastructure.base.ModuleCommon r0 = com.vega.infrastructure.base.ModuleCommon.INSTANCE
            android.app.Application r0 = r0.getApplication()
            boolean r0 = com.bytedance.apm.util.NetUtils.b(r0)
            if (r0 != 0) goto L2f5
            if (r8 == 0) goto L2d5
            com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult r1 = new com.vega.audio.tone.TextToAudioManager$TextToAudioInfoResult
            r0 = -1200(0xfffffffffffffb50, float:NaN)
            r1.<init>(r0, r9, r9)
            r8.invoke(r1)
        L2d5:
            if (r10 == 0) goto L2e9
            java.lang.Boolean r1 = java.lang.Boolean.FALSE
            com.vega.edit.base.audio.tone.TextToAudioInfoPack r0 = new com.vega.edit.base.audio.tone.TextToAudioInfoPack
            r7 = 63
            r2 = r0
            r3 = r9
            r4 = r9
            r5 = r9
            r6 = r9
            r2.<init>(r3, r4, r5, r6, r7)
            r10.invoke(r1, r0)
            goto L2b0
        L2e9:
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1 r0 = new kotlin.jvm.functions.Function0<kotlin.Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1) com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 0
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1.<init>():void");
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final kotlin.Unit invoke() {
                    /*
                        r6 = this;
                        r0 = 2131897356(0x7f122c0c, float:1.94296E38)
                        r1 = 0
                        r5 = 252(0xfc, float:3.53E-43)
                        r2 = r1
                        r3 = r1
                        r4 = r1
                        com.vega.util.ToastUtilKt.d(r0, r1, r2, r3, r4, r5)
                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$1.invoke():java.lang.Object");
                }
            }
            com.vega.infrastructure.extensions.ThreadUtilKt.f(r0)
            com.vega.audio.tone.TextToAudioManager r1 = com.vega.audio.tone.TextToAudioManager.f73896a
            r0 = -5
            com.vega.audio.tone.TextToAudioManager.c(r1, r0)
            goto L2b0
        L2f5:
            kotlin.jvm.internal.Ref$IntRef r2 = new kotlin.jvm.internal.Ref$IntRef
            r2.<init>()
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$realSaveAudio$1 r17 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$realSaveAudio$1
            r69 = 0
            r11 = r17
            r29 = r5
            r30 = r30
            r32 = r32
            r39 = r15
            r40 = r8
            r41 = r10
            r42 = r9
            r43 = r18
            r45 = r13
            r46 = r46
            r47 = r47
            r48 = r14
            r49 = r14
            r50 = r14
            r53 = r27
            r54 = r25
            r55 = r20
            r56 = r23
            r57 = r57
            r60 = r4
            r63 = r63
            r66 = r9
            r67 = r2
            r18 = r1
            r20 = r0
            r23 = r6
            r25 = r3
            r27 = r28
            r28 = r7
            r17.<init>()
            if (r19 == 0) goto L3c9
            com.vega.subscriptionapi.biz.function.ICloneToneBusiness r4 = r1.U0
            java.lang.String r5 = "ai_avatar_edit"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r5)
            java.lang.String r5 = "audio_module"
            if (r7 == 0) goto L37f
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.f131713c
        L352:
            r72 = 1
        L354:
            if (r21 == 0) goto L35a
            if (r98 == 0) goto L35a
            r69 = 1
        L35a:
            com.vega.edit.base.utils.RecommendCapabilityViewInfo$Companion r1 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.o
            r1.getClass()
            java.util.Map r71 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.Companion.d(r59)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r5)
            r72 = r72 ^ r1
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$2 r1 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$2
            r1.<init>()
            r74 = 32
            r65 = r4
            r67 = r3
            r68 = r46
            r70 = r14
            r73 = r1
            com.vega.subscriptionapi.biz.function.ICloneToneBusiness.DefaultImpls.a(r65, r66, r67, r68, r69, r70, r71, r72, r73, r74)
            goto L2b0
        L37f:
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r5)
            if (r7 == 0) goto L388
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.f131714d
            goto L352
        L388:
            java.lang.String r7 = "from_ai_writer"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r7 == 0) goto L3a0
            if (r96 == 0) goto L39d
            int r7 = r96.length()
            if (r7 <= 0) goto L3a3
            r72 = 1
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.f131713c
            goto L354
        L39d:
            r72 = 1
            goto L3a5
        L3a0:
            r72 = 1
            goto L3a5
        L3a3:
            r72 = 1
        L3a5:
            java.lang.String r7 = "lip_sync"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r7 == 0) goto L3b0
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.f
            goto L354
        L3b0:
            java.lang.String r7 = "long_text_editor"
            boolean r7 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r7)
            if (r7 == 0) goto L3bb
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.e
            goto L354
        L3bb:
            int r7 = r1.e0
            r1 = 4
            if (r7 == r1) goto L3c3
            r1 = 6
            if (r7 != r1) goto L3c6
        L3c3:
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.b
            goto L354
        L3c6:
            com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction$Scene r66 = com.vega.subscriptionapi.biz.function.IVoiceCloneApplyFunction.Scene.f131712a
            goto L354
        L3c9:
            r17.invoke()
            goto L2b0
        L3ce:
            int r3 = r1.i1
            r0 = 1
            if (r3 != r0) goto L3d7
            java.util.List<java.lang.String> r3 = r1.h1
            goto L27b
        L3d7:
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r0 = r1.D
            java.lang.Object r3 = r0.getValue()
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r0)
            if (r0 == 0) goto L3ed
            r0 = r18
            java.util.List r3 = r1.f7(r0)
            goto L27b
        L3ed:
            java.lang.String r0 = r1.p7(r2, r14, r14)
            if (r0 == 0) goto L3f9
            java.util.List r3 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r0)
            goto L27b
        L3f9:
            r3 = 0
            goto L27b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.T7(com.vega.audio.tone.viewmodel.ToneSelectViewModel, boolean, com.vega.edit.base.audio.tone.IProgressDialogController, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.util.List, float, int, java.lang.String, java.lang.String, java.lang.String, boolean, com.vega.middlebridge.swig.LVVEMetaType, java.util.List, java.lang.String, java.lang.String, boolean, java.lang.String, boolean, boolean, java.lang.String, java.lang.String, long, com.vega.theme.textpanel.ThemeType, java.lang.String, java.lang.String, boolean, java.lang.String, boolean, com.vega.edit.base.utils.RecommendCapabilityViewInfo, java.lang.Boolean, boolean, com.lemon.lv.data.ToneType, java.util.Map, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function2, int, int):void");
    }

    public static Segment V6(String str, Draft draft) {
        Object obj = null;
        if (str == null) {
            return null;
        }
        VectorOfTrack vectorOfTrackV = draft.v();
        ArrayList arrayList = new ArrayList();
        Iterator<Track> it = vectorOfTrackV.iterator();
        while (it.hasNext()) {
            Track next = it.next();
            if (next.f() == LVVETrackType.TrackTypeAudio) {
                arrayList.add(next);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            VectorOfSegment vectorOfSegmentE = ((Track) it2.next()).e();
            Intrinsics.checkNotNullExpressionValue(vectorOfSegmentE, "");
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, vectorOfSegmentE);
        }
        Iterator it3 = arrayList2.iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            Object next2 = it3.next();
            if (Intrinsics.areEqual(((Node) next2).b(), str)) {
                obj = next2;
                break;
            }
        }
        return (Segment) obj;
    }

    public static Integer W6(ToneSelectViewModel toneSelectViewModel, List list, long j, long j2, VectorOfString vectorOfString) {
        IQueryUtils iQueryUtilsP;
        VectorOfLVVETrackType vectorOfLVVETrackType = new VectorOfLVVETrackType();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            vectorOfLVVETrackType.a((LVVETrackType) it.next());
        }
        LyraSession lyraSessionB = toneSelectViewModel.o.b();
        if (lyraSessionB == null || (iQueryUtilsP = DraftClient.p(lyraSessionB)) == null) {
            return null;
        }
        return Integer.valueOf(QueryUtilsModuleJNI.IQueryUtils_find_target_track_index__SWIG_0(iQueryUtilsP.f118845a, iQueryUtilsP, VectorOfLVVETrackType.b(vectorOfLVVETrackType), vectorOfLVVETrackType, j, j2, 0, VectorOfString.c(vectorOfString), vectorOfString));
    }

    public static Set X6(ToneSelectViewModel toneSelectViewModel, LVVEMetaType lVVEMetaType) {
        SegmentTextTemplate segmentTextTemplate;
        MaterialTextTemplate materialTextTemplateP;
        List<Segment> listY6 = toneSelectViewModel.Y6(lVVEMetaType);
        if (listY6 == null || listY6.isEmpty()) {
            return SetsKt__SetsKt.emptySet();
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Segment segment : listY6) {
            if (segment instanceof SegmentText) {
                if (DraftExpandKt.K(segment)) {
                    linkedHashSet.addAll(new VectorOfString());
                } else {
                    SegmentText segmentText = (SegmentText) segment;
                    if (segmentText != null) {
                        linkedHashSet.addAll(DraftExpandKt.w(segmentText));
                    }
                }
            } else if ((segment instanceof SegmentTextTemplate) && (segmentTextTemplate = (SegmentTextTemplate) segment) != null && (materialTextTemplateP = segmentTextTemplate.p()) != null) {
                VectorOfString vectorOfStringS = materialTextTemplateP.s();
                Intrinsics.checkNotNull(vectorOfStringS);
                linkedHashSet.addAll(vectorOfStringS);
            }
        }
        return CollectionsKt___CollectionsKt.toSet(CollectionsKt___CollectionsKt.filterNotNull(linkedHashSet));
    }

    private final int Z6() {
        return ((Number) this.e1.getValue()).intValue();
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.effectplatform.utils.ReportEditSessionManager.i(com.vega.effectplatform.utils.ReportEditSessionManager):org.json.JSONObject */
    public static void a7(ToneSelectViewModel toneSelectViewModel, boolean z, Integer num) throws JSONException {
        String first;
        Long longOrNull;
        EffectPanel effectPanel = EffectPanel.B;
        toneSelectViewModel.getClass();
        Intrinsics.checkNotNullParameter(effectPanel, "");
        Pair<String, EffectByIdParams> pairU7 = toneSelectViewModel.u7(null);
        JSONObject jSONObjectI = ReportEditSessionManager.i(ReportEditSessionManager.f98584a);
        if (jSONObjectI == null) {
            jSONObjectI = new JSONObject();
        }
        jSONObjectI.put("text", toneSelectViewModel.w7());
        boolean z2 = !Intrinsics.areEqual(toneSelectViewModel.w7(), toneSelectViewModel.c1);
        BaseTabViewModel.F6(toneSelectViewModel, (pairU7 == null || (first = pairU7.getFirst()) == null || (longOrNull = StringsKt__StringNumberConversionsKt.toLongOrNull(first)) == null) ? -1L : longOrNull.longValue(), z2, Integer.valueOf(num != null ? num.intValue() : 50), z ? new PackOptional(false, false, false, true, true, false, true, new ArrayList(), null, null, null, false, false) : null, pairU7 != null ? pairU7.getSecond() : null, z2, jSONObjectI);
    }

    public static void c8(final ToneSelectViewModel toneSelectViewModel, final Context context, boolean z, String str, boolean z2, int i) {
        PagedEffectListState<Effect> value;
        List<Effect> list;
        PagedEffectListState<Effect> value2;
        List<Effect> list2;
        final boolean z3 = z2;
        final String str2 = str;
        final boolean z4 = z;
        if ((i & 2) != 0) {
            z4 = false;
        }
        if ((i & 4) != 0) {
            str2 = "";
        }
        if ((i & 8) != 0) {
            z3 = false;
        }
        toneSelectViewModel.getClass();
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(str2, "");
        toneSelectViewModel.n0 = z3;
        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneLegalGuide$checkBindPhoneAndShowPanel$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                FragmentActivity fragmentActivityC = ContextExKt.c(context);
                if (fragmentActivityC == null) {
                    return null;
                }
                final ToneSelectViewModel toneSelectViewModel2 = toneSelectViewModel;
                final Context context2 = context;
                final boolean z5 = z4;
                final String str3 = str2;
                final boolean z6 = z3;
                CloneToneLoginHelper cloneToneLoginHelper = CloneToneLoginHelper.f73953a;
                LifecycleCoroutineScopeImpl lifecycleCoroutineScopeImplA = LifecycleOwnerKt.a(fragmentActivityC);
                Function2<Boolean, Boolean, Unit> function2 = new Function2<Boolean, Boolean, Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneLegalGuide$checkBindPhoneAndShowPanel$1$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(Boolean bool, Boolean bool2) {
                        boolean zBooleanValue = bool.booleanValue();
                        boolean zBooleanValue2 = bool2.booleanValue();
                        if (zBooleanValue && zBooleanValue2) {
                            final ToneSelectViewModel toneSelectViewModel3 = toneSelectViewModel2;
                            final Context context3 = context2;
                            final boolean z7 = z5;
                            final String str4 = str3;
                            final boolean z8 = z6;
                            toneSelectViewModel3.getClass();
                            CloneToneUtils.f73967a.getClass();
                            if (CloneToneUtils.n(z8)) {
                                AudioChoicePanel audioChoicePanel = new AudioChoicePanel(context3, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showAudioChoicePanel$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        toneSelectViewModel3.z7(context3, str4, z7, z8);
                                        CloneToneStatistics cloneToneStatistics = CloneToneStatistics.f74107a;
                                        String strL7 = toneSelectViewModel3.l7();
                                        String strD7 = toneSelectViewModel3.d7();
                                        cloneToneStatistics.getClass();
                                        CloneToneStatistics.s("click_record", strL7, strD7);
                                        return Unit.INSTANCE;
                                    }
                                }, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showAudioChoicePanel$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        final ToneSelectViewModel toneSelectViewModel4 = toneSelectViewModel3;
                                        final Context context4 = context3;
                                        toneSelectViewModel4.getClass();
                                        Utils.f102492a.getClass();
                                        if (Utils.m()) {
                                            SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context4, "//videos/feedbackmusic");
                                            CloneToneUtils cloneToneUtils = CloneToneUtils.f73967a;
                                            boolean z9 = toneSelectViewModel4.n0;
                                            cloneToneUtils.getClass();
                                            smartRouteBuildRoute.withParam("min_duration", z9 ? ((Number) CloneToneUtils.z.getValue()).intValue() : CloneToneUtils.q(false) * 1000);
                                            smartRouteBuildRoute.withParam("extract_text", ModuleCommonKt.b(R.string.p5z));
                                            smartRouteBuildRoute.withParam("need_show_tips", false);
                                            smartRouteBuildRoute.open();
                                            ExtractGalleryMusicActivity.Companion companion = ExtractGalleryMusicActivity.F;
                                            Function2<Activity, MediaData, Unit> function22 = new Function2<Activity, MediaData, Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$goToExtract$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(2);
                                                }

                                                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                                                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                                @Override // kotlin.jvm.functions.Function2
                                                public final Unit invoke(Activity activity, MediaData mediaData) {
                                                    Activity activity2 = activity;
                                                    MediaData mediaData2 = mediaData;
                                                    Intrinsics.checkNotNullParameter(activity2, "");
                                                    CloneToneStatistics cloneToneStatistics = CloneToneStatistics.f74107a;
                                                    CloneToneStatistics.GenerateFrom generateFrom = CloneToneStatistics.GenerateFrom.f74117c;
                                                    cloneToneStatistics.getClass();
                                                    Intrinsics.checkNotNullParameter(generateFrom, "");
                                                    CloneToneStatistics.e = generateFrom;
                                                    ToneSelectViewModel toneSelectViewModel5 = toneSelectViewModel4;
                                                    Context context5 = context4;
                                                    toneSelectViewModel5.getClass();
                                                    if (mediaData2 == null || mediaData2.getPath().length() == 0) {
                                                        activity2.finish();
                                                    } else {
                                                        LoadingDialog loadingDialog = new LoadingDialog(activity2);
                                                        loadingDialog.setCancelable(false);
                                                        if (!new HeliosApiHook().preInvoke(300000, "com/vega/ui/LoadingDialog", "show", loadingDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSVEIlTB3Kcu5WjxBEU3Wd5nJXHhc/oZqix0EXGS+qIkjR7G7Pt73YUw==")).isIntercept()) {
                                                            loadingDialog.show();
                                                        }
                                                        BuildersKt__Builders_commonKt.launch$default(toneSelectViewModel5, Dispatchers.getMain(), null, new ToneSelectViewModel$extractAudio$1(activity2, mediaData2, toneSelectViewModel5, context5, loadingDialog, null), 2, null);
                                                    }
                                                    return Unit.INSTANCE;
                                                }
                                            };
                                            companion.getClass();
                                            ExtractGalleryMusicActivity.H = function22;
                                        } else {
                                            ToastUtilKt.d(R.string.qmu, 0, 0, 0, 0, 254);
                                        }
                                        CloneToneStatistics cloneToneStatistics = CloneToneStatistics.f74107a;
                                        String strL7 = toneSelectViewModel3.l7();
                                        String strD7 = toneSelectViewModel3.d7();
                                        cloneToneStatistics.getClass();
                                        CloneToneStatistics.s("click_extract_video", strL7, strD7);
                                        return Unit.INSTANCE;
                                    }
                                }, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showAudioChoicePanel$3
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: android.content.Context */
                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Unit invoke() {
                                        IActivityForResult iActivityForResult;
                                        final ToneSelectViewModel toneSelectViewModel4 = toneSelectViewModel3;
                                        final Context context4 = context3;
                                        final boolean z9 = z8;
                                        toneSelectViewModel4.getClass();
                                        Intent intent = new Intent("android.intent.action.GET_CONTENT");
                                        intent.setType("audio/*");
                                        Intent intentCreateChooser = Intent.createChooser(intent, null);
                                        if ((context4 instanceof IActivityForResult) && (iActivityForResult = (IActivityForResult) context4) != null) {
                                            Intrinsics.checkNotNull(intentCreateChooser);
                                            iActivityForResult.W(intentCreateChooser, new Function1<ActivityResult, Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$goToImport$1$1
                                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                {
                                                    super(1);
                                                }

                                                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                                /* JADX WARN: Removed duplicated region for block: B:10:0x003f  */
                                                @Override // kotlin.jvm.functions.Function1
                                                /*
                                                    Code decompiled incorrectly, please refer to instructions dump.
                                                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                                                */
                                                public final kotlin.Unit invoke(androidx.activity.result.ActivityResult r14) throws java.io.IOException {
                                                    /*
                                                        r13 = this;
                                                        androidx.activity.result.ActivityResult r14 = (androidx.activity.result.ActivityResult) r14
                                                        java.lang.String r3 = ""
                                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r14, r3)
                                                        android.content.Intent r0 = r14.b
                                                        r5 = 46
                                                        java.lang.String r6 = "audio"
                                                        if (r0 == 0) goto Lc1
                                                        android.net.Uri r4 = r0.getData()
                                                        if (r4 == 0) goto Lc1
                                                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                                                        r2.<init>()
                                                        com.vega.core.utils.DirectoryUtil r0 = com.vega.core.utils.DirectoryUtil.f79563a
                                                        r0.getClass()
                                                        java.lang.String r0 = com.vega.core.utils.DirectoryUtil.D(r6)
                                                        r2.append(r0)
                                                        long r0 = java.lang.System.currentTimeMillis()
                                                        r2.append(r0)
                                                        r2.append(r5)
                                                        java.lang.String r0 = r4.getPath()
                                                        if (r0 == 0) goto L3f
                                                        kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
                                                        java.lang.String r0 = com.vega.core.ext.ExtentionKt.suffix(r0)
                                                        if (r0 != 0) goto L41
                                                    L3f:
                                                        java.lang.String r0 = "wav"
                                                    L41:
                                                        r2.append(r0)
                                                        java.lang.String r7 = r2.toString()
                                                        com.vega.core.utils.FileUtils r0 = com.vega.core.utils.FileUtils.f79591a
                                                        r0.getClass()
                                                        com.vega.core.utils.FileUtils.d(r4, r7)
                                                    L50:
                                                        int r0 = r7.length()
                                                        if (r0 <= 0) goto Lbe
                                                        com.vega.ve.utils.MediaUtil r0 = com.vega.ve.utils.MediaUtil.f135467a
                                                        r0.getClass()
                                                        com.vega.ve.data.AudioMetaDataInfo r2 = com.vega.ve.utils.MediaUtil.c(r7)
                                                        com.vega.audio.tone.clonetone.ability.CloneToneUtils r1 = com.vega.audio.tone.clonetone.ability.CloneToneUtils.f73967a
                                                        boolean r0 = r4
                                                        r1.getClass()
                                                        int r0 = com.vega.audio.tone.clonetone.ability.CloneToneUtils.p(r0)
                                                        int r4 = r0 * 1000
                                                        int r0 = r2.f135305a
                                                        if (r0 <= r4) goto La9
                                                        java.lang.StringBuilder r2 = new java.lang.StringBuilder
                                                        r2.<init>()
                                                        com.vega.core.utils.DirectoryUtil r0 = com.vega.core.utils.DirectoryUtil.f79563a
                                                        r0.getClass()
                                                        java.lang.String r0 = com.vega.core.utils.DirectoryUtil.D(r6)
                                                        r2.append(r0)
                                                        long r0 = java.lang.System.currentTimeMillis()
                                                        r2.append(r0)
                                                        r2.append(r5)
                                                        java.lang.String r0 = com.vega.core.ext.ExtentionKt.suffix(r7)
                                                        r2.append(r0)
                                                        java.lang.String r8 = r2.toString()
                                                        com.vega.ve.utils.VEUtils r2 = com.vega.ve.utils.VEUtils.f135496a
                                                        long r11 = (long) r4
                                                        r0 = 500(0x1f4, float:7.0E-43)
                                                        long r0 = (long) r0
                                                        long r11 = r11 - r0
                                                        r2.getClass()
                                                        r9 = 0
                                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r3)
                                                        com.ss.android.vesdk.VEUtils.cropAudio(r7, r8, r9, r11)
                                                        r7 = r8
                                                    La9:
                                                        com.vega.audio.tone.clonetone.statistics.CloneToneStatistics r1 = com.vega.audio.tone.clonetone.statistics.CloneToneStatistics.f74107a
                                                        com.vega.audio.tone.clonetone.statistics.CloneToneStatistics$GenerateFrom r0 = com.vega.audio.tone.clonetone.statistics.CloneToneStatistics.GenerateFrom.f74118d
                                                        r1.getClass()
                                                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
                                                        com.vega.audio.tone.clonetone.statistics.CloneToneStatistics.e = r0
                                                        com.vega.audio.tone.viewmodel.ToneSelectViewModel r2 = r3
                                                        android.content.Context r1 = r2
                                                        boolean r0 = r4
                                                        r2.D7(r1, r7, r0)
                                                    Lbe:
                                                        kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                                        return r0
                                                    Lc1:
                                                        r7 = r3
                                                        goto L50
                                                    */
                                                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$goToImport$1$1.invoke(java.lang.Object):java.lang.Object");
                                                }
                                            });
                                        }
                                        CloneToneStatistics cloneToneStatistics = CloneToneStatistics.f74107a;
                                        String strL7 = toneSelectViewModel3.l7();
                                        String strD7 = toneSelectViewModel3.d7();
                                        cloneToneStatistics.getClass();
                                        CloneToneStatistics.s("click_extract_file", strL7, strD7);
                                        return Unit.INSTANCE;
                                    }
                                }, z8);
                                if (!new HeliosApiHook().preInvoke(300000, "com/vega/audio/tone/view/AudioChoicePanel", "show", audioChoicePanel, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSVEIlTB3Kcu5WjxBEU3Wd5nJXHhc/oZqix0EXGS+qIkjR7G7Pt73YUw==")).isIntercept()) {
                                    audioChoicePanel.show();
                                }
                                CloneToneStatistics cloneToneStatistics = CloneToneStatistics.f74107a;
                                String strL7 = toneSelectViewModel3.l7();
                                String strD7 = toneSelectViewModel3.d7();
                                cloneToneStatistics.getClass();
                                CloneToneStatistics.s("show", strL7, strD7);
                            } else {
                                toneSelectViewModel3.z7(context3, str4, z7, z8);
                            }
                        } else {
                            LiveDataExtKt.o(toneSelectViewModel2.P, Boolean.TRUE);
                        }
                        return Unit.INSTANCE;
                    }
                };
                cloneToneLoginHelper.getClass();
                CloneToneLoginHelper.a(fragmentActivityC, lifecycleCoroutineScopeImplA, function2);
                return Unit.INSTANCE;
            }
        };
        if (!z3 ? !(((Boolean) toneSelectViewModel.R0.getValue(toneSelectViewModel, m1[1])).booleanValue() && ((value = toneSelectViewModel.J0.getValue()) == null || (list = value.h) == null || !list.isEmpty())) : !(((Boolean) toneSelectViewModel.S0.getValue(toneSelectViewModel, m1[2])).booleanValue() && ((value2 = toneSelectViewModel.K0.getValue()) == null || (list2 = value2.h) == null || !list2.isEmpty()))) {
            function0.invoke();
            return;
        }
        toneSelectViewModel.J7("show");
        ToneCloneLegalDialog toneCloneLegalDialog = new ToneCloneLegalDialog(context, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneLegalGuide$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                if (z3) {
                    ToneSelectViewModel toneSelectViewModel2 = toneSelectViewModel;
                    toneSelectViewModel2.S0.setValue(toneSelectViewModel2, ToneSelectViewModel.m1[2], Boolean.TRUE);
                } else {
                    ToneSelectViewModel toneSelectViewModel3 = toneSelectViewModel;
                    toneSelectViewModel3.R0.setValue(toneSelectViewModel3, ToneSelectViewModel.m1[1], Boolean.TRUE);
                }
                function0.invoke();
                toneSelectViewModel.J7("record");
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneLegalGuide$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                this.e.J7(EventReport.DIALOG_CLOSE);
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneLegalGuide$3
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                this.e.J7("agree");
                return Unit.INSTANCE;
            }
        });
        if (new HeliosApiHook().preInvoke(300000, "com/vega/audio/tone/clonetone/dialog/ToneCloneLegalDialog", "show", toneCloneLegalDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSVEIlTB3Kcu5WjxBEU3Wd5nJXHhc/oZqix0EXGS+qIkjR7G7Pt73YUw==")).isIntercept()) {
            return;
        }
        toneCloneLegalDialog.show();
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.clipflow.Clipflow.e(com.vega.clipflow.Clipflow, kotlin.reflect.KClass, com.vega.clipflow.ClipflowTaskParam, kotlin.jvm.functions.Function2, int):com.vega.clipflow.ClipflowTask */
    public static boolean d8(final ToneSelectViewModel toneSelectViewModel, ToneType toneType, String str, String str2, float f, int i, boolean z, boolean z2, ThemeType themeType, TextToSpeechReportDigitalHumanEntrance textToSpeechReportDigitalHumanEntrance, boolean z3, boolean z4, ReadingListener readingListener, boolean z5, Function2 function2, int i2) {
        boolean z6;
        String strReplace$default;
        Segment segment;
        final float f2 = f;
        boolean z7 = z;
        boolean z8 = z2;
        boolean z9 = z3;
        final Function2 function22 = function2;
        boolean z10 = z4;
        final ReadingListener readingListener2 = readingListener;
        if ((i2 & 8) != 0) {
            f2 = 1.0f;
        }
        if ((i2 & 32) != 0) {
            z7 = false;
        }
        if ((i2 & 64) != 0) {
            z8 = false;
        }
        boolean z11 = (i2 & 256) != 0;
        if ((i2 & 512) != 0) {
            textToSpeechReportDigitalHumanEntrance = null;
        }
        if ((i2 & 1024) != 0) {
            z9 = false;
        }
        if ((i2 & SpeechEngineDefines.ASR_WORK_MODE_OFFLINE) != 0) {
            z10 = false;
        }
        if ((i2 & SpeechEngineDefines.TTS_WORK_MODE_BOTH) != 0) {
            readingListener2 = null;
        }
        if ((i2 & 8192) != 0) {
            z5 = false;
        }
        if ((i2 & 32768) != 0) {
            function22 = null;
        }
        toneSelectViewModel.getClass();
        Intrinsics.checkNotNullParameter(toneType, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(themeType, "");
        if (NetUtils.b(ModuleCommon.INSTANCE.getApplication())) {
            Emotion defaultEmotion = toneSelectViewModel.X0.get(str);
            if (defaultEmotion == null) {
                defaultEmotion = toneType.getDefaultEmotion();
            }
            String voiceType = (String) ((LinkedHashMap) toneSelectViewModel.Z).get(toneType.getName());
            if (voiceType == null) {
                voiceType = toneType.getVoiceType();
            }
            EmotionOption.h.getClass();
            EmotionOption emotionOptionA = EmotionOption.Companion.a(defaultEmotion);
            String strP7 = toneSelectViewModel.p7(toneType.getCloneToneLanguage(), z7, z8);
            if (strP7 != null && strP7.length() != 0) {
                if (z11) {
                    toneSelectViewModel.F.setValue(toneType);
                }
                toneSelectViewModel.C = str2;
                toneSelectViewModel.R = f2;
                toneSelectViewModel.H = toneType.getToneName();
                TextToSpeechReportDigitalHumanEntrance textToSpeechReportDigitalHumanEntrance2 = textToSpeechReportDigitalHumanEntrance == null ? TextToSpeechReportDigitalHumanEntrance.NONE : textToSpeechReportDigitalHumanEntrance;
                SegmentState value = toneSelectViewModel.R2().getValue();
                LVVEMetaType lVVEMetaTypeG = (value == null || (segment = value.f87871c) == null) ? null : segment.g();
                if (LVVEMetaType.MetaTypeSubtitle == lVVEMetaTypeG) {
                    textToSpeechReportDigitalHumanEntrance2 = TextToSpeechReportDigitalHumanEntrance.TEXT_ADD_SUBTITLE;
                } else if (LVVEMetaType.MetaTypeText == lVVEMetaTypeG) {
                    textToSpeechReportDigitalHumanEntrance2 = TextToSpeechReportDigitalHumanEntrance.TEXT_ADD_TEXT;
                }
                TextToSpeechReportScene textToSpeechReportSceneH8 = toneSelectViewModel.h8(toneSelectViewModel.e0, z7, z8, themeType, textToSpeechReportDigitalHumanEntrance2);
                TextToSpeechReportScene textToSpeechReportScene = TextToSpeechReportScene.AUDIO_CLONE;
                if (!CollectionsKt___CollectionsKt.contains(SetsKt__SetsKt.setOf((Object[]) new TextToSpeechReportScene[]{TextToSpeechReportScene.TEXT_TO_VIDEO_AUDIO_CLONE, textToSpeechReportScene, TextToSpeechReportScene.AUDIO_CLONE_MUSIC}), textToSpeechReportSceneH8)) {
                    textToSpeechReportScene = textToSpeechReportSceneH8;
                }
                boolean z12 = Intrinsics.areEqual(toneType.getPlatform(), "moyin") || CollectionsKt___CollectionsKt.contains((List) toneSelectViewModel.f1.getValue(), textToSpeechReportScene) || ((ToneCommercialOptABTestConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneCommercialOptABTest.class))).enableAuditionOpt();
                boolean zNeedLimit = ((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).hitExperiment() ? CollectionsKt___CollectionsKt.contains((List) toneSelectViewModel.f1.getValue(), textToSpeechReportScene) && ((AuditionUsersTextConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextSettings.class))).needLimit() : ((AuditionUsersTextCacheABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextCacheABTest.class))).needLimit();
                if (!zNeedLimit || strP7.length() <= toneSelectViewModel.Z6()) {
                    z6 = false;
                } else {
                    BLog.i("ToneSelectViewModel", "startReadingNotUpdateToneTypeLiveData need limit text size:" + toneSelectViewModel.Z6() + ", text:" + strP7);
                    z6 = false;
                    strP7 = strP7.substring(0, toneSelectViewModel.Z6());
                    Intrinsics.checkNotNullExpressionValue(strP7, "");
                }
                int length = strP7.length();
                if (f2 == 1.0f) {
                    z6 = true;
                }
                TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(textToSpeechReportScene, textToSpeechReportSceneH8, length, true, !z6, 0L, null, null, textToSpeechReportDigitalHumanEntrance != null ? textToSpeechReportDigitalHumanEntrance.getInfo() : null, null, 736, null);
                BLog.i("ToneSelectViewModel", "startReadingNotUpdateToneTypeLiveData scene:" + textToSpeechReportScene + ",enterFrom:" + toneSelectViewModel.e0 + ", useCache:" + z12 + ", needLimit:" + zNeedLimit + ", finalReadText:" + strP7);
                if (toneType.isSingCloneTone() && ExtentionKt.isNotNullOrEmpty(toneType.getAuditionAudioZhVid())) {
                    String auditionAudioZhVid = toneType.getAuditionAudioZhVid();
                    if (auditionAudioZhVid != null) {
                        ThirdPartyPlayer thirdPartyPlayer = ThirdPartyPlayer.f74363a;
                        thirdPartyPlayer.b();
                        ClipflowSongCloneManagePreviewTask.L.getClass();
                        DirectoryUtil.f79563a.getClass();
                        String strS0 = DirectoryUtil.s0(auditionAudioZhVid);
                        FileUtils.f79591a.getClass();
                        if (!FileUtils.q(strS0) || strS0 == null) {
                            Draft draftL = toneSelectViewModel.o.l();
                            if (draftL == null || (strReplace$default = draftL.b()) == null) {
                                String string = UUID.randomUUID().toString();
                                Intrinsics.checkNotNullExpressionValue(string, "");
                                strReplace$default = StringsKt__StringsJVMKt.replace$default(string, "-", "", false, 4, (Object) null);
                            } else {
                                Intrinsics.checkNotNull(strReplace$default);
                            }
                            ClipflowSongCloneManagePreviewTask clipflowSongCloneManagePreviewTask = toneSelectViewModel.q0;
                            if (clipflowSongCloneManagePreviewTask != null) {
                                clipflowSongCloneManagePreviewTask.F();
                            }
                            thirdPartyPlayer.b();
                            ClipflowSongCloneManagePreviewTask clipflowSongCloneManagePreviewTask2 = (ClipflowSongCloneManagePreviewTask) Clipflow.e(Clipflow.f75419a, Reflection.getOrCreateKotlinClass(ClipflowSongCloneManagePreviewTask.class), new ClipflowSongCloneManagePreviewTask.Param(strReplace$default, auditionAudioZhVid, str, GetPlayInfoAuth.b), null, 12);
                            toneSelectViewModel.q0 = clipflowSongCloneManagePreviewTask2;
                            ClipflowManager.f75461a.getClass();
                            MutableLiveData mutableLiveDataJ = ClipflowManager.j(clipflowSongCloneManagePreviewTask2);
                            if (mutableLiveDataJ != null) {
                                mutableLiveDataJ.observeForever(new ToneSelectViewModel$sam$androidx_lifecycle_Observer$0(new Function1<ClipFlowTaskEvent, Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$startReadingNotUpdateToneTypeLiveData$2$2
                                    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: kotlin.jvm.functions.Function2<? super com.vega.aigcapi.materialgenerate.TtsResult, ? super com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> */
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    /* JADX WARN: Removed duplicated region for block: B:21:0x0073  */
                                    @Override // kotlin.jvm.functions.Function1
                                    /*
                                        Code decompiled incorrectly, please refer to instructions dump.
                                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                                    */
                                    public final kotlin.Unit invoke(com.vega.clipflow.swig.ClipFlowTaskEvent r20) {
                                        /*
                                            r19 = this;
                                            r3 = r20
                                            com.vega.clipflow.swig.ClipFlowTaskEvent r3 = (com.vega.clipflow.swig.ClipFlowTaskEvent) r3
                                            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                                            com.vega.clipflow.swig.ClipFlowTaskExecuteState r0 = com.vega.clipflow.swig.ClipFlowTaskExecuteState.f75790g
                                            boolean r2 = com.vega.clipflow.ClipflowExtendKt.e(r3, r0)
                                            r1 = 0
                                            r0 = r19
                                            if (r2 == 0) goto La6
                                            com.vega.audio.tone.viewmodel.ToneSelectViewModel r2 = r0.e
                                            com.vega.audio.songclone.ClipflowSongCloneManagePreviewTask r3 = r2.q0
                                            if (r3 != 0) goto L1b
                                        L18:
                                            kotlin.Unit r0 = kotlin.Unit.INSTANCE
                                            return r0
                                        L1b:
                                            java.lang.String r2 = "downloadSingCloneToneAudition"
                                            java.lang.Object r4 = r3.o(r2)
                                            boolean r2 = r4 instanceof com.vega.edit.base.clipflow.ClipflowDownloadNode.Output
                                            if (r2 != 0) goto L26
                                            r4 = r1
                                        L26:
                                            com.vega.edit.base.clipflow.ClipflowDownloadNode$Output r4 = (com.vega.edit.base.clipflow.ClipflowDownloadNode.Output) r4
                                            if (r4 == 0) goto L6e
                                            java.lang.String r2 = r4.a()
                                            boolean r2 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r2)
                                            if (r2 == 0) goto L73
                                            com.vega.core.utils.FileUtils r3 = com.vega.core.utils.FileUtils.f79591a
                                            java.lang.String r2 = r4.a()
                                            r3.getClass()
                                            boolean r2 = com.vega.core.utils.FileUtils.q(r2)
                                            if (r2 == 0) goto L73
                                            com.vega.audio.tone.viewmodel.ToneSelectViewModel r2 = r0.e
                                            java.lang.String r6 = r4.a()
                                            float r7 = r3
                                            com.vega.aigcapi.materialgenerate.ReadingListener r4 = r4
                                            kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r3 = r5
                                            r2.getClass()
                                            java.lang.String r2 = ""
                                            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r2)
                                            com.vega.audio.tone.tts.engine.ThirdPartyPlayer r5 = com.vega.audio.tone.tts.engine.ThirdPartyPlayer.f74363a
                                            com.vega.audio.tone.viewmodel.ToneSelectViewModel$playAudio$1 r8 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$playAudio$1
                                            r8.<init>(r4, r3)
                                            X.0bi r9 = new X.0bi
                                            r9.<init>(r4)
                                            r10 = 2
                                            com.vega.audio.tone.tts.engine.ThirdPartyPlayer.a(r5, r6, r7, r8, r9, r10)
                                            com.vega.aigcapi.materialgenerate.ReadingListener r2 = r4
                                            if (r2 == 0) goto L6e
                                            r2.a()
                                        L6e:
                                            com.vega.audio.tone.viewmodel.ToneSelectViewModel r0 = r0.e
                                            r0.q0 = r1
                                            goto L18
                                        L73:
                                            java.lang.String r3 = "ToneSelectViewModel"
                                            java.lang.String r2 = "ClipflowSongCloneManagePreviewTask success, but audio file is not Exist"
                                            com.vega.log.BLog.e(r3, r2)
                                            kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r2 = r5
                                            if (r2 == 0) goto L6e
                                            com.vega.aigcapi.materialgenerate.TtsResult r3 = new com.vega.aigcapi.materialgenerate.TtsResult
                                            com.vega.aigcapi.materialgenerate.StatusResult r4 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                                            r5 = 0
                                            r6 = 0
                                            r8 = 0
                                            r18 = 8190(0x1ffe, float:1.1477E-41)
                                            r7 = r6
                                            r10 = r5
                                            r11 = r6
                                            r12 = r6
                                            r13 = r6
                                            r14 = r5
                                            r15 = r5
                                            r16 = r6
                                            r17 = r6
                                            r3.<init>(r4, r5, r6, r7, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18)
                                            com.lemon.lv.data.TextToAudioInfo r4 = new com.lemon.lv.data.TextToAudioInfo
                                            r10 = 127(0x7f, float:1.78E-43)
                                            r5 = r6
                                            r6 = r6
                                            r7 = r6
                                            r8 = r6
                                            r9 = r6
                                            r4.<init>(r5, r6, r7, r8, r9, r10)
                                            r2.invoke(r3, r4)
                                            goto L6e
                                        La6:
                                            com.vega.clipflow.swig.ClipFlowTaskExecuteState r2 = com.vega.clipflow.swig.ClipFlowTaskExecuteState.h
                                            boolean r2 = com.vega.clipflow.ClipflowExtendKt.e(r3, r2)
                                            if (r2 == 0) goto Ldf
                                            kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r2 = r5
                                            if (r2 == 0) goto Ld9
                                            com.vega.aigcapi.materialgenerate.TtsResult r3 = new com.vega.aigcapi.materialgenerate.TtsResult
                                            com.vega.aigcapi.materialgenerate.StatusResult r4 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                                            r5 = 0
                                            r6 = 0
                                            r8 = 0
                                            r18 = 8190(0x1ffe, float:1.1477E-41)
                                            r7 = r6
                                            r10 = r5
                                            r11 = r6
                                            r12 = r6
                                            r13 = r6
                                            r14 = r5
                                            r15 = r5
                                            r16 = r6
                                            r17 = r6
                                            r3.<init>(r4, r5, r6, r7, r8, r10, r11, r12, r13, r14, r15, r16, r17, r18)
                                            com.lemon.lv.data.TextToAudioInfo r4 = new com.lemon.lv.data.TextToAudioInfo
                                            r10 = 127(0x7f, float:1.78E-43)
                                            r5 = r6
                                            r6 = r6
                                            r7 = r6
                                            r8 = r6
                                            r9 = r6
                                            r4.<init>(r5, r6, r7, r8, r9, r10)
                                            r2.invoke(r3, r4)
                                        Ld9:
                                            com.vega.audio.tone.viewmodel.ToneSelectViewModel r0 = r0.e
                                            r0.q0 = r1
                                            goto L18
                                        Ldf:
                                            com.vega.clipflow.swig.ClipFlowTaskExecuteState r2 = com.vega.clipflow.swig.ClipFlowTaskExecuteState.f
                                            boolean r2 = com.vega.clipflow.ClipflowExtendKt.e(r3, r2)
                                            if (r2 == 0) goto L18
                                            com.vega.audio.tone.viewmodel.ToneSelectViewModel r0 = r0.e
                                            r0.q0 = r1
                                            goto L18
                                        */
                                        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$startReadingNotUpdateToneTypeLiveData$2$2.invoke(java.lang.Object):java.lang.Object");
                                    }
                                }));
                            }
                        } else {
                            BLog.i("ToneSelectViewModel", "sing_clone_audio_preview vid=" + auditionAudioZhVid + " path=" + strS0 + " aleady exist");
                            ThirdPartyPlayer.a(thirdPartyPlayer, strS0, f2, new ToneSelectViewModel$playAudio$1(readingListener2, function22), new C28530bi(readingListener2), 2);
                        }
                    }
                } else {
                    TextToSpeechIntent textToSpeechIntent = new TextToSpeechIntent(null, new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(strP7)), voiceType, z5 ? "ssml" : toneType.getPlatform(), "ToneSelectViewModel", null, TTSBusinessType.f88857c, toneSelectViewModel.j8(toneSelectViewModel.e0), null, f2, i, readingListener2, null, false, textToSpeechReportInfo.toJson(), toneType.getAuditionText(), z9, toneType.getToneModelType(), toneType.getResourceId(), function22, null, emotionOptionA, toneType.getMockToneInfo(), null, false, null, Boolean.valueOf(toneType.isAICloneTone()), toneType.isV3ModelTone(), false, z10, z12, null, null, null, null, false, 663761185, 31);
                    toneSelectViewModel.S = z9;
                    toneSelectViewModel.T = z10;
                    TextToSpeechTaskManager.f74281a.f(textToSpeechIntent);
                }
                return true;
            }
        } else {
            ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$startReadingNotUpdateToneTypeLiveData$1
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    ToastUtilKt.d(R.string.qk__res_0x7f122c0c, 0, 0, 0, 0, 252);
                    return Unit.INSTANCE;
                }
            });
        }
        return false;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public static Effect g8(ToneType toneType) {
        Intrinsics.checkNotNullParameter(toneType, "");
        Effect effect = new Effect(null, 1, 0 == true ? 1 : 0);
        effect.setId(toneType.getId());
        effect.setEffectId(toneType.getEffectId());
        effect.setEffect_id(toneType.getEffectId());
        EffectExKt.s0(effect, toneType.getCategoryID());
        toneType.getCategoryID();
        EffectExKt.s0(effect, toneType.getCategoryID());
        toneType.getCategoryName();
        EffectExKt.u0(effect, toneType.getCategoryName());
        toneType.getResourceId();
        effect.setResourceId(toneType.getResourceId());
        effect.getIconUrl().setUrlList(CollectionsKt__CollectionsJVMKt.listOf(toneType.getIconUrl()));
        effect.setName(toneType.getName());
        effect.setPanel(toneType.getPanel());
        EffectExKt.K0(effect, toneType.isVip());
        EffectExtendKt.j0(effect, toneType.isCommercial());
        EffectExtendKt.Z(effect, toneType.isAICloneTone());
        EffectExKt.C0(effect, toneType.getSecondCategoryKey());
        EffectExKt.D0(effect, toneType.getSecondCategoryName());
        EffectExKt.B0(effect, toneType.getSecondCategoryID());
        effect.setExtra(toneType.getExtra());
        return effect;
    }

    public static MaterialAudio h7(Segment segment) {
        if (segment instanceof SegmentAudio) {
            return ((SegmentAudio) segment).u();
        }
        return null;
    }

    public static /* synthetic */ TextToSpeechReportScene i8(ToneSelectViewModel toneSelectViewModel, int i, boolean z, ThemeType themeType, int i2) {
        ThemeType themeType2 = themeType;
        boolean z2 = z;
        if ((i2 & 4) != 0) {
            z2 = false;
        }
        if ((i2 & 8) != 0) {
            themeType2 = null;
        }
        return toneSelectViewModel.h8(i, false, z2, themeType2, null);
    }

    public static String k7(String str) {
        String strB = ((ToneEmotionConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneEmotionConfigSetting.class))).b(str);
        return strB == null ? "" : strB;
    }

    public static String m7(List list) {
        Intrinsics.checkNotNullParameter(list, "");
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, ((FilterGroup) it.next()).c());
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, ",", null, null, 0, null, new Function1<FilterItem, CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getFilterIds$2
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(FilterItem filterItem) {
                FilterItem filterItem2 = filterItem;
                Intrinsics.checkNotNullParameter(filterItem2, "");
                return filterItem2.b();
            }
        }, 30, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x008d A[PHI: r5
      0x008d: PHI (r5v1 java.lang.String) = (r5v0 java.lang.String), (r5v3 java.lang.String), (r5v3 java.lang.String), (r5v3 java.lang.String) binds: [B:44:0x008a, B:27:0x0049, B:29:0x004f, B:31:0x0055] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final kotlin.Pair<java.lang.String, com.lemon.librespool.model.gen.EffectByIdParams> u7(java.lang.String r8) {
        /*
            r7 = this;
            androidx.lifecycle.LiveData r0 = r7.R2()
            java.lang.Object r0 = r0.getValue()
            com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
            r1 = 0
            if (r0 == 0) goto L31
            com.vega.middlebridge.swig.Segment r2 = r0.f87871c
        Lf:
            boolean r0 = r2 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L2e
            if (r2 == 0) goto L2f
            com.vega.middlebridge.swig.SegmentAudio r0 = r7.o7(r2)
            if (r0 == 0) goto L2f
            com.vega.middlebridge.swig.MaterialAudio r0 = r0.u()
            if (r0 == 0) goto L2f
            java.lang.String r6 = r0.D()
        L25:
            if (r8 == 0) goto L33
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r6, r8)
            if (r0 != 0) goto L33
            return r1
        L2e:
            r2 = r1
        L2f:
            r6 = r1
            goto L25
        L31:
            r2 = r1
            goto Lf
        L33:
            if (r2 == 0) goto L89
            com.vega.middlebridge.swig.SegmentAudio r0 = r7.o7(r2)
            if (r0 == 0) goto L89
            com.vega.middlebridge.swig.MaterialAudio r0 = r0.u()
            if (r0 == 0) goto L89
            java.lang.String r5 = r0.E()
        L45:
            com.vega.middlebridge.swig.SegmentAudio r0 = r7.o7(r2)
            if (r0 == 0) goto L8d
            com.vega.middlebridge.swig.MaterialAudio r0 = r0.u()
            if (r0 == 0) goto L8d
            com.vega.middlebridge.swig.LVVEAudioSourcePlatformType r0 = r0.B()
            if (r0 == 0) goto L8d
            int r0 = r0.ordinal()
            java.lang.Integer r4 = java.lang.Integer.valueOf(r0)
        L5f:
            if (r6 == 0) goto L82
            if (r5 == 0) goto L83
            if (r4 == 0) goto L83
            com.vega.libeffect.repository.CommonRepositoryWrapper r0 = com.vega.libeffect.repository.CommonRepositoryWrapper.f109559a
            r0.getClass()
            java.lang.String r3 = "tone"
            int r0 = com.vega.libeffect.repository.CommonRepositoryWrapper.D(r3)
            com.lemon.librespool.model.gen.EffectByIdParams r2 = new com.lemon.librespool.model.gen.EffectByIdParams
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)
            int r0 = r4.intValue()
            r2.<init>(r5, r1, r0, r3)
        L7d:
            kotlin.Pair r1 = new kotlin.Pair
            r1.<init>(r6, r2)
        L82:
            return r1
        L83:
            r2 = r1
            if (r6 != 0) goto L7d
            if (r1 != 0) goto L7d
            goto L82
        L89:
            r5 = r1
            if (r2 == 0) goto L8d
            goto L45
        L8d:
            r4 = r1
            goto L5f
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.u7(java.lang.String):kotlin.Pair");
    }

    public static List v7(List list) {
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Segment segment = (Segment) it.next();
            if (segment instanceof SegmentText) {
                arrayList.addAll(DraftExpandKt.w((SegmentText) segment));
            } else if (segment instanceof SegmentTextTemplate) {
                VectorOfString vectorOfStringS = ((SegmentTextTemplate) segment).p().s();
                Intrinsics.checkNotNullExpressionValue(vectorOfStringS, "");
                arrayList.addAll(vectorOfStringS);
            }
        }
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v5, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    public final void A7() {
        CategoryListState value = c7().getValue();
        List<EffectCategoryModel> list = value != null ? value.b : null;
        if (this.V0 == -300000 && list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder("last_selected_category_");
            Draft draftL = this.o.l();
            sb.append(draftL != null ? draftL.b() : null);
            this.V0 = n7(sb.toString(), list);
        }
        CategoryListState categoryListState = (CategoryListState) this.x0.getValue();
        List<EffectCategoryModel> list2 = categoryListState != null ? categoryListState.b : null;
        if (this.W0 != -300000 || list2 == null || list2.isEmpty()) {
            return;
        }
        StringBuilder sb2 = new StringBuilder("last_selected_category_mine_");
        Draft draftL2 = this.o.l();
        sb2.append(draftL2 != null ? draftL2.b() : null);
        this.W0 = n7(sb2.toString(), list2);
    }

    public final boolean B7() {
        List list = (List) this.k0.getValue();
        return list != null && (list.isEmpty() ^ true);
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final MutableLiveData<Boolean> C() {
        return this.O0;
    }

    public final boolean C7(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        try {
            VEUtils.f135496a.getClass();
            VEUtils.VEAVFileInfo vEAVFileInfoC = com.vega.ve.utils.VEUtils.c(str, false);
            if (vEAVFileInfoC == null || vEAVFileInfoC.numAudioStreams <= 0) {
                return false;
            }
            return vEAVFileInfoC.duration > 0;
        } catch (Throwable th) {
            BLog.w("ToneSelectViewModel", "isValidAudio e = " + th.getMessage() + ", " + th.getCause());
        }
        return false;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:34:0x0076 */
    /* JADX DEBUG: Type inference failed for r1v2. Raw type applied. Possible types: java.util.Iterator<T>, java.util.Iterator */
    /* JADX DEBUG: Type inference failed for r1v9. Raw type applied. Possible types: java.util.Iterator<T>, java.util.Iterator */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.ArrayList] */
    public final void D7(Context context, String str, boolean z) {
        ?? EmptyList;
        List<Effect> list;
        Serializable serializableRandomUUID;
        List<Effect> list2;
        if (!z) {
            CloneToneUtils.f73967a.getClass();
            if (!CloneToneUtils.c(context, str, true)) {
                return;
            }
        }
        if (z) {
            PagedEffectListState<Effect> value = this.K0.getValue();
            if (value == null || (list2 = value.h) == null) {
                EmptyList = 0;
            } else {
                EmptyList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    EmptyList.add(((com.ss.ugc.effectplatform.model.Effect) it.next()).getName());
                }
            }
        } else {
            PagedEffectListState<Effect> value2 = this.J0.getValue();
            if (value2 == null || (list = value2.h) == null) {
                EmptyList = CollectionsKt__CollectionsKt.emptyList();
            } else {
                EmptyList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    EmptyList.add(((com.ss.ugc.effectplatform.model.Effect) it2.next()).getName());
                }
            }
        }
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//edit/clone_tone");
        Draft draftL = this.o.l();
        if (draftL == null || (serializableRandomUUID = draftL.b()) == null) {
            serializableRandomUUID = UUID.randomUUID();
        }
        smartRouteBuildRoute.withParam("KEY_DRAFT_ID", serializableRandomUUID);
        smartRouteBuildRoute.withParam("KEY_PROCESS_AUDIO", str);
        smartRouteBuildRoute.withParamStringList("key_tone_name_list", new ArrayList<>((Collection) EmptyList));
        smartRouteBuildRoute.withParam("enter_from", this.e0);
        smartRouteBuildRoute.withParam("key_is_sing_clone", z);
        smartRouteBuildRoute.open(1010);
    }

    public final void E7() {
        List<Effect> list;
        List<Effect> list2;
        ToneType value = this.F.getValue();
        if (value == null) {
            return;
        }
        if (value.isAICloneTone() || value.isSingCloneTone()) {
            String resourceId = value.getResourceId();
            Object obj = null;
            if (!value.isSingCloneTone()) {
                PagedEffectListState<Effect> value2 = this.J0.getValue();
                if (value2 == null || (list = value2.h) == null) {
                    return;
                }
                for (Object obj2 : list) {
                    if (Intrinsics.areEqual(((EffectTemplate) obj2).getResourceId(), resourceId)) {
                        obj = obj2;
                        break;
                    }
                }
            } else {
                PagedEffectListState<Effect> value3 = this.K0.getValue();
                if (value3 == null || (list2 = value3.h) == null) {
                    return;
                }
                for (Object obj22 : list2) {
                    if (Intrinsics.areEqual(((EffectTemplate) obj22).getResourceId(), resourceId)) {
                        obj = obj22;
                        break;
                    }
                }
            }
            com.ss.ugc.effectplatform.model.Effect effect = (com.ss.ugc.effectplatform.model.Effect) obj;
            if (effect != null) {
                value.setName(effect.getName());
                value.setToneName(effect.getName());
                this.F.setValue(value);
                this.z.setValue(value.getVoiceType());
            }
        }
    }

    public final void F7(ToneType toneType) {
        Intrinsics.checkNotNullParameter(toneType, "");
        Effect effectG8 = g8(toneType);
        VipMaterialUtils.b(VipMaterialUtils.f89614a, ISessionBusinessBridgeKt.a(this.o), effectG8, EffectExKt.p(effectG8), EffectExKt.r(effectG8), LVVEMetaType.MetaTypeTextToAudio, null, null, null, 224);
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void G0(boolean z, boolean z2, boolean z3) {
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new ToneSelectViewModel$fetchToneCloneEffect$1(this, z, z2, z3, null), 2, null);
    }

    public final void G7() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("action", Intrinsics.areEqual(this.D.getValue(), Boolean.TRUE) ? "select" : "cancel");
        ReportManagerWrapper.INSTANCE.onEvent("click_text_to_audio_apply_all", map);
    }

    public final void H7(String str, int i, LVVEMetaType lVVEMetaType, boolean z) {
        List<String> listF7;
        Intrinsics.checkNotNullParameter(str, "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("action", str);
        map.put("is_replace", Integer.valueOf(i));
        Boolean value = this.D.getValue();
        Boolean bool = Boolean.TRUE;
        boolean zAreEqual = Intrinsics.areEqual(value, bool);
        String string = ProfileManager.VERSION;
        map.put("is_apply_all", zAreEqual ? ProfileManager.VERSION : "0");
        if (!Intrinsics.areEqual(this.D.getValue(), bool) ? !ExtentionKt.isNotNullOrEmpty(p7("", false, false)) : (listF7 = f7(lVVEMetaType)) == null || (string = Integer.valueOf(listF7.size()).toString()) == null) {
            string = "0";
        }
        map.put("text_to_audio_cnt", string);
        if (z) {
            map.put("is_replace_ai_avatar", Integer.valueOf(i));
        }
        ReportManagerWrapper.INSTANCE.onEvent("text_to_audio_popup", map);
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final MutableLiveData<PagedEffectListState<Effect>> I() {
        return this.J0;
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final MutableLiveData<String> I1() {
        return this.z;
    }

    @Override // com.vega.edit.base.view.BaseTabViewModel
    public final void J6(Effect effect) {
        Intrinsics.checkNotNullParameter(effect, "");
    }

    public final void J7(String str) {
        ReportManagerWrapper.INSTANCE.onEvent("voice_clone_legal_popup_action", MapsKt__MapsKt.mapOf(TuplesKt.to("action", str), TuplesKt.to("edit_type", j8(this.e0)), TuplesKt.to("enter_from", l7()), TuplesKt.to("clone_enter_from", d7())));
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void K3(String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(str, "");
        ClipflowSongCloneManagePreviewTask clipflowSongCloneManagePreviewTask = this.q0;
        if (clipflowSongCloneManagePreviewTask != null) {
            clipflowSongCloneManagePreviewTask.F();
            ThirdPartyPlayer.f74363a.b();
            this.q0 = null;
        }
        TextToSpeechTaskManager.f74281a.b(z2);
        if (!TextUtils.isEmpty(str)) {
            this.z.setValue(str);
        }
        if (((TtsStreamingOptimizedConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsStreamingOptimizedConfigSetting.class))).a()) {
            return;
        }
        ThirdPartyPlayer.f74363a.b();
    }

    public final void L7(boolean z, boolean z2) {
        List<Effect> list;
        String str;
        List<Effect> list2;
        HashMap<String, Object> map = new HashMap<>();
        PagedEffectListState<Effect> value = z2 ? this.K0.getValue() : this.J0.getValue();
        if (z) {
            str = (value != null ? value.f98530g : null) == RepoResult.b ? "network_erro_page" : (value == null || (list = value.h) == null || !(list.isEmpty() ^ true)) ? "intro_page" : "voice_page";
        } else {
            str = "launch_page";
        }
        map.put("page_type", str);
        map.put("edit_type", j8(this.e0));
        if (value != null && (list2 = value.h) != null && (!list2.isEmpty())) {
            map.put("clone_cnt", Integer.valueOf(value.h.size()));
        }
        map.put("enter_from", l7());
        map.put("clone_enter_from", d7());
        ReportManagerWrapper.INSTANCE.onEvent("show_voice_clone_page", map);
    }

    public void N6(Double d2, boolean z) {
    }

    public final void O6() {
        this.F.setValue(new ToneType(null, null, "", null, null, "", null, null, null, null, null, null, "", "", null, null, null, false, null, null, false, false, 0, null, 0.0d, false, null, false, false, null, null, null, false, null, null, null, null, null, false, null, null, false, -12325, 1023, null));
        this.s0 = false;
    }

    public final void O7(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("action", str);
        linkedHashMap.put("edit_type", j8(this.e0));
        ReportManagerWrapper.INSTANCE.onEvent("voice_clone_popup_action", (Map<String, String>) linkedHashMap);
    }

    public final boolean P6() {
        String style;
        String emotion;
        Emotion emotionT7 = t7(this.z.getValue());
        if (emotionT7 == null) {
            return false;
        }
        String role = emotionT7.getRole();
        return ((role == null || role.length() == 0) && ((style = emotionT7.getStyle()) == null || style.length() == 0) && ((emotion = emotionT7.getEmotion()) == null || emotion.length() == 0)) ? false : true;
    }

    public final void P7(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "");
        ReportManagerWrapper reportManagerWrapper = ReportManagerWrapper.INSTANCE;
        Map<String, String> mapMutableMapOf = MapsKt__MapsKt.mutableMapOf(TuplesKt.to("action", str), TuplesKt.to("edit_type", j8(this.e0)), TuplesKt.to("enter_from", l7()), TuplesKt.to("clone_enter_from", d7()));
        String str2 = this.f0;
        if (str2 != null) {
            mapMutableMapOf.put("long_text_editor_page_from", str2);
        }
        reportManagerWrapper.onEvent("voice_clone_page_action", mapMutableMapOf);
        if (z) {
            M7(this, ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccount().isLogin(), 2);
            this.l1 = false;
        }
    }

    public final boolean Q6() {
        String nameKey;
        Draft draftL;
        SegmentAudio segmentAudioP;
        MaterialAudio materialAudioU;
        MaterialDigitalHuman materialDigitalHumanC;
        DigitalHumanVoiceInfo digitalHumanVoiceInfoY;
        Emotion emotion = this.X0.get(this.z.getValue());
        if (emotion == null || (nameKey = emotion.getNameKey()) == null) {
            nameKey = "";
        }
        SegmentVideo segmentVideo = this.Q;
        String strG = null;
        if (segmentVideo != null && (materialDigitalHumanC = segmentVideo.C()) != null && (digitalHumanVoiceInfoY = materialDigitalHumanC.y()) != null) {
            return Intrinsics.areEqual(digitalHumanVoiceInfoY.l(), nameKey);
        }
        SegmentState value = R2().getValue();
        Segment segment = value != null ? value.f87871c : null;
        if (segment instanceof SegmentText) {
            VectorOfString vectorOfStringW = DraftExpandKt.w((SegmentText) segment);
            if (!vectorOfStringW.isEmpty()) {
                if (!DraftExpandKt.K(segment) || DraftExpandKt.p(segment) == null) {
                    Draft draftL2 = this.o.l();
                    if (draftL2 != null) {
                        segmentAudioP = (SegmentAudio) V6(vectorOfStringW.get(0), draftL2);
                    }
                } else {
                    segmentAudioP = DraftExpandKt.p(segment);
                }
                if (segmentAudioP != null && (materialAudioU = segmentAudioP.u()) != null) {
                    strG = materialAudioU.G();
                }
            }
        } else if (segment instanceof SegmentTextTemplate) {
            VectorOfString vectorOfStringS = ((SegmentTextTemplate) segment).p().s();
            if (!vectorOfStringS.isEmpty() && (draftL = this.o.l()) != null) {
                segmentAudioP = (SegmentAudio) V6(vectorOfStringS.get(0), draftL);
                if (segmentAudioP != null) {
                    strG = materialAudioU.G();
                }
            }
        }
        return Intrinsics.areEqual(strG, nameKey);
    }

    public final void Q7(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        HashMap<String, Object> map = new HashMap<>();
        map.put("action", str);
        map.put("is_vip", Integer.valueOf(((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).q().isVip() ? 1 : 0));
        ReportManagerWrapper.INSTANCE.onEvent("voice_clone_exceed_free_word_cnt_popup", map);
    }

    public final void R6(ToneType toneType) {
        Intrinsics.checkNotNullParameter(toneType, "");
        if (Intrinsics.areEqual(this.H, toneType.getToneName())) {
            this.H = "";
        }
        ToneType value = this.F.getValue();
        if (Intrinsics.areEqual(value != null ? value.getResourceId() : null, toneType.getResourceId())) {
            this.F.postValue(new ToneType(null, null, "", null, null, "", null, null, null, null, null, null, "", "", null, null, null, false, null, null, false, false, 0, null, 0.0d, false, null, false, false, null, null, null, false, null, null, null, null, null, false, null, null, false, -12325, 1023, null));
            this.z.postValue("");
            this.s0 = false;
        }
        ClipflowSongCloneManagePreviewTask clipflowSongCloneManagePreviewTask = this.q0;
        if (clipflowSongCloneManagePreviewTask != null) {
            clipflowSongCloneManagePreviewTask.F();
        }
        ThirdPartyPlayer.f74363a.b();
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.edit.base.utils.RecommendCapabilityViewInfo.Companion.c(com.vega.edit.base.utils.RecommendCapabilityViewInfo$Companion, com.vega.edit.base.utils.RecommendCapabilityViewInfo, java.lang.String, java.lang.String, int):com.vega.ve.data.SegmentRecommendInfo */
    /* JADX DEBUG: Class process forced to load method for inline: com.vega.ve.utils.IQueryUtilsExKt.b(com.vega.middlebridge.swig.IQueryUtils, java.util.List, long, long, int, java.util.List, int):int */
    /* JADX DEBUG: Multi-variable search result rejected for r7v20, resolved type: com.vega.middlebridge.swig.UpdateTextCompositionParam */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0550 A[LOOP:4: B:149:0x054e->B:150:0x0550, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:151:0x055d  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x05d8  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x05e1  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x061d  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x06ef  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x0755  */
    /* JADX WARN: Removed duplicated region for block: B:417:0x00fa A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0327  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void R7(java.util.List r55, boolean r56, java.lang.String r57, java.lang.String r58, java.util.List r59, java.lang.String r60, boolean r61, boolean r62, boolean r63, boolean r64, float r65, java.lang.String r66, java.lang.String r67, com.vega.middlebridge.swig.LVVEMetaType r68, java.lang.String r69, boolean r70, java.lang.String r71, java.lang.String r72, java.lang.String r73, boolean r74, com.vega.edit.base.utils.RecommendCapabilityViewInfo r75, com.lemon.lv.data.Emotion r76, com.vega.edit.base.audio.tone.TextToAudioInfoPack r77, java.lang.String r78, java.util.Map r79, kotlin.jvm.functions.Function0 r80) {
        /*
            r54 = this;
            r28 = r80
            java.lang.String r4 = ""
            r53 = r55
            r0 = r53
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r52 = r57
            r0 = r52
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r51 = r58
            r0 = r51
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r50 = r59
            r0 = r50
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r49 = r60
            r0 = r49
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r47 = r66
            r0 = r47
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r46 = r67
            r0 = r46
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r45 = r69
            r0 = r45
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r43 = r71
            r0 = r43
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r42 = r72
            r0 = r42
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r41 = r73
            r0 = r41
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            r6 = r77
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r4)
            r39 = r78
            r0 = r39
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r4)
            com.vega.middlebridge.swig.DraftComboParams r27 = new com.vega.middlebridge.swig.DraftComboParams
            r27.<init>()
            if (r63 == 0) goto Lb5c
            java.lang.String r1 = "ADD_TEXT"
        L66:
            r0 = r27
            r0.d(r1)
            r1 = r64
            r0 = r27
            r0.e(r1)
            com.vega.middlebridge.swig.MapOfStringString r2 = r27.b()
            com.vega.edit.base.utils.RecommendCapabilityViewInfo$Companion r5 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.o
            r3 = 0
            r1 = 1
            r7 = r75
            r0 = r47
            com.vega.ve.data.SegmentRecommendInfo r13 = com.vega.edit.base.utils.RecommendCapabilityViewInfo.Companion.c(r5, r7, r3, r0, r1)
            r8 = 0
            r11 = 0
            com.vega.infrastructure.json.JsonProxy r1 = com.vega.infrastructure.json.JsonProxy.f106571a
            com.vega.draft.data.storage.ExtraMapAttachInfo$Companion r0 = com.vega.draft.data.storage.ExtraMapAttachInfo.h
            r0.getClass()
            com.vega.draft.data.storage.ExtraMapAttachInfo$$serializer r0 = com.vega.draft.data.storage.ExtraMapAttachInfo$$serializer.f83075a
            com.vega.draft.data.storage.ExtraMapAttachInfo r7 = new com.vega.draft.data.storage.ExtraMapAttachInfo
            r9 = r8
            r10 = r8
            r12 = r11
            r14 = r8
            r7.<init>(r8, r9, r10, r11, r12, r13, r14)
            r1.getClass()
            java.lang.String r1 = com.vega.infrastructure.json.JsonProxy.b(r0, r7)
            java.lang.String r0 = "ExtraMapAttachInfo"
            r2.put(r0, r1)
            java.util.ArrayList r25 = new java.util.ArrayList
            r25.<init>()
            r9 = r54
            com.vega.container.session.core.ISession r0 = r9.o
            com.vega.middlebridge.lyrasession.LyraSession r19 = r0.b()
            if (r19 == 0) goto Lc7
            java.util.ArrayList r24 = new java.util.ArrayList
            r24.<init>()
            java.util.ArrayList r26 = new java.util.ArrayList
            r26.<init>()
            java.util.ArrayList r23 = new java.util.ArrayList
            r23.<init>()
            com.vega.middlebridge.swig.Draft r0 = com.vega.middlebridge.client.DraftClient.q(r19)
            if (r0 != 0) goto Lc8
        Lc7:
            return
        Lc8:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            long r17 = r0.l()
            java.util.Iterator r22 = r50.iterator()
            r36 = 0
            r5 = 0
        Ld6:
            boolean r0 = r22.hasNext()
            r21 = r56
            r7 = r68
            if (r0 == 0) goto L72d
            int r20 = r5 + 1
            java.lang.Object r32 = r22.next()
            r0 = r32
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            r32 = r0
            r0 = r53
            java.lang.Object r31 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r0, r5)
            r0 = r31
            java.lang.String r0 = (java.lang.String) r0
            r31 = r0
            if (r31 != 0) goto Lfd
        Lfa:
            r5 = r20
            goto Ld6
        Lfd:
            r0 = r31
            boolean r0 = r9.C7(r0)
            if (r0 != 0) goto L106
            goto Lfa
        L106:
            com.vega.ve.utils.MediaUtil r0 = com.vega.ve.utils.MediaUtil.f135467a
            r0.getClass()
            com.vega.ve.data.AudioMetaDataInfo r15 = com.vega.ve.utils.MediaUtil.c(r31)
            int r0 = r15.f135305a
            long r0 = (long) r0
            r2 = 1000(0x3e8, float:1.401E-42)
            long r10 = (long) r2
            long r0 = r0 * r10
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r8 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange
            com.vega.middlebridge.swig.TimeRange r2 = r32.k()
            long r2 = r2.e()
            r8.<init>(r2, r0)
            r0 = r23
            r0.add(r8)
            r0 = r32
            boolean r12 = r0 instanceof com.vega.middlebridge.swig.SegmentText
            if (r12 == 0) goto L17d
            boolean r0 = com.vega.ve.utils.DraftExpandKt.K(r32)
            if (r0 == 0) goto L17d
            r0 = 1
        L135:
            if (r21 == 0) goto L17f
            if (r0 != 0) goto L17f
            com.vega.container.session.core.ISession r0 = r9.o
            com.vega.container.session.core.ISessionScene r1 = r0.C0()
            com.vega.editorapi.session.SessionScene r0 = com.vega.editorapi.session.SessionScene.f97670d
            if (r1 != r0) goto L173
        L143:
            java.util.List r0 = v7(r50)
        L147:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r3 = r0.iterator()
        L14d:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto L17f
            java.lang.Object r2 = r3.next()
            java.lang.String r2 = (java.lang.String) r2
            com.vega.middlebridge.swig.SegmentIdsParam r1 = new com.vega.middlebridge.swig.SegmentIdsParam
            r1.<init>()
            com.vega.middlebridge.swig.VectorOfString r0 = r1.e()
            r0.a(r2)
            com.vega.middlebridge.swig.RemoveSegmentReqStruct r0 = new com.vega.middlebridge.swig.RemoveSegmentReqStruct
            r0.<init>()
            r0.setParams(r1)
            r1 = r25
            r1.add(r0)
            goto L14d
        L173:
            com.vega.editorapi.session.SessionScene r0 = com.vega.editorapi.session.SessionScene.e
            if (r1 != r0) goto L178
            goto L143
        L178:
            java.util.Set r0 = X6(r9, r7)
            goto L147
        L17d:
            r0 = 0
            goto L135
        L17f:
            if (r62 == 0) goto L264
            r0 = r32
            com.vega.middlebridge.swig.SegmentAudio r0 = r9.o7(r0)
            if (r0 == 0) goto L25e
            com.vega.middlebridge.swig.TimeRange r0 = r0.k()
            if (r0 == 0) goto L25e
        L18f:
            long r2 = r0.e()
        L193:
            int r0 = r15.f135305a
            long r0 = (long) r0
            long r0 = r0 * r10
            r13 = 0
            if (r62 == 0) goto L1ab
            long r29 = r0 + r2
            int r7 = (r29 > r17 ? 1 : (r29 == r17 ? 0 : -1))
            if (r7 <= 0) goto L1ab
            long r7 = r17 - r2
            long r7 = java.lang.Math.max(r13, r7)
            long r0 = java.lang.Math.min(r7, r0)
        L1ab:
            boolean r7 = com.vega.performance.PerformanceManagerHelper.blogEnable
            if (r7 == 0) goto L1ca
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r7 = "segment.metaType "
            r8.<init>(r7)
            com.vega.middlebridge.swig.LVVEMetaType r7 = r32.g()
            java.lang.String r7 = r7.name()
            r8.append(r7)
            java.lang.String r8 = r8.toString()
            java.lang.String r7 = "zhngjin_test"
            com.vega.log.BLog.i(r7, r8)
        L1ca:
            java.lang.String r29 = "TEXT_SEGMENT_ID"
            r48 = r65
            r30 = r76
            r40 = r74
            r44 = r70
            if (r12 == 0) goto L327
            r7 = r32
            com.vega.middlebridge.swig.SegmentText r7 = (com.vega.middlebridge.swig.SegmentText) r7
            r33 = r7
            boolean r7 = com.vega.ve.utils.DraftExpandKt.M(r33)
            if (r7 == 0) goto L327
            com.vega.middlebridge.swig.UpdateTextCompositionParam r7 = new com.vega.middlebridge.swig.UpdateTextCompositionParam
            r7.<init>()
            java.lang.String r8 = r33.b()
            long r10 = r7.f123118d
            com.vega.middlebridge.swig.UpdateTextCompositionParamModuleJNI.UpdateTextCompositionParam_seg_id_set(r10, r7, r8)
            com.vega.middlebridge.swig.ReplaceAudioParam r10 = new com.vega.middlebridge.swig.ReplaceAudioParam
            r10.<init>()
            long r11 = r10.f121052d
            r8 = 1
            com.vega.middlebridge.swig.ReplaceAudioParamModuleJNI.ReplaceAudioParam_has_tts_param_set(r11, r10, r8)
            com.vega.middlebridge.swig.SegmentAudio r8 = com.vega.ve.utils.DraftExpandKt.p(r32)
            if (r8 == 0) goto L25c
            java.lang.String r8 = r8.b()
        L205:
            long r11 = r10.f121052d
            com.vega.middlebridge.swig.ReplaceAudioParamModuleJNI.ReplaceAudioParam_seg_id_set(r11, r10, r8)
            com.vega.middlebridge.swig.AddTextAudioParam r8 = new com.vega.middlebridge.swig.AddTextAudioParam
            r8.<init>()
            com.vega.middlebridge.swig.AddAudioParam r11 = new com.vega.middlebridge.swig.AddAudioParam
            r11.<init>()
            r12 = r31
            r11.m(r12)
            com.ss.android.ugc.effectmanager.effect.model.Effect r12 = r9.H0
            if (r12 == 0) goto L25a
            java.lang.String r12 = r12.getResource_id()
        L221:
            r11.v(r12)
            java.lang.String r12 = com.vega.ve.utils.DraftExpandKt.v(r33)
            r11.n(r12)
            r11.B(r2)
            r2 = 0
            r11.A(r2)
            r11.q(r0)
            com.vega.ve.utils.AudioWaveUtils r12 = com.vega.ve.utils.AudioWaveUtils.f135449a
            long r2 = r11.e()
            r12.getClass()
            r12 = r31
            float[] r12 = com.vega.ve.utils.AudioWaveUtils.a(r2, r12)
            int r14 = r12.length
            r3 = 0
        L247:
            if (r3 >= r14) goto L26e
            r2 = r12[r3]
            com.vega.middlebridge.swig.VectorOfDouble r13 = r11.h()
            double r15 = (double) r2
            java.lang.Double r2 = java.lang.Double.valueOf(r15)
            r13.a(r2)
            int r3 = r3 + 1
            goto L247
        L25a:
            r12 = 0
            goto L221
        L25c:
            r8 = 0
            goto L205
        L25e:
            com.vega.middlebridge.swig.TimeRange r0 = r32.k()
            goto L18f
        L264:
            com.vega.middlebridge.swig.TimeRange r0 = r32.k()
            long r2 = r0.e()
            goto L193
        L26e:
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            r11.D(r2)
            r2 = r49
            r11.k(r2)
            r2 = r45
            r11.u(r2)
            r2 = r44
            r11.s(r2)
            r2 = r40
            r11.t(r2)
            r8.f(r11)
            com.vega.middlebridge.swig.SegmentText r2 = com.vega.ve.utils.DraftExpandKt.t(r33)
            java.lang.String r2 = r2.b()
            r8.l(r2)
            r2 = r21
            r8.j(r2)
            r2 = r52
            r8.u(r2)
            r2 = r51
            r8.q(r2)
            r2 = r52
            r8.p(r2)
            com.vega.middlebridge.swig.MapOfStringString r11 = r8.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r11, r4)
            com.vega.middlebridge.swig.SegmentText r2 = com.vega.ve.utils.DraftExpandKt.t(r33)
            java.lang.String r3 = r2.b()
            r2 = r29
            r11.put(r2, r3)
            r2 = r48
            double r2 = (double) r2
            r8.k(r2)
            r2 = r47
            r8.o(r2)
            r2 = r46
            r8.m(r2)
            r2 = r43
            r8.r(r2)
            r2 = r42
            r8.s(r2)
            if (r30 == 0) goto L2de
            r2 = r30
            com.vega.editorapi.util.EmotionUtilsKt.a(r8, r2)
        L2de:
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L2f3
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L2f3
            r2 = r4
        L2f3:
            r8.w(r2)
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L30b
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L30b
            r2 = r4
        L30b:
            r8.h(r2)
            r2 = r39
            r8.v(r2)
            long r2 = r10.f121052d
            long r14 = com.vega.middlebridge.swig.AddTextAudioParam.e(r8)
            r11 = r2
            r13 = r10
            r16 = r8
            com.vega.middlebridge.swig.ReplaceAudioParamModuleJNI.ReplaceAudioParam_text_audio_param_set(r11, r13, r14, r16)
            r7.d(r10)
            r16 = r32
            goto L6c9
        L327:
            com.vega.middlebridge.swig.LVVEMetaType r8 = r32.g()
            com.vega.middlebridge.swig.LVVEMetaType r7 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeText
            if (r8 != r7) goto L44a
            if (r12 == 0) goto Lfa
            r11 = r32
            com.vega.middlebridge.swig.SegmentText r11 = (com.vega.middlebridge.swig.SegmentText) r11
            if (r11 != 0) goto L339
            goto Lfa
        L339:
            com.vega.middlebridge.swig.MapOfStringString r10 = r27.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r4)
            r16 = r32
            java.lang.String r8 = r32.b()
            r7 = r29
            r10.put(r7, r8)
            com.vega.middlebridge.swig.AddTextAudioParam r7 = new com.vega.middlebridge.swig.AddTextAudioParam
            r7.<init>()
            com.vega.middlebridge.swig.AddAudioParam r8 = r7.d()
            r10 = r31
            r8.m(r10)
            com.ss.android.ugc.effectmanager.effect.model.Effect r10 = r9.H0
            if (r10 == 0) goto L39a
            java.lang.String r10 = r10.getResource_id()
        L361:
            r8.v(r10)
            java.lang.String r10 = com.vega.ve.utils.DraftExpandKt.v(r11)
            r8.n(r10)
            r8.B(r2)
            r2 = 0
            r8.A(r2)
            r8.q(r0)
            com.vega.ve.utils.AudioWaveUtils r10 = com.vega.ve.utils.AudioWaveUtils.f135449a
            long r2 = r8.e()
            r10.getClass()
            r10 = r31
            float[] r11 = com.vega.ve.utils.AudioWaveUtils.a(r2, r10)
            int r10 = r11.length
            r3 = 0
        L387:
            if (r3 >= r10) goto L39c
            r12 = r11[r3]
            com.vega.middlebridge.swig.VectorOfDouble r2 = r8.h()
            double r12 = (double) r12
            java.lang.Double r12 = java.lang.Double.valueOf(r12)
            r2.a(r12)
            int r3 = r3 + 1
            goto L387
        L39a:
            r10 = 0
            goto L361
        L39c:
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            r8.D(r2)
            r2 = r49
            r8.k(r2)
            r2 = r45
            r8.u(r2)
            r2 = r41
            r7.t(r2)
            r2 = r44
            r8.s(r2)
            r2 = r40
            r8.t(r2)
            java.lang.String r2 = r32.b()
            r7.l(r2)
            r2 = r21
            r7.j(r2)
            r2 = r52
            r7.u(r2)
            r2 = r51
            r7.q(r2)
            r2 = r52
            r7.p(r2)
            com.vega.middlebridge.swig.MapOfStringString r8 = r7.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r4)
            java.lang.String r3 = r32.b()
            r2 = r29
            r8.put(r2, r3)
            r2 = r48
            double r2 = (double) r2
            r7.k(r2)
            r2 = r47
            r7.o(r2)
            r2 = r46
            r7.m(r2)
            r2 = r43
            r7.r(r2)
            r2 = r42
            r7.s(r2)
            com.ss.android.ugc.effectmanager.effect.model.Effect r2 = r9.H0
            if (r2 == 0) goto L448
            java.lang.String r2 = r2.getResource_id()
        L407:
            r7.i(r2)
            if (r30 == 0) goto L411
            r2 = r30
            com.vega.editorapi.util.EmotionUtilsKt.a(r7, r2)
        L411:
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L426
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L426
            r2 = r4
        L426:
            r7.w(r2)
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L43e
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L43e
            r2 = r4
        L43e:
            r7.h(r2)
            r2 = r39
            r7.v(r2)
            goto L6c9
        L448:
            r2 = 0
            goto L407
        L44a:
            r16 = r32
            com.vega.middlebridge.swig.LVVEMetaType r8 = r32.g()
            com.vega.middlebridge.swig.LVVEMetaType r7 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeLyrics
            if (r8 == r7) goto L45c
            com.vega.middlebridge.swig.LVVEMetaType r8 = r16.g()
            com.vega.middlebridge.swig.LVVEMetaType r7 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeSubtitle
            if (r8 != r7) goto L4b7
        L45c:
            if (r12 == 0) goto Lfa
            r11 = r16
            com.vega.middlebridge.swig.SegmentText r11 = (com.vega.middlebridge.swig.SegmentText) r11
            if (r11 != 0) goto L466
            goto Lfa
        L466:
            com.vega.middlebridge.swig.AddTextAudioParam r7 = new com.vega.middlebridge.swig.AddTextAudioParam
            r7.<init>()
            com.vega.middlebridge.swig.AddAudioParam r8 = r7.d()
            r10 = r31
            r8.m(r10)
            com.ss.android.ugc.effectmanager.effect.model.Effect r10 = r9.H0
            if (r10 == 0) goto L4b5
            java.lang.String r10 = r10.getResource_id()
        L47c:
            r8.v(r10)
            java.lang.String r10 = com.vega.ve.utils.DraftExpandKt.v(r11)
            r8.n(r10)
            r8.B(r2)
            r2 = 0
            r8.A(r2)
            r8.q(r0)
            com.vega.ve.utils.AudioWaveUtils r10 = com.vega.ve.utils.AudioWaveUtils.f135449a
            long r2 = r8.e()
            r10.getClass()
            r10 = r31
            float[] r11 = com.vega.ve.utils.AudioWaveUtils.a(r2, r10)
            int r10 = r11.length
            r3 = 0
        L4a2:
            if (r3 >= r10) goto L61f
            r12 = r11[r3]
            com.vega.middlebridge.swig.VectorOfDouble r2 = r8.h()
            double r12 = (double) r12
            java.lang.Double r12 = java.lang.Double.valueOf(r12)
            r2.a(r12)
            int r3 = r3 + 1
            goto L4a2
        L4b5:
            r10 = 0
            goto L47c
        L4b7:
            com.vega.middlebridge.swig.LVVEMetaType r3 = r16.g()
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextTemplate
            if (r3 == r2) goto L4cf
            com.vega.middlebridge.swig.LVVEMetaType r3 = r16.g()
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextTemplateSubtitle
            if (r3 == r2) goto L4cf
            com.vega.middlebridge.swig.LVVEMetaType r3 = r16.g()
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextTemplateLyrics
            if (r3 != r2) goto L6ed
        L4cf:
            r2 = r16
            boolean r2 = r2 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            if (r2 == 0) goto Lfa
            r8 = r16
            com.vega.middlebridge.swig.SegmentTextTemplate r8 = (com.vega.middlebridge.swig.SegmentTextTemplate) r8
            if (r8 != 0) goto L4dd
            goto Lfa
        L4dd:
            com.vega.middlebridge.swig.TimeRange r2 = r16.k()
            long r12 = r2.e()
            com.vega.middlebridge.swig.MetadataRetriever r2 = com.vega.middlebridge.swig.MetadataRetriever.b()
            r2.c()
            com.vega.middlebridge.swig.MapOfStringString r7 = r27.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r4)
            java.lang.String r3 = r16.b()
            r2 = r29
            r7.put(r2, r3)
            boolean r2 = com.vega.ve.expand.UnifyTextExpandKt.G(r8)
            if (r2 == 0) goto L55f
            java.lang.String r3 = com.vega.ve.expand.UnifyTextExpandKt.y(r8)
        L506:
            if (r3 != 0) goto L509
        L508:
            r3 = r4
        L509:
            com.vega.middlebridge.swig.AddTextAudioParam r7 = new com.vega.middlebridge.swig.AddTextAudioParam
            r7.<init>()
            com.vega.middlebridge.swig.AddAudioParam r8 = r7.d()
            r2 = r31
            r8.m(r2)
            r8.n(r3)
            r8.B(r12)
            com.ss.android.ugc.effectmanager.effect.model.Effect r2 = r9.H0
            if (r2 == 0) goto L55d
            java.lang.String r2 = r2.getResource_id()
        L525:
            r8.v(r2)
            int r2 = r15.f135305a
            long r2 = (long) r2
            long r2 = r2 * r10
            r8.q(r2)
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            r8.D(r2)
            r2 = r49
            r8.k(r2)
            com.vega.middlebridge.swig.VectorOfDouble r12 = r8.h()
            com.vega.ve.utils.AudioWaveUtils r13 = com.vega.ve.utils.AudioWaveUtils.f135449a
            int r2 = r15.f135305a
            long r2 = (long) r2
            long r2 = r2 * r10
            r13.getClass()
            r10 = r31
            float[] r10 = com.vega.ve.utils.AudioWaveUtils.a(r2, r10)
            int r3 = r10.length
            r2 = 0
        L54e:
            if (r2 >= r3) goto L57b
            r11 = r10[r2]
            double r13 = (double) r11
            java.lang.Double r11 = java.lang.Double.valueOf(r13)
            r12.a(r11)
            int r2 = r2 + 1
            goto L54e
        L55d:
            r2 = 0
            goto L525
        L55f:
            com.vega.middlebridge.swig.MaterialTextTemplate r2 = r8.p()
            if (r2 == 0) goto L508
            com.vega.middlebridge.swig.VectorOfTextBindEffectInfo r32 = r2.q()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r32)
            java.lang.String r33 = "/"
            r34 = 0
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1 r37 = new kotlin.jvm.functions.Function1<com.vega.middlebridge.swig.TextBindEffectInfo, java.lang.CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1) com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.vega.middlebridge.swig.TextBindEffectInfo r3) {
                    /*
                        r2 = this;
                        com.vega.middlebridge.swig.TextBindEffectInfo r3 = (com.vega.middlebridge.swig.TextBindEffectInfo) r3
                        com.vega.middlebridge.swig.MaterialText r0 = r3.i()
                        java.lang.String r1 = r0.a0()
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAllTextAudioNewArch$1$audioTitle$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r38 = 30
            r35 = r34
            java.lang.String r3 = kotlin.collections.CollectionsKt.j(r32, r33, r34, r35, r36, r37, r38)
            goto L506
        L57b:
            r2 = r45
            r8.u(r2)
            r2 = r41
            r7.t(r2)
            r2 = r44
            r8.s(r2)
            r2 = r40
            r8.t(r2)
            java.lang.String r2 = r16.b()
            r7.l(r2)
            r2 = r21
            r7.j(r2)
            r2 = r52
            r7.u(r2)
            r2 = r51
            r7.q(r2)
            r2 = r52
            r7.p(r2)
            com.vega.middlebridge.swig.MapOfStringString r8 = r7.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r4)
            java.lang.String r3 = r16.b()
            r2 = r29
            r8.put(r2, r3)
            r2 = r48
            double r2 = (double) r2
            r7.k(r2)
            r2 = r47
            r7.o(r2)
            r2 = r46
            r7.m(r2)
            r2 = r43
            r7.r(r2)
            r2 = r42
            r7.s(r2)
            com.ss.android.ugc.effectmanager.effect.model.Effect r2 = r9.H0
            if (r2 == 0) goto L61d
            java.lang.String r2 = r2.getResource_id()
        L5dc:
            r7.i(r2)
            if (r30 == 0) goto L5e6
            r2 = r30
            com.vega.editorapi.util.EmotionUtilsKt.a(r7, r2)
        L5e6:
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L5fb
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L5fb
            r2 = r4
        L5fb:
            r7.w(r2)
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L613
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L613
            r2 = r4
        L613:
            r7.h(r2)
            r2 = r39
            r7.v(r2)
            goto L6c9
        L61d:
            r2 = 0
            goto L5dc
        L61f:
            com.vega.middlebridge.swig.LVVEMetaType r2 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            r8.D(r2)
            r2 = r49
            r8.k(r2)
            r2 = r45
            r8.u(r2)
            r2 = r41
            r7.t(r2)
            r2 = r44
            r8.s(r2)
            r2 = r40
            r8.t(r2)
            java.lang.String r2 = r16.b()
            r7.l(r2)
            r2 = r21
            r7.j(r2)
            r2 = r52
            r7.u(r2)
            r2 = r51
            r7.q(r2)
            r2 = r52
            r7.p(r2)
            com.vega.middlebridge.swig.MapOfStringString r8 = r7.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r4)
            java.lang.String r3 = r16.b()
            r2 = r29
            r8.put(r2, r3)
            r2 = r48
            double r2 = (double) r2
            r7.k(r2)
            r2 = r47
            r7.o(r2)
            r2 = r46
            r7.m(r2)
            r2 = r43
            r7.r(r2)
            r2 = r42
            r7.s(r2)
            com.ss.android.ugc.effectmanager.effect.model.Effect r2 = r9.H0
            if (r2 == 0) goto L72a
            java.lang.String r2 = r2.getResource_id()
        L68a:
            r7.i(r2)
            if (r30 == 0) goto L694
            r2 = r30
            com.vega.editorapi.util.EmotionUtilsKt.a(r7, r2)
        L694:
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L6a9
            java.util.List<java.lang.String> r2 = r6.e
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L6a9
            r2 = r4
        L6a9:
            r7.w(r2)
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.getOrNull(r2, r5)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L6c1
            java.util.List<java.lang.String> r2 = r6.f
            java.lang.Object r2 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r2)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 != 0) goto L6c1
            r2 = r4
        L6c1:
            r7.h(r2)
            r2 = r39
            r7.v(r2)
        L6c9:
            com.vega.middlebridge.swig.MapOfStringString r8 = r27.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r4)
            java.lang.String r5 = "TONE_TYPE_REPLACE"
            java.lang.String r10 = "TONE_TYPE_ADD"
            if (r21 == 0) goto L728
            r3 = r5
        L6d7:
            java.lang.String r2 = "tone_type"
            r8.put(r2, r3)
            com.vega.middlebridge.swig.MapOfStringString r3 = r7.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            if (r21 == 0) goto L726
        L6e5:
            r3.put(r2, r5)
            r2 = r24
            r2.add(r7)
        L6ed:
            if (r61 == 0) goto Lfa
            com.vega.middlebridge.swig.UpdateTimeRangeParam r5 = new com.vega.middlebridge.swig.UpdateTimeRangeParam
            r5.<init>()
            java.lang.String r2 = r16.b()
            r5.j(r2)
            com.vega.middlebridge.swig.TimeRange r2 = r16.k()
            long r2 = r2.e()
            long r2 = r2 + r0
            r5.f(r2)
            if (r62 == 0) goto L716
            long r2 = r5.e()
            r0 = r17
            long r0 = java.lang.Math.min(r0, r2)
            r5.f(r0)
        L716:
            com.vega.middlebridge.swig.LVVEClipType r0 = com.vega.middlebridge.swig.LVVEClipType.ClipDuration
            r5.g(r0)
            r0 = 1
            r5.h(r0)
            r0 = r26
            r0.add(r5)
            goto Lfa
        L726:
            r5 = r10
            goto L6e5
        L728:
            r3 = r10
            goto L6d7
        L72a:
            r2 = 0
            goto L68a
        L72d:
            com.vega.container.session.core.ISession r0 = r9.o
            com.vega.container.session.core.ISessionScene r1 = r0.C0()
            com.vega.editorapi.session.SessionScene r0 = com.vega.editorapi.session.SessionScene.f97670d
            if (r1 != r0) goto L975
            java.util.List r0 = v7(r50)
        L73b:
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Set r7 = kotlin.collections.CollectionsKt___CollectionsKt.toSet(r0)
            com.vega.middlebridge.swig.Draft r1 = com.vega.middlebridge.client.DraftClient.q(r19)
            if (r1 == 0) goto L755
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            if (r21 == 0) goto L96f
        L74c:
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r4)
            boolean r0 = r23.isEmpty()
            if (r0 == 0) goto L771
        L755:
            r11 = 0
        L756:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r24.iterator()
        L75f:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L97b
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.AddTextAudioParam
            if (r0 == 0) goto L75f
            r3.add(r1)
            goto L75f
        L771:
            com.vega.middlebridge.swig.VectorOfTrack r0 = r1.v()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r5 = r0.iterator()
        L77e:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L797
            java.lang.Object r3 = r5.next()
            r0 = r3
            com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
            com.vega.middlebridge.swig.LVVETrackType r1 = r0.f()
            com.vega.middlebridge.swig.LVVETrackType r0 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeAudio
            if (r1 != r0) goto L77e
            r2.add(r3)
            goto L77e
        L797:
            java.util.LinkedHashSet r10 = new java.util.LinkedHashSet
            r10.<init>()
            java.util.Iterator r13 = r2.iterator()
            r8 = 0
        L7a1:
            boolean r0 = r13.hasNext()
            r2 = 10
            if (r0 == 0) goto L840
            java.lang.Object r1 = r13.next()
            int r12 = r8 + 1
            if (r8 >= 0) goto L7b4
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L7b4:
            com.vega.middlebridge.swig.Track r1 = (com.vega.middlebridge.swig.Track) r1
            com.vega.middlebridge.swig.VectorOfSegment r0 = r1.e()
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L7cf
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r1 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1.<init>(r8, r0)
            r10.add(r1)
        L7cd:
            r8 = r12
            goto L7a1
        L7cf:
            com.vega.middlebridge.swig.VectorOfSegment r0 = r1.e()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r5 = r0.iterator()
        L7dc:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L7f9
            java.lang.Object r1 = r5.next()
            r0 = r1
            com.vega.middlebridge.swig.Node r0 = (com.vega.middlebridge.swig.Node) r0
            java.lang.String r0 = r0.b()
            boolean r0 = r7.contains(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L7dc
            r3.add(r1)
            goto L7dc
        L7f9:
            java.util.List r0 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r3)
            if (r0 == 0) goto L7cd
            java.util.ArrayList r6 = new java.util.ArrayList
            int r1 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r0, r2)
            r6.<init>(r1)
            java.util.Iterator r11 = r0.iterator()
        L80c:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L831
            java.lang.Object r0 = r11.next()
            com.vega.middlebridge.swig.Segment r0 = (com.vega.middlebridge.swig.Segment) r0
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r5 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange
            com.vega.middlebridge.swig.TimeRange r1 = r0.k()
            long r2 = r1.e()
            com.vega.middlebridge.swig.TimeRange r0 = r0.k()
            long r0 = r0.d()
            r5.<init>(r2, r0)
            r6.add(r5)
            goto L80c
        L831:
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.toMutableList(r6)
            if (r1 == 0) goto L7cd
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo
            r0.<init>(r8, r1)
            r10.add(r0)
            goto L7cd
        L840:
            java.util.ArrayList r11 = new java.util.ArrayList
            r11.<init>()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r23)
            java.util.Iterator r20 = r23.iterator()
        L84c:
            boolean r0 = r20.hasNext()
            if (r0 == 0) goto L962
            java.lang.Object r13 = r20.next()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r13 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.CMTimeRange) r13
            boolean r0 = r10.isEmpty()
            if (r0 == 0) goto L879
            r1 = 0
        L85f:
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo
            r0.<init>(r1)
            r11.add(r0)
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r2 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo
            r0 = 1
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange[] r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel.CMTimeRange[r0]
            r0[r36] = r13
            java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsKt.mutableListOf(r0)
            r2.<init>(r1, r0)
            r10.add(r2)
            goto L84c
        L879:
            java.util.Iterator r18 = r10.iterator()
        L87d:
            boolean r0 = r18.hasNext()
            if (r0 == 0) goto L912
            java.lang.Object r0 = r18.next()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.TrackInfo) r0
            int r12 = r0.f74649a
            java.util.List<com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange> r2 = r0.b
            if (r2 == 0) goto L8bf
            boolean r1 = r2.isEmpty()
            r0 = 1
            if (r1 != r0) goto L8bf
        L896:
            java.lang.Integer r5 = java.lang.Integer.valueOf(r12)
        L89a:
            java.util.ArrayList r1 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r10, r0)
            r1.<init>(r0)
            java.util.Iterator r2 = r10.iterator()
        L8a9:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L914
            java.lang.Object r0 = r2.next()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.TrackInfo) r0
            int r0 = r0.f74649a
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r1.add(r0)
            goto L8a9
        L8bf:
            long r7 = r13.f74646a
            long r5 = r13.b
            long r5 = r5 + r7
            if (r2 == 0) goto L8d5
            int r1 = r2.size()
            r0 = 1
            if (r1 <= r0) goto L8d5
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$canInsertThisTrack$$inlined$sortBy$1 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$canInsertThisTrack$$inlined$sortBy$1
            r0.<init>()
            kotlin.collections.CollectionsKt__MutableCollectionsJVMKt.sortWith(r2, r0)
        L8d5:
            r16 = -1
            if (r2 == 0) goto L90d
            java.util.Iterator r15 = r2.iterator()
        L8dd:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L90d
            java.lang.Object r0 = r15.next()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.CMTimeRange) r0
            long r2 = r0.f74646a
            long r0 = r0.b
            long r0 = r0 + r2
            int r14 = (r2 > r7 ? 1 : (r2 == r7 ? 0 : -1))
            if (r14 < 0) goto L8f7
            int r14 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r14 >= 0) goto L8f7
            goto L87d
        L8f7:
            int r14 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r14 <= 0) goto L901
            int r14 = (r0 > r5 ? 1 : (r0 == r5 ? 0 : -1))
            if (r14 > 0) goto L901
            goto L87d
        L901:
            int r14 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r14 > 0) goto L90a
            int r14 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
            if (r14 < 0) goto L90a
            goto L896
        L90a:
            r16 = r0
            goto L8dd
        L90d:
            int r0 = (r16 > r7 ? 1 : (r16 == r7 ? 0 : -1))
            if (r0 > 0) goto L87d
            goto L896
        L912:
            r5 = 0
            goto L89a
        L914:
            java.lang.Comparable r0 = kotlin.collections.CollectionsKt___CollectionsKt.maxOrNull(r1)
            java.lang.Integer r0 = (java.lang.Integer) r0
            if (r0 == 0) goto L957
            int r0 = r0.intValue()
        L920:
            int r1 = r0 + 1
            if (r5 == 0) goto L95b
            r5.intValue()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r1 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo
            int r0 = r5.intValue()
            r1.<init>(r0)
            r11.add(r1)
            java.util.Iterator r3 = r10.iterator()
        L937:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto Lb60
            java.lang.Object r2 = r3.next()
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$TrackInfo r2 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.TrackInfo) r2
            int r1 = r2.f74649a
            r0 = -1
            if (r1 == r0) goto L937
            int r0 = r5.intValue()
            if (r1 != r0) goto L937
            java.util.List<com.vega.audio.tone.viewmodel.ToneSelectViewModel$CMTimeRange> r0 = r2.b
            if (r0 == 0) goto L84c
            r0.add(r13)
            goto L84c
        L957:
            r0 = 2147483647(0x7fffffff, float:NaN)
            goto L920
        L95b:
            r0 = 2147483647(0x7fffffff, float:NaN)
            if (r1 > r0) goto L84c
            goto L85f
        L962:
            int r1 = r23.size()
            int r0 = r11.size()
            int r1 = r1 - r0
            if (r1 != 0) goto L755
            goto L756
        L96f:
            java.util.Set r7 = kotlin.collections.SetsKt__SetsKt.emptySet()
            goto L74c
        L975:
            java.util.Set r0 = X6(r9, r7)
            goto L73b
        L97b:
            java.util.Iterator r6 = r3.iterator()
            r0 = 0
        L980:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto La11
            java.lang.Object r2 = r6.next()
            int r5 = r0 + 1
            if (r0 >= 0) goto L991
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L991:
            com.vega.middlebridge.swig.AddTextAudioParam r2 = (com.vega.middlebridge.swig.AddTextAudioParam) r2
            com.vega.middlebridge.swig.IQueryUtils r30 = com.vega.middlebridge.client.DraftClient.p(r19)
            if (r30 == 0) goto La0f
            kotlin.jvm.internal.Intrinsics.checkNotNull(r30)
            com.vega.middlebridge.swig.LVVETrackType r1 = com.vega.middlebridge.swig.LVVETrackType.TrackTypeAudio
            java.util.List r31 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r1)
            com.vega.middlebridge.swig.AddAudioParam r1 = r2.d()
            long r32 = r1.g()
            com.vega.middlebridge.swig.AddAudioParam r1 = r2.d()
            long r34 = r1.e()
            r37 = 0
            r38 = 24
            r36 = r36
            int r3 = com.vega.ve.utils.IQueryUtilsExKt.b(r30, r31, r32, r34, r36, r37, r38)
        L9bc:
            com.vega.middlebridge.swig.AddAudioParam r1 = r2.d()
            if (r11 == 0) goto L9cc
            java.lang.Object r0 = r11.get(r0)
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$InsertInfo r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel.InsertInfo) r0
            if (r0 == 0) goto L9cc
            int r3 = r0.f74648a
        L9cc:
            r1.C(r3)
            com.vega.middlebridge.swig.AddAudioParam r3 = r2.d()
            long r0 = r3.f115498d
            boolean r0 = com.vega.middlebridge.swig.AddAudioParamModuleJNI.AddAudioParam_is_ai_clone_tone_get(r0, r3)
            if (r0 == 0) goto L9e9
            com.vega.middlebridge.swig.MapOfStringString r3 = r2.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            java.lang.String r1 = "export_key_feature_key"
            java.lang.String r0 = "voice_clone"
            r3.put(r1, r0)
        L9e9:
            r0 = r79
            if (r0 == 0) goto L9ff
            java.lang.String r3 = "__tag_action_feature_id__"
            java.lang.Object r1 = r0.get(r3)
            if (r1 == 0) goto L9ff
            com.vega.middlebridge.swig.MapOfStringString r0 = r2.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            r0.put(r3, r1)
        L9ff:
            com.vega.middlebridge.swig.AddTextAudioReqStruct r1 = new com.vega.middlebridge.swig.AddTextAudioReqStruct
            r1.<init>()
            r1.setParams(r2)
            r0 = r25
            r0.add(r1)
            r0 = r5
            goto L980
        La0f:
            r3 = -1
            goto L9bc
        La11:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r24.iterator()
        La1a:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto La2c
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.UpdateTextCompositionParam
            if (r0 == 0) goto La1a
            r3.add(r1)
            goto La1a
        La2c:
            java.util.Iterator r2 = r3.iterator()
        La30:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto La4a
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.UpdateTextCompositionParam r0 = (com.vega.middlebridge.swig.UpdateTextCompositionParam) r0
            com.vega.middlebridge.swig.UpdateTextCompositionReqStruct r1 = new com.vega.middlebridge.swig.UpdateTextCompositionReqStruct
            r1.<init>()
            r1.setParams(r0)
            r0 = r25
            r0.add(r1)
            goto La30
        La4a:
            java.util.Iterator r3 = r26.iterator()
        La4e:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto La71
            java.lang.Object r0 = r3.next()
            int r2 = r36 + 1
            if (r36 >= 0) goto La5f
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        La5f:
            com.vega.middlebridge.swig.UpdateTimeRangeParam r0 = (com.vega.middlebridge.swig.UpdateTimeRangeParam) r0
            com.vega.middlebridge.swig.UpdateTimeRangeSegmentReqStruct r1 = new com.vega.middlebridge.swig.UpdateTimeRangeSegmentReqStruct
            r1.<init>()
            r1.setParams(r0)
            r0 = r25
            r0.add(r1)
            r36 = r2
            goto La4e
        La71:
            com.ss.android.ugc.effectmanager.effect.model.Effect r0 = r9.H0
            if (r0 == 0) goto La9c
            com.vega.edit.base.vipmaterial.VipMaterialUtils r29 = com.vega.edit.base.vipmaterial.VipMaterialUtils.f89614a
            com.vega.container.session.core.ISession r1 = r9.o
            com.vega.subscriptionapi.legacy.attachment.IBusiness r30 = com.vega.edit.base.session.ISessionBusinessBridgeKt.a(r1)
            com.ss.android.ugc.effectmanager.effect.model.Effect r1 = r9.H0
            if (r1 == 0) goto Lab3
            java.lang.String r32 = com.vega.effectplatform.loki.EffectExKt.p(r1)
        La85:
            com.ss.android.ugc.effectmanager.effect.model.Effect r1 = r9.H0
            if (r1 == 0) goto Lab0
            java.lang.String r33 = com.vega.effectplatform.loki.EffectExKt.r(r1)
        La8d:
            com.vega.middlebridge.swig.LVVEMetaType r34 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            r35 = 0
            r38 = 224(0xe0, float:3.14E-43)
            r36 = r35
            r37 = r35
            r31 = r0
            com.vega.edit.base.vipmaterial.VipMaterialUtils.b(r29, r30, r31, r32, r33, r34, r35, r36, r37, r38)
        La9c:
            java.util.Iterator r1 = r24.iterator()
        Laa0:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto Lab6
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.ActionParam r0 = (com.vega.middlebridge.swig.ActionParam) r0
            r0.a()
            goto Laa0
        Lab0:
            r33 = 0
            goto La8d
        Lab3:
            r32 = 0
            goto La85
        Lab6:
            java.util.Iterator r1 = r26.iterator()
        Laba:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto Laca
            java.lang.Object r0 = r1.next()
            com.vega.middlebridge.swig.ActionParam r0 = (com.vega.middlebridge.swig.ActionParam) r0
            r0.a()
            goto Laba
        Laca:
            com.vega.middlebridge.swig.MapOfStringString r2 = r27.b()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            java.lang.String r1 = "ai_writer_from"
            java.lang.String r0 = r9.m0
            r2.put(r1, r0)
            r9.m0 = r4
            r2 = r19
            r1 = r27
            r0 = r25
            com.vega.middlebridge.swig.DraftRespStruct r0 = com.vega.middlebridge.client.DraftClient.k(r2, r1, r0)
            if (r0 == 0) goto Lb40
            com.vega.middlebridge.swig.EditResult r6 = r0.c()
        Laea:
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            if (r6 == 0) goto Lb42
            com.vega.middlebridge.swig.VectorNodes r0 = r6.d()
            if (r0 == 0) goto Lb42
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            java.util.Iterator r3 = r0.iterator()
        Lafe:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto Lb42
            java.lang.Object r2 = r3.next()
            com.vega.middlebridge.swig.ChangedNode r2 = (com.vega.middlebridge.swig.ChangedNode) r2
            com.vega.middlebridge.swig.ChangedNode$Type r1 = r2.b()
            com.vega.middlebridge.swig.ChangedNode$Type r0 = com.vega.middlebridge.swig.ChangedNode.Type.add
            if (r1 != r0) goto Lb30
            com.vega.container.session.core.ISession r1 = r9.o
            java.lang.String r0 = r2.a()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            com.vega.middlebridge.swig.Segment r0 = r1.i(r0)
            if (r0 == 0) goto Lb3e
            com.vega.middlebridge.swig.LVVEMetaType r1 = r0.g()
        Lb25:
            com.vega.middlebridge.swig.LVVEMetaType r0 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextToAudio
            if (r1 != r0) goto Lb30
            java.lang.String r0 = r2.a()
            r5.add(r0)
        Lb30:
            r6.b()
            r2.a()
            com.vega.middlebridge.swig.ChangedNode$Type r0 = r2.b()
            java.util.Objects.toString(r0)
            goto Lafe
        Lb3e:
            r1 = 0
            goto Lb25
        Lb40:
            r6 = 0
            goto Laea
        Lb42:
            com.vega.container.session.core.ISession r0 = r9.o
            com.vega.container.session.core.ISessionScene r1 = r0.C0()
            com.vega.editorapi.session.SessionScene r0 = com.vega.editorapi.session.SessionScene.f97670d
            if (r1 != r0) goto Lb51
            r1 = 0
            r0 = 1
            r9.N6(r1, r0)
        Lb51:
            r0 = r28
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$realSaveAudio$1$saveHandler$1$3 r0 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel$saveAudio$realSaveAudio$1$saveHandler$1.AnonymousClass3) r0
            r28 = r0
            r28.invoke()
            goto Lc7
        Lb5c:
            java.lang.String r1 = "ADD_TEXT_AUDIO_ACTION"
            goto L66
        Lb60:
            java.util.NoSuchElementException r1 = new java.util.NoSuchElementException
            java.lang.String r0 = "Collection contains no element matching the predicate."
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.R7(java.util.List, boolean, java.lang.String, java.lang.String, java.util.List, java.lang.String, boolean, boolean, boolean, boolean, float, java.lang.String, java.lang.String, com.vega.middlebridge.swig.LVVEMetaType, java.lang.String, boolean, java.lang.String, java.lang.String, java.lang.String, boolean, com.vega.edit.base.utils.RecommendCapabilityViewInfo, com.lemon.lv.data.Emotion, com.vega.edit.base.audio.tone.TextToAudioInfoPack, java.lang.String, java.util.Map, kotlin.jvm.functions.Function0):void");
    }

    public final void S6(Object obj, String str) {
        Intrinsics.checkNotNullParameter(obj, "");
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getMain(), null, new ToneSelectViewModel$emitUiState$1("EVENT_TONE_ITEM_CLICKED", obj, this, null), 2, null);
    }

    public final void S7(boolean z, IProgressDialogController iProgressDialogController, String str, String str2, String str3, String str4, boolean z2, String str5, List<String> list, String str6, boolean z3, boolean z4, Function2<? super Boolean, ? super TextToAudioInfoPack, Unit> function2) {
        Intrinsics.checkNotNullParameter(iProgressDialogController, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        T7(this, z, iProgressDialogController, str, str2, str3, str4, null, 0.0f, 0, null, null, str5, z2, null, list, null, str6, false, null, z3, z4, null, null, 0L, null, null, null, false, null, false, null, null, false, null, null, false, null, null, null, function2, -106299456, 32767);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0083  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<java.lang.String> T6(java.util.List<? extends com.vega.middlebridge.swig.Segment> r14) {
        /*
            r13 = this;
            r7 = 0
            if (r14 == 0) goto L86
            java.util.ArrayList r3 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r14, r0)
            r3.<init>(r0)
            java.util.Iterator r5 = r14.iterator()
        L12:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L85
            java.lang.Object r1 = r5.next()
            com.vega.middlebridge.swig.Segment r1 = (com.vega.middlebridge.swig.Segment) r1
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L2c
            com.vega.middlebridge.swig.SegmentText r1 = (com.vega.middlebridge.swig.SegmentText) r1
            java.lang.String r0 = com.vega.ve.utils.DraftExpandKt.v(r1)
        L28:
            r3.add(r0)
            goto L12
        L2c:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            java.lang.String r4 = ""
            if (r0 == 0) goto L83
            com.vega.middlebridge.swig.SegmentTextTemplate r1 = (com.vega.middlebridge.swig.SegmentTextTemplate) r1
            if (r1 == 0) goto L83
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r1.p()
            if (r0 == 0) goto L83
            com.vega.middlebridge.swig.VectorOfTextBindEffectInfo r1 = r0.q()
            boolean r0 = r1.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L83
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r2 = r1.iterator()
        L51:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L75
            java.lang.Object r1 = r2.next()
            r0 = r1
            com.vega.middlebridge.swig.TextBindEffectInfo r0 = (com.vega.middlebridge.swig.TextBindEffectInfo) r0
            com.vega.middlebridge.swig.MaterialText r0 = r0.i()
            java.lang.String r0 = r0.a0()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r4)
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L51
            r6.add(r1)
            goto L51
        L75:
            r10 = 0
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3 r11 = new kotlin.jvm.functions.Function1<com.vega.middlebridge.swig.TextBindEffectInfo, java.lang.CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3) com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.vega.middlebridge.swig.TextBindEffectInfo r3) {
                    /*
                        r2 = this;
                        com.vega.middlebridge.swig.TextBindEffectInfo r3 = (com.vega.middlebridge.swig.TextBindEffectInfo) r3
                        com.vega.middlebridge.swig.MaterialText r0 = r3.i()
                        java.lang.String r1 = r0.a0()
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$extractMutableTextFromSegments$1$3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r12 = 31
            r8 = r7
            r9 = r7
            java.lang.String r0 = kotlin.collections.CollectionsKt.j(r6, r7, r8, r9, r10, r11, r12)
            if (r0 == 0) goto L83
            goto L28
        L83:
            r0 = r4
            goto L28
        L85:
            r7 = r3
        L86:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.T6(java.util.List):java.util.List");
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object U6(java.util.List<? extends kotlin.Pair<java.lang.String, ? extends com.vega.middlebridge.swig.LVVEEffectSourcePlatformType>> r6, kotlin.coroutines.Continuation<? super java.util.List<? extends com.ss.android.ugc.effectmanager.effect.model.Effect>> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.viewmodel.ToneSelectViewModel$fetchEffects$1
            if (r0 == 0) goto L42
            r4 = r7
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$fetchEffects$1 r4 = (com.vega.audio.tone.viewmodel.ToneSelectViewModel$fetchEffects$1) r4
            int r2 = r4.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L42
            int r2 = r2 - r1
            r4.s = r2
        L12:
            java.lang.Object r2 = r4.q
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.s
            r0 = 1
            if (r1 == 0) goto L30
            if (r1 != r0) goto L48
            kotlin.ResultKt.throwOnFailure(r2)
        L22:
            r0 = r2
            java.util.List r0 = (java.util.List) r0
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L2e
        L2d:
            return r2
        L2e:
            r2 = 0
            goto L2d
        L30:
            kotlin.ResultKt.throwOnFailure(r2)
            com.vega.effectplatform.artist.ArtisPlatformEffectManager r2 = com.vega.effectplatform.artist.ArtisPlatformEffectManager.f98382a
            com.vega.effectplatform.artist.Constants$EffectType r1 = com.vega.effectplatform.artist.Constants.EffectType.r
            r4.s = r0
            java.lang.String r0 = "tone"
            java.lang.Object r2 = r2.o(r6, r0, r1, r4)
            if (r2 != r3) goto L22
            return r3
        L42:
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$fetchEffects$1 r4 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$fetchEffects$1
            r4.<init>(r5, r7)
            goto L12
        L48:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.U6(java.util.List, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void U7(String str, Emotion emotion) {
        if (str == null || str.length() == 0 || emotion == null) {
            return;
        }
        this.X0.put(str, emotion);
    }

    public final void V7(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        this.C = str;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r0v11, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    public final void W7(int i) {
        List<EffectCategoryModel> list;
        EffectCategoryModel effectCategoryModel;
        MutableLiveData mutableLiveData = this.C0;
        Intrinsics.checkNotNull(mutableLiveData, "");
        Integer num = (Integer) mutableLiveData.getValue();
        String strK = null;
        if ((num == null || num.intValue() != i) && i == 1) {
            LiveData<Integer> liveData = this.B0;
            Intrinsics.checkNotNull(liveData, "");
            Integer value = liveData.getValue();
            if (value != null && value.intValue() == 0 && ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccount().isLogin()) {
                P7("show", this.l1);
            }
        }
        MutableLiveData mutableLiveData2 = this.C0;
        Intrinsics.checkNotNull(mutableLiveData2, "");
        mutableLiveData2.setValue(Integer.valueOf(i));
        StringBuilder sb = new StringBuilder("last_selected_category_mine_");
        Draft draftL = this.o.l();
        sb.append(draftL != null ? draftL.b() : null);
        String string = sb.toString();
        CategoryListState categoryListState = (CategoryListState) this.x0.getValue();
        if (categoryListState != null && (list = categoryListState.b) != null && (effectCategoryModel = (EffectCategoryModel) CollectionsKt___CollectionsKt.getOrNull(list, i)) != null) {
            strK = EffectExtendKt.k(effectCategoryModel);
        }
        Z7(string, strK);
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void X4() {
        TextToAudioManager.f73896a.getClass();
        TextToAudioManager.d();
    }

    public void X7(Segment segment) {
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00aa  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00b2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ca A[PHI: r2
      0x00ca: PHI (r2v4 boolean) = (r2v2 boolean), (r2v6 boolean), (r2v6 boolean) binds: [B:55:0x00c9, B:34:0x0086, B:38:0x0096] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00a6 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00ae A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<com.vega.middlebridge.swig.Segment> Y6(com.vega.middlebridge.swig.LVVEMetaType r9) {
        /*
            r8 = this;
            com.vega.container.session.core.ISession r0 = r8.o
            com.vega.middlebridge.lyrasession.LyraSession r0 = r0.b()
            r1 = 0
            if (r0 == 0) goto Ld2
            com.vega.middlebridge.swig.Draft r0 = com.vega.middlebridge.client.DraftClient.q(r0)
            if (r0 == 0) goto Ld2
            com.vega.middlebridge.swig.VectorOfTrack r0 = r0.v()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        L1c:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L35
            java.lang.Object r0 = r2.next()
            com.vega.middlebridge.swig.Track r0 = (com.vega.middlebridge.swig.Track) r0
            com.vega.middlebridge.swig.VectorOfSegment r1 = r0.e()
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            kotlin.collections.CollectionsKt__MutableCollectionsKt.addAll(r3, r1)
            goto L1c
        L35:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r7 = r3.iterator()
        L3e:
            boolean r0 = r7.hasNext()
            if (r0 == 0) goto Lce
            java.lang.Object r3 = r7.next()
            r6 = r3
            com.vega.middlebridge.swig.Segment r6 = (com.vega.middlebridge.swig.Segment) r6
            boolean r0 = r6 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L77
            boolean r0 = com.vega.ve.utils.DraftExpandKt.K(r6)
            if (r0 == 0) goto L5f
            r0 = r6
            com.vega.middlebridge.swig.SegmentText r0 = (com.vega.middlebridge.swig.SegmentText) r0
            boolean r0 = com.vega.ve.utils.DraftExpandKt.M(r0)
            if (r0 != 0) goto L5f
            goto L3e
        L5f:
            r0 = r6
            com.vega.middlebridge.swig.SegmentText r0 = (com.vega.middlebridge.swig.SegmentText) r0
            if (r0 == 0) goto L6b
            com.vega.middlebridge.swig.SegmentText r5 = com.vega.ve.utils.DraftExpandKt.q(r0)
            if (r5 == 0) goto L6b
            goto L78
        L6b:
            r0 = r6
            com.vega.middlebridge.swig.SegmentText r0 = (com.vega.middlebridge.swig.SegmentText) r0
            if (r0 == 0) goto L77
            com.vega.middlebridge.swig.SegmentTextTemplate r5 = com.vega.ve.utils.DraftExpandKt.r(r0)
            if (r5 == 0) goto L77
            goto L78
        L77:
            r5 = r6
        L78:
            boolean r0 = com.vega.ve.expand.UnifyTextExpandKt.J(r9)
            if (r0 == 0) goto Lb2
            com.vega.middlebridge.swig.LVVEMetaType r0 = r5.g()
            boolean r2 = com.vega.ve.expand.UnifyTextExpandKt.J(r0)
        L86:
            if (r9 != 0) goto Lca
            com.vega.middlebridge.swig.LVVEMetaType r1 = r5.g()
            com.vega.middlebridge.swig.LVVEMetaType r0 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeText
            if (r1 == r0) goto L98
            com.vega.middlebridge.swig.LVVEMetaType r1 = r5.g()
            com.vega.middlebridge.swig.LVVEMetaType r0 = com.vega.middlebridge.swig.LVVEMetaType.MetaTypeTextTemplate
            if (r1 != r0) goto Lca
        L98:
            r1 = 1
        L99:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r6)
            boolean r0 = com.vega.ve.utils.DraftExpandKt.K(r6)
            if (r0 != 0) goto La6
            if (r2 != 0) goto La6
            if (r1 == 0) goto L3e
        La6:
            boolean r0 = r5 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 != 0) goto Lae
            boolean r0 = r5 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            if (r0 == 0) goto L3e
        Lae:
            r4.add(r3)
            goto L3e
        Lb2:
            boolean r0 = com.vega.ve.expand.UnifyTextExpandKt.F(r9)
            if (r0 == 0) goto Lc1
            com.vega.middlebridge.swig.LVVEMetaType r0 = r5.g()
            boolean r2 = com.vega.ve.expand.UnifyTextExpandKt.F(r0)
            goto L86
        Lc1:
            if (r9 == 0) goto Lcc
            com.vega.middlebridge.swig.LVVEMetaType r0 = r5.g()
            if (r0 != r9) goto Lcc
            r2 = 1
        Lca:
            r1 = 0
            goto L99
        Lcc:
            r2 = 0
            goto L86
        Lce:
            java.util.List r1 = kotlin.collections.CollectionsKt___CollectionsKt.toList(r4)
        Ld2:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.Y6(com.vega.middlebridge.swig.LVVEMetaType):java.util.List");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v18, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    public final void Y7(int i) {
        List<EffectCategoryModel> list;
        EffectCategoryModel effectCategoryModel;
        String strK = null;
        if (i == 0) {
            LiveData<Integer> liveData = this.B0;
            Intrinsics.checkNotNull(liveData, "");
            Integer value = liveData.getValue();
            if (value == null || value.intValue() != i) {
                MutableLiveData mutableLiveData = this.C0;
                Intrinsics.checkNotNull(mutableLiveData, "");
                Integer num = (Integer) mutableLiveData.getValue();
                if (num != null && num.intValue() == 1 && ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccount().isLogin()) {
                    P7("show", this.l1);
                }
            }
        }
        LiveData<Integer> liveData2 = this.B0;
        Intrinsics.checkNotNull(liveData2, "");
        liveData2.setValue(Integer.valueOf(i));
        StringBuilder sb = new StringBuilder("last_selected_category_");
        Draft draftL = this.o.l();
        sb.append(draftL != null ? draftL.b() : null);
        String string = sb.toString();
        CategoryListState value2 = c7().getValue();
        if (value2 != null && (list = value2.b) != null && (effectCategoryModel = (EffectCategoryModel) CollectionsKt___CollectionsKt.getOrNull(list, i)) != null) {
            strK = EffectExtendKt.k(effectCategoryModel);
        }
        Z7(string, strK);
    }

    public final void Z7(String str, String str2) {
        List<EffectCategoryModel> list;
        Integer numValueOf = null;
        if (!(str2 == null || str2.length() == 0)) {
            BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getDefault(), null, new ToneSelectViewModel$setStorageCategoryIndex$1(this, str, str2, null), 2, null);
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2);
        sb.append(" is null. size: ");
        CategoryListState value = c7().getValue();
        if (value != null && (list = value.b) != null) {
            numValueOf = Integer.valueOf(list.size());
        }
        sb.append(numValueOf);
        BLog.e("ToneSelectViewModel", sb.toString());
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void a1(ToneType toneType, String str, String str2, float f, int i, String str3, boolean z, boolean z2, boolean z3, Object obj, Object obj2, final Function0<Unit> function0, boolean z4, boolean z5, boolean z6, boolean z7) {
        String strSubstring = str3;
        Intrinsics.checkNotNullParameter(toneType, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        if (!NetUtils.b(ModuleCommon.INSTANCE.getApplication())) {
            ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$startReading$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    ToastUtilKt.d(R.string.qk__res_0x7f122c0c, 0, 0, 0, 0, 252);
                    Function0<Unit> function02 = function0;
                    if (function02 != null) {
                        function02.invoke();
                    }
                    return Unit.INSTANCE;
                }
            });
            return;
        }
        Emotion defaultEmotion = this.X0.get(str);
        if (defaultEmotion == null) {
            defaultEmotion = toneType.getDefaultEmotion();
        }
        EmotionOption.h.getClass();
        EmotionOption emotionOptionA = EmotionOption.Companion.a(defaultEmotion);
        if (strSubstring == null && (strSubstring = p7(toneType.getCloneToneLanguage(), z, z2)) == null) {
            return;
        }
        this.R = f;
        this.z.setValue(str);
        this.F.setValue(toneType);
        this.C = str2;
        boolean z8 = Intrinsics.areEqual(toneType.getPlatform(), "moyin") || CollectionsKt___CollectionsKt.contains((List) this.f1.getValue(), obj2) || ((ToneCommercialOptABTestConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneCommercialOptABTest.class))).enableAuditionOpt();
        boolean zNeedLimit = ((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).hitExperiment() ? CollectionsKt___CollectionsKt.contains((List) this.f1.getValue(), obj2) && ((AuditionUsersTextConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextSettings.class))).needLimit() : ((AuditionUsersTextCacheABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextCacheABTest.class))).needLimit();
        if (!z7 && zNeedLimit && strSubstring.length() > Z6()) {
            BLog.i("ToneSelectViewModel", "startReading need limit text size:" + Z6() + ", text:" + strSubstring);
            strSubstring = strSubstring.substring(0, Z6());
            Intrinsics.checkNotNullExpressionValue(strSubstring, "");
        }
        this.o.pause();
        BLog.i("ToneSelectViewModel", "startReading from:" + obj2 + ",useCache:" + z8 + ",needLimit:" + zNeedLimit + ",finalReadText:" + strSubstring);
        TextToSpeechIntent textToSpeechIntent = new TextToSpeechIntent(null, new TextInfo.NoSegTextList(CollectionsKt__CollectionsJVMKt.listOf(strSubstring)), str, toneType.getPlatform(), "ToneSelectViewModel", null, TTSBusinessType.f88857c, j8(this.e0), null, f, i, obj instanceof ReadingListener ? (ReadingListener) obj : null, null, false, new TextToSpeechReportInfo(obj2 instanceof TextToSpeechReportScene ? (TextToSpeechReportScene) obj2 : null, null, strSubstring.length(), true, !(f == 1.0f), 0L, null, null, null, null, 994, null).toJson(), toneType.getAuditionText(), z4, toneType.getToneModelType(), toneType.getResourceId(), null, null, emotionOptionA, toneType.getMockToneInfo(), null, false, null, Boolean.valueOf(toneType.isAICloneTone()), toneType.isV3ModelTone(), false, z5, z8, null, null, null, null, false, 664285473, 31);
        this.S = z4;
        this.T = z5;
        TextToSpeechTaskManager.f74281a.f(textToSpeechIntent);
    }

    public final boolean a8() {
        return (this.T0 && this.U0.l().T1()) ? false : true;
    }

    public final void b7(EffectCategoryModel effectCategoryModel, ThemeType themeType, boolean z, boolean z2, boolean z3, Integer num, boolean z4) throws JSONException {
        Intrinsics.checkNotNullParameter(effectCategoryModel, "");
        Intrinsics.checkNotNullParameter(themeType, "");
        Pair<String, EffectByIdParams> pairU7 = u7(effectCategoryModel.getId());
        boolean z5 = (Intrinsics.areEqual(w7(), this.c1) ^ true) || ((Boolean) this.a1.getValue()).booleanValue();
        JSONObject jSONObjectI = ReportEditSessionManager.i(ReportEditSessionManager.f98584a);
        if (jSONObjectI == null) {
            jSONObjectI = new JSONObject();
        }
        jSONObjectI.put("text", w7());
        if (z2) {
            jSONObjectI.put("ab_optional", new JSONObject().put("enable_paging", true));
        }
        H6(effectCategoryModel.getKey(), themeType, true, jSONObjectI, z || !z5, z3, z2, new PreLoadFirstParams(false, 0, ((MergeApiModel) this.Z0.getValue()).d(), 7), (z3 || pairU7 == null) ? null : pairU7.getSecond(), num, z4, z4);
        this.c1 = w7();
    }

    public final void b8(Context context, boolean z, final Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(function1, "");
        ReadWriteProperty readWriteProperty = this.Q0;
        KProperty<?>[] kPropertyArr = m1;
        if (!((Boolean) readWriteProperty.getValue(this, kPropertyArr[0])).booleanValue() || !((BaseClientSetting) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getAiCloneToneConfig().a() || z) {
            function1.invoke(Boolean.FALSE);
            return;
        }
        O7("show");
        ToneCloneIntroDialog toneCloneIntroDialog = new ToneCloneIntroDialog(context, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneGuide$1
            /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                this.e.O7("click_try");
                function1.invoke(Boolean.TRUE);
                return Unit.INSTANCE;
            }
        }, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$showCloneToneGuide$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                this.e.O7("click_close");
                return Unit.INSTANCE;
            }
        });
        if (!new HeliosApiHook().preInvoke(300000, "com/vega/audio/tone/clonetone/dialog/ToneCloneIntroDialog", "show", toneCloneIntroDialog, new Object[0], "void", new ExtraInfo(false, "()V", "dzBzEhQ/WMuSVEIlTB3Kcu5WjxBEU3Wd5nJXHhc/oZqix0EXGS+qIkjR7G7Pt73YUw==")).isIntercept()) {
            toneCloneIntroDialog.show();
        }
        this.Q0.setValue(this, kPropertyArr[0], Boolean.FALSE);
    }

    public final LiveData<CategoryListState> c7() {
        return this.w0.getValue();
    }

    public final String d7() {
        int i = this.e0;
        return i != 12 ? i != 15 ? "tts" : "sing_voice_change" : "voice_change";
    }

    /* JADX WARN: Removed duplicated region for block: B:21:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.vega.middlebridge.swig.VectorOfString e7() {
        /*
            r2 = this;
            androidx.lifecycle.LiveData r0 = r2.R2()
            java.lang.Object r0 = r0.getValue()
            com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
            if (r0 == 0) goto L1e
            com.vega.middlebridge.swig.Segment r1 = r0.f87871c
            if (r1 == 0) goto L1e
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L24
            com.vega.middlebridge.swig.SegmentText r1 = (com.vega.middlebridge.swig.SegmentText) r1
            if (r1 == 0) goto L1e
            com.vega.middlebridge.swig.VectorOfString r0 = com.vega.ve.utils.DraftExpandKt.w(r1)
        L1c:
            if (r0 != 0) goto L23
        L1e:
            com.vega.middlebridge.swig.VectorOfString r0 = new com.vega.middlebridge.swig.VectorOfString
            r0.<init>()
        L23:
            return r0
        L24:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            if (r0 == 0) goto L1e
            com.vega.middlebridge.swig.SegmentTextTemplate r1 = (com.vega.middlebridge.swig.SegmentTextTemplate) r1
            if (r1 == 0) goto L1e
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r1.p()
            if (r0 == 0) goto L1e
            com.vega.middlebridge.swig.VectorOfString r0 = r0.s()
            goto L1c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.e7():com.vega.middlebridge.swig.VectorOfString");
    }

    public final void e8() {
        TextToSpeechTaskManager.f74281a.a("");
        if (this.T0) {
            this.U0.l().h0();
            this.T0 = false;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0092  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<java.lang.String> f7(com.vega.middlebridge.swig.LVVEMetaType r14) {
        /*
            r13 = this;
            java.util.List r1 = r13.Y6(r14)
            r7 = 0
            if (r1 == 0) goto L95
            java.util.ArrayList r4 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r1, r0)
            r4.<init>(r0)
            java.util.Iterator r5 = r1.iterator()
        L16:
            boolean r0 = r5.hasNext()
            if (r0 == 0) goto L94
            java.lang.Object r1 = r5.next()
            com.vega.middlebridge.swig.Segment r1 = (com.vega.middlebridge.swig.Segment) r1
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L30
            com.vega.middlebridge.swig.SegmentText r1 = (com.vega.middlebridge.swig.SegmentText) r1
            java.lang.String r0 = com.vega.ve.utils.DraftExpandKt.v(r1)
        L2c:
            r4.add(r0)
            goto L16
        L30:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            java.lang.String r3 = ""
            if (r0 == 0) goto L92
            boolean r0 = com.vega.ve.expand.UnifyTextExpandKt.G(r1)
            if (r0 == 0) goto L41
            java.lang.String r0 = com.vega.ve.expand.UnifyTextExpandKt.y(r1)
            goto L2c
        L41:
            com.vega.middlebridge.swig.SegmentTextTemplate r1 = (com.vega.middlebridge.swig.SegmentTextTemplate) r1
            if (r1 == 0) goto L92
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r1.p()
            if (r0 == 0) goto L92
            com.vega.middlebridge.swig.VectorOfTextBindEffectInfo r1 = r0.q()
            boolean r0 = r1.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L92
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.Iterator r2 = r1.iterator()
        L60:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L84
            java.lang.Object r1 = r2.next()
            r0 = r1
            com.vega.middlebridge.swig.TextBindEffectInfo r0 = (com.vega.middlebridge.swig.TextBindEffectInfo) r0
            com.vega.middlebridge.swig.MaterialText r0 = r0.i()
            java.lang.String r0 = r0.a0()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r3)
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L60
            r6.add(r1)
            goto L60
        L84:
            r10 = 0
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3 r11 = new kotlin.jvm.functions.Function1<com.vega.middlebridge.swig.TextBindEffectInfo, java.lang.CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3) com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.vega.middlebridge.swig.TextBindEffectInfo r3) {
                    /*
                        r2 = this;
                        com.vega.middlebridge.swig.TextBindEffectInfo r3 = (com.vega.middlebridge.swig.TextBindEffectInfo) r3
                        com.vega.middlebridge.swig.MaterialText r0 = r3.i()
                        java.lang.String r1 = r0.a0()
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getCurrentAllText$1$3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r12 = 31
            r8 = r7
            r9 = r7
            java.lang.String r0 = kotlin.collections.CollectionsKt.j(r6, r7, r8, r9, r10, r11, r12)
            if (r0 == 0) goto L92
            goto L2c
        L92:
            r0 = r3
            goto L2c
        L94:
            r7 = r4
        L95:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.f7(com.vega.middlebridge.swig.LVVEMetaType):java.util.List");
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v1, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean f8() {
        com.ss.ugc.effectplatform.model.EffectCategoryModel effectCategoryModel = (com.ss.ugc.effectplatform.model.EffectCategoryModel) this.f89478d.getValue();
        return Intrinsics.areEqual(effectCategoryModel != null ? effectCategoryModel.getKey() : null, "clone");
    }

    public final MaterialAudio g7() {
        VectorOfString vectorOfStringS;
        ISession iSession;
        Draft draftL;
        SegmentAudio segmentAudio;
        MaterialAudio materialAudioU;
        SegmentState value = R2().getValue();
        Segment segment = value != null ? value.f87871c : null;
        if (segment instanceof SegmentText) {
            SegmentAudio segmentAudioP = DraftExpandKt.p(segment);
            if (segmentAudioP != null && (materialAudioU = segmentAudioP.u()) != null) {
                return materialAudioU;
            }
            vectorOfStringS = DraftExpandKt.w((SegmentText) segment);
        } else {
            if (!(segment instanceof SegmentTextTemplate)) {
                if (segment instanceof SegmentAudio) {
                    return ((SegmentAudio) segment).u();
                }
                return null;
            }
            vectorOfStringS = ((SegmentTextTemplate) segment).p().s();
        }
        if (vectorOfStringS == null || vectorOfStringS.isEmpty() || (iSession = this.o) == null || (draftL = iSession.l()) == null || (segmentAudio = (SegmentAudio) V6((String) vectorOfStringS.get(0), draftL)) == null) {
            return null;
        }
        return segmentAudio.u();
    }

    public final TextToSpeechReportScene h8(int i, boolean z, boolean z2, ThemeType themeType, TextToSpeechReportDigitalHumanEntrance textToSpeechReportDigitalHumanEntrance) {
        if (i == 4 || i == 6) {
            return !z2 ? TextToSpeechReportScene.TEXT_TO_VIDEO : TextToSpeechReportScene.TEXT_TO_VIDEO_AUDIO_CLONE;
        }
        if (i == 8) {
            return (textToSpeechReportDigitalHumanEntrance == null || textToSpeechReportDigitalHumanEntrance == TextToSpeechReportDigitalHumanEntrance.NONE) ? TextToSpeechReportScene.LONG_TEXT_EDITOR : TextToSpeechReportScene.DIGITAL_HUMAN;
        }
        if (i == 10) {
            return TextToSpeechReportScene.LIP_SYNC;
        }
        if (i == 17) {
            return TextToSpeechReportScene.SMART_INTELLIGENT_EDIT;
        }
        if (z) {
            if (textToSpeechReportDigitalHumanEntrance != null) {
                int i2 = WhenMappings.f74650a[textToSpeechReportDigitalHumanEntrance.ordinal()];
                if (i2 == 1) {
                    return TextToSpeechReportScene.DIGITAL_HUMAN_SUBTITLE;
                }
                if (i2 == 2) {
                    return TextToSpeechReportScene.DIGITAL_HUMAN_TEXT;
                }
            }
            return TextToSpeechReportScene.AI_SCRIPT;
        }
        if (z2) {
            return themeType == ThemeType.f132715g ? TextToSpeechReportScene.AUDIO_CLONE_MUSIC : TextToSpeechReportScene.AUDIO_CLONE;
        }
        if (themeType != null) {
            int i3 = WhenMappings.b[themeType.ordinal()];
            if (i3 == 1 || i3 == 2) {
                return TextToSpeechReportScene.CC4B_AUDIO_PANEL;
            }
            if (i3 == 3) {
                return TextToSpeechReportScene.TEXT_TO_VIDEO;
            }
            if (i3 == 4) {
                return TextToSpeechReportScene.CUT_SAME_AUDIO_PANEL;
            }
            if (i3 == 5) {
                return TextToSpeechReportScene.AUDIO_CLONE;
            }
        }
        ToneType value = this.F.getValue();
        if (value != null) {
            return value.isAICloneTone() ? TextToSpeechReportScene.AUDIO_CLONE : textToSpeechReportDigitalHumanEntrance == TextToSpeechReportDigitalHumanEntrance.INDEPENDENT_AVATAR ? TextToSpeechReportScene.DIGITAL_HUMAN : TextToSpeechReportScene.AUDIO_PANEL;
        }
        return null;
    }

    public final Pair<Double, String> i7() {
        ISession iSession;
        Draft draftL;
        SegmentAudio segmentAudio;
        String strJ;
        Draft draftL2;
        SegmentAudio segmentAudio2;
        MaterialDigitalHuman materialDigitalHumanC;
        DigitalHumanVoiceInfo digitalHumanVoiceInfoY;
        SegmentVideo segmentVideo = this.Q;
        if (segmentVideo != null && (materialDigitalHumanC = segmentVideo.C()) != null && (digitalHumanVoiceInfoY = materialDigitalHumanC.y()) != null) {
            return new Pair<>(Double.valueOf(digitalHumanVoiceInfoY.g()), digitalHumanVoiceInfoY.n());
        }
        SegmentState value = R2().getValue();
        Segment segment = value != null ? value.f87871c : null;
        String str = "";
        if (segment instanceof SegmentText) {
            VectorOfString vectorOfStringW = DraftExpandKt.w((SegmentText) segment);
            if (!vectorOfStringW.isEmpty()) {
                if (!DraftExpandKt.K(segment) || DraftExpandKt.p(segment) == null) {
                    ISession iSession2 = this.o;
                    if (iSession2 != null && (draftL2 = iSession2.l()) != null && (segmentAudio2 = (SegmentAudio) V6(vectorOfStringW.get(0), draftL2)) != null) {
                        dG = segmentAudio2.y().g() <= 3.0d ? segmentAudio2.y().g() : 1.0d;
                        strJ = segmentAudio2.u().J();
                        Intrinsics.checkNotNullExpressionValue(strJ, "");
                        str = strJ;
                    }
                } else {
                    SegmentAudio segmentAudioP = DraftExpandKt.p(segment);
                    if (segmentAudioP != null) {
                        dG = segmentAudioP.y().g() <= 3.0d ? segmentAudioP.y().g() : 1.0d;
                        strJ = segmentAudioP.u().J();
                        Intrinsics.checkNotNullExpressionValue(strJ, "");
                        str = strJ;
                    }
                }
            }
        } else if (segment instanceof SegmentTextTemplate) {
            VectorOfString vectorOfStringS = ((SegmentTextTemplate) segment).p().s();
            if (!vectorOfStringS.isEmpty() && (iSession = this.o) != null && (draftL = iSession.l()) != null && (segmentAudio = (SegmentAudio) V6(vectorOfStringS.get(0), draftL)) != null) {
                dG = segmentAudio.y().g() <= 3.0d ? segmentAudio.y().g() : 1.0d;
                strJ = segmentAudio.u().J();
                Intrinsics.checkNotNullExpressionValue(strJ, "");
                str = strJ;
            }
        }
        return new Pair<>(Double.valueOf(dG), str);
    }

    public final List<Segment> j7() {
        BehaviorSubject<DraftCallbackResult> behaviorSubjectB;
        DraftCallbackResult value;
        Draft draft;
        Track next;
        AbsSessionObservable absSessionObservableE = this.o.e();
        if (absSessionObservableE == null || (behaviorSubjectB = absSessionObservableE.b()) == null || (value = behaviorSubjectB.getValue()) == null || (draft = value.f79054c) == null) {
            return null;
        }
        Iterator<Track> it = draft.v().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next.f() == LVVETrackType.TrackTypeTextToVideoTextAudio) {
                break;
            }
        }
        Track track = next;
        if (track != null) {
            return CollectionsKt___CollectionsKt.toList(track.e());
        }
        return null;
    }

    public final String j8(int i) {
        return (i == 4 || i == 6) ? "text" : i != 8 ? i != 11 ? i != 13 ? i != 14 ? "edit" : "video_inspire" : "template_edit_anchor" : "ai_dubbing" : this.p0 ? "script_to_video_editor_page_album" : "long_text_editor";
    }

    public final void k8(boolean z) {
        ((ToneFlavorImpl) this.u0.getValue()).f89172a = z;
        this.w0 = LazyKt__LazyJVMKt.lazy(new Function0<LiveData<CategoryListState>>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$updateEnableCollect$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final LiveData<CategoryListState> invoke() {
                return this.e.x7();
            }
        });
    }

    public final String l7() {
        int i = this.e0;
        if (i == 7) {
            return "audio_module";
        }
        if (i == 17) {
            return "smart_edit";
        }
        if (i == 18) {
            return "ai_voiceover";
        }
        switch (i) {
            case MotionEventCompat.AXIS_RX /* 12 */:
                return "voice_change";
            case 13:
                return "template_edit_anchor";
            case 14:
                return "video_inspire";
            case 15:
                return "sing_voice_change";
            default:
                return "tts";
        }
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.edit.base.audio.tone.IToneSelectViewModel.DefaultImpls.a(com.vega.edit.base.audio.tone.IToneSelectViewModel, boolean, boolean, int):void */
    public final void l8(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        BLog.i("ToneSelectViewModel", "update tone clone: " + str);
        LiveDataExtKt.o(this.N, str);
        IToneSelectViewModel.DefaultImpls.a(this, false, false, 4);
        this.c0 = false;
    }

    public final void m8(final Function2<? super Boolean, ? super Boolean, Unit> function2) {
        List<Effect> list;
        Intrinsics.checkNotNullParameter(function2, "");
        PagedEffectListState<Effect> value = this.J0.getValue();
        if ((value != null ? value.f98530g : null) != RepoResult.f98531a) {
            this.x.O(new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$waitForCloneToneLoading$1
                /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.Boolean, kotlin.Unit> */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    final ToneSelectViewModel toneSelectViewModel = this.e;
                    final Function2<Boolean, Boolean, Unit> function22 = function2;
                    ThreadUtilKt.e(0L, new Function0<Unit>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$waitForCloneToneLoading$1.1
                        /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super java.lang.Boolean, kotlin.Unit> */
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        /* JADX WARN: Multi-variable type inference failed */
                        {
                            super(0);
                        }

                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function0
                        public final Unit invoke() {
                            List<Effect> list2;
                            PagedEffectListState<Effect> value2 = toneSelectViewModel.J0.getValue();
                            boolean z = true;
                            boolean z2 = (value2 != null ? value2.f98530g : null) == RepoResult.f98531a;
                            Function2<Boolean, Boolean, Unit> function23 = function22;
                            Boolean boolValueOf = Boolean.valueOf(z2);
                            PagedEffectListState<Effect> value3 = toneSelectViewModel.J0.getValue();
                            if (value3 != null && (list2 = value3.h) != null && !list2.isEmpty()) {
                                z = false;
                            }
                            function23.invoke(boolValueOf, Boolean.valueOf(z));
                            toneSelectViewModel.x.J();
                            return Unit.INSTANCE;
                        }
                    });
                    return Unit.INSTANCE;
                }
            });
            IToneSelectViewModel.DefaultImpls.a(this, false, false, 6);
        } else {
            Boolean bool = Boolean.TRUE;
            PagedEffectListState<Effect> value2 = this.J0.getValue();
            function2.invoke(bool, Boolean.valueOf(value2 == null || (list = value2.h) == null || list.isEmpty()));
        }
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void n1(String str) {
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("ToneSelectViewModel", "setSelectText: " + str);
        }
        this.j1 = str;
    }

    public final int n7(String str, List<EffectCategoryModel> list) {
        String strI = this.P0.i(str, "");
        String str2 = strI != null ? strI : "";
        int i = 0;
        int i2 = -1;
        if (!(str2.length() == 0) && list != null && !list.isEmpty()) {
            Iterator<EffectCategoryModel> it = list.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (Intrinsics.areEqual(EffectExtendKt.k(it.next()), str2)) {
                    i2 = i;
                    break;
                }
                i++;
            }
        }
        StringBuilder sb = new StringBuilder("get last ");
        sb.append(str);
        sb.append(" -> ");
        sb.append(i2);
        sb.append(". ");
        sb.append(list != null ? Integer.valueOf(list.size()) : null);
        BLog.i("ToneSelectViewModel", sb.toString());
        return i2;
    }

    public final SegmentAudio o7(Segment segment) {
        MaterialTextTemplate materialTextTemplateP;
        String str;
        SegmentAudio segmentAudio;
        IQueryUtils iQueryUtilsP;
        IQueryUtils iQueryUtilsP2;
        if (segment instanceof SegmentText) {
            SegmentAudio segmentAudioP = DraftExpandKt.p(segment);
            if (segmentAudioP != null) {
                return segmentAudioP;
            }
            String str2 = (String) CollectionsKt___CollectionsKt.lastOrNull((List) DraftExpandKt.w((SegmentText) segment));
            if (str2 == null) {
                return null;
            }
            LyraSession lyraSessionB = this.o.b();
            Segment segmentO = (lyraSessionB == null || (iQueryUtilsP2 = DraftClient.p(lyraSessionB)) == null) ? null : iQueryUtilsP2.o(str2, true);
            if (!(segmentO instanceof SegmentAudio) || (segmentAudio = (SegmentAudio) segmentO) == null) {
                return null;
            }
        } else {
            if (!(segment instanceof SegmentTextTemplate) || (materialTextTemplateP = ((SegmentTextTemplate) segment).p()) == null || (str = (String) CollectionsKt___CollectionsKt.lastOrNull((List) materialTextTemplateP.s())) == null) {
                return null;
            }
            LyraSession lyraSessionB2 = this.o.b();
            Segment segmentM = (lyraSessionB2 == null || (iQueryUtilsP = DraftClient.p(lyraSessionB2)) == null) ? null : iQueryUtilsP.m(str);
            if (!(segmentM instanceof SegmentAudio) || (segmentAudio = (SegmentAudio) segmentM) == null) {
                return null;
            }
        }
        return segmentAudio;
    }

    @Override // com.vega.infrastructure.vm.DisposableViewModel, androidx.lifecycle.ViewModel
    public final void onCleared() {
        super.onCleared();
        TextToSpeechTaskManager.f74281a.destroy();
        ((DigitalHumanCustomizeApi) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(DigitalHumanCustomizeApi.class), null)).n(this.d1);
    }

    /* JADX WARN: Removed duplicated region for block: B:47:0x00cd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.String p7(java.lang.String r12, boolean r13, boolean r14) {
        /*
            r11 = this;
            java.lang.String r1 = r11.j1
            r2 = 1
            if (r1 == 0) goto L2d
            if (r13 == 0) goto L10
            com.vega.edit.base.utils.DigitalHumanUtils r0 = com.vega.edit.base.utils.DigitalHumanUtils.f88933a
            r0.getClass()
            java.lang.String r1 = com.vega.edit.base.utils.DigitalHumanUtils.g(r1)
        L10:
            if (r14 == 0) goto L1c
            com.service.audio.CloneToneLanguageConfig r0 = com.service.audio.CloneToneLanguageConfig.f64851a
            r0.getClass()
            java.lang.String r1 = com.service.audio.CloneToneLanguageConfig.c(r12)
        L1b:
            return r1
        L1c:
            boolean r0 = r11.g0
            if (r0 == 0) goto L1b
            if (r1 == 0) goto L1b
            int r0 = r1.length()
            if (r0 != 0) goto L1b
            java.lang.String r1 = com.lemon.lv.data.ToneTypeKt.a()
            goto L1b
        L2d:
            int r0 = r11.i1
            r1 = 0
            if (r0 != r2) goto L3d
            java.util.List<java.lang.String> r0 = r11.h1
            if (r0 == 0) goto L10
            java.lang.Object r1 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r0)
            java.lang.String r1 = (java.lang.String) r1
            goto L10
        L3d:
            androidx.lifecycle.LiveData r0 = r11.R2()
            java.lang.Object r0 = r0.getValue()
            com.vega.edit.base.model.repository.SegmentState r0 = (com.vega.edit.base.model.repository.SegmentState) r0
            if (r0 == 0) goto L4b
            com.vega.middlebridge.swig.Segment r1 = r0.f87871c
        L4b:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentText
            if (r0 == 0) goto L56
            com.vega.middlebridge.swig.SegmentText r1 = (com.vega.middlebridge.swig.SegmentText) r1
            java.lang.String r1 = com.vega.ve.utils.DraftExpandKt.v(r1)
            goto L10
        L56:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentTextTemplate
            java.lang.String r2 = ""
            if (r0 == 0) goto Lb6
            boolean r0 = com.vega.ve.expand.UnifyTextExpandKt.G(r1)
            if (r0 == 0) goto L67
            java.lang.String r1 = com.vega.ve.expand.UnifyTextExpandKt.y(r1)
            goto L10
        L67:
            com.vega.middlebridge.swig.SegmentTextTemplate r1 = (com.vega.middlebridge.swig.SegmentTextTemplate) r1
            com.vega.middlebridge.swig.MaterialTextTemplate r0 = r1.p()
            com.vega.middlebridge.swig.VectorOfTextBindEffectInfo r1 = r0.q()
            boolean r0 = r1.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto Lcd
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r3 = r1.iterator()
        L82:
            boolean r0 = r3.hasNext()
            if (r0 == 0) goto La6
            java.lang.Object r1 = r3.next()
            r0 = r1
            com.vega.middlebridge.swig.TextBindEffectInfo r0 = (com.vega.middlebridge.swig.TextBindEffectInfo) r0
            com.vega.middlebridge.swig.MaterialText r0 = r0.i()
            java.lang.String r0 = r0.a0()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L82
            r4.add(r1)
            goto L82
        La6:
            r5 = 0
            r8 = 0
            com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3 r9 = new kotlin.jvm.functions.Function1<com.vega.middlebridge.swig.TextBindEffectInfo, java.lang.CharSequence>() { // from class: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3
                static {
                    /*
                        com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3 r0 = new com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3) com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3.e com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3.<init>():void");
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final java.lang.CharSequence invoke(com.vega.middlebridge.swig.TextBindEffectInfo r3) {
                    /*
                        r2 = this;
                        com.vega.middlebridge.swig.TextBindEffectInfo r3 = (com.vega.middlebridge.swig.TextBindEffectInfo) r3
                        com.vega.middlebridge.swig.MaterialText r0 = r3.i()
                        java.lang.String r1 = r0.a0()
                        java.lang.String r0 = ""
                        kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
                        return r1
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel$getReadingText$text$3.invoke(java.lang.Object):java.lang.Object");
                }
            }
            r10 = 31
            r6 = r5
            r7 = r5
            java.lang.String r1 = kotlin.collections.CollectionsKt.j(r4, r5, r6, r7, r8, r9, r10)
            if (r1 == 0) goto Lcd
            goto L10
        Lb6:
            boolean r0 = r1 instanceof com.vega.middlebridge.swig.SegmentVideo
            if (r0 == 0) goto Lcd
            com.vega.middlebridge.swig.SegmentVideo r1 = (com.vega.middlebridge.swig.SegmentVideo) r1
            java.lang.String r1 = com.vega.edit.base.action.DraftExKt.b(r1)
            if (r13 == 0) goto L10
            com.vega.edit.base.utils.DigitalHumanUtils r0 = com.vega.edit.base.utils.DigitalHumanUtils.f88933a
            r0.getClass()
            java.lang.String r1 = com.vega.edit.base.utils.DigitalHumanUtils.g(r1)
            goto L10
        Lcd:
            r1 = r2
            goto L10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.p7(java.lang.String, boolean, boolean):java.lang.String");
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void q3() {
        TextToSpeechTaskManager.f74281a.init(null);
    }

    public final EffectCategoryModel q7() {
        List<EffectCategoryModel> list;
        Integer value = this.B0.getValue();
        if (value == null) {
            return null;
        }
        int iIntValue = value.intValue();
        CategoryListState value2 = c7().getValue();
        if (value2 == null || (list = value2.b) == null) {
            return null;
        }
        return (EffectCategoryModel) CollectionsKt___CollectionsKt.getOrNull(list, iIntValue);
    }

    /* JADX WARN: Removed duplicated region for block: B:5:0x0009  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.lemon.lv.config.ModelConfig r7(com.lemon.lv.data.ToneType r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r3 = 0
            if (r6 == 0) goto L9
            goto Lb
        L9:
            r1 = r3
            goto L23
        Lb:
            java.lang.String r2 = r6.getExtra()     // Catch: java.lang.Throwable -> L27
            if (r2 == 0) goto L9
            com.google.gson.Gson r1 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Throwable -> L27
            java.lang.Class<com.service.audio.data.WebExtra> r0 = com.service.audio.data.WebExtra.class
            java.lang.Object r0 = r1.fromJson(r2, r0)     // Catch: java.lang.Throwable -> L27
            com.service.audio.data.WebExtra r0 = (com.service.audio.data.WebExtra) r0     // Catch: java.lang.Throwable -> L27
            if (r0 == 0) goto L9
            java.util.Map r1 = r0.c()     // Catch: java.lang.Throwable -> L27
        L23:
            kotlin.Result.m17090constructorimpl(r1)     // Catch: java.lang.Throwable -> L27
            goto L2f
        L27:
            r0 = move-exception
            java.lang.Object r1 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r1)
        L2f:
            boolean r0 = kotlin.Result.m17096isFailureimpl(r1)
            if (r0 == 0) goto L36
            r1 = r3
        L36:
            java.util.Map r1 = (java.util.Map) r1
            if (r1 == 0) goto L40
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L45
        L40:
            com.lemon.lv.config.ModelConfig r0 = r5.s7(r7)
            return r0
        L45:
            java.util.Set r0 = r1.entrySet()
            java.util.Iterator r1 = r0.iterator()
        L4d:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto La4
            java.lang.Object r0 = r1.next()
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0
            java.lang.Object r4 = r0.getKey()
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r0 = r0.getValue()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r7)
            if (r0 == 0) goto L4d
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r4)
            if (r0 == 0) goto L4d
            com.vega.core.context.SPIService r1 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.lemon.lv.config.ClientSetting> r0 = com.lemon.lv.config.ClientSetting.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.Object r0 = r1.getImpl(r0, r3)
            com.lemon.lv.config.BaseClientSetting r0 = (com.lemon.lv.config.BaseClientSetting) r0
            com.lemon.lv.config.CloneToneDefaultSettings r0 = r0.getCloneToneDefaultSettings()
            java.util.List r0 = r0.getModelConfigs()
            java.util.Iterator r2 = r0.iterator()
        L89:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto La1
            java.lang.Object r1 = r2.next()
            r0 = r1
            com.lemon.lv.config.ModelConfig r0 = (com.lemon.lv.config.ModelConfig) r0
            java.lang.String r0 = r0.getVoiceType()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r0)
            if (r0 == 0) goto L89
            r3 = r1
        La1:
            com.lemon.lv.config.ModelConfig r3 = (com.lemon.lv.config.ModelConfig) r3
            return r3
        La4:
            com.lemon.lv.config.ModelConfig r0 = r5.s7(r7)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.viewmodel.ToneSelectViewModel.r7(com.lemon.lv.data.ToneType, java.lang.String):com.lemon.lv.config.ModelConfig");
    }

    public final ModelConfig s7(String str) {
        Object obj = null;
        Iterator<T> it = ((BaseClientSetting) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ClientSetting.class), null)).getCloneToneDefaultSettings().getModelConfigs().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (StringsKt__StringsJVMKt.startsWith$default(str, ((ModelConfig) next).getSuffix(), false, 2, null)) {
                obj = next;
                break;
            }
        }
        return (ModelConfig) obj;
    }

    public final Emotion t7(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return this.X0.get(str);
    }

    public void w2(int i, String str, List list, boolean z) {
        this.i1 = i;
        this.h1 = list;
        TextToSpeechTaskManager.f74281a.init(str);
        ReportHelper reportHelper = ReportHelper.f73893a;
        String strJ8 = j8(this.e0);
        reportHelper.getClass();
        ReportHelper.b = strJ8;
    }

    public String w7() {
        return this.b1;
    }

    public final LiveData<CategoryListState> x7() {
        ToneFlavorImpl toneFlavorImpl = (ToneFlavorImpl) this.u0.getValue();
        CategoriesRepository categoriesRepository = this.m;
        int i = this.e0;
        return toneFlavorImpl.a(categoriesRepository, (i == 4 || i == 6) ? false : true);
    }

    public final ToneType y7(Effect effect, EffectCategoryModel effectCategoryModel) throws JSONException {
        Intrinsics.checkNotNullParameter(effect, "");
        int iHashCode = (effect.hashCode() * 31) + ((effectCategoryModel != null ? effectCategoryModel.hashCode() : 0) * 31);
        ToneType toneTypeB = this.g1.get(iHashCode);
        if (toneTypeB == null) {
            ToneUtil toneUtil = ToneUtil.f89173a;
            Map<String, String> mapC = ((ToneSelectPanelTagConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneSelectPanelTagConfigSettings.class))).c();
            ToneTagInfoUtils.f74515a.getClass();
            Map mapA = ToneTagInfoUtils.a();
            toneUtil.getClass();
            toneTypeB = ToneUtil.b(effect, effectCategoryModel, mapC, mapA);
        }
        ToneType toneType = toneTypeB;
        if (this.g1.get(iHashCode) == null) {
            this.g1.put(iHashCode, toneType);
        } else {
            BLog.i("ToneSelectViewModel", "hit toneTypeCache");
        }
        return toneType;
    }

    @Override // com.vega.edit.base.audio.tone.IToneSelectViewModel
    public final void z4() {
        TextToSpeechTaskManager.f74281a.destroy();
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:31:0x00a3 */
    /* JADX DEBUG: Type inference failed for r1v3. Raw type applied. Possible types: java.util.Iterator<T>, java.util.Iterator */
    /* JADX DEBUG: Type inference failed for r1v8. Raw type applied. Possible types: java.util.Iterator<T>, java.util.Iterator */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v3, types: [java.util.Collection] */
    /* JADX WARN: Type inference failed for: r3v4, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r3v5 */
    public final void z7(Context context, String str, boolean z, boolean z2) {
        String string;
        List<Effect> list;
        List<Effect> list2;
        Draft draftQ;
        CloneToneStatistics cloneToneStatistics = CloneToneStatistics.f74107a;
        CloneToneStatistics.GenerateFrom generateFrom = CloneToneStatistics.GenerateFrom.b;
        cloneToneStatistics.getClass();
        Intrinsics.checkNotNullParameter(generateFrom, "");
        CloneToneStatistics.e = generateFrom;
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(str, "");
        VideoCommonUtils.f110462a.getClass();
        Activity activityA = VideoCommonUtils.a(context);
        LyraSession lyraSessionB = this.o.b();
        ?? EmptyList = 0;
        EmptyList = 0;
        if (lyraSessionB == null || (draftQ = DraftClient.q(lyraSessionB)) == null || (string = draftQ.b()) == null) {
            string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
        }
        if (z2) {
            PagedEffectListState<Effect> value = this.K0.getValue();
            if (value != null && (list2 = value.h) != null) {
                EmptyList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list2, 10));
                Iterator it = list2.iterator();
                while (it.hasNext()) {
                    EmptyList.add(((com.ss.ugc.effectplatform.model.Effect) it.next()).getName());
                }
            }
        } else {
            PagedEffectListState<Effect> value2 = this.J0.getValue();
            if (value2 == null || (list = value2.h) == null) {
                EmptyList = CollectionsKt__CollectionsKt.emptyList();
            } else {
                EmptyList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
                Iterator it2 = list.iterator();
                while (it2.hasNext()) {
                    EmptyList.add(((com.ss.ugc.effectplatform.model.Effect) it2.next()).getName());
                }
            }
        }
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(activityA, "//edit/clone_tone");
        smartRouteBuildRoute.withParam("KEY_DRAFT_ID", string);
        smartRouteBuildRoute.withParamStringList("key_tone_name_list", new ArrayList<>((Collection) EmptyList));
        smartRouteBuildRoute.withParam("enter_from", this.e0);
        smartRouteBuildRoute.withParam("page_from", str);
        smartRouteBuildRoute.withParam("key_is_from_empty_panel", z);
        smartRouteBuildRoute.withParam("key_is_sing_clone", z2);
        smartRouteBuildRoute.withAnimation(R.anim.f59527a, R.anim.w);
        smartRouteBuildRoute.open(1010);
    }
}
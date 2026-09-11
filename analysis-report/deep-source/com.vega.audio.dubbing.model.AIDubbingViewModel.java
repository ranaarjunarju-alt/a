package com.vega.audio.dubbing.model;

import android.text.SpannableString;
import androidx.activity.result.ActivityResult;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelKt;
import com.lemon.lv.data.ToneType;
import com.lemon.lv.editor.proxy.IAIDubbingPreviewDigtalHumanController;
import com.lemon.lvoverseas.R;
import com.service.ISplitTextService;
import com.service.data.AudioRecorderOutput;
import com.service.data.SplitStatus;
import com.service.data.SplitTextData;
import com.service.data.SplitTextInfo;
import com.vega.audio.dubbing.AIDubbingUtil;
import com.vega.audio.dubbing.core.AIDubbingDraftManager;
import com.vega.audio.dubbing.core.draft.AIDubbingBreakType;
import com.vega.audio.dubbing.core.draft.AIDubbingDraftParser;
import com.vega.audio.dubbing.core.draft.AIDubbingType;
import com.vega.audio.dubbing.core.draft.AIDubbingVisibility;
import com.vega.audio.dubbing.core.draft.IAIDubbingDraft;
import com.vega.audio.dubbing.core.node.AIDubbingNodeBase;
import com.vega.audio.dubbing.core.node.AIDubbingNodeContent;
import com.vega.audio.dubbing.core.node.AIDubbingNodeContentBreak;
import com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme;
import com.vega.audio.dubbing.core.node.AIDubbingNodeContentSayas;
import com.vega.audio.dubbing.core.node.AIDubbingNodeContentText;
import com.vega.audio.dubbing.core.node.AIDubbingNodeProsody;
import com.vega.audio.dubbing.core.node.AIDubbingNodeVoice;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.ext.LiveDataExtKt;
import com.vega.core.utils.CombinedLiveData;
import com.vega.core.utils.FunctionsKt;
import com.vega.core.utils.LiveDataExKt;
import com.vega.core.viewmodel.LiveEvent;
import com.vega.costreport.aigc.BabiUtil;
import com.vega.edit.base.anim.VipToastHelper;
import com.vega.editorapi.bean.TextAudioData;
import com.vega.editorapi.bean.TextStartSource;
import com.vega.infrastructure.vm.DisposableViewModel;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* loaded from: classes8.dex */
public final class AIDubbingViewModel extends DisposableViewModel {
    public final LiveEvent<Pair<AIDubbingPageState, AIDubbingReadingBundle>> A;
    public String B;
    public final MutableLiveData<SplitTextInfo> C;
    public final MutableLiveData D;
    public String E;
    public final ConcurrentHashMap<String, ToneType> F;
    public final LiveEvent<String> G;
    public final LiveEvent<ToneType> H;
    public final CombinedLiveData I;

    /* renamed from: J, reason: collision with root package name */
    public final LiveEvent<ToneType> f72935J;
    public String K;
    public boolean L;
    public boolean M;
    public String N;
    public final MutableLiveData<Boolean> O;
    public final MutableLiveData<Boolean> P;
    public final CombinedLiveData Q;
    public boolean R;
    public final MutableLiveData<ToneType> S;
    public final MutableLiveData<Boolean> T;
    public final MutableLiveData<Boolean> U;
    public final LiveEvent<ActivityResult> V;
    public IAIDubbingPreviewDigtalHumanController W;

    /* renamed from: X, reason: collision with root package name */
    public final MutableLiveData<TextStartSource> f72936X;
    public final MutableLiveData Y;
    public final MutableLiveData<AudioRecorderOutput> Z;
    public final MutableLiveData a0;
    public final MutableLiveData<List<TextAudioData>> b0;

    /* renamed from: c, reason: collision with root package name */
    public boolean f72937c;
    public final MutableLiveData c0;

    /* renamed from: d, reason: collision with root package name */
    public AIDubbingEnterParameter f72938d;
    public final AIDubbingDraftParser e = new AIDubbingDraftParser();
    public final AIDubbingDraftManager f = new AIDubbingDraftManager();

    /* renamed from: g, reason: collision with root package name */
    public final MutableLiveData<SpannableString> f72939g;
    public final MutableLiveData<IntRange> h;
    public final CombinedLiveData i;
    public final Function2<AIDubbingNodeBase, HashMap<String, String>, Unit> j;
    public final MutableLiveData<AIDubbingNodeBase> k;
    public final MutableLiveData l;
    public final MutableLiveData<Pair<AIDubbingNodeBase, HashMap<String, String>>> m;
    public final MutableLiveData n;
    public final MutableLiveData<AIDubbingPageState> o;
    public final MutableLiveData p;
    public final MutableLiveData<AIDubbingBreakType> q;
    public final MutableLiveData r;
    public final MutableLiveData<Pair<AIDubbingNodeProsody, IntRange>> s;
    public final MutableLiveData t;
    public final MutableLiveData<String> u;
    public final MutableLiveData v;
    public final MutableLiveData<Pair<AIDubbingNodeVoice, IntRange>> w;
    public final MutableLiveData x;
    public final MutableLiveData<IntRange> y;
    public final MutableLiveData z;

    /* loaded from: classes21.dex */
    public static final class Companion {
    }

    /* loaded from: classes6.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f72940a;

        static {
            int[] iArr = new int[AIDubbingPageState.values().length];
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
            try {
                iArr[3] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[4] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[5] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            f72940a = iArr;
        }
    }

    static {
        new Companion();
    }

    public AIDubbingViewModel() {
        MutableLiveData<SpannableString> mutableLiveData = new MutableLiveData<>(new SpannableString(""));
        this.f72939g = mutableLiveData;
        MutableLiveData<IntRange> mutableLiveData2 = new MutableLiveData<>(null);
        this.h = mutableLiveData2;
        this.i = LiveDataExKt.f(mutableLiveData, mutableLiveData2, new Function2<SpannableString, IntRange, Pair<? extends SpannableString, ? extends IntRange>>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$draftRenderInfo$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Pair<? extends SpannableString, ? extends IntRange> invoke(SpannableString spannableString, IntRange intRange) {
                return TuplesKt.to(spannableString, intRange);
            }
        });
        this.j = new Function2<AIDubbingNodeBase, HashMap<String, String>, Unit>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$dubbingNodeClickListener$1
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(AIDubbingNodeBase aIDubbingNodeBase, HashMap<String, String> map) {
                AIDubbingNodeBase aIDubbingNodeBase2 = aIDubbingNodeBase;
                HashMap<String, String> map2 = map;
                Intrinsics.checkNotNullParameter(aIDubbingNodeBase2, "");
                if (map2 == null || map2.isEmpty()) {
                    this.e.G6(aIDubbingNodeBase2);
                } else {
                    LiveDataExtKt.o(this.e.m, TuplesKt.to(aIDubbingNodeBase2, map2));
                }
                return Unit.INSTANCE;
            }
        };
        MutableLiveData<AIDubbingNodeBase> mutableLiveData3 = new MutableLiveData<>();
        this.k = mutableLiveData3;
        this.l = mutableLiveData3;
        MutableLiveData<Pair<AIDubbingNodeBase, HashMap<String, String>>> mutableLiveData4 = new MutableLiveData<>();
        this.m = mutableLiveData4;
        this.n = mutableLiveData4;
        MutableLiveData<AIDubbingPageState> mutableLiveData5 = new MutableLiveData<>(AIDubbingPageState.f72924a);
        this.o = mutableLiveData5;
        this.p = mutableLiveData5;
        MutableLiveData<AIDubbingBreakType> mutableLiveData6 = new MutableLiveData<>();
        this.q = mutableLiveData6;
        this.r = mutableLiveData6;
        MutableLiveData<Pair<AIDubbingNodeProsody, IntRange>> mutableLiveData7 = new MutableLiveData<>(null);
        this.s = mutableLiveData7;
        this.t = mutableLiveData7;
        MutableLiveData<String> mutableLiveData8 = new MutableLiveData<>("none");
        this.u = mutableLiveData8;
        this.v = mutableLiveData8;
        MutableLiveData<Pair<AIDubbingNodeVoice, IntRange>> mutableLiveData9 = new MutableLiveData<>(null);
        this.w = mutableLiveData9;
        this.x = mutableLiveData9;
        MutableLiveData<IntRange> mutableLiveData10 = new MutableLiveData<>(new IntRange(-1, -1));
        this.y = mutableLiveData10;
        this.z = mutableLiveData10;
        this.A = new LiveEvent<>();
        this.B = "";
        Boolean bool = Boolean.FALSE;
        new MutableLiveData(bool);
        MutableLiveData<SplitTextInfo> mutableLiveData11 = new MutableLiveData<>();
        this.C = mutableLiveData11;
        this.D = mutableLiveData11;
        this.F = new ConcurrentHashMap<>();
        LiveEvent<String> liveEvent = new LiveEvent<>();
        this.G = liveEvent;
        LiveEvent<ToneType> liveEvent2 = new LiveEvent<>();
        this.H = liveEvent2;
        this.I = LiveDataExKt.f(liveEvent, liveEvent2, new Function2<String, ToneType, Pair<? extends String, ? extends ToneType>>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$previewToneFirstInitCallback$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Pair<? extends String, ? extends ToneType> invoke(String str, ToneType toneType) {
                return new Pair<>(str, toneType);
            }
        });
        this.f72935J = new LiveEvent<>();
        this.K = "preview_single_list";
        this.N = "";
        Boolean bool2 = Boolean.TRUE;
        MutableLiveData<Boolean> mutableLiveData12 = new MutableLiveData<>(bool2);
        this.O = mutableLiveData12;
        MutableLiveData<Boolean> mutableLiveData13 = new MutableLiveData<>(bool);
        this.P = mutableLiveData13;
        this.Q = LiveDataExKt.f(mutableLiveData12, mutableLiveData13, new Function2<Boolean, Boolean, Boolean>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$enableVoiceToneDialog$1
            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Boolean invoke(Boolean bool3, Boolean bool4) {
                Boolean bool5 = bool3;
                Boolean bool6 = bool4;
                boolean z = false;
                if (bool5 != null && bool5.booleanValue() && bool6 != null && bool6.booleanValue()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        });
        this.S = new MutableLiveData<>(null);
        this.T = new MutableLiveData<>(bool2);
        this.U = new MutableLiveData<>(bool);
        this.V = new LiveEvent<>();
        MutableLiveData<TextStartSource> mutableLiveData14 = new MutableLiveData<>();
        this.f72936X = mutableLiveData14;
        this.Y = mutableLiveData14;
        MutableLiveData<AudioRecorderOutput> mutableLiveData15 = new MutableLiveData<>();
        this.Z = mutableLiveData15;
        this.a0 = mutableLiveData15;
        MutableLiveData<List<TextAudioData>> mutableLiveData16 = new MutableLiveData<>();
        this.b0 = mutableLiveData16;
        this.c0 = mutableLiveData16;
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x027a  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x0318  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void K6(final com.vega.audio.dubbing.model.AIDubbingViewModel r49, final com.vega.audio.dubbing.model.AIDubbingPageState r50, java.lang.String r51, kotlin.ranges.IntRange r52, com.lemon.lv.data.ToneType r53, boolean r54, boolean r55, java.lang.String r56, int r57) {
        /*
            r39 = r56
            r46 = r55
            r15 = r54
            r42 = r53
            r48 = r52
            r3 = r51
            r0 = r57 & 2
            if (r0 == 0) goto L11
            r3 = 0
        L11:
            r0 = r57 & 4
            if (r0 == 0) goto L17
            r48 = 0
        L17:
            r0 = r57 & 8
            if (r0 == 0) goto L1d
            r42 = 0
        L1d:
            r0 = r57 & 16
            r2 = 0
            if (r0 == 0) goto L23
            r15 = 0
        L23:
            r0 = r57 & 32
            if (r0 == 0) goto L29
            r46 = 0
        L29:
            r0 = r57 & 64
            if (r0 == 0) goto L2f
            r39 = 0
        L2f:
            r17 = 0
            r11 = r49
            r11.getClass()
            java.lang.String r1 = ""
            r18 = r50
            r0 = r18
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            if (r3 != 0) goto L31c
            java.lang.String r0 = r11.u6()
        L45:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r1 = "doReading -> fromPage="
            r4.<init>(r1)
            r1 = r18
            r4.append(r1)
            java.lang.String r1 = ", readingText="
            r4.append(r1)
            r4.append(r3)
            java.lang.String r1 = ", usingDefaultContent="
            r4.append(r1)
            r4.append(r15)
            java.lang.String r1 = ", ssml="
            r4.append(r1)
            r4.append(r0)
            java.lang.String r4 = r4.toString()
            java.lang.String r1 = "AIDubbingViewModel"
            com.vega.log.BLog.i(r1, r4)
            int r1 = r0.length()
            r4 = 1
            if (r1 != 0) goto L7a
        L79:
            return
        L7a:
            com.vega.infrastructure.base.ModuleCommon r1 = com.vega.infrastructure.base.ModuleCommon.INSTANCE
            android.app.Application r1 = r1.getApplication()
            boolean r1 = com.bytedance.apm.util.NetUtils.b(r1)
            if (r1 != 0) goto Lcf
            int r2 = r18.ordinal()
            r1 = 2131886503(0x7f1201a7, float:1.9407587E38)
            if (r2 == 0) goto Lca
            if (r2 == r4) goto Lc2
            r0 = 2
            if (r2 == r0) goto Lba
            r0 = 3
            if (r2 == r0) goto Lb5
            r0 = 4
            if (r2 == r0) goto Lad
            r0 = 5
            if (r2 != r0) goto L31f
            r0 = 2131886539(0x7f1201cb, float:1.940766E38)
            java.lang.String r1 = com.vega.core.utils.FunctionsKt.b(r0)
        La4:
            com.vega.edit.base.anim.VipToastHelper r0 = com.vega.edit.base.anim.VipToastHelper.f86943a
            r0.getClass()
            com.vega.edit.base.anim.VipToastHelper.a(r1)
            goto L79
        Lad:
            r0 = 2131886520(0x7f1201b8, float:1.9407621E38)
            java.lang.String r1 = com.vega.core.utils.FunctionsKt.b(r0)
            goto La4
        Lb5:
            java.lang.String r1 = com.vega.core.utils.FunctionsKt.b(r1)
            goto La4
        Lba:
            r0 = 2131886528(0x7f1201c0, float:1.9407637E38)
            java.lang.String r1 = com.vega.core.utils.FunctionsKt.b(r0)
            goto La4
        Lc2:
            r0 = 2131886507(0x7f1201ab, float:1.9407595E38)
            java.lang.String r1 = com.vega.core.utils.FunctionsKt.b(r0)
            goto La4
        Lca:
            java.lang.String r1 = com.vega.core.utils.FunctionsKt.b(r1)
            goto La4
        Lcf:
            r5 = 2147483647(0x7fffffff, float:NaN)
            java.lang.String r10 = "old"
            java.lang.String r9 = "status"
            if (r15 == 0) goto Le0
            if (r3 == 0) goto Le0
            int r1 = r3.length()
            if (r1 != 0) goto L135
        Le0:
            com.vega.audio.dubbing.core.AIDubbingDraftManager r1 = r11.f
            java.util.ArrayList r1 = r1.r(r2, r5)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r7 = r1.iterator()
        Lef:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L165
            java.lang.Object r4 = r7.next()
            kotlin.Pair r4 = (kotlin.Pair) r4
            java.lang.Object r3 = r4.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r3 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r3
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.put(r9, r10)
            r3.a(r1)
            java.lang.Object r1 = r4.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r1 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r1
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> r1 = r1.m
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r6 = r1.iterator()
        L11d:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L131
            java.lang.Object r3 = r6.next()
            boolean r1 = r3 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeProsodyEnter
            r1 = r1 ^ 1
            if (r1 == 0) goto L11d
            r4.add(r3)
            goto L11d
        L131:
            kotlin.collections.CollectionsKt__MutableCollectionsKt.addAll(r2, r4)
            goto Lef
        L135:
            com.vega.audio.dubbing.core.draft.AIDubbingDraftParser r1 = new com.vega.audio.dubbing.core.draft.AIDubbingDraftParser
            r1.<init>()
            com.vega.audio.dubbing.core.draft.AIDubbingDraftController r1 = com.vega.audio.dubbing.core.draft.AIDubbingDraftParser.d(r3)
            if (r1 == 0) goto L263
            java.util.ArrayList r1 = r1.r(r2, r5)
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r3 = r1.iterator()
        L14d:
            boolean r1 = r3.hasNext()
            if (r1 == 0) goto L165
            java.lang.Object r1 = r3.next()
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r1.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r1 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r1
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> r1 = r1.m
            kotlin.collections.CollectionsKt__MutableCollectionsKt.addAll(r2, r1)
            goto L14d
        L165:
            java.util.ArrayList r14 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, r1)
            r14.<init>(r1)
            java.util.Iterator r16 = r2.iterator()
        L174:
            boolean r1 = r16.hasNext()
            if (r1 == 0) goto L264
            java.lang.Object r4 = r16.next()
            com.vega.audio.dubbing.core.node.AIDubbingNodeProsody r4 = (com.vega.audio.dubbing.core.node.AIDubbingNodeProsody) r4
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.put(r9, r10)
            r4.a(r1)
            java.util.HashMap r8 = new java.util.HashMap
            r8.<init>()
            java.lang.String r2 = r4.b
            java.lang.String r1 = "rate"
            r8.put(r1, r2)
            com.vega.audio.dubbing.core.AIDubbingDraftManager r3 = r11.f
            kotlin.ranges.IntRange r2 = new kotlin.ranges.IntRange
            r1 = 0
            r2.<init>(r1, r5)
            java.util.ArrayList r7 = r3.x(r4, r2)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r12 = r7.iterator()
        L1ac:
            boolean r1 = r12.hasNext()
            if (r1 == 0) goto L1ec
            java.lang.Object r4 = r12.next()
            r1 = r4
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r3 = r1.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeBase r3 = (com.vega.audio.dubbing.core.node.AIDubbingNodeBase) r3
            boolean r1 = r3 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentBreak
            java.lang.String r2 = "idle"
            if (r1 == 0) goto L1ea
            r1 = r3
            com.vega.audio.dubbing.core.node.AIDubbingNodeContent r1 = (com.vega.audio.dubbing.core.node.AIDubbingNodeContent) r1
            java.lang.String r1 = r1.f72883c
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r1 == 0) goto L1ea
            r6 = 1
        L1d1:
            boolean r1 = r3 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme
            if (r1 == 0) goto L1e8
            com.vega.audio.dubbing.core.node.AIDubbingNodeContent r3 = (com.vega.audio.dubbing.core.node.AIDubbingNodeContent) r3
            java.lang.String r1 = r3.f72883c
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r1 == 0) goto L1e8
            r1 = 1
        L1e0:
            if (r6 != 0) goto L1e4
            if (r1 == 0) goto L1ac
        L1e4:
            r5.add(r4)
            goto L1ac
        L1e8:
            r1 = 0
            goto L1e0
        L1ea:
            r6 = 0
            goto L1d1
        L1ec:
            java.lang.Object r1 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r5)
            kotlin.Pair r1 = (kotlin.Pair) r1
            int r6 = kotlin.collections.CollectionsKt___CollectionsKt.indexOf(r7, r1)
            if (r1 == 0) goto L25b
            if (r6 < 0) goto L25b
            java.lang.Object r5 = r1.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeBase r5 = (com.vega.audio.dubbing.core.node.AIDubbingNodeBase) r5
            int r13 = r7.size()
            r4 = 0
            r3 = 0
            r2 = 0
        L207:
            if (r4 >= r13) goto L232
            java.lang.Object r1 = r7.get(r4)
            kotlin.Pair r1 = (kotlin.Pair) r1
            java.lang.Object r1 = r1.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeBase r1 = (com.vega.audio.dubbing.core.node.AIDubbingNodeBase) r1
            boolean r12 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentText
            if (r12 != 0) goto L21c
        L219:
            int r4 = r4 + 1
            goto L207
        L21c:
            if (r4 > r6) goto L228
            r12 = r1
            com.vega.audio.dubbing.core.node.AIDubbingNodeContent r12 = (com.vega.audio.dubbing.core.node.AIDubbingNodeContent) r12
            java.lang.String r12 = r12.b
            int r12 = r12.length()
            int r3 = r3 + r12
        L228:
            com.vega.audio.dubbing.core.node.AIDubbingNodeContent r1 = (com.vega.audio.dubbing.core.node.AIDubbingNodeContent) r1
            java.lang.String r1 = r1.b
            int r1 = r1.length()
            int r2 = r2 + r1
            goto L219
        L232:
            java.util.HashMap r1 = new java.util.HashMap
            r1.<init>()
            r1.put(r9, r10)
            r5.a(r1)
            int r1 = r7.size()
            r4 = 1
            int r1 = r1 - r4
            if (r6 != r1) goto L24c
            java.lang.String r4 = "is_end_target"
            java.lang.String r1 = "1"
            r8.put(r4, r1)
        L24c:
            float r3 = (float) r3
            r1 = 1065353216(0x3f800000, float:1.0)
            float r3 = r3 * r1
            float r1 = (float) r2
            float r3 = r3 / r1
            java.lang.String r2 = java.lang.String.valueOf(r3)
            java.lang.String r1 = "target_cursor_ratio"
            r8.put(r1, r2)
        L25b:
            r14.add(r8)
            r5 = 2147483647(0x7fffffff, float:NaN)
            goto L174
        L263:
            r14 = 0
        L264:
            com.vega.audio.tone.tts.TextToSpeechTaskManager r2 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
            com.vega.edit.base.tone.TextInfo$AutoSegText r1 = new com.vega.edit.base.tone.TextInfo$AutoSegText
            r1.<init>(r0)
            com.vega.edit.base.tone.TTSBusinessType r23 = com.vega.edit.base.tone.TTSBusinessType.f88857c
            com.vega.audio.tone.util.TextToSpeechReportInfo r28 = new com.vega.audio.tone.util.TextToSpeechReportInfo
            com.vega.audio.dubbing.AIDubbingUtil r3 = com.vega.audio.dubbing.AIDubbingUtil.f72854a
            r3.getClass()
            boolean r3 = com.vega.audio.dubbing.AIDubbingUtil.i(r11)
            if (r3 == 0) goto L318
            com.vega.audio.dubbing.model.AIDubbingEnterParameter r3 = r11.f72938d
            if (r3 == 0) goto L315
            java.lang.String r4 = r3.b
        L280:
            java.lang.String r3 = "audio"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r3 == 0) goto L305
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r29 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AI_DUBBING_EDITOR_AUDIO
        L28a:
            int r31 = r0.length()
            r32 = 1
            r27 = 0
            r34 = 0
            r40 = 482(0x1e2, float:6.75E-43)
            r30 = r17
            r33 = r27
            r36 = r17
            r37 = r17
            r38 = r17
            r41 = r17
            r28.<init>(r29, r30, r31, r32, r33, r34, r36, r37, r38, r39, r40, r41)
            java.lang.String r31 = r28.toJson()
            java.util.Map r50 = r11.n6(r0)
            com.vega.audio.dubbing.model.AIDubbingViewModel$startReading$1 r28 = new com.vega.audio.dubbing.model.AIDubbingViewModel$startReading$1
            r41 = r28
            r43 = r18
            r44 = r11
            r45 = r15
            r41.<init>()
            com.vega.edit.base.tone.TextToSpeechIntent r0 = new com.vega.edit.base.tone.TextToSpeechIntent
            java.lang.String r20 = "ssml"
            java.lang.String r21 = "ai_dubbing"
            r26 = 0
            r53 = -542811(0xfffffffffff7b7a5, float:NaN)
            r54 = 24
            r19 = r17
            r22 = r17
            r24 = r17
            r25 = r17
            r29 = r17
            r30 = r27
            r32 = r17
            r33 = r27
            r34 = r17
            r35 = r17
            r36 = r17
            r37 = r17
            r38 = r17
            r39 = r17
            r40 = r17
            r41 = r27
            r42 = r17
            r43 = r17
            r44 = r27
            r45 = r27
            r46 = r27
            r47 = r27
            r49 = r14
            r51 = r17
            r52 = r27
            r16 = r0
            r18 = r1
            r16.<init>(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54)
            r2.f(r0)
            goto L79
        L305:
            java.lang.String r3 = "text"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r4, r3)
            if (r3 == 0) goto L311
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r29 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AI_DUBBING_EDITOR_TEXT
            goto L28a
        L311:
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r29 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AI_DUBBING_EDITOR
            goto L28a
        L315:
            r4 = 0
            goto L280
        L318:
            com.vega.aigcapi.materialgenerate.TextToSpeechReportScene r29 = com.vega.aigcapi.materialgenerate.TextToSpeechReportScene.AI_DUBBING_TOOL
            goto L28a
        L31c:
            r0 = r3
            goto L45
        L31f:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.model.AIDubbingViewModel.K6(com.vega.audio.dubbing.model.AIDubbingViewModel, com.vega.audio.dubbing.model.AIDubbingPageState, java.lang.String, kotlin.ranges.IntRange, com.lemon.lv.data.ToneType, boolean, boolean, java.lang.String, int):void");
    }

    public static void M6() {
        TextToSpeechTaskManager.f74281a.b(true);
    }

    public static /* synthetic */ void P6(AIDubbingViewModel aIDubbingViewModel, boolean z, int i) {
        if ((i & 1) != 0) {
            z = true;
        }
        aIDubbingViewModel.O6(z, false);
    }

    public static boolean z6(AIDubbingViewModel aIDubbingViewModel, ToneType toneType, IntRange intRange, String str, String str2, String str3, String str4, String str5, String str6, boolean z, int i) {
        boolean z2 = (i & 256) != 0;
        boolean z3 = (i & 512) == 0 ? z : false;
        aIDubbingViewModel.getClass();
        Intrinsics.checkNotNullParameter(toneType, "");
        Intrinsics.checkNotNullParameter(intRange, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(str6, "");
        boolean zU = aIDubbingViewModel.f.u(toneType.getName(), toneType.getVoiceType(), toneType.getPlatform(), str, str2, str3, str4, intRange.getFirst(), intRange.getLast(), str5, str6);
        aIDubbingViewModel.F.put(toneType.getVoiceType(), toneType);
        if (zU) {
            aIDubbingViewModel.O6(z2, z3);
        }
        return zU;
    }

    public final boolean A6() {
        AIDubbingReadingBundle second;
        AIDubbingReadingState aIDubbingReadingState;
        Pair<AIDubbingPageState, AIDubbingReadingBundle> value = this.A.getValue();
        if (value == null || (second = value.getSecond()) == null || (aIDubbingReadingState = second.f72928a) == null) {
            return false;
        }
        return !SetsKt__SetsKt.setOf((Object[]) new AIDubbingReadingState[]{AIDubbingReadingState.f72932c, AIDubbingReadingState.f, AIDubbingReadingState.f72933d}).contains(aIDubbingReadingState);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:71:0x0065 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00a0  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00c7  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00ce  */
    /* JADX WARN: Type inference failed for: r3v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final kotlin.Pair<com.vega.audio.dubbing.core.node.AIDubbingNodeVoice, com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> B6() {
        /*
            r10 = this;
            com.vega.audio.dubbing.core.AIDubbingDraftManager r2 = r10.f
            r1 = 0
            r0 = 2147483647(0x7fffffff, float:NaN)
            java.util.ArrayList r0 = r2.r(r1, r0)
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r2 = r0.iterator()
        L13:
            boolean r0 = r2.hasNext()
            r8 = 1
            if (r0 == 0) goto L2e
            java.lang.Object r1 = r2.next()
            r0 = r1
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeVoiceEnter
            r8 = r8 ^ r0
            if (r8 == 0) goto L13
            r4.add(r1)
            goto L13
        L2e:
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r4)
            kotlin.Pair r0 = (kotlin.Pair) r0
            r9 = 0
            if (r0 == 0) goto L60
            java.lang.Object r6 = r0.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r6 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r6
            if (r6 == 0) goto L61
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> r0 = r6.m
            if (r0 == 0) goto L61
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        L4c:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L65
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeProsodyEnter
            r0 = r0 ^ 1
            if (r0 == 0) goto L4c
            r3.add(r1)
            goto L4c
        L60:
            r6 = r9
        L61:
            java.util.List r3 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L65:
            int r0 = r4.size()
            if (r0 != r8) goto L73
            int r0 = r3.size()
            if (r0 != r8) goto L73
            if (r6 != 0) goto L74
        L73:
            return r9
        L74:
            java.lang.Object r5 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r3)
            com.vega.audio.dubbing.core.node.AIDubbingNodeProsody r5 = (com.vega.audio.dubbing.core.node.AIDubbingNodeProsody) r5
            if (r5 == 0) goto L73
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeBase> r0 = r5.f72893g
            if (r0 == 0) goto La0
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        L89:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L9b
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentBreak
            if (r0 == 0) goto L89
            r3.add(r1)
            goto L89
        L9b:
            boolean r7 = r3.isEmpty()
            goto La3
        La0:
            r7 = 1
            if (r5 == 0) goto L73
        La3:
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeBase> r0 = r5.f72893g
            if (r0 == 0) goto Lc7
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        Lb0:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto Lc2
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme
            if (r0 == 0) goto Lb0
            r3.add(r1)
            goto Lb0
        Lc2:
            boolean r4 = r3.isEmpty()
            goto Lca
        Lc7:
            r4 = 1
            if (r5 == 0) goto L73
        Lca:
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeBase> r0 = r5.f72893g
            if (r0 == 0) goto Led
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        Ld7:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto Le9
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentSayas
            if (r0 == 0) goto Ld7
            r3.add(r1)
            goto Ld7
        Le9:
            boolean r8 = r3.isEmpty()
        Led:
            if (r7 == 0) goto L73
            if (r4 == 0) goto L73
            if (r8 == 0) goto L73
            kotlin.Pair r0 = kotlin.TuplesKt.to(r6, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.model.AIDubbingViewModel.B6():kotlin.Pair");
    }

    public final void C6(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, Function1<? super String, Unit> function1) {
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
        Intrinsics.checkNotNullParameter(function1, "");
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new AIDubbingViewModel$refreshInputContent$1(this, str6, str, str2, str3, str4, str8, str7, str10, str9, str5, str11, function1, null), 2, null);
    }

    public final void D6(String str, Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "");
        if (str == null || str.length() == 0) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this, Dispatchers.getIO(), null, new AIDubbingViewModel$regenerateInputContent$1(this, str, function1, null), 2, null);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:51:0x009f */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00e0  */
    /* JADX WARN: Type inference failed for: r5v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E6(com.vega.audio.dubbing.model.AIDubbingPageState r9) {
        /*
            r8 = this;
            androidx.lifecycle.MutableLiveData<com.vega.audio.dubbing.model.AIDubbingPageState> r0 = r8.o
            java.lang.Object r2 = r0.getValue()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "setPageState -> currentPage="
            r1.<init>(r0)
            r1.append(r2)
            java.lang.String r0 = ", state="
            r1.append(r0)
            r1.append(r9)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "AIDubbingViewModel"
            com.vega.log.BLog.i(r0, r1)
            if (r2 != r9) goto L24
            return
        L24:
            M6()
            androidx.lifecycle.MutableLiveData<com.vega.audio.dubbing.model.AIDubbingPageState> r0 = r8.o
            com.vega.core.ext.LiveDataExtKt.o(r0, r9)
            com.vega.audio.dubbing.core.AIDubbingDraftManager r3 = r8.f
            int[] r1 = com.vega.audio.dubbing.model.AIDubbingViewModel.WhenMappings.f72940a
            int r0 = r9.ordinal()
            r0 = r1[r0]
            r4 = 0
            switch(r0) {
                case -1: goto L3f;
                case 0: goto L3a;
                case 1: goto L3f;
                case 2: goto Le3;
                case 3: goto L102;
                case 4: goto L121;
                case 5: goto L140;
                case 6: goto L15f;
                default: goto L3a;
            }
        L3a:
            r0 = 3
            P6(r8, r4, r0)
            return
        L3f:
            r0 = 2147483647(0x7fffffff, float:NaN)
            java.util.ArrayList r0 = r3.r(r4, r0)
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.Iterator r2 = r0.iterator()
        L4f:
            boolean r0 = r2.hasNext()
            r6 = 1
            if (r0 == 0) goto L6a
            java.lang.Object r1 = r2.next()
            r0 = r1
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeVoiceEnter
            r6 = r6 ^ r0
            if (r6 == 0) goto L4f
            r7.add(r1)
            goto L4f
        L6a:
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r7)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 == 0) goto L9b
            java.lang.Object r0 = r0.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r0 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r0
            if (r0 == 0) goto L9b
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> r0 = r0.m
            if (r0 == 0) goto L9b
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r2 = r0.iterator()
        L87:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L9f
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeProsodyEnter
            r0 = r0 ^ 1
            if (r0 == 0) goto L87
            r5.add(r1)
            goto L87
        L9b:
            java.util.List r5 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L9f:
            int r0 = r7.size()
            if (r0 > r6) goto Le0
            int r0 = r5.size()
            if (r0 > r6) goto Le0
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r5)
            com.vega.audio.dubbing.core.node.AIDubbingNodeProsody r0 = (com.vega.audio.dubbing.core.node.AIDubbingNodeProsody) r0
            if (r0 == 0) goto Lde
            java.lang.String r1 = r0.b
        Lb5:
            java.lang.String r0 = "1.0"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto Le0
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72876c
        Lbf:
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72873a
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72874c
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.b
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72878g
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72875d
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.e
            r3.j(r1, r0)
            goto L3a
        Lde:
            r1 = 0
            goto Lb5
        Le0:
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f
            goto Lbf
        Le3:
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r2 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72876c
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72873a
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.b
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.b
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72874c
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.e
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72875d
            r3.j(r2, r0)
            goto L3a
        L102:
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r2 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72876c
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72873a
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.b
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.b
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72874c
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.e
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72875d
            r3.j(r2, r0)
            goto L3a
        L121:
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.b
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72873a
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72876c
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.b
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72874c
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.e
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72875d
            r3.j(r1, r0)
            goto L3a
        L140:
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72876c
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72873a
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.b
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72874c
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.e
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.b
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72875d
            r3.j(r1, r0)
            goto L3a
        L15f:
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r2 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.f72876c
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72873a
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.b
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72874c
            r3.j(r2, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingVisibility r1 = com.vega.audio.dubbing.core.draft.AIDubbingVisibility.b
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.e
            r3.j(r1, r0)
            com.vega.audio.dubbing.core.draft.AIDubbingType r0 = com.vega.audio.dubbing.core.draft.AIDubbingType.f72875d
            r3.j(r2, r0)
            goto L3a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.model.AIDubbingViewModel.E6(com.vega.audio.dubbing.model.AIDubbingPageState):void");
    }

    public final void F6(IntRange intRange) {
        LiveDataExtKt.o(this.y, intRange);
    }

    public final void G6(AIDubbingNodeBase aIDubbingNodeBase) {
        LiveDataExtKt.o(this.k, aIDubbingNodeBase);
    }

    public final void H6(Pair<? extends AIDubbingNodeProsody, IntRange> pair) {
        LiveDataExtKt.o(this.s, pair);
    }

    public final void I6(Pair<? extends AIDubbingNodeVoice, IntRange> pair) {
        LiveDataExtKt.o(this.w, pair);
    }

    public final void J6(List<String> list, boolean z) {
        Intrinsics.checkNotNullParameter(list, "");
        ArrayList arrayList = new ArrayList();
        for (String str : list) {
            StringsKt__StringsKt.contains$default(str, "\n", false, 2, (Object) null);
            if (str.length() > 0) {
                arrayList.add(StringsKt__StringsKt.trim((CharSequence) str).toString());
            }
        }
        this.C.setValue(new SplitTextInfo(false, SplitStatus.f64886c, arrayList, z));
    }

    public final void L6(AudioRecorderOutput audioRecorderOutput, String str) {
        Intrinsics.checkNotNullParameter(audioRecorderOutput, "");
        Intrinsics.checkNotNullParameter(str, "");
        ((ISplitTextService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ISplitTextService.class), null)).a(audioRecorderOutput, str, ViewModelKt.a(this), new Function3<SplitStatus, List<? extends TextAudioData>, String, Unit>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$startRecordToComplexSeg$1

            /* loaded from: classes.dex */
            public /* synthetic */ class WhenMappings {
                static {
                    SplitStatus.values();
                }
            }

            {
                super(3);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function3
            public final Unit invoke(SplitStatus splitStatus, List<? extends TextAudioData> list, String str2) {
                SplitStatus splitStatus2 = splitStatus;
                Intrinsics.checkNotNullParameter(splitStatus2, "");
                Intrinsics.checkNotNullParameter(str2, "");
                this.e.getClass();
                int iOrdinal = splitStatus2.ordinal();
                if (iOrdinal != 0) {
                    if (iOrdinal != 2) {
                        VipToastHelper vipToastHelper = VipToastHelper.f86943a;
                        String strB = FunctionsKt.b(R.string.onr);
                        vipToastHelper.getClass();
                        VipToastHelper.a(strB);
                        LiveDataExtKt.o(this.e.b0, null);
                    } else {
                        LiveDataExtKt.o(this.e.b0, list);
                    }
                }
                return Unit.INSTANCE;
            }
        });
    }

    public final void N6(boolean z) {
        AIDubbingDraftManager aIDubbingDraftManager = this.f;
        aIDubbingDraftManager.invalidate();
        aIDubbingDraftManager.k(this.j);
        if (z) {
            MutableLiveData<SpannableString> mutableLiveData = this.f72939g;
            IAIDubbingDraft iAIDubbingDraftI = aIDubbingDraftManager.i();
            LiveDataExtKt.o(mutableLiveData, iAIDubbingDraftI != null ? iAIDubbingDraftI.b() : null);
        }
    }

    public final void O6(boolean z, boolean z2) {
        AIDubbingDraftManager aIDubbingDraftManager = this.f;
        aIDubbingDraftManager.invalidate();
        aIDubbingDraftManager.k(this.j);
        if (z) {
            MutableLiveData<SpannableString> mutableLiveData = this.f72939g;
            IAIDubbingDraft iAIDubbingDraftI = aIDubbingDraftManager.i();
            LiveDataExtKt.o(mutableLiveData, iAIDubbingDraftI != null ? iAIDubbingDraftI.b() : null);
        }
        ArrayList<Pair<AIDubbingNodeVoice, IntRange>> arrayListR = aIDubbingDraftManager.r(0, Integer.MAX_VALUE);
        boolean zQ6 = q6();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator<Pair<AIDubbingNodeVoice, IntRange>> it = arrayListR.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(it.next().getFirst().b);
        }
        boolean z3 = linkedHashSet.size() > 1;
        if (zQ6) {
            LiveDataExtKt.o(this.u, "none");
        } else if (z3) {
            LiveDataExtKt.o(this.u, "multi_tone_id");
        } else if (!z2) {
            Object objFirstOrNull = CollectionsKt___CollectionsKt.firstOrNull(linkedHashSet);
            LiveDataExtKt.o(this.u, objFirstOrNull != null ? objFirstOrNull : "none");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<Pair<AIDubbingNodeVoice, IntRange>> it2 = arrayListR.iterator();
        while (it2.hasNext()) {
            arrayList.addAll(it2.next().getFirst().m);
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            AIDubbingNodeProsody aIDubbingNodeProsody = (AIDubbingNodeProsody) it3.next();
            HashMap<String, String> map = new HashMap<>();
            map.put("path", "");
            aIDubbingNodeProsody.a(map);
        }
    }

    public final void clear() {
        this.M = false;
        this.L = false;
        this.K = "preview_single_list";
        this.G.setValue(null);
        this.H.setValue(null);
        this.f72935J.setValue(null);
        this.A.setValue(null);
        this.S.setValue(null);
        ((ArrayDeque) this.f.f72862a).clear();
    }

    public final void m6(final Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "");
        this.f.n(new Function1<Boolean, Unit>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$buildSayasIdleNode$1
            /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) {
                boolean zBooleanValue = bool.booleanValue();
                if (zBooleanValue) {
                    this.e.N6(true);
                }
                function1.invoke(Boolean.valueOf(zBooleanValue));
                return Unit.INSTANCE;
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01a0 A[PHI: r12
      0x01a0: PHI (r12v14 java.lang.String) = (r12v11 java.lang.String), (r12v15 java.lang.String) binds: [B:65:0x019e, B:63:0x019a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a6 A[PHI: r12
      0x01a6: PHI (r12v13 java.lang.String) = (r12v11 java.lang.String), (r12v14 java.lang.String) binds: [B:65:0x019e, B:67:0x01a4] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.Map<java.lang.String, java.lang.String> n6(java.lang.String r29) {
        /*
            r28 = this;
            int r0 = r29.length()
            r2 = 0
            if (r0 != 0) goto L3b
            r0 = 1
        L8:
            r1 = 2147483647(0x7fffffff, float:NaN)
            r4 = 10
            r13 = r28
            if (r0 == 0) goto L5e
            com.vega.audio.dubbing.core.AIDubbingDraftManager r0 = r13.f
            java.util.ArrayList r0 = r0.r(r2, r1)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        L20:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L3d
            java.lang.Object r1 = r2.next()
            r0 = r1
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeVoiceEnter
            r0 = r0 ^ 1
            if (r0 == 0) goto L20
            r3.add(r1)
            goto L20
        L3b:
            r0 = 0
            goto L8
        L3d:
            java.util.ArrayList r11 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, r4)
            r11.<init>(r0)
            java.util.Iterator r1 = r3.iterator()
        L4a:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto Lb2
            java.lang.Object r0 = r1.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            r11.add(r0)
            goto L4a
        L5e:
            com.vega.audio.dubbing.core.draft.AIDubbingDraftParser r0 = new com.vega.audio.dubbing.core.draft.AIDubbingDraftParser
            r0.<init>()
            com.vega.audio.dubbing.core.draft.AIDubbingDraftController r0 = com.vega.audio.dubbing.core.draft.AIDubbingDraftParser.d(r29)
            if (r0 == 0) goto Lb8
            java.util.ArrayList r0 = r0.r(r2, r1)
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        L76:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L91
            java.lang.Object r1 = r2.next()
            r0 = r1
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeVoiceEnter
            r0 = r0 ^ 1
            if (r0 == 0) goto L76
            r3.add(r1)
            goto L76
        L91:
            java.util.ArrayList r11 = new java.util.ArrayList
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r3, r4)
            r11.<init>(r0)
            java.util.Iterator r1 = r3.iterator()
        L9e:
            boolean r0 = r1.hasNext()
            if (r0 == 0) goto Lb2
            java.lang.Object r0 = r1.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            r11.add(r0)
            goto L9e
        Lb2:
            boolean r0 = r11.isEmpty()
            if (r0 == 0) goto Lc4
        Lb8:
            java.lang.String r1 = "AIDubbingViewModel"
            java.lang.String r0 = "buildSsmlReportMap -> failed, voiceList.isNullOrEmpty()"
            com.vega.log.BLog.w(r1, r0)
            java.util.Map r0 = kotlin.collections.MapsKt__MapsKt.emptyMap()
            return r0
        Lc4:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            java.util.Iterator r4 = r11.iterator()
        Lcd:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L10b
            java.lang.Object r3 = r4.next()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r3 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r3
            java.util.HashMap r2 = new java.util.HashMap
            r2.<init>()
            java.lang.String r1 = "status"
            java.lang.String r0 = "old"
            r2.put(r1, r0)
            r3.a(r2)
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> r0 = r3.m
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        Lf3:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L107
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeProsodyEnter
            r0 = r0 ^ 1
            if (r0 == 0) goto Lf3
            r3.add(r1)
            goto Lf3
        L107:
            kotlin.collections.CollectionsKt__MutableCollectionsKt.addAll(r10, r3)
            goto Lcd
        L10b:
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            java.util.ArrayList r6 = new java.util.ArrayList
            r6.<init>()
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.ArrayList r20 = new java.util.ArrayList
            r20.<init>()
            java.util.Iterator r19 = r11.iterator()
        L141:
            boolean r0 = r19.hasNext()
            if (r0 == 0) goto L1d0
            java.lang.Object r11 = r19.next()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r11 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r11
            java.lang.String r0 = r11.b
            com.lemon.lv.data.ToneType r18 = r13.t6(r0)
            java.lang.String r17 = ""
            if (r18 == 0) goto L19c
            java.util.List r0 = r18.getEmotionList()
            if (r0 == 0) goto L19c
            java.util.Iterator r16 = r0.iterator()
        L161:
            boolean r0 = r16.hasNext()
            if (r0 == 0) goto L1ce
            java.lang.Object r14 = r16.next()
            r12 = r14
            com.lemon.lv.data.Emotion r12 = (com.lemon.lv.data.Emotion) r12
            java.lang.String r15 = r12.getEmotion()
            java.lang.String r0 = r11.f72896g
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r0)
            if (r0 != 0) goto L192
            java.lang.String r15 = r12.getRole()
            java.lang.String r0 = r11.i
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r15, r0)
            if (r0 == 0) goto L161
            java.lang.String r12 = r12.getStyle()
            java.lang.String r0 = r11.j
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r0)
            if (r0 == 0) goto L161
        L192:
            com.lemon.lv.data.Emotion r14 = (com.lemon.lv.data.Emotion) r14
            if (r14 == 0) goto L19c
            java.lang.String r12 = r14.getNameKey()
            if (r12 != 0) goto L1a0
        L19c:
            r12 = r17
            if (r18 == 0) goto L1a6
        L1a0:
            java.lang.String r0 = r18.getResourceId()
            if (r0 != 0) goto L1a8
        L1a6:
            r0 = r17
        L1a8:
            r9.add(r0)
            java.lang.String r0 = r11.b
            r7.add(r0)
            java.lang.String r0 = r11.f72894c
            r6.add(r0)
            r5.add(r12)
            java.lang.String r0 = r11.i
            r4.add(r0)
            java.lang.String r0 = r11.j
            r3.add(r0)
            java.lang.String r0 = r11.f72896g
            r2.add(r0)
            java.lang.String r0 = r11.h
            r1.add(r0)
            goto L141
        L1ce:
            r14 = 0
            goto L192
        L1d0:
            java.util.Iterator r18 = r10.iterator()
            java.lang.String r12 = "-1.0"
            r17 = 0
            r16 = 0
        L1da:
            boolean r0 = r18.hasNext()
            if (r0 == 0) goto L2e9
            java.lang.Object r15 = r18.next()
            com.vega.audio.dubbing.core.node.AIDubbingNodeProsody r15 = (com.vega.audio.dubbing.core.node.AIDubbingNodeProsody) r15
            java.lang.String r0 = r15.b
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r12, r0)
            if (r0 != 0) goto L1f2
            int r17 = r17 + 1
            java.lang.String r12 = r15.b
        L1f2:
            com.vega.audio.dubbing.core.AIDubbingDraftManager r14 = r13.f
            kotlin.ranges.IntRange r11 = new kotlin.ranges.IntRange
            r10 = 2147483647(0x7fffffff, float:NaN)
            r0 = 0
            r11.<init>(r0, r10)
            java.util.ArrayList r11 = r14.x(r15, r11)
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.Iterator r15 = r11.iterator()
        L20a:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L223
            java.lang.Object r10 = r15.next()
            r0 = r10
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentBreak
            if (r0 == 0) goto L20a
            r14.add(r10)
            goto L20a
        L223:
            int r0 = r14.size()
            int r16 = r16 + r0
            java.util.ArrayList r10 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r11, r0)
            r10.<init>(r0)
            java.util.Iterator r14 = r11.iterator()
        L238:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L24c
            java.lang.Object r0 = r14.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            r10.add(r0)
            goto L238
        L24c:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.Iterator r15 = r10.iterator()
        L255:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L267
            java.lang.Object r10 = r15.next()
            boolean r0 = r10 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme
            if (r0 == 0) goto L255
            r14.add(r10)
            goto L255
        L267:
            java.util.Iterator r15 = r14.iterator()
        L26b:
            boolean r0 = r15.hasNext()
            if (r0 == 0) goto L293
            java.lang.Object r14 = r15.next()
            com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme r14 = (com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme) r14
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r0 = r14.b
            r10.append(r0)
            r0 = 58
            r10.append(r0)
            java.lang.String r0 = r14.e
            r10.append(r0)
            java.lang.String r0 = r10.toString()
            r8.add(r0)
            goto L26b
        L293:
            java.util.ArrayList r10 = new java.util.ArrayList
            r0 = 10
            int r0 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r11, r0)
            r10.<init>(r0)
            java.util.Iterator r11 = r11.iterator()
        L2a2:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L2b6
            java.lang.Object r0 = r11.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            r10.add(r0)
            goto L2a2
        L2b6:
            java.util.ArrayList r14 = new java.util.ArrayList
            r14.<init>()
            java.util.Iterator r11 = r10.iterator()
        L2bf:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L2d1
            java.lang.Object r10 = r11.next()
            boolean r0 = r10 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentSayas
            if (r0 == 0) goto L2bf
            r14.add(r10)
            goto L2bf
        L2d1:
            java.util.Iterator r11 = r14.iterator()
        L2d5:
            boolean r0 = r11.hasNext()
            if (r0 == 0) goto L1da
            java.lang.Object r0 = r11.next()
            com.vega.audio.dubbing.core.node.AIDubbingNodeContent r0 = (com.vega.audio.dubbing.core.node.AIDubbingNodeContent) r0
            java.lang.String r10 = r0.b
            r0 = r20
            r0.add(r10)
            goto L2d5
        L2e9:
            r0 = 12
            kotlin.Pair[] r0 = new kotlin.Pair[r0]
            java.lang.String r10 = ","
            r11 = 0
            r23 = 0
            r13 = 0
            r15 = 62
            r12 = r11
            r14 = r11
            r9 = r9
            java.lang.String r10 = kotlin.collections.CollectionsKt.j(r9, r10, r11, r12, r13, r14, r15)
            java.lang.String r9 = "resource_id"
            kotlin.Pair r9 = kotlin.TuplesKt.to(r9, r10)
            r0[r13] = r9
            java.lang.String r22 = ","
            r25 = 0
            r27 = 62
            r24 = r23
            r26 = r23
            r21 = r7
            java.lang.String r9 = kotlin.collections.CollectionsKt.j(r21, r22, r23, r24, r25, r26, r27)
            java.lang.String r7 = "speaker_id"
            kotlin.Pair r9 = kotlin.TuplesKt.to(r7, r9)
            r7 = 1
            r0[r7] = r9
            java.lang.String r10 = ","
            r9 = r6
            r11 = r23
            r12 = r23
            r13 = r25
            r14 = r23
            r15 = r27
            java.lang.String r7 = kotlin.collections.CollectionsKt.j(r9, r10, r11, r12, r13, r14, r15)
            java.lang.String r6 = "tone"
            kotlin.Pair r7 = kotlin.TuplesKt.to(r6, r7)
            r6 = 2
            r0[r6] = r7
            java.lang.String r10 = ","
            r9 = r5
            r11 = r23
            r12 = r23
            r13 = r25
            r14 = r23
            r15 = r27
            java.lang.String r6 = kotlin.collections.CollectionsKt.j(r9, r10, r11, r12, r13, r14, r15)
            java.lang.String r5 = "emotion_name_key"
            kotlin.Pair r6 = kotlin.TuplesKt.to(r5, r6)
            r5 = 3
            r0[r5] = r6
            java.lang.String r10 = ","
            r9 = r4
            r11 = r23
            r12 = r23
            r13 = r25
            r14 = r23
            r15 = r27
            java.lang.String r5 = kotlin.collections.CollectionsKt.j(r9, r10, r11, r12, r13, r14, r15)
            java.lang.String r4 = "emotion_role"
            kotlin.Pair r5 = kotlin.TuplesKt.to(r4, r5)
            r4 = 4
            r0[r4] = r5
            java.lang.String r10 = ","
            r9 = r3
            r11 = r23
            r12 = r23
            r13 = r25
            r14 = r23
            r15 = r27
            java.lang.String r4 = kotlin.collections.CollectionsKt.j(r9, r10, r11, r12, r13, r14, r15)
            java.lang.String r3 = "emotion_style"
            kotlin.Pair r4 = kotlin.TuplesKt.to(r3, r4)
            r3 = 5
            r0[r3] = r4
            java.lang.String r10 = ","
            r9 = r2
            r11 = r23
            r12 = r23
            r13 = r25
            r14 = r23
            r15 = r27
            java.lang.String r3 = kotlin.collections.CollectionsKt.j(r9, r10, r11, r12, r13, r14, r15)
            java.lang.String r2 = "emotion_sami_selection"
            kotlin.Pair r3 = kotlin.TuplesKt.to(r2, r3)
            r2 = 6
            r0[r2] = r3
            java.lang.String r2 = ","
            r5 = 0
            r7 = 62
            r1 = r1
            r3 = r23
            r4 = r23
            r6 = r23
            java.lang.String r2 = kotlin.collections.CollectionsKt.j(r1, r2, r3, r4, r5, r6, r7)
            java.lang.String r1 = "emotion_scale"
            kotlin.Pair r2 = kotlin.TuplesKt.to(r1, r2)
            r1 = 7
            r0[r1] = r2
            java.lang.String r2 = "speed"
            java.lang.String r1 = java.lang.String.valueOf(r17)
            kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r1)
            r1 = 8
            r0[r1] = r2
            java.lang.String r2 = "pasuse"
            java.lang.String r1 = java.lang.String.valueOf(r16)
            kotlin.Pair r2 = kotlin.TuplesKt.to(r2, r1)
            r1 = 9
            r0[r1] = r2
            boolean r1 = r8.isEmpty()
            java.lang.String r3 = "none"
            if (r1 == 0) goto L40e
            r2 = r3
        L3dd:
            java.lang.String r1 = "pronunciation"
            kotlin.Pair r2 = kotlin.TuplesKt.to(r1, r2)
            r1 = 10
            r0[r1] = r2
            boolean r1 = r20.isEmpty()
            if (r1 == 0) goto L3fc
        L3ed:
            java.lang.String r1 = "word_split"
            kotlin.Pair r2 = kotlin.TuplesKt.to(r1, r3)
            r1 = 11
            r0[r1] = r2
            java.util.Map r0 = kotlin.collections.MapsKt__MapsKt.mapOf(r0)
            return r0
        L3fc:
            java.lang.String r2 = ","
            r5 = 0
            r7 = 62
            r1 = r20
            r3 = r23
            r4 = r23
            r6 = r23
            java.lang.String r3 = kotlin.collections.CollectionsKt.j(r1, r2, r3, r4, r5, r6, r7)
            goto L3ed
        L40e:
            java.lang.String r9 = ","
            r8 = r8
            r10 = r23
            r11 = r23
            r13 = r23
            r12 = r5
            r14 = r7
            java.lang.String r2 = kotlin.collections.CollectionsKt.j(r8, r9, r10, r11, r12, r13, r14)
            goto L3dd
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.model.AIDubbingViewModel.n6(java.lang.String):java.util.Map");
    }

    public final void o6() {
        AIDubbingNodeProsody aIDubbingNodeProsody;
        ArrayList<AIDubbingNodeBase> arrayList;
        AIDubbingDraftManager aIDubbingDraftManager = this.f;
        aIDubbingDraftManager.z(SetsKt__SetsKt.setOf((Object[]) new String[]{"old", "idle", "selected", "invalidate", "modified"}));
        aIDubbingDraftManager.v(null, SetsKt__SetsKt.setOf((Object[]) new String[]{"old", "idle", "selected", "invalidate", "modified"}));
        aIDubbingDraftManager.u("", "", "", "", "", "", "", 0, Integer.MAX_VALUE, "", "");
        AIDubbingUtil.f72854a.getClass();
        aIDubbingDraftManager.q(0, Integer.MAX_VALUE, "1.0", AIDubbingUtil.w("1.0"));
        ArrayList<Pair<AIDubbingNodeVoice, IntRange>> arrayListR = aIDubbingDraftManager.r(0, Integer.MAX_VALUE);
        ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayListR, 10));
        Iterator<Pair<AIDubbingNodeVoice, IntRange>> it = arrayListR.iterator();
        while (it.hasNext()) {
            arrayList2.add(it.next().getFirst());
        }
        AIDubbingNodeVoice aIDubbingNodeVoice = (AIDubbingNodeVoice) CollectionsKt___CollectionsKt.firstOrNull((List) arrayList2);
        if (aIDubbingNodeVoice != null && (aIDubbingNodeProsody = (AIDubbingNodeProsody) CollectionsKt___CollectionsKt.firstOrNull((List) aIDubbingNodeVoice.m)) != null && (arrayList = aIDubbingNodeProsody.f72893g) != null) {
            CollectionsKt__MutableCollectionsKt.removeAll((List) arrayList, (Function1) new Function1<AIDubbingNodeBase, Boolean>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$cleanAllConfigure$1$1
                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(AIDubbingNodeBase aIDubbingNodeBase) {
                    Intrinsics.checkNotNullParameter(aIDubbingNodeBase, "");
                    return Boolean.valueOf(aIDubbingNodeBase instanceof AIDubbingNodeContentBreak);
                }
            });
        }
        AIDubbingVisibility aIDubbingVisibility = AIDubbingVisibility.f72876c;
        aIDubbingDraftManager.j(aIDubbingVisibility, AIDubbingType.f72873a);
        aIDubbingDraftManager.j(aIDubbingVisibility, AIDubbingType.f72874c);
        P6(this, true, 2);
    }

    public final boolean p6(AIDubbingNodeContentPhoneme aIDubbingNodeContentPhoneme) {
        return this.f.v(aIDubbingNodeContentPhoneme, SetsKt__SetsKt.setOf((Object[]) new String[]{"old", "idle", "invalidate"}));
    }

    /* JADX WARN: Removed duplicated region for block: B:45:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean q6() {
        /*
            r7 = this;
            com.vega.audio.dubbing.core.AIDubbingDraftManager r1 = r7.f
            r0 = 2147483647(0x7fffffff, float:NaN)
            r3 = 0
            java.util.ArrayList r6 = r1.r(r3, r0)
            java.util.ArrayList r5 = new java.util.ArrayList
            r5.<init>()
            java.util.Iterator r4 = r6.iterator()
        L13:
            boolean r0 = r4.hasNext()
            r2 = 1
            if (r0 == 0) goto L2e
            java.lang.Object r1 = r4.next()
            r0 = r1
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeVoiceEnter
            r2 = r2 ^ r0
            if (r2 == 0) goto L13
            r5.add(r1)
            goto L13
        L2e:
            boolean r0 = r5.isEmpty()
            if (r0 == 0) goto L3c
        L34:
            boolean r0 = r6.isEmpty()
            if (r0 == 0) goto L3b
        L3a:
            r3 = 1
        L3b:
            return r3
        L3c:
            java.util.Iterator r4 = r5.iterator()
        L40:
            boolean r0 = r4.hasNext()
            if (r0 == 0) goto L34
            java.lang.Object r0 = r4.next()
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r2 = r0.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r2 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r2
            java.lang.String r0 = r2.b
            int r0 = r0.length()
            if (r0 != 0) goto L5b
            goto L3a
        L5b:
            java.lang.String r0 = r2.b
            java.lang.String r1 = "NA"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto L3a
            java.lang.String r0 = r2.f72894c
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto L3a
            java.lang.String r0 = r2.f72894c
            int r0 = r0.length()
            if (r0 != 0) goto L76
            goto L3a
        L76:
            java.lang.String r0 = r2.e
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto L3a
            java.lang.String r0 = r2.e
            int r0 = r0.length()
            if (r0 != 0) goto L40
            goto L3a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.model.AIDubbingViewModel.q6():boolean");
    }

    public final boolean r6() {
        ArrayList<Pair<AIDubbingNodeVoice, IntRange>> arrayListR = this.f.r(0, Integer.MAX_VALUE);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayListR, 10));
        Iterator<Pair<AIDubbingNodeVoice, IntRange>> it = arrayListR.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getFirst());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, ((AIDubbingNodeVoice) it2.next()).m);
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, ((AIDubbingNodeProsody) it3.next()).f72893g);
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            Object next = it4.next();
            AIDubbingNodeBase aIDubbingNodeBase = (AIDubbingNodeBase) next;
            if ((aIDubbingNodeBase instanceof AIDubbingNodeContentPhoneme) && Intrinsics.areEqual(((AIDubbingNodeContent) aIDubbingNodeBase).f72883c, "modified") && !aIDubbingNodeBase.c()) {
                return next != null;
            }
        }
        return false;
    }

    public final boolean s6() {
        ArrayList<Pair<AIDubbingNodeVoice, IntRange>> arrayListR = this.f.r(0, Integer.MAX_VALUE);
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayListR, 10));
        Iterator<Pair<AIDubbingNodeVoice, IntRange>> it = arrayListR.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getFirst());
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, ((AIDubbingNodeVoice) it2.next()).m);
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it3 = arrayList2.iterator();
        while (it3.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList3, ((AIDubbingNodeProsody) it3.next()).f72893g);
        }
        Iterator it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            Object next = it4.next();
            AIDubbingNodeBase aIDubbingNodeBase = (AIDubbingNodeBase) next;
            if ((aIDubbingNodeBase instanceof AIDubbingNodeContentSayas) && Intrinsics.areEqual(((AIDubbingNodeContent) aIDubbingNodeBase).f72883c, "modified") && !aIDubbingNodeBase.c()) {
                return next != null;
            }
        }
        return false;
    }

    public final ToneType t6(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Objects.toString(this.F.get(str));
        return this.F.get(str);
    }

    public final String u6() {
        String strC;
        IAIDubbingDraft iAIDubbingDraftI = this.f.i();
        return (iAIDubbingDraftI == null || (strC = iAIDubbingDraftI.c()) == null) ? "" : strC;
    }

    public final Job v6(String str) {
        String json;
        Intrinsics.checkNotNullParameter(str, "");
        AIDubbingEnterParameter aIDubbingEnterParameter = this.f72938d;
        String str2 = aIDubbingEnterParameter != null ? aIDubbingEnterParameter.b : null;
        if (Intrinsics.areEqual(str2, "text")) {
            BabiUtil.f79727a.getClass();
            json = ExtentionKt.toJson(BabiUtil.q());
        } else if (Intrinsics.areEqual(str2, "audio")) {
            BabiUtil.f79727a.getClass();
            json = ExtentionKt.toJson(BabiUtil.m());
        } else {
            BabiUtil.f79727a.getClass();
            json = ExtentionKt.toJson(BabiUtil.f0());
        }
        return ((ISplitTextService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ISplitTextService.class), null)).b(str, json, new Function2<SplitTextData, String, Unit>() { // from class: com.vega.audio.dubbing.model.AIDubbingViewModel$getSplitText$1
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(SplitTextData splitTextData, String str3) {
                SplitTextData splitTextData2 = splitTextData;
                String str4 = str3;
                Intrinsics.checkNotNullParameter(splitTextData2, "");
                Intrinsics.checkNotNullParameter(str4, "");
                AIDubbingViewModel aIDubbingViewModel = this.e;
                aIDubbingViewModel.E = str4;
                SplitStatus splitStatus = splitTextData2.f64888a;
                if (splitStatus == SplitStatus.f64886c) {
                    List<String> list = splitTextData2.b;
                    if (list != null) {
                        aIDubbingViewModel.J6(list, true);
                    }
                } else {
                    aIDubbingViewModel.C.setValue(new SplitTextInfo(splitStatus == SplitStatus.f64885a, splitStatus, splitTextData2.b, false));
                }
                return Unit.INSTANCE;
            }
        }, ViewModelKt.a(this));
    }

    public final String w6() {
        ArrayList<Pair<AIDubbingNodeVoice, IntRange>> arrayListR = this.f.r(0, Integer.MAX_VALUE);
        ArrayList arrayList = new ArrayList();
        Iterator<Pair<AIDubbingNodeVoice, IntRange>> it = arrayListR.iterator();
        while (it.hasNext()) {
            CollectionsKt__MutableCollectionsKt.addAll(arrayList, it.next().getFirst().m);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            ArrayList<AIDubbingNodeBase> arrayList3 = ((AIDubbingNodeProsody) it2.next()).f72893g;
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList3, 10));
            Iterator<AIDubbingNodeBase> it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                AIDubbingNodeBase next = it3.next();
                arrayList4.add(next instanceof AIDubbingNodeContentText ? ((AIDubbingNodeContent) next).b : next instanceof AIDubbingNodeContentSayas ? ((AIDubbingNodeContent) next).b : next instanceof AIDubbingNodeContentPhoneme ? ((AIDubbingNodeContent) next).b : "");
            }
            CollectionsKt__MutableCollectionsKt.addAll(arrayList2, arrayList4);
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList2, "", null, null, 0, null, null, 62, null);
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:77:0x0063 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c3 A[PHI: r10
      0x00c3: PHI (r10v1 boolean) = (r10v0 boolean), (r10v5 boolean) binds: [B:38:0x009d, B:40:0x00a1] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00ca  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00ea A[PHI: r4 r10
      0x00ea: PHI (r4v1 boolean) = (r4v0 boolean), (r4v3 boolean) binds: [B:49:0x00c4, B:51:0x00c8] A[DONT_GENERATE, DONT_INLINE]
      0x00ea: PHI (r10v2 boolean) = (r10v1 boolean), (r10v4 boolean) binds: [B:49:0x00c4, B:51:0x00c8] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Type inference failed for: r8v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.util.ArrayList] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean x6() {
        /*
            r11 = this;
            com.vega.audio.dubbing.core.AIDubbingDraftManager r1 = r11.f
            r0 = 2147483647(0x7fffffff, float:NaN)
            r5 = 0
            java.util.ArrayList r0 = r1.r(r5, r0)
            java.util.ArrayList r9 = new java.util.ArrayList
            r9.<init>()
            java.util.Iterator r2 = r0.iterator()
        L13:
            boolean r0 = r2.hasNext()
            r7 = 1
            if (r0 == 0) goto L2e
            java.lang.Object r1 = r2.next()
            r0 = r1
            kotlin.Pair r0 = (kotlin.Pair) r0
            java.lang.Object r0 = r0.getFirst()
            boolean r0 = r0 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeVoiceEnter
            r7 = r7 ^ r0
            if (r7 == 0) goto L13
            r9.add(r1)
            goto L13
        L2e:
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r9)
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 == 0) goto L5f
            java.lang.Object r0 = r0.getFirst()
            com.vega.audio.dubbing.core.node.AIDubbingNodeVoice r0 = (com.vega.audio.dubbing.core.node.AIDubbingNodeVoice) r0
            if (r0 == 0) goto L5f
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeProsody> r0 = r0.m
            if (r0 == 0) goto L5f
            java.util.ArrayList r8 = new java.util.ArrayList
            r8.<init>()
            java.util.Iterator r2 = r0.iterator()
        L4b:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L63
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeProsodyEnter
            r0 = r0 ^ 1
            if (r0 == 0) goto L4b
            r8.add(r1)
            goto L4b
        L5f:
            java.util.List r8 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
        L63:
            boolean r0 = r9.isEmpty()
            if (r0 != 0) goto L6f
            boolean r0 = r8.isEmpty()
            if (r0 == 0) goto L70
        L6f:
            return r7
        L70:
            java.lang.Object r6 = kotlin.collections.CollectionsKt___CollectionsKt.firstOrNull(r8)
            com.vega.audio.dubbing.core.node.AIDubbingNodeProsody r6 = (com.vega.audio.dubbing.core.node.AIDubbingNodeProsody) r6
            if (r6 == 0) goto L9c
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeBase> r0 = r6.f72893g
            if (r0 == 0) goto L9c
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        L85:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L97
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentBreak
            if (r0 == 0) goto L85
            r3.add(r1)
            goto L85
        L97:
            boolean r10 = r3.isEmpty()
            goto L9f
        L9c:
            r10 = 1
            if (r6 == 0) goto Lc3
        L9f:
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeBase> r0 = r6.f72893g
            if (r0 == 0) goto Lc3
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        Lac:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto Lbe
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentPhoneme
            if (r0 == 0) goto Lac
            r3.add(r1)
            goto Lac
        Lbe:
            boolean r4 = r3.isEmpty()
            goto Lc6
        Lc3:
            r4 = 1
            if (r6 == 0) goto Lea
        Lc6:
            java.util.ArrayList<com.vega.audio.dubbing.core.node.AIDubbingNodeBase> r0 = r6.f72893g
            if (r0 == 0) goto Lea
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r2 = r0.iterator()
        Ld3:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto Le5
            java.lang.Object r1 = r2.next()
            boolean r0 = r1 instanceof com.vega.audio.dubbing.core.node.AIDubbingNodeContentSayas
            if (r0 == 0) goto Ld3
            r3.add(r1)
            goto Ld3
        Le5:
            boolean r2 = r3.isEmpty()
            goto Leb
        Lea:
            r2 = 1
        Leb:
            int r0 = r9.size()
            if (r0 > r7) goto L10a
            int r0 = r8.size()
            if (r0 > r7) goto L10a
            if (r6 == 0) goto L10a
            java.lang.String r1 = r6.b
            java.lang.String r0 = "1.0"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L10a
            if (r10 == 0) goto L10a
            if (r4 == 0) goto L10a
            if (r2 == 0) goto L10a
            r5 = 1
        L10a:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.dubbing.model.AIDubbingViewModel.x6():boolean");
    }

    public final boolean y6(int i, int i2, String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "");
        AIDubbingUtil.f72854a.getClass();
        boolean zQ = this.f.q(i, i2, str, AIDubbingUtil.w(str));
        if (zQ) {
            P6(this, z, 2);
        }
        Objects.toString(this.f.i());
        return zQ;
    }
}
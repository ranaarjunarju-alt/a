package com.vega.edit.videoagent;

import android.net.Uri;
import androidx.activity.ComponentActivity;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.lemon.lvoverseas.R;
import com.vega.aivideoagent.VideoAgentViewModel;
import com.vega.aivideoagent.api.IConversationTaskManager;
import com.vega.aivideoagent.api.TaskProgressListener;
import com.vega.aivideoagent.api.TaskStepState;
import com.vega.aivideoagent.model.message.AgentTaskMessage;
import com.vega.aivideoagent.model.message.AgentToolCallDescData;
import com.vega.aivideoagent.model.message.TaskMediaType;
import com.vega.aivideoagent.model.message.TaskStatus;
import com.vega.clipflow.ClipflowAsyncNode;
import com.vega.clipflow.swig.ClipFlowTaskExecuteState;
import com.vega.core.context.SPIService;
import com.vega.edit.base.viewmodel.IEditUIViewModel;
import com.vega.edit.editpage.activity.EditActivity;
import com.vega.edit.videoagent.IAgentTool;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.koin.ScopeProxy;
import com.vega.log.BLog;
import com.vega.ui.util.ViewExKt;
import java.lang.ref.WeakReference;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import kotlinx.coroutines.flow.FlowKt__ShareKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes21.dex */
public final class AgentToolMessageHelper implements IAgentTool, TaskProgressListener {
    public static final Companion l = new Companion();
    public static final Map<String, AgentToolCallDescData> m = MapsKt__MapsKt.mapOf(TuplesKt.to("ai_writer", new AgentToolCallDescData(ViewExKt.h(R.string.nh__res_0x7f121ce5), ViewExKt.h(R.string.nio), ViewExKt.h(R.string.nh4), ViewExKt.h(R.string.niv), ViewExKt.h(R.string.nha), ViewExKt.h(R.string.nhh))), TuplesKt.to("text_polishing", new AgentToolCallDescData(ViewExKt.h(R.string.nh__res_0x7f121ce5), ViewExKt.h(R.string.nio), ViewExKt.h(R.string.nh4), ViewExKt.h(R.string.niv), ViewExKt.h(R.string.nha), ViewExKt.h(R.string.nhh))), TuplesKt.to("smart_highlight", new AgentToolCallDescData(ViewExKt.h(R.string.nik), ViewExKt.h(R.string.nir), ViewExKt.h(R.string.nh7), ViewExKt.h(R.string.niz), ViewExKt.h(R.string.nhd), ViewExKt.h(R.string.nh0))), TuplesKt.to("remove_filler_word", new AgentToolCallDescData(ViewExKt.h(R.string.nh_), ViewExKt.h(R.string.niq), ViewExKt.h(R.string.nh6), ViewExKt.h(R.string.niy), ViewExKt.h(R.string.nhc), ViewExKt.h(R.string.nhj))), TuplesKt.to("smart_roughly_edit", new AgentToolCallDescData(ViewExKt.h(R.string.nh__res_0x7f121ce6), ViewExKt.h(R.string.nip), ViewExKt.h(R.string.nh5), ViewExKt.h(R.string.nix), ViewExKt.h(R.string.nhb), ViewExKt.h(R.string.nhi))), TuplesKt.to("ai_creator_title", new AgentToolCallDescData(ViewExKt.h(R.string.nin), ViewExKt.h(R.string.niu), ViewExKt.h(R.string.nh__res_0x7f121ce4), ViewExKt.h(R.string.nic), ViewExKt.h(R.string.nhg), ViewExKt.h(R.string.nh3))), TuplesKt.to("smart_editing_text", new AgentToolCallDescData(ViewExKt.h(R.string.nim), ViewExKt.h(R.string.nit), ViewExKt.h(R.string.nh9), ViewExKt.h(R.string.nib), ViewExKt.h(R.string.nhf), ViewExKt.h(R.string.nh2))));

    /* renamed from: a, reason: collision with root package name */
    public final String f96744a;
    public final String b;

    /* renamed from: c, reason: collision with root package name */
    public final MutableStateFlow<ToolCallStatus> f96745c;

    /* renamed from: d, reason: collision with root package name */
    public final StateFlow<ToolCallStatus> f96746d;
    public DraftInfo e;
    public Function0<Unit> f;

    /* renamed from: g, reason: collision with root package name */
    public boolean f96747g;
    public final Function2<Boolean, String, Unit> h;
    public Function0<? extends Map<String, String>> i;
    public boolean j;
    public AgentTaskMessage k;

    public static final class Companion {
    }

    /* loaded from: classes31.dex */
    public static final class DraftInfo {

        /* renamed from: a, reason: collision with root package name */
        public final long f96753a;
        public final String b;

        /* renamed from: c, reason: collision with root package name */
        public final TaskMediaType f96754c;

        public DraftInfo() {
            this(0);
        }

        /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0006: CONSTRUCTOR 
          (0 long)
          ("")
          (wrap:com.vega.aivideoagent.model.message.TaskMediaType:0x0000: SGET  A[WRAPPED] com.vega.aivideoagent.model.message.TaskMediaType.c com.vega.aivideoagent.model.message.TaskMediaType)
         A[MD:(long, java.lang.String, com.vega.aivideoagent.model.message.TaskMediaType):void (m)] call: com.vega.edit.videoagent.AgentToolMessageHelper.DraftInfo.<init>(long, java.lang.String, com.vega.aivideoagent.model.message.TaskMediaType):void type: THIS */
        public /* synthetic */ DraftInfo(int i) {
            this(0L, "", TaskMediaType.f71831c);
        }

        public DraftInfo(long j, String str, TaskMediaType taskMediaType) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(taskMediaType, "");
            this.f96753a = j;
            this.b = str;
            this.f96754c = taskMediaType;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof DraftInfo)) {
                return false;
            }
            DraftInfo draftInfo = (DraftInfo) obj;
            return this.f96753a == draftInfo.f96753a && Intrinsics.areEqual(this.b, draftInfo.b) && this.f96754c == draftInfo.f96754c;
        }

        public final int hashCode() {
            long j = this.f96753a;
            return (((((int) (j ^ (j >>> 32))) * 31) + this.b.hashCode()) * 31) + this.f96754c.hashCode();
        }

        public final String toString() {
            return "DraftInfo(duration=" + this.f96753a + ", coverPath=" + this.b + ", mediaType=" + this.f96754c + ')';
        }
    }

    /* loaded from: classes16.dex */
    public /* synthetic */ class WhenMappings {
        static {
            TaskStatus.values();
            ClipFlowTaskExecuteState.values();
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public AgentToolMessageHelper(ClipflowAsyncNode<?, ?> clipflowAsyncNode) {
        this(clipflowAsyncNode.o(), clipflowAsyncNode.l());
        Intrinsics.checkNotNullParameter(clipflowAsyncNode, "");
    }

    public AgentToolMessageHelper(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        this.f96744a = str;
        this.b = str2;
        MutableStateFlow<ToolCallStatus> MutableStateFlow = StateFlowKt.MutableStateFlow(ToolCallStatus.f96760a);
        this.f96745c = MutableStateFlow;
        this.f96746d = FlowKt__ShareKt.asStateFlow(MutableStateFlow);
        this.e = new DraftInfo(0);
        Function2<Boolean, String, Unit> function2 = new Function2<Boolean, String, Unit>() { // from class: com.vega.edit.videoagent.AgentToolMessageHelper$confirmListener$1
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(Boolean bool, String str3) {
                bool.booleanValue();
                Intrinsics.checkNotNullParameter(str3, "");
                if (Intrinsics.areEqual(str3, this.e.f96744a)) {
                    this.e.getClass();
                    final EditActivity editActivity = (EditActivity) IAgentTool.DefaultImpls.b().get();
                    if (editActivity != null) {
                        Lazy lazy = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.NONE, (Function0) new Function0<VideoAgentViewModel>() { // from class: com.vega.edit.videoagent.AgentToolMessageHelper$confirmListener$1$invoke$$inlined$factoryViewModel$1
                            public final /* synthetic */ Qualifier f = null;

                            /* renamed from: g, reason: collision with root package name */
                            public final /* synthetic */ Function0 f96748g = null;
                            public final /* synthetic */ Function0 h = null;

                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            /* JADX WARN: Type inference failed for: r0v3, types: [androidx.lifecycle.ViewModel, com.vega.aivideoagent.VideoAgentViewModel] */
                            @Override // kotlin.jvm.functions.Function0
                            public final VideoAgentViewModel invoke() {
                                CreationExtras defaultViewModelCreationExtras;
                                ComponentActivity componentActivity = editActivity;
                                Qualifier qualifier = this.f;
                                Function0 function0 = this.f96748g;
                                Function0 function02 = this.h;
                                ViewModelStore viewModelStore = componentActivity.getViewModelStore();
                                if (function0 == null || (defaultViewModelCreationExtras = (CreationExtras) function0.invoke()) == null) {
                                    defaultViewModelCreationExtras = componentActivity.getDefaultViewModelCreationExtras();
                                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                                }
                                ScopeProxy scopeProxyC = ScopeExKt.c(componentActivity);
                                KClass orCreateKotlinClass = Reflection.getOrCreateKotlinClass(VideoAgentViewModel.class);
                                Intrinsics.checkNotNull(viewModelStore);
                                return GetViewModelKt.a(orCreateKotlinClass, viewModelStore, defaultViewModelCreationExtras, qualifier, scopeProxyC, function02);
                            }
                        });
                        if (((VideoAgentViewModel) lazy.getValue()).K6()) {
                            this.e.f96747g = true;
                            ((VideoAgentViewModel) lazy.getValue()).s6();
                        }
                    }
                    AgentToolMessageHelper agentToolMessageHelper = this.e;
                    if (agentToolMessageHelper.f96745c.getValue() == ToolCallStatus.b) {
                        agentToolMessageHelper.f96745c.setValue(ToolCallStatus.f96760a);
                    }
                }
                return Unit.INSTANCE;
            }
        };
        this.h = function2;
        SPIService sPIService = SPIService.INSTANCE;
        ((IConversationTaskManager) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IConversationTaskManager.class), null)).t(this);
        ((IConversationTaskManager) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IConversationTaskManager.class), null)).g(function2);
    }

    public static String f(Map map) {
        Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//editor/function");
        for (Map.Entry entry : map.entrySet()) {
            builderPath.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        String string = builderPath.build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return string;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object h(com.vega.edit.videoagent.AgentToolMessageHelper r10, java.lang.String r11, com.vega.aivideoagent.model.message.TaskStatus r12, boolean r13, com.vega.edit.editpage.activity.EditActivity r14, kotlin.coroutines.Continuation r15, int r16) {
        /*
            r8 = r13
            r0 = r16 & 4
            if (r0 == 0) goto L6
            r8 = 0
        L6:
            r0 = r16 & 8
            r9 = 0
            if (r0 == 0) goto Lbd
        Lb:
            r4 = r10
            r4.getClass()
            if (r9 != 0) goto Lba
            java.lang.ref.WeakReference r0 = com.vega.edit.videoagent.IAgentTool.DefaultImpls.b()
            java.lang.Object r3 = r0.get()
            com.vega.edit.editpage.activity.EditActivity r3 = (com.vega.edit.editpage.activity.EditActivity) r3
        L1b:
            java.lang.String r2 = "AgentToolMessageHelper"
            if (r3 != 0) goto L24
            java.lang.String r0 = "null edit activity"
            com.vega.log.BLog.e(r2, r0)
        L24:
            java.lang.String r10 = ""
            r7 = r12
            if (r3 == 0) goto La7
            kotlin.LazyThreadSafetyMode r1 = kotlin.LazyThreadSafetyMode.NONE
            com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$$inlined$factoryViewModel$1 r0 = new com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$$inlined$factoryViewModel$1
            r0.<init>()
            kotlin.Lazy r0 = kotlin.LazyKt__LazyJVMKt.lazy(r1, r0)
            if (r0 == 0) goto La7
            java.lang.Object r0 = r0.getValue()
            com.vega.aivideoagent.VideoAgentViewModel r0 = (com.vega.aivideoagent.VideoAgentViewModel) r0
            if (r0 == 0) goto La7
            java.lang.String r1 = r4.b
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r10)
            java.util.Map<java.lang.String, com.vega.aivideoagent.model.message.AgentToolCallDescData> r0 = r0.v
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r0 = r0.get(r1)
            com.vega.aivideoagent.model.message.AgentToolCallDescData r0 = (com.vega.aivideoagent.model.message.AgentToolCallDescData) r0
            if (r0 == 0) goto La7
            java.lang.String r6 = r0.a(r7)
            if (r6 == 0) goto La7
            int r0 = r6.length()
            if (r0 <= 0) goto La7
        L5b:
            int r0 = r6.length()
            r5 = r11
            if (r0 != 0) goto L85
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "empty message, id: "
            r1.<init>(r0)
            r1.append(r5)
            java.lang.String r0 = ", status: "
            r1.append(r0)
            r1.append(r7)
            java.lang.String r0 = ", name: "
            r1.append(r0)
            java.lang.String r0 = r4.b
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.w(r2, r0)
        L85:
            com.vega.aivideoagent.model.message.TaskStatus r0 = com.vega.aivideoagent.model.message.TaskStatus.e
            if (r7 != r0) goto L9f
            boolean r0 = r4.j
            if (r0 != 0) goto L9f
            kotlin.jvm.functions.Function0<? extends java.util.Map<java.lang.String, java.lang.String>> r0 = r4.i
            if (r0 == 0) goto L9f
            java.lang.Object r0 = r0.invoke()
            java.util.Map r0 = (java.util.Map) r0
            if (r0 == 0) goto L9f
            java.lang.String r0 = f(r0)
            if (r0 != 0) goto La5
        L9f:
            r11 = r15
            java.lang.Object r0 = r4.g(r5, r6, r7, r8, r9, r10, r11)
            return r0
        La5:
            r10 = r0
            goto L9f
        La7:
            java.util.Map<java.lang.String, com.vega.aivideoagent.model.message.AgentToolCallDescData> r1 = com.vega.edit.videoagent.AgentToolMessageHelper.m
            java.lang.String r0 = r4.b
            java.lang.Object r0 = r1.get(r0)
            com.vega.aivideoagent.model.message.AgentToolCallDescData r0 = (com.vega.aivideoagent.model.message.AgentToolCallDescData) r0
            if (r0 == 0) goto Lb8
            java.lang.String r6 = r0.a(r7)
            goto L5b
        Lb8:
            r6 = r10
            goto L5b
        Lba:
            r3 = r9
            goto L1b
        Lbd:
            r9 = r14
            goto Lb
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.videoagent.AgentToolMessageHelper.h(com.vega.edit.videoagent.AgentToolMessageHelper, java.lang.String, com.vega.aivideoagent.model.message.TaskStatus, boolean, com.vega.edit.editpage.activity.EditActivity, kotlin.coroutines.Continuation, int):java.lang.Object");
    }

    @Override // com.vega.edit.videoagent.IAgentTool
    public final IEditUIViewModel a() {
        return IAgentTool.DefaultImpls.c(this);
    }

    @Override // com.vega.aivideoagent.api.TaskProgressListener
    public final void b(String str, ClipFlowTaskExecuteState clipFlowTaskExecuteState) {
        Function0<Unit> function0;
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(clipFlowTaskExecuteState, "");
        if (Intrinsics.areEqual(str, this.f96744a)) {
            BLog.i("AgentToolMessageHelper", "taskId: " + str + ", type: " + this.b + ", state: " + clipFlowTaskExecuteState);
            if (clipFlowTaskExecuteState == ClipFlowTaskExecuteState.TaskCanceled) {
                if (this.f96745c.getValue() != ToolCallStatus.b && (function0 = this.f) != null) {
                    function0.invoke();
                }
                this.f96745c.setValue(ToolCallStatus.f96761c);
            }
            int iOrdinal = clipFlowTaskExecuteState.ordinal();
            if (iOrdinal == 4 || iOrdinal == 5 || iOrdinal == 6) {
                SPIService sPIService = SPIService.INSTANCE;
                ((IConversationTaskManager) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IConversationTaskManager.class), null)).l(this.h);
                ((IConversationTaskManager) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IConversationTaskManager.class), null)).n(this);
            }
        }
    }

    @Override // com.vega.edit.videoagent.IAgentTool
    public final WeakReference<EditActivity> c() {
        return IAgentTool.DefaultImpls.b();
    }

    @Override // com.vega.aivideoagent.api.TaskProgressListener
    public final void d(double d2, String str) {
        TaskStepState taskStepState = TaskStepState.b;
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(taskStepState, "");
    }

    public final void e(VideoAgentViewModel videoAgentViewModel) {
        Intrinsics.checkNotNullParameter(videoAgentViewModel, "");
        if (this.f96747g) {
            this.f96747g = false;
            videoAgentViewModel.P6();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x01d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object g(java.lang.String r33, java.lang.String r34, com.vega.aivideoagent.model.message.TaskStatus r35, boolean r36, com.vega.edit.editpage.activity.EditActivity r37, java.lang.String r38, kotlin.coroutines.Continuation<? super com.vega.edit.videoagent.ToolCallStatus> r39) {
        /*
            r32 = this;
            r4 = r39
            r2 = r37
            boolean r0 = r4 instanceof com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$2
            r8 = r32
            if (r0 == 0) goto L1d3
            r5 = r4
            com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$2 r5 = (com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$2) r5
            int r3 = r5.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r3 & r1
            if (r0 == 0) goto L1d3
            int r3 = r3 - r1
            r5.s = r3
        L18:
            java.lang.Object r1 = r5.q
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r5.s
            r3 = 1
            if (r0 == 0) goto L2f
            if (r0 != r3) goto L1da
            kotlin.ResultKt.throwOnFailure(r1)
        L28:
            kotlinx.coroutines.flow.MutableStateFlow<com.vega.edit.videoagent.ToolCallStatus> r0 = r8.f96745c
            java.lang.Object r0 = r0.getValue()
            return r0
        L2f:
            kotlin.ResultKt.throwOnFailure(r1)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            java.lang.String r0 = "[Benchmarking] sendTaskMessage: time="
            r6.<init>(r0)
            long r0 = java.lang.System.currentTimeMillis()
            r6.append(r0)
            java.lang.String r0 = ", taskName="
            r6.append(r0)
            java.lang.String r0 = r8.b
            r6.append(r0)
            java.lang.String r0 = ", taskStatus="
            r6.append(r0)
            r7 = r35
            java.lang.String r0 = r7.name()
            r6.append(r0)
            java.lang.String r0 = ", content="
            r6.append(r0)
            r9 = r34
            r6.append(r9)
            java.lang.String r0 = r6.toString()
            java.lang.String r12 = "AgentToolMessageHelper"
            com.vega.log.BLog.i(r12, r0)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r0 = "sendTaskMessage: "
            r11.<init>(r0)
            r10 = r33
            r11.append(r10)
            java.lang.String r1 = ", "
            r11.append(r1)
            java.lang.String r0 = r8.f96744a
            r11.append(r0)
            r11.append(r1)
            java.lang.String r0 = r8.b
            r11.append(r0)
            r11.append(r1)
            r11.append(r9)
            r11.append(r1)
            r11.append(r7)
            r11.append(r1)
            r6 = r36
            r11.append(r6)
            java.lang.String r0 = r11.toString()
            com.vega.log.BLog.i(r12, r0)
            if (r2 != 0) goto Lb9
            java.lang.ref.WeakReference r0 = com.vega.edit.videoagent.IAgentTool.DefaultImpls.b()
            java.lang.Object r2 = r0.get()
            com.vega.edit.editpage.activity.EditActivity r2 = (com.vega.edit.editpage.activity.EditActivity) r2
            if (r2 != 0) goto Lb9
            kotlinx.coroutines.flow.MutableStateFlow<com.vega.edit.videoagent.ToolCallStatus> r0 = r8.f96745c
            java.lang.Object r0 = r0.getValue()
            return r0
        Lb9:
            kotlin.LazyThreadSafetyMode r1 = kotlin.LazyThreadSafetyMode.NONE
            com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$$inlined$factoryViewModel$2 r0 = new com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$$inlined$factoryViewModel$2
            r0.<init>()
            kotlin.Lazy r16 = kotlin.LazyKt__LazyJVMKt.lazy(r1, r0)
            int r1 = r7.ordinal()
            if (r1 == r3) goto L185
            r0 = 2
            if (r1 == r0) goto L178
            r0 = 3
            if (r1 == r0) goto L16b
            r0 = 4
            if (r1 == r0) goto L15e
            r0 = 5
            if (r1 == r0) goto L151
        Ld6:
            com.vega.aivideoagent.model.message.AgentTaskMessage r17 = new com.vega.aivideoagent.model.message.AgentTaskMessage
            java.lang.String r15 = r8.f96744a
            java.lang.String r14 = r8.b
            com.vega.edit.videoagent.AgentToolMessageHelper$DraftInfo r1 = r8.e
            java.lang.String r13 = r1.b
            com.vega.aivideoagent.model.message.TaskMediaType r0 = r1.f96754c
            int r12 = r0.f71833a
            long r0 = r1.f96753a
            int r11 = r7.f71837a
            r3 = 0
            r21 = 0
            r2 = r17
            r28 = 0
            r30 = 264(0x108, float:3.7E-43)
            r29 = r38
            r27 = r11
            r31 = r3
            r23 = r13
            r24 = r12
            r25 = r0
            r19 = r15
            r20 = r14
            r18 = r9
            r17.<init>(r18, r19, r20, r21, r23, r24, r25, r27, r28, r29, r30, r31)
            java.lang.String r9 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r9)
            r2.b = r10
            long r0 = java.lang.System.currentTimeMillis()
            r2.f71815d = r0
            com.vega.aivideoagent.model.message.TaskStatus r0 = com.vega.aivideoagent.model.message.TaskStatus.e
            if (r7 != r0) goto L119
            r8.k = r2
        L119:
            java.lang.Object r0 = r16.getValue()
            com.vega.aivideoagent.VideoAgentViewModel r0 = (com.vega.aivideoagent.VideoAgentViewModel) r0
            r0.a7(r2)
            com.vega.core.context.SPIService r1 = com.vega.core.context.SPIService.INSTANCE
            java.lang.Class<com.vega.aivideoagent.api.IConversationTaskManager> r0 = com.vega.aivideoagent.api.IConversationTaskManager.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            java.lang.Object r2 = r1.getImpl(r0, r3)
            com.vega.aivideoagent.api.IConversationTaskManager r2 = (com.vega.aivideoagent.api.IConversationTaskManager) r2
            java.lang.Object r0 = r16.getValue()
            com.vega.aivideoagent.VideoAgentViewModel r0 = (com.vega.aivideoagent.VideoAgentViewModel) r0
            com.vega.aivideoagent.conversation.VideoAgentConversation r0 = r0.P
            if (r0 == 0) goto L140
            java.lang.String r0 = r0.h()
            if (r0 != 0) goto L14f
        L140:
            java.lang.String r1 = r8.f96744a
            int r0 = r7.ordinal()
            switch(r0) {
                case 0: goto L192;
                case 1: goto L195;
                case 2: goto L198;
                case 3: goto L19b;
                case 4: goto L19e;
                case 5: goto L1a1;
                case 6: goto L19b;
                default: goto L149;
            }
        L149:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L14f:
            r9 = r0
            goto L140
        L151:
            com.vega.aivideoagent.report.VideoAgentReporter r2 = com.vega.aivideoagent.report.VideoAgentReporter.f71921a
            long r0 = java.lang.System.currentTimeMillis()
            r2.getClass()
            com.vega.aivideoagent.report.VideoAgentReporter.p = r0
            goto Ld6
        L15e:
            com.vega.aivideoagent.report.VideoAgentReporter r2 = com.vega.aivideoagent.report.VideoAgentReporter.f71921a
            long r0 = java.lang.System.currentTimeMillis()
            r2.getClass()
            com.vega.aivideoagent.report.VideoAgentReporter.p = r0
            goto Ld6
        L16b:
            com.vega.aivideoagent.report.VideoAgentReporter r2 = com.vega.aivideoagent.report.VideoAgentReporter.f71921a
            long r0 = java.lang.System.currentTimeMillis()
            r2.getClass()
            com.vega.aivideoagent.report.VideoAgentReporter.p = r0
            goto Ld6
        L178:
            com.vega.aivideoagent.report.VideoAgentReporter r2 = com.vega.aivideoagent.report.VideoAgentReporter.f71921a
            long r0 = java.lang.System.currentTimeMillis()
            r2.getClass()
            com.vega.aivideoagent.report.VideoAgentReporter.p = r0
            goto Ld6
        L185:
            com.vega.aivideoagent.report.VideoAgentReporter r2 = com.vega.aivideoagent.report.VideoAgentReporter.f71921a
            long r0 = java.lang.System.currentTimeMillis()
            r2.getClass()
            com.vega.aivideoagent.report.VideoAgentReporter.o = r0
            goto Ld6
        L192:
            com.vega.aivideoagent.api.TaskStepState r0 = com.vega.aivideoagent.api.TaskStepState.b
            goto L1a3
        L195:
            com.vega.aivideoagent.api.TaskStepState r0 = com.vega.aivideoagent.api.TaskStepState.f
            goto L1a3
        L198:
            com.vega.aivideoagent.api.TaskStepState r0 = com.vega.aivideoagent.api.TaskStepState.f71375c
            goto L1a3
        L19b:
            com.vega.aivideoagent.api.TaskStepState r0 = com.vega.aivideoagent.api.TaskStepState.f71376d
            goto L1a3
        L19e:
            com.vega.aivideoagent.api.TaskStepState r0 = com.vega.aivideoagent.api.TaskStepState.e
            goto L1a3
        L1a1:
            com.vega.aivideoagent.api.TaskStepState r0 = com.vega.aivideoagent.api.TaskStepState.f71377g
        L1a3:
            r2.r(r9, r1, r0)
            if (r6 == 0) goto L28
            r0 = 1
            r5.s = r0
            kotlinx.coroutines.flow.MutableStateFlow<com.vega.edit.videoagent.ToolCallStatus> r0 = r8.f96745c
            java.lang.Object r1 = r0.getValue()
            com.vega.edit.videoagent.ToolCallStatus r0 = com.vega.edit.videoagent.ToolCallStatus.f96760a
            if (r1 != r0) goto L1bc
            kotlinx.coroutines.flow.MutableStateFlow<com.vega.edit.videoagent.ToolCallStatus> r1 = r8.f96745c
            com.vega.edit.videoagent.ToolCallStatus r0 = com.vega.edit.videoagent.ToolCallStatus.b
            r1.setValue(r0)
        L1bc:
            kotlinx.coroutines.flow.StateFlow<com.vega.edit.videoagent.ToolCallStatus> r1 = r8.f96746d
            com.vega.edit.videoagent.AgentToolMessageHelper$onMessagePendingTask$2 r0 = new com.vega.edit.videoagent.AgentToolMessageHelper$onMessagePendingTask$2
            r0.<init>(r3)
            java.lang.Object r1 = kotlinx.coroutines.flow.FlowKt__ReduceKt.first(r1, r0, r5)
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r1 != r0) goto L1d0
        L1cd:
            if (r1 != r4) goto L28
            return r4
        L1d0:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            goto L1cd
        L1d3:
            com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$2 r5 = new com.vega.edit.videoagent.AgentToolMessageHelper$sendTaskMessage$2
            r5.<init>(r8, r4)
            goto L18
        L1da:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.edit.videoagent.AgentToolMessageHelper.g(java.lang.String, java.lang.String, com.vega.aivideoagent.model.message.TaskStatus, boolean, com.vega.edit.editpage.activity.EditActivity, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void i(Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(function0, "");
        this.f = function0;
    }
}
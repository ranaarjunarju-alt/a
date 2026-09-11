package com.vega.audio.tone.tts.engine.streaming;

import com.lemon.lv.editor.EditorProxyModule;
import com.lemon.lv.editor.proxy.IAccount;
import com.vega.audio.tone.tts.IStreamingTextToSpeechService;
import com.vega.audio.tone.tts.IStreamingToneManager;
import com.vega.audio.tone.tts.config.TtsStreamingKeepAliveConfig;
import com.vega.audio.tone.tts.config.TtsStreamingKeepAliveConfigSettings;
import com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig;
import com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfigSetting;
import com.vega.audio.tone.tts.data.StreamingToneRequest;
import com.vega.audio.tone.tts.data.StreamingToneResponse;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;

/* loaded from: classes8.dex */
public final class StreamingToneMessageService implements IStreamingTextToSpeechService {
    public Pair<String, String> b;

    /* renamed from: c, reason: collision with root package name */
    public boolean f74485c;

    /* renamed from: g, reason: collision with root package name */
    public Job f74487g;
    public boolean j;
    public int k;

    /* renamed from: a, reason: collision with root package name */
    public final ConcurrentLinkedQueue<Pair<String, String>> f74484a = new ConcurrentLinkedQueue<>();

    /* renamed from: d, reason: collision with root package name */
    public final AtomicBoolean f74486d = new AtomicBoolean(false);
    public final ConcurrentLinkedQueue<StreamingToneResponse> e = new ConcurrentLinkedQueue<>();
    public final Map<String, IStreamingToneManager> f = new LinkedHashMap();
    public final Lazy h = LazyKt__LazyJVMKt.lazy(new Function0<IAccount>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$iAccount$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final IAccount invoke() {
            return ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccount();
        }
    });
    public final Lazy i = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$channelType$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return this.e.j().o();
        }
    });
    public final Lazy l = LazyKt__LazyJVMKt.lazy(new Function0<TtsStreamingKeepAliveConfig>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$connectionKeepAliveConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsStreamingKeepAliveConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsStreamingKeepAliveConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsStreamingKeepAliveConfigSettings.class));
        }
    });
    public final Lazy m = LazyKt__LazyJVMKt.lazy(new Function0<TtsStreamingOptimizedConfig>() { // from class: com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$ttsStreamingOptimizedConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsStreamingOptimizedConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsStreamingOptimizedConfigSetting.class));
        }
    });

    /* loaded from: classes14.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.concurrent.ConcurrentLinkedQueue<com.vega.audio.tone.tts.data.StreamingToneResponse> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void a(String str) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(str, "");
        try {
            objCreateFailure = (StreamingToneResponse) ExtentionKt.getGson().fromJson(str, StreamingToneResponse.class);
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        if (Result.m17096isFailureimpl(objCreateFailure) || objCreateFailure == null) {
            return;
        }
        this.e.offer(objCreateFailure);
        if (this.e.size() == 1) {
            while (!this.e.isEmpty()) {
                StreamingToneResponse streamingToneResponsePoll = this.e.poll();
                if (streamingToneResponsePoll != null) {
                    String taskId = streamingToneResponsePoll.getTaskId();
                    if (taskId == null) {
                        taskId = "";
                    }
                    IStreamingToneManager iStreamingToneManager = (IStreamingToneManager) ((LinkedHashMap) this.f).get(taskId);
                    if (iStreamingToneManager != null) {
                        iStreamingToneManager.a(streamingToneResponsePoll);
                        if (!streamingToneResponsePoll.getHasMore()) {
                            l(taskId);
                        }
                    }
                }
            }
        }
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void b() {
        if (!this.j) {
            this.j = true;
        }
        k();
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void c(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        l(str);
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void d(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        this.f74484a.offer(TuplesKt.to(str, str2));
        if (this.j) {
            k();
        } else {
            e();
        }
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void e() {
        j().n(i());
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final boolean f() {
        return ((TtsStreamingOptimizedConfig) this.m.getValue()).b();
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void g(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        l(str);
        if (this.j) {
            j().k(i(), ExtentionKt.toJson(new StreamingToneRequest(str3, null, null, "cancel", null, str2, null, 86, null)));
        }
    }

    @Override // com.vega.audio.tone.tts.IStreamingTextToSpeechService
    public final void h(String str, IStreamingToneManager iStreamingToneManager) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(iStreamingToneManager, "");
        this.f.put(str, iStreamingToneManager);
    }

    public final String i() {
        return (String) this.i.getValue();
    }

    public final IAccount j() {
        return (IAccount) this.h.getValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:20:0x0052, code lost:
    
        kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(kotlinx.coroutines.Dispatchers.getIO()), null, null, new com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$realSendMessageOptimized$1(r20, null), 3, null);
     */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0283  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x007e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:124:0x012d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0195 A[EDGE_INSN: B:132:0x0195->B:69:0x0195 BREAK  A[LOOP:2: B:61:0x0179->B:135:0x0179], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0263 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x007f A[Catch: all -> 0x029b, TryCatch #1 {all -> 0x029b, blocks: (B:7:0x002b, B:9:0x002f, B:10:0x0032, B:12:0x003c, B:18:0x004e, B:20:0x0052, B:21:0x006c, B:14:0x0040, B:16:0x0044, B:24:0x0076, B:25:0x007a, B:28:0x007f, B:30:0x0097, B:31:0x009a, B:32:0x00ac, B:34:0x00ba, B:36:0x00d2, B:43:0x0115, B:45:0x012d, B:47:0x0137, B:49:0x0145, B:50:0x0148, B:51:0x014d, B:53:0x015b, B:37:0x00e8, B:42:0x00fb), top: B:114:0x002b, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x014d A[Catch: all -> 0x029b, TryCatch #1 {all -> 0x029b, blocks: (B:7:0x002b, B:9:0x002f, B:10:0x0032, B:12:0x003c, B:18:0x004e, B:20:0x0052, B:21:0x006c, B:14:0x0040, B:16:0x0044, B:24:0x0076, B:25:0x007a, B:28:0x007f, B:30:0x0097, B:31:0x009a, B:32:0x00ac, B:34:0x00ba, B:36:0x00d2, B:43:0x0115, B:45:0x012d, B:47:0x0137, B:49:0x0145, B:50:0x0148, B:51:0x014d, B:53:0x015b, B:37:0x00e8, B:42:0x00fb), top: B:114:0x002b, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x024d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void k() {
        /*
            r20 = this;
            r6 = r20
            kotlin.Lazy r0 = r6.m
            java.lang.Object r0 = r0.getValue()
            com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig r0 = (com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig) r0
            boolean r0 = r0.e()
            java.lang.String r14 = "send message failed"
            java.lang.String r12 = "realSendMessage failedMessage="
            java.lang.String r11 = "realSendMessage "
            r10 = 3
            java.lang.String r5 = "realSendMessage end"
            r4 = 0
            java.lang.String r3 = "StreamingToneMessageService"
            r15 = 0
            r9 = 1
            java.lang.String r8 = ""
            java.lang.String r7 = "send message failed "
            if (r0 == 0) goto L16a
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.f74486d
            boolean r0 = r0.compareAndSet(r4, r9)
            if (r0 != 0) goto L2b
        L2a:
            return
        L2b:
            kotlinx.coroutines.Job r0 = r6.f74487g     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L32
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r15, r9, r15)     // Catch: java.lang.Throwable -> L29b
        L32:
            java.util.concurrent.ConcurrentLinkedQueue<kotlin.Pair<java.lang.String, java.lang.String>> r0 = r6.f74484a     // Catch: java.lang.Throwable -> L29b
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L29b
            r0 = r0 ^ 1
            if (r0 != 0) goto L40
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L4e
        L40:
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b     // Catch: java.lang.Throwable -> L29b
            if (r0 != 0) goto L7a
            java.util.concurrent.ConcurrentLinkedQueue<kotlin.Pair<java.lang.String, java.lang.String>> r0 = r6.f74484a     // Catch: java.lang.Throwable -> L29b
            java.lang.Object r0 = r0.poll()     // Catch: java.lang.Throwable -> L29b
            kotlin.Pair r0 = (kotlin.Pair) r0     // Catch: java.lang.Throwable -> L29b
            if (r0 != 0) goto L76
        L4e:
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L6c
            kotlinx.coroutines.CoroutineDispatcher r0 = kotlinx.coroutines.Dispatchers.getIO()     // Catch: java.lang.Throwable -> L29b
            kotlinx.coroutines.CoroutineScope r14 = kotlinx.coroutines.CoroutineScopeKt.CoroutineScope(r0)     // Catch: java.lang.Throwable -> L29b
            com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$realSendMessageOptimized$1 r0 = new com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService$realSendMessageOptimized$1     // Catch: java.lang.Throwable -> L29b
            r0.<init>(r6, r15)     // Catch: java.lang.Throwable -> L29b
            r16 = r15
            r18 = r10
            r19 = r15
            r17 = r0
            kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r14, r15, r16, r17, r18, r19)     // Catch: java.lang.Throwable -> L29b
            goto L160
        L6c:
            java.util.concurrent.ConcurrentLinkedQueue<kotlin.Pair<java.lang.String, java.lang.String>> r0 = r6.f74484a     // Catch: java.lang.Throwable -> L29b
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L2b
            goto L160
        L76:
            r6.b = r0     // Catch: java.lang.Throwable -> L29b
            r6.k = r10     // Catch: java.lang.Throwable -> L29b
        L7a:
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b     // Catch: java.lang.Throwable -> L29b
            if (r0 != 0) goto L7f
            goto L4e
        L7f:
            java.lang.Object r1 = r0.getFirst()     // Catch: java.lang.Throwable -> L29b
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L29b
            java.lang.Object r13 = r0.getSecond()     // Catch: java.lang.Throwable -> L29b
            java.lang.String r13 = (java.lang.String) r13     // Catch: java.lang.Throwable -> L29b
            java.util.Map<java.lang.String, com.vega.audio.tone.tts.IStreamingToneManager> r0 = r6.f     // Catch: java.lang.Throwable -> L29b
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0     // Catch: java.lang.Throwable -> L29b
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> L29b
            com.vega.audio.tone.tts.IStreamingToneManager r0 = (com.vega.audio.tone.tts.IStreamingToneManager) r0     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L9a
            r0.onConnected(r1)     // Catch: java.lang.Throwable -> L29b
        L9a:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L29b
            r0.<init>()     // Catch: java.lang.Throwable -> L29b
            r0.append(r11)     // Catch: java.lang.Throwable -> L29b
            r0.append(r1)     // Catch: java.lang.Throwable -> L29b
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L29b
            com.vega.log.BLog.i(r3, r0)     // Catch: java.lang.Throwable -> L29b
            kotlin.Lazy r0 = r6.m     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig r0 = (com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig) r0     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            boolean r0 = r0.d()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            if (r0 == 0) goto Le8
            com.lemon.lv.editor.proxy.IAccount r2 = r6.j()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.String r0 = r6.i()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            kotlin.Pair r13 = r2.u(r0, r13)     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.Object r0 = r13.getFirst()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            if (r0 != 0) goto Lf8
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            r2.<init>()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            r2.append(r7)     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.Object r0 = r13.getSecond()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            r2.append(r0)     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            goto L115
        Le8:
            com.lemon.lv.editor.proxy.IAccount r2 = r6.j()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            java.lang.String r0 = r6.i()     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            boolean r0 = r2.k(r0, r13)     // Catch: java.lang.Exception -> Lfa java.lang.Throwable -> L29b
            if (r0 != 0) goto Lf8
            r2 = r14
            goto L115
        Lf8:
            r2 = r8
            goto L115
        Lfa:
            r13 = move-exception
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L29b
            r2.<init>()     // Catch: java.lang.Throwable -> L29b
            r2.append(r7)     // Catch: java.lang.Throwable -> L29b
            java.lang.String r0 = r13.getMessage()     // Catch: java.lang.Throwable -> L29b
            r2.append(r0)     // Catch: java.lang.Throwable -> L29b
            java.lang.String r2 = r2.toString()     // Catch: java.lang.Throwable -> L29b
            java.lang.String r0 = r13.getMessage()     // Catch: java.lang.Throwable -> L29b
            com.bytedance.services.apm.api.EnsureManager.ensureNotReachHere(r13, r0)     // Catch: java.lang.Throwable -> L29b
        L115:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L29b
            r0.<init>()     // Catch: java.lang.Throwable -> L29b
            r0.append(r12)     // Catch: java.lang.Throwable -> L29b
            r0.append(r2)     // Catch: java.lang.Throwable -> L29b
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L29b
            com.vega.log.BLog.i(r3, r0)     // Catch: java.lang.Throwable -> L29b
            int r0 = r2.length()     // Catch: java.lang.Throwable -> L29b
            if (r0 <= 0) goto L14d
            r6.j = r4     // Catch: java.lang.Throwable -> L29b
            int r0 = r6.k     // Catch: java.lang.Throwable -> L29b
            int r0 = r0 + (-1)
            r6.k = r0     // Catch: java.lang.Throwable -> L29b
            if (r0 > 0) goto L4e
            r6.b = r15     // Catch: java.lang.Throwable -> L29b
            java.util.Map<java.lang.String, com.vega.audio.tone.tts.IStreamingToneManager> r0 = r6.f     // Catch: java.lang.Throwable -> L29b
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0     // Catch: java.lang.Throwable -> L29b
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> L29b
            com.vega.audio.tone.tts.IStreamingToneManager r0 = (com.vega.audio.tone.tts.IStreamingToneManager) r0     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L148
            r0.b(r1, r2, r4)     // Catch: java.lang.Throwable -> L29b
        L148:
            r6.l(r1)     // Catch: java.lang.Throwable -> L29b
            goto L4e
        L14d:
            r6.b = r15     // Catch: java.lang.Throwable -> L29b
            java.util.Map<java.lang.String, com.vega.audio.tone.tts.IStreamingToneManager> r0 = r6.f     // Catch: java.lang.Throwable -> L29b
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0     // Catch: java.lang.Throwable -> L29b
            java.lang.Object r0 = r0.get(r1)     // Catch: java.lang.Throwable -> L29b
            com.vega.audio.tone.tts.IStreamingToneManager r0 = (com.vega.audio.tone.tts.IStreamingToneManager) r0     // Catch: java.lang.Throwable -> L29b
            if (r0 == 0) goto L32
            r0.b(r1, r8, r9)     // Catch: java.lang.Throwable -> L29b
            goto L32
        L160:
            com.vega.log.BLog.i(r3, r5)
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.f74486d
            r0.set(r4)
            goto L2a
        L16a:
            boolean r0 = r6.f74485c
            if (r0 == 0) goto L170
            goto L2a
        L170:
            r6.f74485c = r9
            kotlinx.coroutines.Job r0 = r6.f74487g
            if (r0 == 0) goto L179
            kotlinx.coroutines.Job.DefaultImpls.cancel$default(r0, r15, r9, r15)
        L179:
            java.util.concurrent.ConcurrentLinkedQueue<kotlin.Pair<java.lang.String, java.lang.String>> r0 = r6.f74484a
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 != 0) goto L187
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b
            if (r0 == 0) goto L195
        L187:
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b
            if (r0 != 0) goto L1b9
            java.util.concurrent.ConcurrentLinkedQueue<kotlin.Pair<java.lang.String, java.lang.String>> r0 = r6.f74484a
            java.lang.Object r0 = r0.poll()
            kotlin.Pair r0 = (kotlin.Pair) r0
            if (r0 != 0) goto L1b5
        L195:
            com.vega.log.BLog.i(r3, r5)
            r6.f74485c = r4
            boolean r0 = r6.j
            if (r0 != 0) goto L2a
            java.util.concurrent.ConcurrentLinkedQueue<kotlin.Pair<java.lang.String, java.lang.String>> r0 = r6.f74484a
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 != 0) goto L1ac
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b
            if (r0 == 0) goto L2a
        L1ac:
            boolean r0 = r6.j
            if (r0 != 0) goto L296
            r6.e()
            goto L2a
        L1b5:
            r6.b = r0
            r6.k = r10
        L1b9:
            kotlin.Pair<java.lang.String, java.lang.String> r0 = r6.b
            if (r0 != 0) goto L1be
            goto L195
        L1be:
            java.lang.Object r2 = r0.getFirst()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r13 = r0.getSecond()
            java.lang.String r13 = (java.lang.String) r13
            java.util.Map<java.lang.String, com.vega.audio.tone.tts.IStreamingToneManager> r0 = r6.f
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r0 = r0.get(r2)
            com.vega.audio.tone.tts.IStreamingToneManager r0 = (com.vega.audio.tone.tts.IStreamingToneManager) r0
            if (r0 == 0) goto L1d9
            r0.onConnected(r2)
        L1d9:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r11)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.vega.log.BLog.i(r3, r0)
            kotlin.Lazy r0 = r6.m     // Catch: java.lang.Exception -> L234
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Exception -> L234
            com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig r0 = (com.vega.audio.tone.tts.config.TtsStreamingOptimizedConfig) r0     // Catch: java.lang.Exception -> L234
            boolean r0 = r0.d()     // Catch: java.lang.Exception -> L234
            if (r0 == 0) goto L224
            com.lemon.lv.editor.proxy.IAccount r1 = r6.j()     // Catch: java.lang.Exception -> L234
            java.lang.String r0 = r6.i()     // Catch: java.lang.Exception -> L234
            kotlin.Pair r13 = r1.u(r0, r13)     // Catch: java.lang.Exception -> L234
            java.lang.Object r0 = r13.getFirst()     // Catch: java.lang.Exception -> L234
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Exception -> L234
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Exception -> L234
            if (r0 != 0) goto L24d
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L234
            r1.<init>()     // Catch: java.lang.Exception -> L234
            r1.append(r7)     // Catch: java.lang.Exception -> L234
            java.lang.Object r0 = r13.getSecond()     // Catch: java.lang.Exception -> L234
            java.lang.String r0 = (java.lang.String) r0     // Catch: java.lang.Exception -> L234
            r1.append(r0)     // Catch: java.lang.Exception -> L234
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Exception -> L234
            goto L24e
        L224:
            com.lemon.lv.editor.proxy.IAccount r1 = r6.j()     // Catch: java.lang.Exception -> L234
            java.lang.String r0 = r6.i()     // Catch: java.lang.Exception -> L234
            boolean r0 = r1.k(r0, r13)     // Catch: java.lang.Exception -> L234
            if (r0 != 0) goto L24d
            r1 = r14
            goto L24e
        L234:
            r13 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r7)
            java.lang.String r0 = r13.getMessage()
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = r13.getMessage()
            com.bytedance.services.apm.api.EnsureManager.ensureNotReachHere(r13, r0)
            goto L24e
        L24d:
            r1 = r8
        L24e:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>(r12)
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.vega.log.BLog.i(r3, r0)
            int r0 = r1.length()
            if (r0 <= 0) goto L283
            r6.j = r4
            int r0 = r6.k
            int r0 = r0 + (-1)
            r6.k = r0
            if (r0 > 0) goto L195
            r6.b = r15
            java.util.Map<java.lang.String, com.vega.audio.tone.tts.IStreamingToneManager> r0 = r6.f
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r0 = r0.get(r2)
            com.vega.audio.tone.tts.IStreamingToneManager r0 = (com.vega.audio.tone.tts.IStreamingToneManager) r0
            if (r0 == 0) goto L27e
            r0.b(r2, r1, r4)
        L27e:
            r6.l(r2)
            goto L195
        L283:
            r6.b = r15
            java.util.Map<java.lang.String, com.vega.audio.tone.tts.IStreamingToneManager> r0 = r6.f
            java.util.LinkedHashMap r0 = (java.util.LinkedHashMap) r0
            java.lang.Object r0 = r0.get(r2)
            com.vega.audio.tone.tts.IStreamingToneManager r0 = (com.vega.audio.tone.tts.IStreamingToneManager) r0
            if (r0 == 0) goto L179
            r0.b(r2, r8, r9)
            goto L179
        L296:
            r6.k()
            goto L2a
        L29b:
            r1 = move-exception
            com.vega.log.BLog.i(r3, r5)
            java.util.concurrent.atomic.AtomicBoolean r0 = r6.f74486d
            r0.set(r4)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.engine.streaming.StreamingToneMessageService.k():void");
    }

    public final void l(String str) {
        Pair<String, String> next;
        this.f.remove(str);
        ConcurrentLinkedQueue<Pair<String, String>> concurrentLinkedQueue = this.f74484a;
        Iterator<Pair<String, String>> it = concurrentLinkedQueue.iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            } else {
                next = it.next();
                if (Intrinsics.areEqual(next.getFirst(), str)) {
                    break;
                }
            }
        }
        concurrentLinkedQueue.remove(next);
        Pair<String, String> pair = this.b;
        if (Intrinsics.areEqual(pair != null ? pair.getFirst() : null, str)) {
            this.b = null;
        }
        if (this.f.isEmpty()) {
            Job job = this.f74487g;
            if (job != null) {
                Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
            }
            this.f74487g = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new StreamingToneMessageService$disconnectWsChannel$1(this, null), 3, null);
        }
    }
}
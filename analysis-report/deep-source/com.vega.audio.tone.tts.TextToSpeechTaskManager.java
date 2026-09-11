package com.vega.audio.tone.tts;

import androidx.core.app.NotificationCompat;
import com.bytedance.bpea.transmit.delegate.BPEAThread;
import com.bytedance.services.apm.api.EnsureManager;
import com.lemon.lv.config.AuditionUsersTextEntranceABConfig;
import com.lemon.lv.config.AuditionUsersTextEntranceABTest;
import com.lemon.lv.config.ToneCloneSupportDeFrConfig;
import com.lemon.lv.config.ToneCloneSupportDeFrConfigABTest;
import com.lemon.lv.config.ToneCommercialOptABTest;
import com.lemon.lv.config.ToneCommercialOptABTestConfig;
import com.service.ResourceReportProxy;
import com.ss.android.ugc.bytex.pthread.base.PThreadExecutorsUtils;
import com.vega.aigcapi.materialgenerate.ReadingListener;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.Operation;
import com.vega.audio.tone.tts.config.TtsMigrationConfig;
import com.vega.audio.tone.tts.config.TtsMigrationConfigSettings;
import com.vega.audio.tone.tts.config.TtsQwenSavingStreamingConfig;
import com.vega.audio.tone.tts.config.TtsQwenSavingStreamingConfigSettings;
import com.vega.audio.tone.tts.config.TtsSavingStreamingConfig;
import com.vega.audio.tone.tts.config.TtsSavingStreamingConfigSettings;
import com.vega.audio.tone.tts.config.TtsStreamingSceneConfig;
import com.vega.audio.tone.tts.config.TtsStreamingSceneConfigSettings;
import com.vega.audio.tone.tts.config.TtsV3ModelToneConfig;
import com.vega.audio.tone.tts.config.TtsV3ModelToneConfigSetting;
import com.vega.audio.tone.tts.core.TextToSpeechExecutorType;
import com.vega.audio.tone.tts.core.TextToSpeechListener;
import com.vega.audio.tone.tts.core.TextToSpeechTask;
import com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler;
import com.vega.audio.tone.tts.core.TextToSpeechTaskType;
import com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.edit.base.tone.EmotionOption;
import com.vega.edit.base.tone.ITextToSpeechTaskManager;
import com.vega.edit.base.tone.TTSBusinessScene;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.libeffectapi.settings.CloneToneAuthenticationConfig;
import com.vega.libeffectapi.settings.CloneToneAuthenticationConfigSetting;
import com.vega.libeffectapi.settings.IEffectSettings;
import com.vega.log.BLog;
import com.vega.materialgenerate.TtsSinkWrapper;
import com.vega.report.ReportManagerWrapper;
import com.vungle.ads.internal.protos.Sdk;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelIterator;
import kotlinx.coroutines.channels.ChannelKt;

/* loaded from: classes22.dex */
public final class TextToSpeechTaskManager implements ITextToSpeechTaskManager {

    /* renamed from: a, reason: collision with root package name */
    public static final TextToSpeechTaskManager f74281a = new TextToSpeechTaskManager();
    public static final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$executeScope$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final CoroutineScope invoke() {
            ExecutorService executorServiceNewSingleThreadExecutor = PThreadExecutorsUtils.newSingleThreadExecutor(new ThreadFactory() { // from class: X.1vm
                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    return new BPEAThread(runnable, "TextToSpeechTaskManager");
                }
            });
            Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "");
            return CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executorServiceNewSingleThreadExecutor).plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
        }
    });

    /* renamed from: c, reason: collision with root package name */
    public static final Channel<Operation> f74282c = ChannelKt.Channel$default(4, null, null, 6, null);

    /* renamed from: d, reason: collision with root package name */
    public static final Lazy f74283d = LazyKt__LazyJVMKt.lazy(new Function0<TextToSpeechTaskScheduler>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$taskScheduler$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final TextToSpeechTaskScheduler invoke() {
            return new TextToSpeechTaskScheduler();
        }
    });
    public static final Lazy e = LazyKt__LazyJVMKt.lazy(new Function0<TtsMigrationConfig>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$ttsMigrationConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsMigrationConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsMigrationConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsMigrationConfigSettings.class));
        }
    });
    public static final Lazy f = LazyKt__LazyJVMKt.lazy(new Function0<TtsStreamingSceneConfig>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$ttsStreamingSceneConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsStreamingSceneConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsStreamingSceneConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsStreamingSceneConfigSettings.class));
        }
    });

    /* renamed from: g, reason: collision with root package name */
    public static final Lazy f74284g = LazyKt__LazyJVMKt.lazy(new Function0<TtsSavingStreamingConfig>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$ttsSavingStreamingConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsSavingStreamingConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsSavingStreamingConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsSavingStreamingConfigSettings.class));
        }
    });
    public static final Lazy h = LazyKt__LazyJVMKt.lazy(new Function0<TtsQwenSavingStreamingConfig>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$ttsQwenSavingStreamingConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsQwenSavingStreamingConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsQwenSavingStreamingConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsQwenSavingStreamingConfigSettings.class));
        }
    });
    public static final Lazy i = LazyKt__LazyJVMKt.lazy(new Function0<TtsV3ModelToneConfig>() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$ttsV3ModelConfig$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.audio.tone.tts.config.TtsV3ModelToneConfig, com.vega.config.IConfig] */
        @Override // kotlin.jvm.functions.Function0
        public final TtsV3ModelToneConfig invoke() {
            return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TtsV3ModelToneConfigSetting.class));
        }
    });
    public static final AtomicBoolean j = new AtomicBoolean(false);
    public static String k = "";

    @DebugMetadata(c = "com.vega.audio.tone.tts.TextToSpeechTaskManager$1", f = "TextToSpeechTaskManager.kt", i = {}, l = {124, Sdk.SDKError.Reason.ASSET_FAILED_MAX_SPACE_EXCEEDED_VALUE, NotificationCompat.FLAG_HIGH_PRIORITY, 129, 130, 132, 133, 134, 138}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.audio.tone.tts.TextToSpeechTaskManager$1, reason: invalid class name */
    /* loaded from: classes20.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public ChannelIterator q;
        public Object r;
        public int s;

        public AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(continuation);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new AnonymousClass1(continuation).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:13:0x0039 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0047  */
        /* JADX WARN: Removed duplicated region for block: B:64:0x016e A[Catch: all -> 0x018c, TryCatch #2 {all -> 0x018c, blocks: (B:62:0x0168, B:64:0x016e), top: B:92:0x0168 }] */
        /* JADX WARN: Removed duplicated region for block: B:87:0x01f9  */
        /* JADX WARN: Removed duplicated region for block: B:95:0x002c A[SYNTHETIC] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0067 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:26:0x0085 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x00a3 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:36:0x00c1 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:40:0x00c9 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x00f2 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:49:0x010d -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:54:0x0129 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:58:0x0131 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:61:0x0160 -> B:92:0x0168). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:71:0x0196 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x01c0 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:74:0x01c2 -> B:11:0x002c). Please report as a decompilation issue!!! */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:78:0x01d3 -> B:11:0x002c). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r8.s
                r2 = 0
                switch(r0) {
                    case 0: goto L23;
                    case 1: goto L3a;
                    case 2: goto L1d;
                    case 3: goto L1d;
                    case 4: goto L1d;
                    case 5: goto L1d;
                    case 6: goto L1d;
                    case 7: goto L1d;
                    case 8: goto L1d;
                    case 9: goto L12;
                    default: goto La;
                }
            La:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r0)
                throw r1
            L12:
                java.lang.Object r6 = r8.r
                java.util.Iterator r6 = (java.util.Iterator) r6
                kotlinx.coroutines.channels.ChannelIterator r4 = r8.q
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1d2
                goto L168
            L1d:
                kotlinx.coroutines.channels.ChannelIterator r4 = r8.q
                kotlin.ResultKt.throwOnFailure(r9)     // Catch: java.lang.Throwable -> L1d2
                goto L2c
            L23:
                kotlin.ResultKt.throwOnFailure(r9)
                kotlinx.coroutines.channels.Channel<com.vega.audio.tone.tts.Operation> r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74282c
                kotlinx.coroutines.channels.ChannelIterator r4 = r0.iterator()
            L2c:
                r8.q = r4
                r8.r = r2
                r0 = 1
                r8.s = r0
                java.lang.Object r9 = r4.hasNext(r8)
                if (r9 != r3) goto L3f
                return r3
            L3a:
                kotlinx.coroutines.channels.ChannelIterator r4 = r8.q
                kotlin.ResultKt.throwOnFailure(r9)
            L3f:
                java.lang.Boolean r9 = (java.lang.Boolean) r9
                boolean r0 = r9.booleanValue()
                if (r0 == 0) goto L1f9
                java.lang.Object r7 = r4.next()
                com.vega.audio.tone.tts.Operation r7 = (com.vega.audio.tone.tts.Operation) r7
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.Init     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L6b
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$Init r7 = (com.vega.audio.tone.tts.Operation.Init) r7     // Catch: java.lang.Throwable -> L1d0
                java.lang.String r1 = r7.f74270a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 2
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = r5.g(r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f1
            L6b:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartReading     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L89
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$StartReading r7 = (com.vega.audio.tone.tts.Operation.StartReading) r7     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r7.f74271a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 3
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = r5.f(r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f2
            L89:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartReadingStreaming     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto La7
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$StartReadingStreaming r7 = (com.vega.audio.tone.tts.Operation.StartReadingStreaming) r7     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r7.f74273a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 4
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = r5.e(r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f3
            La7:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartReadingNonStreaming     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto Lc5
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$StartReadingNonStreaming r7 = (com.vega.audio.tone.tts.Operation.StartReadingNonStreaming) r7     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r7.f74272a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 5
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = r5.c(r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f4
            Lc5:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StopReading     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto Ldb
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r1 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$StopReading r7 = (com.vega.audio.tone.tts.Operation.StopReading) r7     // Catch: java.lang.Throwable -> L1d0
                boolean r0 = r7.f74279a     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler.a(r1, r0)     // Catch: java.lang.Throwable -> L1d0
                goto L2c
            Ldb:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartSaving     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto Lf6
                com.vega.audio.tone.tts.TextToSpeechTaskManager r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0 = r7
                com.vega.audio.tone.tts.Operation$StartSaving r0 = (com.vega.audio.tone.tts.Operation.StartSaving) r0     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r0.f74274a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 6
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                r5.getClass()     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.l(r7, r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f5
            Lf6:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartSavingStreaming     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L111
                com.vega.audio.tone.tts.TextToSpeechTaskManager r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0 = r7
                com.vega.audio.tone.tts.Operation$StartSavingStreaming r0 = (com.vega.audio.tone.tts.Operation.StartSavingStreaming) r0     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r0.f74277a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 7
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                r5.getClass()     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.l(r7, r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f6
            L111:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartSavingNonStreaming     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L12d
                com.vega.audio.tone.tts.TextToSpeechTaskManager r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0 = r7
                com.vega.audio.tone.tts.Operation$StartSavingNonStreaming r0 = (com.vega.audio.tone.tts.Operation.StartSavingNonStreaming) r0     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTask r1 = r0.f74275a     // Catch: java.lang.Throwable -> L1d0
                r8.q = r4     // Catch: java.lang.Throwable -> L1d0
                r0 = 8
                r8.s = r0     // Catch: java.lang.Throwable -> L1d0
                r5.getClass()     // Catch: java.lang.Throwable -> L1d0
                java.lang.Object r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.l(r7, r1, r8)     // Catch: java.lang.Throwable -> L1d0
                if (r0 != r3) goto L2c
                goto L1f7
            L12d:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StopSaving     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L15c
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r6 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$StopSaving r7 = (com.vega.audio.tone.tts.Operation.StopSaving) r7     // Catch: java.lang.Throwable -> L1d0
                java.lang.String r5 = r7.f74280a     // Catch: java.lang.Throwable -> L1d0
                java.lang.String r0 = ""
                com.vega.audio.tone.tts.Operation$Init r1 = new com.vega.audio.tone.tts.Operation$Init     // Catch: java.lang.Throwable -> L1d0
                r1.<init>(r2)     // Catch: java.lang.Throwable -> L1d0
                r6.getClass()     // Catch: java.lang.Throwable -> L1d0
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.queue.TextToSpeechSerialQueue r0 = r6.f74302c     // Catch: java.lang.Throwable -> L1d0
                r0.a(r1)     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.queue.TextToSpeechParallelQueue r0 = r6.f74303d     // Catch: java.lang.Throwable -> L1d0
                r0.b(r5, r1)     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.queue.TextToSpeechMigrationQueue r0 = r6.e     // Catch: java.lang.Throwable -> L1d0
                r0.a(r1)     // Catch: java.lang.Throwable -> L1d0
                goto L2c
            L15c:
                boolean r0 = r7 instanceof com.vega.audio.tone.tts.Operation.StartSavingParallel     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L18e
                com.vega.audio.tone.tts.Operation$StartSavingParallel r7 = (com.vega.audio.tone.tts.Operation.StartSavingParallel) r7     // Catch: java.lang.Throwable -> L1d0
                java.util.List<com.vega.audio.tone.tts.core.TextToSpeechTask> r0 = r7.f74276a     // Catch: java.lang.Throwable -> L1d0
                java.util.Iterator r6 = r0.iterator()     // Catch: java.lang.Throwable -> L1d0
            L168:
                boolean r0 = r6.hasNext()     // Catch: java.lang.Throwable -> L18c
                if (r0 == 0) goto L2c
                java.lang.Object r5 = r6.next()     // Catch: java.lang.Throwable -> L18c
                com.vega.audio.tone.tts.core.TextToSpeechTask r5 = (com.vega.audio.tone.tts.core.TextToSpeechTask) r5     // Catch: java.lang.Throwable -> L18c
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L18c
                r0.getClass()     // Catch: java.lang.Throwable -> L18c
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r1 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L18c
                r8.q = r4     // Catch: java.lang.Throwable -> L18c
                r8.r = r6     // Catch: java.lang.Throwable -> L18c
                r0 = 9
                r8.s = r0     // Catch: java.lang.Throwable -> L18c
                java.lang.Object r0 = r1.d(r5, r8)     // Catch: java.lang.Throwable -> L18c
                if (r0 != r3) goto L168
                goto L1f8
            L18c:
                r5 = move-exception
                goto L1d3
            L18e:
                com.vega.audio.tone.tts.Operation$StopAll r0 = com.vega.audio.tone.tts.Operation.StopAll.f74278a     // Catch: java.lang.Throwable -> L1d0
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L1ba
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.queue.TextToSpeechSerialQueue r1 = r5.f74302c     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$Init r0 = new com.vega.audio.tone.tts.Operation$Init     // Catch: java.lang.Throwable -> L1d0
                r0.<init>(r2)     // Catch: java.lang.Throwable -> L1d0
                r1.a(r0)     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.queue.TextToSpeechMigrationQueue r1 = r5.e     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.Operation$Init r0 = new com.vega.audio.tone.tts.Operation$Init     // Catch: java.lang.Throwable -> L1d0
                r0.<init>(r2)     // Catch: java.lang.Throwable -> L1d0
                r1.a(r0)     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.queue.TextToSpeechParallelQueue r0 = r5.f74303d     // Catch: java.lang.Throwable -> L1d0
                r0.a()     // Catch: java.lang.Throwable -> L1d0
                goto L2c
            L1ba:
                com.vega.audio.tone.tts.Operation$Destroy r0 = com.vega.audio.tone.tts.Operation.Destroy.f74269a     // Catch: java.lang.Throwable -> L1d0
                boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r7, r0)     // Catch: java.lang.Throwable -> L1d0
                if (r0 == 0) goto L2c
                com.vega.audio.tone.tts.TextToSpeechTaskManager r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a     // Catch: java.lang.Throwable -> L1d0
                r0.getClass()     // Catch: java.lang.Throwable -> L1d0
                com.vega.audio.tone.tts.core.TextToSpeechTaskScheduler r0 = com.vega.audio.tone.tts.TextToSpeechTaskManager.k()     // Catch: java.lang.Throwable -> L1d0
                r0.b()     // Catch: java.lang.Throwable -> L1d0
                goto L2c
            L1d0:
                r5 = move-exception
                goto L1d3
            L1d2:
                r5 = move-exception
            L1d3:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r0 = "TextToSpeechTaskManager:  process error: "
                r1.<init>(r0)
                java.lang.String r0 = r5.getMessage()
                r1.append(r0)
                java.lang.String r1 = r1.toString()
                java.lang.String r0 = "TextToSpeech_TextToSpeechTaskManager"
                com.vega.log.BLog.e(r0, r1)
                java.lang.String r0 = "TextToSpeechTaskManager process error"
                com.bytedance.services.apm.api.EnsureManager.ensureNotReachHere(r5, r0)
                goto L2c
            L1f1:
                return r3
            L1f2:
                return r3
            L1f3:
                return r3
            L1f4:
                return r3
            L1f5:
                return r3
            L1f6:
                return r3
            L1f7:
                return r3
            L1f8:
                return r3
            L1f9:
                kotlin.Unit r0 = kotlin.Unit.INSTANCE
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.TextToSpeechTaskManager.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    /* loaded from: classes15.dex */
    public /* synthetic */ class WhenMappings {
        static {
            TextToSpeechExecutorType.values();
        }
    }

    @DebugMetadata(c = "com.vega.audio.tone.tts.TextToSpeechTaskManager$destroy$1", f = "TextToSpeechTaskManager.kt", i = {}, l = {339}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.audio.tone.tts.TextToSpeechTaskManager$destroy$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes35.dex */
    public static final class C152271 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int q;

        public C152271(Continuation<? super C152271> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C152271(continuation);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return new C152271(continuation).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.q;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Channel<Operation> channel = TextToSpeechTaskManager.f74282c;
                Operation.Destroy destroy = Operation.Destroy.f74269a;
                this.q = 1;
                if (channel.send(destroy, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    @DebugMetadata(c = "com.vega.audio.tone.tts.TextToSpeechTaskManager$init$1", f = "TextToSpeechTaskManager.kt", i = {}, l = {176}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.audio.tone.tts.TextToSpeechTaskManager$init$1, reason: invalid class name and case insensitive filesystem */
    /* loaded from: classes33.dex */
    public static final class C152281 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int q;
        public final /* synthetic */ String r;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public C152281(String str, Continuation<? super C152281> continuation) {
            super(2, continuation);
            this.r = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C152281(this.r, continuation);
        }

        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((BaseContinuationImpl) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.q;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Channel<Operation> channel = TextToSpeechTaskManager.f74282c;
                Operation.Init init = new Operation.Init(this.r);
                this.q = 1;
                if (channel.send(init, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX DEBUG: Class process forced to load method for inline: kotlinx.coroutines.channels.ChannelKt.Channel$default(int, kotlinx.coroutines.channels.BufferOverflow, kotlin.jvm.functions.Function1, int, java.lang.Object):kotlinx.coroutines.channels.Channel */
    static {
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new AnonymousClass1(null), 3, null);
    }

    public static final <T> void g(List<Integer> list, List<T> list2) {
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            if (iIntValue >= 0 && iIntValue < list2.size()) {
                list2.remove(iIntValue);
            }
        }
    }

    public static AuditionTextType h(TextToSpeechIntent textToSpeechIntent) {
        Intrinsics.checkNotNullParameter(textToSpeechIntent, "");
        BLog.i("TextToSpeech_TextToSpeechTaskManager", "AuditionUsersTextEntranceABTest group:" + ((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).getGroup());
        return textToSpeechIntent.E ? textToSpeechIntent.q ? AuditionTextType.f74267a : AuditionTextType.b : ((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).isAuditionDefaultText() ? AuditionTextType.f74267a : AuditionTextType.b;
    }

    public static CoroutineScope i() {
        return (CoroutineScope) b.getValue();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static TextToSpeechExecutorType j(boolean z, TextToSpeechIntent textToSpeechIntent) {
        boolean z2 = (textToSpeechIntent.f == TTSBusinessScene.b || textToSpeechIntent.D) ? false : true;
        Boolean bool = textToSpeechIntent.B;
        Boolean bool2 = Boolean.TRUE;
        if (Intrinsics.areEqual(bool, bool2) && ((!((ToneCloneSupportDeFrConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneCloneSupportDeFrConfigABTest.class))).a() || !Intrinsics.areEqual(textToSpeechIntent.B, bool2) || textToSpeechIntent.f88867d.length() <= 0 || Intrinsics.areEqual(textToSpeechIntent.f88867d, "sami")) && ((CloneToneAuthenticationConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(CloneToneAuthenticationConfigSetting.class))).a() && z2)) {
            return TextToSpeechExecutorType.f74292c;
        }
        String str = textToSpeechIntent.f88867d;
        switch (str.hashCode()) {
            case -94228242:
                if (str.equals("microsoft")) {
                    return TextToSpeechExecutorType.b;
                }
                break;
            case 3483983:
                if (str.equals("qwen")) {
                    return TextToSpeechExecutorType.f74294g;
                }
                break;
            case 3522666:
                if (str.equals("sami")) {
                    RemoteSAMIToneUtil.f74436a.getClass();
                    return (((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() && z) ? TextToSpeechExecutorType.f74292c : (((AuditionUsersTextEntranceABConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(AuditionUsersTextEntranceABTest.class))).hitExperiment() && z) ? h(textToSpeechIntent) == AuditionTextType.f74267a ? TextToSpeechExecutorType.f74292c : TextToSpeechExecutorType.f74291a : TextToSpeechExecutorType.f74291a;
                }
                break;
            case 3539967:
                if (str.equals("ssml")) {
                    return TextToSpeechExecutorType.e;
                }
                break;
            case 104090236:
                if (str.equals("moyin")) {
                    return TextToSpeechExecutorType.f;
                }
                break;
            case 1451394726:
                if (str.equals("11labs")) {
                    return TextToSpeechExecutorType.f74293d;
                }
                break;
        }
        RemoteSAMIToneUtil.f74436a.getClass();
        return (((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).v() && z) ? TextToSpeechExecutorType.f74292c : TextToSpeechExecutorType.f74291a;
    }

    public static TextToSpeechTaskScheduler k() {
        return (TextToSpeechTaskScheduler) f74283d.getValue();
    }

    public static Object l(Operation operation, TextToSpeechTask textToSpeechTask, Continuation continuation) {
        Object objC;
        String strTakeIfNotEmpty = ExtentionKt.takeIfNotEmpty(textToSpeechTask.m);
        if (strTakeIfNotEmpty == null && (strTakeIfNotEmpty = ExtentionKt.takeIfNotEmpty(textToSpeechTask.f74298c)) == null) {
            strTakeIfNotEmpty = "none";
        }
        SPIService sPIService = SPIService.INSTANCE;
        ResourceReportProxy resourceReportProxy = (ResourceReportProxy) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ResourceReportProxy.class), null);
        ((ResourceReportProxy) sPIService.getImpl(Reflection.getOrCreateKotlinClass(ResourceReportProxy.class), null)).a();
        resourceReportProxy.b("text_reading", strTakeIfNotEmpty, null, null);
        if (operation instanceof Operation.StartSaving) {
            Object objF = k().f(textToSpeechTask, continuation);
            return objF == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objF : Unit.INSTANCE;
        }
        if (!(operation instanceof Operation.StartSavingStreaming)) {
            return ((operation instanceof Operation.StartSavingNonStreaming) && (objC = k().c(textToSpeechTask, continuation)) == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()) ? objC : Unit.INSTANCE;
        }
        Object objE = k().e(textToSpeechTask, continuation);
        return objE == IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objE : Unit.INSTANCE;
    }

    public static void m(String str, TextToSpeechIntent textToSpeechIntent, boolean z, TtsResult ttsResult, Integer num, Integer num2) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", str);
        map.put("enter_from", textToSpeechIntent.e);
        map.put("business_type", textToSpeechIntent.f88868g.f88859a);
        map.put("edit_type", textToSpeechIntent.h);
        map.put("ad_type", textToSpeechIntent.i);
        map.put("voice_type_id", textToSpeechIntent.f88866c);
        map.put("rate", String.valueOf(textToSpeechIntent.k));
        map.put("text_to_audio_type", textToSpeechIntent.f88867d);
        map.put("intent_type", z ? "saving" : "reading");
        if (ttsResult != null) {
            map.put("generate_time", String.valueOf(ttsResult.e));
            int i2 = ttsResult.b;
            map.put("error_code", i2 == 20000000 ? "0" : String.valueOf(i2));
            map.put("sub_error_code", ttsResult.f69164c);
            map.put("error_msg", ttsResult.f69165d);
        }
        map.put("text_length", String.valueOf(textToSpeechIntent.b.getText().length()));
        if (z) {
            map.put("text_to_audio_cnt", String.valueOf(num));
            map.put("text_to_audio_fail_cnt", String.valueOf(num2));
        }
        Map<String, Object> map2 = textToSpeechIntent.m;
        if (map2 != null) {
            map.putAll(map2);
        }
        ReportManagerWrapper.INSTANCE.onEvent("tech_text_to_speech", map);
    }

    public static /* synthetic */ void n(TextToSpeechTaskManager textToSpeechTaskManager, String str, TextToSpeechIntent textToSpeechIntent, boolean z, TtsResult ttsResult, int i2) {
        TtsResult ttsResult2 = ttsResult;
        if ((i2 & 8) != 0) {
            ttsResult2 = null;
        }
        textToSpeechTaskManager.getClass();
        m(str, textToSpeechIntent, z, ttsResult2, null, null);
    }

    /* JADX DEBUG: Class process forced to load method for inline: com.vega.audio.tone.util.TextToSpeechReportInfo.copy$default(com.vega.audio.tone.util.TextToSpeechReportInfo, com.vega.aigcapi.materialgenerate.TextToSpeechReportScene, com.vega.aigcapi.materialgenerate.TextToSpeechReportScene, int, boolean, boolean, long, java.lang.String, com.vega.audio.tone.util.TextToSpeechReportSceneError, java.lang.String, java.lang.String, int, java.lang.Object):com.vega.audio.tone.util.TextToSpeechReportInfo */
    /* JADX WARN: Removed duplicated region for block: B:71:0x012b A[PHI: r32
      0x012b: PHI (r32v1 boolean) = (r32v0 boolean), (r32v3 boolean) binds: [B:69:0x0128, B:64:0x00fd] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0153  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void o(java.lang.String r40, com.vega.edit.base.tone.TextToSpeechIntent r41, boolean r42, com.vega.aigcapi.materialgenerate.TtsResult r43) {
        /*
            r8 = r40
            r4 = r42
            if (r4 != 0) goto Lf
            java.lang.String r0 = "cancel"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r0)
            if (r0 == 0) goto Lf
            return
        Lf:
            r0 = r4 ^ 1
            r5 = r41
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r7 = j(r0, r5)
            int r0 = r7.ordinal()
            java.lang.String r1 = "11labs"
            switch(r0) {
                case 0: goto L26;
                case 1: goto L29;
                case 2: goto L2c;
                case 3: goto L2f;
                case 4: goto L32;
                case 5: goto L35;
                case 6: goto L38;
                default: goto L20;
            }
        L20:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        L26:
            java.lang.String r20 = "sami"
            goto L3a
        L29:
            java.lang.String r20 = "microsoft"
            goto L3a
        L2c:
            java.lang.String r20 = "remote_sami"
            goto L3a
        L2f:
            r20 = r1
            goto L3a
        L32:
            java.lang.String r20 = "ssml"
            goto L3a
        L35:
            java.lang.String r20 = "moyin"
            goto L3a
        L38:
            java.lang.String r20 = "qwen"
        L3a:
            r6 = r43
            if (r6 == 0) goto L42
            com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r3 = r6.f69166g
            if (r3 != 0) goto L44
        L42:
            com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r3 = com.vega.aigcapi.materialgenerate.TtsResult.RequestScene.b
        L44:
            com.vega.audio.tone.util.TextToSpeechReportInfo$Companion r2 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion
            java.lang.String r0 = r5.o
            r2.getClass()
            com.vega.audio.tone.util.TextToSpeechReportInfo r30 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion.a(r0)
            com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil r0 = com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil.f74436a
            r0.getClass()
            kotlin.Pair r0 = com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil.a(r4, r7, r5)
            java.lang.Object r2 = r0.getFirst()
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r0 = r0.getSecond()
            java.lang.Number r0 = (java.lang.Number) r0
            int r16 = r0.intValue()
            java.lang.String r0 = r5.f88867d
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            java.lang.String r29 = ""
            r12 = 0
            if (r0 == 0) goto L153
            java.lang.String r0 = "success"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r0)
            if (r0 == 0) goto L141
            if (r6 == 0) goto L13e
            java.lang.String r1 = r6.m
        L7f:
            java.lang.String r0 = r5.f88866c
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 != 0) goto L153
            boolean r0 = com.vega.core.ext.ExtentionKt.isNotNullOrEmpty(r1)
            if (r0 == 0) goto L153
        L8d:
            com.vega.audio.tone.util.TextToSpeechReporter r15 = com.vega.audio.tone.util.TextToSpeechReporter.f74499a
            if (r30 == 0) goto L13b
            r33 = 0
            com.vega.audio.tone.util.TextToSpeechReportSceneError$RemoteError r0 = new com.vega.audio.tone.util.TextToSpeechReportSceneError$RemoteError
            if (r6 == 0) goto L138
            int r9 = r6.b
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)
        L9d:
            java.lang.String r11 = java.lang.String.valueOf(r9)
            if (r6 == 0) goto L135
            java.lang.String r9 = r6.f69165d
        La5:
            java.lang.String r10 = java.lang.String.valueOf(r9)
            if (r6 == 0) goto L132
            java.lang.String r9 = r6.f69164c
        Lad:
            java.lang.String r9 = java.lang.String.valueOf(r9)
            r0.<init>(r11, r10, r9)
            r36 = 0
            r42 = 831(0x33f, float:1.164E-42)
            r31 = r12
            r32 = r12
            r34 = r33
            r35 = r33
            r38 = r8
            r39 = r0
            r40 = r12
            r41 = r12
            r43 = r12
            com.vega.audio.tone.util.TextToSpeechReportInfo r17 = com.vega.audio.tone.util.TextToSpeechReportInfo.copy$default(r30, r31, r32, r33, r34, r35, r36, r38, r39, r40, r41, r42, r43)
        Lce:
            java.lang.String r14 = r5.f88866c
            java.lang.String r13 = r3.f69169a
            if (r6 == 0) goto L130
            java.lang.String r9 = r6.l
        Ld6:
            java.lang.String r8 = r5.r
            java.lang.String r3 = r5.s
            com.vega.edit.base.tone.EmotionOption r0 = r5.v
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r10 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.e
            if (r7 != r10) goto L12e
            java.util.Map<java.lang.String, java.lang.String> r7 = r5.I
        Le2:
            if (r6 == 0) goto Le6
            java.lang.String r12 = r6.h
        Le6:
            java.lang.Boolean r10 = r5.B
            if (r10 == 0) goto Lf2
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto Lf2
            java.lang.String r29 = "clone_tone"
        Lf2:
            r10 = 1
            if (r6 == 0) goto L126
            boolean r11 = r6.j
            if (r11 != r10) goto L126
            r32 = 1
        Lfb:
            boolean r6 = r6.k
            if (r6 != r10) goto L12b
            r33 = 1
        L101:
            boolean r5 = r5.K
            java.lang.Integer r19 = java.lang.Integer.valueOf(r16)
            r15.getClass()
            r26 = r8
            r27 = r3
            r28 = r12
            r30 = r7
            r31 = r4
            r34 = r5
            r21 = r13
            r22 = r14
            r23 = r2
            r24 = r9
            r25 = r1
            r18 = r0
            com.vega.audio.tone.util.TextToSpeechReporter.d(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34)
            return
        L126:
            r32 = 0
            if (r6 == 0) goto L12b
            goto Lfb
        L12b:
            r33 = 0
            goto L101
        L12e:
            r7 = r12
            goto Le2
        L130:
            r9 = r12
            goto Ld6
        L132:
            r9 = r12
            goto Lad
        L135:
            r9 = r12
            goto La5
        L138:
            r9 = r12
            goto L9d
        L13b:
            r17 = r12
            goto Lce
        L13e:
            r1 = r12
            goto L7f
        L141:
            if (r6 == 0) goto L14b
            java.lang.String r1 = r6.m
            if (r1 != 0) goto L7f
            java.lang.String r0 = r6.f69164c
            if (r0 != 0) goto L14d
        L14b:
            r0 = r29
        L14d:
            java.lang.String r1 = com.vega.audio.tone.tts.util.TextToSpeechUtilsKt.a(r0)
            goto L7f
        L153:
            r1 = r12
            goto L8d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.TextToSpeechTaskManager.o(java.lang.String, com.vega.edit.base.tone.TextToSpeechIntent, boolean, com.vega.aigcapi.materialgenerate.TtsResult):void");
    }

    public static boolean p(TextToSpeechTask textToSpeechTask) {
        switch (textToSpeechTask.e.ordinal()) {
            case 0:
                return ((TtsMigrationConfig) e.getValue()).d();
            case 1:
                return ((TtsMigrationConfig) e.getValue()).b();
            case 2:
                return ((TtsMigrationConfig) e.getValue()).c();
            case 3:
                return ((TtsMigrationConfig) e.getValue()).a();
            case 4:
                return ((TtsMigrationConfig) e.getValue()).e();
            case 5:
            case 6:
                return true;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Type inference failed for: r0v32, types: [com.vega.audio.tone.tts.TextToSpeechTaskManager$wrapToTask$1] */
    public static TextToSpeechTask q(final TextToSpeechIntent textToSpeechIntent, TextToSpeechTaskType textToSpeechTaskType) {
        final boolean z = textToSpeechTaskType == TextToSpeechTaskType.b;
        TextToSpeechExecutorType textToSpeechExecutorTypeJ = j(textToSpeechTaskType == TextToSpeechTaskType.f74307a, textToSpeechIntent);
        RemoteSAMIToneUtil.f74436a.getClass();
        Pair pairA = RemoteSAMIToneUtil.a(z, textToSpeechExecutorTypeJ, textToSpeechIntent);
        String str = (String) pairA.getFirst();
        int iIntValue = ((Number) pairA.getSecond()).intValue();
        BLog.i("TextToSpeech_TextToSpeechTaskManager", "wrapToTask -> " + textToSpeechIntent.E + "isSaving=" + z + ", executorType=" + textToSpeechExecutorTypeJ + ",useCache=" + textToSpeechIntent.F + ", text=" + str);
        String str2 = textToSpeechIntent.f88865a;
        try {
            Charset charset = Charsets.UTF_8;
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkNotNullExpressionValue(bytes, "");
            str = new String(bytes, charset);
        } catch (UnsupportedEncodingException e2) {
            TtsSinkWrapper.f115002a.getClass();
            TtsSinkWrapper.a("invalid_text", -7, str, "", z);
            EnsureManager.ensureNotReachHere(new RuntimeException("strEncode invalid text"), "strEncode invalid text, text = " + str);
            BLog.e("TextToSpeech_TextToSpeechTaskManager", "strEncode throw e, string = " + str, e2);
        }
        String str3 = textToSpeechIntent.f88866c;
        String str4 = textToSpeechIntent.u;
        float f2 = textToSpeechIntent.j;
        int i2 = textToSpeechIntent.k;
        String str5 = textToSpeechIntent.w;
        boolean zB = textToSpeechIntent.b.b();
        String str6 = textToSpeechIntent.o;
        EmotionOption emotionOption = textToSpeechIntent.v;
        String str7 = textToSpeechIntent.s;
        if (str7 == null) {
            str7 = "";
        }
        Boolean bool = textToSpeechIntent.B;
        Boolean bool2 = Boolean.TRUE;
        return new TextToSpeechTask(str2, str, str3, textToSpeechTaskType, textToSpeechExecutorTypeJ, iIntValue, f2, i2, zB, str6, new TextToSpeechListener() { // from class: com.vega.audio.tone.tts.TextToSpeechTaskManager$wrapToTask$1

            /* loaded from: classes15.dex */
            public /* synthetic */ class WhenMappings {
                static {
                    TextToSpeechExecutorType.values();
                }
            }

            @Override // com.vega.audio.tone.tts.core.TextToSpeechListener
            public final void a() {
                ReadingListener readingListener = textToSpeechIntent.l;
                if (readingListener != null) {
                    readingListener.a();
                }
            }

            @Override // com.vega.audio.tone.tts.core.TextToSpeechListener
            public final void b() {
                ReadingListener readingListener = textToSpeechIntent.l;
                if (readingListener != null) {
                    readingListener.b();
                }
            }

            @Override // com.vega.audio.tone.tts.core.TextToSpeechListener
            public final void c(TtsResult.RequestScene requestScene) {
                Intrinsics.checkNotNullParameter(requestScene, "");
                TextToSpeechTaskManager.n(TextToSpeechTaskManager.f74281a, "cancel", textToSpeechIntent, z, null, 56);
                TextToSpeechTaskManager.o("cancel", textToSpeechIntent, z, new TtsResult(StatusResult.e, 0, null, null, 0L, false, requestScene, null, null, false, false, null, null, 8126));
                Function0<Unit> function0 = textToSpeechIntent.f88864J;
                if (function0 != null) {
                    function0.invoke();
                }
            }

            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            /* JADX WARN: Removed duplicated region for block: B:157:0x0446  */
            /* JADX WARN: Removed duplicated region for block: B:196:0x053c  */
            /* JADX WARN: Removed duplicated region for block: B:82:0x0224  */
            /* JADX WARN: Removed duplicated region for block: B:85:0x025b  */
            @Override // com.vega.audio.tone.tts.core.TextToSpeechListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public final void d(com.vega.audio.tone.tts.core.TextToSpeechExecutorType r30, com.vega.aigcapi.materialgenerate.EventType r31, com.vega.aigcapi.materialgenerate.TtsResult r32, com.lemon.lv.data.TextToAudioInfo r33) {
                /*
                    r29 = this;
                    r1 = r33
                    java.lang.String r4 = ""
                    r5 = r30
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r8 = r31
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r4)
                    java.util.Objects.toString(r5)
                    r2 = r32
                    java.util.Objects.toString(r2)
                    java.util.Objects.toString(r8)
                    java.util.Objects.toString(r1)
                    if (r2 != 0) goto L4a
                    com.vega.aigcapi.materialgenerate.TtsResult r0 = new com.vega.aigcapi.materialgenerate.TtsResult
                    com.vega.aigcapi.materialgenerate.StatusResult r10 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    r11 = 0
                    r12 = 0
                    r14 = 0
                    r24 = 8190(0x1ffe, float:1.1477E-41)
                    r13 = r12
                    r16 = r11
                    r17 = r12
                    r18 = r12
                    r19 = r12
                    r20 = r11
                    r21 = r11
                    r22 = r12
                    r23 = r12
                    r9 = r0
                    r9.<init>(r10, r11, r12, r13, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24)
                L3d:
                    int r3 = r5.ordinal()
                    switch(r3) {
                        case 0: goto L5e;
                        case 1: goto L5b;
                        case 2: goto L58;
                        case 3: goto L55;
                        case 4: goto L52;
                        case 5: goto L4f;
                        case 6: goto L4c;
                        default: goto L44;
                    }
                L44:
                    kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
                    r0.<init>()
                    throw r0
                L4a:
                    r0 = r2
                    goto L3d
                L4c:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.f69160g
                    goto L60
                L4f:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.e
                    goto L60
                L52:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.f
                    goto L60
                L55:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.f69159d
                    goto L60
                L58:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.f69158c
                    goto L60
                L5b:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.b
                    goto L60
                L5e:
                    com.vega.aigcapi.materialgenerate.TextToAudioType r7 = com.vega.aigcapi.materialgenerate.TextToAudioType.f69157a
                L60:
                    r3 = r29
                    com.vega.edit.base.tone.TextToSpeechIntent r6 = r1
                    com.vega.aigcapi.materialgenerate.ReadingListener r6 = r6.l
                    if (r6 == 0) goto L6b
                    r6.c(r7, r8, r2, r1)
                L6b:
                    com.vega.aigcapi.materialgenerate.EventType r9 = com.vega.aigcapi.materialgenerate.EventType.b
                    java.lang.String r16 = ", msg: "
                    java.lang.String r17 = ", subError: "
                    java.lang.String r18 = ", errorCode: "
                    java.lang.String r19 = "onMessageReceived error status: "
                    java.lang.String r7 = "fail"
                    java.lang.String r6 = "TextToSpeech_TextToSpeechTaskManager"
                    if (r8 != r9) goto L2b1
                    if (r1 == 0) goto L1d3
                    java.util.List<java.lang.String> r5 = r1.b
                    if (r5 == 0) goto L1d3
                    int r14 = r5.size()
                L85:
                    boolean r5 = r2
                    if (r5 == 0) goto L686
                    if (r1 == 0) goto L1d6
                    com.vega.audio.tone.tts.TextToSpeechTaskManager r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
                    com.vega.edit.base.tone.TextToSpeechIntent r10 = r3
                    r5.getClass()
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder
                    java.lang.String r5 = "checkFilePathsValid ignoreInvalidPath:"
                    r8.<init>(r5)
                    java.lang.Boolean r5 = r10.x
                    r8.append(r5)
                    java.lang.String r5 = ",correctionTtsInfo:"
                    r8.append(r5)
                    boolean r5 = r10.y
                    r8.append(r5)
                    java.lang.String r5 = r8.toString()
                    com.vega.log.BLog.i(r6, r5)
                    com.vega.edit.base.tone.TextInfo r5 = r10.b
                    boolean r5 = r5.b()
                    if (r5 != 0) goto L138
                    com.vega.edit.base.tone.TextInfo r5 = r10.b
                    int r8 = r5.a()
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    int r5 = r5.size()
                    if (r8 == r5) goto L138
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    r5 = 1300(0x514, float:1.822E-42)
                    r0.b = r5
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder
                    java.lang.String r5 = "checkFilePathsValid: textCount = "
                    r8.<init>(r5)
                    com.vega.edit.base.tone.TextInfo r5 = r10.b
                    int r5 = r5.a()
                    r8.append(r5)
                    java.lang.String r5 = ", filePaths.size = "
                    r8.append(r5)
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    int r5 = r5.size()
                    r8.append(r5)
                    java.lang.String r5 = " invalid"
                    r8.append(r5)
                    java.lang.String r5 = r8.toString()
                    com.vega.log.BLog.e(r6, r5)
                Lfa:
                    com.vega.aigcapi.materialgenerate.StatusResult r8 = r0.f69163a
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    if (r8 != r5) goto L1e1
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    java.util.ArrayList r8 = new java.util.ArrayList
                    r8.<init>()
                    java.util.Iterator r11 = r5.iterator()
                L10b:
                    boolean r5 = r11.hasNext()
                    if (r5 == 0) goto L1e3
                    java.lang.Object r10 = r11.next()
                    r9 = r10
                    java.lang.String r9 = (java.lang.String) r9
                    com.vega.materialgenerate.TtsSinkWrapper r5 = com.vega.materialgenerate.TtsSinkWrapper.f115002a
                    r5.getClass()
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r4)
                    com.vega.ve.utils.VEUtils r5 = com.vega.ve.utils.VEUtils.f135496a     // Catch: java.lang.Throwable -> L10b
                    r5.getClass()     // Catch: java.lang.Throwable -> L10b
                    r5 = 0
                    com.ss.android.vesdk.VEUtils$VEAVFileInfo r9 = com.vega.ve.utils.VEUtils.c(r9, r5)     // Catch: java.lang.Throwable -> L10b
                    if (r9 == 0) goto L10b
                    int r5 = r9.numAudioStreams     // Catch: java.lang.Throwable -> L10b
                    if (r5 <= 0) goto L10b
                    int r5 = r9.duration     // Catch: java.lang.Throwable -> L10b
                    if (r5 <= 0) goto L10b
                    r8.add(r10)
                    goto L10b
                L138:
                    java.util.ArrayList r9 = new java.util.ArrayList
                    r9.<init>()
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    int r13 = r5.size()
                    r12 = 0
                L144:
                    if (r12 >= r13) goto L18e
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    java.lang.Object r11 = r5.get(r12)
                    java.lang.String r11 = (java.lang.String) r11
                    com.bytedance.security.android.aopcheck.PolarisFileWrapper r5 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
                    r5.<init>(r11)
                    boolean r5 = r5.exists()
                    if (r5 != 0) goto L16a
                    java.lang.Boolean r8 = r10.x
                    java.lang.Boolean r5 = java.lang.Boolean.TRUE
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r5)
                    if (r5 == 0) goto L16d
                    java.lang.Integer r5 = java.lang.Integer.valueOf(r12)
                    r9.add(r5)
                L16a:
                    int r12 = r12 + 1
                    goto L144
                L16d:
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    r5 = 1400(0x578, float:1.962E-42)
                    r0.b = r5
                    java.lang.StringBuilder r8 = new java.lang.StringBuilder
                    java.lang.String r5 = "checkFilePathsValid: path = "
                    r8.<init>(r5)
                    r8.append(r11)
                    java.lang.String r5 = " not exits"
                    r8.append(r5)
                    java.lang.String r5 = r8.toString()
                    com.vega.log.BLog.e(r6, r5)
                L18e:
                    boolean r8 = r10.y
                    r5 = 1
                    if (r8 != r5) goto Lfa
                    java.util.List r8 = kotlin.collections.CollectionsKt___CollectionsKt.sortedDescending(r9)
                    boolean r5 = r9.isEmpty()
                    r5 = r5 ^ 1
                    if (r5 == 0) goto Lfa
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    int r9 = r5.size()
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.g(r8, r5)
                    java.util.List<java.lang.String> r5 = r1.b
                    int r5 = r5.size()
                    if (r9 != r5) goto L1b7
                    java.util.List<java.lang.String> r5 = r1.b
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.g(r8, r5)
                L1b7:
                    java.util.List<java.lang.String> r5 = r1.f59068c
                    int r5 = r5.size()
                    if (r9 != r5) goto L1c4
                    java.util.List<java.lang.String> r5 = r1.f59068c
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.g(r8, r5)
                L1c4:
                    java.util.List<java.lang.String> r5 = r1.f59069d
                    int r5 = r5.size()
                    if (r9 != r5) goto Lfa
                    java.util.List<java.lang.String> r5 = r1.f59069d
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.g(r8, r5)
                    goto Lfa
                L1d3:
                    r14 = 0
                    goto L85
                L1d6:
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    r4 = 1200(0x4b0, float:1.682E-42)
                    r0.b = r4
                L1e1:
                    r9 = 0
                    goto L21e
                L1e3:
                    int r9 = r8.size()
                    java.util.List<java.lang.String> r5 = r1.f59067a
                    int r5 = r5.size()
                    if (r9 >= r5) goto L1e1
                    boolean r5 = r8.isEmpty()
                    java.lang.String r9 = "-6003"
                    if (r5 == 0) goto L274
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    r4 = 1500(0x5dc, float:2.102E-42)
                    r0.b = r4
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r5 = r0.f69166g
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r4 = com.vega.aigcapi.materialgenerate.TtsResult.RequestScene.f69168d
                    if (r5 != r4) goto L213
                    r4 = -402444(0xfffffffffff9dbf4, float:NaN)
                    r0.b = r4
                    r0.f69164c = r9
                    java.lang.String r4 = "no valid file path"
                    r0.f69165d = r4
                L213:
                    java.util.List<java.lang.String> r4 = r1.f59067a
                    int r9 = r4.size()
                    int r4 = r8.size()
                    int r9 = r9 - r4
                L21e:
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = r0.f69163a
                    com.vega.aigcapi.materialgenerate.StatusResult r4 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    if (r5 == r4) goto L255
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r4 = r19
                    r5.<init>(r4)
                    com.vega.aigcapi.materialgenerate.StatusResult r4 = r0.f69163a
                    r5.append(r4)
                    r4 = r18
                    r5.append(r4)
                    int r4 = r0.b
                    r5.append(r4)
                    r4 = r17
                    r5.append(r4)
                    java.lang.String r4 = r0.f69164c
                    r5.append(r4)
                    r4 = r16
                    r5.append(r4)
                    java.lang.String r4 = r0.f69165d
                    r5.append(r4)
                    java.lang.String r4 = r5.toString()
                    com.vega.log.BLog.e(r6, r4)
                L255:
                    com.vega.edit.base.tone.TextToSpeechIntent r4 = r1
                    kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r4 = r4.t
                    if (r4 == 0) goto L6ab
                    if (r1 != 0) goto L26f
                    com.lemon.lv.data.TextToAudioInfo r1 = new com.lemon.lv.data.TextToAudioInfo
                    r16 = 0
                    r21 = 127(0x7f, float:1.78E-43)
                    r15 = r1
                    r17 = r16
                    r18 = r16
                    r19 = r16
                    r20 = r16
                    r15.<init>(r16, r17, r18, r19, r20, r21)
                L26f:
                    r4.invoke(r0, r1)
                    goto L6ab
                L274:
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.f69155d
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    r4 = 1600(0x640, float:2.242E-42)
                    r0.b = r4
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r5 = r0.f69166g
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r4 = com.vega.aigcapi.materialgenerate.TtsResult.RequestScene.f69168d
                    if (r5 != r4) goto L213
                    r4 = -402444(0xfffffffffff9dbf4, float:NaN)
                    r0.b = r4
                    r0.f69164c = r9
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    java.lang.String r4 = "file count not match "
                    r5.<init>(r4)
                    int r4 = r8.size()
                    r5.append(r4)
                    java.lang.String r4 = " != "
                    r5.append(r4)
                    java.util.List<java.lang.String> r4 = r1.f59067a
                    int r4 = r4.size()
                    r5.append(r4)
                    java.lang.String r4 = r5.toString()
                    r0.b(r4)
                    goto L213
                L2b1:
                    com.vega.aigcapi.materialgenerate.EventType r9 = com.vega.aigcapi.materialgenerate.EventType.f69148c
                    if (r8 != r9) goto L593
                    if (r2 == 0) goto L2fd
                    com.vega.audio.tone.tts.TextToSpeechTaskManager r5 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
                    r5.getClass()
                    int r8 = r2.b
                    r9 = -49999999(0xfffffffffd050f81, float:-1.1054239E37)
                    if (r8 == r9) goto L2c8
                    r5 = -402442(0xfffffffffff9dbf6, float:NaN)
                    if (r8 != r5) goto L2ed
                L2c8:
                    int r0 = r2.b
                    if (r0 != r9) goto L2d1
                    r0 = -402444(0xfffffffffff9dbf4, float:NaN)
                    r2.b = r0
                L2d1:
                    com.vega.edit.base.tone.TextToSpeechIntent r1 = r1
                    boolean r0 = r2
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.o(r7, r1, r0, r2)
                    boolean r1 = r2
                    com.vega.edit.base.tone.TextToSpeechIntent r0 = r1
                    kotlinx.coroutines.CoroutineScope r2 = com.vega.audio.tone.tts.TextToSpeechTaskManager.i()
                    r3 = 0
                    com.vega.audio.tone.tts.TextToSpeechTaskManager$retryTTSWithNonStreaming$1 r5 = new com.vega.audio.tone.tts.TextToSpeechTaskManager$retryTTSWithNonStreaming$1
                    r5.<init>(r1, r0, r3)
                    r6 = 3
                    r4 = r3
                    r7 = r3
                    kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r2, r3, r4, r5, r6, r7)
                    return
                L2ed:
                    r5 = -402443(0xfffffffffff9dbf5, float:NaN)
                    if (r8 != r5) goto L2fd
                    java.lang.String r8 = r2.f69164c
                    java.lang.String r5 = "231062"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r5)
                    if (r5 == 0) goto L2fd
                    goto L2c8
                L2fd:
                    com.vega.edit.base.tone.TextToSpeechIntent r8 = r3
                    boolean r5 = r8.n
                    if (r5 == 0) goto L375
                    if (r2 == 0) goto L375
                    com.vega.edit.base.tone.TextToSpeechIntent r5 = r1
                    int r10 = r2.b
                    r9 = 3101(0xc1d, float:4.345E-42)
                    if (r10 == r9) goto L30f
                    if (r10 != r9) goto L454
                L30f:
                    java.lang.String r9 = r2.f69164c
                    java.lang.String r14 = "4411"
                    boolean r15 = r14.equals(r9)
                    java.lang.String r13 = "4501"
                    java.lang.String r12 = "4416"
                    java.lang.String r11 = "4415"
                    java.lang.String r10 = "4401"
                    java.lang.String r9 = "4412"
                    if (r15 != 0) goto L34b
                    java.lang.String r15 = r2.f69164c
                    boolean r15 = r9.equals(r15)
                    if (r15 != 0) goto L34b
                    java.lang.String r15 = r2.f69164c
                    boolean r15 = r11.equals(r15)
                    if (r15 != 0) goto L34b
                    java.lang.String r15 = r2.f69164c
                    boolean r15 = r12.equals(r15)
                    if (r15 != 0) goto L34b
                    java.lang.String r15 = r2.f69164c
                    boolean r15 = r10.equals(r15)
                    if (r15 != 0) goto L34b
                    java.lang.String r15 = r2.f69164c
                    boolean r15 = r13.equals(r15)
                    if (r15 == 0) goto L454
                L34b:
                    java.lang.String r15 = r2.f69164c
                    int r8 = r15.hashCode()
                    switch(r8) {
                        case 1600641: goto L43e;
                        case 1600672: goto L428;
                        case 1600673: goto L412;
                        case 1600676: goto L3fc;
                        case 1600677: goto L3e6;
                        case 1601602: goto L3de;
                        default: goto L354;
                    }
                L354:
                    java.util.LinkedHashMap r9 = new java.util.LinkedHashMap
                    r9.<init>()
                    java.lang.String r10 = r5.i
                    java.lang.String r8 = "ad_type"
                    r9.put(r8, r10)
                    java.lang.String r8 = r5.h
                    java.lang.String r5 = "edit_type"
                    r9.put(r5, r8)
                    java.lang.String r8 = r2.f69164c
                    java.lang.String r5 = "error_code"
                    r9.put(r5, r8)
                    com.vega.report.ReportManagerWrapper r8 = com.vega.report.ReportManagerWrapper.INSTANCE
                    java.lang.String r5 = "tone_use_limit_toast"
                    r8.onEvent(r5, r9)
                L375:
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.f69154c
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    java.lang.StringBuilder r5 = new java.lang.StringBuilder
                    r4 = r19
                    r5.<init>(r4)
                    com.vega.aigcapi.materialgenerate.StatusResult r4 = r0.f69163a
                    r5.append(r4)
                    r4 = r18
                    r5.append(r4)
                    int r4 = r0.b
                    r5.append(r4)
                    r4 = r17
                    r5.append(r4)
                    java.lang.String r4 = r0.f69164c
                    r5.append(r4)
                    r4 = r16
                    r5.append(r4)
                    java.lang.String r4 = r0.f69165d
                    r5.append(r4)
                    java.lang.String r4 = r5.toString()
                    com.vega.log.BLog.e(r6, r4)
                    com.vega.edit.base.tone.TextToSpeechIntent r4 = r1
                    kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r4 = r4.t
                    if (r4 == 0) goto L3c5
                    if (r1 != 0) goto L3c2
                    com.lemon.lv.data.TextToAudioInfo r1 = new com.lemon.lv.data.TextToAudioInfo
                    r9 = 0
                    r14 = 127(0x7f, float:1.78E-43)
                    r8 = r1
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    r13 = r9
                    r8.<init>(r9, r10, r11, r12, r13, r14)
                L3c2:
                    r4.invoke(r0, r1)
                L3c5:
                    com.vega.audio.tone.tts.TextToSpeechTaskManager r8 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
                    java.lang.String r9 = "fail"
                    com.vega.edit.base.tone.TextToSpeechIntent r1 = r1
                    boolean r0 = r2
                    r13 = 48
                    r10 = r1
                    r11 = r0
                    r12 = r2
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.n(r8, r9, r10, r11, r12, r13)
                    com.vega.edit.base.tone.TextToSpeechIntent r1 = r1
                    boolean r0 = r2
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.o(r7, r1, r0, r2)
                    goto L6d4
                L3de:
                    boolean r8 = r15.equals(r13)
                    if (r8 != 0) goto L446
                    goto L354
                L3e6:
                    boolean r8 = r15.equals(r12)
                    if (r8 != 0) goto L3ee
                    goto L354
                L3ee:
                    r8 = 2131887561(0x7f1205c9, float:1.9409733E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L354
                L3fc:
                    boolean r8 = r15.equals(r11)
                    if (r8 != 0) goto L404
                    goto L354
                L404:
                    r8 = 2131887563(0x7f1205cb, float:1.9409737E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L354
                L412:
                    boolean r8 = r15.equals(r9)
                    if (r8 != 0) goto L41a
                    goto L354
                L41a:
                    r8 = 2131887562(0x7f1205ca, float:1.9409735E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L354
                L428:
                    boolean r8 = r15.equals(r14)
                    if (r8 != 0) goto L430
                    goto L354
                L430:
                    r8 = 2131899022(0x7f12328e, float:1.9432978E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L354
                L43e:
                    boolean r8 = r15.equals(r10)
                    if (r8 != 0) goto L446
                    goto L354
                L446:
                    r8 = 2131899023(0x7f12328f, float:1.943298E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L354
                L454:
                    java.lang.String r9 = r2.f69164c
                    java.lang.String r5 = "3008"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r5)
                    if (r5 != 0) goto L468
                    java.lang.String r9 = r2.f69164c
                    java.lang.String r5 = "40402002"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r5)
                    if (r5 == 0) goto L499
                L468:
                    com.vega.core.context.SPIService r9 = com.vega.core.context.SPIService.INSTANCE
                    java.lang.Class<com.vega.libeffectapi.settings.IEffectSettings> r5 = com.vega.libeffectapi.settings.IEffectSettings.class
                    kotlin.reflect.KClass r8 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
                    r5 = 0
                    java.lang.Object r5 = r9.getImpl(r8, r5)
                    com.vega.libeffectapi.settings.IEffectSettings r5 = (com.vega.libeffectapi.settings.IEffectSettings) r5
                    boolean r5 = r5.t()
                    if (r5 == 0) goto L48b
                    r8 = 2131887234(0x7f120482, float:1.940907E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L375
                L48b:
                    r8 = 2131899178(0x7f12332a, float:1.9433295E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L375
                L499:
                    java.lang.String r9 = r2.f69164c
                    java.lang.String r5 = "23099"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r9, r5)
                    if (r5 == 0) goto L4c3
                    com.vega.audio.tone.tts.util.TextToSpeechTaskUtils r5 = com.vega.audio.tone.tts.util.TextToSpeechTaskUtils.f74492a
                    r5.getClass()
                    java.lang.String r20 = com.vega.core.ext.ExtentionKt.takeIfNotEmpty(r4)
                    if (r20 == 0) goto L375
                    r21 = 0
                    r26 = 0
                    r28 = 510(0x1fe, float:7.15E-43)
                    r22 = r21
                    r23 = r21
                    r24 = r21
                    r25 = r21
                    r27 = r21
                    com.vega.util.ToastUtilKt.e(r20, r21, r22, r23, r24, r25, r26, r27, r28)
                    goto L375
                L4c3:
                    java.lang.String r5 = r2.f69164c
                    java.lang.String r10 = "231063"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r5, r10)
                    if (r5 != 0) goto L53c
                    java.lang.String r13 = r2.f69164c
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r4)
                    kotlin.text.Regex r12 = new kotlin.text.Regex
                    java.lang.String r5 = "(task_code|code):(\\d+)"
                    r12.<init>(r5)
                    r9 = 2
                    r11 = 0
                    r5 = 0
                    kotlin.sequences.Sequence r5 = kotlin.text.Regex.findAll$default(r12, r13, r5, r9, r11)
                    java.util.Iterator r15 = r5.iterator()
                    r11 = 0
                L4e5:
                    r14 = 0
                L4e6:
                    boolean r5 = r15.hasNext()
                    if (r5 == 0) goto L533
                    java.lang.Object r12 = r15.next()
                    kotlin.text.MatchResult r12 = (kotlin.text.MatchResult) r12
                    kotlin.text.MatchGroupCollection r13 = r12.getGroups()
                    r5 = 1
                    kotlin.text.MatchGroup r5 = r13.get(r5)
                    if (r5 == 0) goto L531
                    java.lang.String r13 = r5.getValue()
                L501:
                    java.lang.String r5 = "task_code"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r5)
                    if (r5 == 0) goto L51a
                    kotlin.text.MatchGroupCollection r5 = r12.getGroups()
                    kotlin.text.MatchGroup r5 = r5.get(r9)
                    if (r5 == 0) goto L518
                    java.lang.String r11 = r5.getValue()
                    goto L4e6
                L518:
                    r11 = 0
                    goto L4e6
                L51a:
                    java.lang.String r5 = "code"
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r5)
                    if (r5 == 0) goto L4e6
                    kotlin.text.MatchGroupCollection r5 = r12.getGroups()
                    kotlin.text.MatchGroup r5 = r5.get(r9)
                    if (r5 == 0) goto L4e5
                    java.lang.String r14 = r5.getValue()
                    goto L4e6
                L531:
                    r13 = 0
                    goto L501
                L533:
                    if (r11 != 0) goto L536
                    r11 = r14
                L536:
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r11, r10)
                    if (r5 == 0) goto L54a
                L53c:
                    r8 = 2131896977(0x7f122a91, float:1.942883E38)
                    r9 = 0
                    r13 = 254(0xfe, float:3.56E-43)
                    r10 = r9
                    r11 = r9
                    r12 = r9
                    com.vega.util.ToastUtilKt.d(r8, r9, r10, r11, r12, r13)
                    goto L375
                L54a:
                    java.lang.Boolean r8 = r8.A
                    java.lang.Boolean r5 = java.lang.Boolean.TRUE
                    boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r5)
                    if (r5 == 0) goto L575
                    com.vega.audio.tone.tts.util.TextToSpeechTaskUtils r5 = com.vega.audio.tone.tts.util.TextToSpeechTaskUtils.f74492a
                    r5.getClass()
                    r5 = 2131895841(0x7f122621, float:1.9426526E38)
                    java.lang.String r20 = com.vega.core.utils.FunctionsKt.b(r5)
                    r21 = 0
                    r26 = 0
                    r28 = 510(0x1fe, float:7.15E-43)
                    r22 = r21
                    r23 = r21
                    r24 = r21
                    r25 = r21
                    r27 = r21
                    com.vega.util.ToastUtilKt.e(r20, r21, r22, r23, r24, r25, r26, r27, r28)
                    goto L375
                L575:
                    com.vega.report.NetworkTipsHelper r5 = com.vega.report.NetworkTipsHelper.f129896a
                    r5.getClass()
                    java.lang.String r20 = com.vega.report.NetworkTipsHelper.a()
                    r21 = 0
                    r26 = 0
                    r28 = 510(0x1fe, float:7.15E-43)
                    r22 = r21
                    r23 = r21
                    r24 = r21
                    r25 = r21
                    r27 = r21
                    com.vega.util.ToastUtilKt.e(r20, r21, r22, r23, r24, r25, r26, r27, r28)
                    goto L375
                L593:
                    com.vega.aigcapi.materialgenerate.EventType r2 = com.vega.aigcapi.materialgenerate.EventType.f69149d
                    if (r8 != r2) goto L6d4
                    com.vega.audio.tone.tts.TextToSpeechTaskManager r6 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
                    com.vega.edit.base.tone.TextToSpeechIntent r2 = r1
                    boolean r14 = r2
                    r6.getClass()
                    r6 = 1
                    com.vega.audio.tone.tts.core.TextToSpeechExecutorType r9 = com.vega.audio.tone.tts.TextToSpeechTaskManager.j(r6, r2)
                    int r6 = r9.ordinal()
                    switch(r6) {
                        case 0: goto L5c4;
                        case 1: goto L5c1;
                        case 2: goto L5be;
                        case 3: goto L5bb;
                        case 4: goto L5b8;
                        case 5: goto L5b5;
                        case 6: goto L5b2;
                        default: goto L5ac;
                    }
                L5ac:
                    kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
                    r0.<init>()
                    throw r0
                L5b2:
                    java.lang.String r20 = "qwen"
                    goto L5c6
                L5b5:
                    java.lang.String r20 = "moyin"
                    goto L5c6
                L5b8:
                    java.lang.String r20 = "ssml"
                    goto L5c6
                L5bb:
                    java.lang.String r20 = "11labs"
                    goto L5c6
                L5be:
                    java.lang.String r20 = "remote_sami"
                    goto L5c6
                L5c1:
                    java.lang.String r20 = "microsoft"
                    goto L5c6
                L5c4:
                    java.lang.String r20 = "sami"
                L5c6:
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r7 = r0.f69166g
                    if (r7 != 0) goto L5cc
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r7 = com.vega.aigcapi.materialgenerate.TtsResult.RequestScene.b
                L5cc:
                    com.vega.audio.tone.util.TextToSpeechReportInfo$Companion r8 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion
                    java.lang.String r6 = r2.o
                    r8.getClass()
                    com.vega.audio.tone.util.TextToSpeechReportInfo r17 = com.vega.audio.tone.util.TextToSpeechReportInfo.Companion.a(r6)
                    com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil r6 = com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil.f74436a
                    r6.getClass()
                    r6 = 0
                    kotlin.Pair r6 = com.vega.audio.tone.tts.engine.server.RemoteSAMIToneUtil.a(r6, r9, r2)
                    java.lang.Object r11 = r6.getFirst()
                    java.lang.String r11 = (java.lang.String) r11
                    java.lang.Object r6 = r6.getSecond()
                    java.lang.Number r6 = (java.lang.Number) r6
                    int r16 = r6.intValue()
                    com.vega.audio.tone.util.TextToSpeechReporter r15 = com.vega.audio.tone.util.TextToSpeechReporter.f74499a
                    java.lang.String r13 = r2.f88866c
                    java.lang.String r10 = r7.f69169a
                    java.lang.String r9 = r2.s
                    com.vega.edit.base.tone.EmotionOption r8 = r2.v
                    java.lang.String r7 = r0.h
                    java.lang.Boolean r2 = r2.B
                    if (r2 == 0) goto L683
                    boolean r2 = r2.booleanValue()
                    if (r2 == 0) goto L683
                    java.lang.String r26 = "clone_tone"
                L609:
                    java.util.HashMap r6 = new java.util.HashMap
                    r6.<init>()
                    java.util.Map<java.lang.String, java.lang.Long> r2 = r0.i
                    if (r2 == 0) goto L615
                    r6.putAll(r2)
                L615:
                    boolean r12 = r0.j
                    r2 = 1
                    if (r12 != r2) goto L681
                    r2 = 1
                L61b:
                    java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
                    java.lang.String r2 = "hit_server_cache"
                    r6.put(r2, r12)
                    boolean r12 = r0.k
                    r2 = 1
                    if (r12 != r2) goto L67f
                    r2 = 1
                L62a:
                    java.lang.Integer r12 = java.lang.Integer.valueOf(r2)
                    java.lang.String r2 = "hit_local_cache"
                    r6.put(r2, r12)
                    java.lang.Integer r19 = java.lang.Integer.valueOf(r16)
                    r15.getClass()
                    r18 = r8
                    r21 = r10
                    r22 = r13
                    r23 = r11
                    r24 = r9
                    r25 = r7
                    r27 = r6
                    r28 = r14
                    com.vega.audio.tone.util.TextToSpeechReporter.e(r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28)
                    boolean r2 = r2
                    if (r2 != 0) goto L6d4
                    com.vega.audio.tone.tts.core.TextToSpeechExecutorType r2 = com.vega.audio.tone.tts.core.TextToSpeechExecutorType.b
                    if (r5 == r2) goto L65f
                    boolean r2 = r0.f
                    if (r2 != 0) goto L65f
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r5 = r0.f69166g
                    com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r2 = com.vega.aigcapi.materialgenerate.TtsResult.RequestScene.b
                    if (r5 == r2) goto L6d4
                L65f:
                    com.vega.aigcapi.materialgenerate.StatusResult r2 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r4)
                    r0.f69163a = r2
                    com.vega.edit.base.tone.TextToSpeechIntent r2 = r1
                    kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r2 = r2.t
                    if (r2 == 0) goto L6d4
                    if (r1 != 0) goto L67b
                    com.lemon.lv.data.TextToAudioInfo r1 = new com.lemon.lv.data.TextToAudioInfo
                    r4 = 0
                    r9 = 127(0x7f, float:1.78E-43)
                    r3 = r1
                    r5 = r4
                    r6 = r4
                    r7 = r4
                    r8 = r4
                    r3.<init>(r4, r5, r6, r7, r8, r9)
                L67b:
                    r2.invoke(r0, r1)
                    goto L6d4
                L67f:
                    r2 = 0
                    goto L62a
                L681:
                    r2 = 0
                    goto L61b
                L683:
                    r26 = r4
                    goto L609
                L686:
                    com.vega.aigcapi.materialgenerate.StatusResult r5 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r4)
                    r0.f69163a = r5
                    com.vega.edit.base.tone.TextToSpeechIntent r4 = r1
                    kotlin.jvm.functions.Function2<com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo, kotlin.Unit> r4 = r4.t
                    if (r4 == 0) goto L6aa
                    if (r1 != 0) goto L6a7
                    com.lemon.lv.data.TextToAudioInfo r1 = new com.lemon.lv.data.TextToAudioInfo
                    r16 = 0
                    r21 = 127(0x7f, float:1.78E-43)
                    r15 = r1
                    r17 = r16
                    r18 = r16
                    r19 = r16
                    r20 = r16
                    r15.<init>(r16, r17, r18, r19, r20, r21)
                L6a7:
                    r4.invoke(r0, r1)
                L6aa:
                    r9 = 0
                L6ab:
                    if (r2 == 0) goto L6d5
                    com.vega.aigcapi.materialgenerate.StatusResult r1 = r2.f69163a
                L6af:
                    com.vega.aigcapi.materialgenerate.StatusResult r0 = com.vega.aigcapi.materialgenerate.StatusResult.b
                    if (r1 != r0) goto L6b5
                    java.lang.String r7 = "success"
                L6b5:
                    com.vega.audio.tone.tts.TextToSpeechTaskManager r4 = com.vega.audio.tone.tts.TextToSpeechTaskManager.f74281a
                    com.vega.edit.base.tone.TextToSpeechIntent r1 = r1
                    boolean r0 = r2
                    java.lang.Integer r11 = java.lang.Integer.valueOf(r14)
                    java.lang.Integer r12 = java.lang.Integer.valueOf(r9)
                    r4.getClass()
                    r7 = r7
                    r8 = r1
                    r9 = r0
                    r10 = r2
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.m(r7, r8, r9, r10, r11, r12)
                    com.vega.edit.base.tone.TextToSpeechIntent r1 = r1
                    boolean r0 = r2
                    com.vega.audio.tone.tts.TextToSpeechTaskManager.o(r7, r1, r0, r2)
                L6d4:
                    return
                L6d5:
                    r1 = 0
                    goto L6af
                */
                throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.TextToSpeechTaskManager$wrapToTask$1.d(com.vega.audio.tone.tts.core.TextToSpeechExecutorType, com.vega.aigcapi.materialgenerate.EventType, com.vega.aigcapi.materialgenerate.TtsResult, com.lemon.lv.data.TextToAudioInfo):void");
            }
        }, str4, str7, Intrinsics.areEqual(bool, bool2), textToSpeechIntent.C, emotionOption, Intrinsics.areEqual(textToSpeechIntent.B, bool2) ? textToSpeechIntent.s : "", Intrinsics.areEqual(textToSpeechIntent.z, bool2) ? "voice_clone_trial" : "voice_clone", textToSpeechIntent.F || ((ToneCommercialOptABTestConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ToneCommercialOptABTest.class))).enableAuditionOpt(), textToSpeechIntent.G, textToSpeechIntent.H, str5);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void a(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new TextToSpeechTaskManager$stopSavingAudio$1(str, null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void b(boolean z) {
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new TextToSpeechTaskManager$stopReading$1(z, null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void c(TextToSpeechIntent textToSpeechIntent) {
        Intrinsics.checkNotNullParameter(textToSpeechIntent, "");
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new TextToSpeechTaskManager$startSavingAudio$1(textToSpeechIntent, null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void d(List<TextToSpeechIntent> list) {
        Intrinsics.checkNotNullParameter(list, "");
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new TextToSpeechTaskManager$startSavingAudioParallel$1(list, null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void destroy() {
        if (j.get()) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new C152271(null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new TextToSpeechTaskManager$stopAll$1(null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void f(TextToSpeechIntent textToSpeechIntent) {
        Intrinsics.checkNotNullParameter(textToSpeechIntent, "");
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new TextToSpeechTaskManager$startReading$1(textToSpeechIntent, null), 3, null);
    }

    @Override // com.vega.edit.base.tone.ITextToSpeechTaskManager
    public final void init(String str) {
        BuildersKt__Builders_commonKt.launch$default(i(), null, null, new C152281(str, null), 3, null);
    }
}
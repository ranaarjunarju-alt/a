package com.vega.audio.tone;

import com.bytedance.bpea.transmit.delegate.BPEAThread;
import com.bytedance.common.profilesdk.ProfileManager;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lv.editor.EditorProxyModule;
import com.lemon.lv.editor.proxy.IAccount;
import com.mammon.audiosdk.enums.SAMICoreCallBackEventType;
import com.service.audio.ITextToAudio;
import com.ss.android.ugc.bytex.pthread.base.PThreadExecutorsUtils;
import com.vega.aigcapi.materialgenerate.StatusResult;
import com.vega.aigcapi.materialgenerate.TextToSpeechReportScene;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.util.TextToSpeechReportInfo;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.edit.base.audio.tone.DealStatus;
import com.vega.edit.base.audio.tone.TextToAudioInfoPack;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import com.vega.edit.base.utils.FeelGoodReportHelper;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.report.ReportManagerWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* loaded from: classes8.dex */
public final class TextToAudioManager {

    /* renamed from: c, reason: collision with root package name */
    public static int f73897c;
    public static final TextToAudioInfoPack e;
    public static final CompletableDeferred<Boolean> j;
    public static final Lazy k;
    public static final Lazy l;
    public static final Lazy m;
    public static ITextToAudio.ReadingListener n;

    /* renamed from: a, reason: collision with root package name */
    public static final TextToAudioManager f73896a = new TextToAudioManager();
    public static int b = 5423;

    /* renamed from: d, reason: collision with root package name */
    public static final LinkedList<Task> f73898d = new LinkedList<>();
    public static String f = "";

    /* renamed from: g, reason: collision with root package name */
    public static HashMap<String, Object> f73899g = new HashMap<>();
    public static String h = "text";
    public static String i = "";

    /* loaded from: classes38.dex */
    public static final class Task {

        public static final class Companion {
        }

        static {
            new Companion();
        }
    }

    /* loaded from: classes9.dex */
    public static final class TextToAudioInfoResult {

        /* renamed from: a, reason: collision with root package name */
        public final int f73900a;
        public final TextToAudioInfoPack b;

        /* renamed from: c, reason: collision with root package name */
        public final TtsResult f73901c;

        public TextToAudioInfoResult(int i, TextToAudioInfoPack textToAudioInfoPack, TtsResult ttsResult) {
            this.f73900a = i;
            this.b = textToAudioInfoPack;
            this.f73901c = ttsResult;
        }

        public final String a() {
            TtsResult ttsResult = this.f73901c;
            return ttsResult == null ? "" : ExtentionKt.toJson(MapsKt__MapsKt.mapOf(TuplesKt.to("msg", ttsResult.f69165d), TuplesKt.to("sub_error", ttsResult.f69164c), TuplesKt.to("extra", ttsResult.h)));
        }

        public final boolean b() {
            TextToAudioInfoPack textToAudioInfoPack;
            return this.f73900a == 0 && (textToAudioInfoPack = this.b) != null && (textToAudioInfoPack.f87040a.isEmpty() ^ true);
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TextToAudioInfoResult)) {
                return false;
            }
            TextToAudioInfoResult textToAudioInfoResult = (TextToAudioInfoResult) obj;
            return this.f73900a == textToAudioInfoResult.f73900a && Intrinsics.areEqual(this.b, textToAudioInfoResult.b) && Intrinsics.areEqual(this.f73901c, textToAudioInfoResult.f73901c);
        }

        public final int hashCode() {
            int i = this.f73900a * 31;
            TextToAudioInfoPack textToAudioInfoPack = this.b;
            int iHashCode = (i + (textToAudioInfoPack == null ? 0 : textToAudioInfoPack.hashCode())) * 31;
            TtsResult ttsResult = this.f73901c;
            return iHashCode + (ttsResult != null ? ttsResult.hashCode() : 0);
        }

        public final String toString() {
            return "TextToAudioInfoResult(errCode=" + this.f73900a + ", textToAudioInfo=" + this.b + ", ttsResult=" + this.f73901c + ')';
        }
    }

    /* loaded from: classes6.dex */
    public /* synthetic */ class WhenMappings {
        static {
            SAMICoreCallBackEventType.values();
            try {
                SAMICoreCallBackEventType.TTS_Started.ordinal();
            } catch (NoSuchFieldError unused) {
            }
            try {
                SAMICoreCallBackEventType.TTS_Finished.ordinal();
            } catch (NoSuchFieldError unused2) {
            }
            try {
                SAMICoreCallBackEventType.TTS_Failed.ordinal();
            } catch (NoSuchFieldError unused3) {
            }
            try {
                SAMICoreCallBackEventType.TTS_WebSocketStateChanged.ordinal();
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    static {
        List list = null;
        e = new TextToAudioInfoPack(list, list, list, list, 63);
        new AtomicReference();
        j = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        k = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.vega.audio.tone.TextToAudioManager$executeScope$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                ExecutorService executorServiceNewSingleThreadExecutor = PThreadExecutorsUtils.newSingleThreadExecutor(new ThreadFactory() { // from class: X.0AF
                    @Override // java.util.concurrent.ThreadFactory
                    public final Thread newThread(Runnable runnable) {
                        return new BPEAThread(runnable, "ReadText");
                    }
                });
                Intrinsics.checkNotNullExpressionValue(executorServiceNewSingleThreadExecutor, "");
                return CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executorServiceNewSingleThreadExecutor).plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
            }
        });
        l = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.vega.audio.tone.TextToAudioManager$timeoutScope$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                return CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
            }
        });
        m = LazyKt__LazyJVMKt.lazy(new Function0<IAccount>() { // from class: com.vega.audio.tone.TextToAudioManager$account$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final IAccount invoke() {
                return ((EditorProxyModule) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(EditorProxyModule.class), null)).getAccount();
            }
        });
    }

    public static void a(ITextToAudio.ReadingListener readingListener) {
        Intrinsics.checkNotNullParameter(readingListener, "");
        if (Intrinsics.areEqual(readingListener, n)) {
            n = null;
        }
    }

    public static void b(String str, DealStatus dealStatus, int i2, Float f2) {
        Intrinsics.checkNotNullParameter(str, "");
        HashMap<String, Object> map = new HashMap<>();
        ReportHelper.f73893a.getClass();
        String str2 = ReportHelper.b;
        if (str2 != null) {
            map.put("edit_type", str2);
        }
        map.put("type", str);
        map.put("text_length", f);
        if (dealStatus != null) {
            map.put("status", dealStatus.f87039a);
        }
        map.put("error_code", i2 == 20000000 ? "0" : String.valueOf(i2));
        if (f2 != null) {
            f2.floatValue();
            map.put("generate_time", f2.toString());
        }
        map.put("is_apply_all", f73897c == 2 ? ProfileManager.VERSION : "0");
        map.putAll(f73899g);
        map.put("enter_from", h);
        if (Intrinsics.areEqual(h, "ai_avatar_edit")) {
            map.put("ai_avatar_entrance", i);
        }
        ReportManagerWrapper.INSTANCE.onEvent("text_to_audio_generate_status", map);
    }

    public static /* synthetic */ void c(TextToAudioManager textToAudioManager, int i2) {
        textToAudioManager.getClass();
        b("begin", null, i2, null);
    }

    public static void d() {
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("TextToAudioManager", "reset");
        }
        f73898d.clear();
        b = 5423;
        TextToAudioInfoPack textToAudioInfoPack = e;
        textToAudioInfoPack.f87040a.clear();
        textToAudioInfoPack.b.clear();
        textToAudioInfoPack.f87041c.clear();
    }

    public static void e(TextToAudioManager textToAudioManager, List list, String str, TextToSpeechReportScene textToSpeechReportScene, final Function2 function2) {
        HashMap<String, Object> map = new HashMap<>();
        textToAudioManager.getClass();
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(str, "");
        f73899g = map;
        f = "";
        h = "text";
        i = "";
        Iterator it = list.iterator();
        int length = 0;
        while (it.hasNext()) {
            length += ((String) it.next()).length();
        }
        TextToSpeechReportInfo textToSpeechReportInfo = new TextToSpeechReportInfo(textToSpeechReportScene, null, length, false, false, 0L, null, null, null, null, 994, null);
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("TextToAudioManager", "startSavingAudio called by outside.");
        }
        TextToSpeechTaskManager.f74281a.c(new TextToSpeechIntent(null, new TextInfo.NoSegTextList(list), str, "sami", "TextToAudioManager", null, TTSBusinessType.f88857c, null, null, 0.0f, 24000, null, null, false, textToSpeechReportInfo.toJson(), null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>() { // from class: com.vega.audio.tone.TextToAudioManager$startSavingAudio$2
            /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function2<? super com.vega.edit.base.audio.tone.DealStatus, ? super com.vega.edit.base.audio.tone.TextToAudioInfoPack, kotlin.Unit> */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                TextToAudioInfoPack textToAudioInfoPack;
                TtsResult ttsResult2 = ttsResult;
                TextToAudioInfo textToAudioInfo2 = textToAudioInfo;
                Intrinsics.checkNotNullParameter(ttsResult2, "");
                Intrinsics.checkNotNullParameter(textToAudioInfo2, "");
                StatusResult statusResult = ttsResult2.f69163a;
                StatusResult statusResult2 = StatusResult.b;
                DealStatus dealStatus = statusResult == statusResult2 ? DealStatus.b : DealStatus.f87036c;
                if (statusResult == statusResult2) {
                    textToAudioInfoPack = new TextToAudioInfoPack(textToAudioInfo2.f59067a, textToAudioInfo2.b, null, textToAudioInfo2.f59068c, 52);
                } else {
                    List list2 = null;
                    textToAudioInfoPack = new TextToAudioInfoPack(list2, list2, list2, list2, 63);
                }
                Function2<DealStatus, TextToAudioInfoPack, Unit> function22 = function2;
                if (function22 != null) {
                    function22.invoke(dealStatus, textToAudioInfoPack);
                }
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -2638943, 31));
        FeelGoodReportHelper.f88997a.getClass();
        FeelGoodReportHelper.b(null, "use_text_to_audio");
    }
}
package com.vega.audio.tone.tts.core.test;

import com.bytedance.bpea.transmit.delegate.BPEAThread;
import com.lemon.lv.data.TextToAudioInfo;
import com.ss.android.ugc.bytex.pthread.base.PThreadExecutorsUtils;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.edit.base.tone.ITextToSpeechTaskManager;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.ExecutorsKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* loaded from: classes16.dex */
public final class SchedulerTestCase {

    /* renamed from: a, reason: collision with root package name */
    public final ITextToSpeechTaskManager f74362a;
    public final Lazy b;

    public SchedulerTestCase(TextToSpeechTaskManager textToSpeechTaskManager) {
        Intrinsics.checkNotNullParameter(textToSpeechTaskManager, "");
        this.f74362a = textToSpeechTaskManager;
        this.b = LazyKt__LazyJVMKt.lazy(new Function0<CoroutineScope>() { // from class: com.vega.audio.tone.tts.core.test.SchedulerTestCase$coroutineScope$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final CoroutineScope invoke() {
                ExecutorService executorServiceNewFixedThreadPool = PThreadExecutorsUtils.newFixedThreadPool(4, new ThreadFactory() { // from class: X.0Av
                    @Override // java.util.concurrent.ThreadFactory
                    public final Thread newThread(Runnable runnable) {
                        return new BPEAThread(runnable, "SchedulerTestCase");
                    }
                });
                Intrinsics.checkNotNullExpressionValue(executorServiceNewFixedThreadPool, "");
                return CoroutineScopeKt.CoroutineScope(ExecutorsKt.from(executorServiceNewFixedThreadPool).plus(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null)));
            }
        });
    }

    public static TextToSpeechIntent b(int i, final boolean z) {
        final String str = "mc task-" + i;
        return new TextToSpeechIntent(str, new TextInfo.AutoSegText("She's been playing with it non-stop and I can see she's excited to learn."), "en-HK-SamNeural", "microsoft", "SchedulerTestCase", null, TTSBusinessType.b, null, null, 0.0f, 0, null, null, false, null, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>(str, z) { // from class: com.vega.audio.tone.tts.core.test.SchedulerTestCase$getMcIntent$mcIntent$1
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                TtsResult ttsResult2 = ttsResult;
                Intrinsics.checkNotNullParameter(ttsResult2, "");
                Intrinsics.checkNotNullParameter(textToAudioInfo, "");
                Objects.toString(ttsResult2.f69163a);
                Objects.toString(textToAudioInfo);
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -524384, 31);
    }

    public static TextToSpeechIntent c(int i, final boolean z) {
        final String str = "sami task-" + i;
        return new TextToSpeechIntent(str, new TextInfo.AutoSegText("It's not too challenging for her to figure out how to use it either."), "BV502_streaming", "sami", "SchedulerTestCase", null, TTSBusinessType.b, null, null, 0.0f, 0, null, null, false, null, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>(str, z) { // from class: com.vega.audio.tone.tts.core.test.SchedulerTestCase$getSamiIntent$samiIntent$1
            {
                super(2);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function2
            public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                TtsResult ttsResult2 = ttsResult;
                Intrinsics.checkNotNullParameter(ttsResult2, "");
                Intrinsics.checkNotNullParameter(textToAudioInfo, "");
                Objects.toString(ttsResult2.f69163a);
                Objects.toString(textToAudioInfo);
                return Unit.INSTANCE;
            }
        }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -524384, 31);
    }

    public final CoroutineScope a() {
        return (CoroutineScope) this.b.getValue();
    }

    public final void d() {
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testMultipleInit$1(this, null), 3, null);
    }

    public final void e() {
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testParallelTaskWithMultiThreadMC$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testParallelTaskWithMultiThreadMC$2(this, null), 3, null);
    }

    public final void f() {
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testParallelTaskWithMultiThreadSAMI$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testParallelTaskWithMultiThreadSAMI$2(this, null), 3, null);
    }

    public final void g() {
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadMC$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadMC$2(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadMC$3(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadMC$4(this, null), 3, null);
    }

    public final void h() {
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadSAMI$1(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadSAMI$2(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadSAMI$3(this, null), 3, null);
        BuildersKt__Builders_commonKt.launch$default(a(), null, null, new SchedulerTestCase$testSerialTaskWithMultiThreadSAMI$4(this, null), 3, null);
    }
}
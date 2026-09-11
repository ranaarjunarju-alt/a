package com.vega.audio.tone.tts.debug;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.bytedance.helios.statichook.lifecycle.ActivityLifecycleHook;
import com.bytedance.sysoptimizer.EnterTransitionCrashOptimizer;
import com.kanyun.kace.AndroidExtensions;
import com.kanyun.kace.AndroidExtensionsBase;
import com.kanyun.kace.AndroidExtensionsImpl;
import com.lemon.lv.data.TextToAudioInfo;
import com.lemon.lvoverseas.R;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.audio.tone.tts.core.test.SchedulerTestCase;
import com.vega.audio.tone.tts.debug.ToneDebugActivity;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$1$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$2$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$3$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$4$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$5$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$6$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$7$1;
import com.vega.audio.tone.tts.debug.ToneDebugActivity$onCreate$8$1;
import com.vega.edit.base.tone.TTSBusinessType;
import com.vega.edit.base.tone.TextInfo;
import com.vega.edit.base.tone.TextToSpeechIntent;
import java.util.Objects;
import java.util.UUID;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* loaded from: classes31.dex */
public final class ToneDebugActivity extends AppCompatActivity implements AndroidExtensions {
    public static final /* synthetic */ int o = 0;
    public final Lazy l = LazyKt__LazyJVMKt.lazy(new Function0<TextToSpeechTaskManager>() { // from class: com.vega.audio.tone.tts.debug.ToneDebugActivity$taskManager$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final TextToSpeechTaskManager invoke() {
            return TextToSpeechTaskManager.f74281a;
        }
    });
    public final Lazy m = LazyKt__LazyJVMKt.lazy(new Function0<SchedulerTestCase>() { // from class: com.vega.audio.tone.tts.debug.ToneDebugActivity$testCase$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final SchedulerTestCase invoke() {
            return new SchedulerTestCase(this.e.f1());
        }
    });
    public final AndroidExtensionsImpl n = new AndroidExtensionsImpl();

    /* loaded from: classes35.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public static void com_vega_audio_tone_tts_debug_ToneDebugActivity_com_bytedance_sysoptimizer_EnterTransitionLancet_onStop(ToneDebugActivity toneDebugActivity) {
        super.onStop();
        if (EnterTransitionCrashOptimizer.getContext() != null) {
            try {
                toneDebugActivity.getWindow().getDecorView().getViewTreeObserver().dispatchOnPreDraw();
            } catch (Throwable unused) {
            }
        }
    }

    public final TextToSpeechTaskManager f1() {
        return (TextToSpeechTaskManager) this.l.getValue();
    }

    @Override // com.kanyun.kace.AndroidExtensionsBase
    public final <T extends View> T findViewByIdCached(AndroidExtensionsBase androidExtensionsBase, int i, Class<T> cls) {
        Intrinsics.checkNotNullParameter(androidExtensionsBase, "");
        Intrinsics.checkNotNullParameter(cls, "");
        return (T) this.n.findViewByIdCached(androidExtensionsBase, i, cls);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        ActivityLifecycleHook.a(this, bundle);
        super.onCreate(bundle);
        setContentView(R.layout.l8w);
        f1().init(null);
        final EditText editText = (EditText) findViewById(R.id.stop_delay);
        editText.setText("2000");
        editText.setHint("stop_delay");
        editText.setInputType(2);
        ((TextView) findViewById(R.id.sami_reading)).setText("sami_reading");
        findViewById(R.id.sami_reading).setOnClickListener(new View.OnClickListener() { // from class: X.1er
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$1$1(null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.sami_stop_reading)).setText("sami_stop_reading");
        findViewById(R.id.sami_stop_reading).setOnClickListener(new View.OnClickListener() { // from class: X.0o3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NumberFormatException {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$2$1(Long.parseLong(editText.getText().toString()), null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.sami_tts)).setText("sami_tts");
        findViewById(R.id.sami_tts).setOnClickListener(new View.OnClickListener() { // from class: X.1es
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$3$1(null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.sami_stop_tts)).setText("sami_stop_tts");
        findViewById(R.id.sami_stop_tts).setOnClickListener(new View.OnClickListener() { // from class: X.0o4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NumberFormatException {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$4$1(Long.parseLong(editText.getText().toString()), null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.microsoft_reading)).setText("microsoft_reading");
        findViewById(R.id.microsoft_reading).setOnClickListener(new View.OnClickListener() { // from class: X.1et
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$5$1(null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.microsoft_stop_reading)).setText("microsoft_stop_reading");
        findViewById(R.id.microsoft_stop_reading).setOnClickListener(new View.OnClickListener() { // from class: X.0o5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NumberFormatException {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$6$1(Long.parseLong(editText.getText().toString()), null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.microsoft_tts)).setText("microsoft_tts");
        findViewById(R.id.microsoft_tts).setOnClickListener(new View.OnClickListener() { // from class: X.1eu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$7$1(null), 3, null);
            }
        });
        ((TextView) findViewById(R.id.microsoft_stop_tts)).setText("microsoft_stop_tts");
        findViewById(R.id.microsoft_stop_tts).setOnClickListener(new View.OnClickListener() { // from class: X.0o6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NumberFormatException {
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new ToneDebugActivity$onCreate$8$1(Long.parseLong(editText.getText().toString()), null), 3, null);
            }
        });
        StringBuilder sb = new StringBuilder("task-");
        String string = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        sb.append(StringsKt__StringsJVMKt.replace$default(string, "-", "", false, 4, (Object) null));
        final String string2 = sb.toString();
        TextInfo.AutoSegText autoSegText = new TextInfo.AutoSegText("It's not too challenging for her to figure out how to use it either. \n She's been playing with it non-stop and I can see she's excited to learn.");
        TTSBusinessType tTSBusinessType = TTSBusinessType.b;
        final TextToSpeechIntent textToSpeechIntent = new TextToSpeechIntent(string2, autoSegText, "BV502_streaming", "sami", "test", null, tTSBusinessType, null, null, 0.0f, 0, null, null, false, null, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>(string2) { // from class: com.vega.audio.tone.tts.debug.ToneDebugActivity$newManagerTest$intent$1
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
        ((TextView) findViewById(R.id.sami_reading_new_engine)).setText("sami_reading_new_engine");
        findViewById(R.id.sami_reading_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31i
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToneDebugActivity toneDebugActivity = this.f13893a;
                toneDebugActivity.f1().f(textToSpeechIntent);
            }
        });
        ((TextView) findViewById(R.id.sami_stop_reading_new_engine)).setText("sami_stop_reading_new_engine");
        findViewById(R.id.sami_stop_reading_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31k
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f13895a.f1().b(false);
            }
        });
        ((TextView) findViewById(R.id.sami_tts_new_engine)).setText("sami_tts_new_engine");
        findViewById(R.id.sami_tts_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToneDebugActivity toneDebugActivity = this.f13891a;
                toneDebugActivity.f1().c(textToSpeechIntent);
            }
        });
        ((TextView) findViewById(R.id.sami_stop_tts_new_engine)).setText("sami_stop_tts_new_engine");
        findViewById(R.id.sami_stop_tts_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31j
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f13894a.f1().a("");
            }
        });
        StringBuilder sb2 = new StringBuilder("mc task-");
        String string3 = UUID.randomUUID().toString();
        Intrinsics.checkNotNullExpressionValue(string3, "");
        sb2.append(StringsKt__StringsJVMKt.replace$default(string3, "-", "", false, 4, (Object) null));
        final TextToSpeechIntent textToSpeechIntent2 = new TextToSpeechIntent(sb2.toString(), new TextInfo.AutoSegText("It's not too challenging for her to figure out how to use it either. \n She's been playing with it non-stop and I can see she's excited to learn."), "en-HK-SamNeural", "microsoft", "test", null, tTSBusinessType, null, null, 0.0f, 0, null, null, false, null, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>(string2) { // from class: com.vega.audio.tone.tts.debug.ToneDebugActivity$newManagerTest$mcIntent$1
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
        ((TextView) findViewById(R.id.ms_reading_new_engine)).setText("ms_reading_new_engine");
        findViewById(R.id.ms_reading_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31h
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToneDebugActivity toneDebugActivity = this.f13892a;
                toneDebugActivity.f1().f(textToSpeechIntent2);
            }
        });
        ((TextView) findViewById(R.id.ms_stop_reading_new_engine)).setText("ms_stop_reading_new_engine");
        findViewById(R.id.ms_stop_reading_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31l
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f13896a.f1().b(false);
            }
        });
        ((TextView) findViewById(R.id.ms_saving_new_engine)).setText("ms_saving_new_engine");
        findViewById(R.id.ms_saving_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31b
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ToneDebugActivity toneDebugActivity = this.f13886a;
                final String str = string2;
                TextInfo.AutoSegText autoSegText2 = new TextInfo.AutoSegText("It's not too challenging for her to figure out how to use it either. \n She's been playing with it non-stop and I can see she's excited to learn.");
                TTSBusinessType tTSBusinessType2 = TTSBusinessType.b;
                toneDebugActivity.f1().d(CollectionsKt__CollectionsKt.listOf((Object[]) new TextToSpeechIntent[]{new TextToSpeechIntent("mc task-111", autoSegText2, "en-HK-SamNeural", "microsoft", "test", null, tTSBusinessType2, null, null, 0.0f, 0, null, null, false, null, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>(str) { // from class: com.vega.audio.tone.tts.debug.ToneDebugActivity$newManagerTest$7$mcIntent1$1
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                        Intrinsics.checkNotNullParameter(ttsResult, "");
                        Intrinsics.checkNotNullParameter(textToAudioInfo, "");
                        Objects.toString(ttsResult);
                        Objects.toString(textToAudioInfo);
                        return Unit.INSTANCE;
                    }
                }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -524384, 31), new TextToSpeechIntent("mc task-222", new TextInfo.AutoSegText("It's not too challenging for her to figure out how to use it either. \n She's been playing with it non-stop and I can see she's excited to learn."), "en-HK-SamNeural", "microsoft", "test", null, tTSBusinessType2, null, null, 0.0f, 0, null, null, false, null, null, false, null, null, new Function2<TtsResult, TextToAudioInfo, Unit>(str) { // from class: com.vega.audio.tone.tts.debug.ToneDebugActivity$newManagerTest$7$mcIntent2$1
                    {
                        super(2);
                    }

                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object, java.lang.Object] */
                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                    @Override // kotlin.jvm.functions.Function2
                    public final Unit invoke(TtsResult ttsResult, TextToAudioInfo textToAudioInfo) {
                        Intrinsics.checkNotNullParameter(ttsResult, "");
                        Intrinsics.checkNotNullParameter(textToAudioInfo, "");
                        Objects.toString(ttsResult);
                        Objects.toString(textToAudioInfo);
                        return Unit.INSTANCE;
                    }
                }, null, null, null, null, false, null, null, false, false, false, false, null, null, null, null, false, -524384, 31)}));
            }
        });
        ((TextView) findViewById(R.id.ms_stop_saving_new_engine)).setText("ms_stop_saving_new_engine");
        findViewById(R.id.ms_stop_saving_new_engine).setOnClickListener(new View.OnClickListener() { // from class: X.31c
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f13887a.f1().e();
            }
        });
        ((TextView) findViewById(R.id.test_init)).setText("test_init");
        findViewById(R.id.test_init).setOnClickListener(new View.OnClickListener() { // from class: X.31o
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((SchedulerTestCase) this.f13899a.m.getValue()).d();
            }
        });
        ((TextView) findViewById(R.id.test_sami_serial)).setText("test_sami_serial");
        findViewById(R.id.test_sami_serial).setOnClickListener(new View.OnClickListener() { // from class: X.31p
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((SchedulerTestCase) this.f13900a.m.getValue()).h();
            }
        });
        ((TextView) findViewById(R.id.test_sami_parallel)).setText("test_sami_parallel");
        findViewById(R.id.test_sami_parallel).setOnClickListener(new View.OnClickListener() { // from class: X.31q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((SchedulerTestCase) this.f13901a.m.getValue()).f();
            }
        });
        ((TextView) findViewById(R.id.test_mc_serial)).setText("test_mc_serial");
        findViewById(R.id.test_mc_serial).setOnClickListener(new View.OnClickListener() { // from class: X.31m
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((SchedulerTestCase) this.f13897a.m.getValue()).g();
            }
        });
        ((TextView) findViewById(R.id.test_mc_parallel)).setText("test_mc_parallel");
        findViewById(R.id.test_mc_parallel).setOnClickListener(new View.OnClickListener() { // from class: X.31n
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ((SchedulerTestCase) this.f13898a.m.getValue()).e();
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        ActivityLifecycleHook.b(this);
        super.onDestroy();
        f1().destroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        ActivityLifecycleHook.c(this);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onResume() {
        ActivityLifecycleHook.d(this);
        super.onResume();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStart() {
        ActivityLifecycleHook.e(this);
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        ActivityLifecycleHook.f(this);
        com_vega_audio_tone_tts_debug_ToneDebugActivity_com_bytedance_sysoptimizer_EnterTransitionLancet_onStop(this);
    }
}
package com.vega.adeditor.utils;

import android.app.Activity;
import android.app.Application;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwnerKt;
import com.lemon.lv.data.ToneType;
import com.lm.components.lynx.bridge.LynxBridgeManager;
import com.lm.components.lynx.msgcenter.LynxMsgCenter;
import com.vega.adeditorapi.PlayTTSApi;
import com.vega.audio.tone.tts.TextToSpeechTaskManager;
import com.vega.core.ActivityLifecycleCallback;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.Job;
import org.json.JSONObject;

/* loaded from: classes26.dex */
public final class PlayTTSApiImpl implements PlayTTSApi {

    /* renamed from: g, reason: collision with root package name */
    public static final /* synthetic */ int f68225g = 0;

    /* renamed from: a, reason: collision with root package name */
    public String f68226a;
    public LifecycleCallback b;
    public Job e;

    /* renamed from: c, reason: collision with root package name */
    public final AtomicBoolean f68227c = new AtomicBoolean(false);

    /* renamed from: d, reason: collision with root package name */
    public final AtomicBoolean f68228d = new AtomicBoolean(false);
    public final Lazy f = LazyKt__LazyJVMKt.lazy(new Function0<TextToSpeechTaskManager>() { // from class: com.vega.adeditor.utils.PlayTTSApiImpl$textToSpeechManager$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final TextToSpeechTaskManager invoke() {
            return TextToSpeechTaskManager.f74281a;
        }
    });

    /* loaded from: classes40.dex */
    public static final class Companion {
    }

    /* loaded from: classes14.dex */
    public static final class LifecycleCallback extends ActivityLifecycleCallback {

        /* renamed from: a, reason: collision with root package name */
        public final WeakReference<Activity> f68229a;
        public final WeakReference<PlayTTSApiImpl> b;

        public LifecycleCallback(WeakReference<Activity> weakReference, WeakReference<PlayTTSApiImpl> weakReference2) {
            Intrinsics.checkNotNullParameter(weakReference, "");
            Intrinsics.checkNotNullParameter(weakReference2, "");
            this.f68229a = weakReference;
            this.b = weakReference2;
        }

        @Override // com.vega.core.ActivityLifecycleCallback, android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityDestroyed(Activity activity) {
            PlayTTSApiImpl playTTSApiImpl;
            Intrinsics.checkNotNullParameter(activity, "");
            if (activity != this.f68229a.get() || (playTTSApiImpl = this.b.get()) == null) {
                return;
            }
            playTTSApiImpl.d();
        }

        @Override // com.vega.core.ActivityLifecycleCallback, android.app.Application.ActivityLifecycleCallbacks
        public final void onActivityStopped(Activity activity) {
            PlayTTSApiImpl playTTSApiImpl;
            Intrinsics.checkNotNullParameter(activity, "");
            if (activity != this.f68229a.get() || (playTTSApiImpl = this.b.get()) == null) {
                return;
            }
            playTTSApiImpl.d();
        }
    }

    static {
        new Companion();
    }

    @Override // com.vega.adeditorapi.PlayTTSApi
    public final void a(FragmentActivity fragmentActivity, Function1<? super List<ToneType>, Unit> function1) {
        Intrinsics.checkNotNullParameter(fragmentActivity, "");
        Intrinsics.checkNotNullParameter(function1, "");
        LifecycleOwnerKt.a(fragmentActivity).d(new PlayTTSApiImpl$getTTSList$1(function1, null));
    }

    @Override // com.vega.adeditorapi.PlayTTSApi
    public final void b() {
        ((TextToSpeechTaskManager) this.f.getValue()).b(true);
    }

    @Override // com.vega.adeditorapi.PlayTTSApi
    public final void c(FragmentActivity fragmentActivity, String str, int i, String str2, String str3, float f, String str4, Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(function1, "");
        if (str == null || str.length() == 0 || str2 == null || str2.length() == 0) {
            function1.invoke(Boolean.FALSE);
            return;
        }
        d();
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("PlayTTSApiImpl", "playTTS, voiceTypeID=" + str2 + ", text=" + str);
        }
        this.f68226a = str2;
        Application application = fragmentActivity.getApplication();
        if (application != null) {
            application.unregisterActivityLifecycleCallbacks(this.b);
            LifecycleCallback lifecycleCallback = new LifecycleCallback(new WeakReference(fragmentActivity), new WeakReference(this));
            this.b = lifecycleCallback;
            application.registerActivityLifecycleCallbacks(lifecycleCallback);
        }
        LifecycleOwnerKt.a(fragmentActivity).d(new PlayTTSApiImpl$playTTSWithTextToSpeechManager$1(str4, this, str2, str, f, i, fragmentActivity, null));
        this.e = LifecycleOwnerKt.a(fragmentActivity).d(new PlayTTSApiImpl$playTTS$1(this, null));
        function1.invoke(Boolean.TRUE);
    }

    public final void d() {
        this.f68227c.set(false);
        this.f68228d.set(false);
        Job job = this.e;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        this.f68226a = null;
    }

    public final void e(String str, String str2) {
        if (PerformanceManagerHelper.blogEnable) {
            StringBuilder sb = new StringBuilder("sendStatus, status=");
            sb.append(str);
            sb.append(", lastVoiceTypeID=");
            sb.append(str2 == null ? this.f68226a : str2);
            BLog.i("PlayTTSApiImpl", sb.toString());
        }
        LynxMsgCenter lynxMsgCenter = LynxMsgCenter.f60731a;
        LynxBridgeManager lynxBridgeManager = LynxBridgeManager.f60479a;
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", str);
        if (str2 == null) {
            str2 = this.f68226a;
        }
        jSONObject.put("voiceTypeID", str2);
        lynxBridgeManager.getClass();
        JSONObject jSONObjectN = LynxBridgeManager.n(jSONObject);
        PlayTTSApiImpl$sendStatus$2 playTTSApiImpl$sendStatus$2 = new Function1<Object, Unit>() { // from class: com.vega.adeditor.utils.PlayTTSApiImpl$sendStatus$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final /* bridge */ /* synthetic */ Unit invoke(Object obj) {
                return Unit.INSTANCE;
            }
        };
        lynxMsgCenter.getClass();
        LynxMsgCenter.g("playTTSStatus", "", jSONObjectN, 1, playTTSApiImpl$sendStatus$2);
    }
}
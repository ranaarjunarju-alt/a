package com.vega.launcher.network.interceptors;

import android.app.Application;
import android.content.SharedPreferences;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.services.apm.api.EnsureManager;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.illusion.light.EffectUtil;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.kv.keva.KevaSpAopHook;
import com.vega.libeffect.repository.ResPoolRepository;
import com.vega.log.BLog;
import java.io.StringReader;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* loaded from: classes9.dex */
public final class CrackingInterceptor implements Interceptor {

    /* renamed from: d, reason: collision with root package name */
    public static final Companion f107402d;
    public static boolean e;
    public boolean b;

    /* renamed from: a, reason: collision with root package name */
    public final Lazy f107403a = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends String>>() { // from class: com.vega.launcher.network.interceptors.CrackingInterceptor$skipCrackingPath$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final List<? extends String> invoke() {
            return CollectionsKt__CollectionsJVMKt.listOf("/lv/v1/rick_control/check_spam");
        }
    });

    /* renamed from: c, reason: collision with root package name */
    public final Lazy f107404c = LazyKt__LazyJVMKt.lazy(new Function0<SharedPreferences>() { // from class: com.vega.launcher.network.interceptors.CrackingInterceptor$sp$2
        public static SharedPreferences INVOKEVIRTUAL_com_vega_launcher_network_interceptors_CrackingInterceptor$sp$2_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(Application application, String str, int i) throws InterruptedException {
            try {
                return KevaSpAopHook.getSharedPreferences(application, str, i);
            } catch (NullPointerException e2) {
                try {
                    Thread.sleep(0L);
                } catch (InterruptedException e3) {
                    e3.printStackTrace();
                }
                if (e2.getMessage() != null && e2.getMessage().contains("isUserKeyUnlocked")) {
                    EnsureManager.ensureNotReachHere(e2, "getSharedPreferences isUserKeyUnlocked NullPointerException name=" + str + ",mode" + i);
                }
                return KevaSpAopHook.getSharedPreferences(application, str, i);
            }
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final SharedPreferences invoke() {
            return INVOKEVIRTUAL_com_vega_launcher_network_interceptors_CrackingInterceptor$sp$2_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(ModuleCommon.INSTANCE.getApplication(), "crackingInfo", 0);
        }
    });

    /* loaded from: classes5.dex */
    public static final class Companion {
    }

    /* loaded from: classes7.dex */
    public /* synthetic */ class WhenMappings {

        /* renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f107405a;

        static {
            int[] iArr = new int[JsonToken.values().length];
            try {
                iArr[JsonToken.NULL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[JsonToken.STRING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[JsonToken.NUMBER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[JsonToken.BOOLEAN.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            f107405a = iArr;
        }
    }

    @DebugMetadata(c = "com.vega.launcher.network.interceptors.CrackingInterceptor$intercept$1", f = "CrackingInterceptor.kt", i = {}, l = {79}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.vega.launcher.network.interceptors.CrackingInterceptor$intercept$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        public int q;

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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.q;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    ResPoolRepository resPoolRepository = ResPoolRepository.f109603a;
                    this.q = 1;
                    obj = ResPoolRepository.G(resPoolRepository, true, this, 6);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                Result.m17090constructorimpl((Integer) obj);
            } catch (Throwable th) {
                Result.m17090constructorimpl(ResultKt.createFailure(th));
            }
            return Unit.INSTANCE;
        }
    }

    static {
        EffectUtil.Ew0PFxIIElcAXVdHWFpVCEM0(14);
        f107402d = new Companion();
    }

    public static String a(String str) {
        if (StringsKt__StringsKt.isBlank(str)) {
            return null;
        }
        try {
            JsonReader jsonReader = new JsonReader(new StringReader(str));
            jsonReader.beginObject();
            while (jsonReader.hasNext()) {
                if (Intrinsics.areEqual(jsonReader.nextName(), "ret")) {
                    JsonToken jsonTokenPeek = jsonReader.peek();
                    int i = jsonTokenPeek == null ? -1 : WhenMappings.f107405a[jsonTokenPeek.ordinal()];
                    if (i == 1) {
                        jsonReader.nextNull();
                        return "";
                    }
                    if (i == 2) {
                        return jsonReader.nextString();
                    }
                    if (i == 3) {
                        return String.valueOf(jsonReader.nextLong());
                    }
                    if (i == 4) {
                        return String.valueOf(jsonReader.nextBoolean());
                    }
                    jsonReader.skipValue();
                    return "";
                }
                jsonReader.skipValue();
            }
        } catch (Exception e2) {
            BLog.e("CrackingInterceptor", "extractRetCodeFromStream failed: " + e2.getMessage());
        }
        return "";
    }

    public final boolean b(String str) {
        Iterator it = ((List) this.f107403a.getValue()).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Object next = it.next();
            if (StringsKt__StringsKt.contains$default(str, (String) next, false, 2, (Object) null)) {
                if (next != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public final void c(int i) {
        ((SharedPreferences) this.f107404c.getValue()).edit().putBoolean("isCracking", true).putLong("updateTime", System.currentTimeMillis()).putInt("crackCode", i).apply();
    }

    public final native void d(String str);

    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final native SsResponse<?> intercept(Interceptor.Chain chain);
}
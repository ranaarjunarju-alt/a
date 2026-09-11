package com.vega.audio.tone;

import X.C0NS;
import X.C0NT;
import X.C1J8;
import com.vega.libeffect.repository.ResourceRepository;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import org.koin.core.Koin;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes34.dex */
public final class ToneTypeHelper implements C0NS {

    /* renamed from: a, reason: collision with root package name */
    public final Lazy f73905a = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<ResourceRepository>() { // from class: com.vega.audio.tone.ToneTypeHelper$special$$inlined$inject$default$1
        public final /* synthetic */ Qualifier f = null;

        /* renamed from: g, reason: collision with root package name */
        public final /* synthetic */ Function0 f73906g = null;

        {
            super(0);
        }

        /* JADX WARN: Type inference failed for: r0v5, types: [com.vega.libeffect.repository.ResourceRepository, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final ResourceRepository invoke() {
            C0NS c0ns = this;
            return (c0ns instanceof C0NT ? ((C0NT) c0ns).F() : c0ns.getKoin().getScopeRegistry().getRootScope()).get(Reflection.getOrCreateKotlinClass(ResourceRepository.class), this.f, this.f73906g);
        }
    });

    public static Object a(String str, String str2, String str3, String str4, Continuation continuation) {
        return BuildersKt__Builders_commonKt.withContext(Dispatchers.getIO(), new ToneTypeHelper$fetchEffectToneType$2(str, str2, str3, str4, null), continuation);
    }

    @Override // X.C0NS
    public final Koin getKoin() {
        return C1J8.a();
    }
}
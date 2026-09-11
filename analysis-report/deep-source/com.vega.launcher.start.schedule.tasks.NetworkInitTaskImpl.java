package com.vega.launcher.start.schedule.tasks;

import X.C0NS;
import X.C0NT;
import X.C1J8;
import com.vega.core.app.AppContext;
import com.vega.launcher.network.interceptors.TimeOutSettingInterceptor;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Reflection;
import org.koin.core.Koin;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes7.dex */
public final class NetworkInitTaskImpl implements C0NS {

    /* renamed from: a, reason: collision with root package name */
    public static final NetworkInitTaskImpl f107610a;
    public static final Lazy b;

    /* renamed from: c, reason: collision with root package name */
    public static volatile TimeOutSettingInterceptor f107611c;

    static {
        final NetworkInitTaskImpl networkInitTaskImpl = new NetworkInitTaskImpl();
        f107610a = networkInitTaskImpl;
        b = LazyKt__LazyJVMKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<AppContext>(networkInitTaskImpl) { // from class: com.vega.launcher.start.schedule.tasks.NetworkInitTaskImpl$special$$inlined$inject$default$1
            public final /* synthetic */ C0NS e;
            public final /* synthetic */ Qualifier f = null;

            /* renamed from: g, reason: collision with root package name */
            public final /* synthetic */ Function0 f107612g = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.e = networkInitTaskImpl;
            }

            /* JADX WARN: Type inference failed for: r0v5, types: [com.vega.core.app.AppContext, java.lang.Object] */
            @Override // kotlin.jvm.functions.Function0
            public final AppContext invoke() {
                C0NS c0ns = this.e;
                return (c0ns instanceof C0NT ? ((C0NT) c0ns).F() : c0ns.getKoin().getScopeRegistry().getRootScope()).get(Reflection.getOrCreateKotlinClass(AppContext.class), this.f, this.f107612g);
            }
        });
    }

    public final AppContext a() {
        return (AppContext) b.getValue();
    }

    @Override // X.C0NS
    public final Koin getKoin() {
        return C1J8.a();
    }
}
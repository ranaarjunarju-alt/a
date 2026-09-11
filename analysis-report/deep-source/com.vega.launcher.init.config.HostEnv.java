package com.vega.launcher.init.config;

import X.C0NS;
import X.C0NT;
import X.C1J8;
import android.app.Application;
import com.vega.core.app.AppContext;
import com.vega.core.context.AppProperty;
import com.vega.core.context.IHostEnv;
import com.vega.core.context.debug.DevelopSetting;
import com.vega.core.data.LaunchInfo;
import com.vega.corex.context.DeviceInfo;
import com.vega.infrastructure.base.ModuleCommon;
import kotlin.jvm.internal.Reflection;
import org.koin.core.Koin;

/* loaded from: classes18.dex */
public final class HostEnv implements IHostEnv, C0NS {

    /* renamed from: a, reason: collision with root package name */
    public final UserLaunchInfo f107297a = UserLaunchInfo.f107301a;
    public final Application b = ModuleCommon.INSTANCE.getApplication();

    /* renamed from: c, reason: collision with root package name */
    public final AssistDevelopSetting f107298c = AssistDevelopSetting.f107289a;

    /* renamed from: d, reason: collision with root package name */
    public final AppContext f107299d;
    public final AppPropertyImpl e;
    public final DeviceInfoImpl f;

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: com.vega.launcher.init.config.HostEnv */
    /* JADX WARN: Multi-variable type inference failed */
    public HostEnv() {
        AppContext appContext = (AppContext) (this instanceof C0NT ? ((C0NT) this).F() : getKoin().getScopeRegistry().getRootScope()).get(Reflection.getOrCreateKotlinClass(AppContext.class), null, null);
        this.f107299d = appContext;
        this.e = new AppPropertyImpl(appContext.getChannel());
        this.f = new DeviceInfoImpl();
    }

    @Override // com.vega.core.context.IHostEnv
    public final Application app() {
        return this.b;
    }

    @Override // com.vega.core.context.IHostEnv
    public final AppContext appContext() {
        return this.f107299d;
    }

    @Override // com.vega.core.context.IHostEnv
    public final AppProperty appProperty() {
        return this.e;
    }

    @Override // com.vega.core.context.IHostEnv
    public final DevelopSetting developSettings() {
        return this.f107298c;
    }

    @Override // com.vega.core.context.IHostEnv
    public final DeviceInfo deviceInfo() {
        return this.f;
    }

    @Override // X.C0NS
    public final Koin getKoin() {
        return C1J8.a();
    }

    @Override // com.vega.core.context.IHostEnv
    public final LaunchInfo launchInfo() {
        return this.f107297a;
    }
}
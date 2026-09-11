package com.bytedance.zoin;

import com.bytedance.zoin.lib.LibModule;
import com.bytedance.zoin.model.AbstractModule;
import com.bytedance.zoin.model.AbstractModuleInfo;
import com.bytedance.zoin.model.ModuleManager;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes25.dex */
public class Zoin {
    public static volatile boolean b;

    /* renamed from: a, reason: collision with root package name */
    public final ConcurrentHashMap<String, AbstractModule> f48322a = new ConcurrentHashMap<>();

    /* loaded from: classes23.dex */
    public static class Holder {

        /* renamed from: a, reason: collision with root package name */
        public static final Zoin f48323a = new Zoin();
    }

    public static Zoin a() {
        return Holder.f48323a;
    }

    public final synchronized void b(LibModule libModule) {
        if (!b) {
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("zoin not initialize, do nothing");
            return;
        }
        AbstractModuleInfo abstractModuleInfoFindModuleByName = ModuleManager.findModuleByName(libModule.moduleName);
        if (abstractModuleInfoFindModuleByName == null) {
            ZoinMonitor zoinMonitorA = ZoinMonitor.a();
            String str = "zoin cant find module " + libModule.moduleName + ", do nothing";
            zoinMonitorA.getClass();
            ZoinMonitor.d(str);
            return;
        }
        libModule.setModuleInfo(abstractModuleInfoFindModuleByName);
        if (this.f48322a.get(libModule.moduleName) != null) {
            ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
            String str2 = "zoin register " + libModule.moduleName + " twice, ignored";
            zoinMonitorA2.getClass();
            ZoinMonitor.d(str2);
            return;
        }
        this.f48322a.put(libModule.moduleName, libModule);
        libModule.init();
        ZoinMonitor zoinMonitorA3 = ZoinMonitor.a();
        String str3 = "RegisterModule:" + libModule.moduleName;
        zoinMonitorA3.getClass();
        ZoinMonitor.d(str3);
    }
}
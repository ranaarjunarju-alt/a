package com.bytedance.zoin;

import android.app.Application;
import android.content.SharedPreferences;
import com.bytedance.bpea.transmit.delegate.BPEAThread;
import com.bytedance.services.apm.api.EnsureManager;
import com.ss.android.ugc.bytex.pthread.base.proxy.PThreadPoolExecutor;
import com.vega.kv.keva.KevaSpAopHook;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: classes2.dex */
public class ZoinMonitor {

    /* renamed from: c, reason: collision with root package name */
    public static ZoinMonitor f48324c;

    /* renamed from: d, reason: collision with root package name */
    public static final List<String> f48325d = new CopyOnWriteArrayList();

    /* renamed from: a, reason: collision with root package name */
    public SharedPreferences f48326a;
    public PThreadPoolExecutor b;

    /* loaded from: classes11.dex */
    public interface ReportDefine {
    }

    public static SharedPreferences INVOKEVIRTUAL_com_bytedance_zoin_ZoinMonitor_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(Application application, String str, int i) throws InterruptedException {
        try {
            return KevaSpAopHook.getSharedPreferences(application, str, i);
        } catch (NullPointerException e) {
            try {
                Thread.sleep(0L);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            if (e.getMessage() != null && e.getMessage().contains("isUserKeyUnlocked")) {
                EnsureManager.ensureNotReachHere(e, "getSharedPreferences isUserKeyUnlocked NullPointerException name=" + str + ",mode" + i);
            }
            return KevaSpAopHook.getSharedPreferences(application, str, i);
        }
    }

    public static ZoinMonitor a() {
        ZoinMonitor zoinMonitor = f48324c;
        return zoinMonitor == null ? new ZoinMonitor() : zoinMonitor;
    }

    public static void d(String str) {
        System.err.println("zoin:" + str);
        ((CopyOnWriteArrayList) f48325d).add(str);
    }

    public final SharedPreferences b() {
        if (this.f48326a == null) {
            this.f48326a = INVOKEVIRTUAL_com_bytedance_zoin_ZoinMonitor_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(SDKContext.f48320a, "zoin—sp", 0);
        }
        return this.f48326a;
    }

    public final ThreadPoolExecutor c() {
        if (this.b == null) {
            this.b = new PThreadPoolExecutor(0, Integer.MAX_VALUE, 100L, TimeUnit.MILLISECONDS, new SynchronousQueue(), new ThreadFactory() { // from class: com.bytedance.zoin.ZoinMonitor.1

                /* renamed from: a, reason: collision with root package name */
                public volatile int f48327a;

                @Override // java.util.concurrent.ThreadFactory
                public final Thread newThread(Runnable runnable) {
                    int i;
                    synchronized (this) {
                        i = this.f48327a;
                        this.f48327a = i + 1;
                    }
                    return new BPEAThread(runnable, "zoin-decode-" + i);
                }
            });
        }
        return this.b;
    }

    public void e(int i, String str, Map map) {
        d("Report module:" + str + ", type: " + i + ", map: " + map);
    }
}
package com.vega.launcher.start.schedule.tasks;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.security.android.aopcheck.PolarisFileWriterWrapper;
import com.bytedance.zoin.SDKContext;
import com.bytedance.zoin.Zoin;
import com.bytedance.zoin.ZoinMonitor;
import com.bytedance.zoin.decode.DecodeProcessor;
import com.bytedance.zoin.lib.LibModule;
import com.bytedance.zoin.model.AbstractModule;
import com.bytedance.zoin.model.ModuleManager;
import com.bytedance.zoin.utils.WorkDirManager;
import com.bytedance.zoin.utils.ZoinException;
import com.vega.launcher.ScaffoldApplication;
import com.vega.log.BLog;
import com.vega.report.ReportManagerWrapper;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public final class ZoinDecompressHelper {
    public static volatile boolean b;

    /* renamed from: a, reason: collision with root package name */
    public static final ZoinDecompressHelper f107635a = new ZoinDecompressHelper();

    /* renamed from: c, reason: collision with root package name */
    public static final ReentrantLock f107636c = new ReentrantLock();

    public static void a() {
        if (b) {
            return;
        }
        ReentrantLock reentrantLock = f107636c;
        reentrantLock.lock();
        try {
            if (b) {
                return;
            }
            f107635a.getClass();
            b();
            b = true;
        } finally {
            reentrantLock.unlock();
        }
    }

    public static void b() {
        Zoin zoinA = Zoin.a();
        ScaffoldApplication scaffoldApplicationA = InitTaskToolsKt.a();
        ZoinMonitor zoinMonitor = new ZoinMonitor() { // from class: com.vega.launcher.start.schedule.tasks.ZoinDecompressHelper$performInitialization$1
            @Override // com.bytedance.zoin.ZoinMonitor
            public final void e(int i, String str, Map map) {
                super.e(i, str, map);
                try {
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Map.Entry entry : map.entrySet()) {
                        if (((String) entry.getKey()) != null) {
                            linkedHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    JSONObject jSONObject = new JSONObject(linkedHashMap);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        Object objOpt = jSONObject.opt("lib_decode_duration");
                        Intrinsics.checkNotNullExpressionValue(objOpt, "");
                        jSONObject2.put("lib_decode_duration", objOpt);
                        jSONObject.remove("lib_decode_duration");
                    } catch (JSONException unused) {
                    }
                    try {
                        Object objOpt2 = jSONObject.opt("lib_install_duration");
                        Intrinsics.checkNotNullExpressionValue(objOpt2, "");
                        jSONObject2.put("lib_install_duration", objOpt2);
                        jSONObject.remove("lib_install_duration");
                    } catch (JSONException unused2) {
                    }
                    try {
                        Object objOpt3 = jSONObject.opt("lib_init_duration");
                        Intrinsics.checkNotNullExpressionValue(objOpt3, "");
                        jSONObject2.put("lib_init_duration", objOpt3);
                        jSONObject.remove("lib_init_duration");
                    } catch (JSONException unused3) {
                    }
                    try {
                        Object objOpt4 = jSONObject.opt("lib_lock_duration");
                        Intrinsics.checkNotNullExpressionValue(objOpt4, "");
                        jSONObject2.put("lib_lock_duration", objOpt4);
                        jSONObject.remove("lib_lock_duration");
                    } catch (JSONException unused4) {
                    }
                    try {
                        Object objOpt5 = jSONObject.opt("duration");
                        Intrinsics.checkNotNullExpressionValue(objOpt5, "");
                        jSONObject2.put("duration", objOpt5);
                        jSONObject.remove("duration");
                    } catch (JSONException unused5) {
                    }
                    BLog.i("zoin", "zoin onReport " + jSONObject2);
                    ReportManagerWrapper.INSTANCE.onEvent("zoin", jSONObject2);
                } catch (Throwable unused6) {
                }
            }
        };
        synchronized (zoinA) {
            if (!Zoin.b) {
                ZoinMonitor.f48324c = zoinMonitor;
                SDKContext.f48320a = scaffoldApplicationA;
                long jCurrentTimeMillis = System.currentTimeMillis();
                ZoinMonitor.a().getClass();
                ZoinMonitor.d("ZoinInitStart:" + jCurrentTimeMillis);
                ModuleManager.init(scaffoldApplicationA);
                WorkDirManager.a(ModuleManager.getTotalLibModuleInfos());
                Zoin.b = true;
                ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                String str = "ZoinInitEnd Status:" + Zoin.b + " isFirst:" + WorkDirManager.f48340a + " " + (System.currentTimeMillis() - jCurrentTimeMillis) + " ms";
                zoinMonitorA.getClass();
                ZoinMonitor.d(str);
            }
        }
        Zoin.a().b(new LibModule(0));
        Zoin zoinA2 = Zoin.a();
        zoinA2.getClass();
        ZoinMonitor.a().getClass();
        ZoinMonitor.d("zoin start decode module capcut force:false");
        AbstractModule abstractModule = zoinA2.f48322a.get("capcut");
        if (abstractModule == null) {
            new ZoinException("cant find module when decode async capcut");
            return;
        }
        abstractModule.doLoad(false);
        if (DecodeProcessor.hasProcessed) {
            ZoinMonitor.a().getClass();
            StringBuilder sb = new StringBuilder();
            Iterator it = ((CopyOnWriteArrayList) ZoinMonitor.f48325d).iterator();
            while (it.hasNext()) {
                sb.append((String) it.next());
                sb.append("\n");
            }
            ((CopyOnWriteArrayList) ZoinMonitor.f48325d).clear();
            String string = sb.toString();
            try {
                try {
                    WorkDirManager.e();
                    PolarisFileWriterWrapper polarisFileWriterWrapper = new PolarisFileWriterWrapper((File) new PolarisFileWrapper(WorkDirManager.b(), "zoin_load.log"), true);
                    polarisFileWriterWrapper.write(string);
                    polarisFileWriterWrapper.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } finally {
                WorkDirManager.f();
            }
        }
    }
}
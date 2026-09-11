package com.vega.launcher.lancet;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.bytedance.librarian.Librarian;
import com.bytedance.zoin.Zoin;
import com.bytedance.zoin.model.ModuleManager;
import com.lm.components.report.ReportManager;
import com.vega.launcher.start.schedule.tasks.ZoinDecompressHelper;
import com.vega.log.BLog;
import com.vega.performance.SoLoadStatistic;
import com.vega.start.MiddleBridgeLaunchOpt;
import com.vega.start.MiddleBridgeSoMonitor;
import com.vega.start.listener.StartStatusHolder;
import java.util.HashMap;
import kotlin.jvm.internal.Intrinsics;
import me.ele.lancet.base.Origin;

/* loaded from: classes13.dex */
public class SoLoadLancet {

    /* renamed from: a, reason: collision with root package name */
    public static Context f107369a;

    public static void a(String str) {
        MiddleBridgeSoMonitor.f130852a.getClass();
        Intrinsics.checkNotNullParameter(str, "");
        if (MiddleBridgeSoMonitor.f130853c.contains(str)) {
            MiddleBridgeLaunchOpt.f130848a.getClass();
            MiddleBridgeLaunchOpt.a();
            StartStatusHolder.f130864a.getClass();
            StartStatusHolder.d();
            if (!MiddleBridgeSoMonitor.b) {
                String stackTraceString = Log.getStackTraceString(new Throwable(str + '#' + StartStatusHolder.d()));
                Intrinsics.checkNotNullExpressionValue(stackTraceString, "");
                BLog.i("MiddleBridgeSoMonitor", stackTraceString);
            }
            if (StartStatusHolder.d() || !MiddleBridgeLaunchOpt.a()) {
                return;
            }
            HashMap map = new HashMap();
            String stackTraceString2 = Log.getStackTraceString(new Throwable(str));
            Intrinsics.checkNotNullExpressionValue(stackTraceString2, "");
            BLog.i("MiddleBridgeSoMonitor", "traceValue: " + stackTraceString2);
            map.put("so_trace", stackTraceString2);
            map.put("is_first_show", String.valueOf(StartStatusHolder.b));
            ReportManager.f61031c.f("so_load_exception", map);
        }
    }

    public static void load(String str) {
        BLog.i("SoLoadLancet", "librarian.load: " + str + " TheadName: " + Thread.currentThread().getName() + " tid:" + Thread.currentThread().getId());
        if (TextUtils.isEmpty(str)) {
            BLog.i("SoLoadLancet", "skip load empty lib");
        } else {
            Origin.callVoid();
        }
        a(str);
    }

    public static void loadLibrary(String str) {
        Zoin.a().getClass();
        if (ModuleManager.isSoDepsNeedsResolve(str)) {
            ZoinDecompressHelper.f107635a.getClass();
            ZoinDecompressHelper.a();
        }
        Context context = f107369a;
        if (context != null) {
            Librarian.loadLibraryForModule(str, context);
        } else {
            Origin.callVoid();
        }
        a(str);
    }

    public static void loadLibraryInternal(String str, boolean z, boolean z2, Context context) {
        System.currentTimeMillis();
        Origin.callVoid();
        SoLoadStatistic soLoadStatistic = SoLoadStatistic.f126244a;
        System.currentTimeMillis();
        soLoadStatistic.getClass();
        Intrinsics.checkNotNullParameter(str, "");
        a(str);
    }
}
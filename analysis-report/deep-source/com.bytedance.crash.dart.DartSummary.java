package com.bytedance.crash.dart;

import android.os.Process;
import com.bytedance.crash.CrashType;
import com.bytedance.crash.Global;
import com.bytedance.crash.dumper.MemoryInfo;
import com.bytedance.crash.dumper.Scraps;
import com.bytedance.crash.entity.CrashBody;
import com.bytedance.crash.entity.Header;
import com.bytedance.crash.monitor.AppMonitor;
import com.bytedance.crash.monitor.AppMonitorConfigService;
import com.bytedance.crash.service.ICrashBodyExtensionDumper;
import com.bytedance.crash.util.App;
import com.bytedance.crash.util.JSONUtils;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes25.dex */
public class DartSummary {
    public static CrashBody a(AppMonitor appMonitor, String str, long j, String str2, Map map, Map map2) {
        CrashBody crashBody = new CrashBody();
        try {
            ICrashBodyExtensionDumper iCrashBodyExtensionDumper = appMonitor.l;
            if (iCrashBodyExtensionDumper != null) {
                JSONUtils.d(crashBody.f38668a, iCrashBodyExtensionDumper.c(CrashType.DART));
            }
            crashBody.b("is_dart", 1);
            crashBody.b("data", str2);
            crashBody.b("crash_time", Long.valueOf(j));
            crashBody.b("crash_thread_name", str);
            crashBody.b("process_name", App.c());
            crashBody.b("app_start_time", Long.valueOf(Global.b()));
            crashBody.b("upload_scene", "direct");
            MemoryInfo.b(crashBody.f38668a, null);
            Scraps.pushTo(crashBody.f38668a);
            if (AppMonitorConfigService.i && map != null) {
                JSONObject jSONObject = new JSONObject();
                JSONUtils.j(map, jSONObject);
                if (map2 != null) {
                    JSONUtils.j(map2, jSONObject);
                }
                crashBody.b("custom", jSONObject);
            }
        } catch (Throwable unused) {
        }
        Header headerB = Header.b(appMonitor, j, CrashType.DART, Process.myPid());
        crashBody.b("header", headerB.f38670a);
        crashBody.b = headerB;
        return crashBody;
    }
}
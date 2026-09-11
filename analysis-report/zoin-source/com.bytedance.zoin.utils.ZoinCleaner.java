package com.bytedance.zoin.utils;

import android.app.Application;
import com.vega.draft.monitor.DraftMonitorLancet;
import com.vega.draft.monitor.MonitorExtKt;
import com.vega.launcher.lancet.FileDirLancet;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.io.File;

/* loaded from: classes5.dex */
public class ZoinCleaner {
    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_draft_monitor_DraftMonitorLancet_delete(File file) {
        try {
            if (MonitorExtKt.e && (file instanceof File)) {
                String absolutePath = file.getAbsolutePath();
                MonitorExtKt.c(file);
                MonitorExtKt.b("DraftMonitorLancet#delete", absolutePath);
                if (!DraftMonitorLancet.a(absolutePath, "delete")) {
                    return false;
                }
            }
        } catch (Throwable unused) {
        }
        return INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_libfiles_files_hook_FileHook_delete(file);
    }

    public static File INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_launcher_lancet_FileDirLancet_getFilesDir(Application application) {
        if (!PerformanceManagerHelper.ipcOptEnable) {
            return application.getFilesDir();
        }
        if (FileDirLancet.f107351a == null) {
            FileDirLancet.f107351a = application.getFilesDir();
        }
        return FileDirLancet.f107351a;
    }

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_libfiles_files_hook_FileHook_delete(File file) {
        FileAssist fileAssist = FileAssist.INSTANCE;
        if (!fileAssist.isEnable()) {
            return file.delete();
        }
        if (PerformanceManagerHelper.blogEnable) {
            BLog.i("FileHook", "hook_delete");
        }
        if (!(file instanceof File)) {
            return false;
        }
        fileAssist.awaitInspect(file);
        if (FileHook.resolvePath(file)) {
            return file.delete();
        }
        return false;
    }

    public static void a(File file) {
        File[] fileArrListFiles;
        if (file == null) {
            return;
        }
        if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                a(file2);
            }
        }
        INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_draft_monitor_DraftMonitorLancet_delete(file);
    }
}
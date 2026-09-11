package com.bytedance.zoin.utils;

import com.bytedance.zoin.ZoinMonitor;
import com.vega.draft.monitor.DraftMonitorLancet;
import com.vega.draft.monitor.MonitorExtKt;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;

/* loaded from: classes29.dex */
public class FileUtils {
    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_utils_FileUtils_com_vega_draft_monitor_DraftMonitorLancet_delete(File file) {
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
        return INVOKEVIRTUAL_com_bytedance_zoin_utils_FileUtils_com_vega_libfiles_files_hook_FileHook_delete(file);
    }

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_utils_FileUtils_com_vega_libfiles_files_hook_FileHook_delete(File file) {
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

    public static void a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException unused) {
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("Failed to close resource");
        }
    }

    public static boolean b(File file) {
        if (!file.exists()) {
            file.mkdirs();
        }
        boolean zCanWrite = file.canWrite();
        boolean zCanRead = file.canRead();
        if (!zCanWrite) {
            zCanWrite = file.setWritable(true);
        }
        return (zCanRead || file.setReadable(true)) && zCanWrite;
    }
}
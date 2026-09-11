package com.bytedance.zoin.utils;

import android.app.Application;
import com.vega.launcher.lancet.FileDirLancet;
import com.vega.performance.PerformanceManagerHelper;
import java.io.File;

/* loaded from: classes23.dex */
public final class IoUtil {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f48336a;

    public static File INVOKEVIRTUAL_com_bytedance_zoin_utils_IoUtil_com_vega_launcher_lancet_FileDirLancet_getFilesDir(Application application) {
        if (!PerformanceManagerHelper.ipcOptEnable) {
            return application.getFilesDir();
        }
        if (FileDirLancet.f107351a == null) {
            FileDirLancet.f107351a = application.getFilesDir();
        }
        return FileDirLancet.f107351a;
    }
}
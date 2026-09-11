package com.bytedance.zoin;

import com.GlobalProxyLancet;
import com.bytedance.zoin.model.ZoinBuildFileInfo;
import com.bytedance.zoin.utils.ExceptionUtils;

/* loaded from: classes40.dex */
public class ZoinNative {
    public static volatile boolean isLoaded;

    public static boolean init() {
        ZoinMonitor zoinMonitorA = ZoinMonitor.a();
        try {
            zoinMonitorA.getClass();
            GlobalProxyLancet.com_vega_launcher_lancet_SoLoadLancet_loadLibrary("zoin");
            int iNInit = nInit(false, false, false);
            isLoaded = iNInit == 1;
            ZoinMonitor.d("ZoinNative init " + iNInit);
        } catch (UnsatisfiedLinkError e) {
            isLoaded = false;
            String str = "Fail to load " + ExceptionUtils.a(e);
            zoinMonitorA.getClass();
            ZoinMonitor.d(str);
        }
        return isLoaded;
    }

    public static boolean isIsLoaded() {
        return isLoaded;
    }

    public static native long nCalculateFileCRC(String str);

    public static native int nDecodeLibs(String str, String str2, String str3, long j, long j2, int i, ZoinBuildFileInfo[] zoinBuildFileInfoArr, boolean z, boolean z2);

    public static native String nDumpDebugLogs();

    public static native int nInit(boolean z, boolean z2, boolean z3);
}
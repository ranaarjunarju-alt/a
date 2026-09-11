package com.bytedance.zoin.utils;

import com.bytedance.security.android.aopcheck.PolarisFileInputStreamWrapper;
import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.zoin.ZoinMonitor;
import com.bytedance.zoin.model.ZoinBuildFileInfo;
import java.io.File;
import java.util.List;
import java.util.zip.CRC32;

/* loaded from: classes28.dex */
public class VerifyUtils {
    public static long a(File file) {
        if (!file.exists()) {
            ZoinMonitor zoinMonitorA = ZoinMonitor.a();
            String str = "File is not exist: " + file.getPath();
            zoinMonitorA.getClass();
            ZoinMonitor.d(str);
            return 0L;
        }
        try {
            CRC32 crc32 = new CRC32();
            PolarisFileInputStreamWrapper polarisFileInputStreamWrapper = new PolarisFileInputStreamWrapper(file);
            try {
                byte[] bArr = new byte[32768];
                while (true) {
                    int i = polarisFileInputStreamWrapper.read(bArr);
                    if (i == -1) {
                        FileUtils.a(polarisFileInputStreamWrapper);
                        return crc32.getValue() + file.length();
                    }
                    crc32.update(bArr, 0, i);
                }
            } catch (Throwable th) {
                FileUtils.a(polarisFileInputStreamWrapper);
                throw th;
            }
        } catch (Exception unused) {
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("Fail to get check num " + file);
            return 0L;
        }
    }

    public static boolean b(File file, List<ZoinBuildFileInfo> list) {
        for (ZoinBuildFileInfo zoinBuildFileInfo : list) {
            long jA = a(new PolarisFileWrapper(file, zoinBuildFileInfo.name));
            if (jA != zoinBuildFileInfo.checkNumber) {
                ZoinMonitor.a().getClass();
                ZoinMonitor.d("VerifyError:" + zoinBuildFileInfo + ", generated:" + jA);
                return false;
            }
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("VerifySuccess:" + zoinBuildFileInfo);
        }
        return true;
    }
}
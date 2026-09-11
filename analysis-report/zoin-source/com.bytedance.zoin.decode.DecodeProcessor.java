package com.bytedance.zoin.decode;

import android.app.Application;
import android.text.TextUtils;
import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.security.android.aopcheck.PolarisZipFileWrapper;
import com.bytedance.zoin.SDKContext;
import com.bytedance.zoin.ZoinMonitor;
import com.bytedance.zoin.ZoinNative;
import com.bytedance.zoin.decode.DecodeProcessor;
import com.bytedance.zoin.model.ZoinBlockInfo;
import com.bytedance.zoin.model.ZoinBuildFileInfo;
import com.bytedance.zoin.utils.AbiHelper;
import com.bytedance.zoin.utils.VerifyUtils;
import com.bytedance.zoin.utils.ZoinException;
import com.vega.draft.monitor.DraftMonitorLancet;
import com.vega.draft.monitor.MonitorExtKt;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.zip.ZipEntry;

/* loaded from: classes13.dex */
public class DecodeProcessor {
    public static volatile boolean hasProcessed;

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_draft_monitor_DraftMonitorLancet_delete(File file) {
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
        return INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_libfiles_files_hook_FileHook_delete(file);
    }

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_libfiles_files_hook_FileHook_delete(File file) {
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

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_libfiles_files_hook_FileHook_renameTo(File file, File file2) {
        if (FileAssist.INSTANCE.isEnable()) {
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("FileHook", "hook renameTo");
            }
            if (file instanceof File) {
                if (PerformanceManagerHelper.blogEnable) {
                    BLog.i("FileHook", "from: " + file.getAbsolutePath() + " renameTo: " + file2.getAbsolutePath());
                }
                if (FileHook.isInMonitoredAppDir(file.getAbsolutePath())) {
                    FileHook.collectStack(file, true, true);
                }
            }
        }
        return file.renameTo(file2);
    }

    public static int decodeAndVerify(File file, List<ZoinBuildFileInfo> list, List<ZoinBlockInfo> list2, boolean z) {
        String path;
        String strFindLibrary;
        hasProcessed = true;
        HashMap map = new HashMap();
        Collections.sort(list2, new Comparator() { // from class: X.4oz
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return DecodeProcessor.lambda$decodeAndVerify$0((ZoinBlockInfo) obj, (ZoinBlockInfo) obj2);
            }
        });
        if (!ZoinNative.isIsLoaded()) {
            ZoinNative.init();
        }
        for (final ZoinBlockInfo zoinBlockInfo : list2) {
            final ArrayList arrayList = new ArrayList();
            final int i = zoinBlockInfo.totalDecompressedLength;
            for (ZoinBuildFileInfo zoinBuildFileInfo : list) {
                if (zoinBuildFileInfo.compressedName.equals(zoinBlockInfo.blockCompressedName)) {
                    arrayList.add(zoinBuildFileInfo);
                }
            }
            if (arrayList.size() != 0) {
                PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(SDKContext.f48320a.getApplicationInfo().nativeLibraryDir);
                PolarisFileWrapper polarisFileWrapper2 = new PolarisFileWrapper(polarisFileWrapper, zoinBlockInfo.blockName);
                PolarisFileWrapper polarisFileWrapper3 = AbiHelper.e;
                if (polarisFileWrapper3 == null) {
                    ZoinMonitor.a().getClass();
                    ZoinMonitor.d("AbiHelper not exist");
                } else {
                    ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                    String str = "Old LibDIR:" + polarisFileWrapper3.getPath();
                    zoinMonitorA.getClass();
                    ZoinMonitor.d(str);
                }
                ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
                String str2 = "New LibDIR:" + polarisFileWrapper.getPath() + " " + polarisFileWrapper2.exists();
                zoinMonitorA2.getClass();
                ZoinMonitor.d(str2);
                String str3 = "lib/" + AbiHelper.d(SDKContext.f48320a) + "/" + zoinBlockInfo.blockName;
                Application application = SDKContext.f48320a;
                PolarisFileWrapper polarisFileWrapper4 = new PolarisFileWrapper(application.getApplicationInfo().sourceDir);
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(polarisFileWrapper4);
                ZoinMonitor.a().getClass();
                ZoinMonitor.d("base apk:" + polarisFileWrapper4);
                String[] strArr = application.getApplicationInfo().splitSourceDirs;
                if (strArr == null || strArr.length == 0) {
                    ZoinMonitor.a().getClass();
                    ZoinMonitor.d("list files");
                    File parentFile = polarisFileWrapper4.getParentFile();
                    parentFile.setReadable(true);
                    File[] fileArrListFiles = parentFile.listFiles();
                    if (fileArrListFiles != null && fileArrListFiles.length > 0) {
                        for (File file2 : fileArrListFiles) {
                            ZoinMonitor zoinMonitorA3 = ZoinMonitor.a();
                            String str4 = "splitDir:" + file2.getAbsolutePath();
                            zoinMonitorA3.getClass();
                            ZoinMonitor.d(str4);
                            if (file2.isFile() && file2.getName().endsWith(".apk")) {
                                arrayList2.add(file2);
                            }
                        }
                    }
                } else {
                    for (String str5 : strArr) {
                        ZoinMonitor.a().getClass();
                        ZoinMonitor.d("splitDir:" + str5);
                        arrayList2.add(new PolarisFileWrapper(str5));
                    }
                }
                Iterator it = arrayList2.iterator();
                while (true) {
                    path = null;
                    if (!it.hasNext()) {
                        str3 = null;
                        break;
                    }
                    File file3 = (File) it.next();
                    PolarisZipFileWrapper polarisZipFileWrapper = new PolarisZipFileWrapper(file3);
                    ZipEntry entry = polarisZipFileWrapper.getEntry(str3);
                    polarisZipFileWrapper.close();
                    if (entry != null && file3 != null) {
                        path = file3.getPath();
                        break;
                    }
                }
                if (polarisFileWrapper3 == null || !polarisFileWrapper3.getPath().equals(polarisFileWrapper.getPath()) || !polarisFileWrapper2.exists() || (strFindLibrary = polarisFileWrapper2.getPath()) == null) {
                    BaseDexClassLoader baseDexClassLoader = (BaseDexClassLoader) SDKContext.f48320a.getClassLoader();
                    String str6 = zoinBlockInfo.blockName;
                    strFindLibrary = baseDexClassLoader.findLibrary(str6.substring(3, str6.length() - 3));
                }
                ZoinMonitor.a().getClass();
                ZoinMonitor.d("MainPath: " + strFindLibrary + " " + path + " " + str3);
                if (TextUtils.isEmpty(strFindLibrary) && TextUtils.isEmpty(path)) {
                    return 4;
                }
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    PolarisFileWrapper polarisFileWrapper5 = new PolarisFileWrapper(file, ((ZoinBuildFileInfo) it2.next()).name + ".temp");
                    if (polarisFileWrapper5.exists()) {
                        boolean zINVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_draft_monitor_DraftMonitorLancet_delete = INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_draft_monitor_DraftMonitorLancet_delete(polarisFileWrapper5);
                        ZoinMonitor.a().getClass();
                        ZoinMonitor.d("DeleteOldTemp " + polarisFileWrapper5 + " " + zINVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_draft_monitor_DraftMonitorLancet_delete);
                    }
                }
                final String str7 = strFindLibrary;
                final String str8 = path;
                final String str9 = str3;
                map.put(zoinBlockInfo, ZoinMonitor.a().c().submit(new Callable() { // from class: X.4oy
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        return DecodeProcessor.lambda$decodeAndVerify$1(zoinBlockInfo, str7, str8, str9, i, arrayList);
                    }
                }));
            }
        }
        boolean z2 = false;
        int iIntValue = 0;
        for (Map.Entry entry2 : map.entrySet()) {
            iIntValue = ((Integer) ((Future) entry2.getValue()).get()).intValue();
            if (iIntValue != 0) {
                ZoinMonitor zoinMonitorA4 = ZoinMonitor.a();
                String str10 = "Child thread decode " + entry2.getKey() + " Failed " + iIntValue;
                zoinMonitorA4.getClass();
                ZoinMonitor.d(str10);
                z2 = true;
            }
        }
        if (!z2) {
            return 0;
        }
        throw new ZoinException("DecodeProcessor Error " + iIntValue);
    }

    public static /* synthetic */ int lambda$decodeAndVerify$0(ZoinBlockInfo zoinBlockInfo, ZoinBlockInfo zoinBlockInfo2) {
        return (int) ((zoinBlockInfo2.blockEndOffset - zoinBlockInfo2.blockBeginOffset) - (zoinBlockInfo.blockEndOffset - zoinBlockInfo.blockBeginOffset));
    }

    public static /* synthetic */ Integer lambda$decodeAndVerify$1(ZoinBlockInfo zoinBlockInfo, String str, String str2, String str3, int i, ArrayList arrayList) {
        ZoinMonitor zoinMonitorA = ZoinMonitor.a();
        String str4 = "Start decode " + zoinBlockInfo.blockCompressedName;
        zoinMonitorA.getClass();
        ZoinMonitor.d(str4);
        long j = zoinBlockInfo.blockBeginOffset;
        long j2 = zoinBlockInfo.blockEndOffset;
        ZoinBuildFileInfo[] zoinBuildFileInfoArr = (ZoinBuildFileInfo[]) arrayList.toArray(new ZoinBuildFileInfo[0]);
        ZoinMonitor.a().getClass();
        int iNDecodeLibs = ZoinNative.nDecodeLibs(str, str2, str3, j, j2, i, zoinBuildFileInfoArr, true, false);
        ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
        String str5 = Thread.currentThread().getName() + " nDecode:" + iNDecodeLibs;
        zoinMonitorA2.getClass();
        ZoinMonitor.d(str5);
        ZoinMonitor zoinMonitorA3 = ZoinMonitor.a();
        String strNDumpDebugLogs = ZoinNative.nDumpDebugLogs();
        zoinMonitorA3.getClass();
        ZoinMonitor.d(strNDumpDebugLogs);
        return Integer.valueOf(iNDecodeLibs);
    }

    public static void monitorDecompressEnd(String str) {
        ZoinMonitor.a().getClass();
        ZoinMonitor.d("decompress end " + str);
    }

    public static void monitorDecompressStart(String str) {
        ZoinMonitor.a().getClass();
        ZoinMonitor.d("decompress start " + str);
    }

    public static int renameTempFiles(File file, List<ZoinBuildFileInfo> list) {
        for (ZoinBuildFileInfo zoinBuildFileInfo : list) {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(file, zoinBuildFileInfo.name + ".temp");
            if (polarisFileWrapper.exists()) {
                PolarisFileWrapper polarisFileWrapper2 = new PolarisFileWrapper(file, zoinBuildFileInfo.name);
                if (!INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_libfiles_files_hook_FileHook_renameTo(polarisFileWrapper, polarisFileWrapper2)) {
                    ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                    String str = "RenameTempFileError " + polarisFileWrapper.getPath() + " " + polarisFileWrapper2.getPath();
                    zoinMonitorA.getClass();
                    ZoinMonitor.d(str);
                    return 3;
                }
            }
        }
        return 0;
    }

    public static int verifyTempFiles(File file, List<ZoinBuildFileInfo> list) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (ZoinBuildFileInfo zoinBuildFileInfo : list) {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(file, zoinBuildFileInfo.name + ".temp");
            long jA = VerifyUtils.a(polarisFileWrapper);
            if (jA != zoinBuildFileInfo.checkNumber) {
                ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                String str = "VerifyTempFile Error:" + zoinBuildFileInfo + ", generated:" + jA + " " + polarisFileWrapper + " " + polarisFileWrapper.length();
                zoinMonitorA.getClass();
                ZoinMonitor.d(str);
                if (ZoinNative.isIsLoaded()) {
                    long jNCalculateFileCRC = ZoinNative.nCalculateFileCRC(polarisFileWrapper.getPath());
                    ZoinMonitor.a().getClass();
                    ZoinMonitor.d("NativeCrc " + jNCalculateFileCRC);
                }
                arrayList.add(polarisFileWrapper);
                i = 2;
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            File file2 = (File) it.next();
            ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
            String str2 = "delete temp " + file2 + " " + INVOKEVIRTUAL_com_bytedance_zoin_decode_DecodeProcessor_com_vega_draft_monitor_DraftMonitorLancet_delete(file2);
            zoinMonitorA2.getClass();
            ZoinMonitor.d(str2);
        }
        return i;
    }
}
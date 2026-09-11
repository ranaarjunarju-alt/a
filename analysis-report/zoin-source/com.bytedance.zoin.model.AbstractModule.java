package com.bytedance.zoin.model;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.zoin.SDKContext;
import com.bytedance.zoin.ZoinMonitor;
import com.bytedance.zoin.ZoinResult;
import com.bytedance.zoin.utils.ExceptionUtils;
import com.bytedance.zoin.utils.ZoinException;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes24.dex */
public abstract class AbstractModule {
    public boolean moduleInited;
    public String moduleName;
    public int moduleType;
    public boolean preFallocate;
    public int maxRetryCounts = 2;
    public Map<String, Object> recordMap = new HashMap();
    public List<Throwable> throwableList = new ArrayList();

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_model_AbstractModule_com_vega_libfiles_files_hook_FileHook_renameTo(File file, File file2) {
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

    public boolean checkDecompressedFiles() {
        return true;
    }

    public abstract ZoinResult decode(boolean z);

    public ZoinResult doLoad(boolean z) {
        ZoinResult zoinResultDecode;
        boolean z2;
        synchronized (this) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (isDecoded()) {
                zoinResultDecode = install();
                z2 = !zoinResultDecode.a();
                ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                String str = "isDecoded:true;result:" + zoinResultDecode.a();
                zoinMonitorA.getClass();
                ZoinMonitor.d(str);
            } else {
                zoinResultDecode = decode(z);
                if (zoinResultDecode.a()) {
                    zoinResultDecode = install();
                }
                ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
                String str2 = "isDecoded:false;result:" + zoinResultDecode.a();
                zoinMonitorA2.getClass();
                ZoinMonitor.d(str2);
                z2 = true;
            }
            long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("needReport:" + z2);
            Map<String, Object> reportMap = getReportMap();
            reportMap.put("key_status", Integer.valueOf(zoinResultDecode.f48329a));
            String strE = SDKContext.e(this.moduleName, "is_first_load");
            reportMap.put("is_first_load", Boolean.valueOf(SDKContext.b(strE, true)));
            reportMap.put("key_module_name", this.moduleName);
            reportMap.put("duration", Long.valueOf(jCurrentTimeMillis2));
            SDKContext.f(strE, false);
            ZoinMonitor.a().e(this.moduleType, this.moduleName, reportMap);
            ZoinMonitor zoinMonitorA3 = ZoinMonitor.a();
            String str3 = "Report module:" + this.moduleName + ", type: " + this.moduleType + ", map: " + reportMap;
            zoinMonitorA3.getClass();
            ZoinMonitor.d(str3);
            ZoinMonitor zoinMonitorA4 = ZoinMonitor.a();
            String str4 = "result:" + zoinResultDecode.f48329a + ";module type:" + this.moduleType + ";duration:" + jCurrentTimeMillis2;
            zoinMonitorA4.getClass();
            ZoinMonitor.d(str4);
        }
        return zoinResultDecode;
    }

    public ZoinResult doRetryLoad() {
        ZoinResult zoinResultDecode;
        synchronized (this) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (isDecoded()) {
                zoinResultDecode = install();
                if (!zoinResultDecode.a()) {
                }
            } else {
                zoinResultDecode = decode(false);
                if (!zoinResultDecode.a()) {
                    processWhenFailedFirst();
                    this.recordMap.put("is_retry_loaded", Boolean.TRUE);
                    zoinResultDecode = decode(false);
                }
                if (zoinResultDecode.a()) {
                    zoinResultDecode = install();
                }
            }
            Map<String, Object> reportMap = getReportMap();
            reportMap.put("key_status", Integer.valueOf(zoinResultDecode.f48329a));
            String strE = SDKContext.e(this.moduleName, "is_first_load");
            reportMap.put("is_first_load", Boolean.valueOf(SDKContext.b(strE, true)));
            reportMap.put("duration", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
            reportMap.put("key_module_name", this.moduleName);
            SDKContext.f(strE, false);
            ZoinMonitor.a().e(this.moduleType, this.moduleName, reportMap);
        }
        return zoinResultDecode;
    }

    public abstract void doWhenUpdateApk();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AbstractModule)) {
            return false;
        }
        AbstractModule abstractModule = (AbstractModule) obj;
        return this.moduleName.equals(abstractModule.moduleName) && this.moduleType == abstractModule.moduleType;
    }

    public abstract AbstractModule findByFileName(String str);

    public Map<String, Object> getRecordMap() {
        return this.recordMap;
    }

    public Map<String, Object> getReportMap() {
        HashMap map = new HashMap();
        List<Throwable> list = this.throwableList;
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator<Throwable> it = this.throwableList.iterator();
            while (it.hasNext()) {
                arrayList.add(ExceptionUtils.a(it.next()));
            }
            map.put("throwable_list", arrayList);
        }
        map.putAll(this.recordMap);
        return map;
    }

    public int hashCode() {
        String str = this.moduleName;
        int iHashCode = str != null ? str.hashCode() : 0;
        int i = this.moduleType;
        return (iHashCode * 31) + (i ^ (i >>> 32));
    }

    public void init() {
    }

    public abstract ZoinResult install();

    public abstract boolean isDecoded();

    public boolean isModuleInited() {
        return this.moduleInited;
    }

    public int preFallocate(List<ZoinBuildFileInfo> list) {
        return 0;
    }

    public void processWhenFailedFirst() {
    }

    public void renameTempFiles(File file, List<ZoinBuildFileInfo> list) {
        for (ZoinBuildFileInfo zoinBuildFileInfo : list) {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(file, zoinBuildFileInfo.name + ".temp");
            if (polarisFileWrapper.exists()) {
                PolarisFileWrapper polarisFileWrapper2 = new PolarisFileWrapper(file, zoinBuildFileInfo.name);
                if (!INVOKEVIRTUAL_com_bytedance_zoin_model_AbstractModule_com_vega_libfiles_files_hook_FileHook_renameTo(polarisFileWrapper, polarisFileWrapper2)) {
                    throw new ZoinException("rename files failed " + polarisFileWrapper.getPath() + " " + polarisFileWrapper2.getPath());
                }
            }
        }
    }

    public void reset() {
    }

    public abstract void setDecoded();

    public abstract void setModuleInfo(AbstractModuleInfo abstractModuleInfo);

    public abstract void setNotDecoded();
}
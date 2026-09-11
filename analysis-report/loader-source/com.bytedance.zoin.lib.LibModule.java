package com.bytedance.zoin.lib;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.zoin.SDKContext;
import com.bytedance.zoin.ZoinMonitor;
import com.bytedance.zoin.ZoinResult;
import com.bytedance.zoin.decode.DecodeProcessor;
import com.bytedance.zoin.lib.inception.Inception;
import com.bytedance.zoin.model.AbstractModule;
import com.bytedance.zoin.model.AbstractModuleInfo;
import com.bytedance.zoin.model.LibDependency;
import com.bytedance.zoin.model.LibModuleInfo;
import com.bytedance.zoin.model.ModuleManager;
import com.bytedance.zoin.model.ZoinBuildFileInfo;
import com.bytedance.zoin.utils.FileUtils;
import com.bytedance.zoin.utils.Locker;
import com.bytedance.zoin.utils.VerifyUtils;
import com.bytedance.zoin.utils.WorkDirManager;
import com.bytedance.zoin.utils.ZoinException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes29.dex */
public class LibModule extends AbstractModule {

    /* renamed from: a, reason: collision with root package name */
    public final boolean f48330a;
    public final boolean b;

    /* renamed from: c, reason: collision with root package name */
    public final boolean f48331c;

    /* renamed from: d, reason: collision with root package name */
    public LibModuleInfo f48332d;
    public LibModuleInfo e;

    public LibModule() {
        this.b = true;
    }

    public LibModule(int i) {
        this.b = true;
        this.moduleName = "capcut";
        this.f48330a = true;
        this.b = true;
        this.f48331c = false;
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final boolean checkDecompressedFiles() {
        if (VerifyUtils.b(WorkDirManager.b(), this.e.curAbiInfo.libFileInfoList)) {
            ZoinMonitor zoinMonitorA = ZoinMonitor.a();
            String str = this.moduleName + " DecompressedFilesPrepared";
            zoinMonitorA.getClass();
            ZoinMonitor.d(str);
            setDecoded();
            return true;
        }
        ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
        String str2 = this.moduleName + " DecompressedFilesChanged";
        zoinMonitorA2.getClass();
        ZoinMonitor.d(str2);
        setNotDecoded();
        return false;
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final ZoinResult decode(boolean z) throws IOException {
        if (!this.moduleInited) {
            return new ZoinResult(6, new ZoinException("prefallocate failed, no disk space"));
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        WorkDirManager.e();
        PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(WorkDirManager.b(), this.moduleName + ".zoin.lib.lk");
        Locker locker = new Locker(polarisFileWrapper);
        ZoinMonitor zoinMonitorA = ZoinMonitor.a();
        if (!polarisFileWrapper.exists()) {
            File parentFile = polarisFileWrapper.getParentFile();
            FileUtils.b(parentFile);
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("Ensure lock permission " + parentFile);
            polarisFileWrapper.createNewFile();
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("Create LockFile " + polarisFileWrapper);
        }
        locker.f48337a = new RandomAccessFile(polarisFileWrapper, "rw");
        String str = "RAF " + polarisFileWrapper.getPath() + " opened";
        zoinMonitorA.getClass();
        ZoinMonitor.d(str);
        try {
            locker.f48338c = locker.f48337a.getChannel();
            try {
                ZoinMonitor.d("Blocking on lock " + polarisFileWrapper.getCanonicalPath());
                locker.b = locker.f48338c.lock();
                ZoinMonitor.d("Acquired on lock " + polarisFileWrapper.getCanonicalPath());
                this.recordMap.put("lib_lock_duration", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
                if (!z && VerifyUtils.b(WorkDirManager.b(), this.f48332d.curAbiInfo.libFileInfoList)) {
                    ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
                    String str2 = this.moduleName + "Locker DecodeSuccess";
                    zoinMonitorA2.getClass();
                    ZoinMonitor.d(str2);
                    setDecoded();
                    WorkDirManager.f();
                    return ZoinResult.f48328c;
                }
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                File fileB = WorkDirManager.b();
                LibModuleInfo.AbiLibInfo abiLibInfo = this.f48332d.curAbiInfo;
                int iDecodeAndVerify = DecodeProcessor.decodeAndVerify(fileB, abiLibInfo.libFileInfoList, abiLibInfo.blockInfoList, !this.preFallocate);
                this.recordMap.put("lib_decode_duration", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis2));
                this.recordMap.put("key_decode_status", Integer.valueOf(iDecodeAndVerify));
                if (iDecodeAndVerify != 0) {
                    WorkDirManager.f();
                    throw new ZoinException("Lib decode failed " + iDecodeAndVerify);
                }
                setDecoded();
                FileLock fileLock = locker.b;
                if (fileLock != null) {
                    try {
                        fileLock.release();
                    } catch (IOException unused) {
                    }
                }
                ZoinMonitor zoinMonitorA3 = ZoinMonitor.a();
                String str3 = "Released lock " + locker.f48339d.getPath();
                zoinMonitorA3.getClass();
                ZoinMonitor.d(str3);
                FileUtils.a(locker.f48338c);
                FileUtils.a(locker.f48337a);
                WorkDirManager.f();
                return ZoinResult.f48328c;
            } catch (IOException e) {
                FileUtils.a(locker.f48338c);
                throw e;
            }
        } catch (IOException e2) {
            FileUtils.a(locker.f48337a);
            throw e2;
        }
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final void doWhenUpdateApk() {
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final AbstractModule findByFileName(String str) {
        Iterator<LibDependency> it = this.f48332d.curAbiInfo.libDependencyList.iterator();
        while (it.hasNext()) {
            if (it.next().libName.equals(str)) {
                return this;
            }
        }
        return null;
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final void init() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        super.init();
        for (ZoinBuildFileInfo zoinBuildFileInfo : this.f48332d.curAbiInfo.libFileInfoList) {
            zoinBuildFileInfo.decompressPath = new PolarisFileWrapper(WorkDirManager.b(), zoinBuildFileInfo.name + ".temp").getPath();
        }
        if (!WorkDirManager.f48340a && !isDecoded()) {
            Iterator<ZoinBuildFileInfo> it = this.f48332d.curAbiInfo.libFileInfoList.iterator();
            while (it.hasNext()) {
                ZoinBuildFileInfo next = it.next();
                long jA = VerifyUtils.a(new PolarisFileWrapper(WorkDirManager.b(), next.name));
                if (jA == next.checkNumber) {
                    ZoinMonitor.a().getClass();
                    ZoinMonitor.d("Ignore " + next);
                    it.remove();
                } else {
                    ZoinMonitor.a().getClass();
                    ZoinMonitor.d("StillNeedDecompress " + next + " " + jA);
                }
            }
        }
        if (this.f48331c) {
            if (isDecoded()) {
                this.moduleInited = true;
            } else {
                long jCurrentTimeMillis2 = System.currentTimeMillis();
                int iPreFallocate = preFallocate(this.f48332d.curAbiInfo.libFileInfoList);
                ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                String str = "zoin fallocate use " + (System.currentTimeMillis() - jCurrentTimeMillis2) + " ms " + iPreFallocate;
                zoinMonitorA.getClass();
                ZoinMonitor.d(str);
                if (iPreFallocate == 0) {
                    this.preFallocate = true;
                    this.moduleInited = true;
                } else {
                    this.moduleInited = false;
                    HashMap map = new HashMap();
                    map.put("fallocate_code", Integer.valueOf(iPreFallocate));
                    map.put("key_module_name", this.moduleName);
                    ZoinMonitor.a().e(101, this.moduleName, map);
                }
            }
            if (!this.moduleInited) {
                return;
            }
        } else {
            this.moduleInited = true;
        }
        if (this.f48330a) {
            long jCurrentTimeMillis3 = System.currentTimeMillis();
            boolean zA = Inception.a(SDKContext.f48320a, SDKContext.a(SDKContext.f48320a.getClassLoader()), WorkDirManager.b().getPath(), this.b, this.f48330a);
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("InjectClassload:invoked " + zA);
            ModuleManager.setDependencyList(this.f48332d.curAbiInfo.libDependencyList);
            this.recordMap.put("lib_install_duration", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis3));
            this.recordMap.put("lib_hook_duration", Long.valueOf(System.currentTimeMillis() - System.currentTimeMillis()));
        }
        this.recordMap.put("lib_init_duration", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final ZoinResult install() {
        if (!this.f48330a) {
            ClassLoader classLoaderA = SDKContext.a(SDKContext.f48320a.getClassLoader());
            boolean zA = Inception.a(SDKContext.f48320a, classLoaderA, WorkDirManager.b().getPath(), this.b, this.f48330a);
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("ClassloaderInject:" + classLoaderA + " invoked " + zA);
            if (!zA) {
                return new ZoinResult(8, new ZoinException("cant inception classloader"));
            }
        }
        return ZoinResult.f48328c;
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final boolean isDecoded() {
        return SDKContext.b(SDKContext.e(this.moduleName, "key.lib.decode.finished"), false);
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final void reset() {
        super.reset();
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final void setDecoded() {
        SDKContext.f(SDKContext.e(this.moduleName, "key.lib.decode.finished"), true);
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final void setModuleInfo(AbstractModuleInfo abstractModuleInfo) {
        LibModuleInfo libModuleInfo = (LibModuleInfo) abstractModuleInfo;
        this.f48332d = libModuleInfo;
        this.e = libModuleInfo;
    }

    @Override // com.bytedance.zoin.model.AbstractModule
    public final void setNotDecoded() {
        SDKContext.f(SDKContext.e(this.moduleName, "key.lib.decode.finished"), false);
    }
}
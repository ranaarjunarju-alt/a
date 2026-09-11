package com.bytedance.zoin.utils;

import android.app.Application;
import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.zoin.SDKContext;
import com.bytedance.zoin.ZoinMonitor;
import com.vega.launcher.lancet.FileDirLancet;
import com.vega.libfiles.files.hook.FileAssist;
import com.vega.libfiles.files.hook.FileHook;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import java.io.File;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class WorkDirManager {

    /* renamed from: a, reason: collision with root package name */
    public static volatile boolean f48340a;
    public static PolarisFileWrapper b;

    /* renamed from: c, reason: collision with root package name */
    public static PolarisFileWrapper f48341c;

    /* renamed from: d, reason: collision with root package name */
    public static PolarisFileWrapper f48342d;
    public static final AtomicInteger e = new AtomicInteger(0);

    public static File INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_launcher_lancet_FileDirLancet_getCacheDir(Application application) {
        if (!PerformanceManagerHelper.ipcOptEnable) {
            return application.getCacheDir();
        }
        if (FileDirLancet.b == null) {
            FileDirLancet.b = application.getCacheDir();
        }
        return FileDirLancet.b;
    }

    public static File INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_launcher_lancet_FileDirLancet_getFilesDir(Application application) {
        if (!PerformanceManagerHelper.ipcOptEnable) {
            return application.getFilesDir();
        }
        if (FileDirLancet.f107351a == null) {
            FileDirLancet.f107351a = application.getFilesDir();
        }
        return FileDirLancet.f107351a;
    }

    public static boolean INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_libfiles_files_hook_FileHook_renameTo(File file, File file2) {
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

    /* JADX WARN: Removed duplicated region for block: B:138:0x05c2 A[Catch: Exception -> 0x05fe, TryCatch #2 {Exception -> 0x05fe, blocks: (B:128:0x05a9, B:130:0x05af, B:132:0x05b5, B:134:0x05bb, B:136:0x05be, B:138:0x05c2, B:140:0x05ca, B:142:0x05d6, B:143:0x05fb), top: B:152:0x05a9 }] */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0557 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:94:0x04f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(java.util.List<com.bytedance.zoin.model.LibModuleInfo> r14) throws java.io.IOException {
        /*
            e()
            android.app.Application r0 = com.bytedance.zoin.SDKContext.f48320a
            java.io.File r4 = INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_launcher_lancet_FileDirLancet_getFilesDir(r0)
            android.app.Application r0 = com.bytedance.zoin.SDKContext.f48320a
            java.io.File r3 = INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_launcher_lancet_FileDirLancet_getCacheDir(r0)
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "FilesDir: "
            r1.<init>(r0)
            r1.append(r4)
            java.lang.String r8 = " "
            r1.append(r8)
            boolean r0 = r4.exists()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r4.canRead()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r4.canWrite()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r4.canExecute()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "CacheDir: "
            r1.<init>(r0)
            r1.append(r3)
            r1.append(r8)
            boolean r0 = r3.exists()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canRead()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canWrite()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canExecute()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            com.bytedance.zoin.ZoinMonitor r6 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r0 = "Current innerSpace "
            r5.<init>(r0)
            android.os.StatFs r1 = new android.os.StatFs     // Catch: java.lang.Throwable -> Lb0
            android.app.Application r0 = com.bytedance.zoin.SDKContext.f48320a     // Catch: java.lang.Throwable -> Lb0
            java.io.File r0 = com.bytedance.zoin.utils.IoUtil.INVOKEVIRTUAL_com_bytedance_zoin_utils_IoUtil_com_vega_launcher_lancet_FileDirLancet_getFilesDir(r0)     // Catch: java.lang.Throwable -> Lb0
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.Throwable -> Lb0
            r1.<init>(r0)     // Catch: java.lang.Throwable -> Lb0
            long r0 = r1.getFreeBytes()     // Catch: java.lang.Throwable -> Lb0
            goto Lcf
        Lb0:
            r3 = move-exception
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "get inner space error "
            r1.<init>(r0)
            java.lang.String r0 = com.bytedance.zoin.utils.ExceptionUtils.a(r3)
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            r0 = 0
        Lcf:
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            r6.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            boolean r0 = r4.exists()
            if (r0 == 0) goto L142
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "Print FilesDir"
            com.bytedance.zoin.ZoinMonitor.d(r0)
            java.io.File[] r6 = r4.listFiles()
            if (r6 == 0) goto L1c6
            int r0 = r6.length
            if (r0 <= 0) goto L1c6
            int r5 = r6.length
            r4 = 0
        Lf9:
            if (r4 >= r5) goto L1c6
            r3 = r6[r4]
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = r3.getName()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.exists()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canRead()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canWrite()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canExecute()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            int r4 = r4 + 1
            goto Lf9
        L142:
            java.io.File r1 = r4.getParentFile()
            boolean r0 = r1.exists()
            if (r0 == 0) goto L1b9
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "Print ParentFile"
            com.bytedance.zoin.ZoinMonitor.d(r0)
            java.io.File[] r6 = r1.listFiles()
            if (r6 == 0) goto L1ac
            int r0 = r6.length
            if (r0 <= 0) goto L1ac
            int r5 = r6.length
            r4 = 0
        L163:
            if (r4 >= r5) goto L1ac
            r3 = r6[r4]
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = r3.getName()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.exists()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canRead()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canWrite()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canExecute()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            int r4 = r4 + 1
            goto L163
        L1ac:
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "Print ParentFile END"
            com.bytedance.zoin.ZoinMonitor.d(r0)
            goto L1d2
        L1b9:
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "FILES NOT WORK"
            com.bytedance.zoin.ZoinMonitor.d(r0)
            goto L1d2
        L1c6:
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "Print FilesDir END"
            com.bytedance.zoin.ZoinMonitor.d(r0)
        L1d2:
            java.io.File r0 = d()
            boolean r0 = r0.exists()
            if (r0 == 0) goto L24c
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "Print RootDir"
            com.bytedance.zoin.ZoinMonitor.d(r0)
            java.io.File r0 = d()
            java.io.File[] r6 = r0.listFiles()
            if (r6 == 0) goto L240
            int r0 = r6.length
            if (r0 <= 0) goto L240
            int r5 = r6.length
            r4 = 0
        L1f7:
            if (r4 >= r5) goto L240
            r3 = r6[r4]
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = r3.getName()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.exists()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canRead()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canWrite()
            r1.append(r0)
            r1.append(r8)
            boolean r0 = r3.canExecute()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            int r4 = r4 + 1
            goto L1f7
        L240:
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = "Print RootDir END"
            com.bytedance.zoin.ZoinMonitor.d(r0)
        L24c:
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r2 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            java.io.File r1 = b()
            java.lang.String r0 = "createFile.test"
            r2.<init>(r1, r0)
            r5 = 1
            boolean r3 = r2.createNewFile()     // Catch: java.io.IOException -> L278
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.io.IOException -> L278
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.io.IOException -> L278
            r1.<init>()     // Catch: java.io.IOException -> L278
            java.lang.String r0 = "TestSuccess "
            r1.append(r0)     // Catch: java.io.IOException -> L278
            r1.append(r3)     // Catch: java.io.IOException -> L278
            java.lang.String r0 = r1.toString()     // Catch: java.io.IOException -> L278
            r2.getClass()     // Catch: java.io.IOException -> L278
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.io.IOException -> L278
            goto L29b
        L278:
            r3 = move-exception
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "TestCreateFail "
            r1.<init>(r0)
            java.lang.String r0 = r3.getMessage()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            java.lang.Throwable r3 = r3.getCause()
            if (r3 != 0) goto L406
        L29b:
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "MD5Check:"
            r1.<init>(r0)
            java.lang.String r0 = com.bytedance.zoin.SDKContext.d()
            r1.append(r0)
            java.lang.String r0 = " Now:"
            r1.append(r0)
            java.lang.String r0 = com.bytedance.zoin.SDKContext.c()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "CurrentDirExist: "
            r1.<init>(r0)
            java.io.File r0 = b()
            r1.append(r0)
            r1.append(r8)
            java.io.File r0 = b()
            boolean r0 = r0.exists()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "LastDirExist: "
            r1.<init>(r0)
            java.io.File r0 = c()
            r1.append(r0)
            r1.append(r8)
            java.io.File r0 = c()
            boolean r0 = r0.exists()
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            java.lang.String r1 = com.bytedance.zoin.SDKContext.c()
            java.lang.String r0 = com.bytedance.zoin.SDKContext.d()
            boolean r0 = r1.equals(r0)
            java.lang.String r4 = "key.lib.decode.finished"
            if (r0 != 0) goto L43a
            java.io.File r0 = c()
            com.bytedance.zoin.utils.FileUtils.b(r0)
            java.util.Iterator r14 = r14.iterator()
        L332:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L49f
            java.lang.Object r1 = r14.next()
            com.bytedance.zoin.model.LibModuleInfo r1 = (com.bytedance.zoin.model.LibModuleInfo) r1
            com.bytedance.zoin.model.LibModuleInfo$AbiLibInfo r0 = r1.curAbiInfo
            java.util.List<com.bytedance.zoin.model.ZoinBuildFileInfo> r6 = r0.libFileInfoList
            java.io.File r10 = b()
            java.io.File r9 = c()
            com.bytedance.zoin.SDKContext.d()
            java.lang.String r3 = r1.moduleName
            com.bytedance.zoin.utils.WorkDirManager.f48340a = r5
            boolean r0 = r9.exists()
            if (r0 == 0) goto L3d0
            java.util.HashSet r7 = new java.util.HashSet
            r7.<init>()
            X.4or r0 = new X.4or
            r0.<init>()
            java.io.File[] r13 = r9.listFiles(r0)
            if (r13 == 0) goto L384
            int r12 = r13.length
            r11 = 0
        L369:
            if (r11 >= r12) goto L384
            r5 = r13[r11]
            com.bytedance.zoin.model.ZoinBuildFileInfo r2 = new com.bytedance.zoin.model.ZoinBuildFileInfo
            r2.<init>()
            long r0 = com.bytedance.zoin.utils.VerifyUtils.a(r5)
            r2.checkNumber = r0
            java.lang.String r0 = r5.getName()
            r2.name = r0
            r7.add(r2)
            int r11 = r11 + 1
            goto L369
        L384:
            java.util.Iterator r12 = r6.iterator()
        L388:
            boolean r0 = r12.hasNext()
            if (r0 == 0) goto L3d3
            java.lang.Object r11 = r12.next()
            com.bytedance.zoin.model.ZoinBuildFileInfo r11 = (com.bytedance.zoin.model.ZoinBuildFileInfo) r11
            boolean r0 = r7.contains(r11)
            if (r0 == 0) goto L388
            java.lang.String r2 = r11.name
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r1 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            r1.<init>(r9, r2)
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r0 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            r0.<init>(r10, r2)
            boolean r5 = INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_libfiles_files_hook_FileHook_renameTo(r1, r0)
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "RenameTo "
            r1.<init>(r0)
            java.lang.String r0 = r11.name
            r1.append(r0)
            r1.append(r8)
            r1.append(r5)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            if (r5 == 0) goto L388
            r12.remove()
            goto L388
        L3d0:
            if (r6 == 0) goto L3f9
            goto L3eb
        L3d3:
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "files which need to be decompressed:"
            r1.<init>(r0)
            r1.append(r6)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
        L3eb:
            int r0 = r6.size()
            if (r0 <= 0) goto L3f9
            java.lang.String r1 = com.bytedance.zoin.SDKContext.e(r3, r4)
            r0 = 0
            com.bytedance.zoin.SDKContext.f(r1, r0)
        L3f9:
            java.lang.String r0 = "is_first_load"
            java.lang.String r1 = com.bytedance.zoin.SDKContext.e(r3, r0)
            r0 = 1
            com.bytedance.zoin.SDKContext.f(r1, r0)
            r5 = 1
            goto L332
        L406:
            boolean r0 = r3 instanceof android.system.ErrnoException
            if (r0 == 0) goto L29b
            android.system.ErrnoException r3 = (android.system.ErrnoException) r3
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "FindErrno "
            r1.<init>(r0)
            int r0 = r3.errno
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            int r1 = r3.errno
            r0 = 23
            if (r1 == r0) goto L432
            r0 = 24
            if (r1 == r0) goto L432
            goto L29b
        L432:
            boolean r0 = com.bytedance.zoin.utils.IoUtil.f48336a
            if (r0 != 0) goto L29b
            com.bytedance.zoin.utils.IoUtil.f48336a = r5
            goto L29b
        L43a:
            java.util.Iterator r6 = r14.iterator()
        L43e:
            boolean r0 = r6.hasNext()
            if (r0 == 0) goto L4d0
            java.lang.Object r5 = r6.next()
            com.bytedance.zoin.model.LibModuleInfo r5 = (com.bytedance.zoin.model.LibModuleInfo) r5
            com.bytedance.zoin.model.LibModuleInfo$AbiLibInfo r0 = r5.curAbiInfo
            java.util.List<com.bytedance.zoin.model.ZoinBuildFileInfo> r0 = r0.libFileInfoList
            java.util.Iterator r2 = r0.iterator()
        L452:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L43e
            java.lang.Object r0 = r2.next()
            com.bytedance.zoin.model.ZoinBuildFileInfo r0 = (com.bytedance.zoin.model.ZoinBuildFileInfo) r0
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r3 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper
            java.io.File r1 = b()
            java.lang.String r0 = r0.name
            r3.<init>(r1, r0)
            boolean r0 = r3.exists()
            if (r0 != 0) goto L452
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = r5.moduleName
            r1.append(r0)
            java.lang.String r0 = " reInit because "
            r1.append(r0)
            r1.append(r3)
            java.lang.String r0 = " not exist"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            java.lang.String r0 = r5.moduleName
            java.lang.String r1 = com.bytedance.zoin.SDKContext.e(r0, r4)
            r0 = 0
            com.bytedance.zoin.SDKContext.f(r1, r0)
            goto L43e
        L49f:
            java.lang.String r3 = com.bytedance.zoin.SDKContext.c()
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            android.content.SharedPreferences r0 = r0.b()
            android.content.SharedPreferences$Editor r1 = r0.edit()
            java.lang.String r0 = "key.meta.md5"
            android.content.SharedPreferences$Editor r0 = r1.putString(r0, r3)
            r0.apply()
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "putString key.meta.md5 "
            r1.<init>(r0)
            r1.append(r3)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
        L4d0:
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            java.lang.String r0 = com.bytedance.zoin.SDKContext.c()
            java.lang.String r7 = "defaultmd5"
            boolean r0 = r0.equals(r7)
            java.lang.String r9 = "read metadata md5 failed, use backup md5"
            if (r0 == 0) goto L505
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r9)
        L4ef:
            java.lang.String r0 = com.bytedance.zoin.SDKContext.c()
            boolean r0 = r0.equals(r7)
            if (r0 == 0) goto L557
            com.bytedance.zoin.ZoinMonitor r0 = com.bytedance.zoin.ZoinMonitor.a()
            r0.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r9)
            goto L5a9
        L505:
            android.app.Application r0 = com.bytedance.zoin.SDKContext.f48320a     // Catch: java.lang.Exception -> L550
            java.io.File r0 = com.bytedance.zoin.utils.ZoinCleaner.INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_launcher_lancet_FileDirLancet_getFilesDir(r0)     // Catch: java.lang.Exception -> L550
            java.io.File r1 = r0.getParentFile()     // Catch: java.lang.Exception -> L550
            r0 = 0
            java.io.File[] r6 = new java.io.File[r0]     // Catch: java.lang.Exception -> L552
            if (r1 == 0) goto L51a
            java.io.File[] r6 = r1.listFiles()     // Catch: java.lang.Exception -> L552
            if (r6 == 0) goto L4ef
        L51a:
            int r0 = r6.length     // Catch: java.lang.Exception -> L552
            if (r0 <= 0) goto L4ef
            int r5 = r6.length     // Catch: java.lang.Exception -> L552
            r4 = 0
        L51f:
            if (r4 >= r5) goto L4ef
            r3 = r6[r4]     // Catch: java.lang.Exception -> L552
            java.lang.String r1 = r3.getName()     // Catch: java.lang.Exception -> L552
            java.lang.String r0 = "app_zoin"
            boolean r0 = r1.startsWith(r0)     // Catch: java.lang.Exception -> L552
            if (r0 == 0) goto L54d
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Exception -> L552
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L552
            r1.<init>()     // Catch: java.lang.Exception -> L552
            java.lang.String r0 = "clean old "
            r1.append(r0)     // Catch: java.lang.Exception -> L552
            r1.append(r3)     // Catch: java.lang.Exception -> L552
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L552
            r2.getClass()     // Catch: java.lang.Exception -> L552
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Exception -> L552
            com.bytedance.zoin.utils.ZoinCleaner.a(r3)     // Catch: java.lang.Exception -> L552
        L54d:
            int r4 = r4 + 1
            goto L51f
        L550:
            r0 = move-exception
            goto L553
        L552:
            r0 = move-exception
        L553:
            r0.printStackTrace()
            goto L4ef
        L557:
            java.io.File r7 = b()     // Catch: java.lang.Exception -> L5a5
            java.io.File r1 = r7.getParentFile()     // Catch: java.lang.Exception -> L5a5
            if (r1 == 0) goto L5a9
            boolean r0 = r1.exists()     // Catch: java.lang.Exception -> L5a5
            if (r0 == 0) goto L5a9
            java.io.File[] r6 = r1.listFiles()     // Catch: java.lang.Exception -> L5a5
            if (r6 == 0) goto L5a9
            int r0 = r6.length     // Catch: java.lang.Exception -> L5a5
            if (r0 <= 0) goto L5a9
            int r5 = r6.length     // Catch: java.lang.Exception -> L5a5
            r4 = 0
        L572:
            if (r4 >= r5) goto L5a9
            r3 = r6[r4]     // Catch: java.lang.Exception -> L5a5
            java.lang.String r1 = r3.getName()     // Catch: java.lang.Exception -> L5a5
            java.lang.String r0 = r7.getName()     // Catch: java.lang.Exception -> L5a5
            boolean r0 = r1.equals(r0)     // Catch: java.lang.Exception -> L5a5
            if (r0 != 0) goto L5a2
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Exception -> L5a5
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5a5
            r1.<init>()     // Catch: java.lang.Exception -> L5a5
            java.lang.String r0 = "clean last old "
            r1.append(r0)     // Catch: java.lang.Exception -> L5a5
            r1.append(r3)     // Catch: java.lang.Exception -> L5a5
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L5a5
            r2.getClass()     // Catch: java.lang.Exception -> L5a5
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Exception -> L5a5
            com.bytedance.zoin.utils.ZoinCleaner.a(r3)     // Catch: java.lang.Exception -> L5a5
        L5a2:
            int r4 = r4 + 1
            goto L572
        L5a5:
            r0 = move-exception
            r0.printStackTrace()
        L5a9:
            java.io.File r1 = b()     // Catch: java.lang.Exception -> L5fe
            if (r1 == 0) goto L602
            boolean r0 = r1.exists()     // Catch: java.lang.Exception -> L5fe
            if (r0 == 0) goto L602
            java.io.File[] r7 = r1.listFiles()     // Catch: java.lang.Exception -> L5fe
            if (r7 == 0) goto L602
            int r0 = r7.length     // Catch: java.lang.Exception -> L5fe
            if (r0 <= 0) goto L602
            int r6 = r7.length     // Catch: java.lang.Exception -> L5fe
            r5 = 0
        L5c0:
            if (r5 >= r6) goto L602
            r4 = r7[r5]     // Catch: java.lang.Exception -> L5fe
            boolean r0 = r4.isFile()     // Catch: java.lang.Exception -> L5fe
            if (r0 == 0) goto L5fb
            java.lang.String r1 = r4.getName()     // Catch: java.lang.Exception -> L5fe
            java.lang.String r0 = ".temp"
            boolean r0 = r1.endsWith(r0)     // Catch: java.lang.Exception -> L5fe
            if (r0 == 0) goto L5fb
            boolean r3 = com.bytedance.zoin.utils.ZoinCleaner.INVOKEVIRTUAL_com_bytedance_zoin_utils_ZoinCleaner_com_vega_draft_monitor_DraftMonitorLancet_delete(r4)     // Catch: java.lang.Exception -> L5fe
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Exception -> L5fe
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L5fe
            r1.<init>()     // Catch: java.lang.Exception -> L5fe
            java.lang.String r0 = "Clean tmp "
            r1.append(r0)     // Catch: java.lang.Exception -> L5fe
            r1.append(r4)     // Catch: java.lang.Exception -> L5fe
            r1.append(r8)     // Catch: java.lang.Exception -> L5fe
            r1.append(r3)     // Catch: java.lang.Exception -> L5fe
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Exception -> L5fe
            r2.getClass()     // Catch: java.lang.Exception -> L5fe
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Exception -> L5fe
        L5fb:
            int r5 = r5 + 1
            goto L5c0
        L5fe:
            r0 = move-exception
            r0.printStackTrace()
        L602:
            f()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.zoin.utils.WorkDirManager.a(java.util.List):void");
    }

    public static synchronized File b() {
        if (f48341c == null) {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(d(), SDKContext.c());
            if (!polarisFileWrapper.exists()) {
                boolean zMkdirs = polarisFileWrapper.mkdirs();
                ZoinMonitor.a().getClass();
                ZoinMonitor.d("CurrentDir mkdirs " + polarisFileWrapper + " " + zMkdirs);
                if (!zMkdirs) {
                    boolean zMkdirs2 = polarisFileWrapper.mkdirs();
                    ZoinMonitor.a().getClass();
                    ZoinMonitor.d("CurrentDir retry mkdirs " + polarisFileWrapper + " " + zMkdirs2);
                }
            }
            f48341c = polarisFileWrapper;
        }
        return f48341c;
    }

    public static synchronized File c() {
        if (f48342d == null) {
            f48342d = new PolarisFileWrapper(d(), SDKContext.d());
        }
        return f48342d;
    }

    public static synchronized File d() {
        if (b == null) {
            PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(INVOKEVIRTUAL_com_bytedance_zoin_utils_WorkDirManager_com_vega_launcher_lancet_FileDirLancet_getFilesDir(SDKContext.f48320a), "zoin");
            b = polarisFileWrapper;
            if (!polarisFileWrapper.exists()) {
                boolean zMkdirs = b.mkdirs();
                ZoinMonitor zoinMonitorA = ZoinMonitor.a();
                String str = "RootDir mkdirs " + b + " " + zMkdirs;
                zoinMonitorA.getClass();
                ZoinMonitor.d(str);
                if (!zMkdirs) {
                    boolean zMkdirs2 = b.mkdirs();
                    ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
                    String str2 = "RootDir retry mkdirs " + b + " " + zMkdirs2;
                    zoinMonitorA2.getClass();
                    ZoinMonitor.d(str2);
                }
            }
        }
        return b;
    }

    public static synchronized void e() {
        if (e.incrementAndGet() == 1) {
            boolean zB = FileUtils.b(b());
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("set writeable true " + zB);
        }
    }

    public static synchronized void f() {
        if (e.decrementAndGet() == 0) {
            boolean writable = b().setWritable(false);
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("set writeable false " + writable);
        }
    }
}
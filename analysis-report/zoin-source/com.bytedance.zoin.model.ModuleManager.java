package com.bytedance.zoin.model;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.zoin.SDKContext;
import com.bytedance.zoin.ZoinMonitor;
import com.bytedance.zoin.model.LibModuleInfo;
import com.bytedance.zoin.utils.AbiHelper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes35.dex */
public class ModuleManager {
    public static List<LibDependency> dependencyList;
    public static final List<AbstractModuleInfo> moduleInfos = new ArrayList();
    public static String sMetaMd5 = "defaultmd5";
    public static final Set<String> depsNeedResolvedSo = new HashSet();
    public static final Set<String> realCompressedSo = new HashSet();
    public static volatile boolean isReadMetadataSuccess = false;

    public interface ParserState {
    }

    static {
        initModuleInfos();
        dependencyList = new ArrayList();
    }

    public static AbstractModuleInfo findModuleByName(String str) {
        for (AbstractModuleInfo abstractModuleInfo : moduleInfos) {
            if (abstractModuleInfo.moduleName.equals(str)) {
                return abstractModuleInfo;
            }
        }
        return null;
    }

    public static List<LibDependency> getDependencyList() {
        return dependencyList;
    }

    public static List<LibModuleInfo> getTotalLibModuleInfos() {
        ArrayList arrayList = new ArrayList();
        for (AbstractModuleInfo abstractModuleInfo : moduleInfos) {
            if (abstractModuleInfo.moduleType == 0) {
                arrayList.add(abstractModuleInfo);
            }
        }
        return arrayList;
    }

    public static boolean init(Context context) {
        if (isReadMetadataSuccess) {
            return true;
        }
        String strD = AbiHelper.d(context);
        ZoinMonitor.a().getClass();
        ZoinMonitor.d("Current ABI:" + strD);
        for (LibModuleInfo libModuleInfo : getTotalLibModuleInfos()) {
            Iterator<LibModuleInfo.AbiLibInfo> it = libModuleInfo.abiLibInfoList.iterator();
            while (true) {
                if (it.hasNext()) {
                    LibModuleInfo.AbiLibInfo next = it.next();
                    if (next.abiName.equals(strD)) {
                        libModuleInfo.curAbiInfo = next;
                        Iterator<LibDependency> it2 = next.libDependencyList.iterator();
                        while (it2.hasNext()) {
                            depsNeedResolvedSo.add(it2.next().libName);
                        }
                        Iterator<ZoinBuildFileInfo> it3 = next.libFileInfoList.iterator();
                        while (it3.hasNext()) {
                            realCompressedSo.add(it3.next().name);
                        }
                    }
                }
            }
        }
        ZoinMonitor zoinMonitorA = ZoinMonitor.a();
        String str = "NeedResolveSo:" + depsNeedResolvedSo;
        zoinMonitorA.getClass();
        ZoinMonitor.d(str);
        ZoinMonitor zoinMonitorA2 = ZoinMonitor.a();
        String str2 = "CompressedSo:" + realCompressedSo;
        zoinMonitorA2.getClass();
        ZoinMonitor.d(str2);
        String str3 = sMetaMd5 + "_" + strD.replace("-", "_");
        if (!TextUtils.isEmpty(str3)) {
            SDKContext.b = str3;
        }
        isReadMetadataSuccess = true;
        return isReadMetadataSuccess;
    }

    public static void initModuleInfos() {
        LibModuleInfo libModuleInfo = new LibModuleInfo();
        libModuleInfo.moduleName = "capcut";
        libModuleInfo.moduleType = 0;
        LibModuleInfo.AbiLibInfo abiLibInfo = new LibModuleInfo.AbiLibInfo();
        abiLibInfo.abiName = "armeabi-v7a";
        ZoinBuildFileInfo zoinBuildFileInfo = new ZoinBuildFileInfo();
        zoinBuildFileInfo.name = "libonetex.so";
        zoinBuildFileInfo.beginOffset = 6564808;
        zoinBuildFileInfo.endOffset = 11223788;
        zoinBuildFileInfo.offsetInFile = 0;
        zoinBuildFileInfo.fileLength = 4658980;
        zoinBuildFileInfo.alignedFileLength = 4658980;
        zoinBuildFileInfo.checkNumber = 1396576587L;
        zoinBuildFileInfo.compressedName = "libcapcut_1.so";
        zoinBuildFileInfo.relativePath = "libonetex.so";
        abiLibInfo.libFileInfoList.add(zoinBuildFileInfo);
        ZoinBuildFileInfo zoinBuildFileInfo2 = new ZoinBuildFileInfo();
        zoinBuildFileInfo2.name = "libspeechspg.so";
        zoinBuildFileInfo2.beginOffset = 0;
        zoinBuildFileInfo2.endOffset = 6564808;
        zoinBuildFileInfo2.offsetInFile = 0;
        zoinBuildFileInfo2.fileLength = 6564808;
        zoinBuildFileInfo2.alignedFileLength = 6564808;
        zoinBuildFileInfo2.checkNumber = 2094404258L;
        zoinBuildFileInfo2.compressedName = "libcapcut_1.so";
        zoinBuildFileInfo2.relativePath = "libspeechspg.so";
        abiLibInfo.libFileInfoList.add(zoinBuildFileInfo2);
        ZoinBlockInfo zoinBlockInfo = new ZoinBlockInfo();
        zoinBlockInfo.blockName = "libcapcut.so";
        zoinBlockInfo.blockCompressedName = "libcapcut_1.so";
        zoinBlockInfo.blockBeginOffset = 0L;
        zoinBlockInfo.blockEndOffset = 2447947L;
        zoinBlockInfo.totalDecompressedLength = 11223788;
        abiLibInfo.blockInfoList.add(zoinBlockInfo);
        LibDependency libDependency = new LibDependency();
        libDependency.libName = "libspeechepg.so";
        libDependency.depsList.add("libc++_shared.so");
        libDependency.depsList.add("libttcrypto.so");
        libDependency.depsList.add("libttboringssl.so");
        libDependency.depsList.add("libsscronet.so");
        libDependency.depsList.add("libopus.so");
        libDependency.depsList.add("libaudio_fingerprint_sdk.so");
        libDependency.depsList.add("libspeechspg.so");
        abiLibInfo.libDependencyList.add(libDependency);
        LibDependency libDependency2 = new LibDependency();
        libDependency2.libName = "libonetex.so";
        abiLibInfo.libDependencyList.add(libDependency2);
        LibDependency libDependency3 = new LibDependency();
        libDependency3.libName = "libspeechspg.so";
        libDependency3.depsList.add("libc++_shared.so");
        libDependency3.depsList.add("libttcrypto.so");
        libDependency3.depsList.add("libttboringssl.so");
        libDependency3.depsList.add("libsscronet.so");
        libDependency3.depsList.add("libopus.so");
        libDependency3.depsList.add("libaudio_fingerprint_sdk.so");
        abiLibInfo.libDependencyList.add(libDependency3);
        libModuleInfo.abiLibInfoList.add(abiLibInfo);
        LibModuleInfo.AbiLibInfo abiLibInfo2 = new LibModuleInfo.AbiLibInfo();
        abiLibInfo2.abiName = "arm64-v8a";
        ZoinBuildFileInfo zoinBuildFileInfo3 = new ZoinBuildFileInfo();
        zoinBuildFileInfo3.name = "libonetex.so";
        zoinBuildFileInfo3.beginOffset = 8726560;
        zoinBuildFileInfo3.endOffset = 15233416;
        zoinBuildFileInfo3.offsetInFile = 0;
        zoinBuildFileInfo3.fileLength = 6506856;
        zoinBuildFileInfo3.alignedFileLength = 6506856;
        zoinBuildFileInfo3.checkNumber = 2234619001L;
        zoinBuildFileInfo3.compressedName = "libcapcut_1.so";
        zoinBuildFileInfo3.relativePath = "libonetex.so";
        abiLibInfo2.libFileInfoList.add(zoinBuildFileInfo3);
        ZoinBuildFileInfo zoinBuildFileInfo4 = new ZoinBuildFileInfo();
        zoinBuildFileInfo4.name = "libspeechspg.so";
        zoinBuildFileInfo4.beginOffset = 0;
        zoinBuildFileInfo4.endOffset = 8726560;
        zoinBuildFileInfo4.offsetInFile = 0;
        zoinBuildFileInfo4.fileLength = 8726560;
        zoinBuildFileInfo4.alignedFileLength = 8726560;
        zoinBuildFileInfo4.checkNumber = 3678770750L;
        zoinBuildFileInfo4.compressedName = "libcapcut_1.so";
        zoinBuildFileInfo4.relativePath = "libspeechspg.so";
        abiLibInfo2.libFileInfoList.add(zoinBuildFileInfo4);
        ZoinBlockInfo zoinBlockInfo2 = new ZoinBlockInfo();
        zoinBlockInfo2.blockName = "libcapcut.so";
        zoinBlockInfo2.blockCompressedName = "libcapcut_1.so";
        zoinBlockInfo2.blockBeginOffset = 0L;
        zoinBlockInfo2.blockEndOffset = 2331059L;
        zoinBlockInfo2.totalDecompressedLength = 15233416;
        abiLibInfo2.blockInfoList.add(zoinBlockInfo2);
        LibDependency libDependency4 = new LibDependency();
        libDependency4.libName = "libspeechepg.so";
        libDependency4.depsList.add("libc++_shared.so");
        libDependency4.depsList.add("libttcrypto.so");
        libDependency4.depsList.add("libttboringssl.so");
        libDependency4.depsList.add("libsscronet.so");
        libDependency4.depsList.add("libopus.so");
        libDependency4.depsList.add("libaudio_fingerprint_sdk.so");
        libDependency4.depsList.add("libspeechspg.so");
        abiLibInfo2.libDependencyList.add(libDependency4);
        LibDependency libDependency5 = new LibDependency();
        libDependency5.libName = "libonetex.so";
        abiLibInfo2.libDependencyList.add(libDependency5);
        LibDependency libDependency6 = new LibDependency();
        libDependency6.libName = "libspeechspg.so";
        libDependency6.depsList.add("libc++_shared.so");
        libDependency6.depsList.add("libttcrypto.so");
        libDependency6.depsList.add("libttboringssl.so");
        libDependency6.depsList.add("libsscronet.so");
        libDependency6.depsList.add("libopus.so");
        libDependency6.depsList.add("libaudio_fingerprint_sdk.so");
        abiLibInfo2.libDependencyList.add(libDependency6);
        libModuleInfo.abiLibInfoList.add(abiLibInfo2);
        moduleInfos.add(libModuleInfo);
        sMetaMd5 = "7cc060024323c1b9";
    }

    public static synchronized boolean isSoCompressed(String str) {
        if (realCompressedSo.isEmpty()) {
            for (AbstractModuleInfo abstractModuleInfo : moduleInfos) {
                if (abstractModuleInfo.moduleType == 0) {
                    Iterator<LibModuleInfo.AbiLibInfo> it = ((LibModuleInfo) abstractModuleInfo).abiLibInfoList.iterator();
                    while (it.hasNext()) {
                        Iterator<ZoinBuildFileInfo> it2 = it.next().libFileInfoList.iterator();
                        while (it2.hasNext()) {
                            realCompressedSo.add(it2.next().name);
                        }
                    }
                }
            }
        }
        return realCompressedSo.contains(str);
    }

    public static synchronized boolean isSoDepsNeedsResolve(String str) {
        if (depsNeedResolvedSo.isEmpty()) {
            for (AbstractModuleInfo abstractModuleInfo : moduleInfos) {
                if (abstractModuleInfo.moduleType == 0) {
                    Iterator<LibModuleInfo.AbiLibInfo> it = ((LibModuleInfo) abstractModuleInfo).abiLibInfoList.iterator();
                    while (it.hasNext()) {
                        Iterator<LibDependency> it2 = it.next().libDependencyList.iterator();
                        while (it2.hasNext()) {
                            depsNeedResolvedSo.add(it2.next().libName);
                        }
                    }
                }
            }
        }
        return depsNeedResolvedSo.contains("lib" + str + ".so");
    }

    public static void logModules() {
        for (AbstractModuleInfo abstractModuleInfo : moduleInfos) {
            ZoinMonitor zoinMonitorA = ZoinMonitor.a();
            String string = abstractModuleInfo.toString();
            zoinMonitorA.getClass();
            ZoinMonitor.d(string);
        }
    }

    public static String prepareDeps() {
        return "";
    }

    public static void setDependencyList(List<LibDependency> list) {
        dependencyList.addAll(list);
    }

    public static boolean waitToBeDecompressed(Context context) {
        return moduleInfos.size() > 0;
    }
}
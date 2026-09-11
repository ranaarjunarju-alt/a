package com.bytedance.zoin.model;

import java.util.ArrayList;
import java.util.List;

/* loaded from: classes18.dex */
public class LibModuleInfo extends AbstractModuleInfo {
    public List<AbiLibInfo> abiLibInfoList = new ArrayList();
    public AbiLibInfo curAbiInfo;

    /* loaded from: classes23.dex */
    public static class AbiLibInfo {
        public String abiName;
        public List<ZoinBuildFileInfo> libFileInfoList = new ArrayList();
        public List<LibDependency> libDependencyList = new ArrayList();
        public List<ZoinBlockInfo> blockInfoList = new ArrayList();
    }
}
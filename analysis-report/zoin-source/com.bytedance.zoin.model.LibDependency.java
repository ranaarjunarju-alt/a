package com.bytedance.zoin.model;

import java.util.LinkedList;

/* loaded from: classes15.dex */
public class LibDependency {
    public LinkedList<String> depsList = new LinkedList<>();
    public String libName;

    public String toString() {
        return "LibDependency{libName='" + this.libName + "', depsList=" + this.depsList + '}';
    }
}
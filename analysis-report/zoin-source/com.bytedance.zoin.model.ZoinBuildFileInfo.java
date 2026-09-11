package com.bytedance.zoin.model;

import java.util.LinkedList;

/* loaded from: classes23.dex */
public class ZoinBuildFileInfo implements Comparable<ZoinBuildFileInfo> {
    public int alignedFileLength;
    public int beginOffset;
    public long checkNumber;
    public String compressedName;
    public String decompressPath;
    public int endOffset;
    public int fileLength;
    public String name;
    public LinkedList<String> neededDeps = new LinkedList<>();
    public int offsetInFile;
    public String relativePath;

    /* JADX DEBUG: Method merged with bridge method: compareTo(Ljava/lang/Object;)I */
    @Override // java.lang.Comparable
    public int compareTo(ZoinBuildFileInfo zoinBuildFileInfo) {
        return this.beginOffset - zoinBuildFileInfo.beginOffset;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ZoinBuildFileInfo)) {
            return false;
        }
        ZoinBuildFileInfo zoinBuildFileInfo = (ZoinBuildFileInfo) obj;
        if (this.checkNumber != zoinBuildFileInfo.checkNumber) {
            return false;
        }
        String str = this.name;
        String str2 = zoinBuildFileInfo.name;
        return str != null ? str.equals(str2) : str2 == null;
    }

    public int getFileBlockLength() {
        return this.endOffset - this.beginOffset;
    }

    public int hashCode() {
        String str = this.name;
        int iHashCode = str != null ? str.hashCode() : 0;
        long j = this.checkNumber;
        return (iHashCode * 31) + ((int) (j ^ (j >>> 32)));
    }

    public String toString() {
        return "ZoinBuildFileInfo{name='" + this.name + "', checkNumber=" + this.checkNumber + ", fileLength=" + this.fileLength + '}';
    }
}
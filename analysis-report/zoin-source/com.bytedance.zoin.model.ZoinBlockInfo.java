package com.bytedance.zoin.model;

/* loaded from: classes16.dex */
public class ZoinBlockInfo {
    public long blockBeginOffset;
    public String blockCompressedName;
    public long blockEndOffset;
    public String blockName;
    public int totalDecompressedLength;

    public String toString() {
        return "ZoinBlockInfo{blockName='" + this.blockName + "', blockCompressedName='" + this.blockCompressedName + "', blockBeginOffset=" + this.blockBeginOffset + ", blockEndOffset=" + this.blockEndOffset + ", totalDecompressedLength=" + this.totalDecompressedLength + '}';
    }
}
package com.bytedance.zoin.utils;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/* loaded from: classes15.dex */
public class Locker {

    /* renamed from: a, reason: collision with root package name */
    public RandomAccessFile f48337a;
    public FileLock b;

    /* renamed from: c, reason: collision with root package name */
    public FileChannel f48338c;

    /* renamed from: d, reason: collision with root package name */
    public final File f48339d;

    public Locker(PolarisFileWrapper polarisFileWrapper) {
        this.f48339d = polarisFileWrapper;
    }
}
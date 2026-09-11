package com.bytedance.zoin;

import com.bytedance.zoin.utils.ZoinException;

/* loaded from: classes10.dex */
public class ZoinResult {

    /* renamed from: c, reason: collision with root package name */
    public static final ZoinResult f48328c = new ZoinResult(0, null);

    /* renamed from: a, reason: collision with root package name */
    public final int f48329a;
    public final Throwable b;

    public ZoinResult(int i, ZoinException zoinException) {
        this.f48329a = i;
        this.b = zoinException;
    }

    public final boolean a() {
        return this.f48329a == 0;
    }

    public final String toString() {
        return "ZoinResult{code=" + this.f48329a + ", throwable=" + this.b + ", duration=0}";
    }
}
package com.bytedance.zoin.utils;

/* loaded from: classes39.dex */
public class ZoinException extends RuntimeException {
    public ZoinException() {
    }

    public ZoinException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        return this;
    }
}
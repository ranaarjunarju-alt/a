package com.bytedance.zoin.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes28.dex */
public class ExceptionUtils {
    public static String a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
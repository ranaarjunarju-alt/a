package com.bytedance.zoin;

import android.app.Application;
import dalvik.system.PathClassLoader;

/* loaded from: classes3.dex */
public class SDKContext {

    /* renamed from: a, reason: collision with root package name */
    public static Application f48320a = null;
    public static String b = "defaultmd5";

    /* renamed from: c, reason: collision with root package name */
    public static String f48321c;

    public static ClassLoader a(ClassLoader classLoader) {
        if (classLoader == null) {
            return null;
        }
        return classLoader.getClass().equals(PathClassLoader.class) ? classLoader : a(classLoader.getParent());
    }

    public static boolean b(String str, boolean z) {
        boolean z2 = ZoinMonitor.a().b().getBoolean(str, z);
        ZoinMonitor.a().getClass();
        ZoinMonitor.d("getBoolean " + str + " " + z2);
        return z2;
    }

    public static synchronized String c() {
        return b;
    }

    public static synchronized String d() {
        if (f48321c == null) {
            String string = ZoinMonitor.a().b().getString("key.meta.md5", "defaultmd5");
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("getString key.meta.md5 " + string);
            f48321c = string;
        }
        return f48321c;
    }

    public static String e(String str, String str2) {
        return str + "_" + str2;
    }

    public static void f(String str, boolean z) {
        ZoinMonitor.a().b().edit().putBoolean(str, z).apply();
        ZoinMonitor.a().getClass();
        ZoinMonitor.d("putBoolean " + str + " " + z);
    }
}
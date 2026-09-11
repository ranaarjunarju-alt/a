package com.bytedance.zoin.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Process;
import android.text.TextUtils;
import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.security.android.aopcheck.PolarisZipFileWrapper;
import com.bytedance.zoin.ZoinMonitor;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes37.dex */
public class AbiHelper {

    /* renamed from: a, reason: collision with root package name */
    public static volatile String f48333a;
    public static final Map<String, Integer> b;

    /* renamed from: c, reason: collision with root package name */
    public static final Map<String, Field> f48334c;

    /* renamed from: d, reason: collision with root package name */
    public static ApplicationInfo f48335d;
    public static PolarisFileWrapper e;
    public static volatile String f;

    static {
        HashMap map = new HashMap();
        b = map;
        f48334c = new HashMap();
        map.put("armeabi", 32);
        map.put("armeabi-v7a", 32);
        map.put("arm64-v8a", 64);
        map.put("x86", 32);
        map.put("x86_64", 64);
        map.put("mips", 32);
        map.put("mips64", 64);
        f = null;
    }

    public static void a(String str, String str2, JSONObject jSONObject) throws JSONException {
        try {
            jSONObject.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public static Map b(PolarisZipFileWrapper polarisZipFileWrapper) {
        String[] strArrSplit;
        HashMap map = new HashMap();
        Enumeration<? extends ZipEntry> enumerationEntries = polarisZipFileWrapper.entries();
        Pattern patternCompile = Pattern.compile("^lib/[^/]+/lib[^/]+.so$");
        while (enumerationEntries.hasMoreElements()) {
            ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
            if (!zipEntryNextElement.isDirectory() && patternCompile.matcher(zipEntryNextElement.getName()).matches() && (strArrSplit = zipEntryNextElement.getName().split(File.separator)) != null && strArrSplit.length >= 2) {
                String str = strArrSplit[strArrSplit.length - 2];
                if (((HashMap) b).containsKey(str)) {
                    if (map.get(str) == null) {
                        map.put(str, new LinkedList());
                    }
                    ((List) map.get(str)).add(zipEntryNextElement);
                }
            }
        }
        ZoinMonitor zoinMonitorA = ZoinMonitor.a();
        String str2 = "getAllSoZipEntries, zipFile=" + polarisZipFileWrapper.getName() + ", soEntries=" + map;
        zoinMonitorA.getClass();
        ZoinMonitor.d(str2);
        return map;
    }

    public static int c() {
        int i = 0;
        try {
            i = Process.is64Bit() ? 64 : 32;
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("inferHostAbiAuto, processMode=" + i);
        } catch (Exception unused) {
            ZoinMonitor.a().getClass();
            ZoinMonitor.d("inferHostAbiAuto, processMode exception default=" + i);
        } catch (NoSuchMethodError unused2) {
        }
        return i;
    }

    public static String d(Context context) {
        if (f == null) {
            f48335d = context.getApplicationInfo();
            e = new PolarisFileWrapper(f48335d.nativeLibraryDir);
            if (f48333a == null) {
                synchronized (AbiHelper.class) {
                    if (f48333a == null) {
                        f48333a = e(context);
                    }
                }
            }
            Integer num = (Integer) ((HashMap) b).get(f48333a);
            if (num == null) {
                num = -1;
            }
            String str = (num.intValue() != 32 && num.intValue() == 64) ? "arm64-v8a" : "armeabi-v7a";
            f = str;
        }
        return f;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00c2  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x00c2 -> B:18:0x00c3). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String e(android.content.Context r16) throws org.json.JSONException {
        /*
            java.lang.String r8 = "1"
            java.lang.String r7 = "manualError"
            java.lang.String r9 = "autoError"
            java.lang.String r6 = "defaultABI0"
            java.lang.String r10 = "matchCpuAbi"
            java.lang.String r3 = "supportedABI0"
            java.lang.String r12 = "processMode"
            java.lang.String r14 = "primaryCpuAbi"
            java.lang.String r1 = "0"
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            r5.put(r14, r1)     // Catch: org.json.JSONException -> L32
            r5.put(r12, r1)     // Catch: org.json.JSONException -> L32
            r5.put(r3, r1)     // Catch: org.json.JSONException -> L32
            r5.put(r10, r1)     // Catch: org.json.JSONException -> L32
            r5.put(r6, r1)     // Catch: org.json.JSONException -> L32
            java.lang.String r0 = "defaultABI"
            r5.put(r0, r1)     // Catch: org.json.JSONException -> L32
            r5.put(r9, r1)     // Catch: org.json.JSONException -> L32
            r5.put(r7, r1)     // Catch: org.json.JSONException -> L32
            goto L36
        L32:
            r0 = move-exception
            r0.printStackTrace()
        L36:
            java.lang.String r4 = "inferHostAbiAuto2, sHostAbi="
            java.lang.String r11 = "inferHostAbiAuto1, sHostAbi="
            java.lang.String r13 = ""
            java.lang.String r15 = "inferHostAbiAuto, primaryCpuAbi="
            android.content.pm.ApplicationInfo r0 = r16.getApplicationInfo()     // Catch: java.lang.Throwable -> La6
            java.lang.Object r2 = f(r0)     // Catch: java.lang.Throwable -> La6
            java.lang.String r2 = (java.lang.String) r2     // Catch: java.lang.Throwable -> La6
            com.bytedance.zoin.ZoinMonitor r1 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> La6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La6
            r0.<init>(r15)     // Catch: java.lang.Throwable -> La6
            r0.append(r2)     // Catch: java.lang.Throwable -> La6
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La6
            r1.getClass()     // Catch: java.lang.Throwable -> La6
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> La6
            a(r14, r2, r5)     // Catch: java.lang.Throwable -> La6
            if (r2 == 0) goto Lc2
            int r1 = c()     // Catch: java.lang.Throwable -> La6
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La6
            r0.<init>(r13)     // Catch: java.lang.Throwable -> La6
            r0.append(r1)     // Catch: java.lang.Throwable -> La6
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> La6
            a(r12, r0, r5)     // Catch: java.lang.Throwable -> La6
            if (r1 == 0) goto L97
            java.util.Map<java.lang.String, java.lang.Integer> r0 = com.bytedance.zoin.utils.AbiHelper.b     // Catch: java.lang.Throwable -> La6
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch: java.lang.Throwable -> La6
            java.lang.Object r0 = r0.get(r2)     // Catch: java.lang.Throwable -> La6
            java.lang.Integer r0 = (java.lang.Integer) r0     // Catch: java.lang.Throwable -> La6
            int r0 = r0.intValue()     // Catch: java.lang.Throwable -> La6
            if (r0 != r1) goto Lc2
            com.bytedance.zoin.ZoinMonitor r1 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> La6
            java.lang.String r0 = r4.concat(r2)     // Catch: java.lang.Throwable -> La6
            r1.getClass()     // Catch: java.lang.Throwable -> La6
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> La6
            goto Lc3
        L97:
            com.bytedance.zoin.ZoinMonitor r1 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> La6
            java.lang.String r0 = r11.concat(r2)     // Catch: java.lang.Throwable -> La6
            r1.getClass()     // Catch: java.lang.Throwable -> La6
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> La6
            goto Lc3
        La6:
            r4 = move-exception
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "inferHostAbiAuto failed!"
            r1.<init>(r0)
            r1.append(r4)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            a(r9, r8, r5)
        Lc2:
            r2 = 0
        Lc3:
            if (r2 == 0) goto Lc6
            return r2
        Lc6:
            java.lang.String r12 = "inferHostAbiManual, host source apk .so is empty, use supportedABIs[0]="
            java.lang.String r9 = "inferHostAbiManual, get host sourceDir PackageInfo failed!"
            r4 = 0
            android.content.pm.PackageManager r1 = r16.getPackageManager()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ldc java.lang.Throwable -> L173
            java.lang.String r0 = r16.getPackageName()     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ldc java.lang.Throwable -> L173
            android.content.pm.PackageInfo r0 = r1.getPackageInfo(r0, r4)     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ldc java.lang.Throwable -> L173
            android.content.pm.ApplicationInfo r0 = r0.applicationInfo     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ldc java.lang.Throwable -> L173
            java.lang.String r2 = r0.sourceDir     // Catch: android.content.pm.PackageManager.NameNotFoundException -> Ldc java.lang.Throwable -> L173
            goto Lf4
        Ldc:
            r2 = move-exception
            com.bytedance.zoin.ZoinMonitor r1 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> L173
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L173
            r0.<init>(r9)     // Catch: java.lang.Throwable -> L173
            r0.append(r2)     // Catch: java.lang.Throwable -> L173
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L173
            r1.getClass()     // Catch: java.lang.Throwable -> L173
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> L173
            r2 = 0
        Lf4:
            com.bytedance.security.android.aopcheck.PolarisZipFileWrapper r1 = new com.bytedance.security.android.aopcheck.PolarisZipFileWrapper     // Catch: java.lang.Throwable -> L173
            com.bytedance.security.android.aopcheck.PolarisFileWrapper r0 = new com.bytedance.security.android.aopcheck.PolarisFileWrapper     // Catch: java.lang.Throwable -> L173
            r0.<init>(r2)     // Catch: java.lang.Throwable -> L173
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L173
            java.util.Map r0 = b(r1)     // Catch: java.lang.Throwable -> L173
            java.util.HashSet r11 = new java.util.HashSet     // Catch: java.lang.Throwable -> L173
            java.util.HashMap r0 = (java.util.HashMap) r0     // Catch: java.lang.Throwable -> L173
            java.util.Set r0 = r0.keySet()     // Catch: java.lang.Throwable -> L173
            r11.<init>(r0)     // Catch: java.lang.Throwable -> L173
            r1.close()     // Catch: java.io.IOException -> L111 java.lang.Throwable -> L173
            goto L11d
        L111:
            com.bytedance.zoin.ZoinMonitor r1 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> L173
            java.lang.String r0 = "inferHostAbiManual, close sourceApkZipFile error!"
            r1.getClass()     // Catch: java.lang.Throwable -> L173
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> L173
        L11d:
            java.lang.String[] r9 = android.os.Build.SUPPORTED_ABIS     // Catch: java.lang.Throwable -> L173
            boolean r0 = r11.isEmpty()     // Catch: java.lang.Throwable -> L173
            if (r0 == 0) goto L145
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> L173
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L173
            r1.<init>(r12)     // Catch: java.lang.Throwable -> L173
            r0 = r9[r4]     // Catch: java.lang.Throwable -> L173
            r1.append(r0)     // Catch: java.lang.Throwable -> L173
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L173
            r2.getClass()     // Catch: java.lang.Throwable -> L173
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> L173
            r0 = r9[r4]     // Catch: java.lang.Throwable -> L173
            a(r3, r0, r5)     // Catch: java.lang.Throwable -> L173
            r3 = r9[r4]     // Catch: java.lang.Throwable -> L173
            goto L198
        L145:
            int r2 = r9.length     // Catch: java.lang.Throwable -> L173
            r1 = 0
        L147:
            if (r1 >= r2) goto L18f
            r3 = r9[r1]     // Catch: java.lang.Throwable -> L173
            boolean r0 = r11.contains(r3)     // Catch: java.lang.Throwable -> L173
            if (r0 == 0) goto L170
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()     // Catch: java.lang.Throwable -> L173
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L173
            r1.<init>()     // Catch: java.lang.Throwable -> L173
            java.lang.String r0 = "inferHostAbiManual, match cpuAbi="
            r1.append(r0)     // Catch: java.lang.Throwable -> L173
            r1.append(r3)     // Catch: java.lang.Throwable -> L173
            java.lang.String r0 = r1.toString()     // Catch: java.lang.Throwable -> L173
            r2.getClass()     // Catch: java.lang.Throwable -> L173
            com.bytedance.zoin.ZoinMonitor.d(r0)     // Catch: java.lang.Throwable -> L173
            a(r10, r3, r5)     // Catch: java.lang.Throwable -> L173
            goto L198
        L170:
            int r1 = r1 + 1
            goto L147
        L173:
            r3 = move-exception
            com.bytedance.zoin.ZoinMonitor r2 = com.bytedance.zoin.ZoinMonitor.a()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "inferHostAbiManual failed!"
            r1.<init>(r0)
            r1.append(r3)
            java.lang.String r0 = r1.toString()
            r2.getClass()
            com.bytedance.zoin.ZoinMonitor.d(r0)
            a(r7, r8, r5)
        L18f:
            java.lang.String[] r1 = android.os.Build.SUPPORTED_ABIS
            r0 = r1[r4]
            a(r6, r0, r5)
            r3 = r1[r4]
        L198:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.zoin.utils.AbiHelper.e(android.content.Context):java.lang.String");
    }

    public static Object f(Object obj) throws NoSuchFieldException, SecurityException {
        Field declaredField;
        if (obj == null) {
            throw new IllegalArgumentException("target object must not be null");
        }
        Class<?> superclass = obj.getClass();
        if (!(!TextUtils.isEmpty("primaryCpuAbi"))) {
            throw new IllegalArgumentException("The field name must not be blank");
        }
        String str = superclass.toString() + "#primaryCpuAbi";
        Map<String, Field> map = f48334c;
        synchronized (map) {
            declaredField = (Field) ((HashMap) map).get(str);
        }
        if (declaredField == null) {
            while (true) {
                if (superclass == null) {
                    declaredField = null;
                    break;
                }
                try {
                    declaredField = superclass.getDeclaredField("primaryCpuAbi");
                    if (!declaredField.isAccessible()) {
                        declaredField.setAccessible(true);
                    }
                    Map<String, Field> map2 = f48334c;
                    synchronized (map2) {
                        continue;
                        ((HashMap) map2).put(str, declaredField);
                    }
                    break;
                } catch (NoSuchFieldException unused) {
                    superclass = superclass.getSuperclass();
                }
            }
        } else if (!declaredField.isAccessible()) {
            declaredField.setAccessible(true);
        }
        if (declaredField == null) {
            return null;
        }
        if (!declaredField.isAccessible()) {
            declaredField.setAccessible(true);
        }
        return declaredField.get(obj);
    }
}
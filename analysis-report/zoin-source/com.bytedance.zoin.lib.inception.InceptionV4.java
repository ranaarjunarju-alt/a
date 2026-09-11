package com.bytedance.zoin.lib.inception;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.zoin.utils.ReflectUtils;
import dalvik.system.BaseDexClassLoader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes39.dex */
public class InceptionV4 implements IInception {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.util.Collection, java.util.List] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5 */
    @Override // com.bytedance.zoin.lib.inception.IInception
    public final boolean a(ClassLoader classLoader, String str, boolean z) throws IllegalAccessException, IllegalArgumentException {
        CopyOnWriteArrayList copyOnWriteArrayList;
        try {
            Object obj = ReflectUtils.a(BaseDexClassLoader.class, "pathList").get(classLoader);
            Field fieldA = ReflectUtils.a(obj.getClass(), "nativeLibraryDirectories");
            Object obj2 = fieldA.get(obj);
            ?? arrayList = obj2 == null ? new ArrayList() : (List) obj2;
            String[] strArrSplit = str.split(":");
            ArrayList arrayList2 = new ArrayList(strArrSplit.length);
            for (String str2 : strArrSplit) {
                PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(str2);
                if ((polarisFileWrapper.exists() || polarisFileWrapper.mkdirs()) && !arrayList.contains(polarisFileWrapper)) {
                    arrayList2.add(polarisFileWrapper);
                }
            }
            if (z) {
                if (arrayList instanceof CopyOnWriteArrayList) {
                    arrayList.addAll(0, arrayList2);
                    copyOnWriteArrayList = arrayList;
                } else {
                    copyOnWriteArrayList = new CopyOnWriteArrayList();
                    copyOnWriteArrayList.addAll(arrayList2);
                    copyOnWriteArrayList.addAll(arrayList);
                    fieldA.set(obj, copyOnWriteArrayList);
                }
            } else if (arrayList instanceof CopyOnWriteArrayList) {
                arrayList.addAll(arrayList2);
                copyOnWriteArrayList = arrayList;
            } else {
                copyOnWriteArrayList = new CopyOnWriteArrayList();
                copyOnWriteArrayList.addAll(arrayList);
                copyOnWriteArrayList.addAll(arrayList2);
                fieldA.set(obj, copyOnWriteArrayList);
            }
            Object obj3 = ReflectUtils.a(obj.getClass(), "systemNativeLibraryDirectories").get(obj);
            List arrayList3 = obj3 == null ? new ArrayList(2) : (List) obj3;
            ArrayList arrayList4 = new ArrayList(copyOnWriteArrayList.size() + arrayList3.size() + 1);
            if (z) {
                arrayList4.addAll(arrayList2);
                arrayList4.addAll(arrayList);
                arrayList4.addAll(arrayList3);
            } else {
                arrayList4.addAll(arrayList);
                arrayList4.addAll(arrayList2);
                arrayList4.addAll(arrayList3);
            }
            ReflectUtils.a(obj.getClass(), "nativeLibraryPathElements").set(obj, (Object[]) ReflectUtils.b(obj, List.class).invoke(obj, arrayList4));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
package com.bytedance.zoin.lib.inception;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.bytedance.zoin.utils.ReflectUtils;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes30.dex */
public class InceptionV1 implements IInception {
    @Override // com.bytedance.zoin.lib.inception.IInception
    public final boolean a(ClassLoader classLoader, String str, boolean z) {
        synchronized (InceptionV1.class) {
            try {
                Object obj = ReflectUtils.a(BaseDexClassLoader.class, "pathList").get(classLoader);
                Field fieldA = ReflectUtils.a(obj.getClass(), "nativeLibraryDirectories");
                Object obj2 = fieldA.get(obj);
                File[] fileArr = obj2 == null ? new File[0] : (File[]) obj2;
                if (str == null) {
                    str = "";
                }
                String[] strArrSplit = str.split(":");
                ArrayList arrayList = new ArrayList(strArrSplit.length);
                List listAsList = Arrays.asList(fileArr);
                for (String str2 : strArrSplit) {
                    PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(str2);
                    if ((polarisFileWrapper.exists() || polarisFileWrapper.mkdirs()) && !listAsList.contains(polarisFileWrapper)) {
                        arrayList.add(polarisFileWrapper);
                    }
                }
                ArrayList arrayList2 = new ArrayList(fileArr.length + arrayList.size());
                if (z) {
                    arrayList2.addAll(arrayList);
                    Collections.addAll(arrayList2, fileArr);
                } else {
                    Collections.addAll(arrayList2, fileArr);
                    arrayList2.addAll(arrayList);
                }
                fieldA.set(obj, arrayList2.toArray(new File[0]));
            } catch (Exception unused) {
                return false;
            }
        }
        return true;
    }
}
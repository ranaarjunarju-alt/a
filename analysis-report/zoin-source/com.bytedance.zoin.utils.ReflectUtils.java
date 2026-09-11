package com.bytedance.zoin.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/* loaded from: classes38.dex */
public class ReflectUtils {
    public static Field a(Class<?> cls, String str) {
        for (Class<?> superclass = cls; superclass != null; superclass = superclass.getSuperclass()) {
            try {
                Field declaredField = superclass.getDeclaredField(str);
                if (!declaredField.isAccessible()) {
                    declaredField.setAccessible(true);
                }
                return declaredField;
            } catch (NoSuchFieldException unused) {
            }
        }
        throw new NoSuchFieldException("Field " + str + " not found in " + cls);
    }

    public static Method b(Object obj, Class... clsArr) throws NoSuchMethodException, SecurityException {
        Class<?> superclass = obj.getClass();
        while (superclass != null) {
            try {
                Method declaredMethod = superclass.getDeclaredMethod("makePathElements", clsArr);
                if (!declaredMethod.isAccessible()) {
                    declaredMethod.setAccessible(true);
                }
                return declaredMethod;
            } catch (NoSuchMethodException unused) {
                superclass = superclass.getSuperclass();
            }
        }
        throw new NoSuchMethodException("Method makePathElements with parameters " + Arrays.asList(clsArr) + " not found in " + superclass);
    }
}
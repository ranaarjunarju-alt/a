package com.bytedance.zoin.lib.inception;

import android.content.Context;
import android.os.Build;

/* loaded from: classes11.dex */
public class Inception {
    public static boolean a(Context context, ClassLoader classLoader, String str, boolean z, boolean z2) {
        IInception inceptionV1;
        int i = Build.VERSION.PREVIEW_SDK_INT != 0 ? Build.VERSION.SDK_INT + 1 : Build.VERSION.SDK_INT;
        if (i >= 26) {
            inceptionV1 = new InceptionV4();
        } else if (i >= 24) {
            inceptionV1 = new InceptionV3();
        } else if (i >= 23) {
            inceptionV1 = new InceptionV2();
        } else {
            if (i < 14) {
                return false;
            }
            inceptionV1 = new InceptionV1();
        }
        return inceptionV1.a(classLoader, str, z);
    }
}
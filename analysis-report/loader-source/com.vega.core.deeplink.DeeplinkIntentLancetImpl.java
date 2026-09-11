package com.vega.core.deeplink;

import android.content.Intent;
import android.net.Uri;
import com.vega.core.context.ContextExtKt;
import com.vega.core.deeplink.DeeplinkPackageConfig;
import com.vega.log.BLog;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes12.dex */
public final class DeeplinkIntentLancetImpl {

    /* renamed from: a, reason: collision with root package name */
    public static final DeeplinkIntentLancetImpl f79222a = new DeeplinkIntentLancetImpl();

    public static final void a(Intent intent) {
        if (intent != null) {
            String str = intent.getPackage();
            if (str == null || str.length() == 0) {
                f79222a.getClass();
                if (Intrinsics.areEqual(intent.getAction(), "android.intent.action.VIEW") && Intrinsics.areEqual(intent.getScheme(), "capcut")) {
                    DeeplinkPackageConfig.f79223a.getClass();
                    if (DeeplinkPackageConfig.Companion.a()) {
                        String strA = DeeplinkPackageConfig.f79224c.a();
                        if (strA == null || strA.length() == 0) {
                            ContextExtKt.app().packageName();
                            strA = "com.lemon.lvoverseas";
                        }
                        intent.setPackage(strA);
                        StringBuilder sb = new StringBuilder("setup ");
                        Uri data = intent.getData();
                        sb.append(data != null ? data.toString() : null);
                        sb.append(" package: ");
                        sb.append(strA);
                        BLog.i("DPLancet", sb.toString());
                    }
                }
            }
        }
    }

    public static final void b(Intent[] intentArr) {
        DeeplinkPackageConfig.f79223a.getClass();
        if (DeeplinkPackageConfig.Companion.a() && intentArr != null) {
            for (Intent intent : intentArr) {
                a(intent);
            }
        }
    }
}
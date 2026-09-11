package com.vega.iac.autogen.impl;

import com.bytedance.ttnet.TTNetInit;
import com.vega.iac.autogen.api.LynxConfigApi;
import java.lang.reflect.Field;

/* loaded from: classes33.dex */
public final class LynxConfigImpl implements LynxConfigApi {

    /* renamed from: a, reason: collision with root package name */
    public final String f104415a = "capcut";
    public final String b;

    public LynxConfigImpl() {
        Field declaredField;
        try {
            declaredField = TTNetInit.class.getDeclaredField("sNotifiedColdStartFinsish");
            declaredField.setAccessible(true);
        } catch (Throwable unused) {
        }
        String str = declaredField.getBoolean(null) ? "https://mon-va.byteoversea.com" : "https://mon-v2-boot.capcutapi.com";
        this.b = str;
    }

    @Override // com.vega.iac.autogen.api.LynxConfigApi
    public final String a() {
        return this.f104415a;
    }

    @Override // com.vega.iac.autogen.api.LynxConfigApi
    public final String getMonitorHost() {
        return this.b;
    }
}
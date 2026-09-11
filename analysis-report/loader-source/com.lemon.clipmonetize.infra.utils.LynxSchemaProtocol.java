package com.lemon.clipmonetize.infra.utils;

import X.C06W;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: classes35.dex */
public final class LynxSchemaProtocol {

    /* renamed from: a, reason: collision with root package name */
    public static final LynxSchemaProtocol f58335a;
    public static final /* synthetic */ LynxSchemaProtocol[] b;

    static {
        LynxSchemaProtocol lynxSchemaProtocol = new LynxSchemaProtocol("CAPCUT", 0, "capcut");
        f58335a = lynxSchemaProtocol;
        LynxSchemaProtocol[] lynxSchemaProtocolArr = {lynxSchemaProtocol, new LynxSchemaProtocol("DREAMINA", 1, "dreamina"), new LynxSchemaProtocol("RETOUCH", 2, "retouch"), new LynxSchemaProtocol("VIDEOCUT", 3, "videocut")};
        b = lynxSchemaProtocolArr;
        C06W.a(lynxSchemaProtocolArr);
    }

    public LynxSchemaProtocol(String str, int i, String str2) {
    }

    public static LynxSchemaProtocol valueOf(String str) {
        return (LynxSchemaProtocol) Enum.valueOf(LynxSchemaProtocol.class, str);
    }

    public static LynxSchemaProtocol[] values() {
        return (LynxSchemaProtocol[]) b.clone();
    }
}
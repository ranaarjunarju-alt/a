package com.lemon.clipmonetize.infra.utils;

import com.lemon.clipmonetize.infra.url.UrlEncoderUtil;
import java.util.ArrayList;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes24.dex */
public final class LynxUtilsKt {
    public static final String a(Map map) {
        LynxSchemaProtocol lynxSchemaProtocol = LynxSchemaProtocol.f58335a;
        Intrinsics.checkNotNullParameter(map, "");
        Intrinsics.checkNotNullParameter(lynxSchemaProtocol, "");
        if (map.containsKey("surl")) {
            map.remove("surl");
        }
        String str = (String) MapsKt__MapsKt.mapOf(TuplesKt.to("retouch", "main/retouch_lynx"), TuplesKt.to("videocut", "main/lynx"), TuplesKt.to("capcut", "main/lynx"), TuplesKt.to("dreamina", "main/lynx")).get("capcut");
        String str2 = str != null ? str : "";
        if (Intrinsics.areEqual(map.get("is_card"), (Object) 1)) {
            return "capcut://lynxview?" + b(map);
        }
        return "capcut://" + str2 + '?' + b(map);
    }

    public static final String b(Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(map, "");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, ? extends Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value != null) {
                arrayList.add(UrlEncoderUtil.a(key) + '=' + UrlEncoderUtil.a(value.toString()));
            }
        }
        return CollectionsKt___CollectionsKt.joinToString$default(arrayList, "&", null, null, 0, null, null, 62, null);
    }
}
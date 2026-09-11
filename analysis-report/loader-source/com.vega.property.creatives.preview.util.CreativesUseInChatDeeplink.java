package com.vega.property.creatives.preview.util;

import android.net.Uri;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.collections.SetsKt__SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes21.dex */
public final class CreativesUseInChatDeeplink {

    /* renamed from: a, reason: collision with root package name */
    public static final CreativesUseInChatDeeplink f126562a = new CreativesUseInChatDeeplink();
    public static final Map<String, String> b = MapsKt__MapsKt.mapOf(TuplesKt.to("pdf", "pdf"), TuplesKt.to("txt", "txt"), TuplesKt.to("md", "txt"), TuplesKt.to("doc", "doc"), TuplesKt.to("docx", "doc"), TuplesKt.to("xls", "xls"), TuplesKt.to("xlsx", "xls"), TuplesKt.to("csv", "xls"), TuplesKt.to("ppt", "ppt"), TuplesKt.to("pptx", "ppt"));

    /* renamed from: c, reason: collision with root package name */
    public static final Set<String> f126563c = SetsKt__SetsKt.setOf((Object[]) new String[]{"application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "text/csv"});

    public static String a(String str, String str2, String str3, String str4, String str5) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", str);
        jSONObject.put("id", str2);
        jSONObject.put("url", str3);
        jSONObject.put("coverUrl", str4);
        if (str5.length() > 0) {
            jSONObject.put("fileName", str5);
        }
        String string = new Uri.Builder().scheme("capcut").path("//ailab_agent/chat").appendQueryParameter("open_input_box", "true").appendQueryParameter("cloud_materials", jSONArray.put(jSONObject).toString()).build().toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return string;
    }

    public static boolean b(String str) {
        return str.length() > 0 && StringsKt__StringsJVMKt.equals(Uri.parse(str).getScheme(), "https", true);
    }
}
package com.bytedance.sdk.ttadn.core;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.sdk.ttadn.api.TtlinkAdData;
import com.bytedance.sdk.ttadn.api.TtlinkAdnAd;
import com.bytedance.sdk.ttadn.core.TtlinkGlobalInfo;
import com.bytedance.sdk.ttadn.ec;
import com.bytedance.sdk.ttadn.gf;
import com.bytedance.sdk.ttadn.i2;
import com.bytedance.sdk.ttadn.i8;
import com.bytedance.sdk.ttadn.l4;
import com.bytedance.sdk.ttadn.m2;
import com.bytedance.sdk.ttadn.rb;
import com.bytedance.sdk.ttadn.sb;
import com.bytedance.sdk.ttadn.tb;
import com.bytedance.sdk.ttadn.u0;
import com.bytedance.sdk.ttadn.z5;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public final class SplashClickHelper {
    public static i8 a(TtlinkAdData ttlinkAdData) throws JSONException {
        i8 i8Var = new i8();
        i8Var.a(ttlinkAdData.getAdId());
        i8Var.e(TextUtils.isEmpty(ttlinkAdData.getCreativeId()) ? ttlinkAdData.getAdId() : ttlinkAdData.getCreativeId());
        i8Var.b(ttlinkAdData.getAdvertiserId());
        i8Var.g(ttlinkAdData.getLogExtra());
        i8Var.o(ttlinkAdData.getWebTitle());
        i8Var.a(3);
        i8Var.d(ttlinkAdData.getInteractionType());
        i8Var.j(ttlinkAdData.getWebUrl());
        if (!TextUtils.isEmpty(ttlinkAdData.getOpenUrl())) {
            i2 i2Var = new i2();
            i2Var.a(ttlinkAdData.getOpenUrl());
            i8Var.a(i2Var);
        }
        if (!TextUtils.isEmpty(ttlinkAdData.getDownloadUrl()) || !TextUtils.isEmpty(ttlinkAdData.getPackageName())) {
            u0 u0Var = new u0();
            u0Var.a(ttlinkAdData.getDownloadUrl());
            u0Var.b(ttlinkAdData.getPackageName());
            i8Var.a(u0Var);
        }
        JSONObject jSONObject = new JSONObject();
        try {
            Long lA = a(i8Var.h(), (Long) null);
            if (lA != null) {
                jSONObject.put("creative_id", lA);
            }
            if (!TextUtils.isEmpty(i8Var.l())) {
                jSONObject.put("log_extra", i8Var.l());
            }
            Long lB = b(i8Var.l());
            if (lB != null) {
                jSONObject.put("group_id", lB);
            }
            jSONObject.put("system_origin", 1);
            jSONObject.put("item_id", i8Var.a());
            Long lA2 = a(i8Var.a(), (Long) null);
            if (lA2 != null) {
                jSONObject.put("ad_id", lA2);
            }
            jSONObject.put("source", "splash");
            if (!TextUtils.isEmpty(i8Var.B())) {
                jSONObject.put("web_url", i8Var.B());
            }
            if (!TextUtils.isEmpty(i8Var.K())) {
                jSONObject.put("web_title", i8Var.K());
                jSONObject.put("title", i8Var.K());
            }
            if (i8Var.i() != null && !TextUtils.isEmpty(i8Var.i().a())) {
                jSONObject.put("open_url", i8Var.i().a());
            }
            if (i8Var.c() != null) {
                if (!TextUtils.isEmpty(i8Var.c().a())) {
                    jSONObject.put("download_url", i8Var.c().a());
                }
                if (!TextUtils.isEmpty(i8Var.c().b())) {
                    jSONObject.put("package", i8Var.c().b());
                }
            }
            if (!TextUtils.isEmpty(ttlinkAdData.getChannelName())) {
                jSONObject.put("channel_name", ttlinkAdData.getChannelName());
            }
            if (!TextUtils.isEmpty(ttlinkAdData.getAppName())) {
                jSONObject.put("app_name", ttlinkAdData.getAppName());
            }
        } catch (JSONException unused) {
        }
        i8Var.f46108a = new JsonParser().parse(jSONObject.toString());
        return i8Var;
    }

    public static Long a(String str, Long l) {
        if (TextUtils.isEmpty(str)) {
            return l;
        }
        try {
            return Long.valueOf(Long.parseLong(str));
        } catch (Throwable unused) {
            return l;
        }
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iIndexOf = str.indexOf("?id=");
        String strSubstring = iIndexOf >= 0 ? str.substring(iIndexOf + 4) : "";
        if (TextUtils.isEmpty(strSubstring) && str.startsWith("market://details?id=")) {
            strSubstring = str.substring(20);
        }
        if (TextUtils.isEmpty(strSubstring)) {
            return "";
        }
        int iIndexOf2 = strSubstring.indexOf(38);
        return iIndexOf2 >= 0 ? strSubstring.substring(0, iIndexOf2) : strSubstring;
    }

    public static String a(String str, String str2, String str3) {
        String strOptString;
        if (TextUtils.isEmpty(str)) {
            return str3;
        }
        try {
            strOptString = new JSONObject(str).optString(str2);
        } catch (Throwable unused) {
        }
        return !TextUtils.isEmpty(strOptString) ? strOptString : str3;
    }

    public static boolean a() {
        return "3006".equals(TtlinkGlobalInfo.b.f45808a.getAppId());
    }

    public static boolean a(Context context, i8 i8Var) {
        z5 z5VarA;
        if (i8Var.c() == null || (z5VarA = ec.a(context, i8Var, "splash_ad")) == null) {
            return false;
        }
        if (z5VarA.a()) {
            return true;
        }
        return z5VarA.c();
    }

    public static boolean a(Context context, i8 i8Var, String str) {
        if (!TtlinkGlobalInfo.b.f45808a.isEnableIABHost()) {
            return false;
        }
        tb splashIabSchemaProvider = TtlinkGlobalInfo.b.f45808a.getSplashIabSchemaProvider();
        if (splashIabSchemaProvider == null || !splashIabSchemaProvider.a()) {
            splashIabSchemaProvider = new m2();
            TtlinkGlobalInfo.b.f45808a.setSplashIabSchemaProvider(splashIabSchemaProvider);
        }
        Long lA = a(i8Var.h(), a(i8Var.a(), (Long) null));
        String strValueOf = lA == null ? null : String.valueOf(lA);
        String strL = i8Var.l();
        sb sbVarA = splashIabSchemaProvider.a(new rb(i8Var, str, i8Var.i() == null ? null : i8Var.i().a(), i8Var.c() != null ? i8Var.c().a() : null, "splash_ad", 4, lA, strValueOf, strL, b(strL), "splash", a(strL, "channel_name", a(strL, "channelName", (String) null)), i8Var.c() == null ? a(str) : i8Var.c().b(), i8Var.K(), i8Var.K()));
        if (sbVarA == null || sbVarA.b() == null || sbVarA.b().f46108a == null || sbVarA.a() == null) {
            return false;
        }
        return l4.a(context, sbVarA.b(), sbVarA.a(), l4.a(context, i8Var, 4, null, "splash_ad", str));
    }

    public static Long b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("template_ad_id")) {
                return Long.valueOf(jSONObject.optLong("template_ad_id"));
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static boolean b(Context context, i8 i8Var) {
        i2 i2VarI = i8Var.i();
        if (i2VarI == null || TextUtils.isEmpty(i2VarI.a())) {
            return false;
        }
        Uri uri = Uri.parse(i2VarI.a());
        String scheme = uri.getScheme();
        if (a() && "sslocal".equalsIgnoreCase(scheme)) {
            i2VarI.a(uri.buildUpon().scheme("capcut").build().toString());
        }
        return gf.a(context, i8Var, "splash_ad");
    }

    public static boolean c(Context context, i8 i8Var) {
        String strB = i8Var.B();
        if (TextUtils.isEmpty(strB)) {
            return false;
        }
        if (strB.contains("play.google.com/store/apps/details?id=")) {
            String strA = a(strB);
            z5 z5VarA = ec.a(context, i8Var, "splash_ad");
            if (z5VarA != null && z5VarA.a(context, strB, strA)) {
                return true;
            }
        }
        if (a(context, i8Var, strB)) {
            return true;
        }
        return gf.a(context, i8Var, 4, (TtlinkAdnAd) null, "splash_ad", strB);
    }

    public static boolean handleSplashClick(Context context, TtlinkAdData ttlinkAdData) {
        i8 i8VarA;
        if (context == null || ttlinkAdData == null || (i8VarA = a(ttlinkAdData)) == null) {
            return false;
        }
        return b(context, i8VarA) || c(context, i8VarA) || a(context, i8VarA);
    }
}
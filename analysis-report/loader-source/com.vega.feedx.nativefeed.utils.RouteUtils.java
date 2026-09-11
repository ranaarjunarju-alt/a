package com.vega.feedx.nativefeed.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.vega.core.context.SPIService;
import com.vega.core.deeplink.DeeplinkIntentLancetImpl;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.ext.FunctionKt;
import com.vega.core.ext.IntentExKt;
import com.vega.feedx.nativefeed.feedlist.card.multicard.survey.FeedSurveyCardInfo;
import com.vega.feelgoodapi.model.Questionnaire;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.lynx.IFeedxLynxSchemeCapCut;
import com.vega.main.config.FlavorMainConfig;
import com.vega.setting.SearchPageConfigKt;
import com.vega.setting.SearchPageOptConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes22.dex */
public final class RouteUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final RouteUtils f101853a = new RouteUtils();
    public static final boolean b = ((FlavorMainConfig) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(FlavorMainConfig.class), null)).getDynamicDiscover().c();

    public static void INVOKEVIRTUAL_com_vega_feedx_nativefeed_utils_RouteUtils_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(Activity activity, Intent intent) {
        DeeplinkIntentLancetImpl.a(intent);
        HeliosApiHook heliosApiHook = new HeliosApiHook();
        Object[] objArr = {intent};
        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/content/Intent;)V", "dzBzEhQ/WMuSU1IkQQrKaO66Ldauz12+wZWBiIPlJjgGHV5QBgdvsjWa");
        if (heliosApiHook.preInvoke(11098, "android/app/Activity", "startActivity", activity, objArr, "void", extraInfo).isIntercept()) {
            heliosApiHook.postInvoke(null, 11098, "android/app/Activity", "startActivity", activity, objArr, extraInfo, false);
        } else {
            activity.startActivity(intent);
            heliosApiHook.postInvoke(null, 11098, "android/app/Activity", "startActivity", activity, objArr, extraInfo, true);
        }
    }

    public static Uri.Builder a(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        Uri.Builder builderAppendQueryParameter = new Uri.Builder().scheme("capcut").path("//main/lynx").appendQueryParameter("channel", str);
        Intrinsics.checkNotNullExpressionValue(builderAppendQueryParameter, "");
        return builderAppendQueryParameter;
    }

    public static void b(RouteUtils routeUtils, Activity activity, Uri uri, JSONObject jSONObject) {
        routeUtils.getClass();
        Bundle bundle = new Bundle();
        if (((SearchPageOptConfig) SearchPageConfigKt.f130063a.getValue()).b()) {
            String string = uri.toString();
            Intrinsics.checkNotNullExpressionValue(string, "");
            FunctionKt.j(activity, string, true, jSONObject, null, bundle);
        } else {
            if (jSONObject != null) {
                IntentExKt.f(bundle, "lynx_data", jSONObject.toString());
            }
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            intent.putExtras(bundle);
            INVOKEVIRTUAL_com_vega_feedx_nativefeed_utils_RouteUtils_com_vega_core_deeplink_DeeplinkIntentLancet_startActivity(activity, intent);
        }
    }

    public static void c(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        FunctionKt.k(ModuleCommon.INSTANCE.getApplication(), str, true, null, null, null, 56);
    }

    public static Uri e(Uri uri, Map map) {
        Uri.Builder builderClearQuery = uri.buildUpon().clearQuery();
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        Intrinsics.checkNotNullExpressionValue(queryParameterNames, "");
        for (String str : queryParameterNames) {
            if (!map.containsKey(str)) {
                List<String> queryParameters = uri.getQueryParameters(str);
                Intrinsics.checkNotNullExpressionValue(queryParameters, "");
                Iterator<T> it = queryParameters.iterator();
                while (it.hasNext()) {
                    builderClearQuery.appendQueryParameter(str, (String) it.next());
                }
            }
        }
        for (Map.Entry entry : map.entrySet()) {
            builderClearQuery.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
        }
        Uri uriBuild = builderClearQuery.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "");
        return uriBuild;
    }

    public final void d(Activity activity, FeedSurveyCardInfo feedSurveyCardInfo, List<String> list, JSONObject jSONObject) throws JSONException {
        String json;
        Intrinsics.checkNotNullParameter(activity, "");
        Intrinsics.checkNotNullParameter(feedSurveyCardInfo, "");
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(jSONObject, "");
        JSONObject jSONObject2 = null;
        Uri uriE = e(Uri.parse(((IFeedxLynxSchemeCapCut) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IFeedxLynxSchemeCapCut.class), null)).d()), MapsKt__MapsJVMKt.mapOf(TuplesKt.to("hide_loading", ProfileManager.VERSION)));
        JSONObject jSONObject3 = new JSONObject();
        Questionnaire questionnaire = feedSurveyCardInfo.b;
        if (questionnaire != null && (json = ExtentionKt.toJson(questionnaire)) != null) {
            jSONObject2 = new JSONObject(json);
        }
        jSONObject3.put("questionnaire", jSONObject2);
        jSONObject3.put("showQuestionType", feedSurveyCardInfo.f101583a.getStyleType() == 0 ? 4 : 2);
        jSONObject3.put("survey", new JSONObject(ExtentionKt.toJson(feedSurveyCardInfo.f101583a)));
        jSONObject3.put("selectedKey", new JSONArray(ExtentionKt.toJson(list)));
        jSONObject3.put("extra", jSONObject);
        b(this, activity, uriE, jSONObject3);
    }
}
package com.vega.launcher.debug;

import android.app.Application;
import android.content.ContentResolver;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.bytedance.helios.statichook.api.Result;
import com.bytedance.services.apm.api.EnsureManager;
import com.lm.components.utils.AssistToolQuery;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.kv.keva.KevaSpAopHook;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt__SequencesKt;
import kotlin.text.StringsKt__StringsKt;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public final class AssistConfig {

    /* renamed from: a, reason: collision with root package name */
    public static final AssistConfig f107239a = new AssistConfig();
    public static final SharedPreferences b = INVOKEVIRTUAL_com_vega_launcher_debug_AssistConfig_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(ModuleCommon.INSTANCE.getApplication(), "com.lemon.lvoverseas_preferences", 0);

    /* renamed from: c, reason: collision with root package name */
    public static final Lazy f107240c = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$openBoeEnv$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    });

    /* renamed from: d, reason: collision with root package name */
    public static final Lazy f107241d = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$host$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ String invoke() {
            return "editor-api.capcutapi.com";
        }
    });
    public static final Lazy e = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$region$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            AssistConfig.f107239a.getClass();
            return AssistConfig.b("lv_pref_key_region", "");
        }
    });
    public static final Lazy f = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$abGroup$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() throws Throwable {
            SharedPreferences sharedPreferences = AssistConfig.b;
            String strB = "";
            if (sharedPreferences.contains("lv_key_local_ab_test_config")) {
                String string = sharedPreferences.getString("lv_key_local_ab_test_config", "");
                if (string != null) {
                    strB = string;
                }
            } else {
                AssistConfig.f107239a.getClass();
                strB = AssistConfig.b("lv_key_local_ab_test_config", "");
            }
            Intrinsics.checkNotNull(strB);
            return strB;
        }
    });

    /* renamed from: g, reason: collision with root package name */
    public static final Lazy f107242g = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$etHost$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ String invoke() {
            return "";
        }
    });
    public static final Lazy h = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$boeHost$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final /* bridge */ /* synthetic */ String invoke() {
            return "xxx";
        }
    });
    public static final Lazy i = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$logToLogcat$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            boolean z2 = false;
            if (!sharedPreferences.contains("lv_pref_key_log_to_logcat") ? AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_log_to_logcat") : sharedPreferences.getBoolean("lv_pref_key_log_to_logcat", false)) {
                z2 = true;
            }
            return Boolean.valueOf(z2);
        }
    });
    public static final Lazy j = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$reportToDebugEnv$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_report_debug_env") ? sharedPreferences.getBoolean("lv_pref_key_report_debug_env", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_report_debug_env"));
        }
    });
    public static final Lazy k = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends String>>() { // from class: com.vega.launcher.debug.AssistConfig$boeHostWhiteList$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final List<? extends String> invoke() {
            List listSplit$default;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (!sharedPreferences.contains("lv_pref_key_boe_hosts_white_list")) {
                AssistConfig.f107239a.getClass();
                List listSplit$default2 = StringsKt__StringsKt.split$default(AssistConfig.b("lv_pref_key_boe_hosts_white_list", ""), new String[]{","}, false, 0, 6, (Object) null);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listSplit$default2) {
                    if (((String) obj).length() > 0) {
                        arrayList.add(obj);
                    }
                }
                return arrayList;
            }
            String string = sharedPreferences.getString("lv_pref_key_boe_hosts_white_list", "");
            if (string == null || (listSplit$default = StringsKt__StringsKt.split$default(string, new String[]{","}, false, 0, 6, (Object) null)) == null) {
                return CollectionsKt__CollectionsKt.emptyList();
            }
            ArrayList arrayList2 = new ArrayList();
            for (Object obj2 : listSplit$default) {
                if (((String) obj2).length() > 0) {
                    arrayList2.add(obj2);
                }
            }
            return arrayList2;
        }
    });
    public static final Lazy l = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$effectDebugChannel$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_effect_debug_env") ? sharedPreferences.getBoolean("lv_pref_key_effect_debug_env", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_effect_debug_env"));
        }
    });
    public static final Lazy m = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$effectModuleDebugChannel$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_key_effect_module_debug_env") ? sharedPreferences.getBoolean("lv_key_effect_module_debug_env", false) : AssistConfig.d(AssistConfig.f107239a, "lv_key_effect_module_debug_env"));
        }
    });
    public static final Lazy n = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$isEtEnabled$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_open_et") ? sharedPreferences.getBoolean("lv_pref_key_open_et", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_open_et"));
        }
    });
    public static final Lazy o = LazyKt__LazyJVMKt.lazy(new Function0<Map<String, String>>() { // from class: com.vega.launcher.debug.AssistConfig$userHeaders$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Map<String, String> invoke() throws Throwable {
            LinkedHashMap linkedHashMap;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pref_key_user_headers")) {
                linkedHashMap = new LinkedHashMap();
                String string = sharedPreferences.getString("lv_pref_key_user_headers", "");
                if (string != null && string.length() > 0) {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator<String> itKeys = jSONObject.keys();
                    Intrinsics.checkNotNullExpressionValue(itKeys, "");
                    for (String str : SequencesKt__SequencesKt.asSequence(itKeys)) {
                        Intrinsics.checkNotNull(str);
                        String strOptString = jSONObject.optString(str, "");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "");
                        linkedHashMap.put(str, strOptString);
                    }
                }
            } else {
                AssistConfig.f107239a.getClass();
                String strB = AssistConfig.b("lv_pref_key_user_headers", "");
                linkedHashMap = new LinkedHashMap();
                if (strB.length() > 0) {
                    JSONObject jSONObject2 = new JSONObject(strB);
                    Iterator<String> itKeys2 = jSONObject2.keys();
                    Intrinsics.checkNotNullExpressionValue(itKeys2, "");
                    for (String str2 : SequencesKt__SequencesKt.asSequence(itKeys2)) {
                        Intrinsics.checkNotNull(str2);
                        String strOptString2 = jSONObject2.optString(str2, "");
                        Intrinsics.checkNotNullExpressionValue(strOptString2, "");
                        linkedHashMap.put(str2, strOptString2);
                    }
                }
            }
            return linkedHashMap;
        }
    });
    public static final Lazy p = LazyKt__LazyJVMKt.lazy(new Function0<Map<String, String>>() { // from class: com.vega.launcher.debug.AssistConfig$geckoHeaders$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Map<String, String> invoke() throws Throwable {
            LinkedHashMap linkedHashMap;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pref_key_gecko_headers")) {
                linkedHashMap = new LinkedHashMap();
                String string = sharedPreferences.getString("lv_pref_key_gecko_headers", null);
                if (string != null && string.length() > 0) {
                    JSONObject jSONObject = new JSONObject(string);
                    Iterator<String> itKeys = jSONObject.keys();
                    Intrinsics.checkNotNullExpressionValue(itKeys, "");
                    for (String str : SequencesKt__SequencesKt.asSequence(itKeys)) {
                        Intrinsics.checkNotNull(str);
                        String strOptString = jSONObject.optString(str, "");
                        Intrinsics.checkNotNullExpressionValue(strOptString, "");
                        linkedHashMap.put(str, strOptString);
                    }
                }
            } else {
                AssistConfig.f107239a.getClass();
                String strB = AssistConfig.b("lv_pref_key_gecko_headers", "");
                linkedHashMap = new LinkedHashMap();
                if (strB.length() > 0) {
                    JSONObject jSONObject2 = new JSONObject(strB);
                    Iterator<String> itKeys2 = jSONObject2.keys();
                    Intrinsics.checkNotNullExpressionValue(itKeys2, "");
                    for (String str2 : SequencesKt__SequencesKt.asSequence(itKeys2)) {
                        Intrinsics.checkNotNull(str2);
                        String strOptString2 = jSONObject2.optString(str2, "");
                        Intrinsics.checkNotNullExpressionValue(strOptString2, "");
                        linkedHashMap.put(str2, strOptString2);
                    }
                }
            }
            return linkedHashMap;
        }
    });
    public static final Lazy q = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$hideGif$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_hide_gif") ? sharedPreferences.getBoolean("lv_pref_key_hide_gif", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_hide_gif"));
        }
    });
    public static final Lazy r = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableImportDraft$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_import_draft") ? sharedPreferences.getBoolean("lv_pref_key_import_draft", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_import_draft"));
        }
    });
    public static final Lazy s = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableExportDraft$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_export_draft") ? sharedPreferences.getBoolean("lv_pref_key_export_draft", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_export_draft"));
        }
    });
    public static final Lazy t = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableLynxDebug$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_lynx_debug") ? sharedPreferences.getBoolean("lv_pref_key_lynx_debug", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_lynx_debug"));
        }
    });
    public static final Lazy u = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableGeckoDebug$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_gecko_debug") ? sharedPreferences.getBoolean("lv_pref_key_gecko_debug", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_gecko_debug"));
        }
    });
    public static final Lazy v = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableWebViewHock$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_hock_webview") ? sharedPreferences.getBoolean("lv_pref_key_hock_webview", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_hock_webview"));
        }
    });
    public static final Lazy w = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$anyWhereDoor$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.FALSE;
        }
    });
    public static final Lazy x = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$webDebugToolUrl$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pref_key_web_debug_tool_url")) {
                String string = sharedPreferences.getString("lv_pref_key_web_debug_tool_url", "");
                return string == null ? "" : string;
            }
            AssistConfig.f107239a.getClass();
            return AssistConfig.b("lv_pref_key_web_debug_tool_url", "");
        }
    });
    public static final Lazy y = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableDevEntrance$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            SharedPreferences sharedPreferences = AssistConfig.b;
            return Boolean.valueOf(sharedPreferences.contains("lv_pref_key_dev_setting_visable") ? sharedPreferences.getBoolean("lv_pref_key_dev_setting_visable", false) : AssistConfig.d(AssistConfig.f107239a, "lv_pref_key_dev_setting_visable"));
        }
    });
    public static final Lazy z = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableAutoDownloadDraftByDeepLink$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(AssistConfig.d(AssistConfig.f107239a, "lv_key_auto_test_download"));
        }
    });
    public static final Lazy A = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableCameraEffectSDK$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zC;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pref_camera_effect_sdk_enable")) {
                zC = sharedPreferences.getBoolean("lv_pref_camera_effect_sdk_enable", true);
            } else {
                AssistConfig.f107239a.getClass();
                zC = AssistConfig.c("lv_pref_camera_effect_sdk_enable", true);
            }
            return Boolean.valueOf(zC);
        }
    });
    public static final Lazy B = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$disableEncryptAllDraft$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zC;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pref_key_encrypt_disable_all")) {
                zC = sharedPreferences.getBoolean("lv_pref_key_encrypt_disable_all", false);
            } else {
                AssistConfig.f107239a.getClass();
                zC = AssistConfig.c("lv_pref_key_encrypt_disable_all", false);
            }
            return Boolean.valueOf(zC);
        }
    });
    public static final Lazy C = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$disableEncryptDraftExport$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zC;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pref_key_draft_export_encrypt_disable")) {
                zC = sharedPreferences.getBoolean("lv_pref_key_draft_export_encrypt_disable", false);
            } else {
                AssistConfig.f107239a.getClass();
                zC = AssistConfig.c("lv_pref_key_draft_export_encrypt_disable", false);
            }
            return Boolean.valueOf(zC);
        }
    });
    public static final Lazy D = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$pitayaRemoteUrl$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() throws Throwable {
            String strB;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_pitaya_remote_debug_url")) {
                strB = sharedPreferences.getString("lv_pitaya_remote_debug_url", "");
            } else {
                AssistConfig.f107239a.getClass();
                strB = AssistConfig.b("lv_pitaya_remote_debug_url", "");
            }
            return strB == null ? "" : strB;
        }
    });
    public static final Lazy E = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$veOrEffectLogLevel$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() throws Throwable {
            String strB;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("lv_key_ve_or_effect_log_level")) {
                strB = sharedPreferences.getString("lv_key_ve_or_effect_log_level", "Info");
            } else {
                AssistConfig.f107239a.getClass();
                strB = AssistConfig.b("lv_key_ve_or_effect_log_level", "Info");
            }
            return strB == null ? "Info" : strB;
        }
    });
    public static final Lazy F = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$isTestChannel$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(AssistConfig.b.getBoolean("lv_pref_ley_channel", false));
        }
    });
    public static final Lazy G = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$fpsReportDebugEnable$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(AssistConfig.b.getBoolean("fps_report_debug_open", false));
        }
    });
    public static final Lazy H = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.launcher.debug.AssistConfig$enableMockNewUserQuestion$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            boolean zC;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("new_install_user_question_mock")) {
                zC = sharedPreferences.getBoolean("new_install_user_question_mock", false);
            } else {
                AssistConfig.f107239a.getClass();
                zC = AssistConfig.c("new_install_user_question_mock", false);
            }
            return Boolean.valueOf(zC);
        }
    });
    public static final Lazy I = LazyKt__LazyJVMKt.lazy(new Function0<String>() { // from class: com.vega.launcher.debug.AssistConfig$getMock2025NewUserQstLibGp$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() throws Throwable {
            String strB;
            SharedPreferences sharedPreferences = AssistConfig.b;
            if (sharedPreferences.contains("2025_newuser_question_mock_lib_group")) {
                strB = sharedPreferences.getString("2025_newuser_question_mock_lib_group", "");
            } else {
                AssistConfig.f107239a.getClass();
                strB = AssistConfig.b("2025_newuser_question_mock_lib_group", "");
            }
            return strB == null ? "" : strB;
        }
    });

    public static SharedPreferences INVOKEVIRTUAL_com_vega_launcher_debug_AssistConfig_com_vega_launcher_lancet_SharedPreferencesLancet_getSharedPreferences(Application application, String str, int i2) throws InterruptedException {
        try {
            return KevaSpAopHook.getSharedPreferences(application, str, i2);
        } catch (NullPointerException e2) {
            try {
                Thread.sleep(0L);
            } catch (InterruptedException e3) {
                e3.printStackTrace();
            }
            if (e2.getMessage() != null && e2.getMessage().contains("isUserKeyUnlocked")) {
                EnsureManager.ensureNotReachHere(e2, "getSharedPreferences isUserKeyUnlocked NullPointerException name=" + str + ",mode" + i2);
            }
            return KevaSpAopHook.getSharedPreferences(application, str, i2);
        }
    }

    public static Cursor a(ContentResolver contentResolver, Uri uri, String[] strArr, String[] strArr2) {
        HeliosApiHook heliosApiHook = new HeliosApiHook();
        Object[] objArr = {uri, strArr, "key=?", strArr2, null};
        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;", "dzBzEhQ/WMuSWVY0SxGMY1nwFUkHasmymiWcYh/FifvnQgRM");
        Result resultPreInvoke = heliosApiHook.preInvoke(240004, "android/content/ContentResolver", "query", contentResolver, objArr, "android.database.Cursor", extraInfo);
        if (resultPreInvoke.isIntercept()) {
            heliosApiHook.postInvoke(null, 240004, "android/content/ContentResolver", "query", contentResolver, objArr, extraInfo, false);
            return (Cursor) resultPreInvoke.getReturnValue();
        }
        Cursor cursorQuery = contentResolver.query(uri, strArr, "key=?", strArr2, null);
        heliosApiHook.postInvoke(cursorQuery, 240004, "android/content/ContentResolver", "query", contentResolver, objArr, extraInfo, true);
        return cursorQuery;
    }

    public static String b(String str, String str2) throws Throwable {
        AssistToolQuery assistToolQuery = AssistToolQuery.f61176a;
        ModuleCommon moduleCommon = ModuleCommon.INSTANCE;
        Application application = moduleCommon.getApplication();
        assistToolQuery.getClass();
        if (AssistToolQuery.b(application)) {
            Cursor cursor = null;
            str = null;
            String str3 = null;
            Cursor cursor2 = null;
            try {
                boolean z2 = true;
                Cursor cursorA = a(moduleCommon.getApplication().getContentResolver(), Uri.parse("content://com.lemon.faceuassist.provider"), new String[]{"value"}, new String[]{str});
                if (cursorA != null) {
                    try {
                        if (cursorA.moveToFirst()) {
                            String string = cursorA.getString(cursorA.getColumnIndex("value"));
                            if (string != null) {
                                if (string.length() <= 0) {
                                    z2 = false;
                                }
                                if (z2) {
                                    str3 = string;
                                }
                            }
                        } else {
                            str3 = str2;
                        }
                        if (str3 != null) {
                            str2 = str3;
                        }
                    } catch (Exception unused) {
                        cursor2 = cursorA;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        return str2;
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorA;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
                if (cursorA != null) {
                    cursorA.close();
                }
            } catch (Exception unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
        }
        return str2;
    }

    public static boolean c(String str, boolean z2) {
        return z2 ? Intrinsics.areEqual(b(str, "true"), "true") : Intrinsics.areEqual(b(str, "false"), "true");
    }

    public static /* synthetic */ boolean d(AssistConfig assistConfig, String str) {
        assistConfig.getClass();
        return c(str, false);
    }

    public static String e(String str) {
        Object objCreateFailure;
        Intrinsics.checkNotNullParameter(str, "");
        try {
            f107239a.getClass();
            objCreateFailure = new JSONObject((String) f.getValue()).optString(str, "");
            kotlin.Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            kotlin.Result.m17090constructorimpl(objCreateFailure);
        }
        return (String) (kotlin.Result.m17096isFailureimpl(objCreateFailure) ? "" : objCreateFailure);
    }
}
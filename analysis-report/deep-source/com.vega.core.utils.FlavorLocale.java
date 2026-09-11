package com.vega.core.utils;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.os.SystemClock;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bytedance.bpea.tt.entry.api.device.info.CarrierEntry;
import com.bytedance.bpea.tt.entry.api.device.info.LocaleEntry;
import com.vega.core.privacy.looki.LookiRegionManager;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.infrastructure.util.AppLanguageUtils;
import com.vega.log.BLog;
import com.vega.performance.PerformanceManagerHelper;
import com.vega.start.logic.StartLifeDispatcher;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes3.dex */
public final class FlavorLocale {
    public static long e;

    /* renamed from: a, reason: collision with root package name */
    public static final FlavorLocale f79592a = new FlavorLocale();
    public static final Map<String, String> b = MapsKt__MapsKt.mapOf(TuplesKt.to("de", "de-DE"), TuplesKt.to("id", "id-ID"), TuplesKt.to("in", "id-ID"), TuplesKt.to("ja", "ja-JP"), TuplesKt.to("ko", "ko-KR"), TuplesKt.to("ms", "ms-MY"), TuplesKt.to("ru", "ru-RU"), TuplesKt.to("th", "th-TH"), TuplesKt.to("tr", "tr-TR"), TuplesKt.to("vi", "vi-VN"), TuplesKt.to("jv", "jv-MY"), TuplesKt.to("ceb", "ceb-PH"), TuplesKt.to("ce", "cs-CZ"), TuplesKt.to("it", "it-IT"), TuplesKt.to("hu", "hu-HU"), TuplesKt.to("nl", "nl-NL"), TuplesKt.to("pl", "pl-PL"), TuplesKt.to("pt", "pt-BR"), TuplesKt.to("pt-br", "pt-BR"), TuplesKt.to("ro", "ro-RO"), TuplesKt.to("sv", "sv-SE"), TuplesKt.to("fil", "fil-PH"), TuplesKt.to("el", "el-GR"), TuplesKt.to("uk", "uk-UA"), TuplesKt.to("mr", "mr-IN"), TuplesKt.to("hi", "hi-IN"), TuplesKt.to("bn", "bn-IN"), TuplesKt.to("pa", "pa-IN"), TuplesKt.to("gu", "gu-IN"), TuplesKt.to("or", "or-IN"), TuplesKt.to("ta", "ta-IN"), TuplesKt.to("te", "te-IN"), TuplesKt.to("kn", "kn-IN"), TuplesKt.to("ml", "ml-IN"), TuplesKt.to("my", "my-MM"), TuplesKt.to("km", "km-KH"), TuplesKt.to("th", "th-TH"), TuplesKt.to("es", "es-LA"), TuplesKt.to("fr", "fr-FR"), TuplesKt.to("ar", "ar"), TuplesKt.to("my", "my-MM"), TuplesKt.to("ro", "ro-RO"), TuplesKt.to("nl", "nl-NL"), TuplesKt.to("cs", "cs-CZ"), TuplesKt.to("el", "el-GR"), TuplesKt.to("hu", "hu-HU"), TuplesKt.to("sv", "sv-SE"), TuplesKt.to("fi", "fi-FI"), TuplesKt.to("iw", "he-IL"), TuplesKt.to("ur", "ur-PK"));

    /* renamed from: c, reason: collision with root package name */
    public static final CopyOnWriteArrayList<RegionChangedListener> f79593c = new CopyOnWriteArrayList<>();

    /* renamed from: d, reason: collision with root package name */
    public static String f79594d = "";
    public static String f = "";

    public static String INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(Locale locale) {
        String language = locale.getLanguage();
        if (Build.VERSION.SDK_INT <= 34) {
            return language;
        }
        language.getClass();
        switch (language.hashCode()) {
            case 3325:
                return !language.equals("he") ? language : "iw";
            case 3355:
                return !language.equals("id") ? language : "in";
            case 3856:
                return !language.equals("yi") ? language : "ji";
            default:
                return language;
        }
    }

    public static String a(String str, Locale locale) {
        return (Intrinsics.areEqual(str, "zh-Hant-HK") || Intrinsics.areEqual(str, "zh-Hant-MO") || Intrinsics.areEqual(str, "zh-HK") || Intrinsics.areEqual(str, "zh-MO") || (StringsKt__StringsKt.split$default(str, new String[]{"-"}, false, 0, 6, (Object) null).size() <= 2 && Intrinsics.areEqual(locale, Locale.TAIWAN))) ? "zh-Hant-TW" : str;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v32, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v14, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v15, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v16, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v17, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v18, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v19, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v20, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v21, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v22, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v23, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v5, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v6, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: T */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0077  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.String b() {
        /*
            long r4 = com.vega.core.utils.FlavorLocale.e
            r2 = 0
            int r0 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r0 > 0) goto Le
            long r0 = android.os.SystemClock.uptimeMillis()
            com.vega.core.utils.FlavorLocale.e = r0
        Le:
            kotlin.jvm.internal.Ref$ObjectRef r4 = new kotlin.jvm.internal.Ref$ObjectRef
            r4.<init>()
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            java.lang.String r0 = r0.q()
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r5 = 1
            r0 = r0 ^ 1
            if (r0 == 0) goto L54
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            java.lang.String r1 = r0.q()
            java.lang.String r0 = "CN"
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.equals(r1, r0, r5)
            if (r0 != 0) goto L54
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            java.lang.String r1 = r0.q()
        L3c:
            r4.element = r1
            com.vega.performance.setting.PerformanceProvider r0 = com.vega.performance.setting.PerformanceProvider.f126381a
            r0.getClass()
            boolean r0 = com.vega.performance.setting.PerformanceProvider.m()
            if (r0 == 0) goto L4f
            boolean r0 = com.bytedance.lego.init.InitScheduler.isFeedShowStart()
            if (r0 == 0) goto Lc8
        L4f:
            T r1 = r4.element
            java.lang.String r1 = (java.lang.String) r1
            goto La6
        L54:
            com.vega.core.utils.LocaleRegionHelper r0 = com.vega.core.utils.LocaleRegionHelper.f79617a
            r0.getClass()
            java.lang.String r0 = com.vega.core.utils.LocaleRegionHelper.f79619d
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L66
            java.lang.String r1 = com.vega.core.utils.LocaleRegionHelper.f79619d
            goto L3c
        L66:
            boolean r0 = com.vega.core.utils.LocaleRegionHelper.f79618c
            if (r0 == 0) goto L77
            java.lang.String r1 = g()
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r1)
            r0 = r0 ^ 1
            if (r0 == 0) goto L77
            goto L3c
        L77:
            boolean r0 = com.vega.core.utils.LocaleRegionHelper.b
            if (r0 == 0) goto L88
            java.lang.String r0 = com.vega.core.utils.LocaleRegionHelper.e
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto L88
            java.lang.String r1 = com.vega.core.utils.LocaleRegionHelper.e
            goto L3c
        L88:
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            java.lang.String r0 = r0.N()
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r0)
            r0 = r0 ^ 1
            if (r0 == 0) goto La1
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            java.lang.String r1 = r0.N()
            goto L3c
        La1:
            java.lang.String r1 = k()
            goto L3c
        La6:
            java.lang.Class<com.vega.core.utils.FlavorConfigOptSetting> r0 = com.vega.core.utils.FlavorConfigOptSetting.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)     // Catch: java.lang.Exception -> Lc2
            com.vega.config.IConfig r0 = com.vega.config.ConfigSettingsKt.a(r0)     // Catch: java.lang.Exception -> Lc2
            com.vega.core.utils.FlavorOptConfig r0 = (com.vega.core.utils.FlavorOptConfig) r0     // Catch: java.lang.Exception -> Lc2
            boolean r0 = r0.a()     // Catch: java.lang.Exception -> Lc2
            if (r0 != 0) goto Lb9
            goto Lc6
        Lb9:
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r1)     // Catch: java.lang.Exception -> Lc2
            if (r0 == 0) goto Lc6
            java.lang.String r1 = "US"
            goto Lc6
        Lc2:
            r0 = move-exception
            com.vega.log.ExceptionPrinter.printStackTrace(r0)
        Lc6:
            r4.element = r1
        Lc8:
            T r1 = r4.element
            java.lang.String r0 = com.vega.core.utils.FlavorLocale.f79594d
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 != 0) goto Ldc
            java.lang.String r1 = com.vega.core.utils.FlavorLocale.f79594d
            com.vega.core.utils.FlavorLocale$country$1 r0 = new com.vega.core.utils.FlavorLocale$country$1
            r0.<init>()
            com.vega.infrastructure.extensions.ThreadUtilKt.e(r2, r0)
        Ldc:
            T r0 = r4.element
            java.lang.String r0 = (java.lang.String) r0
            com.vega.core.utils.FlavorLocale.f79594d = r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.core.utils.FlavorLocale.b():java.lang.String");
    }

    public static String c(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        StartLifeDispatcher.f130871a.getClass();
        if ((!StartLifeDispatcher.f130873d || SystemClock.uptimeMillis() - e <= 20000) && !TextUtils.isEmpty(f)) {
            return f;
        }
        String strD = d(context);
        if (!TextUtils.isEmpty(strD)) {
            f = strD;
            return strD;
        }
        String strE = e(context);
        if (TextUtils.isEmpty(strE)) {
            strE = g();
        }
        f = strE;
        return strE;
    }

    public static String d(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        try {
            Object systemService = context.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "");
            CarrierEntry.f.getClass();
            String upperCase = CarrierEntry.Companion.e((TelephonyManager) systemService).toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "");
            return upperCase;
        } catch (Throwable th) {
            BLog.printStack("FlavorLocale", th);
            return null;
        }
    }

    public static String e(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        try {
            Object systemService = context.getSystemService("phone");
            Intrinsics.checkNotNull(systemService, "");
            CarrierEntry.f.getClass();
            return CarrierEntry.Companion.k((TelephonyManager) systemService);
        } catch (Throwable th) {
            BLog.printStack("FlavorLocale", th);
            return null;
        }
    }

    public static String f() {
        LookiRegionManager.f79462a.getClass();
        String str = LookiRegionManager.f79463c;
        return str.length() == 0 ? b() : str;
    }

    public static String g() {
        String strB;
        try {
            LocaleEntry.Companion companion = LocaleEntry.b;
            Locale locale = Resources.getSystem().getConfiguration().locale;
            Intrinsics.checkNotNullExpressionValue(locale, "");
            companion.getClass();
            strB = LocaleEntry.Companion.b(locale);
        } catch (Throwable th) {
            BLog.printStack("FlavorLocale", th);
            strB = null;
        }
        if (PerformanceManagerHelper.blogEnable) {
            StringBuilder sb = new StringBuilder("getSystemCountry country :");
            sb.append(strB);
            sb.append(",locale:");
            LocaleEntry.Companion companion2 = LocaleEntry.b;
            Locale locale2 = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale2, "");
            companion2.getClass();
            sb.append(LocaleEntry.Companion.b(locale2));
            BLog.i("FlavorLocale", sb.toString());
        }
        if (strB != null) {
            return strB;
        }
        Locale locale3 = Locale.getDefault();
        if (locale3 == null) {
            return "";
        }
        LocaleEntry.b.getClass();
        String strB2 = LocaleEntry.Companion.b(locale3);
        return strB2 == null ? "" : strB2;
    }

    public static String h() {
        AppLanguageUtils appLanguageUtils = AppLanguageUtils.f106615a;
        Application application = ModuleCommon.INSTANCE.getApplication();
        appLanguageUtils.getClass();
        Locale localeD = AppLanguageUtils.d(AppLanguageUtils.b(application));
        String languageTag = localeD.toLanguageTag();
        if (Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(Locale.CHINA))) {
            Intrinsics.checkNotNull(languageTag);
            return a(languageTag, localeD);
        }
        String strINVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage = INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD);
        Intrinsics.checkNotNull(strINVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage);
        return strINVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage;
    }

    public static String i(boolean z) {
        AppLanguageUtils appLanguageUtils = AppLanguageUtils.f106615a;
        Application application = ModuleCommon.INSTANCE.getApplication();
        appLanguageUtils.getClass();
        Locale localeD = AppLanguageUtils.d(AppLanguageUtils.b(application));
        String languageTag = localeD.toLanguageTag();
        if (Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(Locale.CHINA))) {
            Intrinsics.checkNotNull(languageTag);
            return a(languageTag, localeD);
        }
        if (!z) {
            String str = b.get(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD));
            if (str != null) {
                return str;
            }
        } else {
            if (Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(Locale.ENGLISH)) || Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), "ar") || Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), "es") || Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(Locale.FRANCE)) || Intrinsics.areEqual(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD), INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(Locale.FRENCH))) {
                String strINVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage = INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD);
                Intrinsics.checkNotNull(strINVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage);
                return strINVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage;
            }
            String str2 = b.get(INVOKEVIRTUAL_com_vega_core_utils_FlavorLocale_com_vega_launcher_lancet_Target35AdapterLancet_localeGetLanguage(localeD));
            if (str2 != null) {
                return str2;
            }
        }
        return "en";
    }

    public static /* synthetic */ String j(FlavorLocale flavorLocale) {
        flavorLocale.getClass();
        return i(true);
    }

    public static String k() {
        String strC = c(ModuleCommon.INSTANCE.getApplication());
        if (strC == null) {
            return "US";
        }
        Locale locale = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale, "");
        String upperCase = strC.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "");
        return upperCase;
    }
}
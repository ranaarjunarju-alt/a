package com.vega.launcher.init.config;

import JNI.MillenniumMods;
import android.app.Application;
import androidx.core.view.accessibility.C0084;
import androidx.startup.C0095;
import com.bytedance.applog.bdinstall.C0212;
import com.bytedance.applog.engine.C0214;
import com.bytedance.applog.handler.C0215;
import com.bytedance.applog.monitor.C0217;
import com.bytedance.applog.monitor.v3.impl.C0216;
import com.bytedance.applog.priority.C0218;
import com.bytedance.applog.priority.C0219;
import com.bytedance.applog.server.C0220;
import com.bytedance.applog.server.C0221;
import com.bytedance.applog.task.C0222;
import com.bytedance.applog.task.C0223;
import com.bytedance.bdinstall.event.C0225;
import com.bytedance.bdinstall.sensitive.C0226;
import com.bytedance.bdinstall.service.C0227;
import com.google.android.gms.ads.C0467;
import com.google.android.gms.ads.nonagon.signalgeneration.C0466;
import com.google.gson.Gson;
import com.lm.components.core.C0580;
import com.lm.components.core.C0581;
import com.lm.components.network.init.C0583;
import com.lm.components.report.init.C0584;
import com.lynx.tasm.service.security.C0594;
import com.p009.C1755;
import com.ss.android.ad.splash.utils.C0750;
import com.ss.android.common.applog.C0751;
import com.ss.android.deviceregister.core.cache.internal.C0752;
import com.ss.ttm.player.MediaPlayer;
import com.ss.ttvideoengine.TTVideoEngineInterface;
import com.tiktok.open.sdk.core.appcheck.C0790;
import com.vega.cloud.CloudConfig;
import com.vega.core.context.AppProperty;
import com.vega.core.context.LocationProperty;
import com.vega.core.ext.ParameterizedTypeImpl;
import com.vega.core.settings.SettingsManagerWrapper;
import com.vega.core.settings.localab.LocalAbManager;
import com.vega.core.utils.FlavorLocale;
import com.vega.core.utils.LocaleRegionHelper;
import com.vega.effectplatform.artist.Constants;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.kv.KvStorage;
import com.vega.launcher.privacy.looki.network.monitor.async.C1378;
import com.vega.launcher.privacy.looki.network.monitor.async.C1379;
import com.vega.main.ab.NewUserPopAbTest;
import com.vega.report.ReportManagerWrapper;
import com.vega.web.lynx.C1618;
import com.xt.retouch.sdk.RetouchSdk;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Lazy;
import kotlin.Pair;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KProperty;
import kotlin.text.Regex;

/* loaded from: classes16.dex */
public final class AppPropertyImpl implements AppProperty {
    public static final Companion n;
    public static final /* synthetic */ KProperty<Object>[] o;
    public static final Lazy<KvStorage> p;
    public static final KvStorage q;
    public static final KvStorage r;

    /* renamed from: short, reason: not valid java name */
    private static final short[] f538short;

    /* renamed from: a, reason: collision with root package name */
    public final String f107285a;
    public final ReadWriteProperty b;

    /* renamed from: c, reason: collision with root package name */
    public final ReadWriteProperty f107286c;

    /* renamed from: d, reason: collision with root package name */
    public final ReadWriteProperty f107287d;
    public List<LocationProperty> e;
    public final ReadWriteProperty f;

    /* renamed from: g, reason: collision with root package name */
    public final ReadWriteProperty f107288g;
    public final ReadWriteProperty h;
    public final ReadWriteProperty i;
    public final ReadWriteProperty j;
    public final ReadWriteProperty k;
    public final Lazy l;
    public final ReadWriteProperty m;

    /* loaded from: classes12.dex */
    public static final class Companion {
        public static KvStorage a() {
            return AppPropertyImpl.p.getValue();
        }
    }

    static {
        MillenniumMods.classes3Init0(TTVideoEngineInterface.PLAYER_OPTION_IS_DEGRADE_RELEASE);
        f538short = new short[]{385, 390, 411, 412, 393, 388, 388, 417, 396, 1096, 1098, 1115, 1126, 1089, 1116, 1115, 1102, 1091, 1091, 1126, 1099, 1031, 1030, 1123, 1093, 1102, 1113, 1102, 1024, 1091, 1102, 1089, 1096, 1024, 1148, 1115, 1117, 1094, 1089, 1096, 1044, 1016, 1022, 996, 995, 1002, 961, 994, 1006, 723, 721, 704, 737, 711, 733, 730, 723, 760, 731, 727, 668, 669, 760, 734, 725, 706, 725, 667, 728, 725, 730, 723, 667, 743, 704, 710, 733, 730, 723, 655, 1633, 1639, 1661, 1658, 1651, 1624, 1659, 1655, 1624, 1661, 1639, 1632, 1607, 1632, 1638, 1661, 1658, 1651, 3101, 3103, 3086, 3119, 3081, 3091, 3092, 3101, 3126, 3093, 3097, 3126, 3091, 3081, 3086, 3113, 3086, 3080, 3091, 3092, 3101, 3154, 3155, 3126, 3088, 3099, 3084, 3099, 3157, 3094, 3099, 3092, 3101, 3157, 3113, 3086, 3080, 3091, 3092, 3101, 3137, 1072, 1078, 1068, 1067, 1058, 1033, 1060, 1067, 2898, 2896, 2881, 2912, 2886, 2908, 2907, 2898, 2937, 2900, 2907, 2845, 2844, 2937, 2911, 2900, 2883, 2900, 2842, 2905, 2900, 2907, 2898, 2842, 2918, 2881, 2887, 2908, 2907, 2898, 2830, 2874, 2861, 2853, 2855, 2876, 2861, 2820, 2855, 2859, 2435, 2433, 2448, 2486, 2433, 2441, 2443, 2448, 2433, 2472, 2443, 2439, 2508, 2509, 2472, 2446, 2437, 2450, 2437, 2507, 2440, 2437, 2442, 2435, 2507, 2487, 2448, 2454, 2445, 2442, 2435, 2527, 2802, 2804, 2787, 2789, 2792, 2807, 2792, 2802, 2792, 2798, 2799, 2765, 2798, 2786, 2760, 2789, 3325, 3327, 3310, 3273, 3311, 3320, 3326, 3315, 3308, 3315, 3305, 3315, 3317, 3316, 3286, 3317, 3321, 3283, 3326, 3250, 3251, 3286, 3312, 3323, 3308, 3323, 3253, 3318, 3323, 3316, 3325, 3253, 3273, 3310, 3304, 3315, 3316, 3325, 3233, 2111, 2105, 2094, 2088, 2085, 2106, 2085, 2111, 2085, 2083, 2082, 2048, 2083, 2095, 2050, 2093, 2081, 2089, 2620, 2622, 2607, 2568, 2606, 2617, 2623, 2610, 2605, 2610, 2600, 2610, 2612, 2613, 2583, 2612, 2616, 2581, 2618, 2614, 2622, 2675, 2674, 2583, 2609, 2618, 2605, 2618, 2676, 2615, 2618, 2613, 2620, 2676, 2568, 2607, 2601, 2610, 2613, 2620, 2656, 905, 907, 912, 911, 920, 922, 896, 954, 918, 919, 909, 907, 920, 922, 909, 933, 935, 950, 914, 944, 939, 948, 931, 929, 955, 897, 941, 940, 950, 944, 931, 929, 950, 1002, 1003, 920, 2506, 2503, 2543, 2509, 2512, 2520, 2509, 2526, 2514, 2556, 2512, 2513, 2521, 2518, 2520, 2506, 2509, 2522, 2523, 1167, 1165, 1180, 1213, 1168, 1208, 1178, 1159, 1167, 1178, 1161, 1157, 1195, 1159, 1158, 1166, 1153, 1167, 1181, 1178, 1165, 1164, 1216, 1217, 1202, 1342, 1316, 1300, 1317, 1330, 1334, 1315, 1330, 1306, 1336, 1331, 1330, 3160, 3138, 3186, 3139, 3156, 3152, 3141, 3156, 3196, 3166, 3157, 3156, 3097, 3096, 3179, 1348, 1365, 1365, 1291, 1353, 1348, 1360, 1355, 1350, 1357, 1291, 1366, 1365, 1291, 1350, 1354, 1355, 1347, 1356, 1346, 2315, 2325, 2321, 2317, 2362, 2310, 2327, 2308, 2326, 2317, 2362, 2316, 2315, 2307, 2314, 2362, 2305, 2308, 2321, 2308, 1848, 1846, 1834, 1804, 1850, 1853, 1824, 1831, 1842, 1855, 1855, 1804, 1850, 1847, 2882, 2892, 2896, 2934, 2908, 2906, 2880, 2887, 2894, 2934, 2885, 2886, 2890, 2888, 2909, 2880, 2886, 2887, 795, 789, 777, 815, 773, 771, 793, 798, 791, 815, 796, 799, 787, 785, 772, 793, 799, 798, 815, 796, 793, 771, 772, 2915, 2925, 2929, 2903, 2941, 2939, 2913, 2918, 2927, 2903, 2916, 2921, 2918, 2927, 2941, 2921, 2927, 2925, 1572, 1578, 1590, 1552, 1597, 1578, 1570, 1568, 1595, 1578, 1552, 1571, 1568, 1580, 1582, 1595, 1574, 1568, 1569, 1552, 1593, 1661, 2386, 2396, 2368, 2406, 2378, 2380, 2395, 2397, 2384, 2383, 2384, 2378, 2384, 2390, 2391, 2406, 2384, 2397, 1429, 1435, 1415, 1441, 1421, 1419, 1436, 1434, 1431, 1416, 1431, 1421, 1431, 1425, 1424, 1084, 1086, 1061, 1082, 1069, 1071, 1077, 1043, 1071, 1059, 1058, 1080, 1086, 1069, 1071, 1080, 955, 950, 913, 958, 956, 929, 937, 956, 943, 931, 913, 941, 929, 928, 936, 935, 937, 955, 956, 939, 938, 1153, 1174, 1168, 1162, 1165, 1158, 1168, 1168, 1212, 1168, 1158, 1175, 1175, 1162, 1165, 1156, 1212, 1152, 1152, 417, 438, 432, 426, 429, 422, 432, 432, 412, 432, 422, 439, 439, 426, 429, 420, 412, 416, 433, 422, 418, 439, 422, 412, 430, 428, 423, 422, 2182, 2199, 2199, 2249, 2180, 2184, 2185, 2177, 2190, 2176, 1815, 1813, 1806, 1809, 1798, 1796, 1822, 1848, 1796, 1800, 1801, 1811, 1813, 1798, 1796, 1811, 1382, 1384, 1396, 1362, 1379, 1384, 1402, 1362, 1400, 1406, 1384, 1407, 1362, 1405, 1378, 1405, 2288, 2279, 2286, 2279, 2275, 2289, 2279, 279, 276, 280, 282, 279, 292, 271, 286, 264, 271, 1320, 1282, 1295, 1304, 1281, 1308, 1314, 1281, 1293, 1295, 1282, 1291, 1821, 1812, 1801, 1816, 1822, 1883, 1806, 1800, 1822, 1883, 1801, 1822, 1820, 1810, 1812, 1813, 1882, 1883, 1801, 1822, 1820, 1810, 1812, 1813, 1883, 1862, 1883, 3171, 3178, 3191, 3174, 3168, 3109, 3184, 3190, 3168, 3109, 3190, 3196, 3190, 3185, 3168, 3176, 3109, 3191, 3168, 3170, 3180, 3178, 3179, 3108, 3109, 3191, 3168, 3170, 3180, 3178, 3179, 3109, 3128, 3109, 2437, 2435, 2440, 2513, 2436, 2434, 2452, 2513, 2463, 2452, 2437, 2438, 2462, 2435, 2458, 2467, 2452, 2454, 2456, 2462, 2463, 2513, 2508, 2513, 3144, 3150, 3141, 3100, 3145, 3151, 3161, 3100, 3150, 3161, 3153, 3155, 3144, 3161, 3199, 3155, 3145, 3154, 3144, 3150, 3141, 3100, 3073, 3100, 2510, 2504, 2499, 2458, 2511, 2505, 2527, 2458, 2526, 2527, 2524, 2523, 2511, 2518, 2510, 2553, 2517, 2511, 2516, 2510, 2504, 2499, 2458, 2439, 2458, 1857, 1868, 1886, 1881, 1906, 1886, 1880, 1871, 1865, 1860, 1883, 1860, 1886, 1860, 1858, 1859, 1906, 1860, 1865, 644, 658, 661, 661, 642, 649, 659, 696, 660, 658, 645, 643, 654, 657, 654, 660, 654, 648, 649, 696, 654, 643, 2971, 2973, 2954, 2956, 2945, 2974, 2945, 2971, 2945, 2951, 2950, 2999, 2955, 2944, 2953, 2950, 2959, 2957};
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0227.m8204(m14299(), 0, 9, 488), C0219.m6861(m14299(), 9, 32, 1071), 0);
        m14345(mutablePropertyReference1Impl);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0226.m8145(m14299(), 41, 8, 909), C0466.m9216(m14299(), 49, 31, 692), 0);
        m14345(mutablePropertyReference1Impl2);
        MutablePropertyReference1Impl mutablePropertyReference1Impl3 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0584.m10060(m14299(), 80, 18, 1556), C0215.m5905(m14299(), 98, 41, 3194), 0);
        m14345(mutablePropertyReference1Impl3);
        MutablePropertyReference1Impl mutablePropertyReference1Impl4 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0095.m504(m14299(), 139, 8, 1093), C0221.m7121(m14299(), MediaPlayer.MEDIA_PLAYER_OPTION_ABR_STREAM_INFO, 31, 2869), 0);
        m14345(mutablePropertyReference1Impl4);
        MutablePropertyReference1Impl mutablePropertyReference1Impl5 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0216.m6184(m14299(), 178, 9, 2888), C0790.m13361(m14299(), 187, 32, 2532), 0);
        m14345(mutablePropertyReference1Impl5);
        MutablePropertyReference1Impl mutablePropertyReference1Impl6 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C1618.m16385(m14299(), 219, 16, 2689), C0222.m7366(m14299(), 235, 39, 3226), 0);
        m14345(mutablePropertyReference1Impl6);
        MutablePropertyReference1Impl mutablePropertyReference1Impl7 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0227.m8204(m14299(), 274, 18, 2124), C1378.m14666(m14299(), 292, 41, 2651), 0);
        m14345(mutablePropertyReference1Impl7);
        MutablePropertyReference1Impl mutablePropertyReference1Impl8 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0226.m8145(m14299(), 333, 15, 1017), C0225.m7927(m14299(), 348, 21, 962), 0);
        m14345(mutablePropertyReference1Impl8);
        MutablePropertyReference1Impl mutablePropertyReference1Impl9 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0215.m5905(m14299(), 369, 19, 2495), C0750.m12116(m14299(), 388, 25, 1256), 0);
        m14345(mutablePropertyReference1Impl9);
        MutablePropertyReference1Impl mutablePropertyReference1Impl10 = new MutablePropertyReference1Impl(AppPropertyImpl.class, C0467.m9439(m14299(), TTVideoEngineInterface.PLAYER_OPTION_ENABEL_HARDWARE_DECODE_AUDIO, 12, 1367), C0750.m12116(m14299(), 425, 15, 3121), 0);
        m14345(mutablePropertyReference1Impl10);
        o = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2, mutablePropertyReference1Impl3, mutablePropertyReference1Impl4, mutablePropertyReference1Impl5, mutablePropertyReference1Impl6, mutablePropertyReference1Impl7, mutablePropertyReference1Impl8, mutablePropertyReference1Impl9, mutablePropertyReference1Impl10};
        n = new Companion();
        p = m14289(m14291());
        ModuleCommon moduleCommonM14315 = m14315();
        q = new KvStorage(m14302(moduleCommonM14315), C0225.m7927(m14299(), 440, 20, 1317));
        r = new KvStorage(m14302(moduleCommonM14315), C0221.m7121(m14299(), 460, 20, 2405));
    }

    public AppPropertyImpl(String str) {
        Object objM14344;
        String strM5834 = C0214.m5834();
        m14355(str, strM5834);
        this.f107285a = str;
        C0751.m12714(C0467.m9450());
        this.b = m14339(m14341(), C0084.m252(m14299(), 480, 14, 1875), strM5834, false);
        this.f107286c = m14339(m14341(), C0594.m10221(m14299(), 494, 18, 2857), strM5834, false);
        ReadWriteProperty readWritePropertyM14339 = m14339(m14341(), C0751.m12636(m14299(), 512, 23, 880), strM5834, false);
        this.f107287d = readWritePropertyM14339;
        this.e = new ArrayList();
        this.f = m14339(m14341(), C1379.m14815(m14299(), TTVideoEngineInterface.PLAYER_OPTION_SPEED_REPORT_SAMPLIING_RATE, 18, 2824), strM5834, false);
        this.f107288g = m14339(m14341(), C0583.m9906(m14299(), 553, 22, 1615), strM5834, false);
        this.h = m14339(m14341(), C0223.m7524(m14299(), 575, 18, 2361), strM5834, false);
        this.i = m14339(m14341(), C0222.m7366(m14299(), 593, 15, 1534), strM5834, false);
        KvStorage kvStorageM14806 = C1379.m14806();
        Boolean boolM5428 = C0212.m5428();
        this.j = m14339(kvStorageM14806, C0751.m12636(m14299(), 608, 16, 1100), boolM5428, false);
        this.k = m14339(kvStorageM14806, C0219.m6861(m14299(), 624, 21, 974), boolM5428, false);
        this.l = m14289(new Function0<Integer>() { // from class: com.vega.launcher.init.config.AppPropertyImpl$versionNumber$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Integer invoke() {
                List listEmptyList;
                this.e.getClass();
                List<String> listSplit = new Regex("\\.").split("19.6.0", 0);
                if (listSplit.isEmpty()) {
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                } else {
                    ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                    while (listIterator.hasPrevious()) {
                        if (listIterator.previous().length() != 0) {
                            listEmptyList = CollectionsKt___CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                            break;
                        }
                    }
                    listEmptyList = CollectionsKt__CollectionsKt.emptyList();
                }
                String[] strArr = (String[]) listEmptyList.toArray(new String[0]);
                if (strArr.length != 3) {
                    throw new IllegalArgumentException("versionNameCode length must be 3,please check your root build.gradle");
                }
                return Integer.valueOf(Integer.parseInt(strArr[2]) | (Integer.parseInt(strArr[0]) << 20) | 268435456 | (Integer.parseInt(strArr[1]) << 12));
            }
        });
        ReadWriteProperty readWritePropertyM14313 = m14313(m14302(m14315()), boolM5428, C0223.m7524(m14299(), 645, 19, 1251), C0215.m5905(m14299(), 664, 28, 451), false);
        this.m = readWritePropertyM14313;
        KProperty[] kPropertyArrM16786 = C1755.m16786();
        if (C0217.m6311((String) m14352(readWritePropertyM14339, this, kPropertyArrM16786[2])) == 0) {
            if (C0217.m6311(C0466.m9254(this)) == 0) {
                return;
            }
            if (C1618.m16334((Boolean) m14352(readWritePropertyM14313, this, kPropertyArrM16786[9]))) {
                LocationProperty locationPropertyM14312 = m14312();
                String strM9254 = C0466.m9254(this);
                C0751.m12714(locationPropertyM14312);
                m14355(strM9254, strM5834);
                locationPropertyM14312.f79171a = strM9254;
                C0220.m7054(C0580.m9655(this), locationPropertyM14312);
            } else {
                LocationProperty locationPropertyM14356 = m14356();
                String strM92542 = C0466.m9254(this);
                C0751.m12714(locationPropertyM14356);
                m14355(strM92542, strM5834);
                locationPropertyM14356.f79171a = strM92542;
                C0220.m7054(C0580.m9655(this), locationPropertyM14356);
            }
            m14334(readWritePropertyM14339, this, kPropertyArrM16786[2], m14314(C0580.m9655(this)));
            return;
        }
        try {
            Object objM14321 = m14321(m14350(), (String) m14352(readWritePropertyM14339, this, kPropertyArrM16786[2]), new ParameterizedTypeImpl(new Type[]{LocationProperty.class}));
            m14324(objM14321, strM5834);
            objM14344 = m14327((List) objM14321);
            C0218.m6610(objM14344);
        } catch (Throwable th) {
            objM14344 = m14344(th);
            C0218.m6610(objM14344);
        }
        List<LocationProperty> list = (List) (C0752.m12917(objM14344) ? new ArrayList() : objM14344);
        this.e = list;
        if (C0581.m9741(list) > 1) {
            List listM9655 = C0580.m9655(this);
            final AppPropertyImpl$initUsingLocationProperty$2 appPropertyImpl$initUsingLocationProperty$2M14301 = m14301();
            m14335(listM9655, new Comparator() { // from class: X.0PF
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return ((Number) appPropertyImpl$initUsingLocationProperty$2M14301.invoke(obj, obj2)).intValue();
                }
            });
        }
        if (C0226.m8082(C0580.m9655(this))) {
            return;
        }
        m14334(C0226.m8088(this), this, C1755.m16786()[1], m14306((LocationProperty) C0225.m7909(C0580.m9655(this), 0)));
    }

    /* renamed from: ۟۟ۥۡۤ, reason: not valid java name and contains not printable characters */
    public static native boolean m14288(Object obj);

    /* renamed from: ۟۠ۢ۟۠, reason: not valid java name and contains not printable characters */
    public static native Lazy m14289(Object obj);

    /* renamed from: ۟۠ۢۦۤ, reason: not valid java name and contains not printable characters */
    public static native boolean m14290();

    /* renamed from: ۟۠ۧۤ۠, reason: not valid java name and contains not printable characters */
    public static native AppPropertyImpl$Companion$storage$2 m14291();

    /* renamed from: ۣۣ۟ۡ۠, reason: not valid java name and contains not printable characters */
    public static native boolean m14292();

    /* renamed from: ۟ۡۥۦ۠, reason: not valid java name and contains not printable characters */
    public static native Pair m14293(Object obj, Object obj2);

    /* renamed from: ۟ۡۧۦۧ, reason: not valid java name and contains not printable characters */
    public static native String m14294();

    /* renamed from: ۟ۢۡ۠ۨ, reason: not valid java name and contains not printable characters */
    public static native FlavorLocale m14295();

    /* renamed from: ۟ۢۢۤۧ, reason: not valid java name and contains not printable characters */
    public static native ReportManagerWrapper m14296();

    /* renamed from: ۣ۟ۢۤ۟, reason: not valid java name and contains not printable characters */
    public static native String m14297();

    /* renamed from: ۟ۢۤۡۦ, reason: not valid java name and contains not printable characters */
    public static native String m14298(Object obj);

    /* renamed from: ۟ۢۧ۟ۨ, reason: not valid java name and contains not printable characters */
    public static native short[] m14299();

    /* renamed from: ۟ۢۧۡۤ, reason: not valid java name and contains not printable characters */
    public static native String m14300();

    /* renamed from: ۣ۟ۡ۟ۤ, reason: not valid java name and contains not printable characters */
    public static native AppPropertyImpl$initUsingLocationProperty$2 m14301();

    /* renamed from: ۣۣۣ۟ۡ, reason: not valid java name and contains not printable characters */
    public static native Application m14302(Object obj);

    /* renamed from: ۣ۟ۤ۟ۡ, reason: not valid java name and contains not printable characters */
    public static native RetouchSdk.Companion m14303();

    /* renamed from: ۣۣ۟ۤ۟, reason: not valid java name and contains not printable characters */
    public static native Object m14304(Object obj);

    /* renamed from: ۣ۟ۥ۠ۦ, reason: not valid java name and contains not printable characters */
    public static native AppPropertyImpl$addUsingLocation$1 m14305();

    /* renamed from: ۣ۟ۧۧۢ, reason: not valid java name and contains not printable characters */
    public static native String m14306(Object obj);

    /* renamed from: ۟ۤ۟۠ۥ, reason: not valid java name and contains not printable characters */
    public static native RetouchConfigHelper m14307();

    /* renamed from: ۟ۤ۟ۡ۠, reason: not valid java name and contains not printable characters */
    public static native String m14308();

    /* renamed from: ۟ۤ۟ۥۧ, reason: not valid java name and contains not printable characters */
    public static native int m14309(Object obj, int i);

    /* renamed from: ۣ۟ۤ۟ۤ, reason: not valid java name and contains not printable characters */
    public static native NewUserPopAbTest.Companion m14310();

    /* renamed from: ۣ۟ۤۤۥ, reason: not valid java name and contains not printable characters */
    public static native SettingsManagerWrapper m14311();

    /* renamed from: ۟ۤۥ۟, reason: not valid java name and contains not printable characters */
    public static native LocationProperty m14312();

    /* renamed from: ۟ۤۦۦۥ, reason: not valid java name and contains not printable characters */
    public static native ReadWriteProperty m14313(Object obj, Object obj2, Object obj3, Object obj4, boolean z);

    /* renamed from: ۟ۤۦۨ۠, reason: not valid java name and contains not printable characters */
    public static native String m14314(Object obj);

    /* renamed from: ۟ۥۡۢۢ, reason: not valid java name and contains not printable characters */
    public static native ModuleCommon m14315();

    /* renamed from: ۟ۥۢۤۦ, reason: not valid java name and contains not printable characters */
    public static native LocalAbManager m14316();

    /* renamed from: ۟ۥۣۨۡ, reason: not valid java name and contains not printable characters */
    public static native String m14317(Object obj);

    /* renamed from: ۟ۦۡۢۡ, reason: not valid java name and contains not printable characters */
    public static native AppPropertyImpl$removeUsingLocation$1 m14318();

    /* renamed from: ۟ۦۢۨۦ, reason: not valid java name and contains not printable characters */
    public static native AppProperty m14319();

    /* renamed from: ۟ۦۥ۠ۦ, reason: not valid java name and contains not printable characters */
    public static native boolean m14320();

    /* renamed from: ۟ۦۦۢ۟, reason: not valid java name and contains not printable characters */
    public static native Object m14321(Object obj, Object obj2, Object obj3);

    /* renamed from: ۟ۦۨۢ۟, reason: not valid java name and contains not printable characters */
    public static native boolean m14322(Object obj);

    /* renamed from: ۟ۧ۠ۦ۠, reason: not valid java name and contains not printable characters */
    public static native Object m14323(Object obj, Object obj2);

    /* renamed from: ۟ۧۦۨۧ, reason: not valid java name and contains not printable characters */
    public static native void m14324(Object obj, Object obj2);

    /* renamed from: ۠ۤۥ۠, reason: not valid java name and contains not printable characters */
    public static native int m14325();

    /* renamed from: ۠ۤۦۣ, reason: not valid java name and contains not printable characters */
    public static native boolean m14326(Object obj, Object obj2);

    /* renamed from: ۠ۥۡۤ, reason: not valid java name and contains not printable characters */
    public static native List m14327(Object obj);

    /* renamed from: ۠ۧۡۤ, reason: not valid java name and contains not printable characters */
    public static native void m14328(Object obj, Object obj2);

    /* renamed from: ۠ۨ۟ۦ, reason: not valid java name and contains not printable characters */
    public static native int m14329(Object obj);

    /* renamed from: ۣ۠ۨۡ, reason: not valid java name and contains not printable characters */
    public static native String m14330();

    /* renamed from: ۣۡ۟ۦ, reason: not valid java name and contains not printable characters */
    public static native String m14331();

    /* renamed from: ۡ۟ۨۡ, reason: not valid java name and contains not printable characters */
    public static native Map m14332();

    /* renamed from: ۡۧۦۥ, reason: not valid java name and contains not printable characters */
    public static native boolean m14333();

    /* renamed from: ۡۨۢ۟, reason: not valid java name and contains not printable characters */
    public static native void m14334(Object obj, Object obj2, Object obj3, Object obj4);

    /* renamed from: ۢۤۤۨ, reason: not valid java name and contains not printable characters */
    public static native void m14335(Object obj, Object obj2);

    /* renamed from: ۢۥۤ۠, reason: not valid java name and contains not printable characters */
    public static native boolean m14336(Object obj);

    /* renamed from: ۢۦ۟۠, reason: not valid java name and contains not printable characters */
    public static native boolean m14337(Object obj, Object obj2, boolean z);

    /* renamed from: ۣۢۧۦ, reason: not valid java name and contains not printable characters */
    public static native String m14338();

    /* renamed from: ۣ۠۠, reason: not valid java name and contains not printable characters */
    public static native ReadWriteProperty m14339(Object obj, Object obj2, Object obj3, boolean z);

    /* renamed from: ۣۤۡۡ, reason: not valid java name and contains not printable characters */
    public static native LocaleRegionHelper m14340();

    /* renamed from: ۤۡۦۡ, reason: not valid java name and contains not printable characters */
    public static native KvStorage m14341();

    /* renamed from: ۣۤ۟۟, reason: not valid java name and contains not printable characters */
    public static native boolean m14342();

    /* renamed from: ۥ۟ۥۤ, reason: contains not printable characters */
    public static native CloudConfig m14343();

    /* renamed from: ۥۢ۠ۤ, reason: contains not printable characters */
    public static native Object m14344(Object obj);

    /* renamed from: ۥۣ۟, reason: contains not printable characters */
    public static native KMutableProperty1 m14345(Object obj);

    /* renamed from: ۥۦۤ, reason: contains not printable characters */
    public static native Constants.EffectType.Companion m14346();

    /* renamed from: ۥۨۦ, reason: contains not printable characters */
    public static native List m14347();

    /* renamed from: ۦ۠ۨ۠, reason: contains not printable characters */
    public static native List m14348();

    /* renamed from: ۦۥۨۡ, reason: contains not printable characters */
    public static native List m14349(Object obj);

    /* renamed from: ۦۦۣۤ, reason: contains not printable characters */
    public static native Gson m14350();

    /* renamed from: ۧۤۧ۟, reason: not valid java name and contains not printable characters */
    public static native void m14351(Object obj, Object obj2, Object obj3);

    /* renamed from: ۨ۠۠ۤ, reason: not valid java name and contains not printable characters */
    public static native Object m14352(Object obj, Object obj2, Object obj3);

    /* renamed from: ۨ۠ۧۢ, reason: not valid java name and contains not printable characters */
    public static native Map m14353(Object obj);

    /* renamed from: ۣۨۡۧ, reason: not valid java name and contains not printable characters */
    public static native void m14354();

    /* renamed from: ۨۧۨۢ, reason: not valid java name and contains not printable characters */
    public static native void m14355(Object obj, Object obj2);

    /* renamed from: ۨۨۢ, reason: not valid java name and contains not printable characters */
    public static native LocationProperty m14356();

    @Override // com.vega.core.context.AppProperty
    public final native void A();

    @Override // com.vega.core.context.AppProperty
    public final native int B();

    @Override // com.vega.core.context.AppProperty
    public final native void C();

    @Override // com.vega.core.context.AppProperty
    public final native String D();

    @Override // com.vega.core.context.AppProperty
    public final native void E(String str);

    @Override // com.vega.core.context.AppProperty
    public final native String F();

    @Override // com.vega.core.context.AppProperty
    public final native String G();

    @Override // com.vega.core.context.AppProperty
    public final native void H();

    @Override // com.vega.core.context.AppProperty
    public final native void I();

    @Override // com.vega.core.context.AppProperty
    public final native void J(LocationProperty locationProperty);

    @Override // com.vega.core.context.AppProperty
    public final native void K();

    @Override // com.vega.core.context.AppProperty
    public final native void L();

    @Override // com.vega.core.context.AppProperty
    public final native List<String> M();

    @Override // com.vega.core.context.AppProperty
    public final native String N();

    @Override // com.vega.core.context.AppProperty
    public final native void O(String str);

    @Override // com.vega.core.context.AppProperty
    public final native void P();

    public final native String Q();

    @Override // com.vega.core.context.AppProperty
    public final native void a();

    @Override // com.vega.core.context.AppProperty
    public final native void b();

    @Override // com.vega.core.context.AppProperty
    public final native void c();

    @Override // com.vega.core.context.AppProperty
    public final native String channel();

    @Override // com.vega.core.context.AppProperty
    public final native void d();

    @Override // com.vega.core.context.AppProperty
    public final native void e();

    @Override // com.vega.core.context.AppProperty
    public final native void f();

    @Override // com.vega.core.context.AppProperty
    public final native String g();

    @Override // com.vega.core.context.AppProperty
    public final native boolean h();

    @Override // com.vega.core.context.AppProperty
    public final native boolean i();

    @Override // com.vega.core.context.AppProperty
    public final native void isIntegratedBuild();

    @Override // com.vega.core.context.AppProperty
    public final native void isOutBuild();

    @Override // com.vega.core.context.AppProperty
    public final native void j(String str);

    @Override // com.vega.core.context.AppProperty
    public final native void k();

    @Override // com.vega.core.context.AppProperty
    public final native void l();

    @Override // com.vega.core.context.AppProperty
    public final native String m();

    @Override // com.vega.core.context.AppProperty
    public final native void n();

    @Override // com.vega.core.context.AppProperty
    public final native void o();

    @Override // com.vega.core.context.AppProperty
    public final native List<Integer> p();

    @Override // com.vega.core.context.AppProperty
    public final native void packageName();

    @Override // com.vega.core.context.AppProperty
    public final native String q();

    @Override // com.vega.core.context.AppProperty
    public final native void r();

    @Override // com.vega.core.context.AppProperty
    public final native int s();

    @Override // com.vega.core.context.AppProperty
    public final native void setInstallID(String str);

    @Override // com.vega.core.context.AppProperty
    public final native boolean t();

    @Override // com.vega.core.context.AppProperty
    public final native void u(LocationProperty locationProperty);

    @Override // com.vega.core.context.AppProperty
    public final native boolean v();

    @Override // com.vega.core.context.AppProperty
    public final native String w();

    @Override // com.vega.core.context.AppProperty
    public final native String x();

    @Override // com.vega.core.context.AppProperty
    public final native String y();

    @Override // com.vega.core.context.AppProperty
    public final native void z(String str);
}
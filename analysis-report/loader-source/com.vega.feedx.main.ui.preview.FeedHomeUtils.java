package com.vega.feedx.main.ui.preview;

import android.app.Application;
import android.net.Uri;
import com.bytedance.common.profilesdk.ProfileManager;
import com.lm.components.logservice.alog.BLog;
import com.vega.config.ConfigSettingsKt;
import com.vega.feedx.config.ImmersiveFeedSwitchConfig;
import com.vega.feedx.config.ImmersiveFeedSwitchConfigSetting;
import com.vega.feedx.landing.FeedLandingManager;
import com.vega.feedx.main.bean.FeedItem;
import com.vega.feedx.main.ui.middle.FeedPageStatus;
import com.vega.feedx.main.ui.middle.ImmersiveFeedSwitchHelper;
import com.vega.feedx.settings.ImmersiveFeedModeConfig;
import com.vega.feedx.settings.ImmersiveFeedModeConfigSetting;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.infrastructure.extensions.ThreadUtilKt;
import com.vega.kv.KvStorageKt;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt__StringsJVMKt;

/* loaded from: classes32.dex */
public final class FeedHomeUtils {

    /* renamed from: a, reason: collision with root package name */
    public static final FeedHomeUtils f100912a;
    public static final /* synthetic */ KProperty<Object>[] b;

    /* renamed from: c, reason: collision with root package name */
    public static String f100913c;

    /* renamed from: d, reason: collision with root package name */
    public static boolean f100914d;
    public static boolean e;
    public static boolean f;

    /* renamed from: g, reason: collision with root package name */
    public static final Lazy f100915g;
    public static final Lazy h;
    public static final boolean i;
    public static boolean j;
    public static final ReadWriteProperty k;
    public static final ReadWriteProperty l;
    public static final ReadWriteProperty m;
    public static final ReadWriteProperty n;
    public static final ReadWriteProperty o;
    public static long p;
    public static final ReadWriteProperty q;
    public static final ReadWriteProperty r;
    public static boolean s;

    /* loaded from: classes4.dex */
    public /* synthetic */ class WhenMappings {
        static {
            FeedPageStatus.values();
        }
    }

    static {
        boolean z = false;
        MutablePropertyReference1Impl mutablePropertyReference1Impl = new MutablePropertyReference1Impl(FeedHomeUtils.class, "hasShowInspirationListGuideCapsule", "getHasShowInspirationListGuideCapsule()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl);
        MutablePropertyReference1Impl mutablePropertyReference1Impl2 = new MutablePropertyReference1Impl(FeedHomeUtils.class, "hasShowInspirationListGuideRightBtn", "getHasShowInspirationListGuideRightBtn()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl2);
        MutablePropertyReference1Impl mutablePropertyReference1Impl3 = new MutablePropertyReference1Impl(FeedHomeUtils.class, "hasShowImerssiveSwitchGuide", "getHasShowImerssiveSwitchGuide()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl3);
        MutablePropertyReference1Impl mutablePropertyReference1Impl4 = new MutablePropertyReference1Impl(FeedHomeUtils.class, "hasShowBottomSwitchGuide", "getHasShowBottomSwitchGuide()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl4);
        MutablePropertyReference1Impl mutablePropertyReference1Impl5 = new MutablePropertyReference1Impl(FeedHomeUtils.class, "hasShowBottomSwitchNewGuide", "getHasShowBottomSwitchNewGuide()Z", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl5);
        MutablePropertyReference1Impl mutablePropertyReference1Impl6 = new MutablePropertyReference1Impl(FeedHomeUtils.class, "skipElementGuideCount", "getSkipElementGuideCount()I", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl6);
        MutablePropertyReference1Impl mutablePropertyReference1Impl7 = new MutablePropertyReference1Impl(FeedHomeUtils.class, "lastMainFeedTabShowFragmentType", "getLastMainFeedTabShowFragmentType()Ljava/lang/String;", 0);
        Reflection.mutableProperty1(mutablePropertyReference1Impl7);
        b = new KProperty[]{mutablePropertyReference1Impl, mutablePropertyReference1Impl2, mutablePropertyReference1Impl3, mutablePropertyReference1Impl4, mutablePropertyReference1Impl5, mutablePropertyReference1Impl6, mutablePropertyReference1Impl7};
        f100912a = new FeedHomeUtils();
        f100913c = "";
        f100915g = LazyKt__LazyJVMKt.lazy(new Function0<ImmersiveFeedModeConfig>() { // from class: com.vega.feedx.main.ui.preview.FeedHomeUtils$immersiveFeedModeConfigSetting$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.config.IConfig, com.vega.feedx.settings.ImmersiveFeedModeConfig] */
            @Override // kotlin.jvm.functions.Function0
            public final ImmersiveFeedModeConfig invoke() {
                return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ImmersiveFeedModeConfigSetting.class));
            }
        });
        h = LazyKt__LazyJVMKt.lazy(new Function0<ImmersiveFeedSwitchConfig>() { // from class: com.vega.feedx.main.ui.preview.FeedHomeUtils$immersiveFeedSwitchConfigSetting$2
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.config.IConfig, com.vega.feedx.config.ImmersiveFeedSwitchConfig] */
            @Override // kotlin.jvm.functions.Function0
            public final ImmersiveFeedSwitchConfig invoke() {
                return ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(ImmersiveFeedSwitchConfigSetting.class));
            }
        });
        boolean z2 = e().e().length() > 0;
        i = z2;
        ModuleCommon moduleCommon = ModuleCommon.INSTANCE;
        Application application = moduleCommon.getApplication();
        Boolean bool = Boolean.FALSE;
        k = KvStorageKt.d(application, bool, "immersive_fragment", "hasShowInspirationListGuideCapsule", false);
        l = KvStorageKt.d(moduleCommon.getApplication(), bool, "immersive_fragment", "hasShowInspirationListGuideRightBtn", false);
        m = KvStorageKt.d(moduleCommon.getApplication(), bool, "immersive_fragment", "has_show_imerssive_switch_guide", false);
        n = KvStorageKt.d(moduleCommon.getApplication(), bool, "immersive_fragment", "has_show_long_click_guide", false);
        o = KvStorageKt.d(moduleCommon.getApplication(), bool, "immersive_fragment", "has_show_click_tab_switch_guide", false);
        p = -1L;
        q = KvStorageKt.d(moduleCommon.getApplication(), 0, "immersive_fragment", "skipElementGuideCount", false);
        r = KvStorageKt.d(moduleCommon.getApplication(), "default", "immersive_fragment", "last_main_feed_tab_show_fragment_type", false);
        if (z2) {
            FeedLandingManager feedLandingManager = FeedLandingManager.f100194a;
            feedLandingManager.getClass();
            if (Intrinsics.areEqual(FeedLandingManager.b(feedLandingManager, false, 2), "single")) {
                z = true;
            }
        }
        s = z;
    }

    public static boolean a() {
        return Intrinsics.areEqual(f().clickTabType, "preview_page") && i;
    }

    public static boolean b() {
        return Intrinsics.areEqual(f().doubleClickTabType, "refresh") && i;
    }

    public static boolean c() {
        return Intrinsics.areEqual(f().clickTabType, "none") && i && l();
    }

    public static boolean d() {
        return !Intrinsics.areEqual(f().clickTabType, "none") && i;
    }

    public static ImmersiveFeedModeConfig e() {
        return (ImmersiveFeedModeConfig) f100915g.getValue();
    }

    public static ImmersiveFeedSwitchConfig f() {
        return (ImmersiveFeedSwitchConfig) h.getValue();
    }

    public static List h(FeedItem feedItem) {
        Intrinsics.checkNotNullParameter(feedItem, "");
        return feedItem.getSupportInspirationElements(e().d(), e().c());
    }

    public static boolean i() {
        return Intrinsics.areEqual("right", e().c()) || Intrinsics.areEqual("capsule", e().c());
    }

    public static boolean k(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return StringsKt__StringsJVMKt.equals(uri.getScheme(), "capcut", true) && StringsKt__StringsJVMKt.equals(uri.getHost(), "main", true) && StringsKt__StringsJVMKt.equals(uri.getPath(), "/tabbar", true) && Intrinsics.areEqual(uri.getQueryParameter("index"), "7") && Intrinsics.areEqual(uri.getQueryParameter("immersive_style_source"), "survey");
    }

    public static boolean l() {
        return Intrinsics.areEqual(e().e(), "immersive_bottom") || Intrinsics.areEqual(e().e(), "immersive_bottom_normal");
    }

    public static boolean m() {
        return Intrinsics.areEqual(e().e(), "immersive_top") || Intrinsics.areEqual(e().e(), "immersive_top_normal");
    }

    public static void n() {
        if (i && l()) {
            ThreadUtilKt.b(0L, new Function0<Unit>() { // from class: com.vega.feedx.main.ui.preview.FeedHomeUtils$resetShowBottomSwitchGuide$1
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    FeedHomeUtils feedHomeUtils = FeedHomeUtils.f100912a;
                    feedHomeUtils.getClass();
                    if (FeedHomeUtils.c()) {
                        FeedHomeUtils.o.setValue(feedHomeUtils, FeedHomeUtils.b[4], Boolean.FALSE);
                    } else {
                        FeedHomeUtils.n.setValue(feedHomeUtils, FeedHomeUtils.b[3], Boolean.FALSE);
                    }
                    return Unit.INSTANCE;
                }
            });
        }
    }

    public static void o(final String str) {
        Intrinsics.checkNotNullParameter(str, "");
        if (e) {
            return;
        }
        ThreadUtilKt.b(0L, new Function0<Unit>() { // from class: com.vega.feedx.main.ui.preview.FeedHomeUtils$setFeedLoadingFinish$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final Unit invoke() {
                ImmersiveFeedSwitchHelper.f100814a.getClass();
                BLog.i(ImmersiveFeedSwitchHelper.b, "feed load finish in " + str);
                FeedHomeUtils.f100912a.getClass();
                FeedHomeUtils.e = true;
                return Unit.INSTANCE;
            }
        });
    }

    public static void p(Map map) {
        Intrinsics.checkNotNullParameter(map, "");
        map.put("is_immersive", ProfileManager.VERSION);
        map.put("enter_from", "draw_loadmore");
    }

    public final String g() {
        return (String) r.getValue(this, b[6]);
    }

    public final boolean j() {
        return c() ? ((Boolean) n.getValue(this, b[3])).booleanValue() : ((Boolean) o.getValue(this, b[4])).booleanValue();
    }
}
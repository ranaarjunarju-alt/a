package com.vega.feedx.nativefeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import com.bytedance.helios.statichook.lifecycle.ActivityLifecycleHook;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.bytedance.sysoptimizer.EnterTransitionCrashOptimizer;
import com.lemon.lvoverseas.R;
import com.vega.config.ConfigSettingsKt;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.feedx.ailab.utils.AiLabReporter;
import com.vega.feedx.base.ui.BaseContentFragment;
import com.vega.feedx.base.ui.BaseImmerseActivity;
import com.vega.feedx.main.bean.FeedCategoryItem;
import com.vega.feedx.main.bean.FirstCategoryItem;
import com.vega.feedx.nativefeed.FeedContainerFragment;
import com.vega.feedx.nativefeed.action.CategoryScene;
import com.vega.feedx.nativefeed.perf.FeedContainerShowCostMonitor;
import com.vega.feedx.nativefeed.utils.NextFeedExtKt;
import com.vega.feedx.unifyagent.AiLabPageConfigSettingsKt;
import com.vega.feedx.unifyagent.InputBoxSettings;
import com.vega.feedx.unifyagent.PromptStrategy;
import com.vega.feedx.unifyagent.UnifyAgentHomepageSettingsKt;
import com.vega.infrastructure.extensions.ActivityExtKt;
import com.vega.nextfeed.context.TemplateFeedContext;
import com.vega.performance.BadParcelableExceptionOpt;
import com.vega.theme.ThemeUtils;
import com.vega.theme.config.IThemeProvider;
import com.vega.theme.config.LvThemeContext;
import com.vega.theme.config.Theme;
import com.vega.ui.activity.ActivitySystemBarExtensionsKt;
import com.vega.ui.util.DisplayUtils;
import com.vega.videoagentapi.AiLabAgentCommonService;
import com.vega.videoagentapi.IAiLabAgentService;
import com.vega.videoagentapi.common.widget.InputModalTypePickerPopup;
import com.vega.videoagentapi.unifyagent.UnifyAgentReportConstants;
import com.vega.videoagentapi.unifyagent.blur.BackgroundBlurHelper;
import com.vega.videoagentapi.unifyagent.settings.TrendsAgentSugImproveConfig;
import com.vega.videoagentapi.unifyagent.settings.TrendsAgentSugImproveSettings;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes25.dex */
public final class FeedContainerActivity extends BaseImmerseActivity implements IThemeProvider, IFeedActionCallback {
    public static final /* synthetic */ int W = 0;
    public String A;
    public String B;
    public String C;
    public boolean D;
    public String F;
    public FrameLayout K;
    public View P;
    public String Q;
    public String R;
    public String S;
    public String T;
    public boolean U;
    public boolean V;
    public String w;
    public String x;
    public String y;
    public boolean z;
    public String E = "";
    public String G = "";
    public String H = "";
    public final Lazy I = LazyKt__LazyJVMKt.lazy(new Function0<LvThemeContext>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$themeContext$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final LvThemeContext invoke() {
            LvThemeContext lvThemeContext = new LvThemeContext(this.e, Theme.f132644c);
            lvThemeContext.b(true);
            return lvThemeContext;
        }
    });

    /* renamed from: J, reason: collision with root package name */
    public final Lazy f101487J = LazyKt__LazyJVMKt.lazy(new Function0<FeedContainerFragment>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$containerFragment$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final FeedContainerFragment invoke() {
            FeedContainerFragment.Companion companion = FeedContainerFragment.M;
            FeedContainerActivity feedContainerActivity = this.e;
            companion.getClass();
            Intrinsics.checkNotNullParameter(feedContainerActivity, "");
            FeedContainerFragment feedContainerFragment = new FeedContainerFragment();
            feedContainerFragment.f133364g = feedContainerActivity;
            FeedContainerActivity feedContainerActivity2 = this.e;
            Bundle arguments = feedContainerFragment.getArguments();
            Bundle bundle = arguments == null ? new Bundle() : arguments;
            feedContainerActivity2.E1(bundle);
            if (arguments == null && !feedContainerFragment.isAdded()) {
                feedContainerFragment.setArguments(bundle);
            }
            return feedContainerFragment;
        }
    });
    public final Lazy L = LazyKt__LazyJVMKt.lazy(new Function0<AiLabAgentCommonService>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$commonService$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final AiLabAgentCommonService invoke() {
            return AiLabAgentCommonService.b;
        }
    });
    public final Lazy M = LazyKt__LazyJVMKt.lazy(new Function0<IAiLabAgentService>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$aiLabAgentService$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.vega.videoagentapi.IAiLabAgentService, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final IAiLabAgentService invoke() {
            return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAiLabAgentService.class), null);
        }
    });
    public final Lazy N = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$isFixedShapeInputBox$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(AiLabPageConfigSettingsKt.d());
        }
    });
    public final Lazy O = LazyKt__LazyJVMKt.lazy(new Function0<InputModalTypePickerPopup>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$inputModalPickerPopup$2
        {
            super(0);
        }

        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final InputModalTypePickerPopup invoke() {
            final FeedContainerActivity feedContainerActivity = this.e;
            return new InputModalTypePickerPopup(new Function0<Unit>() { // from class: com.vega.feedx.nativefeed.FeedContainerActivity$inputModalPickerPopup$2.1
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final Unit invoke() {
                    ((IAiLabAgentService) feedContainerActivity.M.getValue()).g(feedContainerActivity.P);
                    return Unit.INSTANCE;
                }
            });
        }
    });

    /* loaded from: classes8.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    public static Intent INVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent(FeedContainerActivity feedContainerActivity) {
        Context context;
        Intent intent = feedContainerActivity.getIntent();
        if (intent != null && (context = BadParcelableExceptionOpt.f126209a) != null) {
            intent.setExtrasClassLoader(context.getClassLoader());
        }
        return intent;
    }

    public static void com_vega_feedx_nativefeed_FeedContainerActivity_com_bytedance_sysoptimizer_EnterTransitionLancet_onStop(FeedContainerActivity feedContainerActivity) {
        super.onStop();
        if (EnterTransitionCrashOptimizer.getContext() != null) {
            try {
                feedContainerActivity.getWindow().getDecorView().getViewTreeObserver().dispatchOnPreDraw();
            } catch (Throwable unused) {
            }
        }
    }

    public final void B1(String str, Map map, Map map2) {
        InputBoxSettings inputBoxSettings;
        List<PromptStrategy> listA;
        if (map2 == null || (inputBoxSettings = (InputBoxSettings) map2.get(NextFeedExtKt.f(((FeedContainerFragment) this.f101487J.getValue()).F.f125815a))) == null) {
            return;
        }
        int iHashCode = str.hashCode();
        if (iHashCode != -1752284411) {
            if (iHashCode != 112386354) {
                if (iHashCode != 798216644 || !str.equals("main_input")) {
                    return;
                } else {
                    listA = inputBoxSettings.c();
                }
            } else if (!str.equals("voice")) {
                return;
            } else {
                listA = inputBoxSettings.b();
            }
        } else if (!str.equals("add_material")) {
            return;
        } else {
            listA = inputBoxSettings.a();
        }
        if (listA == null) {
            return;
        }
        map.put("launch_prompt_strategy", UnifyAgentHomepageSettingsKt.a(listA));
    }

    public final Triple<String, String, Integer> C1() {
        Object objCreateFailure;
        String stringExtra;
        String stringExtra2;
        Intent intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent = INVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this);
        if (intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent != null && (stringExtra = intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent.getStringExtra("entrance_name")) != null) {
            Intent intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 = INVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this);
            if (intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent2 == null || (stringExtra2 = intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent2.getStringExtra("entrance_id")) == null) {
                stringExtra2 = "";
            }
            Intent intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent3 = INVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent(this);
            return new Triple<>(stringExtra, stringExtra2, Integer.valueOf(intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent3 != null ? intentINVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent3.getIntExtra("entrance_rank", -1) : -1));
        }
        try {
            objCreateFailure = new JSONObject(this.E);
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        if (Result.m17096isFailureimpl(objCreateFailure)) {
            objCreateFailure = null;
        }
        JSONObject jSONObject = (JSONObject) objCreateFailure;
        if (jSONObject == null) {
            return null;
        }
        String strOptString = jSONObject.optString("entrance_name");
        Intrinsics.checkNotNull(strOptString);
        if (strOptString.length() > 0) {
            return new Triple<>(strOptString, jSONObject.optString("entrance_id"), Integer.valueOf(jSONObject.optInt("entrance_rank", -1)));
        }
        return null;
    }

    public final void E1(Bundle bundle) {
        DisplayUtils displayUtils;
        int i;
        String str = this.w;
        if (str != null) {
            bundle.putString("feed_scene", str);
        }
        String str2 = this.x;
        if (str2 != null) {
            bundle.putString("filter_recommend", str2);
        }
        String str3 = this.y;
        if (str3 != null) {
            bundle.putString("recommend", str3);
        }
        String str4 = this.C;
        if (str4 != null) {
            bundle.putString("category_id", str4);
        }
        String str5 = this.F;
        if (str5 != null) {
            bundle.putString("track_name", str5);
        }
        bundle.putString("trends_agent_source", this.G);
        bundle.putString("tt_anchor_from", this.H);
        String str6 = this.A;
        if (str6 != null) {
            bundle.putString("tag_source", str6);
        }
        if (this.D) {
            if (((Boolean) this.N.getValue()).booleanValue()) {
                displayUtils = DisplayUtils.f134332a;
                i = 130;
            } else {
                displayUtils = DisplayUtils.f134332a;
                i = 100;
            }
            displayUtils.getClass();
            bundle.putInt("bottom_stub_height", DisplayUtils.b(i));
        }
    }

    public final AiLabAgentCommonService F1() {
        return (AiLabAgentCommonService) this.L.getValue();
    }

    public final Map<String, Object> G1() {
        Pair[] pairArr = new Pair[5];
        pairArr[0] = TuplesKt.to("action_location", "template_page_input_panel");
        String str = this.S;
        if (str == null) {
            str = "";
        }
        pairArr[1] = TuplesKt.to("sub_tab", str);
        String str2 = this.T;
        pairArr[2] = TuplesKt.to("root_category", str2 != null ? str2 : "");
        pairArr[3] = TuplesKt.to("input_enter_from", "template_input");
        String strTakeIfNotNullOrEmpty = ExtentionKt.takeIfNotNullOrEmpty(this.R);
        if (strTakeIfNotNullOrEmpty == null) {
            UnifyAgentReportConstants.Scene.f135569a.getClass();
            strTakeIfNotNullOrEmpty = UnifyAgentReportConstants.Scene.a();
        }
        pairArr[4] = TuplesKt.to("scene", strTakeIfNotNullOrEmpty);
        return MapsKt__MapsKt.mutableMapOf(pairArr);
    }

    public final void H1(String str, JSONObject jSONObject) throws JSONException {
        String strA;
        TemplateFeedContext templateFeedContext;
        CategoryScene categorySceneE;
        JSONObject jSONObject2 = jSONObject;
        Triple<String, String, Integer> tripleC1 = C1();
        if (tripleC1 != null) {
            String first = tripleC1.getFirst();
            String second = tripleC1.getSecond();
            int iIntValue = tripleC1.getThird().intValue();
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            jSONObject2.put("entrance_name", first);
            jSONObject2.put("entrance_id", second);
            jSONObject2.put("entrance_rank", iIntValue);
        }
        if (Intrinsics.areEqual(this.G, "home_tool")) {
            strA = "tool_ai_trends";
        } else if (Intrinsics.areEqual(this.A, "tool_videomaker")) {
            strA = this.A;
            if (strA == null) {
                strA = "";
            }
        } else {
            UnifyAgentReportConstants.Scene.f135569a.getClass();
            strA = UnifyAgentReportConstants.Scene.a();
        }
        FeedContainerFragment feedContainerFragment = (FeedContainerFragment) this.f101487J.getValue();
        if ((feedContainerFragment instanceof FeedContainerFragment) && feedContainerFragment != null && (templateFeedContext = feedContainerFragment.F.f125815a) != null && (categorySceneE = NextFeedExtKt.e(templateFeedContext)) != null) {
            FeedCategoryItem feedCategoryItemC = NextFeedExtKt.c(templateFeedContext, categorySceneE);
            FirstCategoryItem.Companion companion = FirstCategoryItem.Companion;
            String str2 = categorySceneE.f101498a;
            companion.getClass();
            this.S = FirstCategoryItem.Companion.a(str2);
            this.T = feedCategoryItemC != null ? feedCategoryItemC.getTrackName() : null;
        }
        AiLabReporter aiLabReporter = AiLabReporter.f99697a;
        String str3 = "ai_trends";
        if (!Intrinsics.areEqual(strA, "tool_ai_trends") && !Intrinsics.areEqual(this.G, "ai_trends")) {
            str3 = Intrinsics.areEqual(this.S, "ai_lab_image") ? "image_template" : "ailab_agent";
        }
        String str4 = this.S;
        String str5 = this.T;
        aiLabReporter.getClass();
        AiLabReporter.e(str, strA, "template_page_input_panel", str3, str4, str5, jSONObject2);
    }

    @Override // android.app.Activity
    public final void finish() {
        super.finish();
        if (Intrinsics.areEqual(this.A, "tool_videomaker")) {
            SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(this, "//main/tabbar");
            smartRouteBuildRoute.withParam("index", "11");
            smartRouteBuildRoute.withParam("is_from_tool_video_maker", true);
            smartRouteBuildRoute.open();
        }
        overridePendingTransition(0, R.anim.x);
    }

    @Override // com.vega.theme.config.IThemeProvider
    public final Theme k2() {
        ThemeUtils.f132621a.getClass();
        return ThemeUtils.b;
    }

    @Override // com.vega.feedx.base.ui.BaseFragmentActivity, com.vega.infrastructure.base.BaseActivity
    public final int m1() {
        return R.layout.lb;
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01b3  */
    @Override // com.vega.infrastructure.base.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o1(android.content.Intent r15) {
        /*
            r14 = this;
            java.lang.String r8 = "feed_scene"
            java.lang.String r7 = "filter_recommend"
            java.lang.String r6 = "recommend"
            java.lang.String r5 = "category_id"
            java.lang.String r3 = "track_name"
            java.lang.String r4 = "is_show_input_box"
            java.lang.String r2 = ""
            if (r15 == 0) goto L6e
            android.os.Bundle r1 = r15.getExtras()
            if (r1 == 0) goto L4e
            java.lang.String r0 = r1.getString(r8)
            r14.w = r0
            java.lang.String r0 = r1.getString(r7)
            r14.x = r0
            java.lang.String r0 = r1.getString(r6)
            r14.y = r0
            java.lang.String r0 = r1.getString(r5)
            r14.C = r0
            java.lang.String r0 = r1.getString(r3)
            r14.F = r0
            java.lang.String r0 = "tag_source"
            java.lang.String r0 = r1.getString(r0)
            r14.A = r0
            r0 = 0
            boolean r0 = r1.getBoolean(r4, r0)
            r14.D = r0
            java.lang.String r0 = "extra_json"
            java.lang.String r0 = r1.getString(r0, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r2)
            r14.E = r0
        L4e:
            android.net.Uri r1 = r15.getData()
            if (r1 == 0) goto L6e
            r1.buildUpon()
            java.lang.String r0 = "source"
            java.lang.String r0 = r1.getQueryParameter(r0)
            if (r0 == 0) goto L64
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r14.G = r0
        L64:
            java.lang.String r0 = "enter_from"
            java.lang.String r0 = r1.getQueryParameter(r0)
            if (r0 != 0) goto L1c6
        L6c:
            r14.H = r2
        L6e:
            java.lang.String r1 = r14.G
            java.lang.String r0 = "home_tool"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            java.lang.String r1 = "ai_trends"
            if (r0 != 0) goto L82
            java.lang.String r0 = r14.G
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L91
        L82:
            r14.A = r1
            r1 = 1
            r14.D = r1
            java.lang.String r0 = "ai_lab_trends"
            r14.x = r0
            java.lang.String r0 = "ai"
            r14.w = r0
            r14.z = r1
        L91:
            r2 = 0
            if (r15 == 0) goto L1c3
            java.lang.String r0 = com.vega.homepage.report.HomeToolReportInfoExtKt.c(r15)
        L98:
            r14.B = r0
            java.lang.String r12 = "trends_agent_source"
            if (r15 == 0) goto La3
            java.lang.String r0 = r14.G
            r15.putExtra(r12, r0)
        La3:
            java.lang.String r11 = "tt_anchor_from"
            if (r15 == 0) goto Lac
            java.lang.String r0 = r14.H
            r15.putExtra(r11, r0)
        Lac:
            kotlin.Lazy r0 = r14.f101487J
            java.lang.Object r10 = r0.getValue()
            androidx.fragment.app.Fragment r10 = (androidx.fragment.app.Fragment) r10
            boolean r0 = r10 instanceof com.vega.feedx.nativefeed.FeedContainerFragment
            if (r0 == 0) goto Lee
            android.os.Bundle r13 = r10.getArguments()
            if (r13 != 0) goto L1c0
            android.os.Bundle r9 = new android.os.Bundle
            r9.<init>()
        Lc3:
            r14.E1(r9)
            boolean r0 = r14.D
            r9.putBoolean(r4, r0)
            java.lang.String r1 = "is_show_history"
            boolean r0 = r14.z
            r9.putBoolean(r1, r0)
            java.lang.String r0 = r14.G
            r9.putString(r12, r0)
            java.lang.String r0 = r14.H
            r9.putString(r11, r0)
            java.lang.String r1 = "home_tool_report_info"
            java.lang.String r0 = r14.B
            r9.putString(r1, r0)
            if (r13 != 0) goto Lee
            boolean r0 = r10.isAdded()
            if (r0 != 0) goto Lee
            r10.setArguments(r9)
        Lee:
            if (r15 == 0) goto L130
            android.net.Uri r9 = r15.getData()
            if (r9 == 0) goto L130
            android.net.Uri$Builder r1 = r9.buildUpon()
            java.lang.String r0 = r9.getQueryParameter(r8)
            if (r0 == 0) goto L102
            r14.w = r0
        L102:
            java.lang.String r0 = r9.getQueryParameter(r7)
            if (r0 == 0) goto L10a
            r14.x = r0
        L10a:
            java.lang.String r0 = r9.getQueryParameter(r6)
            if (r0 == 0) goto L112
            r14.y = r0
        L112:
            java.lang.String r0 = r9.getQueryParameter(r5)
            if (r0 == 0) goto L11a
            r14.C = r0
        L11a:
            java.lang.String r0 = r9.getQueryParameter(r3)
            if (r0 == 0) goto L122
            r14.F = r0
        L122:
            java.lang.String r0 = r9.getQueryParameter(r4)
            if (r0 == 0) goto L12e
            boolean r0 = java.lang.Boolean.parseBoolean(r0)
            r14.D = r0
        L12e:
            if (r1 != 0) goto L179
        L130:
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            java.lang.String r0 = "capcut"
            android.net.Uri$Builder r1 = r1.scheme(r0)
            java.lang.String r0 = "template"
            android.net.Uri$Builder r1 = r1.authority(r0)
            java.lang.String r0 = r14.w
            if (r0 == 0) goto L14a
            if (r1 == 0) goto L14a
            r1.appendQueryParameter(r8, r0)
        L14a:
            java.lang.String r0 = r14.x
            if (r0 == 0) goto L153
            if (r1 == 0) goto L153
            r1.appendQueryParameter(r7, r0)
        L153:
            java.lang.String r0 = r14.y
            if (r0 == 0) goto L15c
            if (r1 == 0) goto L15c
            r1.appendQueryParameter(r6, r0)
        L15c:
            java.lang.String r0 = r14.C
            if (r0 == 0) goto L165
            if (r1 == 0) goto L165
            r1.appendQueryParameter(r5, r0)
        L165:
            java.lang.String r0 = r14.F
            if (r0 == 0) goto L1bd
            if (r1 == 0) goto L177
            r1.appendQueryParameter(r3, r0)
        L16e:
            boolean r0 = r14.D
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r1.appendQueryParameter(r4, r0)
        L177:
            if (r15 != 0) goto L1b3
        L179:
            androidx.fragment.app.FragmentManager r1 = r14.getSupportFragmentManager()
            r0 = 2131302162(0x7f091712, float:1.8222402E38)
            androidx.fragment.app.Fragment r3 = r1.findFragmentById(r0)
            boolean r0 = r3 instanceof com.vega.feedx.nativefeed.FeedContainerFragment
            if (r0 == 0) goto L18a
            if (r3 != 0) goto L192
        L18a:
            kotlin.Lazy r0 = r14.f101487J
            java.lang.Object r3 = r0.getValue()
            androidx.fragment.app.Fragment r3 = (androidx.fragment.app.Fragment) r3
        L192:
            android.os.Bundle r2 = r3.getArguments()
            if (r2 != 0) goto L1b1
            android.os.Bundle r1 = new android.os.Bundle
            r1.<init>()
        L19d:
            r14.E1(r1)
            boolean r0 = r14.D
            r1.putBoolean(r4, r0)
            if (r2 != 0) goto L1b0
            boolean r0 = r3.isAdded()
            if (r0 != 0) goto L1b0
            r3.setArguments(r1)
        L1b0:
            return
        L1b1:
            r1 = r2
            goto L19d
        L1b3:
            if (r1 == 0) goto L1b9
            android.net.Uri r2 = r1.build()
        L1b9:
            r15.setData(r2)
            goto L179
        L1bd:
            if (r1 == 0) goto L177
            goto L16e
        L1c0:
            r9 = r13
            goto Lc3
        L1c3:
            r0 = r2
            goto L98
        L1c6:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            r2 = r0
            goto L6c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.feedx.nativefeed.FeedContainerActivity.o1(android.content.Intent):void");
    }

    @Override // com.vega.feedx.base.ui.BaseFragmentActivity, com.vega.infrastructure.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        ActivityLifecycleHook.a(this, bundle);
        super.onCreate(bundle);
        FeedContainerShowCostMonitor.c();
        ActivitySystemBarExtensionsKt.l(this);
        ActivitySystemBarExtensionsKt.j(this, null);
        ActivityExtKt.c(this, true);
        overridePendingTransition(R.anim.b, 0);
        if (((TrendsAgentSugImproveConfig) ConfigSettingsKt.a(Reflection.getOrCreateKotlinClass(TrendsAgentSugImproveSettings.class))).b()) {
            F1().k(this);
        }
    }

    @Override // com.vega.infrastructure.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        ActivityLifecycleHook.b(this);
        super.onDestroy();
        BackgroundBlurHelper.f135570a.e("tag_unified_agent_feed_page");
    }

    @Override // com.vega.infrastructure.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onPause() {
        ActivityLifecycleHook.c(this);
        super.onPause();
        FeedContainerShowCostMonitor.f101779g = -1L;
        FeedContainerShowCostMonitor.h = -1L;
        FeedContainerShowCostMonitor.b.removeCallbacks(FeedContainerShowCostMonitor.f101777c);
        FeedContainerShowCostMonitor.Session session = FeedContainerShowCostMonitor.e;
        FeedContainerShowCostMonitor.e = null;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public final void onResume() {
        ActivityLifecycleHook.d(this);
        super.onResume();
        if (Intrinsics.areEqual(this.G, "home_tool") || Intrinsics.areEqual(this.G, "ai_trends")) {
            F1().i();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStart() {
        ActivityLifecycleHook.e(this);
        super.onStart();
    }

    @Override // com.vega.infrastructure.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onStop() {
        ActivityLifecycleHook.f(this);
        com_vega_feedx_nativefeed_FeedContainerActivity_com_bytedance_sysoptimizer_EnterTransitionLancet_onStop(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v20, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v7, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r1v8, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r5v2, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r8v14, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r8v15, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r8v20, resolved type: T */
    /* JADX DEBUG: Multi-variable search result rejected for r9v4, resolved type: com.vega.videoagentapi.AiLabAgentCommonService */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:36:0x025d A[PHI: r3
      0x025d: PHI (r3v19 java.lang.String) = (r3v16 java.lang.String), (r3v15 java.lang.String) binds: [B:102:0x03f4, B:35:0x025b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0275  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x027e A[PHI: r2
      0x027e: PHI (r2v38 java.lang.String) = (r2v36 java.lang.String), (r2v33 java.lang.String) binds: [B:94:0x03d9, B:42:0x027c] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x029c A[PHI: r8
      0x029c: PHI (r8v13 java.lang.String) = (r8v21 java.lang.String), (r8v22 java.lang.String) binds: [B:45:0x028e, B:49:0x029a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:57:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x03c4  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x03e1  */
    @Override // com.vega.feedx.base.ui.BaseFragmentActivity, com.vega.infrastructure.base.BaseActivity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void p1(android.view.ViewGroup r28) {
        /*
            r27 = this;
            java.lang.String r4 = ""
            r6 = r28
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r4)
            r0 = r27
            super.p1(r6)
            com.vega.core.utils.SizeUtil r2 = com.vega.core.utils.SizeUtil.f79677a
            android.content.Context r1 = r6.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r2.getClass()
            int r2 = com.vega.core.utils.SizeUtil.c(r1)
            android.content.Context r1 = r6.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            int r11 = com.vega.core.utils.SizeUtil.d(r1)
            com.vega.videoagentapi.unifyagent.blur.util.BackgroundHelper r1 = com.vega.videoagentapi.unifyagent.blur.util.BackgroundHelper.f135580a
            r1.getClass()
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r11, r2, r1)
            android.graphics.Canvas r10 = new android.graphics.Canvas
            r10.<init>(r8)
            android.graphics.Paint r9 = new android.graphics.Paint
            r1 = 1
            r9.<init>(r1)
            android.graphics.LinearGradient r14 = new android.graphics.LinearGradient
            r15 = 0
            float r7 = (float) r2
            r13 = 4
            int[] r3 = new int[r13]
            java.lang.String r12 = "#EFF4FA"
            int r2 = android.graphics.Color.parseColor(r12)
            r5 = 0
            r3[r5] = r2
            int r2 = android.graphics.Color.parseColor(r12)
            r3[r1] = r2
            int r12 = android.graphics.Color.parseColor(r12)
            r2 = 2
            r3[r2] = r12
            java.lang.String r2 = "#FFFFFF"
            int r12 = android.graphics.Color.parseColor(r2)
            r2 = 3
            r3[r2] = r12
            float[] r2 = new float[r13]
            r2 = {x0402: FILL_ARRAY_DATA , data: [0, 1050253722, 1063675494, 1065353216} // fill-array
            android.graphics.Shader$TileMode r21 = android.graphics.Shader.TileMode.CLAMP
            r16 = r15
            r17 = r15
            r18 = r7
            r19 = r3
            r20 = r2
            r14.<init>(r15, r16, r17, r18, r19, r20, r21)
            r9.setShader(r14)
            float r2 = (float) r11
            r10 = r10
            r11 = r15
            r12 = r15
            r13 = r2
            r14 = r7
            r15 = r9
            r10.drawRect(r11, r12, r13, r14, r15)
            android.content.res.Resources r3 = r6.getResources()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            r2.<init>(r3, r8)
            r6.setBackground(r2)
            boolean r2 = r0.D
            if (r2 == 0) goto L3b0
            r2 = 2131298243(0x7f0907c3, float:1.8214454E38)
            android.view.View r9 = r6.findViewById(r2)
            r9.setVisibility(r5)
            android.content.Context r2 = r9.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            int r13 = com.vega.core.utils.SizeUtil.c(r2)
            android.content.Context r2 = r9.getContext()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            int r10 = com.vega.core.utils.SizeUtil.d(r2)
            kotlin.Lazy r2 = r0.N
            java.lang.Object r2 = r2.getValue()
            java.lang.Boolean r2 = (java.lang.Boolean) r2
            boolean r3 = r2.booleanValue()
            java.lang.String r17 = "#FFEFF4FA"
            java.lang.String r16 = "#00EFF4FA"
            r2 = 6
            if (r3 == 0) goto L15c
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r7 = android.graphics.Bitmap.createBitmap(r10, r13, r3)
            android.graphics.Canvas r8 = new android.graphics.Canvas
            r8.<init>(r7)
            android.graphics.Paint r3 = new android.graphics.Paint
            r3.<init>(r1)
            android.graphics.LinearGradient r12 = new android.graphics.LinearGradient
            r18 = 0
            float r11 = (float) r13
            int[] r13 = new int[r2]
            int r14 = android.graphics.Color.parseColor(r16)
            r13[r5] = r14
            int r14 = android.graphics.Color.parseColor(r16)
            r13[r1] = r14
            int r15 = android.graphics.Color.parseColor(r16)
            r14 = 2
            r13[r14] = r15
            int r15 = android.graphics.Color.parseColor(r16)
            r14 = 3
            r13[r14] = r15
            int r15 = android.graphics.Color.parseColor(r17)
            r14 = 4
            r13[r14] = r15
            int r15 = android.graphics.Color.parseColor(r17)
            r14 = 5
            r13[r14] = r15
            float[] r2 = new float[r2]
            r2 = {x040e: FILL_ARRAY_DATA , data: [0, 1028443341, 1036831949, 1061997773, 1063004406, 1065353216} // fill-array
            android.graphics.Shader$TileMode r24 = android.graphics.Shader.TileMode.CLAMP
            r19 = r18
            r20 = r18
            r21 = r11
            r22 = r13
            r23 = r2
            r17 = r12
            r17.<init>(r18, r19, r20, r21, r22, r23, r24)
            r3.setShader(r12)
            float r2 = (float) r10
            r13 = 0
            r12 = r8
            r14 = r13
            r15 = r2
            r16 = r11
            r17 = r3
            r12.drawRect(r13, r14, r15, r16, r17)
            android.content.res.Resources r3 = r9.getResources()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            r2.<init>(r3, r7)
        L13a:
            r9.setBackground(r2)
            r2 = 2131298224(0x7f0907b0, float:1.8214415E38)
            android.view.View r3 = r6.findViewById(r2)
            android.widget.FrameLayout r3 = (android.widget.FrameLayout) r3
            r0.K = r3
            if (r3 == 0) goto L159
            com.vega.core.utils.ScreenUtils r2 = com.vega.core.utils.ScreenUtils.f79669a
            r2.getClass()
            int r2 = com.vega.core.utils.ScreenUtils.a(r0)
            com.vega.ui.util.ViewExKt.m(r2, r3)
            com.vega.infrastructure.extensions.ViewExtKt.d(r3, r1)
        L159:
            java.lang.String r9 = r0.E
            goto L1d0
        L15c:
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.ARGB_8888
            android.graphics.Bitmap r12 = android.graphics.Bitmap.createBitmap(r10, r13, r3)
            android.graphics.Canvas r11 = new android.graphics.Canvas
            r11.<init>(r12)
            android.graphics.Paint r8 = new android.graphics.Paint
            r8.<init>(r1)
            android.graphics.LinearGradient r7 = new android.graphics.LinearGradient
            r18 = 0
            float r13 = (float) r13
            int[] r3 = new int[r2]
            int r14 = android.graphics.Color.parseColor(r16)
            r3[r5] = r14
            int r14 = android.graphics.Color.parseColor(r16)
            r3[r1] = r14
            int r15 = android.graphics.Color.parseColor(r16)
            r14 = 2
            r3[r14] = r15
            int r15 = android.graphics.Color.parseColor(r16)
            r14 = 3
            r3[r14] = r15
            int r15 = android.graphics.Color.parseColor(r17)
            r14 = 4
            r3[r14] = r15
            int r15 = android.graphics.Color.parseColor(r17)
            r14 = 5
            r3[r14] = r15
            float[] r2 = new float[r2]
            r2 = {x041e: FILL_ARRAY_DATA , data: [0, 1028443341, 1036831949, 1061997773, 1064011039, 1065353216} // fill-array
            android.graphics.Shader$TileMode r24 = android.graphics.Shader.TileMode.CLAMP
            r15 = 0
            r19 = r18
            r20 = r18
            r21 = r13
            r22 = r3
            r23 = r2
            r17 = r7
            r17.<init>(r18, r19, r20, r21, r22, r23, r24)
            r8.setShader(r7)
            float r2 = (float) r10
            r14 = r11
            r16 = r15
            r17 = r2
            r18 = r13
            r19 = r8
            r14.drawRect(r15, r16, r17, r18, r19)
            android.content.res.Resources r3 = r9.getResources()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            r2.<init>(r3, r12)
            goto L13a
        L1d0:
            com.google.gson.Gson r8 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Throwable -> L1f4
            com.vega.core.ext.ParameterizedTypeImpl r7 = new com.vega.core.ext.ParameterizedTypeImpl     // Catch: java.lang.Throwable -> L1f4
            java.lang.Class<java.util.Map> r6 = java.util.Map.class
            r2 = 2
            java.lang.reflect.Type[] r3 = new java.lang.reflect.Type[r2]     // Catch: java.lang.Throwable -> L1f4
            java.lang.Class<java.lang.String> r2 = java.lang.String.class
            r3[r5] = r2     // Catch: java.lang.Throwable -> L1f4
            java.lang.Class<java.lang.Object> r2 = java.lang.Object.class
            r3[r1] = r2     // Catch: java.lang.Throwable -> L1f4
            r7.<init>(r6, r3)     // Catch: java.lang.Throwable -> L1f4
            java.lang.Object r6 = r8.fromJson(r9, r7)     // Catch: java.lang.Throwable -> L1f4
            java.util.Map r6 = (java.util.Map) r6     // Catch: java.lang.Throwable -> L1f4
            if (r6 != 0) goto L201
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap     // Catch: java.lang.Throwable -> L1f4
            r6.<init>()     // Catch: java.lang.Throwable -> L1f4
            goto L201
        L1f4:
            r2 = move-exception
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r2)
            kotlin.Result.m17090constructorimpl(r2)
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap
            r6.<init>()
        L201:
            java.lang.String r3 = "tt_anchor_from"
            java.lang.String r2 = r0.H
            r6.put(r3, r2)
            java.lang.String r3 = r0.B
            if (r3 == 0) goto L211
            java.lang.String r2 = "home_tool_report_info"
            r6.put(r2, r3)
        L211:
            kotlin.jvm.internal.Ref$ObjectRef r5 = new kotlin.jvm.internal.Ref$ObjectRef
            r5.<init>()
            android.content.Intent r10 = INVOKEVIRTUAL_com_vega_feedx_nativefeed_FeedContainerActivity_com_vega_launcher_lancet_BadParcelableLancet_getInttent(r0)
            r20 = 0
            if (r10 == 0) goto L3fc
            java.lang.String r7 = "text"
            java.lang.String r3 = r10.getStringExtra(r7)
            if (r3 == 0) goto L235
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r2 = r3.length()
            if (r2 <= 0) goto L235
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r6.put(r7, r3)
        L235:
            java.lang.String r7 = "placeholder"
            java.lang.String r3 = r10.getStringExtra(r7)
            if (r3 == 0) goto L24c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r2 = r3.length()
            if (r2 <= 0) goto L24c
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            r6.put(r7, r3)
        L24c:
            java.lang.String r7 = "mode"
            java.lang.String r3 = r10.getStringExtra(r7)
            if (r3 == 0) goto L3e1
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r2 = r3.length()
            if (r2 <= 0) goto L3e1
        L25d:
            com.vega.videoagentapi.common.input.InputModalType$Companion r2 = com.vega.videoagentapi.common.input.InputModalType.b
            r2.getClass()
            com.vega.videoagentapi.common.input.InputModalType r3 = com.vega.videoagentapi.common.input.InputModalType.Companion.a(r3)
            if (r3 == 0) goto L3f8
            java.lang.String r2 = r3.f135539a
            r6.put(r7, r2)
        L26d:
            java.lang.String r8 = "deeplink_target_model_id"
            java.lang.String r2 = r10.getStringExtra(r8)
            if (r2 == 0) goto L3c4
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r7 = r2.length()
            if (r7 <= 0) goto L3c4
        L27e:
            r6.put(r8, r2)
            java.lang.String r8 = "input_show_default_select_model_first_time"
            java.lang.Boolean r7 = java.lang.Boolean.TRUE
            r6.put(r8, r7)
        L288:
            java.lang.String r9 = "chat_enter_from"
            java.lang.String r8 = r10.getStringExtra(r9)
            if (r8 != 0) goto L29c
            android.net.Uri r7 = r10.getData()
            if (r7 == 0) goto L3c0
            java.lang.String r8 = r7.getQueryParameter(r9)
            if (r8 == 0) goto L3c0
        L29c:
            int r7 = r8.length()
            if (r7 <= 0) goto L3bd
            r7 = 1
        L2a3:
            java.lang.Boolean r7 = java.lang.Boolean.valueOf(r7)
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L3c0
        L2ad:
            r5.element = r8
            if (r8 == 0) goto L2b6
            java.lang.String r7 = "chat_protocol_enter_from"
            r6.put(r7, r8)
        L2b6:
            java.util.Objects.toString(r3)
            if (r3 == 0) goto L2bd
            r0.V = r1
        L2bd:
            kotlin.jvm.internal.Ref$ObjectRef r7 = new kotlin.jvm.internal.Ref$ObjectRef
            r7.<init>()
            java.lang.String r1 = "enter_source"
            java.lang.Object r1 = r6.get(r1)
            if (r1 == 0) goto L2d0
            java.lang.String r1 = r1.toString()
            if (r1 != 0) goto L2d1
        L2d0:
            r1 = r4
        L2d1:
            r7.element = r1
            java.lang.String r8 = r0.G
            java.lang.String r1 = "home_tool"
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r8, r1)
            if (r1 == 0) goto L3b4
            java.lang.String r1 = "tool_ai_trends"
            r7.element = r1
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$inputClickNavigation$1 r1 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$inputClickNavigation$1
            r1.<init>(r0)
            java.lang.String r16 = "ai_trends_tool"
        L2e8:
            java.lang.Class<com.vega.feedx.unifyagent.UnifyAgentHomepageSettings> r8 = com.vega.feedx.unifyagent.UnifyAgentHomepageSettings.class
            java.lang.Object r8 = com.bytedance.news.common.settings.SettingsManager.obtain(r8)
            com.vega.feedx.unifyagent.UnifyAgentHomepageSettings r8 = (com.vega.feedx.unifyagent.UnifyAgentHomepageSettings) r8
            com.vega.feedx.unifyagent.UnifyAgentHomepageConfig r8 = r8.getConfig()
            com.vega.feedx.unifyagent.TemplateSettings r8 = r8.d()
            if (r8 == 0) goto L2fe
            java.util.Map r20 = r8.b()
        L2fe:
            java.lang.String r8 = "scene"
            java.lang.Object r9 = r6.get(r8)
            boolean r8 = r9 instanceof java.lang.String
            if (r8 == 0) goto L30c
            java.lang.String r9 = (java.lang.String) r9
            if (r9 != 0) goto L3b1
        L30c:
            r0.R = r4
            com.vega.videoagentapi.AiLabAgentCommonService r9 = r0.F1()
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$4 r11 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$4
            r18 = r11
            r19 = r0
            r21 = r6
            r22 = r7
            r23 = r1
            r18.<init>()
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$5 r12 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$5
            r18 = r12
            r19 = r0
            r21 = r6
            r22 = r7
            r23 = r1
            r18.<init>()
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$6 r13 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$6
            r18 = r13
            r19 = r0
            r21 = r6
            r22 = r7
            r23 = r1
            r18.<init>()
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$7 r8 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$7
            r8.<init>()
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$8 r4 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$8
            r4.<init>()
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$9 r18 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$9
            r18 = r18
            r19 = r0
            r21 = r6
            r22 = r7
            r23 = r1
            r18.<init>(r19)
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$10 r19 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$10
            r21 = r19
            r22 = r0
            r24 = r6
            r25 = r7
            r26 = r1
            r23 = r20
            r21.<init>(r22)
            T r5 = r5.element
            java.lang.String r5 = (java.lang.String) r5
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$11 r1 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$11
            r1.<init>()
            r15 = 1
            r17 = r4
            r20 = r3
            r21 = r2
            r22 = r5
            r23 = r1
            r10 = r0
            r14 = r8
            android.view.View r4 = r9.y(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23)
            boolean r1 = r4 instanceof android.view.ViewGroup
            if (r1 == 0) goto L393
            r2 = r4
            android.view.ViewGroup r2 = (android.view.ViewGroup) r2
            if (r2 == 0) goto L393
            r1 = 0
            r2.setClipChildren(r1)
            r2.setClipToPadding(r1)
        L393:
            r0.P = r4
            android.widget.FrameLayout r3 = r0.K
            if (r3 == 0) goto L3b0
            android.view.ViewGroup$MarginLayoutParams r2 = new android.view.ViewGroup$MarginLayoutParams
            r1 = -1
            r0 = -2
            r2.<init>(r1, r0)
            com.vega.ui.util.DisplayUtils r0 = com.vega.ui.util.DisplayUtils.f134332a
            r0.getClass()
            r0 = 12
            int r0 = com.vega.ui.util.DisplayUtils.b(r0)
            r2.topMargin = r0
            r3.addView(r4, r2)
        L3b0:
            return
        L3b1:
            r4 = r9
            goto L30c
        L3b4:
            com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$inputClickNavigation$2 r1 = new com.vega.feedx.nativefeed.FeedContainerActivity$initInputFrame$inputClickNavigation$2
            r1.<init>(r0)
            java.lang.String r16 = "unify_agent"
            goto L2e8
        L3bd:
            r7 = 0
            goto L2a3
        L3c0:
            r8 = r20
            goto L2ad
        L3c4:
            android.net.Uri r7 = r10.getData()
            if (r7 == 0) goto L3dd
            java.lang.String r2 = "model"
            java.lang.String r2 = r7.getQueryParameter(r2)
            if (r2 == 0) goto L3dd
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            int r7 = r2.length()
            if (r7 <= 0) goto L3dd
            goto L27e
        L3dd:
            r2 = r20
            goto L288
        L3e1:
            android.net.Uri r2 = r10.getData()
            if (r2 == 0) goto L3f8
            java.lang.String r3 = r2.getQueryParameter(r7)
            if (r3 == 0) goto L3f8
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
            int r2 = r3.length()
            if (r2 <= 0) goto L3f8
            goto L25d
        L3f8:
            r3 = r20
            goto L26d
        L3fc:
            r3 = r20
            r2 = r20
            goto L2b6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.feedx.nativefeed.FeedContainerActivity.p1(android.view.ViewGroup):void");
    }

    @Override // com.vega.feedx.base.ui.BaseFragmentActivity, com.vega.theme.ThemeContextActivity
    public final LvThemeContext u1() {
        return (LvThemeContext) this.I.getValue();
    }

    @Override // com.vega.feedx.base.ui.BaseFragmentActivity
    public final BaseContentFragment v1() {
        return (BaseContentFragment) this.f101487J.getValue();
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008a  */
    @Override // com.vega.feedx.nativefeed.IFeedActionCallback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void w0(com.vega.nextfeed.action.IFeedAction r5) {
        /*
            r4 = this;
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = r5 instanceof com.vega.feedx.nativefeed.action.TemplateFirstCategoryItemAction
            if (r0 == 0) goto L96
            r1 = r5
            com.vega.feedx.nativefeed.action.TemplateFirstCategoryItemAction r1 = (com.vega.feedx.nativefeed.action.TemplateFirstCategoryItemAction) r1
            com.vega.nextfeed.action.CellBehavior r2 = r1.e
            com.vega.nextfeed.action.CellBehavior$DidSelect r0 = com.vega.nextfeed.action.CellBehavior.DidSelect.f125759a
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r0)
            if (r0 == 0) goto L96
            com.vega.feedx.main.bean.FirstCategoryItem r0 = r1.f101506g
            r0.getId()
            com.vega.feedx.main.bean.FirstCategoryItem r0 = r1.f101506g
            java.lang.String r0 = r0.getId()
            r4.Q = r0
            java.lang.String r3 = "ai_lab_trends"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r3)
            java.lang.String r2 = "ai_lab_image"
            if (r0 != 0) goto L35
            java.lang.String r0 = r4.Q
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r2)
            if (r0 == 0) goto L3c
        L35:
            com.vega.videoagentapi.AiLabAgentCommonService r0 = r4.F1()
            r0.i()
        L3c:
            kotlin.Lazy r0 = r4.N
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L51
            boolean r0 = r4.V
            if (r0 == 0) goto L52
            r0 = 0
            r4.V = r0
        L51:
            return
        L52:
            com.vega.feedx.main.bean.FirstCategoryItem r0 = r1.f101506g
            java.lang.String r1 = r0.getId()
            int r0 = r1.hashCode()
            switch(r0) {
                case -391264552: goto L60;
                case 928368095: goto L6c;
                case 1959298258: goto L73;
                case 1971187698: goto L7a;
                default: goto L5f;
            }
        L5f:
            goto L51
        L60:
            java.lang.String r0 = "ai_lab_all"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L69
            goto L51
        L69:
            com.vega.videoagentapi.common.input.InputModalType r1 = com.vega.videoagentapi.common.input.InputModalType.f135536c
            goto L88
        L6c:
            boolean r0 = r1.equals(r3)
            if (r0 != 0) goto L83
            goto L51
        L73:
            boolean r0 = r1.equals(r2)
            if (r0 != 0) goto L86
            goto L51
        L7a:
            java.lang.String r0 = "ai_lab_video"
            boolean r0 = r1.equals(r0)
            if (r0 != 0) goto L83
            goto L51
        L83:
            com.vega.videoagentapi.common.input.InputModalType r1 = com.vega.videoagentapi.common.input.InputModalType.f135537d
            goto L88
        L86:
            com.vega.videoagentapi.common.input.InputModalType r1 = com.vega.videoagentapi.common.input.InputModalType.e
        L88:
            if (r1 == 0) goto L51
            kotlin.Lazy r0 = r4.M
            java.lang.Object r0 = r0.getValue()
            com.vega.videoagentapi.IAiLabAgentService r0 = (com.vega.videoagentapi.IAiLabAgentService) r0
            r0.r(r1)
            goto L51
        L96:
            boolean r0 = r5 instanceof com.vega.feedx.nativefeed.action.TemplateSecondCategoryItemAction
            if (r0 == 0) goto L51
            com.vega.feedx.nativefeed.action.TemplateSecondCategoryItemAction r5 = (com.vega.feedx.nativefeed.action.TemplateSecondCategoryItemAction) r5
            com.vega.nextfeed.action.CellBehavior r1 = r5.e
            com.vega.nextfeed.action.CellBehavior$DidSelect r0 = com.vega.nextfeed.action.CellBehavior.DidSelect.f125759a
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            if (r0 == 0) goto L51
            com.vega.feedx.main.bean.FeedCategoryItem r0 = r5.f101508g
            r0.getTrackName()
            boolean r0 = r4.U
            if (r0 == 0) goto Lb0
            return
        Lb0:
            boolean r0 = r4.D
            if (r0 == 0) goto Lbe
            com.vega.feedx.nativefeed.FeedContainerActivity$feedActionCallback$2 r2 = new com.vega.feedx.nativefeed.FeedContainerActivity$feedActionCallback$2
            r2.<init>()
            r0 = 500(0x1f4, double:2.47E-321)
            com.vega.infrastructure.extensions.ThreadUtilKt.b(r0, r2)
        Lbe:
            r0 = 1
            r4.U = r0
            goto L51
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.feedx.nativefeed.FeedContainerActivity.w0(com.vega.nextfeed.action.IFeedAction):void");
    }
}
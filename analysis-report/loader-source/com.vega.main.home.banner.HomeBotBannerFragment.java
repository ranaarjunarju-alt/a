package com.vega.main.home.banner;

import X.ViewTreeObserverOnWindowFocusChangeListenerC134594iO;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Choreographer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.helios.statichook.api.ExtraInfo;
import com.bytedance.helios.statichook.api.HeliosApiHook;
import com.bytedance.otis.ultimate.inflater.UltimateInflater;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lemon.cloud.ILocalDraftService;
import com.lemon.cloud.viewmodel.IHomeDraftViewModel;
import com.lemon.lvoverseas.R;
import com.vega.core.api.LoginService;
import com.vega.core.context.SPIService;
import com.vega.core.ext.ExtentionKt;
import com.vega.core.ext.LiveDataExtKt;
import com.vega.core.setting.SettingUrlConfig;
import com.vega.core.utils.AppUtils;
import com.vega.core.utils.LiveDataExKt;
import com.vega.infrastructure.extensions.ViewExtKt;
import com.vega.infrastructure.koin.GetViewModelKt;
import com.vega.infrastructure.koin.KoinScopeFragment;
import com.vega.infrastructure.koin.ScopeExKt;
import com.vega.infrastructure.vm.ViewModelActivityKt;
import com.vega.log.BLog;
import com.vega.main.HomepageBannerConfigEntity;
import com.vega.main.IMainService;
import com.vega.main.config.HomeDraftListOptimize;
import com.vega.main.home.banner.HomeBotBannerFragment;
import com.vega.main.home.viewmodel.HomeBotBannerViewModel;
import com.vega.main.home.viewmodel.HomeViewModel;
import com.vega.main.opt.HomeOptimizeManager;
import com.vega.main.widget.BottomBannerHelper;
import com.vega.performance.PerformanceManagerHelper;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.Job;
import org.koin.core.qualifier.Qualifier;

/* loaded from: classes12.dex */
public final class HomeBotBannerFragment extends KoinScopeFragment {
    public static final Companion j = new Companion();
    public static final int k = R.layout._7o_res_0x7f0c0554;
    public static final ILocalDraftService l = (ILocalDraftService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ILocalDraftService.class), null);

    /* renamed from: c, reason: collision with root package name */
    public final Lazy f114026c;

    /* renamed from: d, reason: collision with root package name */
    public final Lazy f114027d;
    public final Lazy e;
    public boolean f;

    /* renamed from: g, reason: collision with root package name */
    public SimpleDraweeView f114028g;
    public View h;
    public final ViewTreeObserverOnWindowFocusChangeListenerC134594iO i;

    /* loaded from: classes39.dex */
    public static final class Companion {
    }

    /* JADX WARN: Type inference failed for: r0v6, types: [X.4iO] */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.vega.main.home.banner.HomeBotBannerFragment$special$$inlined$koinActivityViewModel$default$1] */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.vega.main.home.banner.HomeBotBannerFragment$special$$inlined$koinActivityViewModel$default$3] */
    public HomeBotBannerFragment() {
        final ?? r1 = new Function0<FragmentActivity>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$special$$inlined$koinActivityViewModel$default$1
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentActivity invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return fragmentActivityRequireActivity;
            }
        };
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.NONE;
        this.f114026c = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<HomeViewModel>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$special$$inlined$koinActivityViewModel$default$2
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.main.home.viewmodel.HomeViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HomeViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r1;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(HomeViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        final ?? r12 = new Function0<FragmentActivity>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$special$$inlined$koinActivityViewModel$default$3
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentActivity invoke() {
                FragmentActivity fragmentActivityRequireActivity = this.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return fragmentActivityRequireActivity;
            }
        };
        this.f114027d = LazyKt__LazyJVMKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<HomeBotBannerViewModel>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$special$$inlined$koinActivityViewModel$default$4
            public final /* synthetic */ Qualifier f = null;
            public final /* synthetic */ Function0 h = null;
            public final /* synthetic */ Function0 i = null;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            /* JADX WARN: Type inference failed for: r0v5, types: [androidx.lifecycle.ViewModel, com.vega.main.home.viewmodel.HomeBotBannerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final HomeBotBannerViewModel invoke() {
                CreationExtras defaultViewModelCreationExtras;
                Fragment fragment = this;
                Qualifier qualifier = this.f;
                Function0 function0 = r12;
                Function0 function02 = this.h;
                Function0 function03 = this.i;
                ViewModelStore viewModelStore = ((ViewModelStoreOwner) function0.invoke()).getViewModelStore();
                if (function02 == null || (defaultViewModelCreationExtras = (CreationExtras) function02.invoke()) == null) {
                    defaultViewModelCreationExtras = fragment.getDefaultViewModelCreationExtras();
                    Intrinsics.checkNotNullExpressionValue(defaultViewModelCreationExtras, "");
                }
                return GetViewModelKt.a(Reflection.getOrCreateKotlinClass(HomeBotBannerViewModel.class), viewModelStore, defaultViewModelCreationExtras, qualifier, ScopeExKt.c(fragment), function03);
            }
        });
        this.e = LazyKt__LazyJVMKt.lazy(new Function0<IHomeDraftViewModel>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$draftListViewModel$2
            {
                super(0);
            }

            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function0
            public final IHomeDraftViewModel invoke() {
                HomeBotBannerFragment.j.getClass();
                ILocalDraftService iLocalDraftService = HomeBotBannerFragment.l;
                FragmentActivity fragmentActivityRequireActivity = this.e.requireActivity();
                Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                return iLocalDraftService.g(fragmentActivityRequireActivity, ViewModelActivityKt.a(this.e));
            }
        });
        this.i = new ViewTreeObserver.OnWindowFocusChangeListener() { // from class: X.4iO
            @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
            public final void onWindowFocusChanged(boolean z) {
                HomeBotBannerFragment homeBotBannerFragment = this.f21362a;
                homeBotBannerFragment.f = z;
                homeBotBannerFragment.d4();
            }
        };
    }

    public static void Z3(Fragment fragment, Intent intent) {
        HeliosApiHook heliosApiHook = new HeliosApiHook();
        Object[] objArr = {intent, 1002};
        ExtraInfo extraInfo = new ExtraInfo(false, "(Landroid/content/Intent;I)V", "dzBzEhQ/WMuSWFYoS1yMaS1wfduPdk7Kmu3vp5apRjWNjS0KdtJZDbhkezvJ3Xg=");
        if (heliosApiHook.preInvoke(11087, "androidx/fragment/app/Fragment", "startActivityForResult", fragment, objArr, "void", extraInfo).isIntercept()) {
            heliosApiHook.postInvoke(null, 11087, "androidx/fragment/app/Fragment", "startActivityForResult", fragment, objArr, extraInfo, false);
        } else {
            fragment.startActivityForResult(intent, 1002);
            heliosApiHook.postInvoke(null, 11087, "androidx/fragment/app/Fragment", "startActivityForResult", fragment, objArr, extraInfo, true);
        }
    }

    public final void Y3(Fragment fragment, HomepageBannerConfigEntity homepageBannerConfigEntity, Context context) {
        Object objCreateFailure;
        Intent intentBuildIntent;
        a4().getClass();
        HomeBotBannerViewModel.s6("click");
        if (ExtentionKt.isNotNullOrEmpty(homepageBannerConfigEntity.getAppLink())) {
            AppUtils appUtils = AppUtils.f79550a;
            String appLink = homepageBannerConfigEntity.getAppLink();
            appUtils.getClass();
            Intrinsics.checkNotNullParameter(fragment, "");
            Intrinsics.checkNotNullParameter(appLink, "");
            try {
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse(appLink));
                intent.setFlags(268435456);
                AppUtils.a(fragment, intent);
                if (PerformanceManagerHelper.blogEnable) {
                    BLog.i("HomeBotBannerFragment", "open app success!");
                }
                BottomBannerHelper bottomBannerHelper = BottomBannerHelper.f114784a;
                bottomBannerHelper.getClass();
                if (BottomBannerHelper.d()) {
                    return;
                }
                a4().f.setValue(Boolean.FALSE);
                bottomBannerHelper.c();
                return;
            } catch (Throwable unused) {
            }
        }
        String schema = homepageBannerConfigEntity.getSchema();
        try {
            Uri uri = Uri.parse(schema);
            if (StringsKt__StringsJVMKt.equals("capcut", uri != null ? uri.getScheme() : null, true)) {
                intentBuildIntent = new Intent("android.intent.action.VIEW", uri);
                intentBuildIntent.addFlags(268435456);
            } else {
                SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//main/web");
                smartRouteBuildRoute.withParam("web_url", schema);
                SettingUrlConfig settingUrlConfig = SettingUrlConfig.f79506a;
                Intrinsics.checkNotNull(smartRouteBuildRoute);
                settingUrlConfig.getClass();
                SettingUrlConfig.a(smartRouteBuildRoute);
                intentBuildIntent = smartRouteBuildRoute.buildIntent();
            }
            Z3(fragment, intentBuildIntent);
            if (PerformanceManagerHelper.blogEnable) {
                BLog.i("HomeBotBannerFragment", "open web success!");
            }
            objCreateFailure = Unit.INSTANCE;
            Result.m17090constructorimpl(objCreateFailure);
        } catch (Throwable th) {
            objCreateFailure = ResultKt.createFailure(th);
            Result.m17090constructorimpl(objCreateFailure);
        }
        Throwable thM17093exceptionOrNullimpl = Result.m17093exceptionOrNullimpl(objCreateFailure);
        if (thM17093exceptionOrNullimpl != null) {
            BLog.e("HomeBotBannerFragment", "open banner exception: " + thM17093exceptionOrNullimpl.getMessage());
        }
    }

    public final HomeBotBannerViewModel a4() {
        return (HomeBotBannerViewModel) this.f114027d.getValue();
    }

    public final void c4() {
        View viewFindViewById;
        View view = getView();
        if (view != null && (viewFindViewById = view.findViewById(R.id.bottom_banner_layout)) != null) {
            ViewExtKt.b(viewFindViewById);
        }
        a4().f114496d.setValue(Boolean.FALSE);
    }

    public final void d4() {
        Boolean bool;
        View view = getView();
        View viewFindViewById = view != null ? view.findViewById(R.id.bottom_banner_layout) : null;
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null && parentFragment.isVisible() && isVisible() && this.f && (bool = (Boolean) ((LiveData) a4().f114495c.getValue()).getValue()) != null && bool.booleanValue() && viewFindViewById != null && com.vega.util.ViewExtKt.a(viewFindViewById, 0.5f)) {
            a4().getClass();
            HomeBotBannerViewModel.s6("show");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean e4(final com.vega.main.home.banner.HomeBotBannerFragment r41, final androidx.fragment.app.FragmentActivity r42) {
        /*
            r40 = this;
            r2 = r40
            android.view.View r1 = r2.getView()
            r5 = 0
            if (r1 == 0) goto L1a5
            r0 = 2131313888(0x7f0944e0, float:1.8246185E38)
            android.view.View r4 = r1.findViewById(r0)
            android.view.ViewStub r4 = (android.view.ViewStub) r4
            r9 = 1
            if (r4 == 0) goto L17e
            com.vega.main.home.banner.CustomLayoutInflater r3 = new com.vega.main.home.banner.CustomLayoutInflater
            android.content.Context r1 = r2.requireContext()
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            r3.<init>(r1)
            r4.setLayoutInflater(r3)
            com.vega.main.widget.BottomBannerHelper r0 = com.vega.main.widget.BottomBannerHelper.f114784a
            r0.getClass()
            com.vega.main.HomepageBannerConfigEntity r0 = com.vega.main.widget.BottomBannerHelper.i
            if (r0 == 0) goto L1a0
            r4.inflate()
            java.lang.Class<com.vega.main.config.HomeBannerConfigSettings> r0 = com.vega.main.config.HomeBannerConfigSettings.class
            kotlin.reflect.KClass r0 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r0)
            com.vega.config.IConfig r0 = com.vega.config.ConfigSettingsKt.a(r0)
            com.vega.main.config.HomeBannerConfig r0 = (com.vega.main.config.HomeBannerConfig) r0
            java.lang.Boolean r1 = r0.a()
            java.lang.Boolean r0 = java.lang.Boolean.FALSE
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r0)
            r6 = 2131298895(0x7f090a4f, float:1.8215776E38)
            r8 = 2131303371(0x7f091bcb, float:1.8224854E38)
            r1 = 0
            java.lang.String r7 = "HomeBotBannerFragment"
            r5 = r41
            r4 = r42
            if (r0 != 0) goto Lca
            java.lang.String r0 = "initBotBannerV2"
            com.vega.log.BLog.i(r7, r0)
            com.vega.main.HomepageBannerConfigEntity r7 = com.vega.main.widget.BottomBannerHelper.i
            if (r7 == 0) goto Lb5
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto Lc8
            android.view.View r0 = r0.findViewById(r8)
            com.facebook.drawee.view.SimpleDraweeView r0 = (com.facebook.drawee.view.SimpleDraweeView) r0
        L6c:
            r2.f114028g = r0
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto L78
            android.view.View r1 = r0.findViewById(r6)
        L78:
            r2.h = r1
            com.facebook.drawee.view.SimpleDraweeView r6 = r2.f114028g
            if (r6 == 0) goto L99
            boolean r0 = androidx.core.view.ViewCompat.Api19Impl.c(r6)
            if (r0 == 0) goto Lbf
            boolean r0 = r6.isLayoutRequested()
            if (r0 != 0) goto Lbf
            com.vega.main.home.viewmodel.HomeBotBannerViewModel r3 = r2.a4()
            int r1 = r6.getWidth()
            int r0 = r6.getHeight()
            r3.q6(r7, r1, r0)
        L99:
            com.facebook.drawee.view.SimpleDraweeView r1 = r2.f114028g
            if (r1 == 0) goto La5
            X.4iN r0 = new X.4iN
            r0.<init>()
            r1.setOnClickListener(r0)
        La5:
            android.view.View r1 = r2.h
            if (r1 == 0) goto Lb5
            X.4iL r0 = new X.4iL
            r0.<init>()
            r1.setOnClickListener(r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            if (r0 != 0) goto Lbe
        Lb5:
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto Lbe
            com.vega.infrastructure.extensions.ViewExtKt.b(r0)
        Lbe:
            return r9
        Lbf:
            com.vega.main.home.banner.HomeBotBannerFragment$initBotBannerV2$lambda$15$lambda$12$$inlined$doOnLayout$1 r0 = new com.vega.main.home.banner.HomeBotBannerFragment$initBotBannerV2$lambda$15$lambda$12$$inlined$doOnLayout$1
            r0.<init>()
            r6.addOnLayoutChangeListener(r0)
            goto L99
        Lc8:
            r0 = r1
            goto L6c
        Lca:
            java.lang.String r0 = "initBotBanner"
            com.vega.log.BLog.i(r7, r0)
            com.vega.main.HomepageBannerConfigEntity r3 = com.vega.main.widget.BottomBannerHelper.i
            if (r3 == 0) goto L173
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto Ldf
            kotlin.jvm.internal.Intrinsics.checkNotNull(r0)
            com.vega.infrastructure.extensions.ViewExtKt.e(r0)
        Ldf:
            java.lang.String r0 = "initBotBanner view.show"
            com.vega.log.BLog.i(r7, r0)
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto L170
            android.view.View r0 = r0.findViewById(r8)
            com.facebook.drawee.view.SimpleDraweeView r0 = (com.facebook.drawee.view.SimpleDraweeView) r0
        Lf0:
            r2.f114028g = r0
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto Lfc
            android.view.View r1 = r0.findViewById(r6)
        Lfc:
            r2.h = r1
            com.facebook.drawee.view.SimpleDraweeView r12 = r2.f114028g
            if (r12 == 0) goto L14b
            com.vega.core.image.IImageLoader r10 = com.vega.core.image.ImageLoaderKt.a()
            java.lang.String r11 = r3.getPictureUrl()
            r13 = 0
            r16 = 0
            r20 = 0
            com.vega.main.home.banner.HomeBotBannerFragment$initBotBanner$1$1$1 r1 = new com.vega.main.home.banner.HomeBotBannerFragment$initBotBanner$1$1$1
            r1.<init>()
            com.vega.main.home.banner.HomeBotBannerFragment$initBotBanner$1$1$2 r0 = new com.vega.main.home.banner.HomeBotBannerFragment$initBotBanner$1$1$2
            r0.<init>()
            r39 = 265289724(0xfcffffc, float:2.0510378E-29)
            r14 = r13
            r15 = r13
            r17 = r13
            r18 = r16
            r19 = r13
            r21 = r13
            r22 = r20
            r23 = r13
            r24 = r13
            r25 = r13
            r26 = r13
            r27 = r16
            r28 = r16
            r29 = r13
            r30 = r16
            r31 = r1
            r32 = r0
            r33 = r16
            r34 = r16
            r35 = r16
            r36 = r16
            r37 = r16
            r38 = r16
            com.vega.core.image.IImageLoader.DefaultImpls.b(r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39)
        L14b:
            com.facebook.drawee.view.SimpleDraweeView r1 = r2.f114028g
            if (r1 == 0) goto L157
            X.4iM r0 = new X.4iM
            r0.<init>()
            r1.setOnClickListener(r0)
        L157:
            android.view.View r1 = r2.h
            if (r1 == 0) goto L163
            X.4iK r0 = new X.4iK
            r0.<init>()
            r1.setOnClickListener(r0)
        L163:
            com.vega.main.home.viewmodel.HomeBotBannerViewModel r0 = r2.a4()
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r0.f114496d
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r1.setValue(r0)
            goto Lbe
        L170:
            r0 = r1
            goto Lf0
        L173:
            android.view.View r0 = r2.getView()
            if (r0 == 0) goto Lbe
            com.vega.infrastructure.extensions.ViewExtKt.b(r0)
            goto Lbe
        L17e:
            r0 = 2131298212(0x7f0907a4, float:1.821439E38)
            android.view.View r1 = r1.findViewById(r0)
            if (r1 == 0) goto L1a5
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            boolean r0 = com.vega.infrastructure.extensions.ViewExtKt.a(r1)
            if (r0 == 0) goto L191
            return r5
        L191:
            com.vega.infrastructure.extensions.ViewExtKt.e(r1)
            com.vega.main.home.viewmodel.HomeBotBannerViewModel r0 = r2.a4()
            androidx.lifecycle.MutableLiveData<java.lang.Boolean> r1 = r0.f114496d
            java.lang.Boolean r0 = java.lang.Boolean.TRUE
            r1.setValue(r0)
            return r9
        L1a0:
            java.lang.String r0 = "local home banner config not exist"
            com.bytedance.services.apm.api.EnsureManager.ensureNotReachHere(r0)
        L1a5:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.main.home.banner.HomeBotBannerFragment.e4(com.vega.main.home.banner.HomeBotBannerFragment, androidx.fragment.app.FragmentActivity):boolean");
    }

    @Override // androidx.fragment.app.Fragment
    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002) {
            BottomBannerHelper bottomBannerHelper = BottomBannerHelper.f114784a;
            bottomBannerHelper.getClass();
            if (BottomBannerHelper.d()) {
                return;
            }
            a4().f.setValue(Boolean.FALSE);
            bottomBannerHelper.c();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, Bundle bundle) {
        Intrinsics.checkNotNullParameter(layoutInflater, "");
        try {
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "");
            return UltimateInflater.a(contextRequireContext, k, viewGroup, false);
        } catch (Exception unused) {
            return ((IMainService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IMainService.class), null)).r().b(requireContext(), "BottomBanner", new Function0<View>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onCreateView.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final View invoke() {
                    LayoutInflater layoutInflater2 = layoutInflater;
                    HomeBotBannerFragment.j.getClass();
                    View viewInflate = layoutInflater2.inflate(HomeBotBannerFragment.k, viewGroup, false);
                    Intrinsics.checkNotNullExpressionValue(viewInflate, "");
                    return viewInflate;
                }
            });
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onDestroyView() {
        ViewTreeObserver viewTreeObserver;
        super.onDestroyView();
        View view = getView();
        if (view != null && (viewTreeObserver = view.getViewTreeObserver()) != null) {
            viewTreeObserver.removeOnWindowFocusChangeListener(this.i);
        }
        SimpleDraweeView simpleDraweeView = this.f114028g;
        if (simpleDraweeView != null) {
            simpleDraweeView.setImageBitmap(null);
        }
        HomeBotBannerViewModel homeBotBannerViewModelA4 = a4();
        Job job = homeBotBannerViewModelA4.j;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        homeBotBannerViewModelA4.j = null;
        homeBotBannerViewModelA4.f114497g.setValue(HomeBotBannerViewModel.BannerBitmapState.Idle.f114499a);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        View view;
        View viewFindViewById;
        super.onResume();
        BottomBannerHelper bottomBannerHelper = BottomBannerHelper.f114784a;
        bottomBannerHelper.getClass();
        if (!BottomBannerHelper.d() || (view = getView()) == null || (viewFindViewById = view.findViewById(R.id.bottom_banner_layout)) == null) {
            return;
        }
        if (bottomBannerHelper.a()) {
            ViewExtKt.e(viewFindViewById);
        } else {
            ViewExtKt.b(viewFindViewById);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        Intrinsics.checkNotNullParameter(view, "");
        super.onViewCreated(view, bundle);
        a4().t6();
        HomeDraftListOptimize.f113994a.getClass();
        if (HomeDraftListOptimize.Companion.b()) {
            HomeOptimizeManager.f114584a.getClass();
            LiveDataExtKt.e(HomeOptimizeManager.f114585c).observe(getViewLifecycleOwner(), new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.1
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Boolean bool) {
                    MutableLiveData<Boolean> mutableLiveDataO6 = ((IHomeDraftViewModel) HomeBotBannerFragment.this.e.getValue()).o6();
                    LifecycleOwner viewLifecycleOwner = HomeBotBannerFragment.this.getViewLifecycleOwner();
                    final HomeBotBannerFragment homeBotBannerFragment = HomeBotBannerFragment.this;
                    mutableLiveDataO6.observe(viewLifecycleOwner, new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.1.1
                        {
                            super(1);
                        }

                        /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                        @Override // kotlin.jvm.functions.Function1
                        public final Unit invoke(Boolean bool2) {
                            homeBotBannerFragment.a4().f.setValue(Boolean.valueOf(!Intrinsics.areEqual(bool2, Boolean.TRUE)));
                            return Unit.INSTANCE;
                        }
                    }));
                    return Unit.INSTANCE;
                }
            }));
        } else {
            ((IHomeDraftViewModel) this.e.getValue()).o6().observe(getViewLifecycleOwner(), new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.2
                {
                    super(1);
                }

                /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function1
                public final Unit invoke(Boolean bool) {
                    HomeBotBannerFragment.this.a4().f.setValue(Boolean.valueOf(!Intrinsics.areEqual(bool, Boolean.TRUE)));
                    return Unit.INSTANCE;
                }
            }));
        }
        a4().f.observe(getViewLifecycleOwner(), new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.3
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) {
                String schema;
                if (Intrinsics.areEqual(bool, Boolean.TRUE)) {
                    final HomeBotBannerFragment homeBotBannerFragment = HomeBotBannerFragment.this;
                    FragmentActivity fragmentActivityRequireActivity = homeBotBannerFragment.requireActivity();
                    Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "");
                    BLog.i("HomeBotBannerFragment", "tryShowBotBanner");
                    if (BottomBannerHelper.f114784a.a()) {
                        HomepageBannerConfigEntity homepageBannerConfigEntity = BottomBannerHelper.i;
                        SPIService sPIService = SPIService.INSTANCE;
                        ((LoginService) sPIService.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).isLogin();
                        if (1 == 0) {
                            if (PerformanceManagerHelper.blogEnable) {
                                BLog.i("HomeBotBannerFragment", "Not logged in, no special handling");
                            }
                        } else if (homepageBannerConfigEntity != null && (schema = homepageBannerConfigEntity.getSchema()) != null) {
                            Uri uri = Uri.parse(schema);
                            String queryParameter = uri.getQueryParameter("ug_task_key");
                            if (!TextUtils.isEmpty(queryParameter) && Intrinsics.areEqual(uri.getQueryParameter("req_ug_server"), ProfileManager.VERSION)) {
                                IMainService iMainService = (IMainService) sPIService.getImpl(Reflection.getOrCreateKotlinClass(IMainService.class), null);
                                Intrinsics.checkNotNull(queryParameter);
                                iMainService.m(queryParameter, new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$isSpecialBannerAndNeedCheckActivityStatus$1$1
                                    {
                                        super(1);
                                    }

                                    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
                                    /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                                    @Override // kotlin.jvm.functions.Function1
                                    public final Unit invoke(Boolean bool2) {
                                        boolean zBooleanValue = bool2.booleanValue();
                                        if (homeBotBannerFragment.isAdded() && !homeBotBannerFragment.isDetached()) {
                                            if (zBooleanValue) {
                                                FragmentActivity activity = homeBotBannerFragment.getActivity();
                                                if (activity != null) {
                                                    HomeBotBannerFragment homeBotBannerFragment2 = homeBotBannerFragment;
                                                    homeBotBannerFragment2.e4(homeBotBannerFragment2, activity);
                                                }
                                            } else {
                                                homeBotBannerFragment.c4();
                                            }
                                        }
                                        return Unit.INSTANCE;
                                    }
                                });
                                if (PerformanceManagerHelper.blogEnable) {
                                    BLog.i("HomeBotBannerFragment", "isSpecialBootingBanner and need checkActivityStatus");
                                }
                            }
                        }
                        homeBotBannerFragment.e4(homeBotBannerFragment, fragmentActivityRequireActivity);
                    }
                } else {
                    HomeBotBannerFragment.this.c4();
                }
                return Unit.INSTANCE;
            }
        }));
        LiveData liveData = (LiveData) a4().f114495c.getValue();
        LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
        Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "");
        LiveDataExKt.i(liveData, viewLifecycleOwner, new Observer() { // from class: X.4iP
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f21363a.d4();
            }
        });
        a4().f114497g.observe(getViewLifecycleOwner(), new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<HomeBotBannerViewModel.BannerBitmapState, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.5
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(HomeBotBannerViewModel.BannerBitmapState bannerBitmapState) {
                HomeBotBannerViewModel.BannerBitmapState bannerBitmapState2 = bannerBitmapState;
                SimpleDraweeView simpleDraweeView = HomeBotBannerFragment.this.f114028g;
                if (simpleDraweeView != null) {
                    if (bannerBitmapState2 instanceof HomeBotBannerViewModel.BannerBitmapState.Success) {
                        simpleDraweeView.setImageBitmap(((HomeBotBannerViewModel.BannerBitmapState.Success) bannerBitmapState2).f114500a);
                        final HomeBotBannerFragment homeBotBannerFragment = HomeBotBannerFragment.this;
                        final Function0<Unit> function0 = new Function0<Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.5.1
                            {
                                super(0);
                            }

                            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                            @Override // kotlin.jvm.functions.Function0
                            public final Unit invoke() {
                                BLog.i("HomeBotBannerFragment", "bitmap load Success showed banner");
                                View view2 = homeBotBannerFragment.getView();
                                if (view2 != null) {
                                    ViewExtKt.e(view2);
                                }
                                View view3 = homeBotBannerFragment.h;
                                if (view3 != null) {
                                    ViewExtKt.e(view3);
                                }
                                homeBotBannerFragment.a4().f114496d.setValue(Boolean.TRUE);
                                return Unit.INSTANCE;
                            }
                        };
                        homeBotBannerFragment.getClass();
                        final ViewTreeObserver viewTreeObserver = simpleDraweeView.getViewTreeObserver();
                        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.vega.main.home.banner.HomeBotBannerFragment$runWhenBitmapPresented$listener$1
                            @Override // android.view.ViewTreeObserver.OnPreDrawListener
                            public final boolean onPreDraw() {
                                if (viewTreeObserver.isAlive()) {
                                    viewTreeObserver.removeOnPreDrawListener(this);
                                }
                                Choreographer choreographer = Choreographer.getInstance();
                                final Function0<Unit> function02 = function0;
                                choreographer.postFrameCallback(new Choreographer.FrameCallback() { // from class: X.4QP
                                    @Override // android.view.Choreographer.FrameCallback
                                    public final void doFrame(long j2) {
                                        function02.invoke();
                                    }
                                });
                                return true;
                            }
                        });
                    } else if (bannerBitmapState2 instanceof HomeBotBannerViewModel.BannerBitmapState.Fail) {
                        BLog.i("HomeBotBannerFragment", "bitmap load fail not show banner");
                        View view2 = HomeBotBannerFragment.this.h;
                        if (view2 != null) {
                            ViewExtKt.b(view2);
                        }
                        SimpleDraweeView simpleDraweeView2 = HomeBotBannerFragment.this.f114028g;
                        if (simpleDraweeView2 != null) {
                            ViewExtKt.b(simpleDraweeView2);
                        }
                        HomeBotBannerFragment.this.c4();
                    }
                }
                return Unit.INSTANCE;
            }
        }));
        this.f = view.hasWindowFocus();
        view.getViewTreeObserver().addOnWindowFocusChangeListener(this.i);
        ((HomeViewModel) this.f114026c.getValue()).f114527d.observe(getViewLifecycleOwner(), new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.6
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) {
                HomeBotBannerFragment.this.d4();
                return Unit.INSTANCE;
            }
        }));
        ((HomeViewModel) this.f114026c.getValue()).v.observe(getViewLifecycleOwner(), new HomeBotBannerFragment$sam$androidx_lifecycle_Observer$0(new Function1<Boolean, Unit>() { // from class: com.vega.main.home.banner.HomeBotBannerFragment.onViewCreated.7
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) {
                Boolean bool2 = bool;
                Intrinsics.checkNotNull(bool2);
                if (bool2.booleanValue()) {
                    HomeBotBannerFragment.this.c4();
                }
                return Unit.INSTANCE;
            }
        }));
    }
}
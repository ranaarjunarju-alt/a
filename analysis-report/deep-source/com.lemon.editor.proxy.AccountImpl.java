package com.lemon.editor.proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import com.bytedance.router.SmartRoute;
import com.bytedance.router.SmartRouter;
import com.bytedance.sdk.account.utils.Md5Utils;
import com.lemon.ConstKt;
import com.lemon.LoginUtilKt;
import com.lemon.account.AccountUpdateListener;
import com.lemon.account.IAccountOperation;
import com.lemon.account.IAccountService;
import com.lemon.account.VeryImportantConfig;
import com.lemon.account.multlogin.ISwitchAccountInterceptor;
import com.lemon.account.multlogin.ISwitchAccountService;
import com.lemon.entity.Access;
import com.lemon.entity.Permission;
import com.lemon.lv.editor.data.AccountUpdateProxyListener;
import com.lemon.lv.editor.data.ILogInterceptorProxy;
import com.lemon.lv.editor.proxy.IAccount;
import com.vega.cloud.LvCloudManager$init$3;
import com.vega.core.api.FlavorLoginService;
import com.vega.core.api.LoginService;
import com.vega.core.api.LogoutInterceptor;
import com.vega.core.context.ContextExtKt;
import com.vega.core.context.SPIService;
import com.vega.core.data.PlatFormEntity;
import com.vega.infrastructure.vm.ViewModelActivity;
import com.vega.log.BLog;
import com.vega.ui.IFragmentManagerProvider;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.json.JSONObject;

/* loaded from: classes34.dex */
public final class AccountImpl implements IAccount {

    /* renamed from: c, reason: collision with root package name */
    public static final IAccountService f58621c;

    /* renamed from: a, reason: collision with root package name */
    public final Map<AccountUpdateProxyListener, AccountUpdateListener> f58622a = new LinkedHashMap();
    public final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<IAccountOperation>() { // from class: com.lemon.editor.proxy.AccountImpl$accountOperation$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        /* JADX WARN: Type inference failed for: r0v2, types: [com.lemon.account.IAccountOperation, java.lang.Object] */
        @Override // kotlin.jvm.functions.Function0
        public final IAccountOperation invoke() {
            return SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAccountOperation.class), null);
        }
    });

    /* loaded from: classes7.dex */
    public static final class Companion {
    }

    static {
        new Companion();
        f58621c = (IAccountService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAccountService.class), null);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void A(final AccountUpdateProxyListener accountUpdateProxyListener) {
        AccountUpdateListener accountUpdateListener = new AccountUpdateListener() { // from class: com.lemon.editor.proxy.AccountImpl$addAccountListener$updateListener$1
            @Override // com.lemon.account.AccountUpdateListener
            public final void c() {
                AccountUpdateProxyListener accountUpdateProxyListener2 = accountUpdateProxyListener;
                if (accountUpdateProxyListener2 != null) {
                    accountUpdateProxyListener2.c();
                }
            }

            @Override // com.lemon.account.AccountUpdateListener
            public final void h() {
                AccountUpdateProxyListener accountUpdateProxyListener2 = accountUpdateProxyListener;
                if (accountUpdateProxyListener2 != null) {
                    accountUpdateProxyListener2.h();
                }
            }

            @Override // com.lemon.account.AccountUpdateListener
            public final void j(boolean z) {
                AccountUpdateProxyListener accountUpdateProxyListener2 = accountUpdateProxyListener;
                if (accountUpdateProxyListener2 != null) {
                    accountUpdateProxyListener2.j(z);
                }
            }

            @Override // com.lemon.account.AccountUpdateListener
            public final void k() {
            }

            @Override // com.lemon.account.AccountUpdateListener
            public final void l(String str, boolean z) {
                Intrinsics.checkNotNullParameter(str, "");
            }

            @Override // com.lemon.account.AccountUpdateListener
            public final void m() {
            }
        };
        this.f58622a.put(accountUpdateProxyListener, accountUpdateListener);
        f58621c.Z(accountUpdateListener);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0041  */
    @Override // com.lemon.lv.editor.proxy.IAccount
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object B(android.app.Activity r10, java.lang.String r11, final com.vega.audio.library.TTMusicViewModel$requestAuth$1.AnonymousClass1 r12, kotlin.coroutines.Continuation r13) {
        /*
            r9 = this;
            boolean r0 = r13 instanceof com.lemon.editor.proxy.AccountImpl$requestAuth$1
            if (r0 == 0) goto L41
            r8 = r13
            com.lemon.editor.proxy.AccountImpl$requestAuth$1 r8 = (com.lemon.editor.proxy.AccountImpl$requestAuth$1) r8
            int r2 = r8.s
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L41
            int r2 = r2 - r1
            r8.s = r2
        L12:
            java.lang.Object r3 = r8.q
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r8.s
            r1 = 1
            if (r0 == 0) goto L25
            if (r0 != r1) goto L47
            kotlin.ResultKt.throwOnFailure(r3)
        L22:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L25:
            kotlin.ResultKt.throwOnFailure(r3)
            kotlin.Lazy r0 = r9.b
            java.lang.Object r3 = r0.getValue()
            com.lemon.account.IAccountOperation r3 = (com.lemon.account.IAccountOperation) r3
            com.lemon.editor.proxy.AccountImpl$requestAuth$2 r7 = new com.lemon.editor.proxy.AccountImpl$requestAuth$2
            r7.<init>()
            r8.s = r1
            r6 = 0
            r4 = r10
            r5 = r11
            java.lang.Object r0 = r3.y(r4, r5, r6, r7, r8)
            if (r0 != r2) goto L22
            return r2
        L41:
            com.lemon.editor.proxy.AccountImpl$requestAuth$1 r8 = new com.lemon.editor.proxy.AccountImpl$requestAuth$1
            r8.<init>(r9, r13)
            goto L12
        L47:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lemon.editor.proxy.AccountImpl.B(android.app.Activity, java.lang.String, com.vega.audio.library.TTMusicViewModel$requestAuth$1$1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void C(Context context, String str, boolean z, boolean z2) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(str, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//login");
        smartRouteBuildRoute.withParam("key_enter_from", str);
        smartRouteBuildRoute.withParam("key_uc_enter_from", str);
        smartRouteBuildRoute.withParam("key_login_directly", z2);
        smartRouteBuildRoute.withParam("key_success_back_home", z);
        smartRouteBuildRoute.addFlags(268435456);
        smartRouteBuildRoute.open(1003);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r9v0, resolved type: androidx.fragment.app.Fragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.lemon.lv.editor.proxy.IAccount
    public final Fragment D(Fragment fragment, String str, String str2, String str3, String str4, String str5, boolean z) {
        Intrinsics.checkNotNullParameter(fragment, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        if (fragment instanceof IFragmentManagerProvider) {
            return f58621c.h0((IFragmentManagerProvider) fragment, str, str2, str3, str4, str5, z);
        }
        BLog.e("IAccountImpl", "createLoginIconFragment, fragment doesn't implement IFragmentManagerProvider");
        return null;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void E() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void F(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//login");
        smartRouteBuildRoute.withParam("key_success_back_home", false);
        smartRouteBuildRoute.withParam("key_enter_from", "collection_tab");
        smartRouteBuildRoute.withParam("key_material_type", "text_template");
        smartRouteBuildRoute.withParam("key_login_directly", z);
        smartRouteBuildRoute.open();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean G() {
        Access accessC0 = f58621c.c0();
        Objects.toString(accessC0);
        return accessC0.D;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void H(final LvCloudManager$init$3 lvCloudManager$init$3) {
        Intrinsics.checkNotNullParameter(lvCloudManager$init$3, "");
        ((ISwitchAccountService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(ISwitchAccountService.class), null)).b(new ISwitchAccountInterceptor() { // from class: com.lemon.editor.proxy.AccountImpl$addSwitchAccountInterceptor$1
            @Override // com.lemon.account.multlogin.ISwitchAccountInterceptor
            public final boolean interceptor() {
                return lvCloudManager$init$3.a();
            }
        });
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final String I(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        String strHexDigest = Md5Utils.hexDigest(str);
        Intrinsics.checkNotNull(strHexDigest);
        return strHexDigest;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void J() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void K(Context context, Map<String, String> map, boolean z) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(map, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//login");
        smartRouteBuildRoute.withParam("key_success_back_home", false);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            smartRouteBuildRoute.withParam(entry.getKey(), entry.getValue());
        }
        smartRouteBuildRoute.withParam("key_login_is_half_screen", z);
        smartRouteBuildRoute.open(1003);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void L(Context context, boolean z) {
        Intrinsics.checkNotNullParameter(context, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//login");
        smartRouteBuildRoute.withParam("key_enter_from", "custom_voice");
        smartRouteBuildRoute.withParam("key_material_type", "timbre");
        smartRouteBuildRoute.withParam("key_success_back_home", false);
        smartRouteBuildRoute.withParam("key_login_directly", z);
        smartRouteBuildRoute.open();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void M() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void N() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean O() {
        return f58621c.l();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean P() {
        Permission permission = f58621c.permission();
        Objects.toString(permission);
        return permission.f58715a;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean Q() {
        return f58621c.r0();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean R() {
        Access accessC0 = f58621c.c0();
        Objects.toString(accessC0);
        return accessC0.B;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void S(Activity activity, String str, final Function1<? super Boolean, Unit> function1) {
        Intrinsics.checkNotNullParameter(activity, "");
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(function1, "");
        Bundle bundle = new Bundle();
        bundle.putString("key_enter_from", str);
        if (activity instanceof FragmentActivity) {
            bundle.putBoolean("key_success_back_home", false);
        }
        LoginUtilKt.doLogin(activity, bundle, true, new Function1<Boolean, Unit>() { // from class: com.lemon.editor.proxy.AccountImpl$login$2
            /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
            /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
            @Override // kotlin.jvm.functions.Function1
            public final Unit invoke(Boolean bool) {
                bool.booleanValue();
                function1.invoke(Boolean.valueOf(((LoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).isLogin()));
                return Unit.INSTANCE;
            }
        });
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final String T() {
        f58621c.j0();
        return "ws_channel_type_ai_edit_quick_clip_cot";
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean U(boolean z) {
        return f58621c.E0().f58664a.f58668a && (!z || Intrinsics.areEqual(ContextExtKt.app().F(), "US"));
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void V() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean W() {
        Access accessC0 = f58621c.c0();
        Objects.toString(accessC0);
        return accessC0.f || accessC0.e;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void X() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void Y() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean a() {
        VeryImportantConfig veryImportantConfig = VeryImportantConfig.f54665a;
        veryImportantConfig.getClass();
        return ((Boolean) VeryImportantConfig.f.getValue(veryImportantConfig, VeryImportantConfig.b[3])).booleanValue();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void b() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean c() {
        Access accessC0 = f58621c.c0();
        Objects.toString(accessC0);
        return accessC0.A;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void d(int i, int i2, Intent intent, Function0<Unit> function0, Function0<Unit> function02, Function1<? super String, Unit> function1, Function1<? super String, Unit> function12) {
        ((IAccountOperation) this.b.getValue()).d(i, i2, intent, function0, function02, function1, function12);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean e() {
        return f58621c.c0().o;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean f() {
        Access accessC0 = f58621c.c0();
        Objects.toString(accessC0);
        return accessC0.x;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean g() {
        VeryImportantConfig veryImportantConfig = VeryImportantConfig.f54665a;
        veryImportantConfig.getClass();
        return ((Boolean) VeryImportantConfig.f54668g.getValue(veryImportantConfig, VeryImportantConfig.b[4])).booleanValue();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final long getUserId() {
        return ((LoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).getUserId();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final JSONObject getUserInfo() {
        return ((IAccountService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IAccountService.class), null)).getUserInfo();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final String getUserName() {
        return ((LoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).getUserName();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean h() {
        return f58621c.h();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final String i() {
        f58621c.i();
        return "ws_channel_type_async_task";
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean isLogin() {
        return ((LoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).isLogin();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final PlatFormEntity j() {
        PlatFormEntity platFormEntityS = ((FlavorLoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(FlavorLoginService.class), null)).s("tiktok");
        Objects.toString(platFormEntityS);
        return platFormEntityS;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean k(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        return f58621c.k(str, str2);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void l(ViewModelActivity viewModelActivity, boolean z) {
        Intrinsics.checkNotNullParameter(viewModelActivity, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(viewModelActivity, "//login");
        smartRouteBuildRoute.withParam("key_success_back_home", false);
        smartRouteBuildRoute.withParam("key_enter_from", "collection_tab");
        smartRouteBuildRoute.withParam("key_material_type", "text_special_effect");
        smartRouteBuildRoute.withParam("key_login_directly", z);
        smartRouteBuildRoute.open();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void m(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        f58621c.m(str);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void n(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        f58621c.n(str);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final String o() {
        f58621c.o();
        return "ws_channel_type_tts_streaming";
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void p(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//login");
        smartRouteBuildRoute.withParam("key_enter_from", "click_material_favorite");
        smartRouteBuildRoute.withParam("key_material_type", "sound_effect");
        smartRouteBuildRoute.withParam("key_success_back_home", false);
        smartRouteBuildRoute.open(1003);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void q(Context context, String str, boolean z) {
        Intrinsics.checkNotNullParameter(context, "");
        Intrinsics.checkNotNullParameter(str, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//login");
        smartRouteBuildRoute.withParam("key_enter_from", "library_asset");
        smartRouteBuildRoute.withParam("key_uc_enter_from", "library_asset");
        smartRouteBuildRoute.withParam("key_uc_enter_method", str);
        smartRouteBuildRoute.withParam("key_login_directly", z);
        smartRouteBuildRoute.withParam("key_success_back_home", false);
        smartRouteBuildRoute.addFlags(268435456);
        smartRouteBuildRoute.open(1003);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final MutableLiveData<Boolean> r() {
        return ((LoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(LoginService.class), null)).b();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final CharSequence s() {
        return ConstKt.b();
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void t() {
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final Pair<Boolean, String> u(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        return f58621c.u(str, str2);
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean v() {
        return f58621c.c0().q;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void w(final ILogInterceptorProxy iLogInterceptorProxy) {
        Intrinsics.checkNotNullParameter(iLogInterceptorProxy, "");
        ((LoginService) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(FlavorLoginService.class), null)).P0(new LogoutInterceptor() { // from class: com.lemon.editor.proxy.AccountImpl$addLogInterceptor$1
            @Override // com.vega.core.api.LogoutInterceptor
            public final boolean interceptor() {
                return iLogInterceptorProxy.a();
            }
        });
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void x(AccountUpdateProxyListener accountUpdateProxyListener) {
        if (accountUpdateProxyListener != null) {
            AccountUpdateListener accountUpdateListener = (AccountUpdateListener) ((LinkedHashMap) this.f58622a).get(accountUpdateProxyListener);
            accountUpdateProxyListener.toString();
            f58621c.d0(accountUpdateListener);
            this.f58622a.remove(accountUpdateProxyListener);
        }
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final boolean y() {
        Access accessC0 = f58621c.c0();
        Objects.toString(accessC0);
        return accessC0.f58646c;
    }

    @Override // com.lemon.lv.editor.proxy.IAccount
    public final void z(Context context) {
        Intrinsics.checkNotNullParameter(context, "");
        SmartRoute smartRouteBuildRoute = SmartRouter.buildRoute(context, "//tool/login");
        smartRouteBuildRoute.withParam("key_enter_from", "drafts_cloud");
        smartRouteBuildRoute.withParam("key_uc_enter_from", "edit_page");
        smartRouteBuildRoute.withParam("key_uc_enter_method", "click_cloud");
        smartRouteBuildRoute.withParam("key_success_back_home", true);
        smartRouteBuildRoute.open(1003);
    }
}
package com.lemon.lv.editor.proxy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import com.lemon.lv.editor.data.AccountUpdateProxyListener;
import com.lemon.lv.editor.data.ILogInterceptorProxy;
import com.vega.audio.library.TTMusicViewModel$requestAuth$1;
import com.vega.cloud.LvCloudManager$init$3;
import com.vega.core.data.PlatFormEntity;
import com.vega.infrastructure.vm.ViewModelActivity;
import java.util.Map;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import org.json.JSONObject;

/* loaded from: classes39.dex */
public interface IAccount {

    /* loaded from: classes10.dex */
    public static final class DefaultImpls {
    }

    void A(AccountUpdateProxyListener accountUpdateProxyListener);

    Object B(Activity activity, String str, TTMusicViewModel$requestAuth$1.AnonymousClass1 anonymousClass1, Continuation continuation);

    void C(Context context, String str, boolean z, boolean z2);

    Fragment D(Fragment fragment, String str, String str2, String str3, String str4, String str5, boolean z);

    void E();

    void F(Context context, boolean z);

    boolean G();

    void H(LvCloudManager$init$3 lvCloudManager$init$3);

    String I(String str);

    void J();

    void K(Context context, Map<String, String> map, boolean z);

    void L(Context context, boolean z);

    void M();

    void N();

    boolean O();

    boolean P();

    boolean Q();

    boolean R();

    void S(Activity activity, String str, Function1<? super Boolean, Unit> function1);

    String T();

    boolean U(boolean z);

    void V();

    boolean W();

    void X();

    void Y();

    boolean a();

    void b();

    boolean c();

    void d(int i, int i2, Intent intent, Function0<Unit> function0, Function0<Unit> function02, Function1<? super String, Unit> function1, Function1<? super String, Unit> function12);

    boolean e();

    boolean f();

    boolean g();

    long getUserId();

    JSONObject getUserInfo();

    String getUserName();

    boolean h();

    String i();

    boolean isLogin();

    PlatFormEntity j();

    boolean k(String str, String str2);

    void l(ViewModelActivity viewModelActivity, boolean z);

    void m(String str);

    void n(String str);

    String o();

    void p(Context context);

    void q(Context context, String str, boolean z);

    MutableLiveData<Boolean> r();

    CharSequence s();

    void t();

    Pair<Boolean, String> u(String str, String str2);

    boolean v();

    void w(ILogInterceptorProxy iLogInterceptorProxy);

    void x(AccountUpdateProxyListener accountUpdateProxyListener);

    boolean y();

    void z(Context context);
}
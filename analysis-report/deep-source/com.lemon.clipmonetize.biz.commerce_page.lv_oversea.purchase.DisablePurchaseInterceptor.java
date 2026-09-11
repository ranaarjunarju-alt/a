package com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase;

import X.C117993wi;
import X.C4HW;
import X.C4HX;
import com.lemon.clipmonetize.commerce_page_lv_oversea.generated.resources.Res;
import com.lemon.clipmonetize.commerce_page_lv_oversea.generated.resources.String0_commonMainKt;
import com.lemon.clipmonetize.infra.ui.StringKt;
import com.lemon.lv.clipmonetize.platform.IStringProviderKt;
import com.lemon.lv.clipmonetize.platform.KMPDialog;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* loaded from: classes23.dex */
public final class DisablePurchaseInterceptor {

    /* renamed from: a, reason: collision with root package name */
    public static final DisablePurchaseInterceptor f55634a;
    public static final C117993wi b;

    /* renamed from: c, reason: collision with root package name */
    public static final Function1<? super String, String> f55635c;

    /* renamed from: d, reason: collision with root package name */
    public static final C4HW f55636d;
    public static final C4HX e;
    public static final Function1<? super String, Unit> f;

    /* JADX WARN: Type inference failed for: r0v0, types: [X.3wi] */
    static {
        DisablePurchaseInterceptor disablePurchaseInterceptor = new DisablePurchaseInterceptor();
        f55634a = disablePurchaseInterceptor;
        b = new Function0() { // from class: X.3wi
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return KMPDialog.b;
            }
        };
        f55635c = new DisablePurchaseInterceptor$stringProvider$1(disablePurchaseInterceptor);
        f55636d = new C4HW();
        e = new C4HX();
        f = DisablePurchaseInterceptor$toastProvider$1.b;
    }

    public static String a(String str) {
        int iHashCode = str.hashCode();
        if (iHashCode != -1121643530) {
            if (iHashCode != -410688813) {
                if (iHashCode == 153663858 && str.equals("cc_standard_low_base_price_subscriber_notice_title")) {
                    return StringKt.a(String0_commonMainKt.r(Res.string.f57857a));
                }
            } else if (str.equals("cc_standard_low_base_price_subscriber_notice_text")) {
                return StringKt.a(String0_commonMainKt.q(Res.string.f57857a));
            }
        } else if (str.equals("cc_standard_low_base_price_subscriber_notice_btn")) {
            return StringKt.a(String0_commonMainKt.p(Res.string.f57857a));
        }
        return IStringProviderKt.b(str);
    }
}
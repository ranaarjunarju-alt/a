package com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase;

import com.lemon.clipmonetize.biz.product_service.ProductInfo;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes38.dex */
public final class DisablePurchaseInterceptorKt {
    public static void a(ProductInfo productInfo, boolean z, Function0 function0, int i) {
        if ((i & 2) != 0) {
            z = true;
        }
        DisablePurchaseInterceptorKt$proceedIfPurchaseAllowed$1 disablePurchaseInterceptorKt$proceedIfPurchaseAllowed$1 = (i & 4) != 0 ? new DisablePurchaseInterceptorKt$proceedIfPurchaseAllowed$1(DisablePurchaseInterceptor.f55634a) : null;
        Intrinsics.checkNotNullParameter(disablePurchaseInterceptorKt$proceedIfPurchaseAllowed$1, "");
        Intrinsics.checkNotNullParameter(function0, "");
        if (z && ((Boolean) disablePurchaseInterceptorKt$proceedIfPurchaseAllowed$1.invoke(productInfo)).booleanValue()) {
            return;
        }
        function0.invoke();
    }
}
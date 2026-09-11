package com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase;

import com.lemon.clipmonetize.biz.commerce_page.lv_oversea.settings.PaymentChoicePanelUx;
import com.lemon.lv.clipmonetize.platform.AppContext;
import com.lemon.lv.clipmonetize.platform.KMPAppContext;
import com.lemon.lv.clipmonetize.platform.KMPLog;
import com.lemon.lv.clipmonetize.platform.KMPLogin;
import com.lemon.lv.clipmonetize.platform.KMPReporter;
import com.pipo.capcut.paymentchoice.PaymentChoiceError;
import com.pipo.capcut.paymentchoice.PaymentChoiceEventTracker;
import com.pipo.capcut.paymentchoice.PaymentChoiceHttpClient;
import com.pipo.capcut.paymentchoice.PaymentChoiceSdk;
import com.pipo.capcut.paymentchoice.PaymentChoiceSdkConfig;
import com.pipo.capcut.paymentchoice.internal.PaymentChoiceSdkHolder;
import java.util.Map;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;

/* loaded from: classes36.dex */
public final class PaymentChoiceSdkBridge {

    /* renamed from: a, reason: collision with root package name */
    public static final PaymentChoiceSdkBridge f55667a = new PaymentChoiceSdkBridge();
    public static boolean b;

    /* loaded from: classes21.dex */
    public static final class HostPaymentChoiceEventTracker implements PaymentChoiceEventTracker {

        /* renamed from: a, reason: collision with root package name */
        public static final HostPaymentChoiceEventTracker f55668a = new HostPaymentChoiceEventTracker();

        @Override // com.pipo.capcut.paymentchoice.PaymentChoiceEventTracker
        public final void a(String str, Map<String, String> map) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(map, "");
            KMPReporter.b.a(str, map);
        }
    }

    /* loaded from: classes27.dex */
    public static final class HostPaymentChoiceHttpClient implements PaymentChoiceHttpClient {

        /* renamed from: a, reason: collision with root package name */
        public static final HostPaymentChoiceHttpClient f55669a = new HostPaymentChoiceHttpClient();

        public static PaymentChoiceRequestUrl b(String str) {
            int iIndexOf$default = StringsKt__StringsKt.indexOf$default((CharSequence) str, "://", 0, false, 6, (Object) null);
            if (iIndexOf$default < 0) {
                return new PaymentChoiceRequestUrl(null, str);
            }
            int i = iIndexOf$default + 3;
            int iIndexOf$default2 = StringsKt__StringsKt.indexOf$default((CharSequence) str, '/', i, false, 4, (Object) null);
            int iIndexOf$default3 = StringsKt__StringsKt.indexOf$default((CharSequence) str, '?', i, false, 4, (Object) null);
            if (iIndexOf$default2 >= 0) {
                if (iIndexOf$default3 < 0 || iIndexOf$default2 < iIndexOf$default3) {
                    String strSubstring = str.substring(0, iIndexOf$default2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring, "");
                    String strSubstring2 = str.substring(iIndexOf$default2);
                    Intrinsics.checkNotNullExpressionValue(strSubstring2, "");
                    return new PaymentChoiceRequestUrl(strSubstring, StringsKt__StringsKt.isBlank(strSubstring2) ? "/" : strSubstring2);
                }
            } else if (iIndexOf$default3 < 0) {
                return new PaymentChoiceRequestUrl(str, "/");
            }
            String strSubstring3 = str.substring(0, iIndexOf$default3);
            Intrinsics.checkNotNullExpressionValue(strSubstring3, "");
            StringBuilder sb = new StringBuilder("/");
            String strSubstring4 = str.substring(iIndexOf$default3);
            Intrinsics.checkNotNullExpressionValue(strSubstring4, "");
            sb.append(strSubstring4);
            return new PaymentChoiceRequestUrl(strSubstring3, sb.toString());
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0051  */
        /* JADX WARN: Removed duplicated region for block: B:32:0x00a7  */
        @Override // com.pipo.capcut.paymentchoice.PaymentChoiceHttpClient
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object a(java.lang.String r13, java.lang.String r14, java.util.Map r15, java.util.Map r16, kotlin.coroutines.Continuation r17) {
            /*
                r12 = this;
                r3 = r17
                boolean r0 = r3 instanceof com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$HostPaymentChoiceHttpClient$post$1
                if (r0 == 0) goto La7
                r10 = r3
                com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$HostPaymentChoiceHttpClient$post$1 r10 = (com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$HostPaymentChoiceHttpClient$post$1) r10
                int r2 = r10.s
                r1 = -2147483648(0xffffffff80000000, float:-0.0)
                r0 = r2 & r1
                if (r0 == 0) goto La7
                int r2 = r2 - r1
                r10.s = r2
            L14:
                java.lang.Object r4 = r10.q
                java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r0 = r10.s
                r1 = 1
                if (r0 == 0) goto L34
                if (r0 != r1) goto Lae
                kotlin.ResultKt.throwOnFailure(r4)
            L24:
                com.lemon.lv.clipmonetize.platform.HttpResponse r4 = (com.lemon.lv.clipmonetize.platform.HttpResponse) r4
                com.pipo.capcut.paymentchoice.PaymentChoiceHttpResponse r3 = new com.pipo.capcut.paymentchoice.PaymentChoiceHttpResponse
                int r2 = r4.f58838d
                java.lang.String r1 = r4.f58837c
                java.util.Map r0 = kotlin.collections.MapsKt__MapsKt.emptyMap()
                r3.<init>(r2, r1, r0)
                return r3
            L34:
                kotlin.ResultKt.throwOnFailure(r4)
                boolean r0 = r15.isEmpty()
                if (r0 == 0) goto L66
            L3d:
                com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$PaymentChoiceRequestUrl r4 = b(r13)
                java.lang.String r3 = r4.f55670a
                if (r3 == 0) goto L51
                com.lemon.lv.clipmonetize.platform.KMPHttpClient r0 = com.lemon.lv.clipmonetize.platform.KMPHttpClient.f58843a
                r0.getClass()
                r0 = 0
                com.lemon.lv.clipmonetize.platform.IHttpClient r6 = com.lemon.lv.clipmonetize.platform.KMPHttpClient.a(r3, r0)
                if (r6 != 0) goto L57
            L51:
                com.lemon.lv.clipmonetize.platform.KMPHttpClient r0 = com.lemon.lv.clipmonetize.platform.KMPHttpClient.f58843a
                com.lemon.lv.clipmonetize.platform.IHttpClient r6 = com.lemon.lv.clipmonetize.platform.KMPHttpClient.b(r0)
            L57:
                java.lang.String r7 = r4.b
                r11 = 2
                r10.s = r1
                r8 = r14
                r9 = r16
                java.lang.Object r4 = com.lemon.lv.clipmonetize.platform.IHttpClient.DefaultImpls.a(r6, r7, r8, r9, r10, r11)
                if (r4 != r2) goto L24
                return r2
            L66:
                java.util.Set r3 = r15.entrySet()
                java.lang.String r4 = "&"
                r5 = 0
                r7 = 0
                X.0nn r8 = new X.0nn
                r8.<init>()
                r9 = 30
                r6 = r5
                java.lang.String r5 = kotlin.collections.CollectionsKt.j(r3, r4, r5, r6, r7, r8, r9)
                java.lang.String r4 = "?"
                boolean r0 = X.C93472yG.s(r13, r4)
                if (r0 != 0) goto L8a
                java.lang.String r3 = "&"
                boolean r0 = X.C93472yG.s(r13, r3)
                if (r0 == 0) goto L9f
            L8a:
                java.lang.String r4 = ""
            L8c:
                java.lang.StringBuilder r0 = new java.lang.StringBuilder
                r0.<init>()
                r0.append(r13)
                r0.append(r4)
                r0.append(r5)
                java.lang.String r13 = r0.toString()
                goto L3d
            L9f:
                boolean r0 = X.C93472yG.o(r13, r4)
                if (r0 == 0) goto L8c
                r4 = r3
                goto L8c
            La7:
                com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$HostPaymentChoiceHttpClient$post$1 r10 = new com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$HostPaymentChoiceHttpClient$post$1
                r10.<init>(r12, r3)
                goto L14
            Lae:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r0)
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge.HostPaymentChoiceHttpClient.a(java.lang.String, java.lang.String, java.util.Map, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    /* loaded from: classes.dex */
    public static final class PaymentChoiceRequestUrl {

        /* renamed from: a, reason: collision with root package name */
        public final String f55670a;
        public final String b;

        public PaymentChoiceRequestUrl(String str, String str2) {
            Intrinsics.checkNotNullParameter(str2, "");
            this.f55670a = str;
            this.b = str2;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PaymentChoiceRequestUrl)) {
                return false;
            }
            PaymentChoiceRequestUrl paymentChoiceRequestUrl = (PaymentChoiceRequestUrl) obj;
            return Intrinsics.areEqual(this.f55670a, paymentChoiceRequestUrl.f55670a) && Intrinsics.areEqual(this.b, paymentChoiceRequestUrl.b);
        }

        public final int hashCode() {
            String str = this.f55670a;
            return ((str == null ? 0 : str.hashCode()) * 31) + this.b.hashCode();
        }

        public final String toString() {
            return "PaymentChoiceRequestUrl(host=" + this.f55670a + ", pathWithQuery=" + this.b + ')';
        }
    }

    /* loaded from: classes13.dex */
    public /* synthetic */ class WhenMappings {
        static {
            PaymentChoicePanelUx.values();
        }
    }

    public static boolean b() {
        if (b) {
            return true;
        }
        PaymentChoiceSdk paymentChoiceSdk = PaymentChoiceSdk.f64549a;
        KMPAppContext.f58839a.getClass();
        AppContext appContextA = KMPAppContext.a();
        String str = StringsKt__StringsJVMKt.equals(appContextA.j, "US", true) ? "TTP" : "SG";
        HostPaymentChoiceHttpClient hostPaymentChoiceHttpClient = HostPaymentChoiceHttpClient.f55669a;
        HostPaymentChoiceEventTracker hostPaymentChoiceEventTracker = HostPaymentChoiceEventTracker.f55668a;
        String str2 = appContextA.i;
        if (StringsKt__StringsKt.isBlank(str2)) {
            str2 = "en";
        }
        PaymentChoiceSdkConfig paymentChoiceSdkConfig = new PaymentChoiceSdkConfig(str, hostPaymentChoiceHttpClient, hostPaymentChoiceEventTracker, str2, MapsKt__MapsKt.mapOf(TuplesKt.to("merchant_id", "capcut"), TuplesKt.to("merchant_user_id", String.valueOf(KMPLogin.b.getUserId())), TuplesKt.to("country_code", appContextA.j)));
        paymentChoiceSdk.getClass();
        PaymentChoiceSdkHolder.f64571a.getClass();
        PaymentChoiceError paymentChoiceErrorA = PaymentChoiceSdkHolder.a(paymentChoiceSdkConfig);
        if (paymentChoiceErrorA == null) {
            b = true;
            KMPLog.b.c("PaymentChoiceSdkBridge", "Payment choice sdk initialized");
            return true;
        }
        KMPLog.b.d("PaymentChoiceSdkBridge", "Payment choice sdk initialize failed: " + paymentChoiceErrorA.f64544a + ", " + paymentChoiceErrorA.b);
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00d2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00d3  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00db  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object a(com.lemon.clipmonetize.biz.commerce_page.lv_oversea.settings.PaymentChoicePanelUx r19, com.lemon.clipmonetize.biz.product_service.ProductInfo r20, com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.ExternalDiscountInfo r21, kotlin.coroutines.Continuation<? super com.pipo.capcut.paymentchoice.PopupContentParams> r22) {
        /*
            r18 = this;
            r3 = r22
            r7 = r20
            r6 = r21
            boolean r0 = r3 instanceof com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$createPopupContentParams$1
            r8 = r18
            if (r0 == 0) goto Lde
            r5 = r3
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$createPopupContentParams$1 r5 = (com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$createPopupContentParams$1) r5
            int r2 = r5.A
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto Lde
            int r2 = r2 - r1
            r5.A = r2
        L1a:
            java.lang.Object r15 = r5.y
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r5.A
            java.lang.String r3 = "vip"
            r10 = 0
            r2 = 1
            if (r0 == 0) goto L90
            if (r0 != r2) goto Le5
            int r1 = r5.x
            java.lang.Object r14 = r5.w
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r13 = r5.v
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r12 = r5.u
            java.lang.String r12 = (java.lang.String) r12
            java.lang.Object r11 = r5.t
            java.lang.String r11 = (java.lang.String) r11
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.ExternalDiscountInfo r6 = r5.s
            com.lemon.clipmonetize.biz.product_service.ProductInfo r7 = r5.r
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge r4 = r5.q
            kotlin.ResultKt.throwOnFailure(r15)
        L45:
            java.util.List r15 = (java.util.List) r15
            int r0 = r6.f55639d
            java.lang.Integer r0 = kotlin.coroutines.jvm.internal.Boxing.boxInt(r0)
            r0.intValue()
            if (r1 == 0) goto L8b
        L52:
            int r0 = r0.intValue()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r0 = "% off"
            r1.append(r0)
            java.lang.String r16 = r1.toString()
        L67:
            r4.getClass()
            java.lang.String r0 = r7.a0
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.equals(r0, r3, r2)
            if (r0 == 0) goto L84
            java.lang.String r1 = "#A3E7FF"
            java.lang.String r0 = "#C08AFF"
            java.lang.String[] r0 = new java.lang.String[]{r1, r0}
            java.util.List r17 = kotlin.collections.CollectionsKt__CollectionsKt.listOf(r0)
        L7e:
            com.pipo.capcut.paymentchoice.PopupContentParams r10 = new com.pipo.capcut.paymentchoice.PopupContentParams
            r10.<init>(r11, r12, r13, r14, r15, r16, r17)
            return r10
        L84:
            java.lang.String r0 = "#00CAE0"
            java.util.List r17 = kotlin.collections.CollectionsKt__CollectionsJVMKt.listOf(r0)
            goto L7e
        L8b:
            r16 = 0
            if (r10 == 0) goto L67
            goto L52
        L90:
            kotlin.ResultKt.throwOnFailure(r15)
            int r1 = r6.f55639d
            if (r2 > r1) goto Ld9
            r0 = 100
            if (r1 >= r0) goto Ld9
            r1 = 1
        L9c:
            int r9 = r19.ordinal()
            if (r9 == 0) goto Laa
            if (r9 == r2) goto Ld6
            r0 = 2
            if (r9 == r0) goto Laa
            r0 = 3
            if (r9 != r0) goto Led
        Laa:
            java.lang.String r11 = "discount_sheet"
        Lac:
            java.lang.String r12 = r6.f
            if (r1 == 0) goto Ld3
            java.lang.String r13 = r6.e
        Lb2:
            java.lang.String r14 = r7.j
            java.lang.String r0 = r7.a0
            boolean r0 = kotlin.text.StringsKt__StringsJVMKt.equals(r0, r3, r2)
            r5.q = r8
            r5.r = r7
            r5.s = r6
            r5.t = r11
            r5.u = r12
            r5.v = r13
            r5.w = r14
            r5.x = r1
            r5.A = r2
            java.lang.Object r15 = com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoicePlatform_androidKt.a(r0, r5)
            if (r15 != r4) goto Ldb
            return r4
        Ld3:
            java.lang.String r13 = ""
            goto Lb2
        Ld6:
            java.lang.String r11 = "price_discount_sheet"
            goto Lac
        Ld9:
            r1 = 0
            goto L9c
        Ldb:
            r4 = r8
            goto L45
        Lde:
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$createPopupContentParams$1 r5 = new com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$createPopupContentParams$1
            r5.<init>(r8, r3)
            goto L1a
        Le5:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        Led:
            kotlin.NoWhenBranchMatchedException r0 = new kotlin.NoWhenBranchMatchedException
            r0.<init>()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge.a(com.lemon.clipmonetize.biz.commerce_page.lv_oversea.settings.PaymentChoicePanelUx, com.lemon.clipmonetize.biz.product_service.ProductInfo, com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.ExternalDiscountInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0161  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object c(android.app.Activity r16, com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.ExternalDiscountInfo r17, com.lemon.clipmonetize.biz.commerce_page.lv_oversea.settings.PaymentChoicePanelUx r18, com.lemon.clipmonetize.biz.product_service.ProductInfo r19, kotlin.coroutines.Continuation r20) throws java.lang.Throwable {
        /*
            r15 = this;
            r3 = r20
            boolean r0 = r3 instanceof com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$1
            if (r0 == 0) goto L1de
            r6 = r3
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$1 r6 = (com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$1) r6
            int r2 = r6.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L1de
            int r2 = r2 - r1
            r6.t = r2
        L14:
            java.lang.Object r3 = r6.r
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r6.t
            r13 = 0
            r7 = 1
            java.lang.String r2 = ""
            r1 = 2
            if (r0 == 0) goto L2b
            if (r0 == r7) goto L54
            if (r0 != r1) goto L1e5
            kotlin.ResultKt.throwOnFailure(r3)
        L2a:
            return r3
        L2b:
            kotlin.ResultKt.throwOnFailure(r3)
            boolean r0 = b()
            if (r0 != 0) goto L39
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r13)
            return r0
        L39:
            r0 = r16
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            com.pipo.capcut.paymentchoice.PaymentChoiceUiController r9 = new com.pipo.capcut.paymentchoice.PaymentChoiceUiController
            r9.<init>(r0)
            r6.q = r9
            r6.t = r7
            r4 = r17
            r3 = r18
            r0 = r19
            java.lang.Object r3 = r15.a(r3, r0, r4, r6)
            if (r3 != r5) goto L59
            return r5
        L54:
            com.pipo.capcut.paymentchoice.PaymentChoiceUiController r9 = r6.q
            kotlin.ResultKt.throwOnFailure(r3)
        L59:
            com.pipo.capcut.paymentchoice.PopupContentParams r3 = (com.pipo.capcut.paymentchoice.PopupContentParams) r3
            r6.q = r9
            r6.getClass()
            r6.t = r1
            kotlinx.coroutines.CancellableContinuationImpl r4 = new kotlinx.coroutines.CancellableContinuationImpl
            kotlin.coroutines.Continuation r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.intercepted(r6)
            r4.<init>(r0, r7)
            r4.initCancellability()
            com.pipo.capcut.paymentchoice.PaymentChoiceSdk r0 = com.pipo.capcut.paymentchoice.PaymentChoiceSdk.f64549a
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$2$error$1 r8 = new com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$2$error$1
            r8.<init>()
            r0.getClass()
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r2)
            com.pipo.capcut.paymentchoice.internal.PaymentChoiceSdkHolder r0 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceSdkHolder.f64571a
            r0.getClass()
            com.pipo.capcut.paymentchoice.PaymentChoiceSdkConfig r2 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceSdkHolder.b
            java.lang.String r7 = "degraded_to_iap"
            if (r2 != 0) goto Lde
            com.pipo.capcut.paymentchoice.PaymentChoiceResult r0 = new com.pipo.capcut.paymentchoice.PaymentChoiceResult
            r0.<init>(r7)
            r8.invoke(r0)
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = new com.pipo.capcut.paymentchoice.PaymentChoiceError
            java.lang.String r1 = "not_initialized"
            java.lang.String r0 = "PaymentChoiceSdk has not been initialized"
            r10.<init>(r1, r0)
        L9a:
            if (r10 == 0) goto Lce
            boolean r0 = r4.isActive()
            if (r0 == 0) goto Lce
            com.lemon.lv.clipmonetize.platform.KMPLog r2 = com.lemon.lv.clipmonetize.platform.KMPLog.b
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "Payment choice sdk popup failed: "
            r1.<init>(r0)
            java.lang.String r0 = r10.f64544a
            r1.append(r0)
            java.lang.String r0 = ", "
            r1.append(r0)
            java.lang.String r0 = r10.b
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "PaymentChoiceSdkBridge"
            r2.d(r0, r1)
            r0 = 0
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            kotlin.Result.m17090constructorimpl(r0)
            r4.resumeWith(r0)
        Lce:
            java.lang.Object r3 = r4.getResult()
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            if (r3 != r0) goto Ldb
            kotlin.coroutines.jvm.internal.DebugProbesKt.probeCoroutineSuspended(r6)
        Ldb:
            if (r3 != r5) goto L2a
            return r5
        Lde:
            com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator r0 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator.f64572a
            r0.getClass()
            java.util.Set<java.lang.String> r1 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator.f64573c
            java.lang.String r0 = r3.f64554a
            boolean r0 = r1.contains(r0)
            if (r0 != 0) goto L101
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = new com.pipo.capcut.paymentchoice.PaymentChoiceError
            java.lang.String r1 = "invalid_popup_params"
            java.lang.String r0 = "style must be price_discount_sheet or discount_sheet"
            r10.<init>(r1, r0)
        Lf6:
            if (r10 == 0) goto L163
            com.pipo.capcut.paymentchoice.PaymentChoiceResult r0 = new com.pipo.capcut.paymentchoice.PaymentChoiceResult
            r0.<init>(r7)
            r8.invoke(r0)
            goto L9a
        L101:
            java.util.List<java.lang.String> r0 = r3.f64557g
            boolean r0 = r0.isEmpty()
            r0 = r0 ^ 1
            if (r0 == 0) goto L145
            java.util.List<java.lang.String> r0 = r3.f64557g
            java.util.Iterator r14 = r0.iterator()
        L111:
            boolean r0 = r14.hasNext()
            if (r0 == 0) goto L145
            java.lang.Object r12 = r14.next()
            int r11 = r13 + 1
            if (r13 >= 0) goto L122
            kotlin.collections.CollectionsKt__CollectionsKt.throwIndexOverflow()
        L122:
            java.lang.String r12 = (java.lang.String) r12
            com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator r10 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator.f64572a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "primaryButtonColors["
            r1.<init>(r0)
            r1.append(r13)
            r0 = 93
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r10.getClass()
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator.a(r12, r0)
            if (r10 == 0) goto L143
            goto Lf6
        L143:
            r13 = r11
            goto L111
        L145:
            java.lang.String r1 = r3.h
            if (r1 == 0) goto L161
            boolean r0 = kotlin.text.StringsKt__StringsKt.isBlank(r1)
            r0 = r0 ^ 1
            if (r0 == 0) goto L15f
        L151:
            if (r1 == 0) goto L161
            com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator r0 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator.f64572a
            r0.getClass()
            java.lang.String r0 = "primaryButtonTextColor"
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceValidator.a(r1, r0)
            goto Lf6
        L15f:
            r1 = 0
            goto L151
        L161:
            r10 = 0
            goto Lf6
        L163:
            com.pipo.capcut.dialog.PipoCapcutDialogConfig r2 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceConfigMapperKt.b(r3, r2, r8)     // Catch: java.lang.Throwable -> L16b
            kotlin.Result.m17090constructorimpl(r2)     // Catch: java.lang.Throwable -> L16b
            goto L173
        L16b:
            r0 = move-exception
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r2)
        L173:
            java.lang.Throwable r1 = kotlin.Result.m17093exceptionOrNullimpl(r2)
            java.lang.String r3 = "popup_display_failed"
            if (r1 != 0) goto L1c7
            com.pipo.capcut.dialog.PipoCapcutDialogConfig r2 = (com.pipo.capcut.dialog.PipoCapcutDialogConfig) r2
            com.pipo.capcut.paymentchoice.internal.PaymentChoiceDialogPresenter r0 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceDialogPresenter.f64568a     // Catch: java.lang.Throwable -> L18a
            r0.getClass()     // Catch: java.lang.Throwable -> L18a
            com.pipo.capcut.dialog.PipoCapcutDialogError r2 = com.pipo.capcut.paymentchoice.internal.PaymentChoiceDialogPresenter.a(r9, r2)     // Catch: java.lang.Throwable -> L18a
            kotlin.Result.m17090constructorimpl(r2)     // Catch: java.lang.Throwable -> L18a
            goto L192
        L18a:
            r0 = move-exception
            java.lang.Object r2 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r2)
        L192:
            java.lang.Throwable r1 = kotlin.Result.m17093exceptionOrNullimpl(r2)
            if (r1 != 0) goto L1b0
            com.pipo.capcut.dialog.PipoCapcutDialogError r2 = (com.pipo.capcut.dialog.PipoCapcutDialogError) r2
            if (r2 == 0) goto L1ad
            com.pipo.capcut.paymentchoice.PaymentChoiceResult r0 = new com.pipo.capcut.paymentchoice.PaymentChoiceResult
            r0.<init>(r7)
            r8.invoke(r0)
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = new com.pipo.capcut.paymentchoice.PaymentChoiceError
            java.lang.String r0 = r2.b
            r10.<init>(r3, r0)
            goto L9a
        L1ad:
            r10 = 0
            goto L9a
        L1b0:
            com.pipo.capcut.paymentchoice.PaymentChoiceResult r0 = new com.pipo.capcut.paymentchoice.PaymentChoiceResult
            r0.<init>(r7)
            r8.invoke(r0)
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = new com.pipo.capcut.paymentchoice.PaymentChoiceError
            java.lang.String r0 = r1.getMessage()
            if (r0 != 0) goto L1c2
            java.lang.String r0 = "Failed to show payment choice popup"
        L1c2:
            r10.<init>(r3, r0)
            goto L9a
        L1c7:
            com.pipo.capcut.paymentchoice.PaymentChoiceResult r0 = new com.pipo.capcut.paymentchoice.PaymentChoiceResult
            r0.<init>(r7)
            r8.invoke(r0)
            com.pipo.capcut.paymentchoice.PaymentChoiceError r10 = new com.pipo.capcut.paymentchoice.PaymentChoiceError
            java.lang.String r0 = r1.getMessage()
            if (r0 != 0) goto L1d9
            java.lang.String r0 = "Failed to create payment choice popup config"
        L1d9:
            r10.<init>(r3, r0)
            goto L9a
        L1de:
            com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$1 r6 = new com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge$resolveExternalPaymentSelected$1
            r6.<init>(r15, r3)
            goto L14
        L1e5:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.PaymentChoiceSdkBridge.c(android.app.Activity, com.lemon.clipmonetize.biz.commerce_page.lv_oversea.purchase.ExternalDiscountInfo, com.lemon.clipmonetize.biz.commerce_page.lv_oversea.settings.PaymentChoicePanelUx, com.lemon.clipmonetize.biz.product_service.ProductInfo, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
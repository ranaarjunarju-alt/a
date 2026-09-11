package com.vega.deeplink.provider;

import android.net.Uri;
import com.vega.deeplinkapi.Continue;
import com.vega.deeplinkapi.DeepLinkConfigProvider;
import com.vega.deeplinkapi.DeepLinkDispatchResult;
import com.vega.deeplinkapi.DeepLinkDispatchSession;
import java.util.List;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Named("//ailab_agent/extrafeed")
/* loaded from: classes16.dex */
public final class AiLabAgentExtrafeedDeepLinkConfigProvider implements DeepLinkConfigProvider {

    /* loaded from: classes15.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final List<String> a(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return CollectionsKt__CollectionsKt.emptyList();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003c  */
    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(com.vega.deeplinkapi.DeepLinkDispatchSession r8, kotlin.coroutines.Continuation<? super android.net.Uri> r9) {
        /*
            r7 = this;
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r6 = "mode"
            java.lang.String r5 = r0.getQueryParameter(r6)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.net.Uri r1 = r0.f82398a
            java.lang.String r0 = "model"
            java.lang.String r3 = r1.getQueryParameter(r0)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.net.Uri r0 = r0.f82398a
            java.util.Objects.toString(r0)
            r2 = 1
            if (r5 == 0) goto L54
            int r0 = r5.length()
            if (r0 <= 0) goto L54
            com.vega.videoagentapi.common.input.InputModalType$Companion r0 = com.vega.videoagentapi.common.input.InputModalType.b
            r0.getClass()
            com.vega.videoagentapi.common.input.InputModalType r4 = com.vega.videoagentapi.common.input.InputModalType.Companion.a(r5)
            if (r4 == 0) goto L3c
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.os.Bundle r1 = r0.f82399c
            java.lang.String r0 = r4.f135539a
            r1.putString(r6, r0)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            if (r0 != 0) goto L54
        L3c:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "redirect: mode raw="
            r1.<init>(r0)
            r1.append(r5)
            java.lang.String r0 = " not match InputModalType, drop"
            r1.append(r0)
            java.lang.String r1 = r1.toString()
            java.lang.String r0 = "AiLabAgentExtrafeedDeepLinkConfigProvider"
            com.vega.log.BLog.w(r0, r1)
        L54:
            if (r3 == 0) goto L6e
            int r0 = r3.length()
            if (r0 <= 0) goto L6e
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.os.Bundle r1 = r0.f82399c
            java.lang.String r0 = "deeplink_target_model_id"
            r1.putString(r0, r3)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.os.Bundle r1 = r0.f82399c
            java.lang.String r0 = "input_show_default_select_model_first_time"
            r1.putBoolean(r0, r2)
        L6e:
            android.net.Uri$Builder r1 = new android.net.Uri$Builder
            r1.<init>()
            java.lang.String r0 = "capcut"
            android.net.Uri$Builder r1 = r1.scheme(r0)
            java.lang.String r0 = "//trends_agent/feed"
            android.net.Uri$Builder r2 = r1.path(r0)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.net.Uri r1 = r0.f82398a
            java.util.List r0 = kotlin.collections.CollectionsKt__CollectionsKt.emptyList()
            com.vega.deeplinkapi.DeepLinkUtilsKt.a(r2, r1, r0)
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r1 = "feed_scene"
            java.lang.String r0 = r0.getQueryParameter(r1)
            if (r0 != 0) goto L9e
            java.lang.String r0 = "ai"
            r2.appendQueryParameter(r1, r0)
        L9e:
            com.vega.deeplinkapi.DeepLinkDispatchData r0 = r8.b
            android.net.Uri r0 = r0.f82398a
            java.lang.String r1 = "is_show_input_box"
            java.lang.String r0 = r0.getQueryParameter(r1)
            if (r0 != 0) goto Laf
            java.lang.String r0 = "true"
            r2.appendQueryParameter(r1, r0)
        Laf:
            java.lang.String r1 = r2.toString()
            java.lang.String r0 = ""
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r0)
            android.net.Uri r0 = android.net.Uri.parse(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.deeplink.provider.AiLabAgentExtrafeedDeepLinkConfigProvider.b(com.vega.deeplinkapi.DeepLinkDispatchSession, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final boolean c(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return true;
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final void d(DeepLinkDispatchSession deepLinkDispatchSession) {
        Intrinsics.checkNotNullParameter(deepLinkDispatchSession, "");
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final boolean e(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return false;
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final Object f(DeepLinkDispatchSession deepLinkDispatchSession, Continuation<? super DeepLinkDispatchResult> continuation) {
        return Continue.f82397a;
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final String g(DeepLinkDispatchSession deepLinkDispatchSession) {
        Intrinsics.checkNotNullParameter(deepLinkDispatchSession, "");
        Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//main/tabbar");
        Intrinsics.checkNotNull(builderPath);
        builderPath.appendQueryParameter("index", "0");
        builderPath.appendQueryParameter("anchor_key", "ai_story");
        builderPath.appendQueryParameter("anchor_feature", "ai_lab");
        String string = builderPath.toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return string;
    }
}
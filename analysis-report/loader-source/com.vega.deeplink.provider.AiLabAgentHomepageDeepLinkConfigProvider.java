package com.vega.deeplink.provider;

import android.net.Uri;
import com.vega.deeplinkapi.Continue;
import com.vega.deeplinkapi.DeepLinkConfigProvider;
import com.vega.deeplinkapi.DeepLinkDispatchResult;
import com.vega.deeplinkapi.DeepLinkDispatchSession;
import com.vega.feedx.unifyagent.AiLabHomepageVersionOverride;
import com.vega.log.BLog;
import java.util.List;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringNumberConversionsKt;

@Named("//ailab_agent/homepage")
/* loaded from: classes26.dex */
public final class AiLabAgentHomepageDeepLinkConfigProvider implements DeepLinkConfigProvider {

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

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final Object b(DeepLinkDispatchSession deepLinkDispatchSession, Continuation<? super Uri> continuation) {
        Uri uri = deepLinkDispatchSession.b.f82398a;
        String queryParameter = uri.getQueryParameter("version");
        Integer intOrNull = queryParameter != null ? StringsKt__StringNumberConversionsKt.toIntOrNull(queryParameter) : null;
        BLog.i("AiLabHomepageDL", "redirect sourceUri=" + uri + " version=" + intOrNull);
        if (intOrNull != null) {
            AiLabHomepageVersionOverride aiLabHomepageVersionOverride = AiLabHomepageVersionOverride.f101978a;
            int iIntValue = intOrNull.intValue();
            aiLabHomepageVersionOverride.getClass();
            AiLabHomepageVersionOverride.b = Integer.valueOf(iIntValue);
        }
        Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//main/tabbar");
        Intrinsics.checkNotNull(builderPath);
        builderPath.appendQueryParameter("index", "11");
        builderPath.appendQueryParameter("anchor_feature", "ai_lab");
        String string = builderPath.toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return Uri.parse(string);
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
        return null;
    }
}
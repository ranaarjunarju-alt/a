package com.vega.deeplink.provider;

import android.net.Uri;
import com.vega.deeplinkapi.Continue;
import com.vega.deeplinkapi.DeepLinkConfigProvider;
import com.vega.deeplinkapi.DeepLinkDispatchResult;
import com.vega.deeplinkapi.DeepLinkDispatchSession;
import com.vega.deeplinkapi.DeepLinkUtilsKt;
import com.vega.log.BLog;
import java.util.List;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Named("//ailab_agent/scene")
/* loaded from: classes15.dex */
public final class AiLabAgentSceneDeepLinkConfigProvider implements DeepLinkConfigProvider {

    /* loaded from: classes.dex */
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
        BLog.i("AiLabAgentSceneDL", "[AILabSceneDL] redirect ENTRY sourceUri=" + uri + " page_scene=" + uri.getQueryParameter("page_scene"));
        Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//main/tabbar");
        Intrinsics.checkNotNull(builderPath);
        DeepLinkUtilsKt.a(builderPath, uri, CollectionsKt__CollectionsKt.listOf((Object[]) new String[]{"index", "anchor_feature"}));
        builderPath.appendQueryParameter("index", "11");
        builderPath.appendQueryParameter("anchor_feature", "ai_lab");
        String string = builderPath.toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        Uri uri2 = Uri.parse(string);
        BLog.i("AiLabAgentSceneDL", "[AILabSceneDL] redirect EXIT target=" + uri2);
        return uri2;
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
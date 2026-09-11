package com.vega.deeplink.provider;

import X.C0NI;
import X.C111833mm;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.webkit.URLUtil;
import androidx.core.view.ViewCompat;
import com.vega.core.ext.ExtentionKt;
import com.vega.deeplink.utils.WebCheckUtilsKt;
import com.vega.deeplinkapi.Continue;
import com.vega.deeplinkapi.DeepLinkConfigProvider;
import com.vega.deeplinkapi.DeepLinkDataProvider;
import com.vega.deeplinkapi.DeepLinkDispatchData;
import com.vega.deeplinkapi.DeepLinkDispatchResult;
import com.vega.deeplinkapi.DeepLinkDispatchSession;
import com.vega.deeplinkapi.DeepLinkUtilsKt;
import com.vega.deeplinkapi.data.MainWebDeepLinkData;
import com.vega.deeplinkapi.data.MainWebDeepLinkDataKt;
import java.util.List;
import javax.inject.Named;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

@Named("//main/web")
/* loaded from: classes26.dex */
public final class MainWebDeepLinkConfigProvider implements DeepLinkConfigProvider {
    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final List<String> a(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return CollectionsKt__CollectionsKt.emptyList();
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final Object b(DeepLinkDispatchSession deepLinkDispatchSession, Continuation<? super Uri> continuation) {
        String loadingBgcolor;
        Uri uri = deepLinkDispatchSession.b.f82398a;
        DeepLinkDataProvider deepLinkDataProvider = (DeepLinkDataProvider) C0NI.f2191a.a().getScopeRegistry().getRootScope().getOrNull(Reflection.getOrCreateKotlinClass(DeepLinkDataProvider.class), new C111833mm(Reflection.getOrCreateKotlinClass(MainWebDeepLinkData.class)), null);
        MainWebDeepLinkData mainWebDeepLinkData = (MainWebDeepLinkData) (deepLinkDataProvider != null ? deepLinkDataProvider.b(uri) : null);
        if (!Intrinsics.areEqual(DeepLinkUtilsKt.j(deepLinkDispatchSession.b.f82398a), "//main/web") || mainWebDeepLinkData == null || (loadingBgcolor = mainWebDeepLinkData.getLoadingBgcolor()) == null || Color.alpha(ExtentionKt.rgba("#".concat(loadingBgcolor), ViewCompat.MEASURED_STATE_MASK)) == 255) {
            return null;
        }
        Uri.Builder builderPath = new Uri.Builder().scheme("capcut").path("//main/web_trans");
        Intrinsics.checkNotNull(builderPath);
        DeepLinkUtilsKt.a(builderPath, deepLinkDispatchSession.b.f82398a, CollectionsKt__CollectionsKt.emptyList());
        String string = builderPath.toString();
        Intrinsics.checkNotNullExpressionValue(string, "");
        return Uri.parse(string);
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final boolean c(Uri uri) {
        Intrinsics.checkNotNullParameter(uri, "");
        return MainWebDeepLinkDataKt.f82505a.invoke(uri).booleanValue() && URLUtil.isNetworkUrl(uri.getQueryParameter("web_url")) && WebCheckUtilsKt.a(uri);
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
        Intent intent;
        String stringExtra;
        Uri uri = deepLinkDispatchSession.b.f82398a;
        DeepLinkDataProvider deepLinkDataProvider = (DeepLinkDataProvider) C0NI.f2191a.a().getScopeRegistry().getRootScope().getOrNull(Reflection.getOrCreateKotlinClass(DeepLinkDataProvider.class), new C111833mm(Reflection.getOrCreateKotlinClass(MainWebDeepLinkData.class)), null);
        MainWebDeepLinkData mainWebDeepLinkData = (MainWebDeepLinkData) (deepLinkDataProvider != null ? deepLinkDataProvider.b(uri) : null);
        if ((mainWebDeepLinkData != null && (stringExtra = mainWebDeepLinkData.getWebUrl()) != null) || ((intent = deepLinkDispatchSession.b.l) != null && (stringExtra = intent.getStringExtra("web_url")) != null)) {
            DeepLinkDispatchData deepLinkDispatchData = deepLinkDispatchSession.b;
            deepLinkDispatchData.f82399c.putString("web_url", DeepLinkUtilsKt.b(deepLinkDispatchData.f82398a, stringExtra));
        }
        return Continue.f82397a;
    }

    @Override // com.vega.deeplinkapi.DeepLinkConfigProvider
    public final String g(DeepLinkDispatchSession deepLinkDispatchSession) {
        Intrinsics.checkNotNullParameter(deepLinkDispatchSession, "");
        return null;
    }
}
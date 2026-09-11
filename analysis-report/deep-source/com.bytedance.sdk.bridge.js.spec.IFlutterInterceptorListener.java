package com.bytedance.sdk.bridge.js.spec;

import android.webkit.WebView;
import com.bytedance.sdk.bridge.model.BridgeResult;
import org.json.JSONObject;

/* loaded from: classes11.dex */
public interface IFlutterInterceptorListener {
    void call(String str, JSONObject jSONObject, JsBridgeContext jsBridgeContext);

    BridgeResult callSync(String str, JSONObject jSONObject, JsBridgeContext jsBridgeContext);

    boolean isFlutterWebView(WebView webView);

    boolean isInterceptor();
}
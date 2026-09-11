package com.vega.launcher.privacy.looki.network;

import com.bytedance.retrofit2.SsResponse;
import com.lm.components.network.network.INetWorker;
import com.lm.components.privacy.looki.LookiBanOutLog;
import com.vega.core.net.NetworkManagerWrapper;
import com.vega.core.privacy.looki.LookiNextPseudonymStatus;
import com.vega.core.privacy.looki.LookiSpManager;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes35.dex */
public final class LookiDevicePseudonymManager$reportDevicePseudonymAbtest$1 implements INetWorker.OnRequestListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ Map<String, String> f107455a;
    public final /* synthetic */ int b;

    /* renamed from: c, reason: collision with root package name */
    public final /* synthetic */ int f107456c;

    public LookiDevicePseudonymManager$reportDevicePseudonymAbtest$1(Map<String, String> map, int i, int i2) {
        this.f107455a = map;
        this.b = i;
        this.f107456c = i2;
    }

    @Override // com.lm.components.network.network.INetWorker.OnRequestListener
    public final void onFailure(Exception exc, String str) throws JSONException {
        Objects.toString(exc);
        LookiDevicePseudonymManager lookiDevicePseudonymManager = LookiDevicePseudonymManager.f107452a;
        Map<String, String> map = this.f107455a;
        int i = this.b;
        int i2 = this.f107456c - 1;
        lookiDevicePseudonymManager.getClass();
        if (i2 == 0) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("biz_id", "capcut");
        jSONObject.put("type", i);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value.length() > 0) {
                jSONObject.put(key, value);
            }
        }
        HashMap mapHashMapOf = MapsKt__MapsKt.hashMapOf(TuplesKt.to("host", "general-api-us-looki.capcutapi.com"));
        jSONObject.toString();
        NetworkManagerWrapper networkManagerWrapper = NetworkManagerWrapper.f79356a;
        LookiDevicePseudonymManager$reportDevicePseudonymAbtest$1 lookiDevicePseudonymManager$reportDevicePseudonymAbtest$1 = new LookiDevicePseudonymManager$reportDevicePseudonymAbtest$1(map, i, i2);
        networkManagerWrapper.getClass();
        NetworkManagerWrapper.i("https://general-api-us-looki.capcutapi.com/looki/client/ag/v1/abtest", jSONObject, mapHashMapOf, null, lookiDevicePseudonymManager$reportDevicePseudonymAbtest$1);
    }

    @Override // com.lm.components.network.network.INetWorker.OnRequestListener
    public final void onSuccess(SsResponse<String> ssResponse) {
        if (this.f107455a.containsKey(LookiDevicePseudonymManager.b)) {
            LookiNextPseudonymStatus.k.getClass();
            String str = LookiNextPseudonymStatus.Companion.a().f79456d;
            LookiSpManager.f79474a.getClass();
            LookiSpManager.c().putBoolean(str, false).commit();
            LookiBanOutLog.a("LookiLog-LookiNextPseudonymStatus", "setIPPseudonymChangedSp hasSettingsChanged = false");
        }
        if (this.f107455a.containsKey(LookiDevicePseudonymManager.f107453c)) {
            LookiNextPseudonymStatus.k.getClass();
            String str2 = LookiNextPseudonymStatus.Companion.a().e;
            LookiSpManager.f79474a.getClass();
            LookiSpManager.c().putBoolean(str2, false).commit();
            LookiBanOutLog.a("LookiLog-LookiNextPseudonymStatus", "setGAIDPseudonymChangedSp hasSettingsChanged = false");
        }
        if (this.f107455a.containsKey(LookiDevicePseudonymManager.f107454d)) {
            LookiNextPseudonymStatus.k.getClass();
            String str3 = LookiNextPseudonymStatus.Companion.a().f;
            LookiSpManager.f79474a.getClass();
            LookiSpManager.c().putBoolean(str3, false).commit();
            LookiBanOutLog.a("LookiLog-LookiNextPseudonymStatus", "setAndroidIdPseudonymChangedSp hasSettingsChanged = false");
        }
    }
}
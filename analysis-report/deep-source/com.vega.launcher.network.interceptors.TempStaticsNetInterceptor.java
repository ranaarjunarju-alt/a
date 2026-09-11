package com.vega.launcher.network.interceptors;

import android.net.Uri;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.ttnet.cronet.CronetDataStorageAccess;
import com.lm.components.network.extra.NetworkRuntimeConfig;
import com.vega.core.net.NetworkManagerWrapper;
import com.vega.infrastructure.base.ModuleCommon;
import com.vega.kv.KvStorage;
import com.vega.report.ReportManagerWrapper;
import java.util.Observable;
import java.util.Observer;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref$ObjectRef;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes16.dex */
public final class TempStaticsNetInterceptor implements Interceptor {
    public int b;

    /* renamed from: d, reason: collision with root package name */
    public Boolean f107415d;
    public boolean e;
    public int f;

    /* renamed from: a, reason: collision with root package name */
    public final long f107413a = System.currentTimeMillis();

    /* renamed from: c, reason: collision with root package name */
    public final KvStorage f107414c = new KvStorage(ModuleCommon.INSTANCE.getApplication(), "temp_statics_net_flag");

    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX WARN: Type inference failed for: r0v17, types: [T, com.vega.launcher.network.interceptors.TempStaticsNetInterceptor$intercept$1] */
    @Override // com.bytedance.retrofit2.intercept.Interceptor
    public final SsResponse<?> intercept(Interceptor.Chain chain) throws JSONException {
        Intrinsics.checkNotNullParameter(chain, "");
        Request request = chain.request();
        Intrinsics.checkNotNullExpressionValue(request, "");
        NetworkRuntimeConfig.f60924a.getClass();
        if (NetworkRuntimeConfig.a(request)) {
            SsResponse<?> ssResponseProceed = chain.proceed(request);
            Intrinsics.checkNotNullExpressionValue(ssResponseProceed, "");
            return ssResponseProceed;
        }
        String regionSource = CronetDataStorageAccess.getRegionSource();
        String userRegion = CronetDataStorageAccess.getUserRegion();
        Intrinsics.checkNotNull(userRegion);
        if (userRegion.length() == 0 || (!regionSource.equals("did") && !regionSource.equals("uid"))) {
            if (this.f107415d == null) {
                this.f = this.f107414c.f("launch_count", 0);
                Boolean boolValueOf = Boolean.valueOf(this.f107414c.d("has_report", false));
                this.f107415d = boolValueOf;
                Intrinsics.checkNotNull(boolValueOf);
                if (!boolValueOf.booleanValue()) {
                    this.f107414c.l("has_report", true, false);
                }
            }
            NetworkManagerWrapper.f79356a.getClass();
            if (!NetworkManagerWrapper.n()) {
                this.f107415d = Boolean.TRUE;
                SsResponse<?> ssResponseProceed2 = chain.proceed(request);
                Intrinsics.checkNotNullExpressionValue(ssResponseProceed2, "");
                return ssResponseProceed2;
            }
            Boolean bool = this.f107415d;
            Boolean bool2 = Boolean.FALSE;
            if (Intrinsics.areEqual(bool, bool2) && this.b <= 100 && request.getUrl() != null) {
                this.b++;
                Uri uri = Uri.parse(request.getUrl());
                JSONObject jSONObject = new JSONObject(MapsKt__MapsKt.mapOf(TuplesKt.to("host", uri.getHost()), TuplesKt.to("path", uri.getPath()), TuplesKt.to("launch_time", Long.valueOf(System.currentTimeMillis() - this.f107413a)), TuplesKt.to("count", Integer.valueOf(this.b))));
                jSONObject.toString();
                ReportManagerWrapper.INSTANCE.onEvent("cc_url_before_store_region", jSONObject);
            }
            if (Intrinsics.areEqual(this.f107415d, bool2) && !this.e) {
                this.e = true;
                final Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
                ref$ObjectRef.element = new Observer() { // from class: com.vega.launcher.network.interceptors.TempStaticsNetInterceptor.intercept.1
                    @Override // java.util.Observer
                    public final void update(Observable observable, Object obj) throws JSONException {
                        String regionSource2 = CronetDataStorageAccess.getRegionSource();
                        String userRegion2 = CronetDataStorageAccess.getUserRegion();
                        Intrinsics.checkNotNull(userRegion2);
                        if (userRegion2.length() > 0) {
                            if (regionSource2.equals("did") || regionSource2.equals("uid")) {
                                CronetDataStorageAccess.inst().deleteObserver(ref$ObjectRef.element);
                                JSONObject jSONObject2 = new JSONObject(MapsKt__MapsKt.mapOf(TuplesKt.to("cost_time", Long.valueOf(System.currentTimeMillis() - this.f107413a)), TuplesKt.to("launch_count", Integer.valueOf(this.f))));
                                jSONObject2.toString();
                                ReportManagerWrapper.INSTANCE.onEvent("cc_url_get_user_region", jSONObject2);
                            }
                        }
                    }
                };
                CronetDataStorageAccess.inst().addObserver((Observer) ref$ObjectRef.element);
            }
        }
        SsResponse<?> ssResponseProceed3 = chain.proceed(request);
        Intrinsics.checkNotNullExpressionValue(ssResponseProceed3, "");
        return ssResponseProceed3;
    }
}
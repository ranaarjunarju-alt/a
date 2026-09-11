package com.vega.feedback.upload;

import com.bytedance.common.profilesdk.ProfileManager;
import com.bytedance.privacy.dispatch.DispatchManager;
import com.bytedance.privacy.region.RegionManager;
import com.google.gson.Gson;
import com.vega.core.context.ContextExtKt;
import com.vega.core.ext.JSONObjectExKt;
import com.vega.core.net.NetworkManagerWrapper;
import com.vega.core.net.NetworkManagerWrapperKt;
import com.vega.core.privacy.looki.LookiCurrentPseudonymStatus;
import com.vega.core.privacy.looki.LookiNextPseudonymStatus;
import com.vega.core.privacy.looki.LookiRegionManager;
import com.vega.core.privacy.looki.LookiSpManager;
import com.vega.core.privacy.looki.LookiTncDispatchStatus;
import com.vega.core.privacy.looki.TncDispatchEnum;
import com.vega.feedback.FeedbackUtilKt;
import com.vega.feedback.UrlConfig;
import com.vega.feelgoodapi.model.FeedbackItem;
import com.vega.feelgoodapi.model.RemotePicData;
import com.vega.feelgoodapi.model.UploadPicResult;
import com.vega.feelgoodapi.upload.FeedbackReportApi;
import com.vega.log.BLog;
import com.vega.report.AppLogManagerWrapper;
import com.vega.report.ReportManagerWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes38.dex */
public final class FeedbackReportRequester implements FeedbackReportApi {

    /* renamed from: a, reason: collision with root package name */
    public final String f99538a;
    public final String b;

    public FeedbackReportRequester() {
        UrlConfig urlConfig = UrlConfig.f99522a;
        urlConfig.getClass();
        this.f99538a = "https://editor-api.capcutapi.com/feedback/2/post_message/";
        urlConfig.getClass();
        this.b = "https://editor-api.capcutapi.com/feedback/3/list/";
    }

    public static String f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObjectExKt.g(jSONObject, g(), true);
            RegionManager regionManager = RegionManager.INSTANCE;
            jSONObject.put("store_country_code", regionManager.getCurrentRegion().getRegionStr());
            jSONObject.put("store_country_code_src", regionManager.getCurrentRegion().getSourceStr());
            jSONObject.put("is_dispatch_us_ttp", DispatchManager.INSTANCE.isUSTTP() ? ProfileManager.VERSION : "0");
            JSONObjectExKt.g(jSONObject, FeedbackUtilKt.d(), true);
            String string = jSONObject.toString();
            Intrinsics.checkNotNull(string);
            return string;
        } catch (Throwable unused) {
            return str;
        }
    }

    public static JSONObject g() {
        JSONObject jSONObject = new JSONObject();
        try {
            LookiNextPseudonymStatus.k.getClass();
            jSONObject.put("is_ip_pseudonym", String.valueOf(LookiNextPseudonymStatus.Companion.a().f79454a.f79490a));
            LookiTncDispatchStatus.s.getClass();
            jSONObject.put("is_looki_dispatch", LookiTncDispatchStatus.Companion.a().h == TncDispatchEnum.f79498c ? ProfileManager.VERSION : "0");
            LookiCurrentPseudonymStatus.j.getClass();
            LookiCurrentPseudonymStatus.Companion.a().getClass();
            jSONObject.put("is_did_pseudonym", "0");
            LookiCurrentPseudonymStatus.Companion.a().getClass();
            jSONObject.put("is_uid_pseudonym", "0");
            LookiRegionManager.f79462a.getClass();
            jSONObject.put("store_region_new", LookiRegionManager.f79463c);
            jSONObject.put("store_region_src_new", LookiRegionManager.f79464d);
            jSONObject.put("is_gaid_pseudonym", String.valueOf(LookiNextPseudonymStatus.Companion.a().b.f79490a));
            jSONObject.put("is_android_id_pseudonym", String.valueOf(LookiNextPseudonymStatus.Companion.a().f79455c.f79490a));
            LookiSpManager.f79474a.getClass();
            jSONObject.put("is_phone_number_pseudonym", LookiSpManager.b("looki-pseudonym-phone-number"));
            jSONObject.put("is_email_pseudonym", LookiSpManager.b("looki-pseudonym-email"));
            jSONObject.put("is_identification_card_number_pseudonym", LookiSpManager.b("looki-pseudonym-identification-card-number"));
            jSONObject.put("is_real_name_pseudonym", LookiSpManager.b("looki-pseudonym-real-name"));
            jSONObject.put("is_residential_address_pseudonym", LookiSpManager.b("looki-pseudonym-residential-address"));
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    @Override // com.vega.feelgoodapi.upload.FeedbackReportApi
    public final boolean a(String str, String str2, String str3, ArrayList arrayList, String str4, int i, int i2, String str5, boolean z, boolean z2, Map map) throws JSONException {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str4, "");
        Intrinsics.checkNotNullParameter(str5, "");
        Intrinsics.checkNotNullParameter(map, "");
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            linkedHashMap.put("appkey", "vicut-android");
            linkedHashMap.put("content", str);
            if (str2 != null) {
            }
            linkedHashMap.put("qr_id", str5);
            ContextExtKt.app().K();
            linkedHashMap.put("app_version", "19.6.0");
            if (!(arrayList == null || arrayList.isEmpty())) {
                linkedHashMap.put("multi_image", ProfileManager.VERSION);
                JSONArray jSONArray = new JSONArray();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    RemotePicData remotePicData = (RemotePicData) it.next();
                    jSONArray.put(new JSONObject().put("image_width", String.valueOf(remotePicData.getWidth())).put("image_height", String.valueOf(remotePicData.getHeight())).put("image_uri", remotePicData.getWebUri()));
                }
                String string = jSONArray.toString();
                Intrinsics.checkNotNullExpressionValue(string, "");
                linkedHashMap.put("image_list", string);
            } else if (str3 != null) {
                linkedHashMap.put("image_uri", str3);
                linkedHashMap.put("image_width", String.valueOf(i));
                linkedHashMap.put("image_height", String.valueOf(i2));
            }
            linkedHashMap.put("extra_persistent_params", f(str4));
            JSONObject jSONObjectPut = new JSONObject().put("is_private_image", 1);
            Intrinsics.checkNotNullExpressionValue(jSONObjectPut, "");
            JSONObjectExKt.f(map, jSONObjectPut);
            String string2 = jSONObjectPut.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "");
            linkedHashMap.put("extra_params", string2);
            if (z) {
                if (z2) {
                    linkedHashMap.put("is_cc4b_suggestion", ProfileManager.VERSION);
                } else {
                    linkedHashMap.put("is_cc4b_suggestion", "0");
                }
            }
            ReportManagerWrapper.INSTANCE.onEvent("on_feedback_submit_click");
            NetworkManagerWrapper networkManagerWrapper = NetworkManagerWrapper.f79356a;
            try {
                String str6 = this.f99538a;
                networkManagerWrapper.getClass();
                String strOptString = new JSONObject(NetworkManagerWrapperKt.a(NetworkManagerWrapper.k(str6, linkedHashMap, true))).optString("message", "");
                ALogUpload.c(new ALogUpload());
                return Intrinsics.areEqual(strOptString, "success");
            } catch (Exception e) {
                e = e;
                BLog.w("FeedbackReportRequester", "feedback onFailure : " + e.getMessage());
                return false;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x00a3  */
    @Override // com.vega.feelgoodapi.upload.FeedbackReportApi
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object b(kotlin.coroutines.Continuation<? super org.json.JSONObject> r9) throws java.lang.Throwable {
        /*
            r8 = this;
            boolean r0 = r9 instanceof com.vega.feedback.upload.FeedbackReportRequester$imFeedbackExtraBuild$1
            if (r0 == 0) goto La3
            r4 = r9
            com.vega.feedback.upload.FeedbackReportRequester$imFeedbackExtraBuild$1 r4 = (com.vega.feedback.upload.FeedbackReportRequester$imFeedbackExtraBuild$1) r4
            int r2 = r4.v
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto La3
            int r2 = r2 - r1
            r4.v = r2
        L12:
            java.lang.Object r7 = r4.t
            java.lang.Object r5 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.v
            r3 = 1
            if (r0 == 0) goto L39
            if (r0 != r3) goto Laa
            java.lang.Object r6 = r4.s
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r2 = r4.r
            org.json.JSONObject r2 = (org.json.JSONObject) r2
            java.lang.Object r1 = r4.q
            kotlin.ResultKt.throwOnFailure(r7)
        L2c:
            org.json.JSONObject r7 = (org.json.JSONObject) r7
            org.json.JSONObject r0 = g()
            com.vega.core.ext.JSONObjectExKt.g(r7, r0, r3)
            r2.put(r6, r7)
            return r1
        L39:
            kotlin.ResultKt.throwOnFailure(r7)
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            java.lang.String r1 = "appkey"
            java.lang.String r0 = "vicut-android"
            r2.put(r1, r0)
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            r0.I()
            r0 = 3006(0xbbe, float:4.212E-42)
            java.lang.String r1 = java.lang.String.valueOf(r0)
            java.lang.String r0 = "aid"
            r2.put(r0, r1)
            com.vega.corex.context.DeviceInfo r0 = com.vega.core.context.ContextExtKt.device()
            java.lang.String r1 = r0.c()
            java.lang.String r0 = "device_id"
            r2.put(r0, r1)
            java.lang.String r1 = "app_name"
            java.lang.String r0 = "capcut"
            r2.put(r1, r0)
            com.vega.core.context.AppProperty r0 = com.vega.core.context.ContextExtKt.app()
            r0.K()
            java.lang.String r1 = "19.6.0"
            java.lang.String r0 = "app_version"
            r2.put(r0, r1)
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r0 = "is_private_image"
            org.json.JSONObject r0 = r1.put(r0, r3)
            java.lang.String r1 = r0.toString()
            java.lang.String r0 = "extra_params"
            r2.put(r0, r1)
            r4.q = r2
            r4.r = r2
            java.lang.String r6 = "extra_persistent_params"
            r4.s = r6
            r4.v = r3
            java.lang.Object r7 = com.vega.feedback.FeedbackUtilKt.e(r4)
            if (r7 != r5) goto La1
            return r5
        La1:
            r1 = r2
            goto L2c
        La3:
            com.vega.feedback.upload.FeedbackReportRequester$imFeedbackExtraBuild$1 r4 = new com.vega.feedback.upload.FeedbackReportRequester$imFeedbackExtraBuild$1
            r4.<init>(r8, r9)
            goto L12
        Laa:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.feedback.upload.FeedbackReportRequester.b(kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.feelgoodapi.upload.FeedbackReportApi
    public final void c() {
        ALogUpload.c(new ALogUpload());
    }

    @Override // com.vega.feelgoodapi.upload.FeedbackReportApi
    public final FeedbackItem d() {
        HashMap map = new HashMap();
        map.put("appkey", "vicut-android");
        ContextExtKt.app().I();
        map.put("aid", String.valueOf(3006));
        map.put("iid", ContextExtKt.app().m());
        map.put("device_id", AppLogManagerWrapper.INSTANCE.getServerDeviceId());
        map.put("count", "1000");
        NetworkManagerWrapper networkManagerWrapper = NetworkManagerWrapper.f79356a;
        String str = this.b;
        networkManagerWrapper.getClass();
        Object objFromJson = new Gson().fromJson(NetworkManagerWrapperKt.a(NetworkManagerWrapper.k(str, map, true)), (Class<Object>) FeedbackItem.class);
        Intrinsics.checkNotNullExpressionValue(objFromJson, "");
        return (FeedbackItem) objFromJson;
    }

    @Override // com.vega.feelgoodapi.upload.FeedbackReportApi
    public final UploadPicResult e(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "");
        HashMap map = new HashMap();
        map.put("app_name", "videocut");
        map.put("channel", ContextExtKt.app().channel());
        map.put("device_id", AppLogManagerWrapper.INSTANCE.getServerDeviceId());
        ContextExtKt.app().I();
        map.put("aid", String.valueOf(3006));
        map.put("appkey", "videocut-android");
        map.put("iid", ContextExtKt.app().m());
        map.put("use_private_image", "true");
        NetworkManagerWrapper networkManagerWrapper = NetworkManagerWrapper.f79356a;
        UrlConfig.f99522a.getClass();
        String strValueOf = String.valueOf(System.currentTimeMillis());
        networkManagerWrapper.getClass();
        Object objFromJson = new Gson().fromJson(NetworkManagerWrapperKt.a(NetworkManagerWrapper.u("https://editor-api.capcutapi.com/feedback/image/v1/upload/", "image", bArr, strValueOf, map)), (Class<Object>) UploadPicResult.class);
        Intrinsics.checkNotNullExpressionValue(objFromJson, "");
        return (UploadPicResult) objFromJson;
    }
}
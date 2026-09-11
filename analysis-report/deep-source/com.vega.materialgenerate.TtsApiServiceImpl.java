package com.vega.materialgenerate;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.lemon.lv.data.SentenceData;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes23.dex */
public final class TtsApiServiceImpl implements ITtsApiService {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TtsApiServiceManager f115000a = TtsApiServiceManager.f115001a;

    @Override // com.vega.materialgenerate.ITtsApiService
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/ad_maker/intelligent/cut_sentence")
    public Call<Response<SentenceData>> generateSentence(@Body TypedJson typedJson) {
        Intrinsics.checkNotNullParameter(typedJson, "");
        return this.f115000a.generateSentence(typedJson);
    }
}
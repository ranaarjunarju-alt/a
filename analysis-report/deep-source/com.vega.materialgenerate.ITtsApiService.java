package com.vega.materialgenerate;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.lemon.lv.data.SentenceData;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes4.dex */
public interface ITtsApiService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/ad_maker/intelligent/cut_sentence")
    Call<Response<SentenceData>> generateSentence(@Body TypedJson typedJson);
}
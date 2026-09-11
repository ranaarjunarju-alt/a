package com.vega.image.edit.speech.impl;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes30.dex */
public interface SpeechRecognizeApiService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v2/intelligence/asr/query")
    Call<Response<AudioQueryResponse>> queryAudioText(@Body TypedJson typedJson);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v2/intelligence/asr/submit")
    Call<Response<AsrSubmitTosResp>> submitVid(@Body TypedJson typedJson);
}
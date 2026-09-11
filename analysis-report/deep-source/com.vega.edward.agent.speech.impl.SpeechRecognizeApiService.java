package com.vega.edward.agent.speech.impl;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes28.dex */
public interface SpeechRecognizeApiService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/agent_gen/query_v2t")
    Call<Response<AudioQueryResponse>> queryAudioText(@Body TypedJson typedJson);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/agent_gen/submit_v2t")
    Call<Response<AsrSubmitTosResp>> submitTosId(@Body TypedJson typedJson);
}
package com.vega.audio.tone.tts.engine.ssml;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes18.dex */
public interface SSMLApiService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/text_to_speech/batch_transform_new")
    Call<Response<SSMLAudioTaskRequestData>> audioTaskRequest(@Query("babi_param") String str, @Body TypedJson typedJson);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/text_to_speech/batch_transform_query")
    Call<Response<SSMLAudioTaskResultData>> audioTaskResult(@Body TypedJson typedJson);
}
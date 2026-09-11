package com.vega.audio.tone.tts.engine.elevenlabs;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes20.dex */
public interface ElevenLabsApiService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/text_to_speech/new")
    Call<Response<ElevenLabsAudioTaskRequestData>> audioTaskRequest(@Body TypedJson typedJson);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/text_to_speech/query")
    Call<Response<ElevenLabsAudioTaskResultData>> audioTaskResult(@Body TypedJson typedJson);
}
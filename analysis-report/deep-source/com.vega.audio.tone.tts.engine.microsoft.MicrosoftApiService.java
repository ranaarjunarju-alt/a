package com.vega.audio.tone.tts.engine.microsoft;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.bytedance.retrofit2.http.Query;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes13.dex */
public interface MicrosoftApiService {

    public static final class DefaultImpls {
    }

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/text_to_speech/new")
    Call<Response<AudioTaskRequestData>> audioTaskRequest(@Body TypedJson typedJson, @Query("babi_param") String str);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/text_to_speech/query")
    Call<Response<AudioTaskResultData>> audioTaskResult(@Body TypedJson typedJson);
}
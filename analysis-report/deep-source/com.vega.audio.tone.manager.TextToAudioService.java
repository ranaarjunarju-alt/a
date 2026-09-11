package com.vega.audio.tone.manager;

import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.lemon.lv.data.SignPublicKeyData;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;
import io.reactivex.Observable;

/* loaded from: classes11.dex */
public interface TextToAudioService {
    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/encrypt/get_public_key")
    Observable<Response<SignPublicKeyData>> getPublicKey(@Body TypedJson typedJson);

    @Headers({"Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/get_sami_token")
    Observable<Response<SamiTokenBean>> getSamiToken(@Body TypedJson typedJson);
}
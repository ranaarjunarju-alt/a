package com.vega.adeditor.audio.tonecompare;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.vega.adeditor.audio.tonecompare.data.RiskVoiceCheckRequest;
import com.vega.adeditor.audio.tonecompare.data.RiskVoiceCheckResponse;
import com.vega.adeditor.audio.tonecompare.data.SimilarityParam;
import com.vega.adeditor.audio.tonecompare.data.SimilarityResult;
import com.vega.adeditor.audio.tonecompare.data.SimilaritySidResult;
import com.vega.adeditor.audio.tonecompare.data.UserSidList;
import com.vega.core.net.Response;
import com.vega.core.net.TypedJson;

/* loaded from: classes10.dex */
public interface ICompareMaterialApiService {
    @Headers({"CONNECT_TIMEOUT:3600000", "READ_TIMEOUT:3600000", "WRITE_TIMEOUT:3600000", "Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/check_voiceprint")
    Call<Response<RiskVoiceCheckResponse>> checkRiskVoice(@Body RiskVoiceCheckRequest riskVoiceCheckRequest);

    @Headers({"CONNECT_TIMEOUT:3600000", "READ_TIMEOUT:3600000", "WRITE_TIMEOUT:3600000", "Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/compare_material_similarity")
    Call<Response<SimilarityResult>> compareSimilarity(@Body SimilarityParam similarityParam);

    @Headers({"CONNECT_TIMEOUT:3600000", "READ_TIMEOUT:3600000", "WRITE_TIMEOUT:3600000", "Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/compare_material_similarity")
    Call<Response<SimilaritySidResult>> compareSimilarityForSid(@Body SimilarityParam similarityParam);

    @Headers({"CONNECT_TIMEOUT:3600000", "READ_TIMEOUT:3600000", "WRITE_TIMEOUT:3600000", "Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/get_user_sid_list")
    Call<Response<UserSidList>> getUserSidList();

    @Headers({"CONNECT_TIMEOUT:3600000", "READ_TIMEOUT:3600000", "WRITE_TIMEOUT:3600000", "Content-Type:application/json; charset=utf-8"})
    @POST("/lv/v1/update_user_sid_list")
    Call<Response<String>> updateUserSidList(@Body TypedJson typedJson);
}
package com.vega.edit.base.clonetone.apis;

import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.retrofit2.http.Headers;
import com.bytedance.retrofit2.http.POST;
import com.vega.core.net.Response;
import com.vega.edit.base.clonetone.apis.data.GetPlayInfoParam;
import com.vega.edit.base.clonetone.apis.data.GetPlayInfoResult;

/* loaded from: classes34.dex */
public interface UtilApiService {
    @Headers({"CONNECT_TIMEOUT:3600000", "READ_TIMEOUT:3600000", "WRITE_TIMEOUT:3600000", "Content-Type:application/json; charset=utf-8"})
    @POST("/lv/edit/get_play_info")
    Call<Response<GetPlayInfoResult>> getPlayInfo(@Body GetPlayInfoParam getPlayInfoParam);
}
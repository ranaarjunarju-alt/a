package com.vega.materialgenerate;

import com.bytedance.frameworks.baselib.network.http.retrofit.converter.gson.GsonConverterFactory;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.CallAdapter;
import com.bytedance.retrofit2.client.Client;
import com.bytedance.retrofit2.http.Body;
import com.bytedance.ttnet.utils.RetrofitUtils;
import com.lemon.lv.data.SentenceData;
import com.vega.core.net.Response;
import com.vega.core.net.TimeoutInterceptor;
import com.vega.core.net.TypedJson;
import java.util.ArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes30.dex */
public final class TtsApiServiceManager implements ITtsApiService {

    /* renamed from: a, reason: collision with root package name */
    public static final TtsApiServiceManager f115001a = new TtsApiServiceManager();
    public static final ITtsApiService b;

    static {
        TtsApiServiceFactory.f114999a.getClass();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new TimeoutInterceptor());
        Object objCreateService = RetrofitUtils.createService(RetrofitUtils.createRetrofit(TtsApiServiceFactory.b, arrayList, GsonConverterFactory.create(), (CallAdapter.Factory) null, (Client.Provider) null), ITtsApiService.class);
        Intrinsics.checkNotNullExpressionValue(objCreateService, "");
        b = (ITtsApiService) objCreateService;
    }

    @Override // com.vega.materialgenerate.ITtsApiService
    public Call<Response<SentenceData>> generateSentence(@Body TypedJson typedJson) {
        Intrinsics.checkNotNullParameter(typedJson, "");
        return b.generateSentence(typedJson);
    }
}
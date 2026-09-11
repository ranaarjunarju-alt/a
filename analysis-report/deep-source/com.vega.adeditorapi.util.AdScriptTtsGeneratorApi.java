package com.vega.adeditorapi.util;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import kotlin.coroutines.Continuation;

/* loaded from: classes15.dex */
public interface AdScriptTtsGeneratorApi {
    MutableLiveData<TtsResult> a();

    void b();

    void c(List<String> list);

    void clearData();

    Object d(List<String> list, Continuation<? super TtsResult> continuation);
}
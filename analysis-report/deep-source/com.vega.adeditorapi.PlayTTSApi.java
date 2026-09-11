package com.vega.adeditorapi;

import androidx.fragment.app.FragmentActivity;
import com.lemon.lv.data.ToneType;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* loaded from: classes14.dex */
public interface PlayTTSApi {
    void a(FragmentActivity fragmentActivity, Function1<? super List<ToneType>, Unit> function1);

    void b();

    void c(FragmentActivity fragmentActivity, String str, int i, String str2, String str3, float f, String str4, Function1<? super Boolean, Unit> function1);
}
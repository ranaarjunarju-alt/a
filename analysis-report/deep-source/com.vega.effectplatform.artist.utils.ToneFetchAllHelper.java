package com.vega.effectplatform.artist.utils;

import kotlin.coroutines.Continuation;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes17.dex */
public final class ToneFetchAllHelper {

    /* renamed from: a, reason: collision with root package name */
    public static final ToneFetchAllHelper f98473a = new ToneFetchAllHelper();

    public static Object a(boolean z, Continuation continuation) {
        return BuildersKt__Builders_commonKt.withContext(Dispatchers.getIO(), new ToneFetchAllHelper$fetchToneEffectList$2(z, null), continuation);
    }
}
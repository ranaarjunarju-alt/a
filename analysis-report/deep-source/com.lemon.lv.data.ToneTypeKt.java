package com.lemon.lv.data;

import com.vega.core.context.SPIService;
import com.vega.libeffectapi.settings.IEffectSettings;
import kotlin.jvm.internal.Reflection;

/* loaded from: classes25.dex */
public final class ToneTypeKt {
    public static final String a() {
        String strA = ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).A();
        return strA.length() == 0 ? "You’re using the text to speech feature. Choose a voice you like." : strA;
    }
}
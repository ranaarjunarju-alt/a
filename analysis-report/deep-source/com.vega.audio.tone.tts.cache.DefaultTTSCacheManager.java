package com.vega.audio.tone.tts.cache;

import com.bytedance.security.android.aopcheck.PolarisFileWrapper;
import com.vega.core.context.SPIService;
import com.vega.core.utils.DirectoryUtil;
import com.vega.diskcache.DiskLruCacheWrapper;
import com.vega.infrastructure.util.FileUtil;
import com.vega.libeffectapi.settings.IEffectSettings;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.ranges.RangesKt___RangesKt;

/* loaded from: classes27.dex */
public final class DefaultTTSCacheManager {

    /* renamed from: a, reason: collision with root package name */
    public static final DefaultTTSCacheManager f74288a = new DefaultTTSCacheManager();
    public static final DiskLruCacheWrapper b;

    static {
        DirectoryUtil.f79563a.getClass();
        PolarisFileWrapper polarisFileWrapper = new PolarisFileWrapper(DirectoryUtil.D("default_tts_cache"));
        String absolutePath = polarisFileWrapper.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "");
        FileUtil.f106629a.getClass();
        FileUtil.e(absolutePath);
        long j = 1024;
        b = new DiskLruCacheWrapper(polarisFileWrapper, RangesKt___RangesKt.coerceAtLeast(10485760L, ((IEffectSettings) SPIService.INSTANCE.getImpl(Reflection.getOrCreateKotlinClass(IEffectSettings.class), null)).i() * j * j));
    }
}
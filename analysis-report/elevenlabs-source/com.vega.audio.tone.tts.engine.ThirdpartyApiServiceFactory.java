package com.vega.audio.tone.tts.engine;

import com.vega.core.utils.DirectoryUtil;

/* loaded from: classes11.dex */
public final class ThirdpartyApiServiceFactory {

    /* renamed from: a, reason: collision with root package name */
    public static final ThirdpartyApiServiceFactory f74370a = new ThirdpartyApiServiceFactory();
    public static final String b;

    static {
        StringBuilder sb = new StringBuilder();
        DirectoryUtil.f79563a.getClass();
        sb.append(DirectoryUtil.D("downloadAudio"));
        sb.append("speechAudio");
        b = sb.toString();
    }
}
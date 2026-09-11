package com.vega.audio.tone.manager;

import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.jvm.functions.Function0;

/* loaded from: classes8.dex */
public final class TextToAudioServiceHelper {

    /* renamed from: a, reason: collision with root package name */
    public static final TextToAudioServiceHelper f74165a = new TextToAudioServiceHelper();
    public static final Lazy b = LazyKt__LazyJVMKt.lazy(new Function0<TextToAudioService>() { // from class: com.vega.audio.tone.manager.TextToAudioServiceHelper$API_SERVICE$2
        /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
        @Override // kotlin.jvm.functions.Function0
        public final TextToAudioService invoke() {
            new TextToAudioServiceFactory();
            return TextToAudioServiceFactory.a();
        }
    });
}
package com.vega.audio.tone.tts.engine;

import android.media.MediaPlayer;
import android.media.PlaybackParams;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes5.dex */
public final class ThirdPartyPlayer {
    public static MediaPlayer b;

    /* renamed from: a, reason: collision with root package name */
    public static final ThirdPartyPlayer f74363a = new ThirdPartyPlayer();

    /* renamed from: c, reason: collision with root package name */
    public static final AtomicBoolean f74364c = new AtomicBoolean(false);

    public static void a(ThirdPartyPlayer thirdPartyPlayer, String str, float f, Function0 function0, MediaPlayer.OnCompletionListener onCompletionListener, int i) {
        if ((i & 4) != 0) {
            f = 1.0f;
        }
        if ((i & 8) != 0) {
            function0 = null;
        }
        if ((i & 16) != 0) {
            onCompletionListener = null;
        }
        synchronized (thirdPartyPlayer) {
            Intrinsics.checkNotNullParameter(str, "");
            try {
                if (b == null) {
                    b = new MediaPlayer();
                }
                MediaPlayer mediaPlayer = b;
                if (mediaPlayer != null) {
                    if (mediaPlayer.isPlaying()) {
                        f74363a.b();
                    }
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                    AtomicBoolean atomicBoolean = f74364c;
                    if (atomicBoolean.get()) {
                        mediaPlayer.reset();
                    }
                    mediaPlayer.setDataSource(str);
                    mediaPlayer.prepare();
                    mediaPlayer.setLooping(false);
                    PlaybackParams playbackParams = mediaPlayer.getPlaybackParams();
                    Intrinsics.checkNotNullExpressionValue(playbackParams, "");
                    playbackParams.setSpeed(f);
                    mediaPlayer.setPlaybackParams(playbackParams);
                    atomicBoolean.set(true);
                    mediaPlayer.start();
                    if (function0 != null) {
                        function0.invoke();
                    }
                }
            } catch (Throwable th) {
                th.toString();
                MediaPlayer mediaPlayer2 = b;
                if (mediaPlayer2 != null) {
                    mediaPlayer2.release();
                    b = null;
                    f74364c.set(false);
                }
                if (onCompletionListener != null) {
                    onCompletionListener.onCompletion(null);
                }
            }
        }
    }

    public final synchronized void b() {
        MediaPlayer mediaPlayer = b;
        if (mediaPlayer == null) {
            return;
        }
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
        }
        mediaPlayer.setOnCompletionListener(null);
    }
}
package com.vega.audio.tone.tts.core.executor;

import com.lemon.lv.data.TextToAudioInfo;
import com.vega.aigcapi.materialgenerate.TtsResult;
import com.vega.audio.tone.tts.core.TextToSpeechTask;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;

/* loaded from: classes24.dex */
public abstract class BaseTextToSpeechExecutor implements ITextToSpeechExecutor {

    /* renamed from: a, reason: collision with root package name */
    public final AtomicBoolean f74309a = new AtomicBoolean(false);
    public final TtsResult.RequestScene b = TtsResult.RequestScene.b;

    /* loaded from: classes33.dex */
    public static final class Companion {
    }

    static {
        new Companion();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0076  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ff  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x01ac  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x01bf  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.lang.Object k(com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor r22, com.vega.audio.tone.tts.core.TextToSpeechTask r23, kotlin.coroutines.Continuation<? super kotlin.Unit> r24) {
        /*
            r3 = r24
            r6 = r22
            r5 = r23
            boolean r0 = r3 instanceof com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$1
            if (r0 == 0) goto L17d
            r4 = r3
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$1 r4 = (com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$1) r4
            int r2 = r4.u
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L17d
            int r2 = r2 - r1
            r4.u = r2
        L18:
            java.lang.Object r9 = r4.s
            java.lang.Object r3 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.u
            r7 = 0
            r10 = 3
            r8 = 2
            java.lang.String r2 = "TextToSpeech_BaseTextToSpeechExecutor"
            r1 = 1
            if (r0 == 0) goto L34
            if (r0 == r1) goto L18c
            if (r0 == r8) goto L1b9
            if (r0 != r10) goto L184
            kotlin.ResultKt.throwOnFailure(r9)
        L31:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L34:
            kotlin.ResultKt.throwOnFailure(r9)
            com.vega.audio.tone.tts.core.TextToSpeechListener r12 = r5.l
            if (r12 == 0) goto L44
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r11 = r5.e
            com.vega.aigcapi.materialgenerate.EventType r9 = com.vega.aigcapi.materialgenerate.EventType.f69147a
            r0 = 12
            com.vega.audio.tone.tts.core.TextToSpeechListener.DefaultImpls.b(r12, r11, r9, r7, r0)
        L44:
            boolean r9 = r5.t
            r14 = 0
            java.lang.String r0 = ""
            if (r9 == 0) goto L83
            com.vega.audio.tone.tts.cache.DefaultTTSCacheManager r0 = com.vega.audio.tone.tts.cache.DefaultTTSCacheManager.f74288a
            r0.getClass()
            java.lang.String r0 = "getTextToAudioInfoCache"
            java.lang.String r12 = "DefaultTTSCacheManager"
            com.vega.log.BLog.i(r12, r0)
            com.vega.diskcache.StringKey r13 = new com.vega.diskcache.StringKey
            java.lang.String r11 = r5.a()
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r0 = "meta_"
            r9.<init>(r0)
            r9.append(r11)
            java.lang.String r0 = r9.toString()
            r13.<init>(r0)
            com.vega.diskcache.DiskLruCacheWrapper r0 = com.vega.audio.tone.tts.cache.DefaultTTSCacheManager.b
            java.io.File r0 = r0.b(r13)
            if (r0 != 0) goto L101
        L76:
            r11 = r7
        L77:
            if (r11 == 0) goto Lff
            java.util.List<java.lang.String> r0 = r11.f59067a
            if (r0 == 0) goto Lff
            java.lang.Object r0 = kotlin.collections.CollectionsKt___CollectionsKt.first(r0)
            java.lang.String r0 = (java.lang.String) r0
        L83:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            java.lang.String r9 = "readingAudio useCache"
            r11.<init>(r9)
            boolean r9 = r5.t
            r11.append(r9)
            java.lang.String r9 = ",executorType:"
            r11.append(r9)
            com.vega.audio.tone.tts.core.TextToSpeechExecutorType r9 = r5.e
            r11.append(r9)
            java.lang.String r9 = ",audioPathCache:"
            r11.append(r9)
            r11.append(r0)
            java.lang.String r9 = ",text:"
            r11.append(r9)
            java.lang.String r9 = r5.b
            r11.append(r9)
            java.lang.String r9 = r11.toString()
            com.vega.log.BLog.i(r2, r9)
            if (r0 == 0) goto Lba
            int r9 = r0.length()
            if (r9 != 0) goto Lbb
        Lba:
            r14 = 1
        Lbb:
            if (r14 != 0) goto L16f
            java.lang.String r9 = "readingAudio hit cache"
            com.vega.log.BLog.i(r2, r9)
            com.vega.aigcapi.materialgenerate.StatusResult r10 = com.vega.aigcapi.materialgenerate.StatusResult.b
            com.vega.aigcapi.materialgenerate.TtsResult$RequestScene r17 = r6.g()
            com.vega.aigcapi.materialgenerate.TtsResult r9 = new com.vega.aigcapi.materialgenerate.TtsResult
            r11 = 0
            r12 = 0
            r14 = 0
            r24 = 7070(0x1b9e, float:9.907E-42)
            r13 = r12
            r18 = r12
            r19 = r12
            r20 = r11
            r21 = r1
            r22 = r12
            r23 = r12
            r16 = r1
            r9.<init>(r10, r11, r12, r13, r14, r16, r17, r18, r19, r20, r21, r22, r23, r24)
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$playStatus$1 r13 = new com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$playStatus$1
            r14 = r5
            r15 = r6
            r16 = r9
            r17 = r0
            r18 = r12
            r13.<init>(r14, r15, r16, r17, r18)
            r4.q = r6
            r4.r = r5
            r4.u = r1
            r0 = 60000(0xea60, double:2.9644E-319)
            java.lang.Object r9 = kotlinx.coroutines.TimeoutKt.withTimeout(r0, r13, r4)
            if (r9 != r3) goto L193
            return r3
        Lff:
            r0 = r7
            goto L83
        L101:
            java.lang.String r11 = X.C31M.c(r0)     // Catch: java.lang.Throwable -> L115
            com.google.gson.Gson r9 = com.vega.core.ext.ExtentionKt.getGson()     // Catch: java.lang.Throwable -> L115
            java.lang.Class<com.lemon.lv.data.TextToAudioInfo> r0 = com.lemon.lv.data.TextToAudioInfo.class
            java.lang.Object r11 = r9.fromJson(r11, r0)     // Catch: java.lang.Throwable -> L115
            com.lemon.lv.data.TextToAudioInfo r11 = (com.lemon.lv.data.TextToAudioInfo) r11     // Catch: java.lang.Throwable -> L115
            kotlin.Result.m17090constructorimpl(r11)     // Catch: java.lang.Throwable -> L115
            goto L11d
        L115:
            r0 = move-exception
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r0)
            kotlin.Result.m17090constructorimpl(r11)
        L11d:
            boolean r0 = kotlin.Result.m17096isFailureimpl(r11)
            if (r0 == 0) goto L124
            r11 = r7
        L124:
            com.lemon.lv.data.TextToAudioInfo r11 = (com.lemon.lv.data.TextToAudioInfo) r11
            if (r11 == 0) goto L76
            java.util.List<java.lang.String> r0 = r11.f59067a
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L132
            goto L76
        L132:
            java.lang.String r0 = "has TextToAudioInfo cache"
            com.vega.log.BLog.i(r12, r0)
            java.util.List<java.lang.String> r9 = r11.f59067a
            boolean r0 = r9 instanceof java.util.Collection
            if (r0 == 0) goto L150
            r0 = r9
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L150
        L146:
            r0 = 0
        L147:
            if (r0 == 0) goto L77
            java.lang.String r0 = "file has not exit,so return null"
            com.vega.log.BLog.i(r12, r0)
            goto L76
        L150:
            java.util.Iterator r13 = r9.iterator()
        L154:
            boolean r0 = r13.hasNext()
            if (r0 == 0) goto L146
            java.lang.Object r9 = r13.next()
            java.lang.String r9 = (java.lang.String) r9
            com.vega.infrastructure.util.FileUtil r0 = com.vega.infrastructure.util.FileUtil.f106629a
            r0.getClass()
            boolean r0 = com.vega.infrastructure.util.FileUtil.d(r9)
            r0 = r0 ^ 1
            if (r0 == 0) goto L154
            r0 = 1
            goto L147
        L16f:
            java.lang.String r0 = "readingAudio not hit cache"
            com.vega.log.BLog.i(r2, r0)
            r4.u = r10
            java.lang.Object r0 = r6.i(r5, r4)
            if (r0 != r3) goto L31
            return r3
        L17d:
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$1 r4 = new com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readingAudio$1
            r4.<init>(r6, r3)
            goto L18
        L184:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        L18c:
            com.vega.audio.tone.tts.core.TextToSpeechTask r5 = r4.r
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r9)
        L193:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r0 = "cache play playStatus:"
            r1.<init>(r0)
            r1.append(r9)
            java.lang.String r0 = r1.toString()
            com.vega.log.BLog.i(r2, r0)
            if (r9 != 0) goto L1bf
            r4.q = r7
            r4.r = r7
            r4.u = r8
            java.lang.Object r0 = r6.i(r5, r4)
            if (r0 != r3) goto L1bc
            return r3
        L1b9:
            kotlin.ResultKt.throwOnFailure(r9)
        L1bc:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L1bf:
            r6.h()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor.k(com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor, com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final boolean b() {
        return this.f74309a.compareAndSet(false, true);
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final boolean c() {
        return this.f74309a.get();
    }

    public TtsResult.RequestScene g() {
        return this.b;
    }

    public void h() {
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object i(final com.vega.audio.tone.tts.core.TextToSpeechTask r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1
            if (r0 == 0) goto L3e
            r4 = r7
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1 r4 = (com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1) r4
            int r2 = r4.t
            r1 = -2147483648(0xffffffff80000000, float:-0.0)
            r0 = r2 & r1
            if (r0 == 0) goto L3e
            int r2 = r2 - r1
            r4.t = r2
        L12:
            java.lang.Object r3 = r4.r
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r4.t
            r1 = 1
            if (r0 == 0) goto L2b
            if (r0 != r1) goto L47
            com.vega.audio.tone.tts.core.TextToSpeechTask r6 = r4.q
            kotlin.ResultKt.throwOnFailure(r3)
        L24:
            boolean r0 = r6.t
            if (r0 != 0) goto L44
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L2b:
            kotlin.ResultKt.throwOnFailure(r3)
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$2 r0 = new com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$2
            r0.<init>()
            r4.q = r6
            r4.t = r1
            java.lang.Object r0 = r5.j(r6, r0, r4)
            if (r0 != r2) goto L24
            return r2
        L3e:
            com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1 r4 = new com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor$readAudioForNotCache$1
            r4.<init>(r5, r7)
            goto L12
        L44:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L47:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vega.audio.tone.tts.core.executor.BaseTextToSpeechExecutor.i(com.vega.audio.tone.tts.core.TextToSpeechTask, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object j(TextToSpeechTask textToSpeechTask, Function1<? super Pair<TextToAudioInfo, ? extends TtsResult>, Unit> function1, Continuation<? super Unit> continuation) {
        return Unit.INSTANCE;
    }

    @Override // com.vega.audio.tone.tts.core.executor.ITextToSpeechExecutor
    public final void release() {
        this.f74309a.set(false);
    }
}
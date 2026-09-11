package com.vega.audio.tone.tts.engine.server;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* loaded from: classes35.dex */
public final class RemoteSAMIRequestAudioInternal {

    @SerializedName("disable_newline_strategy")
    public final boolean disableNewlineStrategy;

    @SerializedName("flush_sentence")
    public final boolean flushSentence;

    @SerializedName("max_paragraph_phoneme_size")
    public final int maxParagraphPhonemeSize;

    @SerializedName("phoneme_size")
    public final int phonemeSize;

    /* JADX DEBUG: Multi-variable search result rejected for r1v1, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: java.lang.Object[] */
    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    public RemoteSAMIRequestAudioInternal() {
        this(false, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 0 == true ? 1 : 0, 15, null);
    }

    public RemoteSAMIRequestAudioInternal(boolean z, int i, int i2, boolean z2) {
        this.flushSentence = z;
        this.phonemeSize = i;
        this.maxParagraphPhonemeSize = i2;
        this.disableNewlineStrategy = z2;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0014: CONSTRUCTOR 
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x0000: ARITH (r6v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? false : (r2v0 boolean))
      (wrap:int:?: TERNARY null = ((wrap:int:0x0005: ARITH (r6v0 int) & (2 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r3v0 int))
      (wrap:int:?: TERNARY null = ((wrap:int:0x000a: ARITH (r6v0 int) & (4 int) A[WRAPPED]) != (0 int)) ? (0 int) : (r4v0 int))
      (wrap:boolean:?: TERNARY null = ((wrap:int:0x000f: ARITH (r6v0 int) & (8 int) A[WRAPPED]) != (0 int)) ? false : (r5v0 boolean))
     A[MD:(boolean, int, int, boolean):void (m)] call: com.vega.audio.tone.tts.engine.server.RemoteSAMIRequestAudioInternal.<init>(boolean, int, int, boolean):void type: THIS */
    public /* synthetic */ RemoteSAMIRequestAudioInternal(boolean z, int i, int i2, boolean z2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? false : z, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 0 : i2, (i3 & 8) != 0 ? false : z2);
    }

    public static /* synthetic */ RemoteSAMIRequestAudioInternal copy$default(RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal, boolean z, int i, int i2, boolean z2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = remoteSAMIRequestAudioInternal.flushSentence;
        }
        if ((i3 & 2) != 0) {
            i = remoteSAMIRequestAudioInternal.phonemeSize;
        }
        if ((i3 & 4) != 0) {
            i2 = remoteSAMIRequestAudioInternal.maxParagraphPhonemeSize;
        }
        if ((i3 & 8) != 0) {
            z2 = remoteSAMIRequestAudioInternal.disableNewlineStrategy;
        }
        return remoteSAMIRequestAudioInternal.copy(z, i, i2, z2);
    }

    public final RemoteSAMIRequestAudioInternal copy(boolean z, int i, int i2, boolean z2) {
        return new RemoteSAMIRequestAudioInternal(z, i, i2, z2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RemoteSAMIRequestAudioInternal)) {
            return false;
        }
        RemoteSAMIRequestAudioInternal remoteSAMIRequestAudioInternal = (RemoteSAMIRequestAudioInternal) obj;
        return this.flushSentence == remoteSAMIRequestAudioInternal.flushSentence && this.phonemeSize == remoteSAMIRequestAudioInternal.phonemeSize && this.maxParagraphPhonemeSize == remoteSAMIRequestAudioInternal.maxParagraphPhonemeSize && this.disableNewlineStrategy == remoteSAMIRequestAudioInternal.disableNewlineStrategy;
    }

    public final boolean getDisableNewlineStrategy() {
        return this.disableNewlineStrategy;
    }

    public final boolean getFlushSentence() {
        return this.flushSentence;
    }

    public final int getMaxParagraphPhonemeSize() {
        return this.maxParagraphPhonemeSize;
    }

    public final int getPhonemeSize() {
        return this.phonemeSize;
    }

    public int hashCode() {
        return ((((((this.flushSentence ? 1231 : 1237) * 31) + this.phonemeSize) * 31) + this.maxParagraphPhonemeSize) * 31) + (this.disableNewlineStrategy ? 1231 : 1237);
    }

    public String toString() {
        return "RemoteSAMIRequestAudioInternal(flushSentence=" + this.flushSentence + ", phonemeSize=" + this.phonemeSize + ", maxParagraphPhonemeSize=" + this.maxParagraphPhonemeSize + ", disableNewlineStrategy=" + this.disableNewlineStrategy + ')';
    }
}
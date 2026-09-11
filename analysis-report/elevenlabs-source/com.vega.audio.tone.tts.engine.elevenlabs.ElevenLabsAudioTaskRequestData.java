package com.vega.audio.tone.tts.engine.elevenlabs;

import com.google.gson.annotations.SerializedName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class ElevenLabsAudioTaskRequestData {

    @SerializedName("task_id")
    public final String taskId;

    @SerializedName("text_lan")
    public final String textLan;

    /* JADX WARN: Multi-variable type inference failed */
    public ElevenLabsAudioTaskRequestData() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ElevenLabsAudioTaskRequestData(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        this.taskId = str;
        this.textLan = str2;
    }

    public /* synthetic */ ElevenLabsAudioTaskRequestData(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2);
    }

    public final String a() {
        return this.taskId;
    }

    public final String b() {
        return this.textLan;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ElevenLabsAudioTaskRequestData)) {
            return false;
        }
        ElevenLabsAudioTaskRequestData elevenLabsAudioTaskRequestData = (ElevenLabsAudioTaskRequestData) obj;
        return Intrinsics.areEqual(this.taskId, elevenLabsAudioTaskRequestData.taskId) && Intrinsics.areEqual(this.textLan, elevenLabsAudioTaskRequestData.textLan);
    }

    public final int hashCode() {
        return (this.taskId.hashCode() * 31) + this.textLan.hashCode();
    }

    public final String toString() {
        return "ElevenLabsAudioTaskRequestData(taskId=" + this.taskId + ", textLan=" + this.textLan + ')';
    }
}
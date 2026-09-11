package com.lemon.lv.data;

import com.vungle.ads.internal.protos.Sdk;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes34.dex */
public final class TextToAudioInfo {

    /* renamed from: a, reason: collision with root package name */
    public final List<String> f59067a;
    public final List<String> b;

    /* renamed from: c, reason: collision with root package name */
    public final List<String> f59068c;

    /* renamed from: d, reason: collision with root package name */
    public final List<String> f59069d;
    public final List<String> e;
    public final List<String> f;

    /* renamed from: g, reason: collision with root package name */
    public final List<String> f59070g;

    public TextToAudioInfo() {
        this(null, null, null, null, null, Sdk.SDKError.Reason.ASSET_FAILED_MAX_SPACE_EXCEEDED_VALUE);
    }

    public TextToAudioInfo(List list, List list2, List list3, List list4, List list5, int i) {
        list = (i & 1) != 0 ? new CopyOnWriteArrayList() : list;
        list2 = (i & 2) != 0 ? new ArrayList() : list2;
        list3 = (i & 4) != 0 ? new ArrayList() : list3;
        list4 = (i & 8) != 0 ? new ArrayList() : list4;
        list5 = (i & 16) != 0 ? new ArrayList() : list5;
        ArrayList arrayList = (i & 32) != 0 ? new ArrayList() : null;
        ArrayList arrayList2 = (i & 64) != 0 ? new ArrayList() : null;
        Intrinsics.checkNotNullParameter(list, "");
        Intrinsics.checkNotNullParameter(list2, "");
        Intrinsics.checkNotNullParameter(list3, "");
        Intrinsics.checkNotNullParameter(list4, "");
        Intrinsics.checkNotNullParameter(list5, "");
        Intrinsics.checkNotNullParameter(arrayList, "");
        Intrinsics.checkNotNullParameter(arrayList2, "");
        this.f59067a = list;
        this.b = list2;
        this.f59068c = list3;
        this.f59069d = list4;
        this.e = list5;
        this.f = arrayList;
        this.f59070g = arrayList2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TextToAudioInfo)) {
            return false;
        }
        TextToAudioInfo textToAudioInfo = (TextToAudioInfo) obj;
        return Intrinsics.areEqual(this.f59067a, textToAudioInfo.f59067a) && Intrinsics.areEqual(this.b, textToAudioInfo.b) && Intrinsics.areEqual(this.f59068c, textToAudioInfo.f59068c) && Intrinsics.areEqual(this.f59069d, textToAudioInfo.f59069d) && Intrinsics.areEqual(this.e, textToAudioInfo.e) && Intrinsics.areEqual(this.f, textToAudioInfo.f) && Intrinsics.areEqual(this.f59070g, textToAudioInfo.f59070g);
    }

    public final int hashCode() {
        return (((((((((((this.f59067a.hashCode() * 31) + this.b.hashCode()) * 31) + this.f59068c.hashCode()) * 31) + this.f59069d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.f59070g.hashCode();
    }

    public final String toString() {
        return "TextToAudioInfo(filePaths=" + this.f59067a + ", texts=" + this.b + ", words=" + this.f59068c + ", phonemes=" + this.f59069d + ", taskIds=" + this.e + ", textLans=" + this.f + ", speakerIds=" + this.f59070g + ')';
    }
}
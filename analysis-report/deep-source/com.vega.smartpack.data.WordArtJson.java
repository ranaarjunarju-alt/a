package com.vega.smartpack.data;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes4.dex */
public final class WordArtJson {

    @SerializedName("material_list")
    public final List<SmartPackMaterial> materialList;

    @SerializedName("utterance_list")
    public final List<Caption> utteranceList;

    public WordArtJson(List<Caption> list, List<SmartPackMaterial> list2) {
        Intrinsics.checkNotNullParameter(list, "");
        this.utteranceList = list;
        this.materialList = list2;
    }

    /* JADX DEBUG: Can't inline method, not implemented redirect type for insn: 0x0008: CONSTRUCTOR 
      (wrap:java.util.List:?: TERNARY null = ((wrap:int:0x0000: ARITH (r4v0 int) & (1 int) A[WRAPPED]) != (0 int)) ? (wrap:java.util.List:0x0004: INVOKE  STATIC call: kotlin.collections.CollectionsKt__CollectionsKt.emptyList():java.util.List A[MD:<T>:():java.util.List<T> (m), WRAPPED]) : (r2v0 java.util.List))
      (r3v0 java.util.List)
     A[MD:(java.util.List<com.vega.smartpack.data.Caption>, java.util.List<com.vega.smartpack.data.SmartPackMaterial>):void (m)] call: com.vega.smartpack.data.WordArtJson.<init>(java.util.List, java.util.List):void type: THIS */
    public /* synthetic */ WordArtJson(List list, List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list, list2);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: com.vega.smartpack.data.WordArtJson */
    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ WordArtJson copy$default(WordArtJson wordArtJson, List list, List list2, int i, Object obj) {
        if ((i & 1) != 0) {
            list = wordArtJson.utteranceList;
        }
        if ((i & 2) != 0) {
            list2 = wordArtJson.materialList;
        }
        return wordArtJson.copy(list, list2);
    }

    public final WordArtJson copy(List<Caption> list, List<SmartPackMaterial> list2) {
        Intrinsics.checkNotNullParameter(list, "");
        return new WordArtJson(list, list2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WordArtJson)) {
            return false;
        }
        WordArtJson wordArtJson = (WordArtJson) obj;
        return Intrinsics.areEqual(this.utteranceList, wordArtJson.utteranceList) && Intrinsics.areEqual(this.materialList, wordArtJson.materialList);
    }

    public final List<SmartPackMaterial> getMaterialList() {
        return this.materialList;
    }

    public final List<Caption> getUtteranceList() {
        return this.utteranceList;
    }

    public int hashCode() {
        int iHashCode = this.utteranceList.hashCode() * 31;
        List<SmartPackMaterial> list = this.materialList;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }

    public String toString() {
        return "WordArtJson(utteranceList=" + this.utteranceList + ", materialList=" + this.materialList + ')';
    }
}
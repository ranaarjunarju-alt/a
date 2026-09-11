package com.vega.edit.base.tone;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import ttpobfuscated.b3;

/* loaded from: classes22.dex */
public interface TextInfo {

    /* loaded from: classes25.dex */
    public static final class AutoSegText implements TextInfo {

        /* renamed from: a, reason: collision with root package name */
        public final String f88860a;
        public final int b;

        /* renamed from: c, reason: collision with root package name */
        public final boolean f88861c;

        public AutoSegText(String str) {
            Intrinsics.checkNotNullParameter(str, "");
            this.f88860a = str;
            this.b = 1;
            this.f88861c = true;
        }

        @Override // com.vega.edit.base.tone.TextInfo
        public final int a() {
            return this.b;
        }

        @Override // com.vega.edit.base.tone.TextInfo
        public final boolean b() {
            return this.f88861c;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof AutoSegText) && Intrinsics.areEqual(this.f88860a, ((AutoSegText) obj).f88860a);
        }

        @Override // com.vega.edit.base.tone.TextInfo
        public final String getText() {
            return this.f88860a;
        }

        public final int hashCode() {
            return this.f88860a.hashCode();
        }

        public final String toString() {
            return "AutoSegText(text=" + this.f88860a + ')';
        }
    }

    /* loaded from: classes17.dex */
    public static final class NoSegTextList implements TextInfo {

        /* renamed from: a, reason: collision with root package name */
        public final List<String> f88862a;
        public final Lazy b;

        public NoSegTextList(List<String> list) {
            Intrinsics.checkNotNullParameter(list, "");
            this.f88862a = list;
            this.b = LazyKt__LazyJVMKt.lazy(new Function0<List<? extends String>>() { // from class: com.vega.edit.base.tone.TextInfo$NoSegTextList$filterEmptyTexts$2
                {
                    super(0);
                }

                /* JADX DEBUG: Return type fixed from 'java.lang.Object' to match base method */
                @Override // kotlin.jvm.functions.Function0
                public final List<? extends String> invoke() {
                    List<? extends String> list2;
                    ArrayList arrayList = new ArrayList();
                    Iterator<T> it = this.e.f88862a.iterator();
                    while (it.hasNext()) {
                        arrayList.add(StringsKt__StringsJVMKt.replace$default((String) it.next(), "\n", b3.f151437d, false, 4, (Object) null));
                    }
                    ArrayList arrayList2 = new ArrayList();
                    Iterator it2 = arrayList.iterator();
                    while (it2.hasNext()) {
                        Object next = it2.next();
                        if (!StringsKt__StringsKt.isBlank((String) next)) {
                            arrayList2.add(next);
                        }
                    }
                    return (!(arrayList2.isEmpty() ^ true) || (list2 = CollectionsKt___CollectionsKt.toList(arrayList2)) == null) ? CollectionsKt__CollectionsKt.emptyList() : list2;
                }
            });
        }

        @Override // com.vega.edit.base.tone.TextInfo
        public final int a() {
            return ((List) this.b.getValue()).size();
        }

        @Override // com.vega.edit.base.tone.TextInfo
        public final boolean b() {
            return false;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof NoSegTextList) && Intrinsics.areEqual(this.f88862a, ((NoSegTextList) obj).f88862a);
        }

        @Override // com.vega.edit.base.tone.TextInfo
        public final String getText() {
            return CollectionsKt___CollectionsKt.joinToString$default((List) this.b.getValue(), "\n", null, null, 0, null, null, 62, null);
        }

        public final int hashCode() {
            return this.f88862a.hashCode();
        }

        public final String toString() {
            return "NoSegTextList(texts=" + this.f88862a + ')';
        }
    }

    int a();

    boolean b();

    String getText();
}
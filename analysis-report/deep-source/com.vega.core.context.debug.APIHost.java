package com.vega.core.context.debug;

import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes10.dex */
public final class APIHost {

    /* renamed from: a, reason: collision with root package name */
    public final String f79178a;
    public final String b;

    /* renamed from: c, reason: collision with root package name */
    public final String f79179c;

    /* renamed from: d, reason: collision with root package name */
    public final String f79180d;
    public final String e;
    public final String f;

    /* renamed from: g, reason: collision with root package name */
    public final String f79181g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;

    public APIHost(String str, String str2, String str3) {
        Intrinsics.checkNotNullParameter(str, "");
        Intrinsics.checkNotNullParameter(str2, "");
        Intrinsics.checkNotNullParameter(str3, "");
        this.f79178a = str;
        this.b = str2;
        this.f79179c = str3;
        this.f79180d = "feed-api.capcutapi.com";
        this.e = "";
        this.f = "commerce-api.capcutapi.com";
        this.f79181g = "passport-api.capcutapi.com";
        this.h = "editor-api.capcutapi.com";
        this.i = "";
        this.j = "";
        this.k = "";
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof APIHost)) {
            return false;
        }
        APIHost aPIHost = (APIHost) obj;
        return Intrinsics.areEqual(this.f79178a, aPIHost.f79178a) && Intrinsics.areEqual(this.b, aPIHost.b) && Intrinsics.areEqual(this.f79179c, aPIHost.f79179c) && Intrinsics.areEqual(this.f79180d, aPIHost.f79180d) && Intrinsics.areEqual(this.e, aPIHost.e) && Intrinsics.areEqual(this.f, aPIHost.f) && Intrinsics.areEqual(this.f79181g, aPIHost.f79181g) && Intrinsics.areEqual(this.h, aPIHost.h) && Intrinsics.areEqual(this.i, aPIHost.i) && Intrinsics.areEqual(this.j, aPIHost.j) && Intrinsics.areEqual(this.k, aPIHost.k);
    }

    public final int hashCode() {
        return (((((((((((((((((((this.f79178a.hashCode() * 31) + this.b.hashCode()) * 31) + this.f79179c.hashCode()) * 31) + this.f79180d.hashCode()) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.f79181g.hashCode()) * 31) + this.h.hashCode()) * 31) + this.i.hashCode()) * 31) + this.j.hashCode()) * 31) + this.k.hashCode();
    }

    public final String toString() {
        return "APIHost(api=" + this.f79178a + ", et=" + this.b + ", boe=" + this.f79179c + ", community=" + this.f79180d + ", upload=" + this.e + ", pay=" + this.f + ", login=" + this.f79181g + ", settings=" + this.h + ", vod=" + this.i + ", vodBOE=" + this.j + ", ug=" + this.k + ')';
    }
}
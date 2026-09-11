package com.lm.components.network.extra;

import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.client.Request;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes12.dex */
public final class NetworkRuntimeConfig {

    /* renamed from: a, reason: collision with root package name */
    public static final NetworkRuntimeConfig f60924a = new NetworkRuntimeConfig();
    public static final HashSet<String> b = new HashSet<>();

    public static boolean a(Request request) {
        Intrinsics.checkNotNullParameter(request, "");
        List<Header> headers = request.getHeaders();
        Intrinsics.checkNotNullExpressionValue(headers, "");
        if (!(headers instanceof Collection) || !headers.isEmpty()) {
            Iterator<T> it = headers.iterator();
            while (it.hasNext()) {
                if (Intrinsics.areEqual(((Header) it.next()).getName(), "x-tt-web-proxy")) {
                    return true;
                }
            }
        }
        return b.contains(request.getHost());
    }

    public final synchronized void b(String str) {
        if (str != null) {
            b.remove(str);
        }
    }
}
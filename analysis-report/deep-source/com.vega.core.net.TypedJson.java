package com.vega.core.net;

import com.bytedance.retrofit2.mime.TypedByteArray;
import com.google.gson.Gson;
import com.vega.log.BLog;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes24.dex */
public final class TypedJson extends TypedByteArray {
    public static final Companion b = new Companion();

    /* renamed from: a, reason: collision with root package name */
    public final String f79367a;

    /* loaded from: classes29.dex */
    public static final class Companion {
        public static TypedJson a() {
            return new TypedJson("{}");
        }

        public static TypedJson b(Object obj) {
            Object objCreateFailure;
            Intrinsics.checkNotNullParameter(obj, "");
            try {
                String json = new Gson().toJson(obj);
                Intrinsics.checkNotNullExpressionValue(json, "");
                objCreateFailure = new TypedJson(json);
                Result.m17090constructorimpl(objCreateFailure);
            } catch (Throwable th) {
                objCreateFailure = ResultKt.createFailure(th);
                Result.m17090constructorimpl(objCreateFailure);
            }
            Throwable thM17093exceptionOrNullimpl = Result.m17093exceptionOrNullimpl(objCreateFailure);
            if (thM17093exceptionOrNullimpl != null) {
                BLog.e("TypedJson", "fromObject " + thM17093exceptionOrNullimpl.getMessage());
                TypedJson.b.getClass();
                objCreateFailure = a();
            }
            return (TypedJson) objCreateFailure;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TypedJson(String str) {
        Intrinsics.checkNotNullParameter(str, "");
        b.getClass();
        try {
            Charset charsetForName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "");
            byte[] bytes = str.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "");
            super("application/json; charset=UTF-8", bytes, new String[0]);
            this.f79367a = str;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // com.bytedance.retrofit2.mime.TypedByteArray
    public final String toString() {
        try {
            byte[] bytes = getBytes();
            Intrinsics.checkNotNullExpressionValue(bytes, "");
            Charset charsetForName = Charset.forName("UTF-8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "");
            return new String(bytes, charsetForName);
        } catch (Throwable th) {
            BLog.e("TypedJson", "toString " + th.getMessage());
            throw new AssertionError("Must be able to decode UTF-8");
        }
    }
}
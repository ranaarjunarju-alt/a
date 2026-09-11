package com.vega.audio.tone.tts.engine.nonstreaming.clipflow.nodes;

import X.C06W;
import android.util.Base64;
import com.applovin.mediation.MaxErrorCodes;
import com.vega.clipflow.ClipflowAsyncNode;
import com.vega.clipflow.ClipflowNodeError;
import com.vega.clipflow.NodeResult;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;

/* loaded from: classes9.dex */
public final class SignTextWithRSANode extends ClipflowAsyncNode<Input, Output> {
    public final String s;

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* loaded from: classes10.dex */
    public static final class Error implements ClipflowNodeError {

        /* renamed from: c, reason: collision with root package name */
        public static final Error f74409c;

        /* renamed from: d, reason: collision with root package name */
        public static final Error f74410d;
        public static final /* synthetic */ Error[] e;

        /* renamed from: a, reason: collision with root package name */
        public final int f74411a;
        public final String b;

        static {
            Error error = new Error("SIGN_REQUEST_FAIL", 0, MaxErrorCodes.MEDIATION_ADAPTER_LOAD_FAILED, "get public key fail");
            f74409c = error;
            Error error2 = new Error("SIGN_ENCRYPT_FAIL", 1, -5002, "sign encrypt fail");
            f74410d = error2;
            Error[] errorArr = {error, error2};
            e = errorArr;
            C06W.a(errorArr);
        }

        public Error(String str, int i, int i2, String str2) {
            this.f74411a = i2;
            this.b = str2;
        }

        public static Error valueOf(String str) {
            return (Error) Enum.valueOf(Error.class, str);
        }

        public static Error[] values() {
            return (Error[]) e.clone();
        }

        @Override // com.vega.clipflow.ClipflowNodeError
        public final int getCode() {
            return this.f74411a;
        }

        @Override // com.vega.clipflow.ClipflowNodeError
        public final String getMsg() {
            return this.b;
        }

        @Override // com.vega.clipflow.ClipflowNodeError
        public final <Output> NodeResult<Output> toResult(HashMap<String, String> map) {
            return ClipflowNodeError.DefaultImpls.a(this, map);
        }

        @Override // com.vega.clipflow.ClipflowNodeError
        public final <Output> NodeResult<Output> toResultAppendMessage(String str, HashMap<String, String> map) {
            return ClipflowNodeError.DefaultImpls.c(this, str, map);
        }

        @Override // com.vega.clipflow.ClipflowNodeError
        public final <Output> NodeResult<Output> toResultWithMessage(String str, HashMap<String, String> map) {
            return ClipflowNodeError.DefaultImpls.e(this, str, map);
        }
    }

    /* loaded from: classes16.dex */
    public static final class Input {

        /* renamed from: a, reason: collision with root package name */
        public final String f74412a;

        public Input(String str) {
            Intrinsics.checkNotNullParameter(str, "");
            this.f74412a = str;
        }
    }

    /* loaded from: classes20.dex */
    public static final class Output {

        /* renamed from: a, reason: collision with root package name */
        public final String f74413a;

        public Output(String str) {
            Intrinsics.checkNotNullParameter(str, "");
            this.f74413a = str;
        }
    }

    /* loaded from: classes15.dex */
    public static final class PemBlock {

        /* renamed from: a, reason: collision with root package name */
        public final String f74414a;
        public final byte[] b;

        public PemBlock(String str, byte[] bArr) {
            Intrinsics.checkNotNullParameter(str, "");
            Intrinsics.checkNotNullParameter(bArr, "");
            this.f74414a = str;
            this.b = bArr;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof PemBlock)) {
                return false;
            }
            PemBlock pemBlock = (PemBlock) obj;
            return Intrinsics.areEqual(this.f74414a, pemBlock.f74414a) && Intrinsics.areEqual(this.b, pemBlock.b);
        }

        public final int hashCode() {
            return (this.f74414a.hashCode() * 31) + Arrays.hashCode(this.b);
        }

        public final String toString() {
            return "PemBlock(type=" + this.f74414a + ", bytes=" + Arrays.toString(this.b) + ')';
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SignTextWithRSANode(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "");
        this.s = "SignRequestNode";
    }

    public static String J(String str, byte[] bArr) throws InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Intrinsics.checkNotNullExpressionValue(keyFactory, "");
        byte[] bArr2 = null;
        MatchResult matchResultFind$default = Regex.find$default(new Regex("-----BEGIN (.*)-----([^-]*)-----END \\1-----"), str, 0, 2, null);
        if (matchResultFind$default != null) {
            String str2 = matchResultFind$default.getGroupValues().get(1);
            byte[] bArrDecode = Base64.decode(StringsKt__StringsJVMKt.replace$default(matchResultFind$default.getGroupValues().get(2), "\n", "", false, 4, (Object) null), 0);
            Intrinsics.checkNotNull(bArrDecode);
            bArr2 = new PemBlock(str2, bArrDecode).b;
        }
        PublicKey publicKeyGeneratePublic = keyFactory.generatePublic(new X509EncodedKeySpec(bArr2));
        Intrinsics.checkNotNullExpressionValue(publicKeyGeneratePublic, "");
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, publicKeyGeneratePublic);
        byte[] bArrEncode = Base64.encode(cipher.doFinal(bArr), 0);
        Intrinsics.checkNotNullExpressionValue(bArrEncode, "");
        return new String(bArrEncode, Charsets.UTF_8);
    }

    /* JADX DEBUG: Method merged with bridge method: H(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object; */
    @Override // com.vega.clipflow.ClipflowAsyncNode
    /* renamed from: K, reason: merged with bridge method [inline-methods] */
    public final Object H(Input input, Continuation<? super NodeResult<Output>> continuation) {
        return BuildersKt__Builders_commonKt.withContext(Dispatchers.getIO(), new SignTextWithRSANode$runAsync$2(input, this, null), continuation);
    }

    @Override // com.vega.clipflow.ClipflowNode
    public final String l() {
        return this.s;
    }
}
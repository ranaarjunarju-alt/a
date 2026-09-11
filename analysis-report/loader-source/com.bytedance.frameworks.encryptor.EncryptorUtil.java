package com.bytedance.frameworks.encryptor;

import com.GlobalProxyLancet;

/* loaded from: classes40.dex */
public class EncryptorUtil {
    static {
        try {
            GlobalProxyLancet.com_vega_launcher_lancet_SoLoadLancet_loadLibrary("Encryptor");
        } catch (UnsatisfiedLinkError e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(byte[] bArr, int i) {
        if (bArr != null && i > 0) {
            try {
                if (bArr.length == i) {
                    return ttEncrypt(bArr, i);
                }
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    public static byte[] encryptFixedLength(byte[] bArr, int i) {
        byte[] bArrTtEncrypt = null;
        if (bArr != null && i > 0) {
            try {
                bArrTtEncrypt = ttEncrypt(bArr, i);
                return bArrTtEncrypt;
            } catch (Throwable unused) {
            }
        }
        return bArrTtEncrypt;
    }

    public static native byte[] ttEncrypt(byte[] bArr, int i);
}
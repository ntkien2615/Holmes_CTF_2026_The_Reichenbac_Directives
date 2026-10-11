package com.a.b.a;

import java.security.MessageDigest;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class at {
    private static final byte[] a = new byte[16];

    public static byte[] a(byte[] bArr, String str) {
        byte[] bArr2 = new byte[16];
        System.arraycopy(MessageDigest.getInstance(aq.a("TIB.367", 1)).digest(str.getBytes(aq.a("]\\N5@", 8))), 0, bArr2, 0, 16);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, aq.a("FJX", 5));
        Cipher cipher = Cipher.getInstance(aq.a("FJX4HGH4UPHX:Ufiinsl", 5));
        cipher.init(2, secretKeySpec, new IvParameterSpec(a));
        return cipher.doFinal(bArr);
    }
}

package com.a.b.a;

import android.text.TextUtils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class as {
    public static final byte[] a = {103, -25, -127, -83, 75, -20, -23, -4, -43, -57, -37, -110, -30, 47, 3, 125};
    public static char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', ':', '/', '+', '=', '.'};

    public static byte[] a(byte[] bArr, byte[] bArr2, String str) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length % 16;
        byte[] bArr3 = new byte[(bArr.length + 16) - length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        if (length != 0) {
            for (int length2 = bArr.length; length2 < (bArr.length + 16) - length; length2++) {
                bArr3[length2] = " ".getBytes()[0];
            }
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, aq.a("KO]", 10));
            Cipher cipher = Cipher.getInstance(str);
            if (TextUtils.isEmpty(null)) {
                cipher.init(2, secretKeySpec);
            } else {
                String str2 = null;
                cipher.init(2, secretKeySpec, new IvParameterSpec(str2.getBytes(aq.a("vug.9", 1))));
            }
            return cipher.doFinal(bArr3);
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, aq.a("KO]", 10));
            Cipher cipher = Cipher.getInstance(str);
            if (TextUtils.isEmpty(str2)) {
                cipher.init(2, secretKeySpec);
            } else {
                cipher.init(2, secretKeySpec, new IvParameterSpec(str2.getBytes(aq.a("{zl3>", 6))));
            }
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }
}

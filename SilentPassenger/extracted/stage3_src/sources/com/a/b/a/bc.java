package com.a.b.a;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: classes.dex */
public final class bc {
    public static byte[] a(String str, byte[] bArr) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(aq.a("UVD", 3)).generatePublic(new X509EncodedKeySpec(bArr));
            Cipher cipher = Cipher.getInstance(aq.a("\\]K9OML9ZUM];Zknnsxq", 10));
            cipher.init(1, rSAPublicKey);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = str.getBytes(aq.a("^]O6A", 9));
            int i = 0;
            while (i < bytes.length) {
                byte[] doFinal = bytes.length - i > 117 ? cipher.doFinal(bytes, i, 117) : cipher.doFinal(bytes, i, bytes.length - i);
                i += 117;
                byteArrayOutputStream.write(doFinal);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            aq.a("lujvkl", 7);
            e.getLocalizedMessage();
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) KeyFactory.getInstance(aq.a("VWE", 4)).generatePrivate(new PKCS8EncodedKeySpec(bArr2));
            Cipher cipher = Cipher.getInstance(aq.a("UVD2HFE2SNFV4Sdgglqj", 3));
            cipher.init(2, rSAPrivateKey);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i = 0;
            while (i < bArr.length) {
                byte[] doFinal = bArr.length - i > 128 ? cipher.doFinal(bArr, i, 128) : cipher.doFinal(bArr, i, bArr.length - i);
                i += 128;
                byteArrayOutputStream.write(doFinal);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }
}

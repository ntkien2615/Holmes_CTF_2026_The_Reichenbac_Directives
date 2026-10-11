package com.c.j;

import java.io.ByteArrayOutputStream;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class s {
    public static byte[] a(String str, byte[] bArr) {
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(a.a("YZH", 7)).generatePublic(new X509EncodedKeySpec(bArr));
            Cipher cipher = Cipher.getInstance(a.a("STB0FDC0QLDT2Qbeejoh", 1));
            cipher.init(1, rSAPublicKey);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = str.getBytes(a.a("YXJ1<", 4));
            int i = 0;
            while (i < bytes.length) {
                byte[] doFinal = bytes.length - i > 117 ? cipher.doFinal(bytes, i, 117) : cipher.doFinal(bytes, i, bytes.length - i);
                i += 117;
                byteArrayOutputStream.write(doFinal);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            return null;
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        try {
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) KeyFactory.getInstance(a.a("WXF", 5)).generatePrivate(new PKCS8EncodedKeySpec(bArr2));
            Cipher cipher = Cipher.getInstance(a.a("YZH6LJI6WRJZ8Whkkpun", 7));
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
            e.printStackTrace();
            return null;
        }
    }
}

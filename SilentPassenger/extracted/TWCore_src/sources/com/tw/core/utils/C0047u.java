package com.tw.core.utils;

import android.util.Base64;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.tw.core.utils.u, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0047u {
    private static PublicKey eS;

    private static PublicKey iz() {
        return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(ix("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC2geKn1rer7H4Xjyl8lAGACer+w4NrHBhzNbH2wVLh9Ghmb6Z3wK2CVS7jLvMkCzwLTzpI08foJtG2UObvPwL3w+a5zGUFJayaf7BV+ZYnuUswyrxfMwy753q+da+AnVdkW9ldjeUTomuGUA2+x8wBxlfS8ZRzqlzFgCTwqu4K9wIDAQAB")));
    }

    public static String iv(String str) {
        try {
            if (eS == null) {
                eS = iz();
            }
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
            cipher.init(1, eS);
            cipher.update(str.getBytes("UTF-8"));
            String iy = iy(cipher.doFinal());
            C0034h.hr("RsaUtil", "源数据：%s", str);
            C0034h.hr("RsaUtil", "公钥加密后的数据：%s", iy);
            return iy;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String iw(String str) {
        try {
            if (eS == null) {
                eS = iz();
            }
            Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
            cipher.init(2, eS);
            cipher.update(ix(str));
            String str2 = new String(cipher.doFinal(), "UTF-8");
            C0034h.hr("RsaUtil", "公钥解密后的数据：%s", str2);
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String iy(byte[] bArr) {
        return new String(Base64.encode(bArr, 2));
    }

    private static byte[] ix(String str) {
        return Base64.decode(str, 2);
    }
}

package com.tw.core.utils;

import android.text.TextUtils;
import android.util.Base64;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class N {
    public static String km(String str, String str2) {
        try {
            if (!TextUtils.isEmpty(str2)) {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                byte[] bytes = str.getBytes("utf-8");
                cipher.init(1, secretKeySpec, ko());
                return new String(Base64.encode(cipher.doFinal(bytes), 2));
            }
            return null;
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            C0038l.hE("AES加密报错:{}", e.getMessage());
            return null;
        }
    }

    public static String kn(String str, String str2) {
        byte[] decode = Base64.decode(str, 2);
        try {
            if (!TextUtils.isEmpty(str2)) {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("utf-8"), "AES");
                Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
                cipher.init(2, secretKeySpec, ko());
                return new String(cipher.doFinal(decode), "utf-8");
            }
            return null;
        } catch (UnsupportedEncodingException | InvalidAlgorithmParameterException | InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            C0038l.hE("AES解密报错:{}", e.getMessage());
            return null;
        }
    }

    private static IvParameterSpec ko() {
        return new IvParameterSpec("dofunsaascbciv16".getBytes());
    }

    public static String kl() {
        String str;
        String le = com.tw.core.e.b.le();
        if (le != null) {
            str = le.substring(le.length() - 16);
        } else {
            str = System.currentTimeMillis() + "000";
        }
        C0034h.hr("rdc %s", str, new Object[0]);
        return str;
    }
}

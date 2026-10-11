package com.a.b.a;

import android.text.TextUtils;
import android.util.Base64;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* loaded from: classes.dex */
public final class av {
    private static final String a = aq.a("xwi0;", 3);

    public static String a(String str, String str2, String str3, String str4) {
        if (str2 == null || str2.length() < 8) {
            throw new RuntimeException(aq.a("劤寊夵贩０oi}丑脁尓互<佑", 4));
        }
        if (str == null) {
            return null;
        }
        try {
            Key a2 = a(str2, str3);
            Cipher cipher = Cipher.getInstance(str3);
            if (TextUtils.isEmpty(str4)) {
                cipher.init(2, a2);
            } else {
                cipher.init(1, a2, new IvParameterSpec(str4.getBytes(a)));
            }
            str = new String(Base64.encode(cipher.doFinal(str.getBytes(a)), 0));
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    private static Key a(String str, String str2) {
        return SecretKeyFactory.getInstance(str2).generateSecret(new DESKeySpec(str.getBytes(a)));
    }

    public static String b(String str, String str2, String str3, String str4) {
        if (str == null || str.length() < 8) {
            throw new RuntimeException(aq.a("劥寋夶贪１pj~丒脂尔亓=佒", 5));
        }
        if (str2 == null) {
            return null;
        }
        try {
            Key a2 = a(str, str3);
            Cipher cipher = Cipher.getInstance(str3);
            if (TextUtils.isEmpty(str4)) {
                cipher.init(2, a2);
            } else {
                cipher.init(1, a2, new IvParameterSpec(str4.getBytes(a)));
            }
            str2 = new String(cipher.doFinal(Base64.decode(str2.getBytes(a), 0)), a);
            return str2;
        } catch (Exception e) {
            e.printStackTrace();
            return str2;
        }
    }
}

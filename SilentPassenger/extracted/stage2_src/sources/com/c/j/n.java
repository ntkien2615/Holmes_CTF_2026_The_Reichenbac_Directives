package com.c.j;

import java.util.Random;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class n {
    private static final String a = a.a("Rzc8=^ijt788Zt|i", 5);

    public static String a(String str, String str2) {
        StringBuilder append = new StringBuilder().append(str).append(a.a("5gvo|85", 6));
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        int nextInt = new Random().nextInt(90000) + 10000;
        return append.append(a(b(a(b(str2, String.valueOf(nextInt))) + a.a("~", 2) + currentTimeMillis + a.a("\u0082", 6) + nextInt, a))).toString();
    }

    private static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format(a.a(")46|", 4), Integer.valueOf(b & 255)));
        }
        return sb.toString();
    }

    private static byte[] b(String str, String str2) {
        byte[] bArr = new byte[str.length()];
        int length = str2.length();
        for (int i = 0; i < str.length(); i++) {
            bArr[i] = (byte) (((byte) str.charAt(i)) ^ ((byte) str2.charAt(i % length)));
        }
        return bArr;
    }
}

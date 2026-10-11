package com.tw.core.utils;

/* loaded from: classes.dex */
public class I {
    public static final char[] fr = "0123456789ABCDEF".toCharArray();
    private static char[] fq = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] jU(String str) {
        if (str == null || "".equals(str)) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        char[] charArray = str.toCharArray();
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (jW(charArray[i2 + 1]) | (jW(charArray[i2]) << 4));
        }
        return bArr;
    }

    public static int jV(byte[] bArr, int i) {
        if (i == 1) {
            return bArr[0] & 255;
        }
        if (i == 2) {
            return ((bArr[0] & 255) << 8) | (bArr[1] & 255);
        }
        if (i == 4) {
            return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | (bArr[3] & 255);
        }
        if (i == 6) {
            return ((bArr[0] & 255) << 8) | ((bArr[0] & 255) << 0) | ((bArr[2] & 255) << 24) | ((bArr[3] & 255) << 16) | ((bArr[4] & 255) << 8) | (bArr[5] & 255);
        }
        if (i == 8) {
            return ((bArr[0] & 255) << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8) | ((bArr[3] & 255) << 0) | ((bArr[4] & 255) << 24) | ((bArr[5] & 255) << 16) | ((bArr[6] & 255) << 8) | (bArr[7] & 255);
        }
        return -1;
    }

    public static byte jW(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }
}

package com.x.zg;

/* loaded from: classes.dex */
public final class a {
    public static String a(String str, int i) {
        char[] charArray = str.toCharArray();
        for (int i2 = 0; i2 < charArray.length; i2++) {
            charArray[i2] = (char) (charArray[i2] - i);
        }
        return new String(charArray);
    }
}

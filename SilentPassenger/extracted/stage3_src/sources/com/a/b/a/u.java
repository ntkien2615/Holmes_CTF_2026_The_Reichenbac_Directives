package com.a.b.a;

import java.util.Random;

/* loaded from: classes.dex */
public final class u {
    public static final String[] a = {aq.a(":<666", 6), aq.a("<>88:8", 8), aq.a("8:4465", 4), aq.a(":<6688", 6), aq.a(":<6689", 6), aq.a("682247", 2), aq.a("<>88:?", 8), aq.a("571139", 1), aq.a("57113:", 1), aq.a("7933::", 3), aq.a("<>88?@", 8), aq.a("8:44;=", 4)};
    private Random b = new Random();

    private String a(int i) {
        String str = "";
        for (int i2 = 0; i2 < i; i2++) {
            str = str + Math.abs(this.b.nextInt() % 10);
        }
        return str;
    }

    public final String a() {
        String a2 = a(14);
        char[] charArray = a2.toCharArray();
        int i = 0;
        int i2 = 0;
        while (i < charArray.length) {
            int parseInt = Integer.parseInt(String.valueOf(charArray[i]));
            int i3 = i + 1;
            int parseInt2 = Integer.parseInt(String.valueOf(charArray[i3])) * 2;
            if (parseInt2 >= 10) {
                parseInt2 -= 9;
            }
            i2 += parseInt2 + parseInt;
            i = i3 + 1;
        }
        int i4 = i2 % 10;
        return a2 + (i4 != 0 ? 10 - i4 : 0);
    }

    public final String b() {
        String str = a[Math.abs(this.b.nextInt()) % a.length];
        return str + a((14 - str.length()) + 1);
    }
}

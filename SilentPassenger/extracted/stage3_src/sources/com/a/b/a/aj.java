package com.a.b.a;

import java.util.Random;

/* loaded from: classes.dex */
public final class aj {
    public static int a(int i) {
        if (i < 0) {
            return 0;
        }
        return new Random().nextInt(i);
    }

    public static int a(int i, int i2, int i3) {
        return (int) (((i * 1.0f) / i2) * i3);
    }
}

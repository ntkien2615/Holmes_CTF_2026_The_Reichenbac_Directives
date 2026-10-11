package com.a.b.a;

/* loaded from: classes.dex */
public final class k {
    private static k b;
    public int a;

    private k() {
    }

    public static synchronized k a() {
        k kVar;
        synchronized (k.class) {
            if (b == null) {
                b = new k();
            }
            kVar = b;
        }
        return kVar;
    }
}

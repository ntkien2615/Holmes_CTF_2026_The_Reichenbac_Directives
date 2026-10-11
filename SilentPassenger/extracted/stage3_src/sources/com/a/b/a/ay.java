package com.a.b.a;

/* loaded from: classes.dex */
final class ay extends Enum<ay> {
    public static final int a;
    public static final int b;
    public static final int c;
    public static final int d;
    public static final int e;
    private static final /* synthetic */ int[] f;

    static {
        new ay(aq.a("LFCBC", 1));
        a = 1;
        new ay(aq.a("ZUHRL", 7));
        b = 2;
        new ay(aq.a("ECOGN", 2));
        c = 3;
        new ay(aq.a("TEWGEP", 4));
        d = 4;
        new ay(aq.a("UQFNS", 5));
        e = 5;
        f = new int[]{a, b, c, d, e};
    }

    private ay(String str) {
        super(str, 0);
    }

    public static int[] a() {
        return (int[]) f.clone();
    }
}

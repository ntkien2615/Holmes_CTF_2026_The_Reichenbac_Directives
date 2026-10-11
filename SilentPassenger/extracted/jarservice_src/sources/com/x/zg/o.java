package com.x.zg;

/* loaded from: classes.dex */
public final class o {
    private Object a;

    public o(String str, String str2, int i) {
        try {
            Class<?> cls = Class.forName(a(a.a("|onkyV}}kvM\u0082oN8wo~}\u0083}8us\u0080vkn", 9), i));
            Class<?> cls2 = Class.forName(a(a.a("vihesPwwepG2krep2ezen", 3), i));
            Class<?> cls3 = Class.forName(a(a.a("ipktvU0ipcn0cxcl", 1), i));
            this.a = cls.getConstructor(cls3, cls3, cls3, cls2).newInstance(str, str2, null, a(cls2, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object a(Class cls, int i) {
        try {
            return cls.getMethod(a(a.a("uhgdrOvvdoFphwv|Vwhj", 2), i), new Class[0]).invoke(null, new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    public static String a(String str, int i) {
        char[] charArray = new StringBuffer(str).reverse().toString().toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : charArray) {
            stringBuffer.append((char) (c - i));
        }
        return stringBuffer.toString();
    }

    public final Class<?> a(String str) {
        return (Class) this.a.getClass().getMethod(a.a("vyknMvk}}", 10), String.class).invoke(this.a, str);
    }
}

package com.c.j;

import java.lang.reflect.Constructor;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public class l {
    private Object a;

    public l(String str, String str2, int i) {
        try {
            Class<?> cls = Class.forName(a(a.a("}polzW~~lwN\u0083pO9xp\u007f~\u0084~9vt\u0081wlo", 10), i));
            Class<?> cls2 = Class.forName(a(a.a("tgfcqNuucnE0ipcn0cxcl", 1), i));
            Class<?> cls3 = Class.forName(a(a.a("nupy{Z5nuhs5h}hq", 6), i));
            Constructor<?> constructor = cls.getConstructor(cls3, cls3, cls3, cls2);
            Object[] objArr = new Object[4];
            objArr[0] = str;
            objArr[1] = str2;
            objArr[2] = null;
            ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
            objArr[3] = contextClassLoader == null ? l.class.getClassLoader() : contextClassLoader;
            this.a = constructor.newInstance(objArr);
        } catch (Exception e) {
            e.printStackTrace();
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
        if (this.a == null) {
            throw new IllegalStateException(a.a("Ij}HqfxxQtfijw%nsny%kfnqji", 5));
        }
        return (Class) this.a.getClass().getMethod(a.a("uxjmLuj||", 9), String.class).invoke(this.a, str);
    }
}

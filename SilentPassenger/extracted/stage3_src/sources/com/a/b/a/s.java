package com.a.b.a;

import android.content.Context;
import android.os.Build;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

/* loaded from: classes.dex */
public final class s {
    private static final String c = aq.a("qxxt`nk_rn\u0080", 9);
    public static Random a = new Random();
    public static char[] b = aq.a("56789:;<=>FGHIJKLMNOPQRSTUVWXYZ[\\]^_fghijklmnopqrstuvwxyz{|}~\u007f", 5).toCharArray();

    public static int a(int i) {
        return a.nextInt(i);
    }

    public static String a(String str, String str2, String str3) {
        return b(str, str2, str3);
    }

    public static void a() {
        if (Build.VERSION.SDK_INT < 28) {
            return;
        }
        try {
            Class<?> cls = Class.forName(aq.a("jwm{xrm7jyy7Jl}r\u007fr}\u0082]q{njm", 9));
            Method declaredMethod = cls.getDeclaredMethod(aq.a("gyvvirxEgxmzmx}Xlvieh", 4), new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField(aq.a("uPqllmvIxq_izvqvo[pw\u007fv", 8));
            declaredField.setAccessible(true);
            declaredField.setBoolean(invoke, true);
        } catch (Exception e) {
        }
    }

    public static void a(Context context) {
        Method declaredMethod;
        Object invoke;
        int i = Build.VERSION.SDK_INT;
        try {
            if ((context.getPackageManager().getPackageInfo(context.getPackageName(), 16384).applicationInfo.flags & 1) == 0) {
                return;
            }
            Class<?> cls = Class.forName(aq.a("jwm{xrm7\u0080nktr}7`nk_rn\u0080Ojl}x{\u0082", 9));
            Field declaredField = cls.getDeclaredField(aq.a("vSurylghuLqvwdqfh", 3));
            declaredField.setAccessible(true);
            Object obj = declaredField.get(null);
            if (obj != null) {
                aq.a("zWyv}pklyPuz{hujl'pzu.{'u|ss", 7);
                return;
            }
            if (i > 22) {
                declaredMethod = cls.getDeclaredMethod(aq.a("igvRtqxkfgtEncuu", 2), new Class[0]);
            } else {
                if (i != 22) {
                    aq.a("Epo(u!offe!up!Ippl!XfcWjfx", 1);
                    return;
                }
                declaredMethod = cls.getDeclaredMethod(aq.a("igvHcevqt{Encuu", 2), new Class[0]);
            }
            declaredMethod.setAccessible(true);
            Class cls2 = (Class) declaredMethod.invoke(cls, new Object[0]);
            Class<?> cls3 = Class.forName(aq.a("boespje/xfclju/XfcWjfxEfmfhbuf", 1));
            Constructor<?> declaredConstructor = cls3.getDeclaredConstructor(new Class[0]);
            declaredConstructor.setAccessible(true);
            if (i < 26) {
                Constructor constructor = cls2.getConstructor(cls3);
                if (constructor != null) {
                    constructor.setAccessible(true);
                    invoke = constructor.newInstance(declaredConstructor.newInstance(new Object[0]));
                }
                invoke = obj;
            } else {
                Field declaredField2 = cls.getDeclaredField(aq.a("LQ[XVR^Vh`NK_RN`hOJL]X[bhVN]QXM", 9));
                declaredField2.setAccessible(true);
                String str = (String) declaredField2.get(null);
                if (str == null) {
                    str = aq.a("etgcvg", 2);
                }
                Method method = cls2.getMethod(str, cls3);
                if (method != null) {
                    invoke = method.invoke(null, declaredConstructor.newInstance(new Object[0]));
                }
                invoke = obj;
            }
            if (invoke == null) {
                aq.a("Ippl!gbjmfe\"", 1);
            } else {
                declaredField.set(aq.a("}Z|y\u0080sno|Sx}~kxmo", 10), invoke);
                aq.a("Pwws({}kkm{{)", 8);
            }
        } catch (Throwable th) {
            th.getLocalizedMessage();
        }
    }

    public static void a(String str) {
        new Thread(new t(str)).start();
    }

    public static String b(int i) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < i; i2++) {
            stringBuffer.append(String.valueOf(b[a.nextInt(15) + 1]));
        }
        return stringBuffer.toString();
    }

    private static String b(String str, String str2, String str3) {
        try {
            int indexOf = str.indexOf(str2, 0) + str2.length();
            int indexOf2 = str.indexOf(str3, indexOf);
            if (indexOf2 == -1) {
                return null;
            }
            return str.substring(indexOf, indexOf2);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean b(Context context) {
        try {
            context.getAssets().open(aq.a("qrgpFGDWI", 2));
            return true;
        } catch (Exception e) {
            return new File(context.getFilesDir(), aq.a("uvktJKH[M", 6)).exists();
        }
    }
}

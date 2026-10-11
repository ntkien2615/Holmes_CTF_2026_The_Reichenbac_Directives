package com.a.b.a;

import android.content.Context;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class am {
    public static Context b;
    public static final String a = aq.a("hiz", 7);
    private static final String k = aq.a("PJ^dMTXY7", 5);
    private static final String l = aq.a("LFZ`IPTU", 1);
    private static final String m = aq.a("zxjwNi", 5);
    private static final String n = aq.a("PJ^dYFXPdFUN", 5);
    private static final String o = aq.a("RL`fYLWVY[fHWP", 7);
    private static final String p = aq.a("jvumpn]lyzpvu", 7);
    public static v c = null;
    public static String d = aq.a("3gtg3etm3xewo", 4);
    public static String e = aq.a("3gtg3etm3tvs|}3svmkmr", 4);
    public static String f = aq.a("6jwj6hwp6kl}pjl", 7);
    public static String g = aq.a("2wdvn2uhsruwWdvnOrfdwlrqSkrqh", 3);
    public static String h = aq.a("7|i{s7|zikmzw}|m", 8);
    public static String i = aq.a("9mzm9kzs9\u0082wv", 10);
    public static String j = aq.a("1crk1kpkvAeqphkiXgtukqp?", 2);
    private static String[] q = null;
    private static String[] r = null;

    public static String a() {
        String c2 = c.c(aq.a("pz", 4));
        StringBuilder append = new StringBuilder().append(aq.a("Qd", 5));
        if (TextUtils.isEmpty(c2)) {
            c2 = aq.a("2/1", 1);
        }
        return append.append(c2).toString();
    }

    public static String a(int i2) {
        if (q == null) {
            return null;
        }
        String[] strArr = q;
        return strArr[i2 % strArr.length];
    }

    public static void a(Context context) {
        try {
            b = context;
            if (c == null) {
                c = new v(b, a);
            }
            q = c.b(k);
            r = c.b(l);
            String c2 = c.c(p);
            if (q == null || r == null || Float.parseFloat(c2) < Float.parseFloat(ap.b)) {
                q = new String[]{aq.a("iuuq;00u2/ytibpo234/tct", 1), aq.a("o{{wA66{85\u007ftzhl5ziz", 7), aq.a("myyu?44y63nxmfst9:;3xgx", 5), aq.a("q}}y|C88}:7r|qjwx=>?7|k|", 9)};
                r = new String[]{aq.a("kwws=22d41{vkdrq4561vev", 3), aq.a("iuuq;00b2/yntbf/tct", 1), aq.a("kwws=22d41lvkdqr7891vev", 3), aq.a("lxxtw>33e52mwlers89:2wfw", 4)};
            } else {
                ap.b = c2;
                d = c.c(n);
            }
        } catch (Exception e2) {
            q = new String[]{aq.a("o{{wA66{85\u007fzohvu89:5ziz", 7), aq.a("jvvr<11v30zoucg0udu", 2), aq.a("jvvr<11v30kujcpq6780udu", 2), aq.a("myyux?44y63nxmfst9:;3xgx", 5)};
            r = new String[]{aq.a("q}}yC88j:7\u0081|qjxw:;<7|k|", 9), aq.a("iuuq;00b2/yntbf/tct", 1), aq.a("myyu?44f63nxmfst9:;3xgx", 5), aq.a("p||x{B77i96q{pivw<=>6{j{", 8)};
        }
    }

    public static void a(String[] strArr, String[] strArr2, String str, String str2) {
        c.a(l);
        q = strArr;
        d = str;
        c.a(k, q);
        c.b(l, strArr2);
        c.a(n, d);
        c.a(p, str2);
        ap.b = str2;
    }

    public static String b() {
        return a(s.a(q.length));
    }

    public static String c() {
        String[] strArr = r;
        return strArr[s.a(strArr.length)];
    }
}

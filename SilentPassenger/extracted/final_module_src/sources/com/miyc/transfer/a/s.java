package com.miyc.transfer.a;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
public final class s {
    private static final Set<String> a = new HashSet();
    private static final Set<String> b = new HashSet();

    static {
        a.add(a.a("9:?686869", 8));
        a.add(a.a("9797979", 9));
        a.add(a.a("ruigrnuyz", 6));
        a.add(a.a("BB9", 8));
        b.add(a.a(";:8", 10));
        b.add(a.a("3940380", 2));
        b.add(a.a("3940390", 2));
        b.add(a.a("283/29/", 1));
        b.add(a.a("283/2:/", 1));
        b.add(a.a("4:51531", 3));
        b.add(a.a("9?:6:96", 8));
        b.add(a.a("6<73773", 5));
        b.add(a.a("7=84894", 6));
        b.add(a.a("5;62682", 4));
        b.add(a.a("8>959<5", 7));
        b.add(a.a("5;626:2", 4));
        b.add(a.a("5;626;2", 4));
        b.add(a.a("9?:6:@6", 8));
        b.add(a.a("283/3:/", 1));
        b.add(a.a("7=84964", 6));
        b.add(a.a("4:51641", 3));
        b.add(a.a(":B;7:?A7", 9));
    }

    public static boolean a(String str) {
        if (str == null || str.trim().isEmpty()) {
            return true;
        }
        String lowerCase = str.trim().toLowerCase();
        if (a.contains(lowerCase)) {
            return true;
        }
        if (lowerCase.equals(a.a("ruigrnuyz", 6)) || lowerCase.endsWith(a.a("3qthfq", 5))) {
            return true;
        }
        if (lowerCase.equals(a.a("6@6@6@6@6@6@6@7", 6)) || lowerCase.equals(a.a("3333=3333=3333=3333=3333=3333=3333=3334", 3))) {
            return true;
        }
        Iterator<String> it = b.iterator();
        while (it.hasNext()) {
            if (lowerCase.startsWith(it.next())) {
                return true;
            }
        }
        return false;
    }
}

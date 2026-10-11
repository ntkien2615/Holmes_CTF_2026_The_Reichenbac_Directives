package com.a.b.a;

import java.util.HashMap;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class br extends bt {
    private String h;
    private String i;
    private String j;
    private String k;
    private JSONArray l;
    private boolean m;
    private static final String o = aq.a("1Fqypnqcf1nkdu", 2);
    private static final HashMap<String, Thread> n = new HashMap<>();

    @Override // com.a.b.a.bt
    public final void a() {
        this.h = (String) e(aq.a("pcog", 2));
        this.i = (String) e(aq.a("wtn", 2));
        this.j = (String) e(aq.a("tk<", 7));
        this.l = (JSONArray) e(aq.a("teveqw", 4));
        this.k = (String) e(aq.a("irgyyTgsk", 6));
        Boolean bool = (Boolean) e(aq.a("zmtwil", 8));
        this.m = bool == null ? false : bool.booleanValue();
    }

    @Override // com.a.b.a.bt
    public final String b() {
        if (!n.containsKey(this.j) || this.m) {
            return aq.a("6", 5);
        }
        aq.a("qtfiqng8", 5);
        new StringBuilder().append(aq.a("nkdu\"", 2)).append(this.j).append(aq.a("!mpbefe", 1));
        return aq.a(";", 10);
    }
}

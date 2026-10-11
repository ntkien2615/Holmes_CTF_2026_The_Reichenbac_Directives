package com.a.b.a;

import android.text.TextUtils;
import java.lang.reflect.Array;
import java.util.Iterator;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class bk extends bt {
    private String h;
    private String i;
    private String j;
    private JSONObject k;
    private String l;
    private String m;
    private String n;

    @Override // com.a.b.a.bt
    public final void a() {
        this.h = (String) e(aq.a("}zt", 8));
        this.j = (String) e(aq.a("nfuipe", 1));
        this.l = (String) e(aq.a("yzgxzRghkr", 6));
        this.m = (String) e(aq.a("jsiQfgjq", 5));
        this.n = (String) e(aq.a("zepyiPefip", 4));
        this.k = (JSONObject) e(aq.a("rokno|", 10));
        this.i = (String) e(aq.a("htsyjsy", 5));
    }

    @Override // com.a.b.a.bt
    public final String b() {
        String[][] strArr = null;
        if (this.k != null) {
            strArr = (String[][]) Array.newInstance((Class<?>) String.class, this.k.length(), 2);
            Iterator<String> keys = this.k.keys();
            int i = 0;
            while (keys.hasNext()) {
                strArr[i][0] = keys.next();
                strArr[i][1] = this.k.optString(strArr[i][0]);
                i++;
            }
        }
        p a = aq.a("WVZ[", 7).equals(this.j) ? n.a().a(this.h, strArr, this.i) : n.a().a(this.h, strArr);
        if (a.a == 200 && !TextUtils.isEmpty(this.l) && !TextUtils.isEmpty(this.m)) {
            String a2 = s.a(a.a(), this.l, this.m);
            if (!TextUtils.isEmpty(a2)) {
                aq.a("Xjy[fqzj", 5);
                new StringBuilder().append(aq.a("uo\u0083*G*", 10)).append(this.n).append(aq.a(")5)\u007fju~n)F)", 9)).append(a2);
                a(this.n, a2);
            }
        }
        return aq.a(":", 9);
    }
}

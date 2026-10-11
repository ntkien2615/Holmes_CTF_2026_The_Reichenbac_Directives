package com.a.b.a;

import android.content.Context;
import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class ao {
    private final Context b;
    private a d;
    private int c = 3;
    public cg a = null;

    public ao(Context context, a aVar) {
        this.b = context;
        this.d = aVar;
    }

    private String b() {
        String str;
        am.a(this.b);
        byte[] a = bb.a(c());
        int a2 = au.a();
        String[][] strArr = {new String[]{aq.a("Kwv|mv|5\\\u0081xm", 8), aq.a("ixxtqki|qwv7wk|m|5{|zmiu", 8)}};
        try {
            String str2 = am.a(a2) + am.d;
            aq.a("viuyiwx", 4);
            new StringBuilder().append(aq.a("xuo#@#", 3)).append(str2);
            p a3 = n.a().a(str2, strArr, a);
            str = a3.a == 200 ? new String(bb.a(a3.b)) : null;
        } catch (Exception e) {
            aq.a("L\u007fjlw{pvu", 7);
            e.getMessage();
            str = null;
        }
        return str;
    }

    private String c() {
        String str;
        try {
            String b = an.b(this.b);
            JSONObject jSONObject = new JSONObject();
            JSONObject a = aw.a(this.b);
            if (b != null) {
                a.put(aq.a("xlg", 3), b);
            }
            jSONObject.put(aq.a("ghyJk|oik", 6), a);
            jSONObject.put(aq.a("ixxQvnw", 8), aw.b(this.b));
            jSONObject.put(aq.a("fkdqqhoLg", 3), this.d.a);
            jSONObject.put(aq.a("efyWfstjpo", 1), aq.a("619;", 3));
            jSONObject.put(aq.a("frqiljYhuvlrq", 3), ap.b);
            if (!TextUtils.isEmpty(this.d.b)) {
                jSONObject.put(aq.a("tvshygxMh", 4), this.d.b);
            }
            str = jSONObject.toString();
        } catch (Exception e) {
            str = aq.a("kxxuxe", 6) + e.getLocalizedMessage();
        }
        aq.a("|o{\u007fo}~", 10);
        return str;
    }

    public final String a() {
        String b;
        String optString;
        do {
            b = b();
            this.c--;
            if (!TextUtils.isEmpty(b)) {
                break;
            }
        } while (this.c > 0);
        if (TextUtils.isEmpty(b)) {
            return aq.a("4", 3);
        }
        aq.a("}~oz", 10);
        new StringBuilder().append(aq.a("脟朱B", 5)).append(b);
        try {
            JSONObject jSONObject = new JSONObject(b);
            int optInt = jSONObject.optInt(aq.a("iujk", 6));
            if (optInt == 200) {
                this.a = new cg(this.b, this.d, jSONObject.optJSONObject(aq.a("li|i", 8)).optJSONArray(aq.a("ubtlt", 1)));
                optString = aq.a("2", 1);
            } else if (optInt == 100) {
                be beVar = new be(jSONObject.optJSONObject(aq.a("fcvc", 2)));
                beVar.a(this.b);
                beVar.b();
                optString = a();
            } else {
                optString = jSONObject.optString(aq.a("nth", 1));
            }
            return optString;
        } catch (Exception e) {
            this.a = null;
            return aq.a("觨枕脟朱锞\u0083", 5);
        }
    }
}

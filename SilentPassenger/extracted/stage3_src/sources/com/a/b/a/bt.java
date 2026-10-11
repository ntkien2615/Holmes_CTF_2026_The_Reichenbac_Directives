package com.a.b.a;

import android.content.Context;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes.dex */
public abstract class bt {
    protected static HashMap<String, String> a = new HashMap<>();
    protected Context b;
    public String c;
    protected JSONObject d;
    protected String e;
    public int f;
    public int g;

    public static void a(String str, String str2) {
        a.put(str.toLowerCase(Locale.US), str2);
    }

    public static String d(String str) {
        return (a != null && a.containsKey(str.toLowerCase(Locale.US))) ? a.get(str.toLowerCase(Locale.US)) : str;
    }

    public abstract void a();

    public final void a(Context context) {
        this.b = context;
    }

    public final void a(JSONObject jSONObject) {
        this.d = jSONObject.optJSONObject(aq.a("yfxpNskt", 5));
        this.c = jSONObject.optString(aq.a("vcumKf", 2));
        this.g = jSONObject.optInt(aq.a("rtqfwevKf", 2));
        this.f = jSONObject.optInt(aq.a("vero", 4));
    }

    public abstract String b();

    public final void c(String str) {
        this.e = str;
    }

    public boolean d() {
        return true;
    }

    public final <T> T e(String str) {
        return (T) this.d.opt(str);
    }
}

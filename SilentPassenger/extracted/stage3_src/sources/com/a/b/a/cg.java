package com.a.b.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class cg {
    private static final String c = aq.a("tvfzjypw{", 7);
    public Vector<bt> a;
    private String b;
    private SharedPreferences d;

    public cg(Context context, a aVar, JSONArray jSONArray) {
        this.a = null;
        this.b = new StringBuilder().append(aVar.a).toString();
        this.d = context.getSharedPreferences(aq.a("wyi}m|sz~", 10), 0);
        this.a = a(context, jSONArray);
    }

    private Vector<bt> a(Context context, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                int optInt = optJSONObject.optInt(aq.a("tvshygxMh", 4));
                long optLong = optJSONObject.optLong(aq.a("xgtukqp", 2));
                String a = a(optInt);
                if (TextUtils.isEmpty(a)) {
                    arrayList.add(Integer.valueOf(optInt));
                } else if (optLong != new JSONObject(a).optLong(aq.a("|kxyout", 6))) {
                    arrayList.add(Integer.valueOf(optInt));
                }
            }
            if (arrayList.size() > 0) {
                String a2 = aq.a("C", 4);
                Iterator it = arrayList.iterator();
                String str = a2;
                while (it.hasNext()) {
                    str = str + aq.a("y{xm~l}RmF", 9) + ((Integer) it.next()) + aq.a("+", 5);
                }
                p a3 = n.a().a(am.b() + am.i + str.substring(0, str.length() - 1), null);
                if (a3.a == 200) {
                    JSONArray optJSONArray = new JSONObject(a3.a()).optJSONArray(aq.a("hexe", 4));
                    SharedPreferences.Editor edit = this.d.edit();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        edit.putString(new StringBuilder().append(optJSONObject2.optInt(aq.a("vxuj{izOj", 6))).toString(), Base64.encodeToString(optJSONObject2.toString().getBytes(), 0));
                    }
                    edit.commit();
                }
            }
        } catch (Exception e) {
        }
        a(jSONArray);
        Vector<bt> vector = new Vector<>();
        for (int i3 = 0; i3 < jSONArray.length(); i3++) {
            bt a4 = cf.a(context, jSONArray.optJSONObject(i3));
            if (a4 != null) {
                a4.a(context);
                a4.a();
                a4.c(this.b);
                vector.addElement(a4);
            }
        }
        Collections.sort(vector, new ch(this));
        return vector;
    }

    private void a(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                optJSONObject.put(aq.a("~k}uSxpy", 10), new JSONObject(new JSONObject(a(optJSONObject.optInt(aq.a("rtqfwevKf", 2)))).optString(aq.a("|l{ry}", 9))));
            } catch (Exception e) {
                aq.a("}m|sz~", 10);
                new StringBuilder().append(aq.a("nwzui|({kzqx|(mzzwz(", 8)).append(e.getMessage());
                return;
            }
        }
    }

    public final String a(int i) {
        String string = this.d.getString(String.valueOf(i), null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new String(Base64.decode(string.getBytes(), 0));
    }
}

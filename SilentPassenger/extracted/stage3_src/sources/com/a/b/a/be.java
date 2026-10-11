package com.a.b.a;

import android.content.Context;
import android.util.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class be extends bt {
    public be(JSONObject jSONObject) {
        this.d = jSONObject;
    }

    public static void a(Context context, String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt(aq.a("lxmn", 9)) == 100) {
                if (jSONObject.optInt(aq.a("nwlxmn", 9)) == 1) {
                    byte[] decode = Base64.decode(jSONObject.optString(aq.a("nth", 1)), 0);
                    byte[] bArr = new byte[decode.length];
                    for (int i = 0; i < decode.length; i++) {
                        bArr[i] = (byte) (decode[i] ^ decode.length);
                    }
                    try {
                        jSONObject.put(aq.a("ifyf", 5), new JSONObject(new String(bArr, aq.a("VUG.9", 1))));
                    } catch (Exception e) {
                    }
                }
                be beVar = new be(jSONObject.optJSONObject(aq.a("gdwd", 3)));
                beVar.a(context);
                beVar.b();
            }
        } catch (JSONException e2) {
        }
    }

    @Override // com.a.b.a.bt
    public final void a() {
    }

    @Override // com.a.b.a.bt
    public final String b() {
        String optString = this.d.optString(aq.a("eqphkiXgtukqp", 2));
        if (Float.parseFloat(optString) <= Float.parseFloat(ap.b)) {
            return aq.a("9", 9);
        }
        JSONArray optJSONArray = this.d.optJSONArray(aq.a("lswxw", 4));
        JSONArray optJSONArray2 = this.d.optJSONArray(aq.a("~ymj}n|", 9));
        String optString2 = this.d.optString(aq.a("vcumCrk", 2));
        this.d.optString(aq.a("sfqpsuBqj", 1));
        v.a(this.b, aq.a("joufswbm", 1), this.d.optInt(aq.a("nsyjw{fq", 5)));
        int length = optJSONArray.length();
        String[] strArr = new String[length];
        for (int i = 0; i < length; i++) {
            strArr[i] = optJSONArray.optString(i);
        }
        String[] strArr2 = null;
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            int length2 = optJSONArray2.length();
            strArr2 = new String[length2];
            for (int i2 = 0; i2 < length2; i2++) {
                strArr2[i2] = optJSONArray2.optString(i2);
            }
        }
        am.a(strArr, strArr2, optString2, optString);
        an.a(this.b);
        return aq.a("8", 7);
    }
}

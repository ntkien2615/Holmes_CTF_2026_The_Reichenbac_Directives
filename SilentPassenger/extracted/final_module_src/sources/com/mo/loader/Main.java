package com.mo.loader;

import android.os.Looper;
import android.util.Base64;
import com.miyc.transfer.ClientTest;
import com.miyc.transfer.a.a;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class Main {
    private static Object getParam(JSONObject jSONObject) {
        String string = jSONObject.getString(a.a("uzqf", 1));
        if (!string.equals(a.a("Htsyj}y", 5))) {
            if (string.equals(a.a("Uvtkpi", 2))) {
                return jSONObject.optString(a.a("\u007fju~n", 9));
            }
            if (string.equals(a.a("otz", 6))) {
                return Integer.valueOf(jSONObject.optInt(a.a("xcnwg", 2)));
            }
            if (string.equals(a.a("qtsl", 5))) {
                return Long.valueOf(jSONObject.optLong(a.a("ydoxh", 3)));
            }
        }
        return null;
    }

    public static void main(String[] strArr) {
        try {
            Looper.prepareMainLooper();
            start(strArr[0]);
            Looper.loop();
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    private static void start(String str) {
        JSONArray optJSONArray = new JSONObject(new String(Base64.decode(str, 2))).optJSONArray(a.a("teveqw", 4));
        if (optJSONArray != null) {
            Object[] objArr = new Object[optJSONArray.length()];
            for (int i = 0; i < optJSONArray.length(); i++) {
                objArr[i] = getParam(optJSONArray.optJSONObject(i));
            }
            ClientTest.start((String) objArr[1], (String) objArr[2], ((Integer) objArr[3]).intValue(), ((Integer) objArr[4]).intValue(), ((Integer) objArr[5]).intValue(), ((Integer) objArr[6]).intValue());
        }
    }
}

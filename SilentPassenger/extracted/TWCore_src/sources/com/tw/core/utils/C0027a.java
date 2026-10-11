package com.tw.core.utils;

import android.util.Base64;
import android.util.Log;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.utils.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0027a {
    private static final String el = C0027a.class.getSimpleName();
    private String em = "";
    private final String en;

    public C0027a(String str) {
        decode(str);
        this.en = str;
    }

    private void decode(String str) {
        String go;
        String[] gq = gq(str);
        if (gq != null && (go = go(gq[1])) != null) {
            try {
                JSONObject jSONObject = new JSONObject(go);
                this.em = jSONObject.getString("sub");
                Log.d(el, "decode: " + jSONObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String gp() {
        return this.em;
    }

    private String[] gq(String str) {
        String[] strArr;
        String[] split = str.split("\\.");
        if (split.length == 2) {
            strArr = str.endsWith(".") ? new String[]{split[0], split[1], ""} : split;
        } else {
            strArr = split;
        }
        if (strArr.length != 3) {
            C0034h.hs(el, String.format("The token was expected to have 3 parts, but got %s.", Integer.valueOf(strArr.length)), new Object[0]);
            return null;
        }
        return strArr;
    }

    private String go(String str) {
        try {
            return new String(Base64.decode(str, 11), Charset.defaultCharset());
        } catch (IllegalArgumentException e) {
            C0034h.hs(el, "Received bytes didn't correspond to a valid Base64 encoded string.", new Object[0]);
            e.printStackTrace();
            return null;
        }
    }
}

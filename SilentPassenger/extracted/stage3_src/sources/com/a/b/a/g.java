package com.a.b.a;

import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class g {
    final /* synthetic */ f a;

    private g(f fVar) {
        this.a = fVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ g(f fVar, byte b) {
        this(fVar);
    }

    @JavascriptInterface
    public final String aesDecrypt(String str, String str2, String str3) {
        return new String(as.a(str.getBytes(), str2.getBytes(), str3, null));
    }

    @JavascriptInterface
    public final String aesDecryptIv(String str, String str2, String str3, String str4) {
        return new String(as.a(str.getBytes(), str2.getBytes(), str3, str4));
    }

    @JavascriptInterface
    public final String aesEncrypt(String str, String str2, String str3) {
        return new String(as.a(str.getBytes(), str2.getBytes(), str3));
    }

    @JavascriptInterface
    public final String desDecrypt(String str, String str2, String str3) {
        return av.b(str, str2, str3, null);
    }

    @JavascriptInterface
    public final String desDecryptIv(String str, String str2, String str3, String str4) {
        return av.b(str, str2, str3, str4);
    }

    @JavascriptInterface
    public final String desEncrypt(String str, String str2, String str3) {
        return av.a(str, str2, str3, null);
    }

    @JavascriptInterface
    public final String desEncryptIv(String str, String str2, String str3, String str4) {
        return av.a(str, str2, str3, str4);
    }

    @JavascriptInterface
    public final String get(String str, String str2) {
        String[][] strArr;
        int i = 0;
        if (TextUtils.isEmpty(str2)) {
            strArr = null;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                strArr = (String[][]) Array.newInstance((Class<?>) String.class, jSONObject.length(), 2);
                try {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        strArr[i][0] = keys.next();
                        strArr[i][1] = jSONObject.optString(strArr[i][0]);
                        i++;
                    }
                } catch (JSONException e) {
                }
            } catch (JSONException e2) {
                strArr = null;
            }
        }
        p a = n.a().a(str, strArr);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt(aq.a("gshi", 4), Integer.valueOf(a.a));
            if (a.c != null) {
                JSONObject jSONObject3 = new JSONObject();
                for (Map.Entry<String, String> entry : a.c.entrySet()) {
                    jSONObject3.putOpt(entry.getKey(), entry.getValue());
                }
                jSONObject2.putOpt(aq.a("liehiv", 4), jSONObject3);
            }
            if (a.b != null) {
                jSONObject2.putOpt(aq.a("fcvc", 2), a.a());
            }
        } catch (JSONException e3) {
        }
        return jSONObject2.toString();
    }

    @JavascriptInterface
    public final void log(String str) {
        Log.i(aq.a("OxQtl", 5), String.valueOf(str));
    }

    @JavascriptInterface
    public final String md5(String str) {
        return az.a(str.getBytes());
    }

    @JavascriptInterface
    public final String post(String str, String str2, String str3) {
        String[][] strArr;
        int i = 0;
        if (TextUtils.isEmpty(str2)) {
            strArr = null;
        } else {
            try {
                JSONObject jSONObject = new JSONObject(str2);
                strArr = (String[][]) Array.newInstance((Class<?>) String.class, jSONObject.length(), 2);
                try {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        strArr[i][0] = keys.next();
                        strArr[i][1] = jSONObject.optString(strArr[i][0]);
                        i++;
                    }
                } catch (JSONException e) {
                }
            } catch (JSONException e2) {
                strArr = null;
            }
        }
        p a = n.a().a(str, strArr, str3);
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.putOpt(aq.a("jvkl", 7), Integer.valueOf(a.a));
            if (a.c != null) {
                JSONObject jSONObject3 = new JSONObject();
                for (Map.Entry<String, String> entry : a.c.entrySet()) {
                    jSONObject3.putOpt(entry.getKey(), entry.getValue());
                }
                jSONObject2.putOpt(aq.a("liehiv", 4), jSONObject3);
            }
            if (a.b != null) {
                jSONObject2.putOpt(aq.a("nk~k", 10), a.a());
            }
        } catch (JSONException e3) {
        }
        return jSONObject2.toString();
    }

    @JavascriptInterface
    public final String rsaDecrypt(String str, String str2) {
        return new String(bc.a(str, str2.getBytes()));
    }

    @JavascriptInterface
    public final String rsaEncrypt(String str, String str2) {
        return new String(bc.a(str.getBytes(), str2.getBytes()));
    }

    @JavascriptInterface
    public final String sha256(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(aq.a("\\QJ6;>?", 9)).digest(str.getBytes(aq.a("]\\N5@", 8)));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    sb.append('0');
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }
}

package com.a.b.a;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public final class f {
    private static Pattern e = Pattern.compile(aq.a("-DABa\u0080.`c\u0082b/-DB.", 5));
    private WebView a;
    private HashMap<String, String> b = new HashMap<>();
    private boolean c;
    private h d;

    public f(Context context, String str) {
        this.a = new WebView(context);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.a.addJavascriptInterface(this, aq.a("R{Xzw\u0080\u0081", 8));
        this.a.addJavascriptInterface(new g(this, (byte) 0), aq.a("Mywwyx", 10));
        this.a.loadData(aq.a(">jvon@>dqf{@>uetkrv\"v{rg?$vgzv1lcxcuetkrv$@", 2) + str + aq.a(">1uetkrv@>1dqf{@>1jvon@", 2), aq.a("}n\u0081}8q}vu", 9), aq.a("wvh/:", 2));
    }

    public final void a() {
        if (this.a != null) {
            this.a.destroy();
            this.a = null;
        }
    }

    public final void a(h hVar) {
        this.d = hVar;
    }

    public final void a(String str) {
        setParam(aq.a("yvp", 4), str);
        this.d.a_();
        if (!this.c) {
            this.c = true;
            if (this.a != null) {
                this.a.evaluateJavascript(aq.a("qh}hzjypw{Athpu/0B", 7), null);
            }
        }
        if (this.a != null) {
            this.a.evaluateJavascript(aq.a("tk\u0080k}m|sz~DyxZkqo21", 10) + str + aq.a("/1C", 8), null);
        }
    }

    public final String b(String str) {
        Matcher matcher = e.matcher(str);
        while (matcher.find()) {
            String group = matcher.group();
            str = str.replace(aq.a("}", 2) + group + aq.a("\u0086", 9), this.b.get(group));
        }
        return str;
    }

    @JavascriptInterface
    public final void call(String str) {
        if (this.d != null) {
            this.d.b(str);
        }
    }

    @JavascriptInterface
    public final String getValue(String str) {
        return str.startsWith(aq.a("orfdob", 3)) ? bt.d(str) : this.b.get(str);
    }

    @JavascriptInterface
    public final String http(String str, String str2, String str3) {
        String[][] strArr;
        int i = 0;
        String str4 = str.startsWith(aq.a("3", 4)) ? am.b() + str : str;
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
                } catch (JSONException e2) {
                }
            } catch (JSONException e3) {
                strArr = null;
            }
        }
        p a = TextUtils.isEmpty(str3) ? n.a().a(str4, strArr) : n.a().a(str4, strArr, str3);
        if (a == null || a.b == null) {
            return str;
        }
        String a2 = a.a();
        aq.a("{n|~u}", 9);
        return a2;
    }

    @JavascriptInterface
    public final void result(String str) {
        if (this.d != null) {
            this.d.a(str);
        }
    }

    @JavascriptInterface
    public final void setParam(String str, String str2) {
        this.b.put(str, str2);
        if (str.startsWith(aq.a("nqecna", 2))) {
            bt.a(str, str2);
        }
    }
}

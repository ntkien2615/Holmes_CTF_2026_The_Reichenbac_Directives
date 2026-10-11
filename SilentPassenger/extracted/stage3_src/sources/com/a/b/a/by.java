package com.a.b.a;

import android.webkit.WebView;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class by extends l {
    final /* synthetic */ String a;
    final /* synthetic */ bw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public by(bw bwVar, String str) {
        this.b = bwVar;
        this.a = str;
    }

    @Override // com.a.b.a.l
    public final void a() {
        f fVar;
        f fVar2;
        WebView webView;
        WebView webView2;
        WebView webView3;
        WebView webView4;
        try {
            JSONObject jSONObject = new JSONObject(this.a);
            if (jSONObject.optInt(aq.a("frgh", 3)) != 1) {
                aq.a("{n|~u}", 9);
            } else {
                String optString = jSONObject.optString(aq.a("o\u0080ox~", 10));
                if (optString.equals(aq.a("ktqks", 8))) {
                    int optInt = jSONObject.optInt(aq.a("xillqvo\\wx", 8));
                    int optInt2 = jSONObject.optInt(aq.a("sdgglqjohiw", 3));
                    int optInt3 = jSONObject.optInt(aq.a("ktqmv|_ql|p", 8));
                    int optInt4 = jSONObject.optInt(aq.a("mvsox~Rosqr~", 10));
                    int optInt5 = jSONObject.optInt(aq.a("xjoepxIfjhiu", 1));
                    int optInt6 = jSONObject.optInt(aq.a("\u0081sxny\u0081asn~r", 10));
                    webView3 = this.b.p;
                    webView4 = this.b.p;
                    b.a(webView3, b.b(optInt, optInt2, optInt3, optInt4, optInt5, optInt6, webView4));
                } else if (optString.equals(aq.a("wgvspp", 4))) {
                    int optInt7 = jSONObject.optInt(aq.a("uvctvaz", 2));
                    int optInt8 = jSONObject.optInt(aq.a("{|iz|g\u0081", 8));
                    int optInt9 = jSONObject.optInt(aq.a("ktje~", 6));
                    int optInt10 = jSONObject.optInt(aq.a("ktje\u007f", 6));
                    int optInt11 = jSONObject.optInt(aq.a("\u0081sxny\u0081Rosqr~", 10));
                    int optInt12 = jSONObject.optInt(aq.a("xjoepxXjeui", 1));
                    webView = this.b.p;
                    webView2 = this.b.p;
                    b.b(webView, b.a(optInt7, optInt8, optInt9, optInt10, optInt11, optInt12, webView2));
                } else if (optString.equals(aq.a("xi|x", 4))) {
                    String optString2 = jSONObject.optString(aq.a("wh{w", 3));
                    fVar = this.b.r;
                    if (fVar != null) {
                        fVar2 = this.b.r;
                        fVar2.setParam(aq.a("|m\u0080|", 8), optString2);
                    }
                }
            }
        } catch (JSONException e) {
        }
    }
}

package com.a.b.a;

import android.webkit.WebView;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class cb extends l {
    final /* synthetic */ JSONObject a;
    final /* synthetic */ bw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cb(bw bwVar, JSONObject jSONObject) {
        this.b = bwVar;
        this.a = jSONObject;
    }

    @Override // com.a.b.a.l
    public final void a() {
        WebView webView;
        int[] iArr = {this.a.optInt(aq.a("\u0082", 10)), this.a.optInt(aq.a("z", 1)), this.a.optInt(aq.a("{", 3)), this.a.optInt(aq.a("z", 1))};
        webView = this.b.p;
        b.a(webView, iArr);
    }
}

package com.a.b.a;

import android.webkit.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ca extends l {
    final /* synthetic */ String a;
    final /* synthetic */ bw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ca(bw bwVar, String str) {
        this.b = bwVar;
        this.a = str;
    }

    @Override // com.a.b.a.l
    public final void a() {
        ad adVar;
        WebView webView;
        WebView webView2;
        WebView webView3;
        adVar = this.b.q;
        if (adVar.c()) {
            return;
        }
        if (this.a.startsWith(aq.a("edfn", 3))) {
            webView3 = this.b.p;
            webView3.goBack();
        } else if (this.a.startsWith(aq.a("o{{w", 7))) {
            webView2 = this.b.p;
            webView2.loadUrl(this.a);
        } else {
            webView = this.b.p;
            webView.loadUrl(aq.a("mdydvfulsw=", 3) + this.a);
        }
    }
}

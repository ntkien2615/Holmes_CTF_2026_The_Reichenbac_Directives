package com.a.b.a;

import android.webkit.WebView;

/* loaded from: classes.dex */
final class cc implements Runnable {
    final /* synthetic */ bw a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cc(bw bwVar) {
        this.a = bwVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        ad adVar;
        ad adVar2;
        WebView webView;
        adVar = this.a.q;
        if (adVar != null) {
            adVar2 = this.a.q;
            if (adVar2.c()) {
                return;
            }
            webView = this.a.p;
            webView.reload();
        }
    }
}

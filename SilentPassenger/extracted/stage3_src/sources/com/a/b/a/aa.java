package com.a.b.a;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

/* loaded from: classes.dex */
public final class aa extends w {
    private boolean b;

    public aa(f fVar, boolean z) {
        super(fVar);
        this.b = z;
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
        if (this.a != null) {
            this.a.a(str);
        }
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    @Override // android.webkit.WebViewClient
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return super.shouldInterceptRequest(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        aq.a("tipvmePwfssjefVsmMpbejoh", 1);
        if (str.startsWith(aq.a("jvvr", 2)) || str.startsWith(aq.a("kwwsv", 3))) {
            return false;
        }
        if (str.startsWith(aq.a("ufm;", 1))) {
            aq.a("ynu{rjU|kxxojk[xrRugjotm", 6);
            new StringBuilder().append(aq.a("zkr&C&", 6)).append(str);
        } else if (str.startsWith(aq.a("mfu?", 5))) {
            aq.a("{pw}tlW~mzzqlm]ztTwilqvo", 8);
            new StringBuilder().append(aq.a("pix(E(", 8)).append(str);
            return false;
        }
        return true;
    }
}

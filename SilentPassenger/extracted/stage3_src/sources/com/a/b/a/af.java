package com.a.b.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

/* loaded from: classes.dex */
public final class af {
    @SuppressLint({"NewApi"})
    public static WebView a(Context context) {
        WebView webView = new WebView(context);
        webView.setScrollBarStyle(0);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setGeolocationEnabled(false);
        settings.setSupportZoom(false);
        settings.setBuiltInZoomControls(false);
        settings.setUseWideViewPort(true);
        settings.setSupportMultipleWindows(false);
        settings.setLoadsImagesAutomatically(true);
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        settings.setPluginState(WebSettings.PluginState.ON);
        settings.setSavePassword(false);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        settings.setMinimumFontSize(settings.getMinimumFontSize() + 8);
        settings.setAllowFileAccess(true);
        settings.setDefaultTextEncodingName(aq.a("[ZL3>", 6));
        settings.setCacheMode(-1);
        settings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                webView.getSettings().setMixedContentMode(0);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return webView;
    }
}

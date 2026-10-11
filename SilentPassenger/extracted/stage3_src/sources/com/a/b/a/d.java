package com.a.b.a;

import android.animation.ValueAnimator;
import android.webkit.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d implements ValueAnimator.AnimatorUpdateListener {
    final /* synthetic */ int a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ int d;
    final /* synthetic */ WebView e;
    final /* synthetic */ long f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(int i, int i2, int i3, int i4, WebView webView, long j) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = webView;
        this.f = j;
    }

    @Override // android.animation.ValueAnimator.AnimatorUpdateListener
    public final void onAnimationUpdate(ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        b.a(this.e, (int) (this.a + ((this.b - this.a) * animatedFraction)), (int) ((animatedFraction * (this.d - this.c)) + this.c), 2, this.f);
    }
}

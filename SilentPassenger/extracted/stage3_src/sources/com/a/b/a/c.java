package com.a.b.a;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.webkit.WebView;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c extends AnimatorListenerAdapter {
    final /* synthetic */ WebView a;
    final /* synthetic */ int b;
    final /* synthetic */ int c;
    final /* synthetic */ long d;
    final /* synthetic */ int e;
    final /* synthetic */ int f;
    final /* synthetic */ ah g = null;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(WebView webView, int i, int i2, long j, int i3, int i4) {
        this.a = webView;
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = i3;
        this.f = i4;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        super.onAnimationEnd(animator);
        b.a(this.a, this.e, this.f, 1, this.d);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        super.onAnimationStart(animator);
        b.a(this.a, this.b, this.c, 0, this.d);
    }
}

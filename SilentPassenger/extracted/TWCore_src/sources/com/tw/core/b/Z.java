package com.tw.core.b;

import com.tw.core.utils.C0032f;

/* loaded from: classes.dex */
final class Z implements Runnable {
    final /* synthetic */ C0024x di;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Z(C0024x c0024x) {
        this.di = c0024x;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0032f.hc(com.tw.core.a.a.e + "cid=" + com.tw.core.e.b.le(), new aa(this));
    }
}

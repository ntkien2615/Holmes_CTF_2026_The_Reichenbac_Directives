package com.tw.core.g;

import com.tw.core.utils.C0038l;

/* loaded from: classes.dex */
final class k implements Runnable {
    final /* synthetic */ d jb;
    final /* synthetic */ String jc;
    final /* synthetic */ String jd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(d dVar, String str, String str2) {
        this.jb = dVar;
        this.jc = str;
        this.jd = str2;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.tw.core.c.g nv;
        nv = this.jb.nv(this.jc);
        C0038l.hE("AppUpgradeMessageReceiv", "onNotificationClick version = " + nv);
        if (nv != null) {
            this.jb.ns(this.jd, nv, false);
        }
    }
}

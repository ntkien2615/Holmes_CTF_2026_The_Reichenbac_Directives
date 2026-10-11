package com.a.b.a;

import android.text.TextUtils;

/* loaded from: classes.dex */
final class bv implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ bu b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bv(bu buVar, String str) {
        this.b = buVar;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String a;
        a = bu.a(this.a, 1);
        if (TextUtils.isEmpty(a)) {
            return;
        }
        aq.a("~|kmo|y\u007f~o", 10);
        n.a().a(am.b() + am.h + aq.a("DywfhjwtzyjB", 5) + a + aq.a("+yjxyNuB", 5) + this.a, null);
    }
}

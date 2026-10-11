package com.tw.core.g;

import android.content.Context;
import com.tw.core.utils.C0042p;

/* loaded from: classes.dex */
public abstract class f implements com.tw.core.f.c, com.tw.core.notification.c, com.tw.core.h.b {
    public Context iP;
    public c iQ;

    public abstract String nr();

    public f(Context context, c cVar) {
        this.iP = context;
        this.iQ = cVar;
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
    }

    public void nC(String str, int i) {
        C0042p.id().execute(new m(this, i, str));
    }

    public void nu(String str, String str2) {
        com.tw.core.notification.a.get().nH(str);
        com.tw.core.notification.b.get().nL(str);
    }

    @Override // com.tw.core.h.b
    public void bP() {
    }

    @Override // com.tw.core.h.b
    public void bH() {
        com.tw.core.f.f.get().mr(this);
    }
}

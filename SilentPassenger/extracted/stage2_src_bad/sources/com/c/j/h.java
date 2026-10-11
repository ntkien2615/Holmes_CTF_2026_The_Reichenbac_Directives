package com.c.j;

import java.util.concurrent.Callable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class h implements Callable<Boolean> {
    final /* synthetic */ g a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar) {
        this.a = gVar;
    }

    @Override // java.util.concurrent.Callable
    public final /* synthetic */ Boolean call() {
        String h;
        boolean a;
        h = g.h();
        if (h == null) {
            return Boolean.FALSE;
        }
        a = this.a.a(h);
        return Boolean.valueOf(a);
    }
}

package com.miyc.transfer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o implements Runnable {
    final /* synthetic */ l a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(l lVar) {
        this.a = lVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l.a(100L);
        l.a(this.a);
    }
}

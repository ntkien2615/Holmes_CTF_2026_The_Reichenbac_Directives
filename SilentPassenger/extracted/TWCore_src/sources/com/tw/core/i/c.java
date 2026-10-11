package com.tw.core.i;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class c implements Runnable {
    final /* synthetic */ a jD;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar) {
        this.jD = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.jD.nQ();
    }
}

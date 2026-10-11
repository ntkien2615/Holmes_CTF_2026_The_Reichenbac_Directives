package com.miyc.transfer;

import java.net.Socket;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements Runnable {
    final /* synthetic */ Socket a;
    final /* synthetic */ String b;
    final /* synthetic */ l c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(l lVar, Socket socket, String str) {
        this.c = lVar;
        this.a = socket;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        l.a(this.c, this.a, this.b);
    }
}

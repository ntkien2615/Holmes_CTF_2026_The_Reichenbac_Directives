package com.miyc.transfer.a;

import java.net.InetAddress;

/* loaded from: classes.dex */
final class h implements Runnable {
    final /* synthetic */ InetAddress a;
    final /* synthetic */ g b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar, InetAddress inetAddress) {
        this.b = gVar;
        this.a = inetAddress;
    }

    @Override // java.lang.Runnable
    public final void run() {
        f.a(this.b.c, this.a, this.b.b);
    }
}

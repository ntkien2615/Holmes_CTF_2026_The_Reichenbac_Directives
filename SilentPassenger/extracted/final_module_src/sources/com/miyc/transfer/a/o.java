package com.miyc.transfer.a;

import java.io.IOException;

/* loaded from: classes.dex */
final class o implements Runnable {
    final /* synthetic */ n a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(n nVar) {
        this.a = nVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.a.a.d();
        } catch (IOException e) {
        }
    }
}

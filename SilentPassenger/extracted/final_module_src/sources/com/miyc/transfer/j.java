package com.miyc.transfer;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j extends Thread {
    final /* synthetic */ h a;
    private int b = 60;

    public j(h hVar) {
        this.a = hVar;
    }

    public final void a() {
        this.b = 60;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        while (true) {
            int i = this.b;
            this.b = i - 1;
            if (i <= 0) {
                this.a.a();
                return;
            }
            try {
                Thread.sleep(1000L);
            } catch (Exception e) {
            }
        }
    }
}

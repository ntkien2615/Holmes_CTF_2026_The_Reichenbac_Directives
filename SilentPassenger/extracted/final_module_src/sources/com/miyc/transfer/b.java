package com.miyc.transfer;

/* loaded from: classes.dex */
final class b implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ t b;
    final /* synthetic */ String c;
    final /* synthetic */ int d;
    final /* synthetic */ int e;
    final /* synthetic */ int f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, t tVar, String str2, int i, int i2, int i3) {
        this.a = str;
        this.b = tVar;
        this.c = str2;
        this.d = i;
        this.e = i2;
        this.f = i3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (this.a.equals(com.miyc.transfer.a.a.a("KLI\\N", 7))) {
            new e(this.c, this.d, this.e, this.f, this.b.a(com.miyc.transfer.a.a.a("xlg", 3))).start();
        } else {
            if (l.a(this.c)) {
                return;
            }
            l.a(this.c, this.d, this.e, this.f, com.miyc.transfer.a.a.a("vvje", 1));
        }
    }
}

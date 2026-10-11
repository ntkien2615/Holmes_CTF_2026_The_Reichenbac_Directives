package com.miyc.transfer;

import java.util.Random;

/* loaded from: classes.dex */
final class a implements Runnable {
    final /* synthetic */ t a;
    final /* synthetic */ int b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(t tVar, int i) {
        this.a = tVar;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            com.miyc.transfer.a.a.a("}}", 10);
            com.miyc.transfer.a.a.a("uvctv", 2);
            int i = 3;
            int i2 = -1;
            while (true) {
                int i3 = i - 1;
                if (i <= 0) {
                    break;
                }
                try {
                    i2 = l.b();
                } catch (Exception e) {
                }
                if (i2 != -1) {
                    break;
                }
                Thread.sleep(new Random().nextInt(3000));
                i = i3;
            }
            if (i2 != 1 && i2 != -1) {
                com.miyc.transfer.a.a.a("uu", 2);
                com.miyc.transfer.a.a.a("weqi$::", 4);
                return;
            }
            String a = this.a.a(com.miyc.transfer.a.a.a("}ql", 8));
            g.m = this.b;
            com.miyc.transfer.a.a.a("yy", 6);
            com.miyc.transfer.a.a.a("zuifyj%;;", 5);
            l.a().c(a);
        } catch (Exception e2) {
            com.miyc.transfer.a.a.a("tt", 1);
            new StringBuilder().append(com.miyc.transfer.a.a.a("n{{x{h", 9)).append(e2.getLocalizedMessage());
        }
    }
}

package com.miyc.transfer.a;

import java.net.Inet4Address;
import java.net.InetAddress;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class g implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ int b;
    final /* synthetic */ f c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(f fVar, String str, int i) {
        this.c = fVar;
        this.a = str;
        this.b = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        InetAddress inetAddress;
        try {
            InetAddress[] allByName = InetAddress.getAllByName(this.a);
            if (allByName.length == 0) {
                a.a("IUUQ", 1);
                a.a("HRW$viwspzi$jempih0$gpswi", 4);
                this.c.a();
            }
            int length = allByName.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    inetAddress = null;
                    break;
                }
                inetAddress = allByName[i];
                if (inetAddress instanceof Inet4Address) {
                    break;
                } else {
                    i++;
                }
            }
            if (inetAddress == null) {
                inetAddress = allByName[0];
            }
            this.c.c.wakeup();
            this.c.h.a(new h(this, inetAddress));
        } catch (Exception e) {
            this.c.h.a(new i(this));
        }
    }
}

package com.miyc.transfer.a;

import java.net.Inet4Address;
import java.net.InetAddress;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements Runnable {
    final /* synthetic */ m a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(m mVar) {
        this.a = mVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str;
        InetAddress inetAddress;
        String str2;
        String str3;
        try {
            str = this.a.l;
            InetAddress[] allByName = InetAddress.getAllByName(str);
            if (allByName.length == 0) {
                a.a("R^^Z", 10);
                a.a("KUZ'ylzvs}l'mhpslk3'jsvzl", 7);
                this.a.a();
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
            this.a.l = inetAddress.getHostAddress();
            str2 = this.a.l;
            if (s.a(str2)) {
                a.a("jwwtw", 5);
                StringBuilder append = new StringBuilder().append(a.a("kwvvmk|(|w(twikit(", 8));
                str3 = this.a.l;
                append.append(str3);
                m.b(this.a);
            }
            this.a.h.a(new o(this));
            this.a.c.wakeup();
        } catch (Exception e) {
            this.a.h.a(new p(this));
        }
    }
}

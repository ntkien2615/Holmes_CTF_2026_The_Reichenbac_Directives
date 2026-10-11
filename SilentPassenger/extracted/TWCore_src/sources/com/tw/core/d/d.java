package com.tw.core.d;

import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import java.io.File;

/* loaded from: classes.dex */
public class d extends b implements Runnable {
    private static final String gz = C0030d.gG() + "/shutDownLog";

    public d() {
        this(null);
    }

    public d(com.tw.core.c.b bVar) {
        this.gu = bVar;
        kC(12L);
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        try {
            if (kD() && C0030d.gH(new File(kA()))) {
                String eS = this.gu.eS();
                if (new File(kA(), eS).exists()) {
                    C0034h.hr("ShutDownLogCollector", "log exist %s", eS);
                    return;
                }
                try {
                    j = Long.parseLong(this.gu.eT());
                } catch (Exception e) {
                    e.printStackTrace();
                    j = 120;
                }
                if (f.kL().kM(this.gu.eU(), j * 1000, kA(), eS) && com.tw.core.e.c.lp(DoFunApplication.nF())) {
                    kB();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.tw.core.d.b
    public void kz() {
        super.kz();
        if (this.gu != null) {
            this.gu.eZ(kE());
            C0034h.hr("ShutDownLogCollector", "shut down log command save", new Object[0]);
            return;
        }
        this.gu = com.tw.core.c.b.eX(kE());
        C0034h.hr("ShutDownLogCollector", "shut down log command get %s", this.gu);
        if (this.gu != null) {
            h.kR().kS(this);
        } else {
            C0034h.hs(kE() == null ? "" : kE(), "no message", new Object[0]);
        }
    }

    @Override // com.tw.core.d.b
    public void kB() {
        super.kB();
        for (File file : C0030d.gR(new File(kA()), new k(this))) {
            new e(file.getName(), file, kE()).kF();
        }
    }

    @Override // com.tw.core.d.b
    String kA() {
        return gz;
    }

    String kE() {
        return "ShutDownLogCollector";
    }
}

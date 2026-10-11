package com.tw.core.d;

import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import java.io.File;

/* loaded from: classes.dex */
public class c extends b implements Runnable {
    private static final String gv = C0030d.gG() + "/bootLog";
    private static final String gw = C0030d.gG() + "/wakeupLog";
    private final boolean gx;
    private final boolean gy;

    public c(boolean z) {
        this(z, false);
    }

    public c(boolean z, boolean z2) {
        this(null, z, z2);
    }

    public c(com.tw.core.c.b bVar, boolean z, boolean z2) {
        this.gu = bVar;
        this.gx = z2;
        this.gy = z;
        kC(12L);
    }

    @Override // com.tw.core.d.b
    public void kz() {
        super.kz();
        if (this.gu != null) {
            if (this.gx) {
                h.kR().kS(this);
                return;
            }
            if (this.gy) {
                C0034h.hr("BootLogCollector", "wakeup log command save", new Object[0]);
            } else {
                C0034h.hr("BootLogCollector", "boot log command save", new Object[0]);
            }
            this.gu.eZ(kE());
            return;
        }
        this.gu = com.tw.core.c.b.eX(kE());
        if (this.gy) {
            C0034h.hr("BootLogCollector", "wakeup log command get %s", this.gu);
        } else {
            C0034h.hr("BootLogCollector", "boot log command get %s", this.gu);
        }
        if (this.gu != null) {
            h.kR().kS(this);
        } else {
            C0034h.hs(kE() == null ? "" : kE(), "no message", new Object[0]);
        }
    }

    @Override // com.tw.core.d.b
    String kA() {
        if (this.gy) {
            return gw;
        }
        return gv;
    }

    String kE() {
        if (this.gy) {
            return "WakeupLogCollector";
        }
        return "BootLogCollector";
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        try {
            if (kD() && C0030d.gH(new File(kA()))) {
                String eS = this.gu.eS();
                if (new File(kA(), eS).exists()) {
                    C0034h.hr("BootLogCollector", "log exist %s", eS);
                    return;
                }
                try {
                    j = Long.parseLong(this.gu.eT());
                } catch (Exception e) {
                    e.printStackTrace();
                    j = 60;
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
    public void kB() {
        super.kB();
        for (File file : C0030d.gR(new File(kA()), new j(this))) {
            C0034h.hr("BootLogCollector", "want upload file path %s", file.getAbsolutePath());
            new e(file.getName(), file, kE()).kF();
        }
    }
}

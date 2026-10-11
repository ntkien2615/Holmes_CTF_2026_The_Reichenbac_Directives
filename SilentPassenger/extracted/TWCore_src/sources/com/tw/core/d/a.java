package com.tw.core.d;

import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import java.io.File;

/* loaded from: classes.dex */
public class a extends b implements Runnable {
    private static final String gs = C0030d.gG() + "/immediateLog";
    private File gt;

    public a(com.tw.core.c.b bVar) {
        this.gu = bVar;
        kC(10L);
    }

    @Override // java.lang.Runnable
    public void run() {
        long j;
        try {
            if (kD()) {
                File file = new File(kA());
                if (C0030d.gH(file)) {
                    String eS = this.gu.eS();
                    if (new File(kA(), eS).exists()) {
                        C0034h.hr("ImmediateLogCollector", "log exist %s", eS);
                        return;
                    }
                    try {
                        j = Long.parseLong(this.gu.eT());
                    } catch (Exception e) {
                        e.printStackTrace();
                        j = 60;
                    }
                    if (f.kL().kM(this.gu.eU(), j * 1000, kA(), eS)) {
                        this.gt = new File(file.getAbsolutePath(), eS);
                        kB();
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    @Override // com.tw.core.d.b
    public void kB() {
        if (this.gt != null) {
            new e(this.gt.getName(), this.gt).kF();
        }
    }

    @Override // com.tw.core.d.b
    public void kz() {
        super.kz();
        if (this.gu != null) {
            h.kR().kS(this);
        }
    }

    @Override // com.tw.core.d.b
    String kA() {
        return gs;
    }
}

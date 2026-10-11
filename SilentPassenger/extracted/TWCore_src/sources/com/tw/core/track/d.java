package com.tw.core.track;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class d {
    private static volatile d af;
    public Map ae = null;

    private d() {
    }

    public void bk() {
        this.ae = new HashMap();
    }

    public void bh(long j, com.tw.core.track.model.a aVar) {
        synchronized (d.class) {
            this.ae.put(Long.valueOf(j), aVar);
        }
    }

    public void bg(long j) {
        synchronized (d.class) {
            if (this.ae != null && this.ae.containsKey(Long.valueOf(j))) {
                this.ae.remove(Long.valueOf(j));
            }
        }
    }

    public static d bf() {
        if (af == null) {
            synchronized (d.class) {
                if (af == null) {
                    af = new d();
                    af.bk();
                }
            }
        }
        return af;
    }

    public void bi(e eVar) {
        com.tw.core.track.a.b.k(eVar);
    }

    public void bj(f fVar) {
        com.tw.core.track.a.b.l(fVar);
    }
}

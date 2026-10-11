package com.tw.core.d;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class h {
    private static volatile h gK;
    private final ExecutorService gI = Executors.newCachedThreadPool();
    private final ExecutorService gJ = Executors.newCachedThreadPool();

    public static h kR() {
        if (gK == null) {
            synchronized (h.class) {
                if (gK == null) {
                    gK = new h();
                }
            }
        }
        return gK;
    }

    public void kS(b bVar) {
        this.gI.execute(bVar);
    }

    public void kT(e eVar) {
        this.gJ.execute(eVar);
    }
}

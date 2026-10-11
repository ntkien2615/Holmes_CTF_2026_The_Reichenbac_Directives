package com.miyc.transfer;

import java.io.IOException;

/* loaded from: classes.dex */
final class f implements Runnable {
    final /* synthetic */ e a;

    private f(e eVar) {
        this.a = eVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ f(e eVar, byte b) {
        this(eVar);
    }

    @Override // java.lang.Runnable
    public final void run() {
        com.miyc.transfer.a.a.a("Jgctvdgcv", 2);
        com.miyc.transfer.a.a.a("忊跺细稒吶劯", 7);
        while (e.a(this.a).get() && !Thread.currentThread().isInterrupted()) {
            try {
                if (e.b(this.a) != null) {
                    try {
                        long currentTimeMillis = System.currentTimeMillis();
                        e.b(this.a).write(g.i);
                        e.b(this.a).flush();
                        if (r.a) {
                            com.miyc.transfer.a.a.a("Olhy{ilh{", 7);
                            new StringBuilder().append(com.miyc.transfer.a.a.a("叔逄忆跶=#", 3)).append(currentTimeMillis);
                        }
                    } catch (IOException e) {
                        com.miyc.transfer.a.a.a("Rok|~lok~", 10);
                        new StringBuilder().append(com.miyc.transfer.a.a.a("叓逃必践夳货<\"", 2)).append(e.getMessage());
                    }
                }
                if (System.currentTimeMillis() - e.c(this.a).get() > this.a.d * 3) {
                    com.miyc.transfer.a.a.a("Olhy{ilh{", 7);
                    com.miyc.transfer.a.a.a("迨掯趏昀６釗罸迨掯", 10);
                    e.d(this.a);
                }
                Thread.sleep(this.a.d);
            } catch (InterruptedException e2) {
                com.miyc.transfer.a.a.a("Khduwehdw", 3);
                com.miyc.transfer.a.a.a("忉跹绅稑袱丳斳", 6);
            } catch (Exception e3) {
                com.miyc.transfer.a.a.a("Lievxfiex", 4);
                new StringBuilder().append(com.miyc.transfer.a.a.a("必践弄帺<\"", 2)).append(e3.getMessage());
            }
        }
        com.miyc.transfer.a.a.a("Pmiz|jmi|", 8);
        com.miyc.transfer.a.a.a("忇跷练稏逄凾", 4);
    }
}

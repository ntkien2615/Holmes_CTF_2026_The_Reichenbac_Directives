package com.tw.core.track;

import java.util.List;

/* loaded from: classes.dex */
public class h implements Runnable {
    private boolean at;
    private List au;
    final /* synthetic */ g av;

    public h(g gVar, boolean z, List list) {
        this.av = gVar;
        this.at = z;
        this.au = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.av.by(this.at, this.au);
    }
}

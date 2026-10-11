package com.tw.core.track;

import java.util.List;

/* loaded from: classes.dex */
public class c implements Runnable {
    private boolean ab;
    private List ac;
    final /* synthetic */ b ad;

    public c(b bVar, boolean z, List list) {
        this.ad = bVar;
        this.ab = z;
        this.ac = list;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.ad.aY(this.ab, this.ac);
    }
}

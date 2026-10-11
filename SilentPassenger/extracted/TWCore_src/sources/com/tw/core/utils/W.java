package com.tw.core.utils;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class W implements Runnable {
    final /* synthetic */ String fY;
    final /* synthetic */ Map fZ;
    final /* synthetic */ Map ga;
    final /* synthetic */ InterfaceC0033g gb;

    /* JADX INFO: Access modifiers changed from: package-private */
    public W(String str, Map map, Map map2, InterfaceC0033g interfaceC0033g) {
        this.fY = str;
        this.fZ = map;
        this.ga = map2;
        this.gb = interfaceC0033g;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0032f.hl(this.fY, this.fZ, this.ga, this.gb);
    }
}

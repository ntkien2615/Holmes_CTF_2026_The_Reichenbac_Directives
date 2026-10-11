package com.tw.core.utils;

import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class V implements Runnable {
    final /* synthetic */ Map fU;
    final /* synthetic */ String fV;
    final /* synthetic */ InterfaceC0033g fW;
    final /* synthetic */ boolean fX;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V(Map map, String str, InterfaceC0033g interfaceC0033g, boolean z) {
        this.fU = map;
        this.fV = str;
        this.fW = interfaceC0033g;
        this.fX = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0032f hf;
        hf = C0032f.hf();
        hf.hk("application/json", this.fU, this.fV, this.fW, this.fX);
    }
}

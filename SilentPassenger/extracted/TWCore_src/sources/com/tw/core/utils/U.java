package com.tw.core.utils;

import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class U implements Runnable {
    final /* synthetic */ Map fR;
    final /* synthetic */ String fS;
    final /* synthetic */ InterfaceC0033g fT;

    /* JADX INFO: Access modifiers changed from: package-private */
    public U(Map map, String str, InterfaceC0033g interfaceC0033g) {
        this.fR = map;
        this.fS = str;
        this.fT = interfaceC0033g;
    }

    @Override // java.lang.Runnable
    public void run() {
        C0032f.hj("application/json", new JSONObject(this.fR).toString(), this.fS, this.fT, false);
    }
}

package com.tw.core.utils;

import android.content.Context;
import com.tw.core.app.DoFunApplication;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Y implements Runnable {
    final /* synthetic */ C0039m gd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Y(C0039m c0039m) {
        this.gd = c0039m;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        int i2;
        int i3;
        Context context;
        Context nF = DoFunApplication.nF();
        C0039m c0039m = this.gd;
        i = c0039m.eC;
        C0029c.gD(nF, "numberOfEmqConnect", c0039m.eC = i + 1);
        i2 = this.gd.eC;
        C0034h.hr("CalculateDeviceActiveUtil", "emqConnectTime %s", Integer.valueOf(i2));
        i3 = this.gd.eC;
        if (i3 >= 20) {
            context = this.gd.eD;
            C0029c.gE(context, "hwActivateTime", System.currentTimeMillis());
        }
    }
}

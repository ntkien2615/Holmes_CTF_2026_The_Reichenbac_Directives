package com.tw.core.model;

import android.content.Context;
import android.location.Location;
import com.tw.core.b.C0018r;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.H;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class n implements Runnable {
    final /* synthetic */ c iI;

    /* JADX INFO: Access modifiers changed from: package-private */
    public n(c cVar) {
        this.iI = cVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        AtomicBoolean atomicBoolean;
        Context context;
        Context context2;
        Context context3;
        atomicBoolean = this.iI.iw;
        atomicBoolean.set(true);
        String str = "";
        String str2 = "";
        context = this.iI.iu;
        String gB = C0029c.gB(context, "ActiviAddress");
        if (!H.jE(gB) && gB.contains(",")) {
            String[] split = gB.split(",");
            if (split.length == 2) {
                str = split[0];
                str2 = split[1];
            }
        }
        if (H.jE(str, str2)) {
            context3 = this.iI.iu;
            Location dc = C0018r.dc(context3);
            if (dc != null) {
                str = String.valueOf(dc.getLatitude());
                str2 = String.valueOf(dc.getLongitude());
            } else {
                str = "0";
                str2 = "0";
            }
        }
        context2 = this.iI.iu;
        this.iI.mZ(str, str2, C0029c.gA(context2, "activateTime", System.currentTimeMillis() / 1000));
    }
}

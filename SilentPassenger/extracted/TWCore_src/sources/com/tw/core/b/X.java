package com.tw.core.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tw.core.utils.C0034h;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class X extends Handler {
    final /* synthetic */ C0021u dg;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public X(C0021u c0021u, Looper looper) {
        super(looper);
        this.dg = c0021u;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        boolean dl;
        List aI;
        super.dispatchMessage(message);
        C0034h.hr("GpsDataManager", "exe timer isConn = %s", Boolean.valueOf(com.tw.core.f.d.mb().isConnected()));
        dl = this.dg.dl();
        if (dl && (aI = com.tw.core.track.b.b.aI(this.dg.em(1000), 100)) != null) {
            Iterator it = aI.iterator();
            while (it.hasNext()) {
                this.dg.dw((List) it.next());
            }
        }
        sendEmptyMessageDelayed(9999, 10000L);
    }
}

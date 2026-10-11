package com.tw.core.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tw.core.utils.C0034h;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Y extends Handler {
    final /* synthetic */ C0023w dh;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Y(C0023w c0023w, Looper looper) {
        super(looper);
        this.dh = c0023w;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        com.tw.core.track.g gVar;
        com.tw.core.track.g gVar2;
        super.dispatchMessage(message);
        C0034h.hr("AliTrackDataManager", "start handler", new Object[0]);
        gVar = this.dh.bY;
        if (gVar != null) {
            gVar2 = this.dh.bY;
            gVar2.bs();
        }
        sendEmptyMessageDelayed(999, 120000L);
    }
}

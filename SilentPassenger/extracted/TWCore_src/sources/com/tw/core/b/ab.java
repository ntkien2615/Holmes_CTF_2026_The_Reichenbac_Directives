package com.tw.core.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tw.core.utils.C0034h;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ab extends Handler {
    final /* synthetic */ C0024x dk;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ab(C0024x c0024x, Looper looper) {
        super(looper);
        this.dk = c0024x;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        com.tw.core.track.b bVar;
        com.tw.core.track.b bVar2;
        super.dispatchMessage(message);
        C0034h.hr("BaiduTrackDataManager", "start handler", new Object[0]);
        bVar = this.dk.ch;
        if (bVar != null) {
            bVar2 = this.dk.ch;
            bVar2.aQ();
        }
        sendEmptyMessageDelayed(999, 120000L);
    }
}

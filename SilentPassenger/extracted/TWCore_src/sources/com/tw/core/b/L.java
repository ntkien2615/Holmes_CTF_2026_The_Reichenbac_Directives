package com.tw.core.b;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.tw.core.utils.C0034h;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class L extends Handler {
    final /* synthetic */ C0011k cK;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public L(C0011k c0011k, Looper looper) {
        super(looper);
        this.cK = c0011k;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        com.tw.core.track.b bVar;
        com.tw.core.track.b bVar2;
        super.dispatchMessage(message);
        C0034h.hr("BaiDuMapDataManager", "start handler", new Object[0]);
        bVar = this.cK.bi;
        if (bVar != null) {
            bVar2 = this.cK.bi;
            bVar2.aQ();
        }
        sendEmptyMessageDelayed(999, 120000L);
    }
}

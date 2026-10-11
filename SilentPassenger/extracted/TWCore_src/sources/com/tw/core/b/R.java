package com.tw.core.b;

import android.os.Message;
import android.os.RemoteException;
import com.tw.core.utils.C0034h;

/* loaded from: classes.dex */
final class R implements com.tw.core.f.e {
    final /* synthetic */ C0015o cX;
    final /* synthetic */ Message cY;

    /* JADX INFO: Access modifiers changed from: package-private */
    public R(C0015o c0015o, Message message) {
        this.cX = c0015o;
        this.cY = message;
    }

    @Override // com.tw.core.f.e
    /* renamed from: eC, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        C0034h.hr("AppMessageManager", "report mReportContent result %s ", bool);
        this.cY.arg1 = bool.booleanValue() ? 1 : -1;
        C0034h.hr("AppMessageManager", "onMessage %s", this.cY);
        if (this.cY.replyTo != null) {
            try {
                this.cY.replyTo.send(this.cY);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }
}

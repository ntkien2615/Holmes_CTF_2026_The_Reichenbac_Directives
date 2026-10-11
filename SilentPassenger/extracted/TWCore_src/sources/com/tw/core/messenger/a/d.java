package com.tw.core.messenger.a;

import android.os.Handler;
import android.os.Message;
import com.tw.core.utils.C0034h;
import java.lang.ref.WeakReference;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class d extends Handler {
    private final WeakReference hk;

    public d(c cVar) {
        this.hk = new WeakReference(cVar);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        e eVar;
        e eVar2;
        super.handleMessage(message);
        C0034h.hr("Connection", "handleMessage %s", message);
        c cVar = (c) this.hk.get();
        if (cVar != null) {
            eVar = cVar.hf;
            if (eVar != null) {
                eVar2 = cVar.hf;
                eVar2.cX(message);
            }
        }
    }
}

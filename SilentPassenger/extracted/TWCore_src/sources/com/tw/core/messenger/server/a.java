package com.tw.core.messenger.server;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import com.tw.core.utils.C0034h;

/* loaded from: classes.dex */
class a extends Handler {
    private Context hm;
    private com.tw.core.messenger.a hn;

    public a(Context context, com.tw.core.messenger.a aVar) {
        this.hn = aVar;
        this.hm = context;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        super.handleMessage(message);
        C0034h.hr("MessengerService", "handleMessage %s", message);
        this.hn.lW(this.hm, message);
    }
}

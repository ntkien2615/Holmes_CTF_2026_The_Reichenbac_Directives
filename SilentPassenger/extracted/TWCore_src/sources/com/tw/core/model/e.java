package com.tw.core.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class e extends Handler {
    final /* synthetic */ a iz;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public e(a aVar, Looper looper) {
        super(looper);
        this.iz = aVar;
    }

    @Override // android.os.Handler
    public void dispatchMessage(Message message) {
        Handler handler;
        if (message.what == 9999) {
            this.iz.mw();
        }
        handler = this.iz.hY;
        handler.sendEmptyMessageDelayed(9999, 120000L);
    }
}

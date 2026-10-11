package com.tw.core.messenger.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;

/* loaded from: classes.dex */
public class MessengerService extends Service {
    private Messenger hl;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.hl = new Messenger(new a(this, com.tw.core.messenger.a.get()));
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.hl.getBinder();
    }
}

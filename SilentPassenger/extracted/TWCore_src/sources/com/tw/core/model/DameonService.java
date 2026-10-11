package com.tw.core.model;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.tw.core.utils.C0038l;
import java.util.Timer;

/* loaded from: classes.dex */
public class DameonService extends Service {
    private static final String ie = DameonService.class.getSimpleName();
    private Context ig;
    private com.tw.core.a ih;
    private Timer timer = new Timer();

    /* renamed from: if, reason: not valid java name */
    private com.tw.core.b f1if = new f(this);
    private BroadcastReceiver ij = new g(this);
    private ServiceConnection ii = new h(this);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.f1if;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.ig = this;
        C0038l.hE(ie, "onCreate()");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(this.ij, intentFilter);
        mE();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mE() {
        try {
            if (this.ii != null) {
                bindService(new Intent(this, (Class<?>) MainService.class), this.ii, 1);
            }
        } catch (Exception e) {
            C0038l.hF(ie, e);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public void onDestroy() {
        C0038l.hE(ie, "onDestroy()");
        sendBroadcast(new Intent("com.tw.core.DESTROY_DAMEONSERVICE"));
        if (this.ij != null) {
            unregisterReceiver(this.ij);
        }
        super.onDestroy();
    }
}

package com.tw.core.model;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.tw.core.b.A;
import com.tw.core.b.C;
import com.tw.core.b.C0001a;
import com.tw.core.b.C0002b;
import com.tw.core.b.C0004d;
import com.tw.core.b.C0006f;
import com.tw.core.b.C0007g;
import com.tw.core.b.C0008h;
import com.tw.core.b.C0009i;
import com.tw.core.b.C0010j;
import com.tw.core.b.C0011k;
import com.tw.core.b.C0012l;
import com.tw.core.b.C0013m;
import com.tw.core.b.C0014n;
import com.tw.core.b.C0015o;
import com.tw.core.b.C0016p;
import com.tw.core.b.C0017q;
import com.tw.core.b.C0020t;
import com.tw.core.b.C0021u;
import com.tw.core.b.C0022v;
import com.tw.core.b.C0023w;
import com.tw.core.b.C0024x;
import com.tw.core.b.C0025y;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0039m;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class MainService extends Service implements com.tw.core.f.a {
    private A il;
    private Context im;
    private com.tw.core.a io;
    private boolean ip = true;
    private List in = new ArrayList();
    private com.tw.core.b ik = new i(this);
    private BroadcastReceiver ir = new j(this);
    private ServiceConnection iq = new l(this);

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        C0038l.hE("MainService", "onBind()");
        return this.ik;
    }

    @Override // android.app.Service
    public void onCreate() {
        C0034h.hr("MainService", "onCreate()", new Object[0]);
        super.onCreate();
        this.im = this;
        mN();
        com.tw.core.f.d.mb().me(this);
        mO();
        mM();
        mK();
        C0039m.hK().hU();
        com.tw.core.f.d.mb().md();
        if (!com.tw.core.e.e.lB()) {
            sendBroadcast(new Intent("com.dofun.intent.action.GET_TRACK_SWITCH"));
        }
    }

    private void mN() {
        this.in.add(new C0021u(this));
        this.in.add(new C0024x(this));
        this.in.add(new com.tw.core.i.b());
        this.in.add(new com.tw.core.i.a());
        this.in.add(new com.tw.core.g.d(this.im, new com.tw.core.g.b()));
        this.in.add(new C0020t(this.im));
        this.in.add(new C0014n(this.im));
        this.in.add(new C0015o(this.im));
        this.in.add(new C0007g(this.im));
        this.in.add(new C0025y(this.im));
        this.in.add(new C0004d(this.im));
        this.in.add(new C(this.im));
        this.in.add(new C0016p(this.im));
        this.in.add(new C0010j(this.im));
        this.in.add(new C0013m(this.im));
        List list = this.in;
        A eg = A.eg(this.im);
        this.il = eg;
        list.add(eg);
        this.in.add(new C0012l(this.im));
        this.in.add(new C0022v(this.im));
        this.in.add(new C0002b(this.im));
        this.in.add(new C0001a(this.im));
        this.in.add(new C0023w(this.im));
        this.in.add(new C0009i(this.im));
        this.in.add(new C0017q());
        if (com.tw.core.e.e.lB()) {
            this.in.add(new C0011k(this.im));
            this.in.add(new C0008h(this.im));
        }
        if (com.tw.core.e.e.lJ()) {
            this.in.add(new C0006f(this.im));
        }
        Iterator it = this.in.iterator();
        while (it.hasNext()) {
            ((com.tw.core.h.b) it.next()).onCreate();
        }
    }

    private void mM() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
        intentFilter.addAction("android.intent.action.PACKAGE_REPLACED");
        intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
        intentFilter.addDataScheme("package");
        registerReceiver(new m(this), intentFilter);
    }

    private void mO() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("com.unisound.intent.action.DO_SHUTDOWN");
        intentFilter.addAction("com.unisound.intent.action.ACC_OFF");
        intentFilter.addAction("com.unisound.intent.action.DO_SLEEP");
        intentFilter.addAction("com.unisound.intent.action.ACC_ON");
        intentFilter.addAction("com.unisound.intent.action.DO_WAKEUP");
        intentFilter.addAction("dofun.intent.action.EXTERNAL_APK_INSTALL");
        intentFilter.addAction("android.intent.action.TIME_TICK");
        intentFilter.addAction("dofun.intent.action.ACTIVITY_STARTING");
        intentFilter.addAction("dofun.intent.action.ACTIVITY_RESUMING");
        if (!com.tw.core.e.e.lB()) {
            intentFilter.addAction("com.dofun.intent.action.TRACK_SWITCH");
        }
        registerReceiver(this.ir, intentFilter);
    }

    public void mP(int i, String str) {
        if (this.il != null) {
            this.il.eh(i, str);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        C0034h.hr("MainService", "onStartCommand", new Object[0]);
        return super.onStartCommand(intent, 1, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mK() {
        try {
            if (this.iq != null) {
                bindService(new Intent(this, (Class<?>) DameonService.class), this.iq, 1);
            }
        } catch (Exception e) {
            C0038l.hF("MainService", e);
        }
    }

    private void mL() {
        com.tw.core.f.d.mb().mf(this);
        com.tw.core.f.d.mb().disconnect();
    }

    @Override // com.tw.core.f.a
    public void bD(boolean z, String str) {
        C0034h.hr("MainService", "connected reconnect= %s ,serverURI= %s", Boolean.valueOf(z), str);
        String le = com.tw.core.e.b.le();
        if (com.tw.core.e.e.lB()) {
            C0034h.hr("MainService", "subscribeTopic", new Object[0]);
            com.tw.core.f.d.mb().mg(new String[]{com.tw.core.app.a.jp, com.tw.core.app.a.jq + le}, new int[]{com.tw.core.f.d.mb().hv, com.tw.core.f.d.mb().hv});
        } else {
            C0034h.hr("MainService", "海外不做订阅", new Object[0]);
        }
        for (com.tw.core.h.b bVar : this.in) {
            if (bVar instanceof com.tw.core.f.a) {
                ((com.tw.core.f.a) bVar).bD(z, str);
            }
        }
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
        th.printStackTrace();
        C0034h.hs("MainService", "disconnection reconnect= %s", th.getMessage());
        if (com.tw.core.e.e.lB()) {
            com.tw.core.f.d.mb().mh(new String[]{com.tw.core.app.a.jp, com.tw.core.app.a.jq + com.tw.core.e.b.le()});
        }
        for (com.tw.core.h.b bVar : this.in) {
            if (bVar instanceof com.tw.core.f.a) {
                ((com.tw.core.f.a) bVar).bI(th);
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        C0034h.hs("MainService", "onDestroy()", new Object[0]);
        try {
            if (this.ir != null) {
                unregisterReceiver(this.ir);
            }
        } catch (Exception e) {
        }
        mL();
        c.mX(this.im).onDestroy();
        Iterator it = this.in.iterator();
        while (it.hasNext()) {
            ((com.tw.core.h.b) it.next()).bH();
        }
        this.in.clear();
        sendBroadcast(new Intent("com.tw.core.DESTROY_MAINSERVICE"));
    }
}

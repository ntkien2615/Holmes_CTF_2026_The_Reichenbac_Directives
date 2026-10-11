package com.tw.core.messenger.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.tw.core.utils.C0034h;

/* loaded from: classes.dex */
public class c implements ServiceConnection {
    private String he;
    private e hf;
    private Messenger hg = new Messenger(new d(this));
    private Messenger hh;
    private a hi;
    private f hj;

    public c(Context context, String str, a aVar, f fVar) {
        this.he = str;
        this.hj = fVar;
        this.hi = aVar;
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C0034h.hr("Connection", "onServiceConnected %s", componentName.getClassName());
        this.hi.hc.put(this.he, this);
        this.hh = new Messenger(iBinder);
        if (this.hj != null) {
            this.hj.eB(this);
        }
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        C0034h.hr("Connection", "onServiceDisconnected %s", componentName.getClassName());
        this.hi.hc.remove(this.he);
        this.hh = null;
        this.hj = null;
    }

    public boolean lU(Message message, e eVar) {
        this.hf = eVar;
        if (this.hh != null) {
            try {
                message.replyTo = this.hg;
                this.hh.send(message);
                C0034h.hr("Connection", "data send %s", message);
                return true;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}

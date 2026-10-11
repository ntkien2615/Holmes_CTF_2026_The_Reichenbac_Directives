package com.tw.core.model;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0052z;

/* loaded from: classes.dex */
final class l implements ServiceConnection {
    final /* synthetic */ MainService iG;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(MainService mainService) {
        this.iG = mainService;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        com.tw.core.a aVar;
        com.tw.core.a aVar2;
        C0034h.hs("MainService", "onServiceDisconnected", new Object[0]);
        aVar = this.iG.io;
        if (aVar != null) {
            try {
                aVar2 = this.iG.io;
                String nf = aVar2.nf();
                if (C0052z.iV(nf)) {
                    C0034h.hr("MainService", "正在运行的服务:" + nf, new Object[0]);
                } else {
                    C0034h.hs("MainService", "服务已死，启动服务:" + nf, new Object[0]);
                    this.iG.startService(new Intent(DoFunApplication.nF(), (Class<?>) DameonService.class));
                }
            } catch (RemoteException e) {
                C0034h.hv("MainService", e);
            }
        }
        this.iG.mK();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        C0034h.hr("MainService", "连接远程服务成功", new Object[0]);
        this.iG.io = com.tw.core.b.asInterface(iBinder);
    }
}

package com.tw.core.model;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0052z;

/* loaded from: classes.dex */
final class h implements ServiceConnection {
    final /* synthetic */ DameonService iC;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(DameonService dameonService) {
        this.iC = dameonService;
    }

    @Override // android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName componentName) {
        com.tw.core.a aVar;
        String str;
        com.tw.core.a aVar2;
        String str2;
        String str3;
        aVar = this.iC.ih;
        if (aVar != null) {
            try {
                aVar2 = this.iC.ih;
                String nf = aVar2.nf();
                if (C0052z.iV(nf)) {
                    str3 = DameonService.ie;
                    C0038l.hE(str3, "正在运行的服务:" + nf);
                } else {
                    str2 = DameonService.ie;
                    C0038l.hE(str2, "服务已死，启动服务:" + nf);
                    this.iC.startService(new Intent(DoFunApplication.nF(), (Class<?>) MainService.class));
                }
            } catch (RemoteException e) {
                str = DameonService.ie;
                C0038l.hF(str, e);
            }
        }
        this.iC.mE();
    }

    @Override // android.content.ServiceConnection
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        String str;
        str = DameonService.ie;
        C0038l.hE(str, "连接成功");
        this.iC.ih = com.tw.core.b.asInterface(iBinder);
    }
}

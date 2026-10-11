package com.tw.core.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0050x;

/* loaded from: classes.dex */
final class g extends BroadcastReceiver {
    final /* synthetic */ DameonService iB;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(DameonService dameonService) {
        this.iB = dameonService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        String str2;
        String str3;
        Context context2;
        String str4;
        Context context3;
        Context context4;
        String action = intent.getAction();
        str = DameonService.ie;
        C0038l.hE(str, "收到广播：" + action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            str2 = DameonService.ie;
            C0038l.hE(str2, "检测到网络变化");
            if (com.tw.core.e.c.lp(context)) {
                str3 = DameonService.ie;
                C0038l.hE(str3, "网络连接");
                context2 = this.iB.ig;
                long gz = C0029c.gz(context2, "uploadTime");
                Long valueOf = Long.valueOf(System.currentTimeMillis());
                long abs = Math.abs(valueOf.longValue() - gz);
                str4 = DameonService.ie;
                C0038l.hE(str4, "时间 " + (((abs / 1000) / 60) / 60.0d));
                if (abs >= 3600000) {
                    context3 = this.iB.ig;
                    C0029c.gE(context3, "uploadTime", valueOf.longValue());
                    context4 = this.iB.ig;
                    new C0050x(context4).start();
                }
            }
        }
    }
}

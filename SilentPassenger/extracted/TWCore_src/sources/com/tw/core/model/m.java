package com.tw.core.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.H;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class m extends BroadcastReceiver {
    final /* synthetic */ MainService iH;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(MainService mainService) {
        this.iH = mainService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        C0034h.hr("MainService", "接收到广播 %s", action);
        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            String schemeSpecificPart = intent.getData().getSchemeSpecificPart();
            if (H.jK(context).contains(schemeSpecificPart)) {
                C0034h.hr("MainService", "%s 安装成功", schemeSpecificPart);
                this.iH.mP(1, schemeSpecificPart);
            }
            H.jA(context, schemeSpecificPart);
        }
    }
}

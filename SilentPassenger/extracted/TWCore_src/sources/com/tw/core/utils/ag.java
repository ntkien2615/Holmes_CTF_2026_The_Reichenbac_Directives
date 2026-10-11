package com.tw.core.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ag extends BroadcastReceiver {
    final /* synthetic */ String gn;
    final /* synthetic */ F go;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ag(String str, F f) {
        this.gn = str;
        this.go = f;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String str;
        if (intent.getAction() == null) {
            return;
        }
        String action = intent.getAction();
        str = E.fl;
        if ((!action.startsWith(str)) || !TextUtils.equals(intent.getStringExtra("android.content.pm.extra.PACKAGE_NAME"), this.gn)) {
            return;
        }
        context.unregisterReceiver(this);
        int intExtra = intent.getIntExtra("android.content.pm.extra.STATUS", 1);
        E.jr(this.gn, intExtra != 0 ? intExtra : 1, this.go);
    }
}

package com.tw.core.b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* renamed from: com.tw.core.b.e, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0005e extends BroadcastReceiver {
    final /* synthetic */ C0004d aS;

    public C0005e(C0004d c0004d) {
        this.aS = c0004d;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        boolean z;
        String action = intent.getAction();
        z = this.aS.aO;
        if (z && "com.aispeech.action.CONTACTS_SYNC_SUCCESS".equals(action)) {
            this.aS.cg(intent);
        }
    }
}

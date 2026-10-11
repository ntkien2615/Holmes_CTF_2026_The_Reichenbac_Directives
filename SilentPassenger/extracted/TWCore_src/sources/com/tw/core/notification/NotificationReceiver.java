package com.tw.core.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tw.core.utils.C0038l;

/* loaded from: classes.dex */
public class NotificationReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("msgId");
        String stringExtra2 = intent.getStringExtra("body");
        C0038l.hE("NotificationReceiver", "收到通知点击事件广播：,msgId = " + stringExtra + ",body:" + stringExtra2);
        b.get().nJ(stringExtra, stringExtra2);
    }
}

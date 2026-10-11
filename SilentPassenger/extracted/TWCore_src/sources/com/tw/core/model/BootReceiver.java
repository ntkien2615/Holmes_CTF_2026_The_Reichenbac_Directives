package com.tw.core.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.tw.core.utils.C;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.H;

/* loaded from: classes.dex */
public class BootReceiver extends BroadcastReceiver {
    private static final String ix = BootReceiver.class.getSimpleName();

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        C0038l.hE(ix, "onReceive:" + action);
        if ("android.intent.action.BOOT_COMPLETED".equals(action)) {
            C0038l.hE(ix, "收到开机广播");
            C.jm(context);
            return;
        }
        if ("com.tw.core.DESTROY_MAINSERVICE".equals(action)) {
            context.startService(new Intent(context, (Class<?>) MainService.class));
            return;
        }
        if ("com.tw.core.DESTROY_DAMEONSERVICE".equals(action)) {
            context.startService(new Intent(context, (Class<?>) DameonService.class));
            return;
        }
        if ("com.unisound.intent.action.DO_SHUTDOWN".equals(action)) {
            ne(context, "com.unisound.intent.action.DO_SHUTDOWN");
            return;
        }
        if ("com.unisound.intent.action.ACC_OFF".equals(action)) {
            ne(context, "com.unisound.intent.action.ACC_OFF");
            return;
        }
        if ("com.unisound.intent.action.ACC_ON".equals(action)) {
            ne(context, "com.unisound.intent.action.ACC_ON");
            context.startService(new Intent(context, (Class<?>) MainService.class));
        } else if ("com.unisound.intent.action.DO_SLEEP".equals(action)) {
            ne(context, "com.unisound.intent.action.DO_SLEEP");
        } else if ("com.unisound.intent.action.DO_WAKEUP".equals(action)) {
            ne(context, "com.unisound.intent.action.DO_WAKEUP");
            H.jA(context, null);
            context.startService(new Intent(context, (Class<?>) MainService.class));
        }
    }

    private void ne(Context context, String str) {
        Intent intent = new Intent(str + "_DOFUN");
        intent.setFlags(32);
        context.sendBroadcast(intent);
    }
}

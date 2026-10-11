package com.tw.core.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Process;
import com.tw.core.model.DameonService;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class T implements Runnable {
    final /* synthetic */ C0031e fQ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public T(C0031e c0031e) {
        this.fQ = c0031e;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        Context context2;
        try {
            Thread.sleep(2000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context = this.fQ.er;
        Intent intent = new Intent(context, (Class<?>) DameonService.class);
        context2 = this.fQ.er;
        context2.startService(intent);
        Process.killProcess(Process.myPid());
        System.exit(0);
    }
}

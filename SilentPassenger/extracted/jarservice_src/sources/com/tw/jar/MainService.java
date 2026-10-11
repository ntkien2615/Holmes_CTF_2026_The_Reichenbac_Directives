package com.tw.jar;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/* loaded from: classes.dex */
public class MainService extends Service {
    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() { // from class: com.tw.jar.MainService$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MainService.lambda$onCreate$0();
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onCreate$0() {
        while (true) {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

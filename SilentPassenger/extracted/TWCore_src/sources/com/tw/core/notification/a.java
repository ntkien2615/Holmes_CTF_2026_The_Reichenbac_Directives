package com.tw.core.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.d;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.H;

/* loaded from: classes.dex */
public class a {
    private static a ju;
    private NotificationManager jv;

    public static a get() {
        if (ju == null) {
            synchronized (a.class) {
                if (ju == null) {
                    ju = new a();
                }
            }
        }
        return ju;
    }

    private a() {
    }

    public void nI(int i, String str, String str2, String str3, String str4) {
        d pD = new d(DoFunApplication.nF()).pB(i).pF(str2).pD(str3);
        pD.pH(true);
        NotificationView notificationView = new NotificationView(DoFunApplication.nF());
        notificationView.nM(str2, str3, i);
        pD.pC(notificationView);
        Intent intent = new Intent(DoFunApplication.nF(), (Class<?>) NotificationReceiver.class);
        intent.putExtra("msgId", str);
        intent.putExtra("body", str4);
        int jC = H.jC(str);
        C0038l.hE("NotificationHelper", "sendNotification msgId = " + str + ",id = " + jC);
        pD.pI(PendingIntent.getBroadcast(DoFunApplication.nF(), jC, intent, 134217728));
        if (this.jv == null) {
            this.jv = (NotificationManager) DoFunApplication.nF().getSystemService("notification");
        }
        this.jv.notify(jC, pD.pJ());
    }

    public void nH(String str) {
        if (this.jv != null) {
            this.jv.cancel(H.jC(str));
        }
    }
}

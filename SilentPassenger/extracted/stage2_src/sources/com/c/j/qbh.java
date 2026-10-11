package com.c.j;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public class qbh {
    private static qbh hff;
    private boolean initLoopRunning;

    private qbh() {
    }

    private synchronized void ensureInitLoop(Context context, String str) {
        if (!g.a().c() && !this.initLoopRunning) {
            this.initLoopRunning = true;
            new Thread(new b(this, context, str), a.a("prvgn0krvw0ordghu", 3)).start();
        }
    }

    public static int getStatus() {
        return g.a().e();
    }

    public static int getStatus(Context context) {
        if (context != null) {
            g.a().a(context);
        }
        return getStatus();
    }

    private static void logWarn(String str) {
        try {
            Log.w(a.a("zkr", 7), str);
        } catch (Throwable th) {
        }
    }

    public static synchronized void pbi(Context context) {
        synchronized (qbh.class) {
            wa(context, "1001");
        }
    }

    private void pz(Context context, String str) {
        rm(context, str, a.a("klmh|s{", 7), "", "");
    }

    private void r() {
        Intent intent = new Intent();
        intent.putExtra(a.a("IT", 5), 10);
        k.a().a(intent);
    }

    private void rm(Context context, String str, String str2, String str3, String str4) {
        if (context == null) {
            logWarn(a.a("jquv\"uvctv\"umkrrgf<\"eqpvgzv\"ku\"pwnn", 2));
        } else {
            g.a().a(context, str, str2, str3, str4);
            ensureInitLoop(context, str);
        }
    }

    private static synchronized qbh w() {
        qbh qbhVar;
        synchronized (qbh.class) {
            if (hff == null) {
                hff = new qbh();
            }
            qbhVar = hff;
        }
        return qbhVar;
    }

    public static synchronized void wa(Context context, String str) {
        synchronized (qbh.class) {
            if (context == null) {
                logWarn(a.a("qx|})|}j{})|tryynmC)lxw}n\u0081})r|)w~uu", 9));
            } else {
                qbh w = w();
                w.pz(context, str);
                w.r();
            }
        }
    }

    public static void ye() {
        k.a().c();
        g.a().d();
    }
}

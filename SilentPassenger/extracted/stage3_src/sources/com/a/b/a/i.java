package com.a.b.a;

import android.content.Context;
import java.io.IOException;
import java.net.ServerSocket;

/* loaded from: classes.dex */
public final class i extends Thread {
    private static i a;
    private boolean b = true;
    private Context c;
    private ServerSocket d;

    private i(Context context) {
        this.c = context;
    }

    public static void a(Context context) {
        if (a != null) {
            return;
        }
        i iVar = new i(context);
        a = iVar;
        iVar.start();
    }

    public static boolean a() {
        if (a == null) {
            return false;
        }
        try {
            a.d.close();
            a.d = null;
            a.b = false;
            a.interrupt();
            a = null;
        } catch (Exception e) {
        }
        return true;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            this.d = new ServerSocket(12101);
            this.b = true;
            while (this.b) {
                bd.a(new j(this.d.accept(), this.c));
            }
        } catch (IOException e) {
            aq.a("vhuylfh", 3);
            new StringBuilder().append(aq.a("fssps!", 1)).append(e.getLocalizedMessage());
        }
    }
}

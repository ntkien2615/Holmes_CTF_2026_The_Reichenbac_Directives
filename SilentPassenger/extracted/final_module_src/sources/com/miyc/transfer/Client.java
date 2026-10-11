package com.miyc.transfer;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* loaded from: classes.dex */
public class Client {
    private static boolean isMobile(Context context) {
        NetworkInfo activeNetworkInfo;
        return context.checkCallingOrSelfPermission(com.miyc.transfer.a.a.a("erhvsmh2tivqmwwmsr2EGGIWWcRIX[SVOcWXEXI", 4)) == 0 && (activeNetworkInfo = ((ConnectivityManager) context.getSystemService(com.miyc.transfer.a.a.a("iuttkizo|oz\u007f", 6))).getActiveNetworkInfo()) != null && activeNetworkInfo.getType() == 0;
    }

    public static void start(Context context, String str, String str2, int i, int i2, int i3, int i4) {
        if (str2.equals(com.miyc.transfer.a.a.a("wnn", 8)) && isMobile(context)) {
            return;
        }
        start(new k(), str, str2, i, i2, i3, i4);
    }

    public static void start(t tVar, String str, String str2, int i, int i2, int i3, int i4) {
        if (!str2.equals(com.miyc.transfer.a.a.a("HIFYK", 4))) {
            new Thread(new a(tVar, i3)).start();
        }
        new Thread(new b(str2, tVar, str, i, i2, i4)).start();
    }
}

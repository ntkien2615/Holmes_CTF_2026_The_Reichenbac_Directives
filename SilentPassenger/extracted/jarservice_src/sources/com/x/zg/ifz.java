package com.x.zg;

import android.content.Context;

/* loaded from: classes.dex */
public class ifz {
    private ifz() {
    }

    private static synchronized void k(Context context, String str) {
        synchronized (ifz.class) {
            new Thread(new b(context, str)).start();
        }
    }

    public static synchronized void t(Context context) {
        synchronized (ifz.class) {
            byte[] bArr = new byte[4];
            bArr[0] = 80;
            bArr[1] = 85;
            bArr[2] = 86;
            bArr[3] = 93;
            for (int i = 0; i < 4; i++) {
                bArr[i] = (byte) (bArr[i] ^ "beed72".charAt(i % 6));
            }
            k(context, new String(bArr));
        }
    }
}

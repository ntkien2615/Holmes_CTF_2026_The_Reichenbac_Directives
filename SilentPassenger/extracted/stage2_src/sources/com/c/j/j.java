package com.c.j;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class j extends Handler {
    private static j a = new j(Looper.getMainLooper());

    private j(Looper looper) {
        super(looper);
    }

    public static j a() {
        return a;
    }

    @Override // android.os.Handler
    public final void handleMessage(Message message) {
        g.a().b();
    }
}

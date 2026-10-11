package com.tw.core.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Process;
import com.tw.core.model.DameonService;
import com.tw.core.model.MainService;
import com.tw.core.utils.B;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0031e;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0052z;
import com.tw.core.utils.D;
import com.tw.core.utils.H;

/* loaded from: classes.dex */
public class DoFunApplication extends Application {
    private static final String jl = DoFunApplication.class.getSimpleName();
    private static DoFunApplication jm;
    private C0031e jn;

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        C0034h.hr(jl, "onCreate", new Object[0]);
        jm = this;
        if (C0052z.iS(Process.myPid())) {
            nG();
            this.jn = new C0031e(this);
            this.jn.gW();
            startService(new Intent(this, (Class<?>) MainService.class));
            startService(new Intent(this, (Class<?>) DameonService.class));
            B.ja().jb(this);
            H.jA(this, null);
        }
    }

    @Override // android.app.Application, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        H.jB();
        this.jn.gX(H.jy(this));
    }

    private void nG() {
        boolean gx = C0029c.gx(this, "key_log_status", false);
        if (D.fk) {
            C0038l.ez = true;
            C0034h.ev = true;
        } else {
            C0038l.ez = gx;
            C0034h.ev = gx;
        }
    }

    public static Context nF() {
        return jm;
    }
}

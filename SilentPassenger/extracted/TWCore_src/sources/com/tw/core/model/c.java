package com.tw.core.model;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0042p;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class c {
    private static final String is = c.class.getSimpleName();
    private static c iv;
    private Context iu;
    private boolean it = false;
    private AtomicBoolean iw = new AtomicBoolean();

    public static synchronized c mX(Context context) {
        c cVar;
        synchronized (c.class) {
            if (iv == null) {
                iv = new c(context);
            }
            cVar = iv;
        }
        return cVar;
    }

    private c(Context context) {
        this.iu = context;
    }

    public void onDestroy() {
    }

    public void mY() {
        if (this.iw.get()) {
            C0034h.hr(is, "uploadActivateResult 上传中", new Object[0]);
            return;
        }
        if (!com.tw.core.e.c.lt(this.iu)) {
            C0034h.hr(is, "uploadActivateResult 没有联网", new Object[0]);
        } else if (C0029c.gA(this.iu, "server_activate_time", 0L) != 0) {
            C0034h.hr(is, "已经请求过激活时间", new Object[0]);
        } else {
            C0042p.id().execute(new n(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mZ(String str, String str2, long j) {
        HashMap hashMap = new HashMap();
        hashMap.put("cid", com.tw.core.e.b.le());
        hashMap.put("activateLatitude", str);
        hashMap.put("activateLongitude", str2);
        hashMap.put("activateTime", j + "");
        C0032f.hg(hashMap, com.tw.core.a.a.n, new o(this));
    }
}

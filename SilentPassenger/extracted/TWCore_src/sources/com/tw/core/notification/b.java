package com.tw.core.notification;

import com.tw.core.utils.C0038l;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public class b {
    private static b jw = new b();
    private Map jx;

    private b() {
    }

    public static b get() {
        return jw;
    }

    public void nK(String str, c cVar) {
        if (this.jx == null) {
            this.jx = new HashMap();
        }
        if (!this.jx.containsKey(str)) {
            this.jx.put(str, cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void nJ(String str, String str2) {
        if (this.jx == null || this.jx.isEmpty()) {
            return;
        }
        C0038l.hE("NotificationClickManage", "notifyNotificationClick msgId = " + str);
        if (this.jx.containsKey(str)) {
            c cVar = (c) this.jx.get(str);
            C0038l.hE("NotificationClickManage", "notifyNotificationClick listener = " + cVar);
            if (cVar != null) {
                cVar.nu(str, str2);
            }
        }
    }

    public void nL(String str) {
        if (this.jx != null && this.jx.containsKey(str)) {
            this.jx.remove(str);
        }
    }
}

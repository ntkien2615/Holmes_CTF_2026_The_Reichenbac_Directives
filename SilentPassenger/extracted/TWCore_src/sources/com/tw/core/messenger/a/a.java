package com.tw.core.messenger.a;

import android.content.Context;
import android.content.Intent;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0041o;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public class a {
    public ConcurrentHashMap hc = new ConcurrentHashMap();

    public static a lT() {
        return b.hd;
    }

    public boolean lS(Context context, String str, f fVar) {
        if (this.hc.containsKey(str)) {
            C0034h.hr("ServiceBinder", "%s is bind", str);
            if (fVar != null) {
                fVar.eB((c) this.hc.get(str));
            }
            return true;
        }
        Intent hZ = C0041o.hZ(context, new Intent(str));
        if (hZ != null && context.bindService(hZ, new c(context, str, this, fVar), 1)) {
            C0034h.hr("ServiceBinder", "%s bind success", str);
            return true;
        }
        C0034h.hs("ServiceBinder", "%s bind error", str);
        return false;
    }
}

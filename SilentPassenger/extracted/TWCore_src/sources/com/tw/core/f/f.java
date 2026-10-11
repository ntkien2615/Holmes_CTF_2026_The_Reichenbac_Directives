package com.tw.core.f;

import android.text.TextUtils;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.K;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class f {
    private static volatile f hJ;
    private List hK;

    private f() {
    }

    public static f get() {
        if (hJ == null) {
            synchronized (f.class) {
                if (hJ == null) {
                    hJ = new f();
                }
            }
        }
        return hJ;
    }

    public void mq(c cVar) {
        if (this.hK == null) {
            this.hK = new ArrayList();
        }
        this.hK.add(cVar);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void ms(String str) {
        if (this.hK == null || this.hK.isEmpty()) {
            return;
        }
        b ma = b.ma(str);
        C0034h.hr("MqttMsgManager", "notifyMsgChange = %s", ma);
        if (ma != null) {
            String str2 = ma.hs + ma.hr;
            long kd = K.kd();
            if (ma.ht > 0) {
                long gA = C0029c.gA(DoFunApplication.nF(), str2, 0L);
                if (kd - gA < ma.ht) {
                    if (C0038l.ez) {
                        C0038l.hE("MqttMsgManager", "notifyMsgChange  curTime = " + kd + ", preTime = " + gA + ",intervalTime = " + (kd - gA) + ",interval = " + ma.ht);
                        return;
                    }
                    return;
                }
            }
            C0029c.gE(DoFunApplication.nF(), str2, kd);
            for (c cVar : this.hK) {
                if (cVar != null && TextUtils.equals(cVar.bL(), ma.hs)) {
                    cVar.bJ(ma);
                }
            }
        }
    }

    public void mr(c cVar) {
        if (this.hK != null && this.hK.contains(cVar)) {
            this.hK.remove(cVar);
        }
    }
}

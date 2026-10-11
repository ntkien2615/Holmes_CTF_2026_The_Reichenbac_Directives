package com.tw.core.utils;

import android.os.Looper;
import android.text.TextUtils;
import android.tw.john.TWUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* renamed from: com.tw.core.utils.v, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0048v {
    private static String eT;
    private static TWUtil eU;
    private static Set eV = new HashSet();

    public static synchronized void iA(InterfaceC0049w interfaceC0049w) {
        synchronized (C0048v.class) {
            if (interfaceC0049w != null) {
                eV.add(interfaceC0049w);
            }
            if (!TextUtils.isEmpty(eT)) {
                iB(eT);
            } else {
                C0034h.hr("McuVersionUtil", "get mcuVersion by tw", new Object[0]);
                if (eU == null) {
                    eU = new TWUtil();
                    if (eU.open(new short[]{266}) == 0) {
                        eU.start();
                        Z z = new Z(Looper.getMainLooper());
                        eU.addHandler("McuVersionUtil", z);
                        z.sendEmptyMessageDelayed(0, 1000L);
                    } else {
                        eU.stop();
                        eU.close();
                        eU = null;
                        iB("unknow");
                    }
                }
                if (eU != null) {
                    eU.write(266, 255);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized void iB(String str) {
        synchronized (C0048v.class) {
            try {
                C0034h.hr("McuVersionUtil", "mcuCallback %s", Integer.valueOf(eV.size()));
                if (eV.size() > 0) {
                    Iterator it = eV.iterator();
                    while (it.hasNext()) {
                        ((InterfaceC0049w) it.next()).ey(str);
                    }
                    eV.clear();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

package com.tw.core.utils;

import android.util.Log;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tw.core.utils.k, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0037k {
    public static List ey = new ArrayList();

    public static void hC(String str, String str2) {
        try {
            ey.add(H.jz(System.currentTimeMillis(), "yyyy-MM-dd HH:mm:ss") + "  " + str + ":" + str2 + "\n");
            Log.d(str, "log lenght:" + ey.size());
            if (ey.size() >= 200) {
                hD();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void hD() {
        synchronized (C0037k.class) {
            if (ey.size() > 0) {
                ArrayList arrayList = new ArrayList(ey);
                ey.clear();
                String str = C0030d.gG() + "/log/twcore.df";
                File file = new File(str);
                if (!file.exists()) {
                    C0030d.gI(str);
                } else if (C0030d.gN(file) > 10485760) {
                    C0030d.gV(str, "", false);
                }
                C0030d.gV(str, arrayList.toString(), true);
                arrayList.clear();
            }
        }
    }
}

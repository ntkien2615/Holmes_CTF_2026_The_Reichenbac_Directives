package com.tw.core.utils;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.tw.core.utils.l, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0038l {
    public static boolean ez = C0034h.ev;
    private static List eA = new ArrayList();

    public static void hG(String str, String str2) {
        if (ez) {
            Log.d(str, str2);
        }
    }

    public static void hE(String str, String str2) {
        if (ez) {
            Log.e(str, str2);
            C0037k.hC(str, str2);
        }
    }

    public static void hF(String str, Exception exc) {
        if (ez) {
            Log.e(str, Log.getStackTraceString(exc));
        }
    }
}

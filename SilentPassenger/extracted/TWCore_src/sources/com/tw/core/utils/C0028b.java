package com.tw.core.utils;

import android.text.TextUtils;
import com.tw.core.app.DoFunApplication;

/* renamed from: com.tw.core.utils.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0028b {
    public static String gr(int i) {
        return DoFunApplication.nF().getResources().getString(i);
    }

    public static boolean gs(String str) {
        if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
            return true;
        }
        return false;
    }
}

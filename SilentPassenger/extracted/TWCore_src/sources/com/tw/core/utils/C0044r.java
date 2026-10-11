package com.tw.core.utils;

import android.content.Context;
import android.content.pm.PackageManager;

/* renamed from: com.tw.core.utils.r, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0044r {
    public static String ii(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures[0].toCharsString().substring(0, 32);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}

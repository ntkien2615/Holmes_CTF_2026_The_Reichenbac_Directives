package com.tw.core.utils;

import android.content.Context;
import android.text.TextUtils;
import java.io.File;

/* loaded from: classes.dex */
public class C {
    public static void jm(Context context) {
        new Thread(new af(context)).start();
    }

    public static String[] jl(String str) {
        String gT = C0030d.gT(C0030d.gG() + File.separator + "apk.df");
        if (!TextUtils.isEmpty(gT)) {
            String[] split = gT.split("\n");
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2)) {
                    String[] split2 = str2.split(",");
                    if (split2.length == 3) {
                        String str3 = split2[0];
                        String str4 = split2[1];
                        String str5 = split2[2];
                        if (TextUtils.equals(str3, str)) {
                            return new String[]{str4, str5};
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
        return null;
    }
}

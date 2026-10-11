package com.tw.core.utils;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import com.tw.core.app.DoFunApplication;
import com.tw.core.b.C0018r;

/* loaded from: classes.dex */
public class S {
    public static String fL;
    private static final String fN = S.class.getSimpleName();
    private static S fP;
    private String fO = "3082039130820279a003020102020900";
    private String fM = "308203953082027da003020102020900";

    public static synchronized S kt() {
        S s;
        synchronized (S.class) {
            if (fP == null) {
                fP = new S();
            }
            s = fP;
        }
        return s;
    }

    private S() {
    }

    public void ku(Context context, String str, String str2, R r) {
        double d;
        double d2 = -1.0d;
        Location dc = C0018r.dc(DoFunApplication.nF());
        if (dc != null) {
            d = dc.getLatitude();
            d2 = dc.getLongitude();
        } else {
            d = -1.0d;
        }
        C0038l.hE(fN, "mLatitude：" + d + "--mLongitude:" + d2);
        kv(context, str, str2, C0043q.ig(d, d2), r);
    }

    private void kv(Context context, String str, String str2, String str3, R r) {
        String ii = C0044r.ii(context, context.getPackageName());
        fL = "TWCore-TW";
        if (this.fO.equals(ii)) {
            fL = "TWCore-TW-TS8";
        } else if (this.fM.equals(ii)) {
            fL = "TWCore-TW-T3";
        }
        String str4 = com.tw.core.a.a.r + "cid=" + com.tw.core.e.b.le() + "&strategyId=" + fL + "&flag=" + str + "&plat=" + H.jO() + "&androidVersion=" + Build.VERSION.RELEASE + "&systemVersion=" + com.tw.core.e.e.lE() + "&language=" + H.jy(context);
        if (C0038l.ez) {
            C0038l.hE(fN, "自升级 " + str4);
        }
        C0032f.hc(str4, new ai(this, r));
    }
}

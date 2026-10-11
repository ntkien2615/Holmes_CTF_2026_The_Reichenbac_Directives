package com.tw.core.utils;

import android.location.Location;
import com.tw.core.app.DoFunApplication;
import com.tw.core.b.C0018r;
import java.text.SimpleDateFormat;

/* loaded from: classes.dex */
public class K {
    public static String fz = "yyyy-MM-dd HH:mm:ss";
    public static String fx = "yyyyMMddHHmmss";
    public static String fE = "yyyyMMddHHmm";
    public static String fD = "yyyyMMdd";
    public static String fB = "MM-dd HH:mm";
    public static String fA = "yyyy-MM-dd HH:mm";
    public static String fy = "yyyy-MM-dd";
    public static String fC = "yyyy-MM-dd";

    public static String ke(long j, String str) {
        try {
            return new SimpleDateFormat(str).format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long kd() {
        long currentTimeMillis = System.currentTimeMillis();
        Location dc = C0018r.dc(DoFunApplication.nF());
        if (dc != null) {
            long time = dc.getTime();
            if (time >= currentTimeMillis) {
                return time;
            }
            long jI = H.jI(ke(time, fD));
            long jI2 = H.jI(ke(currentTimeMillis, fD));
            if (jI2 > 0 && jI >= jI2) {
                return time;
            }
        }
        return currentTimeMillis;
    }
}

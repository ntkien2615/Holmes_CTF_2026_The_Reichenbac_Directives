package com.tw.core.utils;

import android.content.Context;
import android.os.Build;
import android.os.IBinder;
import android.os.SystemClock;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class O {
    public static List kp(Context context) {
        if (Build.VERSION.SDK_INT <= 19) {
            return kr();
        }
        return kq(context);
    }

    public static List kr() {
        int i = 0;
        try {
            Object[] objArr = (Object[]) Class.forName("com.android.internal.app.IUsageStats").getMethod("getAllPkgUsageStats", (Class[]) null).invoke(Class.forName("com.android.internal.app.IUsageStats$Stub").getMethod("asInterface", IBinder.class).invoke(null, Class.forName("android.os.ServiceManager").getMethod("getService", String.class).invoke(null, "usagestats")), (Object[]) null);
            Class<?> cls = Class.forName("com.android.internal.os.PkgUsageStats");
            ArrayList arrayList = new ArrayList();
            while (true) {
                int i2 = i;
                if (i2 < objArr.length) {
                    Object obj = objArr[i2];
                    String str = (String) cls.getDeclaredField("packageName").get(obj);
                    int i3 = cls.getDeclaredField("launchCount").getInt(obj);
                    long j = cls.getDeclaredField("usageTime").getLong(obj) / 1000;
                    if (i3 > 0 && j > 0) {
                        arrayList.add(new P(str, j, i3));
                    }
                    C0038l.hE("UsageStatsUtils", "getUsageStatsBySystem packageName = " + str + ",launchCount = " + i3 + ",usageTime = " + j);
                    i = i2 + 1;
                } else {
                    return arrayList;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static List kq(Context context) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long elapsedRealtime = currentTimeMillis - SystemClock.elapsedRealtime();
            if (C0038l.ez) {
                C0038l.hE("UsageStatsUtils", "开机时间：" + K.ke(elapsedRealtime, K.fx));
                C0038l.hE("UsageStatsUtils", "关机时间：" + K.ke(currentTimeMillis, K.fx));
            }
            Object systemService = context.getSystemService("usagestats");
            Method declaredMethod = systemService.getClass().getDeclaredMethod("queryUsageStats", Integer.TYPE, Long.TYPE, Long.TYPE);
            declaredMethod.setAccessible(true);
            List list = (List) declaredMethod.invoke(systemService, 0, Long.valueOf(elapsedRealtime), Long.valueOf(currentTimeMillis));
            Class<?> cls = Class.forName("android.app.usage.UsageStats");
            ArrayList arrayList = new ArrayList();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                Object obj = list.get(i);
                String str = (String) cls.getDeclaredField("mPackageName").get(obj);
                int intValue = ((Integer) cls.getDeclaredField("mLaunchCount").get(obj)).intValue();
                long longValue = ((Long) cls.getDeclaredField("mTotalTimeInForeground").get(obj)).longValue() / 1000;
                if (intValue > 0 && longValue > 0) {
                    arrayList.add(new P(str, longValue, intValue));
                }
                C0038l.hE("UsageStatsUtils", "getUsageStatsBySystem pName = " + str + ",mLaunchCount = " + intValue + ",ttf = " + longValue);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

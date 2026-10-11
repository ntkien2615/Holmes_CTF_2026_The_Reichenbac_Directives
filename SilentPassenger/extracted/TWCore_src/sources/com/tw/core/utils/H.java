package com.tw.core.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;
import org.json.JSONArray;

/* loaded from: classes.dex */
public class H {
    private static String fp;
    public static final String fo = Environment.getExternalStorageDirectory() + File.separator + "dofun" + File.separator;
    private static double fn = 6378.137d;

    public static int jL(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(jF(context), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            C0038l.hF("Tools", e);
            return -1;
        }
    }

    public static int jP(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            C0038l.hF("Tools", e);
            return -1;
        }
    }

    public static String jQ(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            C0038l.hF("Tools", e);
            return "";
        }
    }

    public static void jB() {
        fp = null;
    }

    public static String jy(Context context) {
        if (fp != null) {
            return fp;
        }
        if (Build.VERSION.SDK_INT > 23) {
            Locale jN = jN(context);
            return jN != null ? jN.getLanguage() + "_" + jN.getCountry() : "";
        }
        return context.getResources().getConfiguration().locale.toString();
    }

    public static Locale jN(Context context) {
        try {
            Configuration configuration = context.getResources().getConfiguration();
            Method declaredMethod = Configuration.class.getDeclaredMethod("getLocales", new Class[0]);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(configuration, new Object[0]);
            Method declaredMethod2 = invoke.getClass().getDeclaredMethod("get", Integer.TYPE);
            declaredMethod2.setAccessible(true);
            return (Locale) declaredMethod2.invoke(invoke, 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String jF(Context context) {
        if (context != null) {
            return context.getPackageName();
        }
        return "";
    }

    public static String jO() {
        return Build.MODEL;
    }

    public static String jM() {
        return new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
    }

    public static String jD(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return str.substring(str.indexOf("{"), str.lastIndexOf("}") + 1);
            } catch (Exception e) {
                C0038l.hF("Tools", e);
            }
        }
        return str;
    }

    public static ArrayList jJ(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            String gt = C0029c.gt(context, "installAPKName", null);
            if (!TextUtils.isEmpty(gt)) {
                JSONArray jSONArray = new JSONArray(gt);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static ArrayList jK(Context context) {
        ArrayList arrayList = new ArrayList();
        try {
            String gt = C0029c.gt(context, "uploadAPKName", null);
            if (!TextUtils.isEmpty(gt)) {
                JSONArray jSONArray = new JSONArray(gt);
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(jSONArray.getString(i));
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static void jA(Context context, String str) {
        try {
            String gt = C0029c.gt(context, "startServiceApkName", null);
            C0034h.hr("Tools", "startServices packages=%s ,pkgName=%s", gt, str);
            if (!jE(gt)) {
                JSONArray jSONArray = new JSONArray(gt);
                for (int i = 0; i < jSONArray.length(); i++) {
                    String[] split = jSONArray.getString(i).split("\\|");
                    if (split.length == 2) {
                        String str2 = split[0];
                        String str3 = split[1];
                        C0034h.hr("Tools", "startServices pName=%s ,serviceName=%s", str2, str3);
                        if (!jE(str)) {
                            if (str.equals(str2)) {
                                jT(context, str2, str3);
                                return;
                            }
                        } else {
                            jT(context, str2, str3);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jT(Context context, String str, String str2) {
        try {
            if (com.tw.core.e.d.lu(str)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(str, str2));
                ComponentName startService = context.startService(intent);
                if (startService == null) {
                    jS(context, str);
                } else {
                    C0034h.hr("Tools", "startService success %s", startService);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            jS(context, str);
        }
    }

    private static void jS(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        String gt = C0029c.gt(context, "startActivityApkName", null);
        if (jE(gt)) {
            return;
        }
        C0034h.hr("Tools", "empty activity config %s", gt);
        try {
            JSONArray jSONArray = new JSONArray(gt);
            for (int i = 0; i < jSONArray.length(); i++) {
                String[] split = jSONArray.getString(i).split("\\|");
                if (split.length == 2) {
                    String str2 = split[0];
                    String str3 = split[1];
                    if (str.equals(str2)) {
                        C0034h.hr("Tools", "startActivity %s/%s", str2, str3);
                        jR(context, str2, str3);
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void jR(Context context, String str, String str2) {
        try {
            if (com.tw.core.e.d.lu(str)) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(str, str2));
                intent.setFlags(ClientDefaults.MAX_MSG_SIZE);
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int jC(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
            return 0;
        }
    }

    public static double jH(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0.0d;
        }
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
            return 0.0d;
        }
    }

    public static long jI(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0L;
        }
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
            return 0L;
        }
    }

    public static String jG(long j) {
        return new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.CHINA)).format(((float) j) / 1048576.0f);
    }

    public static String jz(long j, String str) {
        try {
            return new SimpleDateFormat(str).format(Long.valueOf(j));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean jE(String... strArr) {
        if (strArr == null) {
            return true;
        }
        for (String str : strArr) {
            if (TextUtils.isEmpty(str) || "null".equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public static int jw(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(j2);
        int i = calendar.get(1);
        int i2 = calendar2.get(1);
        int i3 = calendar.get(6);
        int i4 = calendar2.get(6);
        if (i - i2 > 0) {
            return calendar2.getActualMaximum(6) + (i3 - i4);
        }
        if (i - i2 < 0) {
            return (i3 - i4) - calendar.getActualMaximum(6);
        }
        return i3 - i4;
    }

    public static boolean jx(Closeable closeable) {
        if (closeable == null) {
            return true;
        }
        try {
            closeable.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}

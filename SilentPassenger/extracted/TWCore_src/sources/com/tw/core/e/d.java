package com.tw.core.e;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0038l;

/* loaded from: classes.dex */
public class d {
    private static final String gW = d.class.getSimpleName();

    public static boolean lu(String str) {
        try {
        } catch (PackageManager.NameNotFoundException e) {
            C0038l.hE(gW, str + " 未安装");
        }
        return DoFunApplication.nF().getPackageManager().getPackageInfo(str, 0) != null;
    }

    public static PackageInfo ly(String str) {
        return DoFunApplication.nF().getPackageManager().getPackageArchiveInfo(str, 1);
    }

    public static PackageInfo lv(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 1);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String lA(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int lz(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String lw(Context context, String str, String str2) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get(str2)) == null) {
                return "";
            }
            return obj.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String lx(Context context) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.addCategory("android.intent.category.DEFAULT");
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        return (resolveActivity == null || resolveActivity.activityInfo == null) ? "unknow" : resolveActivity.activityInfo.packageName;
    }
}

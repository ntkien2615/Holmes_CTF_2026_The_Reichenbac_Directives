package com.tw.core.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import java.util.List;

/* renamed from: com.tw.core.utils.o, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0041o {
    public static void ib(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction("com.unisound.unicar.app.action.close");
        intent.putExtra("packageName", str);
        context.sendBroadcast(intent);
    }

    public static String ia(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Intent hZ(Context context, Intent intent) {
        List<ResolveInfo> queryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() != 1) {
            return null;
        }
        ResolveInfo resolveInfo = queryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }
}

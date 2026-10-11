package com.tw.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import java.io.File;
import java.util.List;

/* loaded from: classes.dex */
final class af implements Runnable {
    final /* synthetic */ Context gm;

    /* JADX INFO: Access modifiers changed from: package-private */
    public af(Context context) {
        this.gm = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        List<PackageInfo> installedPackages = this.gm.getPackageManager().getInstalledPackages(0);
        StringBuffer stringBuffer = new StringBuffer("");
        int size = installedPackages.size();
        for (int i = 0; i < size; i++) {
            PackageInfo packageInfo = installedPackages.get(i);
            String str = packageInfo.packageName;
            String str2 = packageInfo.versionName;
            int i2 = packageInfo.versionCode;
            stringBuffer.append(str);
            stringBuffer.append(",");
            stringBuffer.append(str2);
            stringBuffer.append(",");
            stringBuffer.append(i2);
            if (i < size - 1) {
                stringBuffer.append("\n");
            }
        }
        C0030d.gV(C0030d.gG() + File.separator + "apk.df", stringBuffer.toString(), false);
    }
}

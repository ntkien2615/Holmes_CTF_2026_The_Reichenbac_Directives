package com.tw.core.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.tw.core.app.DoFunApplication;
import java.io.File;
import org.eclipse.paho.client.mqttv3.internal.ClientDefaults;

/* loaded from: classes.dex */
public class E {
    private static final String fl = E.class.getName() + ".DONE.";

    public static boolean jo(Context context, File file) {
        Uri fromFile;
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            if (Build.VERSION.SDK_INT >= 24) {
                fromFile = FileProvider.ip(context.getApplicationContext(), context.getPackageName() + ".fileProvider", file);
                intent.addFlags(1);
            } else {
                fromFile = Uri.fromFile(file);
            }
            intent.addFlags(ClientDefaults.MAX_MSG_SIZE);
            intent.setDataAndType(fromFile, "application/vnd.android.package-archive");
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void jp(Context context, String str) {
        jn(context, str, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x00ab A[Catch: Exception -> 0x009d, TRY_ENTER, TryCatch #2 {Exception -> 0x009d, blocks: (B:13:0x0019, B:27:0x0094, B:29:0x005f, B:31:0x0099, B:38:0x0057, B:40:0x005c, B:45:0x00ab, B:47:0x00b0, B:48:0x00b3), top: B:12:0x0019 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b0 A[Catch: Exception -> 0x009d, TryCatch #2 {Exception -> 0x009d, blocks: (B:13:0x0019, B:27:0x0094, B:29:0x005f, B:31:0x0099, B:38:0x0057, B:40:0x005c, B:45:0x00ab, B:47:0x00b0, B:48:0x00b3), top: B:12:0x0019 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void jn(android.content.Context r10, java.lang.String r11, com.tw.core.utils.F r12) {
        /*
            Method dump skipped, instructions count: 280
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.utils.E.jn(android.content.Context, java.lang.String, com.tw.core.utils.F):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void jr(String str, int i, F f) {
        C0038l.hE("ApkController", "packageName=" + str + "\nreturnCode=" + i);
        Intent intent = new Intent("dofun.intent.action.APK_INSTALL_RESULT");
        intent.putExtra("packageName", str);
        intent.putExtra("returnCode", i);
        DoFunApplication.nF().sendBroadcast(intent);
        if (i == 1) {
            if (f != null) {
                f.ju(str);
            }
            if (DoFunApplication.nF().getPackageName().equals(str)) {
                System.exit(0);
                return;
            }
            return;
        }
        if (f != null) {
            f.jv(i, str);
        }
        C0038l.hE("ApkController", "安装失败=============");
    }

    public static void jq(Context context, String str, R r) {
        if (TextUtils.isEmpty(str) || (!com.tw.core.e.d.lu(str))) {
            C0034h.hs("ApkController", "%s 未安装", str);
        } else {
            context.getPackageManager().deletePackage(str, new G(r), 0);
        }
    }
}

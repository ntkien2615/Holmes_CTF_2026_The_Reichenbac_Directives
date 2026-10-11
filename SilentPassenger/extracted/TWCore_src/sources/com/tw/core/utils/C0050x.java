package com.tw.core.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import java.io.File;
import java.util.TreeMap;

/* renamed from: com.tw.core.utils.x, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0050x extends Thread {
    private static final String eW = C0050x.class.getSimpleName();
    private String eX = "/download/";
    private String eY = ".apk";
    private Context eZ;

    public C0050x(Context context) {
        this.eZ = context;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        if (com.tw.core.e.c.lp(this.eZ)) {
            S.kt().ku(this.eZ, H.jL(this.eZ) + "", com.tw.core.e.a.la(), new aa(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void iG(com.tw.core.c.c cVar) {
        try {
            if (cVar == null) {
                C0038l.hE(eW, "解析为空");
                return;
            }
            int jL = H.jL(this.eZ);
            String fh = cVar.fh();
            C0038l.hE(eW, "当前版本:" + jL + "服务器版本:" + fh);
            if (H.jC(fh) > jL) {
                C0038l.hE(eW, "为旧版本");
                if (iF(fh)) {
                    C0038l.hE(eW, "存在已经下载的apk");
                    iH();
                    return;
                } else {
                    if (!TextUtils.isEmpty(cVar.ff())) {
                        C0038l.hE(eW, "开始下载");
                        boolean hb = C0032f.hb(cVar.ff(), this.eX, "twcore" + this.eY);
                        C0038l.hE(eW, "downloadResult:" + hb);
                        if (hb) {
                            iI(cVar.fg());
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            C0038l.hE(eW, "当前程序为最新版本");
        } catch (Exception e) {
            C0038l.hF(eW, e);
        }
    }

    private void iI(String str) {
        if (!TextUtils.isEmpty(str)) {
            TreeMap treeMap = new TreeMap();
            treeMap.put("appId", str);
            treeMap.put("cid", com.tw.core.e.b.le());
            treeMap.put("strategyId", S.fL);
            C0032f.hg(treeMap, com.tw.core.a.a.q, new ab(this));
        }
    }

    private boolean iF(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        String str2 = C0030d.gG() + this.eX + "twcore" + this.eY;
        File file = new File(str2);
        if (file.exists() && file.isFile()) {
            C0038l.hE(eW, "存在");
            PackageInfo packageArchiveInfo = this.eZ.getPackageManager().getPackageArchiveInfo(str2, 1);
            if (packageArchiveInfo != null) {
                String str3 = packageArchiveInfo.packageName;
                String str4 = packageArchiveInfo.versionName;
                int i = packageArchiveInfo.versionCode;
                C0038l.hE(eW, "packageName=" + str3 + "  apkVersionName=" + str4);
                if (str3.equals(this.eZ.getPackageName()) && H.jC(str) == i) {
                    C0038l.hE(eW, "本地有服务器版本");
                    return true;
                }
                C0038l.hE(eW, "本地有版本，但是是旧版本！");
                file.delete();
                return false;
            }
            file.delete();
            return false;
        }
        C0038l.hE(eW, "不存在");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void iH() {
        String str = C0030d.gG() + this.eX + "twcore" + this.eY;
        C0038l.hE(eW, "安装路径：" + str);
        if (new File(str).exists()) {
            E.jp(this.eZ, str);
        }
    }
}

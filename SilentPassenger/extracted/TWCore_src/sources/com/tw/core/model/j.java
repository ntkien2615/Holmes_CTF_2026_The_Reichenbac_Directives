package com.tw.core.model;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.tw.core.b.C0003c;
import com.tw.core.utils.B;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0039m;
import com.tw.core.utils.E;
import com.tw.core.utils.H;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j extends BroadcastReceiver {
    final /* synthetic */ MainService iE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(MainService mainService) {
        this.iE = mainService;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        Context context2;
        List list;
        boolean z;
        Context context3;
        Context context4;
        List list2;
        List list3;
        List list4;
        List list5;
        List list6;
        String action = intent.getAction();
        C0034h.hr("MainService", "receive broadcast：%s", action);
        if ("com.unisound.intent.action.DO_SHUTDOWN".equals(action)) {
            list6 = this.iE.in;
            Iterator it = list6.iterator();
            while (it.hasNext()) {
                ((com.tw.core.h.b) it.next()).bP();
            }
            return;
        }
        if ("com.unisound.intent.action.DO_SLEEP".equals(action)) {
            list5 = this.iE.in;
            Iterator it2 = list5.iterator();
            while (it2.hasNext()) {
                ((com.tw.core.h.b) it2.next()).bQ();
            }
            return;
        }
        if ("com.unisound.intent.action.ACC_OFF".equals(action)) {
            list4 = this.iE.in;
            Iterator it3 = list4.iterator();
            while (it3.hasNext()) {
                ((com.tw.core.h.b) it3.next()).bM();
            }
            return;
        }
        if ("com.unisound.intent.action.ACC_ON".equals(action)) {
            list3 = this.iE.in;
            Iterator it4 = list3.iterator();
            while (it4.hasNext()) {
                ((com.tw.core.h.b) it4.next()).bN();
            }
            return;
        }
        if ("com.unisound.intent.action.DO_WAKEUP".equals(action)) {
            list2 = this.iE.in;
            Iterator it5 = list2.iterator();
            while (it5.hasNext()) {
                ((com.tw.core.h.b) it5.next()).bR();
            }
            return;
        }
        if ("android.intent.action.TIME_TICK".equals(action)) {
            C0039m hK = C0039m.hK();
            context3 = this.iE.im;
            hK.hP(context3);
            C0039m hK2 = C0039m.hK();
            context4 = this.iE.im;
            hK2.hQ(context4);
            return;
        }
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean lp = com.tw.core.e.c.lp(context);
            C0034h.hr("MainService", "network change : %s", Boolean.valueOf(lp));
            list = this.iE.in;
            Iterator it6 = list.iterator();
            while (it6.hasNext()) {
                ((com.tw.core.h.b) it6.next()).bO(lp);
            }
            if (lp) {
                com.tw.core.f.d.mb().md();
                try {
                    boolean z2 = Math.abs(System.currentTimeMillis() - C0029c.gA(context, "LastTime", 0L)) > 3600000;
                    C0034h.hr("MainService", "network change 时间是否大于1小时 %s", Boolean.valueOf(z2));
                    if (z2) {
                        C0029c.gE(context, "LastTime", System.currentTimeMillis());
                        z = this.iE.ip;
                        if (z) {
                            this.iE.ip = false;
                            B.ja().jc();
                            return;
                        }
                        return;
                    }
                    return;
                } catch (Exception e) {
                    C0038l.hF("MainService", e);
                    return;
                }
            }
            com.tw.core.f.d.mb().disconnect();
            return;
        }
        if ("dofun.intent.action.EXTERNAL_APK_INSTALL".equals(intent.getAction())) {
            String stringExtra = intent.getStringExtra("ApkFilePath");
            C0034h.hr("MainService", "收到安装apk广播：%s", stringExtra);
            if (!TextUtils.isEmpty(stringExtra) && C0030d.gQ(stringExtra)) {
                ArrayList jJ = H.jJ(context);
                C0034h.hr("MainService", "返回数据 %s", jJ.toString());
                PackageInfo ly = com.tw.core.e.d.ly(stringExtra);
                if (ly != null && jJ.contains(ly.packageName) && com.tw.core.e.d.lz(context, ly.packageName) < ly.versionCode) {
                    C0034h.hr("MainService", "返回数据 %s", ly.packageName);
                    context2 = this.iE.im;
                    E.jn(context2, stringExtra, new k(this));
                    return;
                }
                return;
            }
            return;
        }
        if ("com.dofun.intent.action.TRACK_SWITCH".equals(intent.getAction())) {
            boolean booleanExtra = intent.getBooleanExtra("trackSwitch", false);
            C0034h.hr("MainService", "ACTION_TRACK_SWITCH %s", Boolean.valueOf(booleanExtra));
            C0003c.cb().ce(booleanExtra);
        }
    }
}

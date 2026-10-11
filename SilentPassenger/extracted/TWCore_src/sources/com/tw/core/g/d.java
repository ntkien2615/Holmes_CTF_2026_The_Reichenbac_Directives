package com.tw.core.g;

import android.content.Context;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import com.tw.core.R;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0035i;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.E;
import com.tw.core.utils.F;
import com.tw.core.utils.H;
import com.tw.core.utils.J;
import com.tw.core.utils.L;
import com.tw.core.utils.M;
import java.io.File;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class d extends f {
    private e iL;
    private boolean iM;

    public d(Context context, c cVar) {
        super(context, cVar);
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0500";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0038l.hE("AppUpgradeMessageReceiv", "emqMessageChanged = " + bVar);
        C0042p.id().execute(new j(this, bVar));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void np(String str, String str2, String str3) {
        int lastIndexOf;
        String str4 = null;
        com.tw.core.c.g nv = nv(str3);
        C0038l.hE("AppUpgradeMessageReceiv", "parsePushMessage = " + nv);
        if (nv == null) {
            nC(str2, 106);
            C0038l.hE("AppUpgradeMessageReceiv", "数据错误");
            return;
        }
        if (!nv.fR() && (!com.tw.core.e.d.lu(nv.fq()))) {
            nC(str2, 107);
            C0038l.hE("AppUpgradeMessageReceiv", "设备未安装对应应用，return");
            return;
        }
        String fq = nv.fq();
        int fr = nv.fr();
        int lz = com.tw.core.e.d.lz(this.iP, fq);
        C0038l.hE("AppUpgradeMessageReceiv", "verCode = " + fr + ",appVerCode = " + lz);
        if (lz >= fr) {
            nC(str2, 104);
            C0038l.hE("AppUpgradeMessageReceiv", "本地有新版本，return");
            return;
        }
        String lE = com.tw.core.e.e.lE();
        if (!TextUtils.isEmpty(lE) && lE.contains("_") && lE.length() > (lastIndexOf = lE.lastIndexOf("_") + 1)) {
            str4 = lE.substring(lastIndexOf);
        }
        C0038l.hE("AppUpgradeMessageReceiv", "systemVersion = " + lE + " , fotaVersion = " + str4);
        if (!TextUtils.isEmpty(str4)) {
            List fS = nv.fS();
            if (fS != null && fS.size() > 0 && fS.contains(str4)) {
                nC(str2, 105);
                C0038l.hE("AppUpgradeMessageReceiv", "当前fota版本处于黑名单，return");
                return;
            }
            List fT = nv.fT();
            if (fT != null && fT.size() > 0 && (!fT.contains(str4))) {
                nC(str2, 105);
                C0038l.hE("AppUpgradeMessageReceiv", "当前fota版本不在白名单，return");
                return;
            }
        }
        String fU = nv.fU();
        C0038l.hE("AppUpgradeMessageReceiv", "systemVersion = " + lE + " , systemRegular = " + fU);
        if (!H.jE(fU) && (!Pattern.matches(fU, lE))) {
            nC(str2, 105);
            C0038l.hE("AppUpgradeMessageReceiv", "正则表达匹配失败，return");
            return;
        }
        List fV = nv.fV();
        C0038l.hE("AppUpgradeMessageReceiv", "language = " + H.jy(this.iP));
        if (fV != null && fV.size() > 0 && (!fV.contains(r2))) {
            nC(str2, 102);
            C0038l.hE("AppUpgradeMessageReceiv", "当前语言环境不在白名单，return");
            return;
        }
        List fW = nv.fW();
        if (fW != null && fW.size() > 0) {
            String lw = com.tw.core.e.d.lw(this.iP, nv.fq(), "UMENG_CHANNEL");
            C0038l.hE("AppUpgradeMessageReceiv", "apkChannel = " + lw);
            if (!TextUtils.isEmpty(lw) && (!fW.contains(lw))) {
                nC(str2, 103);
                C0038l.hE("AppUpgradeMessageReceiv", "当前渠道版本不在白名单，return");
                return;
            } else if (TextUtils.isEmpty(lw) & (!nv.fR())) {
                nC(str2, 107);
                C0038l.hE("AppUpgradeMessageReceiv", "设备未安装对应渠道应用，且不能覆盖，return");
                return;
            }
        }
        nC(str2, 101);
        boolean nt = nt(nv);
        nC(str2, nt ? 100 : 99);
        if (!nt) {
            C0038l.hE("AppUpgradeMessageReceiv", "loadApkFileResult 文件未下载完成，return");
            return;
        }
        int type = nv.getType();
        if (type == 0) {
            ns(str2, nv, true);
        } else if (type == 1) {
            C0038l.hE("AppUpgradeMessageReceiv", "sendNotification : taskId = " + str2 + ",version = " + nv);
            com.tw.core.notification.b.get().nK(str2, this);
            com.tw.core.notification.a.get().nI(R.mipmap.ic_upgrade, str2, nv.fX(), nv.fY(), str3);
        }
    }

    private boolean nt(com.tw.core.c.g gVar) {
        String nq = nq(gVar);
        boolean nn = nn(nq, gVar);
        C0038l.hE("AppUpgradeMessageReceiv", "loadApkFileResult apkExists = " + nn);
        if (!nn) {
            nn = no(nq, gVar);
        }
        C0038l.hE("AppUpgradeMessageReceiv", "loadApkFileResult checkLocalApkExists = " + nn);
        return nn;
    }

    @Override // com.tw.core.g.f, com.tw.core.notification.c
    public void nu(String str, String str2) {
        super.nu(str, str2);
        C0042p.id().execute(new k(this, str2, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ns(String str, com.tw.core.c.g gVar, boolean z) {
        String nq = nq(gVar);
        C0038l.hE("AppUpgradeMessageReceiv", "installApk apkFilePath = " + nq);
        nx();
        l lVar = new l(this, gVar, str, nq);
        C0038l.hE("AppUpgradeMessageReceiv", "installApk type = " + z);
        if (z) {
            E.jn(this.iP, nq, lVar);
            return;
        }
        nw(lVar);
        if (!E.jo(this.iP, new File(nq))) {
            nC(str, 0);
        }
    }

    private String nq(com.tw.core.c.g gVar) {
        return C0030d.gG() + "/push/apk/" + M.kj(gVar.fq()) + gVar.fZ();
    }

    private boolean no(String str, com.tw.core.c.g gVar) {
        try {
            C0042p.id().submit(new J(gVar.ga(), new File(str))).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        boolean nn = nn(str, gVar);
        C0038l.hE("AppUpgradeMessageReceiv", "downloadApk downloadResult = " + nn);
        return nn;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public com.tw.core.c.g nv(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("package");
            int i = jSONObject.getInt("flag");
            String string2 = jSONObject.getString("path");
            String optString = jSONObject.optString("version");
            String optString2 = jSONObject.optString("title");
            String optString3 = jSONObject.optString("describe");
            String optString4 = jSONObject.optString("name");
            String optString5 = jSONObject.optString("system_version_regular");
            boolean optBoolean = jSONObject.optBoolean("not_exist_install");
            List hx = C0035i.hx(jSONObject.optJSONArray("target_channel"), String.class);
            List hx2 = C0035i.hx(jSONObject.optJSONArray("language"), String.class);
            List hx3 = C0035i.hx(jSONObject.optJSONArray("blacklist_full"), String.class);
            List hx4 = C0035i.hx(jSONObject.optJSONArray("whitelist_full"), String.class);
            List hx5 = C0035i.hx(jSONObject.optJSONArray("blacklist_flag"), String.class);
            List hx6 = C0035i.hx(jSONObject.optJSONArray("whitelist_flag"), String.class);
            String optString6 = jSONObject.optString("key");
            return new com.tw.core.c.g(string, i, optString, optString4).gb(string2).gc(optString2).gd(optString3).ge(hx2).gf(optString6).gg(jSONObject.optInt("type")).gh(optBoolean).gi(hx).gj(hx3).gk(hx4).gl(hx5).gm(optString5).gn(hx6);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean nn(String str, com.tw.core.c.g gVar) {
        boolean equals = C0030d.gQ(str) ? TextUtils.equals(gVar.fZ(), L.kg(str)) : false;
        PackageInfo ly = com.tw.core.e.d.ly(str);
        if (C0038l.ez) {
            C0038l.hE("AppUpgradeMessageReceiv", "checkLocalApkExists version = " + gVar + ",fileDownload = " + equals + ",calculateMd5 = " + L.kg(str) + ",packageInfo.packageName = " + (ly != null ? ly.packageName : "null") + ",packageInfo.versionCode = " + (ly != null ? Integer.valueOf(ly.versionCode) : "null") + ",apkFilePath = " + str);
        }
        return equals && ly != null && TextUtils.equals(gVar.fq(), ly.packageName) && ly.versionCode == gVar.fr();
    }

    @Override // com.tw.core.g.f
    public String nr() {
        return "0501";
    }

    private void nw(F f) {
        if (!this.iM) {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.PACKAGE_ADDED");
            intentFilter.addAction("android.intent.action.PACKAGE_REMOVED");
            intentFilter.addDataScheme("package");
            if (this.iL == null) {
                this.iL = new e(this, null);
            }
            this.iP.registerReceiver(this.iL, intentFilter);
            this.iM = true;
        }
        this.iL.nB(f);
    }

    @Override // com.tw.core.h.b
    public void bQ() {
    }

    @Override // com.tw.core.h.b
    public void bM() {
    }

    @Override // com.tw.core.h.b
    public void bR() {
    }

    @Override // com.tw.core.h.b
    public void bN() {
    }

    private void nx() {
        if (this.iM) {
            if (this.iL != null) {
                this.iL.nB(null);
                this.iP.unregisterReceiver(this.iL);
            }
            this.iM = false;
        }
    }
}

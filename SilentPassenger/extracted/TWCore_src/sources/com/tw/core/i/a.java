package com.tw.core.i;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.app.DoFunApplication;
import com.tw.core.b.C0003c;
import com.tw.core.f.f;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.C0048v;
import com.tw.core.utils.H;
import com.tw.core.utils.InterfaceC0049w;
import com.tw.core.utils.K;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class a implements com.tw.core.h.b, com.tw.core.f.a, InterfaceC0049w, com.tw.core.f.c {
    private static final String jy = com.tw.core.app.a.jo + "/cloud/collect/app/list";
    private boolean jA;
    private long jB = 2592000000L;
    private AtomicBoolean jz = new AtomicBoolean(false);

    @Override // com.tw.core.h.b
    public void onCreate() {
        f.get().mq(this);
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0303";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0038l.hE("AppStatisticsManager", "emqMessageChanged =" + bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            this.jA = jSONObject.getBoolean("collect_on_off");
            this.jB = jSONObject.getLong("collect_frequency") * 1000;
            C0038l.hE("AppStatisticsManager", "emqMessageChanged enable=" + this.jA + ",freq=" + this.jB);
            nN();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    private void nN() {
        if (this.jA) {
            C0042p.id().execute(new c(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nQ() {
        if (this.jz.get()) {
            C0038l.hE("AppStatisticsManager", "加载中");
            return;
        }
        long gz = C0029c.gz(DoFunApplication.nF(), "LastTimeUploadAppStatistics");
        long kd = K.kd();
        if (C0038l.ez) {
            C0038l.hE("AppStatisticsManager", "lastTime：" + K.ke(gz, K.fx));
            C0038l.hE("AppStatisticsManager", "currentTime：" + K.ke(kd, K.fx));
            C0038l.hE("AppStatisticsManager", "currentTime - lastTime = " + (kd - gz) + ",interval = " + this.jB);
        }
        if (kd - gz < this.jB) {
            C0038l.hE("AppStatisticsManager", "时间未达到");
        } else {
            this.jz.set(true);
            C0048v.iA(this);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0049w
    public void ey(String str) {
        C0038l.hE("AppStatisticsManager", "onBackMcuVersion = " + str);
        PackageManager packageManager = DoFunApplication.nF().getPackageManager();
        List<PackageInfo> installedPackages = packageManager.getInstalledPackages(8192);
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        for (PackageInfo packageInfo : installedPackages) {
            String str2 = packageInfo.packageName;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (str2.contains("com.dofun") || H.jF(DoFunApplication.nF()).equals(str2) || str2.contains("launcher") || (applicationInfo.flags & 1) <= 0) {
                arrayList.add(str2);
            } else {
                intent.setPackage(packageInfo.packageName);
                List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
                if (queryIntentActivities != null && queryIntentActivities.size() > 0) {
                    arrayList.add(str2);
                }
            }
            if (C0038l.ez) {
                C0038l.hE("AppStatisticsManager", "label = " + packageInfo.applicationInfo.loadLabel(packageManager).toString() + ",packageName = " + packageInfo.packageName);
            }
        }
        nP(arrayList, str);
    }

    private void nP(List list, String str) {
        JSONArray jSONArray = new JSONArray();
        int size = list.size();
        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < size; i++) {
            String str2 = (String) list.get(i);
            PackageInfo lv = com.tw.core.e.d.lv(DoFunApplication.nF(), str2);
            if (lv != null) {
                String charSequence = lv.applicationInfo.loadLabel(DoFunApplication.nF().getPackageManager()).toString();
                long eR = new com.tw.core.c.a(lv.applicationInfo.uid).eR();
                String str3 = lv.versionName;
                int i2 = lv.versionCode;
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("package", str2);
                    jSONObject.put("name", charSequence);
                    jSONObject.put("version", str3);
                    jSONObject.put("flag", i2);
                    String jG = eR > 0 ? H.jG(eR) : "0.00";
                    C0038l.hE("AppStatisticsManager", charSequence + "使用流量 " + jG + "mb");
                    jSONObject.put("flow", jG);
                    jSONObject.put("channel", com.tw.core.e.d.lw(DoFunApplication.nF(), str2, "UMENG_CHANNEL"));
                    jSONObject.put("size", H.jG(new File(lv.applicationInfo.publicSourceDir).length()));
                    jSONArray.put(jSONObject);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            atomicInteger.incrementAndGet();
            if (atomicInteger.get() == size) {
                nO(jSONArray, str);
            }
        }
    }

    private void nO(JSONArray jSONArray, String str) {
        String le = com.tw.core.e.b.le();
        String kZ = com.tw.core.e.a.kZ();
        String str2 = SystemProperties.get("ro.tw.version");
        long eK = com.tw.core.c.a.eK();
        long eN = com.tw.core.c.a.eN();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app", jSONArray);
            jSONObject.put("cid", le);
            jSONObject.put("plat", kZ);
            jSONObject.put("sys_version", str2);
            jSONObject.put("mcu_version", str);
            jSONObject.put("language", H.jy(DoFunApplication.nF()));
            jSONObject.put("time", K.kd() / 1000);
            jSONObject.put("4g", H.jH(H.jG(eK)));
            jSONObject.put("wifi", H.jH(H.jG(eN - eK)));
            jSONObject.put("default_theme", com.tw.core.e.d.lx(DoFunApplication.nF()));
            String ca = C0003c.cb().ca();
            if (!TextUtils.isEmpty(ca)) {
                jSONObject.put("uid", ca);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (C0038l.ez) {
            C0038l.hE("AppStatisticsManager", "上传 App 数据：" + jSONObject.toString());
        }
        com.tw.core.f.d.mb().mc("0202" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), jy, com.tw.core.f.d.mb().hu, new d(this));
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
    }

    @Override // com.tw.core.h.b
    public void bP() {
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

    @Override // com.tw.core.h.b
    public void bH() {
        f.get().mr(this);
    }
}

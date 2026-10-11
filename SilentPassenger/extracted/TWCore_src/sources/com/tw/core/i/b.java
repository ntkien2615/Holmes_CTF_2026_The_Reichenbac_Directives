package com.tw.core.i;

import android.content.pm.PackageInfo;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.app.DoFunApplication;
import com.tw.core.b.C0003c;
import com.tw.core.f.f;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.O;
import com.tw.core.utils.P;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b extends com.tw.core.g.a implements com.tw.core.f.c {
    private boolean jC;

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        f.get().mq(this);
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        boolean gx = C0029c.gx(DoFunApplication.nF(), "UserBehaviorDataCollectEnable", false);
        C0038l.hE("UserBehaviorManager", "connected UserBehaviorDataCollect Enable = " + gx);
        if (gx) {
            super.bD(z, str);
        }
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bP() {
        boolean gx = C0029c.gx(DoFunApplication.nF(), "UserBehaviorDataCollectEnable", false);
        C0034h.hr("UserBehaviorManager", "onShutdown UserBehaviorDataCollect Enable = %s", Boolean.valueOf(gx));
        if (gx || this.jC) {
            C0042p.id().execute(new e(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nT() {
        List<P> kp = O.kp(DoFunApplication.nF());
        if (C0038l.ez) {
            C0038l.hE("UserBehaviorManager", "uploadUsageStats ：" + kp);
        }
        if (kp != null && kp.size() > 0) {
            JSONArray jSONArray = new JSONArray();
            for (P p : kp) {
                try {
                    PackageInfo lv = com.tw.core.e.d.lv(DoFunApplication.nF(), p.fJ);
                    if (lv != null) {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("package", p.fJ);
                        jSONObject.put("count", p.fI);
                        jSONObject.put("time", p.fK);
                        jSONObject.put("version", lv.versionName);
                        jSONObject.put("flag", lv.versionCode);
                        jSONObject.put("channel", com.tw.core.e.d.lw(DoFunApplication.nF(), p.fJ, "UMENG_CHANNEL"));
                        jSONObject.put("name", DoFunApplication.nF().getPackageManager().getApplicationLabel(lv.applicationInfo).toString());
                        jSONArray.put(jSONObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("app", jSONArray);
                jSONObject2.put("cid", com.tw.core.e.b.le());
                long currentTimeMillis = System.currentTimeMillis();
                jSONObject2.put("up", (currentTimeMillis - SystemClock.elapsedRealtime()) / 1000);
                jSONObject2.put("down", currentTimeMillis / 1000);
                String ca = C0003c.cb().ca();
                if (!TextUtils.isEmpty(ca)) {
                    jSONObject2.put("uid", ca);
                }
                if (C0038l.ez) {
                    C0038l.hE("UserBehaviorManager", "上传 user behavior 数据：" + jSONObject2.toString());
                }
                nj("0203" + Base64.encodeToString(jSONObject2.toString().getBytes(), 8));
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return C0030d.gG() + "/statistisc/.cache_user_behavior.df";
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return com.tw.core.app.a.jo + "/cloud/collect/app/journey";
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0304";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("UserBehaviorManager", "emqMessageChanged = %s", bVar);
        try {
            this.jC = new JSONObject(bVar.hq).optBoolean("collect_on_off", false);
            if (com.tw.core.e.e.lB()) {
                C0029c.gC(DoFunApplication.nF(), "UserBehaviorDataCollectEnable", this.jC);
            }
            C0034h.hr("UserBehaviorManager", "emqMessageChanged enable= %s", Boolean.valueOf(this.jC));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bH() {
        super.bH();
        f.get().mr(this);
    }
}

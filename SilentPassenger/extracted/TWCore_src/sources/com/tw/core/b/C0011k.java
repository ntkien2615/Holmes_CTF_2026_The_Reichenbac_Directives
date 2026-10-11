package com.tw.core.b;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import com.tw.core.track.model.GpsModel;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0040n;
import com.tw.core.utils.C0051y;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* renamed from: com.tw.core.b.k, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0011k extends B implements com.tw.core.f.a, com.tw.core.f.c, com.tw.core.h.b {
    private static DecimalFormat bf = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.CHINA));
    private boolean bg;
    private com.tw.core.track.b bi;
    private com.tw.core.c.e bj;
    private Context bk;
    private String bl;
    private C0018r bm;
    private Handler bn;
    private String bh = null;
    private long bo = 0;

    public C0011k(Context context) {
        this.bk = context;
        this.bj = com.tw.core.c.e.fs(C0029c.gB(this.bk, "BaiDuRecorderConfig"));
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
        bS();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
        if (z) {
            cI();
        } else {
            cK();
        }
    }

    private void cH() {
        boolean z;
        if (this.bg) {
            C0034h.hs("BaiDuMapDataManager", "已经注册", new Object[0]);
            return;
        }
        List ft = this.bj.ft();
        if (ft != null) {
            Iterator it = ft.iterator();
            boolean z2 = false;
            while (true) {
                if (!it.hasNext()) {
                    z = z2;
                    break;
                }
                String str = (String) it.next();
                z = com.tw.core.e.d.lu(str);
                if (z) {
                    C0034h.hr("BaiDuMapDataManager", "包名 %s 存在", str);
                    break;
                }
                z2 = z;
            }
        } else {
            z = false;
        }
        if (!z) {
            C0034h.hs("BaiDuMapDataManager", "不存在相关记录仪", new Object[0]);
            return;
        }
        this.bg = true;
        HashMap hashMap = new HashMap();
        hashMap.put("device_no", com.tw.core.e.b.le());
        hashMap.put("partner_name", "doufeng");
        hashMap.put("device_imei", com.tw.core.e.b.ll(this.bk));
        hashMap.put("channel_maker", "data_co-op");
        hashMap.put("sys_maker", "doufeng");
        hashMap.put("product", com.tw.core.e.a.kZ());
        hashMap.put("os_ver", com.tw.core.e.e.lE());
        try {
            hashMap.put("app_ver", this.bk.getPackageManager().getPackageInfo(this.bk.getPackageName(), 0).versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        hashMap.put("authorized", "0");
        hashMap.put("vehicle_type", "9000");
        List iK = C0051y.iK();
        if (iK != null && iK.size() > 0) {
            hashMap.put("iccid", ((com.tw.core.c.f) iK.get(0)).fJ());
        } else {
            hashMap.put("iccid", "0");
        }
        hashMap.put("device_ver_no", "0");
        hashMap.put("hardware_maker", "TW");
        hashMap.put("device_dpi", com.tw.core.e.f.lN() + "");
        hashMap.put("total_space", bf.format(com.tw.core.e.e.lF() / 1024.0d) + " GB");
        hashMap.put("free_space", bf.format(((com.tw.core.e.e.lG() / 1024) / 1024) / 1024.0d) + " GB");
        hashMap.put("channel_id", "14159284");
        hashMap.put("flow_plan", "");
        hashMap.put("cpu", com.tw.core.e.e.lH()[0]);
        hashMap.put("cpuz", com.tw.core.e.e.lI());
        C0040n.hY(hashMap, "99a16577e9bc4ef63d736402174bbbc4");
        C0032f.gZ(hashMap, "http://dedc.baidu.com/grey/register", new K(this));
    }

    private void cI() {
        if (this.bj != null && !(!this.bj.fu()) && !C0029c.gw(this.bk, "no_upload_gps_device_key") && this.bn == null) {
            this.bn = new L(this, Looper.getMainLooper());
            this.bn.sendEmptyMessageDelayed(999, 10000L);
        }
    }

    private void cK() {
        if (this.bn != null) {
            this.bn.removeCallbacksAndMessages(null);
            this.bn = null;
        }
    }

    @Override // com.tw.core.h.b
    public void bP() {
        C0034h.hr("BaiDuMapDataManager", "onShutdown uploadData", new Object[0]);
        if (this.bi != null) {
            this.bi.aR();
        }
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
        cJ();
        cK();
        com.tw.core.f.f.get().mr(this);
    }

    @Override // com.tw.core.b.B
    /* renamed from: cB, reason: merged with bridge method [inline-methods] */
    public String bU(GpsModel gpsModel) {
        return gpsModel != null ? gpsModel.v() : "";
    }

    @Override // com.tw.core.b.B
    public GpsModel bE(String str) {
        return GpsModel.s(str);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        if (this.bl == null) {
            this.bl = C0030d.gG() + File.separator + ".bdrecordergps.df";
        }
        return this.bl;
    }

    @Override // com.tw.core.b.B
    /* renamed from: cA, reason: merged with bridge method [inline-methods] */
    public boolean bT(GpsModel gpsModel) {
        if (gpsModel == null) {
            return false;
        }
        int jw = com.tw.core.utils.H.jw(System.currentTimeMillis(), com.tw.core.utils.H.jI(gpsModel.D()) * 1000);
        int fv = this.bj != null ? this.bj.fv() : 0;
        if (fv <= 0) {
            fv = 3;
        }
        if (C0034h.ev) {
            C0034h.hr("BaiDuMapDataManager", "cacheDay=%s offectDay=%s", Integer.valueOf(fv), Integer.valueOf(jw));
        }
        return jw < fv;
    }

    @Override // com.tw.core.b.B
    protected long cD() {
        int fv = this.bj != null ? this.bj.fv() : 0;
        if (fv <= 0) {
            fv = 3;
        }
        return Math.min(fv * 8 * 1024 * 1024, 26214400L);
    }

    private String cC() {
        String gt = C0029c.gt(this.bk, "BaiduRecorderAK", null);
        this.bh = gt;
        return gt;
    }

    private long cE() {
        long gA = C0029c.gA(this.bk, "BaiduRecorderServiceId", 0L);
        this.bo = gA;
        return gA;
    }

    public void bS() {
        if (this.bj == null || (!this.bj.fw()) || C0029c.gw(this.bk, "no_upload_gps_device_key")) {
            return;
        }
        long fx = this.bj.fx();
        C0038l.hE("BaiDuMapDataManager", "colFreq intervalTime=" + fx);
        if (this.bi == null) {
            this.bi = new com.tw.core.track.b(this.bk, this, cC(), cE(), true);
        }
        if (this.bm == null) {
            this.bm = new C0018r(this.bk);
            this.bm.db(this.bi, fx);
        } else {
            this.bm.da(fx);
        }
        this.bi.aS(this.bj.fu());
        this.bi.aT(this.bj.fy());
        this.bi.aU(this.bj.fz());
    }

    public void cJ() {
        if (this.bm != null) {
            this.bm.release();
            this.bm = null;
        }
    }

    public void cG(com.tw.core.c.e eVar) {
        if (C0034h.ev) {
            C0034h.hr("BaiDuMapDataManager", "onConfigChange = %s curConfig = %s", eVar, this.bj);
        }
        this.bj = eVar;
        cH();
        if (eVar != null && (!eVar.equals(this.bj))) {
            C0034h.hr("BaiDuMapDataManager", "onConfigChange", new Object[0]);
            if (this.bj.fw()) {
                bS();
            } else {
                cJ();
            }
            if (this.bj.fu()) {
                cI();
            } else {
                cK();
            }
        }
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0316";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        com.tw.core.c.e fs = com.tw.core.c.e.fs(bVar.hq);
        C0034h.hr("BaiDuMapDataManager", "dataCollectConfig = %s", fs);
        if (fs != null) {
            C0029c.gu(this.bk, "BaiDuRecorderConfig", bVar.hq);
            cG(fs);
        }
    }

    @Override // com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
    }

    @Override // com.tw.core.b.B
    protected boolean cF() {
        return true;
    }
}

package com.tw.core.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tw.core.track.model.GpsModel;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import java.io.File;

/* renamed from: com.tw.core.b.x, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0024x extends B implements com.tw.core.f.a, com.tw.core.f.c, com.tw.core.h.a, com.tw.core.h.b {
    private boolean cf;
    private com.tw.core.track.b ch;
    private com.tw.core.c.e ci;
    private Context cj;
    private String ck;
    private Handler cl;
    private boolean cm;

    /* renamed from: cn, reason: collision with root package name */
    private C0018r f2cn;
    private String cg = null;
    private long co = 0;

    public C0024x(Context context) {
        this.cj = context;
        this.ci = com.tw.core.c.e.fs(C0029c.gB(this.cj, "NewBaiduConfig"));
    }

    private boolean dR() {
        if (C0003c.cb().bY()) {
            return true;
        }
        if (this.ci != null) {
            return this.ci.fw();
        }
        return false;
    }

    private boolean dS() {
        if (C0003c.cb().bY()) {
            return true;
        }
        if (this.ci != null) {
            return this.ci.fu();
        }
        return false;
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
        dQ();
        bS();
        com.tw.core.f.f.get().mq(this);
        C0003c.cb().bW(this);
    }

    @Override // com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    private String dO() {
        if (com.tw.core.utils.H.jE(this.cg)) {
            this.cg = C0029c.gB(this.cj, "BaiduAK");
        }
        C0034h.hr("BaiduTrackDataManager", "返回的ak %s", this.cg);
        return this.cg;
    }

    private long dP() {
        if (this.co == 0) {
            this.co = C0029c.gA(this.cj, "BaiduServiceId", 0L);
        }
        C0034h.hr("BaiduTrackDataManager", "返回的serviceId %s", Long.valueOf(this.co));
        return this.co;
    }

    public void dL() {
        C0034h.hr("BaiduTrackDataManager", "refreshAkAndServiceId", new Object[0]);
        this.cg = null;
        this.co = 0L;
        C0029c.gu(this.cj, "BaiduAK", null);
        C0029c.gE(this.cj, "BaiduServiceId", 0L);
        dQ();
    }

    private void dQ() {
        if (com.tw.core.utils.H.jE(dO()) || dP() == 0) {
            C0034h.hr("BaiduTrackDataManager", "网络获取service 和 ak", new Object[0]);
            C0042p.id().execute(new Z(this));
        }
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0306";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        com.tw.core.c.e fs = com.tw.core.c.e.fs(bVar.hq);
        C0038l.hE("BaiduTrackDataManager", "dataCollectConfig =" + fs);
        if (fs != null) {
            C0029c.gu(this.cj, "NewBaiduConfig", bVar.hq);
            cG(fs);
        }
    }

    public void bS() {
        if (this.cf) {
            C0034h.hr("BaiduTrackDataManager", "location is start", new Object[0]);
            return;
        }
        if (!dR()) {
            C0034h.hr("BaiduTrackDataManager", "can not collect gps", new Object[0]);
            return;
        }
        if (this.ci == null) {
            return;
        }
        long fx = this.ci.fx();
        C0038l.hE("BaiduTrackDataManager", "colFreq intervalTime=" + fx);
        if (this.ch == null) {
            this.ch = new com.tw.core.track.b(this.cj, this, dO(), dP());
        }
        if (this.f2cn == null) {
            this.f2cn = new C0018r(this.cj);
            this.f2cn.db(this.ch, fx);
        } else {
            this.f2cn.da(fx);
        }
        this.ch.aS(dS());
        this.ch.aT(this.ci.fy());
        this.ch.aU(this.ci.fz());
        this.cf = true;
    }

    public void cJ() {
        if (!this.cf) {
            C0034h.hr("BaiduTrackDataManager", "location is stop", new Object[0]);
            return;
        }
        C0034h.hr("BaiduTrackDataManager", "stop location", new Object[0]);
        if (this.f2cn != null) {
            this.f2cn.release();
            this.f2cn = null;
        }
        this.cf = false;
    }

    public void cG(com.tw.core.c.e eVar) {
        if (C0038l.ez) {
            C0038l.hE("BaiduTrackDataManager", "onConfigChange =" + eVar + ",curConfig=" + this.ci);
        }
        if (eVar != null && (!eVar.equals(this.ci))) {
            C0038l.hE("BaiduTrackDataManager", "onConfigChange");
            this.ci = eVar;
            if (dR()) {
                bS();
            } else {
                cJ();
            }
            if (dS()) {
                dT();
            } else {
                dU();
            }
        }
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
        C0038l.hE("BaiduTrackDataManager", "onNetworkChange =" + z);
        if (z) {
            dT();
            dQ();
        } else {
            dU();
        }
    }

    @Override // com.tw.core.h.b
    public void bP() {
        C0034h.hr("BaiduTrackDataManager", "onShutdown uploadData", new Object[0]);
        if (this.ch != null) {
            this.ch.aR();
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

    private void dU() {
        if (!this.cm) {
            C0034h.hr("BaiduTrackDataManager", "timer is stop", new Object[0]);
            return;
        }
        C0034h.hr("BaiduTrackDataManager", "stop timer", new Object[0]);
        if (this.cl != null) {
            this.cl.removeCallbacksAndMessages(null);
            this.cl = null;
        }
        this.cm = false;
    }

    private void dT() {
        if (this.cm) {
            C0034h.hr("BaiduTrackDataManager", "timer is start", new Object[0]);
            return;
        }
        if (!dS()) {
            C0034h.hr("BaiduTrackDataManager", "can not upload gps", new Object[0]);
        } else {
            if (this.ci == null) {
                return;
            }
            C0034h.hr("BaiduTrackDataManager", "start timer", new Object[0]);
            this.cl = new ab(this, Looper.getMainLooper());
            this.cl.sendEmptyMessageDelayed(999, 10000L);
            this.cm = true;
        }
    }

    @Override // com.tw.core.b.B
    /* renamed from: dN, reason: merged with bridge method [inline-methods] */
    public String bU(GpsModel gpsModel) {
        return gpsModel != null ? gpsModel.v() : "";
    }

    @Override // com.tw.core.b.B
    public GpsModel bE(String str) {
        return GpsModel.s(str);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        if (this.ck == null) {
            this.ck = C0030d.gG() + File.separator + ".bdgps.df";
        }
        return this.ck;
    }

    @Override // com.tw.core.b.B
    /* renamed from: dM, reason: merged with bridge method [inline-methods] */
    public boolean bT(GpsModel gpsModel) {
        if (gpsModel == null) {
            return false;
        }
        int jw = com.tw.core.utils.H.jw(System.currentTimeMillis(), com.tw.core.utils.H.jI(gpsModel.D()) * 1000);
        int fv = this.ci != null ? this.ci.fv() : 0;
        if (fv <= 0) {
            fv = 3;
        }
        return jw < fv;
    }

    @Override // com.tw.core.b.B
    protected long cD() {
        int fv = this.ci != null ? this.ci.fv() : 0;
        if (fv <= 0) {
            fv = 3;
        }
        return Math.min(fv * 8 * 1024 * 1024, 26214400L);
    }

    @Override // com.tw.core.b.B
    protected boolean cF() {
        return true;
    }

    @Override // com.tw.core.h.b
    public void bH() {
        cJ();
        dU();
        com.tw.core.f.f.get().mr(this);
        C0003c.cb().cc(this);
    }

    @Override // com.tw.core.h.a
    public void dr(String str) {
    }

    @Override // com.tw.core.h.a
    public void ds() {
    }

    @Override // com.tw.core.h.a
    public void dt(boolean z) {
        if (dR()) {
            bS();
        } else {
            cJ();
        }
        if (dS()) {
            dT();
        } else {
            dU();
        }
    }
}

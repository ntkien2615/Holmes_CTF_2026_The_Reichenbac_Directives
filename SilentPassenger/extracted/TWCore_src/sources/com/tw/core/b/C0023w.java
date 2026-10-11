package com.tw.core.b;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tw.core.track.model.GpsModel;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import java.io.File;

/* renamed from: com.tw.core.b.w, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0023w extends B implements com.tw.core.f.a, com.tw.core.f.c, com.tw.core.h.a, com.tw.core.h.b {
    private boolean bX;
    private com.tw.core.track.g bY;
    private com.tw.core.c.e bZ;
    private Context ca;
    private String cb;
    private Handler cc;
    private boolean cd;
    private C0018r ce;

    public C0023w(Context context) {
        this.ca = context;
        this.bZ = com.tw.core.c.e.fs(C0029c.gB(this.ca, "key_ali_config"));
    }

    private boolean dG() {
        if (C0003c.cb().bY()) {
            return true;
        }
        if (this.bZ != null) {
            return this.bZ.fw();
        }
        return false;
    }

    private boolean dH() {
        if (C0003c.cb().bY()) {
            return true;
        }
        if (this.bZ != null) {
            return this.bZ.fu();
        }
        return false;
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
        bS();
        com.tw.core.f.f.get().mq(this);
        C0003c.cb().bW(this);
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
        C0038l.hE("AliTrackDataManager", "onNetworkChange =" + z);
        if (z) {
            dI();
        } else {
            dJ();
        }
    }

    @Override // com.tw.core.h.b
    public void bP() {
        C0034h.hr("AliTrackDataManager", "onShutdown uploadData", new Object[0]);
        if (this.bY != null) {
            this.bY.bo();
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
        dJ();
        com.tw.core.f.f.get().mr(this);
        C0003c.cb().cc(this);
    }

    @Override // com.tw.core.b.B
    /* renamed from: dF, reason: merged with bridge method [inline-methods] */
    public String bU(GpsModel gpsModel) {
        return gpsModel != null ? gpsModel.v() : "";
    }

    @Override // com.tw.core.b.B
    public GpsModel bE(String str) {
        return GpsModel.s(str);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        if (this.cb == null) {
            this.cb = C0030d.gG() + File.separator + ".aligps.df";
        }
        return this.cb;
    }

    @Override // com.tw.core.b.B
    /* renamed from: dE, reason: merged with bridge method [inline-methods] */
    public boolean bT(GpsModel gpsModel) {
        if (gpsModel == null) {
            return false;
        }
        int jw = com.tw.core.utils.H.jw(System.currentTimeMillis(), com.tw.core.utils.H.jI(gpsModel.D()) * 1000);
        int fv = this.bZ != null ? this.bZ.fv() : 0;
        if (fv <= 0) {
            fv = 3;
        }
        C0038l.hE("AliTrackDataManager", "cacheDay=" + fv + ",offectDay=" + jw);
        return jw < fv;
    }

    public void bS() {
        if (this.bX) {
            C0034h.hr("AliTrackDataManager", "location is start", new Object[0]);
            return;
        }
        if (!dG()) {
            C0034h.hr("AliTrackDataManager", "can not collect gps", new Object[0]);
            return;
        }
        if (this.bZ == null) {
            return;
        }
        long fx = this.bZ.fx();
        C0038l.hE("AliTrackDataManager", "colFreq intervalTime=" + fx);
        if (this.bY == null) {
            this.bY = new com.tw.core.track.g(this.ca, this);
        }
        if (this.ce == null) {
            this.ce = new C0018r(this.ca);
            this.ce.db(this.bY, fx);
        } else {
            this.ce.da(fx);
        }
        this.bY.bp(dH());
        this.bY.bq(this.bZ.fy());
        this.bY.br(this.bZ.fz());
        this.bX = true;
    }

    public void cJ() {
        if (!this.bX) {
            C0034h.hr("AliTrackDataManager", "location is stop", new Object[0]);
            return;
        }
        C0034h.hr("AliTrackDataManager", "stop location", new Object[0]);
        if (this.ce != null) {
            this.ce.release();
            this.ce = null;
        }
        this.bX = false;
    }

    public void cG(com.tw.core.c.e eVar) {
        if (C0034h.ev) {
            C0034h.hr("AliTrackDataManager", "onConfigChange = %s ,curConfig= %s", eVar, this.bZ);
        }
        if (eVar != null && (!eVar.equals(this.bZ))) {
            C0038l.hE("AliTrackDataManager", "onConfigChange");
            this.bZ = eVar;
            if (dG()) {
                bS();
            } else {
                cJ();
            }
            if (dH()) {
                dI();
            } else {
                dJ();
            }
        }
    }

    private void dI() {
        if (this.cd) {
            C0034h.hr("AliTrackDataManager", "timer is start", new Object[0]);
            return;
        }
        if (!dH()) {
            C0034h.hr("AliTrackDataManager", "can not upload gps", new Object[0]);
        } else {
            if (this.bZ == null) {
                return;
            }
            C0034h.hr("AliTrackDataManager", "start timer", new Object[0]);
            this.cc = new Y(this, Looper.getMainLooper());
            this.cc.sendEmptyMessageDelayed(999, 10000L);
            this.cd = true;
        }
    }

    private void dJ() {
        if (!this.cd) {
            C0034h.hr("AliTrackDataManager", "timer is stop", new Object[0]);
            return;
        }
        C0034h.hr("AliTrackDataManager", "stop timer", new Object[0]);
        if (this.cc != null) {
            this.cc.removeCallbacksAndMessages(null);
            this.cc = null;
        }
        this.cd = false;
    }

    @Override // com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0314";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        com.tw.core.c.e fs = com.tw.core.c.e.fs(bVar.hq);
        C0034h.hr("AliTrackDataManager", "ali config %s", fs);
        if (fs != null) {
            C0029c.gu(this.ca, "key_gps_ali_url", fs.fA());
            C0029c.gu(this.ca, "key_ali_config", bVar.hq);
            cG(fs);
        }
    }

    @Override // com.tw.core.h.a
    public void dr(String str) {
    }

    @Override // com.tw.core.h.a
    public void ds() {
    }

    @Override // com.tw.core.h.a
    public void dt(boolean z) {
        if (dG()) {
            bS();
        } else {
            cJ();
        }
        if (dH()) {
            dI();
        } else {
            dJ();
        }
    }
}

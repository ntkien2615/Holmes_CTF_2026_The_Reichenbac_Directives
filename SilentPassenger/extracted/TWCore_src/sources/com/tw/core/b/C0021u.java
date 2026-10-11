package com.tw.core.b;

import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.track.model.GpsModel;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.u, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0021u extends B implements InterfaceC0019s, com.tw.core.f.c, com.tw.core.messenger.server.b, com.tw.core.h.a, com.tw.core.h.b {
    private boolean bJ;
    private com.tw.core.c.e bL;
    private Context bM;
    private boolean bN;
    private String bO;
    private Handler bQ;
    private boolean bR;
    private C0018r bS;
    private boolean bU;
    private List bK = new ArrayList();
    private List bP = new ArrayList();
    private AtomicBoolean bT = new AtomicBoolean();
    private boolean bV = true;

    public C0021u(Context context) {
        this.bM = context;
        this.bL = com.tw.core.c.e.fs(C0029c.gB(this.bM, "DefaultDoFunConfig"));
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
        bS();
        com.tw.core.f.f.get().mq(this);
        com.tw.core.messenger.a.get().lX(this);
        C0003c.cb().bW(this);
    }

    private boolean dp() {
        if (C0003c.cb().bY()) {
            return true;
        }
        if (this.bL != null) {
            return this.bL.fw();
        }
        return false;
    }

    private boolean dq() {
        if (C0003c.cb().bY()) {
            return true;
        }
        if (this.bL != null) {
            return this.bL.fu();
        }
        return false;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0305";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0038l.hE("GpsDataManager", "emqMessageChanged =" + bVar);
        com.tw.core.c.e fs = com.tw.core.c.e.fs(bVar.hq);
        C0038l.hE("GpsDataManager", "dataCollectConfig =" + fs);
        if (fs != null) {
            C0029c.gu(this.bM, "DefaultDoFunConfig", bVar.hq);
            cG(fs);
        }
    }

    public void bS() {
        if (this.bJ) {
            C0034h.hr("GpsDataManager", "location is start", new Object[0]);
            return;
        }
        if (!dp()) {
            C0034h.hr("GpsDataManager", "can not collect gps", new Object[0]);
            return;
        }
        if (this.bL == null) {
            return;
        }
        long fx = this.bL.fx();
        C0038l.hE("GpsDataManager", "startLocation colFreq=" + fx);
        if (this.bS == null) {
            this.bS = new C0018r(this.bM);
            this.bS.db(this, fx);
        } else {
            this.bS.da(fx);
        }
        this.bJ = true;
    }

    @Override // com.tw.core.b.InterfaceC0019s
    public void aW(Location location) {
        C0038l.hE("GpsDataManager", "onLocation getLatitude=" + location.getLatitude() + ",getLongitude" + location.getLongitude() + ",getAltitude" + location.getAltitude() + ",getAccuracy" + location.getAccuracy() + ",getBearing" + location.getBearing() + ",getSpeed" + location.getSpeed() + ",getTime" + location.getTime());
        dx(GpsModel.G(location, dn(location.getTime())));
        if (this.bV) {
            this.bV = false;
            if (TextUtils.isEmpty(C0029c.gB(this.bM, "mFirstLocation"))) {
                try {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("gpsx", location.getLatitude());
                    jSONObject.put("gpsy", location.getLongitude());
                    jSONObject.put("gpsh", location.getAltitude());
                    jSONObject.put("gpst", location.getTime() / 1000);
                    jSONObject.put("gpsp", location.getAccuracy());
                    jSONObject.put("gpss", location.getSpeed());
                    C0029c.gu(this.bM, "mFirstLocation", jSONObject.toString());
                } catch (Exception e) {
                    C0038l.hF("GpsDataManager", e);
                }
            }
        }
        dB();
    }

    private void dx(GpsModel gpsModel) {
        if (!dl()) {
            this.bK.add(gpsModel);
            if (this.bK.size() >= 5) {
                du();
            }
            C0038l.hE("GpsDataManager", "sendOnlineMsg 上传开关关闭");
            return;
        }
        this.bP.add(gpsModel);
        C0034h.hs("GpsDataManager", "mGpsList.size %s", Integer.valueOf(this.bP.size()));
        if (this.bP.size() >= this.bL.fz()) {
            dv(false);
        }
    }

    private void dy() {
        if (!dl()) {
            return;
        }
        dv(true);
    }

    private void dv(boolean z) {
        ArrayList arrayList = new ArrayList(this.bP);
        this.bP.clear();
        if (z) {
            GpsModel t = GpsModel.t(this.bM, C0018r.dc(this.bM));
            C0034h.hr("GpsDataManager", "shutdown last point %s", t);
            if (t != null) {
                String gt = C0029c.gt(this.bM, "key_last_shut_down_point_time", null);
                String D = t.D();
                C0034h.hr("GpsDataManager", "lastShutDownPointTime %s shutDownPointTime %s", gt, D);
                if (!TextUtils.equals(gt, D)) {
                    arrayList.add(t);
                    C0029c.gu(this.bM, "key_last_shut_down_point_time", D);
                }
            }
        }
        JSONArray m0do = m0do(arrayList);
        C0034h.hr("GpsDataManager", "online上传的gps %s", m0do.toString());
        com.tw.core.f.d.mb().ml("0199" + Base64.encodeToString(m0do.toString().getBytes(), 8), new V(this, arrayList));
    }

    /* renamed from: do, reason: not valid java name */
    private JSONArray m0do(List list) {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            jSONArray.put(((GpsModel) it.next()).u());
        }
        return jSONArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean dl() {
        if (C0038l.ez) {
            C0038l.hE("GpsDataManager", "checkAgreeUpload mCanUpload= " + dq() + ",NetworkInfoTools.isAvailable " + com.tw.core.f.d.mb().isConnected() + ",uid " + C0003c.cb().ca());
        }
        if (!com.tw.core.f.d.mb().isConnected() || this.bL == null || !dp()) {
            return false;
        }
        if (this.bL.fy()) {
            return !TextUtils.isEmpty(C0003c.cb().ca());
        }
        return true;
    }

    private void du() {
        if (this.bK != null && this.bK.size() > 0) {
            ArrayList arrayList = new ArrayList(this.bK);
            this.bK.clear();
            eo(arrayList);
        }
    }

    public void dw(List list) {
        C0034h.hr("GpsDataManager", "sendOfflineMsg gpsDatas: %s", list);
        this.bT.set(false);
        if (list != null && list.size() > 0) {
            JSONArray m0do = m0do(list);
            C0034h.hr("GpsDataManager", "sendOfflineMsg JsonArray: %s", m0do.toString());
            com.tw.core.f.d.mb().ml("0199" + Base64.encodeToString(m0do.toString().getBytes(), 8), new W(this, list));
            do {
                try {
                    Thread.sleep(1L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (!this.bT.get());
        }
    }

    private void dB() {
        if (C0038l.ez) {
            C0038l.hE("GpsDataManager", "uploadCarLine mUploadCarLineLoading：" + this.bU + ",mCurrentDayUploadFinish:" + this.bN);
        }
        if (!this.bN && !this.bU && com.tw.core.e.c.lp(this.bM)) {
            String jM = com.tw.core.utils.H.jM();
            String gB = C0029c.gB(this.bM, "LastUploadOnlineTime");
            if (C0038l.ez) {
                C0038l.hE("GpsDataManager", "uploadCarLine curDate：" + jM + "-previousDate:" + gB);
            }
            if (!TextUtils.equals(jM, gB)) {
                this.bN = false;
                Location dc = C0018r.dc(this.bM);
                if (C0038l.ez) {
                    C0038l.hE("GpsDataManager", "uploadCarLine location：" + dc);
                }
                if (dc != null) {
                    this.bU = true;
                    return;
                }
                return;
            }
            this.bN = true;
        }
    }

    public long dn(long j) {
        long j2;
        if (Math.abs(j - C0029c.gA(this.bM, "KeyTrackTime", 0L)) < 600000) {
            j2 = C0029c.gA(this.bM, "KeyTrackId", 0L);
        } else {
            C0029c.gE(this.bM, "KeyTrackId", j);
            j2 = j;
        }
        C0029c.gE(this.bM, "KeyTrackTime", j);
        return j2 / 1000;
    }

    public void cJ() {
        if (!this.bJ) {
            C0034h.hr("GpsDataManager", "location is stop", new Object[0]);
            return;
        }
        C0034h.hr("GpsDataManager", "stop location", new Object[0]);
        if (this.bS != null) {
            this.bS.release();
            this.bS = null;
        }
        this.bJ = false;
    }

    public void cG(com.tw.core.c.e eVar) {
        if (C0038l.ez) {
            C0038l.hE("GpsDataManager", "onConfigChange =" + eVar + ",curConfig=" + this.bL);
        }
        if (eVar != null && (!eVar.equals(this.bL))) {
            C0038l.hE("GpsDataManager", "onConfigChange");
            this.bL = eVar;
            if (dp()) {
                bS();
            } else {
                cJ();
            }
            if (dq()) {
                dz();
            } else {
                dA();
            }
        }
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
        C0038l.hE("GpsDataManager", "onNetworkChange = " + z);
        if (z) {
            dz();
        } else {
            dA();
        }
    }

    @Override // com.tw.core.h.b
    public void bP() {
        dy();
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

    private void dA() {
        if (!this.bR) {
            C0034h.hr("GpsDataManager", "timer is stop", new Object[0]);
            return;
        }
        C0034h.hr("GpsDataManager", "stop timer", new Object[0]);
        if (this.bQ != null) {
            this.bQ.removeCallbacksAndMessages(null);
            this.bQ = null;
        }
        this.bR = false;
    }

    private void dz() {
        if (this.bR) {
            C0034h.hr("GpsDataManager", "timer is start", new Object[0]);
            return;
        }
        if (!dq()) {
            C0034h.hr("GpsDataManager", "can not upload gps", new Object[0]);
            return;
        }
        if (this.bL == null) {
            return;
        }
        C0034h.hr("GpsDataManager", "start timer", new Object[0]);
        HandlerThread handlerThread = new HandlerThread("handle_cache_point");
        handlerThread.start();
        this.bQ = new X(this, handlerThread.getLooper());
        this.bQ.sendEmptyMessageDelayed(9999, 10000L);
        this.bR = true;
    }

    @Override // com.tw.core.b.B
    public String bK() {
        if (this.bO == null) {
            this.bO = C0030d.gG() + File.separator + ".newGps.df";
        }
        return this.bO;
    }

    @Override // com.tw.core.b.B
    /* renamed from: dk, reason: merged with bridge method [inline-methods] */
    public boolean bT(GpsModel gpsModel) {
        if (gpsModel == null) {
            return false;
        }
        int jw = com.tw.core.utils.H.jw(System.currentTimeMillis(), com.tw.core.utils.H.jI(gpsModel.D()) * 1000);
        int fv = this.bL != null ? this.bL.fv() : 0;
        if (fv <= 0) {
            fv = 3;
        }
        C0038l.hE("GpsDataManager", "cacheDay=" + fv + ",offectDay=" + jw);
        return jw < fv;
    }

    @Override // com.tw.core.b.B
    /* renamed from: dm, reason: merged with bridge method [inline-methods] */
    public String bU(GpsModel gpsModel) {
        return gpsModel != null ? gpsModel.u() : "";
    }

    @Override // com.tw.core.b.B
    public GpsModel bE(String str) {
        return GpsModel.R(str);
    }

    @Override // com.tw.core.b.B
    protected long cD() {
        int fv = this.bL != null ? this.bL.fv() : 0;
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
        dA();
        cJ();
        com.tw.core.f.f.get().mr(this);
        C0003c.cb().cc(this);
    }

    @Override // com.tw.core.messenger.server.b
    public void cY(Message message) {
        Message obtain = Message.obtain(message);
        List em = em(1);
        obtain.arg1 = (em == null || em.size() <= 0) ? 0 : 1;
        try {
            message.replyTo.send(obtain);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.messenger.server.b
    public int[] getMessageId() {
        return new int[]{1000};
    }

    @Override // com.tw.core.h.a
    public void dr(String str) {
    }

    @Override // com.tw.core.h.a
    public void ds() {
    }

    @Override // com.tw.core.h.a
    public void dt(boolean z) {
        if (dp()) {
            bS();
        } else {
            cJ();
        }
        if (dq()) {
            dz();
        } else {
            dA();
        }
    }
}

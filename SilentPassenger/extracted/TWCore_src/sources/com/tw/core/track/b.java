package com.tw.core.track;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.tw.core.b.B;
import com.tw.core.b.C0003c;
import com.tw.core.b.InterfaceC0019s;
import com.tw.core.track.model.CoordType;
import com.tw.core.track.model.GpsModel;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.H;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public class b implements InterfaceC0019s {
    private String O;
    private List Q;
    private boolean R;
    private String S;
    private boolean T;
    private boolean U;
    private Context V;
    private B W;
    private int X;
    private long Z;
    private static AtomicLong Y = new AtomicLong();
    private static AtomicInteger aa = new AtomicInteger();
    private static AtomicInteger P = new AtomicInteger();

    public b(Context context, B b, String str, long j) {
        this(context, b, str, j, false);
    }

    public b(Context context, B b, String str, long j, boolean z) {
        this.O = "BaiduLbsBatchListener";
        this.S = null;
        this.Z = 0L;
        this.Q = new ArrayList();
        this.X = 30;
        this.W = b;
        this.V = context;
        this.S = str;
        this.Z = j;
        this.R = z;
        this.O = "BaiduLbsBatchListener-" + this.Z;
    }

    @Override // com.tw.core.b.InterfaceC0019s
    public void aW(Location location) {
        GpsModel t = GpsModel.t(this.V, location);
        C0034h.hr(this.O, "onLocation gpsModel %s", t.toString());
        aX(t);
    }

    public void aX(GpsModel gpsModel) {
        C0038l.hE(this.O, "============================进入实时分发百度监听器============================");
        if (gpsModel == null) {
            C0038l.hE(this.O, "========>解析gpsModelΪ为空");
            return;
        }
        this.Q.add(gpsModel);
        C0038l.hE(this.O, "size = " + this.Q.size() + ",mRealSendSize=" + this.X);
        if (this.Q.size() >= this.X) {
            C0038l.hE(this.O, "======================满足实时" + this.X + "条，进入发送数据=====================");
            aR();
        }
    }

    public void aR() {
        if (this.Q.size() > 0) {
            ArrayList arrayList = new ArrayList(this.Q);
            this.Q.clear();
            if (!aV()) {
                C0038l.hE(this.O, "======================checkAgreeUpload 不滿足條件========");
                this.W.eo(arrayList);
                return;
            }
            try {
                C0042p.id().execute(new c(this, false, arrayList));
            } catch (Exception e) {
                e.printStackTrace();
                this.W.eo(arrayList);
                if (C0038l.ez) {
                    C0038l.hE(this.O, "nowUpload发送数据到百度出错>" + e + "============>" + arrayList.toString());
                }
            }
        }
    }

    private boolean aV() {
        if (C0038l.ez) {
            C0038l.hE(this.O, "checkAgreeUpload mCanUpload=" + this.U + ",NetworkInfoTools.isAvailable(mContext=" + com.tw.core.e.c.lp(this.V) + ",getAk=" + this.S + ",getServiceId=" + this.Z + ",mBindUpload=" + this.T + ",bind=" + (this.T ? !TextUtils.isEmpty(C0003c.cb().ca()) : true));
        }
        if (!this.U || !com.tw.core.e.c.lp(this.V) || !(!H.jE(this.S)) || this.Z == 0) {
            return false;
        }
        if (this.T) {
            return !TextUtils.isEmpty(C0003c.cb().ca());
        }
        return true;
    }

    public void aU(int i) {
        if (i > 0) {
            this.X = i;
        }
    }

    public void aS(boolean z) {
        this.U = z;
    }

    public void aT(boolean z) {
        this.T = z;
    }

    public void aP(String str) {
        this.S = str;
    }

    public void aO(long j) {
        this.Z = j;
        this.O = "BaiduLbsBatchListener-" + this.Z;
    }

    public void aQ() {
        C0038l.hE(this.O, "============================进入缓存数据分发百度监听器============================");
        if (this.W == null || !aV()) {
            return;
        }
        List<List> aI = com.tw.core.track.b.b.aI(this.W.em(1000), 100);
        C0038l.hE(this.O, "============================进入缓存数据分发百度监听器cachePointHandle============================" + (aI != null ? Integer.valueOf(aI.size()) : null));
        if (aI != null && aI.size() > 0) {
            for (List list : aI) {
                if (C0038l.ez) {
                    C0038l.hE(this.O, "======================cachePointHandle，进入发送数据=====================" + list.size());
                }
                try {
                    C0042p.id().execute(new c(this, true, list));
                } catch (Exception e) {
                    e.printStackTrace();
                    if (C0038l.ez) {
                        C0038l.hE(this.O, "cachePointHandle 发送数据到百度出错>" + e + "============>" + list.toString());
                    }
                }
            }
        }
    }

    public void aY(boolean z, List list) {
        if (C0038l.ez) {
            C0038l.hE(this.O, "===========================开始批量发送======================cache====" + z + ",gpsList=" + list);
        }
        d bf = d.bf();
        long incrementAndGet = Y.incrementAndGet();
        bf.bh(incrementAndGet, new i(this, bf, incrementAndGet, z, list));
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            GpsModel gpsModel = (GpsModel) it.next();
            if (gpsModel != null) {
                double jH = H.jH(gpsModel.z());
                double jH2 = H.jH(gpsModel.A());
                if (jH != 0.0d && jH2 != 0.0d) {
                    com.tw.core.track.model.c cVar = new com.tw.core.track.model.c(new com.tw.core.track.model.b(jH, jH2), CoordType.wgs84, H.jH(gpsModel.B()), H.jI(gpsModel.D()), (int) H.jH(gpsModel.x()), H.jH(gpsModel.C()), H.jH(gpsModel.y()), gpsModel.w(), null);
                    cVar.Y(cVar.ah() * 1000);
                    if (!this.R) {
                        String E = gpsModel.E();
                        if (!H.jE(E)) {
                            HashMap hashMap = new HashMap();
                            hashMap.put("uid", E);
                            cVar.X(hashMap);
                        }
                    }
                    if (C0034h.ev) {
                        C0034h.hr(this.O, "上报的点 %s", cVar.toString());
                    }
                    arrayList.add(cVar);
                }
            }
        }
        if (arrayList.size() == 1) {
            bf.bi(new e(incrementAndGet, this.S, this.Z, ((com.tw.core.track.model.c) arrayList.get(0)).V(), (com.tw.core.track.model.c) arrayList.get(0)));
            return;
        }
        HashMap hashMap2 = new HashMap(16);
        hashMap2.put("batch_upload_" + incrementAndGet, arrayList);
        bf.bj(new f(incrementAndGet, this.S, this.Z, hashMap2));
    }
}

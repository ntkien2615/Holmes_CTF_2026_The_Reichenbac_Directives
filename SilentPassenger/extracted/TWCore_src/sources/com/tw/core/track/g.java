package com.tw.core.track;

import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.tw.core.b.B;
import com.tw.core.b.C0003c;
import com.tw.core.b.InterfaceC0019s;
import com.tw.core.track.model.GpsModel;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.H;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class g implements InterfaceC0019s {
    private boolean an;
    private boolean ao;
    private Context ap;
    private B aq;
    private String as;
    private static DecimalFormat al = new DecimalFormat("#0.000000", new DecimalFormatSymbols(Locale.CHINA));
    private static DecimalFormat ak = new DecimalFormat("#0.00", new DecimalFormatSymbols(Locale.CHINA));
    private static DecimalFormat aj = new DecimalFormat("#0.0", new DecimalFormatSymbols(Locale.CHINA));
    private List am = new ArrayList();
    private int ar = 30;

    public g(Context context, B b) {
        this.ap = context;
        this.aq = b;
    }

    @Override // com.tw.core.b.InterfaceC0019s
    public void aW(Location location) {
        GpsModel t = GpsModel.t(this.ap, location);
        C0034h.hr("AliBatchListener", "onLocation gpsModel %s", t.toString());
        bx(t);
    }

    public void bx(GpsModel gpsModel) {
        C0034h.hr("AliBatchListener", "============================进入实时分发阿里监听器============================", new Object[0]);
        if (gpsModel == null) {
            C0038l.hE("AliBatchListener", "========>解析gpsModelΪ为空");
            return;
        }
        this.am.add(gpsModel);
        C0038l.hE("AliBatchListener", "size = " + this.am.size() + ",mRealSendSize=" + this.ar);
        if (this.am.size() >= this.ar) {
            C0038l.hE("AliBatchListener", "======================满足实时" + this.ar + "条，进入发送数据=====================");
            bo();
        }
    }

    public void bo() {
        if (this.am.size() > 0) {
            ArrayList arrayList = new ArrayList(this.am);
            this.am.clear();
            if (!bt()) {
                C0038l.hE("AliBatchListener", "======================checkAgreeUpload 不滿足條件========");
                this.aq.eo(arrayList);
                return;
            }
            try {
                C0042p.id().execute(new h(this, false, arrayList));
            } catch (Exception e) {
                e.printStackTrace();
                this.aq.eo(arrayList);
                if (C0038l.ez) {
                    C0038l.hE("AliBatchListener", "nowUpload发送数据到百度出错>" + e + "============>" + arrayList.toString());
                }
            }
        }
    }

    public void br(int i) {
        if (i > 0) {
            this.ar = i;
        }
    }

    public void bp(boolean z) {
        this.ao = z;
    }

    public void bq(boolean z) {
        this.an = z;
    }

    public void bs() {
        C0038l.hE("AliBatchListener", "============================进入缓存数据分发百度监听器============================");
        if (this.aq == null || !bt()) {
            return;
        }
        List<List> aI = com.tw.core.track.b.b.aI(this.aq.em(1000), 100);
        C0038l.hE("AliBatchListener", "============================进入缓存数据分发百度监听器cachePointHandle============================" + (aI != null ? Integer.valueOf(aI.size()) : null));
        if (aI != null && aI.size() > 0) {
            for (List list : aI) {
                if (C0038l.ez) {
                    C0038l.hE("AliBatchListener", "======================cachePointHandle，进入发送数据=====================" + list.size());
                }
                try {
                    C0042p.id().execute(new h(this, true, list));
                } catch (Exception e) {
                    e.printStackTrace();
                    if (C0038l.ez) {
                        C0038l.hE("AliBatchListener", "cachePointHandle 发送数据到百度出错>" + e + "============>" + list.toString());
                    }
                }
            }
        }
    }

    private boolean bt() {
        if (C0038l.ez) {
            C0038l.hE("AliBatchListener", "checkAgreeUpload mCanUpload=" + this.ao + ",NetworkInfoTools.isAvailable(mContext=" + com.tw.core.e.c.lp(this.ap) + ",getUrl=" + bw() + ",mBindUpload=" + this.an + ",bind=" + (this.an ? !TextUtils.isEmpty(C0003c.cb().ca()) : true));
        }
        if (!this.ao || !com.tw.core.e.c.lp(this.ap) || !(!H.jE(C0029c.gB(this.ap, "key_gps_ali_url")))) {
            return false;
        }
        if (this.an) {
            return !TextUtils.isEmpty(C0003c.cb().ca());
        }
        return true;
    }

    private String bw() {
        if (this.as == null) {
            this.as = C0029c.gt(this.ap, "key_gps_ali_url", null);
        }
        return this.as;
    }

    public void by(boolean z, List list) {
        if (C0038l.ez) {
            C0038l.hE("AliBatchListener", "===========================开始批量发送======================cache====" + z + ",gpsList=" + list);
        }
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                GpsModel gpsModel = (GpsModel) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("metric", "gps");
                jSONObject.put("fields", bu(gpsModel));
                jSONObject.put("tags", bv(gpsModel));
                jSONObject.put("timestamp", gpsModel.D());
                jSONArray.put(jSONObject);
            }
            if (C0034h.ev) {
                C0034h.hr("AliBatchListener", "上报的数据 %s", jSONArray);
            }
            C0032f.hh(jSONArray, this.as, new j(this));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JSONObject bu(GpsModel gpsModel) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("speed", ak.format(Float.valueOf(gpsModel.C())));
        jSONObject.put("direction", String.valueOf((int) Float.parseFloat(gpsModel.x())));
        jSONObject.put("longitude", al.format(Double.valueOf(gpsModel.A())));
        jSONObject.put("latitude", al.format(Double.valueOf(gpsModel.z())));
        jSONObject.put("height", ak.format(Float.valueOf(gpsModel.y())));
        jSONObject.put("radius", ak.format(Float.valueOf(gpsModel.B())));
        return jSONObject;
    }

    private JSONObject bv(GpsModel gpsModel) {
        JSONObject jSONObject = new JSONObject();
        if (!H.jE(gpsModel.E())) {
            jSONObject.put("uid", gpsModel.E());
        }
        jSONObject.put("cid", com.tw.core.e.b.le());
        return jSONObject;
    }
}

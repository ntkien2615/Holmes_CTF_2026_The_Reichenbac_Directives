package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.track.model.ThirdPartnerGpsModel;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0001a extends B implements com.tw.core.f.a, com.tw.core.f.c, com.tw.core.h.b {
    private Context aD;
    private List aE = new ArrayList();
    private int aC = 3;

    public C0001a(Context context) {
        this.aD = context;
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
    }

    @Override // com.tw.core.h.b
    public void bP() {
        if (this.aE != null) {
            Iterator it = this.aE.iterator();
            while (it.hasNext()) {
                ((com.tw.core.model.a) it.next()).mt();
            }
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
        if (this.aE != null) {
            Iterator it = this.aE.iterator();
            while (it.hasNext()) {
                ((com.tw.core.model.a) it.next()).release();
            }
            this.aE.clear();
            this.aE = null;
        }
        com.tw.core.f.f.get().mr(this);
    }

    @Override // com.tw.core.b.B
    /* renamed from: bF, reason: merged with bridge method [inline-methods] */
    public String bU(ThirdPartnerGpsModel thirdPartnerGpsModel) {
        JSONObject al;
        return (thirdPartnerGpsModel == null || (al = thirdPartnerGpsModel.al()) == null) ? "" : al.toString();
    }

    @Override // com.tw.core.b.B
    public ThirdPartnerGpsModel bE(String str) {
        return ThirdPartnerGpsModel.am(str);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.b.B
    /* renamed from: bC, reason: merged with bridge method [inline-methods] */
    public boolean bT(ThirdPartnerGpsModel thirdPartnerGpsModel) {
        if (thirdPartnerGpsModel == null) {
            return false;
        }
        int jw = com.tw.core.utils.H.jw(System.currentTimeMillis(), thirdPartnerGpsModel.an());
        C0034h.hr("ThirdPartnerGpsDataManager", "cacheDay=" + this.aC + ",offectDay=" + jw, new Object[0]);
        return jw < this.aC;
    }

    public void bS() {
        if (this.aE != null) {
            Iterator it = this.aE.iterator();
            while (it.hasNext()) {
                ((com.tw.core.model.a) it.next()).mu();
            }
        }
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0313";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            this.aC = jSONObject.optInt("keep_days", 3);
            JSONArray optJSONArray = jSONObject.optJSONArray("dispense");
            C0034h.hr("ThirdPartnerGpsDataManager", "gps 分发 %s", optJSONArray.toString());
            if (optJSONArray.length() > 0) {
                if (this.aE == null) {
                    this.aE = new ArrayList();
                }
                Iterator it = this.aE.iterator();
                while (it.hasNext()) {
                    ((com.tw.core.model.a) it.next()).release();
                }
                this.aE.clear();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    int optInt = optJSONObject.optInt("collect_frequency");
                    int optInt2 = optJSONObject.optInt("upload_frequency");
                    String optString = optJSONObject.optString("url");
                    arrayList.add(optString);
                    this.aE.add(new com.tw.core.model.a(this.aD, this, optInt, optInt2, optString));
                }
                bG(arrayList);
                bS();
                return;
            }
            if (this.aE != null) {
                Iterator it2 = this.aE.iterator();
                while (it2.hasNext()) {
                    ((com.tw.core.model.a) it2.next()).release();
                }
                this.aE.clear();
                this.aE = null;
            }
            bG(Collections.emptyList());
            C0034h.hr("ThirdPartnerGpsDataManager", "无GPS数据 取消分发", new Object[0]);
        } catch (JSONException e) {
            e.printStackTrace();
            Iterator it3 = this.aE.iterator();
            while (it3.hasNext()) {
                ((com.tw.core.model.a) it3.next()).release();
            }
        }
    }

    private void bG(List list) {
        try {
            String gt = C0029c.gt(this.aD, "key_gps_deliver_config", null);
            C0034h.hr("ThirdPartnerGpsDataManager", "上次配置的GPS链接 %s", gt);
            if (!TextUtils.isEmpty(gt)) {
                JSONArray jSONArray = new JSONArray(gt);
                for (int i = 0; i < jSONArray.length(); i++) {
                    String optString = jSONArray.optString(i);
                    if (!list.contains(optString)) {
                        C0034h.hs("ThirdPartnerGpsDataManager", "%s 不存在了", optString);
                        String str = C0030d.gG() + File.separator + "." + com.tw.core.utils.M.kj(optString) + ".df";
                        File file = new File(str);
                        if (file.exists()) {
                            C0034h.hr("ThirdPartnerGpsDataManager", "文件 %s 删除结果 %s", str, Boolean.valueOf(file.delete()));
                        }
                    }
                }
            }
            C0029c.gu(this.aD, "key_gps_deliver_config", new JSONArray((Collection) list).toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
    }
}

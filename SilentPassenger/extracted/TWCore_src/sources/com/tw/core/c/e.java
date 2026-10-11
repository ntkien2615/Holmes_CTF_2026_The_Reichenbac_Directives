package com.tw.core.c;

import android.text.TextUtils;
import com.tw.core.utils.C0047u;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class e {
    private int dK;
    private int dL;
    private boolean dM;
    private boolean dN;
    private boolean dO;
    private List dP;
    private int dQ;
    private String dR;

    public static e fs(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            boolean z = jSONObject.getBoolean("collect_on_off");
            int i = jSONObject.getInt("collect_frequency") * 1000;
            boolean z2 = jSONObject.getBoolean("upload_on_off");
            int i2 = jSONObject.getInt("upload_frequency");
            boolean optBoolean = jSONObject.optBoolean("binding_on_off");
            int i3 = jSONObject.getInt("keep_days");
            e eVar = new e();
            if (jSONObject.has("url") && jSONObject.optString("url") != null) {
                String iw = C0047u.iw(jSONObject.optString("url"));
                if (!TextUtils.isEmpty(iw)) {
                    eVar.fI(iw);
                }
            }
            String optString = jSONObject.optString("support_packages");
            if (!TextUtils.isEmpty(optString)) {
                if (optString.contains(",")) {
                    eVar.fF(new ArrayList(Arrays.asList(optString.split(","))));
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(optString);
                    eVar.fF(arrayList);
                }
            }
            eVar.fE(z);
            eVar.fD(i);
            eVar.fH(z2);
            eVar.fG(i2);
            eVar.fB(optBoolean);
            eVar.fC(i3);
            return eVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean fw() {
        return this.dN;
    }

    public void fE(boolean z) {
        this.dN = z;
    }

    public int fx() {
        return this.dL;
    }

    public void fD(int i) {
        this.dL = i;
    }

    public boolean fu() {
        return this.dO;
    }

    public void fH(boolean z) {
        this.dO = z;
    }

    public int fz() {
        return this.dQ;
    }

    public void fG(int i) {
        this.dQ = i;
    }

    public boolean fy() {
        return this.dM;
    }

    public void fB(boolean z) {
        this.dM = z;
    }

    public int fv() {
        return this.dK;
    }

    public void fC(int i) {
        this.dK = i;
    }

    public String fA() {
        return this.dR;
    }

    public void fI(String str) {
        this.dR = str;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof e)) {
            return false;
        }
        e eVar = (e) obj;
        if (fx() == eVar.fx() && fw() == eVar.fw() && fu() == eVar.fu() && fz() == eVar.fz() && fy() == eVar.fy() && fv() == eVar.fv() && ft() == eVar.ft()) {
            return TextUtils.equals(fA(), eVar.fA());
        }
        return false;
    }

    public String toString() {
        return "DataCollectConfig{isColOn=" + this.dN + ", colFreq=" + this.dL + ", isUploadOn=" + this.dO + ", uploadFreq=" + this.dQ + ", isBindOn=" + this.dM + ", cacheDay=" + this.dK + ", url=" + this.dR + ", recordList=" + this.dP + '}';
    }

    public List ft() {
        return this.dP;
    }

    public void fF(List list) {
        this.dP = list;
    }
}

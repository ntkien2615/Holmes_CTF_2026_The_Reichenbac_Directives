package com.tw.core.c;

import android.text.TextUtils;
import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0029c;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class b {
    private String du;
    private String dv;
    private String dw;
    private String dx;
    private String time;

    public static b eW(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(str);
            b bVar = new b();
            bVar.dv = jSONObject.optString("logId");
            bVar.time = jSONObject.optString("time");
            bVar.du = jSONObject.optString("instruct");
            bVar.dx = jSONObject.optString("target");
            bVar.dw = jSONObject.optString("logcatScene");
            return bVar;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public JSONObject eV() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("logId", this.dv);
            jSONObject.put("time", this.time);
            jSONObject.put("instruct", this.du);
            jSONObject.put("target", this.dx);
            jSONObject.put("logcatScene", this.dw);
            return jSONObject;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void eZ(String str) {
        JSONObject eV = eV();
        if (eV != null) {
            C0029c.gu(DoFunApplication.nF(), str, eV.toString());
        }
    }

    public static b eX(String str) {
        return eW(C0029c.gt(DoFunApplication.nF(), str, null));
    }

    public static void eY(String str) {
        C0029c.gu(DoFunApplication.nF(), str, null);
    }

    public String eS() {
        return this.dv;
    }

    public void fb(String str) {
        this.dv = str;
    }

    public String eT() {
        return this.time;
    }

    public void fe(String str) {
        this.time = str;
    }

    public String eU() {
        return this.du;
    }

    public void fa(String str) {
        this.du = str;
    }

    public void fd(String str) {
        this.dx = str;
    }

    public void fc(String str) {
        this.dw = str;
    }

    public String toString() {
        return "LogMessage{logId='" + this.dv + "', time='" + this.time + "', instruct='" + this.du + "', targetPkg='" + this.dx + "', logcatScene='" + this.dw + "'}";
    }
}

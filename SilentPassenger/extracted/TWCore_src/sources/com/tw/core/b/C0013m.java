package com.tw.core.b;

import android.content.Context;
import android.os.Handler;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.m, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0013m extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String bt = com.tw.core.app.a.jo + "/cloud/collect/app/installinfo";
    private Context bs;

    public C0013m(Context context) {
        this.bs = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0310";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("AppConfigManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            JSONArray optJSONArray = jSONObject.optJSONArray("up_service_list");
            JSONArray optJSONArray2 = jSONObject.optJSONArray("up_interface_list");
            JSONArray optJSONArray3 = jSONObject.optJSONArray("silent_install_list");
            JSONArray optJSONArray4 = jSONObject.optJSONArray("report_install_list");
            new Handler().postDelayed(new N(this, jSONObject.optJSONArray("kill_service_list")), 5000L);
            C0029c.gu(this.bs, "startServiceApkName", optJSONArray == null ? null : optJSONArray.toString());
            C0029c.gu(this.bs, "startActivityApkName", optJSONArray2 == null ? null : optJSONArray2.toString());
            C0029c.gu(this.bs, "installAPKName", optJSONArray3 == null ? null : optJSONArray3.toString());
            C0029c.gu(this.bs, "uploadAPKName", optJSONArray4 != null ? optJSONArray4.toString() : null);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return bt;
    }
}

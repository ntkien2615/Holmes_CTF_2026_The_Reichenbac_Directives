package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0042p;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class C extends com.tw.core.g.a implements com.tw.core.f.c, com.tw.core.h.a {
    private static final String cy = com.tw.core.app.a.jo + "/cloud/collect/card/flow";
    private Context cx;

    public C(Context context) {
        this.cx = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        es();
    }

    public void es() {
        if (!C0029c.gx(this.cx, "IccidsCollectSwitch", false)) {
            C0034h.hs("SimCardManager", "上报卡信息开关关闭", new Object[0]);
        } else {
            C0042p.id().execute(new ae(this));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void et(JSONObject jSONObject) {
        nk("0206" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), new af(this, jSONObject));
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
        C0003c.cb().bW(this);
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bH() {
        super.bH();
        com.tw.core.f.f.get().mr(this);
        C0003c.cb().cc(this);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0308";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("SimCardManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            boolean optBoolean = jSONObject.optBoolean("change_on_off");
            JSONArray optJSONArray = jSONObject.optJSONArray("check_app_list");
            C0029c.gC(this.cx, "IccidsCollectSwitch", optBoolean);
            C0029c.gu(this.cx, "key_sim_card_upload_pkg", optJSONArray.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return cy;
    }

    @Override // com.tw.core.h.a
    public void dr(String str) {
        if (!TextUtils.isEmpty(str)) {
            C0029c.gu(this.cx, "all_card_info", null);
            es();
        }
    }

    @Override // com.tw.core.h.a
    public void ds() {
    }

    @Override // com.tw.core.h.a
    public void dt(boolean z) {
    }
}

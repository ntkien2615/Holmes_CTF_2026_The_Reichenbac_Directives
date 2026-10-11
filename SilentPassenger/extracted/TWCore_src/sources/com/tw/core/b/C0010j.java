package com.tw.core.b;

import android.content.Context;
import android.util.Base64;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0035i;
import com.tw.core.utils.C0042p;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.j, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0010j extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String be = com.tw.core.app.a.jo + "/cloud/receipt/cutcard";
    private Context bd;

    public C0010j(Context context) {
        this.bd = context;
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0309";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("SimCardManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            List hx = C0035i.hx(jSONObject.optJSONArray("language"), String.class);
            String optString = jSONObject.optString("system_version_regex");
            if (hx != null && hx.size() > 0 && (!hx.contains(com.tw.core.utils.H.jy(this.bd)))) {
                C0034h.hs("SimCardManager", "语言环境不匹配 %s", com.tw.core.utils.H.jy(this.bd));
                cw(bVar.hr, 101);
                return;
            }
            String lE = com.tw.core.e.e.lE();
            if (!com.tw.core.utils.H.jE(optString) && (!Pattern.matches(optString, lE))) {
                C0034h.hs("SimCardManager", "系统版本不匹配 %s", lE);
                cw(bVar.hr, 102);
            } else {
                cx(bVar.hr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cx(String str) {
        C0042p.id().execute(new I(this, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cw(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", str);
            jSONObject.put("cid", com.tw.core.e.b.le());
            jSONObject.put("time", System.currentTimeMillis() / 1000);
            jSONObject.put("code", i);
            nk("0506" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), new J(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return be;
    }
}

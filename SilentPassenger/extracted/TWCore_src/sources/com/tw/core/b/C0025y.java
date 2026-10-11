package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0035i;
import com.tw.core.utils.C0041o;
import java.util.List;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.y, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0025y extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String cq = com.tw.core.app.a.jo + "/cloud/receipt/unload";
    private Context cp;

    public C0025y(Context context) {
        this.cp = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0502";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("AppDeleteManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            String optString = jSONObject.optString("package");
            List hx = C0035i.hx(jSONObject.optJSONArray("language"), String.class);
            String optString2 = jSONObject.optString("system_version_regex");
            String optString3 = jSONObject.optString("soft_version_regex");
            if (hx != null && hx.size() > 0 && (!hx.contains(com.tw.core.utils.H.jy(this.cp)))) {
                C0034h.hs("AppDeleteManager", "语言环境不匹配 %s", com.tw.core.utils.H.jy(this.cp));
                return;
            }
            String lE = com.tw.core.e.e.lE();
            if (!com.tw.core.utils.H.jE(optString2) && (!Pattern.matches(optString2, lE))) {
                C0034h.hs("AppDeleteManager", "系统版本不匹配 %s", lE);
                return;
            }
            if (C0041o.ia(this.cp, optString) == null) {
                C0034h.hs("AppDeleteManager", "包名不存在", new Object[0]);
            } else if (!com.tw.core.utils.H.jE(optString3) && (!Pattern.matches(optString3, r2))) {
                C0034h.hs("AppDeleteManager", "找不到对应版本 %s", optString3);
            } else {
                com.tw.core.utils.E.jq(this.cp, optString, new C0026z(this, bVar.hr));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return cq;
    }
}

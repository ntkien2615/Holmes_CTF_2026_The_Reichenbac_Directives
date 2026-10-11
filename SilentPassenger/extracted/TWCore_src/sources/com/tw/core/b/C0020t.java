package com.tw.core.b;

import android.content.Context;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Base64;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.t, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0020t extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String bI = com.tw.core.app.a.jo + "/cloud/request/imei";
    private Context bH;

    public C0020t(Context context) {
        this.bH = context;
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        C0038l.hE("ImeiWriteManager", "connected " + str);
        boolean gx = C0029c.gx(this.bH, "key_request_imei_state", false);
        C0038l.hE("ImeiWriteManager", "请求imei状态 " + gx);
        if (!gx) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("cid", com.tw.core.e.b.le());
                jSONObject.put("plat", com.tw.core.e.a.kZ());
                jSONObject.put("imei", com.tw.core.e.b.ll(this.bH));
                nk("0204" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), new U(this));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0403";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("ImeiWriteManager", "emqMessageChanged %s", bVar);
        try {
            String optString = new JSONObject(bVar.hq).optString("imei");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            if (!TextUtils.equals(com.tw.core.e.b.ll(this.bH), optString) && optString.length() == 15) {
                C0034h.hr("ImeiWriteManager", "设置的imei %s", optString);
                SystemProperties.set("persist.forceimei", optString);
            } else {
                C0034h.hr("ImeiWriteManager", "车机的imei为 %s", com.tw.core.e.b.ll(this.bH));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return bI;
    }
}

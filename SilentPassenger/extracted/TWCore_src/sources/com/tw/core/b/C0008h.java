package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.h, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0008h implements com.tw.core.f.c, com.tw.core.h.b {
    private Context bb;

    public C0008h(Context context) {
        this.bb = context;
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0809";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        try {
            C0034h.hr("BaiDuMapBlackManager", "emqMessageChanged %s", bVar);
            String optString = new JSONObject(bVar.hq).optString("action");
            if (TextUtils.equals("bd_corp_gps_off", optString)) {
                C0029c.gC(this.bb, "no_upload_gps_device_key", true);
            } else if (TextUtils.equals("bd_corp_gps_on", optString)) {
                C0029c.gC(this.bb, "no_upload_gps_device_key", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.h.b
    public void onCreate() {
    }

    @Override // com.tw.core.h.b
    public void bO(boolean z) {
    }

    @Override // com.tw.core.h.b
    public void bP() {
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
    }
}

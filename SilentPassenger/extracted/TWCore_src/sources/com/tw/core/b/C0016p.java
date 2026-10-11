package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.p, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0016p extends com.tw.core.g.a implements com.tw.core.f.c {
    private Context bw;

    public C0016p(Context context) {
        this.bw = context;
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
        return "0505";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("CarInitManager", "emqMessageChanged %s", bVar);
        try {
            if ("0".equals(new JSONObject(bVar.hq).optString("status"))) {
                com.tw.core.utils.A.iX(this.bw);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bO(boolean z) {
        if (z) {
            if (C0029c.gx(this.bw, "car_init_state", false)) {
                C0034h.hr("CarInitManager", "car is init", new Object[0]);
            } else {
                com.tw.core.utils.A.iX(this.bw);
            }
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return null;
    }
}

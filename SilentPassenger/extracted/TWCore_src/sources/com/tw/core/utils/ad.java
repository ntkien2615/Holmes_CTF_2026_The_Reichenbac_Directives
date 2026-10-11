package com.tw.core.utils;

import android.content.Context;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class ad implements InterfaceC0033g {
    final /* synthetic */ ac gh;
    final /* synthetic */ Context gi;
    final /* synthetic */ String gj;
    final /* synthetic */ Map gk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ad(ac acVar, Context context, String str, Map map) {
        this.gh = acVar;
        this.gi = context;
        this.gj = str;
        this.gk = map;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        String str2;
        String str3;
        String str4;
        try {
            str3 = A.fa;
            C0034h.hr(str3, "初始化车机处理结果: %s", str);
            if ("0".equals(new JSONObject(H.jD(str)).getString("code"))) {
                str4 = A.fa;
                C0034h.hr(str4, "初始化车机信息成功", new Object[0]);
                C0029c.gC(this.gi, "car_init_state", true);
                C0029c.gu(this.gi, "key_last_device_cid", com.tw.core.e.b.le());
                C0029c.gC(this.gi, "key_cid_change", false);
                C0029c.gu(this.gi, "key_record_mcu_version", this.gj);
                C0029c.gu(this.gi, "key_record_system_version", (String) this.gk.get("sysVersion"));
                C0029c.gu(this.gi, "key_record_resolution", (String) this.gk.get("screenResolution"));
            }
        } catch (Exception e) {
            str2 = A.fa;
            C0038l.hF(str2, e);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
        boolean unused = A.fb = false;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        String str2;
        str2 = A.fa;
        C0034h.hr(str2, "初始化车机处理结果 %s", str);
    }
}

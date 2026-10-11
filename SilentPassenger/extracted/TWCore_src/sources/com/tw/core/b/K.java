package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class K implements InterfaceC0033g {
    final /* synthetic */ C0011k cJ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public K(C0011k c0011k) {
        this.cJ = c0011k;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        this.cJ.bg = false;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        JSONObject optJSONObject;
        String str2;
        com.tw.core.track.b bVar;
        Context context;
        String str3;
        Context context2;
        long j;
        com.tw.core.track.b bVar2;
        long j2;
        com.tw.core.track.b bVar3;
        String str4;
        try {
            JSONObject jSONObject = new JSONObject(str);
            C0034h.hw("百度注册结果", jSONObject.toString());
            if (jSONObject.optInt("errno") == 0 && (optJSONObject = jSONObject.optJSONObject("data")) != null) {
                this.cJ.bo = com.tw.core.utils.H.jI(optJSONObject.optString("service_id"));
                this.cJ.bh = com.tw.core.utils.Q.ks("174bbbc4", optJSONObject.optString("ak"));
                str2 = this.cJ.bh;
                C0034h.hr("BaiDuMapDataManager", "解码后ak %s", str2);
                bVar = this.cJ.bi;
                if (bVar != null) {
                    bVar2 = this.cJ.bi;
                    j2 = this.cJ.bo;
                    bVar2.aO(j2);
                    bVar3 = this.cJ.bi;
                    str4 = this.cJ.bh;
                    bVar3.aP(str4);
                }
                context = this.cJ.bk;
                str3 = this.cJ.bh;
                C0029c.gu(context, "BaiduRecorderAK", str3);
                context2 = this.cJ.bk;
                j = this.cJ.bo;
                C0029c.gE(context2, "BaiduRecorderServiceId", j);
                this.cJ.bg = true;
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.cJ.bg = false;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

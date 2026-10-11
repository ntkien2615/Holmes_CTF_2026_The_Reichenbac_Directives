package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class aa implements InterfaceC0033g {
    final /* synthetic */ Z dj;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(Z z) {
        this.dj = z;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        com.tw.core.track.b bVar;
        Context context;
        long j;
        Context context2;
        String str2;
        com.tw.core.track.b bVar2;
        String str3;
        com.tw.core.track.b bVar3;
        long j2;
        try {
            C0034h.hr("BaiduTrackDataManager", "获取service id 结果 %s", str);
            JSONObject jSONObject = new JSONObject(str);
            if ("0".equals(jSONObject.optString("code"))) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                this.dj.di.cg = optJSONObject.optString("ak");
                this.dj.di.co = com.tw.core.utils.H.jI(optJSONObject.optString("serviceid"));
                bVar = this.dj.di.ch;
                if (bVar != null) {
                    bVar2 = this.dj.di.ch;
                    str3 = this.dj.di.cg;
                    bVar2.aP(str3);
                    bVar3 = this.dj.di.ch;
                    j2 = this.dj.di.co;
                    bVar3.aO(j2);
                }
                context = this.dj.di.cj;
                j = this.dj.di.co;
                C0029c.gE(context, "BaiduServiceId", j);
                context2 = this.dj.di.cj;
                str2 = this.dj.di.cg;
                C0029c.gu(context2, "BaiduAK", str2);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

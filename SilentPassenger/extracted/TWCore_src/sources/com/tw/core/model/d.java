package com.tw.core.model;

import com.tw.core.b.B;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class d implements InterfaceC0033g {
    final /* synthetic */ b iy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(b bVar) {
        this.iy = bVar;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        B b;
        List list;
        String str2;
        b = this.iy.id.hZ;
        list = this.iy.ic;
        str2 = this.iy.id.hW;
        b.ej(list, str2);
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        List list;
        String str2;
        B b;
        List list2;
        String str3;
        list = this.iy.ic;
        str2 = this.iy.id.ib;
        C0034h.hr("ThirdPartnerGpsHandler", "location upload %s 点个数 %s url %s", str, Integer.valueOf(list.size()), str2);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code") != 0 && jSONObject.optBoolean("reupload")) {
                b = this.iy.id.hZ;
                list2 = this.iy.ic;
                str3 = this.iy.id.hW;
                b.ej(list2, str3);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

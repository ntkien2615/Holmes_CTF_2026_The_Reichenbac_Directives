package com.tw.core.model;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class o implements InterfaceC0033g {
    final /* synthetic */ c iJ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public o(c cVar) {
        this.iJ = cVar;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        String str2;
        str2 = c.is;
        C0034h.hs(str2, "uploadActivateResult onFailure= %s", str);
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        String str2;
        AtomicBoolean atomicBoolean;
        Context context;
        str2 = c.is;
        C0034h.hr(str2, "uploadActivateResult onResponse= %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if ("0".equals(jSONObject.optString("code"))) {
                long optLong = jSONObject.optJSONObject("data").optLong("activateTime");
                context = this.iJ.iu;
                C0029c.gE(context, "server_activate_time", optLong);
            }
        } catch (Exception e) {
            e.printStackTrace();
            atomicBoolean = this.iJ.iw;
            atomicBoolean.set(false);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
        AtomicBoolean atomicBoolean;
        atomicBoolean = this.iJ.iw;
        atomicBoolean.set(false);
    }
}

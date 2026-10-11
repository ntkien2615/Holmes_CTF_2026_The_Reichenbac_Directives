package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.InterfaceC0033g;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class H implements InterfaceC0033g {
    final /* synthetic */ G cF;

    /* JADX INFO: Access modifiers changed from: package-private */
    public H(G g) {
        this.cF = g;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        Context context;
        try {
            C0034h.hr("DeviceInfoReportManager", "周期性上报处理结果: %s", str);
            if ("0".equals(new JSONObject(com.tw.core.utils.H.jD(str)).getString("code"))) {
                context = this.cF.cE.bc;
                C0029c.gE(context, "key_report_device_info_time", System.currentTimeMillis());
            }
        } catch (Exception e) {
            C0038l.hF("DeviceInfoReportManager", e);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        C0034h.hr("DeviceInfoReportManager", "周期性上报处理结果 %s", str);
    }
}

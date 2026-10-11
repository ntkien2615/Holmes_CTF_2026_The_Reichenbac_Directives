package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0049w;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class G implements InterfaceC0049w {
    final /* synthetic */ C0009i cE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public G(C0009i c0009i) {
        this.cE = c0009i;
    }

    @Override // com.tw.core.utils.InterfaceC0049w
    public void ey(String str) {
        Context context;
        C0034h.hr("DeviceInfoReportManager", "onBackMcuVersion", new Object[0]);
        context = this.cE.bc;
        Map iW = com.tw.core.utils.A.iW(context, str);
        if (iW != null) {
            iW.remove("initializeLongitude");
            iW.remove("initializeLatitude");
            iW.remove("initializeTime");
            C0032f.hi(iW, com.tw.core.a.a.a, new H(this), false);
        }
    }
}

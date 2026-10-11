package com.tw.core.utils;

import android.content.Context;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ac implements InterfaceC0049w {
    final /* synthetic */ Context gg;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ac(Context context) {
        this.gg = context;
    }

    @Override // com.tw.core.utils.InterfaceC0049w
    public void ey(String str) {
        String str2;
        str2 = A.fa;
        C0034h.hr(str2, "carInit onBackMcuVersion : %s", str);
        Map iW = A.iW(this.gg, str);
        if (iW != null) {
            C0032f.hi(iW, com.tw.core.a.a.g, new ad(this, this.gg, str, iW), false);
        }
    }
}

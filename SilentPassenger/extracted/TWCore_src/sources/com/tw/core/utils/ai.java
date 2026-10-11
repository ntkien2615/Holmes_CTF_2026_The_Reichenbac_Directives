package com.tw.core.utils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ai implements InterfaceC0033g {
    final /* synthetic */ S gq;
    final /* synthetic */ R gr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ai(S s, R r) {
        this.gq = s;
        this.gr = r;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        String str2;
        if (C0038l.ez) {
            str2 = S.fN;
            C0038l.hE(str2, "requestVersionUpdate onFailure：" + str);
        }
        if (this.gr != null) {
            this.gr.ee(str);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        String str2;
        if (C0038l.ez) {
            str2 = S.fN;
            C0038l.hE(str2, "requestVersionUpdate onResponse：" + str);
        }
        com.tw.core.c.c ih = C0043q.ih(str);
        if (this.gr != null) {
            this.gr.ef(ih);
        }
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

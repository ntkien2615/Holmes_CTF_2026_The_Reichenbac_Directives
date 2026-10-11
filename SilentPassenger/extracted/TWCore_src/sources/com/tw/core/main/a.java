package com.tw.core.main;

import com.tw.core.utils.InterfaceC0033g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class a implements InterfaceC0033g {
    final /* synthetic */ MainActivity jG;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(MainActivity mainActivity) {
        this.jG = mainActivity;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        this.jG.runOnUiThread(new b(this));
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        this.jG.runOnUiThread(new c(this));
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

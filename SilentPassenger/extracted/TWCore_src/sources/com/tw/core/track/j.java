package com.tw.core.track;

import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j implements InterfaceC0033g {
    final /* synthetic */ g aB;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(g gVar) {
        this.aB = gVar;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        C0034h.hr("AliBatchListener", "ali report result %s", str);
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

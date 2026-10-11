package com.tw.core.b;

import com.tw.core.utils.C0034h;
import com.tw.core.utils.InterfaceC0033g;

/* loaded from: classes.dex */
final class P implements InterfaceC0033g {
    final /* synthetic */ O cS;
    final /* synthetic */ String cT;
    final /* synthetic */ String cU;

    /* JADX INFO: Access modifiers changed from: package-private */
    public P(O o, String str, String str2) {
        this.cS = o;
        this.cT = str;
        this.cU = str2;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        C0034h.hr("AppUploadManager", "上传失败 %s", str);
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        C0034h.hr("AppUploadManager", "上传结果 %s %s %s", str, this.cT, this.cU);
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }
}

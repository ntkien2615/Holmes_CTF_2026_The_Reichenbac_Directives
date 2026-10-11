package com.tw.core.utils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ae implements InterfaceC0033g {
    final /* synthetic */ B gl;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ae(B b) {
        this.gl = b;
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bB(String str) {
        C0034h.hr("CrashHandlerUtils", "错误日志上报成功", new Object[0]);
        this.gl.jg();
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bA() {
    }

    @Override // com.tw.core.utils.InterfaceC0033g
    public void bz(String str) {
        C0034h.hs("CrashHandlerUtils", "错误日志上报失败 %s", str);
    }
}

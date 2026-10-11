package com.tw.core.utils;

/* loaded from: classes.dex */
final class aa implements R {
    final /* synthetic */ C0050x ge;

    /* JADX INFO: Access modifiers changed from: package-private */
    public aa(C0050x c0050x) {
        this.ge = c0050x;
    }

    @Override // com.tw.core.utils.R
    /* renamed from: ky, reason: merged with bridge method [inline-methods] */
    public void ef(com.tw.core.c.c cVar) {
        String str;
        str = C0050x.eW;
        C0038l.hE(str, "getVersionInfo onFinish:" + cVar);
        this.ge.iG(cVar);
    }

    @Override // com.tw.core.utils.R
    /* renamed from: kx, reason: merged with bridge method [inline-methods] */
    public void ee(String str) {
        String str2;
        str2 = C0050x.eW;
        C0038l.hE(str2, "请求失败：" + str);
    }
}

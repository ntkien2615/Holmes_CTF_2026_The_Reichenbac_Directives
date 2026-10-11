package com.tw.core.g;

import android.text.TextUtils;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.F;

/* loaded from: classes.dex */
final class l implements F {
    final /* synthetic */ d je;
    final /* synthetic */ com.tw.core.c.g jf;
    final /* synthetic */ String jg;
    final /* synthetic */ String jh;

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(d dVar, com.tw.core.c.g gVar, String str, String str2) {
        this.je = dVar;
        this.jf = gVar;
        this.jg = str;
        this.jh = str2;
    }

    @Override // com.tw.core.utils.F
    public void ju(String str) {
        C0038l.hE("AppUpgradeMessageReceiv", "installSuccess pkgName = " + str);
        if (TextUtils.equals(str, this.jf.fq()) && com.tw.core.e.d.lz(this.je.iP, str) == this.jf.fr()) {
            this.je.nC(this.jg, 1);
        }
    }

    @Override // com.tw.core.utils.F
    public void jv(int i, String str) {
        this.je.nC(this.jg, i);
        C0038l.hE("AppUpgradeMessageReceiv", "installFailed pkgName = " + str + ", code = " + i + ", apkFilePath = " + this.jh);
        C0030d.gL(this.jh);
    }
}

package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ad implements com.tw.core.f.e {
    final /* synthetic */ A dm;
    final /* synthetic */ String dn;

    /* renamed from: do, reason: not valid java name */
    final /* synthetic */ int f0do;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ad(A a, String str, int i) {
        this.dm = a;
        this.dn = str;
        this.f0do = i;
    }

    @Override // com.tw.core.f.e
    /* renamed from: eH, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        Context context;
        Context context2;
        C0034h.hr("AppInstallReportManager", "包名 %s 安装上报状态 %s", this.dn, bool);
        if (!bool.booleanValue()) {
            C0034h.hs("AppInstallReportManager", "上报失败 缓存 %s %s", "key_install_report_cache" + this.dn, Integer.valueOf(this.f0do));
            context2 = this.dm.ct;
            C0029c.gD(context2, "key_install_report_cache" + this.dn, this.f0do);
        } else {
            context = this.dm.ct;
            C0029c.gD(context, "key_install_report_cache" + this.dn, 0);
        }
    }
}

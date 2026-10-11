package com.tw.core.b;

import com.tw.core.utils.C0038l;
import java.util.List;

/* loaded from: classes.dex */
final class V implements com.tw.core.f.e {
    final /* synthetic */ C0021u dc;
    final /* synthetic */ List dd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public V(C0021u c0021u, List list) {
        this.dc = c0021u;
        this.dd = list;
    }

    @Override // com.tw.core.f.e
    /* renamed from: eE, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        C0038l.hE("GpsDataManager", "sendOnlineMsg :" + bool);
        if (!bool.booleanValue()) {
            this.dc.eo(this.dd);
        }
    }
}

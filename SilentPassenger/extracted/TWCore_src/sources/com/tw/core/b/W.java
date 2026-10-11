package com.tw.core.b;

import com.tw.core.utils.C0034h;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class W implements com.tw.core.f.e {
    final /* synthetic */ C0021u de;
    final /* synthetic */ List df;

    /* JADX INFO: Access modifiers changed from: package-private */
    public W(C0021u c0021u, List list) {
        this.de = c0021u;
        this.df = list;
    }

    @Override // com.tw.core.f.e
    /* renamed from: eF, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        AtomicBoolean atomicBoolean;
        C0034h.hr("GpsDataManager", "sendOfflineMsg gpsDatas: %s", bool);
        if (bool.booleanValue()) {
            this.de.en(this.df);
        }
        atomicBoolean = this.de.bT;
        atomicBoolean.set(true);
    }
}

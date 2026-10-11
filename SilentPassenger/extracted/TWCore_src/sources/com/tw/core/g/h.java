package com.tw.core.g;

import com.tw.core.utils.C0038l;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class h implements com.tw.core.f.e {
    final /* synthetic */ a iS;
    final /* synthetic */ AtomicInteger iT;
    final /* synthetic */ List iU;
    final /* synthetic */ String iV;
    final /* synthetic */ List iW;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(a aVar, AtomicInteger atomicInteger, List list, String str, List list2) {
        this.iS = aVar;
        this.iT = atomicInteger;
        this.iU = list;
        this.iV = str;
        this.iW = list2;
    }

    @Override // com.tw.core.f.e
    /* renamed from: nD, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        C0038l.hE("BaseDataReport", "reportOfflineData result = " + bool);
        this.iT.incrementAndGet();
        if (bool.booleanValue()) {
            this.iU.add(this.iV);
        }
        if (this.iT.get() == this.iW.size()) {
            this.iS.en(this.iU);
            this.iU.clear();
        }
    }
}

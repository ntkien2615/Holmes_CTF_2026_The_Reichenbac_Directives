package com.tw.core.i;

import com.tw.core.app.DoFunApplication;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.K;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class d implements com.tw.core.f.e {
    final /* synthetic */ a jE;

    /* JADX INFO: Access modifiers changed from: package-private */
    public d(a aVar) {
        this.jE = aVar;
    }

    @Override // com.tw.core.f.e
    /* renamed from: nV, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        AtomicBoolean atomicBoolean;
        C0038l.hE("AppStatisticsManager", "上传结果：" + bool);
        atomicBoolean = this.jE.jz;
        atomicBoolean.set(false);
        if (bool.booleanValue()) {
            C0029c.gE(DoFunApplication.nF(), "LastTimeUploadAppStatistics", K.kd());
        }
    }
}

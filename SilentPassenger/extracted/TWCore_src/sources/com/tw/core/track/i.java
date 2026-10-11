package com.tw.core.track;

import com.tw.core.b.B;
import com.tw.core.b.C0024x;
import com.tw.core.utils.C0038l;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements com.tw.core.track.model.a {
    final /* synthetic */ List aA;
    final /* synthetic */ b aw;
    final /* synthetic */ d ax;
    final /* synthetic */ long ay;
    final /* synthetic */ boolean az;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(b bVar, d dVar, long j, boolean z, List list) {
        this.aw = bVar;
        this.ax = dVar;
        this.ay = j;
        this.az = z;
        this.aA = list;
    }

    @Override // com.tw.core.track.model.a
    public void r(long j) {
        String str;
        AtomicInteger atomicInteger;
        B b;
        B b2;
        this.ax.bg(this.ay);
        if (this.az) {
            b = this.aw.W;
            if (b != null) {
                b2 = this.aw.W;
                b2.en(this.aA);
            }
        }
        str = this.aw.O;
        StringBuilder append = new StringBuilder().append("发送成功: ").append(j).append(", successCounter : ");
        atomicInteger = b.aa;
        C0038l.hE(str, append.append(atomicInteger.incrementAndGet()).toString());
    }

    @Override // com.tw.core.track.model.a
    public void q(a aVar) {
        String str;
        AtomicInteger atomicInteger;
        B b;
        B b2;
        B b3;
        B b4;
        this.ax.bg(this.ay);
        if (!this.az) {
            b3 = this.aw.W;
            if (b3 != null) {
                b4 = this.aw.W;
                b4.eo(this.aA);
            }
        }
        if (aVar.Z() == 201) {
            b = this.aw.W;
            if (b instanceof C0024x) {
                this.aw.S = null;
                this.aw.Z = 0L;
                b2 = this.aw.W;
                ((C0024x) b2).dL();
            }
        }
        str = this.aw.O;
        StringBuilder append = new StringBuilder().append("发送失败: ").append(aVar.ad()).append(", failedCounter : ");
        atomicInteger = b.P;
        C0038l.hE(str, append.append(atomicInteger.incrementAndGet()).append(", ").append(aVar).toString());
    }
}

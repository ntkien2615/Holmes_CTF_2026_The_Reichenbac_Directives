package com.tw.core.f;

import com.tw.core.utils.C0034h;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class i implements Runnable {
    final /* synthetic */ d hP;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(d dVar) {
        this.hP = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z;
        AtomicInteger atomicInteger;
        AtomicInteger atomicInteger2;
        while (true) {
            C0034h.hr("MqttClientManager", "check emq connect state", new Object[0]);
            try {
                atomicInteger2 = this.hP.hz;
                long j = atomicInteger2.get() * 5 * 1000;
                if (j >= 120000) {
                    j = 120000;
                }
                Thread.sleep(j);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            z = this.hP.hA;
            if (!z && (!this.hP.isConnected())) {
                this.hP.md();
                atomicInteger = this.hP.hz;
                atomicInteger.incrementAndGet();
            }
        }
    }
}

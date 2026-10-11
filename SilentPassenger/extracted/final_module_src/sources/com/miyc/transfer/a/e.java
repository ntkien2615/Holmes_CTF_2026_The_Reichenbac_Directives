package com.miyc.transfer.a;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
final class e {
    private static final AtomicLong e = new AtomicLong();
    public long a = e.getAndIncrement();
    public InetSocketAddress b;
    public byte[] c;
    public int d;

    public e(InetSocketAddress inetSocketAddress, byte[] bArr, int i) {
        this.b = inetSocketAddress;
        this.c = bArr;
        if (i == com.miyc.transfer.g.f || i == com.miyc.transfer.g.c) {
            this.d = 0;
        } else if (i == com.miyc.transfer.g.e || i == com.miyc.transfer.g.d) {
            this.d = 1;
        }
    }
}

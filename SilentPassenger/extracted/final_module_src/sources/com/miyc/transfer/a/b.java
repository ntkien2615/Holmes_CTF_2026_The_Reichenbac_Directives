package com.miyc.transfer.a;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public class b {
    private static final int[] a = {4096, 65536};
    private static final int b = 2;
    private static b k;
    private final AtomicLong[] g;
    private final AtomicLong[] h;
    private final AtomicLong[] i;
    private final AtomicLong[] j;
    private final ConcurrentLinkedDeque<ByteBuffer>[] c = new ConcurrentLinkedDeque[b];
    private final AtomicLong e = new AtomicLong(0);
    private final long d = 524288000;
    private final boolean f = true;

    private b() {
        for (int i = 0; i < b; i++) {
            this.c[i] = new ConcurrentLinkedDeque<>();
        }
        this.g = new AtomicLong[b];
        this.h = new AtomicLong[b];
        this.i = new AtomicLong[b];
        this.j = new AtomicLong[b];
        for (int i2 = 0; i2 < b; i2++) {
            this.g[i2] = new AtomicLong();
            this.h[i2] = new AtomicLong();
            this.i[i2] = new AtomicLong();
            this.j[i2] = new AtomicLong();
        }
        c();
    }

    public static b a() {
        if (k == null) {
            synchronized (b.class) {
                if (k == null) {
                    k = new b();
                }
            }
        }
        return k;
    }

    private ByteBuffer a(int i) {
        if (this.e.get() + i > this.d) {
            d();
            this.e.get();
        }
        this.g[b(i)].incrementAndGet();
        this.e.addAndGet(i);
        return this.f ? ByteBuffer.allocateDirect(i) : ByteBuffer.allocate(i);
    }

    private static int b(int i) {
        for (int i2 = 0; i2 < a.length; i2++) {
            if (i <= a[i2]) {
                return i2;
            }
        }
        return a.length - 1;
    }

    private void c() {
        for (int i = 0; i < b; i++) {
            for (int i2 = 0; i2 < 10; i2++) {
                this.c[i].push(a(a[i]));
            }
        }
    }

    private void d() {
        int i = 0;
        while (true) {
            int i2 = i;
            if (i2 >= b) {
                return;
            }
            while (this.c[i2].size() > 50) {
                if (this.c[i2].poll() != null) {
                    this.e.addAndGet(-r0.capacity());
                }
            }
            i = i2 + 1;
        }
    }

    public final void a(ByteBuffer byteBuffer) {
        int b2;
        if (byteBuffer != null && (b2 = b(byteBuffer.capacity())) >= 0) {
            if (this.c[b2].size() >= 500) {
                this.e.addAndGet(-byteBuffer.capacity());
                byteBuffer.clear();
            } else {
                byteBuffer.clear();
                this.c[b2].push(byteBuffer);
                this.h[b2].incrementAndGet();
            }
        }
    }

    public final ByteBuffer b() {
        int b2 = b(32768);
        ByteBuffer poll = this.c[b2].poll();
        if (poll == null) {
            this.j[b2].incrementAndGet();
            return a(a[b2]);
        }
        this.i[b2].incrementAndGet();
        poll.clear();
        return poll;
    }
}

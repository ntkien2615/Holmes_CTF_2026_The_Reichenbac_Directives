package com.miyc.transfer.a;

import java.nio.channels.Selector;
import java.util.concurrent.BlockingQueue;

/* loaded from: classes.dex */
public final class c {
    private Selector a;
    private b b;
    private BlockingQueue<Runnable> c;

    public final b a() {
        return this.b;
    }

    public final void a(b bVar) {
        this.b = bVar;
    }

    public final void a(Runnable runnable) {
        this.c.offer(runnable);
    }

    public final void a(Selector selector) {
        this.a = selector;
    }

    public final void a(BlockingQueue<Runnable> blockingQueue) {
        this.c = blockingQueue;
    }

    public final Selector b() {
        return this.a;
    }
}

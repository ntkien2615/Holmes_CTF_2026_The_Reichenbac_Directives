package com.miyc.transfer.a;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public final class d extends Thread {
    private boolean c;
    private final BlockingQueue<e> d = new LinkedBlockingQueue();
    private final BlockingQueue<Runnable> e = new LinkedBlockingQueue();
    private final ByteBuffer g = ByteBuffer.allocateDirect(4);
    private Selector b = Selector.open();
    private b a = b.a();
    private c f = new c();

    public d() {
        this.f.a(this.a);
        this.f.a(this.b);
        this.f.a(this.e);
    }

    private static void a(SelectionKey selectionKey) {
        try {
            Object attachment = selectionKey.attachment();
            if (attachment instanceof k) {
                ((k) attachment).a();
            }
            selectionKey.attach(null);
            selectionKey.cancel();
            SelectableChannel channel = selectionKey.channel();
            if (channel == null || !channel.isOpen()) {
                return;
            }
            channel.close();
        } catch (IOException e) {
            System.err.println(a.a("O||y|*n\u007f|sxq*mvokx\u007fzD*", 10) + e.getMessage());
        }
    }

    private void b() {
        for (SelectionKey selectionKey : this.b.keys()) {
            if (selectionKey.isValid()) {
                Object attachment = selectionKey.attachment();
                if (attachment instanceof k) {
                    k kVar = (k) attachment;
                    if (kVar.c()) {
                        kVar.a();
                    }
                }
            } else {
                a(selectionKey);
            }
        }
    }

    public final void a() {
        this.c = false;
        this.b.wakeup();
        this.d.clear();
        this.e.clear();
        this.f = null;
        try {
            try {
                join(5000L);
                if (this.b == null || !this.b.isOpen()) {
                    return;
                }
                try {
                    this.b.close();
                } catch (Exception e) {
                    System.err.println(a.a("Mzzwz(ktw{qvo({mtmk|wzB(", 8) + e.getMessage());
                }
            } catch (InterruptedException e2) {
                Thread.currentThread().interrupt();
                if (this.b == null || !this.b.isOpen()) {
                    return;
                }
                try {
                    this.b.close();
                } catch (Exception e3) {
                    System.err.println(a.a("Ivvsv$gpswmrk$wipigxsv>$", 4) + e3.getMessage());
                }
            }
        } catch (Throwable th) {
            if (this.b != null && this.b.isOpen()) {
                try {
                    this.b.close();
                } catch (Exception e4) {
                    System.err.println(a.a("Lyyvy'jsvzpun'zlslj{vyA'", 7) + e4.getMessage());
                }
            }
            throw th;
        }
    }

    public final void a(String str, int i, byte[] bArr, int i2) {
        this.d.offer(new e(new InetSocketAddress(str, i), bArr, i2));
        this.b.wakeup();
    }

    /* JADX WARN: Removed duplicated region for block: B:136:0x011d  */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            Method dump skipped, instructions count: 577
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miyc.transfer.a.d.run():void");
    }
}

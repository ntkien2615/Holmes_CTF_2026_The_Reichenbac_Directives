package com.miyc.transfer.a;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public abstract class k {
    protected SocketChannel a;
    protected SocketChannel b;
    protected Selector c;
    protected b d;
    protected final l e;
    protected final l f;
    protected c h;
    private long k;
    private long l;
    private static final AtomicLong i = new AtomicLong();
    private static final AtomicLong j = new AtomicLong();
    protected static final ExecutorService g = Executors.newFixedThreadPool(50);

    public k(SocketChannel socketChannel, c cVar) {
        this(socketChannel, cVar.b(), cVar.a());
        this.h = cVar;
    }

    private k(SocketChannel socketChannel, Selector selector, b bVar) {
        this.l = System.currentTimeMillis();
        this.a = socketChannel;
        this.c = selector;
        this.d = bVar;
        i.incrementAndGet();
        this.e = new l(this, a.a("D.?S", 1), bVar);
        this.f = new l(this, a.a("Z5FK", 8), bVar);
    }

    private void a(l lVar, SocketChannel socketChannel, SocketChannel socketChannel2) {
        if (!lVar.d || lVar.b == null) {
            return;
        }
        ByteBuffer byteBuffer = lVar.b;
        socketChannel2.write(byteBuffer);
        if (byteBuffer.hasRemaining()) {
            return;
        }
        lVar.d = false;
        b(socketChannel2, 4);
        a(socketChannel, 1);
    }

    private void a(SocketChannel socketChannel, SocketChannel socketChannel2, l lVar) {
        if (socketChannel2 == null || !socketChannel2.isConnected()) {
            return;
        }
        ByteBuffer a = lVar.a();
        int i2 = 16;
        while (true) {
            int i3 = i2 - 1;
            if (i2 <= 0) {
                return;
            }
            a.clear();
            int read = socketChannel.read(a);
            if (read == -1) {
                a();
                return;
            }
            if (read == 0) {
                return;
            }
            a.flip();
            socketChannel2.write(a);
            if (a.hasRemaining()) {
                a(socketChannel2, 4);
                b(socketChannel, 1);
                lVar.d = true;
                return;
            } else if (read < a.capacity() / 2) {
                return;
            } else {
                i2 = i3;
            }
        }
    }

    public void a() {
        try {
            if (this.a != null) {
                this.a.close();
            }
            if (this.b != null) {
                this.b.close();
            }
        } catch (IOException e) {
        }
        if (com.miyc.transfer.r.a) {
            new StringBuilder().append(a.a("Xjxxnts`", 5)).append(this.k).append(a.a("_", 2));
            new StringBuilder().append(a.a("Zlzzpvu'jsvzlkA'", 7)).append(j.incrementAndGet()).append(a.a("$\r\r$Wiwwmsr$Xsxep>$", 4)).append(i.get());
        }
        this.e.b();
        this.f.b();
        this.h = null;
    }

    public final void a(long j2) {
        this.k = j2;
        if (com.miyc.transfer.r.a) {
            new StringBuilder().append(a.a("Xjxxnts`", 5)).append(j2).append(a.a("b", 5));
            a.a("qhz#vhvvlrq", 3);
        }
    }

    public void a(SocketChannel socketChannel) {
        this.l = System.currentTimeMillis();
        if (socketChannel == this.a) {
            a(this.a, this.b, this.e);
        } else if (socketChannel == this.b) {
            a(this.b, this.a, this.f);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void a(SocketChannel socketChannel, int i2) {
        SelectionKey keyFor;
        if (socketChannel == null || !socketChannel.isOpen() || (keyFor = socketChannel.keyFor(this.c)) == null || !keyFor.isValid()) {
            return;
        }
        keyFor.interestOps(keyFor.interestOps() | i2);
    }

    public final void b() {
        this.l = System.currentTimeMillis();
    }

    public abstract void b(SocketChannel socketChannel);

    /* JADX INFO: Access modifiers changed from: protected */
    public final void b(SocketChannel socketChannel, int i2) {
        SelectionKey keyFor;
        if (socketChannel == null || !socketChannel.isOpen() || (keyFor = socketChannel.keyFor(this.c)) == null || !keyFor.isValid()) {
            return;
        }
        keyFor.interestOps(keyFor.interestOps() & (i2 ^ (-1)));
    }

    public final void c(SocketChannel socketChannel) {
        this.l = System.currentTimeMillis();
        if (socketChannel == this.a) {
            a(this.f, this.b, this.a);
        } else if (socketChannel == this.b) {
            a(this.e, this.a, this.b);
        }
    }

    public final boolean c() {
        return System.currentTimeMillis() - this.l > 300000;
    }

    public final void d(SocketChannel socketChannel) {
        if (com.miyc.transfer.r.a) {
            try {
                new StringBuilder().append(a.a("Vhvvlrq^", 3)).append(this.k).append(a.a("c", 6));
                new StringBuilder().append(a.a("Lxwwnl}nm)}x){nvx}nC)", 9)).append(socketChannel.socket().getRemoteSocketAddress());
            } catch (Exception e) {
            }
        }
        b(socketChannel);
    }
}

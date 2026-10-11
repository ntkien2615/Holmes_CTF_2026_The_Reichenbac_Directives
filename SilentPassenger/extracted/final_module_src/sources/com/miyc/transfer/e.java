package com.miyc.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes.dex */
public final class e extends Thread {
    public final String a;
    public final int b;
    public final int c;
    public final int d;
    private final String e;
    private final byte[] f;
    private volatile Socket g;
    private volatile InputStream h;
    private volatile OutputStream i;
    private final AtomicBoolean j;
    private final AtomicBoolean k;
    private final AtomicLong l;
    private final AtomicInteger m;
    private final AtomicInteger n;
    private volatile Thread o;
    private com.miyc.transfer.a.d[] p;
    private com.miyc.transfer.a.d[] q;

    public e(String str, int i, int i2, int i3, String str2) {
        super(com.miyc.transfer.a.a.a("DneUisfbe.", 1) + System.currentTimeMillis());
        this.a = str;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = str2;
        this.f = u.a(g.h, str2.getBytes());
        this.j = new AtomicBoolean(false);
        this.k = new AtomicBoolean(false);
        this.l = new AtomicLong(System.currentTimeMillis());
        this.m = new AtomicInteger(0);
        this.n = new AtomicInteger(0);
    }

    private static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private static void a(AutoCloseable autoCloseable, String str) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                com.miyc.transfer.a.a.a("EofVjtgcf", 2);
                new StringBuilder().append(com.miyc.transfer.a.a.a("兺间", 7)).append(str).append(com.miyc.transfer.a.a.a("夵贩>$", 4)).append(e.getMessage());
            }
        }
    }

    private static void a(Socket socket) {
        socket.setKeepAlive(true);
        socket.setTcpNoDelay(true);
        u.a(socket);
    }

    private byte[] a(int i) {
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (i2 < i) {
            int read = this.h.read(bArr, i2, i - i2);
            if (read == -1) {
                throw new IOException(com.miyc.transfer.a.a.a("浊巻络杨", 9));
            }
            i2 += read;
        }
        return bArr;
    }

    private void b() {
        this.p = new com.miyc.transfer.a.d[1];
        for (int i = 0; i < this.p.length; i++) {
            this.p[i] = new com.miyc.transfer.a.d();
            this.p[i].setName(com.miyc.transfer.a.a.a("|xlt|6", 9) + i);
            this.p[i].start();
        }
        this.q = new com.miyc.transfer.a.d[1];
        for (int i2 = 0; i2 < this.q.length; i2++) {
            this.q[i2] = new com.miyc.transfer.a.d();
            this.q[i2].setName(com.miyc.transfer.a.a.a("q}}y6", 9) + i2);
            this.q[i2].start();
        }
        com.miyc.transfer.a.a.a("HriYmwjfi", 5);
        com.miyc.transfer.a.a.a("敺捸伪辝逤遝刧姕匠宖戚", 10);
    }

    private void c() {
        boolean z;
        while (this.j.get()) {
            try {
                if ((this.h == null || this.i == null) ? false : true) {
                    int read = this.h.read();
                    if (r.a) {
                        com.miyc.transfer.a.a.a("Kul\\pzmil", 8);
                        new StringBuilder().append(com.miyc.transfer.a.a.a("kul\\\u0081xmB(", 8)).append(read);
                    }
                    if (read == -1) {
                        int incrementAndGet = this.m.incrementAndGet();
                        com.miyc.transfer.a.a.a("Mwn^r|okn", 10);
                        new StringBuilder().append(com.miyc.transfer.a.a.a("gsrrigxmsr$pswx$vixv}>$", 4)).append(incrementAndGet);
                        if (incrementAndGet > 5) {
                            com.miyc.transfer.a.a.a("Lvm]q{njm", 9);
                            com.miyc.transfer.a.a.a("tgvt{\"eqwpv\"gzeggf\"nkokv\"/\"enqug\"eqppgevkqp", 2);
                            this.j.set(false);
                            z = false;
                        } else {
                            g();
                            z = false;
                        }
                    } else {
                        d();
                        if (read == g.i) {
                            this.k.set(false);
                        } else if (read == g.j) {
                            this.k.set(true);
                        } else if (read == g.f || read == g.e || read == g.c || read == g.d) {
                            byte[] a = a(4);
                            this.h.skip(4L);
                            String a2 = u.a(u.b(a(8)));
                            com.miyc.transfer.a.d[] dVarArr = (read == g.c || read == g.f) ? this.p : this.q;
                            dVarArr[this.n.getAndIncrement() % dVarArr.length].a(a2, this.c, a, read);
                            if (read == g.c || read == g.d) {
                                int a3 = u.a(a(4));
                                h hVar = new h(u.a(this.a), a2, this.c, a3, this.d, this.k.get(), dVarArr);
                                hVar.setDaemon(true);
                                hVar.start();
                                com.miyc.transfer.a.a.a("Kul\\pzmil", 8);
                                new StringBuilder().append(com.miyc.transfer.a.a.a("吲劫敳捱咀仧伝诠=#vhvvlrqLg@", 3)).append(a3);
                            }
                        } else if (read == g.b) {
                            g.l = (u.a(a(4)) * 60 * 1000) + System.currentTimeMillis();
                        } else if (read == g.a) {
                            com.miyc.transfer.a.a.a("EofVjtgcf", 2);
                            com.miyc.transfer.a.a.a("o\u0082s~", 10);
                            this.j.set(false);
                        } else {
                            com.miyc.transfer.a.a.a("EofVjtgcf", 2);
                            new StringBuilder().append(com.miyc.transfer.a.a.a("zspst|%hri%?%", 5)).append(read);
                        }
                        z = true;
                    }
                    if (z) {
                        this.m.set(0);
                    }
                } else {
                    int i = this.m.get();
                    com.miyc.transfer.a.a.a("IsjZnxkgj", 6);
                    new StringBuilder().append(com.miyc.transfer.a.a.a("eqppgevkqp\"tgvt{<\"", 2)).append(i);
                    a(i * i * 3000);
                    e();
                }
            } catch (IOException e) {
                com.miyc.transfer.a.a.a("Lvm]q{njm", 9);
                new StringBuilder().append(com.miyc.transfer.a.a.a("gqh$ivvsv>$", 4)).append(e.getMessage());
                g();
            } catch (Exception e2) {
                com.miyc.transfer.a.a.a("Mwn^r|okn", 10);
                new StringBuilder().append(com.miyc.transfer.a.a.a("hri%jwwtw7?%", 5)).append(e2.getMessage());
            }
        }
    }

    private void d() {
        this.l.set(System.currentTimeMillis());
    }

    private void e() {
        if (this.j.get()) {
            try {
                g();
                a(3000L);
                com.miyc.transfer.a.a.a("Lvm]q{njm", 9);
                new StringBuilder().append(com.miyc.transfer.a.a.a("kwvvmk|({mz~mz(B(", 8)).append(this.a).append(com.miyc.transfer.a.a.a("A", 7)).append(this.b);
                this.g = new Socket();
                this.g.connect(new InetSocketAddress(this.a, this.b), 10000);
                a(this.g);
                this.h = this.g.getInputStream();
                this.i = this.g.getOutputStream();
                f();
                d();
                com.miyc.transfer.a.a.a("HriYmwjfi", 5);
                new StringBuilder().append(com.miyc.transfer.a.a.a("htssjhy%xzhhjxx?%", 5)).append(this.a);
            } catch (IOException e) {
                com.miyc.transfer.a.a.a("FpgWkuhdg", 3);
                new StringBuilder().append(com.miyc.transfer.a.a.a("迡推头质=#", 3)).append(e.getMessage());
                g();
            }
        }
    }

    private void f() {
        com.miyc.transfer.a.a.a("DneUisfbe", 1);
        new StringBuilder().append(com.miyc.transfer.a.a.a("取逆让识俦恴?%", 5)).append(this.e);
        this.i.write(this.f);
        this.i.flush();
    }

    public void g() {
        a(this.g, com.miyc.transfer.a.a.a("Tpdlfu", 1));
        a(this.h, com.miyc.transfer.a.a.a("NsuzyXywjfr", 5));
        a(this.i, com.miyc.transfer.a.a.a("TzyuzyXywjfr", 5));
        this.g = null;
        this.h = null;
        this.i = null;
    }

    private void h() {
        com.miyc.transfer.a.a.a("HriYmwjfi", 5);
        new StringBuilder().append(com.miyc.transfer.a.a.a("细稒逇刁３扮術渌琍A'", 7)).append(this.a);
        l.b(this.a);
        a();
    }

    private void i() {
        if (this.p != null) {
            for (com.miyc.transfer.a.d dVar : this.p) {
                if (dVar != null) {
                    dVar.a();
                }
            }
            this.p = null;
        }
        if (this.q != null) {
            for (com.miyc.transfer.a.d dVar2 : this.q) {
                if (dVar2 != null) {
                    dVar2.a();
                }
            }
            this.q = null;
        }
    }

    public final void a() {
        com.miyc.transfer.a.a.a("GqhXlvieh", 4);
        new StringBuilder().append(com.miyc.transfer.a.a.a("dmptf;!", 1)).append(this.a);
        g();
        if (this.o != null) {
            this.o.interrupt();
            this.o = null;
        }
        i();
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        try {
            com.miyc.transfer.a.a.a("IsjZnxkgj", 6);
            new StringBuilder().append(com.miyc.transfer.a.a.a("吸励咆仭终稔C)qx|}F", 9)).append(this.a).append(com.miyc.transfer.a.a.a("3'rl\u0080D", 7)).append(this.e);
            b();
            this.j.set(true);
            e();
            this.o = new Thread(new f(this, (byte) 0), com.miyc.transfer.a.a.a("Ifbsucfbu.", 1) + System.currentTimeMillis());
            this.o.setDaemon(true);
            this.o.start();
            c();
        } catch (Exception e) {
            com.miyc.transfer.a.a.a("EofVjtgcf", 2);
            new StringBuilder().append(com.miyc.transfer.a.a.a("织稓弊幀逈刂B(", 8)).append(e.getMessage());
        } finally {
            h();
        }
    }
}

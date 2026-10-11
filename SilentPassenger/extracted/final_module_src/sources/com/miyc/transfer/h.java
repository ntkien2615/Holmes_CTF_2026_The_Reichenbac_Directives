package com.miyc.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* loaded from: classes.dex */
public final class h extends Thread {
    private static boolean h = false;
    Socket a;
    InputStream b;
    OutputStream c;
    String d;
    private final int e;
    private final byte[] f;
    private final boolean g;
    private int i;
    private int j;
    private com.miyc.transfer.a.d[] k;
    private j l;
    private Thread m;

    public h(byte[] bArr, String str, int i, int i2, int i3, boolean z, com.miyc.transfer.a.d[] dVarArr) {
        this.f = bArr;
        this.g = z;
        this.d = str;
        this.i = i2;
        this.j = i;
        this.e = i3;
        this.k = dVarArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(byte[] bArr) {
        try {
            if (h) {
                if (this.a != null) {
                    this.a.close();
                    this.b = null;
                    this.c = null;
                    Thread.sleep(3000L);
                }
                this.a = new Socket(this.d, 5555);
                this.a.setTcpNoDelay(true);
                this.b = this.a.getInputStream();
                this.c = this.a.getOutputStream();
                byte[] bArr2 = new byte[bArr.length + 1];
                bArr2[0] = g.h;
                System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
                this.c.write(bArr2);
                this.c.flush();
            }
        } catch (Exception e) {
            com.miyc.transfer.a.a.a("vxu~\u007f8", 6);
            new StringBuilder().append(com.miyc.transfer.a.a.a("迠控凼玲弄帺5", 2)).append(e.getLocalizedMessage());
        }
    }

    public final void a() {
        h = false;
        if (this.a != null) {
            try {
                this.a.close();
            } catch (IOException e) {
            }
            this.a = null;
            this.b = null;
            this.c = null;
        }
        if (this.l != null) {
            this.l.interrupt();
        }
        if (this.m != null) {
            this.m.interrupt();
        }
        interrupt();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        int read;
        int i = 0;
        try {
            new StringBuilder().append(com.miyc.transfer.a.a.a("秼亥逛達", 1)).append(this.d).append(com.miyc.transfer.a.a.a("/vhvvlrqLg@", 3)).append(this.i);
            u.a();
            byte[] a = u.a(this.i);
            h = true;
            this.l = new j(this);
            this.l.start();
            a(a);
            this.m = new i(this, a, this.e);
            this.m.start();
            while (h) {
                try {
                    read = this.b.read();
                } catch (Exception e) {
                    e = e;
                }
                if (read == -1) {
                    return;
                }
                new StringBuilder().append(com.miyc.transfer.a.a.a("^", 3)).append(this.i).append(com.miyc.transfer.a.a.a("cyuiqkz&z\u007fvk&", 6)).append(read);
                u.a();
                if (read == g.f || read == g.e) {
                    byte[] bArr = new byte[4];
                    this.b.read(bArr);
                    this.b.skip(4L);
                    byte[] bArr2 = new byte[8];
                    this.b.read(bArr2);
                    String a2 = u.a(u.b(bArr2));
                    int i2 = i + 1;
                    try {
                        this.k[i % this.k.length].a(a2, this.j, bArr, read);
                        this.l.a();
                        i = i2;
                    } catch (Exception e2) {
                        e = e2;
                        i = i2;
                        h = false;
                        new StringBuilder().append(com.miyc.transfer.a.a.a("y{x\u0081\u0082)", 9)).append(this.i);
                        new StringBuilder().append(com.miyc.transfer.a.a.a("迠控凼玲弄帺3", 2)).append(e.getLocalizedMessage());
                    }
                } else if (read == g.a) {
                    h = false;
                }
            }
        } catch (Exception e3) {
            new StringBuilder().append(com.miyc.transfer.a.a.a("qspyz!", 1)).append(this.i);
            new StringBuilder().append(com.miyc.transfer.a.a.a("迟掦击玱弃帹3", 1)).append(e3.getLocalizedMessage());
        } finally {
            a();
        }
    }
}

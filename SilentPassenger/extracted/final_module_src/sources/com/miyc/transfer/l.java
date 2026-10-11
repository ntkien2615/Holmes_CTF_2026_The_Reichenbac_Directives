package com.miyc.transfer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public final class l {
    private static final String a = com.miyc.transfer.a.a.a("45:131314", 3);
    private static final l b = new l();
    private volatile ServerSocket f;
    private final CopyOnWriteArrayList<e> c = new CopyOnWriteArrayList<>();
    private final ExecutorService d = Executors.newCachedThreadPool(new m(this));
    private final AtomicBoolean e = new AtomicBoolean(false);
    private volatile int g = 66;

    private l() {
    }

    private int a(InputStream inputStream) {
        boolean z;
        String d = d(inputStream);
        Iterator<e> it = this.c.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = false;
                break;
            }
            if (it.next().a.equals(d)) {
                z = true;
                break;
            }
        }
        return z ? 1 : 0;
    }

    private int a(InputStream inputStream, String str) {
        String d = d(inputStream);
        d(inputStream);
        int c = c(inputStream);
        int c2 = c(inputStream);
        int c3 = c(inputStream);
        com.miyc.transfer.a.a.a("gjjZnxkgj", 6);
        String.format(com.miyc.transfer.a.a.a("渀劥组稐?%mtxyB*x1%hriUtwyB*i1%ifyfUtwyB*i", 5), d, Integer.valueOf(c), Integer.valueOf(c2));
        Iterator<e> it = this.c.iterator();
        while (it.hasNext()) {
            if (it.next().a.equals(d)) {
                com.miyc.transfer.a.a.a("jmm]q{njm", 9);
                new StringBuilder().append(com.miyc.transfer.a.a.a("绀稌巳孙圩;!", 1)).append(d);
                return 1;
            }
        }
        try {
            e eVar = new e(d, c, c2, c3, str);
            eVar.start();
            this.c.add(eVar);
            com.miyc.transfer.a.a.a("fiiYmwjfi", 5);
            new StringBuilder().append(com.miyc.transfer.a.a.a("组稐渀劥戕劤?%", 5)).append(d);
            return 1;
        } catch (Exception e) {
            com.miyc.transfer.a.a.a("beeUisfbe", 1);
            new StringBuilder().append(com.miyc.transfer.a.a.a("吶劯细稒夸贬A'", 7)).append(e.getMessage());
            return 0;
        }
    }

    public static l a() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(long j) {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(l lVar) {
        lVar.e.set(false);
        if (lVar.f == null || lVar.f.isClosed()) {
            return;
        }
        try {
            lVar.f.close();
            com.miyc.transfer.a.a.a("gpswiWsgoix", 4);
            com.miyc.transfer.a.a.a("朏劣噪奙控孙巴兵闯", 2);
        } catch (IOException e) {
            com.miyc.transfer.a.a.a("lux|n\\xltn}", 9);
            new StringBuilder().append(com.miyc.transfer.a.a.a("兼闶奠掮孠夺贮C)", 9)).append(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(l lVar, Socket socket, String str) {
        int i = 1;
        try {
            InputStream inputStream = socket.getInputStream();
            try {
                OutputStream outputStream = socket.getOutputStream();
                try {
                    int read = inputStream.read();
                    if (read == -1) {
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                            return;
                        }
                        return;
                    }
                    switch (read) {
                        case 0:
                            int c = c(inputStream);
                            com.miyc.transfer.a.a.a("wfstjpoDifdl", 1);
                            String.format(com.miyc.transfer.a.a.a("攽刷牏朳棇柬A'pujvtpunD,k3'j|yylu{D,k", 7), Integer.valueOf(c), Integer.valueOf(lVar.g));
                            if (c > lVar.g && c != 999) {
                                com.miyc.transfer.a.a.a("zivwmsrGligo", 4);
                                com.miyc.transfer.a.a.a("棁浌刱斱牉札－凇夈兴问彔剎朎劢噩幷迂秼迟掦", 1);
                                lVar.e.set(false);
                                new Thread(new o(lVar), com.miyc.transfer.a.a.a("WivzivWlyxhs{r", 4)).start();
                                break;
                            } else {
                                i = 0;
                                break;
                            }
                            break;
                        case 1:
                            i = lVar.a(inputStream);
                            break;
                        case 2:
                            i = lVar.a(inputStream, str);
                            break;
                        case 3:
                            i = lVar.b(inputStream);
                            break;
                        default:
                            com.miyc.transfer.a.a.a("j}jhzyjHtrrfsi", 5);
                            new StringBuilder().append(com.miyc.transfer.a.a.a("朱矬咄仫粂垒A'", 7)).append(read);
                            i = 0;
                            break;
                    }
                    outputStream.write(u.a(i));
                    outputStream.flush();
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (Throwable th) {
                    if (outputStream != null) {
                        try {
                            outputStream.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            } finally {
            }
        } catch (Exception e) {
            com.miyc.transfer.a.a.a("lerhpiGpmirx", 4);
            new StringBuilder().append(com.miyc.transfer.a.a.a("夋琍课汉夸贬A'", 7)).append(e.getMessage());
        }
    }

    private static void a(List<q> list) {
        if (list.isEmpty()) {
            com.miyc.transfer.a.a.a("qmkvexmsr", 4);
            com.miyc.transfer.a.a.a("沨朐震覈迈稂皋迥掬", 7);
            return;
        }
        com.miyc.transfer.a.a.a("vrp{j}rxw", 9);
        String.format(com.miyc.transfer.a.a.a("弆姑过稁&+j&丰迤掫制斶牎朲", 6), Integer.valueOf(list.size()));
        int i = 0;
        int i2 = 0;
        for (q qVar : list) {
            if (a(qVar.a)) {
                i2++;
                com.miyc.transfer.a.a.a("njhsbujpo", 1);
                new StringBuilder().append(com.miyc.transfer.a.a.a("迟掦巳孙圩亏斱牉札;!", 1)).append(qVar.a);
            } else if (a(qVar.a, qVar.b, qVar.c, qVar.d, com.miyc.transfer.a.a.a("vvje", 1))) {
                i2++;
                com.miyc.transfer.a.a.a("okitcvkqp", 2);
                new StringBuilder().append(com.miyc.transfer.a.a.a("迄秾戓劢=#", 3)).append(qVar.a);
            } else {
                i++;
                com.miyc.transfer.a.a.a("somxgzout", 6);
                new StringBuilder().append(com.miyc.transfer.a.a.a("过稁夷贫@&", 6)).append(qVar.a);
            }
        }
        com.miyc.transfer.a.a.a("okitcvkqp", 2);
        String.format(com.miyc.transfer.a.a.a("迃秽宎戒<\"戒务?'f.\"夳货?'f", 2), Integer.valueOf(i2), Integer.valueOf(i));
    }

    public static boolean a(String str) {
        try {
            Socket g = g();
            if (g == null) {
                if (g == null) {
                    return false;
                }
                g.close();
                return false;
            }
            try {
                OutputStream outputStream = g.getOutputStream();
                byte[] bytes = str.getBytes();
                outputStream.write(1);
                outputStream.write(u.a(bytes.length));
                outputStream.write(bytes);
                outputStream.flush();
                boolean a2 = a(g);
                if (g != null) {
                    g.close();
                }
                return a2;
            } finally {
            }
        } catch (IOException e) {
            com.miyc.transfer.a.a.a("pi{Kul\\pzmil", 8);
            new StringBuilder().append(com.miyc.transfer.a.a.a("棈柭夹购B(", 8)).append(e.getMessage());
            return false;
        }
    }

    public static boolean a(String str, int i, int i2, int i3, String str2) {
        boolean z = false;
        try {
            Socket g = g();
            try {
                if (g == null) {
                    com.miyc.transfer.a.a.a("beeDneUisfbe", 1);
                    com.miyc.transfer.a.a.a("既泗迠控刲朏劣噪", 2);
                    if (g != null) {
                        g.close();
                    }
                } else {
                    OutputStream outputStream = g.getOutputStream();
                    byte[] bytes = str.getBytes();
                    byte[] bytes2 = str2.getBytes();
                    outputStream.write(2);
                    outputStream.write(u.a(bytes.length));
                    outputStream.write(bytes);
                    outputStream.write(u.a(bytes2.length));
                    outputStream.write(bytes2);
                    outputStream.write(u.a(i));
                    outputStream.write(u.a(i2));
                    outputStream.write(u.a(i3));
                    outputStream.flush();
                    boolean a2 = a(g);
                    if (g != null) {
                        g.close();
                    }
                    z = a2;
                }
            } finally {
            }
        } catch (Exception e) {
            com.miyc.transfer.a.a.a("dggFpgWkuhdg", 3);
            new StringBuilder().append(com.miyc.transfer.a.a.a("淽劢夳货<\"", 2)).append(e.getMessage());
        }
        return z;
    }

    private static boolean a(Socket socket) {
        return c(socket.getInputStream()) == 1;
    }

    public static int b() {
        Socket g = g();
        if (g == null) {
            if (g != null) {
                g.close();
            }
            return -1;
        }
        try {
            OutputStream outputStream = g.getOutputStream();
            outputStream.write(0);
            outputStream.write(u.a(66));
            outputStream.flush();
            int c = c(g.getInputStream());
            if (g == null) {
                return c;
            }
            g.close();
            return c;
        } catch (Throwable th) {
            if (g != null) {
                try {
                    g.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private int b(InputStream inputStream) {
        String d = d(inputStream);
        com.miyc.transfer.a.a.a("n\u0081r}]q{njm", 9);
        new StringBuilder().append(com.miyc.transfer.a.a.a("课汉逇刁细稒A'", 7)).append(d);
        Iterator<e> it = this.c.iterator();
        while (it.hasNext()) {
            e next = it.next();
            if (d.equals(next.a)) {
                try {
                    this.c.remove(next);
                    com.miyc.transfer.a.a.a("m\u0080q|\\pzmil", 8);
                    new StringBuilder().append(com.miyc.transfer.a.a.a("绉稕巼兽闷D*", 10)).append(d);
                    return 1;
                } catch (Exception e) {
                    com.miyc.transfer.a.a.a("i|mxXlvieh", 4);
                    new StringBuilder().append(com.miyc.transfer.a.a.a("兼闶终稔夺贮C)", 9)).append(e.getMessage());
                }
            }
        }
        return 1;
    }

    public static boolean b(String str) {
        try {
            Socket g = g();
            if (g == null) {
                if (g == null) {
                    return true;
                }
                g.close();
                return true;
            }
            try {
                OutputStream outputStream = g.getOutputStream();
                byte[] bytes = str.getBytes();
                outputStream.write(3);
                outputStream.write(u.a(bytes.length));
                outputStream.write(bytes);
                outputStream.flush();
                boolean a2 = a(g);
                if (g != null) {
                    g.close();
                }
                return a2;
            } finally {
            }
        } catch (IOException e) {
            com.miyc.transfer.a.a.a("i|mxXlvieh", 4);
            new StringBuilder().append(com.miyc.transfer.a.a.a("逅凿夶贪?%", 5)).append(e.getMessage());
            return true;
        }
    }

    private static int c(InputStream inputStream) {
        byte[] bArr = new byte[4];
        if (inputStream.read(bArr) != 4) {
            throw new IOException(com.miyc.transfer.a.a.a("课叙敷敳头质", 3));
        }
        return u.a(bArr);
    }

    private int d() {
        for (int i = 1; i <= 3; i++) {
            try {
                if (b() == 1) {
                    com.miyc.transfer.a.a.a("nsny", 5);
                    com.miyc.transfer.a.a.a("速矪事旬牍朱典闲１筎徊竴叨量敃333", 5);
                    a(2000L);
                }
            } catch (IOException e) {
            }
            try {
                this.f = new ServerSocket();
                this.f.setReuseAddress(true);
                this.f.bind(new InetSocketAddress(a, 10121));
                return p.a;
            } catch (IOException e2) {
                com.miyc.transfer.a.a.a("lqlw", 3);
                String.format(com.miyc.transfer.a.a.a("给客夹购(c-l7-leB(-{", 8), Integer.valueOf(i), 3, e2.getMessage());
                if (i < 3) {
                    a(1000L);
                }
            }
        }
        return p.b;
    }

    private static String d(InputStream inputStream) {
        int c = c(inputStream);
        if (c <= 0 || c > 1024) {
            throw new IOException(com.miyc.transfer.a.a.a("旨敐皌孟笮为閇庮B(", 8) + c);
        }
        byte[] bArr = new byte[c];
        if (inputStream.read(bArr) != c) {
            throw new IOException(com.miyc.transfer.a.a.a("调叞孟笮为夹购", 8));
        }
        return new String(bArr);
    }

    private void d(String str) {
        while (this.e.get()) {
            try {
                this.d.submit(new n(this, this.f.accept(), str));
            } catch (IOException e) {
                if (this.e.get()) {
                    com.miyc.transfer.a.a.a("|n{\u007fn{Uxxy", 9);
                    new StringBuilder().append(com.miyc.transfer.a.a.a("掦变迟掦弃帹;!", 1)).append(e.getMessage());
                }
            }
        }
        com.miyc.transfer.a.a.a("ugtxgtNqqr", 2);
        com.miyc.transfer.a.a.a("乄徳玸巻选刃", 9);
    }

    private List<q> e() {
        ArrayList arrayList = new ArrayList();
        Iterator<e> it = this.c.iterator();
        while (it.hasNext()) {
            e next = it.next();
            arrayList.add(new q(next.a, next.b, next.c, next.d));
        }
        return arrayList;
    }

    private void f() {
        com.miyc.transfer.a.a.a("tivuepxo", 1);
        com.miyc.transfer.a.a.a("养闵扈朑咅们织稓666", 8);
        Iterator<e> it = this.c.iterator();
        while (it.hasNext()) {
            try {
                it.next().a();
            } catch (Exception e) {
                com.miyc.transfer.a.a.a("xmzyit|s", 5);
                new StringBuilder().append(com.miyc.transfer.a.a.a("养闵织稓夹购B(", 8)).append(e.getMessage());
            }
        }
    }

    private static Socket g() {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(a, 10121), 5000);
            socket.setSoTimeout(5000);
            return socket;
        } catch (IOException e) {
            socket.close();
            return null;
        }
    }

    public final void c(String str) {
        r.a();
        if (this.e.get()) {
            com.miyc.transfer.a.a.a("wxevxWivziv", 4);
            new StringBuilder().append(com.miyc.transfer.a.a.a("朐劤噫巵圫迓衏丰／牋术=#", 3)).append(this.g);
            return;
        }
        com.miyc.transfer.a.a.a("uvctvUgtxgt", 2);
        new StringBuilder().append(com.miyc.transfer.a.a.a("尧诟吹劲朗劫噲６牒朶D*", 10)).append(this.g);
        int d = d();
        if (d == p.b) {
            com.miyc.transfer.a.a.a("yzgxzYkx|kx", 6);
            com.miyc.transfer.a.a.a("竰古袬卡甩－台胾朊具仗牉札圩近衍", 1);
            return;
        }
        if (d == p.c) {
            com.miyc.transfer.a.a.a("}~k|~]o|\u0080o|", 10);
            com.miyc.transfer.a.a.a("朐劤噫删姎匙头质", 3);
            return;
        }
        com.miyc.transfer.a.a.a("xyfwyXjw{jw", 5);
        com.miyc.transfer.a.a.a("朒劦噭吴劭戕劤１盖吱竴叨?%65676", 5);
        this.e.set(true);
        d(str);
        com.miyc.transfer.a.a.a("yn{zju}t", 6);
        String.format(com.miyc.transfer.a.a.a("朎劢噩兴问丮－牉札>&e－绀稌敱>&e", 1), Integer.valueOf(this.g), Integer.valueOf(this.c.size()));
        List<q> e = e();
        f();
        com.miyc.transfer.a.a.a("wlyxhs{r", 4);
        com.miyc.transfer.a.a.a("兽闷绉稕汪888", 10);
        this.d.shutdown();
        try {
            if (!this.d.awaitTermination(10L, TimeUnit.SECONDS)) {
                com.miyc.transfer.a.a.a("zo|{kv~u", 7);
                com.miyc.transfer.a.a.a("绁稍汢兵闯趇旸．弼券兵闯", 2);
                this.d.shutdownNow();
            }
        } catch (InterruptedException e2) {
            com.miyc.transfer.a.a.a("zo|{kv~u", 7);
            com.miyc.transfer.a.a.a("筎徊组稐汥典闲袰串斲", 5);
            this.d.shutdownNow();
            Thread.currentThread().interrupt();
        }
        com.miyc.transfer.a.a.a("wsq|k~syx", 10);
        com.miyc.transfer.a.a.a("筏律斶牎朲朓劧噮吵劮444", 6);
        a(3000L);
        a(e);
        this.c.clear();
        com.miyc.transfer.a.a.a("}r\u007f~ny\u0081x", 10);
        com.miyc.transfer.a.a.a("朖努噱兼闶宕戙", 9);
    }
}

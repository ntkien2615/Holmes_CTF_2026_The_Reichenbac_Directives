package com.miyc.transfer.a;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public final class f extends k {
    private static final byte[] k = a.a("KWWS2414#533#Frqqhfwlrq#Hvwdeolvkhg\u0010\r\u0010\r", 3).getBytes(StandardCharsets.US_ASCII);
    private int i;
    private boolean j;

    public f(SocketChannel socketChannel, c cVar) {
        super(socketChannel, cVar);
        this.i = j.a;
        this.j = false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void a(f fVar, InetAddress inetAddress, int i) {
        try {
            fVar.b = SocketChannel.open();
            fVar.b.configureBlocking(false);
            fVar.b.connect(new InetSocketAddress(inetAddress, i));
            fVar.i = j.c;
            fVar.b.register(fVar.c, 8, fVar);
        } catch (IOException e) {
            fVar.a();
        }
    }

    @Override // com.miyc.transfer.a.k
    public final void a() {
        this.i = j.f;
        super.a();
    }

    @Override // com.miyc.transfer.a.k
    public final void a(SocketChannel socketChannel) {
        String host;
        int port;
        b();
        switch (this.i - 1) {
            case 0:
                ByteBuffer a = this.e.a();
                if (socketChannel.read(a) == -1) {
                    a();
                    return;
                }
                a.flip();
                a.mark();
                byte[] bArr = new byte[a.remaining()];
                a.get(bArr);
                String str = new String(bArr, StandardCharsets.UTF_8);
                int indexOf = str.indexOf("\r\n\r\n");
                a.reset();
                String substring = indexOf != -1 ? str.substring(0, indexOf + 4) : null;
                if (substring == null) {
                    if (a.limit() != a.capacity()) {
                        a.compact();
                        return;
                    }
                    a.a("R^^Z", 10);
                    a.a("Ifbefs!upp!mbshf-!dmptjoh/", 1);
                    a();
                    return;
                }
                this.i = j.b;
                b(this.a, 1);
                try {
                    String[] split = substring.substring(0, substring.indexOf("\r\n")).split(a.a("f}5", 10));
                    String str2 = split[0];
                    String str3 = split[1];
                    if (str2.equalsIgnoreCase(a.a("IUTTKIZ", 6))) {
                        this.j = true;
                        String[] split2 = str3.split(a.a("B", 8));
                        host = split2[0];
                        port = split2.length > 1 ? Integer.parseInt(split2[1]) : 443;
                    } else {
                        this.j = false;
                        URL url = new URL(str3);
                        host = url.getHost();
                        port = url.getPort();
                        if (port == -1) {
                            port = url.getDefaultPort();
                        }
                    }
                    if (s.a(host)) {
                        a();
                        return;
                    }
                    if (this.j) {
                        int length = substring.getBytes(StandardCharsets.UTF_8).length;
                        ByteBuffer a2 = this.e.a();
                        if (length <= a2.limit()) {
                            a2.position(length);
                        }
                        if (!a2.hasRemaining()) {
                            a2.clear();
                            this.e.d = false;
                            g.submit(new g(this, host, port));
                            return;
                        }
                    }
                    this.e.d = true;
                    g.submit(new g(this, host, port));
                    return;
                } catch (Exception e) {
                    a();
                    return;
                }
            case 1:
            case 2:
                b(this.a, 1);
                return;
            case 3:
            default:
                a();
                return;
            case 4:
                super.a(socketChannel);
                return;
        }
    }

    @Override // com.miyc.transfer.a.k
    public final void b(SocketChannel socketChannel) {
        this.b = socketChannel;
        if (this.j) {
            this.i = j.e;
            try {
                ByteBuffer wrap = ByteBuffer.wrap(k);
                while (wrap.hasRemaining()) {
                    this.a.write(wrap);
                }
            } catch (IOException e) {
                a();
            }
        } else {
            this.i = j.e;
            try {
                a(this.b, 4);
                c(this.b);
            } catch (IOException e2) {
                a();
            }
        }
        a(this.a, 1);
        a(this.b, 1);
    }
}

package com.miyc.transfer.a;

import android.os.Build;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
final class m extends k {
    private static final ByteBuffer n = ByteBuffer.allocateDirect(1024);
    public r i;
    private int j;
    private byte k;
    private String l;
    private int m;

    public m(SocketChannel socketChannel, c cVar) {
        super(socketChannel, cVar);
        this.j = q.d;
    }

    private void a(byte b) {
        ByteBuffer allocate = ByteBuffer.allocate(22);
        allocate.put((byte) 5);
        allocate.put(b);
        allocate.put((byte) 0);
        InetSocketAddress inetSocketAddress = (InetSocketAddress) this.b.socket().getLocalSocketAddress();
        if (inetSocketAddress.getAddress() instanceof Inet4Address) {
            allocate.put((byte) 1);
            allocate.put(inetSocketAddress.getAddress().getAddress());
        } else {
            allocate.put((byte) 4);
            allocate.put(inetSocketAddress.getAddress().getAddress());
        }
        allocate.putShort((short) inetSocketAddress.getPort());
        allocate.flip();
        this.a.write(allocate);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ byte b(m mVar) {
        mVar.k = (byte) 4;
        return (byte) 4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.k == 1) {
            e();
            return;
        }
        if (this.k == 2) {
            f();
        } else if (this.k == 3) {
            g();
        } else {
            a((byte) 1);
            a();
        }
    }

    private void e() {
        this.b = SocketChannel.open();
        this.b.configureBlocking(false);
        if (Build.VERSION.SDK_INT >= 24) {
            this.b.setOption((SocketOption<SocketOption>) StandardSocketOptions.TCP_NODELAY, (SocketOption) Boolean.TRUE);
            this.b.setOption((SocketOption<SocketOption>) StandardSocketOptions.SO_RCVBUF, (SocketOption) 1048576);
            this.b.setOption((SocketOption<SocketOption>) StandardSocketOptions.SO_SNDBUF, (SocketOption) 1048576);
        }
        if (!this.b.connect(new InetSocketAddress(this.l, this.m))) {
            this.b.register(this.c, 8, this);
        } else {
            b(this.b);
            this.b.register(this.c, 1, this);
        }
    }

    private void f() {
        a((byte) 1);
        a();
    }

    private void g() {
        h();
        i();
    }

    private void h() {
        DatagramChannel open = DatagramChannel.open();
        open.socket().setBroadcast(true);
        open.socket().bind(new InetSocketAddress(0));
        open.configureBlocking(false);
        this.i = new r(open, new InetSocketAddress(((InetSocketAddress) this.a.socket().getRemoteSocketAddress()).getAddress(), com.miyc.transfer.g.m));
        open.register(this.c, 1, this);
    }

    private void i() {
        ByteBuffer allocate = ByteBuffer.allocate(256);
        allocate.put((byte) 5);
        allocate.put((byte) 0);
        allocate.put((byte) 0);
        byte[] address = this.i.a().getAddress().getAddress();
        if (address.length == 4) {
            allocate.put((byte) 1);
            allocate.put(address);
        } else {
            if (address.length != 16) {
                throw new IOException(a.a("]v{}xxwz|ml(illzm{{(|\u0081xm", 8));
            }
            allocate.put((byte) 4);
            allocate.put(address);
        }
        j();
        int port = this.i.a().getPort();
        allocate.put((byte) ((port >> 8) & 255));
        allocate.put((byte) (port & 255));
        allocate.flip();
        this.a.write(allocate);
    }

    private void j() {
        ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.put((byte) 1);
        allocate.put((byte) 1);
        allocate.put(t.a(this.m));
        allocate.put((byte) (this.m & 255));
        try {
            allocate.flip();
            this.i.a(allocate, this.i.a());
        } catch (IOException e) {
        }
    }

    @Override // com.miyc.transfer.a.k
    public final void a() {
        this.j = q.f;
        super.a();
        if (this.i != null) {
            this.i.b();
            this.i = null;
        }
    }

    @Override // com.miyc.transfer.a.k
    public final void a(SocketChannel socketChannel) {
        switch (this.j - 1) {
            case 3:
                n.clear();
                if (socketChannel.read(n) < 0) {
                    a();
                    return;
                }
                n.flip();
                if (n.remaining() >= 4) {
                    if (n.get() != 5) {
                        a((byte) 1);
                        a();
                        return;
                    }
                    this.k = n.get();
                    n.get();
                    byte b = n.get();
                    switch (b) {
                        case 1:
                            if (n.remaining() >= 6) {
                                byte[] bArr = new byte[4];
                                n.get(bArr);
                                this.l = (bArr[0] & 255) + a.a("6", 8) + (bArr[1] & 255) + a.a("/", 1) + (bArr[2] & 255) + a.a("2", 4) + (bArr[3] & 255);
                                break;
                            } else {
                                return;
                            }
                        case 2:
                        default:
                            a((byte) 1);
                            a();
                            return;
                        case 3:
                            if (n.remaining() > 0) {
                                int i = n.get();
                                if (n.remaining() >= i + 2) {
                                    byte[] bArr2 = new byte[i];
                                    n.get(bArr2);
                                    this.l = new String(bArr2, StandardCharsets.UTF_8);
                                    break;
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        case 4:
                            if (n.remaining() >= 18) {
                                byte[] bArr3 = new byte[16];
                                n.get(bArr3);
                                StringBuilder sb = new StringBuilder();
                                for (int i2 = 0; i2 < 16; i2 += 2) {
                                    if (i2 > 0) {
                                        sb.append(a.a("C", 9));
                                    }
                                    sb.append(String.format(a.a("'24z'24z", 2), Integer.valueOf(bArr3[i2] & 255), Integer.valueOf(bArr3[i2 + 1] & 255)));
                                }
                                this.l = sb.toString();
                                break;
                            } else {
                                return;
                            }
                    }
                    this.m = ((n.get() & 255) << 8) | (n.get() & 255);
                    if (s.a(this.l)) {
                        a.a("mzzwz", 8);
                        new StringBuilder().append(a.a("gsrrigx$xs$psegep$", 4)).append(this.l);
                        this.k = (byte) 4;
                    }
                    if (b == 3) {
                        g.submit(new n(this));
                        return;
                    } else {
                        d();
                        return;
                    }
                }
                return;
            case 4:
                super.a(socketChannel);
                return;
            default:
                a();
                return;
        }
    }

    @Override // com.miyc.transfer.a.k
    public final void b(SocketChannel socketChannel) {
        this.b = socketChannel;
        this.j = q.e;
        try {
            a((byte) 0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

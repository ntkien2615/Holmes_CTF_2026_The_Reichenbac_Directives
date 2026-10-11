package com.miyc.transfer.a;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Arrays;

/* loaded from: classes.dex */
public final class r {
    private DatagramChannel a;
    private InetSocketAddress b;

    public r(DatagramChannel datagramChannel, InetSocketAddress inetSocketAddress) {
        this.a = datagramChannel;
        this.b = inetSocketAddress;
    }

    public final InetSocketAddress a() {
        return this.b;
    }

    public final void a(ByteBuffer byteBuffer, SocketAddress socketAddress) {
        this.a.send(byteBuffer, socketAddress);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:17:0x0056. Please report as an issue. */
    public final void a(DatagramChannel datagramChannel) {
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        try {
            SocketAddress receive = datagramChannel.receive(allocate);
            allocate.flip();
            byte[] bArr = new byte[allocate.remaining()];
            allocate.get(bArr);
            if (bArr[0] != 0 || bArr[1] != 0) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) receive;
                byte[] address = inetSocketAddress.getAddress().getAddress();
                int length = address.length;
                int port = inetSocketAddress.getPort();
                byte[] bArr2 = new byte[bArr.length + 6 + length];
                bArr2[1] = 0;
                bArr2[0] = 0;
                bArr2[2] = 0;
                bArr2[3] = (byte) (length == 4 ? 1 : 4);
                System.arraycopy(address, 0, bArr2, 4, length);
                bArr2[length + 4] = t.a(port);
                bArr2[length + 5] = (byte) (port & 255);
                System.arraycopy(bArr, 0, bArr2, length + 6, bArr.length);
                a(ByteBuffer.wrap(bArr2), this.b);
            } else {
                if (bArr[0] != 0 || bArr[1] != bArr[0]) {
                    throw new IOException(a.a("YUIQY&|kxyout&kxxux", 6));
                }
                if (bArr[2] != 0) {
                    throw new IOException(a.a("ZVJRZ'mylntlu{'pz'uv{'z|wwvy{lk", 7));
                }
                InetAddress inetAddress = null;
                int i = -1;
                byte[] bArr3 = null;
                switch (bArr[3]) {
                    case 1:
                        try {
                            inetAddress = InetAddress.getByAddress(Arrays.copyOfRange(bArr, 4, 8));
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                        i = t.a(bArr[8], bArr[9]);
                        bArr3 = Arrays.copyOfRange(bArr, 10, bArr.length);
                        datagramChannel.send(ByteBuffer.wrap(bArr3), new InetSocketAddress(inetAddress, i));
                        break;
                    case 2:
                    default:
                        datagramChannel.send(ByteBuffer.wrap(bArr3), new InetSocketAddress(inetAddress, i));
                        break;
                    case 3:
                        byte b = bArr[4];
                        try {
                            inetAddress = InetAddress.getByName(new String(bArr, 5, (int) b));
                        } catch (UnknownHostException e2) {
                            e2.printStackTrace();
                        }
                        i = t.a(bArr[b + 5], bArr[b + 6]);
                        bArr3 = Arrays.copyOfRange(bArr, b + 7, bArr.length);
                        datagramChannel.send(ByteBuffer.wrap(bArr3), new InetSocketAddress(inetAddress, i));
                        break;
                    case 4:
                        try {
                            inetAddress = InetAddress.getByAddress(Arrays.copyOfRange(bArr, 4, 20));
                            i = t.a(bArr[20], bArr[21]);
                            bArr3 = Arrays.copyOfRange(bArr, 22, bArr.length);
                            datagramChannel.send(ByteBuffer.wrap(bArr3), new InetSocketAddress(inetAddress, i));
                            break;
                        } catch (UnknownHostException e3) {
                            throw new IOException(a.a("\\uruv~u'ovz{", 7));
                        }
                }
            }
            allocate.clear();
        } catch (IOException e4) {
        }
    }

    public final void b() {
        try {
            this.a.close();
            this.b = null;
            this.a = null;
        } catch (IOException e) {
        }
        this.b = null;
        this.a = null;
    }
}

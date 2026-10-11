package org.eclipse.paho.client.mqttv3.internal.websocket;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.security.SecureRandom;

/* loaded from: classes.dex */
public class WebSocketFrame {
    public static final int frameLengthOverhead = 6;
    private boolean closeFlag;
    private boolean fin;
    private byte opcode;
    private byte[] payload;

    public byte getOpcode() {
        return this.opcode;
    }

    public boolean isFin() {
        return this.fin;
    }

    public byte[] getPayload() {
        return this.payload;
    }

    public boolean isCloseFlag() {
        return this.closeFlag;
    }

    public WebSocketFrame(byte b, boolean z, byte[] bArr) {
        this.closeFlag = false;
        this.opcode = b;
        this.fin = z;
        this.payload = bArr;
    }

    public WebSocketFrame(byte[] bArr) {
        this.closeFlag = false;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        setFinAndOpCode(wrap.get());
        byte b = wrap.get();
        boolean z = (b & 128) != 0;
        int i = (byte) (b & Byte.MAX_VALUE);
        int i2 = i != 127 ? i != 126 ? 0 : 2 : 8;
        while (true) {
            i2--;
            if (i2 <= 0) {
                break;
            } else {
                i |= (wrap.get() & 255) << (i2 * 8);
            }
        }
        byte[] bArr2 = null;
        if (z) {
            bArr2 = new byte[4];
            wrap.get(bArr2, 0, 4);
        }
        this.payload = new byte[i];
        wrap.get(this.payload, 0, i);
        if (!z) {
            return;
        }
        for (int i3 = 0; i3 < this.payload.length; i3++) {
            this.payload[i3] = (byte) (r0[i3] ^ bArr2[i3 % 4]);
        }
    }

    private void setFinAndOpCode(byte b) {
        this.fin = (b & 128) != 0;
        this.opcode = (byte) (b & 15);
    }

    public WebSocketFrame(InputStream inputStream) {
        int i = 2;
        this.closeFlag = false;
        setFinAndOpCode((byte) inputStream.read());
        if (this.opcode == 2) {
            byte read = (byte) inputStream.read();
            boolean z = (read & 128) != 0;
            int i2 = (byte) (read & Byte.MAX_VALUE);
            if (i2 == 127) {
                i = 8;
            } else if (i2 != 126) {
                i = 0;
            }
            i2 = i > 0 ? 0 : i2;
            while (true) {
                i--;
                if (i < 0) {
                    break;
                } else {
                    i2 |= (((byte) inputStream.read()) & 255) << (i * 8);
                }
            }
            byte[] bArr = null;
            if (z) {
                bArr = new byte[4];
                inputStream.read(bArr, 0, 4);
            }
            this.payload = new byte[i2];
            int i3 = i2;
            int i4 = 0;
            while (i4 != i2) {
                int read2 = inputStream.read(this.payload, i4, i3);
                i4 += read2;
                i3 -= read2;
            }
            if (!z) {
                return;
            }
            for (int i5 = 0; i5 < this.payload.length; i5++) {
                this.payload[i5] = (byte) (r0[i5] ^ bArr[i5 % 4]);
            }
            return;
        }
        if (this.opcode == 8) {
            this.closeFlag = true;
            return;
        }
        throw new IOException("Invalid Frame: Opcode: " + ((int) this.opcode));
    }

    public byte[] encodeFrame() {
        int length = this.payload.length + 6;
        if (this.payload.length > 65535) {
            length += 8;
        } else if (this.payload.length >= 126) {
            length += 2;
        }
        ByteBuffer allocate = ByteBuffer.allocate(length);
        appendFinAndOpCode(allocate, this.opcode, this.fin);
        byte[] generateMaskingKey = generateMaskingKey();
        appendLengthAndMask(allocate, this.payload.length, generateMaskingKey);
        for (int i = 0; i < this.payload.length; i++) {
            byte[] bArr = this.payload;
            byte b = (byte) (bArr[i] ^ generateMaskingKey[i % 4]);
            bArr[i] = b;
            allocate.put(b);
        }
        allocate.flip();
        return allocate.array();
    }

    public static void appendLengthAndMask(ByteBuffer byteBuffer, int i, byte[] bArr) {
        if (bArr != null) {
            appendLength(byteBuffer, i, true);
            byteBuffer.put(bArr);
        } else {
            appendLength(byteBuffer, i, false);
        }
    }

    private static void appendLength(ByteBuffer byteBuffer, int i, boolean z) {
        if (i < 0) {
            throw new IllegalArgumentException("Length cannot be negative");
        }
        int i2 = !z ? 0 : -128;
        if (i > 65535) {
            byteBuffer.put((byte) (i2 | 127));
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) 0);
            byteBuffer.put((byte) ((i >> 24) & 255));
            byteBuffer.put((byte) ((i >> 16) & 255));
            byteBuffer.put((byte) ((i >> 8) & 255));
            byteBuffer.put((byte) (i & 255));
            return;
        }
        if (i >= 126) {
            byteBuffer.put((byte) (i2 | 126));
            byteBuffer.put((byte) (i >> 8));
            byteBuffer.put((byte) (i & 255));
            return;
        }
        byteBuffer.put((byte) (i2 | i));
    }

    public static void appendFinAndOpCode(ByteBuffer byteBuffer, byte b, boolean z) {
        byteBuffer.put((byte) ((z ? (byte) 128 : (byte) 0) | (b & 15)));
    }

    public static byte[] generateMaskingKey() {
        SecureRandom secureRandom = new SecureRandom();
        return new byte[]{(byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255), (byte) secureRandom.nextInt(255)};
    }
}

package org.eclipse.paho.client.mqttv3.internal.security;

import org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage;

/* loaded from: classes.dex */
public class SimpleBase64Encoder {
    private static final String PWDCHARS_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final char[] PWDCHARS_ARRAY = PWDCHARS_STRING.toCharArray();

    public static String encode(byte[] bArr) {
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer(((length + 2) / 3) * 4);
        int i = 0;
        while (length >= 3) {
            stringBuffer.append(to64(((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8) | (bArr[i + 2] & 255), 4));
            i += 3;
            length -= 3;
        }
        if (length == 2) {
            stringBuffer.append(to64(((bArr[i] & 255) << 8) | (bArr[i + 1] & 255), 3));
        }
        if (length == 1) {
            stringBuffer.append(to64(bArr[i] & 255, 2));
        }
        return stringBuffer.toString();
    }

    public static byte[] decode(String str) {
        byte[] bytes = str.getBytes();
        int length = bytes.length;
        byte[] bArr = new byte[(length * 3) / 4];
        int i = length;
        int i2 = 0;
        int i3 = 0;
        while (i >= 4) {
            int i4 = i - 4;
            int i5 = i2 + 4;
            long from64 = from64(bytes, i2, 4);
            for (int i6 = 2; i6 >= 0; i6--) {
                bArr[i3 + i6] = (byte) (255 & from64);
                from64 >>= 8;
            }
            i3 += 3;
            i = i4;
            i2 = i5;
        }
        if (i == 3) {
            long from642 = from64(bytes, i2, 3);
            for (int i7 = 1; i7 >= 0; i7--) {
                bArr[i3 + i7] = (byte) (255 & from642);
                from642 >>= 8;
            }
        }
        if (i == 2) {
            bArr[i3] = (byte) (from64(bytes, i2, 2) & 255);
        }
        return bArr;
    }

    private static final String to64(long j, int i) {
        StringBuffer stringBuffer = new StringBuffer(i);
        while (i > 0) {
            i--;
            stringBuffer.append(PWDCHARS_ARRAY[(int) (63 & j)]);
            j >>= 6;
        }
        return stringBuffer.toString();
    }

    private static final long from64(byte[] bArr, int i, int i2) {
        int i3 = 0;
        long j = 0;
        while (i2 > 0) {
            i2--;
            int i4 = i + 1;
            byte b = bArr[i];
            long j2 = b != 47 ? 0L : 1L;
            if (b >= 48 && b <= 57) {
                j2 = (b + 2) - 48;
            }
            if (b >= 65 && b <= 90) {
                j2 = (b + MqttWireMessage.MESSAGE_TYPE_PINGREQ) - 65;
            }
            if (b >= 97 && b <= 122) {
                j2 = (b + 38) - 97;
            }
            j += j2 << i3;
            i3 += 6;
            i = i4;
        }
        return j;
    }
}

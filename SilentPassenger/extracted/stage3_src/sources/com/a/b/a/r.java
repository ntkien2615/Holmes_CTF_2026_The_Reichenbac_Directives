package com.a.b.a;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* loaded from: classes.dex */
public final class r {
    private static r a;

    private r() {
    }

    public static r a() {
        if (a == null) {
            a = new r();
        }
        return a;
    }

    public static void a(String str, String str2, String str3) {
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        FileOutputStream fileOutputStream2 = null;
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str3);
            inputStream = httpURLConnection.getInputStream();
            if (file2.exists()) {
                file2.delete();
            }
            file2.createNewFile();
            fileOutputStream = new FileOutputStream(file2);
        } catch (Exception e) {
            fileOutputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    fileOutputStream.flush();
                    inputStream.close();
                    try {
                        fileOutputStream.close();
                        return;
                    } catch (Exception e2) {
                        return;
                    }
                }
                fileOutputStream.write(bArr, 0, read);
            }
        } catch (Exception e3) {
            try {
                fileOutputStream.close();
            } catch (Exception e4) {
            }
        } catch (Throwable th2) {
            fileOutputStream2 = fileOutputStream;
            th = th2;
            try {
                fileOutputStream2.close();
            } catch (Exception e5) {
            }
            throw th;
        }
    }
}

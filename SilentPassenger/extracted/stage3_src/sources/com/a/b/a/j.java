package com.a.b.a;

import android.content.Context;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/* loaded from: classes.dex */
final class j implements Runnable {
    private Socket a;
    private Context b;

    public j(Socket socket, Context context) {
        this.a = socket;
        this.b = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        String str = null;
        aq.a("fruh", 3);
        aq.a("wjhjn{j%rxl", 5);
        try {
            InputStream inputStream = this.a.getInputStream();
            OutputStream outputStream = this.a.getOutputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            if (dataInputStream.readInt() == 25746) {
                byte readByte = dataInputStream.readByte();
                int readInt = dataInputStream.readInt();
                if (readInt > 1024) {
                    inputStream.close();
                    outputStream.close();
                    try {
                        this.a.close();
                        return;
                    } catch (IOException e) {
                        return;
                    }
                }
                byte[] bArr = new byte[readInt];
                dataInputStream.read(bArr);
                String str2 = new String(bArr);
                if (readByte == 101) {
                    String[] split = str2.split(aq.a("-", 1));
                    if (split.length == 2) {
                        str = this.b.getSharedPreferences(split[0], 0).getString(split[1], null);
                    }
                } else if (readByte == 102 && ba.a) {
                    str = aq.a("vtwg", 2);
                }
                if (str != null) {
                    DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
                    dataOutputStream.writeInt(25746);
                    byte[] bytes = str.getBytes();
                    dataOutputStream.writeInt(bytes.length);
                    dataOutputStream.write(bytes);
                    dataOutputStream.flush();
                }
            }
            inputStream.close();
            outputStream.close();
            try {
                this.a.close();
            } catch (IOException e2) {
            }
        } catch (IOException e3) {
            try {
                this.a.close();
            } catch (IOException e4) {
            }
        } catch (Throwable th) {
            try {
                this.a.close();
            } catch (IOException e5) {
            }
            throw th;
        }
    }
}

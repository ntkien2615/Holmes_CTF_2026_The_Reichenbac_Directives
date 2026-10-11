package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0042p;
import com.tw.core.utils.C0052z;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

/* renamed from: com.tw.core.b.f, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0006f extends com.tw.core.g.a implements com.tw.core.f.c {
    private Context aT;
    private String aU;
    private String aV;

    public C0006f(Context context) {
        this.aT = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("GpsPermissionManager", "emqMessageChanged %s", bVar);
        C0042p.id().execute(new F(this, bVar));
    }

    public String co() {
        if (TextUtils.isEmpty(this.aV)) {
            this.aV = "/data/tw/location.wl";
        }
        return this.aV;
    }

    public String cn() {
        if (TextUtils.isEmpty(this.aU)) {
            this.aU = "/system/etc/location.wl";
        }
        return this.aU;
    }

    public void cr(String str, String str2) {
        FileOutputStream fileOutputStream = new FileOutputStream(C0030d.gI(str));
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(str2);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        fileOutputStream.close();
        C0052z.iT("chmod 777 " + str);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0051 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean cp() {
        /*
            r5 = this;
            r3 = 0
            r2 = 0
            java.io.File r0 = new java.io.File
            java.lang.String r1 = r5.cn()
            r0.<init>(r1)
            boolean r1 = r0.exists()
            if (r1 == 0) goto L46
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4d
            java.io.FileReader r4 = new java.io.FileReader     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4d
            java.lang.String r0 = r0.getPath()     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4d
            r4.<init>(r0)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4d
            r1.<init>(r4)     // Catch: java.lang.Exception -> L3c java.lang.Throwable -> L4d
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            java.lang.String r2 = " "
            java.lang.String[] r0 = r0.split(r2)     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            int r0 = r0.length     // Catch: java.lang.Throwable -> L5a java.lang.Exception -> L5c
            r2 = 3
            if (r0 != r2) goto L35
            r0 = 1
        L2f:
            if (r1 == 0) goto L34
            r1.close()     // Catch: java.io.IOException -> L37
        L34:
            return r0
        L35:
            r0 = r3
            goto L2f
        L37:
            r1 = move-exception
            r1.printStackTrace()
            goto L34
        L3c:
            r0 = move-exception
            r1 = r2
        L3e:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L5a
            if (r1 == 0) goto L46
            r1.close()     // Catch: java.io.IOException -> L48
        L46:
            r0 = r3
            goto L34
        L48:
            r0 = move-exception
            r0.printStackTrace()
            goto L46
        L4d:
            r0 = move-exception
            r1 = r2
        L4f:
            if (r1 == 0) goto L54
            r1.close()     // Catch: java.io.IOException -> L55
        L54:
            throw r0
        L55:
            r1 = move-exception
            r1.printStackTrace()
            goto L54
        L5a:
            r0 = move-exception
            goto L4f
        L5c:
            r0 = move-exception
            goto L3e
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.b.C0006f.cp():boolean");
    }

    public boolean cq() {
        return new File(cn()).exists();
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0311";
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return "";
    }
}

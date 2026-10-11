package com.tw.core.b;

import android.text.TextUtils;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0038l;
import java.util.List;

/* loaded from: classes.dex */
public abstract class B {
    private final Object cw = new Object();

    public abstract Object bE(String str);

    public abstract String bK();

    public abstract boolean bT(Object obj);

    public abstract String bU(Object obj);

    public void ej(List list, String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        synchronized (this.cw) {
            ep(str);
            if (cF()) {
                eq();
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (list != null && list.size() > 0) {
                if (C0038l.ez) {
                    C0038l.hE("BaseDataManager", "saveData :" + list);
                }
                int size = list.size();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < size; i++) {
                    sb.append(bU(list.get(i)));
                    if (i < size - 1) {
                        sb.append("\n");
                    }
                }
                C0030d.gV(str, sb.toString(), true);
            }
            if (C0038l.ez) {
                C0038l.hE("BaseDataManager", "saveData handle time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
        }
    }

    public void eo(List list) {
        ej(list, bK());
    }

    protected boolean cF() {
        return false;
    }

    protected void ep(String str) {
        if (!TextUtils.isEmpty(str)) {
            long gM = C0030d.gM(str);
            C0038l.hG("BaseDataManager", "deleteBigData fileSize = " + gM);
            if (gM > cD()) {
                C0030d.gL(str);
            }
        }
    }

    protected long cD() {
        return 26214400L;
    }

    /* JADX WARN: Removed duplicated region for block: B:63:0x00b6  */
    /* JADX WARN: Removed duplicated region for block: B:65:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void er(java.lang.String r12) {
        /*
            Method dump skipped, instructions count: 300
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.b.B.er(java.lang.String):void");
    }

    protected void eq() {
        er(bK());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x007a A[Catch: all -> 0x00a7, TRY_LEAVE, TryCatch #3 {, blocks: (B:8:0x000b, B:10:0x0011, B:30:0x006d, B:31:0x0073, B:33:0x007a, B:50:0x00ad, B:51:0x00b3, B:46:0x00a0), top: B:7:0x000b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List ek(int r12, java.lang.String r13) {
        /*
            r11 = this;
            r2 = 0
            boolean r0 = android.text.TextUtils.isEmpty(r13)
            if (r0 == 0) goto L8
            return r2
        L8:
            java.lang.Object r4 = r11.cw
            monitor-enter(r4)
            boolean r0 = com.tw.core.utils.C0030d.gQ(r13)     // Catch: java.lang.Throwable -> La7
            if (r0 == 0) goto Lb4
            long r6 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> La7
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La7
            r5.<init>()     // Catch: java.lang.Throwable -> La7
            java.util.ArrayList r8 = new java.util.ArrayList     // Catch: java.lang.Throwable -> La7
            r8.<init>()     // Catch: java.lang.Throwable -> La7
            java.io.FileReader r3 = new java.io.FileReader     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Laa
            java.io.File r0 = new java.io.File     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Laa
            r0.<init>(r13)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Laa
            r3.<init>(r0)     // Catch: java.lang.Exception -> L9b java.lang.Throwable -> Laa
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbe
            r1.<init>(r3)     // Catch: java.lang.Throwable -> Lb6 java.lang.Exception -> Lbe
            r0 = 0
        L2f:
            java.lang.String r2 = r1.readLine()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            if (r2 == 0) goto L53
            java.lang.Object r9 = r11.bE(r2)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            if (r9 == 0) goto L51
            boolean r10 = r11.bT(r9)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            if (r10 == 0) goto L51
            boolean r10 = r5.contains(r2)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            r10 = r10 ^ 1
            if (r10 == 0) goto L51
            r5.add(r2)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            r8.add(r9)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            int r0 = r0 + 1
        L51:
            if (r0 < r12) goto L2f
        L53:
            java.lang.String r2 = "BaseDataManager"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            r9.<init>()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            java.lang.String r10 = "readData count="
            java.lang.StringBuilder r9 = r9.append(r10)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            java.lang.StringBuilder r0 = r9.append(r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            com.tw.core.utils.C0038l.hE(r2, r0)     // Catch: java.lang.Throwable -> Lb9 java.lang.Exception -> Lc2
            com.tw.core.utils.H.jx(r3)     // Catch: java.lang.Throwable -> La7
            com.tw.core.utils.H.jx(r1)     // Catch: java.lang.Throwable -> La7
        L73:
            r5.clear()     // Catch: java.lang.Throwable -> La7
            boolean r0 = com.tw.core.utils.C0038l.ez     // Catch: java.lang.Throwable -> La7
            if (r0 == 0) goto L99
            java.lang.String r0 = "BaseDataManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
            r1.<init>()     // Catch: java.lang.Throwable -> La7
            java.lang.String r2 = "readData handle time="
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> La7
            long r2 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> La7
            long r2 = r2 - r6
            java.lang.StringBuilder r1 = r1.append(r2)     // Catch: java.lang.Throwable -> La7
            java.lang.String r1 = r1.toString()     // Catch: java.lang.Throwable -> La7
            com.tw.core.utils.C0038l.hE(r0, r1)     // Catch: java.lang.Throwable -> La7
        L99:
            monitor-exit(r4)
            return r8
        L9b:
            r0 = move-exception
            r1 = r2
        L9d:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lbb
            com.tw.core.utils.H.jx(r2)     // Catch: java.lang.Throwable -> La7
            com.tw.core.utils.H.jx(r1)     // Catch: java.lang.Throwable -> La7
            goto L73
        La7:
            r0 = move-exception
            monitor-exit(r4)
            throw r0
        Laa:
            r0 = move-exception
            r1 = r2
            r3 = r2
        Lad:
            com.tw.core.utils.H.jx(r3)     // Catch: java.lang.Throwable -> La7
            com.tw.core.utils.H.jx(r1)     // Catch: java.lang.Throwable -> La7
            throw r0     // Catch: java.lang.Throwable -> La7
        Lb4:
            monitor-exit(r4)
            return r2
        Lb6:
            r0 = move-exception
            r1 = r2
            goto Lad
        Lb9:
            r0 = move-exception
            goto Lad
        Lbb:
            r0 = move-exception
            r3 = r2
            goto Lad
        Lbe:
            r0 = move-exception
            r1 = r2
            r2 = r3
            goto L9d
        Lc2:
            r0 = move-exception
            r2 = r3
            goto L9d
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.b.B.ek(int, java.lang.String):java.util.List");
    }

    public List em(int i) {
        return ek(i, bK());
    }

    /* JADX WARN: Removed duplicated region for block: B:82:0x012d A[Catch: all -> 0x0066, TRY_LEAVE, TryCatch #4 {, blocks: (B:8:0x000c, B:10:0x0010, B:12:0x002c, B:14:0x0032, B:16:0x003c, B:17:0x0045, B:19:0x004b, B:21:0x0069, B:53:0x0109, B:54:0x010f, B:60:0x0172, B:64:0x017d, B:65:0x0183, B:80:0x0129, B:82:0x012d), top: B:7:0x000c }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void el(java.util.List r14, java.lang.String r15) {
        /*
            Method dump skipped, instructions count: 406
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.b.B.el(java.util.List, java.lang.String):void");
    }

    public void en(List list) {
        el(list, bK());
    }
}

package com.tw.core.c;

import android.net.TrafficStats;

/* loaded from: classes.dex */
public class a {
    private int ds;
    private long dt = 0;

    public a(int i) {
        this.ds = 0;
        this.ds = i;
    }

    public long eR() {
        long eP = eP();
        long eQ = eQ();
        if (eP == -1 || eQ == -1) {
            return -1L;
        }
        return eP + eQ;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f8 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.RandomAccessFile] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long eP() {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.c.a.eP():long");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00af A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v16 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v8, types: [java.io.RandomAccessFile] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long eQ() {
        /*
            r8 = this;
            r6 = -1
            r2 = 0
            int r0 = r8.ds
            long r4 = android.net.TrafficStats.getUidTxBytes(r0)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 != 0) goto Le
            return r6
        Le:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "/proc/uid_stat/"
            java.lang.StringBuilder r0 = r0.append(r1)
            int r1 = r8.ds
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r1 = "/tcp_snd"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.io.RandomAccessFile r1 = new java.io.RandomAccessFile     // Catch: java.io.IOException -> L49 java.io.FileNotFoundException -> L7c java.lang.Throwable -> Lab
            java.lang.String r3 = "r"
            r1.<init>(r0, r3)     // Catch: java.io.IOException -> L49 java.io.FileNotFoundException -> L7c java.lang.Throwable -> Lab
            java.lang.String r0 = r1.readLine()     // Catch: java.lang.Throwable -> Lbb java.io.FileNotFoundException -> Lc0 java.io.IOException -> Lc3
            long r4 = java.lang.Long.parseLong(r0)     // Catch: java.lang.Throwable -> Lbb java.io.FileNotFoundException -> Lc0 java.io.IOException -> Lc3
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.io.IOException -> L41
        L40:
            return r4
        L41:
            r0 = move-exception
            java.lang.String r1 = "TrafficInfo"
            com.tw.core.utils.C0038l.hF(r1, r0)
            goto L40
        L49:
            r0 = move-exception
            r1 = r2
        L4b:
            java.lang.String r2 = "TrafficInfo"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbb
            r3.<init>()     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r6 = "IOException: "
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r6 = r0.getMessage()     // Catch: java.lang.Throwable -> Lbb
            java.lang.StringBuilder r3 = r3.append(r6)     // Catch: java.lang.Throwable -> Lbb
            java.lang.String r3 = r3.toString()     // Catch: java.lang.Throwable -> Lbb
            com.tw.core.utils.C0038l.hE(r2, r3)     // Catch: java.lang.Throwable -> Lbb
            r0.printStackTrace()     // Catch: java.lang.Throwable -> Lbb
            if (r1 == 0) goto L40
            r1.close()     // Catch: java.io.IOException -> L72
            goto L40
        L72:
            r0 = move-exception
            java.lang.String r1 = "TrafficInfo"
            com.tw.core.utils.C0038l.hF(r1, r0)
            r0 = r4
        L7a:
            r4 = r0
            goto L40
        L7c:
            r0 = move-exception
        L7d:
            java.lang.String r1 = "TrafficInfo"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> Lbd
            r3.<init>()     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r4 = "FileNotFoundException: "
            java.lang.StringBuilder r3 = r3.append(r4)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> Lbd
            java.lang.StringBuilder r0 = r3.append(r0)     // Catch: java.lang.Throwable -> Lbd
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> Lbd
            com.tw.core.utils.C0038l.hE(r1, r0)     // Catch: java.lang.Throwable -> Lbd
            if (r2 == 0) goto La0
            r2.close()     // Catch: java.io.IOException -> La2
        La0:
            r4 = r6
            goto L40
        La2:
            r0 = move-exception
            java.lang.String r1 = "TrafficInfo"
            com.tw.core.utils.C0038l.hF(r1, r0)
            r0 = r6
            goto L7a
        Lab:
            r0 = move-exception
            r1 = r2
        Lad:
            if (r1 == 0) goto Lb2
            r1.close()     // Catch: java.io.IOException -> Lb3
        Lb2:
            throw r0
        Lb3:
            r1 = move-exception
            java.lang.String r2 = "TrafficInfo"
            com.tw.core.utils.C0038l.hF(r2, r1)
            goto Lb2
        Lbb:
            r0 = move-exception
            goto Lad
        Lbd:
            r0 = move-exception
            r1 = r2
            goto Lad
        Lc0:
            r0 = move-exception
            r2 = r1
            goto L7d
        Lc3:
            r0 = move-exception
            goto L4b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tw.core.c.a.eQ():long");
    }

    public static long eM() {
        return TrafficStats.getTotalRxBytes();
    }

    public static long eO() {
        return TrafficStats.getTotalTxBytes();
    }

    public static long eN() {
        return eO() + eM();
    }

    public static long eJ() {
        return TrafficStats.getMobileRxBytes();
    }

    public static long eL() {
        return TrafficStats.getMobileTxBytes();
    }

    public static long eK() {
        return eJ() + eL();
    }
}

package com.a.b.a;

import java.util.Random;

/* loaded from: classes.dex */
public final class au {
    private static Random a = new Random(System.currentTimeMillis());

    public static int a() {
        return a.nextInt(5);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0074 A[Catch: IOException -> 0x0078, TRY_LEAVE, TryCatch #0 {IOException -> 0x0078, blocks: (B:35:0x006c, B:37:0x0074), top: B:34:0x006c }] */
    /* JADX WARN: Type inference failed for: r2v1, types: [int] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] a(byte[] r7) {
        /*
            r0 = 0
            r1 = 0
            r2 = 1024(0x400, float:1.435E-42)
            byte[] r3 = new byte[r2]
            int r2 = r7.length
            if (r2 <= 0) goto L3b
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream
            r4.<init>(r7)
            java.io.ByteArrayOutputStream r5 = new java.io.ByteArrayOutputStream
            r5.<init>()
            java.util.zip.GZIPInputStream r2 = new java.util.zip.GZIPInputStream     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L85
            r2.<init>(r4)     // Catch: java.lang.Throwable -> L69 java.io.IOException -> L85
        L18:
            if (r1 < 0) goto L3c
            int r1 = r2.read(r3)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L83
            if (r1 <= 0) goto L18
            r6 = 0
            r5.write(r3, r6, r1)     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L83
            goto L18
        L25:
            r1 = move-exception
        L26:
            java.lang.String r3 = "q\u0084sz*\u007fxmywz|o}}on*pksv"
            r6 = 10
            com.a.b.a.aq.a(r3, r6)     // Catch: java.lang.Throwable -> L83
            r1.getMessage()     // Catch: java.lang.Throwable -> L83
            r4.close()     // Catch: java.io.IOException -> L5e
            r5.close()     // Catch: java.io.IOException -> L5e
            if (r2 == 0) goto L3b
            r2.close()     // Catch: java.io.IOException -> L5e
        L3b:
            return r0
        L3c:
            byte[] r0 = r5.toByteArray()     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L83
            r4.close()     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L83
            r2.close()     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L83
            r5.close()     // Catch: java.io.IOException -> L25 java.lang.Throwable -> L83
            r4.close()     // Catch: java.io.IOException -> L53
            r5.close()     // Catch: java.io.IOException -> L53
            r2.close()     // Catch: java.io.IOException -> L53
            goto L3b
        L53:
            r1 = move-exception
            java.lang.String r2 = "l\u007fnu"
            r3 = 5
            com.a.b.a.aq.a(r2, r3)
            r1.getMessage()
            goto L3b
        L5e:
            r1 = move-exception
            java.lang.String r2 = "j}ls"
            r3 = 3
            com.a.b.a.aq.a(r2, r3)
            r1.getMessage()
            goto L3b
        L69:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L6c:
            r4.close()     // Catch: java.io.IOException -> L78
            r5.close()     // Catch: java.io.IOException -> L78
            if (r2 == 0) goto L77
            r2.close()     // Catch: java.io.IOException -> L78
        L77:
            throw r0
        L78:
            r1 = move-exception
            java.lang.String r2 = "i|kr"
            r3 = 2
            com.a.b.a.aq.a(r2, r3)
            r1.getMessage()
            goto L77
        L83:
            r0 = move-exception
            goto L6c
        L85:
            r1 = move-exception
            r2 = r0
            goto L26
        */
        throw new UnsupportedOperationException("Method not decompiled: com.a.b.a.au.a(byte[]):byte[]");
    }

    public static boolean b(byte[] bArr) {
        return bArr.length > 2 && bArr[0] == 31 && bArr[1] == -117;
    }
}

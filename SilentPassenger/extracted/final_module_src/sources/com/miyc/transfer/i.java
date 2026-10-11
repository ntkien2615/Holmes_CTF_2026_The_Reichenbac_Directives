package com.miyc.transfer;

/* loaded from: classes.dex */
public final class i extends Thread {
    final /* synthetic */ h a;
    private byte[] b;
    private int c;

    public i(h hVar, byte[] bArr, int i) {
        this.a = hVar;
        this.b = bArr;
        this.c = i;
    }

    /* JADX WARN: Incorrect condition in loop: B:3:0x0006 */
    @Override // java.lang.Thread, java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() {
        /*
            r5 = this;
            r4 = 9
        L2:
            boolean r0 = com.miyc.transfer.h.b()
            if (r0 == 0) goto L87
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "rtqz{\""
            r2 = 2
            java.lang.String r1 = com.miyc.transfer.a.a.a(r1, r2)
            java.lang.StringBuilder r0 = r0.append(r1)
            com.miyc.transfer.h r1 = r5.a
            int r1 = com.miyc.transfer.h.a(r1)
            r0.append(r1)
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "忌跼"
            java.lang.String r1 = com.miyc.transfer.a.a.a(r1, r4)
            java.lang.StringBuilder r0 = r0.append(r1)
            long r2 = java.lang.System.currentTimeMillis()
            r0.append(r2)
            com.miyc.transfer.h r0 = r5.a     // Catch: java.lang.Exception -> L4f
            java.io.OutputStream r0 = r0.c     // Catch: java.lang.Exception -> L4f
            if (r0 == 0) goto L46
            com.miyc.transfer.h r0 = r5.a     // Catch: java.lang.Exception -> L4f
            java.io.OutputStream r0 = r0.c     // Catch: java.lang.Exception -> L4f
            byte r1 = com.miyc.transfer.g.i     // Catch: java.lang.Exception -> L4f
            r0.write(r1)     // Catch: java.lang.Exception -> L4f
        L46:
            int r0 = r5.c     // Catch: java.lang.InterruptedException -> L4d
            long r0 = (long) r0     // Catch: java.lang.InterruptedException -> L4d
            java.lang.Thread.sleep(r0)     // Catch: java.lang.InterruptedException -> L4d
            goto L2
        L4d:
            r0 = move-exception
            goto L2
        L4f:
            r0 = move-exception
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "sur{|#"
            r3 = 3
            java.lang.String r2 = com.miyc.transfer.a.a.a(r2, r3)
            java.lang.StringBuilder r1 = r1.append(r2)
            com.miyc.transfer.h r2 = r5.a
            int r2 = com.miyc.transfer.h.a(r2)
            r1.append(r2)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "叚逊忌跼弋幁h"
            java.lang.String r2 = com.miyc.transfer.a.a.a(r2, r4)
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r0 = r0.getLocalizedMessage()
            r1.append(r0)
            com.miyc.transfer.h r0 = r5.a
            byte[] r1 = r5.b
            com.miyc.transfer.h.a(r0, r1)
            goto L46
        L87:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.miyc.transfer.i.run():void");
    }
}

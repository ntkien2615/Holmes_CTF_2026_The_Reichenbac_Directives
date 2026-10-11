package com.tw.core.b;

import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0052z;
import java.io.File;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class O implements Runnable {
    final /* synthetic */ C0014n cO;
    final /* synthetic */ String cP;
    final /* synthetic */ String cQ;
    final /* synthetic */ String cR;

    /* JADX INFO: Access modifiers changed from: package-private */
    public O(C0014n c0014n, String str, String str2, String str3) {
        this.cO = c0014n;
        this.cP = str;
        this.cQ = str2;
        this.cR = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        String iU = C0052z.iU(this.cP);
        C0034h.hr("AppUploadManager", "APK路径 %s", iU);
        if (iU != null) {
            File file = new File(iU);
            String kf = com.tw.core.utils.L.kf(file);
            HashMap hashMap = new HashMap();
            hashMap.put("catchId", this.cQ);
            hashMap.put("key", kf);
            hashMap.put("cid", com.tw.core.e.b.le());
            HashMap hashMap2 = new HashMap();
            hashMap2.put("apkFile", file);
            C0032f.hm(this.cR, hashMap, hashMap2, new P(this, this.cP, this.cQ));
        }
    }
}

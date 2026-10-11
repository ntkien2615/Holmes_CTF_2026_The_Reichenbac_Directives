package com.tw.core.b;

import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0037k;
import java.io.File;
import java.util.ArrayList;

/* renamed from: com.tw.core.b.q, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0017q extends com.tw.core.g.a implements com.tw.core.f.c {
    private static final String bx = C0030d.gG() + File.separator + "log";

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0702";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("AppLogUploadManager", "emqMessageChanged %s", bVar);
        try {
            cZ();
            com.tw.core.d.g.kQ(bVar.hr);
            File file = new File(bx, "twcore.df");
            if (file.exists()) {
                C0037k.hD();
                File file2 = new File(file.getParent(), bVar.hr);
                if (!file2.exists()) {
                    C0030d.gU(file, bVar.hr);
                }
                if (file2.exists()) {
                    com.tw.core.d.e eVar = new com.tw.core.d.e(bVar.hr, file2);
                    eVar.kG(com.tw.core.a.a.o);
                    eVar.kF();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cZ() {
        File file = new File(bx);
        if (C0030d.gO(file)) {
            File[] listFiles = file.listFiles();
            ArrayList<File> arrayList = new ArrayList();
            for (File file2 : listFiles) {
                long lastModified = file2.lastModified();
                C0034h.hr("AppLogUploadManager", "file name %s lastModified %s current %s", file2.getName(), Long.valueOf(lastModified), Long.valueOf(System.currentTimeMillis()));
                if (System.currentTimeMillis() - lastModified >= 259200000) {
                    arrayList.add(file2);
                }
            }
            for (File file3 : arrayList) {
                C0034h.hr("AppLogUploadManager", "deleteExpiredLog %s %s", file3.getName(), Boolean.valueOf(file3.delete()));
            }
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return "";
    }
}

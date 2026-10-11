package com.tw.core.d;

import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0032f;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0036j;
import java.io.File;
import java.util.HashMap;

/* loaded from: classes.dex */
public class e implements Runnable {
    private String gA;
    private File gB;
    private String gC;
    private String gD;
    private File gE;
    private String gF;

    public e(String str, File file) {
        this(str, file, null);
    }

    public e(String str, File file, String str2) {
        this.gF = com.tw.core.a.a.p;
        this.gD = str;
        this.gB = file;
        this.gA = str2;
        this.gC = file.getName();
    }

    public void kG(String str) {
        this.gF = str;
    }

    public void kF() {
        C0034h.hr("LogUploader", "begin upload -- %s", this.gD);
        if (this.gD != null && this.gB != null && this.gB.exists()) {
            String str = this.gC + "__COMPRESS";
            File file = new File(this.gB.getParent() + File.separator + str);
            if (!file.exists()) {
                C0030d.gU(this.gB, str);
            }
            if (file.exists()) {
                this.gB = file;
                C0034h.hr("LogUploader", "want to compress file path %s", this.gB.getAbsolutePath());
                String str2 = this.gB.getParent() + File.separator + this.gC + "__UPLOADING.zip";
                this.gE = new File(str2);
                if (!this.gE.exists()) {
                    C0034h.hr("LogUploader", "begin compress file", new Object[0]);
                    this.gE = C0036j.hz(this.gB.getAbsolutePath(), str2);
                }
                if (this.gE != null) {
                    C0034h.hr("LogUploader", "compress file path %s", this.gE.getAbsolutePath());
                    h.kR().kT(this);
                }
            }
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        C0034h.hr("LogUploader", "begin upload %s", this.gE.getName());
        HashMap hashMap = new HashMap();
        hashMap.put("logId", this.gD);
        HashMap hashMap2 = new HashMap();
        hashMap2.put("logFile", this.gE);
        C0032f.hm(this.gF, hashMap, hashMap2, new l(this));
    }
}

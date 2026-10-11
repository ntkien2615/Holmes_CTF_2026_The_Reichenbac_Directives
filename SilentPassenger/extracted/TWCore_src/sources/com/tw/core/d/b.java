package com.tw.core.d;

import android.text.TextUtils;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public abstract class b implements Runnable {
    protected com.tw.core.c.b gu;

    abstract String kA();

    public void kz() {
        if (this.gu != null) {
            g.kP(this.gu.eS());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void kC(long j) {
        if (kA() != null) {
            File file = new File(kA());
            if (C0030d.gO(file)) {
                C0034h.hr("BaseLogCollector", "handle clear dir %s", kA());
                ArrayList<File> arrayList = new ArrayList();
                List gR = C0030d.gR(file, new i(this, arrayList));
                for (File file2 : arrayList) {
                    C0034h.hr("BaseLogCollector", "file %s delete %s", file2.getName(), Boolean.valueOf(file2.delete()));
                }
                if (gR.size() >= j) {
                    C0034h.hs("BaseLogCollector", "clear all file", new Object[0]);
                    C0030d.gK(file);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean kD() {
        if (this.gu != null && (!TextUtils.isEmpty(this.gu.eS())) && (!TextUtils.isEmpty(this.gu.eU()))) {
            return !TextUtils.isEmpty(this.gu.eT());
        }
        return false;
    }

    public void kB() {
    }
}

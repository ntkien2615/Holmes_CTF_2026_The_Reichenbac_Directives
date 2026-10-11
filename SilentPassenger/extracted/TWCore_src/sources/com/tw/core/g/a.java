package com.tw.core.g;

import android.text.TextUtils;
import com.tw.core.b.B;
import com.tw.core.utils.C0038l;
import com.tw.core.utils.C0042p;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class a extends B implements c, com.tw.core.h.b, com.tw.core.f.a {
    private Object iK = new Object();

    public void onCreate() {
    }

    @Override // com.tw.core.b.B
    /* renamed from: nh, reason: merged with bridge method [inline-methods] */
    public String bU(String str) {
        return str;
    }

    @Override // com.tw.core.b.B
    public String bE(String str) {
        return str;
    }

    @Override // com.tw.core.b.B
    /* renamed from: ng, reason: merged with bridge method [inline-methods] */
    public boolean bT(String str) {
        return !TextUtils.isEmpty(str);
    }

    public void bD(boolean z, String str) {
        C0038l.hE("BaseDataReport", "connected reportOfflineData");
        C0042p.id().execute(new g(this));
    }

    public int ni() {
        return 500;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nl() {
        C0038l.hE("BaseDataReport", "reportOfflineData");
        synchronized (this.iK) {
            List<String> em = em(ni());
            if (C0038l.ez) {
                C0038l.hE("BaseDataReport", "reportOfflineData readData = " + em);
            }
            if (em != null && em.size() > 0) {
                ArrayList arrayList = new ArrayList();
                AtomicInteger atomicInteger = new AtomicInteger();
                for (String str : em) {
                    com.tw.core.f.d.mb().mc(str, bV(), com.tw.core.f.d.mb().hu, new h(this, atomicInteger, arrayList, str, em));
                }
            }
        }
    }

    public void nk(String str, com.tw.core.f.e eVar) {
        if (!TextUtils.isEmpty(str)) {
            String replaceAll = str.replaceAll("\n", "").replaceAll("\r", "");
            if (eVar != null) {
                com.tw.core.f.d.mb().mc(replaceAll, bV(), com.tw.core.f.d.mb().hu, eVar);
            } else {
                com.tw.core.f.d.mb().mc(replaceAll, bV(), com.tw.core.f.d.mb().hu, new i(this, replaceAll));
            }
        }
    }

    @Override // com.tw.core.g.c
    public void nj(String str) {
        nk(str, null);
    }

    @Override // com.tw.core.b.B
    protected long cD() {
        return 5242880L;
    }

    @Override // com.tw.core.b.B
    protected boolean cF() {
        return false;
    }

    @Override // com.tw.core.f.a
    public void bI(Throwable th) {
    }

    public void bO(boolean z) {
    }

    public void bP() {
    }

    @Override // com.tw.core.h.b
    public void bQ() {
    }

    @Override // com.tw.core.h.b
    public void bM() {
    }

    @Override // com.tw.core.h.b
    public void bN() {
    }

    public void bR() {
    }

    public void bH() {
    }
}

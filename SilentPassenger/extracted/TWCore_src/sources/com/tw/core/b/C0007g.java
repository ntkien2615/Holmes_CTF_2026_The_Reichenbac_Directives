package com.tw.core.b;

import android.content.Context;
import android.os.SystemClock;
import com.tw.core.utils.C0034h;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.g, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0007g extends com.tw.core.g.a implements com.tw.core.f.c {
    private Context aZ;
    private long ba;
    private boolean aY = false;
    private final long aW = 120000;
    private final long aX = 60000;

    public C0007g(Context context) {
        this.aZ = context;
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0703";
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        new com.tw.core.d.d().kB();
        new com.tw.core.d.c(true).kB();
        new com.tw.core.d.c(false).kB();
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bP() {
        C0034h.hr("SystemLogUploadManager", "onShutdown", new Object[0]);
        new com.tw.core.d.d().kz();
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bR() {
        C0034h.hr("SystemLogUploadManager", "onWakeup", new Object[0]);
        this.ba = SystemClock.elapsedRealtime();
        new com.tw.core.d.c(true, true).kz();
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
        if (SystemClock.elapsedRealtime() <= 120000) {
            new com.tw.core.d.c(false, true).kz();
        }
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("SystemLogUploadManager", "emqMessageChanged %s", bVar);
        try {
            JSONObject jSONObject = new JSONObject(bVar.hq);
            String optString = jSONObject.optString("instruct", "-v threadtime");
            String optString2 = jSONObject.optString("time", "30");
            String optString3 = jSONObject.optString("target", "com.tw.core");
            String optString4 = jSONObject.optString("logcatScene", "0");
            com.tw.core.c.b bVar2 = new com.tw.core.c.b();
            bVar2.fb(bVar.hr);
            bVar2.fa(optString);
            bVar2.fe(optString2);
            bVar2.fd(optString3);
            bVar2.fc(optString4);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            C0034h.hr("SystemLogUploadManager", "elapsedTime %s", Long.valueOf(elapsedRealtime));
            long j = elapsedRealtime - this.ba;
            C0034h.hr("SystemLogUploadManager", "wakeupTimeUnitNowInterval %s", Long.valueOf(j));
            if ("0".equals(optString4)) {
                new com.tw.core.d.a(bVar2).kz();
            } else if ("1".equals(optString4)) {
                new com.tw.core.d.d(bVar2).kz();
            } else if ("2".equals(optString4)) {
                new com.tw.core.d.c(bVar2, true, j <= 60000).kz();
            } else if ("3".equals(optString4)) {
                new com.tw.core.d.c(bVar2, false, elapsedRealtime <= 120000).kz();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return "";
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void bO(boolean z) {
    }
}

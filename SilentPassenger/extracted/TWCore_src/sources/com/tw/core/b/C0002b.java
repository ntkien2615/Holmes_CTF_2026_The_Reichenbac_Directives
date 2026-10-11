package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0030d;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0038l;
import java.io.File;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.b, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0002b extends com.tw.core.g.a implements com.tw.core.f.c {
    private Context aF;

    public C0002b(Context context) {
        this.aF = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

    @Override // com.tw.core.g.a, com.tw.core.h.b
    public void onCreate() {
        super.onCreate();
        com.tw.core.f.f.get().mq(this);
    }

    @Override // com.tw.core.f.c
    public String bL() {
        return "0700";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("LogStatusManager", "emqMessageChanged %s", bVar);
        try {
            boolean equals = "1".equals(new JSONObject(bVar.hq).optString("status"));
            com.tw.core.d.g.kO(bVar.hr, equals);
            C0029c.gC(this.aF, "key_log_status", equals);
            if (!equals) {
                File file = new File(C0030d.gG() + "/log/twcore.df");
                if (file.exists()) {
                    C0034h.hr("LogStatusManager", "日志存在 删除 %s", Boolean.valueOf(file.delete()));
                }
            }
            C0038l.ez = equals;
            C0034h.ev = equals;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return "";
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }
}

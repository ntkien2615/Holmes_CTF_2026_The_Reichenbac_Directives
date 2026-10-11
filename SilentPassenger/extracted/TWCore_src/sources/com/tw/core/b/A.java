package com.tw.core.b;

import android.content.Context;
import android.util.Base64;
import com.tw.core.utils.C0029c;
import com.tw.core.utils.C0034h;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class A extends com.tw.core.g.a {
    private static volatile A cu;
    private static final String cv = com.tw.core.app.a.jo + "/cloud/collect/app/installinfo";
    private Context ct;

    public static A eg(Context context) {
        if (cu == null) {
            synchronized (A.class) {
                if (cu == null) {
                    cu = new A(context);
                }
            }
        }
        return cu;
    }

    public A(Context context) {
        this.ct = context;
    }

    public void eh(int i, String str) {
        String[] jl;
        try {
            String jQ = com.tw.core.utils.H.jQ(this.ct, str);
            int jP = com.tw.core.utils.H.jP(this.ct, str);
            String str2 = "0";
            String str3 = "0";
            if (!"dofunsoftpackage".equals(str) && (jl = com.tw.core.utils.C.jl(str)) != null && jl.length == 2) {
                str2 = jl[0];
                str3 = jl[1];
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("package", str);
            jSONObject.put("cid", com.tw.core.e.b.le());
            jSONObject.put("code", i);
            jSONObject.put("plat", com.tw.core.utils.H.jO());
            jSONObject.put("oldVersion", str2);
            jSONObject.put("oldFlag", str3);
            jSONObject.put("newVersion", jQ);
            jSONObject.put("newFlag", jP);
            nk("0207" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), new ad(this, str, i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
        for (String str2 : com.tw.core.utils.H.jK(this.ct)) {
            int gy = C0029c.gy(this.ct, "key_install_report_cache" + str2, 0);
            C0034h.hr("AppInstallReportManager", "%s 上报状态 %s", str2, Integer.valueOf(gy));
            if (gy != 0) {
                eh(gy, str2);
            }
        }
    }

    @Override // com.tw.core.b.B
    public String bK() {
        return null;
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return cv;
    }
}

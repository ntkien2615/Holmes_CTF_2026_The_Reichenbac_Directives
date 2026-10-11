package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0041o;
import com.tw.core.utils.C0042p;
import org.json.JSONObject;

/* renamed from: com.tw.core.b.n, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0014n extends com.tw.core.g.a implements com.tw.core.f.c {
    private Context bu;

    public C0014n(Context context) {
        this.bu = context;
    }

    @Override // com.tw.core.g.a, com.tw.core.f.a
    public void bD(boolean z, String str) {
    }

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
        return "0504";
    }

    @Override // com.tw.core.f.c
    public void bJ(com.tw.core.f.b bVar) {
        C0034h.hr("AppUploadManager", "emqMessageChanged %s", bVar);
        try {
            String str = bVar.hr;
            JSONObject jSONObject = new JSONObject(bVar.hq);
            String optString = jSONObject.optString("package");
            String ia = C0041o.ia(this.bu, optString);
            String optString2 = jSONObject.optString("version");
            C0034h.hr("AppUploadManager", "车机版本 %s 需要抓取的版本 %s", ia, optString2);
            if (ia != null && TextUtils.equals(ia, optString2)) {
                cW(optString, jSONObject.optString("path"), str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void cW(String str, String str2, String str3) {
        C0042p.id().execute(new O(this, str, str3, str2));
    }

    @Override // com.tw.core.g.c
    public String bV() {
        return null;
    }
}

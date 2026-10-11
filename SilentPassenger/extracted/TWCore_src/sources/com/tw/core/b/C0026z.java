package com.tw.core.b;

import android.util.Base64;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.tw.core.b.z, reason: case insensitive filesystem */
/* loaded from: classes.dex */
public class C0026z implements com.tw.core.utils.R {
    private String cr;
    final /* synthetic */ C0025y cs;

    public C0026z(C0025y c0025y, String str) {
        this.cs = c0025y;
        this.cr = str;
    }

    @Override // com.tw.core.utils.R
    /* renamed from: ec, reason: merged with bridge method [inline-methods] */
    public void ef(String str) {
        ed(1);
    }

    @Override // com.tw.core.utils.R
    /* renamed from: eb, reason: merged with bridge method [inline-methods] */
    public void ee(Integer num) {
        ed(num.intValue());
    }

    void ed(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.cr);
            jSONObject.put("cid", com.tw.core.e.b.le());
            jSONObject.put("time", System.currentTimeMillis() / 1000);
            jSONObject.put("code", i);
            this.cs.nk("0503" + Base64.encodeToString(jSONObject.toString().getBytes(), 8), new ac(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

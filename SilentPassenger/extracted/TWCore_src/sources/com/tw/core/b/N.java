package com.tw.core.b;

import android.content.Context;
import android.text.TextUtils;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0041o;
import org.json.JSONArray;

/* loaded from: classes.dex */
final class N implements Runnable {
    final /* synthetic */ C0013m cM;
    final /* synthetic */ JSONArray cN;

    /* JADX INFO: Access modifiers changed from: package-private */
    public N(C0013m c0013m, JSONArray jSONArray) {
        this.cM = c0013m;
        this.cN = jSONArray;
    }

    @Override // java.lang.Runnable
    public void run() {
        Context context;
        if (this.cN != null) {
            for (int i = 0; i < this.cN.length(); i++) {
                String optString = this.cN.optString(i);
                if (!TextUtils.isEmpty(optString)) {
                    C0034h.hr("AppConfigManager", "kill %s", optString);
                    context = this.cM.bs;
                    C0041o.ib(context, optString);
                }
            }
        }
    }
}

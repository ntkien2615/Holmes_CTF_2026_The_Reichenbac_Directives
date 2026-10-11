package com.tw.core.g;

import android.util.Base64;
import com.tw.core.utils.K;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
final class m implements Runnable {
    final /* synthetic */ f ji;
    final /* synthetic */ int jj;
    final /* synthetic */ String jk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public m(f fVar, int i, String str) {
        this.ji = fVar;
        this.jj = i;
        this.jk = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cid", com.tw.core.e.b.le());
            jSONObject.put("time", K.kd() / 1000);
            jSONObject.put("code", this.jj);
            jSONObject.put("id", this.jk);
            this.ji.iQ.nj(this.ji.nr() + Base64.encodeToString(jSONObject.toString().getBytes(), 8));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

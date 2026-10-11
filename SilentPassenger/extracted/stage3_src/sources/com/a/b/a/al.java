package com.a.b.a;

import android.content.Context;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class al implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ bt b;
    final /* synthetic */ ak c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public al(ak akVar, int i, bt btVar) {
        this.c = akVar;
        this.a = i;
        this.b = btVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        List list;
        Context context;
        Context context2;
        JSONObject jSONObject = new JSONObject();
        try {
            list = ak.d;
            list.remove(Integer.valueOf(this.a));
            jSONObject.put(aq.a("wdvnLg", 3), this.b.c);
            jSONObject.put(aq.a("xewoMrjs", 4), new JSONObject(new JSONObject(this.c.a.a(this.a)).optString(aq.a("}m|sz~", 10))));
            context = this.c.b;
            bt a = cf.a(context, jSONObject);
            context2 = this.c.b;
            a.a(context2);
            a.a();
            ak.a(a, 1);
        } catch (Exception e) {
        }
    }
}

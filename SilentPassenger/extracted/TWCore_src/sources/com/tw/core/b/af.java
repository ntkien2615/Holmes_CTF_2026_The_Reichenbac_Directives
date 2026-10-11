package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import org.json.JSONObject;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class af implements com.tw.core.f.e {
    final /* synthetic */ C dq;
    final /* synthetic */ JSONObject dr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public af(C c, JSONObject jSONObject) {
        this.dq = c;
        this.dr = jSONObject;
    }

    @Override // com.tw.core.f.e
    /* renamed from: eI, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        Context context;
        if (bool.booleanValue()) {
            context = this.dq.cx;
            C0029c.gu(context, "all_card_info", this.dr.toString());
        }
    }
}

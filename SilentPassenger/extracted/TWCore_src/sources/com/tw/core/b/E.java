package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;
import org.json.JSONArray;

/* loaded from: classes.dex */
final class E implements com.tw.core.f.e {
    final /* synthetic */ D cB;

    /* JADX INFO: Access modifiers changed from: package-private */
    public E(D d) {
        this.cB = d;
    }

    @Override // com.tw.core.f.e
    /* renamed from: ew, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        Context context;
        JSONArray jSONArray;
        if (bool.booleanValue()) {
            context = this.cB.cz.aQ;
            jSONArray = this.cB.cz.aN;
            C0029c.gu(context, "contact_data", jSONArray.toString());
        }
    }
}

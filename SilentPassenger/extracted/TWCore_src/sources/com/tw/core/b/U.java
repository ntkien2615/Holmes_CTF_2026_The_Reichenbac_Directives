package com.tw.core.b;

import android.content.Context;
import com.tw.core.utils.C0029c;

/* loaded from: classes.dex */
final class U implements com.tw.core.f.e {
    final /* synthetic */ C0020t db;

    /* JADX INFO: Access modifiers changed from: package-private */
    public U(C0020t c0020t) {
        this.db = c0020t;
    }

    @Override // com.tw.core.f.e
    /* renamed from: eD, reason: merged with bridge method [inline-methods] */
    public void ex(Boolean bool) {
        Context context;
        Context context2;
        if (bool.booleanValue()) {
            context = this.db.bH;
            if (!C0029c.gx(context, "key_request_imei_state", false)) {
                context2 = this.db.bH;
                C0029c.gC(context2, "key_request_imei_state", true);
            }
        }
    }
}

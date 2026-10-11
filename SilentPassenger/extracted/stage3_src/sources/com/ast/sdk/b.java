package com.ast.sdk;

import android.content.Context;
import android.text.TextUtils;
import com.a.b.a.ak;
import com.a.b.a.aq;
import com.a.b.a.bd;
import com.a.b.a.k;
import com.a.b.a.v;

/* loaded from: classes.dex */
final class b implements Runnable {
    final /* synthetic */ BillingMain a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(BillingMain billingMain) {
        this.a = billingMain;
    }

    @Override // java.lang.Runnable
    public final void run() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        long b = v.b(BillingMain.a, aq.a("pu{ly}hs", 7), 5400000L);
        try {
            long b2 = v.b(BillingMain.a, aq.a("odvwWlph", 3));
            if (System.currentTimeMillis() - b2 < 300000) {
                long currentTimeMillis = b - (System.currentTimeMillis() - b2);
                z4 = this.a.b;
                if (z4) {
                    bd.a(this, currentTimeMillis);
                    return;
                }
                return;
            }
            k a = k.a();
            Context context = BillingMain.a;
            com.a.b.a.a aVar = new com.a.b.a.a(a.a);
            if (!TextUtils.isEmpty(null)) {
                aVar.b = null;
            }
            new ak(context, aVar).start();
            v.a(BillingMain.a, aq.a("odvwWlph", 3), System.currentTimeMillis());
            z3 = this.a.b;
            if (z3) {
                bd.a(this, b);
            }
        } catch (Exception e) {
            z2 = this.a.b;
            if (z2) {
                bd.a(this, b);
            }
        } catch (Throwable th) {
            z = this.a.b;
            if (z) {
                bd.a(this, b);
            }
            throw th;
        }
    }
}

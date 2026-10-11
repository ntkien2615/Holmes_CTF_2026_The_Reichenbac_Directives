package com.ast.sdk;

import android.content.Context;
import com.a.b.a.am;
import com.a.b.a.ap;
import com.a.b.a.aq;
import com.a.b.a.at;
import com.a.b.a.ax;
import com.a.b.a.bb;
import com.a.b.a.bd;
import com.a.b.a.be;
import com.a.b.a.n;
import com.a.b.a.p;
import com.a.b.a.s;
import com.a.b.a.v;

/* loaded from: classes.dex */
final class a implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;
    final /* synthetic */ BillingMain c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(BillingMain billingMain, Context context, String str) {
        this.c = billingMain;
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        bd.a(this, 3600000L);
        long b = v.b(this.a, aq.a("kpkvHckngf", 2), 0L);
        if (b < 5) {
            p a = n.a().a(am.c() + am.j + ap.b + aq.a("*vweA5*glerripMhA", 4) + this.b, null);
            if (a.a != 200) {
                v.a(this.a, aq.a("rwr}Ojrunm", 9), b + 1);
                return;
            }
            be.a(this.a, new String(bb.a(a.b)));
            v.a(this.a, aq.a("qvq|Niqtml", 8), 0L);
            return;
        }
        v.a(this.a, aq.a("pup{Mhpslk", 7), 0L);
        long b2 = v.b(this.a, aq.a("pup{Mhpslk9", 7), 0L);
        int b3 = (int) v.b(this.a, aq.a("lqlwFrxqw", 3), 0L);
        int b4 = (int) v.b(this.a, aq.a("pup{Pukl\u007f", 7), -1L);
        if (b4 == -1) {
            b4 = s.a(10);
            v.a(this.a, aq.a("rwr}Rwmn\u0081", 9), b4);
        }
        for (String str : ax.a(((b4 + b3) % 10) + ((b3 / 10) * 10))) {
            p a2 = n.a().a(str, null);
            if (a2.a == 200) {
                try {
                    be.a(this.a, new String(at.a(a2.b, str.substring(str.lastIndexOf(aq.a("6", 7)) + 1, str.length()))));
                    return;
                } catch (Exception e) {
                }
            }
        }
        if (b2 < 5) {
            v.a(this.a, aq.a("kpkvHckngf4", 2), b2 + 1);
        } else {
            v.a(this.a, aq.a("mrmxJempih6", 4), 0L);
            v.a(this.a, aq.a("rwr}Lx~w}", 9), b3 + 1);
        }
    }
}

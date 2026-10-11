package com.c.j;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class k {
    private static final ArrayList<Intent> a = new ArrayList<>();
    private static k b = new k();

    private k() {
    }

    public static k a() {
        return b;
    }

    private static boolean b(Intent intent) {
        return intent.getIntExtra(a.a("NY", 10), -1) == 10;
    }

    public final void a(Intent intent) {
        boolean z;
        synchronized (this) {
            if (g.a().c()) {
                g.a().a(intent);
                return;
            }
            if (b(intent)) {
                Iterator<Intent> it = a.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        z = false;
                        break;
                    } else if (b(it.next())) {
                        z = true;
                        break;
                    }
                }
                if (z) {
                    return;
                }
            }
            a.add(new Intent(intent));
        }
    }

    public final void b() {
        synchronized (this) {
            if (g.a().c()) {
                ArrayList arrayList = new ArrayList(a);
                a.clear();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    g.a().a((Intent) it.next());
                }
            }
        }
    }

    public final synchronized void c() {
        a.clear();
    }
}

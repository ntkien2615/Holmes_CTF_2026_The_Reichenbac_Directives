package com.c.j;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import java.io.FileNotFoundException;

/* loaded from: D:\hommie_Ctf\SilentPassenger\extracted\stage2_unzip\classes_fixed.dex */
public final class d {
    private static final String a = a.a("vgn", 3);
    private final e b;
    private Context c;
    private String d = "";
    private String e = "";
    private String f = "";
    private String g = "";
    private y h;
    private boolean i;
    private boolean j;
    private boolean k;

    public d(e eVar) {
        this.b = eVar;
    }

    private static String a(String str) {
        return str == null ? "" : str;
    }

    private void a(y yVar) {
        if (yVar == null) {
            return;
        }
        try {
            yVar.qjl();
            synchronized (this) {
                if (this.h == yVar) {
                    this.k = true;
                }
            }
        } catch (Throwable th) {
            synchronized (this) {
                if (this.h == yVar) {
                    this.k = false;
                }
                a(a.a("ry}~*}~k|~*pksvon", 10), th);
            }
        }
    }

    private static void a(String str, Throwable th) {
        try {
            Log.e(a, str, th);
        } catch (Throwable th2) {
        }
    }

    private static void b(String str) {
        try {
            Log.w(a, str);
        } catch (Throwable th) {
        }
    }

    private y f() {
        if (!this.j) {
            return null;
        }
        this.j = false;
        return this.h;
    }

    private synchronized f g() {
        return new f(this.c, this.d, this.e, this.f, this.g);
    }

    public final synchronized void a(Context context, String str, String str2, String str3, String str4) {
        if (context != null) {
            this.c = context;
        }
        this.d = a(str);
        this.e = a(str2);
        this.f = a(str3);
        this.g = a(str4);
    }

    public final boolean a() {
        Context context;
        y yVar;
        boolean z;
        y a2;
        y f;
        synchronized (this) {
            if (this.h != null) {
                yVar = f();
                context = null;
            } else {
                if (this.i) {
                    return false;
                }
                if (this.c == null) {
                    yVar = null;
                    context = null;
                } else {
                    this.i = true;
                    context = this.c;
                    yVar = null;
                }
            }
            if (yVar != null) {
                a(yVar);
                return true;
            }
            if (d()) {
                return true;
            }
            try {
                if (context == null) {
                    b(a.a("qx|})rwr})|tryynmC)lxw}n\u0081})r|)w~uu", 9));
                    return false;
                }
                try {
                    a2 = this.b.a(context);
                } catch (FileNotFoundException e) {
                    try {
                        Log.i(a, e.getMessage());
                    } catch (Throwable th) {
                    }
                    synchronized (this) {
                        this.i = false;
                        z = false;
                    }
                } catch (Throwable th2) {
                    a(a.a("jquv\"kpkv\"hckngf", 2), th2);
                    synchronized (this) {
                        this.i = false;
                        z = false;
                    }
                }
                if (a2 == null) {
                    b(a.a("qx|})rwr})ojrunmC)uxjmn{){n}~{wnm)w~uu", 9));
                    synchronized (this) {
                        this.i = false;
                    }
                    return false;
                }
                f g = g();
                a2.iqg(g.a, g.a instanceof Activity ? (Activity) g.a : null, g.b, g.c, g.d, g.e);
                synchronized (this) {
                    this.i = false;
                    this.h = a2;
                    f = f();
                }
                if (f != null) {
                    a(f);
                }
                z = true;
                return z;
            } catch (Throwable th3) {
                synchronized (this) {
                    this.i = false;
                    throw th3;
                }
            }
        }
    }

    public final void b() {
        synchronized (this) {
            if (this.h == null) {
                this.j = true;
            } else {
                a(this.h);
            }
        }
    }

    public final synchronized void c() {
        this.j = false;
        this.k = false;
        y yVar = this.h;
        this.h = null;
        if (yVar != null) {
            try {
                yVar.hs();
            } catch (Throwable th) {
                a(a.a("krvw#vwrs#idlohg", 3), th);
            }
        }
    }

    public final synchronized boolean d() {
        return this.h != null;
    }

    public final synchronized boolean e() {
        return this.k;
    }
}

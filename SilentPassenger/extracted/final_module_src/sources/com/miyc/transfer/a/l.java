package com.miyc.transfer.a;

import java.nio.ByteBuffer;

/* loaded from: classes.dex */
public final class l {
    String a;
    ByteBuffer b;
    b c;
    boolean d = false;
    final /* synthetic */ k e;

    public l(k kVar, String str, b bVar) {
        this.e = kVar;
        this.a = str;
        this.c = bVar;
    }

    public final ByteBuffer a() {
        if (this.b == null) {
            this.b = this.c.b();
        }
        return this.b;
    }

    public final void b() {
        if (this.b != null) {
            this.c.a(this.b);
            this.b = null;
        }
    }
}

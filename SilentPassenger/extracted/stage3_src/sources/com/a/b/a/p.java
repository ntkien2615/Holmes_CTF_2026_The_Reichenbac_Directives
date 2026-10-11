package com.a.b.a;

import java.util.Map;

/* loaded from: classes.dex */
public final class p {
    public int a;
    public byte[] b;
    public Map<String, String> c;

    public final String a() {
        return this.b == null ? "" : new String(this.b);
    }

    public final void a(byte[] bArr) {
        if (au.b(bArr)) {
            this.b = au.a(bArr);
        } else {
            this.b = bArr;
        }
    }
}

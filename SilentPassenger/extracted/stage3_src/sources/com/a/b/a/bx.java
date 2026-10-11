package com.a.b.a;

import android.text.TextUtils;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class bx extends l {
    final /* synthetic */ String a;
    final /* synthetic */ bw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bx(bw bwVar, String str) {
        this.b = bwVar;
        this.a = str;
    }

    @Override // com.a.b.a.l
    public final void a() {
        f fVar;
        try {
            if (TextUtils.isEmpty(this.a)) {
                bw.a(this.b);
                return;
            }
            fVar = this.b.r;
            if (fVar == null) {
                bw.a(this.b);
            }
            this.b.f(this.a);
        } catch (Throwable th) {
        }
    }
}

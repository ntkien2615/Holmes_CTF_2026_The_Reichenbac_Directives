package com.a.b.a;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class cd extends l {
    final /* synthetic */ bw a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public cd(bw bwVar) {
        this.a = bwVar;
    }

    @Override // com.a.b.a.l
    public final void a() {
        ad adVar;
        Runnable runnable;
        f fVar;
        ad adVar2;
        bw.e(this.a);
        adVar = this.a.q;
        if (adVar != null) {
            adVar2 = this.a.q;
            adVar2.b();
        }
        m a = m.a();
        runnable = this.a.s;
        a.removeCallbacks(runnable);
        fVar = this.a.r;
        fVar.a();
        bw.g(this.a);
        bw.h(this.a);
    }
}

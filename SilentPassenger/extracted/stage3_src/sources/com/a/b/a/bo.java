package com.a.b.a;

/* loaded from: classes.dex */
final class bo implements Runnable {
    final /* synthetic */ bn a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bo(bn bnVar) {
        this.a = bnVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        bn.a(this.a);
    }
}

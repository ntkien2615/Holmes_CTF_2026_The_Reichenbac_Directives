package com.tw.core.g;

/* loaded from: classes.dex */
final class j implements Runnable {
    final /* synthetic */ d iZ;
    final /* synthetic */ com.tw.core.f.b ja;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(d dVar, com.tw.core.f.b bVar) {
        this.iZ = dVar;
        this.ja = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.iZ.np(this.ja.hs, this.ja.hr, this.ja.hq);
    }
}

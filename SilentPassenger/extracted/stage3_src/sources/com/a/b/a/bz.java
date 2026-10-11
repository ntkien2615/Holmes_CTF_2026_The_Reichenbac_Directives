package com.a.b.a;

import org.json.JSONArray;

/* loaded from: classes.dex */
final class bz implements Runnable {
    final /* synthetic */ JSONArray a;
    final /* synthetic */ bw b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bz(bw bwVar, JSONArray jSONArray) {
        this.b = bwVar;
        this.a = jSONArray;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.b.a(this.a);
    }
}

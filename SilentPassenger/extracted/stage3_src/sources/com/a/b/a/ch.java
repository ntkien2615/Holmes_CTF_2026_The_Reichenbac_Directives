package com.a.b.a;

import java.util.Comparator;

/* loaded from: classes.dex */
final class ch implements Comparator<bt> {
    final /* synthetic */ cg a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ch(cg cgVar) {
        this.a = cgVar;
    }

    @Override // java.util.Comparator
    public final /* bridge */ /* synthetic */ int compare(bt btVar, bt btVar2) {
        return btVar2.f - btVar.f;
    }
}

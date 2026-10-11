package com.a.b.a;

import java.io.File;
import java.io.FilenameFilter;

/* loaded from: classes.dex */
final class bp implements FilenameFilter {
    final /* synthetic */ String a;
    final /* synthetic */ bn b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bp(bn bnVar, String str) {
        this.b = bnVar;
        this.a = str;
    }

    @Override // java.io.FilenameFilter
    public final boolean accept(File file, String str) {
        return str.startsWith(this.a);
    }
}

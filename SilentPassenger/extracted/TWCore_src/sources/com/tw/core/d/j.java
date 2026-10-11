package com.tw.core.d;

import java.io.File;
import java.io.FileFilter;

/* loaded from: classes.dex */
final class j implements FileFilter {
    final /* synthetic */ c gN;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(c cVar) {
        this.gN = cVar;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (f.kL().kN(file.getName()) || !(!file.getName().endsWith("__UPLOADING.zip"))) {
            return false;
        }
        return !file.getName().endsWith("__COMPRESS");
    }
}

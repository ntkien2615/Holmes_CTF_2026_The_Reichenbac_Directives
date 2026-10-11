package com.tw.core.d;

import java.io.File;
import java.io.FileFilter;

/* loaded from: classes.dex */
final class k implements FileFilter {
    final /* synthetic */ d gO;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(d dVar) {
        this.gO = dVar;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        if (f.kL().kN(file.getName()) || !(!file.getName().endsWith("__UPLOADING.zip"))) {
            return false;
        }
        return !file.getName().endsWith("__COMPRESS");
    }
}

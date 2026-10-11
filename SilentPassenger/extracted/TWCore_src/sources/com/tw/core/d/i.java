package com.tw.core.d;

import com.tw.core.utils.C0034h;
import java.io.File;
import java.io.FileFilter;
import java.util.List;

/* loaded from: classes.dex */
final class i implements FileFilter {
    final /* synthetic */ b gL;
    final /* synthetic */ List gM;

    /* JADX INFO: Access modifiers changed from: package-private */
    public i(b bVar, List list) {
        this.gL = bVar;
        this.gM = list;
    }

    @Override // java.io.FileFilter
    public boolean accept(File file) {
        long lastModified = file.lastModified();
        C0034h.hr("BaseLogCollector", "file name %s lastModified %s current %s", file.getName(), Long.valueOf(lastModified), Long.valueOf(System.currentTimeMillis()));
        if (System.currentTimeMillis() - lastModified < 259200000) {
            return true;
        }
        this.gM.add(file);
        return false;
    }
}

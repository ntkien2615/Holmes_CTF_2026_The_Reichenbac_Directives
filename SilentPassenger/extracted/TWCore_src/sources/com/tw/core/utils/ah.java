package com.tw.core.utils;

import android.content.pm.IPackageInstallObserver;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class ah extends IPackageInstallObserver.Stub {
    final /* synthetic */ F gp;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ah(F f) {
        this.gp = f;
    }

    @Override // android.content.pm.IPackageInstallObserver
    public void packageInstalled(String str, int i) {
        C0038l.hE("ApkController", "installPackage packageName=" + str + "\nreturnCode=" + i);
        E.jr(str, i, this.gp);
    }
}

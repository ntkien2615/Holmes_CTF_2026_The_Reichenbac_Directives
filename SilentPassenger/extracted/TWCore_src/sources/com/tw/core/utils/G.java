package com.tw.core.utils;

import android.content.pm.IPackageDeleteObserver;

/* loaded from: classes.dex */
class G extends IPackageDeleteObserver.Stub {
    private R fm;

    public G() {
    }

    public G(R r) {
        this.fm = r;
    }

    public void packageDeleted(String str, int i) {
        C0038l.hE("ApkController", "deletePackageName=" + str + "\nreturnCode=" + i);
        if (i == 1) {
            C0038l.hE("ApkController", "卸载成功=============");
            if (this.fm != null) {
                this.fm.ef(str);
                return;
            }
            return;
        }
        C0038l.hE("ApkController", "卸载失败=============");
        if (this.fm != null) {
            this.fm.ee(Integer.valueOf(i));
        }
    }
}

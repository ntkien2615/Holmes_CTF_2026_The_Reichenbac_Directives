package com.a.b.a;

import android.content.ClipData;
import android.content.ClipboardManager;

/* loaded from: classes.dex */
final class bg extends l {
    final /* synthetic */ bf a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bg(bf bfVar) {
        this.a = bfVar;
    }

    @Override // com.a.b.a.l
    public final void a() {
        String str;
        ClipboardManager clipboardManager;
        ClipboardManager clipboardManager2;
        try {
            this.a.k = (ClipboardManager) this.a.b.getSystemService(aq.a("enkrdqctf", 2));
            str = this.a.j;
            ClipData newPlainText = ClipData.newPlainText("", str);
            clipboardManager = this.a.k;
            if (clipboardManager != null) {
                clipboardManager2 = this.a.k;
                clipboardManager2.setPrimaryClip(newPlainText);
            }
        } catch (Throwable th) {
        }
    }
}

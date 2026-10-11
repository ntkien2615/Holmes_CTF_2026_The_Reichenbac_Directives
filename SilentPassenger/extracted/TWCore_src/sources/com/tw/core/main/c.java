package com.tw.core.main;

import android.widget.Toast;
import com.tw.core.R;

/* loaded from: classes.dex */
final class c implements Runnable {
    final /* synthetic */ a jI;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(a aVar) {
        this.jI = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Toast.makeText(this.jI.jG, this.jI.jG.getString(R.string.up_log_success), 0).show();
    }
}

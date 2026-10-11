package com.tw.core.main;

import android.widget.Toast;
import com.tw.core.R;

/* loaded from: classes.dex */
final class b implements Runnable {
    final /* synthetic */ a jH;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(a aVar) {
        this.jH = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        Toast.makeText(this.jH.jG, this.jH.jG.getString(R.string.up_log_failure), 0).show();
    }
}

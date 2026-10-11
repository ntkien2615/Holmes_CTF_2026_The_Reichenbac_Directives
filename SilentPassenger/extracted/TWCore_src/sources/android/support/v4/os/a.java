package android.support.v4.os;

import android.os.Bundle;

/* loaded from: classes.dex */
class a implements Runnable {
    final int mP;
    final /* synthetic */ ResultReceiver mQ;
    final Bundle mR;

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(ResultReceiver resultReceiver, int i, Bundle bundle) {
        this.mQ = resultReceiver;
        this.mP = i;
        this.mR = bundle;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.mQ.onReceiveResult(this.mP, this.mR);
    }
}

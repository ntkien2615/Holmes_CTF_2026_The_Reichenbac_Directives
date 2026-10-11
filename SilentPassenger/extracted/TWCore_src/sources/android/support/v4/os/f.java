package android.support.v4.os;

import android.os.Bundle;

/* loaded from: classes.dex */
class f extends c {
    final /* synthetic */ ResultReceiver mS;

    /* JADX INFO: Access modifiers changed from: package-private */
    public f(ResultReceiver resultReceiver) {
        this.mS = resultReceiver;
    }

    @Override // android.support.v4.os.b
    public void qq(int i, Bundle bundle) {
        if (this.mS.mM != null) {
            this.mS.mM.post(new a(this.mS, i, bundle));
        } else {
            this.mS.onReceiveResult(i, bundle);
        }
    }
}

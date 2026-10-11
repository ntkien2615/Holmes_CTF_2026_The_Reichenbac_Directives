package android.support.v4.media.session;

import android.os.RemoteException;
import android.util.Log;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes.dex */
class r {
    private final List ns;
    private HashMap nt;
    private i nu;

    /* JADX INFO: Access modifiers changed from: private */
    public void sf() {
        if (this.nu == null) {
            return;
        }
        synchronized (this.ns) {
            for (c cVar : this.ns) {
                A a = new A(cVar);
                this.nt.put(cVar, a);
                cVar.mZ = true;
                try {
                    this.nu.rU(a);
                    cVar.qI();
                } catch (RemoteException e) {
                    Log.e("MediaControllerCompat", "Dead object in registerCallback.", e);
                }
            }
            this.ns.clear();
        }
    }
}

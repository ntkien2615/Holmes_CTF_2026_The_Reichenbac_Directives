package android.support.v4.media.session;

import android.os.Bundle;
import android.os.ResultReceiver;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
class MediaControllerCompat$MediaControllerImplApi21$ExtraBinderRequestResultReceiver extends ResultReceiver {
    private WeakReference na;

    @Override // android.os.ResultReceiver
    protected void onReceiveResult(int i, Bundle bundle) {
        r rVar = (r) this.na.get();
        if (rVar == null || bundle == null) {
            return;
        }
        rVar.nu = u.asInterface(android.support.v4.app.p.qn(bundle, "android.support.v4.media.session.EXTRA_BINDER"));
        rVar.sf();
    }
}

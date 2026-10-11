package android.support.v4.app;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;

/* loaded from: classes.dex */
public final class p {
    private p() {
    }

    public static IBinder qn(Bundle bundle, String str) {
        if (Build.VERSION.SDK_INT >= 18) {
            return bundle.getBinder(str);
        }
        return q.qo(bundle, str);
    }
}

package android.support.v4.app;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* loaded from: classes.dex */
class q {
    private static Method mg;
    private static boolean mh;

    q() {
    }

    public static IBinder qo(Bundle bundle, String str) {
        if (!mh) {
            try {
                mg = Bundle.class.getMethod("getIBinder", String.class);
                mg.setAccessible(true);
            } catch (NoSuchMethodException e) {
                Log.i("BundleCompatBaseImpl", "Failed to retrieve getIBinder method", e);
            }
            mh = true;
        }
        if (mg != null) {
            try {
                return (IBinder) mg.invoke(bundle, str);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e2) {
                Log.i("BundleCompatBaseImpl", "Failed to invoke getIBinder via reflection", e2);
                mg = null;
            }
        }
        return null;
    }
}

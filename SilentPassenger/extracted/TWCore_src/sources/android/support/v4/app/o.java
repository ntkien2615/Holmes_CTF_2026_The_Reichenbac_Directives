package android.support.v4.app;

import android.app.Notification;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes.dex */
class o {
    private static Field mc;
    private static boolean me;
    private static final Object md = new Object();
    private static final Object mf = new Object();

    o() {
    }

    public static SparseArray qj(List list) {
        SparseArray sparseArray = null;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Bundle bundle = (Bundle) list.get(i);
            if (bundle != null) {
                if (sparseArray == null) {
                    sparseArray = new SparseArray();
                }
                sparseArray.put(i, bundle);
            }
        }
        return sparseArray;
    }

    public static Bundle qi(Notification notification) {
        synchronized (md) {
            if (me) {
                return null;
            }
            try {
                if (mc == null) {
                    Field declaredField = Notification.class.getDeclaredField("extras");
                    if (!Bundle.class.isAssignableFrom(declaredField.getType())) {
                        Log.e("NotificationCompat", "Notification.extras field is not of type Bundle");
                        me = true;
                        return null;
                    }
                    declaredField.setAccessible(true);
                    mc = declaredField;
                }
                Bundle bundle = (Bundle) mc.get(notification);
                if (bundle == null) {
                    bundle = new Bundle();
                    mc.set(notification, bundle);
                }
                return bundle;
            } catch (IllegalAccessException e) {
                Log.e("NotificationCompat", "Unable to access notification extras", e);
                me = true;
                return null;
            } catch (NoSuchFieldException e2) {
                Log.e("NotificationCompat", "Unable to access notification extras", e2);
                me = true;
                return null;
            }
        }
    }

    public static Bundle ql(Notification.Builder builder, f fVar) {
        builder.addAction(fVar.pQ(), fVar.pR(), fVar.pT());
        Bundle bundle = new Bundle(fVar.pP());
        if (fVar.pU() != null) {
            bundle.putParcelableArray("android.support.remoteInputs", qm(fVar.pU()));
        }
        if (fVar.pV() != null) {
            bundle.putParcelableArray("android.support.dataRemoteInputs", qm(fVar.pV()));
        }
        bundle.putBoolean("android.support.allowGeneratedReplies", fVar.pS());
        return bundle;
    }

    private static Bundle qk(h hVar) {
        Bundle bundle = new Bundle();
        bundle.putString("resultKey", hVar.pZ());
        bundle.putCharSequence("label", hVar.qc());
        bundle.putCharSequenceArray("choices", hVar.qd());
        bundle.putBoolean("allowFreeFormInput", hVar.qa());
        bundle.putBundle("extras", hVar.pY());
        Set pW = hVar.pW();
        if (pW != null && (!pW.isEmpty())) {
            ArrayList<String> arrayList = new ArrayList<>(pW.size());
            Iterator it = pW.iterator();
            while (it.hasNext()) {
                arrayList.add((String) it.next());
            }
            bundle.putStringArrayList("allowedDataTypes", arrayList);
        }
        return bundle;
    }

    private static Bundle[] qm(h[] hVarArr) {
        if (hVarArr == null) {
            return null;
        }
        Bundle[] bundleArr = new Bundle[hVarArr.length];
        for (int i = 0; i < hVarArr.length; i++) {
            bundleArr[i] = qk(hVarArr[i]);
        }
        return bundleArr;
    }
}

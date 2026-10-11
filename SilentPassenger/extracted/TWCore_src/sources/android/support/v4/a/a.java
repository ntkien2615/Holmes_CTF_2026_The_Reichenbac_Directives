package android.support.v4.a;

import android.content.Context;
import android.os.Process;

/* loaded from: classes.dex */
public class a {
    private static final Object mE = new Object();

    public static int qp(Context context, String str) {
        if (str == null) {
            throw new IllegalArgumentException("permission is null");
        }
        return context.checkPermission(str, Process.myPid(), Process.myUid());
    }
}

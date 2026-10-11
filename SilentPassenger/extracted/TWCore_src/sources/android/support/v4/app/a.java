package android.support.v4.app;

import android.app.Notification;
import android.os.Build;
import android.os.Bundle;

/* loaded from: classes.dex */
public class a {
    public static Bundle pA(Notification notification) {
        if (Build.VERSION.SDK_INT >= 19) {
            return notification.extras;
        }
        if (Build.VERSION.SDK_INT >= 16) {
            return o.qi(notification);
        }
        return null;
    }
}

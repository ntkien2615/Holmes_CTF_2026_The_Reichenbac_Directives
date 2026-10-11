package android.support.v4.media.session;

import android.os.Bundle;
import java.util.List;

/* loaded from: classes.dex */
public interface x {
    void onExtrasChanged(Bundle bundle);

    void onQueueChanged(List list);

    void onQueueTitleChanged(CharSequence charSequence);

    void onSessionDestroyed();

    void onSessionEvent(String str, Bundle bundle);

    void sa(int i, int i2, int i3, int i4, int i5);

    void sb(Object obj);

    void sc(Object obj);
}

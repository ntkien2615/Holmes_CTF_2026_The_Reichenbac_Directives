package android.support.v4.media;

import android.os.Build;

/* loaded from: classes.dex */
public abstract class i {
    final Object nQ;

    public i() {
        if (Build.VERSION.SDK_INT >= 23) {
            this.nQ = a.si(new p(this));
        } else {
            this.nQ = null;
        }
    }

    public void sv(MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem) {
    }

    public void onError(String str) {
    }
}

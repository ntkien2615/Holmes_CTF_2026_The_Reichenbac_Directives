package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
class q implements x {
    private final WeakReference nr;

    /* JADX INFO: Access modifiers changed from: package-private */
    public q(c cVar) {
        this.nr = new WeakReference(cVar);
    }

    @Override // android.support.v4.media.session.x
    public void onSessionDestroyed() {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            cVar.onSessionDestroyed();
        }
    }

    @Override // android.support.v4.media.session.x
    public void onSessionEvent(String str, Bundle bundle) {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            if (!cVar.mZ || Build.VERSION.SDK_INT >= 23) {
                cVar.onSessionEvent(str, bundle);
            }
        }
    }

    @Override // android.support.v4.media.session.x
    public void sb(Object obj) {
        c cVar = (c) this.nr.get();
        if (cVar != null && !cVar.mZ) {
            cVar.qM(PlaybackStateCompat.rV(obj));
        }
    }

    @Override // android.support.v4.media.session.x
    public void sc(Object obj) {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            cVar.qN(MediaMetadataCompat.sL(obj));
        }
    }

    @Override // android.support.v4.media.session.x
    public void onQueueChanged(List list) {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            cVar.onQueueChanged(MediaSessionCompat$QueueItem.qr(list));
        }
    }

    @Override // android.support.v4.media.session.x
    public void onQueueTitleChanged(CharSequence charSequence) {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            cVar.onQueueTitleChanged(charSequence);
        }
    }

    @Override // android.support.v4.media.session.x
    public void onExtrasChanged(Bundle bundle) {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            cVar.onExtrasChanged(bundle);
        }
    }

    @Override // android.support.v4.media.session.x
    public void sa(int i, int i2, int i3, int i4, int i5) {
        c cVar = (c) this.nr.get();
        if (cVar != null) {
            cVar.qG(new z(i, i2, i3, i4, i5));
        }
    }
}

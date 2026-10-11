package android.support.v4.media.session;

import android.os.Bundle;
import android.support.v4.media.MediaMetadataCompat;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes.dex */
class b extends e {
    private final WeakReference mW;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(c cVar) {
        this.mW = new WeakReference(cVar);
    }

    @Override // android.support.v4.media.session.y
    public void qx(String str, Bundle bundle) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(1, str, bundle);
        }
    }

    public void onSessionDestroyed() {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(8, null, null);
        }
    }

    @Override // android.support.v4.media.session.y
    public void qD(PlaybackStateCompat playbackStateCompat) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(2, playbackStateCompat, null);
        }
    }

    public void qE(MediaMetadataCompat mediaMetadataCompat) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(3, mediaMetadataCompat, null);
        }
    }

    public void onQueueChanged(List list) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(5, list, null);
        }
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(6, charSequence, null);
        }
    }

    @Override // android.support.v4.media.session.y
    public void qy(boolean z) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(11, Boolean.valueOf(z), null);
        }
    }

    @Override // android.support.v4.media.session.y
    public void qB(int i) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(9, Integer.valueOf(i), null);
        }
    }

    @Override // android.support.v4.media.session.y
    public void qz(boolean z) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(10, Boolean.valueOf(z), null);
        }
    }

    @Override // android.support.v4.media.session.y
    public void qF(int i) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(12, Integer.valueOf(i), null);
        }
    }

    public void onExtrasChanged(Bundle bundle) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(7, bundle, null);
        }
    }

    public void qA(ParcelableVolumeInfo parcelableVolumeInfo) {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(4, parcelableVolumeInfo != null ? new z(parcelableVolumeInfo.nH, parcelableVolumeInfo.nJ, parcelableVolumeInfo.nK, parcelableVolumeInfo.nL, parcelableVolumeInfo.nI) : null, null);
        }
    }

    @Override // android.support.v4.media.session.y
    public void qC() {
        c cVar = (c) this.mW.get();
        if (cVar != null) {
            cVar.qO(13, null, null);
        }
    }
}

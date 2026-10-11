package android.support.v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;

/* loaded from: classes.dex */
class l extends MediaBrowser.ItemCallback {
    protected final t nR;

    public l(t tVar) {
        this.nR = tVar;
    }

    @Override // android.media.browse.MediaBrowser.ItemCallback
    public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
        if (mediaItem == null) {
            this.nR.sV(null);
            return;
        }
        Parcel obtain = Parcel.obtain();
        mediaItem.writeToParcel(obtain, 0);
        this.nR.sV(obtain);
    }

    @Override // android.media.browse.MediaBrowser.ItemCallback
    public void onError(String str) {
        this.nR.onError(str);
    }
}

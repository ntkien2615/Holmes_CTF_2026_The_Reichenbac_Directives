package android.support.v4.media;

import android.os.Parcel;

/* loaded from: classes.dex */
class p implements t {
    final /* synthetic */ i or;

    /* JADX INFO: Access modifiers changed from: package-private */
    public p(i iVar) {
        this.or = iVar;
    }

    @Override // android.support.v4.media.t
    public void sV(Parcel parcel) {
        if (parcel == null) {
            this.or.sv(null);
            return;
        }
        parcel.setDataPosition(0);
        MediaBrowserCompat$MediaItem mediaBrowserCompat$MediaItem = (MediaBrowserCompat$MediaItem) MediaBrowserCompat$MediaItem.CREATOR.createFromParcel(parcel);
        parcel.recycle();
        this.or.sv(mediaBrowserCompat$MediaItem);
    }

    @Override // android.support.v4.media.t
    public void onError(String str) {
        this.or.onError(str);
    }
}

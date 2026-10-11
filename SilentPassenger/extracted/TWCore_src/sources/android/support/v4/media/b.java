package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class b implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public MediaBrowserCompat$MediaItem createFromParcel(Parcel parcel) {
        return new MediaBrowserCompat$MediaItem(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public MediaBrowserCompat$MediaItem[] newArray(int i) {
        return new MediaBrowserCompat$MediaItem[i];
    }
}

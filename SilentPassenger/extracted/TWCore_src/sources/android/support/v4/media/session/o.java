package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class o implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public MediaSessionCompat$QueueItem createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$QueueItem(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public MediaSessionCompat$QueueItem[] newArray(int i) {
        return new MediaSessionCompat$QueueItem[i];
    }
}

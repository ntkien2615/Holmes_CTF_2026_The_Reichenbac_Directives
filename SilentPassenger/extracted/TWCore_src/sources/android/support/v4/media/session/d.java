package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class d implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public PlaybackStateCompat createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public PlaybackStateCompat[] newArray(int i) {
        return new PlaybackStateCompat[i];
    }
}

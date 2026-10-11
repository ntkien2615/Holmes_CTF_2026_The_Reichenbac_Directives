package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class r implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public MediaMetadataCompat createFromParcel(Parcel parcel) {
        return new MediaMetadataCompat(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public MediaMetadataCompat[] newArray(int i) {
        return new MediaMetadataCompat[i];
    }
}

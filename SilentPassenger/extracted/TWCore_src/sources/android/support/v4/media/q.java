package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class q implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public RatingCompat createFromParcel(Parcel parcel) {
        return new RatingCompat(parcel.readInt(), parcel.readFloat());
    }

    @Override // android.os.Parcelable.Creator
    public RatingCompat[] newArray(int i) {
        return new RatingCompat[i];
    }
}

package android.support.v4.media;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class k implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public MediaDescriptionCompat createFromParcel(Parcel parcel) {
        if (Build.VERSION.SDK_INT < 21) {
            return new MediaDescriptionCompat(parcel);
        }
        return MediaDescriptionCompat.sJ(o.sT(parcel));
    }

    @Override // android.os.Parcelable.Creator
    public MediaDescriptionCompat[] newArray(int i) {
        return new MediaDescriptionCompat[i];
    }
}

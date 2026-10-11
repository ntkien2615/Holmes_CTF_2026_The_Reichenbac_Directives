package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class l implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public ParcelableVolumeInfo createFromParcel(Parcel parcel) {
        return new ParcelableVolumeInfo(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public ParcelableVolumeInfo[] newArray(int i) {
        return new ParcelableVolumeInfo[i];
    }
}

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class v implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public MediaSessionCompat$ResultReceiverWrapper createFromParcel(Parcel parcel) {
        return new MediaSessionCompat$ResultReceiverWrapper(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public MediaSessionCompat$ResultReceiverWrapper[] newArray(int i) {
        return new MediaSessionCompat$ResultReceiverWrapper[i];
    }
}

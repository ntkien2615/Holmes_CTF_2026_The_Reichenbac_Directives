package android.support.v4.media.session;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class p implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public MediaSessionCompat$Token createFromParcel(Parcel parcel) {
        Object readStrongBinder;
        if (Build.VERSION.SDK_INT >= 21) {
            readStrongBinder = parcel.readParcelable(null);
        } else {
            readStrongBinder = parcel.readStrongBinder();
        }
        return new MediaSessionCompat$Token(readStrongBinder);
    }

    @Override // android.os.Parcelable.Creator
    public MediaSessionCompat$Token[] newArray(int i) {
        return new MediaSessionCompat$Token[i];
    }
}

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.PlaybackStateCompat;

/* loaded from: classes.dex */
final class m implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public PlaybackStateCompat.CustomAction createFromParcel(Parcel parcel) {
        return new PlaybackStateCompat.CustomAction(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public PlaybackStateCompat.CustomAction[] newArray(int i) {
        return new PlaybackStateCompat.CustomAction[i];
    }
}

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class r implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public BackStackState createFromParcel(Parcel parcel) {
        return new BackStackState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public BackStackState[] newArray(int i) {
        return new BackStackState[i];
    }
}

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class m implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public FragmentState createFromParcel(Parcel parcel) {
        return new FragmentState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public FragmentState[] newArray(int i) {
        return new FragmentState[i];
    }
}

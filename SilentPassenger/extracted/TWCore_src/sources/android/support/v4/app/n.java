package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class n implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public FragmentManagerState createFromParcel(Parcel parcel) {
        return new FragmentManagerState(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public FragmentManagerState[] newArray(int i) {
        return new FragmentManagerState[i];
    }
}

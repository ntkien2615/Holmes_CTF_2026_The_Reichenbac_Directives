package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class b implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public AbsSavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        if (parcel.readParcelable(classLoader) != null) {
            throw new IllegalStateException("superState must be null");
        }
        return AbsSavedState.oA;
    }

    @Override // android.os.Parcelable.Creator
    public AbsSavedState createFromParcel(Parcel parcel) {
        return createFromParcel(parcel, (ClassLoader) null);
    }

    @Override // android.os.Parcelable.Creator
    public AbsSavedState[] newArray(int i) {
        return new AbsSavedState[i];
    }
}

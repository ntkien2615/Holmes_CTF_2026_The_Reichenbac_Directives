package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class d implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public DrawerLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new DrawerLayout$SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public DrawerLayout$SavedState createFromParcel(Parcel parcel) {
        return new DrawerLayout$SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public DrawerLayout$SavedState[] newArray(int i) {
        return new DrawerLayout$SavedState[i];
    }
}

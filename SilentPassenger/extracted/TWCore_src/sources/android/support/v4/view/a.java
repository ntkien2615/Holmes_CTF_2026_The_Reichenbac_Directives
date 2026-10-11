package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class a implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public ViewPager$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new ViewPager$SavedState(parcel, classLoader);
    }

    @Override // android.os.Parcelable.Creator
    public ViewPager$SavedState createFromParcel(Parcel parcel) {
        return new ViewPager$SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public ViewPager$SavedState[] newArray(int i) {
        return new ViewPager$SavedState[i];
    }
}

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class b implements Parcelable.ClassLoaderCreator {
    @Override // android.os.Parcelable.ClassLoaderCreator
    public SlidingPaneLayout$SavedState createFromParcel(Parcel parcel, ClassLoader classLoader) {
        return new SlidingPaneLayout$SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public SlidingPaneLayout$SavedState createFromParcel(Parcel parcel) {
        return new SlidingPaneLayout$SavedState(parcel, null);
    }

    @Override // android.os.Parcelable.Creator
    public SlidingPaneLayout$SavedState[] newArray(int i) {
        return new SlidingPaneLayout$SavedState[i];
    }
}

package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class SlidingPaneLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new b();
    boolean mK;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SlidingPaneLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.mK = parcel.readInt() != 0;
    }

    @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mK ? 1 : 0);
    }
}

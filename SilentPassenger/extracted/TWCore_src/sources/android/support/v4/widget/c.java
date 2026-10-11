package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* loaded from: classes.dex */
final class c implements Parcelable.Creator {
    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v4.widget.NestedScrollView$SavedState] */
    @Override // android.os.Parcelable.Creator
    public NestedScrollView$SavedState createFromParcel(final Parcel parcel) {
        return new View.BaseSavedState(parcel) { // from class: android.support.v4.widget.NestedScrollView$SavedState
            public static final Parcelable.Creator CREATOR = new c();
            public int mL;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(parcel);
                this.mL = parcel.readInt();
            }

            @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                super.writeToParcel(parcel2, i);
                parcel2.writeInt(this.mL);
            }

            public String toString() {
                return "HorizontalScrollView.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " scrollPosition=" + this.mL + "}";
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public NestedScrollView$SavedState[] newArray(int i) {
        return new NestedScrollView$SavedState[i];
    }
}

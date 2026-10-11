package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

/* loaded from: classes.dex */
final class k implements Parcelable.Creator {
    /* JADX WARN: Type inference failed for: r0v0, types: [android.support.v4.app.FragmentTabHost$SavedState] */
    @Override // android.os.Parcelable.Creator
    public FragmentTabHost$SavedState createFromParcel(final Parcel parcel) {
        return new View.BaseSavedState(parcel) { // from class: android.support.v4.app.FragmentTabHost$SavedState
            public static final Parcelable.Creator CREATOR = new k();
            String mb;

            /* JADX INFO: Access modifiers changed from: package-private */
            {
                super(parcel);
                this.mb = parcel.readString();
            }

            @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
            public void writeToParcel(Parcel parcel2, int i) {
                super.writeToParcel(parcel2, i);
                parcel2.writeString(this.mb);
            }

            public String toString() {
                return "FragmentTabHost.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " curTab=" + this.mb + "}";
            }
        };
    }

    @Override // android.os.Parcelable.Creator
    public FragmentTabHost$SavedState[] newArray(int i) {
        return new FragmentTabHost$SavedState[i];
    }
}

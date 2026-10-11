package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public abstract class AbsSavedState implements Parcelable {
    private final Parcelable oz;
    public static final AbsSavedState oA = new AbsSavedState() { // from class: android.support.v4.view.AbsSavedState.1
    };
    public static final Parcelable.Creator CREATOR = new b();

    /* synthetic */ AbsSavedState(AbsSavedState absSavedState) {
        this();
    }

    private AbsSavedState() {
        this.oz = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbsSavedState(Parcel parcel, ClassLoader classLoader) {
        Parcelable readParcelable = parcel.readParcelable(classLoader);
        this.oz = readParcelable == null ? oA : readParcelable;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.oz, i);
    }
}

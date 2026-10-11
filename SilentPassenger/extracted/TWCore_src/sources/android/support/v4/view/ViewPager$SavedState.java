package android.support.v4.view;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ViewPager$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new a();
    ClassLoader oB;
    int oC;
    Parcelable oD;

    @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.oC);
        parcel.writeParcelable(this.oD, i);
    }

    public String toString() {
        return "FragmentPager.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.oC + "}";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ViewPager$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        classLoader = classLoader == null ? getClass().getClassLoader() : classLoader;
        this.oC = parcel.readInt();
        this.oD = parcel.readParcelable(classLoader);
        this.oB = classLoader;
    }
}

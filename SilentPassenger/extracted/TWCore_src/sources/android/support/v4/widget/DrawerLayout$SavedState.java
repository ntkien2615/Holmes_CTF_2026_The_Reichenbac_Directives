package android.support.v4.widget;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.AbsSavedState;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class DrawerLayout$SavedState extends AbsSavedState {
    public static final Parcelable.Creator CREATOR = new d();
    int mF;
    int mG;
    int mH;
    int mI;
    int mJ;

    public DrawerLayout$SavedState(Parcel parcel, ClassLoader classLoader) {
        super(parcel, classLoader);
        this.mJ = 0;
        this.mJ = parcel.readInt();
        this.mG = parcel.readInt();
        this.mH = parcel.readInt();
        this.mI = parcel.readInt();
        this.mF = parcel.readInt();
    }

    @Override // android.support.v4.view.AbsSavedState, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(this.mJ);
        parcel.writeInt(this.mG);
        parcel.writeInt(this.mH);
        parcel.writeInt(this.mI);
        parcel.writeInt(this.mF);
    }
}

package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class FragmentManagerState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new n();
    int[] lW;
    BackStackState[] lX;
    int lY;
    FragmentState[] lZ;
    int ma;

    public FragmentManagerState() {
        this.lY = -1;
    }

    public FragmentManagerState(Parcel parcel) {
        this.lY = -1;
        this.lZ = (FragmentState[]) parcel.createTypedArray(FragmentState.CREATOR);
        this.lW = parcel.createIntArray();
        this.lX = (BackStackState[]) parcel.createTypedArray(BackStackState.CREATOR);
        this.lY = parcel.readInt();
        this.ma = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedArray(this.lZ, i);
        parcel.writeIntArray(this.lW);
        parcel.writeTypedArray(this.lX, i);
        parcel.writeInt(this.lY);
        parcel.writeInt(this.ma);
    }
}

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ParcelableVolumeInfo implements Parcelable {
    public static final Parcelable.Creator CREATOR = new l();
    public int nH;
    public int nI;
    public int nJ;
    public int nK;
    public int nL;

    public ParcelableVolumeInfo(Parcel parcel) {
        this.nH = parcel.readInt();
        this.nK = parcel.readInt();
        this.nL = parcel.readInt();
        this.nI = parcel.readInt();
        this.nJ = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.nH);
        parcel.writeInt(this.nK);
        parcel.writeInt(this.nL);
        parcel.writeInt(this.nI);
        parcel.writeInt(this.nJ);
    }
}

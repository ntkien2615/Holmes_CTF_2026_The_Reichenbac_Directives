package android.support.v4.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class FragmentState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new m();
    Bundle mi;
    final boolean mj;
    final Bundle mk;
    final int ml;
    final String mm;
    final int mn;
    final String mo;
    final boolean mp;
    final boolean mq;
    final int mr;
    final boolean ms;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FragmentState(Parcel parcel) {
        this.mo = parcel.readString();
        this.mn = parcel.readInt();
        this.mp = parcel.readInt() != 0;
        this.ml = parcel.readInt();
        this.mr = parcel.readInt();
        this.mm = parcel.readString();
        this.mq = parcel.readInt() != 0;
        this.mj = parcel.readInt() != 0;
        this.mk = parcel.readBundle();
        this.ms = parcel.readInt() != 0;
        this.mi = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mo);
        parcel.writeInt(this.mn);
        parcel.writeInt(this.mp ? 1 : 0);
        parcel.writeInt(this.ml);
        parcel.writeInt(this.mr);
        parcel.writeString(this.mm);
        parcel.writeInt(this.mq ? 1 : 0);
        parcel.writeInt(this.mj ? 1 : 0);
        parcel.writeBundle(this.mk);
        parcel.writeInt(this.ms ? 1 : 0);
        parcel.writeBundle(this.mi);
    }
}

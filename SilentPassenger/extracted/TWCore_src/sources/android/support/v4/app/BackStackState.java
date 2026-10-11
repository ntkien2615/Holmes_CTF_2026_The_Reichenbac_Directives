package android.support.v4.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;

/* loaded from: classes.dex */
final class BackStackState implements Parcelable {
    public static final Parcelable.Creator CREATOR = new r();
    final boolean mA;
    final ArrayList mB;
    final ArrayList mC;
    final int mD;
    final String mName;
    final CharSequence mt;
    final int[] mu;
    final int mv;
    final int mw;
    final int mx;
    final int my;
    final CharSequence mz;

    public BackStackState(Parcel parcel) {
        this.mu = parcel.createIntArray();
        this.mw = parcel.readInt();
        this.mv = parcel.readInt();
        this.mName = parcel.readString();
        this.mD = parcel.readInt();
        this.my = parcel.readInt();
        this.mz = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mx = parcel.readInt();
        this.mt = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mB = parcel.createStringArrayList();
        this.mC = parcel.createStringArrayList();
        this.mA = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeIntArray(this.mu);
        parcel.writeInt(this.mw);
        parcel.writeInt(this.mv);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mD);
        parcel.writeInt(this.my);
        TextUtils.writeToParcel(this.mz, parcel, 0);
        parcel.writeInt(this.mx);
        TextUtils.writeToParcel(this.mt, parcel, 0);
        parcel.writeStringList(this.mB);
        parcel.writeStringList(this.mC);
        parcel.writeInt(this.mA ? 1 : 0);
    }
}

package cn.cardoor.user.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
final class a implements Parcelable.Creator {
    @Override // android.os.Parcelable.Creator
    public Token createFromParcel(Parcel parcel) {
        return new Token(parcel);
    }

    @Override // android.os.Parcelable.Creator
    public Token[] newArray(int i) {
        return new Token[i];
    }
}

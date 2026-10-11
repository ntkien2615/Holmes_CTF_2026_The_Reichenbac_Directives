package com.tw.bt;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class ContactCoordinateHelper implements Parcelable, Cloneable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { // from class: com.tw.bt.ContactCoordinateHelper.1
        @Override // android.os.Parcelable.Creator
        public ContactCoordinateHelper createFromParcel(Parcel parcel) {
            return new ContactCoordinateHelper(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ContactCoordinateHelper[] newArray(int i) {
            return new ContactCoordinateHelper[i];
        }
    };
    public String mName;
    public String mNumber;

    public ContactCoordinateHelper(String str, String str2) {
        this.mName = str;
        this.mNumber = str2;
    }

    protected ContactCoordinateHelper(Parcel parcel) {
        this.mName = parcel.readString();
        this.mNumber = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mNumber);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ContactCoordinateHelper) {
            return this.mNumber.equals(((ContactCoordinateHelper) obj).mNumber);
        }
        return false;
    }

    public int hashCode() {
        return this.mNumber.hashCode();
    }
}

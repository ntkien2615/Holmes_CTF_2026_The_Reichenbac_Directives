package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class RatingCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new q();
    private final int os;
    private final float ot;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RatingCompat(int i, float f) {
        this.os = i;
        this.ot = f;
    }

    public String toString() {
        return "Rating:style=" + this.os + " rating=" + (this.ot < 0.0f ? "unrated" : String.valueOf(this.ot));
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return this.os;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.os);
        parcel.writeFloat(this.ot);
    }
}

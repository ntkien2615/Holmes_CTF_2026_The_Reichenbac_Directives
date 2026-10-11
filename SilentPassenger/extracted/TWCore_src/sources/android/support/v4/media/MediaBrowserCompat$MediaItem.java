package android.support.v4.media;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class MediaBrowserCompat$MediaItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new b();
    private final int op;
    private final MediaDescriptionCompat oq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaBrowserCompat$MediaItem(Parcel parcel) {
        this.op = parcel.readInt();
        this.oq = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.op);
        this.oq.writeToParcel(parcel, i);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("MediaItem{");
        sb.append("mFlags=").append(this.op);
        sb.append(", mDescription=").append(this.oq);
        sb.append('}');
        return sb.toString();
    }
}

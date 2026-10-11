package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;

/* loaded from: classes.dex */
final class MediaSessionCompat$ResultReceiverWrapper implements Parcelable {
    public static final Parcelable.Creator CREATOR = new v();
    private ResultReceiver nv;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSessionCompat$ResultReceiverWrapper(Parcel parcel) {
        this.nv = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.nv.writeToParcel(parcel, i);
    }
}

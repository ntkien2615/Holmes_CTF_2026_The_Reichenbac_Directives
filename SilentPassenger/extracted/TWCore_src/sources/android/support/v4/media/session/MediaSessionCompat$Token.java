package android.support.v4.media.session;

import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class MediaSessionCompat$Token implements Parcelable {
    public static final Parcelable.Creator CREATOR = new p();
    private final i np;
    private final Object nq;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSessionCompat$Token(Object obj) {
        this(obj, null);
    }

    MediaSessionCompat$Token(Object obj, i iVar) {
        this.nq = obj;
        this.np = iVar;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            parcel.writeParcelable((Parcelable) this.nq, i);
        } else {
            parcel.writeStrongBinder((IBinder) this.nq);
        }
    }

    public int hashCode() {
        if (this.nq == null) {
            return 0;
        }
        return this.nq.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSessionCompat$Token)) {
            return false;
        }
        MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token) obj;
        if (this.nq == null) {
            return mediaSessionCompat$Token.nq == null;
        }
        if (mediaSessionCompat$Token.nq == null) {
            return false;
        }
        return this.nq.equals(mediaSessionCompat$Token.nq);
    }
}

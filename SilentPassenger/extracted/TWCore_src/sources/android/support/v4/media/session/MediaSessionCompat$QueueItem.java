package android.support.v4.media.session;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.MediaDescriptionCompat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class MediaSessionCompat$QueueItem implements Parcelable {
    public static final Parcelable.Creator CREATOR = new o();
    private Object mT;
    private final MediaDescriptionCompat mU;
    private final long mV;

    private MediaSessionCompat$QueueItem(Object obj, MediaDescriptionCompat mediaDescriptionCompat, long j) {
        if (mediaDescriptionCompat == null) {
            throw new IllegalArgumentException("Description cannot be null.");
        }
        if (j == -1) {
            throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
        }
        this.mU = mediaDescriptionCompat;
        this.mV = j;
        this.mT = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaSessionCompat$QueueItem(Parcel parcel) {
        this.mU = (MediaDescriptionCompat) MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
        this.mV = parcel.readLong();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        this.mU.writeToParcel(parcel, i);
        parcel.writeLong(this.mV);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public static MediaSessionCompat$QueueItem qs(Object obj) {
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat$QueueItem(obj, MediaDescriptionCompat.sJ(f.qP(obj)), f.qQ(obj));
    }

    public static List qr(List list) {
        if (list == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(qs(it.next()));
        }
        return arrayList;
    }

    public String toString() {
        return "MediaSession.QueueItem {Description=" + this.mU + ", Id=" + this.mV + " }";
    }
}

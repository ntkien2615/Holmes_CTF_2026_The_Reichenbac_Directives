package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new d();
    final long nd;
    final long ne;
    final long nf;
    final Bundle ng;
    List nh;
    final long ni;
    final int nj;
    private Object nk;
    final CharSequence nl;
    final int nm;
    final long nn;
    final float no;

    PlaybackStateCompat(int i, long j, long j2, float f, long j3, int i2, CharSequence charSequence, long j4, List list, long j5, Bundle bundle) {
        this.nm = i;
        this.nn = j;
        this.ne = j2;
        this.no = f;
        this.nd = j3;
        this.nj = i2;
        this.nl = charSequence;
        this.nf = j4;
        this.nh = new ArrayList(list);
        this.ni = j5;
        this.ng = bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PlaybackStateCompat(Parcel parcel) {
        this.nm = parcel.readInt();
        this.nn = parcel.readLong();
        this.no = parcel.readFloat();
        this.nf = parcel.readLong();
        this.ne = parcel.readLong();
        this.nd = parcel.readLong();
        this.nl = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.nh = parcel.createTypedArrayList(CustomAction.CREATOR);
        this.ni = parcel.readLong();
        this.ng = parcel.readBundle();
        this.nj = parcel.readInt();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("PlaybackState {");
        sb.append("state=").append(this.nm);
        sb.append(", position=").append(this.nn);
        sb.append(", buffered position=").append(this.ne);
        sb.append(", speed=").append(this.no);
        sb.append(", updated=").append(this.nf);
        sb.append(", actions=").append(this.nd);
        sb.append(", error code=").append(this.nj);
        sb.append(", error message=").append(this.nl);
        sb.append(", custom actions=").append(this.nh);
        sb.append(", active item id=").append(this.ni);
        sb.append("}");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.nm);
        parcel.writeLong(this.nn);
        parcel.writeFloat(this.no);
        parcel.writeLong(this.nf);
        parcel.writeLong(this.ne);
        parcel.writeLong(this.nd);
        TextUtils.writeToParcel(this.nl, parcel, i);
        parcel.writeTypedList(this.nh);
        parcel.writeLong(this.ni);
        parcel.writeBundle(this.ng);
        parcel.writeInt(this.nj);
    }

    public static PlaybackStateCompat rV(Object obj) {
        Bundle bundle;
        if (obj != null && Build.VERSION.SDK_INT >= 21) {
            List qT = g.qT(obj);
            ArrayList arrayList = null;
            if (qT != null) {
                arrayList = new ArrayList(qT.size());
                Iterator it = qT.iterator();
                while (it.hasNext()) {
                    arrayList.add(CustomAction.sh(it.next()));
                }
            }
            if (Build.VERSION.SDK_INT >= 22) {
                bundle = k.rW(obj);
            } else {
                bundle = null;
            }
            PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(g.qZ(obj), g.qR(obj), g.qU(obj), g.qS(obj), g.qY(obj), 0, g.qX(obj), g.qV(obj), arrayList, g.qW(obj), bundle);
            playbackStateCompat.nk = obj;
            return playbackStateCompat;
        }
        return null;
    }

    /* loaded from: classes.dex */
    public final class CustomAction implements Parcelable {
        public static final Parcelable.Creator CREATOR = new m();
        private final Bundle nA;
        private final String nw;
        private final int nx;
        private final CharSequence ny;
        private Object nz;

        CustomAction(String str, CharSequence charSequence, int i, Bundle bundle) {
            this.nw = str;
            this.ny = charSequence;
            this.nx = i;
            this.nA = bundle;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public CustomAction(Parcel parcel) {
            this.nw = parcel.readString();
            this.ny = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
            this.nx = parcel.readInt();
            this.nA = parcel.readBundle();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.nw);
            TextUtils.writeToParcel(this.ny, parcel, i);
            parcel.writeInt(this.nx);
            parcel.writeBundle(this.nA);
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public static CustomAction sh(Object obj) {
            if (obj == null || Build.VERSION.SDK_INT < 21) {
                return null;
            }
            CustomAction customAction = new CustomAction(C0000a.qv(obj), C0000a.qw(obj), C0000a.qu(obj), C0000a.qt(obj));
            customAction.nz = obj;
            return customAction;
        }

        public String toString() {
            return "Action:mName='" + this.ny + ", mIcon=" + this.nx + ", mExtras=" + this.nA;
        }
    }
}

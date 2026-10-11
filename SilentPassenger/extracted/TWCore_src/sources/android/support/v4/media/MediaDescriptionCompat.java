package android.support.v4.media;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/* loaded from: classes.dex */
public final class MediaDescriptionCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR = new k();
    private Object oa;
    private final CharSequence ob;
    private final Bundle oc;
    private final CharSequence od;
    private final CharSequence oe;
    private final String of;
    private final Uri og;
    private final Uri oh;
    private final Bitmap oi;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaDescriptionCompat(String str, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, Bitmap bitmap, Uri uri, Bundle bundle, Uri uri2) {
        this.of = str;
        this.od = charSequence;
        this.ob = charSequence2;
        this.oe = charSequence3;
        this.oi = bitmap;
        this.oh = uri;
        this.oc = bundle;
        this.og = uri2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaDescriptionCompat(Parcel parcel) {
        this.of = parcel.readString();
        this.od = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.ob = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.oe = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.oi = (Bitmap) parcel.readParcelable(null);
        this.oh = (Uri) parcel.readParcelable(null);
        this.oc = parcel.readBundle();
        this.og = (Uri) parcel.readParcelable(null);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (Build.VERSION.SDK_INT < 21) {
            parcel.writeString(this.of);
            TextUtils.writeToParcel(this.od, parcel, i);
            TextUtils.writeToParcel(this.ob, parcel, i);
            TextUtils.writeToParcel(this.oe, parcel, i);
            parcel.writeParcelable(this.oi, i);
            parcel.writeParcelable(this.oh, i);
            parcel.writeBundle(this.oc);
            parcel.writeParcelable(this.og, i);
            return;
        }
        o.sS(sK(), parcel, i);
    }

    public String toString() {
        return this.od + ", " + this.ob + ", " + this.oe;
    }

    public Object sK() {
        if (this.oa != null || Build.VERSION.SDK_INT < 21) {
            return this.oa;
        }
        Object ss = h.ss();
        h.sm(ss, this.of);
        h.st(ss, this.od);
        h.su(ss, this.ob);
        h.sn(ss, this.oe);
        h.sp(ss, this.oi);
        h.sq(ss, this.oh);
        Bundle bundle = this.oc;
        if (Build.VERSION.SDK_INT < 23 && this.og != null) {
            if (bundle == null) {
                bundle = new Bundle();
                bundle.putBoolean("android.support.v4.media.description.NULL_BUNDLE_FLAG", true);
            }
            bundle.putParcelable("android.support.v4.media.description.MEDIA_URI", this.og);
        }
        h.so(ss, bundle);
        if (Build.VERSION.SDK_INT >= 23) {
            n.sI(ss, this.og);
        }
        this.oa = h.sr(ss);
        return this.oa;
    }

    public static MediaDescriptionCompat sJ(Object obj) {
        Bundle bundle;
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        m mVar = new m();
        mVar.sH(o.sU(obj));
        mVar.sE(o.sM(obj));
        mVar.sB(o.sO(obj));
        mVar.sD(o.sR(obj));
        mVar.sz(o.sP(obj));
        mVar.sG(o.sQ(obj));
        Bundle sN = o.sN(obj);
        Uri uri = sN == null ? null : (Uri) sN.getParcelable("android.support.v4.media.description.MEDIA_URI");
        if (uri == null) {
            bundle = sN;
        } else if (sN.containsKey("android.support.v4.media.description.NULL_BUNDLE_FLAG") && sN.size() == 2) {
            bundle = null;
        } else {
            sN.remove("android.support.v4.media.description.MEDIA_URI");
            sN.remove("android.support.v4.media.description.NULL_BUNDLE_FLAG");
            bundle = sN;
        }
        mVar.sC(bundle);
        if (uri != null) {
            mVar.sA(uri);
        } else if (Build.VERSION.SDK_INT >= 23) {
            mVar.sA(s.sW(obj));
        }
        MediaDescriptionCompat sF = mVar.sF();
        sF.oa = obj;
        return sF;
    }
}

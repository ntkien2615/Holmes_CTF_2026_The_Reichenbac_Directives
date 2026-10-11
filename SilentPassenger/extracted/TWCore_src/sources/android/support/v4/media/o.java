package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;

/* loaded from: classes.dex */
class o {
    public static String sU(Object obj) {
        return ((MediaDescription) obj).getMediaId();
    }

    public static CharSequence sM(Object obj) {
        return ((MediaDescription) obj).getTitle();
    }

    public static CharSequence sO(Object obj) {
        return ((MediaDescription) obj).getSubtitle();
    }

    public static CharSequence sR(Object obj) {
        return ((MediaDescription) obj).getDescription();
    }

    public static Bitmap sP(Object obj) {
        return ((MediaDescription) obj).getIconBitmap();
    }

    public static Uri sQ(Object obj) {
        return ((MediaDescription) obj).getIconUri();
    }

    public static Bundle sN(Object obj) {
        return ((MediaDescription) obj).getExtras();
    }

    public static void sS(Object obj, Parcel parcel, int i) {
        ((MediaDescription) obj).writeToParcel(parcel, i);
    }

    public static Object sT(Parcel parcel) {
        return MediaDescription.CREATOR.createFromParcel(parcel);
    }
}

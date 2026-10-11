package android.support.v4.media;

import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public final class MediaMetadataCompat implements Parcelable {
    public static final Parcelable.Creator CREATOR;
    private static final String[] ok;
    static final android.support.v4.g.j ol = new android.support.v4.g.j();
    private static final String[] om;
    private static final String[] on;
    final Bundle oj;
    private Object oo;

    static {
        ol.put("android.media.metadata.TITLE", 1);
        ol.put("android.media.metadata.ARTIST", 1);
        ol.put("android.media.metadata.DURATION", 0);
        ol.put("android.media.metadata.ALBUM", 1);
        ol.put("android.media.metadata.AUTHOR", 1);
        ol.put("android.media.metadata.WRITER", 1);
        ol.put("android.media.metadata.COMPOSER", 1);
        ol.put("android.media.metadata.COMPILATION", 1);
        ol.put("android.media.metadata.DATE", 1);
        ol.put("android.media.metadata.YEAR", 0);
        ol.put("android.media.metadata.GENRE", 1);
        ol.put("android.media.metadata.TRACK_NUMBER", 0);
        ol.put("android.media.metadata.NUM_TRACKS", 0);
        ol.put("android.media.metadata.DISC_NUMBER", 0);
        ol.put("android.media.metadata.ALBUM_ARTIST", 1);
        ol.put("android.media.metadata.ART", 2);
        ol.put("android.media.metadata.ART_URI", 1);
        ol.put("android.media.metadata.ALBUM_ART", 2);
        ol.put("android.media.metadata.ALBUM_ART_URI", 1);
        ol.put("android.media.metadata.USER_RATING", 3);
        ol.put("android.media.metadata.RATING", 3);
        ol.put("android.media.metadata.DISPLAY_TITLE", 1);
        ol.put("android.media.metadata.DISPLAY_SUBTITLE", 1);
        ol.put("android.media.metadata.DISPLAY_DESCRIPTION", 1);
        ol.put("android.media.metadata.DISPLAY_ICON", 2);
        ol.put("android.media.metadata.DISPLAY_ICON_URI", 1);
        ol.put("android.media.metadata.MEDIA_ID", 1);
        ol.put("android.media.metadata.BT_FOLDER_TYPE", 0);
        ol.put("android.media.metadata.MEDIA_URI", 1);
        ol.put("android.media.metadata.ADVERTISEMENT", 0);
        ol.put("android.media.metadata.DOWNLOAD_STATUS", 0);
        ok = new String[]{"android.media.metadata.TITLE", "android.media.metadata.ARTIST", "android.media.metadata.ALBUM", "android.media.metadata.ALBUM_ARTIST", "android.media.metadata.WRITER", "android.media.metadata.AUTHOR", "android.media.metadata.COMPOSER"};
        om = new String[]{"android.media.metadata.DISPLAY_ICON", "android.media.metadata.ART", "android.media.metadata.ALBUM_ART"};
        on = new String[]{"android.media.metadata.DISPLAY_ICON_URI", "android.media.metadata.ART_URI", "android.media.metadata.ALBUM_ART_URI"};
        CREATOR = new r();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MediaMetadataCompat(Parcel parcel) {
        this.oj = parcel.readBundle();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeBundle(this.oj);
    }

    public static MediaMetadataCompat sL(Object obj) {
        if (obj == null || Build.VERSION.SDK_INT < 21) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        e.sl(obj, obtain, 0);
        obtain.setDataPosition(0);
        MediaMetadataCompat mediaMetadataCompat = (MediaMetadataCompat) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        mediaMetadataCompat.oo = obj;
        return mediaMetadataCompat;
    }
}

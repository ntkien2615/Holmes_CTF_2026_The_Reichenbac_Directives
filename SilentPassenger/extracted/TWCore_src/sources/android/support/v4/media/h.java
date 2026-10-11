package android.support.v4.media;

import android.graphics.Bitmap;
import android.media.MediaDescription;
import android.net.Uri;
import android.os.Bundle;

/* loaded from: classes.dex */
class h {
    public static Object ss() {
        return new MediaDescription.Builder();
    }

    public static void sm(Object obj, String str) {
        ((MediaDescription.Builder) obj).setMediaId(str);
    }

    public static void st(Object obj, CharSequence charSequence) {
        ((MediaDescription.Builder) obj).setTitle(charSequence);
    }

    public static void su(Object obj, CharSequence charSequence) {
        ((MediaDescription.Builder) obj).setSubtitle(charSequence);
    }

    public static void sn(Object obj, CharSequence charSequence) {
        ((MediaDescription.Builder) obj).setDescription(charSequence);
    }

    public static void sp(Object obj, Bitmap bitmap) {
        ((MediaDescription.Builder) obj).setIconBitmap(bitmap);
    }

    public static void sq(Object obj, Uri uri) {
        ((MediaDescription.Builder) obj).setIconUri(uri);
    }

    public static void so(Object obj, Bundle bundle) {
        ((MediaDescription.Builder) obj).setExtras(bundle);
    }

    public static Object sr(Object obj) {
        return ((MediaDescription.Builder) obj).build();
    }
}

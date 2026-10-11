package android.support.v4.media.session;

import android.media.session.PlaybackState;
import android.os.Bundle;

/* renamed from: android.support.v4.media.session.a, reason: case insensitive filesystem */
/* loaded from: classes.dex */
final class C0000a {
    C0000a() {
    }

    public static String qv(Object obj) {
        return ((PlaybackState.CustomAction) obj).getAction();
    }

    public static CharSequence qw(Object obj) {
        return ((PlaybackState.CustomAction) obj).getName();
    }

    public static int qu(Object obj) {
        return ((PlaybackState.CustomAction) obj).getIcon();
    }

    public static Bundle qt(Object obj) {
        return ((PlaybackState.CustomAction) obj).getExtras();
    }
}

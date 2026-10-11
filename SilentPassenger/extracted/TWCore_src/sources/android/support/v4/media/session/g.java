package android.support.v4.media.session;

import android.media.session.PlaybackState;
import java.util.List;

/* loaded from: classes.dex */
class g {
    g() {
    }

    public static int qZ(Object obj) {
        return ((PlaybackState) obj).getState();
    }

    public static long qR(Object obj) {
        return ((PlaybackState) obj).getPosition();
    }

    public static long qU(Object obj) {
        return ((PlaybackState) obj).getBufferedPosition();
    }

    public static float qS(Object obj) {
        return ((PlaybackState) obj).getPlaybackSpeed();
    }

    public static long qY(Object obj) {
        return ((PlaybackState) obj).getActions();
    }

    public static CharSequence qX(Object obj) {
        return ((PlaybackState) obj).getErrorMessage();
    }

    public static long qV(Object obj) {
        return ((PlaybackState) obj).getLastPositionUpdateTime();
    }

    public static List qT(Object obj) {
        return ((PlaybackState) obj).getCustomActions();
    }

    public static long qW(Object obj) {
        return ((PlaybackState) obj).getActiveQueueItemId();
    }
}

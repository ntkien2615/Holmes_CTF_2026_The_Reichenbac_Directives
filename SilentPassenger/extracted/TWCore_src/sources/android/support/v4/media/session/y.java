package android.support.v4.media.session;

import android.os.Bundle;
import android.os.IInterface;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

/* loaded from: classes.dex */
public interface y extends IInterface {
    void onExtrasChanged(Bundle bundle);

    void onQueueChanged(List list);

    void onQueueTitleChanged(CharSequence charSequence);

    void onSessionDestroyed();

    void qA(ParcelableVolumeInfo parcelableVolumeInfo);

    void qB(int i);

    void qC();

    void qD(PlaybackStateCompat playbackStateCompat);

    void qE(MediaMetadataCompat mediaMetadataCompat);

    void qF(int i);

    void qx(String str, Bundle bundle);

    void qy(boolean z);

    void qz(boolean z);
}

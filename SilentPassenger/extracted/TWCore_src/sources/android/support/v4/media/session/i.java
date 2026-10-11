package android.support.v4.media.session;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IInterface;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.RatingCompat;
import android.view.KeyEvent;
import java.util.List;

/* loaded from: classes.dex */
public interface i extends IInterface {
    void next();

    void rA(int i, int i2, String str);

    void rB(y yVar);

    void rC();

    void rD(String str, Bundle bundle);

    CharSequence rE();

    void rF(MediaDescriptionCompat mediaDescriptionCompat, int i);

    void rG();

    boolean rH(KeyEvent keyEvent);

    boolean rI();

    void rJ(RatingCompat ratingCompat, Bundle bundle);

    void rK(int i);

    void rL(String str, Bundle bundle, MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper);

    Bundle rM();

    int rN();

    int rO();

    void rP();

    void rQ(long j);

    int rR();

    void rS(Uri uri, Bundle bundle);

    boolean rT();

    void rU(y yVar);

    void ra(String str, Bundle bundle);

    String rb();

    boolean rc();

    void rd(boolean z);

    void re(MediaDescriptionCompat mediaDescriptionCompat);

    void rf(int i, int i2, String str);

    MediaMetadataCompat rg();

    void rh(String str, Bundle bundle);

    void ri(MediaDescriptionCompat mediaDescriptionCompat);

    String rj();

    void rk();

    void rl(boolean z);

    void rm();

    void rn(int i);

    PendingIntent ro();

    void rp(Uri uri, Bundle bundle);

    void rq(String str, Bundle bundle);

    void rr();

    void rs(int i);

    void rt(long j);

    void ru(RatingCompat ratingCompat);

    void rv(String str, Bundle bundle);

    PlaybackStateCompat rw();

    List rx();

    ParcelableVolumeInfo ry();

    long rz();

    void stop();
}

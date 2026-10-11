package android.support.v4.media.session;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;

/* loaded from: classes.dex */
public abstract class c implements IBinder.DeathRecipient {
    j mX;
    private final Object mY;
    boolean mZ;

    public c() {
        if (Build.VERSION.SDK_INT >= 21) {
            this.mY = t.sg(new q(this));
        } else {
            this.mY = new b(this);
        }
    }

    public void qI() {
    }

    public void onSessionDestroyed() {
    }

    public void onSessionEvent(String str, Bundle bundle) {
    }

    public void qM(PlaybackStateCompat playbackStateCompat) {
    }

    public void qN(MediaMetadataCompat mediaMetadataCompat) {
    }

    public void onQueueChanged(List list) {
    }

    public void onQueueTitleChanged(CharSequence charSequence) {
    }

    public void onExtrasChanged(Bundle bundle) {
    }

    public void qG(z zVar) {
    }

    public void qH(boolean z) {
    }

    public void qK(int i) {
    }

    public void qL(boolean z) {
    }

    public void qJ(int i) {
    }

    @Override // android.os.IBinder.DeathRecipient
    public void binderDied() {
        onSessionDestroyed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void qO(int i, Object obj, Bundle bundle) {
        if (this.mX != null) {
            Message obtainMessage = this.mX.obtainMessage(i, obj);
            obtainMessage.setData(bundle);
            obtainMessage.sendToTarget();
        }
    }
}

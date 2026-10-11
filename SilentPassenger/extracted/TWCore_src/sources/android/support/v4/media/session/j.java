package android.support.v4.media.session;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.media.MediaMetadataCompat;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class j extends Handler {
    final /* synthetic */ c nb;
    boolean nc;

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        if (!this.nc) {
            return;
        }
        switch (message.what) {
            case Logger.SEVERE /* 1 */:
                this.nb.onSessionEvent((String) message.obj, message.getData());
                return;
            case Logger.WARNING /* 2 */:
                this.nb.qM((PlaybackStateCompat) message.obj);
                return;
            case 3:
                this.nb.qN((MediaMetadataCompat) message.obj);
                return;
            case 4:
                this.nb.qG((z) message.obj);
                return;
            case Logger.FINE /* 5 */:
                this.nb.onQueueChanged((List) message.obj);
                return;
            case 6:
                this.nb.onQueueTitleChanged((CharSequence) message.obj);
                return;
            case Logger.FINEST /* 7 */:
                this.nb.onExtrasChanged((Bundle) message.obj);
                return;
            case 8:
                this.nb.onSessionDestroyed();
                return;
            case 9:
                this.nb.qK(((Integer) message.obj).intValue());
                return;
            case MqttConnectOptions.MAX_INFLIGHT_DEFAULT /* 10 */:
                this.nb.qL(((Boolean) message.obj).booleanValue());
                return;
            case 11:
                this.nb.qH(((Boolean) message.obj).booleanValue());
                return;
            case 12:
                this.nb.qJ(((Integer) message.obj).intValue());
                return;
            case 13:
                this.nb.qI();
                return;
            default:
                return;
        }
    }
}

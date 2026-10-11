package android.support.v4.media.session;

import android.media.AudioAttributes;
import android.media.session.MediaController;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public class n {
    public static AudioAttributes rZ(Object obj) {
        return ((MediaController.PlaybackInfo) obj).getAudioAttributes();
    }

    public static int rX(Object obj) {
        return rY(rZ(obj));
    }

    private static int rY(AudioAttributes audioAttributes) {
        if ((audioAttributes.getFlags() & 1) == 1) {
            return 7;
        }
        if ((audioAttributes.getFlags() & 4) == 4) {
            return 6;
        }
        switch (audioAttributes.getUsage()) {
            case Logger.SEVERE /* 1 */:
            case 11:
            case 12:
            case 14:
                return 3;
            case Logger.WARNING /* 2 */:
                return 0;
            case 3:
                return 8;
            case 4:
                return 4;
            case Logger.FINE /* 5 */:
            case Logger.FINEST /* 7 */:
            case 8:
            case 9:
            case MqttConnectOptions.MAX_INFLIGHT_DEFAULT /* 10 */:
                return 5;
            case 6:
                return 2;
            case 13:
                return 1;
            default:
                return 3;
        }
    }
}

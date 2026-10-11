package android.support.v4.media;

import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
class MediaBrowserCompat$CustomActionResultReceiver extends ResultReceiver {
    private final String nM;
    private final j nN;
    private final Bundle nO;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.support.v4.os.ResultReceiver
    public void onReceiveResult(int i, Bundle bundle) {
        if (this.nN == null) {
            return;
        }
        switch (i) {
            case -1:
                this.nN.sw(this.nM, this.nO, bundle);
                return;
            case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                this.nN.sy(this.nM, this.nO, bundle);
                return;
            case Logger.SEVERE /* 1 */:
                this.nN.sx(this.nM, this.nO, bundle);
                return;
            default:
                Log.w("MediaBrowserCompat", "Unknown result code: " + i + " (extras=" + this.nO + ", resultData=" + bundle + ")");
                return;
        }
    }
}

package com.android.ex.editstyledtext;

import android.util.Log;
import java.util.HashMap;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.logging.Logger;

/* loaded from: classes.dex */
public class b {
    private int jP;
    private f jQ;
    private HashMap jR;

    public void nY(int i, Object[] objArr) {
        ob(i).po(objArr);
        this.jP = i;
        nZ(i);
    }

    public void oa(int i) {
        nY(i, null);
    }

    public void nX() {
        nZ(5);
    }

    private h ob(int i) {
        if (this.jR.containsKey(Integer.valueOf(i))) {
            return (h) this.jR.get(Integer.valueOf(i));
        }
        return null;
    }

    public boolean nZ(int i) {
        Log.d("EditModeActions", "--- do the next action: " + i + "," + this.jQ.oN());
        h ob = ob(i);
        if (ob == null) {
            Log.e("EditModeActions", "--- invalid action error.");
            return false;
        }
        switch (this.jQ.oN()) {
            case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                return ob.pn();
            case Logger.SEVERE /* 1 */:
                return ob.pp();
            case Logger.WARNING /* 2 */:
                return ob.pk();
            case 3:
                if (this.jQ.oP()) {
                    return ob.pm();
                }
                return ob.pl();
            default:
                return false;
        }
    }
}

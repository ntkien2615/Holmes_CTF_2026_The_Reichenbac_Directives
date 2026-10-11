package com.tw.core.f;

import android.os.Handler;
import android.os.Looper;
import com.tw.core.utils.C0034h;
import com.tw.core.utils.C0039m;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class g implements MqttCallbackExtended {
    final /* synthetic */ d hL;

    /* JADX INFO: Access modifiers changed from: package-private */
    public g(d dVar) {
        this.hL = dVar;
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallbackExtended
    public void connectComplete(boolean z, String str) {
        List list;
        List list2;
        List list3;
        C0034h.hr("MqttClientManager", "connect success reconnect %s serverURI %s", Boolean.valueOf(z), str);
        try {
            list = this.hL.hF;
            if (list != null) {
                list2 = this.hL.hF;
                if (list2.size() > 0) {
                    list3 = this.hL.hF;
                    Iterator it = list3.iterator();
                    while (it.hasNext()) {
                        ((a) it.next()).bD(z, str);
                    }
                }
            }
            C0039m.hK().hS();
            synchronized (this.hL.hH) {
                if (!this.hL.hH.isEmpty()) {
                    for (Map.Entry entry : this.hL.hH.entrySet()) {
                        this.hL.mg((String[]) entry.getKey(), (int[]) entry.getValue());
                    }
                    this.hL.hH.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void connectionLost(Throwable th) {
        List list;
        List list2;
        List list3;
        th.printStackTrace();
        C0039m.hK().hN();
        C0034h.hs("MqttClientManager", "mqtt connection lost", new Object[0]);
        list = this.hL.hF;
        if (list != null) {
            list2 = this.hL.hF;
            if (list2.size() > 0) {
                list3 = this.hL.hF;
                Iterator it = list3.iterator();
                while (it.hasNext()) {
                    ((a) it.next()).bI(th);
                }
            }
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void messageArrived(String str, MqttMessage mqttMessage) {
        new Handler(Looper.getMainLooper()).post(new h(this, mqttMessage, str));
    }

    @Override // org.eclipse.paho.client.mqttv3.MqttCallback
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        try {
            C0034h.hr("MqttClientManager", "deliveryComplete: %s", iMqttDeliveryToken.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

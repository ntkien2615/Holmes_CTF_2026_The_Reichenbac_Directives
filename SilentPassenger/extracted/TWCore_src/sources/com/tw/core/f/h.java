package com.tw.core.f;

import com.tw.core.utils.C0034h;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/* loaded from: classes.dex */
final class h implements Runnable {
    final /* synthetic */ g hM;
    final /* synthetic */ MqttMessage hN;
    final /* synthetic */ String hO;

    /* JADX INFO: Access modifiers changed from: package-private */
    public h(g gVar, MqttMessage mqttMessage, String str) {
        this.hM = gVar;
        this.hN = mqttMessage;
        this.hO = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.hN != null) {
                String str = new String(this.hN.getPayload());
                C0034h.hr("MqttClientManager", "messageArrived %s msg %s", this.hO, str);
                f.get().ms(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

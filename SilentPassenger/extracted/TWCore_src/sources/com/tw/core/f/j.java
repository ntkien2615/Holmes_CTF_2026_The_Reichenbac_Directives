package com.tw.core.f;

import com.tw.core.utils.C0034h;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class j implements IMqttActionListener {
    final /* synthetic */ d hQ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public j(d dVar) {
        this.hQ = dVar;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken iMqttToken) {
        AtomicInteger atomicInteger;
        C0034h.hr("MqttClientManager", "mqtt connect success %s", iMqttToken);
        this.hQ.hA = false;
        atomicInteger = this.hQ.hz;
        atomicInteger.set(1);
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        C0034h.hs("MqttClientManager", "mqtt connect failure %s", iMqttToken);
        this.hQ.hA = false;
        th.printStackTrace();
    }
}

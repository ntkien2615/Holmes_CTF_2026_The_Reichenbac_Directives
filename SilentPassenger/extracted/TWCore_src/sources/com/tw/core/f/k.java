package com.tw.core.f;

import com.tw.core.utils.C0034h;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class k implements IMqttActionListener {
    final /* synthetic */ d hR;
    final /* synthetic */ e hS;

    /* JADX INFO: Access modifiers changed from: package-private */
    public k(d dVar, e eVar) {
        this.hR = dVar;
        this.hS = eVar;
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onSuccess(IMqttToken iMqttToken) {
        C0034h.hr("MqttClientManager", "onSuccess:%s", iMqttToken);
        if (this.hS != null) {
            this.hS.ex(true);
        }
    }

    @Override // org.eclipse.paho.client.mqttv3.IMqttActionListener
    public void onFailure(IMqttToken iMqttToken, Throwable th) {
        th.printStackTrace();
        C0034h.hs("MqttClientManager", "onFailure:", new Object[0]);
        if (this.hS != null) {
            this.hS.ex(false);
        }
    }
}

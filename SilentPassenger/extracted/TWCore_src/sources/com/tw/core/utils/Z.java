package com.tw.core.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class Z extends Handler {
    /* JADX INFO: Access modifiers changed from: package-private */
    public Z(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        String str;
        String str2;
        try {
            switch (message.what) {
                case MqttConnectOptions.MQTT_VERSION_DEFAULT /* 0 */:
                default:
                    return;
                case 266:
                    if (message.arg1 == 0) {
                        String unused = C0048v.eT = (String) message.obj;
                        str = C0048v.eT;
                        C0034h.hr("McuVersionUtil", "mcu %s", str);
                        str2 = C0048v.eT;
                        C0048v.iB(str2);
                        return;
                    }
                    return;
            }
        } catch (Exception e) {
            C0048v.iB("unknow");
        }
        C0048v.iB("unknow");
    }
}

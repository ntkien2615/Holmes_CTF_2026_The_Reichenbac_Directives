package org.eclipse.paho.client.mqttv3.internal.wire;

/* loaded from: classes.dex */
public class MqttPingResp extends MqttAck {
    public static final String KEY = "Ping";

    public MqttPingResp(byte b, byte[] bArr) {
        super(MqttWireMessage.MESSAGE_TYPE_PINGRESP);
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() {
        return new byte[0];
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public boolean isMessageIdRequired() {
        return false;
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String getKey() {
        return "Ping";
    }
}

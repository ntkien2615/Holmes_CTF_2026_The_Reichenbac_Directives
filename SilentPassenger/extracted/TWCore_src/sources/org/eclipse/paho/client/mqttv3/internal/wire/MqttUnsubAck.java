package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* loaded from: classes.dex */
public class MqttUnsubAck extends MqttAck {
    public MqttUnsubAck(byte b, byte[] bArr) {
        super(MqttWireMessage.MESSAGE_TYPE_UNSUBACK);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() {
        return new byte[0];
    }
}

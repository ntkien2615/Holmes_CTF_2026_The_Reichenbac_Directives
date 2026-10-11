package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* loaded from: classes.dex */
public class MqttPubRec extends MqttAck {
    public MqttPubRec(byte b, byte[] bArr) {
        super((byte) 5);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        dataInputStream.close();
    }

    public MqttPubRec(MqttPublish mqttPublish) {
        super((byte) 5);
        this.msgId = mqttPublish.getMessageId();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() {
        return encodeMessageId();
    }
}

package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* loaded from: classes.dex */
public class MqttSuback extends MqttAck {
    private int[] grantedQos;

    public MqttSuback(byte b, byte[] bArr) {
        super((byte) 9);
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        this.msgId = dataInputStream.readUnsignedShort();
        int i = 0;
        this.grantedQos = new int[bArr.length - 2];
        for (int read = dataInputStream.read(); read != -1; read = dataInputStream.read()) {
            this.grantedQos[i] = read;
            i++;
        }
        dataInputStream.close();
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    protected byte[] getVariableHeader() {
        return new byte[0];
    }

    @Override // org.eclipse.paho.client.mqttv3.internal.wire.MqttAck, org.eclipse.paho.client.mqttv3.internal.wire.MqttWireMessage
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(super.toString()).append(" granted Qos");
        for (int i = 0; i < this.grantedQos.length; i++) {
            stringBuffer.append(" ").append(this.grantedQos[i]);
        }
        return stringBuffer.toString();
    }

    public int[] getGrantedQos() {
        return this.grantedQos;
    }
}

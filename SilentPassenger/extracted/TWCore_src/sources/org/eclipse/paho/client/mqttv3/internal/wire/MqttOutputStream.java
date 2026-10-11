package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes.dex */
public class MqttOutputStream extends OutputStream {
    private static final String CLASS_NAME = MqttOutputStream.class.getName();
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private ClientState clientState;
    private BufferedOutputStream out;

    public MqttOutputStream(ClientState clientState, OutputStream outputStream) {
        this.clientState = null;
        this.clientState = clientState;
        this.out = new BufferedOutputStream(outputStream);
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.out.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        this.out.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr) {
        this.out.write(bArr);
        this.clientState.notifySentBytes(bArr.length);
    }

    @Override // java.io.OutputStream
    public void write(byte[] bArr, int i, int i2) {
        this.out.write(bArr, i, i2);
        this.clientState.notifySentBytes(i2);
    }

    @Override // java.io.OutputStream
    public void write(int i) {
        this.out.write(i);
    }

    public void write(MqttWireMessage mqttWireMessage) {
        byte[] header = mqttWireMessage.getHeader();
        byte[] payload = mqttWireMessage.getPayload();
        this.out.write(header, 0, header.length);
        this.clientState.notifySentBytes(header.length);
        int i = 0;
        while (i < payload.length) {
            int min = Math.min(1024, payload.length - i);
            this.out.write(payload, i, min);
            i += 1024;
            this.clientState.notifySentBytes(min);
        }
        log.fine(CLASS_NAME, "write", "529", new Object[]{mqttWireMessage});
    }
}

package org.eclipse.paho.client.mqttv3.internal.wire;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import org.eclipse.paho.client.mqttv3.internal.ClientState;
import org.eclipse.paho.client.mqttv3.internal.ExceptionHelper;
import org.eclipse.paho.client.mqttv3.logging.Logger;
import org.eclipse.paho.client.mqttv3.logging.LoggerFactory;

/* loaded from: classes.dex */
public class MqttInputStream extends InputStream {
    private static final String CLASS_NAME = MqttInputStream.class.getName();
    private static final Logger log = LoggerFactory.getLogger(LoggerFactory.MQTT_CLIENT_MSG_CAT, CLASS_NAME);
    private ClientState clientState;
    private DataInputStream in;
    private byte[] packet;
    private long packetLen;
    private ByteArrayOutputStream bais = new ByteArrayOutputStream();
    private long remLen = -1;

    public MqttInputStream(ClientState clientState, InputStream inputStream) {
        this.clientState = null;
        this.clientState = clientState;
        this.in = new DataInputStream(inputStream);
    }

    @Override // java.io.InputStream
    public int read() {
        return this.in.read();
    }

    @Override // java.io.InputStream
    public int available() {
        return this.in.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.in.close();
    }

    public MqttWireMessage readMqttWireMessage() {
        try {
            if (!(this.remLen >= 0)) {
                this.bais.reset();
                byte readByte = this.in.readByte();
                this.clientState.notifyReceivedBytes(1);
                byte b = (byte) ((readByte >>> 4) & 15);
                if (b < 1 || b > 14) {
                    throw ExceptionHelper.createMqttException(32108);
                }
                this.remLen = MqttWireMessage.readMBI(this.in).getValue();
                this.bais.write(readByte);
                this.bais.write(MqttWireMessage.encodeMBI(this.remLen));
                this.packet = new byte[(int) (this.bais.size() + this.remLen)];
                this.packetLen = 0L;
            }
            if (this.remLen < 0) {
                return null;
            }
            readFully();
            this.remLen = -1L;
            byte[] byteArray = this.bais.toByteArray();
            System.arraycopy(byteArray, 0, this.packet, 0, byteArray.length);
            MqttWireMessage createWireMessage = MqttWireMessage.createWireMessage(this.packet);
            log.fine(CLASS_NAME, "readMqttWireMessage", "501", new Object[]{createWireMessage});
            return createWireMessage;
        } catch (SocketTimeoutException e) {
            return null;
        }
    }

    private void readFully() {
        int size = ((int) this.packetLen) + this.bais.size();
        int i = (int) (this.remLen - this.packetLen);
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        }
        int i2 = 0;
        while (i2 < i) {
            try {
                int read = this.in.read(this.packet, size + i2, i - i2);
                this.clientState.notifyReceivedBytes(read);
                if (read < 0) {
                    throw new EOFException();
                }
                i2 = read + i2;
            } catch (SocketTimeoutException e) {
                this.packetLen += i2;
                throw e;
            }
        }
    }
}
